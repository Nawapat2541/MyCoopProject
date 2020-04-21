package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.ExpTravelType;
import com.cubesofttech.model.Expense;

@Repository
public class ExpTravelTypeDAOImpl implements ExpTravelTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(ExpTravelType exptraveltype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(exptraveltype);
		session.flush();
		// session.close();
	}

	@Override
	public List<ExpTravelType> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpTravelType> ExpTravelType = null;
		try {
			ExpTravelType = session.createCriteria(ExpTravelType.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return ExpTravelType;
	}

	@Override
	public void update(ExpTravelType exptraveltype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(exptraveltype);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(ExpTravelType exptraveltype) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(exptraveltype);
		session.flush();
		// session.close();
	}

	@Override
	public List<ExpTravelType> findByExpTravelTypeId(String exptraveltypeId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpTravelType> list = null;
		try {

			Criteria cr = session.createCriteria(ExpTravelType.class);
			cr.add(Restrictions.eq("expTravelTypeId", exptraveltypeId));
			list = cr.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}
	
	@Override
	public ExpTravelType findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ExpTravelType exptraveltype = null;
		try {
			exptraveltype = (ExpTravelType) session.get(ExpTravelType.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return exptraveltype;
	}
	@Override
	public List<Map<String, Object>> findByExpId(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> list = null;
		String expId = id ;
		try {
			String sql = "SELECT * , exp_travel_type_id as field FROM `exp_travel_type` WHERE exp_travel_type_id = " + expId  ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Expense> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(ExpTravelType.class).setProjection(Projections.max("expTravelTypeId"));
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
	public List<Map<String, Object>> search(String other) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> goBy = null;
		try {
			String sql = " SELECT exp_travel_type.name "
					   + " FROM exp_travel_type "
					   + " WHERE name = :other ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("other",other);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			goBy = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return goBy;		
	}

	@Override
	public List<Map<String, Object>> findAllType() throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> totalAmountByPaid = null;
		try {
			String sql = "SELECT *, name  as user_id ,exp_travel_type.exp_travel_type_id as field FROM exp_travel_type";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			totalAmountByPaid = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmountByPaid;
	}

}
