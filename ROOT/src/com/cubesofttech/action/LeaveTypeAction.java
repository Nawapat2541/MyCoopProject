package com.cubesofttech.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.LeaveTypeDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.LeaveType;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;
public class LeaveTypeAction extends ActionSupport {
	private static final Logger log = Logger.getLogger(LeaveTypeAction.class);
	private static final long serialVersionUID = 1L;
	public static final String TYPELIST  = "leavetypelist";
	public static final String USERLIST = "leavetypelist_user";
	
	
	@Autowired
	private LeaveTypeDAO leaveTypeDAO;

	@Autowired
	private UserDAO userDAO;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	public String list() {
		log.debug("[Begin] list |excute|");
		try {

			List<LeaveType> leaveTypeList = leaveTypeDAO.findAll2();
			request.setAttribute(TYPELIST, leaveTypeList);
			List<Object> leaveTypeListUser = leaveTypeDAO.searchalluser();
			request.setAttribute(USERLIST , leaveTypeListUser);
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			log.debug("[End] list |excute| ");
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			
			return ERROR;
		}

	}

	public String init() {
		log.debug("[Begin] init |excute|");

		try {

		} catch (Exception e) {
			
			
		}
		log.debug("[End] init |excute|");
		return SUCCESS;
	}

	public String searchTable() {
		try {
			log.debug("[Begin] searchTable |excute|");
			String user = request.getParameter("myselect");
			if (user.equals("All")) {
				List<LeaveType> leaveTypeList = leaveTypeDAO.findAll2();
				request.setAttribute(TYPELIST, leaveTypeList);
				List<Object> leavetypeListUser = leaveTypeDAO.searchalluser();
				request.setAttribute(USERLIST , leavetypeListUser);
				log.debug(leaveTypeList);
			} else {
				List<LeaveType> leaveTypeList = leaveTypeDAO.searchtable(user);
				request.setAttribute(TYPELIST, leaveTypeList);
			}
			List<Object> leaveTypeListAllUser = leaveTypeDAO.searchalluser();
			request.setAttribute(USERLIST , leaveTypeListAllUser);
			request.setAttribute("myselect", user);
			log.debug(user);
			log.debug("[End] searchTable |excute|");
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
		
			return ERROR;
		}

	}

	public String saveLeaveType() throws Exception {
		try {
			log.debug("[Begin] saveLeaveType |excute|");
			User ur = (User) request.getSession().getAttribute("onlineUser"); 
																				
			String nameuser = ur.getId(); 

			LeaveType leaveType = new LeaveType();
			String idLeaveType = request.getParameter("idleavetype");
			String nameLeaveType = request.getParameter("nameleavetype");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			Timestamp dateTime = DateUtil.dateToTimestamp(date, time);
			String discription = request.getParameter("description");
			List<Map<String, Object>> leaveTypeCheck = leaveTypeDAO.findById2(idLeaveType);
			List<Map<String, Object>> leaveTypeCheckName = leaveTypeDAO.findByName(nameLeaveType);
			String name = leaveTypeCheckName.toString();
			String id = leaveTypeCheck.toString();
			log.debug(name);
			log.debug(name.length());
			if (id.length() != 2) {
				request.setAttribute("flag", "1");
				return INPUT;
			}
			if (name.length() != 2) {
				request.setAttribute("flag2", "1");
				return INPUT;
			}

			leaveType.setLeaveTypeId(idLeaveType);
			leaveType.setLeaveTypeName(nameLeaveType);
			leaveType.setTimeCreate(dateTime);
			leaveType.setTimeUpdate(dateTime);
			leaveType.setDescription(discription);
			leaveType.setUserCreate(nameuser);
			leaveType.setUserUpdate(nameuser);
			leaveTypeDAO.save(leaveType);

			log.debug("[End] saveLeaveType |excute|");
			return SUCCESS;

		} catch (Exception e) {
			log.error(e);
		
			return ERROR;
		}
	}

	public String delete() {

		try {
			log.debug("[Begin] delete |excute|");
			String leaveTypeId = request.getParameter("id");
			LeaveType leaveType= leaveTypeDAO.findById(leaveTypeId);
			leaveTypeDAO.delete(leaveType);
			log.debug("[End] delete |excute|");
			return SUCCESS;

		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String edit() {

		try {
			log.debug("[Begin] edit |excute|");
			String leaveTypeId = request.getParameter("id");
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(TYPELIST, leaveTypeDAO.findByLeaveTypeId2(leaveTypeId));
			log.debug("[End] edit |excute|");
			return SUCCESS;

		} catch (Exception e) {
			log.error(e);
		
			return ERROR;
		}
	}

	public String update() {

		try {
			log.debug("[Begin] update |excute|");
			User ur = (User) request.getSession().getAttribute("onlineUser"); 																	
			String nameUser = ur.getId(); 
			String leaveTypeId = request.getParameter("leave_type_id");
			String name = request.getParameter("leave_type_name");
			String description = request.getParameter("leave_description");
			String timeUpDate = request.getParameter("leave_time_update");
			String time = request.getParameter("time");
			Timestamp dateTime = DateUtil.dateToTimestamp(timeUpDate, time);
			LeaveType leaveType =  leaveTypeDAO.findById(leaveTypeId);
			leaveType.setLeaveTypeName(name);
			leaveType.setDescription(description);
			leaveType.setUserUpdate(nameUser);
			leaveType.setTimeUpdate(dateTime);
			request.setAttribute(TYPELIST, leaveType);
			leaveTypeDAO.update(leaveType);
			log.debug(leaveType);
			log.debug("[End] update |excute|");
			return SUCCESS;

		} catch (Exception e) {
			log.error(e);
		
			return ERROR;
		}
	}

}
