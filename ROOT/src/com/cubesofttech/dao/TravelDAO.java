package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.TravelList;

public interface TravelDAO {

	public void save(TravelList travelList) throws Exception;

	public List<TravelList> findAll() throws Exception;

	public List<TravelList> findByTravelListId(String travelList) throws Exception;

	public  TravelList findById(int id_depart) throws Exception;
	public void update(TravelList travelList) throws Exception;

	public void delete(TravelList travelList) throws Exception;
	public List<Map<String, Object>> findByName(String keyword) throws Exception;
	public List<TravelList> searchtable(String date) throws Exception;
	public List<TravelList> searchalluser() throws Exception;
	
	


}
