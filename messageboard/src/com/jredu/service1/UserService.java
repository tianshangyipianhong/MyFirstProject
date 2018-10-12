package com.jredu.service1;

import java.util.List;

import com.jredu.model.ClickMessage;
import com.jredu.model.ClickUsers;
import com.jredu.model.User;
import com.jredu.model.UserMessage;

public interface UserService {
	public boolean update(User user);
	
	public boolean login(String username,String password);
	
	public boolean insertMessage(UserMessage user);
	
	public  List<UserMessage> selectMessage(String name);
	public boolean deleteMessage(String id);
	public List<User> selectSexy(String name);
	public boolean click(String uid,int newgood);
	public boolean addMToM(String umd,String username); 
	public boolean updateImg(String name,String headImg);
	public List<User> showheadImg(String username);
	public boolean  addclickMessage(ClickMessage message);
	public List<ClickMessage> selectAllMessages(String name);
	public boolean deleteclicks(String uuid);
	public List<UserMessage> selectmymessage(String name);
	public boolean insertusers(String id,String name);
	public List<ClickUsers> selectUsers(String uuid);
	public List<UserMessage> countloginmessages();
	
	public  List<UserMessage> getAllUsersWithPageNumAndPageSize(int pageNum,int pageSize);
}
