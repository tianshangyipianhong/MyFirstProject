package com.jredu.dao;

import java.sql.SQLException;
import java.util.List;

import com.jredu.model.ClickMessage;
import com.jredu.model.ClickUsers;
import com.jredu.model.User;
import com.jredu.model.UserMessage;


public interface UserDao {
	public int registerUpdate(String sql,List<Object> params) throws SQLException;
	public List<User> loginCheck(String sql,List<Object> params) throws  Exception;
	public int insertMessage(String sql,List<Object> params) throws SQLException;
	public List<UserMessage> selectMessage(String sql,List<Object> params) throws Exception;
	public int deleteMessage(String sql,List<Object> params) throws SQLException;
	public List<User> selectSexy(String sql,List<Object> params) throws Exception; 
	public int click(String sql,List<Object> params) throws Exception;
	public int addMToM(String sql,List<Object> params) throws Exception;
	public int updateImg(String sql,List<Object> params)throws Exception;
	public List<User> showheadImg(String sql,List<Object> params) throws Exception;
	public int addclickMessage(String sql,List<Object> params)throws Exception;
	public List<ClickMessage> selectALLMessages(String sql,List<Object>params) throws Exception;
	public int deleteclicks(String sql,List<Object>params) throws Exception;
	public List<UserMessage> selectMymessage(String sql,List<Object> params) throws Exception;
	public int myclickusers(String sql,List<Object> params)throws Exception;
	public List<ClickUsers> seletUsers(String sql,List<Object> param)throws Exception;
	public List<UserMessage> selectAllloginmessages(String sql) throws Exception;
	
	public List<UserMessage> getAllUsersWithPageNumAndPageSize(String sql,List<Object> params)throws Exception;
}
