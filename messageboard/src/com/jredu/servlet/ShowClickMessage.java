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

import com.jredu.model.ClickMessage;
import com.jredu.service1.UserService;

/**
 * Servlet implementation class ShowClickMessage
 */
@WebServlet("/showclick")
public class ShowClickMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowClickMessage() {
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
			//…Ë÷√±‡¬Î
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String usename=(String) request.getSession().getAttribute("name");
		List<ClickMessage> list=service.selectAllMessages(usename);
		request.getSession().setAttribute("clicks",list);
		request.getRequestDispatcher("showclickmessage.jsp").forward(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service=(UserService)ctx.getBean(UserService.class);
	}
	
	
}
