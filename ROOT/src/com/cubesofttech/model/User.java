
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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT t FROM User t")})
public class User implements Serializable {
    
    /** Creates a new instance of User */
    public User() {
    }
    public User(
            String id	
            , String roleId	
            , String departmentId	
            , String managerId	
            , String positionId	
            , String employeeId	
            , String name	
            , String nickName	
            , String password	
            , String email	
            , String emailPassword	
            , String emailEnable	
            , java.sql.Date birthDate	
            , String address	
            , java.sql.Date startDate	
            , java.sql.Date endDate	
            , String workDayStart	
            , String workDayEnd	
            , String workTimeStart	
            , String workTimeEnd	
            , BigDecimal latestSalary	
            , String eduInstitute1	
            , String eduInstitute2	
            , String eduInstitute3	
            , String eduInstitute4	
            , String eduDurStart1	
            , String eduDurStart2	
            , String eduDurStart3	
            , String eduDurStart4	
            , String eduDurEnd1	
            , String eduDurEnd2	
            , String eduDurEnd3	
            , String eduDurEnd4	
            , String eduDegree1	
            , String eduDegree2	
            , String eduDegree3	
            , String eduDegree4	
            , String enable	
            , BigDecimal leaveQuota1	
            , BigDecimal leaveQuota2	
            , BigDecimal leaveQuota3	
            , BigDecimal leaveQuota4	
            , java.sql.Timestamp timeCreate	
            , java.sql.Timestamp timeUpdate	
            , String emailHost	
            , java.sql.Timestamp passwordUpdate	
            , Integer loginFailed	
            , java.sql.Timestamp lastLoginFailedTime	
            , String path
            , String line_id
            , String facebookid
            , String flagSearch
            , String phone_num
            , String gender
        ) {
        this.id = id;	
        this.roleId = roleId;	
        this.departmentId = departmentId;	
        this.managerId = managerId;	
        this.positionId = positionId;	
        this.employeeId = employeeId;	
        this.name = name;	
        this.nickName = nickName;	
        this.password = password;	
        this.email = email;	
        this.emailPassword = emailPassword;	
        this.emailEnable = emailEnable;	
        this.birthDate = birthDate;	
        this.address = address;	
        this.startDate = startDate;	
        this.endDate = endDate;	
        this.workDayStart = workDayStart;	
        this.workDayEnd = workDayEnd;	
        this.workTimeStart = workTimeStart;	
        this.workTimeEnd = workTimeEnd;	
        this.latestSalary = latestSalary;	
        this.eduInstitute1 = eduInstitute1;	
        this.eduInstitute2 = eduInstitute2;	
        this.eduInstitute3 = eduInstitute3;	
        this.eduInstitute4 = eduInstitute4;	
        this.eduDurStart1 = eduDurStart1;	
        this.eduDurStart2 = eduDurStart2;	
        this.eduDurStart3 = eduDurStart3;	
        this.eduDurStart4 = eduDurStart4;	
        this.eduDurEnd1 = eduDurEnd1;	
        this.eduDurEnd2 = eduDurEnd2;	
        this.eduDurEnd3 = eduDurEnd3;	
        this.eduDurEnd4 = eduDurEnd4;	
        this.eduDegree1 = eduDegree1;	
        this.eduDegree2 = eduDegree2;	
        this.eduDegree3 = eduDegree3;	
        this.eduDegree4 = eduDegree4;	
        this.enable = enable;	
        this.leaveQuota1 = leaveQuota1;	
        this.leaveQuota2 = leaveQuota2;	
        this.leaveQuota3 = leaveQuota3;	
        this.leaveQuota4 = leaveQuota4;	
        this.timeCreate = timeCreate;	
        this.timeUpdate = timeUpdate;	
        this.emailHost = emailHost;	
        this.passwordUpdate = passwordUpdate;	
        this.loginFailed = loginFailed;	
        this.lastLoginFailedTime = lastLoginFailedTime;	
        this.path = path;
        this.line_id = line_id;
        this.facebookid = facebookid;
        this.flagSearch = flagSearch;
        this.phone_num = phone_num;
        this.gender = gender;

    }
    
    @Id
    @Column(name = "id")
    private String id;	
    @Column(name = "role_id")
    private String roleId;	
    @Column(name = "department_id")
    private String departmentId;	
    @Column(name = "manager_id")
    private String managerId;	
    @Column(name = "position_id")
    private String positionId;	
    @Column(name = "employee_id")
    private String employeeId;	
    @Column(name = "name")
    private String name;	
    @Column(name = "nick_name")
    private String nickName;	
    @Column(name = "password")
    private String password;	
    @Column(name = "email")
    private String email;	
    @Column(name = "email_password")
    private String emailPassword;	
    @Column(name = "email_enable")
    private String emailEnable;	
    @Column(name = "birth_date")
    private java.sql.Date birthDate;	
    @Column(name = "flag_search")
    private String flagSearch;
    @Column(name = "address")
    private String address;	
    @Column(name = "start_date")
    private java.sql.Date startDate;	
    @Column(name = "end_date")
    private java.sql.Date endDate;	
    @Column(name = "work_day_start")
    private String workDayStart;	
    @Column(name = "work_day_end")
    private String workDayEnd;	
    @Column(name = "work_time_start")
    private String workTimeStart;	
    @Column(name = "work_time_end")
    private String workTimeEnd;	
    @Column(name = "latest_salary")
    private BigDecimal latestSalary;	
    @Column(name = "edu_institute_1")
    private String eduInstitute1;	
    @Column(name = "edu_institute_2")
    private String eduInstitute2;	
    @Column(name = "edu_institute_3")
    private String eduInstitute3;	
    @Column(name = "edu_institute_4")
    private String eduInstitute4;	
    @Column(name = "edu_dur_start_1")
    private String eduDurStart1;	
    @Column(name = "edu_dur_start_2")
    private String eduDurStart2;	
    @Column(name = "edu_dur_start_3")
    private String eduDurStart3;	
    @Column(name = "edu_dur_start_4")
    private String eduDurStart4;	
    @Column(name = "edu_dur_end_1")
    private String eduDurEnd1;	
    @Column(name = "edu_dur_end_2")
    private String eduDurEnd2;	
    @Column(name = "edu_dur_end_3")
    private String eduDurEnd3;	
    @Column(name = "edu_dur_end_4")
    private String eduDurEnd4;	
    @Column(name = "edu_degree_1")
    private String eduDegree1;	
    @Column(name = "edu_degree_2")
    private String eduDegree2;	
    @Column(name = "edu_degree_3")
    private String eduDegree3;	
    @Column(name = "edu_degree_4")
    private String eduDegree4;	
    @Column(name = "enable")
    private String enable;	
    @Column(name = "leave_quota_1")
    private BigDecimal leaveQuota1;	
    @Column(name = "leave_quota_2")
    private BigDecimal leaveQuota2;	
    @Column(name = "leave_quota_3")
    private BigDecimal leaveQuota3;	
    @Column(name = "leave_quota_lastyear")
    private BigDecimal leaveQuota4; //this is last year	
    @Column(name = "time_create")
    private java.sql.Timestamp timeCreate;	
    @Column(name = "time_update")
    private java.sql.Timestamp timeUpdate;	
    @Column(name = "email_host")
    private String emailHost;	
    @Column(name = "password_update")
    private java.sql.Timestamp passwordUpdate;	
    @Column(name = "login_failed")
    private Integer loginFailed;	
    @Column(name = "last_login_failed_time")
    private java.sql.Timestamp lastLoginFailedTime;	
    @Column(name = "path")
    private String path;
    @Column(name = "facebookid")
    private String facebookid;
    //line
    @Column(name = "line_id")
    private String line_id;
    @Column(name = "phone_num")
    private String phone_num;
    @Column(name = "gender")
    private String gender;
    



    public String getFlagSearch() {
		return flagSearch;
	}
	public void setFlagSearch(String flagSearch) {
		this.flagSearch = flagSearch;
	}
	public BigDecimal getLeaveQuota4() {
		return leaveQuota4;
	}
	public void setLeaveQuota4(BigDecimal leaveQuota4) {
		this.leaveQuota4 = leaveQuota4;
	}
	public String getId() {
        return this.id;
    }		
    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return this.roleId;
    }		
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getDepartmentId() {
        return this.departmentId;
    }		
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getManagerId() {
        return this.managerId;
    }		
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    public String getPositionId() {
        return this.positionId;
    }		
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }		
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return this.name;
    }		
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return this.nickName;
    }		
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return this.password;
    }		
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }		
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmailPassword() {
        return this.emailPassword;
    }		
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }
    public String getEmailEnable() {
        return this.emailEnable;
    }		
    public void setEmailEnable(String emailEnable) {
        this.emailEnable = emailEnable;
    }
    public java.sql.Date getBirthDate() {
        return this.birthDate;
    }		
    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getAddress() {
        return this.address;
    }		
    public void setAddress(String address) {
        this.address = address;
    }
    public java.sql.Date getStartDate() {
        return this.startDate;
    }		
    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }
    public java.sql.Date getEndDate() {
        return this.endDate;
    }		
    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }
    public String getWorkDayStart() {
        return this.workDayStart;
    }		
    public void setWorkDayStart(String workDayStart) {
        this.workDayStart = workDayStart;
    }
    public String getWorkDayEnd() {
        return this.workDayEnd;
    }		
    public void setWorkDayEnd(String workDayEnd) {
        this.workDayEnd = workDayEnd;
    }
    public String getWorkTimeStart() {
        return this.workTimeStart;
    }		
    public void setWorkTimeStart(String workTimeStart) {
        this.workTimeStart = workTimeStart;
    }
    public String getWorkTimeEnd() {
        return this.workTimeEnd;
    }		
    public void setWorkTimeEnd(String workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }
    public BigDecimal getLatestSalary() {
        return this.latestSalary;
    }		
    public void setLatestSalary(BigDecimal latestSalary) {
        this.latestSalary = latestSalary;
    }
    public String getEduInstitute1() {
        return this.eduInstitute1;
    }		
    public void setEduInstitute1(String eduInstitute1) {
        this.eduInstitute1 = eduInstitute1;
    }
    public String getEduInstitute2() {
        return this.eduInstitute2;
    }		
    public void setEduInstitute2(String eduInstitute2) {
        this.eduInstitute2 = eduInstitute2;
    }
    public String getEduInstitute3() {
        return this.eduInstitute3;
    }		
    public void setEduInstitute3(String eduInstitute3) {
        this.eduInstitute3 = eduInstitute3;
    }
    public String getEduInstitute4() {
        return this.eduInstitute4;
    }		
    public void setEduInstitute4(String eduInstitute4) {
        this.eduInstitute4 = eduInstitute4;
    }
    public String getEduDurStart1() {
        return this.eduDurStart1;
    }		
    public void setEduDurStart1(String eduDurStart1) {
        this.eduDurStart1 = eduDurStart1;
    }
    public String getEduDurStart2() {
        return this.eduDurStart2;
    }		
    public void setEduDurStart2(String eduDurStart2) {
        this.eduDurStart2 = eduDurStart2;
    }
    public String getEduDurStart3() {
        return this.eduDurStart3;
    }		
    public void setEduDurStart3(String eduDurStart3) {
        this.eduDurStart3 = eduDurStart3;
    }
    public String getEduDurStart4() {
        return this.eduDurStart4;
    }		
    public void setEduDurStart4(String eduDurStart4) {
        this.eduDurStart4 = eduDurStart4;
    }
    public String getEduDurEnd1() {
        return this.eduDurEnd1;
    }		
    public void setEduDurEnd1(String eduDurEnd1) {
        this.eduDurEnd1 = eduDurEnd1;
    }
    public String getEduDurEnd2() {
        return this.eduDurEnd2;
    }		
    public void setEduDurEnd2(String eduDurEnd2) {
        this.eduDurEnd2 = eduDurEnd2;
    }
    public String getEduDurEnd3() {
        return this.eduDurEnd3;
    }		
    public void setEduDurEnd3(String eduDurEnd3) {
        this.eduDurEnd3 = eduDurEnd3;
    }
    public String getEduDurEnd4() {
        return this.eduDurEnd4;
    }		
    public void setEduDurEnd4(String eduDurEnd4) {
        this.eduDurEnd4 = eduDurEnd4;
    }
    public String getEduDegree1() {
        return this.eduDegree1;
    }		
    public void setEduDegree1(String eduDegree1) {
        this.eduDegree1 = eduDegree1;
    }
    public String getEduDegree2() {
        return this.eduDegree2;
    }		
    public void setEduDegree2(String eduDegree2) {
        this.eduDegree2 = eduDegree2;
    }
    public String getEduDegree3() {
        return this.eduDegree3;
    }		
    public void setEduDegree3(String eduDegree3) {
        this.eduDegree3 = eduDegree3;
    }
    public String getEduDegree4() {
        return this.eduDegree4;
    }		
    public void setEduDegree4(String eduDegree4) {
        this.eduDegree4 = eduDegree4;
    }
    public String getEnable() {
        return this.enable;
    }		
    public void setEnable(String enable) {
        this.enable = enable;
    }
    public BigDecimal getLeaveQuota1() {
        return this.leaveQuota1;
    }		
    public void setLeaveQuota1(BigDecimal leaveQuota1) {
        this.leaveQuota1 = leaveQuota1;
    }
    public BigDecimal getLeaveQuota2() {
        return this.leaveQuota2;
    }		
    public void setLeaveQuota2(BigDecimal leaveQuota2) {
        this.leaveQuota2 = leaveQuota2;
    }
    public BigDecimal getLeaveQuota3() {
        return this.leaveQuota3;
    }		
    public void setLeaveQuota3(BigDecimal leaveQuota3) {
        this.leaveQuota3 = leaveQuota3;
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
    public String getEmailHost() {
        return this.emailHost;
    }		
    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }
    public java.sql.Timestamp getPasswordUpdate() {
        return this.passwordUpdate;
    }		
    public void setPasswordUpdate(java.sql.Timestamp passwordUpdate) {
        this.passwordUpdate = passwordUpdate;
    }
    public Integer getLoginFailed() {
        return this.loginFailed;
    }		
    public void setLoginFailed(Integer loginFailed) {
        this.loginFailed = loginFailed;
    }
    public java.sql.Timestamp getLastLoginFailedTime() {
        return this.lastLoginFailedTime;
    }		
    public void setLastLoginFailedTime(java.sql.Timestamp lastLoginFailedTime) {
        this.lastLoginFailedTime = lastLoginFailedTime;
    }
    public String getPath() {
        return this.path;
    }		
    public void setPath(String path) {
        this.path = path;
    }
    public String getFacebookid() {
        return this.facebookid;
    }		
    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }
    
//    line
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getPhonenum() {
        return this.phone_num;
    }		
    public void setPhonenum(String phone_num) {
        this.phone_num = phone_num;
    }
	public String getGender() {
        return this.gender;
    }		
    public void setGender(String gender) {
        this.gender = gender;
    }


    
    public String toString() {
        return super.toString() + "id=[" + id + "]\n" + "roleId=[" + roleId + "]\n" + "departmentId=[" + departmentId + "]\n" + "managerId=[" + managerId + "]\n" + "positionId=[" + positionId + "]\n" + "employeeId=[" + employeeId + "]\n" + "name=[" + name + "]\n" + "nickName=[" + nickName + "]\n" + "password=[" + password + "]\n" + "email=[" + email + "]\n" + "emailPassword=[" + emailPassword + "]\n" + "emailEnable=[" + emailEnable + "]\n" + "birthDate=[" + birthDate + "]\n" + "address=[" + address + "]\n" + "startDate=[" + startDate + "]\n" + "endDate=[" + endDate + "]\n" + "workDayStart=[" + workDayStart + "]\n" + "workDayEnd=[" + workDayEnd + "]\n" + "workTimeStart=[" + workTimeStart + "]\n" + "workTimeEnd=[" + workTimeEnd + "]\n" + "latestSalary=[" + latestSalary + "]\n" + "eduInstitute1=[" + eduInstitute1 + "]\n" + "eduInstitute2=[" + eduInstitute2 + "]\n" + "eduInstitute3=[" + eduInstitute3 + "]\n" + "eduInstitute4=[" + eduInstitute4 + "]\n" + "eduDurStart1=[" + eduDurStart1 + "]\n" + "eduDurStart2=[" + eduDurStart2 + "]\n" + "eduDurStart3=[" + eduDurStart3 + "]\n" + "eduDurStart4=[" + eduDurStart4 + "]\n" + "eduDurEnd1=[" + eduDurEnd1 + "]\n" + "eduDurEnd2=[" + eduDurEnd2 + "]\n" + "eduDurEnd3=[" + eduDurEnd3 + "]\n" + "eduDurEnd4=[" + eduDurEnd4 + "]\n" + "eduDegree1=[" + eduDegree1 + "]\n" + "eduDegree2=[" + eduDegree2 + "]\n" + "eduDegree3=[" + eduDegree3 + "]\n" + "eduDegree4=[" + eduDegree4 + "]\n" + "enable=[" + enable + "]\n" + "leaveQuota1=[" + leaveQuota1 + "]\n" + "leaveQuota2=[" + leaveQuota2 + "]\n" + "leaveQuota3=[" + leaveQuota3 + "]\n" + "timeCreate=[" + timeCreate + "]\n" + "timeUpdate=[" + timeUpdate + "]\n" + "emailHost=[" + emailHost + "]\n" + "passwordUpdate=[" + passwordUpdate + "]\n" + "loginFailed=[" + loginFailed + "]\n" + "lastLoginFailedTime=[" + lastLoginFailedTime + "]\n" + "path=[" + path + "]\n"+ "facebookid=[" + facebookid + "]\n"+ "line_id=[" + line_id + "]\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
                return true;
        }
        if (!(obj instanceof User)) {
                return false;
        }
        User that = (User) obj;
        if (!(that.getId() == null ? this.getId() == null
                        : that.getId().equals(this.getId()))) {
                return false;
        }
        if (!(that.getRoleId() == null ? this.getRoleId() == null
                        : that.getRoleId().equals(this.getRoleId()))) {
                return false;
        }
        if (!(that.getDepartmentId() == null ? this.getDepartmentId() == null
                        : that.getDepartmentId().equals(this.getDepartmentId()))) {
                return false;
        }
        if (!(that.getManagerId() == null ? this.getManagerId() == null
                        : that.getManagerId().equals(this.getManagerId()))) {
                return false;
        }
        if (!(that.getPositionId() == null ? this.getPositionId() == null
                        : that.getPositionId().equals(this.getPositionId()))) {
                return false;
        }
        if (!(that.getEmployeeId() == null ? this.getEmployeeId() == null
                        : that.getEmployeeId().equals(this.getEmployeeId()))) {
                return false;
        }
        if (!(that.getName() == null ? this.getName() == null
                        : that.getName().equals(this.getName()))) {
                return false;
        }
        if (!(that.getNickName() == null ? this.getNickName() == null
                        : that.getNickName().equals(this.getNickName()))) {
                return false;
        }
        if (!(that.getPassword() == null ? this.getPassword() == null
                        : that.getPassword().equals(this.getPassword()))) {
                return false;
        }
        if (!(that.getEmail() == null ? this.getEmail() == null
                        : that.getEmail().equals(this.getEmail()))) {
                return false;
        }
        if (!(that.getEmailPassword() == null ? this.getEmailPassword() == null
                        : that.getEmailPassword().equals(this.getEmailPassword()))) {
                return false;
        }
        if (!(that.getEmailEnable() == null ? this.getEmailEnable() == null
                        : that.getEmailEnable().equals(this.getEmailEnable()))) {
                return false;
        }
        if (!(that.getBirthDate() == null ? this.getBirthDate() == null
                        : that.getBirthDate().equals(this.getBirthDate()))) {
                return false;
        }
        if (!(that.getAddress() == null ? this.getAddress() == null
                        : that.getAddress().equals(this.getAddress()))) {
                return false;
        }
        if (!(that.getStartDate() == null ? this.getStartDate() == null
                        : that.getStartDate().equals(this.getStartDate()))) {
                return false;
        }
        if (!(that.getEndDate() == null ? this.getEndDate() == null
                        : that.getEndDate().equals(this.getEndDate()))) {
                return false;
        }
        if (!(that.getWorkDayStart() == null ? this.getWorkDayStart() == null
                        : that.getWorkDayStart().equals(this.getWorkDayStart()))) {
                return false;
        }
        if (!(that.getWorkDayEnd() == null ? this.getWorkDayEnd() == null
                        : that.getWorkDayEnd().equals(this.getWorkDayEnd()))) {
                return false;
        }
        if (!(that.getWorkTimeStart() == null ? this.getWorkTimeStart() == null
                        : that.getWorkTimeStart().equals(this.getWorkTimeStart()))) {
                return false;
        }
        if (!(that.getWorkTimeEnd() == null ? this.getWorkTimeEnd() == null
                        : that.getWorkTimeEnd().equals(this.getWorkTimeEnd()))) {
                return false;
        }
        if (!(that.getLatestSalary() == null ? this.getLatestSalary() == null
                        : that.getLatestSalary().equals(this.getLatestSalary()))) {
                return false;
        }
        if (!(that.getEduInstitute1() == null ? this.getEduInstitute1() == null
                        : that.getEduInstitute1().equals(this.getEduInstitute1()))) {
                return false;
        }
        if (!(that.getEduInstitute2() == null ? this.getEduInstitute2() == null
                        : that.getEduInstitute2().equals(this.getEduInstitute2()))) {
                return false;
        }
        if (!(that.getEduInstitute3() == null ? this.getEduInstitute3() == null
                        : that.getEduInstitute3().equals(this.getEduInstitute3()))) {
                return false;
        }
        if (!(that.getEduInstitute4() == null ? this.getEduInstitute4() == null
                        : that.getEduInstitute4().equals(this.getEduInstitute4()))) {
                return false;
        }
        if (!(that.getEduDurStart1() == null ? this.getEduDurStart1() == null
                        : that.getEduDurStart1().equals(this.getEduDurStart1()))) {
                return false;
        }
        if (!(that.getEduDurStart2() == null ? this.getEduDurStart2() == null
                        : that.getEduDurStart2().equals(this.getEduDurStart2()))) {
                return false;
        }
        if (!(that.getEduDurStart3() == null ? this.getEduDurStart3() == null
                        : that.getEduDurStart3().equals(this.getEduDurStart3()))) {
                return false;
        }
        if (!(that.getEduDurStart4() == null ? this.getEduDurStart4() == null
                        : that.getEduDurStart4().equals(this.getEduDurStart4()))) {
                return false;
        }
        if (!(that.getEduDurEnd1() == null ? this.getEduDurEnd1() == null
                        : that.getEduDurEnd1().equals(this.getEduDurEnd1()))) {
                return false;
        }
        if (!(that.getEduDurEnd2() == null ? this.getEduDurEnd2() == null
                        : that.getEduDurEnd2().equals(this.getEduDurEnd2()))) {
                return false;
        }
        if (!(that.getEduDurEnd3() == null ? this.getEduDurEnd3() == null
                        : that.getEduDurEnd3().equals(this.getEduDurEnd3()))) {
                return false;
        }
        if (!(that.getEduDurEnd4() == null ? this.getEduDurEnd4() == null
                        : that.getEduDurEnd4().equals(this.getEduDurEnd4()))) {
                return false;
        }
        if (!(that.getEduDegree1() == null ? this.getEduDegree1() == null
                        : that.getEduDegree1().equals(this.getEduDegree1()))) {
                return false;
        }
        if (!(that.getEduDegree2() == null ? this.getEduDegree2() == null
                        : that.getEduDegree2().equals(this.getEduDegree2()))) {
                return false;
        }
        if (!(that.getEduDegree3() == null ? this.getEduDegree3() == null
                        : that.getEduDegree3().equals(this.getEduDegree3()))) {
                return false;
        }
        if (!(that.getEduDegree4() == null ? this.getEduDegree4() == null
                        : that.getEduDegree4().equals(this.getEduDegree4()))) {
                return false;
        }
        if (!(that.getEnable() == null ? this.getEnable() == null
                        : that.getEnable().equals(this.getEnable()))) {
                return false;
        }
        if (!(that.getLeaveQuota1() == null ? this.getLeaveQuota1() == null
                        : that.getLeaveQuota1().equals(this.getLeaveQuota1()))) {
                return false;
        }
        if (!(that.getLeaveQuota2() == null ? this.getLeaveQuota2() == null
                        : that.getLeaveQuota2().equals(this.getLeaveQuota2()))) {
                return false;
        }
        if (!(that.getLeaveQuota3() == null ? this.getLeaveQuota3() == null
                        : that.getLeaveQuota3().equals(this.getLeaveQuota3()))) {
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
        if (!(that.getEmailHost() == null ? this.getEmailHost() == null
                        : that.getEmailHost().equals(this.getEmailHost()))) {
                return false;
        }
        if (!(that.getPasswordUpdate() == null ? this.getPasswordUpdate() == null
                        : that.getPasswordUpdate().equals(this.getPasswordUpdate()))) {
                return false;
        }
        if (!(that.getLoginFailed() == null ? this.getLoginFailed() == null
                        : that.getLoginFailed().equals(this.getLoginFailed()))) {
                return false;
        }
        if (!(that.getLastLoginFailedTime() == null ? this.getLastLoginFailedTime() == null
                        : that.getLastLoginFailedTime().equals(this.getLastLoginFailedTime()))) {
                return false;
        }
        if (!(that.getPath() == null ? this.getPath() == null
                        : that.getPath().equals(this.getPath()))) {
                return false;
        }
        if (!(that.getFacebookid() == null ? this.getFacebookid() == null
                : that.getFacebookid().equals(this.getFacebookid()))) {
        return false;
        }
        if (!(that.getPhonenum() == null ? this.getPhonenum() == null
                : that.getPhonenum().equals(this.getPhonenum()))) {
        return false;
        }
        if (!(that.getGender() == null ? this.getGender() == null
                : that.getGender().equals(this.getGender()))) {
        return false;
        }
    return true;
    }

}
