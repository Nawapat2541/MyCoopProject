package com.cubesofttech.action;

import java.io.Console;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.AuthorizedObjectDAO;
import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.dao.LeaveDAO;
import com.cubesofttech.dao.LeaveTypeDAO;
import com.cubesofttech.dao.LeaveUserDAO;
import com.cubesofttech.dao.RoleAuthorizedObjectDAO;
import com.cubesofttech.dao.TimesheetDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.Holiday;
import com.cubesofttech.model.LeaveType;
import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.Role;
import com.cubesofttech.model.RoleAuthorizedObject;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.util.RangeValueIterator.Element;
import com.opensymphony.xwork2.ActionSupport;

public class LeaveAction extends ActionSupport {
	private static final Integer Interger = null;
	private static final long serialVersionUID = 2280661337420278284L;
	private static String check_flag = "";
	private static String Global_flag = "";
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	@Autowired
	private LeaveDAO leaveDAO;

	@Autowired
	private LeaveTypeDAO leavetypeDAO;

	@Autowired
	private LeaveUserDAO leaveuserDAO;

	@Autowired
	private HolidayDAO holidayDAO;

	@Autowired
	private RoleAuthorizedObjectDAO roleAuthorizedObjectDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	public TimesheetDAO timesheetDAO;

	private String roleId;

	private Leaves leave;

	private int leaveId;

	private Role role;

	public Leaves getLeaves() {
		return leave;
	}

	@Autowired
	private AuthorizedObjectDAO authorizedObjectDAO;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setLeave(Leaves leave) {
		this.leave = leave;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	private String user;

	private String userId;

	private String userList;

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<User> leader;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<User> getLeader() {
		return leader;
	}

	public void setLeader(List<User> leader) {
		this.leader = leader;
	}

	public List<Map<String, Object>> approve;

	private String id;

	public List<Map<String, Object>> getApprove() {
		return approve;
	}

	public void setApprove(List<Map<String, Object>> approve) {
		this.approve = approve;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String list() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();
			HttpSession session = request.getSession();
			String listbyuser = request.getParameter("Id");
			if (userLogin != listbyuser) {
				listbyuser = userLogin;
			}
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			List<Map<String, Object>> leave = leaveDAO.listoneperson(listbyuser); // พัง
			request.setAttribute("leave", leave);

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
			request.setAttribute("leavenameList", leavenameList);
			// request.setAttribute("leaveList", leaveDAO.findAll());

			Timestamp start_date = DateUtil.dateToTimestamp("01-01-" + DateUtil.getYear(), "00:00");
			Timestamp end_date = DateUtil.dateToTimestamp("31-12-" + DateUtil.getYear(), "00:00");

			request.setAttribute("leaveList", leaveDAO.searchtable2(start_date, end_date, userLogin));

			request.setAttribute("leavetypeList", leavetypeDAO.findAll2());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());

			List<LeaveType> type_leave = leavetypeDAO.findAll();
			request.setAttribute("leavetypelistChoice", type_leave);
			request.setAttribute("type_1", type_leave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", type_leave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", type_leave.get(2).getLeaveTypeName());
			request.setAttribute("type_4", type_leave.get(3).getLeaveTypeName());
			request.setAttribute("type_5", type_leave.get(4).getLeaveTypeName());
			request.setAttribute("type_6", type_leave.get(5).getLeaveTypeName());
			myleave();
			return SUCCESS;
		} catch (Exception e) {

			e.printStackTrace();
			return ERROR;
		}
	}

	public String openEdit() {
		try {

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			User ur = (User) request.getSession().getAttribute("onlineUser"); // wishida
			String userLogin = ur.getId();

			String userId = request.getParameter("userId"); // applicant = null
			if (userId == null || userId == userLogin) { // null not in loop
				userId = userLogin;
			}
			if (userId == null && userId == userLogin) {
				List<User> leader = userDAO.findBySelect(userId);
				request.setAttribute("leader", leader);
				request.setAttribute("userId", userId);
			}
			List<User> leader = userDAO.findBySelect(userId);
			request.setAttribute("leader", leader);
			request.setAttribute("userId", userId);

			List<Map<String, Object>> leaveList = leaveDAO.findLeave();
			request.setAttribute("leaveList", leaveList);
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String select() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser"); //
			String userLogin = ur.getId();

			String userId = request.getParameter("userId"); // applicant
			request.setAttribute("userList", userDAO.findAll());
			if (userId == null || userId == userLogin) { // null not in loop
				userId = userLogin;
			}
			if (userId == null && userId == userLogin) {
				List<User> leader = userDAO.findBySelect(userId);
				request.setAttribute("leader", leader);
				request.setAttribute("userId", userId);
			}
			leader = userDAO.findBySelect(userId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String add() {

		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();// online user
			String userId = request.getParameter("name");

			String leaveStatusId = request.getParameter("leaveStatusId");
			String apprUserId = request.getParameter("apprUserId");
			String description = request.getParameter("description");
			String halfDay = request.getParameter("halfDay"); // null set 0
			String reason = request.getParameter("reason");

			String from = request.getParameter("from");
			String to = request.getParameter("to");

			Timestamp start_date = DateUtil.dateFormatEdit(from);// changeformat
			Timestamp end_date = DateUtil.dateFormatEdit(to);// changeformat
			String noDay = request.getParameter("noDay");
			BigDecimal no_day = new BigDecimal(noDay);

			Integer l = leaveDAO.getMaxId() + 1;
			Leaves le = new Leaves();
			le.setLeaveId(l);
			String TypeId = request.getParameter("leaveTypeId");
			if (!TypeId.equals("1") && !TypeId.equals("2") && !TypeId.equals("3") && !TypeId.equals("4")
					&& !TypeId.equals("5") && !TypeId.equals("6")) {
				le.setLeaveTypeId("9");
			} else {
				le.setLeaveTypeId(TypeId);
			}

			le.setLeaveStatusId(leaveStatusId);
			le.setHalfDay(halfDay);
			String hd = request.getParameter("halfDay");
			if (hd == null) {
				le.setHalfDay("0");
			}
			le.setUserId(userId);
			le.setApprUserId(apprUserId);
			le.setStartDate(start_date);
			le.setEndDate(end_date);
			le.setDescription(description);
			le.setReason(reason);
			le.setNoDay(no_day);
			le.setUserCreate(userLogin);
			le.setUserUpdate(userLogin);
			le.setTimeCreate(DateUtil.getCurrentTime());
			le.setTimeUpdate(DateUtil.getCurrentTime());

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
			request.setAttribute("leavenameList", leavenameList);
			request.setAttribute("leavenameList", leaveDAO.findAll());
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("test", "test");
			request.setAttribute("id", userId);

			leaveDAO.save(le);
			return SUCCESS;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String changesleader() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser"); // wishida
			String userLogin = ur.getId();

			String userId = request.getParameter("userId"); // applicant = null
			if (userId == null || userId == userLogin) {
				userId = userLogin;
			}

			String apprUserId = request.getParameter("apprUserId"); // apirat.c
			request.setAttribute("userList", userDAO.findAll());

			if (apprUserId != null) {
				List<Map<String, Object>> approve = userDAO.findByApprove(apprUserId); // null
				request.setAttribute("approve", approve);
				request.setAttribute("apprUserId", apprUserId);
			}
			approve = userDAO.findByApprove(apprUserId);
			request.setAttribute("userId", userId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String performEdit() { // leave form with edit and approve
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			String user_name = request.getParameter("user_id");
			request.setAttribute("user_name", user_name);
			request.setAttribute("flag_search", "1");
			User ur = (User) request.getSession().getAttribute("onlineUser");
			HttpSession session = request.getSession();
			String vv = "leave.approve";
			String x = ur.getRoleId();
			int flag = 0;
			if (x != null) {
				Set<String> userAuthority = new HashSet<>();
				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO.findByRoleId(x);
				if (roleAuthorizedObjectList != null) {
					Iterator<RoleAuthorizedObject> it = roleAuthorizedObjectList.iterator();
					while (it.hasNext()) {
						RoleAuthorizedObject rao = it.next();
						userAuthority.add(rao.getAuthorizedObjectId());
						for (Object test : userAuthority) {
							if (test.toString().indexOf(vv) != -1) {
								flag = 1;

							}
						}
					}
				}
				session.setAttribute("userAuthority", userAuthority);
			}
			request.setAttribute("flag", flag);
			String id = request.getParameter("Id");
			request.setAttribute("userList", userDAO.findAll());

			if (id != null) {
				List<Map<String, Object>> approve = leaveDAO.approverform(id);
				request.setAttribute("approve", approve);
				request.setAttribute("id", id);
			}

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
			request.setAttribute("leavenameList", leavenameList);
			request.setAttribute("leaveList", leaveDAO.findAll());
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());

			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String selectapprove() {
		try {
			String userId = request.getParameter("userId"); // applicant
			request.setAttribute("userList", userDAO.findAll());
			if (userId != null) {
				List<User> leader = userDAO.findBySelect(userId);
				request.setAttribute("leader", leader);
				request.setAttribute("userId", userId);
			}
			leader = userDAO.findBySelect(userId);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String approve() { // leave edit
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();

			String leaveid = request.getParameter("leaveId");
			int leave_Id = Integer.valueOf(leaveid);

			String userId = request.getParameter("name");
			String leaveStatusId = request.getParameter("leaveStatusId");
			String apprUserId = request.getParameter("apprUserId"); // have a
																	// data
			String description = request.getParameter("description");
			String TypeId = request.getParameter("leaveTypeId");
			String reason = request.getParameter("reason");// "" free

			String noDay = request.getParameter("noDay");
			BigDecimal no_day = new BigDecimal(noDay);

			Leaves le = leaveDAO.findByLeaveId(leaveId);

			le.setLeaveId(leave_Id);
			if (!TypeId.equals("1") && !TypeId.equals("2") && !TypeId.equals("3") && !TypeId.equals("4")
					&& !TypeId.equals("5") && !TypeId.equals("6")) {
				le.setLeaveTypeId("9");
			} else {
				le.setLeaveTypeId(TypeId);
			}
			le.setLeaveStatusId(leaveStatusId);
			le.setDescription(description);
			le.setReason(reason);
			le.setNoDay(no_day); // error
			le.setUserUpdate(userLogin);
			le.setUserId(userId); // get applicant
			le.setApprUserId(apprUserId);
			le.setTimeUpdate(DateUtil.getCurrentTime());

			String halfDay = request.getParameter("halfDay");
			if (halfDay == null) {
				le.setHalfDay("0");
			} else if (halfDay != "") {
				le.setHalfDay(halfDay);
			}

			String startDate = request.getParameter("startDate");
			if (startDate != "") {
				Timestamp start_date = DateUtil.dateFormatEdit(startDate);
				le.setStartDate(start_date);
			}
			String endDate = request.getParameter("endDate");
			if (endDate != "") {
				Timestamp end_date = DateUtil.dateFormatEdit(endDate);
				le.setEndDate(end_date);
			}

			List<Leaves> leavenameList = leaveDAO.findAll();
			request.setAttribute("leavenameList", leavenameList);
			request.setAttribute("leavenameList", leaveDAO.findAll());
			request.setAttribute("leavetypeList", leavetypeDAO.findAll());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());

			leaveDAO.update(le);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String changesapprove() {
		try {
			String apprUserId = request.getParameter("apprUserId");
			request.setAttribute("userList", userDAO.findAll());

			if (apprUserId != null) {
				List<Map<String, Object>> approve = leaveDAO.approverform(apprUserId);
				request.setAttribute("approve", approve);
				request.setAttribute("apprUserId", apprUserId);
			}
			approve = leaveDAO.approverform(apprUserId);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String leave_wlist() { // link from user page
		try {
			String listbyuser = request.getParameter("Id");

			List<Map<String, Object>> oneperson = leaveDAO.listwaitperson(listbyuser);
			request.setAttribute("oneperson", oneperson);

			request.setAttribute("userList", userDAO.findAll());

			request.setAttribute("leaveoneuser", listbyuser);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchleave() {
		try {
			String userSelect = request.getParameter("name1");
			String leaveStatus = request.getParameter("appr");
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");

			Timestamp start_date = DateUtil.changeYearStart(startdate);
			Timestamp end_date = DateUtil.changeYearEnd(enddate);
			if (!"All".equals(userSelect)) { // selected user
				if (leaveStatus.equals("3")) {
					request.setAttribute("leaveList", leaveDAO.searchtable2(start_date, end_date, userSelect));
				} else {
					request.setAttribute("leaveList",
							leaveDAO.searchtable2(start_date, end_date, userSelect, leaveStatus));
				}
			} else {// not select user
				if (leaveStatus.equals("3")) { // all status
					request.setAttribute("leaveList", leaveDAO.searchtable(start_date, end_date, userSelect));
				} else {
					request.setAttribute("leaveList",
							leaveDAO.searchtable(start_date, end_date, userSelect, leaveStatus));
				}
			}
			request.setAttribute("flag_search", "1");
			request.setAttribute("appr", leaveStatus);
			request.setAttribute("userId", userSelect);
			request.setAttribute("userSelect", userSelect);

			List<Map<String, Object>> cubeUser = userDAO.allName();
			request.setAttribute("cubeUser", cubeUser);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			Timestamp startDate = DateUtil.changeYearStart(startdate);
			request.setAttribute("startdate", startDate);

			Timestamp endDate = DateUtil.changeYearEnd(enddate);
			request.setAttribute("enddate", endDate);

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
			request.setAttribute("leavenameList", leavenameList);
			List<LeaveType> type_leave = leavetypeDAO.findAll();

			request.setAttribute("type_1", type_leave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", type_leave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", type_leave.get(2).getLeaveTypeName());
			request.setAttribute("type_4", type_leave.get(3).getLeaveTypeName());
			request.setAttribute("type_5", type_leave.get(4).getLeaveTypeName());
			request.setAttribute("type_6", type_leave.get(5).getLeaveTypeName());
			request.setAttribute("leavetypelistChoice", type_leave); // not in use because change to query by
																		// leavestatus

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	// use for search leaves are not approve by type
	public String searchleaveapproved() {
		try {
			String userSelect = request.getParameter("name1");
			String typeLeaves = request.getParameter("type");
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");

			Timestamp start_date = DateUtil.changeYearStart(startdate);
			Timestamp end_date = DateUtil.changeYearEnd(enddate);
			if (!"All".equals(userSelect)) {
				if (typeLeaves.equals("All")) {
					request.setAttribute("leave", leaveDAO.searchapproved(start_date, end_date, userSelect));
				} else {
					request.setAttribute("leave", leaveDAO.searchtable3(start_date, end_date, userSelect, typeLeaves)); // search
																														// by
																														// type
				}
			} else {
				if (typeLeaves.equals("All")) {
					request.setAttribute("leave", leaveDAO.searchapprovedall(start_date, end_date, userSelect));
				} else {
					request.setAttribute("leave", leaveDAO.searchtable4(start_date, end_date, userSelect, typeLeaves));
				}
			}
			request.setAttribute("flag_search", "1");
			request.setAttribute("type", typeLeaves);
			request.setAttribute("userId", userSelect);
			request.setAttribute("userSelect", userSelect);

			List<Map<String, Object>> cubeUser = userDAO.allName();
			request.setAttribute("cubeUser", cubeUser);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			Timestamp startDate = DateUtil.changeYearStart(startdate);
			request.setAttribute("startdate", startDate);

			Timestamp endDate = DateUtil.changeYearEnd(enddate);
			request.setAttribute("enddate", endDate);

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
			request.setAttribute("leavenameList", leavenameList);
			List<LeaveType> type_leave = leavetypeDAO.findAll();
			request.setAttribute("type_1", type_leave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", type_leave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", type_leave.get(2).getLeaveTypeName());
			request.setAttribute("type_4", type_leave.get(3).getLeaveTypeName());
			request.setAttribute("type_5", type_leave.get(4).getLeaveTypeName());
			request.setAttribute("type_6", type_leave.get(5).getLeaveTypeName());
			request.setAttribute("leavetypelistChoice", type_leave);
			myleave();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchmyleave() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();

			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String s = "00:00:00.0";

			Timestamp start_date = DateUtil.dateToTimestamp(startdate, s);
			Timestamp end_date = DateUtil.changeYearEnd(enddate);

			List<Map<String, Object>> leave = leaveDAO.searchtable2(start_date, end_date, userLogin);

			request.setAttribute("leave", leave);

			request.setAttribute("userList", userDAO.findAll());
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());

			Timestamp startDate = DateUtil.changeYearStart(startdate);
			request.setAttribute("startdate", startDate);
			Timestamp endDate = DateUtil.changeYearEnd(enddate);
			request.setAttribute("enddate", endDate);

			request.setAttribute("userLogin", userLogin);
			BigDecimal x1 = new BigDecimal(0);
			BigDecimal x2 = new BigDecimal(0);
			BigDecimal x3 = new BigDecimal(0);
			BigDecimal x4;

			List<Map<String, Object>> wanla1 = leaveDAO.findPatiwanla(userLogin, 1, startDate, endDate);
			List<Map<String, Object>> wanla2 = leaveDAO.findPatiwanla(userLogin, 2, startDate, endDate);
			List<Map<String, Object>> wanla3 = leaveDAO.findPatiwanla(userLogin, 3, startDate, endDate);
			int n1 = wanla1.size();
			int n2 = wanla2.size();
			int n3 = wanla3.size();
			for (int i = 0; i < n1; i++) {
				BigDecimal a = (BigDecimal) wanla1.get(i).get("no_day");

				BigDecimal b = a;

				x1 = b.add(x1);

				request.setAttribute("x1", x1);

			}

			for (int i = 0; i < n2; i++) {
				BigDecimal a = (BigDecimal) wanla2.get(i).get("no_day");

				BigDecimal b = a;

				x2 = b.add(x2);

				request.setAttribute("x2", x2);

			}

			for (int i = 0; i < n3; i++) {
				BigDecimal a = (BigDecimal) wanla3.get(i).get("no_day");

				BigDecimal b = a;

				x3 = b.add(x3);

				request.setAttribute("x3", x3);

			}
			x4 = x1.add(x2).add(x3);
			request.setAttribute("x4", x4);

			List<LeaveType> type_leave = leavetypeDAO.findAll_calendar();
			request.setAttribute("type_1", type_leave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", type_leave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", type_leave.get(2).getLeaveTypeName());

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String listmy() {
		try {
			String check_flag1 = request.getParameter("flag");
			String useradd = request.getParameter("useradd");

			if (check_flag1 != null) {
				check_flag = check_flag1;

				request.setAttribute("flag_1", check_flag1);
				String date = request.getParameter("date");
				request.setAttribute("date_calendar", date);
			}
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			request.setAttribute("useradd", useradd);
			HttpSession session = request.getSession();
			User ur = (User) request.getSession().getAttribute("onlineUser"); // wishida
			String userLogin = ur.getId();
			String date = request.getParameter("date");
			request.setAttribute("date", date); // Set Date from Click Calendar
			String vv = "leave.approve";
			String x = ur.getRoleId();
			int flag = 0;
			if (x != null) {
				Set<String> userAuthority = new HashSet<>();
				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO.findByRoleId(x);
				if (roleAuthorizedObjectList != null) {
					Iterator<RoleAuthorizedObject> it = roleAuthorizedObjectList.iterator();
					while (it.hasNext()) {
						RoleAuthorizedObject rao = it.next();
						userAuthority.add(rao.getAuthorizedObjectId());
						for (Object test : userAuthority) {
							if (test.toString().indexOf(vv) != -1) {
								flag = 1;
							}
						}
					}
				}
				session.setAttribute("userAuthority", userAuthority);
			}
			request.setAttribute("flag", flag);
			String userId = request.getParameter("userId"); // applicant = null
			if (userId == null || userId == userLogin) { // null not in loop
				userId = userLogin;
			}
			if (userId == null && userId == userLogin) {
				List<User> leader = userDAO.findBySelect(userId);
				request.setAttribute("leader", leader);
				request.setAttribute("userId", userId);
			}
			List<User> leader = userDAO.findBySelect(userId);
			request.setAttribute("leader", leader);
			request.setAttribute("userId", userId);

			List<Map<String, Object>> leaveList = leaveDAO.findLeave();
			request.setAttribute("leaveList", leaveList);

			List<LeaveType> leavetypeList = leavetypeDAO.findAll_calendar();
			request.setAttribute("leavetypeList", leavetypeList);

			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());
			List<Map<String, Object>> holidayList = leaveDAO.findHoliday();
			request.setAttribute("holidayList", holidayList);
			List<Map<String, Object>> setHoli = timesheetDAO.findHoliday2();
			request.setAttribute("setHoli", setHoli);
			List<Map<String, Object>> cutholiday = leaveDAO.findHoliday3();
			request.setAttribute("cutholiday", cutholiday);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String changesmyleader() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser"); // wishida
			String userLogin = ur.getId();

			String userId = request.getParameter("userId"); // applicant = null
			if (userId == null || userId == userLogin) {
				userId = userLogin;
			}

			String apprUserId = request.getParameter("apprUserId"); // apirat.c
			request.setAttribute("userList", userDAO.findAll());

			if (apprUserId != null) {
				List<Map<String, Object>> approve = userDAO.findByApprove(apprUserId); // null
				request.setAttribute("approve", approve);
				request.setAttribute("apprUserId", apprUserId);
			}
			approve = userDAO.findByApprove(apprUserId);
			request.setAttribute("userId", userId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String selectmyleader() {
		try {
			String userId = request.getParameter("userId"); // applicant
			request.setAttribute("userList", userDAO.findAll());
			if (userId != null) {
				List<User> leader = userDAO.findBySelect(userId);
				request.setAttribute("leader", leader);
				request.setAttribute("userId", userId);
			}
			leader = userDAO.findBySelect(userId);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String addmy() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();// online user
			// getParameter
			String userId = request.getParameter("name");
			String leaveStatusId = request.getParameter("leaveStatusId");
			String apprUserId = request.getParameter("apprUserId"); // Approvers
			String description = request.getParameter("description");
			String halfDay = request.getParameter("halfDay");// null set 0
			String reason = request.getParameter("reason");
			String time_hours = request.getParameter("comboA");
			String from = request.getParameter("from");
			String to = request.getParameter("to");

			java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(from);
			java.sql.Date start_date1 = new java.sql.Date(utilDate.getTime());

			Timestamp start_date = DateUtil.dateFormatEdit(from);// changeformat
			Timestamp end_date = DateUtil.dateFormatEdit(to);// changeformat
			String noDay = request.getParameter("noDay");
			BigDecimal no_day = new BigDecimal(noDay);
			BigDecimal time_comboA = new BigDecimal(time_hours);
			BigDecimal result = no_day.add(time_comboA);
			Integer l = leaveDAO.getMaxId() + 1;
			Leaves le = new Leaves();
			le.setLeaveId(l);
			String TypeId = request.getParameter("leaveTypeId");
			log.debug(TypeId);
			if (!TypeId.equals("1") && !TypeId.equals("2") && !TypeId.equals("3") && !TypeId.equals("4")
					&& !TypeId.equals("5") && !TypeId.equals("6")) {
				le.setLeaveTypeId("9");
			} else {
				le.setLeaveTypeId(TypeId);
			}
			if (leaveStatusId == null) {
				le.setLeaveStatusId("0");
			} else {
				le.setLeaveStatusId(leaveStatusId);
			}

			le.setHalfDay(halfDay);

			String hd = request.getParameter("halfDay");
			if (hd == null) {
				le.setHalfDay("0");
			}
			le.setUserId(userId);
			le.setApprUserId(apprUserId);
			le.setStartDate(start_date);
			le.setEndDate(end_date);
			le.setDescription(description);
			le.setReason(reason);
			le.setNoDay(result);
			le.setUserCreate(userLogin);
			le.setUserUpdate(userLogin);
			le.setTimeCreate(DateUtil.getCurrentTime());
			le.setTimeUpdate(DateUtil.getCurrentTime());

			List<Map<String, Object>> leavenameList = leaveDAO.findLeave();

			request.setAttribute("leavenameList", leavenameList);
			request.setAttribute("leavenameList", leaveDAO.findAll());
			request.setAttribute("leavetypeList", leavetypeDAO.findAll());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());

			leaveDAO.save(le);
			request.setAttribute("flag", check_flag);
			String status = "";
			Map<String, String> obj = new HashMap<>();

			if (check_flag.equals("1")) {
				check_flag = "";
				return SUCCESS;
			}

			request.setAttribute("flag12", start_date1);
			return INPUT;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String listone() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			check_flag = "1";
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();
			HttpSession session = request.getSession();
			String listbyuser = request.getParameter("Id");

			DateTimeFormatter date1 = DateTimeFormatter.ofPattern("01-01-yyyy");
			DateTimeFormatter dateNow = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.now();
			String s = "00:00:00.0";

			Timestamp start_date = DateUtil.dateToTimestamp(date1.format(localDate), s);
			Timestamp end_date = DateUtil.changeYearEnd(dateNow.format(localDate));

			if (userLogin != listbyuser) {
				listbyuser = userLogin;
			}
			List<Map<String, Object>> oneperson = leaveDAO.searchtable2(start_date, end_date, userLogin);
			request.setAttribute("oneperson", oneperson);
			BigDecimal x1 = new BigDecimal(0);
			BigDecimal x2 = new BigDecimal(0);
			BigDecimal x3 = new BigDecimal(0);
			BigDecimal x4 = new BigDecimal(0);
			List<Map<String, Object>> wanla1 = leaveDAO.findPatiwanla(userLogin, 1, start_date, end_date);
			List<Map<String, Object>> wanla2 = leaveDAO.findPatiwanla(userLogin, 2, start_date, end_date);
			List<Map<String, Object>> wanla3 = leaveDAO.findPatiwanla(userLogin, 3, start_date, end_date);
			List<Map<String, Object>> wanla4 = leaveDAO.findPatiwanla(userLogin, 5, start_date, end_date);
			int n1 = wanla1.size();
			int n2 = wanla2.size();
			int n3 = wanla3.size();
			int n4 = wanla4.size();
			for (int i = 0; i < n1; i++) {
				BigDecimal a = (BigDecimal) wanla1.get(i).get("no_day");

				BigDecimal b = a;

				x1 = b.add(x1);

				request.setAttribute("y1", x1);

			}

			for (int i = 0; i < n2; i++) {
				BigDecimal a = (BigDecimal) wanla2.get(i).get("no_day");

				BigDecimal b = a;

				x2 = b.add(x2);

				request.setAttribute("y2", x2);

			}

			for (int i = 0; i < n3; i++) {
				BigDecimal a = (BigDecimal) wanla3.get(i).get("no_day");

				BigDecimal b = a;

				x3 = b.add(x3);

				request.setAttribute("y3", x3);

			}
			for (int i = 0; i < n3; i++) {
				BigDecimal a = (BigDecimal) wanla3.get(i).get("no_day");

				BigDecimal b = a;

				x3 = b.add(x3);

				request.setAttribute("y3", x3);

			}
			for (int i = 0; i < n4; i++) {
				BigDecimal a = (BigDecimal) wanla4.get(i).get("no_day");

				BigDecimal b = a;

				x4 = b.add(x4);

				request.setAttribute("y4", x4);

			}
			// x4 = x1.add(x2).add(x3);
			request.setAttribute("userList", userDAO.findAll());

			request.setAttribute("listbyuser", listbyuser);
			// request.setAttribute("x4", x4);
			request.setAttribute("leavetypeList", leavetypeDAO.findAll());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());
			String vv = "leave.approve";
			String x = ur.getRoleId();
			int flag = 0;
			if (x != null) {
				Set<String> userAuthority = new HashSet<>();
				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO.findByRoleId(x);
				if (roleAuthorizedObjectList != null) {
					Iterator<RoleAuthorizedObject> it = roleAuthorizedObjectList.iterator();
					while (it.hasNext()) {
						RoleAuthorizedObject rao = it.next();
						userAuthority.add(rao.getAuthorizedObjectId());
						for (Object test : userAuthority) {
							if (test.toString().indexOf(vv) != -1) {
								flag = 1;

							}
						}
					}

				}
				session.setAttribute("userAuthority", userAuthority);
			}
			request.setAttribute("flag", flag);
			request.setAttribute("aoList", authorizedObjectDAO.findAll());
			request.setAttribute("raoList", roleAuthorizedObjectDAO.findByRoleId(roleId));

			List<LeaveType> type_leave = leavetypeDAO.findAll_calendar();
			request.setAttribute("type_1", type_leave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", type_leave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", type_leave.get(2).getLeaveTypeName());
			request.setAttribute("type_4", type_leave.get(3).getLeaveTypeName());
			request.setAttribute("type_5", type_leave.get(4).getLeaveTypeName());
			request.setAttribute("type_6", type_leave.get(5).getLeaveTypeName());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String myEdit() {
		try {

			String flag_checkform = request.getParameter("flag");

			if (flag_checkform != null) {
				Global_flag = flag_checkform;
				String user = request.getParameter("thisuser");
				request.setAttribute("userId", user);
				request.setAttribute("flag_search", flag_checkform);

			}

			User ur = (User) request.getSession().getAttribute("onlineUser");
			HttpSession session = request.getSession();
			int flag = 0;
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			String vv = "leave.approve";
			String id = request.getParameter("Id");
			String x = ur.getRoleId();

			if (id != null) {
				List<Map<String, Object>> approve = leaveDAO.approverform(id);
				request.setAttribute("approve", approve);
				request.setAttribute("id", id);
			}

			if (x != null) {
				Set<String> userAuthority = new HashSet<>();
				List<RoleAuthorizedObject> roleAuthorizedObjectList = roleAuthorizedObjectDAO.findByRoleId(x);
				if (roleAuthorizedObjectList != null) {
					Iterator<RoleAuthorizedObject> it = roleAuthorizedObjectList.iterator();
					while (it.hasNext()) {
						RoleAuthorizedObject rao = it.next();
						userAuthority.add(rao.getAuthorizedObjectId());
						for (Object test : userAuthority) {
							if (test.toString().indexOf(vv) != -1) {
								flag = 1;
							}
						}
					}
				}
				session.setAttribute("userAuthority", userAuthority);
			}
			request.setAttribute("flag", flag);
			List<Leaves> leaveList = leaveDAO.findAll();
			request.setAttribute("leaveList", leaveList);
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());
			List<Map<String, Object>> holidayList = leaveDAO.findHoliday();
			request.setAttribute("holidayList", holidayList);
			List<Map<String, Object>> setHoli = timesheetDAO.findHoliday2();
			request.setAttribute("setHoli", setHoli);
			List<Map<String, Object>> cutholiday = leaveDAO.findHoliday3();
			request.setAttribute("cutholiday", cutholiday);
			String date_now = request.getParameter("date");
			if (date_now != null) {
				request.setAttribute("date", date_now);
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String changesmyapprove() {
		try {
			String apprUserId = request.getParameter("apprUserId"); // apirat.c
			request.setAttribute("userList", userDAO.findAll());

			if (apprUserId != null) {
				List<Map<String, Object>> approve = userDAO.findByApprove(apprUserId); // null
				request.setAttribute("approve", approve);
				request.setAttribute("apprUserId", apprUserId);
			}
			approve = userDAO.findByApprove(apprUserId);
			request.setAttribute("userId", userId);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String selectmyapprove() {
		try {

			String userId = request.getParameter("userId"); // applicant
			request.setAttribute("userList", userDAO.findAll());

			if (userId != null) {
				List<Map<String, Object>> approve = userDAO.findByApprove(userId);
				request.setAttribute("approve", approve);
				request.setAttribute("userId", userId);
			}
			approve = userDAO.findByApprove(userId);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String myLeaveUpdate() {

		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();// online user

			// getParameter

			String userId = request.getParameter("name");
			String leaveid = request.getParameter("leaveId");
			int leaveId = Integer.valueOf(leaveid);

			String leaveStatusId = request.getParameter("leaveStatusId");

			String apprUserId = request.getParameter("apprUserId");
			String description = request.getParameter("description");
			String halfDay = request.getParameter("halfDay"); // null set 0
			String reason = request.getParameter("reason");
			String time_hours = request.getParameter("comboA");
			String from = request.getParameter("startDate");
			String to = request.getParameter("endDate");

			Timestamp start_date = DateUtil.dateFormat(from);// changeformat
			Timestamp end_date = DateUtil.dateFormatEdit(to);// changeformat
			String noDay = request.getParameter("noDay");
			BigDecimal no_day = new BigDecimal(noDay);
			BigDecimal time_comboA = new BigDecimal(time_hours);
			BigDecimal result = no_day.add(time_comboA);

			Leaves le = leaveDAO.findByLeaveId(leaveId);
			if (leaveStatusId != null) {
				le.setLeaveStatusId(leaveStatusId);
			}
			le.setLeaveId(leaveId);
			String TypeId = request.getParameter("leaveTypeId");
			if (!TypeId.equals("1") && !TypeId.equals("2") && !TypeId.equals("3") && !TypeId.equals("4")
					&& !TypeId.equals("5") && !TypeId.equals("6")) {
				le.setLeaveTypeId("9");
			} else {
				le.setLeaveTypeId(TypeId);
			}

			le.setHalfDay(halfDay);
			String hd = request.getParameter("halfDay");
			if (hd == null) {
				le.setHalfDay("0");
			}

			le.setUserId(userLogin);
			le.setApprUserId(apprUserId);
			le.setStartDate(start_date);
			le.setEndDate(end_date);
			le.setDescription(description);
			le.setReason(reason);

			le.setNoDay(result);
			le.setUserCreate(userLogin);
			le.setUserUpdate(userLogin);
			le.setTimeCreate(DateUtil.getCurrentTime());
			le.setTimeUpdate(DateUtil.getCurrentTime());

			List<Map<String, Object>> leaveList = leaveDAO.findLeave();
			request.setAttribute("leaveList", leaveList);
			request.setAttribute("leavetypeList", leavetypeDAO.findAll_calendar());
			request.setAttribute("leaveuserList", leaveuserDAO.findAll());
			request.setAttribute("userList", userDAO.findAll());

			leaveDAO.update(le);
			if (Global_flag.equals("2")) {
				Global_flag = "";
				return SUCCESS;
			} else {

				Global_flag = "";
				return INPUT;
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String list_Updatena() {
		try {
			String leaveid = request.getParameter("leave_id");
			int x = Integer.parseInt(leaveid);
			Leaves l = leaveDAO.findByLeaveId(x);
			l.setLeaveStatusId("1");
			leaveDAO.update(l);
			List<Map<String, Object>> leaveList = leaveDAO.findLeave();
			request.setAttribute("leaveList", leaveList);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String leave_approve() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	public String deleteleave() {
		try {
			String s = request.getParameter("leave_id");
			int leaves = Integer.parseInt(s);
			Leaves leaveid = leaveDAO.findByLeaveId(leaves);
			String user = leaveid.getUserId();
			String a1 = leaveid.getStartDate().toString();
			leaveDAO.delete(leaveid);
			String userSelect = "All";
			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			String tstm2 = tstamp.toString();
			String tstm4 = tstm2.substring(0, 4);
			String tstm5 = tstm2.substring(5, 7);
			String tstm6 = tstm2.substring(8, 10);
			String datee = tstm6 + "-" + tstm5 + "-" + tstm4;
			String tim2 = "23:59:59";
			String tim3 = "00:00:00";
			Timestamp dateen = DateUtil.dateToTimestamp2(datee, tim2);
			Date date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			String year = datenow.substring(6, 10);
			String newyear = "01-01" + "-" + year;
			Timestamp startda = DateUtil.dateToTimestamp(newyear, tim3);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			request.setAttribute("userId", user);
			List<Map<String, Object>> leave = leaveDAO.searchtable(startda, dateen, userSelect);
			request.setAttribute("leave", leave);
			request.setAttribute("flag12", a1.substring(0, 10));

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
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
			} else {
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
			List leaveListDashboard = leaveDAO.myLeavesList(userLogin, start_date, end_date, status);

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

			Date day = new Date();
			LocalDate localdate = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Double quotaLastYear = leaveDAO.LastYearQuota(userLogin, localdate.getYear());
			Double quotaThisYear = leaveDAO.ThisYearQuota(userLogin);
			request.setAttribute("quotaThisYear", quotaThisYear);
			request.setAttribute("quotaLastYear", quotaLastYear);
			request.setAttribute("leave_6l", quotaLastYear - leave_6);

			String year = localdate.toString().substring(0, 4);
			Timestamp tend = Timestamp.valueOf(year + "-04-01 00:00:00"); // time end is april month
			Timestamp tnow = new Timestamp(day.getTime());

			request.setAttribute("tnow", tnow);
			request.setAttribute("tend", tend);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String LeaveAdd() {
		String date = request.getParameter("date");
		try {
			String leaveTypeJSON = leavetypeDAO.getForDisplayJSON();
			String holidayJSON = holidayDAO.getallOnlyDateJSON();
			String userListJSON = userDAO.userListJSON();

			request.setAttribute("date", date);
			request.setAttribute("userList", userListJSON);
			request.setAttribute("leaveType", leaveTypeJSON);
			request.setAttribute("holiday", holidayJSON);
			request.setAttribute("action", "Add");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String LeaveEdit() {
		String id = request.getParameter("id");
		if (id == null) {
			return ERROR;
		}
		try {
			Leaves leave = leaveDAO.findByLeaveId(Integer.parseInt(id));
			if (leave == null) {
				return ERROR;
			}
			String leaveTypeJSON = leavetypeDAO.getForDisplayJSON();
			String holidayJSON = holidayDAO.getallOnlyDateJSON();
			String userListJSON = userDAO.userListJSON();

			request.setAttribute("userList", userListJSON);
			request.setAttribute("leaveType", leaveTypeJSON);
			request.setAttribute("holiday", holidayJSON);
			request.setAttribute("leave", new Gson().toJson(leave));
			request.setAttribute("action", "Edit");
			request.getSession().setAttribute("leaveId", id);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String LeaveAdd_Do() {
		try {
			User onlineUser = (User) request.getSession().getAttribute("onlineUser");

			String user = request.getParameter("user");
			String leaveType = request.getParameter("leaveType");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String amount = request.getParameter("amount");
			String amount_sub = request.getParameter("amount_sub");
			String halfDay = request.getParameter("halfDay");
			String description = request.getParameter("description");
			String approver = request.getParameter("approver");
			String status = request.getParameter("status");
			String reason = request.getParameter("reason");
			if (user == null) {
				user = request.getParameter("user_hidden");
			}
			if (approver == null) {
				approver = request.getParameter("approver_hidden");
			}
			if (status == null) {
				status = request.getParameter("status_hidden");
			}

			float amount_n = Float.parseFloat(amount);
			float amount_sub_n = Float.parseFloat(amount_sub);
			BigDecimal noDay = BigDecimal.valueOf(amount_n + amount_sub_n);
			Timestamp startDate = DateUtil.dateFormatEdit(from);
			Timestamp endDate = DateUtil.dateFormatEdit(to);
			Integer id = leaveDAO.getMaxId() + 1;

			Leaves leave = new Leaves();
			leave.setLeaveId(id);
			leave.setLeaveTypeId(leaveType);
			leave.setLeaveStatusId(status);
			leave.setHalfDay(halfDay);
			leave.setUserId(user);
			leave.setApprUserId(approver);
			leave.setDescription(description);
			leave.setReason(reason);
			leave.setStartDate(startDate);
			leave.setEndDate(endDate);
			leave.setNoDay(noDay);
			leave.setUserCreate(onlineUser.getId());
			leave.setUserUpdate(onlineUser.getId());
			leave.setTimeCreate(DateUtil.getCurrentTime());
			leaveDAO.save(leave);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String LeaveEdit_Do() {
		try {
			User onlineUser = (User) request.getSession().getAttribute("onlineUser");
			String id_s = (String) request.getSession().getAttribute("leaveId");
			int id = Integer.parseInt(id_s);

			String user = request.getParameter("user");
			String leaveType = request.getParameter("leaveType");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String amount = request.getParameter("amount");
			String amount_sub = request.getParameter("amount_sub");
			String halfDay = request.getParameter("halfDay");
			String description = request.getParameter("description");
			String approver = request.getParameter("approver");
			String status = request.getParameter("status");
			String reason = request.getParameter("reason");
			if (user == null) {
				user = request.getParameter("user_hidden");
			}
			if (approver == null) {
				approver = request.getParameter("approver_hidden");
			}
			if (status == null) {
				status = request.getParameter("status_hidden");
			}

			float amount_n = Float.parseFloat(amount);
			float amount_sub_n = Float.parseFloat(amount_sub);
			BigDecimal noDay = BigDecimal.valueOf(amount_n + amount_sub_n);
			Timestamp startDate = DateUtil.dateFormatEdit(from);
			Timestamp endDate = DateUtil.dateFormatEdit(to);

			Leaves leave = leaveDAO.findByLeaveId(id);
			leave.setLeaveTypeId(leaveType);
			leave.setLeaveStatusId(status);
			leave.setHalfDay(halfDay);
			leave.setUserId(user);
			leave.setApprUserId(approver);
			leave.setDescription(description);
			leave.setReason(reason);
			leave.setStartDate(startDate);
			leave.setEndDate(endDate);
			leave.setNoDay(noDay);
			leave.setUserUpdate(onlineUser.getId());
			leave.setTimeUpdate(DateUtil.getCurrentTime());
			leaveDAO.save(leave);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String Leave_inList() {
		try {
			User onlineUser = (User) request.getSession().getAttribute("onlineUser");
			String id_s = request.getParameter("leave_id");
			int id = Integer.parseInt(id_s);

			String user = request.getParameter("user");
			String status = ("1");

			Leaves leave = leaveDAO.findByLeaveId(id);
			leave.setLeaveStatusId(status);
			leaveDAO.save(leave);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	// this is Anan method
	
	public String Leave_inListLeaveStatusToWaiting() {
		try {
			User onlineUser = (User) request.getSession().getAttribute("onlineUser");
			String id_s = request.getParameter("leave_id");
			int id = Integer.parseInt(id_s);

			String user = request.getParameter("user");
			String status = ("0");

			Leaves leave = leaveDAO.findByLeaveId(id);
			leave.setLeaveStatusId(status);
			leaveDAO.save(leave);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	// this is Nawapat.s's method
	
	public String Leave_inListLeaveStatusToReject() {
		try {
			User onlineUser = (User) request.getSession().getAttribute("onlineUser");
			String id_s = request.getParameter("leave_id");
			int id = Integer.parseInt(id_s);

			String user = request.getParameter("user");
			String status = ("2");

			Leaves leave = leaveDAO.findByLeaveId(id);
			leave.setLeaveStatusId(status);
			leaveDAO.save(leave);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	// this is Nawapat.s's method
	
	public String checkNull(String name,String n) {
		try {
			if(name == null) {
				name = n;
			}else {
				name=name+" , "+n;
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//Leave Summary
	public String ytdLeaveSummary() {
		try {
			java.util.Date date = new java.util.Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int yearnow = localDate.getYear();
			int year = yearnow;
			int month = localDate.getMonthValue();
			int day = localDate.getDayOfMonth();

			request.setAttribute("type", "1");
			request.setAttribute("daynow", day);
			request.setAttribute("monthnow", month);
			request.setAttribute("yearnow", yearnow);
			request.setAttribute("year", year);
			List<Map<String, Object>> leaves = leaveDAO.test_LeavesSummary(year,1);
			request.setAttribute("leaves", leaves);
			
			List<Map<String,Object>> holidayList = holidayDAO.test_holiday(year);
			request.setAttribute("holidayList", holidayList);
			
			int[][] s;
			s = new int[12][31];
			String[][] name;
			name = new String[12][31];
			for (int i = 0; i < leaves.size(); i++) {
				int monthNumber = (Integer) leaves.get(i).get("Month");
				int dayNumber = (Integer) leaves.get(i).get("Day");
				BigDecimal noDay = (BigDecimal) leaves.get(i).get("no_day");
				BigDecimal A = new BigDecimal (1.00);
				String n = (String) leaves.get(i).get("user_id");
				s[monthNumber-1][dayNumber-1]++;
				name[monthNumber-1][dayNumber-1]=checkNull(name[monthNumber-1][dayNumber-1],n);
				if (noDay.compareTo(A)>0) {
					 double d = 0;
					 d=Math.ceil(noDay.doubleValue());
					 for(int j=1 ; j < d ; j++) {
						 if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {//find leap year
							 if(monthNumber==2) {
								 if(dayNumber-1+j>=29) {
									 s[monthNumber][j-1]++;
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 } 
							}else if(monthNumber==4||monthNumber==6||monthNumber==9||monthNumber==11) {
								 if(dayNumber-1+j>=30) {
									 s[monthNumber][j-1]++;	
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 }
							}else {
								 if(dayNumber-1+j>=31) {
									 s[monthNumber][j-1]++;
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 }
							 } 
						 	}else {
						 		if(monthNumber==2) {
									 if(dayNumber-1+j>=28) {
										 s[monthNumber][j-1]++;
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 } 
								}else if(monthNumber==4||monthNumber==6||monthNumber==9||monthNumber==11) {
									 if(dayNumber-1+j>=30) {
										 s[monthNumber][j-1]++;	
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 }
								}else {
									 if(dayNumber-1+j>=31) {
										 s[monthNumber][j-1]++;
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 }
								 } 
							 } 
						 }
					 }
				}
			
			
			List<Integer> list = new ArrayList<>();
			List<String> nameList = new ArrayList<>();
			    for(int i = 1 ; i <= 12 ; i++ ){
					for(int j = 1 ; j <= 31 ; j++){
						 list.add(s[i-1][j-1]);
						 nameList.add(name[i-1][j-1]);
					}
			    }
			    request.setAttribute("alldata", list);	  
			    request.setAttribute("allname", nameList);	
			   
			    findWeekendsList(year);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}
	//search leave
	public String searchLeaveSummary() {
		try {
			List<Map<String, Object>> leaves = null;
			String searchyaer = request.getParameter("year");
			int year = Integer.parseInt(searchyaer);
			request.setAttribute("year", year);
			
			java.util.Date date = new java.util.Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int yearnow = localDate.getYear();
			request.setAttribute("yearnow", yearnow);
			
			String type1 = request.getParameter("type");
			int type = Integer.parseInt(type1);
			request.setAttribute("type", type1);
			if(type == 0) {
				leaves = leaveDAO.test_LeavesSummary(year,type);
			}
			if(type == 1) {
				leaves = leaveDAO.test_LeavesSummary(year,type);
			}
			
			List<Map<String,Object>> holidayList = holidayDAO.test_holiday(year);
			request.setAttribute("holidayList", holidayList);
			
			
			weekendList = new ArrayList();
			weekendList2 = new ArrayList();
			
			Calendar calendar = null;
			calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year); 
			calendar.set(Calendar.MONTH, 0); 
			calendar.set(Calendar.DATE, 1); 
			// The while loop ensures that you are only checking dates in the current year
			while(calendar.get(Calendar.YEAR) == year){
			// The switch checks the day of the week for Saturdays and Sundays
			switch(calendar.get(Calendar.DAY_OF_WEEK)){
			case Calendar.SATURDAY:
			case Calendar.SUNDAY:
			weekendList.add(calendar.get(Calendar.DATE));
			weekendList2.add(calendar.get(Calendar.MONTH));

			break;
			}
			// Increment the day of the year for the next iteration of the while loop
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			}
			request.setAttribute("weekEndDay",weekendList);
			request.setAttribute("weekEndMonth",weekendList2);

			request.setAttribute("leaves", leaves);
			int[][] s;
			s = new int[12][31];
			String[][] name;
			name = new String[12][31];
			for (int i = 0; i < leaves.size(); i++) {
				int monthNumber = (Integer) leaves.get(i).get("Month");
				int dayNumber = (Integer) leaves.get(i).get("Day");
				BigDecimal noDay = (BigDecimal) leaves.get(i).get("no_day");
				BigDecimal A = new BigDecimal (1.00);
				String n = (String) leaves.get(i).get("user_id");
				s[monthNumber-1][dayNumber-1]++;
				name[monthNumber-1][dayNumber-1]=checkNull(name[monthNumber-1][dayNumber-1],n);
				if (noDay.compareTo(A)>0) {
					 double d = 0;
					 d=Math.ceil(noDay.doubleValue());
					 for(int j=1 ; j < d ; j++) {
						 if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {//find leap year
							 if(monthNumber==2) {
								 if(dayNumber-1+j>=29) {
									 s[monthNumber][j-1]++;
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 } 
							}else if(monthNumber==4||monthNumber==6||monthNumber==9||monthNumber==11) {
								 if(dayNumber-1+j>=30) {
									 s[monthNumber][j-1]++;	
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 }
							}else {
								 if(dayNumber-1+j>=31) {
									 s[monthNumber][j-1]++;
									 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
								 }else {
									 s[monthNumber-1][dayNumber-1+j]++;
									 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
								 }
							 } 
						 	}else {
						 			if(monthNumber==2) {
									 if(dayNumber-1+j>=28) {
										 s[monthNumber][j-1]++;
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 } 
								}else if(monthNumber==4||monthNumber==6||monthNumber==9||monthNumber==11) {
									 if(dayNumber-1+j>=30) {
										 s[monthNumber][j-1]++;	
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 }
								}else {
									 if(dayNumber-1+j>=31) {
										 s[monthNumber][j-1]++;
										 name[monthNumber][j-1]=checkNull(name[monthNumber][j-1],n);
									 }else {
										 s[monthNumber-1][dayNumber-1+j]++;
										 name[monthNumber-1][dayNumber-1+j]=checkNull(name[monthNumber-1][dayNumber-1+j],n);
									 }
								 } 
							 } 
						 }
					 }
				}
			
			
			List<Integer> list = new ArrayList<>();
			List<String> nameList = new ArrayList<>();
			    for(int i = 1 ; i <= 12 ; i++ ){
					for(int j = 1 ; j <= 31 ; j++){
						 list.add(s[i-1][j-1]);
						 nameList.add(name[i-1][j-1]);
					}
			    }
			    request.setAttribute("alldata", list);	  
			    request.setAttribute("allname", nameList);	
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}
	//find SATURDAY and SUNDAY
		private ArrayList weekendList = null;
		private ArrayList weekendList2 = null;

		public void findWeekendsList(int year)
		{
		weekendList = new ArrayList();
		weekendList2 = new ArrayList();

		Calendar calendar = null;
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year); 
		calendar.set(Calendar.MONTH, 0); 
		calendar.set(Calendar.DATE, 1); 
		// The while loop ensures that you are only checking dates in the current year
		while(calendar.get(Calendar.YEAR) == year){
		// The switch checks the day of the week for Saturdays and Sundays
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
		case Calendar.SATURDAY:
		case Calendar.SUNDAY:
		weekendList.add(calendar.get(Calendar.DATE));
		weekendList2.add(calendar.get(Calendar.MONTH));

		break;
		}
		// Increment the day of the year for the next iteration of the while loop
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		request.setAttribute("weekEndDay",weekendList);
		request.setAttribute("weekEndMonth",weekendList2);

		}
		
		
		
		public String LeaveSelectMonth() {
			try {

			java.util.Date date = new java.util.Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int yearnow = localDate.getYear();
			int year = yearnow;
			int month = localDate.getMonthValue();
			log.info(month);

			List<Map<String, Object>> leave = leaveDAO.leaveselectM(year);
			request.setAttribute("leave", leave);
			log.info(leave);

			request.setAttribute("year", year);
			request.setAttribute("type", month);
			return SUCCESS;
			} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
			}
			}

			public String searchLeaveSelectMonth() {
			try {
			String searchyaer = request.getParameter("year");
			int year = Interger.parseInt(searchyaer);
			request.setAttribute("year", year);

			 List<Map<String, Object>> leave = leaveDAO.leaveselectM(year);
			request.setAttribute("leave", leave);
			log.info(leave);


			String type1 = request.getParameter("type");
			int type = Interger.parseInt(type1);
			request.setAttribute("type", type1);

			return SUCCESS;
			} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
			}
			}
			
			public String searchList_edit() {
				try {
					String jsonId = request.getParameter("nameSelect");
					String jsonStatus = request.getParameter("appr");
					String jsonType = request.getParameter("type");
					String jsonStartDate = request.getParameter("startdate");
					String jsonEndDate = request.getParameter("enddate");
					
					String listbyuser = request.getParameter("Id");
					if (jsonId != listbyuser) {
						listbyuser = jsonId;
					}		
					Timestamp start_date = DateUtil.dateToTimestamp(jsonStartDate, "00:00");
					Timestamp end_date = DateUtil.dateToTimestamp(jsonEndDate, "00:00");
					 Gson gson = new GsonBuilder().create();
				     String responseJSON = gson.toJson(leaveDAO.searchApproved(start_date, end_date, jsonId, jsonStatus, jsonType));
				     request.setAttribute("json", responseJSON);
					return "json";
				} catch (Exception e) {

					e.printStackTrace();
					return ERROR;
				}
			}
			
			public String searchShowDashboard() {
				try {
					Date newDate = new Date();  
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
					String date= dateFormat.format(newDate);
					String jsonId = request.getParameter("nameSelect");
					
					String listbyuser = request.getParameter("Id");
					if (jsonId != listbyuser) {
						listbyuser = jsonId;
					}			
					 Gson gson = new GsonBuilder().create();
				     String responseJSON = gson.toJson(leaveDAO.searchDashboard(jsonId, date));
				     request.setAttribute("json", responseJSON);
					return "json";
				} catch (Exception e) {

					e.printStackTrace();
					return ERROR;
				}
			}
			
			public String searchShowDashboardQuota() {
				try {
					log.debug("searchShowDashboardQuota");
					Date newDate = new Date();  
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
					String date= dateFormat.format(newDate);
					String jsonId = request.getParameter("nameSelect");
					log.debug("UserId: "+jsonId);
					log.debug("date: "+date);
					
					String listbyuser = request.getParameter("Id");
					if (jsonId != listbyuser) {
						listbyuser = jsonId;
					}			
					 Gson gson = new GsonBuilder().create();
				     String responseJSON = gson.toJson(leaveDAO.searchDashboardQuota(jsonId, date));
				     request.setAttribute("json", responseJSON);
				     log.debug(responseJSON);
					return "json";
				} catch (Exception e) {

					e.printStackTrace();
					return ERROR;
				}
			}

			public String leaveUpdateStatus() {
				try {
					log.debug("leaveUpdateStatus");	
					String user = request.getParameter("user");
					String leaveId = request.getParameter("leaveId");
					log.debug("user: "+user);
					log.debug("leaveId: "+leaveId);
					Gson gson = new GsonBuilder().create();
				    String responseJSON = gson.toJson(leaveDAO.leaveUpdateStatus(user, leaveId));
				    request.setAttribute("json", responseJSON);
				    log.debug(responseJSON);
					return "json";
				} catch (Exception e) {

					e.printStackTrace();
					return ERROR;
				}
			}
}