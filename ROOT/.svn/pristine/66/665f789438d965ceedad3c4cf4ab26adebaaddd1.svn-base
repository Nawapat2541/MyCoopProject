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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Article;
import com.cubesofttech.model.Role;
import com.cubesofttech.model.TagAr;
import com.cubesofttech.util.DateUtil;

@Repository
public class TagArDAOimpl implements TagArDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TagAr> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<TagAr> tagArList = null;
		try {
			String sql = " SELECT * FROM tag ORDER BY tag_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			tagArList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagArList;
	}

	@Override
	public List<TagAr> findArticleInTag() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<TagAr> tagAr = null;
		try {
			String sql = " SELECT tag.name,article.article_id FROM tag LEFT JOIN article_tag ON article_tag.tag_id = tag.tag_id LEFT JOIN article ON article.article_id = article_tag.article_id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			tagAr = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagAr;
	}

	@Override
	public TagAr findBytagId(int tagArId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		TagAr tagAr = null;
		try {
			tagAr = (TagAr) session.get(TagAr.class, tagArId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return tagAr;
	}

	@Override
	public void save(TagAr tag) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(tag);
		session.flush();
		// session.close();
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<TagAr> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(TagAr.class).setProjection(Projections.max("tagArId"));
			maxId = (Integer) criteria.uniqueResult();

			if (maxId == null) {
				maxId = 0;
			} else {
				return maxId;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Integer(0);

		} finally {

		}
		if (maxId != null) {
			return maxId;
		} else {
			return new Integer(0);
		}
	}

	@Override
	public /* String */List<Integer> findByTagArId(String tagArName) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Integer> list = null;
		// String list;
		try {

			Criteria cr = session.createCriteria(TagAr.class);
			cr.add(Restrictions.eq("tagArName", tagArName))
					.setProjection(Projections.property("tagArId"));/* It will return only tag_id */
			list = cr.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}

}
