package com.cubesofttech.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.ExpenseDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.User;
import com.cubesofttech.util.ReportUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.cubesofttech.dao.WorkHoursDAO;
public class ReportAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	public static final String LOGOPATH = "logoPath";
	public static final String JASPERPATH = "/WEB-INF/classes/jasper";
	public static final String IMAGEPATH = "/images";
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private WorkHoursDAO workHoursDAO;
	public String expenseTravelReport() throws IOException {
		ServletContext context = request.getServletContext();
		String expenseGroupId = request.getParameter("expenseGroupId");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, image );
		reportParameter.put("expenseGroupId",expenseGroupId );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/expTravel", "expTravel.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	@Autowired
	private ExpenseDAO expenseDAO;
	public String expenseTravelReportList() throws IOException {
		ServletContext context = request.getServletContext();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, image );
		reportParameter.put("month",month );
		reportParameter.put("year",year );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/expTravelList", "hero.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	
	public String expenseTravelReportTH() throws IOException {
		ServletContext context = request.getServletContext();
		String expenseGroupId = request.getParameter("expenseGroupId");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, image );
		reportParameter.put("expenseGroupId",expenseGroupId );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/expTravelTH", "expTravelTH.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public String leaveReport() throws IOException {
		ServletContext context = request.getServletContext();
		String leaveId = request.getParameter("leaveId");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		File checkbox = new File(imagePath + "/checked_checkbox.png");
		BufferedImage chcekboximage = ImageIO.read(checkbox);
		File uncheckbox = new File(imagePath + "/unchecked_checkbox.png");
		BufferedImage unchcekboximage = ImageIO.read(uncheckbox);
		reportParameter.put(LOGOPATH, logoimage );
		reportParameter.put("checkboxPath", chcekboximage );
		reportParameter.put("unCheckboxPath", unchcekboximage );
		reportParameter.put("leaveId",leaveId );
		try {
		ReportUtil.printReportToBrowsePdf(jasperPath, "/leaveForm", "leaveForm.pdf", reportParameter, request,
		response);
		} catch (Exception e) {
		log.debug(e);
		}
		return null;
		}
	

	public String leaveMonthReport() throws IOException {
		ServletContext context = request.getServletContext();
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		File checkbox = new File(imagePath + "/checked_checkbox.png");
		BufferedImage chcekboximage = ImageIO.read(checkbox);
		File uncheckbox = new File(imagePath + "/checked_checkbox.png");
		BufferedImage unchcekboximage = ImageIO.read(uncheckbox);
		reportParameter.put(LOGOPATH, logoimage );
		reportParameter.put("checkboxPath", chcekboximage );
		reportParameter.put("unCheckboxPath", unchcekboximage );
		reportParameter.put("month",month );
		reportParameter.put("year",year );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/leaveMonthReport", "leaveMonthReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public String borrowReport() throws IOException {
		ServletContext context = request.getServletContext();
		String borrowId = request.getParameter("borrowId");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, logoimage );
		reportParameter.put("borrowId",borrowId );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath+"/", "borrowReport", "borrowReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public String borrowSendBackReport() throws IOException {
		ServletContext context = request.getServletContext();
		String borrowId = request.getParameter("borrowId");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, logoimage );
		reportParameter.put("borrowId",borrowId );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath+"/", "borrowSendBackReport", "borrowSendBackReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
		
	public String leaveslistReport() throws Exception {
		ServletContext context = request.getServletContext();
		String username = request.getParameter("username");
		String year = request.getParameter("year");
		User u = userDAO.findById(username);
		
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		File checkbox = new File(imagePath + "/checked_checkbox.png");
		BufferedImage chcekboximage = ImageIO.read(checkbox);
		File uncheckbox = new File(imagePath + "/checked_checkbox.png");
		BufferedImage unchcekboximage = ImageIO.read(uncheckbox);
		reportParameter.put("Parameter3", logoimage );
//		reportParameter.put("checkboxPath", chcekboximage );
//		reportParameter.put("unCheckboxPath", unchcekboximage );
		reportParameter.put("Parameter2",username);
		reportParameter.put("Parameter1",year);
		reportParameter.put("Parameter4",u.getName());
		reportParameter.put("Parameter5",year);

	    
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/LeavesReport", "LeavesReport.pdf", reportParameter, request,
					response);
			
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public String HolidayReport() throws IOException {
		ServletContext context = request.getServletContext();
		String year = request.getParameter("year");
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, image );
		reportParameter.put("Parameter1",year);
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/holidayReport", "HolidayReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public String checkListReport() throws Exception {
		ServletContext context = request.getServletContext();
		String user = request.getParameter("user.id");
		int compare=0;
		String check;
		if(user.compareTo("All")==compare) {
			check = "WHERE date(work_hours_time_work)  BETWEEN date($P{start}) AND date($P{end})";
		}else {
			check = "WHERE user_create =$P{user} AND date(work_hours_time_work)  BETWEEN date($P{start}) AND date($P{end})";
		}
		String dateStart = request.getParameter("start"); 
		String dateEnd = request.getParameter("end");  
		String imagePath = context.getRealPath(IMAGEPATH);
	    Date start=new SimpleDateFormat("dd-MM-yyyy").parse(dateStart);
	    Date end=new SimpleDateFormat("dd-MM-yyyy").parse(dateEnd);
		String jasperPath = context.getRealPath(JASPERPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put("check",check );
		 reportParameter.put("user",user );
		 reportParameter.put("start",start );
		 reportParameter.put("end",end );
		 reportParameter.put(LOGOPATH, image );
		 
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/checkList", "checkList.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public String userAllReport() throws IOException {
		ServletContext context = request.getServletContext();
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage image = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, image );
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath, "/userAllReport", "userAllReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public String userReport() throws IOException {
		log.debug("userReport");
		ServletContext context = request.getServletContext();
		String userId = request.getParameter("id");
		log.debug(userId);
		String jasperPath = context.getRealPath(JASPERPATH);
		String imagePath = context.getRealPath(IMAGEPATH);
		Map<String, Object> reportParameter = new HashMap<>();
		File logo = new File(imagePath + "/logo.png");
		BufferedImage logoimage = ImageIO.read(logo);
		reportParameter.put(LOGOPATH, logoimage );
		reportParameter.put("userId",userId );
		log.debug(reportParameter);
		try {
			ReportUtil.printReportToBrowsePdf(jasperPath+"/", "userReport", "userReport.pdf", reportParameter, request,
					response);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
}
