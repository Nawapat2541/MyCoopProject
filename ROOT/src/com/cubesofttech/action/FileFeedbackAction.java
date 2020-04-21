package com.cubesofttech.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.FeedbackDAO;
import com.cubesofttech.dao.FileFeedbackDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.FileFeedback;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FileFeedbackAction extends ActionSupport{
	private static final long serialVersionUID = 2280661337420278284L;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private FileFeedbackDAO filefeedbackDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;

	private User user;

	private String userId;

	private File fileFeedback;

	private String fileFeedbackFileName;

	private String userFeedbackId;

	private String userFeedbackCreate;

	private String fileFeedbackSize;

	private int fileIdfb;

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

	public File getFileFeedback() {
		return fileFeedback;
	}

	public void setFileFeedback(File fileFeedback) {
		this.fileFeedback = fileFeedback;
	}

	public String getFileFeedbackFileName() {
		return fileFeedbackFileName;
	}

	public void setFileFeedbackFileName(String fileFeedbackFileName) {
		this.fileFeedbackFileName = fileFeedbackFileName;
	}

	public String getUserFeedbackId() {
		return userFeedbackId;
	}

	public void setUserFeedbackId(String userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}

	public String getUserFeedbackCreate() {
		return userFeedbackCreate;
	}

	public void setUserFeedbackCreate(String userFeedbackCreate) {
		this.userFeedbackCreate = userFeedbackCreate;
	}

	public String getFileFeedbackSize() {
		return fileFeedbackSize;
	}

	public void setFileFeedbackSize(String fileFeedbackSize) {
		this.fileFeedbackSize = fileFeedbackSize;
	}

	public int getFileIdfb() {
		return fileIdfb;
	}

	public void setFileIdfb(int fileIdfb) {
		this.fileIdfb = fileIdfb;
	}

	public static final String USERSEQ = "userseq";
	public static final String USERLIST = "userList";
	public static final String FILEFEEDBACKLIST = "filefeedbackList";
	public static final String FILEUPLOADLIST = "fileuploadList";

	public String list() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEFEEDBACKLIST, filefeedbackDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String upload() {
		try {
			int maxId = filefeedbackDAO.getMaxId() + 1;
			FileFeedback filefeedback = new FileFeedback();
			if (fileFeedback != null) {
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				filefeedback.setSize(fileFeedbackSize);
				String fileName = fileFeedbackFileName; 
				filefeedback.setPath("/upload/filefeedback/" + maxId + "_" + fileName);
				FileUtil.upload(fileFeedback, fileServerPath + "upload/filefeedback/", maxId + "_" + fileName);
			} else {
				return ERROR;
			}

			int l = fileFeedbackFileName.length();
			int split = fileFeedbackFileName.indexOf('.');
			String name = fileFeedbackFileName.substring(0, split);
			String type = (String) fileFeedbackFileName.subSequence(split, l);

			filefeedback.setFileIdfb(maxId);
			filefeedback.setUserId(userFeedbackId);
			filefeedback.setUserCreate(userFeedbackCreate);
			filefeedback.setName(name);
			filefeedback.setType(type);
			filefeedback.setUserUpdate(userFeedbackCreate);
			filefeedback.setTimeCreate(DateUtil.getCurrentTime());
			filefeedback.setTimeUpdate(DateUtil.getCurrentTime());
			filefeedbackDAO.save(filefeedback);

			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEFEEDBACKLIST, filefeedbackDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String delete() {
		try {
			

			String fid = request.getParameter("id");
			log.info(fid);
			Integer ffid = Integer.valueOf(fid);

			FileFeedback filee = filefeedbackDAO.findById(ffid);
			log.info(filee.getPath());
			
			String path = request.getServletContext().getRealPath(filee.getPath());
			File file = new File(path);
			log.info(file.getName());
			log.info(file.getAbsolutePath());
			
			if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
			
			filefeedbackDAO.delete(filee);
			

			// FileFeedback file = new FileFeedback();
			// file.setFileIdfb(Integer.valueOf(request.getParameter("file_Idfb")));
			// filefeedbackDAO.delete(file);
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEFEEDBACKLIST, filefeedbackDAO.findAll());

			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String list2() {
		try {
			request.setAttribute(USERSEQ, userDAO.sequense());
			request.setAttribute(USERLIST, userDAO.findAll());
			request.setAttribute(FILEFEEDBACKLIST, filefeedbackDAO.findByuser(request.getParameter("userId")));
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
}
