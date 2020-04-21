package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.User;
import com.google.gson.Gson;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User User) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(User);
		session.flush();
		// session.close();
	}

	@Override
	public List<User> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = null;
		try {
			userList = session.createCriteria(User.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return userList;
	}

	@Override
	public User findById(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		User User = null;
		try {
			User = (User) session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return User;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByemail(String email) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> findByemail = null;
		try {
			String sql = " SELECT email,id FROM user WHERE user.email = :email ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("email",email);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			findByemail = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return findByemail;
	}
	
	@Override
	public List<Map<String, Object>> findById3(String ur) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> user = null;
		try {
			String sql = " SELECT role_id FROM user WHERE user.id = :ur ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("ur",ur);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			user = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}
	

	@Override
	public void update(User User) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		session.update(User);
		session.flush();
		// session.close();
	}

	@Override
	public void delete(User User) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(User);
		session.flush();
		// session.close();
	}

	@Override
	public List<User> findBySelect(String usertoappr) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> selectuser = null;
		try {
			String approver = "";
			if (usertoappr != null) {
				approver = "   WHERE user.id =  :usertoappr";
			}

			String sql = " SELECT  manager_id  FROM user "+approver+"";
					

			// SELECT MIN(user.id), user.manager_id, user.department_id,
			// user.name FROM user
			// WHERE user.id = (SELECT user.id FROM user WHERE user.id =
			// 'wishida.p' )

			// SELECT user.id, user.manager_id, user.department_id, user.name
			// FROM user WHERE user.id =
			// ( SELECT user.id FROM user WHERE user.id = 'wishida.p' )

			// SELECT Student_Fname, Student_Lname
			// FROM Student
			// WHERE Age= manager
			// (SELECT Age manager
			// FROM Student
			// WHERE Student_Fname='เยาวภา');

			// SELECT user.id, user.manager_id FROM user WHERE user.id
			// ='wishida.p'

			SQLQuery query = session.createSQLQuery(sql);
			if (usertoappr != null) {
				query.setParameter("usertoappr", usertoappr);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			selectuser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectuser;
	}

	@Override
	public List<Map<String, Object>> findByApprove(String usertoappr) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> selectuser = null;
		try {
			String approver = "";
			if (usertoappr != null) {
				approver = "   WHERE user.id =  :usertoappr";
			}

			String sql = " SELECT MIN(user.id), user.manager_id, user.department_id, user.name  FROM user " + approver;

			SQLQuery query = session.createSQLQuery(sql);
			if (usertoappr != null) {
				query.setParameter("usertoappr", usertoappr);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			selectuser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectuser;
	}

	@Override
	public List<Map<String, Object>> allName() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> user = null;
		try {
			String sql = "SELECT id,department_id,CONCAT(department_id,' - ',id) AS roleuser,name FROM user "
					+ " ORDER BY enable DESC, department_id ASC ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			user = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Map<String, Object>> sequense() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> user = null;
		try {
			String sql = " SELECT id,enable, CONCAT(department_id,' - ',id) AS roleuser,  department_id, manager_id, name "
					+ " FROM user  " + " ORDER BY enable DESC, department_id ASC   ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			user = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Map<String, Object>> Query_Userlist() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> user = null;
		try {
			String sql = "SELECT id,name,flag_search,role_id,department_id,email,enable ,CONCAT(id), position_id,start_date FROM user WHERE flag_search = 1 ORDER BY id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			user = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Map<String, Object>> findChangeLeader(String approverchange) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> selectuser = null;
		try {
			String approver = "";
			if (approverchange != null) {
				approver = "   WHERE user.manager_id =  :approverchange";
			}

			String sql = " SELECT MIN(user.manager_id), user.manager_id, user.department_id, user.name  FROM user "
					+ approver;

			SQLQuery query = session.createSQLQuery(sql);
			if (approverchange != null) {
				query.setParameter("approverchange", approverchange);
			}
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			selectuser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectuser;
	}
	
	@Override
	public List<Map<String, Object>> findById2(String id) throws Exception{
		Session session = this.sessionFactory.getCurrentSession();
		List id1 = null;
		try {
			String sql = " SELECT * FROM user WHERE user.id = :id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id",id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			id1 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return id1;
		
		
	}

	@Override
	public List<Map<String, Object>> positionuser(String currentUserlist) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> positionuser = null;
		try {

			String sql = " SELECT department_id "
						+ " FROM user " 
						+ " WHERE id = :currentUserlist "
						+ " LIMIT 1 ";

			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("currentUserlist", currentUserlist);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			positionuser = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return positionuser;
	}
	@Override
	public int[] count_user(){
		Session session = this.sessionFactory.getCurrentSession();
		int[] count = new int[5];
		int count_it = 0;
		int count_intern = 0;
		int count_hr = 0;
		int count_admin = 0;
		int count_total = 0;
		try {
			String sql = " SELECT COUNT(id) "
						+ " FROM user " 
						+"where department_id = 'IT'";
			String sql2 = " SELECT COUNT(id) "
					+ " FROM user " 
					+"where department_id = 'IN'";
			
			String sql3 = " SELECT COUNT(id) "
					+ " FROM user " 
					+"where department_id = 'HR'";
			
			String sql4 = " SELECT COUNT(id) "
					+ " FROM user " 
					+"where department_id = 'AD'";
			String sql5 = " SELECT COUNT(id) "
					+ " FROM user " ;
					
			SQLQuery query = session.createSQLQuery(sql);
			SQLQuery query2 = session.createSQLQuery(sql2);
			SQLQuery query3 = session.createSQLQuery(sql3);
			SQLQuery query4 = session.createSQLQuery(sql4);
			SQLQuery query5 = session.createSQLQuery(sql5);
			
			count_it = ((Number)query.uniqueResult()).intValue();
			count_intern = ((Number)query2.uniqueResult()).intValue();
			count_hr = ((Number)query3.uniqueResult()).intValue();
			count_admin = ((Number)query4.uniqueResult()).intValue();
			count_total = ((Number)query5.uniqueResult()).intValue();
	

			
			count[0] = count_it;
			count[1] = count_intern;
			count[2] = count_hr;
			count[3] = count_admin;
			count[4] = count_total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		  
	} 
	@Override
	public User findByFbId(String fbId) throws Exception {
		User result = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("facebookid", fbId));
		List results = cr.list();
		if(!results.isEmpty()) {
			result = (User) results.get(0);
			return result;
		}
		else {
			return null;
		}

	}

@Override
public User findbyLineId(String lineId) throws Exception{
	User result = null;
	Session session = this.sessionFactory.getCurrentSession();
	Criteria cr = session.createCriteria(User.class);
	cr.add(Restrictions.eq("line_id", lineId));
	List results = cr.list();
	if(!results.isEmpty()) {
		result = (User) results.get(0);
		return result;
	}
	else {
		return null;
	}

}

@Override
public void linkLine(String userId, String lineId) {
	Session session = this.sessionFactory.getCurrentSession();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		user.setLine_id(lineId);
		session.update(user);
		tx.commit();
		session.flush();
	}catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
}
@Override
public void linkFacebook(String userId, String facebookid) {
	Session session = this.sessionFactory.getCurrentSession();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		user.setFacebookid(facebookid);
		session.update(user);
		tx.commit();
		session.flush();
	}catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
}

@Override
public String userListJSON() {
	Session session = this.sessionFactory.getCurrentSession();
	String result = null;
	List<Map<String,String>> list = null;
	try {
		String hql = "SELECT new map(u.id as id, u.name as name, u.enable as enable, u.roleId as role, u.departmentId as department, u.positionId as position, u.managerId as manager) FROM User u";
		list = session.createQuery(hql).list();
		result = new Gson().toJson(list);
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return result;
}
//reset lastyear quota
public String resetLastyearQuota() {
	
	Session session = this.sessionFactory.getCurrentSession();
	
	try {
		String sql = " UPDATE user SET leave_quota_lastyear = 0 WHERE Month(sysdate()) BETWEEN '4' and '12' ";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return "1";
}
//Count User Enable and Disable
public List<Map<String, Object>> UserCountEnable() {

	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> UserActive = null;

	try {
		String sql = "SELECT COUNT(enable) as total ,name,id,department_id FROM user WHERE enable = 1";
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		UserActive = query.list();
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return UserActive;
}
// USER ACTIVE / IN
public List<Map<String, Object>> UserEnable(String enable) {

	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> UserActive = null;

	try {
		String sql = "SELECT name,id,department_id FROM user WHERE enable =" + enable + "" ;
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		UserActive = query.list();
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return UserActive;
}

public List<Map<String, Object>> UserDisable() {

	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> UserInactive = null;

	try {
		String sql = "SELECT COUNT(enable) as total ,name,id,department_id FROM user WHERE enable = 0";

		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		UserInactive = query.list();
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return UserInactive;
}

@Override
public User findByPhoneNum(String phone_num) throws Exception {
	User result = null;
	Session session = this.sessionFactory.getCurrentSession();
	Criteria cr = session.createCriteria(User.class);
	cr.add(Restrictions.eq("phone_num", phone_num));
	List results = cr.list();
	if(!results.isEmpty()) {
		result = (User) results.get(0);
		return result;
	}
	else {
		return null;
	}

}

public List<Map<String, Object>> findByWhereInId( String online_user) {

	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> userOnlineList = null;

	try {
		String sql = "SELECT * FROM `user` WHERE id IN ("+online_user+") ORDER BY `id` ASC";

		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		userOnlineList = query.list();
	} catch (HibernateException e) {
		e.printStackTrace();
	}
	return userOnlineList;
}

	/* test */
@Override
public List<Map<String, Object>> findRoleNameById(String id){
Session session = this.sessionFactory.getCurrentSession();
List<Map<String, Object>> test_holiday = null;
try {

String sql = "SELECT user.id, user.path, role.name FROM user  INNER JOIN role ON user.role_id=role.id  WHERE user.id IN ("+id+") ORDER BY `id` ASC";

SQLQuery query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
test_holiday = query.list();
} catch (Exception e) {
e.printStackTrace();
}
return test_holiday;
}


@Override
public List<Map<String, Object>> test_birthdaysummary() throws Exception {
Session session = this.sessionFactory.getCurrentSession();
List<Map<String, Object>> test_birthdaysummary = null;
String sql;
try {

//String sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='yanikar.t' AND YEAR(time_create)= '2017' GROUP BY (time_create) ASC";
		
			//sql = "SELECT time_create,status_id,user_id,DAY(time_create)AS DAY,MONTH(time_create) AS MONTH, YEAR(time_create) AS YEAR FROM `expense_group` WHERE user_id='' AND YEAR(time_create)= '' GROUP BY (time_create) ASC";
			sql = "SELECT enable,birth_date,id,DAY(birth_date)AS DAY,MONTH(birth_date) AS MONTH, YEAR(birth_date) AS YEAR FROM user WHERE enable = '1' AND birth_date IS NOT NULL ORDER BY MONTH(birth_date) ASC,DAY(birth_date) ASC";
SQLQuery query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
test_birthdaysummary = query.list();
} catch (Exception e) {
e.printStackTrace();
}
return test_birthdaysummary;
}
public List<Map<String, Object>> Query_Userlist2() throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> user = null;
	try {
		String sql = "SELECT id,name,flag_search,role_id,department_id,email,enable, position_id,start_date,user_create,MAX(work_hours.time_create)AS last_chackin, gender, CASE WHEN gender= 'M' THEN '1'WHEN gender = 'F' THEN '2'ELSE 'Null'END AS gendertrue FROM user,work_hours WHERE user.id=work_hours.user_create AND user.flag_search = 1 GROUP BY user.id";
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		user = query.list();
			/* System.out.println(user); */
	} catch (Exception e) {
		e.printStackTrace();
	}
	return user;
}

public List<Map<String, Object>> getGender(String[] setgender) throws Exception {
	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> user = null;

	try {
		String sql = "SELECT id, name FROM `user` WHERE id = '"+setgender[0]+"'";
			/* System.out.println(sql); */
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		user = query.list();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return user;
}

public List<Map<String, Object>> updateGender(String[] setgender) throws Exception {

	Session session = this.sessionFactory.getCurrentSession();
	List<Map<String, Object>> user = null;

			try {
				String sql = " UPDATE user SET gender = '"+setgender[1]+"' WHERE id = '"+setgender[0]+"'";
			/* System.out.println(sql); */
				SQLQuery query = session.createSQLQuery(sql);
				query.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
}

@Override
public List<Map<String, Object>> findRoleById(String id){
Session session = this.sessionFactory.getCurrentSession();
List<Map<String, Object>> role = null;
try {

String sql = "SELECT * FROM `user_role` WHERE user_id = '"+id+"'";

SQLQuery query = session.createSQLQuery(sql);
query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
role = query.list();
} catch (Exception e) {
e.printStackTrace();
}
return role;
}

}