package com.cubesofttech.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_date")
	private Long id_date;

	@Column(name = "start_date")
	private java.sql.Date start_date;
	@Column(name = "end_date")
	private java.sql.Date end_date;

	

	public java.sql.Date getStart_date() {
		return start_date;
	}
	public void setStart_date(java.sql.Date start_date) {
		this.start_date = start_date;
	}
	@Column(name = "head", length = 200)
	public String head;
	@Column(name = "description", length = 2000)
	public String description;
	
	
 public Long getId_date() {
		return id_date;
	}
	public void setId_date(Long id_date) {
		this.id_date = id_date;
	}
	
	/*@Column(name = "time_create", length = 150)
	public java.sql.Timestamp time_create;
	@Column(name = "user_create", length = 150)
	public String user_create;
*/
		public Holiday(Date start_date, Date end_date, String head, String description) {
			super();
			this.start_date = start_date;
			this.end_date = end_date;
			this.head = head;
			this.description = description;
		}
		public java.sql.Date getEnd_date() {
			return end_date;
		}
		public void setEnd_date(java.sql.Date end_date) {
			this.end_date = end_date;
		}
		public Holiday() {
			super();
			// TODO Auto-generated constructor stub
		}

	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
