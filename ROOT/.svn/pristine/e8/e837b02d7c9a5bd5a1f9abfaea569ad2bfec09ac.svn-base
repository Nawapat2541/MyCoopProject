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

import com.cubesofttech.model.Borrow;
import com.cubesofttech.model.Expense;
import com.cubesofttech.model.FileUpload;
import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.QRCodeList;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;

@Repository
public class QRCodeDAOimpl implements QRCodeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<QRCodeList> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<QRCodeList> articleList = null;
		try {
			String sql = " SELECT * FROM qrcode_list ORDER BY qr_id DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			articleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	@Override
	public QRCodeList findByQRId(int qrId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		QRCodeList qr = null;
		try {
			qr = (QRCodeList) session.get(QRCodeList.class, qrId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return qr;
	}

	
	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Integer maxId = 0;

		try {

			Criteria criteria = session.createCriteria(QRCodeList.class).setProjection(Projections.max("qrId"));
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
	public void update(QRCodeList qrcode_list) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(qrcode_list);
		session.flush();
		// session.close();
		
	}
	
	@Override
	public void save(QRCodeList qrcode_list) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(qrcode_list);
		session.flush();
		// session.close();
		
	}

	@Override
	public void delete(QRCodeList qrcode_list) throws Exception {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(qrcode_list);
			session.flush();
			// session.close();
		}		
	}
	

