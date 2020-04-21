package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import com.cubesofttech.model.User;

public interface UserDAO {

	public void save(User user) throws Exception;

	public List<User> findAll() throws Exception;

	public User findById(String id) throws Exception;
	
	/*public static User findByRoleId(String roleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/
	public String resetLastyearQuota();
	
	public List<Map<String, Object>> findByemail(String id) throws Exception;
	
	public List<Map<String, Object>> findById2(String id) throws Exception;

	public void update(User user) throws Exception;

	public void delete(User user) throws Exception;

	public List<User> findBySelect(String usertoappr) throws Exception;

	public List<Map<String, Object>> allName() throws Exception;

	public List<Map<String, Object>> findByApprove(String usertoappr) throws Exception;

	public List<Map<String, Object>> sequense() throws Exception;

	public List<Map<String, Object>> Query_Userlist() throws Exception;

	public List<Map<String, Object>> findChangeLeader(String approverchange) throws Exception;

	public List<Map<String, Object>> findById3(String ur) throws Exception;

	public List<Map<String, Object>> positionuser(String logonUser) throws Exception;

	public int[] count_user(); //PieCharts 

	public User findByFbId(String id)  throws Exception;

	public User findbyLineId(String lineId) throws Exception;
	
	public void linkLine(String userId, String lineId);

	public void linkFacebook(String userId, String facebookid);

	public String userListJSON();
	
	public List<Map<String, Object>> UserCountEnable();
	
	public List<Map<String, Object>> UserEnable(String enable);

	public List<Map<String, Object>> UserDisable();
	
	public User findByPhoneNum(String phone_num) throws Exception;

	public List<Map<String, Object>> findByWhereInId( String online_user);

	List<Map<String, Object>> findRoleNameById(String id);
	
	public List<Map<String, Object>> test_birthdaysummary() throws Exception;
	
	public List<Map<String, Object>> Query_Userlist2() throws Exception;
	
	public List<Map<String, Object>> getGender(String[] setgender) throws Exception;
	
	public List<Map<String, Object>> updateGender(String[] setgender) throws Exception;
	
	public List<Map<String, Object>> findRoleById(String id);
}
