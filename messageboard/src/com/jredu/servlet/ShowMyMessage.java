package com.jredu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jredu.model.UserMessage;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class ShowMyMessage
 */
@WebServlet("/showmymessage")
public class ShowMyMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMyMessage() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String name1=new String(name.getBytes("iso-8859-1"),"UTF-8");
		List<UserMessage> list= service.selectmymessage(name1);
		request.setAttribute("myMessages", list);
		request.getRequestDispatcher("mymessage.jsp").forward(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service=(UserService)ctx.getBean(UserService.class);
	}
	
}
