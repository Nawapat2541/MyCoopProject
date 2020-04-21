package com.cubesofttech.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.RoleAuthorizedObjectDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.dao.UserRoleDAO;
import com.cubesofttech.model.RoleAuthorizedObject;
import com.cubesofttech.model.User;
import com.cubesofttech.model.UserRole;
import com.cubesofttech.service.LoginService;
import com.cubesofttech.system.Constant;
import com.cubesofttech.util.Token;
import com.opensymphony.xwork2.ActionSupport;

public class FacebookAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	final String redirect_uri = "https://localhost/fbAuth";
	final String client_id = "358584058066865";
	final String client_secret = "3436dadeed5ad4c19d9078a149551141";
	
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = new JSONObject();
	
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RoleAuthorizedObjectDAO roleAuthorizedObjectDAO;
	
	public void gofbAuth() {
		try {
			HttpSession httpSession = request.getSession();
			String page = request.getParameter("page");
			httpSession.setAttribute("page_redirect", page);
			final String state = Token.getToken();
			String url = "https://www.facebook.com/v3.2/dialog/oauth?"
					+ "client_id=" +client_id
					+"&redirect_uri="+redirect_uri
					+"&state="+state
					+"&scope=email";
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String fbAuth() {
//		log.info("Code :"+request.getParameter("code"));
//		log.info("State : "+request.getParameter("state"));
		HttpSession httpSession = request.getSession();
		String page = (String) httpSession.getAttribute("page_redirect");
		String code = request.getParameter("code");
		JSONObject jsonObject = generateFbToken(code);
		String accessToken = (String) jsonObject.get("access_token");
		jsonObject = verifyFbToken(accessToken);
		
		httpSession.setAttribute("FbJSON", jsonObject);
		try {
			if (page.equals("login")) {
				return "login";
			}
			if (page.equals("profile")) {
				return "profile";
			} else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String facebookLogin() {
		try {
			HttpSession httpSession = request.getSession();
			JSONObject jsonObject = (JSONObject) httpSession.getAttribute("FbJSON");
			String FbId = (String) jsonObject.get("id");
			User user = userDAO.findByFbId(FbId);

			if (user != null) {
				Set<String> userAuthority = new HashSet<>();
				Constant.onlineUserList.add(user.getId());

				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO
						.findByRoleId(user.getRoleId());

				userAuthority = loginService.addRoleByUserTable(roleAuthorizedObjectList, userAuthority);

				List<UserRole> userRoleList = userRoleDAO.findByUserId(user.getId());

				userAuthority = loginService.addRoleByUserRoleTabel(userRoleList, userAuthority);

				httpSession.setAttribute("user", user);
				httpSession.setAttribute("onlineUser", user);
				httpSession.setAttribute("userAuthority", userAuthority);
				httpSession.removeAttribute("FbJSON");
				return SUCCESS;
			} else {
				request.setAttribute("status", "facebookLink");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
		
	public String facebookLink() {
		try {
			HttpSession httpSession = request.getSession();
			JSONObject jsonObject = (JSONObject) httpSession.getAttribute("FbJSON");
			String FbId = (String) jsonObject.get("id");
			String userlogin = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDAO.findById(userlogin);
			String md5Password = loginService.generateMD5(password);
			if (user != null && md5Password.equals(user.getPassword())) {
				userDAO.linkFacebook(userlogin, FbId);
				Set<String> userAuthority = new HashSet<>();
				Constant.onlineUserList.add(user.getId());

				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO
						.findByRoleId(user.getRoleId());

				userAuthority = loginService.addRoleByUserTable(roleAuthorizedObjectList, userAuthority);

				List<UserRole> userRoleList = userRoleDAO.findByUserId(user.getId());

				userAuthority = loginService.addRoleByUserRoleTabel(userRoleList, userAuthority);

				httpSession.setAttribute("user", user);
				httpSession.setAttribute("onlineUser", user);
				httpSession.setAttribute("userAuthority", userAuthority);
				httpSession.removeAttribute("FbJSON");
				request.removeAttribute("status");
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String facebookLinkProfile() {
		try {
			HttpSession session = request.getSession();
			JSONObject jsonObject = (JSONObject) session.getAttribute("FbJSON");
			String FbId = (String) jsonObject.get("id");
			User user = (User) session.getAttribute("onlineUser");
			String userId = user.getId();
			userDAO.linkFacebook(userId, FbId);
			session.removeAttribute("FbJSON");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String unlinkFacebook() {
		try {
			String userId = request.getParameter("userId");
			userDAO.linkFacebook(userId, null);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public JSONObject generateFbToken(String code) {
		try {
			URL url = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"
					+ "client_id=" + client_id
					+ "&redirect_uri=" + redirect_uri 
					+ "&client_secret=" + client_secret
					+ "&code=" + code);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);

			InputStream inputStream = null;
			inputStream = conn.getInputStream();

			jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jsonObject;
	}
	
	public JSONObject verifyFbToken(String accessToken) {
		try {
			String url2 = "https://graph.facebook.com/v3.2/me?fields=id,name,email,picture,short_name&access_token="
					+ accessToken;
			URL obj = new URL(url2);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			// System.out.println("\nSending 'GET' request to URL : " + url2);
			// System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = "";
			StringBuffer response = new StringBuffer();
		
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			jsonObject = (JSONObject) jsonParser.parse(response.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return jsonObject;
	}
	
}
