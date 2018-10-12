package com.jredu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class DeleteClicks
 */
@WebServlet("/DeleteClicks")
public class DeleteClicks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClicks() {
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
		String uuid=request.getParameter("uuid");
		boolean issuccess=service.deleteclicks(uuid);
		if (issuccess) {
			response.sendRedirect(request.getContextPath()+"/showclick");
		}else {
			System.out.println("É¾³ýÊ§°Ü");
		}
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx =new
				ClassPathXmlApplicationContext("applicationContext-annotation.xml");
			service=(UserService)ctx.getBean(UserService.class);
	}
	
}
