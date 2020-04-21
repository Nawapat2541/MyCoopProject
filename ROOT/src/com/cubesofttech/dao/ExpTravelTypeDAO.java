package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.ExpTravelType;

public interface ExpTravelTypeDAO {

	public void save(ExpTravelType exptraveltype) throws Exception;
	
	Integer getMaxId() throws Exception;
	
	public List<ExpTravelType> findAll() throws Exception;
	
	public List<Map<String, Object>> search(String other) throws Exception;

	public List<ExpTravelType> findByExpTravelTypeId(String exptraveltypeId) throws Exception;

	public ExpTravelType findById(String id) throws Exception;

	public void update(ExpTravelType exptraveltype) throws Exception;

	public void delete(ExpTravelType exptraveltype) throws Exception;

	public List<Map<String, Object>> findAllType() throws Exception;
	
	public List<Map<String, Object>> findByExpId(String expType) throws Exception;

}
