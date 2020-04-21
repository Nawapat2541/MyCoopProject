/*package com.cubesofttech.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.DepartmentDAO;
import com.cubesofttech.dao.ExpTravelTypeDAO;
import com.cubesofttech.dao.RoleDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.User;
import com.cubesofttech.util.Convert;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.MD5;
import com.opensymphony.xwork2.ActionSupport;

public class ExpTravelTypeAction extends ActionSupport {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 2280661337420278284L;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Autowired
	private ExpTravelTypeDAO expTravelTypeDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	private User user;

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

	public String list() {
		try {
			request.setAttribute("expTravelTypeList", expTravelTypeDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String openEdit() {
		try {
			user = userDAO.findById(userId);
			request.setAttribute("departmentList", departmentDAO.findAll());
			request.setAttribute("roleList", roleDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());
			request.setAttribute("user", user);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String performEdit() {
		try {

			String page = request.getParameter("page");
			User u = userDAO.findById(user.getId());
			u.setName(user.getName());
			u.setEmailPassword(user.getEmailPassword());
			u.setNickName(user.getNickName());
			u.setRoleId(user.getRoleId());
			u.setDepartmentId(user.getDepartmentId());
			u.setManagerId(user.getManagerId());
			u.setAddress(user.getAddress());
			u.setTimeUpdate(DateUtil.getCurrentTime());
			String bd = request.getParameter("birthDate");
			if (bd != "") {
				Date birthDate = Convert.parseDate(bd);
				u.setBirthDate(birthDate);
			}
			String sd = request.getParameter("startDate");
			if (sd != "") {
				Date startDate = Convert.parseDate(sd);
				u.setStartDate(startDate);
			}
			if (page.equals("1")) {
				log.debug("user edit");
				u.setEmailHost(user.getEmailHost());
				userDAO.update(u);
			} else if (page.equals("2")) {
				log.debug("admin edit");
				u.setEnable(user.getEnable());
				u.setEmailEnable(user.getEmailEnable());
				u.setPassword(user.getPassword());
				u.setEmailPassword(user.getEmailPassword());
				u.setEmployeeId(user.getEmployeeId());
				u.setPositionId(user.getPositionId());
				String ed = request.getParameter("endDate");
				if (ed != "") {
					Date endDate = Convert.parseDate(ed);
					u.setEndDate(endDate);
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
				u.setLeaveQuota2(user.getLeaveQuota2());
				u.setLeaveQuota3(user.getLeaveQuota3());
				userDAO.update(u);
			}
			userId = user.getId();

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String changePassword() {
		try {
			String oldpassInput = MD5.getInstance().hashData(request.getParameter("oldpass_i").getBytes());
			String newpassInsert = MD5.getInstance().hashData(request.getParameter("newpass").getBytes());
			
			if (oldpassInput.equals(request.getParameter("oldpass"))) {
				User u = userDAO.findById(user.getId());
				u.setPassword(newpassInsert);
				u.setPasswordUpdate(DateUtil.getCurrentTime());
				userDAO.update(u);
				userId = user.getId();
				return SUCCESS;
			}
			
			userId = user.getId();
			return ERROR;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String performAdd() {
		try {
			user.setTimeCreate(DateUtil.getCurrentTime());
			user.setTimeUpdate(DateUtil.getCurrentTime());
			String bd = request.getParameter("birthDate");
			if (bd != "") {
				Date birthDate = Convert.parseDate(bd);
				user.setBirthDate(birthDate);
			}
			String sd = request.getParameter("startDate");
			if (sd != "") {
				Date startDate = Convert.parseDate(sd);
				user.setStartDate(startDate);
			}
			user.setEnable("1");
			user.setName(user.getName().trim());
			userDAO.save(user);
			userId = user.getId();
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String listUpdate() {
		try {
			User u = userDAO.findById(request.getParameter("userid"));
			u.setEnable(request.getParameter("enable"));
			userDAO.update(u);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

}
*/