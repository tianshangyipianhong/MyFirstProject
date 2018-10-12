package com.jredu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.springframework.stereotype.Repository;

import com.jredu.dao.BaseDao;
import com.jredu.dao.UserDao;
import com.jredu.model.ClickMessage;
import com.jredu.model.ClickUsers;
import com.jredu.model.User;
import com.jredu.model.UserMessage;
@Repository
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public int registerUpdate(String sql,List<Object> params) throws SQLException {
		return executeUpdate(sql, params);
	}


	@Override
	public List<User> loginCheck(String sql,List<Object> params) throws Exception {
		return	getModelListByResultSet(sql, params, new User());
	}


	@Override
	public int insertMessage(String sql, List<Object> params) throws SQLException {
		return executeUpdate(sql, params) ;
	}


	@Override
	public List<UserMessage> selectMessage(String sql,List<Object> params ) throws Exception{
		List<UserMessage> list = getModelListByResultSet(sql, params, new UserMessage());
		return list;
	}


	@Override
	public int deleteMessage(String sql, List<Object> params) throws SQLException {
		return  executeUpdate(sql, params);
	}


	@Override
	public List<User> selectSexy(String sql,List<Object> params) throws Exception{
		return  getModelListByResultSet(sql, params, new User());
	}


	@Override
	public int click(String sql,List<Object> params) throws Exception{
		return  executeUpdate(sql, params);
	}


	@Override
	public int addMToM(String sql, List<Object> params) throws Exception {
		return executeUpdate(sql, params);
	}


	@Override
	public int updateImg(String sql, List<Object> params) throws Exception {
		return executeUpdate(sql, params);
	}


	@Override
	public List<User> showheadImg(String sql, List<Object> params) throws Exception {
		return getModelListByResultSet(sql, params, new User());
	}


	@Override
	public int addclickMessage(String sql, List<Object> params) throws Exception {
		return executeUpdate(sql, params);
	}


	@Override
	public List<ClickMessage> selectALLMessages(String sql,List<Object> params) throws Exception{
		return getModelListByResultSet(sql,params, new ClickMessage());
	}


	@Override
	public int deleteclicks(String sql, List<Object> params) throws Exception {
		return executeUpdate(sql, params);
	}


	@Override
	public List<UserMessage> selectMymessage(String sql, List<Object> params)throws Exception {
		return getModelListByResultSet(sql, params, new UserMessage());
	}


	@Override
	public int myclickusers(String sql, List<Object> params) throws Exception {
		return executeUpdate(sql, params);
	}


	@Override
	public List<ClickUsers> seletUsers(String sql, List<Object> param) throws Exception {
		return getModelListByResultSet(sql, param, new ClickUsers());
	}


	@Override
	public List<UserMessage> selectAllloginmessages(String sql) throws Exception {
		return getModelListByResultSet(sql, null, new UserMessage());
	}


	@Override
	public List<UserMessage> getAllUsersWithPageNumAndPageSize(String sql, List<Object> params) throws Exception {
		return getModelListByResultSet(sql, params, new UserMessage());
	}


}
