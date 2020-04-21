package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Article;
import com.cubesofttech.model.ArticleTag;
import com.cubesofttech.util.DateUtil;

@Repository
public class ArticleTagDAOImpl implements ArticleTagDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
    public void save(ArticleTag articletag) throws Exception{
        Session session = this.sessionFactory.getCurrentSession();
        session.save(articletag);
        session.flush();
        //session.close();
    }
	
	
    @Override
    public int deleteByArticleId(String articleId) throws Exception {
    	Session session = this.sessionFactory.getCurrentSession();
    	int updated = 0;
        List<ArticleTag> list = null;
        try {
        	Query deleteQuery = session.createSQLQuery(
        		    "delete from article_tag "
        		    + "where article_id = ? ");
        		deleteQuery.setString(0, articleId);
        		updated = deleteQuery.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();

        } finally {

        }
		return updated;
    }   
}
