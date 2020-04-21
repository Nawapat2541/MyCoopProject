package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Expense;
import com.google.gson.Gson;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Expense expense) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(expense);
		session.flush();
		// session.close();
	}

	@Override
	public List<Expense> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Expense> Expense = null;
		try {
			Expense = session.createCriteria(Expense.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return Expense;
	}

	@Override
	public void update(Expense expense) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(expense);
		session.flush();
		// session.close();
	}


	@Override
	public void delete(Expense expense) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(expense);
		session.flush();
		// session.close();
	}

	@Override
	public List<Expense> findByExpenseId(int expenseId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Expense> list = null;
		try {

			Criteria cr = session.createCriteria(Expense.class);
			cr.add(Restrictions.eq("expenseId", expenseId));
			list = cr.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}
	// take all the data to edit page for edit page 
	@Override
	public List<Expense> findByExpId(int expenseId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Expense> list = null;
		try {
			int expenseId1 = expenseId ;
			String sql = "SELECT expense.* , user.name , user.department_id ,exp_travel_type.exp_travel_type_id , exp_travel_type.name AS exp_travel_name FROM `expense` LEFT JOIN user ON expense.user_id = user.id LEFT JOIN exp_travel_type ON expense.dt_by = exp_travel_type.exp_travel_type_id WHERE expense_id =" + expenseId1 ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	/*  
	public int[] findAmountByExpenseGroupId(int expenseGroupId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		int[] amount = null;
		try {
			int expGroupId = expenseGroupId ;
			String sql = "SELECT amount FROM `expense` WHERE expense_group_id =" + expGroupId + " ORDER BY `expense_id` ASC" ;
			SQLQuery query = session.createSQLQuery(sql);
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			amount = query;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	*/
	// for update total amount
	@Override
	public String findAmountByExpenseGroupId(int expenseGroupId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		String result = null;
		List<Map<String,String>> list = null;
		try {
			int expGroupId = expenseGroupId ;
			String sql = "SELECT SUM(amount) FROM `expense` WHERE expense_group_id =" + expGroupId ;
			// list = session.createQuery(sql).list();			
			SQLQuery query = session.createSQLQuery(sql);	
			
			result = query.uniqueResult().toString();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@Override
	public Expense findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Expense expense = null;
		try {
			expense = (Expense) session.get(Expense.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return expense;
	}

	@Override
	public Expense findById(int id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Expense expense = null;
		try {
			expense = (Expense) session.get(Expense.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return expense;
	}

	@Override
	public Integer getMaxId() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Expense> list = null;
		Integer maxId;

		try {

			Criteria criteria = session.createCriteria(Expense.class).setProjection(Projections.max("expenseId"));
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
	public List<Map<String, Object>> findExpense() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> expSearch(String userId, String from, String to) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;

		try {
			if (from == null || to == null) {
				String sql = "SELECT * FROM expense WHERE user_id =  :userId ORDER BY expense_group_id ASC, dt_start ASC ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				expSearch = query.list();
			} else if (from != null && to != null) {
				String sql = "SELECT * FROM expense WHERE user_id =  :userId  AND time_create BETWEEN :from  AND :to ORDER BY expense_group_id ASC, dt_start ASC  ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				query.setParameter("from", from);
				query.setParameter("to", to);
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
	public List<Map<String, Object>> expSearch_forCalendar(String userId, String from, String to) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;

		try {
			if (from == null || to == null) {
				String sql = "SELECT * FROM expense WHERE user_id =  :userId ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				expSearch = query.list();
			} else if (from != null && to != null) {
				String sql = "SELECT * FROM expense WHERE user_id =  :userId  AND time_create BETWEEN :from  AND :to   ";
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
	public int[] count_exp_type() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		int[] count = new int[6];
		int count_taxi = 0;
		int count_mrt = 0;
		int count_bts = 0;
		int count_motor = 0;
		int count_express = 0;
		int count_total = 0;
		try {
			String sql = " SELECT COUNT(expense_id) " + " FROM expense " + "where dt_by = 1 ";
			String sql2 = " SELECT COUNT(expense_id) " + " FROM expense " + "where dt_by = 2 ";
			String sql3 = " SELECT COUNT(expense_id) " + " FROM expense " + "where dt_by = 3 ";
			String sql4 = " SELECT COUNT(expense_id) " + " FROM expense " + "where dt_by = 5 ";
			String sql5 = " SELECT COUNT(expense_id) " + " FROM expense " + "where dt_by = 8 ";
			String sql6 = " SELECT COUNT(expense_id) " + " FROM expense ";

			SQLQuery query = session.createSQLQuery(sql);
			SQLQuery query2 = session.createSQLQuery(sql2);
			SQLQuery query3 = session.createSQLQuery(sql3);
			SQLQuery query4 = session.createSQLQuery(sql4);
			SQLQuery query5 = session.createSQLQuery(sql5);
			SQLQuery query6 = session.createSQLQuery(sql6);

			count_taxi = ((Number) query.uniqueResult()).intValue();
			count_mrt = ((Number) query2.uniqueResult()).intValue();
			count_bts = ((Number) query3.uniqueResult()).intValue();
			count_motor = ((Number) query4.uniqueResult()).intValue();
			count_express = ((Number) query5.uniqueResult()).intValue();
			count_total = ((Number) query6.uniqueResult()).intValue();

			count[0] = count_taxi;
			count[1] = count_mrt;
			count[2] = count_bts;
			count[3] = count_motor;
			count[4] = count_express;
			count[5] = count_total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> expSearch(String userId, String from, String to, String status_id) // Search
																										// with
																										// Form
			throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;
		String condition = "AND expense_group.status_id = '" + status_id + "'";
		if ("All".equals(status_id)) {
			condition = "";
		}

		try {
			if ("0".equals(status_id)) {
				String sql = " SELECT expense.expense_id,expense.dt_start,expense.dt_end,expense.from_location,expense.to_location,expense.dt_by,expense.amount,expense.user_id,expense.expense_group_id,expense.time_create,expense_group.status_id "
						+ " FROM expense "
						+ " LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
						+ " WHERE expense.user_id = '" + userId
						+ "'  AND  expense.expense_group_id='0' AND expense.time_create BETWEEN '" + from + "'  AND '"
						+ to + "' ORDER BY expense.expense_id desc  ";
				SQLQuery query = session.createSQLQuery(sql);
				// query.setParameter("userId", userId);
				// query.setParameter("from", from);
				// query.setParameter("to", to);
				// query.setParameter("status_id", status_id);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				expSearch = query.list();
			} else if (from != null && to != null) {
				String sql = " SELECT expense.expense_id,expense.dt_start,expense.dt_end,expense.from_location,expense.to_location,expense.dt_by,expense.amount,expense.user_id,expense.expense_group_id,expense.time_create,expense_group.status_id "
						+ " FROM expense "
						+ " LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
						+ " WHERE expense.user_id = '" + userId + "'   " + condition
						+ "  AND expense.time_create BETWEEN '" + from + "'  AND '" + to
						+ "' ORDER BY expense.expense_id desc  ";
				SQLQuery query = session.createSQLQuery(sql);
				// query.setParameter("userId", userId);
				// query.setParameter("from", from);
				// query.setParameter("to", to);
				// query.setParameter("status_id", status_id);
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
	public List<Map<String, Object>> findUserbyYear(String year) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> expSearch = null;

		try {

			String sql = "SELECT expense.user_id , user.name FROM expense"
					+ " left join user on expense.user_id = user.id  "
					+ " where year(expense.dt_start) = '" + year + "' group by expense.user_id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			expSearch = query.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return expSearch;
	}

	@Override
	public List<Map<String, Object>> ReportByuser_PAID(String year, String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT COALESCE(sum(expense_group.total_amount),0) as sum FROM  expense_group "
					+ "	 "
					+ "	where expense_group.paid_year = '"+year+"' and expense_group.paid_month = '1'  "
					+ "	and  expense_group.status_id = 'P' and expense_group.user_id  ='" + user + "' ";
			for (int i = 2; i <= 12; i++) {
				sql += " UNION ALL "
						+ "SELECT COALESCE(sum(expense_group.total_amount),0) as sum FROM  expense_group "
						+ "	 "
						+ "	where expense_group.paid_year = '"+year+"' and expense_group.paid_month = '"+i+"'  "
						+ "	and  expense_group.status_id = 'P' and expense_group.user_id  ='" + user + "' \n ";
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
	public List<Map<String, Object>> ReportByuser_REJECT(String year, String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT expense_group.user_id , COALESCE(sum(expense_group.total_amount),0) as sum FROM  expense_group "
					+ "	 "
					+ "	where year(expense_group.time_create) = '"+year+"' and month(expense_group.time_create) = '1'  "
					+ "	and  expense_group.status_id = 'R' and expense_group.user_id  ='" + user + "' ";
			for (int i = 2; i <= 12; i++) {
				sql += " UNION ALL "
						+ "SELECT expense_group.user_id  , COALESCE(sum(expense_group.total_amount),0) as sum FROM  expense_group "
						+ "	 "
						+ "	where year(expense_group.time_create) = '"+year+"' and month(expense_group.time_create) = '"+i+"'  "
						+ "	and  expense_group.status_id = 'R' and expense_group.user_id  ='" + user + "' \n ";

			}
			sql += "group by expense_group.user_id";
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
	public List<Map<String, Object>> ReportByuser_REQUEST(String year, String user) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT expense.user_id , COALESCE(sum(expense.amount),0) as sum FROM expense "
					+ "	where year(expense.dt_start) = '"+year+"' and month(expense.dt_start) = '1'  "
					+ "	and expense.user_id ='" + user + "' and  expense.expense_group_id = 0 \n";
			for (int i = 2; i <= 12; i++) {
				sql += " UNION ALL "
						+ "SELECT expense.user_id , COALESCE(sum(expense.amount),0) as sum FROM expense "
						
						+ "	where year(expense.dt_start) = '"+year+"' and month(expense.dt_start) = '"+i+"'  "
						+ "	and expense.user_id ='" + user + "' and  expense.expense_group_id = 0  \n ";
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
	public List<Map<String, Object>> findByYearMonth(int year, int month) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> search = null;
		try {
			String sql = "SELECT * FROM expense where dt_start LIKE '" + year+"-"+month +"%'";
			
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
	public List<Map<String, Object>> sequense() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> user = null;
		try {
			String sql = "SELECT expense.* , user.name , user.department_id"
					+ " FROM `expense`"
					+ " LEFT JOIN user ON expense.user_id = user.id";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			user = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	


	
	@Override
	public List<Map<String, Object>> findAmountBy(String name, String expType, String expStatus , String top , int year)  throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> totalAmount = null;
		String whereName = "";
		String whereType = "";
		String whereYear = "";
		String status = " AND (expense_group.status_id = 'P' OR expense_group.status_id = 'W') ";
		String top10 = "";
		String year2 = Integer.toString(year);
		if (year2 != "") {
			whereYear = "  AND year(expense.time_create) = " + year +" " ;
		}
		if (name != "") {
			whereName = " AND expense.user_id = '" + name + "'" ;
		}
		if (expType != "") {
			whereType = " AND expense.dt_by = " +expType+ " ";
		}
		if (top == "top") {
			top10 = " GROUP BY expense.user_id order by total desc limit 10 ";
		} else if (top == "all") {
			top10 = " GROUP BY exp_travel_type.name , expense.user_id ORDER BY sum(expense.amount) DESC";
		}
		if ( expStatus.equals("P")) {
			status = " AND expense_group.status_id = 'P' ";
		} else if (expStatus.equals("W")) {
			status = " AND expense_group.status_id = 'W' ";
		}
		try {
			String sql = "SELECT expense.user_id , sum(expense.amount) as total ,exp_travel_type.name ,year(expense.time_create) as year ,expense.dt_by as expId, exp_travel_type.exp_travel_type_id , exp_travel_type.exp_travel_type_id as field"
					+ " FROM expense "
					+ " LEFT JOIN exp_travel_type ON expense.dt_by = exp_travel_type.exp_travel_type_id"
					+ " LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
					+ " WHERE  expense.amount IS NOT NULL " + status +  " "+whereName+ " "+whereYear + " "+whereType+" "+top10+" " ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			totalAmount = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmount;
	}

	@Override
	public List<Map<String, Object>> findAmountBy(String name, String expType, int year , String field)  throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> totalAmount = null;
		String whereName = "";
		String whereType = "";
		String whereYear = "";
		String field2 = "";
		String year2 = Integer.toString(year);
		if (year2 != "") {
			whereYear = "  AND year(expense.time_create) = " + year +" " ;
		}
		if (name != "") {
			whereName = " AND expense.user_id = '" + name + "'" ;
		}
		if (expType != "") {
			whereType = " AND expense.dt_by = " +expType+ " ";
		}
		if (field == "type") {
			field2 = "  exp_travel_type.name as field ,";
		} else if (field == "year") {
			field2 = "  exp_travel_type.exp_travel_type_id as field ,";
		}
		try {
			String sql = "SELECT expense.user_id , "+ field2 +" sum(expense.amount) as total ,exp_travel_type.name ,year(expense.time_create) as year ,expense.dt_by as expId, exp_travel_type.exp_travel_type_id "
					+ " FROM expense "
					+ " LEFT JOIN exp_travel_type ON expense.dt_by = exp_travel_type.exp_travel_type_id"
					+ " LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
					+ " WHERE  expense.amount IS NOT NULL AND expense_group.status_id = 'P'  "+whereName+ " "+whereYear + " "+whereType+"  GROUP BY exp_travel_type.name , expense.user_id ORDER BY sum(expense.amount) DESC " ;
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			totalAmount = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmount;
	}
	
	@Override
	public List<Map<String, Object>> findAmountByStatus(String status , String top, int year) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> totalAmountByPaid = null;
		String group = "";
		String whereYear = "";
		String year2 = Integer.toString(year);
		if (year2 != "") {
			whereYear = "  AND year(expense.time_create) = " + year +" " ;
		}
		if (top == "top") {
			group = "GROUP BY expense.user_id order by total desc limit 10" ;
		}
		try {
			String sql = "SELECT expense.user_id , sum(expense.amount) as total ,exp_travel_type.name , exp_travel_type.exp_travel_type_id as field "
					+" FROM expense"
					+" LEFT JOIN exp_travel_type ON expense.dt_by = exp_travel_type.exp_travel_type_id "
					+" LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
					+"  WHERE expense_group.status_id = '"+ status +"'" + " "+whereYear+ " " + group;
			 
					 
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			totalAmountByPaid = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmountByPaid;
	}
	
	@Override
	public List<Map<String, Object>> findFeildAsUserId(String name, String expType, int thisyear , int toyear , String findYear)  throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> totalAmount = null;
		String whereName = "";
		String whereType = "";
		String whereYear = "";
		String whereYear2 = "";
		String findFieldAsUserId = " expense.user_id , ";
		if ( findYear == "findYear") {
			findFieldAsUserId = " year(expense.time_create)  as user_id , year(expense.time_create) as field , " ;
		} else if ( findYear == "findType") {
			findFieldAsUserId = " exp_travel_type.name as user_id , exp_travel_type.name as field ,  ";
		}
		if (thisyear != 0) {
			whereYear = "  AND year(expense.time_create) = " + thisyear +" " ;
			whereYear2 = " GROUP BY exp_travel_type.exp_travel_type_id  ORDER BY sum(expense.amount) DESC ";
		}
		if (toyear != 0) {
			whereYear = "  AND year(expense.time_create) <= " + toyear +" " ;
			whereYear2 = "  GROUP BY year(expense.time_create)  ";
		}
		if (name != "") {
			whereName = " AND expense.user_id = '" + name + "'" ;
		}
		if (expType != "") {
			whereType = " AND expense.dt_by = " +expType+ " ";
		}
		
		try {
			String sql = "SELECT " + findFieldAsUserId + " sum(expense.amount) as total ,exp_travel_type.name ,year(expense.time_create) as year ,expense.dt_by as expId,exp_travel_type.exp_travel_type_id"
					+ " FROM expense "
					+ " LEFT JOIN exp_travel_type ON expense.dt_by = exp_travel_type.exp_travel_type_id"
					+ " LEFT JOIN expense_group ON expense.expense_group_id = expense_group.expense_group_id "
					+ " WHERE  expense.amount IS NOT NULL AND expense_group.status_id = 'P' "+whereName+ " "+whereYear + " "+whereType+" "  + whereYear2 +" ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			totalAmount = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalAmount;
	}

	// It should be implemented
	@Override
	public List<Map<String, Object>> findAmount(String top, int year)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override //All
	public List<Map<String, Object>> test_travelsummary(String userId, int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelsummary = null;
	String sql;
	try {

	//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
			if(userId.equals("All")) {
				sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE YEAR(time_create)= '"+ year +"' ORDER BY time_create ASC";
				
			}else {
				sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='"+ userId +"' AND YEAR(time_create)= '"+ year +"' GROUP BY (time_create) ASC";
			}
	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelsummary = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelsummary;
	}
	
	@Override //status = P
	public List<Map<String, Object>> test_travelsummaryP(String userId, int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelsummaryP = null;
	String sql;
	try {

	//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
			if(userId.equals("All")) {
				sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE YEAR(time_create)= '"+year+"' AND status_id = 'P' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
				
			}else {
				sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE user_id='"+userId+"' AND YEAR(time_create)= '"+year+"' AND status_id = 'P' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
			}
	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelsummaryP = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelsummaryP;
	}
	@Override //status = W
	public List<Map<String, Object>> test_travelsummaryW(String userId, int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelsummaryW = null;
	String sql;
	try {

	//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
		if(userId.equals("All")) {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE YEAR(time_create)= '"+year+"' AND status_id = 'W' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
			
		}else {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE user_id='"+userId+"' AND YEAR(time_create)= '"+year+"' AND status_id = 'W' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
		}
	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelsummaryW = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelsummaryW;
	}
	@Override //status = R
	public List<Map<String, Object>> test_travelsummaryR(String userId, int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelsummaryR = null;
	String sql;
	try {

	//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
		if(userId.equals("All")) {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE YEAR(time_create)= '"+year+"' AND status_id = 'R' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
			
		}else {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE user_id='"+userId+"' AND YEAR(time_create)= '"+year+"' AND status_id = 'R' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
		}
	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelsummaryR = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelsummaryR;
	}
	@Override //status = A
	public List<Map<String, Object>> test_travelsummaryA(String userId, int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelsummaryA = null;
	String sql;
	try {

	//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
		if(userId.equals("All")) {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE YEAR(time_create)= '"+year+"' AND status_id = 'A' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
			
		}else {
			sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR,SUM(total_amount)AS SUM FROM `expense_group` WHERE user_id='"+userId+"' AND YEAR(time_create)= '"+year+"' AND status_id = 'A' AND total_amount IS NOT NULL  GROUP BY MONTH ASC";
		}
	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelsummaryA = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelsummaryA;
	}
	
	@Override
	public List<Map<String, Object>> test_travelamount(int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelamount = null;
	try {

	String sql = "SELECT DAY(dt_start)AS DAY,MONTH(dt_start)AS MONTH,YEAR(dt_start)AS YEAR,user_create,amount,user.enable FROM `expense` LEFT JOIN user ON expense.user_create = user.id WHERE enable='1'  AND YEAR(dt_start)='"+ year +"' ORDER BY MONTH(dt_start),DAY(dt_start) ASC" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelamount = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelamount;
	}
	@Override
	public List<Map<String, Object>> test_travelamountAll(int year) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> test_travelamountAll = null;
	try {

	String sql = "SELECT DAY(dt_start)AS DAY1,MONTH(dt_start)AS MONTH1,YEAR(dt_start)AS YEAR1,SUM(amount)AS SUM,user.enable FROM `expense` LEFT JOIN user ON expense.user_create = user.id WHERE enable='1'  AND YEAR(dt_start)='"+year+"' GROUP BY date(dt_start)" ;

	SQLQuery query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	test_travelamountAll = query.list();
	} catch (Exception e) {
	e.printStackTrace();
	}
	return test_travelamountAll;
	}
	

}