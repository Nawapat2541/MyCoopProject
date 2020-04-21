package com.cubesofttech.action;



import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.ExpTravelTypeDAO;
import com.cubesofttech.dao.ExpenseDAO;
import com.cubesofttech.dao.HolidayDAO;
import com.cubesofttech.dao.LeaveDAO;
import com.cubesofttech.dao.LeaveTypeDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.dao.WorkHoursDAO;
import com.cubesofttech.model.LeaveType;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;



public class GraphAction extends ActionSupport {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  String graph_year ="";
	private static  String graph_month ="";
	private static  String flag_search_graph ="";
	private static  String statusEnable ="1";
	private static  String sortz = "";
	private static final Logger log = Logger.getLogger(GraphAction.class);
	private static  Calendar cal = Calendar.getInstance(); // Use Calendar .Year
	public static final String ONLINEUSER = "onlineUser";

	
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LeaveTypeDAO leavetypeDAO;
	@Autowired
	private LeaveDAO leaveDAO;
	@Autowired
	private WorkHoursDAO workHoursDAO;
	
	@Autowired
	private ExpenseDAO expenseDAO;
	
	@Autowired
	private ExpTravelTypeDAO expTravelTypeDAO;
	
	@Autowired
	private LeaveDAO leavesDAO;
	
	@Autowired
	private HolidayDAO holidayDAO;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

public String open_graph() throws Exception{
	int month_now = cal.get(Calendar.MONTH) + 1 ;
	   String user = request.getParameter("userId");
	   String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	  
	  String yearnow = timeStamp.substring(0, 7);
	 
	  int year2 = cal.get(Calendar.YEAR) ;
	  request.setAttribute("year1",year2);
	  if(flag_search_graph != ""){
		  if(!graph_year.equals("All")){
		  yearnow =  graph_year + "-" + graph_month ;
		   year2 = Integer.valueOf(graph_year);
			request.setAttribute("userId",user);
			request.setAttribute("month1",graph_month);
			request.setAttribute("year1",graph_year);
			request.setAttribute("flag","1");
			month_now = 12 ;
		  }else{
			 yearnow =  year2 + "-" + graph_month ;
			 month_now = 12 ;
				request.setAttribute("userId",user);
				request.setAttribute("month1",graph_month);
				request.setAttribute("year1",graph_year);
				request.setAttribute("flag","1");
				  year2 = 0;
		  }
		  request.setAttribute("statusEnable",statusEnable);
	  }
	    // Pie Charts
       int[] x = userDAO.count_user(); 	
       request.setAttribute("count",x[0]); //count_IT
       request.setAttribute("count2",x[1]); //count_Intern
       request.setAttribute("count3",x[2]);//count_HR 
       request.setAttribute("count4",x[3]);//count_Admin
	
       //Rader Charts
       int[] rader = expenseDAO.count_exp_type(); 	
       request.setAttribute("count5",rader[0]);//count_taxi
       request.setAttribute("count6",rader[1]);// count_mrt
       request.setAttribute("count7",rader[2]);//count_bts
       request.setAttribute("count8",rader[3]);// count_morter
       request.setAttribute("count9",rader[4]);// count_express
       request.setAttribute("count10",rader[5]);//count_all_exp 
       
       
       // XY Charts
       List<Map<String, Object>> work_in = workHoursDAO.graph_workhours(user,yearnow);
       List<Map<String, Object>> work_out = workHoursDAO.graph_workhours_out(user,yearnow);
       request.setAttribute("mount_date",yearnow+"-02");
		List<String> day = new ArrayList<String>(); 
		List<String> hours = new ArrayList<String>();
			if(work_in.size() != 0){
		 request.setAttribute("work_checkin",work_in);
	    for(int i = 0 ; i < work_in.size() ; i++){
		String a   = work_in.get(i).get("work_hours_time_work").toString();
		a  = a.substring(0,10);
		day.add(a);	
		String work_avg_in_day = workHoursDAO.graph_workhours_in_avg(a);
		work_avg_in_day = work_avg_in_day.substring(0,5);
    	hours.add(work_avg_in_day);
	    }
	}
	List<String> day2 = new ArrayList<String>(); 
	List<String> hours2 = new ArrayList<String>();
	if(work_out.size() != 0){
		request.setAttribute("work_checkout",work_out);
	    for(int i = 0 ; i < work_out.size() ; i++){
		String a   = work_out.get(i).get("work_hours_time_work").toString();
		a  = a.substring(0,10);
		day2.add(a);	
		String work_avg_out_day = workHoursDAO.graph_workhours_out_avg(a);
		work_avg_out_day = work_avg_out_day.substring(0,5);
    	hours2.add(work_avg_out_day);		
	    }
	}
	    request.setAttribute("avg_day_in",day);
	    request.setAttribute("avg_hour_in",hours);

	    request.setAttribute("avg_day_out",day2);
	    request.setAttribute("avg_hour_out",hours2);
       List<String> work_avg_in = workHoursDAO.graph_workhours_avg_in(user,yearnow);
       List<String> work_avg_out = workHoursDAO.graph_workhours_avg_out(user,yearnow);
       
       List<String> work_avg_in_all = workHoursDAO.graph_workhours_avg_in(yearnow);
       List<String> work_avg_out_all = workHoursDAO.graph_workhours_avg_out(yearnow);
       String time_avg_in = work_avg_in.get(0);
       String time_avg_out = work_avg_out.get(0);
       
       String time_avg_in_all = work_avg_in_all.get(0);
       String time_avg_out_all = work_avg_out_all.get(0);
   	List<Map<String, Object>> userseq = userDAO.sequense();
	request.setAttribute("userseq", userseq);
      
       request.setAttribute("count_user",x[4]);
     
       request.setAttribute("avg_in",time_avg_in);
       request.setAttribute("avg_out",time_avg_out);
       
       request.setAttribute("avg_in_all",time_avg_in_all);
       request.setAttribute("avg_out_all",time_avg_out_all);
       
  
   	
   	//Bar Charts 
   
	List<Map<String, Object>> top10Users = leavesDAO.top10(year2,statusEnable);
	
   ArrayList<ArrayList<Map<String, Object>>> leaveBarcharts = new ArrayList<ArrayList<Map<String, Object>>>();
   	for(int i = 0 ; i < top10Users.size() ; i++){
   	
   		List<Map<String, Object>> test   = leavesDAO.searchCountbyUser((String) top10Users.get(i).get("user_id"),year2);
   		leaveBarcharts.add((ArrayList<Map<String, Object>>) test);
   	}
   	
    request.setAttribute("leaveBarcharts",leaveBarcharts);
    request.setAttribute("top10users",top10Users);
	List<LeaveType> typeLeave = leavetypeDAO.findAll_calendar();
	request.setAttribute("type_1", typeLeave.get(0).getLeaveTypeName());
	request.setAttribute("type_2", typeLeave.get(1).getLeaveTypeName());
	request.setAttribute("type_3", typeLeave.get(2).getLeaveTypeName());
	request.setAttribute("type_5", typeLeave.get(4).getLeaveTypeName());
   	
	// Leaves of Years
	ArrayList<ArrayList<Map<String, Object>>> leavesArraylist = new ArrayList<ArrayList<Map<String, Object>>>();
	
	for(int i = 1 ; i <= month_now; i++){
	   	
		List<Map<String, Object>> test   = leavesDAO.searchCountbyMonths(i,year2,statusEnable);
		leavesArraylist.add((ArrayList<Map<String, Object>>) test);
   		
   	}
	request.setAttribute("leavesArraylist",leavesArraylist);
	
 	flag_search_graph = "";
   	graph_year = "" ; //Global Variable
   	graph_month = "";
   	statusEnable = "1";
	return SUCCESS;
}
public String search_graph(){
	String user = request.getParameter("name");
	String month = request.getParameter("month");
	String year = request.getParameter("year");
	String enable = request.getParameter("enable");
	request.setAttribute("userId",user);
	flag_search_graph = "1";
	graph_year = year ; //Global Variable
	graph_month = month;
	statusEnable = enable;
	return SUCCESS;
}
public String open_BI(){
	return SUCCESS;
}

public String openleavesReport() throws Exception{
	int year2 = cal.get(Calendar.YEAR) ;
	
	try{
		String typeLeaves = request.getParameter("type");
		
		List<Map<String, Object>> Users = leavesDAO.findUserByyear(String.valueOf(year2));
		ArrayList<ArrayList<Map<String, Object>>> leavesArraylist = new ArrayList<ArrayList<Map<String, Object>>>();
		double[] total = new double[12];
		for(int j = 0 ; j< Users.size() ; j ++){
			 BigDecimal[] bg = new BigDecimal[12];
				List<Map<String, Object>> test = leavesDAO.ReportsByyear2(year2, (String)Users.get(j).get("user_id"));
				bg[0] =  (BigDecimal) test.get(0).get("sum");
				bg[1] =  (BigDecimal) test.get(1).get("sum");
				bg[2] =  (BigDecimal) test.get(2).get("sum");
				bg[3] =  (BigDecimal) test.get(3).get("sum");
				bg[4] =  (BigDecimal) test.get(4).get("sum");
				bg[5] =  (BigDecimal) test.get(5).get("sum");
				bg[6] =  (BigDecimal) test.get(6).get("sum");
				bg[7] =  (BigDecimal) test.get(7).get("sum");
				bg[8] =  (BigDecimal) test.get(8).get("sum");
				bg[9] =  (BigDecimal) test.get(9).get("sum");
				bg[10] =  (BigDecimal) test.get(10).get("sum");
				bg[11] =  (BigDecimal) test.get(11).get("sum");

				 total[0] +=  bg[0].doubleValue();
				 total[1] +=  bg[1].doubleValue();
				 total[2] +=  bg[2].doubleValue();
				 total[3] +=  bg[3].doubleValue();
				 total[4] +=  bg[4].doubleValue();
				 total[5] +=  bg[5].doubleValue();
				 total[6] +=  bg[6].doubleValue();
				 total[7] +=  bg[7].doubleValue(); 
				 total[8] +=  bg[8].doubleValue();
				 total[9] +=  bg[9].doubleValue();
				 total[10] +=  bg[10].doubleValue();
				 total[11] +=  bg[11].doubleValue();
			leavesArraylist.add((ArrayList<Map<String, Object>>) test);
	   		
		}
		List<LeaveType> type_leave = leavetypeDAO.findAll();
		request.setAttribute("leavetypelistChoice", type_leave);
		request.setAttribute("type", typeLeaves);
		request.setAttribute("totalallMonth",total);
		
		request.setAttribute("leavesArraylist",leavesArraylist);
		request.setAttribute("year",year2);
	request.setAttribute("leave",Users);
	}catch (Exception e){
		log.error(e);
	}

	return SUCCESS;
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
		List<Map<String, Object>> leave = leaveDAO.listoneperson(listbyuser); // เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน€เธ�โ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
		request.setAttribute("leave", leave);

		List<Map<String, Object>> leavenameList = leaveDAO.findLeave();
		request.setAttribute("leavenameList", leavenameList);
		request.setAttribute("leaveList", leaveDAO.findAll());
		request.setAttribute("leavetypeList", leavetypeDAO.findAll2());
		
		request.setAttribute("userList", userDAO.findAll());

		List<LeaveType> type_leave = leavetypeDAO.findAll();
		request.setAttribute("leavetypelistChoice", type_leave);
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
public String searchleave() {
	try {
		String userSelect = request.getParameter("name1");
		String leaveStatus = request.getParameter("appr");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");

		Timestamp start_date = DateUtil.changeYearStart(startdate);
		Timestamp end_date = DateUtil.changeYearEnd(enddate);
		if (!"All".equals(userSelect)) {   //selected user
			if (leaveStatus.equals("3")) {
				request.setAttribute("leave", leaveDAO.searchtable2(start_date, end_date, userSelect));
			} else {
				request.setAttribute("leave", leaveDAO.searchtable2(start_date, end_date, userSelect, leaveStatus));
			}
		}
		else {//not select user
			if (leaveStatus.equals("3")) { //all status
				request.setAttribute("leave", leaveDAO.searchtable(start_date, end_date, userSelect));
			} else {
				request.setAttribute("leave", leaveDAO.searchtable(start_date, end_date, userSelect, leaveStatus));
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
		request.setAttribute("leavetypelistChoice", type_leave);                //not in use because change to query by leavestatus
		
		return SUCCESS;
	} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
	}
}
public String searchleavesReport() throws Exception{			
	try{
		String year = request.getParameter("year");
		String typeLeaves = request.getParameter("type");
		
		List<Map<String, Object>> Users = leavesDAO.findUserByyear(year);
		ArrayList<ArrayList<Map<String, Object>>> leavesArraylist = new ArrayList<ArrayList<Map<String, Object>>>();
	
		double[] total = new double[12];
		for(int j = 0 ; j< Users.size() ; j ++){
			 BigDecimal[] bg = new BigDecimal[12];
				List<Map<String, Object>> test = leavesDAO.ReportsByyear(Integer.valueOf(year), Integer.valueOf(typeLeaves), (String)Users.get(j).get("user_id"));
				
				bg[0] =  (BigDecimal) test.get(0).get("sum");
				bg[1] =  (BigDecimal) test.get(1).get("sum");
				bg[2] =  (BigDecimal) test.get(2).get("sum");
				bg[3] =  (BigDecimal) test.get(3).get("sum");
				bg[4] =  (BigDecimal) test.get(4).get("sum");
				bg[5] =  (BigDecimal) test.get(5).get("sum");
				bg[6] =  (BigDecimal) test.get(6).get("sum");
				bg[7] =  (BigDecimal) test.get(7).get("sum");
				bg[8] =  (BigDecimal) test.get(8).get("sum");
				bg[9] =  (BigDecimal) test.get(9).get("sum");
				bg[10] =  (BigDecimal) test.get(10).get("sum");
				bg[11] =  (BigDecimal) test.get(11).get("sum");

				 total[0] +=  bg[0].doubleValue();
				 total[1] +=  bg[1].doubleValue();
				 total[2] +=  bg[2].doubleValue();
				 total[3] +=  bg[3].doubleValue();
				 total[4] +=  bg[4].doubleValue();
				 total[5] +=  bg[5].doubleValue();
				 total[6] +=  bg[6].doubleValue();
				 total[7] +=  bg[7].doubleValue(); 
				 total[8] +=  bg[8].doubleValue();
				 total[9] +=  bg[9].doubleValue();
				 total[10] +=  bg[10].doubleValue();
				 total[11] +=  bg[11].doubleValue();
			leavesArraylist.add((ArrayList<Map<String, Object>>) test);
	   		
		}
		List<LeaveType> type_leave = leavetypeDAO.findAll();
		request.setAttribute("leavetypelistChoice", type_leave);
		request.setAttribute("type", typeLeaves);
		request.setAttribute("totalallMonth",total);
		request.setAttribute("leavesArraylist",leavesArraylist);
		request.setAttribute("year",year);
		request.setAttribute("leave",Users);
	

	}catch (Exception e){
		log.error(e);
	}

	return SUCCESS;
}

public String searchleavesReportbyType() throws Exception{			
	try{
		String year = request.getParameter("year");
		String user = request.getParameter("user");
		String month = request.getParameter("month");
		 
		List<Map<String, Object>> Users = leavesDAO.ReportsType(year, user, month);
		  PrintWriter out = response.getWriter();
          JSONObject json = new JSONObject();
          
          List<String> jsonObj1 = new ArrayList<String>();
          List<String> jsonObj2 = new ArrayList<String>();


          for(int i = 0 ; i < Users.size() ; i++ ){
        	  jsonObj1.add((String) Users.get(i).get("name"));
        	  jsonObj2.add((String) Users.get(i).get("sum").toString());

        	  
          }

          JSONArray test = new JSONArray(jsonObj1);
          JSONArray test2 = new JSONArray(jsonObj2);

   json.put("name",test);
   json.put("sum",test2);

    out.print(json);
	out.flush();
	out.close();

	}catch (Exception e){
		log.error(e);
	}

	return SUCCESS;
}

public String openExpenseReport() throws Exception{
	int year2 = cal.get(Calendar.YEAR) ;
		try{
		
		List<Map<String, Object>> Users = expenseDAO.findUserbyYear(String.valueOf(year2));
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist = new ArrayList<ArrayList<Map<String, Object>>>();
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist2 = new ArrayList<ArrayList<Map<String, Object>>>();
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist3 = new ArrayList<ArrayList<Map<String, Object>>>();
		double[] total = new double[12];
		for(int j = 0 ; j< Users.size() ; j ++){
			 BigDecimal[] bg = new BigDecimal[12];
			List<Map<String, Object>> test =expenseDAO.ReportByuser_PAID(String.valueOf(year2), (String)Users.get(j).get("user_id"));
			reportArraylist.add((ArrayList<Map<String, Object>>) test);
			reportArraylist2.add((ArrayList<Map<String, Object>>) expenseDAO.ReportByuser_REJECT(String.valueOf(year2), (String)Users.get(j).get("user_id")));
			reportArraylist3.add((ArrayList<Map<String, Object>>) expenseDAO.ReportByuser_REQUEST(String.valueOf(year2), (String)Users.get(j).get("user_id")));
			
			bg[0] =  (BigDecimal) test.get(0).get("sum");
			bg[1] =  (BigDecimal) test.get(1).get("sum");
			bg[2] =  (BigDecimal) test.get(2).get("sum");
			bg[3] =  (BigDecimal) test.get(3).get("sum");
			bg[4] =  (BigDecimal) test.get(4).get("sum");
			bg[5] =  (BigDecimal) test.get(5).get("sum");
			bg[6] =  (BigDecimal) test.get(6).get("sum");
			bg[7] =  (BigDecimal) test.get(7).get("sum");
			bg[8] =  (BigDecimal) test.get(8).get("sum");
			bg[9] =  (BigDecimal) test.get(9).get("sum");
			bg[10] =  (BigDecimal) test.get(10).get("sum");
			bg[11] =  (BigDecimal) test.get(11).get("sum");

			 total[0] +=  bg[0].doubleValue();
			 total[1] +=  bg[1].doubleValue();
			 total[2] +=  bg[2].doubleValue();
			 total[3] +=  bg[3].doubleValue();
			 total[4] +=  bg[4].doubleValue();
			 total[5] +=  bg[5].doubleValue();
			 total[6] +=  bg[6].doubleValue();
			 total[7] +=  bg[7].doubleValue(); 
			 total[8] +=  bg[8].doubleValue();
			 total[9] +=  bg[9].doubleValue();
			 total[10] +=  bg[10].doubleValue();
			 total[11] +=  bg[11].doubleValue();
			
			
		}
		
		request.setAttribute("totalallMonth",total);
		request.setAttribute("leavesArraylist",reportArraylist);
		request.setAttribute("leavesArraylist2",reportArraylist2);
		request.setAttribute("leavesArraylist3",reportArraylist3);
		request.setAttribute("year",year2);
	request.setAttribute("expense",Users);
	}catch (Exception e){
		log.error(e);
	}

	return SUCCESS;
}
public String searcchExpenseReport() throws Exception{
	
		try{
		String year = request.getParameter("year");
		List<Map<String, Object>> Users = expenseDAO.findUserbyYear(String.valueOf(year));
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist = new ArrayList<ArrayList<Map<String, Object>>>();
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist2 = new ArrayList<ArrayList<Map<String, Object>>>();
		ArrayList<ArrayList<Map<String, Object>>>reportArraylist3 = new ArrayList<ArrayList<Map<String, Object>>>();
	
		double[] total = new double[12];
		for(int j = 0 ; j< Users.size() ; j ++){
			 BigDecimal[] bg = new BigDecimal[12];
			List<Map<String, Object>> test =expenseDAO.ReportByuser_PAID(String.valueOf(year), (String)Users.get(j).get("user_id"));
			reportArraylist.add((ArrayList<Map<String, Object>>) test);
			reportArraylist2.add((ArrayList<Map<String, Object>>) expenseDAO.ReportByuser_REJECT(String.valueOf(year), (String)Users.get(j).get("user_id")));
			reportArraylist3.add((ArrayList<Map<String, Object>>) expenseDAO.ReportByuser_REQUEST(String.valueOf(year), (String)Users.get(j).get("user_id")));
			
			bg[0] =  (BigDecimal) test.get(0).get("sum");
			bg[1] =  (BigDecimal) test.get(1).get("sum");
			bg[2] =  (BigDecimal) test.get(2).get("sum");
			bg[3] =  (BigDecimal) test.get(3).get("sum");
			bg[4] =  (BigDecimal) test.get(4).get("sum");
			bg[5] =  (BigDecimal) test.get(5).get("sum");
			bg[6] =  (BigDecimal) test.get(6).get("sum");
			bg[7] =  (BigDecimal) test.get(7).get("sum");
			bg[8] =  (BigDecimal) test.get(8).get("sum");
			bg[9] =  (BigDecimal) test.get(9).get("sum");
			bg[10] =  (BigDecimal) test.get(10).get("sum");
			bg[11] =  (BigDecimal) test.get(11).get("sum");

			 total[0] +=  bg[0].doubleValue();
			 total[1] +=  bg[1].doubleValue();
			 total[2] +=  bg[2].doubleValue();
			 total[3] +=  bg[3].doubleValue();
			 total[4] +=  bg[4].doubleValue();
			 total[5] +=  bg[5].doubleValue();
			 total[6] +=  bg[6].doubleValue();
			 total[7] +=  bg[7].doubleValue(); 
			 total[8] +=  bg[8].doubleValue();
			 total[9] +=  bg[9].doubleValue();
			 total[10] +=  bg[10].doubleValue();
			 total[11] +=  bg[11].doubleValue();
			
			
		}
		
		request.setAttribute("totalallMonth",total);
		request.setAttribute("leavesArraylist",reportArraylist);
		request.setAttribute("leavesArraylist2",reportArraylist2);
		request.setAttribute("leavesArraylist3",reportArraylist3);
		request.setAttribute("year",year);
	request.setAttribute("expense",Users);
	}catch (Exception e){
		log.error(e);
	}

	return SUCCESS;
}

public String openLeaveCharts() throws Exception{
		try {
			//String user = request.getParameter("userId");
			//log.info(user);
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			
			List<Map<String, Object>> leaveuser = leaveDAO.findleaveByuser(user);
			List<LeaveType> leavetype = leavetypeDAO.findAll();
			String[] typeleave = new String[leavetype.size()];
			int[] countleave1 = new int[leavetype.size()];
			int[] countleave2 = new int[leavetype.size()];
			int[] countleave3 = new int[leavetype.size()];
			int[] countleave4 = new int[leavetype.size()];
			int[] countleave5 = new int[leavetype.size()];
			String[] indexleave = new String[leavetype.size()];
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			String cutshortdate = datenow.substring(0, 7);
			String y = datenow.substring(0,4);
			String m = datenow.substring(5,7);
			String d = datenow.substring(8,10);
			int getYear = Integer.parseInt(y);
			int getMouth = Integer.parseInt(m)-1;
			int getDay = Integer.parseInt(d)-1;
			Calendar cal = Calendar.getInstance();
			cal.set(getYear,getMouth,getDay);
			int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
			String condatestart = cutshortdate+"-01";//current
			String timelengstart;
			String timelengend;
			String cutdatestart;
			String cutdateend;
			String idleave;
			for(int i=0;i<leavetype.size();i++) {
				typeleave[i]= leavetype.get(i).getLeaveTypeName();
				countleave1[i] = 0;
				countleave2[i] = 0;
				countleave3[i] = 0;
				countleave4[i] = 0;
				countleave5[i] = 0;
				indexleave[i] = leavetype.get(i).getLeaveTypeId();
				
			}
			for(int j=0;j<leavetype.size();j++) {
				for (int i = 0; i < leaveuser.size(); i++) {
					 timelengstart = leaveuser.get(i).get("start_date").toString();
					 timelengend = leaveuser.get(i).get("end_date").toString();
					 cutdatestart = timelengstart.substring(0,10);//2017-03-25
					 cutdateend= timelengend.substring(0,10);
					 idleave = leaveuser.get(i).get("leave_type_id").toString();//get id type leave
					 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
							 || cutdatestart.compareTo(condatestart)==0) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 Date oldDate = myFormat.parse(cutdatestart);
					     Date newDate = myFormat.parse(cutdateend);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdatestart.substring(8,10);
						 String getmothyear = cutdatestart.substring(0,7);
						 //set final day
						 String startdate = getmothyear+"-"+dd;
						 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
						 Date oldDate = myFormat.parse(startdate);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
						 
					 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdateend.substring(8,10);
						 String getmothyear = cutdateend.substring(0,7);
						 //set final day
						 int  nameday = Integer.parseInt(dd);
						 String enddate = getmothyear+"-"+Integer.toString(nameday);
						 Date oldDate = myFormat.parse(condatestart);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }
					 
				}
			}
			//set date 
			java.util.Date datetime = new java.util.Date();
			request.setAttribute("datetime",datetime);
			request.setAttribute("newname",user);
			request.setAttribute("sizearray",leavetype.size()-1);
			request.setAttribute("nametypeleave",typeleave);
			request.setAttribute("countleave5",countleave5);
			request.setAttribute("countleave4",countleave4);
			request.setAttribute("countleave3",countleave3);
			request.setAttribute("countleave2",countleave2);
			request.setAttribute("countleave1",countleave1);
			request.setAttribute("indexleave",indexleave);
		    return SUCCESS;
		}
		catch(Exception e)
		{
		   return ERROR;
		}
	}
public String searchLeaveCharts() throws Exception {
	try {
		//String user = request.getParameter("userId");
		//log.info(user);
		String user = request.getParameter("nameuser");
		request.setAttribute("userId",user);
		List<Map<String, Object>> userseq = userDAO.sequense();
		request.setAttribute("userseq", userseq);
		
		
		List<Map<String, Object>> leaveuser = leaveDAO.findleaveByuser(user);
		List<LeaveType> leavetype = leavetypeDAO.findAll();
		String[] typeleave = new String[leavetype.size()];
		int[] countleave1 = new int[leavetype.size()];
		int[] countleave2 = new int[leavetype.size()];
		int[] countleave3 = new int[leavetype.size()];
		int[] countleave4 = new int[leavetype.size()];
		int[] countleave5 = new int[leavetype.size()];
		String[] indexleave = new String[leavetype.size()];
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String datenow = year+"-"+month+"-01";
		String cutshortdate = datenow.substring(0, 7);
		String y = datenow.substring(0,4);
		String m = datenow.substring(5,7);
		String d = datenow.substring(8,10);
		int getYear = Integer.parseInt(y);
		int getMouth = Integer.parseInt(m)-1;
		int getDay = Integer.parseInt(d)-1;
		Calendar cal = Calendar.getInstance();
		cal.set(getYear,getMouth,getDay);
		int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
		String condatestart = cutshortdate+"-01";//current
		String timelengstart;
		String timelengend;
		String cutdatestart;
		String cutdateend;
		String idleave;
		for(int i=0;i<leavetype.size();i++) {
			typeleave[i]= leavetype.get(i).getLeaveTypeName();
			countleave1[i] = 0;
			countleave2[i] = 0;
			countleave3[i] = 0;
			countleave4[i] = 0;
			countleave5[i] = 0;
			indexleave[i] = leavetype.get(i).getLeaveTypeId();
			
		}
		for(int j=0;j<leavetype.size();j++) {
			for (int i = 0; i < leaveuser.size(); i++) {
				 timelengstart = leaveuser.get(i).get("start_date").toString();
				 timelengend = leaveuser.get(i).get("end_date").toString();
				 cutdatestart = timelengstart.substring(0,10);//2017-03-25
				 cutdateend= timelengend.substring(0,10);
				 idleave = leaveuser.get(i).get("leave_type_id").toString();//get id type leave
				 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
						 || cutdatestart.compareTo(condatestart)==0) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 Date oldDate = myFormat.parse(cutdatestart);
				     Date newDate = myFormat.parse(cutdateend);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
				     for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
							 countleave1[j]+=1;
						 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
							 countleave2[j]+=1;
						 }
						 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
							 countleave3[j]+=1;
						 }
						 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
							 countleave4[j]+=1;
						 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
							 countleave5[j]+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdatestart.substring(8,10);
					 String getmothyear = cutdatestart.substring(0,7);
					 //set final day
					 String startdate = getmothyear+"-"+dd;
					 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
					 Date oldDate = myFormat.parse(startdate);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
				     for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
							 countleave1[j]+=1;
						 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
							 countleave2[j]+=1;
						 }
						 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
							 countleave3[j]+=1;
						 }
						 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
							 countleave4[j]+=1;
						 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
							 countleave5[j]+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
					 
				 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdateend.substring(8,10);
					 String getmothyear = cutdateend.substring(0,7);
					 //set final day
					 int  nameday = Integer.parseInt(dd);
					 String enddate = getmothyear+"-"+Integer.toString(nameday);
					 Date oldDate = myFormat.parse(condatestart);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
				     for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
							 countleave1[j]+=1;
						 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
							 countleave2[j]+=1;
						 }
						 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
							 countleave3[j]+=1;
						 }
						 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
							 countleave4[j]+=1;
						 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
							 countleave5[j]+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }
				 
			}
		}
		//set date 
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date datetime = formatter.parse(datenow);
		request.setAttribute("datetime",datetime);
		request.setAttribute("newname",user);
		request.setAttribute("sizearray",leavetype.size()-1);
		request.setAttribute("nametypeleave",typeleave);
		request.setAttribute("countleave5",countleave5);
		request.setAttribute("countleave4",countleave4);
		request.setAttribute("countleave3",countleave3);
		request.setAttribute("countleave2",countleave2);
		request.setAttribute("countleave1",countleave1);
		request.setAttribute("indexleave",indexleave);
	    return SUCCESS;
	}
	catch(Exception e)
	{
		log.info(e.getMessage());
	   return ERROR;
	}
}

// Leave Chart Report
	public String openRELeaveCharts() throws Exception{
		
		  String user = request.getParameter("userId");
		  int month = cal.get(Calendar.MONTH) + 1 ;
		  int year2 = cal.get(Calendar.YEAR) ;
		  int sort2 = 0;
		  request.setAttribute("userId",user);
		  request.setAttribute("year1",year2);
		  request.setAttribute("month", month);
		  request.setAttribute("sort", sort2);
		  //set graph to show
		  if(flag_search_graph != ""){ 
			  if( graph_month.equals("all_M")) {   //search in each year that choose month is All
				  	year2 = Integer.valueOf(graph_year);
				  	sort2 = Integer.valueOf(sortz);
					request.setAttribute("userId",user);
					request.setAttribute("month1",graph_month);
					request.setAttribute("year1",graph_year);
					request.setAttribute("sortAl",sortz);
					request.setAttribute("flag","1");
					month = 0 ;
			  }else if (!graph_month.equals("") && !graph_year.equals("all_Y")){ 	//search with each month of year
					month = Integer.valueOf(graph_month);
					year2 = Integer.valueOf(graph_year);
					sort2 = Integer.valueOf(sortz);
					request.setAttribute("userId",user);
					request.setAttribute("month1",graph_month);
					request.setAttribute("year1",graph_year);
					request.setAttribute("sortAl",sortz);
					request.setAttribute("flag","1");
			  }else{	 // search all of year
				  	year2 = 0;
				  	sort2 = Integer.valueOf(sortz);
				  	request.setAttribute("userId",user);
					request.setAttribute("month1",graph_month);
					request.setAttribute("year1",graph_year);
					request.setAttribute("sortAl",sortz);
					request.setAttribute("flag","1");
			  }
			  request.setAttribute("statusEnable",statusEnable);
		  }
			//Bar Charts 
			List<Map<String, Object>> userLeaves = leavesDAO.LeaveAll(month,year2,statusEnable,sort2);
		    ArrayList<ArrayList<Map<String, Object>>> leaveBarcharts = new ArrayList<ArrayList<Map<String, Object>>>();
		   	for(int i = 0 ; i < userLeaves.size() ; i++){
		   		List<Map<String, Object>> test   = leavesDAO.LeaveTypename((String) userLeaves.get(i).get("user_id"),year2,month);
		   		leaveBarcharts.add((ArrayList<Map<String, Object>>) test);
		   	}
		    request.setAttribute("leaveBarcharts",leaveBarcharts);
		    request.setAttribute("userLeaves",userLeaves);
		    //set type leave name	
			List<LeaveType> typeLeave = leavetypeDAO.findAll_calendar();
			request.setAttribute("type_1", typeLeave.get(0).getLeaveTypeName());
			request.setAttribute("type_2", typeLeave.get(1).getLeaveTypeName());
			request.setAttribute("type_3", typeLeave.get(2).getLeaveTypeName());
			request.setAttribute("type_4", typeLeave.get(3).getLeaveTypeName());
			request.setAttribute("type_5", typeLeave.get(4).getLeaveTypeName());
			flag_search_graph = "";
		   	graph_year = "" ; //Global Variable
		   	graph_month = "";
		   	statusEnable = "1";
			return SUCCESS;
		}
	public String searchLeaveChartsReport() {
		String user = request.getParameter("name");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String enable = request.getParameter("enable");
		String sort = request.getParameter("sort");
		request.setAttribute("userId",user);
		request.setAttribute("year", year);
		request.setAttribute("month",month);
		request.setAttribute("sort",sort);
		flag_search_graph = "1";
		graph_year = year ; //Global Variable
		graph_month = month;
		sortz= sort;
		return SUCCESS;
	}
	
public String leaveChartsAll() throws Exception{
		try {
			//String user = request.getParameter("userId");
			//log.info(user);
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			
			List<Map<String, Object>> leaveuser = leaveDAO.findleaveAll();
			List<LeaveType> leavetype = leavetypeDAO.findAll();
			String[] typeleave = new String[leavetype.size()];
			int[] countleave1 = new int[leavetype.size()];
			int[] countleave2 = new int[leavetype.size()];
			int[] countleave3 = new int[leavetype.size()];
			int[] countleave4 = new int[leavetype.size()];
			int[] countleave5 = new int[leavetype.size()];
			String[] indexleave = new String[leavetype.size()];
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			String cutshortdate = datenow.substring(0, 7);
			String y = datenow.substring(0,4);
			String m = datenow.substring(5,7);
			String d = datenow.substring(8,10);
			int getYear = Integer.parseInt(y);
			int getMouth = Integer.parseInt(m)-1;
			int getDay = Integer.parseInt(d)-1;
			Calendar cal = Calendar.getInstance();
			cal.set(getYear,getMouth,getDay);
			int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
			String condatestart = cutshortdate+"-01";//current
			String timelengstart;
			String timelengend;
			String cutdatestart;
			String cutdateend;
			String idleave;
			for(int i=0;i<leavetype.size();i++) {
				typeleave[i]= leavetype.get(i).getLeaveTypeName();
				countleave1[i] = 0;
				countleave2[i] = 0;
				countleave3[i] = 0;
				countleave4[i] = 0;
				countleave5[i] = 0;
				indexleave[i] = leavetype.get(i).getLeaveTypeId();
				
			}
			for(int j=0;j<leavetype.size();j++) {
				for (int i = 0; i < leaveuser.size(); i++) {
					 timelengstart = leaveuser.get(i).get("start_date").toString();
					 timelengend = leaveuser.get(i).get("end_date").toString();
					 cutdatestart = timelengstart.substring(0,10);//2017-03-25
					 cutdateend= timelengend.substring(0,10);
					 idleave = leaveuser.get(i).get("leave_type_id").toString();//get id type leave
					 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
							 || cutdatestart.compareTo(condatestart)==0) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 Date oldDate = myFormat.parse(cutdatestart);
					     Date newDate = myFormat.parse(cutdateend);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdatestart.substring(8,10);
						 String getmothyear = cutdatestart.substring(0,7);
						 //set final day
						 String startdate = getmothyear+"-"+dd;
						 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
						 Date oldDate = myFormat.parse(startdate);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
						 
					 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdateend.substring(8,10);
						 String getmothyear = cutdateend.substring(0,7);
						 //set final day
						 int  nameday = Integer.parseInt(dd);
						 String enddate = getmothyear+"-"+Integer.toString(nameday);
						 Date oldDate = myFormat.parse(condatestart);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }
					 
				}
			}
			//set date 
			java.util.Date datetime = new java.util.Date();
			request.setAttribute("datetime",datetime);
			request.setAttribute("newname",user);
			request.setAttribute("sizearray",leavetype.size()-1);
			request.setAttribute("nametypeleave",typeleave);
			request.setAttribute("countleave5",countleave5);
			request.setAttribute("countleave4",countleave4);
			request.setAttribute("countleave3",countleave3);
			request.setAttribute("countleave2",countleave2);
			request.setAttribute("countleave1",countleave1);
			request.setAttribute("indexleave",indexleave);
		    return SUCCESS;
		}
		catch(Exception e)
		{
		   return ERROR;
		}
	}
public String searchLeaveChartsAll() throws Exception{
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			
			List<Map<String, Object>> leaveuser = leaveDAO.findleaveAll();
			List<LeaveType> leavetype = leavetypeDAO.findAll();
			String[] typeleave = new String[leavetype.size()];
			int[] countleave1 = new int[leavetype.size()];
			int[] countleave2 = new int[leavetype.size()];
			int[] countleave3 = new int[leavetype.size()];
			int[] countleave4 = new int[leavetype.size()];
			int[] countleave5 = new int[leavetype.size()];
			String[] indexleave = new String[leavetype.size()];
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String datenow = year+"-"+month+"-01";
			String cutshortdate = datenow.substring(0, 7);
			String y = datenow.substring(0,4);
			String m = datenow.substring(5,7);
			String d = datenow.substring(8,10);
			int getYear = Integer.parseInt(y);
			int getMouth = Integer.parseInt(m)-1;
			int getDay = Integer.parseInt(d)-1;
			Calendar cal = Calendar.getInstance();
			cal.set(getYear,getMouth,getDay);
			int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
			String condatestart = cutshortdate+"-01";//current
			String timelengstart;
			String timelengend;
			String cutdatestart;
			String cutdateend;
			String idleave;
			for(int i=0;i<leavetype.size();i++) {
				typeleave[i]= leavetype.get(i).getLeaveTypeName();
				countleave1[i] = 0;
				countleave2[i] = 0;
				countleave3[i] = 0;
				countleave4[i] = 0;
				countleave5[i] = 0;
				indexleave[i] = leavetype.get(i).getLeaveTypeId();
				
			}
			for(int j=0;j<leavetype.size();j++) {
				for (int i = 0; i < leaveuser.size(); i++) {
					 timelengstart = leaveuser.get(i).get("start_date").toString();
					 timelengend = leaveuser.get(i).get("end_date").toString();
					 cutdatestart = timelengstart.substring(0,10);//2017-03-25
					 cutdateend= timelengend.substring(0,10);
					 idleave = leaveuser.get(i).get("leave_type_id").toString();//get id type leave
					 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
							 || cutdatestart.compareTo(condatestart)==0) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 Date oldDate = myFormat.parse(cutdatestart);
					     Date newDate = myFormat.parse(cutdateend);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdatestart.substring(8,10);
						 String getmothyear = cutdatestart.substring(0,7);
						 //set final day
						 String startdate = getmothyear+"-"+dd;
						 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
						 Date oldDate = myFormat.parse(startdate);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
						 
					 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
							 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
					 {
						 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						 String dd = cutdateend.substring(8,10);
						 String getmothyear = cutdateend.substring(0,7);
						 //set final day
						 int  nameday = Integer.parseInt(dd);
						 String enddate = getmothyear+"-"+Integer.toString(nameday);
						 Date oldDate = myFormat.parse(condatestart);
					     Date newDate = myFormat.parse(enddate);
					     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
					     Calendar cal1 = Calendar.getInstance();
				    	 cal1.setTime(oldDate);
				    	 int dayofmonth1;
					     for(int firstday=1;firstday<=diffInDays;firstday++) {
							 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
							 if(dayofmonth1==1 && idleave.equals(indexleave[j])) {
								 countleave1[j]+=1;
							 }else if(dayofmonth1==2 && idleave.equals(indexleave[j])) {
								 countleave2[j]+=1;
							 }
							 else if(dayofmonth1==3 && idleave.equals(indexleave[j])) {
								 countleave3[j]+=1;
							 }
							 else if(dayofmonth1==4 && idleave.equals(indexleave[j])) {
								 countleave4[j]+=1;
							 }else if(dayofmonth1==5 && idleave.equals(indexleave[j])) {
								 countleave5[j]+=1;
							 }
							 cal1.add( Calendar.DATE, 1 );
					     }
					 }
					 
				}
			}
			//set date 
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date datetime = formatter.parse(datenow);
			request.setAttribute("datetime",datetime);
			request.setAttribute("newname",user);
			request.setAttribute("sizearray",leavetype.size()-1);
			request.setAttribute("nametypeleave",typeleave);
			request.setAttribute("countleave5",countleave5);
			request.setAttribute("countleave4",countleave4);
			request.setAttribute("countleave3",countleave3);
			request.setAttribute("countleave2",countleave2);
			request.setAttribute("countleave1",countleave1);
			request.setAttribute("indexleave",indexleave);
		    return SUCCESS;
		}
		catch(Exception e)
		{
		   return ERROR;
		}
	}
	
public String openExpenseCharts() throws Exception{
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			
			Date date = new Date();
			/* JRE 1.8 
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int month = localDate.getMonthValue();
			int year = localDate.getYear();
			*/
			int month = date.getMonth();
			int year = date.getYear();
			
			List<Map<String, Object>> expenseMonth = expenseDAO.findByYearMonth(year,month);
			
			int monday = 0, tueday = 0, wenday = 0, thuday = 0, friday = 0,  monamount = 0, tueamount = 0, wenamount = 0, thuamount = 0, friamount = 0;
			
			
			for (int i = 0; i < expenseMonth.size(); i++) {
				String timeleng = expenseMonth.get(i).get("dt_start").toString();
				log.info(timeleng);
				BigDecimal samount = (BigDecimal)expenseMonth.get(i).get("amount");
				log.info(samount);
				int amount = samount.intValue();
				String getYear = timeleng.substring(0,4);
				String getMouth = timeleng.substring(5,7);
				String getDay = timeleng.substring(8,10);
				
				int y = Integer.parseInt(getYear);
				int m = Integer.parseInt(getMouth)-1;
				int d = Integer.parseInt(getDay)-1;
				//check number day of week
				Calendar cal = Calendar.getInstance();
				cal.set(y,m,d);
				int day = cal.get(Calendar.DAY_OF_WEEK);
				
					if(day==1) {
						monday++ ;
						monamount+=amount ;
					}else if(day==2) {
						tueday++ ;
						tueamount+=amount;
					}else if(day==3) {
						wenday++;
						wenamount+=amount;
					}else if(day==4) {
						thuday++;
						thuamount+=amount;
					}else if(day==5) {
						friday++;
						friamount+=amount;
					}
			}
			java.util.Date datetime = new java.util.Date();
			request.setAttribute("datetime",datetime);
			request.setAttribute("monday",monday);
			request.setAttribute("tueday",tueday);
			request.setAttribute("wenday",wenday);
			request.setAttribute("thuday",thuday);
			request.setAttribute("friday",friday);
			request.setAttribute("monamount",monamount);
			request.setAttribute("tueamount",tueamount);
			request.setAttribute("wenamount",wenamount);
			request.setAttribute("thuamount",thuamount);
			request.setAttribute("friamount",friamount);
			return SUCCESS;
		} catch(Exception e) {
			return ERROR;
		}
	}
public String search_graphexpense() throws Exception{
		try {
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String year1 = request.getParameter("year");
		String month1 = request.getParameter("month");
		String datenow = year1+"-"+month1+"-01";
		
		List<Map<String, Object>> expenseMonth = expenseDAO.findByYearMonth(year,month);
		log.info(expenseMonth.size());
		
		int monday = 0, tueday = 0, wenday = 0, thuday = 0, friday = 0, monamount = 0, tueamount = 0, wenamount = 0, thuamount = 0, friamount = 0;
				
		for (int i = 0; i < expenseMonth.size(); i++) {
			String timeleng = expenseMonth.get(i).get("dt_start").toString();
			log.info(timeleng);
			BigDecimal samount = (BigDecimal)expenseMonth.get(i).get("amount");
			log.info(samount);
			int amount = samount.intValue();
			
			String getYear = timeleng.substring(0,4);
			String getMouth = timeleng.substring(5,7);
			String getDay = timeleng.substring(8,10);
			
			int y = Integer.parseInt(getYear);
			int m = Integer.parseInt(getMouth)-1;
			int d = Integer.parseInt(getDay)-1;
			//check number day of week
			Calendar cal = Calendar.getInstance();
			cal.set(y,m,d);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
				if(day==1) {
					monday++ ;
					monamount+=amount ;
				}else if(day==2) {
					tueday++ ;
					tueamount+=amount ;
				}else if(day==3) {
					wenday++;
					wenamount+=amount ;
				}else if(day==4) {
					thuday++;
					thuamount+=amount ;
				}else if(day==5) {
					friday++;
					friamount+=amount ;
				}
		}
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date datetime = formatter.parse(datenow);
		request.setAttribute("datetime",datetime);
		request.setAttribute("monday",monday);
		request.setAttribute("tueday",tueday);
		request.setAttribute("wenday",wenday);
		request.setAttribute("thuday",thuday);
		request.setAttribute("friday",friday);
		request.setAttribute("monamount",monamount);
		request.setAttribute("tueamount",tueamount);
		request.setAttribute("wenamount",wenamount);
		request.setAttribute("thuamount",thuamount);
		request.setAttribute("friamount",friamount);
		return SUCCESS;
		}
		catch(Exception e)
		{
		   return ERROR;
		}
	}
public String holidayCharts() throws Exception{
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			int monday = 0, tueday = 0, wenday = 0, thuday = 0, friday = 0;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			String cutshortdate = datenow.substring(0, 7);
			String y = datenow.substring(0,4);
			String m = datenow.substring(5,7);
			String d = datenow.substring(8,10);
			int getYear = Integer.parseInt(y);
			int getMouth = Integer.parseInt(m)-1;
			int getDay = Integer.parseInt(d)-1;
			Calendar cal = Calendar.getInstance();
			cal.set(getYear,getMouth,getDay);
			int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
			String condatestart = cutshortdate+"-01";//current
			String timelengstart;
			String timelengend;
			String cutdatestart;
			String cutdateend;
			List<Map<String, Object>> holiday = holidayDAO.findAll1();
			for(int i=0;i<holiday.size();i++) {
				timelengstart =holiday.get(i).get("start_date").toString();
				timelengend = holiday.get(i).get("end_date").toString();
				cutdatestart = timelengstart.substring(0,10);//2017-03-25
				cutdateend= timelengend.substring(0,10);
				 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
						 || cutdatestart.compareTo(condatestart)==0) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 Date oldDate = myFormat.parse(cutdatestart);
				     Date newDate = myFormat.parse(cutdateend);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
				     for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdatestart.substring(8,10);
					 String getmothyear = cutdatestart.substring(0,7);
					 //set final day
					 String startdate = getmothyear+"-"+dd;
					 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
					 Date oldDate = myFormat.parse(startdate);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
			    	 for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
					 
				 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdateend.substring(8,10);
					 String getmothyear = cutdateend.substring(0,7);
					 //set final day
					 int  nameday = Integer.parseInt(dd);
					 String enddate = getmothyear+"-"+Integer.toString(nameday);
					 Date oldDate = myFormat.parse(condatestart);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
			    	 for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }
			}
	    	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date datetime = formatter.parse(datenow);
			request.setAttribute("datetime",datetime);
			request.setAttribute("monday",monday);
			request.setAttribute("tueday",tueday);
			request.setAttribute("wenday",wenday);
			request.setAttribute("thuday",thuday);
			request.setAttribute("thuday",friday);
			return SUCCESS;
		}catch(Exception e) {
			log.info(e.getMessage());
			return ERROR;
		}
	}
public String holidayChartsSearch() throws Exception{
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String user = ur.getId();
			request.setAttribute("userId",user);
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			int monday = 0, tueday = 0, wenday = 0, thuday = 0, friday = 0;
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String datenow = year+"-"+month+"-01";
			String cutshortdate = datenow.substring(0, 7);
			String y = datenow.substring(0,4);
			String m = datenow.substring(5,7);
			String d = datenow.substring(8,10);
			int getYear = Integer.parseInt(y);
			int getMouth = Integer.parseInt(m)-1;
			int getDay = Integer.parseInt(d)-1;
			Calendar cal = Calendar.getInstance();
			cal.set(getYear,getMouth,getDay);
			int dayofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String condateend =  cutshortdate+"-"+Integer.toString(dayofmonth);//เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
			String condatestart = cutshortdate+"-01";//current
			String timelengstart;
			String timelengend;
			String cutdatestart;
			String cutdateend;
			List<Map<String, Object>> holiday = holidayDAO.findAll1();
			for(int i=0;i<holiday.size();i++) {
				timelengstart =holiday.get(i).get("start_date").toString();
				timelengend = holiday.get(i).get("end_date").toString();
				cutdatestart = timelengstart.substring(0,10);//2017-03-25
				cutdateend= timelengend.substring(0,10);
				 if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 &&  (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)
						 || cutdatestart.compareTo(condatestart)==0) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 Date oldDate = myFormat.parse(cutdatestart);
				     Date newDate = myFormat.parse(cutdateend);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
				     for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }else if((cutdatestart.compareTo(condatestart)>0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)>0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdatestart.substring(8,10);
					 String getmothyear = cutdatestart.substring(0,7);
					 //set final day
					 String startdate = getmothyear+"-"+dd;
					 String enddate = getmothyear+"-"+Integer.toString(dayofmonth);
					 Date oldDate = myFormat.parse(startdate);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
			    	 for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
					 
				 }else if((cutdatestart.compareTo(condatestart)<0 && cutdatestart.compareTo(condateend)<0) && //เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ� เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ 1 เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌโ€�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�โ�ฌ๏ฟฝเน€เธ�โ�ฌเน€เธ�๏ฟฝเน�๏ฟฝเธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�เน€เธ�โ�ฌเน€เธ�๏ฟฝเน�เธ�เธ�
						 (cutdateend.compareTo(condatestart)>0 && cutdateend.compareTo(condateend)<0)) 
				 {
					 SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					 String dd = cutdateend.substring(8,10);
					 String getmothyear = cutdateend.substring(0,7);
					 //set final day
					 int  nameday = Integer.parseInt(dd);
					 String enddate = getmothyear+"-"+Integer.toString(nameday);
					 Date oldDate = myFormat.parse(condatestart);
				     Date newDate = myFormat.parse(enddate);
				     int diffInDays = (int)( (newDate.getTime() - oldDate.getTime())/ (1000 * 60 * 60 * 24) )+1;
				     Calendar cal1 = Calendar.getInstance();
			    	 cal1.setTime(oldDate);
			    	 int dayofmonth1;
			    	 for(int firstday=1;firstday<=diffInDays;firstday++) {
						 dayofmonth1 = cal1.get(Calendar.DAY_OF_WEEK)-1;
						 if(dayofmonth1==1) {
							 monday+=1;
						 }else if(dayofmonth1==2) {
							 tueday+=1;
						 }
						 else if(dayofmonth1==3) {
							 wenday+=1;
						 }
						 else if(dayofmonth1==4) {
							 thuday+=1;
						 }else if(dayofmonth1==5) {
							 friday+=1;
						 }
						 cal1.add( Calendar.DATE, 1 );
				     }
				 }
			}
	    	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date datetime = formatter.parse(datenow);
			request.setAttribute("datetime",datetime);
			request.setAttribute("monday",monday);
			request.setAttribute("tueday",tueday);
			request.setAttribute("wenday",wenday);
			request.setAttribute("thuday",thuday);
			request.setAttribute("friday",friday);
			return SUCCESS;
		}catch(Exception e) {
			log.info(e.getMessage());
			return ERROR;
		}
	}
	public String checkinOnTime() throws Exception{
		
		Timestamp today = DateUtil.getCurrentTime();
		String now = today.toString(); 
		String[] date_now = now.split("-");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String limit = request.getParameter("limit");
		
		List<Map<String, Object>> status = workHoursDAO.checkStatusCheckIn(month, year, limit);
		try {
			request.setAttribute("status", status);
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("limit", limit);
		return SUCCESS;
		}catch(Exception e) {
			log.info(e.getMessage());
			return ERROR;
		}
	}
public String checkinLate() throws Exception{
		
		Timestamp today = DateUtil.getCurrentTime();
		String now = today.toString(); 
		String[] date_now = now.split("-");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String limit = request.getParameter("limit");
		
		List<Map<String, Object>> status = workHoursDAO.checkStatusLate(month, year, limit);
		try {
			request.setAttribute("status", status);
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("limit", limit);
		return SUCCESS;
		}catch(Exception e) {
			log.info(e.getMessage());
			return ERROR;
		}
	}

public String searchTravelExpReport() throws Exception{
	try {
		// get value from form
		User ur = (User) request.getSession().getAttribute("onlineUser");
		String userLogin = ur.getId();
		String user = request.getParameter("name");
		String type = request.getParameter("type");
		int year = Integer.parseInt(request.getParameter("year"));		
		
		// update search bar
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String yearnow = timeStamp.substring(0, 4);
		request.setAttribute("type", type);
		request.setAttribute("yearnow", yearnow);
		request.setAttribute("year", year);
		List<Map<String, Object>> expType = expTravelTypeDAO.findAllType();
		request.setAttribute("exptype", expType);
		List<Map<String, Object>> userseq = userDAO.sequense();
		request.setAttribute("userseq", userseq);
		request.setAttribute("userSelect", user);
		
		//  search action 
		String userSearch = user ;
		String typeSearch = type ;
		List<Map<String, Object>> topAmount ;
		List<Map<String, Object>> type2 ;
		ArrayList<ArrayList<Map<String, Object>>> expBarcharts = new ArrayList<ArrayList<Map<String, Object>>>();
		
		if (user.equals("All")) { 
			userSearch = "";
				if ( type.equals("0")) { 
					typeSearch = "" ;
					topAmount = expenseDAO.findAmountByStatus("P","top",year);
					type2 = expTravelTypeDAO.findAllType();
					request.setAttribute("expTypeChart", type2);
				} else { 	
					topAmount = expenseDAO.findAmountBy("" , type, "P" ,"top",year);
					type2 = expTravelTypeDAO.findByExpId(type);
					request.setAttribute("expTypeChart", type2);
				}
				//Set Graph Category Field & Data
		   	for(int i = 0 ; i < topAmount.size() ; i++){
		   		List<Map<String, Object>> test   = expenseDAO.findAmountBy((String) topAmount.get(i).get("user_id"),"","P","all",year);
		   		expBarcharts.add((ArrayList<Map<String, Object>>) test);
		   	}
 			request.setAttribute("top10P", topAmount);

			}
			else { 
				if ( type.equals("0")) { 
					typeSearch = "";
					topAmount = expenseDAO.findAmountBy(user , "", "P" ,"all",year);
					type2 = expTravelTypeDAO.findAllType();
					
	 				//Set Graph Category Field & Data
				   	for(int i = 0 ; i < topAmount.size() ; i++){
						BigInteger expId =	((BigInteger)topAmount.get(i).get("exp_travel_type_id"));
						String str1 =  expId.toString();
				   		List<Map<String, Object>> test   = expenseDAO.findAmountBy((String) topAmount.get(i).get("user_id"),str1, year ,"type");
				   		expBarcharts.add((ArrayList<Map<String, Object>>) test);
				   	}
				   	
					List<Map<String, Object>> typeList = expenseDAO.findFeildAsUserId(user, "", year, 0,"findType");
		 			request.setAttribute("top10P", typeList);
					request.setAttribute("expTypeChart", typeList);

				} else { 	
					// Graph compare year by year 
					topAmount = expenseDAO.findFeildAsUserId(user, type, 0 , year,"");
					type2 = expTravelTypeDAO.findByExpId(type);
					request.setAttribute("expTypeChart", type2);
					List<Map<String, Object>> yearList = expenseDAO.findFeildAsUserId(user, type, 0  , year,"findYear");
		 			request.setAttribute("top10P", yearList);

		 		
	 				//Set Graph Category Field & Data
				   	for(int i = 0 ; i < topAmount.size() ; i++){
				   		Integer year2 = (Integer) topAmount.get(i).get("year");
						BigInteger expId =	((BigInteger)topAmount.get(i).get("exp_travel_type_id"));
						String str1 =  expId.toString();
				   		List<Map<String, Object>> test   = expenseDAO.findAmountBy((String) topAmount.get(i).get("user_id"),str1,year2, "year");
				   		expBarcharts.add((ArrayList<Map<String, Object>>) test);
				   	}
				   	
				}
			} 
			// Set Dashboard
			List<Map<String, Object>> allAmount = expenseDAO.findAmountBy(userSearch , typeSearch, "", "", year);
			List<Map<String, Object>> PaidAmount = expenseDAO.findAmountBy(userSearch , typeSearch, "P" ,"", year);
			List<Map<String, Object>> WaitAmount = expenseDAO.findAmountBy(userSearch, typeSearch, "W" ,"", year);
			request.setAttribute("allAmount", allAmount);
			request.setAttribute("AmountP", PaidAmount);
			request.setAttribute("AmountW", WaitAmount);

			// Set categoryField
			request.setAttribute("expBarcharts",expBarcharts);
				
		return SUCCESS;
	}catch(Exception e) {
		log.info(e.getMessage());
		return ERROR;
	}
}

public String openTravelExpReport() throws Exception  {
try {
		User ur = (User) request.getSession().getAttribute("onlineUser");
		String userLogin = ur.getId();
		HttpSession session = request.getSession();
		String listbyuser = request.getParameter("Id");
	
/// default search
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String yearnow = timeStamp.substring(0, 4);
		int year = Integer.parseInt(yearnow);
		request.setAttribute("yearnow", yearnow);
		List<Map<String, Object>> type = expTravelTypeDAO.findAllType();
		request.setAttribute("exptype", type);	
		List<Map<String, Object>> userseq = userDAO.sequense();
		request.setAttribute("userseq", userseq);
		request.setAttribute("userSelect", "All");

///// total travel expense Amount <<<<<  too many loop >>>>>
//	List<Map<String, Object>> allAmount = expenseDAO.findAmount();
//	int totalAmount = 0;
//	for (int i=0 ; i < allAmount.size(); i++) {
//		BigDecimal amounts = (BigDecimal)allAmount.get(i).get("total_amount");
//		log.info(amounts);
//		int amount = amounts.intValue();
//		totalAmount = totalAmount + amount;
//	}
//	request.setAttribute("totalAmount", totalAmount);
	
//	String[] status =  { "P" , "W"} ;
//	for (int j=0 ; j < status.length; j++) {
//		String expStatus = status[j];
//	List<Map<String, Object>> amountS = expenseDAO.findAmountByStatus(expStatus);
//	int expAmount = 0;
//	for (int k=0 ; k < amountS.size(); k++) {
//		BigDecimal amounts = (BigDecimal)amountS.get(k).get("total_amount");
//		log.info(amounts);
//		int amount = amounts.intValue();
//		expAmount = expAmount + amount;
//	}
//	request.setAttribute("Amount"+status[j], expAmount);
//	}

		List<Map<String, Object>> allAmount = expenseDAO.findAmountBy("" , "", "", "", year);
		request.setAttribute("allAmount", allAmount);
		
		String[] status =  { "P" , "W"} ;
		for (int j=0 ; j < status.length; j++) {
			List<Map<String, Object>> expAmount = expenseDAO.findAmountByStatus(status[j] ,"", year);
		request.setAttribute("Amount"+status[j], expAmount);
		}
	
/// Top Paid for Charts
		List<Map<String, Object>> topAmount = expenseDAO.findAmountByStatus("P" ,"top", year);
		request.setAttribute("top10P", topAmount);
	
		ArrayList<ArrayList<Map<String, Object>>> expBarcharts = new ArrayList<ArrayList<Map<String, Object>>>();
	   		for(int i = 0 ; i < topAmount.size() ; i++){
	   		List<Map<String, Object>> test   = expenseDAO.findAmountBy((String) topAmount.get(i).get("user_id"),"","P","all", year);
	   		expBarcharts.add((ArrayList<Map<String, Object>>) test);
	   		}
	   	
	   	request.setAttribute("expBarcharts",expBarcharts);
		List<Map<String, Object>> type2 = expTravelTypeDAO.findAllType();
		request.setAttribute("expTypeChart", type2);
	    
	return SUCCESS;
	} catch (Exception e) {

		e.printStackTrace();
		return ERROR;
		}
	}

	public String UserChart() {
		try {

			String select = request.getParameter("active");
			log.info(select);
			if (select != null) {
				if (select.equals("1")) {
					List<Map<String, Object>> userseq = userDAO.UserEnable(select);
					request.setAttribute("userA", userseq);
				} else {
					List<Map<String, Object>> userseq = userDAO.UserEnable(select);
					request.setAttribute("userA", userseq);
				}
			} else {
				List<Map<String, Object>> userseq = userDAO.UserEnable("1");
				request.setAttribute("userA", userseq);
			}
			request.setAttribute("userActive", userDAO.UserCountEnable());
			request.setAttribute("userInactive", userDAO.UserDisable());
			request.setAttribute("appr", select);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

}