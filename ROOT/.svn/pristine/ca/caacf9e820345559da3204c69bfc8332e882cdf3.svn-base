package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.ArticleType;

import com.cubesofttech.util.DateUtil;

@Repository
public class ArticleTypeDAOimpl implements ArticleTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ArticleType> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ArticleType> articleTypeList = null;
		try {
			String sql = " SELECT * FROM article_type ORDER BY article_type_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleTypeList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleTypeList;
	}
	
	@Override
	public ArticleType findByArticleTypeId(int articleTypeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ArticleType articleType = null;
		try {
			articleType = (ArticleType) session.get(ArticleType.class, articleTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return articleType;
	}
	
}
