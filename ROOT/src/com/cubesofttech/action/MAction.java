package com.cubesofttech.action;
import com.opensymphony.xwork2.ActionSupport;

public class MAction extends ActionSupport{
	
	public String test() {
		try {
					
					return SUCCESS;
				} catch (Exception e) {
					
					
					return ERROR;
				}
		   }
	
	public String testA() {
		try {
					
					return SUCCESS;
				} catch (Exception e) {
					
					
					return ERROR;
				}
		   }
}
