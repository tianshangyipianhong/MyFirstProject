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
		//处理上传内容
		//创建硬盘文件缓冲工厂设置缓冲区
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//限制文件大小..类型等并且和磁盘缓冲工厂关联
		ServletFileUpload serf= new ServletFileUpload(factory);
		//预定大小为10M
		serf.setFileSizeMax(1024*1024*10);
		//色绘制请求头的编码
		serf.setHeaderEncoding("utf-8");
		//解析传递过来的值的类型
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list=serf.parseRequest(request);
			for (FileItem fileItem : list) {
				//item里面封装的可能是普通的字符串,也可能是文件
			if(fileItem.isFormField()){
				//判断是普通的表单元素
				//1:获取该表单元素的属性名
				String name = fileItem.getFieldName();
				//2:获取该表单元素的值
				String value = fileItem.getString("utf-8");
			}else{
				//文件元素
				//1:获取上传的文件的名字
				String name=fileItem.getName();
				//2:重新定义文件名   system.currentTimeMills()获取当前时间的毫秒数
				String newName=System.currentTimeMillis()+name;
				//3:将缓存的文件持久化到本地
				File file=new File("G://img/"+newName);
				
				fileItem.write(file);
					//清楚缓冲文件
				fileItem.delete();
				//执行service方法将图片路径在表内修改
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
