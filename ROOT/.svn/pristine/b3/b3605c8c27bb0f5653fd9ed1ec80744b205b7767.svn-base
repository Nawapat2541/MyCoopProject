package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.TravelList;

@Repository
public class TravelDAOImpl implements TravelDAO  {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(TravelDAOImpl.class);
	
	
	@Override
	public void save(TravelList travelList) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(travelList);
		session.flush();
	}

	
	@Override
	public List<TravelList> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<TravelList> travelList = null;
		try {
			String sql = "SELECT * FROM exp_travel_type ORDER BY exp_travel_type_id ASC; ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			travelList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return travelList;
	}
	

	@Override
	public TravelList findById(int exp_travel_type_id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		TravelList travelList = (TravelList) session.get(TravelList.class,exp_travel_type_id);
		return travelList;
	}

	@Override
	public void update(TravelList travelList) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(travelList);
		session.flush();
		
	}

	@Override
	public void delete(TravelList travelList) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(travelList);
		session.flush();
		
	}


	@Override
	public List<TravelList> findByTravelListId(String travelList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


	@Override
	public List<TravelList> searchtable(String date)  throws Exception{
		
		Session session = this.sessionFactory.getCurrentSession();
		 List<TravelList> TravelList = null;
		  try {
		   String sql = "SELECT * FROM exp_travel_type WHERE user_create; ";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.addEntity(TravelList.class);
		  
		   TravelList = query.list();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
			  
		  }
		  return TravelList;
	}


	@Override
	public List<TravelList> searchalluser() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Map<String, Object>> findByName(String keyword) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> leaveType = null;
		try {
			String sql = " SELECT name FROM exp_travel_type WHERE name = :keyword ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("keyword",keyword);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			leaveType = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return leaveType;	
	}	
	



}