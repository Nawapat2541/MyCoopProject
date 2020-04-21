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
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.User;
import com.cubesofttech.model.WorkHours;

@Repository
public class WorkHoursDAOImpl implements WorkHoursDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(WorkHours WorkHours) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(WorkHours);
		session.flush();
		// session.close();
	}

	@Override
	public List<WorkHours> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkHours> workHoursList = null;
		try {
			workHoursList = session.createCriteria(WorkHours.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return workHoursList;
	}

	@Override
	public void update(WorkHours WorkHours) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(WorkHours);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(WorkHours WorkHours) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(WorkHours);
		session.flush();
		// session.close();
	}

	@Override
	public WorkHours findByWorkHoursId(int workHoursId) throws Exception {
		WorkHours result = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(WorkHours.class);
		cr.add(Restrictions.eq("workHoursId", workHoursId));
		List results = cr.list();
		if(!results.isEmpty()) {
			result = (WorkHours) results.get(0);
			return result;
		}
		else {
			return null;
		}

	}


	@Override
	public WorkHours findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		WorkHours WorkHours = null;
		try {
			WorkHours = (WorkHours) session.get(WorkHours.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return WorkHours;
	}

	@Override
	public List<WorkHours> search(String workHoursId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<WorkHours> list = null;
		try {

			Criteria cr = session.createCriteria(WorkHours.class);
			cr.add(Restrictions.eq("workHoursId", workHoursId));

			list = cr.list();

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
		List<WorkHours> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(WorkHours.class).setProjection(Projections.max("workHoursId"));
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
	public List<Map<String, Object>> checkhistory(String currentUserhistory, String month, String year)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work)"
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description"
					+ " from work_hours m" + " where  work_hours_type = 2 AND user_create =:currentUserhistory "
					+ " AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY  work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserhistory", currentUserhistory);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistLL(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {
		
			String sql = "select work_hours_id,work_hours_type,work_hours_time_work ,latitude ,longitude ,description ,ip_address ,user_create ,time_create"
					+ " from work_hours m "
					+ "WHERE work_hours_id=:id";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}
	
	@Override
	public List<Map<String, Object>> checklist(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work), description "
					+ " AS mymin,m.work_hours_id,m.work_hours_type,m.work_hours_time_work,"
					+ " date_format(m.work_hours_time_work,'%d-%m-%y')as `date`,time(m.work_hours_time_work)as `time`,"
					+ " m.user_create,m.time_create,m.ip_address,m.description,m.latitude,m.longitude,u.role_id as 'u_rold',u.name as 'u_name',m.work_hours_type as `check_type`"
					+ " from work_hours m "
					+ " inner join `user` u on m.user_create = u.id"
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}
	
	@Override
	public List<Map<String, Object>> QRchecklist(String work_hour_id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work), description "
					+ " AS mymin,m.work_hours_id,m.work_hours_type,m.work_hours_time_work,"
					+ " date_format(m.work_hours_time_work,'%d-%m-%y')as `date`,time(m.work_hours_time_work)as `time`,"
					+ " m.user_create,m.time_create,m.ip_address,m.description,m.latitude,m.longitude,u.role_id as 'u_rold',u.name as 'u_name',m.work_hours_type as `check_type`"
					+ " from work_hours m "
					+ " inner join `user` u on m.user_create = u.id"
					+ " where work_hours_id = :work_hour_id   "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("work_hour_id", work_hour_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}
	

	@Override
	public List<Map<String, Object>> timemonth(String currentUserlist, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select DISTINCT DATE(work_hours_time_work) " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month ";
				

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistcalendar(String currentUserlist, String month, String year)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select DISTINCT DATE(work_hours_time_work) " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month ";
				

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}
	
//	test 20/03
	@Override
	public List<Map<String, Object>> checkStatus(String currentUserlist,String month, String year) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkstatus = null;
		try {

			String sql = "SELECT user.work_time_start, id FROM user "
					;
					 
				

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkstatus = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkstatus;
	}
	
	@Override
	public List<Map<String, Object>> checklistcalendars(String datecheckin, String currentUserlist, String month,
			String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_time_work AS mytimecheckout " + " from work_hours "
					+ " where DATE(work_hours_time_work)=:datecheckin AND user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type=2 ";


			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("datecheckin", datecheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> idcheckinsearchcheckout(String datecheckin, String currentUserlist,
			java.sql.Timestamp Datenow, java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_id " + " from work_hours "
					+ " where DATE(work_hours_time_work)=:datecheckin AND user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type=2 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("datecheckin", datecheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checkincalendars(String datecheckin, String currentUserlist, String month,
			String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_time_work AS mycheckin " + " from work_hours "
					+ " where DATE(work_hours_time_work)=:datecheckin AND user_create = :currentUserlist "
					+ "AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type=1 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("datecheckin", datecheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> idcheckoutcalendars(String idcheckin, String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_id " + " from work_hours "
					+ " where work_hours_id > :idcheckin  AND user_create = :currentUserlist AND work_hours_type=2 "
					+ " ORDER BY work_hours_id ASC " + " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("idcheckin", idcheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> timecheckoutfromid(String idcheckoutsearchtime) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_time_work " + " from work_hours "
					+ " where work_hours_id =:idcheckoutsearchtime ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("idcheckoutsearchtime", idcheckoutsearchtime);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}
	
	@Override
	public List<Map<String, Object>> lastcheckin(String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "SELECT MAX(work_hours_time_work) as work_hours_time_work"
					+" FROM work_hours "
					+" WHERE user_create =:user "
					+" AND  work_hours_type = 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("user", user);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> idcheckincalendars(String datecheckin, String currentUserlist, String month,
			String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select work_hours_id " 
					+ " from work_hours "
					+ " where DATE(work_hours_time_work)=:datecheckin AND user_create = :currentUserlist"
					+ " AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type=1 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("datecheckin", datecheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistcalendarstime(String datecheckin, String currentUserlist, String month,
			String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select workinghours AS mytimecheckout " + " from work_hours "
					+ " where DATE(work_hours_time_work)=:datecheckin AND user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type=2 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("datecheckin", datecheckin);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistapprove(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " AND work_hours_type = 1 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistapprovecheckout(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistapprovestatus(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " AND work_hours_type = 1 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistapprovestatusall(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, DATE(work_hours_time_work) AS checkDate, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checklistapprovestatusall1(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> timecheckout(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timecheckout = null;
		try {

			String sql = "select work_hours_time_work " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			/*
			 * query.setParameter("Datenow", Datenow);
			 * query.setParameter("DateBefore", DateBefore);
			 */
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timecheckout = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timecheckout;
	}
 /* edit search check list*/
	@Override 
	public List<Map<String, Object>> searchList(String Username, java.sql.Timestamp Datestart, java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {
			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) AS mymin, description "
					+ " ,m.work_hours_id,m.work_hours_type,m.user_create,m.time_create,m.ip_address,m.latitude,"
					+ " m.longitude,m.work_hours_time_work,date_format(m.work_hours_time_work,'%d-%m-%y')as `date`,"
					+ " time(m.work_hours_time_work)as `time`,u.role_id as 'u_rold',u.name as 'u_name',m.work_hours_type as `check_type`"
					+ " FROM work_hours m " 
					+ " inner join `user` u on m.user_create = u.id"
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " ORDER BY work_hours_id ASC ";	
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> worksearchapprove(String Username, java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> worksearchapproveall(String Username, java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " ORDER BY work_hours_id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	public List<Map<String, Object>> searchHis(String Username, java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m "
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 2 " + " ORDER BY  work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	public List<Map<String, Object>> checkIn(String Username) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {
			String Username_value = "";
			if (Username != "") {
				Username_value = " WHERE user_create = :Username ";
			}
			String sql = "SELECT work_hours_type,time_create FROM work_hours " + Username_value
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<Map<String, Object>> checkinhourtime(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkinhourtime = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myinhourtime " + " from work_hours m "
					+ " where user_create = :currentUserlist "
					+ " AND work_hours_time_work BETWEEN :DateBefore AND :Datenow AND work_hours_type =1 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("Datenow", Datenow);
			query.setParameter("DateBefore", DateBefore);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkinhourtime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkinhourtime;
	}

	@Override
	public List<Map<String, Object>> checkouthourtime(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkouthourtime = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myouthourtime " + " from work_hours m "
					+ " where user_create = :currentUserlist "
					+ " AND work_hours_time_work BETWEEN :DateBefore AND :Datenow AND work_hours_type =2 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("Datenow", Datenow);
			query.setParameter("DateBefore", DateBefore);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkouthourtime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkouthourtime;
	}

	@Override
	public List<Map<String, Object>> checkinmintime(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkinmintime = null;
		try {

			String sql = "select minute(m.work_hours_time_work) AS checkinmintime " + " from work_hours m "
					+ " where user_create = :currentUserlist "
					+ " AND work_hours_time_work BETWEEN :DateBefore AND :Datenow AND work_hours_type =1 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("Datenow", Datenow);
			query.setParameter("DateBefore", DateBefore);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkinmintime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkinmintime;
	}

	@Override
	public List<Map<String, Object>> checkoutmintime(String currentUserlist, java.sql.Timestamp Datenow,
			java.sql.Timestamp DateBefore) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkoutmintime = null;
		try {

			String sql = "select minute(m.work_hours_time_work) AS checkoutmintime " + " from work_hours m "
					+ " where user_create = :currentUserlist "
					+ " AND work_hours_time_work BETWEEN :DateBefore AND :Datenow AND work_hours_type =2 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("Datenow", Datenow);
			query.setParameter("DateBefore", DateBefore);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkoutmintime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkoutmintime;
	}

	@Override
	public List<Map<String, Object>> searchcheckinhourtime(String Username, java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchcheckinhourtime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " ORDER BY work_hours_id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchcheckinhourtime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchcheckinhourtime;
	}
	
	

	@Override
	public List<Map<String, Object>> searchcheckinhourtimeapprove(String Username, java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchcheckinhourtime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " WHERE user_create = :Username AND work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Username", Username);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchcheckinhourtime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchcheckinhourtime;
	}

	@Override
	public List<Map<String, Object>> checktimehours(String currentUserlist, String date,String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktimehours = null;
		try {

			String sql = " select hour(work_hours_time_work) "
					+ " FROM work_hours WHERE user_create =:currentUserlist AND DAY(work_hours_time_work)=:date AND YEAR(work_hours_time_work)=:year "
					+ " AND MONTH(work_hours_time_work)=:month AND work_hours_type=1 ORDER BY work_hours_id DESC LIMIT 1";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("date", date);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktimehours = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktimehours;
	}
	
	@Override
	public List<Map<String, Object>> check_in(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> check_in = null;
		try {

			String sql = " select DAY(work_hours_time_work) "
					+ " FROM work_hours WHERE user_create =:currentUserlist "
					+ " AND work_hours_type=1 ORDER BY work_hours_id DESC LIMIT 1 ";
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			check_in = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check_in;
	}

	@Override
	public List<Map<String, Object>> checktimemin(String currentUserlist, String date,String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktimemin = null;
		try {

			String sql = "select minute(m.work_hours_time_work) AS myinhourtime " + " from work_hours m "
					+ " where user_create = :currentUserlist " + " AND work_hours_type =1 AND DAY(work_hours_time_work)=:date AND YEAR(work_hours_time_work)=:year  "
					+ " AND MONTH(work_hours_time_work)=:month ORDER BY work_hours_id DESC " + " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("date", date);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktimemin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktimemin;
	}
	
	@Override
	public List<Map<String, Object>> usertype1(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> usertype1 = null;
		try {

			String sql = "select TIME(work_hours_time_work) " 
					+ " from work_hours "
					+ " where user_create = :currentUserlist AND work_hours_type =1 "
					+ " ORDER BY work_hours_id DESC " 
					+ " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			usertype1 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usertype1;
	}

	@Override
	public List<Map<String, Object>> checkdate(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkdate = null;
		try {

			String sql = "select DATE(work_hours_time_work) " + " from work_hours m "
					+ " where user_create = :currentUserlist " + " AND work_hours_type =1 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkdate = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkdate;
	}

	@Override
	public List<Map<String, Object>> checktime(String currentUserlist, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktime;
	}
	
	@Override
	public List<Map<String, Object>> QRchecktime(String work_hour_id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " where work_hours_id = :work_hour_id "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("work_hour_id", work_hour_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktime;
	}

	@Override
	public List<Map<String, Object>> checktimecalendar(String currentUserlist, String month, String year)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month AND work_hours_type =2 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktime;
	}

	@Override
	public List<Map<String, Object>> checktimeapprove(String currentUserlist, String month, String year)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checktime = null;
		try {

			String sql = "select workinghours " + " from work_hours "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checktime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checktime;
	}

	@Override
	public List<Map<String, Object>> timecheckin(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timecheckin = null;
		try {

			String sql = "select TIME(work_hours_time_work) " + " from work_hours "
					+ " where user_create = :currentUserlist " + " AND work_hours_type =1 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timecheckin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timecheckin;
	}

	@Override
	public List<Map<String, Object>> datecheckin(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> datechechin = null;
		try {

			String sql = "select DATE(work_hours_time_work) " + " from work_hours "
					+ " where user_create = :currentUserlist " + " AND work_hours_type =1 "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			datechechin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datechechin;
	}

	@Override
	public List<Map<String, Object>> startmonth(String currentUserlist, String month, String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> startmonth = null;
		try {

			String sql = "select DATE(work_hours_time_work) AS datestart " + " from work_hours m "
					+ " where user_create = :currentUserlist AND YEAR(work_hours_time_work)=:year AND MONTH(work_hours_time_work)=:month "
					+ " ORDER BY work_hours_id ASC " + " LIMIT 1";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			startmonth = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startmonth;
	}

	@Override
	public List<Map<String, Object>> searchListall(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListall = null;
		try {
			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) AS mymin, description "
					+ " ,m.work_hours_id,m.work_hours_type,m.user_create,m.time_create,m.ip_address,m.latitude,"
					+ " m.longitude,m.work_hours_time_work,date_format(m.work_hours_time_work,'%d-%m-%y')as `date`,"
					+ " time(m.work_hours_time_work)as `time`,u.role_id as 'u_rold',u.name as 'u_name',m.work_hours_type as `check_type`"
					+ " FROM work_hours m " 
					+ " inner join `user` u on m.user_create = u.id"
					+ " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListall;
	}
	@Override
	public List<Map<String, Object>> workallTime()
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListall = null;
		try {
			String sql = "SELECT id,work_time_start FROM user";

			SQLQuery query = session.createSQLQuery(sql);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListall;
	}

	@Override
	public List<Map<String, Object>> searchListallapprove(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListall = null;
		try {
			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) AS mymin "
					+ " ,m.work_hours_id,m.work_hours_type,m.work_hours_time_work,m.user_create,m.time_create,m.ip_address,description "
					+ " FROM work_hours m " + " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 2 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListall;
	}

	@Override
	public List<Map<String, Object>> searchListallapprovecheckin(java.sql.Timestamp Datestart,
			java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListallcheckin = null;
		try {
			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) AS mymin "
					+ " ,m.work_hours_id,m.work_hours_type,m.work_hours_time_work,m.user_create,m.time_create,m.ip_address,description "
					+ " FROM work_hours m " + " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 1 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListallcheckin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListallcheckin;
	}

	@Override
	public List<Map<String, Object>> searchListallapprovecheckinuser(String currentUserlist,
			java.sql.Timestamp Datestart, java.sql.Timestamp Dateend) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListallcheckin = null;
		try {
			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) AS mymin "
					+ " ,m.work_hours_id,m.work_hours_type,m.work_hours_time_work,m.user_create,m.time_create,m.ip_address,description "
					+ " FROM work_hours m "
					+ " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend AND user_create = :currentUserlist "
					+ " AND work_hours_type = 1 " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListallcheckin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListallcheckin;
	}

	@Override
	public List<Map<String, Object>> searchListalltime(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListalltime = null;
		try {
			String sql = "select workinghours " + " FROM work_hours m "
					+ " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend " + " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListalltime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListalltime;
	}

	@Override
	public List<Map<String, Object>> searchListalltimeapprove(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListalltime = null;
		try {
			String sql = "select workinghours " + " FROM work_hours m "
					+ " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend " + " AND work_hours_type = 2 "
					+ " ORDER BY work_hours_id ASC ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListalltime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListalltime;
	}

	public List<Map<String, Object>> searchHisall(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchHisall = null;
		try {

			String sql = "select hour(m.work_hours_time_work) AS myhour, minute(m.work_hours_time_work) "
					+ " AS mymin,work_hours_id,work_hours_type,work_hours_time_work,user_create,time_create,ip_address,description "
					+ " from work_hours m " + " WHERE work_hours_time_work BETWEEN :Datestart AND :Dateend "
					+ " AND work_hours_type = 2 " + " ORDER BY  work_hours_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchHisall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchHisall;
	}

	@Override
	public List<Map<String, Object>> checkin(String usercheck, String y, String m, String d) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkintime = null;
		try {
			String sql = "SELECT work_hours_id " + " FROM work_hours "
					+ " WHERE YEAR(work_hours_time_work)=:y AND work_hours_type=1 "
					+ " AND MONTH(work_hours_time_work)=:m AND DAY(work_hours_time_work)=:d AND user_create=:usercheck "
					+ " ORDER BY work_hours_id DESC " + " LIMIT 1	";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("y", y);
			query.setParameter("m", m);
			query.setParameter("d", d);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkintime = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkintime;
	}

	@Override
	public List<Map<String, Object>> idtoday(String usercheck, String y, String m, String d) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> idtoday = null;
		try {
			String sql = "SELECT work_hours_id " + " FROM work_hours "
					+ " WHERE YEAR(work_hours_time_work)=:y AND work_hours_type=2 "
					+ " AND MONTH(work_hours_time_work)=:m AND DAY(work_hours_time_work)=:d AND user_create=:usercheck "
					+ " ORDER BY work_hours_id ASC " + " LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("y", y);
			query.setParameter("m", m);
			query.setParameter("d", d);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			idtoday = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idtoday;
	}

	@Override
	public List<Map<String, Object>> hourtimeidtodaycheckin(String usercheck, String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> idtodaycheckin = null;
		try {

			String sql = " SELECT hour(work_hours_time_work) FROM work_hours "
					+ " WHERE work_hours_id < :id AND work_hours_type=1 AND user_create=:usercheck  "
					+ " ORDER By work_hours_id DESC LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			idtodaycheckin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idtodaycheckin;
	}

	@Override
	public List<Map<String, Object>> mintimeidtodaycheckin(String usercheck, String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> idtodaycheckin = null;
		try {

			String sql = " SELECT minute(work_hours_time_work) FROM work_hours "
					+ " WHERE work_hours_id < :id AND work_hours_type=1 AND user_create=:usercheck  "
					+ " ORDER By work_hours_type DESC LIMIT 1 ";
			/*
			 * String sql = "SELECT work_hours_id " + " FROM work_hours " +
			 * " WHERE work_hours_id < id AND work_hours_type=1 ORDER BY work_hours_id DESC "
			 * + " LIMIT 1 ";
			 */
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			idtodaycheckin = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idtodaycheckin;
	}

	@Override
	public List<Map<String, Object>> fullidtimecheckout(String usercheck, String y, String m, String d)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> fullidtimecheckout = null;
		try {
			String sql = "SELECT work_hours_id " + " FROM work_hours "
					+ " WHERE YEAR(work_hours_time_work)=:y AND work_hours_type=2 "
					+ " AND MONTH(work_hours_time_work)=:m AND DAY(work_hours_time_work)=:d AND user_create=:usercheck ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("y", y);
			query.setParameter("m", m);
			query.setParameter("d", d);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			fullidtimecheckout = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullidtimecheckout;
	}

	@Override
	public List<Map<String, Object>> searchidcheckintomorrow(String usercheck, String idcheckintoday) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchidcheckintomorrow = null;
		try {
			String sql = "SELECT work_hours_id " + " FROM work_hours " + " WHERE work_hours_type=2 "
					+ " AND work_hours_id >:idcheckintoday AND user_create=:usercheck " + " ORDER BY work_hours_id ASC "
					+ " LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("idcheckintoday", idcheckintoday);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchidcheckintomorrow = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchidcheckintomorrow;
	}

	@Override
	public List<Map<String, Object>> searchtimehourcheckintomorrow(String usercheck, String idcheckintoday) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchidcheckintomorrow = null;
		try {
			String sql = "SELECT hour(work_hours_time_work) " + " FROM work_hours " + " WHERE work_hours_type=2 "
					+ " AND work_hours_id >:idcheckintoday AND user_create=:usercheck " + " ORDER BY work_hours_id ASC "
					+ " LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("idcheckintoday", idcheckintoday);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchidcheckintomorrow = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchidcheckintomorrow;
	}

	@Override
	public List<Map<String, Object>> searchtimemincheckintomorrow(String usercheck, String idcheckintoday) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchidcheckintomorrow = null;
		try {
			String sql = "SELECT minute(work_hours_time_work) " + " FROM work_hours " + " WHERE work_hours_type=2 "
					+ " AND work_hours_id >:idcheckintoday AND user_create=:usercheck " + " ORDER BY work_hours_id ASC "
					+ " LIMIT 1 ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setParameter("idcheckintoday", idcheckintoday);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchidcheckintomorrow = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchidcheckintomorrow;
	}

	@Override
	public List<Map<String, Object>> timeidcheckouthour(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timeidcheckout = null;
		try {
			String sql = "SELECT hour(work_hours_time_work) AS myhour " + " FROM work_hours "
					+ " WHERE work_hours_id=:id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timeidcheckout = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeidcheckout;
	}

	@Override
	public List<Map<String, Object>> timeidcheckoutmin(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timeidcheckout = null;
		try {
			String sql = "SELECT minute(work_hours_time_work) AS minute " + " FROM work_hours "
					+ " WHERE work_hours_id=:id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timeidcheckout = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeidcheckout;
	}

	@Override
	public List<Map<String, Object>> checkin1(String usercheck) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> checkintime1 = null;
		try {
			String sql = "SELECT work_hours_id " + " FROM work_hours "
					+ " WHERE user_create=:usercheck AND work_hours_type=1 " + " ORDER BY work_hours_id DESC "
					+ " LIMIT 1	";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("usercheck", usercheck);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			checkintime1 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkintime1;
	}

	@Override
	public List<Map<String, Object>> timecheckin1(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timecheckin1 = null;
		try {
			String sql = " SELECT hour(work_hours_time_work) " + " FROM work_hours " + " WHERE work_hours_id=:id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timecheckin1 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timecheckin1;
	}

	@Override
	public List<Map<String, Object>> timecheckin2(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timecheckin2 = null;
		try {
			String sql = " SELECT minute(work_hours_time_work) " + " FROM work_hours " + " WHERE work_hours_id=:id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timecheckin2 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timecheckin2;
	}

	@Override
	public WorkHours findById(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		WorkHours workHours = null;
		try {
			workHours = (WorkHours) session.get(WorkHours.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return workHours;
	}
	
	
	// OAT
	
	
	public List<Map<String, Object>> graph_workhours(String user,String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {
         
            
			String sql = " SELECT work_hours_time_work  FROM work_hours  WHERE work_hours_type= 1 and user_create=:user " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' "
		//	"and date(work_hours_time_work) >=:year and date(work_hours_time_work) <=:year ";
				+ "order by date(work_hours_time_work) asc";

			
			
			SQLQuery query = session.createSQLQuery(sql);			  
			query.setParameter("user", user);
		//	query.setParameter("year", year);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}


	public List<Map<String, Object>> graph_workhours_out(String user,String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> work_status = null;
		try {
         
            
			String sql = " SELECT work_hours_time_work,workinghours  FROM work_hours  WHERE work_hours_type= 2 and user_create=:user " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' "
					+ "order by date(work_hours_time_work) asc";

			
			
			SQLQuery query = session.createSQLQuery(sql);			  
			query.setParameter("user", user);
		//	query.setParameter("year", year);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<String> graph_workhours_avg_in(String user, String year) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<String> work_status = null;
		try {
         
            
			String sql = " SELECT  TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%Hh %im')  FROM work_hours  WHERE work_hours_type= 1 and user_create=:user " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' ";
		
			SQLQuery query = session.createSQLQuery(sql);			  
			query.setParameter("user", user);
		//	query.setParameter("year", year);

		//	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;

	}
	public List<String> graph_workhours_avg_in(String year) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<String> work_status = null;
		try {
               
			String sql = " SELECT  TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%Hh %im')  FROM work_hours  WHERE work_hours_type= 1 " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' ";
		
			SQLQuery query = session.createSQLQuery(sql);			  
		

		//	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;

	}

	@Override
	public List<String> graph_workhours_avg_out(String user, String year) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<String> work_status = null;
		try {
         
            
			String sql = " SELECT TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%Hh %im') AS text FROM work_hours  WHERE work_hours_type= 2 and user_create=:user " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' ";
					

			
			
			SQLQuery query = session.createSQLQuery(sql);			  
			query.setParameter("user", user);
		//	query.setParameter("year", year);

		//	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			work_status = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;

	}	
	
	public String graph_workhours_out_avg(String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		 String work_status = null;
		try {           
			String sql = " SELECT TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%H:%i:%s')  FROM work_hours  WHERE work_hours_type= 2 " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' ";			
			SQLQuery query = session.createSQLQuery(sql);	
			
			work_status = query.uniqueResult().toString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;

	}	

	public String graph_workhours_in_avg(String year) throws Exception { //à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹�à¸Ÿà¸�à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹‚ï¿½à¸Œà¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜â€¦à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜â€¢à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‹à¹�à¸Ÿà¸�à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹‚ï¿½à¸Œà¹�à¸Ÿà¸�à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹‚ï¿½à¸Œà¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹‚ï¿½à¸Œà¹‚â‚¬ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹€à¸˜ï¿½à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸˜à¸‚à¹€à¸˜â€˜à¹€à¸˜à¸ƒà¸¢Â à¹€à¸˜à¸‚à¹€à¸˜ï¿½à¹€à¸™ï¿½à¹�à¸Ÿà¸�à¹€à¸˜ï¿½
		Session session = this.sessionFactory.getCurrentSession();
		 String work_status = null;
		try {           
			String sql = " SELECT TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%H:%i:%s')  FROM work_hours  WHERE work_hours_type= 1 " +
			"and date(work_hours_time_work) LIKE '%"+year+"%' ";			
			SQLQuery query = session.createSQLQuery(sql);	
			
			work_status = query.uniqueResult().toString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return work_status;
	}

	@Override
	public List<String> graph_workhours_avg_out(String year) throws Exception {
		// TODO Auto-generated method stub
				Session session = this.sessionFactory.getCurrentSession();
				List<String> work_status = null;
				try {
		        		            
					String sql = " SELECT TIME_FORMAT(SEC_TO_TIME(AVG(TIME_TO_SEC(work_hours_time_work))),'%Hh %im') AS text FROM work_hours  WHERE work_hours_type= 2 " +
					"and date(work_hours_time_work) LIKE '%"+year+"%' ";
										
					SQLQuery query = session.createSQLQuery(sql);			  
		
					work_status = query.list();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return work_status;

	}
	
	//18-01-2019
	@Override
	public List<Map<String, Object>> checkInMapAll(java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListall = null;
		try {
			String sql = "SELECT w.work_hours_id as `id`, " 
					+ " u.name as `name`, "
					+ "u.role_id as `role`, "
					+ "w.latitude as `lat`, "
					+ "w.longitude as `lng`, "
					+ "w.work_hours_type as `check_type`, "					
					+ "date_format(w.work_hours_time_work, '%d-%m-%Y') as `date`, "
					+ "time(w.work_hours_time_work) as `time` "
					+ "from work_hours w "
					+ "inner join `user` u on w.user_create = u.id "
					+ "where w.work_hours_time_work between :Datestart AND :Dateend "
					+ "ORDER by w.work_hours_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListall;
	}
	
	@Override
	public List<Map<String, Object>> checkInMap(String name, java.sql.Timestamp Datestart, java.sql.Timestamp Dateend)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchListall = null;
		try {
			String sql = "SELECT w.work_hours_id as `id`, " 
					+ " u.name as `name`, "
					+ "u.role_id as `role`, "
					+ "w.latitude as `lat`, "
					+ "w.longitude as `lng`, "
					+ "w.work_hours_type as `check_type`, "	
					+ "date_format(w.work_hours_time_work, '%d-%m-%Y') as `date`, "
					+ "time(w.work_hours_time_work) as `time` "
					+ "from work_hours w "
					+ "inner join `user` u on w.user_create = u.id "
					+ "where :Name = w.user_create and w.work_hours_time_work between :Datestart AND :Dateend "
					+ "ORDER by w.work_hours_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("Name", name);
			query.setParameter("Datestart", Datestart);
			query.setParameter("Dateend", Dateend);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchListall = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchListall;
	}
	
	public List<Map<String, Object>> checkStatusCheckIn(String month, String year, String limit)throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> statuscheckin = null;
		try {
			String sql ="SELECT `work_hours_id`,HOUR(`work_hours_time_work`) as hour_time_work, "
					+ "MINUTE(`work_hours_time_work`) as minute_time_work,`user_create`,`name`,COUNT(`user_create`) as count, "
					+ "HOUR(`work_time_start`) as hour_time_start,MINUTE(`work_time_start`) as minute_time_start "
					+ "FROM work_hours LEFT JOIN user on `user_create`= `id` WHERE `work_hours_type` = 1 "
					+ "AND MONTH(`work_hours_time_work`) = "+month+" AND YEAR(`work_hours_time_work`) = "+year+" "
					+ "AND (HOUR(`work_hours_time_work`) < HOUR(`work_time_start`) OR "
					+ "MINUTE(`work_hours_time_work`) < MINUTE(`work_time_start`)) GROUP BY `user_create` ORDER BY count DESC LIMIT "+limit+"";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			statuscheckin = query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return statuscheckin;
	}
	
	public List<Map<String, Object>> checkStatusLate(String month, String year, String limit)throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> statuscheckin = null;
		try {
			String sql ="SELECT `work_hours_id`,`work_hours_time_work` as hour_time_work,MINUTE(`work_hours_time_work`) as minute_time_work,"
					+ "`user_create`,`name`,COUNT(`user_create`) as count, HOUR(`work_time_start`) as hour_time_start,"
					+ "MINUTE(`work_time_start`) as minute_time_start FROM work_hours LEFT JOIN user on `user_create`= `id` "
					+ "WHERE `work_hours_type` = 1 AND MONTH(`work_hours_time_work`) = "+month+" AND YEAR(`work_hours_time_work`) = "+year+" "
					+ "AND (HOUR(`work_hours_time_work`) > HOUR(`work_time_start`) OR (HOUR(`work_hours_time_work`) = HOUR(`work_time_start`) "
					+ "AND MINUTE(`work_hours_time_work`) > MINUTE(`work_time_start`)))GROUP BY `user_create` ORDER BY count DESC LIMIT "+limit+"";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			statuscheckin = query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return statuscheckin;
	}
	@Override
	public List<Map<String, Object>> searchByMonthAndYearDescriptionNull(String monthSelect, String yearSelect,int amoutDataInt) 
			throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchMonth = null;
		try { 
			String sql = "SELECT user_create, sum(workinghours) as total,time_create "
						+"FROM work_hours WHERE MONTH(time_create) = "+monthSelect+" AND YEAR(time_create) = "+yearSelect
						+" AND `description`= \"\" group by user_create order by total desc limit "+amoutDataInt;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchMonth = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchMonth;
	}
	@Override
	public List<Map<String, Object>> searchByMonthAndYearDescriptionNotNull(String monthSelect, String yearSelect) 
			throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchMonth = null;
		try { 
			String sql = "SELECT user_create, count(`work_hours_type`)*480 as total "
						+"FROM work_hours WHERE work_hours_type = 2 AND MONTH(time_create) = "+monthSelect+" AND YEAR(time_create) = "+yearSelect
						+" AND `description`<> \"\" group by user_create order by total desc";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchMonth = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchMonth;
	}
	
	@Override
	public List<Map<String, Object>> searchWorkCountbyUser(String user,int year,int month) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchUser = null;
		try { 
			String sql = "SELECT user_create, sum(workinghours) as total "
						+"FROM  work_hours WHERE `description`=\"\" AND work_hours_type = 2 AND user_create ='"+user+"' AND MONTH(time_create) = "+month+" AND YEAR(time_create) = "+year
						;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchUser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchUser;
	}
	@Override
	public List<Map<String, Object>> searchWorkCountDescriptionNotNullbyUser(String user,int year,int month) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> searchUser = null;
		try { 
			String sql = "SELECT user_create, count(`work_hours_type`)*480 as total "
						+"FROM  work_hours WHERE `description`<>\"\" AND work_hours_type = 2 AND user_create ='"+user+"' AND MONTH(time_create) = "+month+" AND YEAR(time_create) = "+year
						;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			searchUser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchUser;
	}
	
	@Override
	public List<Map<String, Object>> check_work(String currentUserlist, String type) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> check_work = null;
		try {
			String myStr = null;
			if(type != null) {
				myStr = "AND work_hours_type =" +type;
			}
			String sql = " SELECT * FROM `work_hours` "
					+ " WHERE user_create ='"+ currentUserlist +"' AND DATE(work_hours_time_work) = CURRENT_DATE "+myStr
					+ " ORDER BY `work_hours_time_work` DESC ";
					
					
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			check_work = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check_work;
	}
	
	@Override
	public List<Map<String, Object>> test_workhoursummary2(String id,int year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> test_workhoursummary = null;
		try {
			
			String sql = "SELECT DAY(work_hours_time_work) AS DAY,MONTH(work_hours_time_work) AS MONTH,workinghours,work_hours_type,work_hours_time_work FROM `work_hours` WHERE user_create ='"+ id +"' AND YEAR(work_hours_time_work)='"+ year +"' AND workinghours > 0 GROUP BY DATE(work_hours_time_work) ORDER BY DATE(work_hours_time_work) ASC " ;
			
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			test_workhoursummary = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test_workhoursummary;
	}
	
	@Override
	public List<Map<String, Object>> test_timeckeckin(String type, String logonUser,int year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> test_workhoursummary = null;
		try {
			
			String sql = "SELECT TIME_FORMAT(work_hours_time_work, \"%H:%i\") AS TIME,DAY(work_hours_time_work) AS DAY,MONTH(work_hours_time_work) AS MONTH FROM `work_hours` WHERE user_create ='"+ logonUser +"' AND work_hours_type= '" + type + "' AND YEAR(work_hours_time_work)='"+ year +"' GROUP BY DATE(work_hours_time_work)" ;
			
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			test_workhoursummary = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test_workhoursummary;
	}
	
	@Override
	public List<Map<String, Object>> test_workhoursummary(String id,int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_workhoursummary = null;
	try {

	String sql = "SELECT work_hours_id,work_hours_type,work_hours_time_work,user_create FROM `work_hours`" + "WHERE user_create ='"+ id +"' AND YEAR(work_hours_time_work)='" + year +"' GROUP BY DATE(work_hours_time_work) " ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_workhoursummary = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_workhoursummary;
	}
	
	@Override
	public List<Map<String, Object>> test_workHoursAnniversary() throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_workHoursAnniversary = null;
	try {

	String sql = "SELECT work_hours_time_work,user_create,DAY(MIN(work_hours_time_work))AS DAYMIN,DAY(MAX(work_hours_time_work))AS DAYMAX,MONTH(MIN(work_hours_time_work))AS MONTHMIN,MONTH(MAX(work_hours_time_work))AS MONTHMAX,YEAR(MIN(work_hours_time_work))AS YEARMIN,YEAR(MAX(work_hours_time_work))AS YEARMAX,user.enable FROM `work_hours` LEFT JOIN user ON work_hours.user_create = user.id WHERE enable=1 GROUP BY(user_create) ORDER BY MONTH(MIN(work_hours_time_work)),DAY(MIN(work_hours_time_work)) ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_workHoursAnniversary = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_workHoursAnniversary;
	}
	
	@Override
	public List<Map<String, Object>> test_lastWorkHour(int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_lastWorkHour = null;
	try {

	String sql = "SELECT DAY(work_hours_time_work)AS DAY,MONTH(work_hours_time_work)AS MONTH,TIME(work_hours_time_work)AS time,user_create,work_hours_type,user.enable FROM `work_hours`LEFT JOIN user ON work_hours.user_create = user.id WHERE enable=1 AND work_hours_type=1 AND YEAR(work_hours_time_work)='"+ year +"' ORDER BY MONTH(work_hours_time_work),DAY(work_hours_time_work) ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_lastWorkHour = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_lastWorkHour;
	}
	@Override
	public List<Map<String, Object>> test_lastWorkHour2(int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_lastWorkHour = null;
	try {

	String sql = "SELECT date(work_hours_time_work),COUNT(user_create)AS count,DAY(work_hours_time_work)AS DAY,MONTH(work_hours_time_work)AS MONTH,user.enable FROM `work_hours` LEFT JOIN user ON work_hours.user_create = user.id WHERE  work_hours_type=1 AND ENABLE=1 AND YEAR(work_hours_time_work)='"+year+"' GROUP BY date(work_hours_time_work)   ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_lastWorkHour = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_lastWorkHour;
	}
	
	@Override
	public List<Map<String, Object>> todayLogin(String date)
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> data = null;
		try {
			String sql = "SELECT user_create, MAX(work_hours_id) AS work_hours_id, work_hours_type, DATE_FORMAT(time_create,'%d/%m/%Y %H:%i') AS time_create FROM work_hours WHERE DATE(time_create) = '"+date+"' GROUP BY user_create DESC";		
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			data = query.list();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	}
	
	