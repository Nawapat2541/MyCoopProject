package com.cubesofttech.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.ExpTravelTypeDAO;
import com.cubesofttech.dao.ExpenseDAO;
import com.cubesofttech.dao.ExpenseGroupDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.Expense;
import com.cubesofttech.model.ExpenseGroup;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ExpenseGroupAction extends ActionSupport {

	private static final long serialVersionUID = 2280661337420278284L;
	public static final String STARTTIME = "0000-01-01 01:01:01";
	public static final String ENDTIME = "9999-12-31 23:59:59";
	public static final String EXPSEARCHLIST = "expSearchList";

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private ExpenseGroupDAO expensegroupDAO;

	@Autowired
	private ExpenseDAO expenseDAO;
	
	@Autowired
	private ExpTravelTypeDAO expTravelTypeDAO;

	@Autowired
	private UserDAO userDAO;

	private ExpenseGroup expensegroup;

	private int expenseGroupId;

	public ExpenseGroup getExpensegroup() {
		return expensegroup;
	}

	public void setExpensegroup(ExpenseGroup expensegroup) {
		this.expensegroup = expensegroup;
	}

	public int getExpenseGroupId() {
		return expenseGroupId;
	}

	public void setExpenseGroupId(int expenseGroupId) {
		this.expenseGroupId = expenseGroupId;
	}

	public static final String USERLIST = "userList";
	public static final String EXPENSEGROUPLIST = "expensegroupList";
	public static final String ONLINEUSER = "onlineUser";
	public static final String USERSEQ = "userseq";
	private static final String USERID = null;

	public String listID() { // list all
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			
			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1;
			date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			
			request.setAttribute("logonUser", ur.getId());
			request.setAttribute("nameofuser", request.getParameter("name"));
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute("groupList", expensegroupDAO.listId());
			request.setAttribute("onlyone",  expensegroupDAO.searchtable(ur.getId()));
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute("datenow", datenow);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String search() {
		try {
			String nameofuser = request.getParameter("name");
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			request.setAttribute("userSelect", ur.getId());
			String startdate = request.getParameter("startdate");
			String enddate = request.getParameter("enddate");
			String s = "00:00:00.0";
			
			Timestamp start_date = DateUtil.dateToTimestamp(startdate, s);
			Timestamp end_date = DateUtil.changeYearEnd(enddate);
			
			if (nameofuser.equals("All")) {
				List<Map<String, Object>> userAll = expensegroupDAO.searchAll(start_date, end_date);
				request.setAttribute("userAll", userAll);
			} else if (nameofuser != "All") {
				List<Map<String, Object>> onlyone = expensegroupDAO.searchtable2(nameofuser, start_date, end_date);
				request.setAttribute("onlyone", onlyone);
			}
			
			Timestamp startDate = DateUtil.changeYearStart(startdate);
			request.setAttribute("startdate", startDate);
			Timestamp endDate = DateUtil.changeYearEnd(enddate);
			request.setAttribute("enddate", endDate);
			request.setAttribute("nameofuser", nameofuser);
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String list() { // list form
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			request.setAttribute("logonUser", ur.getId());
			String flag = request.getParameter("flag");
			if (flag != null) {
				String userId = request.getParameter("user");
				request.setAttribute("flag_search", flag);
				request.setAttribute("userId", userId);
			}
			request.setAttribute("groupId", expensegroupDAO.listgroup(request.getParameter("Id")));
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute("expenseList", expenseDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String detail() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String expgroup = request.getParameter("expenseGroupId");
			
			int expgroupId = Integer.parseInt(expgroup);
			ExpenseGroup ex = expensegroupDAO.findById(expgroup);
			ex.setExpenseGroupId(expgroupId);
			ex.setUserUpdate(ur.getId());
			ex.setTimeUpdate(DateUtil.getCurrentTime());
			expensegroupDAO.update(ex);
			
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String status() {// save status
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			int groupId = Integer.parseInt(request.getParameter("expenseGroupId"));
			int paid = Integer.parseInt(request.getParameter("paidyear"));
			
			ExpenseGroup ex = expensegroupDAO.findByGroupId(expenseGroupId); // null
			ex.setPaidYear(paid);
			ex.setExpenseGroupId(groupId);
			ex.setStatusId( request.getParameter("statusId"));
			ex.setUserUpdate(ur.getId());
			ex.setTimeUpdate(DateUtil.getCurrentTime());
			expensegroupDAO.update(ex);
			
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	public String deleteGroup() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			request.setAttribute("logonUser", ur.getId());
			String flag = request.getParameter("flag");
			if (flag != null) {
				String userId = request.getParameter("user");
				request.setAttribute("flag_search", flag);
				request.setAttribute("userId", userId);
			}
			Expense exp = new Expense();
			int expId = Integer.parseInt(request.getParameter("expId"));
			
			 exp = expenseDAO.findById(expId); 
			int expgroupId = Integer.parseInt(request.getParameter("groupId"));
			ExpenseGroup ex = expensegroupDAO.findById(expgroupId);
			
			BigDecimal totalGroup = ex.getTotalAmount();
			BigDecimal total = exp.getAmount(); 
			int	totalAmount = totalGroup.intValue() - total.intValue();
			Integer groupId = 0;
			
			String userId = request.getParameter("userId");
			String from = STARTTIME;
			String to = ENDTIME;

			
			exp.setExpenseGroupId(groupId);
			exp.setUserUpdate(ur.getId());
			exp.setTimeUpdate(DateUtil.getCurrentTime());
			expenseDAO.update(exp);
			
			if(totalAmount != 0) {
				BigDecimal amount = new BigDecimal(totalAmount);
				ex.setTotalAmount(amount);
				ex.setUserUpdate(ur.getId());
				ex.setTimeUpdate(DateUtil.getCurrentTime());
				expensegroupDAO.update(ex);
				
				
			}else {
				expensegroupDAO.delete(ex);
			}
			List<Map<String, Object>> expSearchList = expenseDAO.expSearch(userId, from, to, "All");
			request.setAttribute(EXPSEARCHLIST, expSearchList);
			request.setAttribute("userId", userId);
			List<Map<String, Object>> expensetableList = expenseDAO.findExpense();
			request.setAttribute("expensetableList", expensetableList);
			request.setAttribute("expenseList", expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute("expTravelTypeList", expTravelTypeDAO.findAll());
			request.setAttribute("expensegroup_type", expensegroupDAO.findtype());
			

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
}
