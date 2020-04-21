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
@Table(name = "expense_group")
@NamedQueries({
    @NamedQuery(name = "ExpenseGroup.findAll", query = "SELECT t FROM ExpenseGroup t")})
public class ExpenseGroup implements Serializable {
    
    /** Creates a new instance of ExpenseGroup */
    public ExpenseGroup() {
    }
    public ExpenseGroup(
            Integer expenseGroupId	
            , String expTypeId	
            , BigDecimal totalAmount	
            , String statusId	
            , String userId	
            , Short paidMonth	
            , Integer paidYear	
            , String userCreate	
            , String userUpdate	
            , java.sql.Timestamp timeCreate	
            , java.sql.Timestamp timeUpdate	
        ) {
        this.expenseGroupId = expenseGroupId;	
        this.expTypeId = expTypeId;	
        this.totalAmount = totalAmount;	
        this.statusId = statusId;	
        this.userId = userId;	
        this.paidMonth = paidMonth;	
        this.paidYear = paidYear;	
        this.userCreate = userCreate;	
        this.userUpdate = userUpdate;	
        this.timeCreate = timeCreate;	
        this.timeUpdate = timeUpdate;	
    }
    
    @Id
    @Column(name = "expense_group_id")
    private Integer expenseGroupId;	
    @Column(name = "exp_type_id")
    private String expTypeId;	
    @Column(name = "total_amount")
    private BigDecimal totalAmount;	
    @Column(name = "status_id")
    private String statusId;	
    @Column(name = "user_id")
    private String userId;	
    @Column(name = "paid_month")
    private Short paidMonth;	
    @Column(name = "paid_year")
    private Integer paidYear;	
    @Column(name = "user_create")
    private String userCreate;	
    @Column(name = "user_update")
    private String userUpdate;	
    @Column(name = "time_create")
    private java.sql.Timestamp timeCreate;	
    @Column(name = "time_update")
    private java.sql.Timestamp timeUpdate;	



    public Integer getExpenseGroupId() {
        return this.expenseGroupId;
    }		
    public void setExpenseGroupId(Integer expenseGroupId) {
        this.expenseGroupId = expenseGroupId;
    }
    public String getExpTypeId() {
        return this.expTypeId;
    }		
    public void setExpTypeId(String expTypeId) {
        this.expTypeId = expTypeId;
    }
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }		
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatusId() {
        return this.statusId;
    }		
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    public String getUserId() {
        return this.userId;
    }		
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Short getPaidMonth() {
        return this.paidMonth;
    }		
    public void setPaidMonth(Short paidMonth) {
        this.paidMonth = paidMonth;
    }
    public Integer getPaidYear() {
        return this.paidYear;
    }		
    public void setPaidYear(Integer paidYear) {
        this.paidYear = paidYear;
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
        return super.toString() + "expenseGroupId=[" + expenseGroupId + "]\n" + "expTypeId=[" + expTypeId + "]\n" + "totalAmount=[" + totalAmount + "]\n" + "statusId=[" + statusId + "]\n" + "userId=[" + userId + "]\n" + "paidMonth=[" + paidMonth + "]\n" + "paidYear=[" + paidYear + "]\n" + "userCreate=[" + userCreate + "]\n" + "userUpdate=[" + userUpdate + "]\n" + "timeCreate=[" + timeCreate + "]\n" + "timeUpdate=[" + timeUpdate + "]\n";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
                return true;
        }
        if (!(obj instanceof ExpenseGroup)) {
                return false;
        }
        ExpenseGroup that = (ExpenseGroup) obj;
        if (!(that.getExpenseGroupId() == null ? this.getExpenseGroupId() == null
                        : that.getExpenseGroupId().equals(this.getExpenseGroupId()))) {
                return false;
        }
        if (!(that.getExpTypeId() == null ? this.getExpTypeId() == null
                        : that.getExpTypeId().equals(this.getExpTypeId()))) {
                return false;
        }
        if (!(that.getTotalAmount() == null ? this.getTotalAmount() == null
                        : that.getTotalAmount().equals(this.getTotalAmount()))) {
                return false;
        }
        if (!(that.getStatusId() == null ? this.getStatusId() == null
                        : that.getStatusId().equals(this.getStatusId()))) {
                return false;
        }
        if (!(that.getUserId() == null ? this.getUserId() == null
                        : that.getUserId().equals(this.getUserId()))) {
                return false;
        }
        if (!(that.getPaidMonth() == null ? this.getPaidMonth() == null
                        : that.getPaidMonth().equals(this.getPaidMonth()))) {
                return false;
        }
        if (!(that.getPaidYear() == null ? this.getPaidYear() == null
                        : that.getPaidYear().equals(this.getPaidYear()))) {
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
