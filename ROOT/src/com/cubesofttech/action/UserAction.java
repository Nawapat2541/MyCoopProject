package com.cubesofttech.action;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.BorrowDAO;
import com.cubesofttech.dao.DepartmentDAO;
import com.cubesofttech.dao.EquipmentDAO;
import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.dao.LeaveDAO;
import com.cubesofttech.dao.LeaveTypeDAO;
import com.cubesofttech.dao.LeaveUserDAO;
import com.cubesofttech.dao.NewsDAO;
import com.cubesofttech.dao.PositionDAO;
import com.cubesofttech.dao.RoleDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.Borrow;
import com.cubesofttech.model.Department;
import com.cubesofttech.model.Equipment;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.model.LeaveType;
import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.Position;
import com.cubesofttech.model.Role;
import com.cubesofttech.model.Timesheet;
import com.cubesofttech.model.User;
import com.cubesofttech.util.Convert;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.cubesofttech.util.MD5;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;


public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2280661337420278284L;
	private static final Integer Interger = null;
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public static final String User = "userList";
	public static final String ONLINEUSER = "onlineUser";

	@Autowired
	private NewsDAO newsDAO;
	
	@Autowired
	private EquipmentDAO equipmentDAO;

	@Autowired
	private LeaveTypeDAO leavetypeDAO;

	@Autowired
	private LeaveUserDAO leaveuserDAO;
	
	
	private User onlineUser = (User) request.getSession().getAttribute("onlineUser");

	private String confirmpassword;

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@Autowired
	private FileUploadDAO fileuploadDAO;
	
	@Autowired
	private BorrowDAO borrowDAO;


	private String endDate;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	

	@Autowired
	private LeaveDAO leaveDAO;
	
	@Autowired
	private PositionDAO positionDAO;
	
private String position_id;
	
	public String getPosition_id(){
		return position_id;
	}
	
	
	
	public void setPosition_id(String position_id){
		this.position_id = position_id;
	}
	

	private String department_id;

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private User user;

	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	private String userId;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String birthDate;

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	private String user_id;

	private String startDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	private String user_email;
	private String user_name;
	private String user_nickName;
	private String user_address;
	public String getUser_phonenum() {
		return user_phonenum;
	}

	public void setUser_phonenum(String user_phonenum) {
		this.user_phonenum = user_phonenum;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	private File fileUpload;
	private String fileUploadSize;
	private String fileUploadFileName;
	private String mypic;
	private String user_phonenum;
	private String user_gender;

	public String getMypic() {
		return mypic;
	}

	public void setMypic(String mypic) {
		this.mypic = mypic;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getFileUploadSize() {
		return fileUploadSize;
	}

	public void setFileUploadSize(String fileUploadSize) {
		this.fileUploadSize = fileUploadSize;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}


	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String list() {
		try {
			List<Map<String, Object>> cubesoftUsers = userDAO.Query_Userlist();
			request.setAttribute("cubesoftUsers", cubesoftUsers);
			// request.setAttribute("userList", userDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String resetLastyearQuota(){
		userDAO.resetLastyearQuota();
		return SUCCESS;
	}

	public String listUpdate() {
		try {
			List<Map<String, Object>> cubesoftUsers = userDAO.Query_Userlist();
			request.setAttribute("cubesoftUsers", cubesoftUsers);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String updateUserStatus() {
		try {

			//String enable = request.getParameter("enable");
			String userid = request.getParameter("userid");
			User u = userDAO.findById(userid);
			//toggle
			String status = "enable";
			if ("1".equals(u.getEnable())){
				u.setEnable("0");
				status = "disable";
			} else {
				u.setEnable("1");
				status = "enable";
			}

			userDAO.update(u);
			
	        Gson gson = new GsonBuilder().create();
	        String responseJSON = gson.toJson(u);
			log.debug(responseJSON);
			
			request.setAttribute("json",responseJSON);
			
			
			String JSON = "json";

			return JSON;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String openEdit() {
		try {
			User selectUser = userDAO.findById(userId);
			List<Map<String, Object>> departmentList = departmentDAO.sequense();
			
			List<Map<String, Object>> positionList = positionDAO.sequense();
			
			request.setAttribute("positionList", positionList);
			
			request.setAttribute("departmentList", departmentList);

			List<Role> roleList = roleDAO.findAll();
			request.setAttribute("roleList", roleList);

			request.setAttribute("userList", userDAO.sequense());

			request.setAttribute("selectUser", selectUser);

			List<Map<String, Object>> leavwait = leaveDAO.listwaitperson(String.valueOf(userId));
			List<Map<String, Object>> leavhis = leaveDAO.listoneperson(String.valueOf(userId));
			int sum_w = leavwait.size();
			int sum_h = leavhis.size();
			request.setAttribute("leaveW", sum_w);
			request.setAttribute("leaveH", sum_h);
			
			request.setAttribute("borrow_history", 	borrowDAO.findHistoryByUser(selectUser.getId()));
			

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String open() {
		try {
			
			List<Map<String, Object>> departmentList = departmentDAO.sequense();
			request.setAttribute("departmentList", departmentList);
			
			List<Map<String, Object>> positionList = positionDAO.sequense();
			request.setAttribute("positionList", positionList);
			
			List<Role> roleList = roleDAO.findAll();
			request.setAttribute("roleList", roleList);

			request.setAttribute("userList", userDAO.sequense());

			
			List<Map<String, Object>> leavwait = leaveDAO.listwaitperson(String.valueOf(userId));
			List<Map<String, Object>> leavhis = leaveDAO.listoneperson(String.valueOf(userId));
			int sum_w = leavwait.size();
			int sum_h = leavhis.size();
			request.setAttribute("leaveW", sum_w);
			request.setAttribute("leaveH", sum_h);
	
			

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String performEdit() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			log.info(logonUser);
			
			FileUpload fileupload = new FileUpload();
			int maxId = fileuploadDAO.getMaxId() + 1;
			User u = userDAO.findById(user.getId());
			if (fileUpload != null) {

				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				//long size = fileUpload.getUsableSpace();
				fileupload.setSize(fileUploadSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
				log.info("0");
				FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);
				int l = fileUploadFileName.length();
				int split = fileUploadFileName.indexOf(".");
				String name = fileUploadFileName.substring(0, split);
				String type = (String) fileUploadFileName.subSequence(split, l);

				fileupload.setFileId(maxId);
				fileupload.setUserId(logonUser);
				fileupload.setUserCreate(logonUser);
				fileupload.setName(name);
				fileupload.setType(type);
				log.info("1");
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				log.info("2");
				fileuploadDAO.save(fileupload);

				u.setPath("/upload/user/" + maxId + "_" + fileName);

				u.setName(u.getName());
				u.setNickName(u.getNickName());
				String passwor = password; // F56
				String passw = u.getPassword();
				String ed = "";
				ed = request.getParameter("endDate");
				log.info(ed);
				if (ed != null && !ed.equals("")) {
					Date endDate = Convert.parseDate(ed);
					u.setEndDate(endDate);
				}
				if (passwor.equalsIgnoreCase(passw)) {

					u.setPassword(passwor);
				} else if (!passwor.equalsIgnoreCase(passw)) {
					String password = MD5.getInstance().hashData(passwor.getBytes());
					u.setPassword(password);
				}
				String role = user.getRoleId();
				String depart = department_id;
				String posit = position_id;
				// String depart = request.getParameter("department.id");

				if (role != null) {
					u.setRoleId(role);
				}
				if (depart != null) {
					u.setDepartmentId(depart);
				}
				if (posit !=null) {
					u.setPositionId(posit);
				}
				u.setEmail(user_email);

				u.setManagerId(user.getManagerId());
				u.setAddress(user.getAddress());
				u.setTimeUpdate(DateUtil.getCurrentTime());

				String bd = birthDate;
				if (bd != null && !bd.equals("")) {
					Date birthDate = Convert.parseDate(bd);
					u.setBirthDate(birthDate);
				}

				if (startDate != null && !startDate.equals("")) {
					Date startDatex = Convert.parseDate(startDate);
					// u.setStartDate(startDate);
					u.setStartDate(startDatex);
				}
				if (page.equals("1")) {
					log.debug("user edit");
					u.setEmailHost(user.getEmailHost());
					userDAO.update(u);
				} else if (page.equals("2")) {
					log.debug("admin edit");
					u.setEnable(user.getEnable());
					u.setEmailEnable(user.getEmailEnable());

					// u.setEmailPassword(emailpassword);
					u.setEmployeeId(user.getEmployeeId());
				//	u.setPositionId(user.getPositionId());
					u.setPositionId(posit);
					u.setDepartmentId(depart);
					
					u.setWorkDayStart(user.getWorkDayStart());
					u.setWorkDayEnd(user.getWorkDayEnd());
					u.setWorkTimeStart(user.getWorkTimeStart());
					u.setWorkTimeEnd(user.getWorkTimeEnd());
					u.setEduInstitute1(user.getEduInstitute1());
					u.setEduInstitute2(user.getEduInstitute2());
					u.setEduInstitute3(user.getEduInstitute3());
					u.setEduInstitute4(user.getEduInstitute4());
					u.setEduDurStart1(user.getEduDurStart1());
					u.setEduDurStart2(user.getEduDurStart2());
					u.setEduDurStart3(user.getEduDurStart3());
					u.setEduDurStart4(user.getEduDurStart4());
					u.setEduDurEnd1(user.getEduDurEnd1());
					u.setEduDurEnd2(user.getEduDurEnd2());
					u.setEduDurEnd3(user.getEduDurEnd3());
					u.setEduDurEnd4(user.getEduDurEnd4());
					u.setEduDegree1(user.getEduDegree1());
					u.setEduDegree2(user.getEduDegree2());
					u.setEduDegree3(user.getEduDegree3());
					u.setEduDegree4(user.getEduDegree4());
					u.setLeaveQuota1(user.getLeaveQuota1());
					u.setLeaveQuota4(user.getLeaveQuota4());
					u.setLeaveQuota3(user.getLeaveQuota3());
					u.setPhonenum(user.getPhonenum());
					u.setGender(user.getGender());
					userDAO.update(u);
					userId = user.getId();

					User selectUser = userDAO.findById(userId);
					List<Map<String, Object>> departmentList = departmentDAO.sequense();

					List<Map<String, Object>> userList = userDAO.sequense();
					
					List<Map<String, Object>> positionList = positionDAO.sequense();
					
					request.setAttribute("positionList", positionList);


					request.setAttribute("departmentList", departmentList);

					request.setAttribute("roleList", roleDAO.findAll());

					request.setAttribute("userList", userList);

					request.setAttribute("selectUser", selectUser);

					List<Map<String, Object>> leavwait = leaveDAO.listwaitperson(String.valueOf(userId));
					List<Map<String, Object>> leavhis = leaveDAO.listoneperson(String.valueOf(userId));
					int sum_w = leavwait.size();
					int sum_h = leavhis.size();
					request.setAttribute("leaveW", sum_w);
					request.setAttribute("leaveH", sum_h);

				}
			}

			else {

				u.setName(user.getName());
				u.setNickName(user.getNickName());

				// String passwor = request.getParameter("user.password"); //
				// F56
				String passwor = password; // F56
				String passw = u.getPassword();
				String ed = "";
				ed = request.getParameter("endDate");
				if (ed != null && !ed.equals(null)) {
					Date endDate = Convert.parseDate(ed);
					u.setEndDate(endDate);
				}
				if (passwor.equalsIgnoreCase(passw)) {

					u.setPassword(passwor);
				} else if (!passwor.equalsIgnoreCase(passw)) {
					String password = MD5.getInstance().hashData(passwor.getBytes());
					u.setPassword(password);
				}
				

				// String depart = request.getParameter("department.id");
				String role = user.getRoleId();
				String depart = department_id;
				String posit = position_id;
				if (role != null) {
					u.setRoleId(role);
				}
				if (depart != null) {
					u.setDepartmentId(depart);
				}
				if (posit !=null){
					u.setPositionId(posit);
				}
				
				u.setEmail(user_email);

				u.setManagerId(user.getManagerId());
				u.setAddress(user.getAddress());
				u.setTimeUpdate(DateUtil.getCurrentTime());
				log.info("4");
				String bd = birthDate;
				if (bd != null && !bd.equals("")) {
					Date birthDate = Convert.parseDate(bd);
					u.setBirthDate(birthDate);
				}

				if (startDate != null && !startDate.equals("")) {
					Date startDatex = Convert.parseDate(startDate);
					u.setStartDate(startDatex);
				}
				if (page.equals("1")) {
					log.debug("user edit");
					u.setEmailHost(user.getEmailHost());
					userDAO.update(u);
				} else if (page.equals("2")) {
					log.debug("admin edit");
					u.setEnable(user.getEnable());
					u.setEmailEnable(user.getEmailEnable());

					// u.setEmailPassword(emailpassword);
					u.setEmployeeId(user.getEmployeeId());
				//	u.setPositionId(user.getPositionId());
					u.setDepartmentId(depart);
					u.setPositionId(posit);
					
					}
					u.setWorkDayStart(user.getWorkDayStart());
					u.setWorkDayEnd(user.getWorkDayEnd());
					u.setWorkTimeStart(user.getWorkTimeStart());
					u.setWorkTimeEnd(user.getWorkTimeEnd());
					u.setEduInstitute1(user.getEduInstitute1());
					u.setEduInstitute2(user.getEduInstitute2());
					u.setEduInstitute3(user.getEduInstitute3());
					u.setEduInstitute4(user.getEduInstitute4());
					u.setEduDurStart1(user.getEduDurStart1());
					u.setEduDurStart2(user.getEduDurStart2());
					u.setEduDurStart3(user.getEduDurStart3());
					u.setEduDurStart4(user.getEduDurStart4());
					u.setEduDurEnd1(user.getEduDurEnd1());
					u.setEduDurEnd2(user.getEduDurEnd2());
					u.setEduDurEnd3(user.getEduDurEnd3());
					u.setEduDurEnd4(user.getEduDurEnd4());
					u.setEduDegree1(user.getEduDegree1());
					u.setEduDegree2(user.getEduDegree2());
					u.setEduDegree3(user.getEduDegree3());
					u.setEduDegree4(user.getEduDegree4());
					u.setLeaveQuota1(user.getLeaveQuota1());
					u.setLeaveQuota4(user.getLeaveQuota4());
					u.setLeaveQuota3(user.getLeaveQuota3());
					u.setPhonenum(user.getPhonenum());
					u.setGender(user.getGender());

					userDAO.update(u);
				}

				userId = user.getId();

				User selectUser = userDAO.findById(userId);
				List<Map<String, Object>> departmentList = departmentDAO.sequense();

				List<Map<String, Object>> userList = userDAO.sequense();
				
				List<Map<String, Object>> positionList = positionDAO.sequense();
				
				request.setAttribute("positionList", positionList);

				request.setAttribute("departmentList", departmentList);

				request.setAttribute("roleList", roleDAO.findAll());

				request.setAttribute("userList", userList);

				request.setAttribute("selectUser", selectUser);

				List<Map<String, Object>> leavwait = leaveDAO.listwaitperson(String.valueOf(userId));
				List<Map<String, Object>> leavhis = leaveDAO.listoneperson(String.valueOf(userId));
				int sum_w = leavwait.size();
				int sum_h = leavhis.size();
				request.setAttribute("leaveW", sum_w);
				request.setAttribute("leaveH", sum_h);

			

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String changepass() {
		try {
			String oldpass_i = request.getParameter("oldpass_i");
			String oldpass_input = MD5.getInstance().hashData(oldpass_i.getBytes());
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");
			String newpass_insert = MD5.getInstance().hashData(newpass.getBytes());
			if (oldpass_input.equals(oldpass)) {
				User u = userDAO.findById(user.getId());
				u.setPassword(newpass_insert);
				u.setPasswordUpdate(DateUtil.getCurrentTime());
				userDAO.update(u);
				userId = user.getId();
				return SUCCESS;
			}

			userId = user.getId();
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String performAdd() {
		try {
			String email = request.getParameter("user.email");
//			String emailpas = request.getParameter("user.emailPassword");
//			String emailpass = MD5.getInstance().hashData(emailpas.getBytes());
			String phone = request.getParameter("user.phone");
			String nickname = request.getParameter("user.nickName");
//			String emailhost = request.getParameter("user.emailHost");
			String gender = request.getParameter("user.gender");
			String role = request.getParameter("user.roleId");
			String address = request.getParameter("user.address");
			String department = request.getParameter("user.departmentId");
			String position = request.getParameter("user.positionId");	
			String leaveQuota4 = request.getParameter("user.leaveQuota4");	
			BigDecimal lastYearQuota=new BigDecimal(leaveQuota4);

			
			user.setTimeCreate(DateUtil.getCurrentTime());
			user.setTimeUpdate(DateUtil.getCurrentTime());
			String bd = request.getParameter("birthDate");
			if (bd != null && !bd.equals("")) {
				Date birthDate = Convert.parseDate(bd);
				user.setBirthDate(birthDate);
			}
			String sd = request.getParameter("startDate");
			if (sd != null && !sd.equals("")) {
				Date startDate = Convert.parseDate(sd);
				user.setStartDate(startDate);
			}
			user.setEnable("1");
			user.setName(user.getName().trim());
			user.setEmail(email);
//			user.setEmailPassword(emailpass);
			user.setPhonenum(phone);
			user.setNickName(nickname);
//			user.setEmailHost(emailhost);
			user.setGender(gender);
			user.setRoleId(role);
			user.setAddress(address);
			user.setDepartmentId(department);
			user.setPositionId(position);
			user.setFlagSearch("1");
			user.setLeaveQuota4(lastYearQuota);
			
			
			
			userDAO.save(user);
			
			userId = user.getId();

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			
			
			request.setAttribute("flag", "1");
			return ERROR;
		}
	}

	public String Edit_myprofile() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			log.info(logonUser);		
	

			User u = userDAO.findById(user_id);
			log.debug(user_id + " aaa"); 
			u.setEmail(user_email);
			u.setName(user_name);
			u.setNickName(user_nickName);
			u.setAddress(user_address);
			u.setPhonenum(user_phonenum);
			u.setGender(user_gender);
			String bd = birthDate;
			if (bd != null && !bd.equals("")) {
				Date birthDate = Convert.parseDate(bd);
				u.setBirthDate(birthDate);
			}

			userDAO.update(u);
			FileUpload fileupload = new FileUpload();
			String picture = mypic;
			if ("".equals(picture)) {
				if (fileUpload != null) {
					int maxId = fileuploadDAO.getMaxId() + 1;
					ServletContext context = request.getServletContext();
					String fileServerPath = context.getRealPath("/");
					//long size = fileUpload.getUsableSpace();
					fileupload.setSize(fileUploadSize);
					String fileName = fileUploadFileName;
					fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
					FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);

					int l = fileUploadFileName.length();
					int split = fileUploadFileName.indexOf(".");
					String name = fileUploadFileName.substring(0, split);
					String type = (String) fileUploadFileName.subSequence(split, l);

					fileupload.setFileId(maxId);
					fileupload.setUserId(logonUser);
					fileupload.setUserCreate(logonUser);
					fileupload.setName(name);
					fileupload.setType(type);
					fileupload.setTimeCreate(DateUtil.getCurrentTime());
					fileuploadDAO.save(fileupload);

					u.setPath("/upload/user/" + maxId + "_" + fileName);
					userDAO.update(u);
				}
			} else { 
				if (fileUpload != null) {
					int maxId = fileuploadDAO.getMaxId();
					ServletContext context = request.getServletContext();
					String fileServerPath = context.getRealPath("/");
					//long size = fileUpload.getUsableSpace();
					fileupload.setSize(fileUploadSize);
					String fileName = fileUploadFileName;
					fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
					FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);

					int l = fileUploadFileName.length();
					int split = fileUploadFileName.indexOf(".");
					String name = fileUploadFileName.substring(0, split);
					String type = (String) fileUploadFileName.subSequence(split, l);

					fileupload.setFileId(maxId);
					fileupload.setUserId(logonUser);
					fileupload.setUserCreate(logonUser);
					fileupload.setName(name);
					fileupload.setType(type);
					fileupload.setTimeCreate(DateUtil.getCurrentTime());
					fileuploadDAO.update(fileupload);

					u.setPath("/upload/user/" + maxId + "_" + fileName);
					userDAO.update(u);
				}
			}
			request.setAttribute("logonUser", logonUser);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String open_myprofile() {
		try {

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			request.setAttribute("logonUser", logonUser);

			userId = ur.getId();
			user = userDAO.findById(userId);

			List<Department> departmentList = departmentDAO.findAll();

			request.setAttribute("user", user);
			request.setAttribute("userList", userDAO.findAll());
			request.setAttribute("departmentList", departmentList);
			request.setAttribute("roleList", roleDAO.findAll());

			int yearNow = DateUtil.checkCurrentYear();
			if (yearNow > 2500) {
				yearNow = yearNow - 543;
			}

			List<Map<String, Object>> myleaves = newsDAO.totallyleaves(logonUser, yearNow);
			request.setAttribute("myleaves", myleaves);

			List<Map<String, Object>> myobtainMoney = newsDAO.obtainTravel(logonUser);
			request.setAttribute("myobtainMoney", myobtainMoney);

			List<Map<String, Object>> mytravel = newsDAO.totaltravel(logonUser);
			request.setAttribute("mytravel", mytravel);
			
			List<Borrow> borrows = borrowDAO.findBorrowByUser(onlineUser.getId());
			
			log.info("borrows="+borrows);

			List<Equipment> equipments = new ArrayList<Equipment>(); 
			for (int i = 0; i < borrows.size(); i++) {
				String equipment_id = borrows.get(i).getEquipmentId();
				Equipment equipments2 = equipmentDAO.getById(Integer.parseInt(equipment_id));
				log.info("equipments2="+equipments2);
				equipments.add(equipments2);
				log.info("equipments="+equipments);
			}

			
			request.setAttribute("borrows", new Gson().toJson(borrows));
			request.setAttribute("equipments", new Gson().toJson(equipments));
			
			myleave();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void user_notifi() {

		try {
			String b = request.getParameter("user.id");
			Map<String,String> obj = new HashMap<>();
			List<Map<String, Object>> user = userDAO.findById2(b);
			String s = user.toString();
			if (s.equals("[]")) {
				String x = "0";
				obj.put("flag", x);
			} else {
				String a = "1";
				obj.put("flag", a);
			}
			Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
			String jsonObjStr = gson.toJson(obj);
			PrintWriter out = response.getWriter();
			out.print(jsonObjStr);
			out.flush();
			out.close();
			// Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy
			// HH:mm:ss").create();
			// String jsonObjStr = gson.toJson(mapObj);
		}
 
		catch (Exception e) {
			e.printStackTrace();

		}
  
	}  
	public String keepfacebook() throws Exception{
		try { 
			String userid = request.getParameter("userid");
			String facebookid = request.getParameter("id");
			User u = userDAO.findById(userid);
			//log.debug(userid + " aa");
			//log.debug(facebookid + "st");
			
			u.setFacebookid(facebookid);
			userDAO.update(u);
			
			userId = user.getId();
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}
	public String setFlagsearch(){
		try{
			String id = request.getParameter("id");
			user = userDAO.findById(id);
			log.debug(user);
			user.setFlagSearch("0");
			userDAO.update(user);
			return SUCCESS;
		}catch (Exception e){
			return ERROR;
		}
	}
	
	public String deleteUser(){
		try{
			String id = request.getParameter("id");
			User user = new User();
			user = userDAO.findById(id);
			log.debug(user);
			userDAO.delete(user);
			List<User> userList = userDAO.findAll();
			request.setAttribute(User, userList);
			return SUCCESS;
		}catch (Exception e){
			return ERROR;
		}
	}
	
	public String myleave() {
		try {
			User ur = new User();
			String userLogin = null;
			String type = request.getParameter("type");
			if (type == null) {
				ur = (User) request.getSession().getAttribute("onlineUser");
				userLogin = ur.getId();
			}
			else { 
				userLogin = request.getParameter("name1");
			}
			
			String listbyuser = request.getParameter("Id");

			DateTimeFormatter date1 = DateTimeFormatter.ofPattern("01-01-yyyy");
			LocalDate localDate = LocalDate.now();
			String s = "00:00:00.0";

			String start = request.getParameter("startdate");
			String end = request.getParameter("enddate");
			Timestamp start_date;
			Timestamp end_date;
			if (start == null && end == null) {
				start_date = DateUtil.dateToTimestamp(date1.format(localDate), s);
				end_date = DateUtil.changetoEndYear(date1.format(localDate));
			} else {
				start_date = DateUtil.dateFormatEdit(start);
				end_date = DateUtil.dateFormatEdit(end);
			}

			Date enddate = new Date(end_date.getTime());
			request.setAttribute("enddate", enddate);

			if (userLogin != listbyuser) {
				listbyuser = userLogin;
			}

			List<LeaveType> type_leave = leavetypeDAO.findAll_calendar();
			for (int i = 0; i < type_leave.size(); i++) {
				LeaveType leave = type_leave.get(i);
				request.setAttribute("type_" + leave.getLeaveTypeId(), leave.getLeaveTypeName());
			}
			List leavelist = leaveDAO.myLeavesList(userLogin, start_date, end_date);
			String status = "1";
			List leaveListDashboard = leaveDAO.myLeavesList(userLogin, start_date, end_date,status);


			request.setAttribute("leavelist", leavelist);

			Double leave_1 = 0.000, leave_2 = 0.000, leave_3 = 0.000, leave_5 = 0.000, leave_6 = 0.000;

			for (Iterator iterator = leavelist.iterator(); iterator.hasNext();) {
				Leaves leave = (Leaves) iterator.next();
			}
			

			for (Iterator iterator = leaveListDashboard.iterator(); iterator.hasNext();) {
				Leaves leaveDashboard = (Leaves) iterator.next();
				Double noday = leaveDashboard.getNoDay().doubleValue();
				if (leaveDashboard.getLeaveTypeId().contains("1")) {
					leave_1 = leave_1 + noday;
				}
				if (leaveDashboard.getLeaveTypeId().contains("2")) {
					leave_2 = leave_2 + noday;
				}
				if (leaveDashboard.getLeaveTypeId().contains("3")) {
					leave_3 = leave_3 + noday;
				}
				if (leaveDashboard.getLeaveTypeId().contains("5")) {
					leave_5 = leave_5 + noday;
				}
				if (leaveDashboard.getLeaveTypeId().contains("6")) {
					leave_6 = leave_6 + noday;
				}
			}

			
			request.setAttribute("leave_1", leave_1);
			request.setAttribute("leave_2", leave_2);
			request.setAttribute("leave_3", leave_3);
			request.setAttribute("leave_5", leave_5);
			request.setAttribute("leave_6", leave_6);

			DateTimeFormatter year2 = DateTimeFormatter.ofPattern("yyyy");
			Date day = new Date(System.currentTimeMillis());
			int year = Integer.parseInt(day.toString().substring(0, 4));
			Double quotaLastYear = leaveDAO.LastYearQuota(userLogin, year);
			Double quotaThisYear = leaveDAO.ThisYearQuota(userLogin);
			request.setAttribute("quotaThisYear", quotaThisYear);
			request.setAttribute("quotaLastYear", quotaLastYear);
			request.setAttribute("leave_6l", quotaLastYear - leave_6);

			Timestamp tend = Timestamp.valueOf(year + "-04-01 00:00:00"); //time end is april month
			Timestamp tnow = new Timestamp(day.getTime());

			request.setAttribute("tnow", tnow);
			request.setAttribute("tend", tend);

			
			
				
				
			String leave_7 = leaveDAO.sumWaitLeave(userLogin);
			request.setAttribute("leave_7", leave_7);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String BirthdaySummary() {
		try {

		List<Map<String, Object>> bd = userDAO.test_birthdaysummary();
		request.setAttribute("bd", bd);
		log.info("bd="+bd);
		
		
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}
	public String list2() {
		try {
			List<Map<String, Object>> cubesoftUsers = userDAO.Query_Userlist2();
			request.setAttribute("cubesoftUsers", cubesoftUsers);
			// request.setAttribute("userList", userDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String updateGender() {

		try {
			String gender = request.getParameter("gender");
			String[] setgender = gender.split("/");
			boolean result = false;
			/* System.out.println("setgender : " + setgender[1]); */
			if (setgender[1].equals("M") || setgender[1].equals("F")) {
				List<Map<String, Object>> user = userDAO.getGender(setgender);
				request.setAttribute("datauser", user);
				log.info(user);
				if (user.isEmpty() == false) {
					/* System.out.println(user.isEmpty()); */
					List<Map<String, Object>> xxx = userDAO.updateGender(setgender);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		return null;
	}
		
	
}
