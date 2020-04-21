package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.FileFeedback;


public interface FileFeedbackDAO {
	
public void save(FileFeedback filefeedback) throws Exception;
    
    public List<FileFeedback> findAll() throws Exception;
    
    public FileFeedback findById(Integer id) throws Exception;
    
    public void update(FileFeedback filefeedback) throws Exception;
    
    public void delete(FileFeedback filefeedback) throws Exception;
    
    Integer getMaxId() throws Exception;

	List<FileFeedback> findByuser(String user) throws Exception;
	
	public List<Map<String, Object>> findUni(String uni) throws Exception;
	public FileFeedback findByKey(String id) throws Exception ;

	public List<FileFeedback> findByunique(String user) throws Exception ;
	
	public void deleteAllUnique(String user,String uni) throws Exception;
}
