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
 * Servlet implementation class ClickServlet
 */
@WebServlet("/click")
public class ClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickServlet() {
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
		//获取ajax传回来的值
		int  newgood = Integer.valueOf(request.getParameter("newgood"));
		String uid = request.getParameter("umd");
		String username = (String) request.getSession().getAttribute("name");
		service.addMToM(uid, username);
		boolean isSuccess = service.click(uid,newgood);
		if (isSuccess) {
			request.getRequestDispatcher("/ShowMessage").forward(request, response);
			PrintWriter out=response.getWriter();
			out.write(isSuccess?"1":"0");
			out.flush();
			out.close();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service=(UserService)ctx.getBean(UserService.class);
	}
	
}
