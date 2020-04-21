package com.cubesofttech.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.TravelDAO;
import com.cubesofttech.model.TravelList;
import com.cubesofttech.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class TravelAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(TravelAction.class);
	private static final long serialVersionUID = 1L;
	

	public Timestamp date_to_timestampfortravellist(String date, String time) throws ParseException {
		Timestamp ts;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		ts = new Timestamp(sdf1.parse(date + " " + time).getTime());
		return ts;
	}

	
	
	@Autowired
	public TravelDAO travelDAO;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	// แสดง List รายการทั้งหมด

	public String list() {

		try {
			log.info("sre");
			List<TravelList> travelList = travelDAO.findAll();
			request.setAttribute("travelList", travelList);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);

			return ERROR;
		}
	}

	// เสร็จสิ้น การเเสดง รายการ ทั้งหมด __________________________

	// ปุ่ม ลบ รายการ
	public String delete() {

		try {
			log.info("Pass");
			String listId = request.getParameter("id");
			int tranId = Integer.parseInt(listId);
			TravelList travel_delete = new TravelList();
			travel_delete = travelDAO.findById(tranId);
			travelDAO.delete(travel_delete);

			return SUCCESS;

		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	// เสร็จสิ้น การลบ รายการ __________________________

	// การ Add เพิ่ม ข้อมูล

	public String saveTravel() {

		try {
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username
																				// login
			String logonUser = ur.getId(); // Username login

			log.info(logonUser);
			TravelList travelsave = new TravelList();
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			// เพิ่มวันที่ และอื่นๆ;
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			log.info(time);
			Timestamp ts = date_to_timestampfortravellist(date, time);

			List<Map<String, Object>> travelcheckname = travelDAO.findByName(name);
			log.info(travelcheckname);
			String namecheck = travelcheckname.toString();
			log.info(namecheck.length());
			
			if (namecheck.length() != 2) {
				request.setAttribute("flag", "1");
				return INPUT;
			} 
			
			else
				travelsave.setDescription(description);
				travelsave.setName(name);
				travelsave.setUserCreate(logonUser);
				travelsave.setTimeCreate(ts);
				travelDAO.save(travelsave);
	
			List<TravelList> travelList = travelDAO.findAll();
			request.setAttribute("travelList", travelList);
			return SUCCESS;
		} catch (Exception e) {

			return ERROR;
		}
	}
	// จบวันที่

	public String edit() { // ปุ่มเรียกดู EDIT เรียก id มา
		try {
			log.info("edit");
			String editlist = request.getParameter("id");
			TravelList depart = new TravelList();
			int tranID = Integer.parseInt(editlist);
			depart = travelDAO.findById(tranID);
			request.setAttribute("depart", depart); // ส่งค่าจากหลังไปหน้า
			return SUCCESS;

		} catch (Exception e) {

			return ERROR;
		}
	}

	public String updatedept() { // ปุ่มเรียกดู
		try {
			log.info("pass");
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username
																				// login
			String logonUser = ur.getId(); // Username login

			log.info(logonUser);
			TravelList travelListupdate = new TravelList();
			String id = request.getParameter("id");
			int tranID = Integer.parseInt(id);
			travelListupdate = travelDAO.findById(tranID);

			String name = request.getParameter("name");
			String deptdes = request.getParameter("deptdes");

			String date = request.getParameter("date");
			String time = request.getParameter("time");
			log.info(time);

			Timestamp ts = date_to_timestampfortravellist(date, time);
			
			List<Map<String, Object>> travelchecknameupdate = travelDAO.findByName(name);
			String namecheck = travelchecknameupdate.toString();
			log.info(namecheck);
			log.info(namecheck.length());
			
			travelListupdate.setName(name);
			travelListupdate.setDescription(deptdes);
			travelListupdate.setTimeUpdate(ts);
			travelListupdate.setUserUpdate(logonUser);
			travelDAO.update(travelListupdate);
			
			List<TravelList> departentlist = travelDAO.findAll();
			request.setAttribute("departentlist", departentlist);
			return SUCCESS;

		} catch (Exception e) {

			return ERROR;
		}
		
	}	
}
