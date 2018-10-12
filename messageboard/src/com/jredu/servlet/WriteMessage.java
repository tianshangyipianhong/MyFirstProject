package com.jredu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jredu.dao.UUIDUtils;
import com.jredu.model.UserMessage;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class WriteMessage
 */
@WebServlet("/WriteMessage")
public class WriteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteMessage() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text");
		response.setCharacterEncoding("utf-8");
		String usexy = (String) request.getSession().getAttribute("sexy");
		String uname =(String) request.getSession().getAttribute("name");
		String ucontent = request.getParameter("content");
		String headimg = (String) request.getSession().getAttribute("headImg");
		UserMessage user = new UserMessage();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat();
		String date1 = sf.format(date);
		user.setUmd(UUIDUtils.getId());
		user.setUname(uname);
		user.setUcontent(ucontent);
		user.setUsexy(usexy);
		user.setUclick(0);
		user.setUpubdate(date1);
		user.setUheadimg(headimg);
		ApplicationContext ctx= 
				new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		UserService service=(UserService)ctx.getBean(UserService.class);
		service.insertMessage(user);
		request.getSession().setAttribute("user", user);
		System.out.println(user.getUcontent());
		request.getRequestDispatcher("/ShowMessage").forward(request, response);
	}
}
