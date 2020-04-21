package com.cubesofttech.action;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.ExpTravelTypeDAO;
import com.cubesofttech.dao.ExpTypeDAO;
import com.cubesofttech.dao.ExpenseDAO;
import com.cubesofttech.dao.ExpenseGroupDAO;
import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.ExpTravelType;
import com.cubesofttech.model.Expense;
import com.cubesofttech.model.ExpenseGroup;
import com.cubesofttech.model.TravelList;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ExpenseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2280661337420278284L;
	private static final Integer Interger = null;
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private ExpenseDAO expenseDAO;

	@Autowired
	private ExpenseGroupDAO expensegroupDAO;
	@Autowired
	private HolidayDAO holidayDAO;
	@Autowired
	private ExpTravelTypeDAO exptraveltypeDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ExpTravelTypeDAO expTravelTypeDAO;

	@Autowired
	private ExpTypeDAO exptypeDAO;

	private Expense expense;

	private int expenseId;

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public static final String USERSEQ = "userseq";
	public static final String EXPENSESEQ = "expenseseq";
	public static final String EXPENSEID = "expenseId";
	public static final String USERID = "userId";
	public static final String ONLINEUSER = "onlineUser";
	public static final String STARTTIME = "0000-01-01 01:01:01";
	public static final String ENDTIME = "9999-12-31 23:59:59";
	public static final String EXPSEARCHLIST = "expSearchList";
	public static final String EXPENSETABLELIST = "expensetableList";
	public static final String EXPENSELIST = "expenseList";
	public static final String EXPENSEGROUPLIST = "expensegroupList";
	public static final String USERLIST = "userList";
	public static final String EXPTRAVELTYPELIST = "expTravelTypeList";


	public String list() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);

			String flag = request.getParameter("flag");
			if (flag != null) {
				String date = request.getParameter("date");
				request.setAttribute("date", date);
				request.setAttribute("flag", flag);
			}
			String userId = request.getParameter(USERID);
			String from = STARTTIME;
			String to = ENDTIME;
			List<Map<String, Object>> expSearchList = expenseDAO.expSearch(userId, from, to, "All");
			request.setAttribute(EXPSEARCHLIST, expSearchList);
 			request.setAttribute(USERID, userId);
			List<Map<String, Object>> expensetableList = expenseDAO.findExpense();
			request.setAttribute(EXPENSETABLELIST, expensetableList);
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			request.setAttribute("expensegroup_type", expensegroupDAO.findtype());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	
	public String openEdit() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);
			
			List<Map<String, Object>> expenseseq = expenseDAO.sequense();
			request.setAttribute(EXPENSESEQ, expenseseq);
			
			String expenseId = request.getParameter(EXPENSEID);
			
			String flag = request.getParameter("flag");
			if (flag != null) {
				String date = request.getParameter("date");
				request.setAttribute("date", date);
				request.setAttribute("flag", flag);
			}
			String userId = request.getParameter(USERID);
			String from = STARTTIME;
			String to = ENDTIME;
			List<Map<String, Object>> expSearchList = expenseDAO.expSearch(userId, from, to, "All");
			request.setAttribute(EXPSEARCHLIST, expSearchList);
			request.setAttribute(USERID, userId);
			request.setAttribute(EXPENSEID, expenseId);
			List<Map<String, Object>> expensetableList = expenseDAO.findExpense();
			request.setAttribute(EXPENSETABLELIST, expensetableList);
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			request.setAttribute("expensegroup_type", expensegroupDAO.findtype());
			request.setAttribute("expense", expenseDAO.findByExpId(getExpenseId()));
			



			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	/* public String edit() {
		try {
				request.setAttribute(USERSEQ, userDAO.sequense());
				String userUpdate = request.getParameter("userUpdate");
				String userId = request.getParameter("name");
				String expenseString = request.getParameter("expenseId");
				String dayOfDeparture = DateUtil.changePstocs(request.getParameter("dayOfDeparture"));
				int expenseId = Integer.parseInt(expenseString);
				String startTime = request.getParameter("startTimeEdit");
				String endTime = request.getParameter("endTimeEdit");
				Timestamp dateStart = DateUtil.dateToTimestamp(dayOfDeparture, startTime);
				Timestamp dateEnd = DateUtil.dateToTimestamp(dayOfDeparture, endTime);
				Expense exp = new Expense();
				exp.setExpenseId(expenseId);
				exp.setDtStart(dateStart);
				exp.setDtEnd(dateEnd);
				exp.setDtBy(expense.getDtBy());
				exp.setFromLocation(expense.getFromLocation());
				exp.setToLocation(expense.getToLocation());
				exp.setDescription(expense.getDescription());
				exp.setAmount(expense.getAmount());
				exp.setUserUpdate(userUpdate);
				exp.setTimeUpdate(DateUtil.getCurrentTime());
				expenseDAO.update(exp);
				String from = STARTTIME;
				String to = ENDTIME;
				request.setAttribute(EXPSEARCHLIST, expenseDAO.expSearch(userUpdate, from, to));
				request.setAttribute(USERID, userUpdate);
				request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
				request.setAttribute(EXPENSELIST, expenseDAO.findAll());
				request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
				request.setAttribute(USERLIST, userDAO.findAll());
				request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
				return SUCCESS;
			} catch (

			Exception e) {
				log.error(e);
				return ERROR;
			}
	}  
	*/
	// for update database
	 public String edit() {
		try {
		
			log.info("pass");
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username
																				// login
			String logonUser = ur.getId(); // Username login
			log.info(logonUser);
			
			Expense expenseListUpdate = new Expense();
			String id = request.getParameter("expenseId");
			int expenseID = Integer.parseInt(id);
			
		// use findById first after use findByExpenseId
			expenseListUpdate = expenseDAO.findById(expenseID);			

			String userUpdate = request.getParameter("userUpdate");
				String dayOfDeparture = DateUtil.changePstocs(request.getParameter("dayOfDeparture"));
				String startTime = request.getParameter("startTimeEdit");
				String endTime = request.getParameter("endTimeEdit");
				//BigDecimal money=expense.getAmount();

				Timestamp dateStart = DateUtil.dateToTimestamp(dayOfDeparture, startTime);
				Timestamp dateEnd = DateUtil.dateToTimestamp(dayOfDeparture, endTime);
				log.info(startTime);
				
					
				expenseListUpdate.setDtStart(dateStart);
				expenseListUpdate.setDtEnd(dateEnd);
				expenseListUpdate.setDtBy(expense.getDtBy());
				expenseListUpdate.setFromLocation(expense.getFromLocation());
				expenseListUpdate.setToLocation(expense.getToLocation());
				expenseListUpdate.setDescription(expense.getDescription());
				expenseListUpdate.setAmount(expense.getAmount());
				expenseListUpdate.setUserUpdate(userUpdate);
				expenseListUpdate.setTimeUpdate(DateUtil.getCurrentTime());
				expenseDAO.update(expenseListUpdate);	
				 //update total amount in expense_group
				
				String GroupId = request.getParameter("expenseGroupId");
				int expenseGroupId = Integer.parseInt(GroupId);
				ExpenseGroup expenseGroupUpdate = expensegroupDAO.findById(expenseGroupId);			
				String amountList = expenseDAO.findAmountByExpenseGroupId(expenseGroupId);
				BigDecimal amount = new BigDecimal(amountList);
				expenseGroupUpdate.setTotalAmount(amount);
				expensegroupDAO.update(expenseGroupUpdate);
			

				return SUCCESS;
			} catch (

			Exception e) {
				log.error(e);
				return ERROR;
			}
	}  
	 
	 public String edit_record() {
			try {
			
				log.info("pass");
				User ur = (User) request.getSession().getAttribute("onlineUser"); // Username
																					// login
				String logonUser = ur.getId(); // Username login
				log.info(logonUser);
				
				Expense expenseListUpdate = new Expense();
				String id = request.getParameter("expenseId");
				int expenseID = Integer.parseInt(id);
				
			// use findById first after use findByExpenseId
				expenseListUpdate = expenseDAO.findById(expenseID);			

				String userUpdate = request.getParameter("userUpdate");
					String dayOfDeparture = DateUtil.changePstocs(request.getParameter("dayOfDeparture"));
					String startTime = request.getParameter("startTimeEdit");
					String endTime = request.getParameter("endTimeEdit");
					//BigDecimal money=expense.getAmount();
					String userId = request.getParameter("userId");

					Timestamp dateStart = DateUtil.dateToTimestamp(dayOfDeparture, startTime);
					Timestamp dateEnd = DateUtil.dateToTimestamp(dayOfDeparture, endTime);
					log.info(startTime);
					
						
					expenseListUpdate.setDtStart(dateStart);
					expenseListUpdate.setDtEnd(dateEnd);
					expenseListUpdate.setDtBy(expense.getDtBy());
					expenseListUpdate.setFromLocation(expense.getFromLocation());
					expenseListUpdate.setToLocation(expense.getToLocation());
					expenseListUpdate.setDescription(expense.getDescription());
					expenseListUpdate.setAmount(expense.getAmount());
					expenseListUpdate.setUserUpdate(userUpdate);
					expenseListUpdate.setTimeUpdate(DateUtil.getCurrentTime());
					expenseDAO.update(expenseListUpdate);	
					 //update total amount in expense_group
					
					
					String GroupId = request.getParameter("expenseGroupId");
					int expenseGroupId = Integer.parseInt(GroupId);
					if ( expenseGroupId > 0) {
					ExpenseGroup expenseGroupUpdate = expensegroupDAO.findById(expenseGroupId);			
					String amountList = expenseDAO.findAmountByExpenseGroupId(expenseGroupId);
					BigDecimal amount = new BigDecimal(amountList);
					expenseGroupUpdate.setTotalAmount(amount);
					expensegroupDAO.update(expenseGroupUpdate);
					}
					request.setAttribute(USERID, userId);


					return SUCCESS;
				} catch (

				Exception e) {
					log.error(e);
					return ERROR;
				}
		}  

	
	public String performEdit() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String add() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			String userCreate = request.getParameter("userCreate");
			String userId = request.getParameter("name");
			String dayOfDeparture = DateUtil.changePstocs(request.getParameter("dayOfDeparture"));

			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Timestamp dateStart = DateUtil.dateToTimestamp(dayOfDeparture, startTime);
			Timestamp dateEnd = DateUtil.dateToTimestamp(dayOfDeparture, endTime);
			Expense exp = new Expense();
			exp.setExpenseId(expenseDAO.getMaxId() + 1);
			exp.setExpenseGroupId(0);
			exp.setExpTypeId("T");
			exp.setDtStart(dateStart);
			exp.setDtEnd(dateEnd);
			exp.setUserId(userId);
			exp.setDtBy(expense.getDtBy());
			exp.setFromLocation(expense.getFromLocation());
			exp.setToLocation(expense.getToLocation());
			exp.setDescription(expense.getDescription());
			exp.setAmount(expense.getAmount());
			exp.setUserCreate(userCreate);
			exp.setUserUpdate(userCreate);
			exp.setTimeCreate(DateUtil.getCurrentTime());
			exp.setTimeUpdate(DateUtil.getCurrentTime());
			expenseDAO.save(exp);
			String from = STARTTIME;
			String to = ENDTIME;
			request.setAttribute(EXPSEARCHLIST, expenseDAO.expSearch(userCreate, from, to));
			request.setAttribute(USERID, userCreate);
			request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			return SUCCESS;
		} catch (

		Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String expSearch() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);
			String userId = request.getParameter(USERID);
			String statusExp = request.getParameter("status_exp");
			String from = DateUtil.dateFromFormat(request.getParameter("from"));
			String to = DateUtil.dateToFormat(request.getParameter("to"));
			request.setAttribute("from", request.getParameter("from"));
			request.setAttribute("to", request.getParameter("to"));
			request.setAttribute(USERID, userId);
			String name = request.getParameter("name");
			if (name != null) {
				userId = name;
			}
			request.setAttribute(EXPSEARCHLIST, expenseDAO.expSearch(userId, from, to, statusExp));
			request.setAttribute("status_form", statusExp);
			request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			request.setAttribute("userSelect1", name);

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String expDelete() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);
			Expense exp = new Expense();
			String expId = request.getParameter("expId");
			exp.setExpenseId(Integer.parseInt(expId));
			expenseDAO.delete(exp);
			String userId = request.getParameter(USERID);
			String from = DateUtil.dateFromFormat(request.getParameter("from"));
			String to = DateUtil.dateToFormat(request.getParameter("to"));
			request.setAttribute("from", request.getParameter("from"));
			request.setAttribute("to", request.getParameter("to"));
			request.setAttribute(USERID, userId);
			request.setAttribute(EXPSEARCHLIST, expenseDAO.expSearch(userId, from, to));
			request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String expUpdateGroup() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			String userId = request.getParameter(USERID);
			String userOnline = request.getParameter("userOnline");
			if (userId == "") {
				userId = userOnline;
			}
			Integer maxGroupId = expensegroupDAO.getMaxId() + 1;
			ExpenseGroup expGroup = new ExpenseGroup();
			expGroup.setExpenseGroupId(maxGroupId);
			expGroup.setExpTypeId("T");
			expGroup.setStatusId("W");
			expGroup.setUserId(userId);
			Short month = DateUtil.getMonth();
			int year = DateUtil.getYear();
			expGroup.setPaidMonth(month);
			expGroup.setPaidYear(year);
			expGroup.setUserCreate(userOnline);
			expGroup.setUserUpdate(userOnline);
			expGroup.setTimeCreate(DateUtil.getCurrentTime());
			expGroup.setTimeUpdate(DateUtil.getCurrentTime());
			expensegroupDAO.save(expGroup);
			
			String[] expIdList = request.getParameterValues("expGroupId"); 
			int totalAmount = 0;
			for (int i = 0; i < expIdList.length; i++) {
				int expId = Integer.parseInt(expIdList[i]); 
				Expense exp = expenseDAO.findById(expId); 
				BigDecimal total = exp.getAmount(); 
				totalAmount += total.intValue();   
				exp.setExpenseGroupId(maxGroupId); 
				expenseDAO.update(exp);
			}
			BigDecimal amount = new BigDecimal(totalAmount);
			ExpenseGroup expGroupUp = expensegroupDAO.findById(maxGroupId);
			expGroupUp.setTotalAmount(amount);
			expensegroupDAO.update(expGroupUp);
			String from = DateUtil.dateFromFormat(request.getParameter("from"));
			String to = DateUtil.dateToFormat(request.getParameter("to"));
			request.setAttribute("from", request.getParameter("from"));
			request.setAttribute("to", request.getParameter("to"));
			request.setAttribute(USERID, userId);

			request.setAttribute(EXPSEARCHLIST, expenseDAO.expSearch(userId, from, to));
			request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
			request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	


	public String openCalendar() throws Exception {

		List<Map<String, Object>> userseq = userDAO.sequense();
		request.setAttribute(USERSEQ, userseq);

		String userId = request.getParameter(USERID);
		request.setAttribute("userSelect", userId);
		String from = STARTTIME;
		String to = ENDTIME;
		List<Map<String, Object>> expSearchList = expenseDAO.expSearch_forCalendar(userId, from, to);

		request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
		request.setAttribute("holidayList", holidayDAO.findAll());

		List<ExpenseGroup> expensegroup = new ArrayList<>();
		BigInteger expenseGroupId;
		for (int i = 0; i < expSearchList.size(); i++) {
			expenseGroupId = (BigInteger) expSearchList.get(i).get("expense_group_id");
			ExpenseGroup ex = expensegroupDAO.findByGroupId(expenseGroupId.intValue()); // null
			expensegroup.add(ex);
		}

		request.setAttribute(EXPSEARCHLIST, expSearchList);
		request.setAttribute("expgroupList", expensegroup);
		request.setAttribute("flag_search", "");
		request.setAttribute(USERID, userId);
		return SUCCESS;

	}

	public String travelExpCalendarDelete() throws Exception {

		Expense exp = new Expense();
		User ur = (User) request.getSession().getAttribute(ONLINEUSER);
		String user = ur.getId();
		String userId = request.getParameter("user_Id");
		String userid = request.getParameter(USERID);

		request.setAttribute(USERID, user);

		int expId = Integer.parseInt(userId);
		exp.setExpenseId(expId);

		expenseDAO.delete(exp);

		return SUCCESS;
	}

	public String addOther() throws Exception {

		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			String userCreate = request.getParameter("user");
			String other = request.getParameter("other");
			ExpTravelType expTravel = new ExpTravelType();
			Integer maxGroupId = expTravelTypeDAO.getMaxId() + 1;
			List<Map<String, Object>> checkGoBy = expTravelTypeDAO.search(other);
			if (checkGoBy.isEmpty()) {
				expTravel.setExpTravelTypeId(maxGroupId);
				expTravel.setName(other);
				expTravel.setUserCreate(userCreate);
				expTravel.setTimeCreate(DateUtil.getCurrentTime());
				expTravelTypeDAO.save(expTravel);
				return SUCCESS;
			}
			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			return INPUT;
		} catch (

		Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String travelexpCalendarSearch() throws Exception {

		request.setAttribute(USERSEQ, userDAO.sequense());
		String userId = request.getParameter("name");
		request.setAttribute("userSelect", userId);
		String from = STARTTIME;
		String to = ENDTIME;
		request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
		request.setAttribute("holidayList", holidayDAO.findAll());
		List<Map<String, Object>> expSearchList = expenseDAO.expSearch_forCalendar(userId, from, to);
		List<ExpenseGroup> expensegroup = new ArrayList<>();
		BigInteger expenseGroupId;
		for (int i = 0; i < expSearchList.size(); i++) {
			expenseGroupId = (BigInteger) expSearchList.get(i).get("expense_group_id");
			ExpenseGroup ex = expensegroupDAO.findByGroupId(expenseGroupId.intValue()); // null
			expensegroup.add(ex);
		}
		request.setAttribute(USERID, userId);
		request.setAttribute(EXPSEARCHLIST, expSearchList);
		request.setAttribute("expgroupList", expensegroup);
		return SUCCESS;
	}

	/*
	 * public String TravelSummary() { try { request.setAttribute(USERSEQ,
	 * userDAO.sequense()); String userId = request.getParameter("USERID");
	 * request.setAttribute("userSelect", userId); java.util.Date date = new
	 * java.util.Date(); LocalDate localDate =
	 * date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); int yearnow =
	 * localDate.getYear(); int year = yearnow;
	 * 
	 * request.setAttribute("yearnow", yearnow); request.setAttribute("year", year);
	 * 
	 * List<Map<String, Object>> ts = expenseDAO.test_travelsummary(userId,year);
	 * request.setAttribute("ts", ts); log.info("ts="+ts); return SUCCESS; } catch
	 * (Exception e) { log.error(e); return ERROR; } }
	 * 
	 * 
	 * 
	 * public String SearchTravelSummary() { try { List<Map<String, Object>> userseq
	 * = userDAO.sequense(); request.setAttribute(USERSEQ, userseq); String userId =
	 * request.getParameter(USERID); request.setAttribute(USERID, userId); String
	 * name = request.getParameter("name"); if (name != null) { userId = name; }
	 * request.setAttribute(EXPENSETABLELIST, expenseDAO.findExpense());
	 * request.setAttribute(EXPENSELIST, expenseDAO.findAll());
	 * request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
	 * request.setAttribute(USERLIST, userDAO.findAll());
	 * request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
	 * request.setAttribute("userSelect1", name);
	 * 
	 * return SUCCESS; } catch (Exception e) { log.error(e); return ERROR; } }
	 */
	
	public String TravelSummary() {
		try {

		User ur = (User) request.getSession().getAttribute("onlineUser");
		String logonUser = ur.getId();
		request.setAttribute("searchuser", logonUser);
		List<Map<String, Object>> cubeUser = userDAO.sequense();
		request.setAttribute("cubeUser", cubeUser);
		java.util.Date date = new java.util.Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearnow = localDate.getYear();
		int year = yearnow;
		request.setAttribute("yearnow", yearnow); 
		request.setAttribute("year", year);
		List<Map<String, Object>> ts = expenseDAO.test_travelsummary(logonUser,year);
		request.setAttribute("ts", ts);
		
		List<Map<String, Object>> tsP = expenseDAO.test_travelsummaryP(logonUser,year);
		request.setAttribute("tsP", tsP);
	
		List<Map<String, Object>> tsW = expenseDAO.test_travelsummaryW(logonUser,year);
		request.setAttribute("tsW", tsW);
	
		List<Map<String, Object>> tsR = expenseDAO.test_travelsummaryR(logonUser,year);
		request.setAttribute("tsR", tsR);
	
		List<Map<String, Object>> tsA = expenseDAO.test_travelsummaryA(logonUser,year);
		request.setAttribute("tsA", tsA);
	
		
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}
		//searchWorkHourSummary
		public String SearchTravelSummary() {
		try {
		String logonUser = request.getParameter("user_id");
		request.setAttribute("searchuser", logonUser);
		String searchyaer = request.getParameter("year");
		int year = Interger.parseInt(searchyaer);
		request.setAttribute("year", year);
		java.util.Date date = new java.util.Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearnow = localDate.getYear();
		request.setAttribute("yearnow", yearnow);
List<Map<String, Object>> cubeUser = userDAO.sequense();
		request.setAttribute("cubeUser", cubeUser);
		List<Map<String, Object>> ts = expenseDAO.test_travelsummary(logonUser,year);
		request.setAttribute("ts", ts);
		
		List<Map<String, Object>> tsP = expenseDAO.test_travelsummaryP(logonUser,year);
		request.setAttribute("tsP", tsP);
		
		List<Map<String, Object>> tsW = expenseDAO.test_travelsummaryW(logonUser,year);
		request.setAttribute("tsW", tsW);
		
		List<Map<String, Object>> tsR = expenseDAO.test_travelsummaryR(logonUser,year);
		request.setAttribute("tsR", tsR);
		
		List<Map<String, Object>> tsA = expenseDAO.test_travelsummaryA(logonUser,year);
		request.setAttribute("tsA", tsA);
	
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}
		
		public String TravelSelectMonth() {
			try {

			java.util.Date date = new java.util.Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int yearnow = localDate.getYear();
			int year = yearnow;
			int month = localDate.getMonthValue();
			

			List<Map<String, Object>> ta = expenseDAO.test_travelamount(year);
			request.setAttribute("ta", ta);
		
			
			List<Map<String, Object>> taAll = expenseDAO.test_travelamountAll(year);
			request.setAttribute("taAll", taAll);
			

			request.setAttribute("year", year);
			request.setAttribute("type", month);
			return SUCCESS;
			} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
			}
			}

			public String searchTravelSelectMonth() {
			try {
			String searchyaer = request.getParameter("year");
			int year = Interger.parseInt(searchyaer);
			request.setAttribute("year", year);

			List<Map<String, Object>> ta = expenseDAO.test_travelamount(year);
			request.setAttribute("ta", ta);
		
			
			List<Map<String, Object>> taAll = expenseDAO.test_travelamountAll(year);
			request.setAttribute("taAll", taAll);
			

			String type1 = request.getParameter("type");
			int type = Interger.parseInt(type1);
			request.setAttribute("type", type1);

			return SUCCESS;
			} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
			}
			}

	}


