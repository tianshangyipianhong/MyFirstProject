package com.jredu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jredu.model.User;
import com.jredu.model.UserMessage;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class ShowMessage
 */
@WebServlet("/ShowMessage")
public class ShowMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMessage() {
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
		String name = (String) request.getSession().getAttribute("name");
		HttpSession session = request.getSession();	
			List<UserMessage> list = service.selectMessage(name);
			List<User> list1=service.showheadImg(name);
			session.setAttribute("messages", list);
			session.setAttribute("headImg",list1.get(0).getHeadimg());
			request.getRequestDispatcher("message.jsp").forward(request, response);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
	super.init(config);
	ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
	 service = (UserService)ctx.getBean(UserService.class);
	}

}
