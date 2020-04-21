package com.cubesofttech.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.dao.LeaveDAO;
import com.cubesofttech.dao.NewsDAO;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.model.News;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 2280661337420278284L;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private FileUploadDAO fileuploadDAO;
	
	@Autowired
	private LeaveDAO leaveDAO;
	
	private int newId;
	
	private User user;

	private String userId;
	
	private String title_News;

	private String detail_News;
	
	private int mynewsId;

	private File fileUpload;

	private String fileUploadFileName;

	private String userUploadId;

	private String userUploadCreate;

	private String fileUploadSize;
	
	private int mypic;
	
	private int allNews;
	
	public int getAllNews() {
		return allNews;
	}

	public void setAllNews(int allNews) {
		this.allNews = allNews;
	}
	

	public int getMypic() {
		return mypic;
	}

	public void setMypic(int mypic) {
		this.mypic = mypic;
	}
	
	public int getNewId() {
		return newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
	}
	
	public String getFileUploadSize() {
		return fileUploadSize;
	}

	public void setFileUploadSize(String fileUploadSize) {
		this.fileUploadSize = fileUploadSize;
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
	
	public int getMynewsId() {
		return mynewsId;
	}

	public void setMynewsId(int mynewsId) {
		this.mynewsId = mynewsId;
	}
	
	public String getTitle_News() {
		return title_News;
	}

	public void setTitle_News(String title_News) {
		this.title_News = title_News;
	}
	
	public String getDetail_News() {
		return detail_News;
	}

	public void setDetail_News(String detail_News) {
		this.detail_News = detail_News;
	}
	
	 public static final String NEWSFEED = "newsfeed";
	 public static final String ONLINEUSER = "onlineUser";
	 public static final String NODAY = "no_day";

	public String feedList() {
		try {
			String plusLimit = request.getParameter("mylimit");
			int limit = 0;
			if(plusLimit != null)
			{
				limit = Integer.parseInt(plusLimit);
			}
			int realLimit = 5+limit;
	        request.setAttribute(NEWSFEED,newsDAO.listnews(realLimit));
	        request.setAttribute("realLimit",realLimit);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String moreFeedList() 
	{
		try {
			String plusLimit = request.getParameter("mylimit");
			int limit = 0;
			if(plusLimit != null)
			{
				limit = Integer.parseInt(plusLimit);
			}
			int realLimit = 5+limit;
			List<Map<String, Object>> newsfeed = newsDAO.listnews(realLimit);
			allNews = newsfeed.get(0).get("allnews").hashCode();
			if(realLimit > allNews || realLimit < allNews)
			{
				String showHidebtn = "display: none;";
				request.setAttribute("show_hidebtn",showHidebtn);
			}
			else
			{
				String showHidebtn = "";
				request.setAttribute("show_hidebtn",showHidebtn);
			}
			request.setAttribute(NEWSFEED,newsDAO.listnews(allNews));
	        request.setAttribute("allNews",allNews);
            return  SUCCESS;
			} 
		
		catch (Exception e) 
			{
				log.error(e);	
				return ERROR;
			}
	}
	
	public String addNews() {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			request.setAttribute(NEWSFEED, newsDAO.mynews(ur.getId()));
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String performEdit() {
		try {
			News news = newsDAO.findById(newId);
			Integer fileId = news.getFileId();
			if(fileId != null)
			{
		 		request.setAttribute("pathfile", newsDAO.find_filepath(fileId));
			}
			request.setAttribute("news", news);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String editNews() {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			String logonUser = ur.getId();
			FileUpload fileupload = new FileUpload();
			
			Integer mynewsid = Integer.valueOf(mynewsId);
			News ne = newsDAO.findById(mynewsid);
			ne.setNewsId(mynewsId);
			ne.setNewsHead(title_News);
			ne.setNewsDescription(detail_News);
			ne.setTimeUpdate(DateUtil.getCurrentTime());
			ne.setUserUpdate(logonUser);
			newsDAO.update(ne);	
			int picture = mypic;
			if(picture > 0)
			{
				if (fileUpload != null) 
				{
				int maxId = fileuploadDAO.getMaxId();
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				fileupload.setSize(fileUploadSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/user/" + maxId + "_" + fileName);
				FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId + "_" + fileName);
				
				int l = fileUploadFileName.length();
				int split = fileUploadFileName.indexOf('.');
				String name = fileUploadFileName.substring(0, split);
				String type = (String) fileUploadFileName.subSequence(split, l);

				fileupload.setFileId(maxId);
				fileupload.setUserId(logonUser);
				fileupload.setUserCreate(logonUser);
				fileupload.setName(name);
				fileupload.setType(type);
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				fileuploadDAO.update(fileupload);
				
				ne.setFileId(maxId);
				newsDAO.update(ne);
				}
			}
			else
			{
				if (fileUpload != null) 
				{
				int maxId2 = fileuploadDAO.getMaxId()+1;
				ServletContext context = request.getServletContext();
				String fileServerPath = context.getRealPath("/");
				fileupload.setSize(fileUploadSize);
				String fileName = fileUploadFileName;
				fileupload.setPath("/upload/user/" + maxId2 + "_" + fileName);
				FileUtil.upload(fileUpload, fileServerPath + "upload/user/", maxId2 + "_" + fileName);
				
				int l = fileUploadFileName.length();
				int split = fileUploadFileName.indexOf('.');
				String name = fileUploadFileName.substring(0, split);
				String type = (String) fileUploadFileName.subSequence(split, l);

				fileupload.setFileId(maxId2);
				fileupload.setUserId(logonUser);
				fileupload.setUserCreate(logonUser);
				fileupload.setName(name);
				fileupload.setType(type);
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				fileuploadDAO.save(fileupload);
				
				ne.setFileId(maxId2);
				newsDAO.update(ne);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			
			User ur = (User) request.getSession().getAttribute(ONLINEUSER);
			
            News news = new News();
            news.setNewsId(Integer.valueOf(request.getParameter("news_id")));
            newsDAO.delete(news);
            
 			request.setAttribute(NEWSFEED, newsDAO.mynews(ur.getId()));
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	public String saveNewsFeed(){
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
				String name = fileUploadFileName.substring(0, split);
				String type = (String) fileUploadFileName.subSequence(split, l);

				fileupload.setFileId(maxId);
				fileupload.setUserId(logonUser);
				fileupload.setUserCreate(logonUser);
				fileupload.setName(name);
				fileupload.setType(type);
				fileupload.setTimeCreate(DateUtil.getCurrentTime());
				fileuploadDAO.save(fileupload);		
				
				News ns = new News();
				ns.setFileId(maxId);
				ns.setUserCreate(logonUser);
				ns.setNewsId(newsDAO.getMaxId() + 1);
				ns.setNewsHead(title_News);
				ns.setNewsDescription(detail_News);
				ns.setTimeCreate(DateUtil.getCurrentTime());
				newsDAO.save(ns);
				request.setAttribute(NEWSFEED, newsDAO.listnews(5));
			} else {
				News ns = new News();
				ns.setUserCreate(logonUser);
				ns.setNewsId(newsDAO.getMaxId() + 1);
				ns.setNewsHead(title_News);
				ns.setNewsDescription(detail_News);
				ns.setTimeCreate(DateUtil.getCurrentTime());
				newsDAO.save(ns);
				request.setAttribute(NEWSFEED, newsDAO.listnews(5));
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	public String dashList() {
		try {
			User ur = (User) request.getSession().getAttribute(ONLINEUSER); 
			String logonUser = ur.getId();
    		request.setAttribute("sumtravel", newsDAO.sumtravelPrice());
    		request.setAttribute("News", newsDAO.dashboardNews());
    		request.setAttribute("Goodem", newsDAO.mostcometoWork());
    		request.setAttribute("totalborrowNows", newsDAO.sumItem());
    		request.setAttribute("myobtainMoney", newsDAO.obtainTravel(logonUser));
    		
    		int yearNow = DateUtil.checkCurrentYear();
    		if(yearNow > 2500)
    		{
    			yearNow = yearNow - 543;
    		}
    		request.setAttribute("mytravel", newsDAO.totaltravel(logonUser));
    		request.setAttribute("myleaves", newsDAO.totallyleaves(logonUser,yearNow));
    		   		
    		BigDecimal x1 = new BigDecimal(0);
			BigDecimal x2 = new BigDecimal(0);
			BigDecimal x3 = new BigDecimal(0);
			BigDecimal x4 ;
			BigDecimal x5 = new BigDecimal(0);
			BigDecimal x6 = new BigDecimal(0);
			BigDecimal x7 = new BigDecimal(0);
			List<Map<String, Object>> wanla1 = leaveDAO.findleaveallByType(logonUser,1);
			List<Map<String, Object>> wanla2 = leaveDAO.findleaveallByType(logonUser,2);
			List<Map<String, Object>> wanla3 = leaveDAO.findleaveallByType(logonUser,3);
			List<Map<String, Object>> wanla5 = leaveDAO.findleaveallByType(logonUser,4);
			List<Map<String, Object>> wanla6 = leaveDAO.findleaveallByType(logonUser,5);
			List<Map<String, Object>> wanla7 = leaveDAO.findleaveallByType(logonUser,9);
			int n1 = wanla1.size();
			int n2 = wanla2.size();
			int n3 = wanla3.size();
			int n5 = wanla5.size();
			int n6 = wanla6.size();
			int n9 = wanla7.size();
			for(int i=0 ; i<n1 ;i++){
				BigDecimal a = (BigDecimal) wanla1.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x1 = b.add(x1);
			
				
				request.setAttribute("x1", x1);
				
			}
			
			for(int i=0 ; i<n2 ;i++){
				BigDecimal a = (BigDecimal) wanla2.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x2 = b.add(x2);
			
				
				request.setAttribute("x2", x2);
				
			}
			
			for(int i=0 ; i<n3 ;i++){
				BigDecimal a = (BigDecimal) wanla3.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x3 = b.add(x3);
			
				
				request.setAttribute("x3", x3);
				
			}
			for(int i=0 ; i<n5 ;i++){
				BigDecimal a = (BigDecimal) wanla5.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x5 = b.add(x5);
			
				
				request.setAttribute("x5", x5);
				}
			
			for(int i=0 ; i<n6 ;i++){
				BigDecimal a = (BigDecimal) wanla6.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x6 = b.add(x6);
			
				
				request.setAttribute("x6", x6);
				
			}
			for(int i=0 ; i<n9 ;i++){
				BigDecimal a = (BigDecimal) wanla7.get(i).get(NODAY);
				
				BigDecimal b = a;
				
				x7 = b.add(x7);
			
				
				request.setAttribute("x7", x7);
				
			}
			x4 = x1.add(x2).add(x3).add(x5).add(x6).add(x7);
			request.setAttribute("x4", x4);
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
}
