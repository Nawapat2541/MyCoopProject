package com.cubesofttech.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@NamedQueries({ @NamedQuery(name = "Article.findAll", query = "SELECT t FROM Article t") })
public class Article implements Serializable{

	/** Creates a new instance of Article */
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public Article(Integer articleId,
					String articleTypeId,
					String detail,
					String topic,
					String userId,
					java.sql.Timestamp timeCreate,
					java.sql.Timestamp timeUpdate,
					String userUpdate,
					String fileId,
					String userCreate
			) {
		this.articleId = articleId;
	}
	
	@Id
	@Column(name = "article_id")
	private Integer articleId;
	
	@Column(name = "article_type_id")
	private String articleTypeId;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "file_id")
	private String fileId;
	
	@Column(name = "user_create")
	private String userCreate;
	
	@Column(name = "time_create")
	private java.sql.Timestamp timeCreate;
	
	
	@Column(name = "user_update")
	private String userUpdate;
	
	
	@Column(name = "time_update")
	private java.sql.Timestamp timeUpdate;


	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(String articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public java.sql.Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(java.sql.Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public java.sql.Timestamp getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(java.sql.Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	
}
