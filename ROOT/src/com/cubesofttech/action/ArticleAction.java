package com.cubesofttech.action;

import java.io.Console;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cubesofttech.dao.ArticleDAO;
import com.cubesofttech.dao.ArticleTagDAO;
import com.cubesofttech.dao.ArticleTypeDAO;
import com.cubesofttech.dao.TagArDAO;
import com.cubesofttech.dao.UserDAO;
import com.cubesofttech.dao.FileUploadDAO;
import com.cubesofttech.model.Article;
import com.cubesofttech.model.ArticleTag;
import com.cubesofttech.model.RoleAuthorizedObject;
import com.cubesofttech.model.TagAr;
import com.cubesofttech.model.User;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.util.DateUtil;
import com.cubesofttech.util.FileUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	@Autowired
	private ArticleDAO articleDAO;

	@Autowired
	private ArticleTypeDAO articleTypeDAO;

	@Autowired
	private ArticleTagDAO articleTagDAO;

	@Autowired
	private TagArDAO tagArDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ArticleTagDAO articletagDAO;

	@Autowired
	private FileUploadDAO fileuploadDAO;

	private Article article;
	private int articleId;
	private File fileUpload;
	private String fileUploadSize;
	private String fileUploadFileName;
	private String detail;
	private String topic;
	private String type;
	private String tags;
	private String path;
	private int fileId;
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadSize() {
		return fileUploadSize;
	}

	public void setFileUploadSize(String fileUploadSize) {
		this.fileUploadSize = fileUploadSize;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public static final String USERSEQ = "userseq";
	public static final String USERID = "userId";
	public static final String ARTICLEID = "articleId";
	public static final String ARTICLELIST = "articleList";

	public String init() {
		try {
			request.setAttribute("articleList", articleDAO.findAll());
			request.setAttribute("articleType", articleTypeDAO.findAll());
			request.setAttribute("tag", tagArDAO.findAll());
			List<Map<String, Object>> cubesoftUsers = userDAO.Query_Userlist();
			request.setAttribute("cubesoftUsers", cubesoftUsers);

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String article_add() {
		try {
			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();
			log.info(logonUser);

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
			}

			Article article = new Article();
			article.setTopic(topic); // use parameter from upper part of this file
			article.setArticleTypeId(type); // use parameter from upper part of this file
			article.setDetail(detail); // use parameter from upper part of this file
			article.setUserId(author); // use parameter from upper part of this file

			if (fileUpload != null) {
				article.setFileId(Integer.toString(maxId));
			}

			article.setArticleId(articleDAO.getMaxId() + 1);
			article.setTimeCreate(DateUtil.getCurrentTime());
			article.setUserCreate(logonUser);
			articleDAO.save(article);

			if (tags != "" || tags != null) { // use parameter from upper part of this file
				String[] tag = tags.split(",");
				log.debug(tag.length);
				for (int i = 0; i < tag.length; i++) {
					tag[i] = tag[i].replaceAll("\\s+", " ");
					tag[i] = tag[i].trim();

					ArticleTag at = new ArticleTag();
					at.setArticleId(Integer.toString(articleDAO.getMaxId()));

					List<Integer> check = tagArDAO.findByTagArId(tag[i]);
					if (check.isEmpty()) {/* if there is a new tag */
						TagAr t = new TagAr();
						t.setTagId(tagArDAO.getMaxId() + 1);
						t.settagArName(tag[i]);
						tagArDAO.save(t);
						at.setTagId(Integer.toString(tagArDAO.getMaxId()));
					} else {/* if there already have one */
						at.setTagId(Integer.toString(check.get(0)));

					}
					articleTagDAO.save(at);
				}
			} else {
				request.setAttribute("articleList", articleDAO.findAll());
				request.setAttribute("articleType", articleTypeDAO.findAll());
				return SUCCESS;
			}

			request.setAttribute("articleList", articleDAO.findAll());
			request.setAttribute("articleType", articleTypeDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String openEdit() {
		try {
			List<Map<String, Object>> userseq = userDAO.sequense();
			request.setAttribute(USERSEQ, userseq);

			String articleId = request.getParameter(ARTICLEID);

			String userId = request.getParameter(USERID);
			request.setAttribute(ARTICLELIST, articleDAO.findAll());
			request.setAttribute(USERID, userId);
			request.setAttribute("arList", articleDAO.findAllNews());
			request.setAttribute("article", articleDAO.findByArticleId(getArticleId()));
			request.setAttribute("tag", tagArDAO.findAll());
			request.setAttribute("articleType", articleTypeDAO.findAll());
			request.setAttribute("ArticleInTag", tagArDAO.findArticleInTag());

			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String edit() {
		try {


			User ur = (User) request.getSession().getAttribute("onlineUser");
			String logonUser = ur.getId();

			Article ar = articleDAO.findByArticleId(articleId);
			ar.setArticleTypeId(type); 
			ar.setTopic(topic);
			ar.setDetail(detail);
			ar.setTimeUpdate(DateUtil.getCurrentTime());
			ar.setUserUpdate(logonUser);
			articleDAO.update(ar);

			articletagDAO.deleteByArticleId(Integer.toString(ar.getArticleId()));

			int file_id = fileId;

			FileUpload fileupload = new FileUpload();
			int picture = file_id;
			if ("".equals(picture)) {
				if (fileUpload != null) {
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

					ar.setFileId(Integer.toString(maxId));
					articleDAO.update(ar);
				}
			} else {
				if (fileUpload != null) {
					int maxId2 = fileuploadDAO.getMaxId() + 1;
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

					ar.setFileId(Integer.toString(maxId2));
					articleDAO.update(ar);
				}
			}

			// String[] articletags = request.getParameterValues("articletag");
			if (tags != null) { // use parameter from upper part of this file
				String[] tag = tags.split(",");
				log.debug(tag.length);
				for (int i = 0; i < tag.length; i++) {

					ArticleTag at = new ArticleTag();
					int arid = ar.getArticleId();
					at.setArticleId(Integer.toString(arid));

					List<Integer> check = tagArDAO.findByTagArId(tag[i]);
					if (check.isEmpty()) {/* if there is a new tag */
						TagAr t = new TagAr();
						t.setTagId(tagArDAO.getMaxId() + 1);
						t.settagArName(tag[i]);
						tagArDAO.save(t);
						at.setTagId(Integer.toString(tagArDAO.getMaxId()));
					} else {/* if there already have one */
						at.setTagId(Integer.toString(check.get(0)));

					}
					articleTagDAO.save(at);

				}
			} else {
				request.setAttribute("articleList", articleDAO.findAll());
				request.setAttribute("articleType", articleTypeDAO.findAll());
				request.setAttribute("tag", tagArDAO.findAll());
				return SUCCESS;
			}
			request.setAttribute("articleList", articleDAO.findAll());
			request.setAttribute("articleType", articleTypeDAO.findAll());
			request.setAttribute("tag", tagArDAO.findAll());
			return SUCCESS;
		} catch (

		Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String delete() {

		try {
			log.info("Pass");
			String listId = request.getParameter("id");
			int arId = Integer.parseInt(listId);
			Article ar_delete = new Article();
			ar_delete = articleDAO.findByArticleId(arId);
			articleDAO.delete(ar_delete);

			articletagDAO.deleteByArticleId(Integer.toString(arId));

			request.setAttribute("articleList", articleDAO.findAll());
			request.setAttribute("articleType", articleTypeDAO.findAll());
			request.setAttribute("tag", tagArDAO.findAll());
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}

	public String articleList() {
		try {
			
			
	        Gson gson = new GsonBuilder().create();
	        
	        String responseJSON = gson.toJson(articleDAO.findAllNews());
	      
	        
			request.setAttribute("json", responseJSON);
			log.debug(responseJSON);

	
			return SUCCESS;
		} catch (Exception e) {
			log.error(e);
			return ERROR;
		}
	}
	
	public String open_article_graph() throws Exception{
		try {
			request.setAttribute("writer", articleDAO.writers());
			request.setAttribute("type", articleDAO.types());
			request.setAttribute("tag", articleDAO.tags());
		}
		catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
}
