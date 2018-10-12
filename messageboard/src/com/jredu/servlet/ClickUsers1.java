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

import com.jredu.model.ClickUsers;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class ClickUsers
 */
@WebServlet("/ClickUsers")
public class ClickUsers1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private UserService service; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickUsers1() {
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
		String id = request.getParameter("id");
		String user = request.getParameter("users");
		String users=new String(user.getBytes("iso-8859-1"),"UTF-8");
		String action = request.getParameter("action");
		if ("insert".equals(action)) {
			service.insertusers(id, users);
			}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		service=(UserService)ctx.getBean(UserService.class);
	}

}
