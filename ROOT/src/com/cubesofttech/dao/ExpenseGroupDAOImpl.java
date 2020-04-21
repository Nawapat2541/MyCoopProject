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

import com.cubesofttech.model.ExpenseGroup;

@Repository
public class ExpenseGroupDAOImpl implements ExpenseGroupDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(ExpenseGroup expensegroup) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(expensegroup);
		session.flush();
		// session.close();
	}

	@Override
	public List<ExpenseGroup> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpenseGroup> ExpenseGroup = null;
		try {
			ExpenseGroup = session.createCriteria(ExpenseGroup.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return ExpenseGroup;
	}

	@Override
	public void update(ExpenseGroup expensegroup) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(expensegroup);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(ExpenseGroup expensegroup) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(expensegroup);
		session.flush();
		// session.close();
	}

	@Override
	public ExpenseGroup findByGroupId(int expenseGroupId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ExpenseGroup list = null;
		try {
			list = (ExpenseGroup) session.get(ExpenseGroup.class, expenseGroupId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<ExpenseGroup> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(ExpenseGroup.class)
					.setProjection(Projections.max("expenseGroupId"));
			maxId = (Integer) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			return new Integer(0);

		} finally {

		}
		return maxId;
	}

	@Override
	public ExpenseGroup findById(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ExpenseGroup expensegroup = null;
		try {
			expensegroup = (ExpenseGroup) session.get(ExpenseGroup.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return expensegroup;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchtable(String Expenseuserid) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String user_value = "";

			if (Expenseuserid != "" && !Expenseuserid.equals("All")) {
				user_value = "WHERE user_id = :Expenseuserid ";
			} else if (Expenseuserid.equals("All")) {
				user_value = "";
			}

			String sql = " SELECT expense_group.expense_group_id,expense_group.time_create,"
					+ " expense_group.user_id, expense_group.total_amount, expense_group.status_id "
					+ " FROM expense_group " 
					+ user_value
					+ " ORDER BY expense_group.expense_group_id DESC limit 20 ";
					

			SQLQuery query = session.createSQLQuery(sql);
			if (Expenseuserid != "" && !Expenseuserid.equals("All")) {
				query.setParameter("Expenseuserid", Expenseuserid);
			}

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchtable2(String Expenseuserid,java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = " SELECT * FROM expense_group  WHERE user_id = :Expenseuserid AND time_create BETWEEN :Datestart AND :Dateend"
					+ " ORDER BY expense_group.expense_group_id DESC ";
					

			SQLQuery query = session.createSQLQuery(sql);
			
				query.setParameter("Expenseuserid", Expenseuserid);
				query.setParameter("Datestart", Datestart);
				query.setParameter("Dateend", Dateend);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	@Override
	public List<Map<String, Object>> searchAll(java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = " SELECT expense_group.expense_group_id,expense_group.time_create,"
					+ " expense_group.user_id, expense_group.total_amount, expense_group.status_id "
					+ " FROM expense_group " 
					+ " WHERE  time_create BETWEEN :Datestart AND :Dateend "
					+ " ORDER BY expense_group.expense_group_id DESC ";
					

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listgroup(String expgroupId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> detail = null;
		try {

			String expgroup_id = "";

			if (expgroupId != "") {

				expgroup_id = "     WHERE expense_group.expense_group_id = :expgroupId ";
			}
			String sql = "SELECT  expense_group.user_id, expense_group.time_create, "
					+ " expense_group.total_amount, expense_group.status_id, expense_group.time_update, "
					+ " expense.expense_id, expense.dt_start, expense.dt_end, expense.from_location, expense.to_location, "
					+ " expense.amount,expense.description ,exp_travel_type.name"
					+ " FROM expense_group"
					+ " LEFT JOIN expense ON expense.expense_group_id = expense_group.expense_group_id  " 
					+ "LEFT JOIN exp_travel_type ON exp_travel_type.exp_travel_type_id = expense.dt_by "
					+ expgroup_id;
			// + " GROUP BY expense_group.user_id \n"
			// + " ORDER BY expense_group.time_create DESC";

			SQLQuery query = session.createSQLQuery(sql);
			if (expgroupId != "") {
				query.setParameter("expgroupId", expgroupId);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			detail = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> detail = null;
		try {
			String sql = " SELECT * FROM expense_group " + "  ORDER BY expense_group_id DESC \n"
					+ " LIMIT 20 \n";

			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			detail = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

	@Override
	public ExpenseGroup findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		ExpenseGroup group = null;
		try {
			group = (ExpenseGroup) session.get(ExpenseGroup.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return group;
	}

	@Override
	public List<Map<String, Object>> findtype() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> detail = null;
		try {
			String sql = " SELECT distinct status_id  FROM expense_group " ;
			

			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			detail = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}
}
