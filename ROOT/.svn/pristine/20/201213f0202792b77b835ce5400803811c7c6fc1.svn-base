package com.cubesofttech.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Leaves;
import com.cubesofttech.model.User;
import com.cubesofttech.util.DateUtil;

@Repository
public class LeaveDAOImpl implements LeaveDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Leaves leaves) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(leaves);
		session.flush();
		// session.close();
	}

	@Override
	public List<Leaves> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Leaves> leaveList = null;
		try {
			String sql = " SELECT * FROM leaves ORDER BY leave_id DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			leaveList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveList;
	}

	@Override
	public Leaves findByLeaveId(int leaveId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Leaves leave = null;
		try {
			leave = (Leaves) session.get(Leaves.class, leaveId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return leave;
	}

	@Override
	public void update(Leaves leaves) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(leaves);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(Leaves leaves) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(leaves);
		session.flush();
		// session.close();
	}

	@Override
	public List<Leaves> search(String leaveId) throws Exception {

		return null;
	}
	
	@Override
	public List<Map<String, Object>> findHoliday() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> holidaysheet = null;
		  try {
		   String sql =  " SELECT date_format(start_date,'%d-%m-%Y')as `start_date` ,date_format(end_date,'%d-%m-%Y')as `end_date` FROM holiday";
		   SQLQuery query = session.createSQLQuery(sql);  
	
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		   holidaysheet = query.list();
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return holidaysheet;
	}
	
	@Override
	public List<Map<String, Object>> findHoliday3() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> holidaysheet = null;
		  try {
		   String sql =  " SELECT date_format(start_date,'%m-%d-%Y')as `start_date` ,date_format(end_date,'%m-%d-%Y')as `end_date` FROM holiday";
		   SQLQuery query = session.createSQLQuery(sql);  
	
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		   holidaysheet = query.list();
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return holidaysheet;
	}
	
	
	@Override
	public List<Map<String, Object>> findLeave() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> list = null;
		try {

			/*
			 * SELECT leaves.leave_id, leave_type.leave_type_name,
			 * leave_user.user_id FROM leaves RIGHT JOIN leave_type ON
			 * leave_type.leave_type_id=leaves.leave_type_id RIGHT JOIN
			 * leave_user ON leave_user.leave_id=leaves.leave_id GROUP BY
			 * leave_user.leave_id
			 */

			String sql = "SELECT leaves.leave_id, leaves.end_date, leaves.end_time, leaves.leave_type_id, \n"
					+ " leaves.half_day, leaves.no_day, leaves.reason, leaves.user_update, \n"
					+ " leaves.start_date, leaves.time_create, leaves.leave_status_id, leaves.user_id, \n"
					+ " leaves.appr_user_id, leaves.description, leaves.leave_file, \n"
					+ " leave_type.leave_type_name, \n" + " user.id, user.name \n" + " FROM leaves \n"
					+ " LEFT JOIN leave_type ON leaves.leave_type_id = leave_type.leave_type_id \n"
					+ "  LEFT JOIN user ON user.id = leaves.user_id  \n"
					// + "WHERE leaves.user_id is not null\n"
					+ " GROUP BY leaves.leave_id \n" + " ORDER BY leaves.leave_id DESC \n" + " LIMIT 20 \n";

			SQLQuery query = session.createSQLQuery(sql);
			// query.setParameter("leaveId", leaveId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> listoneperson(String leaveId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> one = null;
		try {
			String leave_id = "";
			if (leaveId != null) {
				leave_id = "  WHERE user_id =  :leaveId";
			}

			String sql = "SELECT leaves.description,leaves.leave_id, leaves.end_date, leaves.end_time,leaves.leave_type_id, leaves.half_day, \n"
					+ "  leaves.appr_user_id, leaves.no_day, leaves.reason,leaves.start_date, leaves.time_create,user.path,  \n"
					+ "  leaves.leave_status_id, leaves.user_id, leave_type.leave_type_name"
					+ " FROM leaves LEFT JOIN leave_type ON leaves.leave_type_id = leave_type.leave_type_id \n"
					+ " LEFT JOIN user ON leaves.user_id = user.id " + leave_id
					+ "  ORDER BY leaves.leave_id DESC " + " LIMIT 20 \n";

			SQLQuery query = session.createSQLQuery(sql);
			if (leaveId != null) {
				query.setParameter("leaveId", leaveId);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			one = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Leaves> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(Leaves.class).setProjection(Projections.max("leaveId"));
			maxId = (Integer) criteria.uniqueResult();

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
	public List<Map<String, Object>> findPatiwanla(String userLogin, int type,Timestamp startDate, Timestamp endDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> wannla = null;
		try {

			String sql = " Select no_day FROM leaves WHERE user_id = :userLogin AND leave_type_id =:type AND start_date BETWEEN :startDate AND :endDate";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userLogin", userLogin);
			query.setParameter("type", type);
			query.setParameter("userLogin", userLogin);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			wannla = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wannla;

	}
	@Override
	public List<Map<String, Object>> findleaveallByType(String userLogin, int type) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> wannla = null;
		try {

			String sql = " Select no_day FROM leaves WHERE user_id = :userLogin AND leave_type_id =:type ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userLogin", userLogin);
			query.setParameter("type", type);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			wannla = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wannla;

	}
	
	@Override
	public List<Map<String, Object>> selectId(String userId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> select = null;
		try {
			String sql = "SELECT * FROM leaves WHERE leave_id = '" + userId + "'";

			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			select = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return select;
	}

	@Override
	public List<Map<String, Object>> listwaitperson(String leaveId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> one = null;
		try {
			String leave_id = "";
			if (leaveId != null) {
				leave_id = "WHERE user_id =  :leaveId AND leaves.leave_status_id = 0 ";
			}

			String sql = "SELECT leaves.leave_id, leaves.end_date, leaves.end_time, \n"
					+ " leaves.leave_type_id, leaves.half_day,\n" + " leaves.no_day, leaves.reason, \n"
					+ " leaves.start_date, leaves.time_create, leaves.leave_status_id, \n"
					+ " leaves.user_id, leaves.user_update \n" + " FROM leaves \n" + leave_id
					+ " ORDER BY leaves.leave_id DESC \n" + " LIMIT 20 \n";

			SQLQuery query = session.createSQLQuery(sql);
			if (leaveId != null) {
				query.setParameter("leaveId", leaveId);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			one = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}

	@Override
	public List<Map<String, Object>> searchAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT  leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date,leaves.end_date, "
					+ " leaves.no_day,leave_type.leave_type_name ,leaves.leave_status_id " + " FROM leaves "
					+ " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id"
					+ " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public List<Map<String, Object>> searchAll_user(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT  leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date,leaves.end_date, "
					+ " leaves.no_day,leave_type.leave_type_name ,leaves.leave_status_id " + " FROM leaves "
					+ " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id " + " WHERE user_id= '"
					+ user + "' " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			// query.setParameter("userId", user);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	//this is override method use for search all leave type and all user
	@Override
	public List<Map<String, Object>> searchtable(Timestamp startDate, Timestamp endDate, String userId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name, user.path "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE leaves.start_date BETWEEN :startDate AND :endDate " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	//this is override method use for search leave by type and all user
	public List<Map<String, Object>> searchtable(Timestamp startDate, Timestamp endDate, String userId,String type)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT leaves.description,leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name , user.path "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE leaves.start_date BETWEEN :startDate AND :endDate AND leaves.leave_status_id = :type " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("type", type);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	//use for search all unapprove by type
	public List<Map<String, Object>> searchtable4(Timestamp startDate, Timestamp endDate, String userId,String type)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " user.path, leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name,leaves.leave_type_id "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE leaves.start_date BETWEEN :startDate AND :endDate AND leaves.leave_type_id = :type " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("type", type);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	//approvedall
	@Override
	public List<Map<String, Object>> searchapprovedall(Timestamp startDate, Timestamp endDate, String userId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " user.path,leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name,leaves.leave_type_id"
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE leaves.start_date BETWEEN :startDate AND :endDate AND leave_status_id='0' " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchmyleave(Timestamp startDate, Timestamp endDate, String user)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {

			String sql = " SELECT * FROM leaves  WHERE user_id = :user AND start_date BETWEEN :startDate AND :endDate "
					+ " ORDER BY leaves.start_date DESC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("user", user);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	// SELECT * FROM leaves WHERE start_date >= '2013-10-07 00:00:00'
	// AND end_date <= '2015-10-07 00:00:00'
	// AND user_id = 'kunchanok.s'

	@Override
	public List<Map<String, Object>> approverform(String leaveId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> one = null;
		try {
			String leave_id = "";
			if (leaveId != null) {
				leave_id = "  WHERE leave_id =  :leaveId";
			}

			String sql = "SELECT MIN(appr_user_id), leaves.leave_id, leaves.end_date, leaves.end_time, \n"
					+ " leaves.leave_type_id, leaves.half_day, leaves.user_id, leaves.no_day, leaves.reason, \n"
					+ " leaves.start_date, leaves.time_create, leaves.leave_status_id, leaves.appr_user_id, \n"
					+ " leave_type.leave_type_name, \n" + " user.id, user.department_id, user.manager_id \n"
					+ " FROM leaves \n" + " LEFT JOIN leave_type ON leaves.leave_type_id = leave_type.leave_type_id \n"
					+ " LEFT JOIN user ON user.id = leaves.user_id \n" + leave_id + " ORDER BY user.id ASC "
					+ " LIMIT 20 \n";

			SQLQuery query = session.createSQLQuery(sql);
			if (leaveId != null) {
				query.setParameter("leaveId", leaveId);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			one = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}

	@Override
	public List<Map<String, Object>> findMyApprover(String manager) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> list = null;
		try {
			String approver = "";

			if (manager != "") {
				approver = "   WHERE leave_id =  :manager";
			}

			String sql = "SELECT MIN(appr_user_id), leaves.user_id, leaves.leave_id,\n" + " leaves.appr_user_id  "
					+ " FROM leaves \n" + approver;

			SQLQuery query = session.createSQLQuery(sql);
			if (manager != "") {
				query.setParameter("manager", manager);
			}

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//this is override method for search all leave type and specific user
	@Override
	public List<Map<String, Object>> searchtable2(Timestamp startDate, Timestamp endDate, String userId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name, user.path "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE user_id = :userId AND leaves.start_date BETWEEN :startDate AND :endDate "
					+ " ORDER BY start_date DESC ";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("userId", userId);
			
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	////use for search by leave type and specific user
	public List<Map<String, Object>> searchtable2(Timestamp startDate, Timestamp endDate, String userId,String type)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " user.path, leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name  "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE user_id = :userId AND leaves.start_date BETWEEN :startDate AND :endDate AND leaves.leave_status_id = :type "
					+ " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("userId", userId);
			query.setParameter("type", type);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	//use for search unapprove by type
	public List<Map<String, Object>> searchtable3(Timestamp startDate, Timestamp endDate, String userId,String type)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " user.path, leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name,leaves.leave_type_id "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE user_id = :userId AND leaves.start_date BETWEEN :startDate AND :endDate AND leaves.leave_type_id = :type "
					+ " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("userId", userId);
			query.setParameter("type", type);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	//
	//approved
	@Override
	public List<Map<String, Object>> searchapproved(Timestamp startDate, Timestamp endDate, String userId)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT leaves.description, leaves.leave_id,leaves.time_create,leaves.user_id,leaves.start_date, "
					+ " user.path, leaves.end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name ,leaves.leave_type_id "
					+ " FROM leaves " + " LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id "
					+ " LEFT JOIN user ON leaves.user_id = user.id "
					+ " WHERE user_id = :userId AND leaves.start_date BETWEEN :startDate AND :endDate AND leave_status_id='0'"
					+ " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("userId", userId);
			
			
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	@Override
	public List<Map<String, Object>> startyear(Timestamp startDate, Timestamp endDate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> startyear = null;
		try {
			String sql = " Select * FROM leaves ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			startyear = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startyear;

	}

	@Override
	public List<Map<String, Object>> findleaveall(String userLogin) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> wannla = null;
		try {

			String sql = " Select no_day,leave_type_id FROM leaves WHERE user_id = :userLogin ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userLogin", userLogin);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			wannla = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wannla;

	}
	@Override
	public List<Map<String, Object>> findleaveByuser(String userLogin) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> wannla = null;
		try {

			String sql = " Select * FROM leaves WHERE user_id = :userLogin ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userLogin", userLogin);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			wannla = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wannla;

	}
	@Override
	public List<Map<String, Object>> join3table(String role) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> joinn3 = null;
		try {
			String sql = " SELECT  ";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Map<String, Object>> searchtable3(String userId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT * FROM leaves WHERE user_id = :userId " + " ORDER BY start_date DESC ";
			SQLQuery query = session.createSQLQuery(sql);

			query.setParameter("userId", userId);
			// query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public List<Map<String, Object>> top10(int year,String enable) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String mystring = "";
		String mystring2 = "";
		if(year != 0 && !enable.equals("0")){
		 mystring = "and year(leaves.start_date) = '"+year+"'";
		}else if(year != 0){ mystring = "where year(leaves.start_date) = '"+year+"'";}else{mystring = "";}
		if(!enable.equals("0")){
			 mystring2 = "where user.enable = '"+enable+"'";
			}
		try {
			String sql = "SELECT leaves.user_id ,sum(leaves.no_day)as total,user.enable,user.name FROM leaves " +
" left join  user  on user.id = leaves.user_id "+
""+mystring2+ " " +mystring +" "+
" group by leaves.user_id "+
" order by total desc limit 10 ; ";
			SQLQuery query = session.createSQLQuery(sql);		
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	//Leaves Chart Report
	@Override
	public List<Map<String, Object>> LeaveAll(int month,int year,String enable,int sortz) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String dateYM   = "";
		String status   = "";
		String Sorttype = "";
		//search each year without month
		if(month == 0 &&  !enable.equals("0")) { 
			dateYM = "and year(leaves.start_date) = '"+year+"' ";
		}else if(month!=0 && year!=0) {
			dateYM = "and year(leaves.start_date) = '"+year+"' and month(leaves.start_date) ='"+month+"'" ;
		}else if (year == 0) {dateYM ="";} // search sum all year
		
		if(!enable.equals("0")){ 
			status = "where user.enable = '"+enable+"'"; }
		//leave largest  to smallest
		if( sortz == 1){ 
			Sorttype = "total desc";
		}else {Sorttype = "total asc";}
		
		try {
			String sql = "SELECT leaves.user_id,user.enable,sum(leaves.no_day)as total,leave_type_id,user.name FROM leaves " +
					" left join  user  on user.id = leaves.user_id "+
					""+status+ " " +dateYM +" "+
					" group by leaves.user_id "+
					" ORDER BY "+Sorttype+" ";
			SQLQuery query = session.createSQLQuery(sql);		
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (
		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	@Override
	public List<Map<String, Object>> LeaveTypename(String user, int year,int month) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String mystring  = "";
		if(month == 0 && year != 0 ){
			mystring = "and year(leaves.start_date) = '"+year+"'";
		}else if (year == 0) {
			mystring = "";
		}else{
			mystring = " and year(leaves.start_date) = '"+year+"'and month(leaves.start_date) ='"+month+"'"; }
		try {
			String sql = "SELECT leaves.leave_type_id ,sum(leaves.no_day) as countL,leave_type.leave_type_name as name FROM leaves "
					+" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id "
					+ "WHERE user_id = '"+user+"' "+mystring+" "
					+ "group by leaves.leave_type_id " ;
			
			SQLQuery query = session.createSQLQuery(sql);		
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (
		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	@Override
	public List<Map<String, Object>> searchCountbyUser(String user, int year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String mystring = "";
		if(year != 0){
		 mystring = "and year(leaves.start_date) = '"+year+"'";
		}
		try {
			String sql = "SELECT leaves.leave_type_id ,sum(leaves.no_day) as count, leave_type.leave_type_name as name FROM leaves "
					+" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id where user_id = '"+user+"' "+mystring+" "
					+ "group by leaves.leave_type_id ";
			SQLQuery query = session.createSQLQuery(sql);		
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	@Override
	public List<Map<String, Object>> searchCountbyMonths(int month,int year,String enable) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String mystring = "";
		String mystring2 = "";
		if(year != 0){
		 mystring = "and year(leaves.start_date) = '"+year+"'";
		}
		if(!enable.equals("0")){
			 mystring2 = "and user.enable = '"+enable+"'";
			}
		try {
			String sql = "SELECT leaves.leave_type_id ,sum(leaves.no_day) as counts,leave_type.leave_type_name as name  FROM leaves  "+
" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id "+
" left join user on leaves.user_id = user.id "+
" where month(leaves.start_date) = '"+month+"' "+mystring+" and (leaves.leave_type_id = '1' or leaves.leave_type_id = '3' or leaves.leave_type_id = '5') "+
"  " +mystring2+ " "
+ " group by leaves.leave_type_id ; ";
			SQLQuery query = session.createSQLQuery(sql);		
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public List<Map<String, Object>> findUserByyear(String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT leaves.user_id, user.name FROM leaves left join user on leaves.user_id = user.id "
					+ " WHERE  year(leaves.start_date)= '"+year+"' group by leaves.user_id  ";
			SQLQuery query = session.createSQLQuery(sql);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;

	}

	@Override
	public List<Map<String, Object>> ReportsByyear(int year, int type,String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT COALESCE(sum(no_day),0) as sum FROM leaves "+
 " left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id  "+
" where  year(leaves.start_date)= '"+year+"' and leaves.user_id ='"+user+"' and leaves.leave_type_id ='"+type+"' and month(leaves.start_date) = '1' ";
			for(int i = 2 ; i <= 12 ; i++){
				sql += " UNION ALL "+
" SELECT COALESCE(sum(no_day),0) as sum  FROM leaves "+
" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id "+
" where  year(leaves.start_date)= '"+year+"' and leaves.user_id ='"+user+"' and leaves.leave_type_id ='"+type+"' and month(leaves.start_date) = '"+i+"' \n ";
			}
			SQLQuery query = session.createSQLQuery(sql);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	
	public List<Map<String, Object>> ReportsByyear2(int year,String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT COALESCE(sum(no_day),0) as sum FROM leaves "+
 " left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id  "+
" where  year(leaves.start_date)= '"+year+"' and leaves.user_id ='"+user+"'  and month(leaves.start_date) = '1' ";
			for(int i = 2 ; i <= 12 ; i++){
				sql += " UNION ALL "+
" SELECT COALESCE(sum(no_day),0) as sum  FROM leaves "+
" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id "+
" where  year(leaves.start_date)= '"+year+"' and leaves.user_id ='"+user+"'  and month(leaves.start_date) = '"+i+"' \n ";
			}
			SQLQuery query = session.createSQLQuery(sql);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}

	@Override
	public List<Map<String, Object>> ReportsType(String year, String user, String month) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT COALESCE(sum(no_day),0) as sum ,leave_type.leave_type_name as name FROM leaves " +
" left join  leave_type on leaves.leave_type_id = leave_type.leave_type_id  "+
" where  year(leaves.start_date)= '"+year+"' and leaves.user_id ='"+user+"' and month(leaves.start_date) = '"+month+"'  "+
" group by leaves.leave_type_id ";
			SQLQuery query = session.createSQLQuery(sql);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;

	
	}
	@Override
	public List<Map<String, Object>> findleaveAll() throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT * FROM leaves";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		return search;
	}
	
	@Override
	public Double LastYearQuota(String userId, int currentYear) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Leaves.class);
		Criteria cr2 = session.createCriteria(User.class);
		
		int year = currentYear -1 ;
		String year_s = "01-01-"+ year;
		String year_e = "31-12-" + year;
		Timestamp start_date = DateUtil.dateFormat(year_s);
		Timestamp end_date = DateUtil.dateFormat(year_e);
		Double daysleft = 0.000;
		
		User user = (User) cr2.add(Restrictions.eq("id", userId)).uniqueResult();
		Double quota1 = 0.0, quota2 = 0.0; //1=horiday leave 2=business leave
		if(user.getLeaveQuota1() != null) {  //if have not value set
			quota1 = user.getLeaveQuota1().doubleValue();
		}
		if(user.getLeaveQuota2() != null) {
			quota2 = user.getLeaveQuota2().doubleValue();
		}
		//else quota=0
		daysleft = user.getLeaveQuota4().doubleValue();
		
		List daysoff = cr.add(Restrictions.eq("userId", userId))
				.add(Restrictions.gt("startDate", start_date))
				.add(Restrictions.lt("endDate", end_date))
				.list();
		
//        for (Iterator iterator = daysoff.iterator(); iterator.hasNext();){
//            Leaves leave = (Leaves) iterator.next(); 
//            Double noday = leave.getNoDay().doubleValue();
//            if(leave.getLeaveTypeId().contains("1")
//            		|| leave.getLeaveTypeId().contains("2")) {
//            	daysleft -= noday;
//            }
//         }
        
		return daysleft;
	}
	
	@Override
	public Double ThisYearQuota(String userId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		User user = (User) cr.add(Restrictions.eq("id", userId))
				.uniqueResult();
		Double quota1 = 0.0, quota2 = 0.0;
		if(user.getLeaveQuota1() != null) {
			quota1 = user.getLeaveQuota1().doubleValue();
		}
		if(user.getLeaveQuota2() != null) {
			quota2 = user.getLeaveQuota2().doubleValue();
		}
		Double quota  = quota1 + quota2;
		
		return quota; //calculate quota from database
	}
	
	@Override
	public List<Map<String, Object>> myLeavesList(String userId, Timestamp startDate, Timestamp endDate){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Leaves.class);
		
		List list = cr.add(Restrictions.eq("userId", userId))
				.add(Restrictions.gt("startDate", startDate))
				.add(Restrictions.lt("endDate", endDate))
				.addOrder(Order.desc("leaveId"))
				.list();
		
		return list;
	}

	@Override
	public List<Map<String, Object>> myLeavesList(String userId, Timestamp startDate, Timestamp endDate, String status){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Leaves.class);
		
		List list = cr.add(Restrictions.eq("userId", userId))
				.add(Restrictions.eq("leaveStatusId", status))
				.add(Restrictions.gt("startDate", startDate))
				.add(Restrictions.lt("endDate", endDate))
				.addOrder(Order.desc("leaveId"))
				.list();
		
		return list;
	}
	
	@Override
	public String sumWaitLeave(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		String result = null;
		try {
			String sql = "SELECT SUM(no_day) as sum FROM leaves WHERE user_id = '"+ user +"' AND leave_status_id = 0 ";
			SQLQuery query = session.createSQLQuery(sql);

			result = query.uniqueResult().toString();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> test_LeavesList(String userId, int year){
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_LeavesList = null;
	try {

	String sql = "SELECT user_id,start_date,end_date,leaves.leave_type_id,leave_type.leave_type_name FROM `leaves` INNER JOIN leave_type ON leaves.leave_type_id=leave_type.leave_type_id WHERE user_id = '"+userId+"' AND YEAR(start_date) = '"+year+"' AND leave_status_id = '1' GROUP BY DATE(start_date) ORDER BY (start_date)" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_LeavesList = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_LeavesList;
	}
	
	@Override
	public List<Map<String, Object>> test_LeavesSummary(int year,int type){
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_LeavesSummary = null;
	try {
	String sql = "SELECT user_id,DAY(start_date)AS Day ,MONTH(start_date)AS Month ,no_day,leave_status_id FROM `leaves` WHERE YEAR(start_date) ='"+year+"' AND leave_status_id= '"+type+"' ORDER BY start_date ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_LeavesSummary = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_LeavesSummary;
	}
	
	@Override
	public List<Map<String, Object>> leaveselectM(int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> leaveselectM = null;
	try {

	String sql = "SELECT user_id,DAY(start_date)AS DAY,MONTH(start_date)AS MONTH,DAY(end_date)AS ENDDAY,MONTH(end_date)AS ENDMONTH FROM `leaves` WHERE YEAR(start_date)='"+year+"' ORDER BY DATE(start_date) ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	leaveselectM = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return leaveselectM;
	}
	
	@Override
	public List<Map<String, Object>> searchApproved(Timestamp start_date, Timestamp end_date, String jsonId, String jsonStatus,
			String jsonType)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String type;
		String status;
		String id;
		if(jsonId.equals("All")) {
			id="";
		}else {
			id=" user_id = '"+jsonId+"' AND ";
		}
		if(jsonType.equals("All")) {
			type="";
		}else {
			type=" leaves.leave_type_id = '"+jsonType+"' AND ";
		}
		if(jsonStatus.equals("3")) {
			status="";
		}else {
			status=" leaves.leave_status_id = '"+jsonStatus+"' AND ";
		}
		try {
			String sql = "SELECT leaves.description, leaves.leave_id,DATE_FORMAT(leaves.time_create,'%d/%m/%Y %H:%i') AS time_create,leaves.user_id,DATE_FORMAT(leaves.start_date,'%d/%m/%Y') AS start_date, DATE_FORMAT(leaves.end_date,'%d/%m/%Y') AS end_date, leaves.no_day ,leaves.leave_status_id,leave_type.leave_type_name, user.path   FROM leaves LEFT JOIN leave_type ON leave_type.leave_type_id = leaves.leave_type_id LEFT JOIN user ON leaves.user_id = user.id WHERE "+id+""+type+""+status+ "leaves.start_date BETWEEN '"+start_date+"' AND '"+end_date+"' ORDER BY start_date DESC";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	public List<Map<String, Object>> searchDashboard(String jsonId, String date)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String id;
		if(jsonId.equals("All")) {
			id="";
		}else {
			id=jsonId;
		}
		try {
			String sql = "SELECT user_id,(SELECT FORMAT(SUM(no_day),2)AS type6 FROM leaves WHERE user_id='"+id+"' AND leave_status_id='1' AND leave_type_id='1' AND year(start_date)="+date+")as type1,(SELECT FORMAT(SUM(no_day),2)AS type6 FROM leaves WHERE user_id='"+id+"' AND leave_status_id='1' AND leave_type_id='3' AND year(start_date)="+date+")as type3,(SELECT FORMAT(SUM(no_day),2)AS type6 FROM leaves WHERE user_id='"+id+"' AND leave_status_id='1' AND leave_type_id='6' AND year(start_date)="+date+")as type6 FROM leaves WHERE user_id='"+id+"' AND leave_status_id='1' AND (leave_type_id='1' OR leave_type_id='3' OR leave_type_id='6') GROUP BY user_id='"+id+"'";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
	public List<Map<String, Object>> searchDashboardQuota(String jsonId, String date)
			throws Exception {
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		String id;
		if(jsonId.equals("All")) {
			id="";
		}else {
			id=jsonId;
		}
		try {
			String sql = "SELECT id,FORMAT(leave_quota_1,2)AS thisYear,FORMAT(leave_quota_lastyear,2)AS lastYear FROM user WHERE id='"+id+"'";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	public List<Map<String, Object>> leaveUpdateStatus(String userId, String leave)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		
		try {
			String sql = "UPDATE leaves SET leave_status_id = '1', appr_user_id= '"+userId+"' WHERE leave_id = '"+leave+"'";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
			
			String sqlSearch = "SELECT * FROM `leaves` WHERE leave_id = '"+leave+"'";
			
			SQLQuery querySearch = session.createSQLQuery(sqlSearch);
			querySearch.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			search = querySearch.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return search;
	}
	
}
