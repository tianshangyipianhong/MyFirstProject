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
	
	//1 �ṩ����
	public static final String URL="jdbc:oracle:thin:@192.168.38.128:1521:orcl";
	//2 �ṩ������
	public static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	
	//3 �ṩ�˺�����
	public static final String USER="scott";
	public static final String PWD="tiger";
	
	
	/**********************	ȫ������****************************/
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	
	/***
	 * ��ȡ���ݿ�����
	 */
	 private void getConnect(){
		//����������
		try {
			Class.forName(DRIVER);
			//��������
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
		
		//1 �������
		pstmt=conn.prepareStatement(sql);
		
		//2 ���ò���
		if(params!=null){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(i+1, params.get(i));
			}
		}
		
		//3 ִ�����
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
		
		//1 �������
		pstmt=conn.prepareStatement(sql);
		
		//2 ���ò���
		if(params!=null){
			for(int i=0;i<params.size();i++){
				pstmt.setObject(i+1, params.get(i));
			}
		}
		 rs=pstmt.executeQuery();
		//3 ִ�����
		return rs;
	}
	
	
	
	/***
	 * ���ض���ļ���list
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
		//1 ��ѯ
		ResultSet rs=executeQuery(sql, params);
		//1.5��ʼ�������б�
		List<T> list=new ArrayList<T>();
		
		//��ȡT���౾������
		Class<?> C =t.getClass();
		
		//2 �����������װ����
		while(rs.next()){
			//��ʼ��һ��ģ�Ͷ���
			@SuppressWarnings("unchecked")
			T obj=(T) C.newInstance();
			//������obj�����ֵ
			//1>��ȡ��ǰobj�����ж��ٸ����԰�
			Field[] fields= C.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				Field f=fields[i];
				//��ȡ��ǰ��������
				String fname=f.getName();
				//��ȡ��ǰ�����Ե�������
				String type=f.getType().getSimpleName().toLowerCase();
				//��ȡ���еķ���
				Method[] method=C.getDeclaredMethods();
				///�ҵ�ר������set��ǰField�ķ���
				for(int j=0;j<method.length;j++){
					Method m=method[j];
//					System.out.println(m.getName()+"==="+"set"+fname+"==="+m.getName().equalsIgnoreCase("set"+fname));
					if(m.getName().equalsIgnoreCase("set"+fname)){
						
						//�жϵ�ǰ���Ƿ���ڣ����ڵĻ����ٽ�����һ��
						if(!columExist(fname,rs))continue;
						Object value=null;
						//��ȡ��ǰ���Զ�Ӧֵ
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
						//����ܽ������˵���ҶԵ�ǰf�����set������
						m.invoke(obj, value);
						break;
					}
				}
			}
			
			//�������������
			list.add(obj);
		}
		
		return list;
		
	}
	
	
	
	/**
	 * �ж����Ƿ����
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
	 * �ر�����
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
		 * �ر�����
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
