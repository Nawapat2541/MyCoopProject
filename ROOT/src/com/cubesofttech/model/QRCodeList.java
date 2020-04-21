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
@Table(name = "qrcode_list")
@NamedQueries({ @NamedQuery(name = "QRCodeList.findAll", query = "SELECT t FROM QRCodeList t") })
public class QRCodeList implements Serializable{

	/** Creates a new instance of Article */
	public QRCodeList() {
		// TODO Auto-generated constructor stub
	}
	
	public QRCodeList(Integer qrId,
					String status,
					java.sql.Timestamp timeCreate,
					java.sql.Timestamp timeUpdate
			) {
		this.qrId = qrId;
		this.status = status;
		this.timeCreate = timeCreate;
		this.timeUpdate = timeUpdate;
	}
	
	@Id
	@Column(name = "qr_id")
	private Integer qrId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "time_create")
	private java.sql.Timestamp timeCreate;
	
	public Integer getQrId() {
		return qrId;
	}

	public void setQrId(Integer qrId) {
		this.qrId = qrId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "time_update")
	private java.sql.Timestamp timeUpdate;

	public java.sql.Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(java.sql.Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public java.sql.Timestamp getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(java.sql.Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	
}
