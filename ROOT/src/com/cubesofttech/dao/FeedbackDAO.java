package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.cubesofttech.model.Feedback;
public interface FeedbackDAO {
	public void save(Feedback feedback) throws Exception;
	public void update(Feedback feedback) throws Exception;
	public void delete(Feedback feedback) throws Exception;
	public List<Feedback> findAll() throws Exception;
	public List<Map<String, Object>> searchtable(Timestamp startDate, Timestamp endDate) throws Exception;
	public List<Map<String, Object>> searchid(Integer id) throws Exception;
	public Feedback findById(Integer id) throws Exception;
	public List<Map<String, Object>> findByUni(String uni) throws Exception;
	public List<Map<String,Object>> findlistuser(String user) throws Exception;
	public List<Map<String, Object>> searchkey(Integer id) throws Exception;
}
