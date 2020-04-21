package com.cubesofttech.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.model.Holiday;
import com.cubesofttech.model.Leaves;
import com.opensymphony.xwork2.ActionSupport;

public class HolidayAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(HolidayAction.class);
	private static final long serialVersionUID = 1L;
	private static  String checkFlag ="";
	
	private static  String Check_add ="";
	private static  String dateTime ="";

	private static  Calendar cal = Calendar.getInstance(); // Use Calendar .Year

	@Autowired
	private HolidayDAO holidayDAO;
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	public String List() {
		try {
	  checkFlag = "1";	
	  List<Holiday> holidayList = holidayDAO.findAll();
	  request.setAttribute("holidayList", holidayList);	 
	  List<Object> holidayList_year = holidayDAO.searchallyear();
	   request.setAttribute("holidayList_year", holidayList_year);
	 
	 
	   
		return SUCCESS;
		} catch (Exception e) { 
		e.printStackTrace();
		return ERROR; }
		}
	
	public String List1() {
		try {
	   checkFlag = "0";
	  
    	String date = request.getParameter("date");
    	String date1 = request.getParameter("date1");
		if(date != null){			
			 java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			    java.sql.Date start_date = new java.sql.Date(utilDate.getTime());
			 
			 request.setAttribute("flag12",start_date);      //flag12 = Set default Date for Calendar  	  
		}
		if(date1 != null){			
			 request.setAttribute("flag12",date1);      //flag12 = Set default Date for Calendar  	  
		}
		
		
	  List<Holiday> holidayList = holidayDAO.findAll();
	  request.setAttribute("holidayList", holidayList);	
	  String flag_cal = request.getParameter("flag");
	   if(flag_cal != null ){
	     Calendar cal1 = Calendar.getInstance(); 
	  
	       cal = cal1;
	   }
		 int month = cal.get(Calendar.MONTH);
		 int year = cal.get(Calendar.YEAR);
		
		
		  request.setAttribute("num_month", month);
		  request.setAttribute("num_year", year);
	  checkFlag ="0";
		return SUCCESS;
		} catch (Exception e) { 
		e.printStackTrace();
		return ERROR; }
		}
	//**
	public String Add() {
		try {
			int flag = 1;
			int type;
			String Date_Start = request.getParameter("Date-Start");
			String Date_End = request.getParameter("Date-End");		
			String name_form = request.getParameter("name");
			String description_form = request.getParameter("description");
			Long Id = holidayDAO.getMaxId() + 1;

			java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(Date_Start);
			java.sql.Date start_date = new java.sql.Date(utilDate.getTime());

			java.util.Date utilDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(Date_End);
			java.sql.Date end_date = new java.sql.Date(utilDate1.getTime());
			if (Date_Start.equals(Date_End)) {
				type = 0;
			} else {
				type = 1;
			}

			switch (type) {
			case 0:
				List<Map<String, Object>> holiday_checkdate = holidayDAO.findByDate(start_date);
				if (holiday_checkdate.size() != 0) { // à¸•à¸£à¸§à¸ˆà¸ªà¸­à¸šà¸§à¸±à¸™à¸—à¸µà¹ˆà¹„à¸¡à¹ˆà¹ƒà¸«à¹‰à¸‹à¹‰à¸³à¸�à¸±à¸™
					request.setAttribute("flag", flag);
					request.setAttribute("date", Date_Start);
					request.setAttribute("flag_form", checkFlag);
					return INPUT;
				} else {
					// Save
					Holiday holiday_save = new Holiday();
				    holiday_save.setId_date(Id);
					holiday_save.setStart_date(start_date);
					holiday_save.setEnd_date(end_date);
					holiday_save.setDescription(description_form);
					holiday_save.setHead(name_form);
					holidayDAO.save(holiday_save);
					List<Holiday> holiday_checkdate1 = holidayDAO.findByMonth(Date_Start); // à¸„à¹‰à¸™à¸«à¸²à¸§à¸±à¸™à¸—à¸µà¹ˆà¸«à¸¢à¸¸à¸”à¸—à¸±à¹‰à¸‡à¸«à¸¡à¸”à¹ƒà¸™à¹€à¸”à¸·à¸­à¸™à¸™à¸±à¹ˆà¸™
					Holiday check = null;
					Holiday check2 = null;
					int flag2 = 0;
					for (int i = 0; i < holiday_checkdate1.size(); i++) {
						if (holiday_checkdate1.get(i).getStart_date() == start_date) {
							if (i != 0) {
								flag2 = 1;
								check = holiday_checkdate1.get(i - 1);
								check2 = holiday_checkdate1.get(i); // à¸§à¸±à¸™à¸—à¸µà¹ˆà¹€à¸žà¸´à¹ˆà¸¡à¹€à¸‚à¹‰à¸²à¸¡à¸²à¹ƒà¸«à¸¡à¹ˆ
								break;
							} else {
								flag2 = 0;
								check = holiday_checkdate1.get(i);
								break;
							}
						}
					} // end for
					if (flag2 == 1) {
						List<Holiday> holiday_check = holidayDAO.protect(check);
						if (holiday_check.size() > 1) {
							holidayDAO.delete(check2);
							request.setAttribute("flag", flag2);
							return INPUT;
						}
					} // end flag2 = 1 check à¸§à¹ˆà¸² à¸‚à¹‰à¸­à¸¡à¸¹à¸¥à¸—à¸µà¹ˆà¹€à¸žà¸´à¹ˆà¸¡à¹€à¸‚à¹‰à¸²à¹„à¸›à¹ƒà¸«à¸¡à¹ˆ à¸­à¸¢à¸¹à¹ˆ index à¸—à¸µà¹ˆ 0 à¸£à¸¶à¹€à¸›à¸¥à¹ˆà¸²
					if (Check_add.equals("0")) {
						return SUCCESS;
					} else {
						Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
						cal1.setTime(start_date);

						cal = cal1; // Cal is Global variable
						request.setAttribute("flag12", start_date);
						return LOGIN;
					}
				}
			case 1:
				Holiday holiday_save2 = new Holiday();
				holiday_save2.setId_date(Id);
				holiday_save2.setStart_date(start_date);
				holiday_save2.setEnd_date(end_date);
				holiday_save2.setDescription(description_form);
				holiday_save2.setHead(name_form);
				List<Holiday> holiday_checkdate2 = holidayDAO.protect(holiday_save2);
				if (holiday_checkdate2.size() != 0) {
					request.setAttribute("flag", flag);
					return INPUT;
				}

				holidayDAO.save(holiday_save2);
				if (Check_add.equals("0")) {
					return SUCCESS;
				} else {
					Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
					cal1.setTime(start_date);

					cal = cal1; // Cal is Global variable
					request.setAttribute("flag12", start_date);
					return LOGIN;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String Edit() {
		try {

			String dateid = request.getParameter("id");
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("flag_form", checkFlag);
			int x = Integer.parseInt(dateid);
			Holiday holidayrecord = holidayDAO.findById(x);
			request.setAttribute("holidayrecord", holidayrecord);
			return SUCCESS;
		} catch (Exception e) {

			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String Update() {
	
		try {
			int flag = 1 ;
       
			String dateid = request.getParameter("id_date");
			String head = request.getParameter("name");
			String description = request.getParameter("description");
			String Date_Start = request.getParameter("Date-Start");
			String Date_End = request.getParameter("Date-End");
			if(Date_End.equals("")){
				Date_End = Date_Start;
			}
			int x = Integer.parseInt(dateid);
			java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(Date_Start);
		    java.sql.Date start_date = new java.sql.Date(utilDate.getTime());
		    
		    java.util.Date utilDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(Date_End);
		    java.sql.Date end_date = new java.sql.Date(utilDate1.getTime());
		    
		    
			Holiday holiday_update = new Holiday();
			holiday_update = holidayDAO.findById(x);
			holiday_update.setStart_date(start_date);
			holiday_update.setEnd_date(end_date);
			holiday_update.setHead(head);
			holiday_update.setDescription(description);
			
			List<Map<String,Object>> holiday_checkdate =  holidayDAO.findByDate(start_date);
			 Holiday check = holiday_update;
			  
								 
			 List<Holiday> a = null;
			 List<Holiday> holiday_check =  holidayDAO.protect_edit(check);
			 List<Holiday> holiday_check1 =  holidayDAO.protect_edit1(check);

			 if(holiday_check.size() != 0){a = holiday_check;}else{ a = holiday_check1;}
			 
			 
            if(holiday_checkdate.size() != 0 || a.size() != 0  ){         	
                 	if(holiday_checkdate.get(0).equals(holiday_update) || a.get(0).equals(holiday_update) ){
                 				
            			holidayDAO.update(holiday_update);
            			if(checkFlag.equals("1")){
            			return SUCCESS;
            			}else{
            				   Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
           				    cal1.setTime(start_date);
           			
           				    cal = cal1; // Cal is Global variable

            		 request.setAttribute("flag12", start_date);       	  
            				return LOGIN ;}
                 	}
           	 request.setAttribute("flag", flag);
        	Holiday holidayrecord = holidayDAO.findById(x);
			request.setAttribute("holidayrecord", holidayrecord); // à¸ªà¹ˆà¸‡à¸„à¹ˆà¸²à¹ƒà¸«à¹‰à¸«à¸™à¹‰à¸² Edit 
			
           	 return INPUT;
            	}else{		     
            		
			holidayDAO.update(holiday_update);
			if(checkFlag.equals("1")){
    			return SUCCESS;
    			}else{
    				   Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
   				    cal1.setTime(start_date);
   			
   				    cal = cal1; // Cal is Global variable

    				 request.setAttribute("flag12", start_date);       	  
    				return LOGIN ;}
            }
           
		} catch (Exception e) {
			
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	public String Delete() {
		
		try {
			
			String dateid = request.getParameter("id");
			int x = Integer.parseInt(dateid);
			Holiday holiday_delete = new Holiday();
			holiday_delete = holidayDAO.findById(x);
			
			holidayDAO.delete(holiday_delete);
			
    			return SUCCESS;
    		   	  
    			
	
		} catch (Exception e) {
			
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public String searchtable() {
		try {
			String date_1 = request.getParameter("myselect");
			 List<Holiday> holidayList =  holidayDAO.searchtable(date_1);	
	     request.setAttribute("holidayList", holidayList);
	     List<Object> holidayList_year = holidayDAO.searchallyear();
		   request.setAttribute("holidayList_year", holidayList_year);
		   request.setAttribute("myselect", date_1);
		return SUCCESS;
		} catch (Exception e) { 
		e.printStackTrace();
		return ERROR; }
		}
	
	public String formadd(){
		String flag = request.getParameter("flag");
		String date = request.getParameter("date_cal");		
		
		
	
		 if(flag.equals("0")){
			 Check_add = "1";
			 dateTime = date;
		 }else {Check_add = "0";}
		 request.setAttribute("date", date); 
		 request.setAttribute("flag_form", checkFlag);  
		return SUCCESS;
	}
	public String Update_calendar()  {
		
		
		try {
			
			String dateid = request.getParameter("id_date");
			String Date_End = request.getParameter("date_end");
			
			int x = Integer.parseInt(dateid);
		    java.util.Date utilDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(Date_End);
		    java.sql.Date end_date = new java.sql.Date(utilDate1.getTime());
		  			Holiday holiday_update = new Holiday();
			holiday_update = holidayDAO.findById(x);
			holiday_update.setEnd_date(end_date);	
			holidayDAO.update(holiday_update);
		  request.setAttribute("flag12",end_date);  
		   Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
		    cal1.setTime(end_date);
	
		    cal = cal1; // Cal is Global variable

           return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
			return ERROR;
		}

		
		}
	
public String moving_calendar()  {	
		try {
			
			String dateid = request.getParameter("id_date");
			String Date_End = request.getParameter("Date-End");
			String Date_Start = request.getParameter("Date-Start");
			int x = Integer.parseInt(dateid);
			java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(Date_Start);
		    java.sql.Date start_date = new java.sql.Date(utilDate.getTime());
		    
		    java.util.Date utilDate1 = new SimpleDateFormat("dd-MM-yyyy").parse(Date_End);
		    java.sql.Date end_date = new java.sql.Date(utilDate1.getTime());
		    
			Holiday holiday_update = new Holiday();
			holiday_update = holidayDAO.findById(x);
			holiday_update.setEnd_date(end_date);
			holiday_update.setStart_date(start_date);
				    Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
				    cal1.setTime(start_date);
			
				    cal = cal1; // Cal is Global variable
			  
			holidayDAO.update(holiday_update);
		  request.setAttribute("flag12",end_date);       	  
           return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
			return ERROR;
		}

		
		}
	
public String Delete_calendar() {
	
	try {
	
		String dateid = request.getParameter("id");
		String Date_Start = request.getParameter("date");
		
		java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(Date_Start);
	    java.sql.Date start_date = new java.sql.Date(utilDate.getTime());

		int x = Integer.parseInt(dateid);
		Holiday holiday_delete = new Holiday();
		holiday_delete = holidayDAO.findById(x);
		   Calendar cal1 = Calendar.getInstance(); // Use Calendar .Year
		    cal1.setTime(start_date);
	
		    cal = cal1; // Cal is Global variable

		
		holidayDAO.delete(holiday_delete);
	 request.setAttribute("flag12",start_date);
	 
			return SUCCESS;
	} catch (Exception e) {
		
		e.printStackTrace();
		return ERROR;
	}
}
public void findnext_year() {
	try {
//  List<Holiday> holidayList = holidayDAO.findAll();
  String next = request.getParameter("year_next");
  
  List<Holiday> holidayList = holidayDAO.findnext_Year(next);

  JSONArray arrayObj1 = new JSONArray();
  JSONArray arrayObj2 = new JSONArray();
  JSONArray arrayObj3 = new JSONArray();
  JSONArray arrayObj4 = new JSONArray();
  JSONArray arrayObj5 = new JSONArray();

    for(int i = 0 ; i < holidayList.size(); i++ ){
    	arrayObj1.put(holidayList.get(i).getId_date());
    	arrayObj2.put(holidayList.get(i).head);
    	arrayObj3.put(holidayList.get(i).description);
    	arrayObj4.put(holidayList.get(i).getStart_date().toString());
    	arrayObj5.put(holidayList.get(i).getEnd_date().toString());
    
    }
  
    PrintWriter out = response.getWriter();
           JSONObject json = new JSONObject();

    json.put("id", arrayObj1);
    json.put("title", arrayObj2);
    json.put("des", arrayObj3);
    json.put("start", arrayObj4);
    json.put("end", arrayObj5);
    
     out.print(json);
	out.flush();
	out.close();

	
	} catch (Exception e) { 
	e.printStackTrace();
	 }
	}


	
}
