package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.FileFeedback;
@Repository
public class FileFeedbackDAOImpl implements FileFeedbackDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public void save(FileFeedback filefeedback) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(filefeedback);
		session.flush();

	}

	@Override
	public List<FileFeedback> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<FileFeedback> fileList = null;
		try {
			fileList = session.createCriteria(FileFeedback.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return fileList;
	}

	@Override
	public FileFeedback findById(Integer id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		FileFeedback FileFeedback = null;
        try {
        	FileFeedback = (FileFeedback) session.get(FileFeedback.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return FileFeedback;  
	}

	@Override
	public void update(FileFeedback filefeedback) throws Exception {
		 Session session = this.sessionFactory.getCurrentSession();
	        session.clear();
	        session.update(filefeedback);
	        session.flush();
		
	}

	@Override
	public void delete(FileFeedback filefeedback) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
        session.delete(filefeedback);
        session.flush();
		
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Integer maxId = 0;

		try {

			Criteria criteria = session.createCriteria(FileFeedback.class).setProjection(Projections.max("fileIdfb"));
			maxId = (Integer) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			maxId = new Integer(0);

		} finally {

		}
		if (maxId != null) {
			return maxId;
		} else{
			return new Integer(0);
		}
	}

	@Override
	public List<FileFeedback> findByuser(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<FileFeedback> holidayList = null;
		try {

			String sql = "SELECT  * FROM file_feedback where user_create='" + user + "'  ";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FileFeedback.class);

			holidayList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return holidayList;
	}

	@Override
	public List<FileFeedback> findByunique(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<FileFeedback> holidayList = null;
		try {

			String sql = "SELECT * FROM file_feedback WHERE file_feedback.unique_key='"
					+ user + "'";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(FileFeedback.class);

			holidayList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return holidayList;
	}

	@Override
	public List<Map<String, Object>> findUni(String uni) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		
		try {
		
			String sql = "SELECT * FROM file_feedback "
					+ " WHERE unique_key = '" + uni +"'";
			
			SQLQuery query = session.createSQLQuery(sql);
			// query.setParameter("id", id);

			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public void deleteAllUnique(String user, String uni) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();

		String sql = "DELETE FROM file_feedback WHERE file_feedback.unique_key='" + uni
				+ "' AND file_feedback.user_id = '" + user + "'";
		SQLQuery query = session.createSQLQuery(sql);

		query.executeUpdate();

	}

	@Override
	public FileFeedback findByKey(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		FileFeedback FileFeedback = null;
        try {
        	FileFeedback = (FileFeedback) session.get(FileFeedback.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return FileFeedback;  
	}
	
}
