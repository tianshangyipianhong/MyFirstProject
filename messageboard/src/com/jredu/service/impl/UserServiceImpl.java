package com.jredu.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jredu.dao.UserDao;
import com.jredu.model.ClickMessage;
import com.jredu.model.ClickUsers;
import com.jredu.model.User;
import com.jredu.model.UserMessage;
import com.jredu.service1.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean update(User user) {
	String sql = "insert into register values(?,?,?,?,?)";
	List<Object> params = new ArrayList<Object>();
	params.add(user.getUsername());
	params.add(user.getPassword());
	params.add(user.getIdcard());
	params.add(user.getSexy());
	params.add(user.getHeadimg());
	int count =0;
	try {
		count=dao.registerUpdate(sql, params);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count>0;
	}

	@Override
	public boolean login(String username, String password) {
		String sql = "select * from register where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(username!=null && !"".equals(username)){
			sql+=" and username= ?";
			params.add(username);
		}
		
		//Æ´½ÓpassWord
		if(password!=null && !"".equals(password)){
			sql+=" and password= ?";
			params.add(password);
			
		}
		List<User> list =null;
		try {
			list =dao.loginCheck(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.size()>0;
	}

	@Override
	public boolean insertMessage(UserMessage user) {
		String sql="insert into loginMessage values(?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUmd());
		params.add(user.getUname());
		params.add(user.getUcontent());
		params.add(user.getUsexy());
		params.add(user.getUclick());
		params.add(user.getUpubdate());
		params.add(user.getUheadimg());
		int count=0;
		try {
			count= dao.insertMessage(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0;
	}

	@Override
	public List<UserMessage> selectMessage(String name) {
		String sql="select m.*,(select count(*) from t_good g where"
				+ " g.tname=? and g.tid=m.umd) canaddgood "
				+ "from loginMessage m order by m.upubdate";
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		List<UserMessage> list=null;
		try {
			list = dao.selectMessage(sql,params);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteMessage(String id) {
		String sql="delete from loginMessage where umd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		int count=0;
		try {
			count = dao.deleteMessage(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0;
	}

	@Override
	public List<User> selectSexy(String name) {
		String sql="select *from register where username=?";
		List<Object> params =new ArrayList<Object>();
		params.add(name);
		try {
			return dao.selectSexy(sql,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean click(String uid,int newgood) {
		String sql = "update loginMessage set uclick=?  where umd=?";
		List<Object> params = new ArrayList<Object>();
		params.add(newgood);
		params.add(uid);
		int count=0;
		try {
			count= dao.click(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count>0;
	}

	@Override
	public boolean addMToM(String umd, String username) {
		String sql= "insert into t_good values(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(umd);
		params.add(username);
		try {
			 int count=dao.addMToM(sql, params);
			 return count>0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateImg(String name, String headImg) {
		String sql= "update register set headimg=? where username=?";
		List<Object> params=new ArrayList<Object>();
		params.add(headImg);
		params.add(name);
		try {
			int count=dao.updateImg(sql, params);
			return count>0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> showheadImg(String username) {
		String sql="select * from register where username=?";
		List<Object> params = new ArrayList<Object>();
		params.add(username);				
		List<User> list = null;
		try {
			list=dao.showheadImg(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addclickMessage(ClickMessage message) {
		String sql= "insert into clickmessage values(?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(message.getName());
		params.add(message.getContent());
		params.add(message.getTime());
		params.add(message.getId());
		params.add(message.getUsename());
		params.add(message.getUuid());
		int count=0;
		try {
			 count=dao.addclickMessage(sql, params);
			 return count>0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ClickMessage> selectAllMessages(String name) {
	String sql= "select * from  clickmessage where usename=?";
		List<ClickMessage> list =null;
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		try {
			list =dao.selectALLMessages(sql,params);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteclicks(String uuid) {
		String sql="delete from clickmessage where uuid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uuid);
		try {
			int a=	dao.deleteclicks(sql, params);
			return a>0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserMessage> selectmymessage(String name) {
		String sql= "select * from loginmessage where uname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		List<UserMessage> list=null;
		try {
			list=dao.selectMymessage(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertusers(String id, String name) {
		String sql="insert into myclickuser values(?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		params.add(name);
		int a=0;
		try {
			a=dao.myclickusers(sql, params);
			return a>0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ClickUsers> selectUsers(String uuid) {
		String sql="select * from myclickuser where id=?";
		List<Object> param = new ArrayList<Object>();
		param.add(uuid);
		List<ClickUsers> list=null;
		try {
			list=dao.seletUsers(sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<UserMessage> countloginmessages() {
		String sql="select * from loginMessage";
		List<UserMessage> list = null;
		try {
			list= dao.selectAllloginmessages(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<UserMessage> getAllUsersWithPageNumAndPageSize(int pageNum, int pageSize) {
		String sql="select * from(select m.*,rownum num from loginmessage m)A where A.num>=? and A.num<?";
		List<Object> params = new ArrayList<Object>();
		int from=(pageNum-1)*pageSize+1;
		int to=from+pageSize;
		params.add(from);
		params.add(to);
		List<UserMessage> list1=null;
		try {
			list1= dao.getAllUsersWithPageNumAndPageSize(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list1;
	}
	
}
