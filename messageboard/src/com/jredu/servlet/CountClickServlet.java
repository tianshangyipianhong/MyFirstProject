package com.jredu.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jredu.dao.UUIDUtils;
import com.jredu.model.ClickMessage;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class CountClickServlet
 */
@WebServlet("/CountClickServlet")
public class CountClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountClickServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String usename = (String) request.getSession().getAttribute("name");
		String id = request.getParameter("umd");
		String name=request.getParameter("name");
		String content=request.getParameter("ucontent");
		String time=request.getParameter("time");
		//解决ajax传值乱码的问题
		String content1=new String(content.getBytes("iso-8859-1"),"UTF-8");
		String name1=new String(name.getBytes("iso-8859-1"),"UTF-8");
		String time1=new String(time.getBytes("iso-8859-1"),"UTF-8");
		ClickMessage message = new ClickMessage();
		message.setId(id);
		message.setName(name1);
		message.setContent(content1);
		message.setTime(time1);
		message.setUsename(usename);
		message.setUuid(UUIDUtils.getId());
		service.addclickMessage(message);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service=(UserService)ctx.getBean(UserService.class);
	}

}
