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
@Table(name = "tag")
@NamedQueries({ @NamedQuery(name = "TagAr.findAll", query = "SELECT t FROM TagAr t") })
public class TagAr  {

	public TagAr() {
		
	}

	public TagAr(Integer tagArId
			,String tagArName
			,String tagDescription
			,String userCreate
			,java.sql.Timestamp timeCreate
			,String userUpdate
			,java.sql.Timestamp timeUpdate
			) {
		this.tagArId=tagArId;
		this.tagArName=tagArName;
		this.tagDescription=tagDescription;
		this.userCreate=userCreate;
		this.timeCreate=timeCreate;
		this.userUpdate=userUpdate;
		this.timeUpdate=timeUpdate;
	}
	
	@Id
	@Column(name = "tag_id")
	private Integer tagArId;
	
	@Column(name = "name")
	private String tagArName;
	
	@Column(name = "description")
	private String tagDescription;
	
	@Column(name = "user_create")
	private String userCreate;
	
	@Column(name = "time_create")
	private java.sql.Timestamp timeCreate;
	
	
	@Column(name = "user_update")
	private String userUpdate;
	
	
	@Column(name = "time_update")
	private java.sql.Timestamp timeUpdate;


	public Integer getTagArId() {
		return tagArId;
	}

	public void setTagId(Integer tagArId) {
		this.tagArId = tagArId;
	}

	public String gettagArName() {
		return tagArName;
	}

	public void settagArName(String tagArName) {
		this.tagArName = tagArName;
	}

	public String getTagDescription() {
		return tagDescription;
	}

	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
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
