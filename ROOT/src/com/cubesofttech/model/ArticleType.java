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
@Table(name = "article_type")
@NamedQueries({ @NamedQuery(name = "ArticleType.findAll", query = "SELECT t FROM ArticleType t") })
public class ArticleType implements Serializable {

	public ArticleType() {
		
	}
	
	public ArticleType(Integer articleTypeId
			,String articleTypeName
			,String articleTypeDescription
			,String userCreate
			,java.sql.Timestamp timeCreate
			,String userUpdate
			,java.sql.Timestamp timeUpdate) {
		this.articleTypeId=articleTypeId;
		this.articleTypeName=articleTypeName;
		this.articleTypeDescription=articleTypeDescription;
		this.userCreate=userCreate;
		this.userUpdate=userUpdate;
		this.timeUpdate=timeUpdate;
	}
	
	
	

	@Id
	@Column(name = "article_type_id")
	private Integer articleTypeId;
	
	@Column(name = "name")
	private String articleTypeName;
	
	@Column(name = "description")
	private String articleTypeDescription;
	
	@Column(name = "user_create")
	private String userCreate;
	
	@Column(name = "time_create")
	private java.sql.Timestamp timeCreate;
	
	
	@Column(name = "user_update")
	private String userUpdate;
	
	
	@Column(name = "time_update")
	private java.sql.Timestamp timeUpdate;


	public Integer getarticleTypeId() {
		return articleTypeId;
	}

	public void setarticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getarticleTypeName() {
		return articleTypeName;
	}

	public void setarticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public String getarticleTypeDescription() {
		return articleTypeDescription;
	}

	public void setarticleTypeDescription(String articleTypeDescription) {
		this.articleTypeDescription = articleTypeDescription;
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
