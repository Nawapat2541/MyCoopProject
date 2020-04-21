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

import com.cubesofttech.model.Article;
import com.cubesofttech.model.Borrow;
import com.cubesofttech.model.Expense;
import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;

@Repository
public class ArticleDAOimpl implements ArticleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Article> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articleList = null;
		try {
			String sql = " SELECT * FROM article ORDER BY article_id DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	@Override
	public Article findByArticleId(int articleId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Article article = null;
		try {
			article = (Article) session.get(Article.class, articleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return article;
	}
	
	@Override
	public List<Article> findAllNews() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articleList = null;
		try {
			String sql = "SELECT article.*,tag.name,file.path,file.type FROM `article` "
					+ "LEFT JOIN article_tag ON article.article_id = article_tag.article_id "
					+ "LEFT JOIN tag ON article_tag.tag_id = tag.tag_id "
					+ "LEFT JOIN file ON article.file_id = file.file_id ORDER BY `article`.`article_id` ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	@Override
	public void update(Article article) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(article);
		session.flush();
		// session.close();
	}
	
	@Override
	public void save(Article article) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(article);
		session.flush();
		// session.close();
	}
	
	@Override
	public void delete(Article article) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(article);
		session.flush();
		// session.close();
	}
	
	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(Article.class).setProjection(Projections.max("articleId"));
			maxId = (Integer) criteria.uniqueResult();
			
			if(maxId == null){
				maxId = 0;	
			}
			else{
				return maxId;	
			}

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
	public List<Article> writers() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articleList = null;
		try {
			String sql = " SELECT COUNT(user_id) AS articlenum,user_id FROM article GROUP BY `user_id` ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	@Override
	public List<Article> types() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articleList = null;
		try {
			String sql = " SELECT COUNT(article.article_type_id) AS typenum,article_type.name FROM article LEFT JOIN article_type ON article.article_type_id = article_type.article_type_id GROUP BY article.article_type_id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	@Override
	public List<Article> tags() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Article> articleList = null;
		try {
			String sql = " SELECT COUNT(article.topic) AS tagnum,tag.name FROM `article` LEFT JOIN article_tag ON article.article_id = article_tag.article_id LEFT JOIN tag ON article_tag.tag_id = tag.tag_id GROUP BY tag.name ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
}
