package com.cubesofttech.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.util.FileUtil;
import com.cubesofttech.dao.BorrowDAO;
import com.cubesofttech.dao.EquipmentDAO;
import com.cubesofttech.dao.EquipmentStatusDAO;

import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.model.Borrow;

import com.cubesofttech.dao.EquipmentTypeDAO;

import com.cubesofttech.model.Equipment;
import com.cubesofttech.model.EquipmentStatus;

import com.cubesofttech.model.FileUpload;

import com.cubesofttech.model.EquipmentType;

import com.cubesofttech.model.User;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class EquipmentAction extends ActionSupport {
		
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private EquipmentDAO equipmentDAO;
	
	@Autowired
	private EquipmentStatusDAO equipmentStatusDAO;
	
	@Autowired
	private FileUploadDAO fileuploadDAO;
	
	@Autowired
	private BorrowDAO borrowDAO;
	
	private File image;
	private String imageContentType;
	private String imageFileName;
	
	private String itemNo;
	private String type;
	private String name;
	private String serialNo;
	private int amount;
	private String location;
	private String windows;
	private String process;
	private String ram;
	private String hdd;
	private String battery;
	private String detail;
	private String status;
	private String note;
	
	private String UPLOAD_PATH = "upload/equipment/";
	
	private User onlineUser = (User) request.getSession().getAttribute("onlineUser");
	
	public String eAdd() {
		try {
			List<EquipmentStatus> status = equipmentStatusDAO.getall();
			List<EquipmentType> type = equipmentTypeDAO.getall();
			
			request.setAttribute("type", new Gson().toJson(type));
			request.setAttribute("status", new Gson().toJson(status));
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String eEdit() {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Equipment e = equipmentDAO.getById(id);
			
			List<EquipmentStatus> status = equipmentStatusDAO.getall();
			List<EquipmentType> type = equipmentTypeDAO.getall();
			
			request.getSession().setAttribute("id_s", id);
			request.setAttribute("equipment", new Gson().toJson(e));
			request.setAttribute("status", new Gson().toJson(status));
			request.setAttribute("type", new Gson().toJson(type));
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String newE() {
		try {					
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			Equipment e = new Equipment();
			
			if(image != null) {
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				String uploadPath = fileServerPath + UPLOAD_PATH;
				
				int maxId = fileuploadDAO.getMaxId() +1 ;
				String fName = maxId + "_" + imageFileName;
				
				FileUtil.upload(image, uploadPath, fName);
				
				String[] fullFileName = imageFileName.split("[.]");
				String uploadFileName = fullFileName[0];
				String uploadFileType = fullFileName[1];
				
				FileUpload f = new FileUpload();
				f.setFileId(maxId);
				f.setName(uploadFileName);
				f.setPath(UPLOAD_PATH + fName);
				f.setSize(FileUtil.getFileSize(image.length()));
				f.setTimeCreate(timestamp);
				f.setType(uploadFileType);
				f.setUserCreate(user.getId());
				f.setUserId(user.getId());
				fileuploadDAO.save(f);
				
				e.setImage(UPLOAD_PATH + fName);
			}
			
			e.setAmount(amount);
			e.setBattery(battery);
			e.setDetail(detail);
			e.setEquipmentId(equipmentDAO.getMaxId() + 1);
			e.setHdd(hdd);
			e.setItemNo(itemNo);
			e.setLocation(location);
			e.setName(name);
			e.setNote(note);
			e.setProcess(process);
			e.setRam(ram);
			e.setSerialNo(serialNo);
			e.setStatus(status);
			e.setTimeCreate(timestamp);
			e.setType(type);
			e.setUserCreate(user.getId());
			e.setWindows(windows);
			equipmentDAO.save(e);
				
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String updateE() {
		try {
			User user = (User) request.getSession().getAttribute("onlineUser");
			int id_s = (int) request.getSession().getAttribute("id_s");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			Equipment e = equipmentDAO.getById(id_s);
			
			if(image != null) {
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				String uploadPath = fileServerPath + UPLOAD_PATH;
				
				int maxId = fileuploadDAO.getMaxId() +1 ;
				String fName = maxId + "_" + imageFileName;
				
				FileUtil.upload(image, uploadPath, fName);
				
				String[] fullFileName = imageFileName.split("[.]");
				String uploadFileName = fullFileName[0];
				String uploadFileType = fullFileName[1];
				
				FileUpload f = new FileUpload();
				f.setFileId(maxId);
				f.setName(uploadFileName);
				f.setPath(UPLOAD_PATH + fName);
				f.setSize(FileUtil.getFileSize(image.length()));
				f.setTimeCreate(timestamp);
				f.setType(uploadFileType);
				f.setUserCreate(user.getId());
				f.setUserId(user.getId());
				fileuploadDAO.save(f);
				
				e.setImage(UPLOAD_PATH + fName);
			}
			
			e.setAmount(amount);
			e.setBattery(battery);
			e.setDetail(detail);
			e.setHdd(hdd);
			e.setItemNo(itemNo);
			e.setLocation(location);
			e.setName(name);
			e.setNote(note);
			e.setProcess(process);
			e.setRam(ram);
			e.setSerialNo(serialNo);
			e.setStatus(status);
			e.setTimeCreate(timestamp);
			e.setType(type);
			e.setUserCreate(user.getId());
			e.setWindows(windows);
			equipmentDAO.update(e);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
		
	public String deleteE() {
		try {
			String id = request.getParameter("id");
			
			Equipment e = equipmentDAO.findByEquipmentId(Integer.parseInt(id));
			equipmentDAO.delete(e);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	

	@Autowired
	private EquipmentTypeDAO equipmentTypeDAO;
	

	public String statusList() {
		try {
			List<EquipmentStatus> list = equipmentStatusDAO.getall();
			request.setAttribute("list", new Gson().toJson(list));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
		
	public String statusSave() {
		try {
			String status = request.getParameter("status");
			String color = request.getParameter("color");
			String description = request.getParameter("description");
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			EquipmentStatus eStatus = new EquipmentStatus();
			eStatus.setColor(color);
			eStatus.setDescription(description);
			eStatus.setStatusId(status);
			eStatus.setTimeCreate(timestamp);
			eStatus.setUserCreate(user.getId());
			equipmentStatusDAO.save(eStatus);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String statusUpdate() {
		try {
			String status = request.getParameter("status");
			String color = request.getParameter("color");
			String description = request.getParameter("description");
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			EquipmentStatus eStatus = equipmentStatusDAO.findByStatus(status);
			if(eStatus == null) {
				eStatus = new EquipmentStatus();
				eStatus.setColor(color);
				eStatus.setDescription(description);
				eStatus.setStatusId(status);
				eStatus.setTimeCreate(timestamp);
				eStatus.setUserCreate(user.getId());
				equipmentStatusDAO.save(eStatus);
			} else {
				eStatus.setColor(color);
				eStatus.setDescription(description);
				eStatus.setUserUpdate(user.getId());
				eStatus.setTimeUpdate(timestamp);
				equipmentStatusDAO.update(eStatus);
			}
	
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String statusDelete() {
		try {
			String id = request.getParameter("id");
			
			EquipmentStatus eStatus = equipmentStatusDAO.findByStatus(id);
			equipmentStatusDAO.delete(eStatus);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String statusEdit() {
		try {
			String id = request.getParameter("id");
			
			EquipmentStatus eStatus = equipmentStatusDAO.findByStatus(id);
			
			request.setAttribute("info", new Gson().toJson(eStatus));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public String table() {
		String selType = request.getParameter("type");
		String selStatus = request.getParameter("status");
		try {
			List<EquipmentStatus> status = equipmentStatusDAO.getall();
			List<EquipmentType> type = equipmentTypeDAO.getall();
			List<Borrow> borrows = borrowDAO.findAll();
			List<Equipment> equipments = equipmentDAO.getAll();
			
			request.setAttribute("borrows", new Gson().toJson(borrows));
			request.setAttribute("equipments", new Gson().toJson(equipments));
			request.setAttribute("selStatus", selStatus);
			request.setAttribute("selType", selType);
			request.setAttribute("status", new Gson().toJson(status));
			request.setAttribute("type", new Gson().toJson(type));
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String EquipmentPieChart() {
		try {
			List<Equipment> list = equipmentDAO.getAll();
			List<EquipmentStatus> status = equipmentStatusDAO.getall();
			List<EquipmentType> type = equipmentTypeDAO.getall();
			
			request.setAttribute("info", new Gson().toJson(list));
			request.setAttribute("status", new Gson().toJson(status));
			request.setAttribute("type", new Gson().toJson(type));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWindows() {
		return windows;
	}

	public void setWindows(String windows) {
		this.windows = windows;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	//Equipment Type
	public String typelist() {
		try {
			List<EquipmentType> list = equipmentTypeDAO.getall();
			request.setAttribute("tlist", new Gson().toJson(list));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void TypeRecord() {
		try {
			String Type = request.getParameter("Type");
			List<Equipment> list = equipmentDAO.findByTypes(Type);
			Map<String, String> map = new HashMap<String, String>();

			if(list.size()==0) {
				map.put("message", "ok");
			} else {
				map.put("message", "can't");
				map.put("count", Integer.toString(list.size()));
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(map));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String typeSave() {
		try {
			String Type = request.getParameter("Type");
			String description = request.getParameter("description");
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			EquipmentType typeS = new EquipmentType();
			typeS.setDescription(description);
			typeS.setTypeID(Type);
			typeS.setTimeCreate(timestamp);
			typeS.setUserCreate(user.getId());
			equipmentTypeDAO.save(typeS);

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String typeUpdate() {
		try {
			String Type = request.getParameter("Type");
			String description = request.getParameter("description");
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			EquipmentType TypeU = equipmentTypeDAO.findByType(Type);
			if(TypeU == null) {
				TypeU = new EquipmentType();
				TypeU.setDescription(description);
				TypeU.setTypeID(Type);
				TypeU.setTimeCreate(timestamp);
				TypeU.setUserCreate(user.getId());
				equipmentTypeDAO.save(TypeU);
			} else {
				TypeU.setDescription(description);
				TypeU.setUserUpdate(user.getId());
				TypeU.setTimeUpdate(timestamp);
				equipmentTypeDAO.update(TypeU);
			}
	
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String typeDelete() {
		try {
			String id = request.getParameter("id");
			EquipmentType TypeD = equipmentTypeDAO.findByType(id);
			equipmentTypeDAO.delete(TypeD);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String typeEdit() {
		try {
			String id = request.getParameter("id");
			EquipmentType TypeE = equipmentTypeDAO.findByType(id);
			request.setAttribute("info", new Gson().toJson(TypeE));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public String typeEdit2() {
		try {
			String id = request.getParameter("Type");
			EquipmentType TypeE = equipmentTypeDAO.findByType(id);
			request.setAttribute("save", new Gson().toJson(TypeE));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//JSON API
	public void getEquipmentJSON() {
		try {
			if(onlineUser != null) {
				List<Equipment> list = equipmentDAO.getAll();
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(new Gson().toJson(list));
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getEquipmentStatusJSON() {
		try {
			List<EquipmentStatus> list = equipmentStatusDAO.getall();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(list));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getEquipmentTypeJSON() {
		try {
			List<EquipmentType> list = equipmentTypeDAO.getall();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(list));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateStatusColor() {
		try {
			String id = request.getParameter("id");
			String color = request.getParameter("color");
			User user = (User) request.getSession().getAttribute("onlineUser");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			EquipmentStatus eStatus = equipmentStatusDAO.findByStatus(id);
			eStatus.setColor(color);
			eStatus.setUserUpdate(user.getId());
			eStatus.setTimeUpdate(timestamp);
			equipmentStatusDAO.update(eStatus);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("success");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eCheckItemNo() {
		try {
			String itemNo = request.getParameter("itemNo");
			Map<String, String> map = new HashMap<String, String>();
			Equipment e = equipmentDAO.findByItemNo(itemNo);
			
			if(e == null) {
				map.put("message","available");
			} else {
				map.put("message", "used");
				map.put("name", e.getName());
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(map));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eCheckBorrow() {
		try {
			String eId = request.getParameter("eId");
			List<Borrow> borrows = borrowDAO.findBorrowByEquipmentIdAndStatus(eId, "B");
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(borrows));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eCheckAllBorrow() {
		try {
			String eId = request.getParameter("eId");
			List<Borrow> borrows = borrowDAO.findBorrowByEquipmentId(eId);
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(borrows));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkEStatusRecord() {
		try {
			String status = request.getParameter("status");
			List<Equipment> list = equipmentDAO.findByStatus(status);
			Map<String, String> map = new HashMap<String, String>();

			if(list.size()==0) {
				map.put("message", "ok");
			} else {
				map.put("message", "can't");
				map.put("count", Integer.toString(list.size()));
			}
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(map));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//END JSON API
}
