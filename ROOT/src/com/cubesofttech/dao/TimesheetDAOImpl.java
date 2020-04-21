package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Timesheet;

@Repository
public class TimesheetDAOImpl implements TimesheetDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getLogger(TimesheetDAOImpl.class);

	@Override
	public void save(Timesheet timesheet) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(timesheet);
		session.flush();

	}

	@Override
	public List<Map<String, Object>> sequense() throws Exception {

		return null;
	}

	@Override
	public List<Timesheet> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Timesheet> timesheet = null;
		try {
			String sql = "SELECT * FROM timesheet";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet = query.list();
		} catch (Exception e) {

		}
		return timesheet;

	}

	
	@Override
	public void delete(Timesheet timesheet) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(timesheet);
		session.flush();

	}
	/*@Override
	public List<Map<String, Object>> checkholiday() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timesheet_status = null;
		try {
			String sql = "SELECT `start_date`, `end_date` FROM `holiday`";

			SQLQuery query = session.createSQLQuery(sql);
			//query.setParameter("id_date", userID);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet_status = query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return timesheet_status;
	}*/
	@Override
	public List<Map<String, Object>> findHoliday() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> holidaysheet = null;
		  try {
		   String sql =  " SELECT start_date,end_date FROM holiday";
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
	public List<Map<String, Object>> findHoliday2() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> holidaysheet = null;
		  try {
		   String sql =  " SELECT start_date,end_date FROM holiday";
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
	public List<Map<String, Object>> findTimeInTimeOutBytimeMonthYear(String user,String startDay,String endDay) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timesheet = null;
		  try {
		   String sql = 	"SELECT date(workout.work_hours_time_work) AS date,workin.work_hours_time_work AS workin,workout.work_hours_time_work AS workout FROM work_hours workin "
				   			+"LEFT JOIN work_hours workout on  date(workout.work_hours_time_work) = date(workin.work_hours_time_work) "
				   			+"Where workin.work_hours_time_work >= :startDay and workin.work_hours_time_work <= :endDay  "
				   			+"AND workout.work_hours_time_work >= :startDay and workout.work_hours_time_work <= :endDay "
				   			+"and workin.work_hours_type = 1 and workout.work_hours_type = 2 and workin.user_create = :user "
				   			+"and workout.user_create = :user";	
		   SQLQuery query = session.createSQLQuery(sql);
		   query.setParameter("user",user);
		   query.setParameter("startDay",startDay);
		   query.setParameter("endDay",endDay);
		   
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet = query.list();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return timesheet;
	}

	@Override
	public List<Map<String, Object>> findDescription(String user, String startDay,String endDay) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timesheet = null;
		  try {
		   String sql =  " SELECT description,time_check_in,time_check_out,id,status  FROM timesheet Where time_check_in >= :startDay and time_check_in <= :endDay and user_create = :user ";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.setParameter("user",user);
		   query.setParameter("startDay",startDay);
		   query.setParameter("endDay",endDay);
		   
		   
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet = query.list();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return timesheet;
	}

	@Override
	public void update(Timesheet timesheet) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(timesheet);
		session.flush();
	}

	@Override
	public List<Map<String, Object>> searchIdTimesheet(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timesheet = null;
		  try {
			  String sql = "SELECT id FROM timesheet  Where id = :id";
		   SQLQuery query = session.createSQLQuery(sql);
		   query.setParameter("id",id);
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet = query.list();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return timesheet;
	}

	@Override
	public List<Map<String, Object>> searchTimesheet(String user, String date) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> timesheet = null;
		  try {
			  String sql = " SELECT id, user_create,date(time_check_in) as date FROM timesheet Where time_check_in LIKE '%"+date+"%'  and user_create = :user "; 
		   SQLQuery query = session.createSQLQuery(sql);
		   query.setParameter("user",user);
		   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			timesheet = query.list();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		  }
		  return timesheet;

	}
	@Override
	public List<Map<String, Object>> timesheetSearch_forCalendar(String userId, String from, String to) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;
		try {
			if (from == null || to == null) {
				String sql = "SELECT * FROM timesheet WHERE user_create =  :userId ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				expSearch = query.list();
			} else if (from != null && to != null) {
				String sql = "SELECT * FROM timesheet WHERE user_create =  :userId  AND time_create BETWEEN :from  and :to   ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				query.setParameter("from", from);
				query.setParameter("to", to);
				// query.addEntity(Expense.class);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				expSearch = query.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return expSearch;
	}
	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Timesheet> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(Timesheet.class).setProjection(Projections.max("id"));
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
	public Timesheet findById(Integer id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Timesheet timesheet = (Timesheet) session.get(Timesheet.class,id);
		return timesheet;
	}
	@Override
	public List<Map<String, Object>> searchTimesheetByUserCreate (String userId) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;
		try {
			String sql = "SELECT * FROM timesheet WHERE user_create =  :userId";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			expSearch = query.list();
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {

		}
		return expSearch;
	}
	
	@Override
	public List<Map<String, Object>> searchTimesheetByTimeUpdate (String userId) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;
		try {
			String sql = "SELECT * FROM timesheet WHERE user_create =  :userId ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userId", userId);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			expSearch = query.list();
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {

		}
		return expSearch;
	}
	
	
	public List<Timesheet> findAll_calendar() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		 List<Timesheet> timesheetList = null;
		try {
			String sql =" SELECT * FROM timesheet order by id ASC;";
			SQLQuery query = session.createSQLQuery(sql);
		//	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			 query.addEntity(Timesheet.class);

			 timesheetList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return timesheetList;
	}
	
}
