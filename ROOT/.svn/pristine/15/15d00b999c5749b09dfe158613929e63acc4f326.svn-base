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

import com.cubesofttech.model.News;
@Repository
public class NewsDAOImpl implements NewsDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
 
    @Override
    public void save(News News) throws Exception{
        Session session = this.sessionFactory.getCurrentSession();
        session.save(News);
        session.flush();
        //session.close();
    }

    @Override
    public List<News> findAll() throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        List<News> newsList = null;
        try {
        	newsList = session.createCriteria(News.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return newsList;
    }

    @Override
    public News findById(int newId) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        News News = null;
        try {
        	News = (News) session.get(News.class, newId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //session.close();
        }        
        return News;
    }

    @Override
    public void update(News News) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.clear();
        session.update(News);
        session.flush();
        //session.close();
    }

    @Override
    public void delete(News News) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(News);
        session.flush();
        //session.close();
    }
    
    @Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<News> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(News.class)
					.setProjection(Projections.max("newsId"));
			maxId = (Integer) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			return new Integer(0);

		} finally {

		}
		if (maxId != null) {
			return maxId;
		} else{
			return new Integer(0);
		}
	}
    @Override
    public List<Map<String, Object>> listnews(int moreLimit) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT  news.file_id,news.news_head,news.news_description,news.user_create,news.time_create,file.path,file.name,file.type,user.path AS userprofile, "
					+ " (SELECT COUNT(news.news_id) FROM news) AS allnews "
        			+ " FROM news LEFT JOIN file "
        			+ " ON news.file_id = file.file_id "
        			+ " LEFT JOIN user "
        			+ " ON news.user_create = user.id "
        			+ " ORDER BY news_id DESC "
        			+ " LIMIT :moreLimit";
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("moreLimit", moreLimit);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> dashboardNews() throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT news.file_id,news.news_head,news.news_description,news.user_create,news.time_create,file.path,file.name,file.type " 
        			+"FROM news LEFT JOIN file "
        			+"ON news.file_id = file.file_id "
        			+"ORDER BY news_id DESC "
        			+"LIMIT 1 ";
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> mynews(String user) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT news.news_id,news.file_id,news.news_head,news.news_description,news.user_create,news.time_create,file.path,file.name,file.type "
        			+"FROM news "
        			+"LEFT JOIN file "
        			+"ON news.file_id = file.file_id "
        			+"WHERE news.user_create =:user ";
        			
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("user", user);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> find_filepath(int fileId) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT path FROM file "
        				+"WHERE file_id =:fileId ";
        			
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("fileId", fileId);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> sumtravelPrice(String user) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
//        	
//        	String sql= "SELECT (SUM(total_amount),'#,##') as price,MONTH(time_update) AS mymonth,YEAR(time_update) AS myyear,COUNT(expense_group_id) AS mybill "
//          			 +"FROM expense_group "
//           			 +"WHERE status_id = 'w' "
//                     +"GROUP BY MONTH(time_update) "
//           			 +"ORDER by time_update DESC LIMIT 1";
        	String sql= "SELECT SUM(total_amount)as price  "
         			 +"FROM expense_group "
          			 +"WHERE status_id = 'w' ,user_create = :user"
                  ;

        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("user", user);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> mostcometoWork() throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	String sql= "SELECT COUNT(work_hours_id) "
        			+" FROM work_hours "
        			+" WHERE work_hours_type = '1' "
        			;
        			
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> sumItem() throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	String sql= "SELECT (Select COUNT(equipment_id) as allitem FROM equipment WHERE status = 'B') AS borrowitem, " 
        			+" (Select COUNT(equipment_id) as allitem FROM equipment WHERE NOT status = 'Z') AS  allitem, "
        			+" (Select COUNT(equipment_id) as allitem FROM equipment WHERE status = 'B')*100 "
        			+" /(Select COUNT(equipment_id) as allitem FROM equipment WHERE NOT status = 'Z') AS persentage ";
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> totallyleaves(String user,int thisyear) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= " SELECT (user.leave_quota_1) - SUM(leaves.no_day) AS totally, "
       			+ " FORMAT(((user.leave_quota_1)-(leaves.no_day)) * 100 / (user.leave_quota_1),'#,##0.00') AS persentage, " 
       			+ " user.id, "
       			+ " YEAR(leaves.time_create) AS yearNow "
       			+ " FROM user "
       			+ " LEFT JOIN leaves ON user.id = leaves.user_create "
       			+ " WHERE leaves.user_create =:user AND YEAR(leaves.time_create) =:thisyear "
       			+ " GROUP BY YEAR(leaves.time_create) , leaves.no_day  ";
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("user", user);
        	query.setParameter("thisyear", thisyear);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> totaltravel(String user) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT  COUNT(expense_group_id) AS mybill  FROM expense_group "
        			  + " WHERE user_create =:user AND status_id = 'w' ";
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("user", user);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }
    
    @Override
    public List<Map<String, Object>> obtainTravel(String user) throws Exception 
    {  	
        Session session = this.sessionFactory.getCurrentSession();
        List<Map<String, Object>> news_feed = null;
        try 
        {
        	
        	String sql= "SELECT SUM(total_amount) AS amount FROM expense_group "
        			   +" WHERE user_create =:user AND status_id = 'p' ";
        			
        	
        	SQLQuery query = session.createSQLQuery(sql);
        	query.setParameter("user", user);
        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        	news_feed = query.list();      			
        }
        catch (Exception e)
        {
        	 e.printStackTrace();
        }
        return news_feed;
    }

	@Override
	public List<Map<String, Object>> sumtravelPrice() throws Exception {
		 Session session = this.sessionFactory.getCurrentSession();
	        List<Map<String, Object>> news_feed = null;
	        try 
	        {
//	        	
//	        	String sql= "SELECT (SUM(total_amount),'#,##') as price,MONTH(time_update) AS mymonth,YEAR(time_update) AS myyear,COUNT(expense_group_id) AS mybill "
//	          			 +"FROM expense_group "
//	           			 +"WHERE status_id = 'w' "
//	                     +"GROUP BY MONTH(time_update) "
//	           			 +"ORDER by time_update DESC LIMIT 1";
	        	String sql= "SELECT SUM(total_amount)as price  "
	         			 +"FROM expense_group "
	          			 +"WHERE status_id = 'w'"
	                  ;

	        	
	        	SQLQuery query = session.createSQLQuery(sql);
	        
	        	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	        	news_feed = query.list();      			
	        }
	        catch (Exception e)
	        {
	        	 e.printStackTrace();
	        }
	        return news_feed;
	}
}
