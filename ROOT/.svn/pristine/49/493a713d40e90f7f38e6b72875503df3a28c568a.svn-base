package com.cubesofttech.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 2280661337420278284L;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private FileUploadDAO fileuploadDAO;

	private User user;

	private String userId;

	private File fileUpload;

	private String fileUploadFileName;

	private String userUploadId;

	private String userUploadCreate;

	private String fileUploadSize;

	private int fileId;

	public String getFileUploadSize() {
		return fileUploadSize;
	}

	public void setFileUploadSize(String fileUploadSize) {
		this.fileUploadSize = fileUploadSize;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getUserUploadCreate() {
		return userUploadCreate;
	}

	public void setUserUploadCreate(String userUploadCreate) {
		this.userUploadCreate = userUploadCreate;
	}

	public String getUserUploadId() {
		return userUploadId;
	}

	public void setUserUploadId(String userUploadId) {
		this.userUploadId = userUploadId;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static final String USERSEQ = "userseq";
	public static final String USERLIST = "userList";
	public static final String FILEUPLOADLIST = "fileuploadList";

	public String list() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEUPLOADLIST, fileuploadDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String upload(){
		try {
			int maxId = fileuploadDAO.getMaxId() + 1;
			FileUpload fileupload = new FileUpload();
			if (fileUpload != null) {
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				fileupload.setSize(fileUploadSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
				FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);
			} else {
				return ERROR;
			}

			int l = fileUploadFileName.length();
			int split = fileUploadFileName.indexOf('.');
			String name = fileUploadFileName.substring(0, split);
			String type = (String) fileUploadFileName.subSequence(split, l);

			fileupload.setFileId(maxId);
			fileupload.setUserId(userUploadId);
			fileupload.setUserCreate(userUploadCreate);
			fileupload.setName(name);
			fileupload.setType(type);
			fileupload.setUserUpdate(userUploadCreate);
			fileupload.setTimeCreate(DateUtil.getCurrentTime());
			fileupload.setTimeUpdate(DateUtil.getCurrentTime());
			fileuploadDAO.save(fileupload);
			
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEUPLOADLIST, fileuploadDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String delete() {
		try {
			FileUpload file = new FileUpload();
			file.setFileId(Integer.valueOf(request.getParameter("fileId")));
			fileuploadDAO.delete(file);
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEUPLOADLIST, fileuploadDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String list2() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEUPLOADLIST, fileuploadDAO.findByuser(request.getParameter("userId")));
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

}
