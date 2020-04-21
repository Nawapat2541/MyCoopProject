
package com.cubesofttech.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "id_running")
@NamedQueries({
    @NamedQuery(name = "IdRunning.findAll", query = "SELECT t FROM IdRunning t")})
public class IdRunning implements Serializable {
    
    /** Creates a new instance of IdRunning */
    public IdRunning() {
    }
    public IdRunning(
            String id	
            , String pkId	
            , String name	
            , Integer runningNo	
            , java.sql.Timestamp timeCreate	
            , java.sql.Timestamp timeUpdate	
        ) {
        this.id = id;	
        this.pkId = pkId;	
        this.name = name;	
        this.runningNo = runningNo;	
        this.timeCreate = timeCreate;	
        this.timeUpdate = timeUpdate;	
    }
    
    @Id
    @Column(name = "id")
    private String id;	
    @Column(name = "pk_id")
    private String pkId;	
    @Column(name = "name")
    private String name;	
    @Column(name = "running_no")
    private Integer runningNo;	
    @Column(name = "time_create")
    private java.sql.Timestamp timeCreate;	
    @Column(name = "time_update")
    private java.sql.Timestamp timeUpdate;	



    public String getId() {
        return this.id;
    }		
    public void setId(String id) {
        this.id = id;
    }
    public String getPkId() {
        return this.pkId;
    }		
    public void setPkId(String pkId) {
        this.pkId = pkId;
    }
    public String getName() {
        return this.name;
    }		
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRunningNo() {
        return this.runningNo;
    }		
    public void setRunningNo(Integer runningNo) {
        this.runningNo = runningNo;
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
        return super.toString() + "id=[" + id + "]\n" + "pkId=[" + pkId + "]\n" + "name=[" + name + "]\n" + "runningNo=[" + runningNo + "]\n" + "timeCreate=[" + timeCreate + "]\n" + "timeUpdate=[" + timeUpdate + "]\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
                return true;
        }
        if (!(obj instanceof IdRunning)) {
                return false;
        }
        IdRunning that = (IdRunning) obj;
        if (!(that.getId() == null ? this.getId() == null
                        : that.getId().equals(this.getId()))) {
                return false;
        }
        if (!(that.getPkId() == null ? this.getPkId() == null
                        : that.getPkId().equals(this.getPkId()))) {
                return false;
        }
        if (!(that.getName() == null ? this.getName() == null
                        : that.getName().equals(this.getName()))) {
                return false;
        }
        if (!(that.getRunningNo() == null ? this.getRunningNo() == null
                        : that.getRunningNo().equals(this.getRunningNo()))) {
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
