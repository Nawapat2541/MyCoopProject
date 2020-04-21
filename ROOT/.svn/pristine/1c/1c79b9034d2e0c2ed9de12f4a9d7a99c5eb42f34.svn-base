package com.cubesofttech.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.ExpType;

@Repository
public class ExpTypeDAOImpl implements ExpTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(ExpType exptype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(exptype);
		session.flush();
		// session.close();
	}

	@Override
	public List<ExpType> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpType> ExpType = null;
		try {
			ExpType = session.createCriteria(ExpType.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return ExpType;
	}

	@Override
	public void update(ExpType exptype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(exptype);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(ExpType exptype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(exptype);
		session.flush();
		// session.close();
	}

	@Override
	public List<ExpType> findByExpTypeId(int exptypeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpType> list = null;
		try {

			Criteria cr = session.createCriteria(ExpType.class);
			cr.add(Restrictions.eq("exptypeId", exptypeId));
			list = cr.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}

	@Override
	public ExpType findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ExpType exptype = null;
		try {
			exptype = (ExpType) session.get(ExpType.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return exptype;
	}

}
