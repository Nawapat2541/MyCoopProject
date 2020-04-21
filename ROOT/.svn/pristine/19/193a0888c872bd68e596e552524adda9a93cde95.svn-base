package com.cubesofttech.dao;
import java.util.List;
import java.util.Map;

import com.cubesofttech.model.Holiday;
public interface HolidayDAO {

	
	public Holiday findById(long id_date) throws Exception;
	public List<Holiday> findAll() throws Exception;
	public void save(Holiday holiday) throws Exception;
	public void update(Holiday holiday) throws Exception;
	public void delete(Holiday holiday) throws Exception;
	public List<Holiday> searchBycolumn(String column,String keyword) throws Exception;
	
	public List<Map<String, Object>> findByDate(java.sql.Date keyword) throws Exception; //à¸„à¹‰à¸™à¸«à¸²à¸§à¸±à¸™à¸—à¸µà¹ˆ
	public List<Holiday> findByMonth(String keyword) throws Exception; //à¸„à¹‰à¸™à¸«à¸²à¹€à¸”à¸·à¸­à¸™
	public  List<Holiday> searchtable(String date) throws Exception;
	public List<Object> searchallyear() throws Exception;
	public List<Holiday> protect(Holiday holiday) throws Exception;// à¸›à¹‰à¸­à¸‡à¸�à¸±à¸™ à¸�à¸²à¸£à¹�à¸—à¸£à¸�à¸§à¸±à¸™à¸«à¸¢à¸¸à¸”
	List<Holiday> protect_edit(Holiday holiday) throws Exception;
	List<Holiday> protect_edit1(Holiday holiday) throws Exception;
	public List<Holiday> findnext_Year(String keyword) throws Exception;
	public List<Map<String, Object>> findAll1() throws Exception;
	Long getMaxId() throws Exception;
	public List<Holiday> getall();
	public String getallOnlyDateJSON();
	public List<Map<String, Object>>test_holiday(int year);
}

