
package com.cubesofttech.dao;

import java.util.List;

import java.util.Map;

import com.cubesofttech.model.Expense;
import com.cubesofttech.model.ExpenseGroup;

public interface ExpenseDAO {

	public void save(Expense expense) throws Exception;

	public List<Expense> findAll() throws Exception;
	
	public List<Map<String, Object>> sequense() throws Exception;

	public List<Expense> findByExpenseId(int expenseId) throws Exception;

	public Expense findById(int i) throws Exception;

	public void update(Expense expense) throws Exception;

	public void delete(Expense expense) throws Exception;

	public List<Map<String, Object>> findExpense() throws Exception;

	Integer getMaxId() throws Exception;

	public List<Map<String, Object>> expSearch(String userId, String from, String to) throws Exception;

	public Expense findById(String id) throws Exception;

	List<Map<String, Object>> expSearch_forCalendar(String userId, String from, String to) throws Exception;

	public int[] count_exp_type() throws Exception; // Rader Charts

	List<Map<String, Object>> expSearch(String userId, String from, String to, String status_id) throws Exception;

//	public List<Map<String, Object>> listgroup() throws Exception;
	List<Map<String, Object>> findUserbyYear(String year) throws Exception;
	List<Map<String, Object>> ReportByuser_PAID(String year , String user) throws Exception;
	List<Map<String, Object>> ReportByuser_REJECT(String year , String user) throws Exception;
	List<Map<String, Object>> ReportByuser_REQUEST(String year , String user) throws Exception;

	
	public List<Map<String, Object>> findByYearMonth(int year, int month) throws Exception;

	List<Expense> findByExpId(int expenseId) throws Exception;

	public String findAmountByExpenseGroupId(int expenseGroupId) throws Exception;

	public List<Map<String, Object>> findAmountByStatus(String status , String top, int year) throws Exception;

	public List<Map<String, Object>> findAmount(String top , int year) throws Exception;

	public List<Map<String, Object>> findAmountBy(String name, String expType, String expStatus , String top ,  int year) throws Exception;
	
	public List<Map<String, Object>> findAmountBy(String name, String expType, int year , String field) throws Exception;

	public List<Map<String, Object>> findFeildAsUserId(String name, String expType, int thisyear , int toyear , String findYear)  throws Exception;
	public List<Map<String, Object>> test_travelsummary(String userId, int year) throws Exception;
	public List<Map<String, Object>> test_travelsummaryP(String userId, int year) throws Exception;
	public List<Map<String, Object>> test_travelsummaryA(String userId, int year) throws Exception;
	public List<Map<String, Object>> test_travelsummaryW(String userId, int year) throws Exception;
	public List<Map<String, Object>> test_travelsummaryR(String userId, int year) throws Exception;
	
	public List<Map<String, Object>> test_travelamount(int year) throws Exception ;
	public List<Map<String, Object>> test_travelamountAll(int year) throws Exception ;
}
