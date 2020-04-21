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
@Table(name = "file_feedback")
@NamedQueries({
    @NamedQuery(name = "FileFeedback.findAll", query = "SELECT t FROM FileFeedback t")})
public class FileFeedback implements Serializable {
	public FileFeedback() {
		
	}
	
public FileFeedback(
		Integer fileIdfb	
        , String userId	
        , String uniqueKey	
        , String name	
        , String type	
        , String size	
        , String path	
        , String userCreate	
        , String userUpdate	
        , java.sql.Timestamp timeCreate	
        , java.sql.Timestamp timeUpdate	
     ) {
	this.fileIdfb = fileIdfb;	
    this.userId = userId;
    this.uniqueKey = uniqueKey;
    this.name = name;	
    this.type = type;	
    this.size = size;	
    this.path = path;	
    this.userCreate = userCreate;	
    this.userUpdate = userUpdate;	
    this.timeCreate = timeCreate;	
    this.timeUpdate = timeUpdate;	
	
	
		
	}
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "file_idfb")
		private Integer fileIdfb;	
		@Column(name = "user_id")
		private String userId;
		@Column(name = "unique_Key")
		private String uniqueKey;	
		@Column(name = "name")
		private String name;	
		@Column(name = "type")
		private String type;	
		@Column(name = "size")
		private String size;	
		@Column(name = "path")
		private String path;	
		@Column(name = "user_create")
		private String userCreate;	
		@Column(name = "user_update")
		private String userUpdate;	
		@Column(name = "time_create")
		private java.sql.Timestamp timeCreate;	
		@Column(name = "time_update")
		private java.sql.Timestamp timeUpdate;
		
		public Integer getFileIdfb() {
			return fileIdfb;
		}

		public void setFileIdfb(Integer fileIdfb) {
			this.fileIdfb = fileIdfb;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUniqueKey() {
			return uniqueKey;
		}

		public void setUniqueKey(String uniqueKey) {
			this.uniqueKey = uniqueKey;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getUserCreate() {
			return userCreate;
		}

		public void setUserCreate(String userCreate) {
			this.userCreate = userCreate;
		}

		public String getUserUpdate() {
			return userUpdate;
		}

		public void setUserUpdate(String userUpdate) {
			this.userUpdate = userUpdate;
		}

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
		
		public String toString() {
	        return super.toString() + "fileIdfb=[" + fileIdfb + "]\n" + "userId=[" + userId + "]\n"+ "uniqueKey=[" + uniqueKey + "]\n" + "typeId=["  + "name=[" + name + "]\n" + "type=[" + type + "]\n" + "size=[" + size + "]\n" + "path=[" + path + "]\n" + "userCreate=[" + userCreate + "]\n" + "userUpdate=[" + userUpdate + "]\n" + "timeCreate=[" + timeCreate + "]\n" + "timeUpdate=[" + timeUpdate + "]\n";
	    }
		
		 public boolean equals(Object obj) {
		        if (this == obj) {
		                return true;
		        }
		        if (!(obj instanceof FileFeedback)) {
		                return false;
		        }
		        FileFeedback that = (FileFeedback) obj;
		        if (!(that.getFileIdfb() == null ? this.getFileIdfb() == null
		                        : that.getFileIdfb().equals(this.getFileIdfb()))) {
		                return false;
		        }
		        if (!(that.getUserId() == null ? this.getUserId() == null
		                        : that.getUserId().equals(this.getUserId()))) {
		                return false;
		        }
		        if (!(that.getUniqueKey() == null ? this.getUniqueKey() == null
                        : that.getUniqueKey().equals(this.getUniqueKey()))) {
		        		return false;
        }
		        if (!(that.getName() == null ? this.getName() == null
		                        : that.getName().equals(this.getName()))) {
		                return false;
		        }
		        if (!(that.getType() == null ? this.getType() == null
		                        : that.getType().equals(this.getType()))) {
		                return false;
		        }
		        if (!(that.getSize() == null ? this.getSize() == null
		                        : that.getSize().equals(this.getSize()))) {
		                return false;
		        }
		        if (!(that.getPath() == null ? this.getPath() == null
		                        : that.getPath().equals(this.getPath()))) {
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
