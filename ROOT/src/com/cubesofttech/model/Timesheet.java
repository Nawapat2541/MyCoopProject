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
@Table(name = "timesheet")
@NamedQueries({
    @NamedQuery(name = "Timesheet.findAll", query = "SELECT t FROM Timesheet t")})
public class Timesheet implements Serializable {
    
    /** Creates a new instance of Timesheet */
    public Timesheet() {
    }
    public Timesheet(
            Integer id	
            , String description	
            , java.sql.Timestamp timeCheckIn	
            , java.sql.Timestamp timeCheckOut
            , String status	
            , String userCreate	
            , String userUpdate	
            , java.sql.Timestamp timeCreate	
            , java.sql.Timestamp timeUpdate	
        ) {
        this.id = id;	
        this.description = description;	
        this.timeCheckIn = timeCheckIn;	
        this.timeCheckOut = timeCheckOut;
        this.status = status;
        this.userCreate = userCreate;	
        this.userUpdate = userUpdate;	
        this.timeCreate = timeCreate;	
        this.timeUpdate = timeUpdate;	
    }
    
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;	
    @Column(name = "description")
    private String description;	
    @Column(name = "time_check_in")
    private java.sql.Timestamp timeCheckIn;	
    @Column(name = "time_check_out")
    private java.sql.Timestamp timeCheckOut;	
    @Column(name = "status")
    private String status;	
	@Column(name = "user_create")
    private String userCreate;	
    @Column(name = "user_update")
    private String userUpdate;	
    @Column(name = "time_create")
    private java.sql.Timestamp timeCreate;	
    @Column(name = "time_update")
    private java.sql.Timestamp timeUpdate;	



    public Integer getId() {
        return this.id;
    }		
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }		
    public void setDescription(String description) {
        this.description = description;
    }
    public java.sql.Timestamp getTimeCheckIn() {
        return this.timeCheckIn;
    }		
    public void setTimeCheckIn(java.sql.Timestamp timeCheckIn) {
        this.timeCheckIn = timeCheckIn;
    }
    public java.sql.Timestamp getTimeCheckOut() {
        return this.timeCheckOut;
    }		
    public void setTimeCheckOut(java.sql.Timestamp timeCheckOut) {
        this.timeCheckOut = timeCheckOut;
    }
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    public String getUserCreate() {
        return this.userCreate;
    }		
    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
    public String getUserUpdate() {
        return this.userUpdate;
    }		
    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }
    public java.sql.Timestamp getTimeCreate() {
        return this.timeCreate;
    }		
    public void setTimeCreate(java.sql.Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }
    public java.sql.Timestamp getTimeUpdate() {
        return this.timeUpdate;
    }		
    public void setTimeUpdate(java.sql.Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }


    
    public String toString() {
        return super.toString() + "id=[" + id + "]\n" + "description=[" + description + "]\n" + "timeCheckIn=[" + timeCheckIn + "]\n" + "timeCheckOut=[" + timeCheckOut + "]\n"+ "status=[" + status + "]\n" + "userCreate=[" + userCreate + "]\n" + "userUpdate=[" + userUpdate + "]\n" + "timeCreate=[" + timeCreate + "]\n" + "timeUpdate=[" + timeUpdate + "]\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
                return true;
        }
        if (!(obj instanceof Timesheet)) {
                return false;
        }
        Timesheet that = (Timesheet) obj;
        if (!(that.getId() == null ? this.getId() == null
                        : that.getId().equals(this.getId()))) {
                return false;
        }
        if (!(that.getDescription() == null ? this.getDescription() == null
                        : that.getDescription().equals(this.getDescription()))) {
                return false;
        }
        if (!(that.getTimeCheckIn() == null ? this.getTimeCheckIn() == null
                        : that.getTimeCheckIn().equals(this.getTimeCheckIn()))) {
                return false;
        }
        if (!(that.getTimeCheckOut() == null ? this.getTimeCheckOut() == null
                        : that.getTimeCheckOut().equals(this.getTimeCheckOut()))) {
                return false;
        }
        if (!(that.getStatus() == null ? this.getStatus() == null
                		: that.getStatus().equals(this.getStatus()))) {
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
