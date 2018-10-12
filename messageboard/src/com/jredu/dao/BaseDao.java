package com.jredu.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BaseDao {
	
	//1 提供链接
	public static final String URL="jdbc:oracle:thin:@192.168.38.128:1521:orcl";
	//2 提供驱动类
	public static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	
	//3 提供账号密码
	public static final String USER="scott";
	public static final String PWD="tiger";
	
	
	/**********************	全局属性****************************/
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	
	/***
	 * 获取数据库连接
	 */
	 private void getConnect(){
		//加载驱动类
		try {
			Class.forName(DRIVER);
			//建立连接
			if(conn==null)conn=DriverManager.getConnection(URL,USER,PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 
	
	/***
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public int executeUpdate(String sql,List<Object> params) throws SQLException{
		getConnect();
		
		//1 创建句柄
		pstmt=conn.prepareStatement(sql);
		
		//2 设置参数
		if(params!=null){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(i+1, params.get(i));
			}
		}
		
		//3 执行语句
		return pstmt.executeUpdate();
	}
	
	
	
	/***
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet executeQuery(String sql,List<Object> params) throws SQLException{
		getConnect();
		
		//1 创建句柄
		pstmt=conn.prepareStatement(sql);
		
		//2 设置参数
		if(params!=null){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(i+1, params.get(i));
			}
		}
		 rs=pstmt.executeQuery();
		//3 执行语句
		return rs;
	}
	
	
	
	/***
	 * 返回对象的集合list
	 * @param sql
	 * @param params
	 * @param t
	 * @return
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public <T> List<T> getModelListByResultSet(String sql,List<Object> params,T t) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//1 查询
		ResultSet rs=executeQuery(sql, params);
		//1.5初始化集合列表
		List<T> list=new ArrayList<T>();
		
		//获取T的类本质类型
		Class<?> C =t.getClass();
		
		//2 遍历结果集封装对象
		while(rs.next()){
			//初始化一个模型对象
			@SuppressWarnings("unchecked")
			T obj=(T) C.newInstance();
			//往对象obj里面存值
			//1>获取当前obj到底有多少个属性啊
			Field[] fields= C.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				Field f=fields[i];
				//获取当前的属性名
				String fname=f.getName();
				//获取当前的属性的类型名
				String type=f.getType().getSimpleName().toLowerCase();
				//获取所有的方法
				Method[] method=C.getDeclaredMethods();
				///找到专门用来set当前Field的方法
				for(int j=0;j<method.length;j++){
					Method m=method[j];
//					System.out.println(m.getName()+"==="+"set"+fname+"==="+m.getName().equalsIgnoreCase("set"+fname));
					if(m.getName().equalsIgnoreCase("set"+fname)){
						
						//判断当前列是否存在，存在的话，再进行下一步
						if(!columExist(fname,rs))continue;
						Object value=null;
						//获取当前属性对应值
						if("string".equals(type)){
							value=rs.getString(fname);
						}else if("int".equals(type)){
							value=rs.getInt(fname);
						}else if("float".equals(type)){
							value=rs.getFloat(fname);
						}else if("double".equals(type)){
							value=rs.getFloat(fname);
						}else if("boolean".equals(type)){
							value=rs.getBoolean(fname);
						}else if("long".equals(type)){
							value=rs.getLong(fname);
						}else if("date".equals(type)){
							value=rs.getDate(fname);
						}else if("timestamp".equals(type)){
							value=rs.getTimestamp(fname);
						}else if("integer".equals(type)){
							value=rs.getInt(fname);
						}
						//如果能进来这里，说明找对当前f对象的set方法了
						m.invoke(obj, value);
						break;
					}
				}
			}
			
			//将对象加入数组
			list.add(obj);
		}
		
		return list;
		
	}
	
	
	
	/**
	 * 判断列是否存在
	 */
	public boolean columExist(String columName,ResultSet rs){
		
		boolean flag=false;
		
		try{
			if(rs.findColumn(columName)>0){
				flag=true;
			} 
		}catch(Exception e){
			flag=false;
		}
		return flag;
	}
	
 
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 关闭所有
	 */
	public void closeAll(){
		try {
			
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
			
			pstmt=null;
			conn=null;
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		/**
		 * 关闭所有
		 */
	public void closeResult(){
		try {
			rs.close();
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 
	 * @return
	 * 1 2 3 4 5 6 7
	 * 20170302
	 */
	public  int  getCurrentIndex(String seqMsg){
		
		String sql="select "+seqMsg+".nextval IDS from dual";
		
	    try {
			ResultSet rs=executeQuery(sql, null);
			if(rs.next()){
				return rs.getInt("IDS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return 0;
	}
 
	
}
