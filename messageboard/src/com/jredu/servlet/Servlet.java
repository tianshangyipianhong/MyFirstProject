package com.jredu.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jredu.service1.UserService;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ϴ�����
		//����Ӳ���ļ����幤�����û�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ļ���С..���͵Ȳ��Һʹ��̻��幤������
		ServletFileUpload serf= new ServletFileUpload(factory);
		//Ԥ����СΪ10M
		serf.setFileSizeMax(1024*1024*10);
		//ɫ��������ͷ�ı���
		serf.setHeaderEncoding("utf-8");
		//�������ݹ�����ֵ������
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list=serf.parseRequest(request);
			for (FileItem fileItem : list) {
				//item�����װ�Ŀ�������ͨ���ַ���,Ҳ�������ļ�
			if(fileItem.isFormField()){
				//�ж�����ͨ�ı�Ԫ��
				//1:��ȡ�ñ�Ԫ�ص�������
				String name = fileItem.getFieldName();
				//2:��ȡ�ñ�Ԫ�ص�ֵ
				String value = fileItem.getString("utf-8");
			}else{
				//�ļ�Ԫ��
				//1:��ȡ�ϴ����ļ�������
				String name=fileItem.getName();
				//2:���¶����ļ���   system.currentTimeMills()��ȡ��ǰʱ��ĺ�����
				String newName=System.currentTimeMillis()+name;
				//3:��������ļ��־û�������
				File file=new File("G://img/"+newName);
				
				fileItem.write(file);
					//��������ļ�
				fileItem.delete();
				//ִ��service������ͼƬ·���ڱ����޸�
				String  username = (String) request.getSession().getAttribute("name");
				boolean isSuccess = service.updateImg(username, newName);
				if (isSuccess) {
					request.getRequestDispatcher("/ShowMessage").forward(request, response);
				}
			}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
			service=(UserService)ctx.getBean(UserService.class);
	}
	
}
