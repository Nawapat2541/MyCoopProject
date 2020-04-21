package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.Timesheet;


public interface TimesheetDAO {
    public void save(Timesheet timesheet) throws Exception;
    public List<Map<String, Object>> sequense() throws Exception;
    public List<Timesheet> findAll() throws Exception;
    Integer getMaxId() throws Exception;
    public void update(Timesheet timesheet) throws Exception;
    public void delete(Timesheet timesheet) throws Exception;
    public Timesheet findById(Integer id) throws Exception;
	public List<Map<String, Object>> findTimeInTimeOutBytimeMonthYear( String user,String startDay,String endDay ) throws Exception;
	public List<Map<String, Object>> findDescription( String user,String startDay,String endDay) throws Exception;
	public List<Map<String, Object>> searchIdTimesheet( String id) throws Exception;
	public List<Map<String, Object>> searchTimesheet( String user,String date) throws Exception;
	public List<Map<String, Object>> searchTimesheetByUserCreate( String userId) throws Exception;
	public List<Map<String, Object>> searchTimesheetByTimeUpdate( String userId) throws Exception;
	public List<Map<String, Object>> timesheetSearch_forCalendar(String userId, String from, String to) throws Exception;
	public List<Timesheet> findAll_calendar() throws Exception;
	//public List<Map<String, Object>> checkholiday() throws Exception ;
	List<Map<String, Object>> findHoliday() throws Exception;
	List<Map<String, Object>> findHoliday2() throws Exception;
	
}
   