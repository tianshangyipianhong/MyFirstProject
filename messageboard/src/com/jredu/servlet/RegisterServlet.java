package com.jredu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.jredu.service1.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String sexy =request.getParameter("sexy");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String idCard = request.getParameter("idcard");
		User user = new User();
		user.setUsername(name);
		user.setPassword(pwd);
		user.setSexy(sexy);
		user.setIdcard(idCard);
		user.setHeadimg("0");
		boolean success = service.update(user);
		if (success) {
			response.sendRedirect("successRegister.jsp");
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service=ctx.getBean(UserService.class);
	}

}
