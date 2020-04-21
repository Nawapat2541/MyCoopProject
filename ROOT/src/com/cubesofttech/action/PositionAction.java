package com.cubesofttech.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.DepartmentDAO;
import com.cubesofttech.dao.PositionDAO;
import com.cubesofttech.model.Position;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class PositionAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(PositionAction.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	public PositionDAO positionDAO;
	@Autowired
	public DepartmentDAO departmentDAO;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public static final String Position = "positionList";

	public String list() {
		try {
					List<Position> positionList = positionDAO.findAll();
					request.setAttribute(Position, positionList);
					return SUCCESS;
				} catch (Exception e) {
					log.error(e);
					
					return ERROR;
				}
		   }
	public String addPosition(){
		try{
			List<Map<String, Object>> departmentList = departmentDAO.sequense();
			request.setAttribute("departmentList", departmentList);
			return SUCCESS;
			
		}catch (Exception e){
			
			return ERROR;
		}
	}
	

	
	public String savePosition() {
		try{
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username login 
			String logonUser = ur.getId(); // Username login 

			Position position = new Position();
			String positionId = request.getParameter("positionId");
			String departmentId = request.getParameter("user.departmentId");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
		
			
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			Timestamp ts = DateUtil.dateToTimestamp(date, time);
			
			Position positionCheck =  positionDAO.findById(positionId); // ทำการหา ID
			if(positionCheck == null){ // Check ว่า Id มีการซ้ำไหม ถ้าไม่ซ้ำ Save
				
				position.setPositionId(positionId);
				position.setDepartmentId(departmentId);
				position.setName(name);
				position.setDescription(description);
				position.setUserCreate(logonUser);
				position.setUserUpdate(logonUser);
				position.setTimeCreate(ts);
				position.setTimeUpdate(ts);
				
			positionDAO.save(position);
			}else{ // ถ้าซ้ำ ทำการ Alert โดยสร้าง Flag ไว้ในหน้า department_add
				request.setAttribute("flag", "1");
				return INPUT;
			}
			List<Position> positionList = positionDAO.findAll();
			request.setAttribute(Position, positionList);
			return SUCCESS;
		}catch (Exception e){
			return ERROR;
		}
	}
	
	public String PositionEdit(){
		try{
			String positionId = request.getParameter("position_id");
			Position positionList = new Position();
			positionList = positionDAO.findById(positionId);
			request.setAttribute(Position, positionList);  //ส่งค่าจากหลังไปหน้า
			
			List<Map<String, Object>> departmentList = departmentDAO.sequense();
		//	String departmentId = request.getParameter("department_id");
		//	Department departmentList = new Department();
		//	departmentList = departmentDAO.findById(departmentId);
			request.setAttribute("departmentList", departmentList);
		//	request.setAttribute("departmentList", departmentList);
			
			
			
			
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
	}
	
	public String PositionUpdate(){
		try{
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username login 
			String logonUser = ur.getId(); // Username login 
			
			Position position = new Position();
			String positionId = request.getParameter("positionId");
			position = positionDAO.findById(positionId);
			
//			ส่วนของ GET PARAMETER
			String departmentId = request.getParameter("departmentId");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String date = request.getParameter("date");
			String time = request.getParameter("time");

			Timestamp ts = DateUtil.dateToTimestamp(date, time);
			
			position.setName(logonUser);
			position.setPositionId(positionId);
			position.setName(name);
			position.setDepartmentId(departmentId);
			position.setDescription(description);
			position.setTimeUpdate(ts);
			
			positionDAO.update(position);
			
			
			List<Position> positionList = positionDAO.findAll();
			request.setAttribute(Position, positionList);
			
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
	}
	
	public String deletePosition(){
		try{
			String positionId = request.getParameter("position_id");
			Position position = new Position();
			position = positionDAO.findById(positionId);
			log.debug(position);
			positionDAO.delete(position);
			List<Position> positionList = positionDAO.findAll();
			request.setAttribute(Position, positionList);
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
		
	}
		
		

}