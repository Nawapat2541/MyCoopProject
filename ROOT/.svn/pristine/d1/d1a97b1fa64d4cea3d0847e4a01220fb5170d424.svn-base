package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Feedback;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Feedback feedback) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(feedback);
		session.flush();
		
	}

	@Override
	public void update(Feedback feedback) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(feedback);
		session.flush();
		
	}

	@Override
	public void delete(Feedback feedback) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(feedback);
		session.flush();
	}

	@Override
	public List<Feedback> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Feedback> feedback = null;
		try {
			String sql = "SELECT * FROM feedback";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			feedback = query.list();
		} catch (Exception e) {

		}
		return feedback;
	}

	@Override
	public List<Map<String, Object>> searchtable(Timestamp startDate, Timestamp endDate) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT feedback.idfeedback,feedback.title,feedback.time_create, "
					+ "feedback.user_create,feedback.status,feedback.description FROM feedback "
					+ " WHERE time_create BETWEEN :startDate AND :endDate "
					+ " ORDER BY time_create DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public List<Map<String, Object>> searchid(Integer id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		
		try {
			String sql = "SELECT feedback.idfeedback,feedback.title,feedback.time_create,"
					+ "feedback.time_update, "
					+ "feedback.user_create,feedback.status,feedback.description ,feedback.description_res ,feedback.unique_key FROM feedback "
					+ " WHERE idfeedback = '" + id +"'";
			
			SQLQuery query = session.createSQLQuery(sql);
			//query.setParameter("id", id);
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		return search;
	}
	
	@Override
	public Feedback findById(Integer id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Feedback feedback = (Feedback) session.get(Feedback.class,id);
		return feedback;
	}
	@Override
	public List<Map<String,Object>> findlistuser(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		 List<Map<String,Object>> feedbacklist = null;
		  try {
			 
		   String sql = "SELECT * FROM feedback WHERE user_create ='"+user+"'  ";
		   SQLQuery query = session.createSQLQuery(sql);
		   
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		   feedbacklist = query.list();
		 	
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		   // session.close();
		  }
		  
		return feedbacklist;
	}
	@Override
	public List<Map<String, Object>> findByUni(String uni) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT feedback.idfeedback FROM feedback "
					+ " WHERE unique_key = '" + uni +"'";
			
			SQLQuery query = session.createSQLQuery(sql);
			//query.setParameter("id", id);
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		return search;
	}
	@Override
	public List<Map<String, Object>> searchkey(Integer id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		
		try {
			String sql = "SELECT feedback.unique_key FROM feedback "
					+ " WHERE unique_key = '" + id +"'";
			
			SQLQuery query = session.createSQLQuery(sql);
			//query.setParameter("id", id);
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		return search;
	}

}
