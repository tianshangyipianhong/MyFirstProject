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

import com.jredu.service1.UserService;

/**
 * Servlet implementation class deleteMessage
 */
@WebServlet("/deleteMessage")
public class deleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteMessage() {
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
		String uuid =request.getParameter("uuid");
		boolean issuccess= service.deleteMessage(uuid);
		if (issuccess) {
			request.getRequestDispatcher("/ShowMessage").forward(request, response);
			
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
	 service=(UserService)ctx.getBean(UserService.class);
	}
	
}
