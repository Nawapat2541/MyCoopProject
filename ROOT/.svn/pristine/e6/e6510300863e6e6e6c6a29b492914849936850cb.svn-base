package com.cubesofttech.action;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.cubesofttech.dao.ExpenseDAO;
import com.cubesofttech.dao.ExpenseGroupDAO;
import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.dao.TimesheetDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.Timesheet;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.ibm.icu.util.GregorianCalendar;
import com.opensymphony.xwork2.ActionSupport;

public class TimesheetAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(TimesheetAction.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	public TimesheetDAO timesheetDAO;
	@Autowired
	private ExpenseDAO expenseDAO;
	@Autowired
	private ExpTravelTypeDAO expTravelTypeDAO;
	@Autowired
	private HolidayDAO holidayDAO;
	@Autowired
	private ExpenseGroupDAO expensegroupDAO;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public static final String TIMESHEET = "timesheet";

	public static final String USERSEQ = "userseq";
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
			Date dateTimeNow = new Date();
			request.setAttribute("dateTimeNow", dateTimeNow);
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String user = ur.getId();
			request.setAttribute("logonUser", user);
			String nametest = request.getParameter("nametest");
			request.setAttribute("nametest", nametest);
			String mounthtest = request.getParameter("mounthtest");
			request.setAttribute("mounthtest", mounthtest);
			
			int monthInt;
			int yearInt;
			GregorianCalendar date = new GregorianCalendar();
			monthInt = date.get(Calendar.MONTH);
			yearInt = date.get(Calendar.YEAR);
			monthInt = monthInt + 1;
			String month = Integer.toString(monthInt);
			if (month.length() == 1) {
				month = "0" + month;
			}
			String year = Integer.toString(yearInt);
			// set search
			request.setAttribute("name", user);
			request.setAttribute("monthSearch", month);
			request.setAttribute("yearSearch", year);
			request.setAttribute("flag", "1");
			// End set search

			// set day month year
			int iYear = Integer.parseInt(year);
			int iMonth = Integer.parseInt(month);
			// Create a calendar object and set year and month
			GregorianCalendar mycal = new GregorianCalendar(iYear, iMonth - 1, 1);
			// Get the number of days in that month
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
			String dateStartSearch = year + '/' + month + '/' + "01";
			String dateEndSearch = year + '/' + month + '/' + (daysInMonth + 2);
			List<Map<String, Object>> setTimeInTimeOut = timesheetDAO.findTimeInTimeOutBytimeMonthYear(user,
					dateStartSearch, dateEndSearch);
			List<Map<String, Object>> setDescription = timesheetDAO.findDescription(user, dateStartSearch,
					dateEndSearch);

			String[] arrayDay = new String[daysInMonth];
			String[] arrayTimeIn = new String[daysInMonth];
			String[] arrayTimeOut = new String[daysInMonth];
			String[] arrayDescription = new String[daysInMonth];
			String[] arrayStatus = new String[daysInMonth];
			String[] arrayId = new String[daysInMonth];
			String[] arrayDayHidden = new String[daysInMonth];
			Date[] fullDateKub = new Date[daysInMonth];
			
			for (int dayNum = 0; dayNum < daysInMonth; dayNum++) {
				dayNum = dayNum++;
				int num = dayNum + 1;
				String numStr = Integer.toString(num);
				arrayDay[dayNum] = numStr;
				if (numStr.length() == 1) {
					numStr = "0" + numStr;
				}
				String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
				String fullDate = numStr + "-" + newMonth + "-" + iYear;

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date dates = formatter.parse(fullDate);

			
				fullDateKub[dayNum] = dates;

				arrayDayHidden[dayNum] = numStr;

				for (Map<String, Object> obj : setTimeInTimeOut) {
					Object dateObj = obj.get("date");
					String timeInObj = obj.get("workin").toString().substring(11, 16);
					String timeOutObj = obj.get("workout").toString().substring(11, 16);
					String dateobjStr = dateObj.toString().substring(8, 10);
					if (numStr.equals(dateobjStr)) {
						arrayTimeIn[dayNum] = timeInObj;
						arrayTimeOut[dayNum] = timeOutObj;
					}

				}
				for (Map<String, Object> objDescription : setDescription) {
					String idTimesheet = objDescription.get("id").toString();
					String descriptionStr = objDescription.get("description").toString();
					String statusStr = objDescription.get("status").toString();
					String dateInStr = objDescription.get("time_check_in").toString();
					String timeInStr = objDescription.get("time_check_in").toString().substring(11, 16);
					String timeOutStr = objDescription.get("time_check_out").toString().substring(11, 16);
					String dateStr = dateInStr.substring(8, 10);
					if (numStr.length() == 1) {
						numStr = "0" + numStr;
					}
					if (numStr.equals(dateStr)) {
						arrayDescription[dayNum] = descriptionStr;
						arrayStatus[dayNum] = statusStr;
						if (!arrayDescription[dayNum].isEmpty()) {
							arrayTimeIn[dayNum] = timeInStr;
							arrayTimeOut[dayNum] = timeOutStr;
							arrayId[dayNum] = idTimesheet;
						}
					}

				}

			}
			List<Map<String, Object>> setHoli = timesheetDAO.findHoliday();
			request.setAttribute("setHoli", setHoli);
			
			request.setAttribute("arrayDayHidden", arrayDayHidden);
			request.setAttribute("monthList", iMonth);
			request.setAttribute("yearList", iYear);
			request.setAttribute("arrayDay", arrayDay);
			request.setAttribute("arrayTimeIn", arrayTimeIn);
			request.setAttribute("arrayTimeOut", arrayTimeOut);
			request.setAttribute("arrayDescription", arrayDescription);
			request.setAttribute("arrayStatus", arrayStatus);
			request.setAttribute("arrayId", arrayId);
			String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
			String newMouthnum = Integer.toString(iMonth);
			request.setAttribute("newMonth", newMonth);
			request.setAttribute("newMouthnum", newMouthnum);
			request.setAttribute("daysInMonth", daysInMonth);
			request.setAttribute("fullDateKub", fullDateKub);
			
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String listforadd() throws Exception {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			String useradd = request.getParameter("useradd");
			request.setAttribute(USERSEQ, userseq);
			request.setAttribute("useradd", useradd);

			String flag = request.getParameter("flag");
			if (flag != null) {
				String date = request.getParameter("date");
				request.setAttribute("date", date);
				request.setAttribute("flag", flag);
			}
			String userId = request.getParameter(USERID);
			String from = STARTTIME;
			String to = ENDTIME;
			request.setAttribute(USERID, userId);
			// List<Map<String, Object>> expensetableList = expenseDAO.findExpense();
			// request.setAttribute(EXPENSETABLELIST, expensetableList);
			// request.setAttribute(EXPENSELIST, expenseDAO.findAll());
			// request.setAttribute(EXPENSEGROUPLIST, expensegroupDAO.findAll());
			// request.setAttribute(USERLIST, userDAO.findAll());
			// request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());
			// request.setAttribute("expensegroup_type", expensegroupDAO.findtype());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String calendar() throws Exception {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String userIdTest = ur.getId();
			String userLogin = ur.getId();
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);

			String userId = request.getParameter(USERID);
			request.setAttribute("userSelect", userId);
			String from = STARTTIME;
			String to = ENDTIME;
			List<Map<String, Object>> timesheetSearchList = timesheetDAO.timesheetSearch_forCalendar(userId, from, to);
			// List<Map<String, Object>> expSearchList =
			// expenseDAO.expSearch_forCalendar(userId, from, to);

			request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());// ??????????????
			request.setAttribute("holidayList", holidayDAO.findAll());

			// List<ExpenseGroup> expensegroup = new ArrayList<>();
			// BigInteger expenseGroupId;
			/*
			 * for (int i = 0; i < expSearchList.size(); i++) { expenseGroupId =
			 * (BigInteger) expSearchList.get(i).get("expense_group_id"); ExpenseGroup ex =
			 * expensegroupDAO.findByGroupId(expenseGroupId.intValue()); // null
			 * expensegroup.add(ex); }
			 */

			request.setAttribute(EXPSEARCHLIST, timesheetSearchList);
			// request.setAttribute("expgroupList", expensegroup);
			request.setAttribute("flag_search", "");
			request.setAttribute(USERID, userId);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String timesheetDel() throws Exception {
		try {
			String Id = request.getParameter("id");
			Integer idValue = Integer.valueOf(Id);
			Timesheet timesheet = timesheetDAO.findById(idValue);
			request.setAttribute(USERID, timesheet.getUserCreate());
			timesheetDAO.delete(timesheet);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String addTimesheet() throws Exception {
		try {
			// Date dateTimeNow = new Date();

			String name = request.getParameter("name");
			String useradd = request.getParameter("useradd");
			String description = request.getParameter("description");
			String timestart = request.getParameter("timestart");
			String endtime = request.getParameter("endtime");
			Integer l = timesheetDAO.getMaxId() + 1;
			Timesheet timesheet = new Timesheet();
			timesheet.setId(l);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			Timestamp nowdate = Timestamp.valueOf(datenow);
			timesheet.setTimeCreate(nowdate);
			timesheet.setTimeUpdate(nowdate);
			timesheet.setDescription(description);
			timesheet.setStatus("W");
			Timestamp timestart1 = Timestamp.valueOf(timestart);
			Timestamp endtime1 = Timestamp.valueOf(endtime);

			timesheet.setTimeCheckIn(timestart1);
			timesheet.setTimeCheckOut(endtime1);

			timesheet.setUserCreate(useradd);
			timesheet.setUserUpdate(name);

			timesheetDAO.save(timesheet);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String updateTimesheet() throws Exception {// ????????????
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			Timestamp nowdate = Timestamp.valueOf(datenow);

			String userupdate = request.getParameter("userupdate");
			String timestartnew = request.getParameter("timestartnew");
			String timeendnew = request.getParameter("timeendnew");
			String id = request.getParameter("id");
			Integer idValue = Integer.valueOf(id);
			Timesheet timesheet = timesheetDAO.findById(idValue);

			String timeinit = timesheet.getTimeCheckIn().toString();
			String timelast = timesheet.getTimeCheckOut().toString();
			String subtimeinit = timeinit.substring(11, 19);
			String subtimelast = timelast.substring(11, 19);
			
			String newtimestart = timestartnew+" "+subtimeinit;
			String newtimelast = timeendnew+" "+subtimelast;
			
			Timestamp timestart = Timestamp.valueOf(newtimestart);
			Timestamp endtime = Timestamp.valueOf(newtimelast);

			timesheet.setTimeCheckIn(timestart);
			timesheet.setTimeCheckOut(endtime);
			timesheet.setUserUpdate(userupdate);
			timesheet.setTimeUpdate(nowdate);
			timesheetDAO.update(timesheet);
			return SUCCESS;

		} catch (Exception e) {
			return ERROR;
		}
	}

	public String saveTimesheet() throws Exception {
		try {
			Date dateTimeNow = new Date();
			request.setAttribute("dateTimeNow", dateTimeNow);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String nameuser = ur.getId();
			Timesheet timesheet = new Timesheet();
			String discription = request.getParameter("discription");
			String status = request.getParameter("status");
			String timeIn = request.getParameter("time_in");
			String timeOut = request.getParameter("time_out");
			String dateInOut = request.getParameter("dateInOut");
			String monthInOut = request.getParameter("monthInOut");
			String yearInOut = request.getParameter("yearInOut");
			String dateInOutStr = dateInOut + "-" + monthInOut + "-" + yearInOut;

			String dateNow = request.getParameter("date_now");
			String timeNow = request.getParameter("time_now");
			String idStr = request.getParameter("id");

			Timestamp dateTimeNowList = DateUtil.dateToTimestamp(dateNow, timeNow);
			Timestamp dateTimeIn = DateUtil.dateToTimestamp(dateInOutStr, timeIn);
			Timestamp dateTimeOut = DateUtil.dateToTimestamp(dateInOutStr, timeOut);
			String dateInOutStrSearch = dateTimeIn.toString().substring(0, 10);
			
			Integer l = timesheetDAO.getMaxId() + 1;
			timesheet.setId(l);
			
			timesheet.setDescription(discription);
			timesheet.setStatus("W");
			timesheet.setTimeCheckIn(dateTimeIn);
			timesheet.setTimeCheckOut(dateTimeOut);
			timesheet.setTimeCreate(dateTimeNowList);
			timesheet.setTimeUpdate(dateTimeNowList);
			timesheet.setUserCreate(nameuser);
			timesheet.setUserUpdate(nameuser);

			List<Map<String, Object>> timesheetDateList = timesheetDAO.searchTimesheet(nameuser, dateInOutStrSearch);
			if (timesheetDateList.isEmpty()) {
				timesheetDAO.save(timesheet);
			}
			List<Map<String, Object>> timesheetDate = timesheetDAO.searchTimesheet(nameuser, dateInOutStrSearch);
			String idListStr = timesheetDate.get(0).get("id").toString();
			if (!timesheetDateList.isEmpty()) {
				int idInt = Integer.parseInt(idListStr);
				timesheet.setId(idInt);
				timesheetDAO.update(timesheet);
			}

			return SUCCESS;

		} catch (Exception e) {
			log.error(e);

			return ERROR;
		}
	}

	public String searchTable() {
		try {
			Date dateTimeNow = new Date();
			request.setAttribute("dateTimeNow", dateTimeNow);
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			String user = request.getParameter("tempuser");
			String month = request.getParameter("monthSearch");
			String year = request.getParameter("yearSearch");
			// set search
			request.setAttribute("name", user);
			request.setAttribute("monthSearch", month);
			request.setAttribute("yearSearch", year);
			request.setAttribute("flag", "1");
			// End set search
			List<Map<String, Object>> setHoli = timesheetDAO.findHoliday();
			request.setAttribute("setHoli", setHoli);
			// set day month year
			int iYear = Integer.parseInt(year);
			int iMonth = Integer.parseInt(month);
			// Create a calendar object and set year and month
			GregorianCalendar mycal = new GregorianCalendar(iYear, iMonth - 1, 1);
			// Get the number of days in that month
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
			String dateStartSearch = year + '/' + month + '/' + "01";
			String dateEndSearch = year + '/' + month + '/' + (daysInMonth + 2);
			List<Map<String, Object>> setTimeInTimeOut = timesheetDAO.findTimeInTimeOutBytimeMonthYear(user,
					dateStartSearch, dateEndSearch);
			List<Map<String, Object>> setDescription = timesheetDAO.findDescription(user, dateStartSearch,dateEndSearch);
			String[] arrayDay = new String[daysInMonth];
			String[] arrayTimeIn = new String[daysInMonth];
			String[] arrayTimeOut = new String[daysInMonth];
			String[] arrayDescription = new String[daysInMonth];
			String[] arrayStatus = new String[daysInMonth];
			String[] arrayId = new String[daysInMonth];
			String[] arrayDayHidden = new String[daysInMonth];
			Date[] fullDateKub = new Date[daysInMonth];
			for (int dayNum = 0; dayNum < daysInMonth; dayNum++) {
				dayNum = dayNum++;
				int num = dayNum + 1;
				String numStr = Integer.toString(num);
				arrayDay[dayNum] = numStr;
				if (numStr.length() == 1) {
					numStr = "0" + numStr;
				}
				String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
				String fullDate = numStr + "-" + newMonth + "-" + iYear;

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date dates = formatter.parse(fullDate);

				fullDateKub[dayNum] = dates;
				arrayDayHidden[dayNum] = numStr;
				for (Map<String, Object> obj : setTimeInTimeOut) {
					Object dateObj = obj.get("date");
					String timeInObj = obj.get("workin").toString().substring(11, 16);
					String timeOutObj = obj.get("workout").toString().substring(11, 16);
					String dateobjStr = dateObj.toString().substring(8, 10);

					if (numStr.equals(dateobjStr)) {
						arrayTimeIn[dayNum] = timeInObj;
						arrayTimeOut[dayNum] = timeOutObj;

					}

				}
				for (Map<String, Object> objDescription : setDescription) {
					String idTimesheet = objDescription.get("id").toString();
					String descriptionStr = objDescription.get("description").toString();
					String statusStr = objDescription.get("status").toString();
					String dateInStr = objDescription.get("time_check_in").toString();
					String timeInStr = objDescription.get("time_check_in").toString().substring(11, 16);
					String timeOutStr = objDescription.get("time_check_out").toString().substring(11, 16);
					String dateStr = dateInStr.substring(8, 10);
					if (numStr.length() == 1) {
						numStr = "0" + numStr;
					}
					if (numStr.equals(dateStr)) {
						arrayDescription[dayNum] = descriptionStr;
						arrayStatus[dayNum] = statusStr;
						if (!arrayDescription[dayNum].isEmpty()) {
							arrayTimeIn[dayNum] = timeInStr;
							arrayTimeOut[dayNum] = timeOutStr;
							arrayId[dayNum] = idTimesheet;

						}
					}
				}

			}

			// set jsp
			request.setAttribute("arrayDayHidden", arrayDayHidden);
			request.setAttribute("monthList", iMonth);
			request.setAttribute("yearList", iYear);
			request.setAttribute("arrayDay", arrayDay);
			request.setAttribute("arrayTimeIn", arrayTimeIn);
			request.setAttribute("arrayTimeOut", arrayTimeOut);
			request.setAttribute("arrayId", arrayId);
			request.setAttribute("arrayDescription", arrayDescription);
			request.setAttribute("arrayStatus", arrayStatus);
			String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
			String newMouthnum = Integer.toString(iMonth);
			request.setAttribute("newMouthnum", newMouthnum);
			request.setAttribute("newMonth", newMonth);
			request.setAttribute("daysInMonth", daysInMonth);
			request.setAttribute("fullDateKub", fullDateKub);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);

			return ERROR;
		}

	}

	public String searchTable2() {
		try {
			Date dateTimeNow = new Date();
			request.setAttribute("dateTimeNow", dateTimeNow);
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			String user = request.getParameter("name");
			String month = request.getParameter("monthSearch");
			String year = request.getParameter("yearSearch");
			// set search
			request.setAttribute("name", user);
			request.setAttribute("monthSearch", month);
			request.setAttribute("yearSearch", year);
			request.setAttribute("flag", "1");
			// End set search

			// set day month year
			int iYear = Integer.parseInt(year);
			int iMonth = Integer.parseInt(month);
			// Create a calendar object and set year and month
			GregorianCalendar mycal = new GregorianCalendar(iYear, iMonth - 1, 1);
			// Get the number of days in that month
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
			String dateStartSearch = year + '/' + month + '/' + "01";
			String dateEndSearch = year + '/' + month + '/' + daysInMonth;
			List<Map<String, Object>> setTimeInTimeOut = timesheetDAO.findTimeInTimeOutBytimeMonthYear(user,
					dateStartSearch, dateEndSearch);
			List<Map<String, Object>> setDescription = timesheetDAO.findDescription(user, dateStartSearch,
					dateEndSearch);

			String[] arrayDay = new String[daysInMonth];
			String[] arrayTimeIn = new String[daysInMonth];
			String[] arrayTimeOut = new String[daysInMonth];
			String[] arrayDescription = new String[daysInMonth];
			String[] arrayStatus = new String[daysInMonth];
			String[] arrayId = new String[daysInMonth];
			String[] arrayDayHidden = new String[daysInMonth];
			Date[] fullDateKub = new Date[daysInMonth];
			for (int dayNum = 0; dayNum < daysInMonth; dayNum++) {
				dayNum = dayNum++;
				int num = dayNum + 1;
				String numStr = Integer.toString(num);
				arrayDay[dayNum] = numStr;
				if (numStr.length() == 1) {
					numStr = "0" + numStr;
				}
				String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
				String fullDate = numStr + "-" + newMonth + "-" + iYear;

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				Date dates = formatter.parse(fullDate);

				fullDateKub[dayNum] = dates;
				arrayDayHidden[dayNum] = numStr;
				for (Map<String, Object> obj : setTimeInTimeOut) {
					Object dateObj = obj.get("date");
					String timeInObj = obj.get("workin").toString().substring(11, 16);
					String timeOutObj = obj.get("workout").toString().substring(11, 16);
					String dateobjStr = dateObj.toString().substring(8, 10);

					if (numStr.equals(dateobjStr)) {
						arrayTimeIn[dayNum] = timeInObj;
						arrayTimeOut[dayNum] = timeOutObj;

					}

				}
				for (Map<String, Object> objDescription : setDescription) {
					String idTimesheet = objDescription.get("id").toString();
					String descriptionStr = objDescription.get("description").toString();
					String statusStr = objDescription.get("status").toString();
					String dateInStr = objDescription.get("time_check_in").toString();
					String timeInStr = objDescription.get("time_check_in").toString().substring(11, 16);
					String timeOutStr = objDescription.get("time_check_out").toString().substring(11, 16);
					String dateStr = dateInStr.substring(8, 10);
					if (numStr.length() == 1) {
						numStr = "0" + numStr;
					}
					if (numStr.equals(dateStr)) {
						arrayDescription[dayNum] = descriptionStr;
						arrayStatus[dayNum] = statusStr;
						if (!arrayDescription[dayNum].isEmpty()) {
							arrayTimeIn[dayNum] = timeInStr;
							arrayTimeOut[dayNum] = timeOutStr;
							arrayId[dayNum] = idTimesheet;

						}
					}

				}

			}

			// set jsp
			request.setAttribute("arrayDayHidden", arrayDayHidden);
			request.setAttribute("monthList", iMonth);
			request.setAttribute("yearList", iYear);
			request.setAttribute("arrayDay", arrayDay);
			request.setAttribute("arrayTimeIn", arrayTimeIn);
			request.setAttribute("arrayTimeOut", arrayTimeOut);
			request.setAttribute("arrayId", arrayId);
			request.setAttribute("holidayList", holidayDAO.findAll());
			request.setAttribute("arrayDescription", arrayDescription);
			request.setAttribute("arrayStatus", arrayStatus);
			String newMonth = new DateFormatSymbols().getShortMonths()[iMonth - 1];
			String newMouthnum = Integer.toString(iMonth);
			request.setAttribute("newMonth", newMonth);
			request.setAttribute("newMouthnum", newMouthnum);
			request.setAttribute("daysInMonth", daysInMonth);
			request.setAttribute("fullDateKub", fullDateKub);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);

			return ERROR;
		}

	}

	public String timesheetCalendarSearch() throws Exception {
		request.setAttribute(USERSEQ, userDAO.sequense());
		String userId = request.getParameter("name");
		request.setAttribute("userSelect", userId);
		String from = STARTTIME;
		String to = ENDTIME;
		// request.setAttribute(EXPTRAVELTYPELIST, expTravelTypeDAO.findAll());//
		// ??????????
		request.setAttribute("holidayList", holidayDAO.findAll());
		List<Map<String, Object>> timesheetSearchList = timesheetDAO.timesheetSearch_forCalendar(userId, from, to);
		request.setAttribute(USERID, userId);
		if (timesheetSearchList != null) {
			return SUCCESS;
		}
		// request.setAttribute(EXPSEARCHLIST, expSearchList);
		// request.setAttribute("expgroupList", expensegroup);
		return ERROR;
	}

	public String listUpdatestatusA() throws Exception {
		try {

			String Id = request.getParameter("id");
			Integer idValue = Integer.valueOf(Id);
			Timesheet timesheet = timesheetDAO.findById(idValue);
			request.setAttribute(USERID, timesheet.getUserCreate());
			timesheet.setStatus("A");
			timesheetDAO.update(timesheet);
			List<Timesheet> timesheetList = timesheetDAO.findAll();
			request.setAttribute("timesheetList", timesheetList);
			return SUCCESS;

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String listUpdatestatusAall() throws Exception {
		try {

			String Id = request.getParameter("id");
			String mm = request.getParameter("s");
			List<Map<String, Object>> listtimesheet = timesheetDAO.searchTimesheetByUserCreate(Id);
			for (int i = 0; i < listtimesheet.size(); i++) {
				BigInteger key = (BigInteger) listtimesheet.get(i).get("id");
				String result = listtimesheet.get(i).get("time_check_in").toString();
				String subresult = result.substring(0, 7);
				if (subresult.equals(mm)) {
					int id = key.intValue();
					Timesheet timesheet = timesheetDAO.findById(id);
					timesheet.setStatus("A");
					timesheetDAO.update(timesheet);
				}
			}

			return SUCCESS;

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String listUpdatestatusRall() throws Exception {
		try {
			String Id = request.getParameter("id");
			String mm = request.getParameter("s");
			List<Map<String, Object>> listtimesheet = timesheetDAO.searchTimesheetByUserCreate(Id);
			for (int i = 0; i < listtimesheet.size(); i++) {
				BigInteger key = (BigInteger) listtimesheet.get(i).get("id");
				int id = key.intValue();
				String result = listtimesheet.get(i).get("time_check_in").toString();
				String subresult = result.substring(0, 7);
				if (mm.equals(subresult)) {
					Timesheet timesheet = timesheetDAO.findById(id);
					timesheet.setStatus("R");
					timesheetDAO.update(timesheet);
				}

			}

			return SUCCESS;

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String listUpdatestatusR() throws Exception {
		try {

			String Id = request.getParameter("id");
			Integer idValue = Integer.valueOf(Id);
			Timesheet timesheet = timesheetDAO.findById(idValue);
			request.setAttribute(USERID, timesheet.getUserCreate());
			timesheet.setStatus("R");
			timesheetDAO.update(timesheet);
			List<Timesheet> timesheetList = timesheetDAO.findAll();
			request.setAttribute("timesheetList", timesheetList);
			return SUCCESS;

		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

}