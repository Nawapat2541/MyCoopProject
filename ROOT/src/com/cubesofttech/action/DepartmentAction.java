package com.cubesofttech.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.DepartmentDAO;
import com.cubesofttech.model.Department;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DepartmentAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(DepartmentAction.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	public DepartmentDAO departmentDAO;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public static final String Department = "departmentList";
	
	public String list() {
		try {
					List<Department> departmentList = departmentDAO.findAll();
					request.setAttribute(Department, departmentList);
					return SUCCESS;
				} catch (Exception e) {
					log.error(e);
					
					return ERROR;
				}
		   }
	public String deleteDepartment(){
		try{
			String idDepart = request.getParameter("id");
			Department depart = new Department();
			depart = departmentDAO.findById(idDepart);
			log.debug(depart);
			departmentDAO.delete(depart);
			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute(Department, departmentList);
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
		
	}
	public String updateDepart(){
		try{
			String idDepart = request.getParameter("id");
			Department departmentList = new Department();
			departmentList = departmentDAO.findById(idDepart);
			request.setAttribute(Department, departmentList);  //ส่งค่าจากหลังไปหน้า
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
	}
	
	public String editDepart(){
		try{
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username login 
			String logonUser = ur.getId(); // Username login 
			
			Department depart = new Department();
			String idDepart = request.getParameter("ID");
			depart = departmentDAO.findById(idDepart);
			
//			ส่วนของ GET PARAMETER
			String name = request.getParameter("name");
			String deptdes = request.getParameter("deptdes");
			String prefixId = request.getParameter("testnaja");
			
			String date = request.getParameter("date");
			String time = request.getParameter("time");

			Timestamp ts = DateUtil.dateToTimestamp(date, time);
			
			depart.setUserupdate(logonUser);
			depart.setName(name);
			depart.setDescription(deptdes);
			depart.setPrefixId(prefixId);
			depart.setTimeUpdate(ts);
			
			departmentDAO.update(depart);
			
			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute(Department, departmentList);
			
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
	}
	public String addDepart(){
		try{
			return SUCCESS;
			
		}catch (Exception e){
			
			return ERROR;
		}
	}
	public String saveDepart() {
		try{
			User ur = (User) request.getSession().getAttribute("onlineUser"); // Username login 
			String logonUser = ur.getId(); // Username login 

			Department depart = new Department();
			String idDepart = request.getParameter("ID");
			String name = request.getParameter("name");
			String deptdes = request.getParameter("deptdes");
			String deptpre = request.getParameter("deptpre");
			
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			Timestamp ts = DateUtil.dateToTimestamp(date, time);
			
			Department departmentCheck =  departmentDAO.findById(idDepart); // ทำการหา ID
			if(departmentCheck == null){ // Check ว่า Id มีการซ้ำไหม ถ้าไม่ซ้ำ Save
				
			depart.setTimeCreate(ts);
			depart.setId(idDepart);
			depart.setName(name);
			depart.setUsercreate(logonUser);
			depart.setDescription(deptdes);
			depart.setPrefixId(deptpre);
			
			departmentDAO.save(depart);
			}else{ // ถ้าซ้ำ ทำการ Alert โดยสร้าง Flag ไว้ในหน้า department_add
				request.setAttribute("flag", "1");
				return INPUT;
			}
			List<Department> departmentList = departmentDAO.findAll();
			request.setAttribute(Department, departmentList);
			return SUCCESS;
		}catch (Exception e){
			
			return ERROR;
		}
	}
}