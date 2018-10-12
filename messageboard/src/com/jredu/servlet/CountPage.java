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
 * Servlet implementation class CountPage
 */
@WebServlet("/CountPage")
public class CountPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private UserService service; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountPage() {
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
		//需要显示第几页数据
		String pageNumStr=request.getParameter("pageNum");
		//刚开始首先进入servlet,pageNum必定为空,显示第一页,展示第一页数据
		if(pageNumStr==null||"".equals(pageNumStr)){
			pageNumStr="1";
		}
		int pageNum =Integer.valueOf(pageNumStr);
		//每页显示的数据条数
		int pageCount = 4;
		//获取总条数
		int total= service.countloginmessages().size();
		//获取所有分页后的数据
		List<UserMessage> list=service.getAllUsersWithPageNumAndPageSize(pageNum,pageCount);
		request.setAttribute("list", list);//展示的数据
		request.setAttribute("pageCount", pageCount);//每页条数
		request.setAttribute("pageNum", pageNum);//当前页
		request.setAttribute("total",total );//总条数
		request.getRequestDispatcher("fneye.jsp").forward(request, response);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
		service = (UserService)ctx.getBean(UserService.class);
	}
	
}
