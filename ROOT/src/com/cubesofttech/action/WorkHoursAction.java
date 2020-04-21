
package com.cubesofttech.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.DepartmentDAO;
import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.dao.LeaveDAO;
import com.cubesofttech.dao.LeaveTypeDAO;
//import com.cubesofttech.dao.QRCodeDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.dao.WorkHoursDAO;
import com.cubesofttech.model.Borrow;
import com.cubesofttech.model.Department;
import com.cubesofttech.model.Expense;
//import com.cubesofttech.model.QRCodeList;
import com.cubesofttech.model.Holiday;
import com.cubesofttech.model.LeaveType;
import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.User;
import com.cubesofttech.model.WorkHours;
import com.cubesofttech.service.LoginService;
import com.cubesofttech.system.Constant;
import com.cubesofttech.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.opensymphony.xwork2.ActionSupport;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class WorkHoursAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2280661337420278284L;

	private static final Integer Interger = null;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private UserDAO userDAO;

//	@Autowired
//	private QRCodeDAO qrcodeDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private LeaveDAO leaveDAO;

	@Autowired
	private LeaveTypeDAO leavetypeDAO;

	@Autowired
	private HolidayDAO holidayDAO;

	@Autowired
	private WorkHoursDAO workHoursDAO;
	
//	@Autowired
//	private Constant constant;

	@Autowired
	private LoginService loginService;
	
	
	private WorkHours workhours;
	private String number;
	private String worktype;
	private String qrcodeID;
//	private QRCodeList qrcodelist;
	

	private int maxId;
	private int currentQrID;
	private int currentQrStatus;


	
//	public QRCodeList getQrcodelist() {
//		return qrcodelist;
//	}
//	public void setQrcodelist(QRCodeList qrcodelist) {
//		this.qrcodelist = qrcodelist;
//	}
	public String getQrcodeID() {
		return qrcodeID;
	}
	public void setQrcodeID(String qrcodeID) {
		this.qrcodeID = qrcodeID;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype=worktype;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number=number;
	}

	public WorkHours getWorkhours() {
		return workhours;
	}

	public void setWorkhours(WorkHours workhours) {
		this.workhours = workhours;
	}

	private Integer workId;

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public int getMaxId() {
		return maxId;
	}
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}
	public int getCurrentQrID() {
		return currentQrID;
	}
	public void setCurrentQrID(int currentQrID) {
		this.currentQrID = currentQrID;
	}
	public int getCurrentQrStatus() {
		return currentQrStatus;
	}
	public void setCurrentQrStatus(int currentQrStatus) {
		this.currentQrStatus = currentQrStatus;
	}

	public static final String USERID = "userId";
	public static final String ONLINEUSER = "onlineUser";

	private static final String SUCCESS2 = null;

	public String checkin() {
		try {

			log.debug("login");

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			List<Map<String, Object>> work = workHoursDAO.checkIn(logonUser);
			List<Map<String, Object>> timecheckin = workHoursDAO.timecheckin(logonUser);
			List<Map<String, Object>> datecheckin = workHoursDAO.datecheckin(logonUser);
			List<Map<String, Object>> lastcheckin = workHoursDAO.lastcheckin(logonUser);
			String days = null;
			String da = null;
			for (Map<String, Object> maps : datecheckin) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					log.debug(entry.getValue());
					Date date;
					date = (Date) entry.getValue();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					days = dateFormat.format(date);

				}
			}
			for (Map<String, Object> maps : timecheckin) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					log.debug(entry.getValue());
					Date date = new Date();
					date = (Date) entry.getValue();
					DateFormat dateFormat = new SimpleDateFormat("HH:mm");
					da = dateFormat.format(date);
				}
			}

			request.setAttribute("datecheckin", days);
			request.setAttribute("timecheckin", da);
			request.setAttribute("lastcheckin", lastcheckin);
			request.setAttribute("currentDate", DateUtil.getCurrentTime());

			if (!work.isEmpty()) {
				String beforeType = String.valueOf(work.get(0).get("work_hours_type"));
				String beforeStamp = String.valueOf(work.get(0).get("time_create"));
				request.setAttribute("beforeType", beforeType);
				request.setAttribute("beforeStamp", beforeStamp);
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String day = dateFormat.format(date);
			String daycut = day.substring(6, 10);
			int foo = Integer.parseInt(daycut);
			if (foo > 2500) {
				int year = foo - 543;
				String strI = Integer.toString(year);
				String daycut2 = day.substring(0, 6);
				String fulldate = daycut2 + strI.trim();
				String time = DateUtil.getTimeNow();
				request.setAttribute("time", time);
				request.setAttribute("fulldate", fulldate);
			} else {
				String strI = Integer.toString(foo);
				String daycut2 = day.substring(0, 6);
				String fulldate = daycut2 + strI.trim();
				String time = DateUtil.getTimeNow();
				request.setAttribute("time", time);
				request.setAttribute("fulldate", fulldate);
			}

			
			//request.setAttribute("GOOGLE_API_KEY", constant.getGoogleApiKey());
			//PRODUCTION GOOGLE KEY
			request.setAttribute("GOOGLE_API_KEY", "AIzaSyDQajNmqwu5dl6tJ3nnOoYhnRl2ndchKAg");


			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchlist() {
		try {

			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser1 = ur.getId();
			String logonUser = request.getParameter("user.roletId");

			if (logonUser == null) {
				logonUser = logonUser1;
			}
			String department = request.getParameter("user.departmentId");
			request.setAttribute("departments", department);
			Timestamp mystartStamp = DateUtil.myDateStart(request.getParameter("Date-Start"));
			Timestamp myEndStamp = DateUtil.myDateEnd(request.getParameter("Date-End"));

			List<Map<String, Object>> work = workHoursDAO.searchList(logonUser, mystartStamp, myEndStamp);
			request.setAttribute("work", work);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			String startdate = request.getParameter("Date-Start");
			String enddate = request.getParameter("Date-End");

			request.setAttribute("department", department);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("logonUser", logonUser);
			request.setAttribute("flag_search", "1");

			if (logonUser.equals("All")) {
				List<Map<String, Object>> workallTime = workHoursDAO.workallTime();
				request.setAttribute("workallTime", workallTime);

				List<Map<String, Object>> workall = workHoursDAO.searchListall(mystartStamp, myEndStamp);
				request.setAttribute("workAll", workall);

				List<Map<String, Object>> checkinhourtime = workHoursDAO.searchListalltime(mystartStamp, myEndStamp);
				request.setAttribute("checkinhourtime", checkinhourtime);
				int hours = 0;
				int min = 0;
				int i = 0;
				for (Map<String, Object> map : checkinhourtime) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							workall.get(i).put("hour", hours);
							workall.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", mins);
									i++;

								} else {
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;

							}
						}
					}
				}

			} else {
				User user = userDAO.findById(logonUser);
				String stime = user.getWorkTimeStart() + ":00";
				request.setAttribute("stime", stime);

				List<Map<String, Object>> checkinhourtime = workHoursDAO.searchcheckinhourtime(logonUser, mystartStamp,
						myEndStamp);
				request.setAttribute("checkinhourtime", checkinhourtime);
				int hours = 0;
				int min = 0;
				int i = 0;
				for (Map<String, Object> map : checkinhourtime) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							work.get(i).put("hour", hours);
							work.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								work.get(i).put("hour", hours);
								work.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hour);
									work.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									work.get(i).put("hour", hour);
									work.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hours);
									work.get(i).put("min", mins);
									i++;

								} else {
									work.get(i).put("hour", hours);
									work.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								work.get(i).put("hour", hours);
								work.get(i).put("min", min);
								i++;

							}
						}
					}
				}
			}
			
			//request.setAttribute("GOOGLE_API_KEY", constant.getGoogleApiKey());
			//PRODDUCTION GOOGLE KEY
			request.setAttribute("GOOGLE_API_KEY", "AIzaSyDQajNmqwu5dl6tJ3nnOoYhnRl2ndchKAg");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	// workhourschart
	public String workHoursChart() {
		try {
			String monthSelect = request.getParameter("monthSelect");
			String yearSelect = request.getParameter("yearSelect");
			String amoutData = request.getParameter("amoutData");
			if (monthSelect == null || yearSelect == null || amoutData == null) {
				int amoutDataInt = 5;
				
				java.util.Date date = new java.util.Date();
				int year = date.getYear();
				year += 1900;
				int month = date.getMonth();
				month += 1;
				String monthNull = String.valueOf(month);
				String yearNull = String.valueOf(year); // if month and year both are null use this data
				
				List<Map<String, Object>> workByMonthDescriptionNull = workHoursDAO
						.searchByMonthAndYearDescriptionNull(monthNull, yearNull,amoutDataInt); //list of user that have no description
				
				List<Map<String, Object>> workByMonthDescriptionNotNull = workHoursDAO
						.searchByMonthAndYearDescriptionNotNull(monthNull, yearNull); //list of user that have description
				//create new list for keep data
				ArrayList<ArrayList<Map<String, Object>>> workBarchartsDescriptionNull = new ArrayList<ArrayList<Map<String, Object>>>();
				
				for (int i = 0; i < workByMonthDescriptionNull.size(); i++) {
					List<Map<String, Object>> testDescriptionNull = workHoursDAO.searchWorkCountbyUser( 
							(String) workByMonthDescriptionNull.get(i).get("user_create"), year, month);
					
					workBarchartsDescriptionNull.add((ArrayList<Map<String, Object>>) testDescriptionNull); //add user to new list one by one
				
				}
				
				
				request.setAttribute("NullworkBarcharts", workBarchartsDescriptionNull);
				
			
					request.setAttribute("NotNullworkBarcharts", workByMonthDescriptionNotNull);
				
				
				request.setAttribute("amountReturn", amoutDataInt);
				request.setAttribute("monthReturn", month);
				request.setAttribute("yearReturn", year);

				return SUCCESS;
			} else {
				int amoutDataInt = Integer.parseInt(amoutData);
				List<Map<String, Object>> workByMonth = workHoursDAO.searchByMonthAndYearDescriptionNull(monthSelect,
						yearSelect,amoutDataInt); 
				List<Map<String, Object>> workByMonthDescriptionNotNull = workHoursDAO
						.searchByMonthAndYearDescriptionNotNull(monthSelect, yearSelect); //list of user that have description
				ArrayList<ArrayList<Map<String, Object>>> workBarcharts = new ArrayList<ArrayList<Map<String, Object>>>();
				for (int i = 0; i < workByMonth.size(); i++) {
					Date time = (Date) workByMonth.get(i).get("time_create");
					int year = time.getYear();
					int month = time.getMonth();
					year += 1900;
					month += 1;
					List<Map<String, Object>> test = workHoursDAO
							.searchWorkCountbyUser((String) workByMonth.get(i).get("user_create"), year, month); //find work time by user
					workBarcharts.add((ArrayList<Map<String, Object>>) test);
				}
				request.setAttribute("NullworkBarcharts", workBarcharts);
				request.setAttribute("NotNullworkBarcharts", workByMonthDescriptionNotNull);
				request.setAttribute("amountReturn", amoutDataInt);
				request.setAttribute("monthReturn", monthSelect);
				request.setAttribute("yearReturn", yearSelect);
				request.setAttribute("amoutReturn", amoutData);

				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String checkEdit() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();

			String id = request.getParameter("Id");
			List<Map<String, Object>> work = workHoursDAO.checklistLL(id);
			request.setAttribute("work", work);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String updatecheck() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			String type = request.getParameter("work_hours_type");
			String mydate = request.getParameter("work_hours_date_work");
			String mytime = request.getParameter("work_hours_time_work");
			String lat = request.getParameter("latitude");
			String lng = request.getParameter("longitude");

			String userId = request.getParameter("name");
			String workid = request.getParameter("workhoursId");
			int workId = Integer.valueOf(workid);

			Timestamp fulldatetime = DateUtil.dateToTimestamp(mydate, mytime);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			String date = null;
			String month = null;
			String year = null;
			date = mydate.substring(0, 2);
			month = mydate.substring(3, 5);
			year = mydate.substring(6, 10);

			List<Map<String, Object>> checktimehours = workHoursDAO.checktimehours(logonUser, date, month, year);
			List<Map<String, Object>> checktimemin = workHoursDAO.checktimemin(logonUser, date, month, year);

			int types = Integer.valueOf(type);
			int inhour = 0, fullhour = 0, fullmin = 0, inmin = 0, fulltime = 0, outhours = 0, outmins = 0;
			String timeouthour = null, timeoutmin = null;
			String timeouthours = mytime.substring(1, 2);
			if (timeouthours.equals(":")) {
				timeouthour = mytime.substring(0, 1);
				timeoutmin = mytime.substring(2, 4);
			} else {
				timeouthour = mytime.substring(0, 2);
				timeoutmin = mytime.substring(3, 5);
			}
			outhours = Integer.valueOf(timeouthour);
			outmins = Integer.valueOf(timeoutmin);

			String des = request.getParameter("description");
			Date date2 = new Date();
			Timestamp tstamp = new Timestamp(date2.getTime());

			WorkHours wh = workHoursDAO.findById(workId);

			wh.setWorkHoursId(workId);
			wh.setWorkHoursTimeWork(fulldatetime);
			wh.setWorkHoursType(type);
			wh.setWorkinghours(fulltime);
			// wh.setUserCreate(logonUser);
			wh.setTimeCreate(tstamp);
			wh.setDescription(des);
			wh.setLatitude(lat);
			wh.setLongitude(lng);

			Map<String, String> headersInfo = getHeadersInfo(request);
			String userAgent = (String) headersInfo.get("user-agent");
			wh.setUserAgent(userAgent);

			String ipAddress = (String) headersInfo.get("ipAddress");
			wh.setIpAddress(ipAddress);

			workHoursDAO.update(wh);

			return SUCCESS;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchhis() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String searchlogonUser = ur.getId();
			request.setAttribute("logonUser", searchlogonUser);

			String logonUser = request.getParameter("user.roletId");
			String department = request.getParameter("user.departmentId");

			Timestamp mystartStamp = DateUtil.myDateStart(request.getParameter("Date-Start"));
			Timestamp myEndStamp = DateUtil.myDateEnd(request.getParameter("Date-End"));

			List<Map<String, Object>> work = workHoursDAO.searchHis(logonUser, mystartStamp, myEndStamp);
			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);
			request.setAttribute("work", work);
			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			String startdate = request.getParameter("Date-Start");
			String enddate = request.getParameter("Date-End");

			request.setAttribute("department", department);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("logonUser", logonUser);

			if (logonUser.equals("All")) {
				List<Map<String, Object>> workall = workHoursDAO.searchHisall(mystartStamp, myEndStamp);
				request.setAttribute("workall", workall);
			}
			log.info(work);

			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String list() {
		try {

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			request.setAttribute("logonUser", logonUser);
			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(logonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date Longday = DateUtil.periodMinus(date, 8);
			Timestamp tstampbefore = new Timestamp(Longday.getTime());

			Date date1;
			date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);

			String month = datenow.substring(3, 5);
			String year = datenow.substring(6, 10);

			List<Map<String, Object>> work = workHoursDAO.checklist(logonUser, tstamp, tstampbefore, month, year);

			User user = userDAO.findById(logonUser);

			String stime = user.getWorkTimeStart() + ":00";

			request.setAttribute("stime", stime);
			request.setAttribute("work", work);
			JSONArray data = new JSONArray(work);
			request.setAttribute("data", data);
			List<Map<String, Object>> checktime = workHoursDAO.checktime(logonUser, month, year);
			request.setAttribute("checktime", checktime);
			int hours = 0;
			int min = 0;
			int i = 0;

			for (Map<String, Object> map : checktime) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() == null) {
						hours = 0;
						min = 0;
						work.get(i).put("hour", hours);
						work.get(i).put("min", min);
						i++;
					} else {
						int x = (Integer) entry.getValue();
						if ((x == 0)) {
							hours = 0;
							min = 0;
							String h = String.valueOf(hours);
							String m = String.valueOf(min);
							String hour = ("" + 0 + h);
							String mins = ("" + 0 + m);
							work.get(i).put("hour", hour);
							work.get(i).put("min", mins);
							i++;
						} else if (x > 0) {
							min = x % 60;
							hours = x / 60;
							if (hours < 10 && min < 10) {
								String h = String.valueOf(hours);
								String m = String.valueOf(min);
								String hour = ("" + 0 + h);
								String mins = ("" + 0 + m);
								work.get(i).put("hour", hour);
								work.get(i).put("min", mins);
								i++;
							} else if (hours < 10) {
								String h = String.valueOf(hours);
								String hour = ("" + 0 + h);
								work.get(i).put("hour", hour);
								work.get(i).put("min", min);
								i++;
							} else if (min < 10) {
								String m = String.valueOf(min);
								String mins = ("" + 0 + m);
								work.get(i).put("hour", hours);
								work.get(i).put("min", mins);
								i++;

							} else {
								work.get(i).put("hour", hours);
								work.get(i).put("min", min);
								i++;

							}
						} else {
							hours = 0;
							min = 0;
							work.get(i).put("hour", hours);
							work.get(i).put("min", min);
							i++;

						}
					}

				}
			}

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11;
					date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			if (startmonth.size() == 0) {
				startmonths = "01-" + month + "-" + year;

			}
			request.setAttribute("startmonths", startmonths);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String listhistory() {
		try {

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userhistory = ur.getId();
			String searchlogonUser = ur.getId();
			request.setAttribute("logonUser", searchlogonUser);

			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(searchlogonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1;
			date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);

			String month = datenow.substring(3, 5);
			String year = datenow.substring(6, 10);

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(searchlogonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = new Date();
					date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			if (startmonth.size() == 0) {
				startmonths = "01-" + month + "-" + year;
			}
			request.setAttribute("startmonths", startmonths);

			List<Map<String, Object>> work = workHoursDAO.checkhistory(userhistory, month, year);
			request.setAttribute("work", work);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String savecheck() {
		try {
			String type = request.getParameter("work_hours_type");
			String mydate = request.getParameter("work_hours_date_work");
			String mytime = request.getParameter("work_hours_time_work");

			String lat = request.getParameter("latitude");
			String lng = request.getParameter("longitude");

			Timestamp fulldatetime = DateUtil.dateToTimestamp(mydate, mytime);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			String date = null;
			String month = null;
			String year = null;
			date = mydate.substring(0, 2);
			month = mydate.substring(3, 5);
			year = mydate.substring(6, 10);

			List<Map<String, Object>> checktimehours = workHoursDAO.checktimehours(logonUser, date, month, year);
			List<Map<String, Object>> checktimemin = workHoursDAO.checktimemin(logonUser, date, month, year);

			int types = Integer.valueOf(type);
			int inhour = 0, fullhour = 0, fullmin = 0, inmin = 0, fulltime = 0, outhours = 0, outmins = 0;
			String timeouthour = null, timeoutmin = null;
			String timeouthours = mytime.substring(1, 2);
			if (timeouthours.equals(":")) {
				timeouthour = mytime.substring(0, 1);
				timeoutmin = mytime.substring(2, 4);
			} else {
				timeouthour = mytime.substring(0, 2);
				timeoutmin = mytime.substring(3, 5);
			}
			outhours = Integer.valueOf(timeouthour);
			outmins = Integer.valueOf(timeoutmin);

			for (Map<String, Object> map : checktimehours) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					BigInteger x = (BigInteger) entry.getValue();
					inhour = x.intValue();

				}
			}

			for (Map<String, Object> maps : checktimemin) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					BigInteger a = (BigInteger) entry.getValue();
					inmin = a.intValue();
					fullmin = (outmins - inmin);
				}
			}
			if (checktimehours.size() == 0) {
				String time = null;
				List<Map<String, Object>> usertype1 = workHoursDAO.usertype1(logonUser);
				for (Map<String, Object> maps : usertype1) {
					for (Map.Entry<String, Object> entry : maps.entrySet()) {
						log.debug(entry.getValue());
						Date date1 = new Date();
						date1 = (Date) entry.getValue();
						DateFormat dateFormat = new SimpleDateFormat("HH:mm");
						time = dateFormat.format(date1);
						inhour = Interger.valueOf(time.substring(0, 2));
						inmin = Interger.valueOf(time.substring(3, 5));
						fullmin = (outmins - inmin);
					}

				}

			}

			if (types == 2) {

				if (outhours < inhour) {
					fullhour = ((24 - inhour) + outhours) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;

				} else if (outhours == inhour && inmin > outmins) {
					fullhour = ((24 - inhour) + outhours) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;
				} else {
					fullhour = (outhours - inhour) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;
				}

			} else if (types == 1) {
				fulltime = 0;
			}

			Integer w = workHoursDAO.getMaxId();
			String des = request.getParameter("description");
			Date date2 = new Date();
			Timestamp tstamp = new Timestamp(date2.getTime());
			List<Map<String, Object>> check_in = workHoursDAO.check_in(logonUser);
			int datecheck_in = 0;
			int datecheck_out = Integer.valueOf(date);
			int checkinnotcheckout = ((inhour * 60) + inmin);
			int checkoutnotcheckout = ((outhours * 60) + outmins);
			for (Map<String, Object> maps : check_in) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					BigInteger x = (BigInteger) entry.getValue();
					datecheck_in = Integer.valueOf(x.toString());
				}
			}
			if (datecheck_in != datecheck_out && checkoutnotcheckout >= checkinnotcheckout) {
				fulltime = 0;

			}
			if (checktimehours.size() == 0) {
				fulltime = 0;
			}

			WorkHours wh = new WorkHours();
			wh.setWorkHoursTimeWork(fulldatetime);
			wh.setWorkHoursType(type);
			wh.setWorkinghours(fulltime);
			wh.setUserCreate(logonUser);
			wh.setTimeCreate(tstamp);
			wh.setDescription(des);
			wh.setWorkHoursId(w + 1);
			wh.setLatitude(lat);
			wh.setLongitude(lng);

			Map<String, String> headersInfo = getHeadersInfo(request);
			String userAgent = (String) headersInfo.get("user-agent");
			wh.setUserAgent(userAgent);

			String ipAddress = (String) headersInfo.get("ipAddress");
			wh.setIpAddress(ipAddress);

			workHoursDAO.save(wh);

			return SUCCESS;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getHeader("X_FORWARDED_FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("ipAddress", ipAddress);

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	public String approve() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			request.setAttribute("logonUser", logonUser);
			request.setAttribute("checkradio", 1);
			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(logonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date Longday = DateUtil.periodMinus(date, 8);
			Timestamp tstampbefore = new Timestamp(Longday.getTime());

			Date date1;
			date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);

			String month = datenow.substring(3, 5);
			String year = datenow.substring(6, 10);

			List<Map<String, Object>> workapprove = workHoursDAO.checklistapprove(logonUser, tstamp, tstampbefore,
					month, year);
			request.setAttribute("workapprove", workapprove);

			List<Map<String, Object>> timcheckout = workHoursDAO.timecheckout(logonUser, tstamp, tstampbefore, month,
					year);
			request.setAttribute("timcheckout", timcheckout);

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11;
					date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			if (startmonth.size() == 0) {
				startmonths = "01-" + month + "-" + year;

			}
			request.setAttribute("startmonths", startmonths);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			return SUCCESS;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	public String search_approve() {
		try {

			String logonUser = request.getParameter("user.roletId");
			User ur = (User) userDAO.findById(logonUser);
			String department = request.getParameter("user.departmentId");
			request.setAttribute("departments", department);
			Timestamp mystartStamp = DateUtil.myDateStart(request.getParameter("Date-Start"));
			Timestamp myEndStamp = DateUtil.myDateEnd(request.getParameter("Date-End"));

			List<Map<String, Object>> workapprove = workHoursDAO.worksearchapprove(logonUser, mystartStamp, myEndStamp);
			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);
			request.setAttribute("workapprove", workapprove);
			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			String startdate = request.getParameter("Date-Start");
			String enddate = request.getParameter("Date-End");

			request.setAttribute("department", department);
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);
			request.setAttribute("logonUser", ur.getId());
			String checkradio = request.getParameter("check");
			request.setAttribute("checkradio", checkradio);
			if (logonUser.equals("All") && checkradio.equals("2")) {
				List<Map<String, Object>> workall = workHoursDAO.searchListallapprove(mystartStamp, myEndStamp);
				request.setAttribute("workall", workall);

				List<Map<String, Object>> checkinhourtime = workHoursDAO.searchListalltimeapprove(mystartStamp,
						myEndStamp);
				request.setAttribute("checkinhourtime", checkinhourtime);
				int hours = 0, min = 0, i = 0;
				for (Map<String, Object> map : checkinhourtime) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							workall.get(i).put("hour", hours);
							workall.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", mins);
									i++;

								} else {
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;

							}
						}
					}
				}

			} else if (logonUser != "All" && checkradio.equals("2")) {

				List<Map<String, Object>> checkinhourtimeapprove = workHoursDAO.searchcheckinhourtimeapprove(logonUser,
						mystartStamp, myEndStamp);
				request.setAttribute("checkinhourtimeapprove", checkinhourtimeapprove);
				int hours = 0, min = 0, i = 0;
				for (Map<String, Object> map : checkinhourtimeapprove) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							workapprove.get(i).put("hour", hours);
							workapprove.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								workapprove.get(i).put("hour", hours);
								workapprove.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workapprove.get(i).put("hour", hour);
									workapprove.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									workapprove.get(i).put("hour", hour);
									workapprove.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									workapprove.get(i).put("hour", hours);
									workapprove.get(i).put("min", mins);
									i++;

								} else {
									workapprove.get(i).put("hour", hours);
									workapprove.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								workapprove.get(i).put("hour", hours);
								workapprove.get(i).put("min", min);
								i++;

							}
						}
					}
				}
			} else if (logonUser.equals("All") && checkradio.equals("1")) {
				List<Map<String, Object>> workall = workHoursDAO.searchListallapprovecheckin(mystartStamp, myEndStamp);
				request.setAttribute("workall", workall);

			} else if (logonUser != "All" && checkradio.equals("1")) {
				List<Map<String, Object>> workapprove1 = workHoursDAO.searchListallapprovecheckinuser(logonUser,
						mystartStamp, myEndStamp);
				request.setAttribute("workapprove", workapprove1);

			} else if (logonUser.equals("All") && checkradio.equals("3")) {
				List<Map<String, Object>> workall = workHoursDAO.searchListall(mystartStamp, myEndStamp);
				request.setAttribute("workall", workall);

				List<Map<String, Object>> checkinhourtime = workHoursDAO.searchListalltime(mystartStamp, myEndStamp);
				request.setAttribute("checkinhourtime", checkinhourtime);
				int hours = 0, min = 0, i = 0;
				for (Map<String, Object> map : checkinhourtime) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							workall.get(i).put("hour", hours);
							workall.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									workall.get(i).put("hour", hour);
									workall.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", mins);
									i++;

								} else {
									workall.get(i).put("hour", hours);
									workall.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								workall.get(i).put("hour", hours);
								workall.get(i).put("min", min);
								i++;

							}
						}
					}
				}

			} else if (logonUser != "All" && checkradio.equals("3")) {
				List<Map<String, Object>> workapprove2 = workHoursDAO.worksearchapproveall(logonUser, mystartStamp,
						myEndStamp);
				request.setAttribute("workapprove", workapprove2);

				List<Map<String, Object>> checkinhourtime = workHoursDAO.searchcheckinhourtime(logonUser, mystartStamp,
						myEndStamp);
				request.setAttribute("checkinhourtime", checkinhourtime);
				int hours = 0, min = 0, i = 0;
				for (Map<String, Object> map : checkinhourtime) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if (entry.getValue() == null) {
							hours = 0;
							min = 0;
							workapprove2.get(i).put("hour", hours);
							workapprove2.get(i).put("min", min);
							i++;
						} else {
							int z = (Integer) entry.getValue();
							if (z == 0) {
								hours = 0;
								min = 0;
								workapprove2.get(i).put("hour", hours);
								workapprove2.get(i).put("min", min);
								i++;
							} else if (z > 0) {
								min = z % 60;
								hours = z / 60;
								if (hours < 10 && min < 10) {
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workapprove2.get(i).put("hour", hour);
									workapprove2.get(i).put("min", mins);
									i++;
								} else if (hours < 10) {
									String h = String.valueOf(hours);
									String hour = ("" + 0 + h);
									workapprove2.get(i).put("hour", hour);
									workapprove2.get(i).put("min", min);
									i++;
								} else if (min < 10) {
									String m = String.valueOf(min);
									String mins = ("" + 0 + m);
									workapprove2.get(i).put("hour", hours);
									workapprove2.get(i).put("min", mins);
									i++;

								} else {
									workapprove2.get(i).put("hour", hours);
									workapprove2.get(i).put("min", min);
									i++;

								}
							} else {
								hours = 0;
								min = 0;
								workapprove2.get(i).put("hour", hours);
								workapprove2.get(i).put("min", min);
								i++;

							}
						}
					}
				}
			} else {
			}

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String saveapprove() {
		try {
			String[] time = request.getParameterValues("time");
			request.setAttribute("time", time);

			String[] usercheckin = request.getParameterValues("usercheckin");
			request.setAttribute("usercheckin", usercheckin);

			String[] timecheck = request.getParameterValues("timecheckout");
			request.setAttribute("timecheck", timecheck);

			String[] idcheckout = request.getParameterValues("idcheckout");
			request.setAttribute("idcheckout", idcheckout);

			String[] checktype = request.getParameterValues("checktype");
			request.setAttribute("checktype", checktype);

			String checkradio = request.getParameter("checkradio");
			request.setAttribute("checkradio", checkradio);

			String userupdate = request.getParameter("userupdate");
			request.setAttribute("checkradio", userupdate);

			if (time != null) {
				for (int i = 0; i < time.length; i++) {
					int outhours = 0, outmin = 0;
					String timeouthour = null, timeoutmin = null;
					String timecheckoout = timecheck[i].substring(1, 2);
					String checktyp = checktype[i];
					if (timecheckoout.equals(":")) {
						timeouthour = timecheck[i].substring(0, 1);
						timeoutmin = timecheck[i].substring(2, 4);
					} else {
						timeouthour = timecheck[i].substring(0, 2);
						timeoutmin = timecheck[i].substring(3, 5);
					}
					outhours = Integer.valueOf(timeouthour);
					outmin = Integer.valueOf(timeoutmin);

					String timecheckin = time[i];
					String usercheck = usercheckin[i];
					String y = null;
					String m = null;
					String d = null;
					String id = null;
					String id1 = null;
					y = timecheckin.substring(0, 4);
					m = timecheckin.substring(5, 7);
					d = timecheckin.substring(8, 10);

					List<Map<String, Object>> checkintime = workHoursDAO.checkin(usercheck, y, m, d);
					request.setAttribute("checkintime", checkintime);

					for (Map<String, Object> maps : checkintime) {
						for (Map.Entry<String, Object> entry : maps.entrySet()) {
							BigInteger x = (BigInteger) entry.getValue();
							id = x.toString();
						}
					}
					if (checktyp.equals("2")) {
						List<Map<String, Object>> idtoday = workHoursDAO.idtoday(usercheck, y, m, d);
						request.setAttribute("idtoday", idtoday);
						int inhour = 0;
						int inmin = 0;
						int fullhour = 0;
						int fullmin = 0;
						int fulltime = 0;
						for (Map<String, Object> maps : idtoday) {
							for (Map.Entry<String, Object> entry : maps.entrySet()) {
								BigInteger x = (BigInteger) entry.getValue();
								id1 = x.toString();

							}
						}

						List<Map<String, Object>> hourtimeidtodaycheckin = workHoursDAO
								.hourtimeidtodaycheckin(usercheck, id1);
						request.setAttribute("hourtimeidtodaycheckin", hourtimeidtodaycheckin);
						for (Map<String, Object> maps : hourtimeidtodaycheckin) {
							for (Map.Entry<String, Object> entry : maps.entrySet()) {
								BigInteger x1 = (BigInteger) entry.getValue();
								inhour = x1.intValue();

							}
						}
						List<Map<String, Object>> mintimeidtodaycheckin = workHoursDAO.mintimeidtodaycheckin(usercheck,
								id);
						request.setAttribute("mintimeidtodaycheckin", mintimeidtodaycheckin);
						for (Map<String, Object> maps : mintimeidtodaycheckin) {
							for (Map.Entry<String, Object> entry : maps.entrySet()) {
								BigInteger x1 = (BigInteger) entry.getValue();
								inmin = x1.intValue();

							}
						}

						if (outhours < inhour) {
							String time1 = timecheckin.substring(0, 10);
							String timenew = timecheck[i];
							Timestamp fulldatetime = DateUtil.yearToTimestamp(time1, timenew);
							fullhour = ((24 - inhour) + outhours) * 60;
							fullmin = (outmin - inmin);
							fulltime = fullhour + fullmin;
							WorkHours updatetime = workHoursDAO.findById(Integer.valueOf(id1));
							updatetime.setWorkHoursTimeWork(fulldatetime);
							updatetime.setWorkinghours(fulltime);
							workHoursDAO.update(updatetime);

						}
					}

					if ((checkradio.equals("1") || checkradio.equals("3")) && checktyp.equals("1")) {
						String idcheckintoday = idcheckout[i];

						String time1 = timecheckin.substring(0, 10);
						String timenew = timecheck[i];
						Timestamp fulldatetime = DateUtil.yearToTimestamp(time1, timenew);

						WorkHours updatetime = workHoursDAO.findById(Integer.valueOf(idcheckout[i]));
						updatetime.setWorkHoursTimeWork(fulldatetime);
						workHoursDAO.update(updatetime);

						List<Map<String, Object>> fullidtimecheckout = workHoursDAO.fullidtimecheckout(usercheck, y, m,
								d); // check-out > 1
						request.setAttribute("fullidtimecheckout", fullidtimecheckout);

						if (fullidtimecheckout.size() != 0) {
							for (int j = 0; j < fullidtimecheckout.size(); j++) {
								for (Map<String, Object> maps : fullidtimecheckout) {
									for (Map.Entry<String, Object> entry : maps.entrySet()) {
										BigInteger x = (BigInteger) entry.getValue();
										id = x.toString();
										int inhour = 0;
										int inmin = 0;
										int fullhour = 0;
										int fullmin = 0;
										int fulltime = 0;
										List<Map<String, Object>> timeidcheckouthour = workHoursDAO
												.timeidcheckouthour(id);
										request.setAttribute("timeidcheckouthour", timeidcheckouthour);

										List<Map<String, Object>> timeidcheckoutmin = workHoursDAO
												.timeidcheckoutmin(id);
										request.setAttribute("timeidcheckoutmin", timeidcheckoutmin);

										for (Map<String, Object> map : timeidcheckouthour) {
											for (Map.Entry<String, Object> entry1 : map.entrySet()) {
												BigInteger x1 = (BigInteger) entry1.getValue();
												outhours = x1.intValue();
											}
										}
										for (Map<String, Object> map : timeidcheckoutmin) {
											for (Map.Entry<String, Object> entry1 : map.entrySet()) {
												BigInteger x1 = (BigInteger) entry1.getValue();
												outmin = x1.intValue();
											}
										}
										inhour = Integer.valueOf(timeouthour);
										inmin = Integer.valueOf(timeoutmin);

										if (outhours > inhour) {
											fullhour = ((outhours - inhour) * 60);
											fullmin = (outmin - inmin);
											fulltime = (fullhour + fullmin);

											WorkHours updatetime1 = workHoursDAO.findById(Integer.valueOf(id));
											updatetime1.setWorkinghours(fulltime);
											workHoursDAO.update(updatetime1);
										} else {
										}
									}
								}

							}

						} else { // check out tomorow
							String idtomorrow = null;
							int inhour = 0;
							int inmin = 0;
							int fullhour = 0;
							int fullmin = 0;
							int fulltime = 0;
							List<Map<String, Object>> searchidcheckintomorrow = workHoursDAO
									.searchidcheckintomorrow(usercheck, idcheckintoday);
							for (Map<String, Object> maps : searchidcheckintomorrow) {
								for (Map.Entry<String, Object> entry : maps.entrySet()) {
									BigInteger x = (BigInteger) entry.getValue();
									idtomorrow = x.toString();

								}
							}

							List<Map<String, Object>> searchtimehourcheckintomorrow = workHoursDAO
									.searchtimehourcheckintomorrow(usercheck, idcheckintoday);
							for (Map<String, Object> maps : searchtimehourcheckintomorrow) {
								for (Map.Entry<String, Object> entry : maps.entrySet()) {
									BigInteger x1 = (BigInteger) entry.getValue();
									inhour = x1.intValue();

								}
							}
							List<Map<String, Object>> searchtimemincheckintomorrow = workHoursDAO
									.searchtimemincheckintomorrow(usercheck, idcheckintoday);
							for (Map<String, Object> maps : searchtimemincheckintomorrow) {
								for (Map.Entry<String, Object> entry : maps.entrySet()) {
									BigInteger x2 = (BigInteger) entry.getValue();
									inmin = x2.intValue();

								}
							}
							if (outhours > inhour && idtomorrow != null) { // in=out
								fullhour = ((24 - outhours) + inhour) * 60;
								fullmin = (outmin - inmin);
								fulltime = fullhour + fullmin;
								WorkHours updatetime1 = workHoursDAO.findById(Integer.valueOf(idtomorrow));
								updatetime1.setWorkinghours(fulltime);
								workHoursDAO.update(updatetime1);

							}
						}

					} else if ((checkradio.equals("2") || checkradio.equals("3")) && checktyp.equals("2")) {

						String time1 = timecheckin.substring(0, 10);
						String timenew = timecheck[i];
						Timestamp fulldatetime = DateUtil.yearToTimestamp(time1, timenew);

						List<Map<String, Object>> timecheckin1 = workHoursDAO.timecheckin1(id);
						request.setAttribute("timecheckin1", timecheckin1);
						List<Map<String, Object>> timecheckin2 = workHoursDAO.timecheckin2(id);
						request.setAttribute("timecheckin2", timecheckin2);
						int inhour = 0;
						int inmin = 0;
						int fullmin = 0;
						int fullhour = 0;
						int fulltime = 0;

						for (Map<String, Object> map : timecheckin1) {
							for (Map.Entry<String, Object> entry : map.entrySet()) {
								BigInteger x = (BigInteger) entry.getValue();
								inhour = x.intValue();
							}
						}

						for (Map<String, Object> maps : timecheckin2) {
							for (Map.Entry<String, Object> entry : maps.entrySet()) {
								BigInteger a = (BigInteger) entry.getValue();
								inmin = a.intValue();
							}
						}

						if (outhours > inhour && id != null) {
							fullhour = ((outhours - inhour) * 60);
							fullmin = (outmin - inmin);
							fulltime = (fullhour + fullmin);

							WorkHours updatetime = workHoursDAO.findById(Integer.valueOf(idcheckout[i]));
							updatetime.setWorkinghours(fulltime);
							updatetime.setWorkHoursTimeWork(fulldatetime);
							workHoursDAO.update(updatetime);

						} else if (outhours < inhour) {
							// check in Yesterday

						} else {
							fulltime = 0;
							// check in Yesterday > 1 day
						}
					} else if (checkradio.equals("All")) {

					}

					else {
					}

				}
			}
			// return
			List<Map<String, Object>> cubeUser = userDAO.allName();
			request.setAttribute("cubeUser", cubeUser);

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			request.setAttribute("logonUser", logonUser);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date Longday = DateUtil.periodMinus(date, 8);
			Timestamp tstampbefore = new Timestamp(Longday.getTime());

			Date date1 = tstamp;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);

			String month = datenow.substring(3, 5);
			String year = datenow.substring(6, 10);

			List<Map<String, Object>> checktimeapprove = workHoursDAO.checktimeapprove(logonUser, month, year);
			request.setAttribute("checktimeapprove", checktimeapprove);

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			if (startmonth.size() == 0) {
				startmonths = "01-" + month + "-" + year;
			}
			request.setAttribute("startmonths", startmonths);

			if (time != null) {
				if (checkradio.equals("1")) {

					List<Map<String, Object>> workapprove = workHoursDAO.checklistapprovestatus(logonUser, tstamp,
							tstampbefore, month, year);
					request.setAttribute("workapprove", workapprove);
				}

				else if (checkradio.equals("3")) {

					List<Map<String, Object>> workapprove = workHoursDAO.checklistapprovestatusall(logonUser, tstamp,
							tstampbefore, month, year);
					request.setAttribute("workapprove", workapprove);
					List<Map<String, Object>> workapprovetime = workHoursDAO.checklistapprovestatusall1(logonUser,
							tstamp, tstampbefore, month, year);
					request.setAttribute("workapprove", workapprove);
					int hours = 0, min = 0, i = 0;
					for (Map<String, Object> map : workapprovetime) {
						for (Map.Entry<String, Object> entry : map.entrySet()) {
							if (entry.getValue() == null) {
								hours = 0;
								min = 0;
								workapprove.get(i).put("hour", hours);
								workapprove.get(i).put("min", min);
								i++;
							} else {
								int z = (Integer) entry.getValue();
								if (z == 0) {
									hours = 0;
									min = 0;
									workapprove.get(i).put("hour", hours);
									workapprove.get(i).put("min", min);
									i++;
								} else if (z > 0) {
									min = z % 60;
									hours = z / 60;
									if (hours < 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour = ("" + 0 + h);
										String mins = ("" + 0 + m);
										workapprove.get(i).put("hour", hour);
										workapprove.get(i).put("min", mins);
										i++;
									} else if (hours < 10) {
										String h = String.valueOf(hours);
										String hour = ("" + 0 + h);
										workapprove.get(i).put("hour", hour);
										workapprove.get(i).put("min", min);
										i++;
									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										workapprove.get(i).put("hour", hours);
										workapprove.get(i).put("min", mins);
										i++;

									} else {
										workapprove.get(i).put("hour", hours);
										workapprove.get(i).put("min", min);
										i++;

									}
								} else {
									hours = 0;
									min = 0;
									workapprove.get(i).put("hour", hours);
									workapprove.get(i).put("min", min);
									i++;

								}
							}
						}
					}
				} else if (checkradio.equals("2")) {

					List<Map<String, Object>> workapprove = workHoursDAO.checklistapprovecheckout(logonUser, tstamp,
							tstampbefore, month, year);
					request.setAttribute("workapprove", workapprove);
					int hours = 0;
					int min = 0;
					int i = 0;
					for (Map<String, Object> map : checktimeapprove) {
						for (Map.Entry<String, Object> entry : map.entrySet()) {
							if (entry.getValue() == null) {
								hours = 0;
								min = 0;
								workapprove.get(i).put("hour", hours);
								workapprove.get(i).put("min", min);
								i++;
							} else {
								int x = (Integer) entry.getValue();
								if ((x == 0)) {
									hours = 0;
									min = 0;
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									workapprove.get(i).put("hour", hour);
									workapprove.get(i).put("min", mins);
									i++;
								} else if (x > 0) {
									min = x % 60;
									hours = x / 60;
									if (hours < 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour = ("" + 0 + h);
										String mins = ("" + 0 + m);
										workapprove.get(i).put("hour", hour);
										workapprove.get(i).put("min", mins);
										i++;
									} else if (hours < 10) {
										String h = String.valueOf(hours);
										String hour = ("" + 0 + h);
										workapprove.get(i).put("hour", hour);
										workapprove.get(i).put("min", min);
										i++;
									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										workapprove.get(i).put("hour", hours);
										workapprove.get(i).put("min", mins);
										i++;

									} else {
										workapprove.get(i).put("hour", hours);
										workapprove.get(i).put("min", min);
										i++;

									}
								} else {
									hours = 0;
									min = 0;
									workapprove.get(i).put("hour", hours);
									workapprove.get(i).put("min", min);
									i++;

								}
							}
						}
					}
				} else {
				}
			}
			request.setAttribute("checkradio", checkradio);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String calendar() {
		try {

			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			request.setAttribute("logonUser", logonUser);
			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(logonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1 = tstamp;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);
			request.setAttribute("datenowcalendar", datenow);
			String timecalendar = request.getParameter("timecalendar");

			String datecalendar = request.getParameter("datecalendar");
			request.setAttribute("datecalendar", datecalendar);

			String month = null;
			String year = null;

			if (datecalendar == null) {
				month = datenow.substring(3, 5);
				year = datenow.substring(6, 10);
			} else {
				month = datecalendar.substring(0, 2);
				year = datecalendar.substring(6, 10);

			}

			List<Map<String, Object>> work = workHoursDAO.checklistcalendar(logonUser, month, year);

			List<Map<String, Object>> timemonth = workHoursDAO.timemonth(logonUser, month, year);
			int i = 0;
			int checkinhourtoday = 0;
			int checkinmintoday = 0;
			int checkourhourtomorrow = 0;
			int checkourmintomorrow = 0;
			int fullhourtomorrow = 0;
			int fullmintomorrow = 0;
			int fulltomorrow = 0;
			int fulltimehour = 0;
			int fulltimemin = 0;
			for (Map<String, Object> map : timemonth) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String x = datFormat.format(date11);
					String time = x.toString();
					String datecheckin = time.substring(0, 10);
					List<Map<String, Object>> checkincalendars = workHoursDAO.checkincalendars(datecheckin, logonUser,
							month, year);
					String checkout = null;
					String checkout1 = null;
					for (Map<String, Object> map1 : checkincalendars) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							Timestamp x1 = (Timestamp) entry1.getValue();
							checkout = x1.toString().substring(11, 16);
							checkourhourtomorrow = Interger.parseInt(x1.toString().substring(11, 13)); // hourin
							checkourmintomorrow = Interger.parseInt(x1.toString().substring(14, 16)); // minin
							work.get(i).put("mycheckin", checkout);
							work.get(i).put("mycheckins", x1);
						}
					}

					if (checkincalendars.size() == 0) {
						work.get(i).put("mycheckins", x);
					}

					List<Map<String, Object>> works = workHoursDAO.checklistcalendars(datecheckin, logonUser, month,
							year);

					if (works.size() == 0) { // time checkout tomorrow
						List<Map<String, Object>> idcheckincalendars = workHoursDAO.idcheckincalendars(datecheckin,
								logonUser, month, year);

						for (Map<String, Object> map1 : idcheckincalendars) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								BigInteger a = (BigInteger) entry1.getValue();
								String idcheckin = a.toString();
								List<Map<String, Object>> idcheckoutcalendars = workHoursDAO
										.idcheckoutcalendars(idcheckin, logonUser);
								for (Map<String, Object> map2 : idcheckoutcalendars) {
									for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
										BigInteger a2 = (BigInteger) entry2.getValue();
										String idcheckoutsearchtime = a2.toString();
										List<Map<String, Object>> timecheckoutfromid = workHoursDAO
												.timecheckoutfromid(idcheckoutsearchtime);
										for (Map<String, Object> map3 : timecheckoutfromid) {
											for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
												Timestamp x3 = (Timestamp) entry3.getValue();
												String checkout3 = x3.toString().substring(11, 16);
												checkinhourtoday = Interger.parseInt(x3.toString().substring(11, 13)); // hourtoday
												checkinmintoday = Interger.parseInt(x3.toString().substring(14, 16)); // mintoday
												fullhourtomorrow = ((24 - checkourhourtomorrow) + checkinhourtoday)
														* 60;
												fullmintomorrow = (checkinmintoday - checkourmintomorrow);
												fulltomorrow = (fullhourtomorrow + fullmintomorrow);
												fulltimehour = (fulltomorrow / 60) - 1;
												fulltimemin = fulltomorrow % 60;

												if (checkinhourtoday == checkourhourtomorrow
														&& checkinmintoday <= checkourmintomorrow) { // checkin>checkout
													work.get(i).put("checkouttime", checkout3);
												} else if (checkinhourtoday < checkourhourtomorrow) {
													work.get(i).put("checkouttime", checkout3);
												} else {
												}

											}
										}

									}
								}
							}
						} // close time checkout tomorrow

					} else {
						for (Map<String, Object> map1 : works) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								Timestamp x1 = (Timestamp) entry1.getValue();
								checkout1 = x1.toString().substring(11, 16);
								work.get(i).put("checkouttime", checkout1);

							}
						}

					}

					List<Map<String, Object>> works1 = workHoursDAO.checklistcalendarstime(datecheckin, logonUser,
							month, year);
					String fulltimehourx = null, fulltimeminx = null;
					if (works1.size() == 0
							&& (checkinhourtoday == checkourhourtomorrow && checkinmintoday <= checkourmintomorrow)) { // timecheckouttomorrow

						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}

					} else if (checkinhourtoday < checkourhourtomorrow) {
						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("hour", fulltimehourx);
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}
					} else {
					}
					checkinhourtoday = 0;
					checkinmintoday = 0;
					checkourhourtomorrow = 0;
					checkourmintomorrow = 0;
					fullhourtomorrow = 0;
					fullmintomorrow = 0;
					fulltomorrow = 0;
					fulltimehour = 0;
					fulltimemin = 0;
					//
					int hours = 0;
					int min = 0;
					String fulltimehourx1 = null;
					for (Map<String, Object> map1 : works1) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							if (entry1.getValue() == null) { //
								fulltimehourx1 = "";
								work.get(i).put("hour", fulltimehourx1);
								work.get(i).put("min", fulltimehourx1);
							} else {
								int x1 = (Integer) entry1.getValue();
								if ((x1 == 0)) {
									hours = 0;
									min = 0;
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hour);
									work.get(i).put("min", mins);
								} else if (x1 > 0) {
									min = x1 % 60;
									hours = x1 / 60;
									if (hours <= 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										String mins = ("" + 0 + m);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", mins);

									} else if (hours <= 10 && min > 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", min);

									} else if (hours > 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour2 = ("" + 0 + h);
										String min1 = ("" + 0 + m);
										int hour = Interger.parseInt(hour2);
										work.get(i).put("hour", (hour - 1));
										work.get(i).put("min", min1);

									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", mins);
									} else {
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", min);
									}
								} else {
									hours = 0;
									min = 0;
									work.get(i).put("hour", hours);
									work.get(i).put("min", min);
								}
							}

						}
					}
					i++;
				}
			}

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			request.setAttribute("startmonths", startmonths);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			request.setAttribute("work", work);
			request.setAttribute("checktimecalendar", "1");

			String test = (year + "-" + month + "-" + "01");
			request.setAttribute("month", month);
			request.setAttribute("test", test);

			if (timecalendar != null) {
				request.setAttribute("flag12", test);
			}
			List<Holiday> holidayList = holidayDAO.findAll();
			request.setAttribute("holidayList", holidayList);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchcalendar() {
		try {
			String usercalendar = request.getParameter("usercalendar");
			request.setAttribute("usercalendar", usercalendar);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = request.getParameter("usercalendar");
			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(logonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1 = tstamp;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);
			request.setAttribute("datenowcalendar", datenow);
			String timecalendar = request.getParameter("timecalendar");

			String datecalendar = request.getParameter("datecalendar");
			request.setAttribute("datecalendar", datecalendar);

			String month = null;
			String year = null;

			if (datecalendar == null) {
				month = datenow.substring(3, 5);
				year = datenow.substring(6, 10);
			} else {
				month = datecalendar.substring(0, 2);
				year = datecalendar.substring(6, 10);

			}

			List<Map<String, Object>> work = workHoursDAO.checklistcalendar(logonUser, month, year);

			List<Map<String, Object>> timemonth = workHoursDAO.timemonth(logonUser, month, year);

			int i = 0;
			int checkinhourtoday = 0;
			int checkinmintoday = 0;
			int checkourhourtomorrow = 0;
			int checkourmintomorrow = 0;
			int fullhourtomorrow = 0;
			int fullmintomorrow = 0;
			int fulltomorrow = 0;
			int fulltimehour = 0;
			int fulltimemin = 0;
			for (Map<String, Object> map : timemonth) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String x = datFormat.format(date11);
					String time = x.toString();
					String datecheckin = time.substring(0, 10);

					List<Map<String, Object>> checkincalendars = workHoursDAO.checkincalendars(datecheckin, logonUser,
							month, year);

					String checkout = null;
					String checkout1 = null;
					for (Map<String, Object> map1 : checkincalendars) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							Timestamp x1 = (Timestamp) entry1.getValue();
							checkout = x1.toString().substring(11, 16);
							checkourhourtomorrow = Interger.parseInt(x1.toString().substring(11, 13)); // hourin
							checkourmintomorrow = Interger.parseInt(x1.toString().substring(14, 16)); // minin
							work.get(i).put("mycheckin", checkout);
							work.get(i).put("mycheckins", x1);
						}
					}

					if (checkincalendars.size() == 0) {
						work.get(i).put("mycheckins", x);
					}

					List<Map<String, Object>> works = workHoursDAO.checklistcalendars(datecheckin, logonUser, month,
							year);

					if (works.size() == 0) { // time checkout tomorrow
						List<Map<String, Object>> idcheckincalendars = workHoursDAO.idcheckincalendars(datecheckin,
								logonUser, month, year);

						for (Map<String, Object> map1 : idcheckincalendars) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								BigInteger a = (BigInteger) entry1.getValue();
								String idcheckin = a.toString();
								List<Map<String, Object>> idcheckoutcalendars = workHoursDAO
										.idcheckoutcalendars(idcheckin, logonUser);
								for (Map<String, Object> map2 : idcheckoutcalendars) {
									for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
										BigInteger a2 = (BigInteger) entry2.getValue();
										String idcheckoutsearchtime = a2.toString();
										List<Map<String, Object>> timecheckoutfromid = workHoursDAO
												.timecheckoutfromid(idcheckoutsearchtime);
										for (Map<String, Object> map3 : timecheckoutfromid) {
											for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
												Timestamp x3 = (Timestamp) entry3.getValue();
												String checkout3 = x3.toString().substring(11, 16);
												checkinhourtoday = Interger.parseInt(x3.toString().substring(11, 13)); // hourtoday
												checkinmintoday = Interger.parseInt(x3.toString().substring(14, 16)); // mintoday
												fullhourtomorrow = ((24 - checkourhourtomorrow) + checkinhourtoday)
														* 60;
												fullmintomorrow = (checkinmintoday - checkourmintomorrow);
												fulltomorrow = (fullhourtomorrow + fullmintomorrow);
												fulltimehour = (fulltomorrow / 60) - 1;
												fulltimemin = fulltomorrow % 60;

												if (checkinhourtoday == checkourhourtomorrow
														&& checkinmintoday <= checkourmintomorrow) { // checkin>checkout
													work.get(i).put("checkouttime", checkout3);
												} else if (checkinhourtoday < checkourhourtomorrow) {
													work.get(i).put("checkouttime", checkout3);
												} else {
												}

											}
										}

									}
								}
							}
						} // close time checkout tomorrow

					} else {
						for (Map<String, Object> map1 : works) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								Timestamp x1 = (Timestamp) entry1.getValue();
								checkout1 = x1.toString().substring(11, 16);
								work.get(i).put("checkouttime", checkout1);

							}
						}

					}

					List<Map<String, Object>> works1 = workHoursDAO.checklistcalendarstime(datecheckin, logonUser,
							month, year);
					String fulltimehourx = null, fulltimeminx = null;
					if (works1.size() == 0
							&& (checkinhourtoday == checkourhourtomorrow && checkinmintoday <= checkourmintomorrow)) { // timecheckouttomorrow

						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}

					} else if (checkinhourtoday < checkourhourtomorrow) {
						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("hour", fulltimehourx);
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}
					} else {
					}
					checkinhourtoday = 0;
					checkinmintoday = 0;
					checkourhourtomorrow = 0;
					checkourmintomorrow = 0;
					fullhourtomorrow = 0;
					fullmintomorrow = 0;
					fulltomorrow = 0;
					fulltimehour = 0;
					fulltimemin = 0;
					//
					int hours = 0;
					int min = 0;
					String fulltimehourx1 = null;
					for (Map<String, Object> map1 : works1) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							if (entry1.getValue() == null) {
								fulltimehourx1 = "";
								work.get(i).put("hour", fulltimehourx1);
								work.get(i).put("min", fulltimehourx1);
							} else {
								int x1 = (Integer) entry1.getValue();
								if ((x1 == 0)) {
									hours = 0;
									min = 0;
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hour);
									work.get(i).put("min", mins);
								} else if (x1 > 0) {
									min = x1 % 60;
									hours = x1 / 60;
									if (hours <= 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										String mins = ("" + 0 + m);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", mins);

									} else if (hours <= 10 && min > 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", min);

									} else if (hours > 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour2 = ("" + 0 + h);
										String min1 = ("" + 0 + m);
										int hour = Interger.parseInt(hour2);
										work.get(i).put("hour", (hour - 1));
										work.get(i).put("min", min1);

									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", mins);
									} else {
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", min);
									}
								} else {
									hours = 0;
									min = 0;
									work.get(i).put("hour", hours);
									work.get(i).put("min", min);
								}
							}

						}
					}
					i++;
				}
			}

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			request.setAttribute("startmonths", startmonths);

			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute("departmentList", departmentList);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);

			request.setAttribute("work", work);
			request.setAttribute("checktimecalendar", "1");

			String test = (year + "-" + month + "-" + "01");
			request.setAttribute("month", month);
			request.setAttribute("test", test);

			if (timecalendar != null) {
				request.setAttribute("flag12", test);
			}
			List<Holiday> holidayList = holidayDAO.findAll();
			request.setAttribute("holidayList", holidayList);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			request.setAttribute("logonUser", logonUser);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

//	20/03
	public String CalendarAll() {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser = ur.getId(); // check list
			String userId = ur.getId(); // leaves
			request.setAttribute("logonUser", logonUser);
			request.setAttribute("userId", userId);
			String usercalendar = request.getParameter(USERID);
			if (usercalendar != null) {
				userId = usercalendar;
			}
			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1 = tstamp;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);
			request.setAttribute("datenowcalendar", datenow);
			String timecalendar = request.getParameter("timecalendar");
			String datecalendar = request.getParameter("datecalendar");
			request.setAttribute("datecalendar", datecalendar);

			String month = null;
			String year = null;
			if (datecalendar == null) {
				month = datenow.substring(3, 5);
				year = datenow.substring(6, 10);
			} else {
				month = datecalendar.substring(0, 2);
				year = datecalendar.substring(6, 10);
			}

			List<Map<String, Object>> work = workHoursDAO.checklistcalendar(logonUser, month, year);
			List<Map<String, Object>> timemonth = workHoursDAO.timemonth(logonUser, month, year);

			int i = 0;
			int checkinhourtoday = 0;
			int checkinmintoday = 0;
			int checkourhourtomorrow = 0;
			int checkourmintomorrow = 0;
			int fullhourtomorrow = 0;
			int fullmintomorrow = 0;
			int fulltomorrow = 0;
			int fulltimehour = 0;
			int fulltimemin = 0;

			for (Map<String, Object> map : timemonth) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String x = datFormat.format(date11);
					String time = x.toString();
					String datecheckin = time.substring(0, 10);
					request.setAttribute("datecheckin", datecheckin);

					// check status check in
					User user = userDAO.findById(logonUser);
					String stime = user.getWorkTimeStart();
					request.setAttribute("stime", stime);

					List<Map<String, Object>> checkincalendars = workHoursDAO.checkincalendars(datecheckin, logonUser,
							month, year);
					String checkout = null;
					String checkout1 = null;
					for (Map<String, Object> map1 : checkincalendars) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							Timestamp x1 = (Timestamp) entry1.getValue();
							checkout = x1.toString().substring(0, 16);
							checkourhourtomorrow = Interger.parseInt(x1.toString().substring(11, 13)); // hourin
							checkourmintomorrow = Interger.parseInt(x1.toString().substring(14, 16)); // minin
							work.get(i).put("mycheckin", checkout);
							work.get(i).put("mycheckins", x1);
						}
					}
					if (checkincalendars.size() == 0) {
						work.get(i).put("mycheckins", x);
					}
					List<Map<String, Object>> works = workHoursDAO.checklistcalendars(datecheckin, logonUser, month,
							year);
					if (works.size() == 0) { // time checkout tomorrow
						List<Map<String, Object>> idcheckincalendars = workHoursDAO.idcheckincalendars(datecheckin,
								logonUser, month, year);
						for (Map<String, Object> map1 : idcheckincalendars) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								BigInteger a = (BigInteger) entry1.getValue();
								String idcheckin = a.toString();

								List<Map<String, Object>> idcheckoutcalendars = workHoursDAO
										.idcheckoutcalendars(idcheckin, logonUser);
								for (Map<String, Object> map2 : idcheckoutcalendars) {
									for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
										BigInteger a2 = (BigInteger) entry2.getValue();
										String idcheckoutsearchtime = a2.toString();
										List<Map<String, Object>> timecheckoutfromid = workHoursDAO
												.timecheckoutfromid(idcheckoutsearchtime);
										for (Map<String, Object> map3 : timecheckoutfromid) {
											for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
												Timestamp x3 = (Timestamp) entry3.getValue();
												String checkout3 = x3.toString().substring(11, 16);
												checkinhourtoday = Interger.parseInt(x3.toString().substring(11, 13)); // hourtoday
												checkinmintoday = Interger.parseInt(x3.toString().substring(14, 16)); // mintoday
												fullhourtomorrow = ((24 - checkourhourtomorrow) + checkinhourtoday)
														* 60;
												fullmintomorrow = (checkinmintoday - checkourmintomorrow);
												fulltomorrow = (fullhourtomorrow + fullmintomorrow);
												fulltimehour = (fulltomorrow / 60) - 1;
												fulltimemin = fulltomorrow % 60;

												if (checkinhourtoday == checkourhourtomorrow
														&& checkinmintoday <= checkourmintomorrow) { // checkin>checkout
													work.get(i).put("checkouttime", checkout3);
												} else if (checkinhourtoday < checkourhourtomorrow) {
													work.get(i).put("checkouttime", checkout3);
												} else {
												}
											}
										}
									}
								}
							}
						} // close time checkout tomorrow
					} else {
						for (Map<String, Object> map1 : works) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								Timestamp x1 = (Timestamp) entry1.getValue();
								checkout1 = x1.toString().substring(11, 16);
								work.get(i).put("checkouttime", checkout1);
							}
						}
					}
					List<Map<String, Object>> works1 = workHoursDAO.checklistcalendarstime(datecheckin, logonUser,
							month, year);
					String fulltimehourx = null, fulltimeminx = null;
					if (works1.size() == 0
							&& (checkinhourtoday == checkourhourtomorrow && checkinmintoday <= checkourmintomorrow)) { // timecheckouttomorrow
						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}
					} else if (checkinhourtoday < checkourhourtomorrow) {

						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("hour", fulltimehourx);
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}
					} else {
					}

					checkinhourtoday = 0;
					checkinmintoday = 0;
					checkourhourtomorrow = 0;
					checkourmintomorrow = 0;
					fullhourtomorrow = 0;
					fullmintomorrow = 0;
					fulltomorrow = 0;
					fulltimehour = 0;
					fulltimemin = 0;
					int hours = 0;
					int min = 0;
					String fulltimehourx1 = null;
					for (Map<String, Object> map1 : works1) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							if (entry1.getValue() == null) { //
								fulltimehourx1 = "";
								work.get(i).put("hour", fulltimehourx1);
								work.get(i).put("min", fulltimehourx1);
							} else {
								int x1 = (Integer) entry1.getValue();
								if ((x1 == 0)) {
									hours = 0;
									min = 0;
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hour);
									work.get(i).put("min", mins);
								} else if (x1 > 0) {
									min = x1 % 60;
									hours = x1 / 60;
									if (hours <= 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										String mins = ("" + 0 + m);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", mins);

									} else if (hours <= 10 && min > 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", min);

									} else if (hours > 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour2 = ("" + 0 + h);
										String min1 = ("" + 0 + m);
										int hour = Interger.parseInt(hour2);
										work.get(i).put("hour", (hour - 1));
										work.get(i).put("min", min1);

									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", mins);
									} else {
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", min);
									}
								} else {
									hours = 0;
									min = 0;
									work.get(i).put("hour", hours);
									work.get(i).put("min", min);
								}
							}
						}
					}
					i++;
				}
			}

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			request.setAttribute("startmonths", startmonths);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);
			request.setAttribute("work", work);
			request.setAttribute("checktimecalendar", "1");

			String test = (year + "-" + month + "-" + "01");
			request.setAttribute("month", month);
			request.setAttribute("test", test);
			if (timecalendar != null) {
				request.setAttribute("flag12", test);
			}

			List<Holiday> holidayList = holidayDAO.findAll();
			request.setAttribute("holidayList", holidayList);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			// leaves
			List<LeaveType> leavetypeList = leavetypeDAO.findAll_calendar();
			request.setAttribute("leavetypeList", leavetypeList);

			List<LeaveType> type_leave = leavetypeDAO.findAll_calendar();
			for (int j = 0; j < type_leave.size(); j++) {
				LeaveType leave = type_leave.get(j);
				request.setAttribute("type_" + leave.getLeaveTypeId(), leave.getLeaveTypeName());
			}

			DateTimeFormatter dateT = DateTimeFormatter.ofPattern("01-01-yyyy");
			LocalDate localDate = LocalDate.now();
			String s = "00:00:00.0";

			Timestamp start_date = DateUtil.dateToTimestamp(dateT.format(localDate), s);
			Timestamp end_date = DateUtil.changetoEndYear(dateT.format(localDate));
			List leavelist = leaveDAO.myLeavesList(userId, start_date, end_date);
			Double leave_1 = 0.000, leave_2 = 0.000, leave_3 = 0.000, leave_5 = 0.000, leave_6 = 0.000;

			if (leavelist != null) {
				request.setAttribute("leave", leavelist);
				for (Iterator iterator = leavelist.iterator(); iterator.hasNext();) {
					Leaves leave = (Leaves) iterator.next();
					Double noday = leave.getNoDay().doubleValue();
					if (leave.getLeaveTypeId().contains("1")) {
						leave_1 += noday;
					}
					if (leave.getLeaveTypeId().contains("2")) {
						leave_2 += noday;
					}
					if (leave.getLeaveTypeId().contains("3")) {
						leave_3 += noday;
					}
					if (leave.getLeaveTypeId().contains("5")) {
						leave_5 += noday;
					}
					if (leave.getLeaveTypeId().contains("6")) {
						leave_6 += noday;
					}
				}
			}
			request.setAttribute("leave_1", leave_1);
			request.setAttribute("leave_2", leave_2);
			request.setAttribute("leave_3", leave_3);
			request.setAttribute("leave_5", leave_5);
			request.setAttribute("leave_6", leave_6);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String searchAlll_calendar() {
		try {
			// leaves
			request.setAttribute(USERID, request.getParameter("usercalendar"));

			// check list
			String usercalendar = request.getParameter("usercalendar");
			request.setAttribute("usercalendar", usercalendar);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = request.getParameter("usercalendar");

			String positionusers = null;
			List<Map<String, Object>> positionuser = userDAO.positionuser(logonUser);
			for (Map<String, Object> map : positionuser) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					positionusers = (String) entry.getValue();
				}
			}
			request.setAttribute("position", positionusers);

			Date date = new Date();
			Timestamp tstamp = new Timestamp(date.getTime());
			Date date1 = tstamp;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String datenow = dateFormat.format(date1);
			request.setAttribute("datenow", datenow);
			request.setAttribute("datenowcalendar", datenow);
			String timecalendar = request.getParameter("timecalendar");

			String datecalendar = request.getParameter("datecalendar");
			request.setAttribute("datecalendar", datecalendar);

			String userId = null;
			if (usercalendar != null) {
				userId = usercalendar;
			}
			String month = null;
			String year = null;

			if (datecalendar == null) {
				month = datenow.substring(3, 5);
				year = datenow.substring(6, 10);
			} else {
				month = datecalendar.substring(0, 2);
				year = datecalendar.substring(6, 10);
			}

			List<Map<String, Object>> work = workHoursDAO.checklistcalendar(logonUser, month, year);
			List<Map<String, Object>> timemonth = workHoursDAO.timemonth(logonUser, month, year);

			int i = 0;
			int checkinhourtoday = 0;
			int checkinmintoday = 0;
			int checkourhourtomorrow = 0;
			int checkourmintomorrow = 0;
			int fullhourtomorrow = 0;
			int fullmintomorrow = 0;
			int fulltomorrow = 0;
			int fulltimehour = 0;
			int fulltimemin = 0;
			for (Map<String, Object> map : timemonth) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String x = datFormat.format(date11);
					String time = x.toString();
					String datecheckin = time.substring(0, 10);

					// check status check in
					User user = userDAO.findById(logonUser);
					String stime = user.getWorkTimeStart();
					request.setAttribute("stime", stime);

					List<Map<String, Object>> checkincalendars = workHoursDAO.checkincalendars(datecheckin, logonUser,
							month, year);
					String checkout = null;
					String checkout1 = null;
					for (Map<String, Object> map1 : checkincalendars) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							Timestamp x1 = (Timestamp) entry1.getValue();
							checkout = x1.toString().substring(0, 16);
							checkourhourtomorrow = Interger.parseInt(x1.toString().substring(11, 13)); // hourin
							checkourmintomorrow = Interger.parseInt(x1.toString().substring(14, 16)); // minin
							work.get(i).put("mycheckin", checkout);
							work.get(i).put("mycheckins", x1);
						}
					}

					if (checkincalendars.size() == 0) {
						work.get(i).put("mycheckins", x);
					}

					List<Map<String, Object>> works = workHoursDAO.checklistcalendars(datecheckin, logonUser, month,
							year);
					if (works.size() == 0) { // time checkout tomorrow
						List<Map<String, Object>> idcheckincalendars = workHoursDAO.idcheckincalendars(datecheckin,
								logonUser, month, year);
						for (Map<String, Object> map1 : idcheckincalendars) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								BigInteger a = (BigInteger) entry1.getValue();
								String idcheckin = a.toString();
								List<Map<String, Object>> idcheckoutcalendars = workHoursDAO
										.idcheckoutcalendars(idcheckin, logonUser);
								for (Map<String, Object> map2 : idcheckoutcalendars) {
									for (Map.Entry<String, Object> entry2 : map2.entrySet()) {
										BigInteger a2 = (BigInteger) entry2.getValue();
										String idcheckoutsearchtime = a2.toString();
										List<Map<String, Object>> timecheckoutfromid = workHoursDAO
												.timecheckoutfromid(idcheckoutsearchtime);
										for (Map<String, Object> map3 : timecheckoutfromid) {
											for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
												Timestamp x3 = (Timestamp) entry3.getValue();
												String checkout3 = x3.toString().substring(11, 16);
												checkinhourtoday = Interger.parseInt(x3.toString().substring(11, 13)); // hourtoday
												checkinmintoday = Interger.parseInt(x3.toString().substring(14, 16)); // mintoday
												fullhourtomorrow = ((24 - checkourhourtomorrow) + checkinhourtoday)
														* 60;
												fullmintomorrow = (checkinmintoday - checkourmintomorrow);
												fulltomorrow = (fullhourtomorrow + fullmintomorrow);
												fulltimehour = (fulltomorrow / 60) - 1;
												fulltimemin = fulltomorrow % 60;

												if (checkinhourtoday == checkourhourtomorrow
														&& checkinmintoday <= checkourmintomorrow) { // checkin>checkout
													work.get(i).put("checkouttime", checkout3);

												} else if (checkinhourtoday < checkourhourtomorrow) {
													work.get(i).put("checkouttime", checkout3);
												} else {
												}
											}
										}
									}
								}
							}
						} // close time checkout tomorrow

					} else {
						for (Map<String, Object> map1 : works) {
							for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
								Timestamp x1 = (Timestamp) entry1.getValue();
								checkout1 = x1.toString().substring(11, 16);
								work.get(i).put("checkouttime", checkout1);
							}
						}
					}

					List<Map<String, Object>> works1 = workHoursDAO.checklistcalendarstime(datecheckin, logonUser,
							month, year);
					String fulltimehourx = null, fulltimeminx = null;
					if (works1.size() == 0
							&& (checkinhourtoday == checkourhourtomorrow && checkinmintoday <= checkourmintomorrow)) { // timecheckouttomorrow

						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}

					} else if (checkinhourtoday < checkourhourtomorrow) {
						if (fulltimehour == 0 && fulltimemin == 0) {
							fulltimehourx = "";
							fulltimeminx = "";
							work.get(i).put("hour", fulltimehourx);
							work.get(i).put("min", fulltimeminx);
						} else {
							work.get(i).put("hour", fulltimehour);
							work.get(i).put("min", fulltimemin);
						}
					} else {
					}
					checkinhourtoday = 0;
					checkinmintoday = 0;
					checkourhourtomorrow = 0;
					checkourmintomorrow = 0;
					fullhourtomorrow = 0;
					fullmintomorrow = 0;
					fulltomorrow = 0;
					fulltimehour = 0;
					fulltimemin = 0;
					//
					int hours = 0;
					int min = 0;
					String fulltimehourx1 = null;
					for (Map<String, Object> map1 : works1) {
						for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
							if (entry1.getValue() == null) {
								fulltimehourx1 = "";
								work.get(i).put("hour", fulltimehourx1);
								work.get(i).put("min", fulltimehourx1);
							} else {
								int x1 = (Integer) entry1.getValue();
								if ((x1 == 0)) {
									hours = 0;
									min = 0;
									String h = String.valueOf(hours);
									String m = String.valueOf(min);
									String hour = ("" + 0 + h);
									String mins = ("" + 0 + m);
									work.get(i).put("hour", hour);
									work.get(i).put("min", mins);
								} else if (x1 > 0) {
									min = x1 % 60;
									hours = x1 / 60;
									if (hours <= 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										String mins = ("" + 0 + m);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", mins);

									} else if (hours <= 10 && min > 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hours1 = ("" + 0 + h);
										int hour = Interger.parseInt(hours1);
										if (hour == 0) {
											work.get(i).put("hour", (hour));
										} else {
											work.get(i).put("hour", (hour - 1));
										}
										work.get(i).put("min", min);

									} else if (hours > 10 && min < 10) {
										String h = String.valueOf(hours);
										String m = String.valueOf(min);
										String hour2 = ("" + 0 + h);
										String min1 = ("" + 0 + m);
										int hour = Interger.parseInt(hour2);
										work.get(i).put("hour", (hour - 1));
										work.get(i).put("min", min1);

									} else if (min < 10) {
										String m = String.valueOf(min);
										String mins = ("" + 0 + m);
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", mins);
									} else {
										work.get(i).put("hour", (hours - 1));
										work.get(i).put("min", min);
									}
								} else {
									hours = 0;
									min = 0;
									work.get(i).put("hour", hours);
									work.get(i).put("min", min);
								}
							}
						}
					}
					i++;
				}
			}

			List<Map<String, Object>> startmonth = workHoursDAO.startmonth(logonUser, month, year);
			String startmonths = null;
			for (Map<String, Object> maps : startmonth) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					Date date11 = (Date) entry.getValue();
					DateFormat datFormat = new SimpleDateFormat("01-MM-yyyy");
					startmonths = datFormat.format(date11);
				}
			}
			request.setAttribute("startmonths", startmonths);

			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);
			request.setAttribute("work", work);
			request.setAttribute("checktimecalendar", "1");

			String test = (year + "-" + month + "-" + "01");
			request.setAttribute("month", month);
			request.setAttribute("test", test);
			if (timecalendar != null) {
				request.setAttribute("flag12", test);
			}

			List<Holiday> holidayList = holidayDAO.findAll();
			request.setAttribute("holidayList", holidayList);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			request.setAttribute("logonUser", logonUser);

			// leaves
			List<LeaveType> leavetypeList = leavetypeDAO.findAll_calendar();
			request.setAttribute("leavetypeList", leavetypeList);

			List<LeaveType> type_leave = leavetypeDAO.findAll_calendar();
			for (int j = 0; j < type_leave.size(); j++) {
				LeaveType leave = type_leave.get(j);
				request.setAttribute("type_" + leave.getLeaveTypeId(), leave.getLeaveTypeName());
			}

			DateTimeFormatter dateT = DateTimeFormatter.ofPattern("01-01-yyyy");
			LocalDate localDate = LocalDate.now();
			String s = "00:00:00.0";

			Timestamp start_date = DateUtil.dateToTimestamp(dateT.format(localDate), s);
			Timestamp end_date = DateUtil.changetoEndYear(dateT.format(localDate));
			List leavelist = leaveDAO.myLeavesList(userId, start_date, end_date);
			Double leave_1 = 0.000, leave_2 = 0.000, leave_3 = 0.000, leave_5 = 0.000, leave_6 = 0.000;

			if (leavelist != null) {
				request.setAttribute("leave", leavelist);
				for (Iterator iterator = leavelist.iterator(); iterator.hasNext();) {
					Leaves leave = (Leaves) iterator.next();
					Double noday = leave.getNoDay().doubleValue();
					if (leave.getLeaveTypeId().contains("1")) {
						leave_1 += noday;
					}
					if (leave.getLeaveTypeId().contains("2")) {
						leave_2 += noday;
					}
					if (leave.getLeaveTypeId().contains("3")) {
						leave_3 += noday;
					}
					if (leave.getLeaveTypeId().contains("5")) {
						leave_5 += noday;
					}
					if (leave.getLeaveTypeId().contains("6")) {
						leave_6 += noday;
					}
				}
			}
			request.setAttribute("leave_1", leave_1);
			request.setAttribute("leave_2", leave_2);
			request.setAttribute("leave_3", leave_3);
			request.setAttribute("leave_5", leave_5);
			request.setAttribute("leave_6", leave_6);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String checkcaractor() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String calendarday() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String map_list() {

		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String selectedUser = request.getParameter("user.roletId");
			if (selectedUser == null) {
				selectedUser = "All";
			}
			String logonUser = ur.getId();

			String department = request.getParameter("user.departmentId");
			request.setAttribute("departments", department);
			Timestamp mystartStamp;
			Timestamp myEndStamp;
			String startdate = "";
			String enddate = "";

			if (request.getParameter("Date-Start") == null && request.getParameter("Date-End") == null) {
				Date date = new Date();
				Date datebefore = DateUtil.periodMinus(date, 8);
				mystartStamp = new Timestamp(datebefore.getTime());
				myEndStamp = new Timestamp(date.getTime());
			} else {
				mystartStamp = DateUtil.myDateStart(request.getParameter("Date-Start"));
				myEndStamp = DateUtil.myDateEnd(request.getParameter("Date-End"));
			}

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			startdate = dateFormat.format(mystartStamp);
			enddate = dateFormat.format(myEndStamp);
			List<Map<String, Object>> cubeUser = userDAO.sequense();
			request.setAttribute("cubeUser", cubeUser);
			request.setAttribute("logonUser", logonUser);
			request.setAttribute("selectedUser", selectedUser);
			request.setAttribute("flag_search", "1");
			request.setAttribute("startdate", startdate);
			request.setAttribute("enddate", enddate);

			if (selectedUser.equals("All")) {
				List<Map<String, Object>> workall = workHoursDAO.checkInMapAll(mystartStamp, myEndStamp);
				request.setAttribute("CheckIn", workall);
			} else {
				List<Map<String, Object>> work = workHoursDAO.checkInMap(selectedUser, mystartStamp, myEndStamp);
				request.setAttribute("CheckIn", work);
			}
			
			//request.setAttribute("GOOGLE_API_KEY", constant.getGoogleApiKey());
			//PRODDUCTION GOOGLE KEY
			request.setAttribute("GOOGLE_API_KEY", "AIzaSyDQajNmqwu5dl6tJ3nnOoYhnRl2ndchKAg");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String ontimeChart() {
		try {
			Timestamp today = DateUtil.getCurrentTime();
			String now = today.toString(); 
			String[] date_now = now.split("-");
			String month = Integer.toString(Integer.parseInt(date_now[1])-1);
			String year = date_now[0];
			String limit = "10";
			
			List<Map<String, Object>> status = workHoursDAO.checkStatusCheckIn(month, year, limit);
			
			request.setAttribute("status", status);
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("limit", limit);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public String lateChart() {
		try {
			Timestamp today = DateUtil.getCurrentTime();
			String now = today.toString(); 
			String[] date_now = now.split("-");
			String month = Integer.toString(Integer.parseInt(date_now[1])-1);
			String year = date_now[0];
			String limit = "10";
			
			List<Map<String, Object>> status = workHoursDAO.checkStatusLate(month, year, limit);
			
			request.setAttribute("status", status);
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("limit", limit);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	
	//QRCODE GENERATE


	public String QRCodeGenerate() throws Exception {
		try {
			String qrIdCheck;
			int qrIdStatus =1;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyHH-mm-ss");
			Date timenow = new Date(); 
			String timeuse = formatter.format(timenow);
			int genNewQr = 0;
			
		
			
			//If can't get qrIdStatus form context == generate QR first time
			ServletContext context = request.getServletContext();
			try {
				// If qrIdStatus == qrIdStatus2 mean QRCode was used
				int qrIdStatus2 = (Integer) context.getAttribute("qrIdStatus");
				if (qrIdStatus == qrIdStatus2) {
					 qrIdCheck = (String) context.getAttribute("qrIdCheck");
						context.setAttribute("qrIdStatus", 0);	
					 qrIdStatus = (Integer) context.getAttribute("qrIdStatus");
					 genNewQr =1;
				} else { 
					 qrIdCheck = (String) context.getAttribute("qrIdCheck");
					 genNewQr =2;
				}

			} catch (Exception e) {
				context.setAttribute("qrIdStatus", 0);	
				qrIdStatus = (Integer) context.getAttribute("qrIdStatus");
				
				//Hash time to MD5 then keep in ServletContext
				String md5Password = loginService.generateMD5(timeuse);
				
				context.setAttribute("qrIdCheck", md5Password);	
				 qrIdCheck = (String) context.getAttribute("qrIdCheck");
				 genNewQr = 1;
			}
			
			log.info("qrIdCheck: "+qrIdCheck);
			

			// if genNewQr == 1 gen again   -  if == 2 dont do anything
			if(genNewQr == 1) {
				
				//Chenge ip to main web path
			String qrCodeText = "http://192.168.0.162:8080/QRCode_form?qrID="+qrIdCheck;
			
			String fileServerPath = context.getRealPath("/");
			String filePath = fileServerPath + "upload/QRCode.png";
			File file = new File(fileServerPath+"upload/QRCode.png");
			if(file.delete()){
    			log.info(file.getName() + " is deleted!");
    		}else{
    			log.info("Delete operation is failed.");
    		}
			int size = 125;
			String fileType = "png";
			File qrFile = new File(filePath);
			
			createQRImage(qrFile, qrCodeText, size, fileType);
			log.info(filePath);
			log.info("DONE");
			}
			request.setAttribute("timenow", timeuse);
			request.setAttribute("qrIdCheck", qrIdCheck);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}


	public String QRCodeForm() throws Exception {
		try {
			
			//check status of qrcode
			String qrID = request.getParameter("qrID");
			request.setAttribute("qrID", qrID);
			
//			int qrcodeID = Integer.parseInt(qrID);
			String success = "success";
			String phoneNum = null;
			String warningtext = null;
			int reScan = 0;
			ServletContext context = request.getServletContext();
			String qrIdCheck = (String) context.getAttribute("qrIdCheck");
			int qrIdStatus = (Integer) context.getAttribute("qrIdStatus");
			String md5Password = loginService.generateMD5(qrIdCheck);
			
			//check qrId from path  == qrId from ServletContext
			if(qrIdStatus == 0 && qrID.equals(qrIdCheck)) {

			Date timenow = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String QRtimeCheckDate = formatter.format(timenow);
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
			String time = formatter2.format(timenow);
			SimpleDateFormat formatter3 = new SimpleDateFormat("HH");
			int timeType = Integer.parseInt(formatter3.format(timenow));
			
			request.setAttribute("timenow", time);
			request.setAttribute("datenow", QRtimeCheckDate);

			// check cookie and check type&time
			Cookie[] thecookie = request.getCookies();
			User ur = new User();
			String logonUser = null;
			if (thecookie != null) {
				for (Cookie tmp : thecookie) {
					if (tmp.getName().equals("phonejaphone")) {
						String phone = tmp.getValue();
						phoneNum = phone;
						ur = userDAO.findByPhoneNum(phone);
						logonUser = ur.getId();
						List<Map<String, Object>> checkwork = workHoursDAO.check_work(logonUser, "1");
						List<Map<String, Object>> checkwork2 = workHoursDAO.check_work(logonUser, "2");
						if (checkwork.size() >= 1 && timeType < 12) {
							log.info("checkwork >= 1 && time < 12");
							reScan = 1;
						} else if(checkwork2.size() >= 1) {
							log.info("checkout 2 times");
							reScan = 2;
						} else {
							QRcheckin();
							success = "success2";

						}
					}
				}
			}
			} else {
				reScan = 3;
			}
			log.info(success);
			request.setAttribute("warningtext",warningtext);
			request.setAttribute("phoneNum", phoneNum);
			request.setAttribute("reScan",reScan);
		
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String QRcheckin() {
		try {
			// check status of qrcode
			String qrID = request.getParameter("qrID");
			String qrID2 = "0";
			if (qrID != null) {
				qrID2 = qrID;
			} else {
				qrID2 = qrcodeID;
			}
			String success = "success";
			
			ServletContext context = request.getServletContext();
			String qrIdCheck = (String) context.getAttribute("qrIdCheck");
			log.info("qrIdCheck="+qrIdCheck);
			int qrIdStatus = (Integer) context.getAttribute("qrIdStatus");
			
			//recheck qrId from path  == qrId from ServletContext
			if(qrIdStatus == 0 && qrID2.equals(qrIdCheck)) {
				// update and create new qr code
				QRupdate();

				Timestamp fulldatetime = DateUtil.getCurrentTime();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = formatter.format(fulldatetime);
				SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
				String mytime = formatter2.format(fulldatetime);
				SimpleDateFormat formatter3 = new SimpleDateFormat("HH");
				int timeType = Integer.parseInt(formatter3.format(fulldatetime));

				User ur = new User();
				String phoneNum = "1";
				log.info("number ="+number);
				
				// If checkin by cookie >> number = number from cookie
				if(number != null){
					ur = userDAO.findByPhoneNum(number);
					phoneNum = number;}
				else {
				Cookie[] thecookie = request.getCookies();
				if (thecookie != null) {
					for (Cookie tmp : thecookie) {
						if (tmp.getName().equals("phonejaphone")) {
							String phone = tmp.getValue();
							ur = userDAO.findByPhoneNum(phone);
							phoneNum = phone;
						}
					}
				}
				}
			String logonUser = ur.getId();
			String date = null;
			String month = null;
			String year = null;
			date = mydate.substring(0, 2);
			month = mydate.substring(3, 5);
			year = mydate.substring(6, 10);

			List<Map<String, Object>> checktimehours = workHoursDAO.checktimehours(logonUser, date, month, year);
			List<Map<String, Object>> checktimemin = workHoursDAO.checktimemin(logonUser, date, month, year);
		
			// Check in if never check in this day -  Check out if check in already  - warn when double check in
			int types = 0;
			String worktypes = worktype;
			if(worktypes == null) {
			List<Map<String, Object>> checkwork = workHoursDAO.check_work(logonUser,"1");
			if(checkwork.size() == 0) {
				log.info("checkwork.size() == 0");
				types = 1;
			} else if(checkwork.size() <2){
				log.info("checkwork.size() <2");
				types = 2; 
			} else {
				log.info("outofelse");
				types = 2; 
			}
			} else {
				types = Integer.parseInt(worktypes);
			}
			int inhour = 0, fullhour = 0, fullmin = 0, inmin = 0, fulltime = 0, outhours = 0, outmins = 0;
			String timeouthour = null, timeoutmin = null;
			String timeouthours = mytime.substring(1, 2);
			if (timeouthours.equals(":")) {
				timeouthour = mytime.substring(0, 1);
				timeoutmin = mytime.substring(2, 4);
			} else {
				timeouthour = mytime.substring(0, 2);
				timeoutmin = mytime.substring(3, 5);
			}
			outhours = Integer.valueOf(timeouthour);
			outmins = Integer.valueOf(timeoutmin);

			for (Map<String, Object> map : checktimehours) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					BigInteger x = (BigInteger) entry.getValue();
					inhour = x.intValue();

				}
			}

			for (Map<String, Object> maps : checktimemin) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					BigInteger a = (BigInteger) entry.getValue();
					inmin = a.intValue();
					fullmin = (outmins - inmin);
				}
			}
			if (checktimehours.size() == 0) {
				String time = null;
				List<Map<String, Object>> usertype1 = workHoursDAO.usertype1(logonUser);
				for (Map<String, Object> maps : usertype1) {
					for (Map.Entry<String, Object> entry : maps.entrySet()) {
						log.debug(entry.getValue());
						Date date1 = new Date();
						date1 = (Date) entry.getValue();
						DateFormat dateFormat = new SimpleDateFormat("HH:mm");
						time = dateFormat.format(date1);
						inhour = Interger.valueOf(time.substring(0, 2));
						inmin = Interger.valueOf(time.substring(3, 5));
						fullmin = (outmins - inmin);
					}

				}

			}

			if (types == 2) {

				if (outhours < inhour) {
					fullhour = ((24 - inhour) + outhours) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;

				} else if (outhours == inhour && inmin > outmins) {
					fullhour = ((24 - inhour) + outhours) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;
				} else {
					fullhour = (outhours - inhour) * 60;
					fullmin = (outmins - inmin);
					fulltime = fullhour + fullmin;
				}

			} else if (types == 1) {
				fulltime = 0;
			}
			String type =  String.valueOf(types);
			Integer w = workHoursDAO.getMaxId();
			Integer workId = w +1 ;
			String des = request.getParameter("description");
			Date date2 = new Date();
			Timestamp tstamp = new Timestamp(date2.getTime());
			List<Map<String, Object>> check_in = workHoursDAO.check_in(logonUser);
			int datecheck_in = 0;
			int datecheck_out = Integer.valueOf(date);
			int checkinnotcheckout = ((inhour * 60) + inmin);
			int checkoutnotcheckout = ((outhours * 60) + outmins);
			for (Map<String, Object> maps : check_in) {
				for (Map.Entry<String, Object> entry : maps.entrySet()) {
					BigInteger x = (BigInteger) entry.getValue();
					datecheck_in = Integer.valueOf(x.toString());
				}
			}
			if (datecheck_in != datecheck_out && checkoutnotcheckout >= checkinnotcheckout) {
				fulltime = 0;

			}
			if (checktimehours.size() == 0) {
				fulltime = 0;
			}
		
			//save record
			WorkHours wh = new WorkHours();
			wh.setWorkHoursTimeWork(fulldatetime);
			wh.setWorkHoursType(type);
			wh.setWorkinghours(fulltime);
			wh.setUserCreate(logonUser);
			wh.setTimeCreate(tstamp);
			wh.setDescription(des);
			wh.setWorkHoursId(w + 1);

			Map<String, String> headersInfo = getHeadersInfo(request);
			String userAgent = (String) headersInfo.get("user-agent");
			wh.setUserAgent(userAgent);

			String ipAddress = (String) headersInfo.get("ipAddress");
			wh.setIpAddress(ipAddress);

			workHoursDAO.save(wh);
			
			QRchecklist(phoneNum,workId);
			request.setAttribute("logonUser", logonUser);
			request.setAttribute("phoneNum", phoneNum);

			} else {
				success = "success2";
				request.setAttribute("qrID", qrID2);
			}
			log.info(success);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	} 
	
	public String QRchecklist(String num , int workID) {
		try {
			
			// Send data for checked page
			String workId = String.valueOf(workID);
			int workId2 = workID;
			WorkHours w = new WorkHours();
			
			
			//List<WorkHours> ur = new List<WorkHours>();
			w = workHoursDAO.findByWorkHoursId(workId2);
			
			String logonUser = w.getUserCreate();
			List<Map<String, Object>> work = workHoursDAO.QRchecklist(workId);
			
			User user = userDAO.findById(logonUser);
			String stime = user.getWorkTimeStart() + ":00";

			JSONArray data = new JSONArray(work);
			List<Map<String, Object>> checktime = workHoursDAO.QRchecktime(workId);
			
			request.setAttribute("data", data);
			request.setAttribute("checktime", checktime);
			request.setAttribute("stime", stime);
			request.setAttribute("work", work);
			request.setAttribute("logonUser", logonUser);
			
			// Calculation 
			int hours = 0;
			int min = 0;
			int i = 0;
			
			for (Map<String, Object> map : checktime) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() == null) {
						hours = 0;
						min = 0;
						work.get(i).put("hour", hours);
						work.get(i).put("min", min);
						i++;
					} else {
						int x = (Integer) entry.getValue();
						if ((x == 0)) {
							hours = 0;
							min = 0;
							String h = String.valueOf(hours);
							String m = String.valueOf(min);
							String hour = ("" + 0 + h);
							String mins = ("" + 0 + m);
							work.get(i).put("hour", hour);
							work.get(i).put("min", mins);
							i++;
						} else if (x > 0) {
							min = x % 60;
							hours = x / 60;
							if (hours < 10 && min < 10) {
								String h = String.valueOf(hours);
								String m = String.valueOf(min);
								String hour = ("" + 0 + h);
								String mins = ("" + 0 + m);
								work.get(i).put("hour", hour);
								work.get(i).put("min", mins);
								i++;
							} else if (hours < 10) {
								String h = String.valueOf(hours);
								String hour = ("" + 0 + h);
								work.get(i).put("hour", hour);
								work.get(i).put("min", min);
								i++;
							} else if (min < 10) {
								String m = String.valueOf(min);
								String mins = ("" + 0 + m);
								work.get(i).put("hour", hours);
								work.get(i).put("min", mins);
								i++;

							} else {
								work.get(i).put("hour", hours);
								work.get(i).put("min", min);
								i++;

							}
						} else {
							hours = 0;
							min = 0;
							work.get(i).put("hour", hours);
							work.get(i).put("min", min);
							i++;

						}
					}

				}
			}
			
			// Save Cookies 
			String phone = num;
			Cookie cookie=new Cookie("phonejaphone",num);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String QRupdate() throws Exception {
		try {
			
//			response.setHeader("Refresh", "0; URL=http://localhost:8080/QRCode_Generate");
			//Servlet

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyyHH-mm-ss");
			Date timenow = new Date(); 
			System.out.println(formatter.format(timenow));
			String timeuse = formatter.format(timenow);
			
			// Hash Time to MD5 Then keep in ServletContext
			String md5Password = loginService.generateMD5(timeuse);
			log.info("md5="+md5Password);
			
			ServletContext context = request.getServletContext();
			context.setAttribute("qrIdCheck", md5Password);	
			context.setAttribute("qrIdStatus", 1);	
			QRCodeGenerate() ;
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	

	//WorkHourSummary
		public String workHoursSummary1() {
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
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();

		request.setAttribute("type", "0");
		request.setAttribute("daynow", day);
		request.setAttribute("monthnow", month);
		request.setAttribute("yearnow", yearnow); 
		request.setAttribute("year", year);

		List<Map<String, Object>> wh = workHoursDAO.test_workhoursummary(logonUser,year);
		request.setAttribute("wh", wh);

		List<Map<String, Object>> leaves = leaveDAO.test_LeavesList(logonUser,year);
		request.setAttribute("leaves", leaves);

		List<Map<String,Object>> holidayList = holidayDAO.test_holiday(year);
		request.setAttribute("holidayList", holidayList);

		findWeekendsList(year);

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date timenow = new Date();

		String timeuse = formatter.format(timenow);

		List<Map<String,Object>> timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
		request.setAttribute("timeckeckin", timeckeckin);


		List<Map<String,Object>> timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
		request.setAttribute("timeckeckout", timeckeckout);


		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}

		//searchWorkHourSummary
		public String searchWorkHourSummary1() {
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

		String type1 = request.getParameter("type"); 
		int type = Interger.parseInt(type1);
		request.setAttribute("type", type1);


		int daynow = localDate.getDayOfMonth();
		request.setAttribute("daynow", daynow);

		int monthnow = localDate.getMonthValue();
		request.setAttribute("monthnow", monthnow);

		List<Map<String, Object>> cubeUser = userDAO.sequense();
		request.setAttribute("cubeUser", cubeUser);

		List<Map<String, Object>> leaves = null;
		List<Map<String,Object>> holidayList = null ;
		List<Map<String, Object>> wh = null;
		List<Map<String,Object>> timeckeckin = null ;
		List<Map<String,Object>> timeckeckout = null;
		weekendList = new ArrayList();
		weekendList2 = new ArrayList();

		if(type == 0 || type == 1 || type == 2) {
		holidayList = holidayDAO.test_holiday(year);

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


		}
		if(type == 1 || type == 0) {
		wh = workHoursDAO.test_workhoursummary(logonUser,year);

		timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
		timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
		leaves = leaveDAO.test_LeavesList(logonUser,year);


		}
		if(type == 2 || type == 0) {
		leaves = leaveDAO.test_LeavesList(logonUser,year);

		wh = workHoursDAO.test_workhoursummary(logonUser,year);
		}

		request.setAttribute("holidayList", holidayList);
		request.setAttribute("wh", wh);
		request.setAttribute("timeckeckin", timeckeckin);
		request.setAttribute("timeckeckout", timeckeckout);
		request.setAttribute("leaves", leaves);
		request.setAttribute("weekEndDay",weekendList);
		request.setAttribute("weekEndMonth",weekendList2);


		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}

	//WorkHourSummary
	public String workHoursSummary() {
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
	int month = localDate.getMonthValue();
	int day = localDate.getDayOfMonth();

	request.setAttribute("type", "0");
	request.setAttribute("daynow", day);
	request.setAttribute("monthnow", month);
	request.setAttribute("yearnow", yearnow); 
	request.setAttribute("year", year);

	List<Map<String, Object>> wh = workHoursDAO.test_workhoursummary(logonUser,year);
	request.setAttribute("wh", wh);

	List<Map<String, Object>> leaves = leaveDAO.test_LeavesList(logonUser,year);
	request.setAttribute("leaves", leaves);

	List<Map<String,Object>> holidayList = holidayDAO.test_holiday(year);
	request.setAttribute("holidayList", holidayList);

	findWeekendsList(year);

	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	Date timenow = new Date();

	String timeuse = formatter.format(timenow);

	List<Map<String,Object>> timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
	request.setAttribute("timeckeckin", timeckeckin);


	List<Map<String,Object>> timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
	request.setAttribute("timeckeckout", timeckeckout);


	return SUCCESS;
	} catch (Exception e) {
	e.printStackTrace();
	return ERROR;
	}
	}

	//searchWorkHourSummary
	public String searchWorkHourSummary() {
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

	String type1 = request.getParameter("type"); 
	int type = Interger.parseInt(type1);
	request.setAttribute("type", type1);


	int daynow = localDate.getDayOfMonth();
	request.setAttribute("daynow", daynow);

	int monthnow = localDate.getMonthValue();
	request.setAttribute("monthnow", monthnow);

	List<Map<String, Object>> cubeUser = userDAO.sequense();
	request.setAttribute("cubeUser", cubeUser);

	List<Map<String, Object>> leaves = null;
	List<Map<String,Object>> holidayList = null ;
	List<Map<String, Object>> wh = null;
	List<Map<String,Object>> timeckeckin = null ;
	List<Map<String,Object>> timeckeckout = null;
	weekendList = new ArrayList();
	weekendList2 = new ArrayList();

	if(type == 0 || type == 1 || type == 2) {
	holidayList = holidayDAO.test_holiday(year);

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


	}
	if(type == 1 || type == 0) {
	wh = workHoursDAO.test_workhoursummary(logonUser,year);

	timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
	timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
	leaves = leaveDAO.test_LeavesList(logonUser,year);


	}
	if(type == 2 || type == 0) {
	leaves = leaveDAO.test_LeavesList(logonUser,year);

	wh = workHoursDAO.test_workhoursummary(logonUser,year);
	}

	request.setAttribute("holidayList", holidayList);
	request.setAttribute("wh", wh);
	request.setAttribute("timeckeckin", timeckeckin);
	request.setAttribute("timeckeckout", timeckeckout);
	request.setAttribute("leaves", leaves);
	request.setAttribute("weekEndDay",weekendList);
	request.setAttribute("weekEndMonth",weekendList2);


	return SUCCESS;
	} catch (Exception e) {
	e.printStackTrace();
	return ERROR;
	}
	}

	//WorkHourSummary
	public String workHoursSummary2() {
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
	int month = localDate.getMonthValue();
	int day = localDate.getDayOfMonth();

	request.setAttribute("type", "0");
	request.setAttribute("daynow", day);
	request.setAttribute("monthnow", month);
	request.setAttribute("yearnow", yearnow); 
	request.setAttribute("year", year);

	List<Map<String, Object>> wh = workHoursDAO.test_workhoursummary2(logonUser,year);
	request.setAttribute("wh", wh);

	List<Map<String, Object>> leaves = leaveDAO.test_LeavesList(logonUser,year);
	request.setAttribute("leaves", leaves);

	List<Map<String,Object>> holidayList = holidayDAO.test_holiday(year);
	request.setAttribute("holidayList", holidayList);

	findWeekendsList(year);

	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	Date timenow = new Date();

	String timeuse = formatter.format(timenow);

	List<Map<String,Object>> timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
	request.setAttribute("timeckeckin", timeckeckin);


	List<Map<String,Object>> timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
	request.setAttribute("timeckeckout", timeckeckout);


	return SUCCESS;
	} catch (Exception e) {
	e.printStackTrace();
	return ERROR;
	}
	}

	//searchWorkHourSummary
	public String searchWorkHourSummary2() {
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

	String type1 = request.getParameter("type");
	int type = Interger.parseInt(type1);
	request.setAttribute("type", type1);


	int daynow = localDate.getDayOfMonth();
	request.setAttribute("daynow", daynow);

	int monthnow = localDate.getMonthValue();
	request.setAttribute("monthnow", monthnow);

	List<Map<String, Object>> cubeUser = userDAO.sequense();
	request.setAttribute("cubeUser", cubeUser);

	List<Map<String, Object>> leaves = null;
	List<Map<String,Object>> holidayList = null ;
	List<Map<String, Object>> wh = null;
	List<Map<String,Object>> timeckeckin = null ;
	List<Map<String,Object>> timeckeckout = null;
	weekendList = new ArrayList();
	weekendList2 = new ArrayList();

	if(type == 0 || type == 1 || type == 2) {
	holidayList = holidayDAO.test_holiday(year);

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


	}
	if(type == 1 || type == 0) {
	wh = workHoursDAO.test_workhoursummary2(logonUser,year);

	timeckeckin = workHoursDAO.test_timeckeckin("1",logonUser,year);
	timeckeckout = workHoursDAO.test_timeckeckin("2",logonUser,year);
	leaves = leaveDAO.test_LeavesList(logonUser,year);

	}
	if(type == 2 || type == 0) {
	leaves = leaveDAO.test_LeavesList(logonUser,year);

	wh = workHoursDAO.test_workhoursummary2(logonUser,year);
	}

	request.setAttribute("holidayList", holidayList);
	request.setAttribute("wh", wh);
	request.setAttribute("timeckeckin", timeckeckin);
	request.setAttribute("timeckeckout", timeckeckout);
	request.setAttribute("leaves", leaves);
	request.setAttribute("weekEndDay",weekendList);
	request.setAttribute("weekEndMonth",weekendList2);


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
	
	public String workHoursAnniversary() {
		try {
		List<Map<String, Object>> whAnniversary = workHoursDAO.test_workHoursAnniversary();
		request.setAttribute("whAnniversary", whAnniversary);
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}

		public String workHoursSelectMonth() {
		try {

		java.util.Date date = new java.util.Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int yearnow = localDate.getYear();
		int year = yearnow;
		int month = localDate.getMonthValue();

		List<Map<String, Object>> whLastWorkHour = workHoursDAO.test_lastWorkHour(year);
		request.setAttribute("whLastWorkHour", whLastWorkHour);
		
		List<Map<String, Object>> whLastWorkHour2 = workHoursDAO.test_lastWorkHour2(year);
		request.setAttribute("whLastWorkHour2", whLastWorkHour2);

		request.setAttribute("year", year);
		request.setAttribute("type", month);
		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}

		public String searchworkHoursSelectMonth() {
		try {
		String searchyaer = request.getParameter("year");
		int year = Interger.parseInt(searchyaer);
		request.setAttribute("year", year);

		List<Map<String, Object>> whLastWorkHour = workHoursDAO.test_lastWorkHour(year);
		request.setAttribute("whLastWorkHour", whLastWorkHour);
		List<Map<String, Object>> whLastWorkHour2 = workHoursDAO.test_lastWorkHour2(year);
		request.setAttribute("whLastWorkHour2", whLastWorkHour2);
		

		String type1 = request.getParameter("type");
		int type = Interger.parseInt(type1);
		request.setAttribute("type", type1);

		return SUCCESS;
		} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
		}
		}
		
		public String todayLogin() {
			try {
				log.debug("today login method");
				Date newDate = new Date();  
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date= dateFormat.format(newDate);
				log.debug(date);
				return SUCCESS;
			} catch (Exception e) {

				e.printStackTrace();
				return ERROR;
			}
		}
		
		public String todayLoginJson() {
			try {
				Date newDate = new Date();  
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date= dateFormat.format(newDate);
				Gson gson = new GsonBuilder().create();
			     String responseJSON = gson.toJson(workHoursDAO.todayLogin(date));
			     request.setAttribute("json", responseJSON);
				return "json";
			} catch (Exception e) {

				e.printStackTrace();
				return ERROR;
			}
		}
	}