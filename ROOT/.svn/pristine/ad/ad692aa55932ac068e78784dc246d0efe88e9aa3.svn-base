package com.cubesofttech.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DemoAction extends ActionSupport {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2280661337420278284L;
	
	Logger log = Logger.getLogger(getClass());
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    String pageName;
    

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String demo() {
        try {
        	return this.pageName;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
 

}
