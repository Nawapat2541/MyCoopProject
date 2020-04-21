package com.cubesofttech.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")})
public class Feedback implements Serializable{
	
	public Feedback() {
		
	}
	
	public Feedback(
			Integer idfeedback
			,String title
			,String description
			,String status
			,String uniqueKey
			,String descriptionRes
			, java.sql.Timestamp timeCreate
			, String userCreate	
			, java.sql.Timestamp timeUpdate
			, String userUpdate	) {
		this.idfeedback = idfeedback;
		this.title = title;
		this.description = description;
		this.status = status;
		this.uniquekey = uniqueKey;
		this.descriptionRes = descriptionRes;
		this.timeCreate = timeCreate;
		this.userCreate = userCreate;
		this.timeUpdate = timeUpdate;
		this.userUpdate = userUpdate;
		
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "idfeedback")
	 private Integer idfeedback;
	 @Column(name = "title")
	 private String title;
	 @Column(name = "description")
	 private String description;
	 @Column(name = "status")
	 private String status;
	 @Column(name = "unique_key")
	 private String uniquekey;
	 @Column(name = "time_create")
	 private java.sql.Timestamp timeCreate;
	 @Column(name = "user_create")
	 private String userCreate;
	 @Column(name = "time_update")
	 private java.sql.Timestamp timeUpdate;
	 @Column(name = "user_update")
	 private String userUpdate;
	 @Column(name = "description_res")
	 private String descriptionRes;
	 
	public String getDescriptionRes() {
		return descriptionRes;
	}

	public void setDescriptionRes(String descriptionRes) {
		this.descriptionRes = descriptionRes;
	}

	public Integer getIdfeedback() {
		return idfeedback;
	}

	public void setIdfeedback(Integer idfeedback) {
		this.idfeedback = idfeedback;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUniquekey() {
		return uniquekey;
	}

	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}

	public java.sql.Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(java.sql.Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public java.sql.Timestamp getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(java.sql.Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	
	
	public String toString() {
        return super.toString() + "idfeedback=[" + idfeedback + "]\n"+ "title=[" + title + "]\n" + "description=[" + description + "]\n"+ "status=[" + status + "]\n"+ "uniquekey=["+ uniquekey + "]\n" + "userCreate=[" + userCreate + "]\n" + "userUpdate=[" + userUpdate + "]\n" + "timeCreate=[" + timeCreate + "]\n"+ "descriptionRes=[" + descriptionRes + "]\n" + "timeUpdate=[" + timeUpdate + "]\n";
    }
	
	
	 public boolean equals(Object obj) {
	        if (this == obj) {
	                return true;
	        }
	        if (!(obj instanceof Feedback)) {
	                return false;
	        }
	        Feedback that = (Feedback) obj;
	        if (!(that.getIdfeedback() == null ? this.getIdfeedback() == null
	                        : that.getIdfeedback().equals(this.getIdfeedback()))) {
	                return false;
	        }
	        if (!(that.getTitle() == null ? this.getTitle() == null
                    : that.getTitle().equals(this.getTitle()))) {
            return false;
    }
	        if (!(that.getDescription() == null ? this.getDescription() == null
	                        : that.getDescription().equals(this.getDescription()))) {
	                return false;
	        }
	        if (!(that.getStatus() == null ? this.getStatus() == null
	                		: that.getStatus().equals(this.getStatus()))) {
	        		return false;
	        }
	        if (!(that.getDescriptionRes() == null ? this.getDescriptionRes() == null
    		: that.getDescriptionRes().equals(this.getDescriptionRes()))) {
	        	return false;
	        }
	        if (!(that.getUniquekey() == null ? this.getUniquekey() == null
            		: that.getUniquekey().equals(this.getUniquekey()))) {
    		return false;
	        }
	        if (!(that.getUserCreate() == null ? this.getUserCreate() == null
	                        : that.getUserCreate().equals(this.getUserCreate()))) {
	                return false;
	        }
	        if (!(that.getUserUpdate() == null ? this.getUserUpdate() == null
	                        : that.getUserUpdate().equals(this.getUserUpdate()))) {
	                return false;
	        }
	        if (!(that.getTimeCreate() == null ? this.getTimeCreate() == null
	                        : that.getTimeCreate().equals(this.getTimeCreate()))) {
	                return false;
	        }
	        if (!(that.getTimeUpdate() == null ? this.getTimeUpdate() == null
	                        : that.getTimeUpdate().equals(this.getTimeUpdate()))) {
	                return false;
	        }
	    return true;
	    }
	 
}
