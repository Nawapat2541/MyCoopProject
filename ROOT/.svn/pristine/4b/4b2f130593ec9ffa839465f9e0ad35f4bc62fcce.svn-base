package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.ExpenseGroup;

public interface ExpenseGroupDAO {

	public void save(ExpenseGroup expensegroup) throws Exception;

	public List<ExpenseGroup> findAll() throws Exception;

	public ExpenseGroup findByGroupId(int expenseGroupId) throws Exception;

	public ExpenseGroup findById(String id) throws Exception;

	public ExpenseGroup findById(int id) throws Exception;

	public void update(ExpenseGroup expensegroup) throws Exception;

	public void delete(ExpenseGroup expensegroup) throws Exception;

	Integer getMaxId() throws Exception;

	public List<Map<String, Object>> searchtable(String Expenseuserid) throws Exception;
	public List<Map<String, Object>> searchtable2(String Expenseuserid,java.sql.Timestamp Datestart, java.sql.Timestamp Dateend) throws Exception;
	public List<Map<String, Object>> searchAll(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend) throws Exception;
	public List<Map<String, Object>> listId() throws Exception;

	List<Map<String, Object>> listgroup(String expgroupId) throws Exception;

	public List<Map<String, Object>> findtype() throws Exception;

}
