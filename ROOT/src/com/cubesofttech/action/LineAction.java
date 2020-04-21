package com.cubesofttech.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.service.LineAPIService;
import com.cubesofttech.service.LoginService;
import com.cubesofttech.system.Constant;
import com.cubesofttech.util.Token;
import com.cubesofttech.dao.RoleAuthorizedObjectDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.dao.UserRoleDAO;
import com.cubesofttech.model.LineIdToken;
import com.cubesofttech.model.RoleAuthorizedObject;
import com.cubesofttech.model.User;
import com.cubesofttech.model.UserRole;
import com.cubesofttech.model.lineAccessToken;
import com.opensymphony.xwork2.ActionSupport;

public class LineAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6143686455584578743L;

	@Autowired
	private LineAPIService lineAPIService;

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RoleAuthorizedObjectDAO roleAuthorizedObjectDAO;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	public void goLineAuth() {
		try {
			HttpSession httpSession = request.getSession();
			String page = request.getParameter("page");
			httpSession.setAttribute("page_redirect", page);
			final String state = Token.getToken();
			final String nonce = Token.getToken();
			final String url = lineAPIService.getLineWebLoginUrl(state, nonce, Arrays.asList("openid", "profile"));
//			log.debug(url);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String lineAuth() {
		try {
			HttpSession httpSession = request.getSession();
			String code = request.getParameter("code");
//			String state = request.getParameter("state");
//			String scope = request.getParameter("scope");
//			String error = request.getParameter("error");
//			String errorCode = request.getParameter("errorCode");
//			String errorMessage = request.getParameter("errorMessage");
//			log.debug("parameter code : " + code);
//			log.debug("parameter state `: " + state);
//			log.debug("parameter scope : " + scope);
//			log.debug("parameter error : " + error);
//			log.debug("parameter errorCode : " + errorCode);
//			log.debug("parameter errorMessage : " + errorMessage);
			lineAccessToken token = lineAPIService.accessToken(code);
//			log.debug("scope : " + token.scope);
//			log.debug("access_token : " + token.access_token);
//			log.debug("token_type : " + token.token_type);
//			log.debug("expires_in : " + token.expires_in);
//			log.debug("refresh_token : " + token.refresh_token);
//			log.debug("id_token : " + token.id_token);
			httpSession.setAttribute("line_token", token);

			String page = (String) httpSession.getAttribute("page_redirect");
//			log.info(page);
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
			return ERROR;
		}
	}

	public String lineLogin() {
		try {
			HttpSession httpSession = request.getSession();
			lineAccessToken token = (lineAccessToken) httpSession.getAttribute("line_token");
			LineIdToken idToken = lineAPIService.idToken(token.id_token);
			User user = userDAO.findbyLineId(idToken.sub);

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
				return SUCCESS;
			} else {
				request.setAttribute("status", "lineLink");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String lineLink() {
		try {
			HttpSession httpSession = request.getSession();
			lineAccessToken token = (lineAccessToken) httpSession.getAttribute("line_token");
			LineIdToken idToken = lineAPIService.idToken(token.id_token);
			String lineId = idToken.sub;
//			log.info(token);
//			log.info(idToken);
//			log.info(lineId);
			String userlogin = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDAO.findById(userlogin);
			String md5Password = loginService.generateMD5(password);
			if (user != null && md5Password.equals(user.getPassword())) {
				userDAO.linkLine(userlogin, lineId);
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
				request.removeAttribute("status");
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String lineLinkProfile() {
		try {
			HttpSession httpSession = request.getSession();
			lineAccessToken token = (lineAccessToken) httpSession.getAttribute("line_token");
			LineIdToken idToken = lineAPIService.idToken(token.id_token);
			String lineId = idToken.sub;
			User user = (User) httpSession.getAttribute("onlineUser");
			String userId = user.getId();
			userDAO.linkLine(userId, lineId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String unlinkLine() {
		try {
			String userId = request.getParameter("userId");
			userDAO.linkLine(userId, null);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
