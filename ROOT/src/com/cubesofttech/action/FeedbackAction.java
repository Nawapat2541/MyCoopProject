package com.cubesofttech.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.FeedbackDAO;
import com.cubesofttech.dao.FileFeedbackDAO;
import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.model.Feedback;
import com.cubesofttech.model.FileFeedback;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.cubesofttech.util.UUID;
import com.ibm.icu.text.SimpleDateFormat;
import com.opensymphony.xwork2.ActionSupport;

public class FeedbackAction extends ActionSupport {

	private static final Logger log = Logger.getLogger(FeedbackAction.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private FileUploadDAO fileuploadDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	private FileFeedbackDAO feedbackfileDAO;

	private User user;

	private String userId;

	private File fileUpload;

	private String fileUploadFileName;

	private String userUploadId;

	private String userUploadCreate;

	private String fileUploadSize;

	private int fileId;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private int idfeedback;

	public int getIdfeedback() {
		return idfeedback;
	}


	public void setIdfeedback(int idfeedback) {
		this.idfeedback = idfeedback;
	}

	private String title_Feedback;

	private String description_Feedback;

	public String getTitle_Feedback() {
		return title_Feedback;
	}

	public void setTitle_Feedback(String title_Feedback) {
		this.title_Feedback = title_Feedback;
	}

	public String getDescription_Feedback() {
		return description_Feedback;
	}

	public void setDescription_Feedback(String description_Feedback) {
		this.description_Feedback = description_Feedback;
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


	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public static final String ONLINEUSER = "onlineUser";
	public static final String FEEDID = "idfeedback";
	public static final String FILEFEEDBACKLIST = "filefeedbacklist";
	public static final String FILEFEEDBACK = "myfileunique";
	public static final String FILEUPLOADLIST = "fileuploadList";

	public String saveFeed() {
		try {

			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser = ur.getId();

			int maxId = fileuploadDAO.getMaxId() + 1;

			FileUpload fileupload = new FileUpload();
			if (fileUpload != null) { 
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				fileupload.setSize(fileUploadSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
				FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);
				
				int l = fileUploadFileName.length();
				int split = fileUploadFileName.indexOf('.');
				String nameFeed = fileUploadFileName.substring(0, split);
				String typeFeed = (String) fileUploadFileName.subSequence(split, l);

				fileupload.setFileId(maxId);
				fileupload.setUserId(logonUser);
				fileupload.setUserCreate(logonUser);
				fileupload.setName(nameFeed);
				fileupload.setType(typeFeed);
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				fileuploadDAO.save(fileupload);		
				
				
				Feedback fb = new Feedback();
				fb.setIdfeedback(idfeedback);
				fb.setTitle(title_Feedback);
				fb.setDescription(description_Feedback);
				fb.setStatus("R");
				fb.setTimeCreate(DateUtil.getCurrentTime());
				fb.setUserCreate(logonUser);
				feedbackDAO.save(fb);
				
			} else {
				Feedback fb = new Feedback();
				fb.setIdfeedback(idfeedback);
				fb.setTitle(title_Feedback);
				fb.setDescription(description_Feedback);
				fb.setStatus("R");
				fb.setTimeCreate(DateUtil.getCurrentTime());
				fb.setUserCreate(logonUser);
				feedbackDAO.save(fb);
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	
	public String list() {
		
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String userLogin = ur.getId();// online user
			String userID = request.getParameter("Id");
			if (userID != null) {
				userLogin = userID;
			}
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			List<Feedback> feedback = feedbackDAO.findAll();
			request.setAttribute("feedback", feedback);
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String searchFeed() {
		
		try {
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			String startdate = request.getParameter("startdate");// 30-12-2554
			String enddate = request.getParameter("enddate");// 17-04-2560
			Timestamp start_date = DateUtil.changeYearStart(startdate);// error
			Timestamp end_date = DateUtil.changeYearEnd(enddate);
			request.setAttribute("feedback", feedbackDAO.searchtable(start_date, end_date));
			List<Feedback> feedbackList = feedbackDAO.findAll();
			request.setAttribute("feedbackList", feedbackList);
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String openFeed() {
		
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser = ur.getId();
			
			
			
			String idff = request.getParameter("id_feed");
			Integer idfff = Integer.parseInt(idff);
			List<Map<String, Object>> feedback = feedbackDAO.searchid(idfff);
			request.setAttribute("feedback",feedback.get(0));
			String key = (String)feedback.get(0).get("unique_key");
			request.setAttribute(FILEUPLOADLIST, feedbackfileDAO.findByunique(key));
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String saveUpdate() {
		
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser = ur.getId();

			String idff = request.getParameter("id_feedd");
			int idfff = Integer.parseInt(idff);
			log.info(idff);
			List<Map<String, Object>> feedback = feedbackDAO.searchid(idfff);
			Integer idd = (Integer) feedback.get(0).get("idfeedback");
			Feedback f1 = feedbackDAO.findById(idd);
			String statusUU = request.getParameter("statusU");
			String res = request.getParameter("response");
			f1.setDescriptionRes(res);
			f1.setTimeUpdate(DateUtil.getCurrentTime());
			f1.setUserUpdate(logonUser);
			feedbackDAO.save(f1);
			Feedback f2 = feedbackDAO.findById(idd);
			f2.setStatus(statusUU);
			feedbackDAO.update(f2);
			List<Feedback> feedback1 = feedbackDAO.findAll();
			request.setAttribute("feedback", feedback1);
			return SUCCESS;
		}catch(Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

	public String feedback_del1() throws Exception {
		try {
			String Id = request.getParameter("id");
			Integer idValue = Integer.valueOf(Id);
			Feedback feedback = feedbackDAO.findById(idValue);
			List<Map<String, Object>> feedback1 = feedbackDAO.searchid(idValue);
			String bts = (String)feedback1.get(0).get("unique_key");
			List<Map<String, Object>> mrt = feedbackfileDAO.findUni(bts);
			log.info(mrt);
			for(int i=0;i<mrt.size();i++) {
				Integer arl = (Integer)mrt.get(i).get("file_idfb");
				String path = mrt.get(i).get("path").toString();
				log.info(path);
				String filepath = request.getServletContext().getRealPath(path);
				File file = new File(filepath);
				log.info(file.getName());
				log.info(file.getAbsolutePath());
				
				if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
				FileFeedback sky = feedbackfileDAO.findById(arl);
				feedbackfileDAO.delete(sky);
			}
			feedbackDAO.delete(feedback);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
}

	public String listuser() {
		
		try {
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String user = ur.getId();
			request.setAttribute("logonUser", user);
		
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);
			
			List<Map<String,Object>> feedback = feedbackDAO.findlistuser(user);
			request.setAttribute("feedback", feedback);
			return SUCCESS;
		}catch(Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String saveFeedback() {
		try {
			
			String title = request.getParameter("title");
			String uKey = request.getParameter("ukey");
			log.info(uKey);
			String description = request.getParameter("description");
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String userLogin = ur.getId();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String datenow = sdf.format(timestamp).toString();
			Timestamp nowdate = Timestamp.valueOf(datenow);

			Feedback fb = new Feedback();
			fb.setTitle(title);
			fb.setDescription(description);
			fb.setUserCreate(userLogin);
			fb.setUserUpdate(userLogin);
			fb.setTimeCreate(nowdate);
			fb.setStatus("R");
			fb.setTimeUpdate(nowdate);
			fb.setUniquekey(uKey);
			feedbackDAO.save(fb);
			
			
			return SUCCESS;
		}catch(Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}

public String saveFile() {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String userLogin = ur.getId();
			String key = request.getParameter("ukey");
			log.info(key);
			int maxId = feedbackfileDAO.getMaxId() + 1; //get max ID
			FileFeedback fileupload = new FileFeedback();
			//read size file
			double fileSize = 0.0;
			String modifiedFileSize = null;
			if (fileUpload != null) {
				fileSize = (double) fileUpload.length();
				if (fileSize < 1024) {
		            modifiedFileSize = String.valueOf(fileSize).concat(" Byte");
		        } else if (fileSize > 1024 && fileSize < (1024 * 1024)) {
		            modifiedFileSize = String.valueOf(Math.round((fileSize / 1024 * 100.0)) / 100.0).concat(" KB");
		        } else {
		            modifiedFileSize = String.valueOf(Math.round((fileSize / (1024 * 1024) * 100.0)) / 100.0).concat(" MB");
		        }
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				fileupload.setSize(modifiedFileSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/feedbackfile/" + maxId + "_" + fileName);
				FileUtil.upload(fileUpload, fileServerPath + "upload/feedbackfile/", maxId + "_" + fileName);
				int l = fileUploadFileName.length();
				int split = fileUploadFileName.lastIndexOf('.');
				String name = fileUploadFileName.substring(0, split);
				String type = (String) fileUploadFileName.subSequence(split, l);
				
				fileupload.setFileIdfb(maxId);
				fileupload.setUserId(userLogin);
				fileupload.setUserCreate(userLogin);
				fileupload.setName(name);
				fileupload.setUniqueKey(key);
				fileupload.setType(type);
				fileupload.setSize(modifiedFileSize);
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				feedbackfileDAO.save(fileupload);
				
			}
			if(modifiedFileSize!=null) {
				JSONObject json = new JSONObject();
				json.put("input", 1);
				PrintWriter out = response.getWriter();	
				out.print(json);
				out.flush();
				out.close();
				log.info(modifiedFileSize);
			}
			
			
			
			return SUCCESS;
		}catch(Exception e) {
			return ERROR;
		}
	}

public String getUkey() {
		try {
			long epoch = System.currentTimeMillis()/1000;
			String suffixUn = String.valueOf(epoch);
			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
			UUID uuid = new UUID();
			String key = uuid.getUUID().concat(suffixUn);
			
			JSONObject json = new JSONObject();
			json.put("k", key);
			PrintWriter out = response.getWriter();	
			out.print(json);
			out.flush();
			out.close();

			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	

	public String userDetail() {

		try {
			List<Map<String, Object>> userSeq = userDAO.sequense();
			request.setAttribute("userseq", userSeq);
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String user = ur.getId();
			request.setAttribute("logonUser", user);

			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute("userseq", userseq);

			String someting = request.getParameter("id_feed");
			log.info(someting);
			int id = Integer.parseInt(someting);
			List<Map<String, Object>> feedback = feedbackDAO.searchid(id);
			request.setAttribute("feedback", feedback.get(0));
			//String someting2 = (String) feedback.get(0).get("user_create");			
			String key = (String) feedback.get(0).get("unique_key");

			request.setAttribute(FILEFEEDBACK, feedbackfileDAO.findByunique(key));

			log.info(feedback);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			return ERROR;
		}
	}
	public String feedback_del() throws Exception {
		try {
			String Id = request.getParameter("id");
			Integer idValue = Integer.valueOf(Id);
			int id = Integer.parseInt(Id);
			Feedback feedback = feedbackDAO.findById(idValue);
			log.info(feedback);
			List<Map<String,Object>> User = feedbackDAO.searchid(id);
			log.info(User);
			log.info(id);
			String user = (String) User.get(0).get("user_create");
			String Uniq = (String) User.get(0).get("unique_key");
			List<Map<String, Object>> mrt = feedbackfileDAO.findUni(Uniq);
			log.info(mrt);
			for(int i=0;i<mrt.size();i++) {
				Integer arl = (Integer)mrt.get(i).get("file_idfb");
				log.info(arl);
				String path = mrt.get(i).get("path").toString();
				log.info(path);
				String filepath = request.getServletContext().getRealPath(path);
				File file = new File(filepath);
				log.info(file.getName());
				log.info(file.getAbsolutePath());
				
				if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
				FileFeedback sky = feedbackfileDAO.findById(arl);
				feedbackfileDAO.delete(sky);
			}
			log.info(user);
			log.info(Uniq);
			feedbackDAO.delete(feedback);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

}
