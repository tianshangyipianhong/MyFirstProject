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
		//��Ҫ��ʾ�ڼ�ҳ����
		String pageNumStr=request.getParameter("pageNum");
		//�տ�ʼ���Ƚ���servlet,pageNum�ض�Ϊ��,��ʾ��һҳ,չʾ��һҳ����
		if(pageNumStr==null||"".equals(pageNumStr)){
			pageNumStr="1";
		}
		int pageNum =Integer.valueOf(pageNumStr);
		//ÿҳ��ʾ����������
		int pageCount = 4;
		//��ȡ������
		int total= service.countloginmessages().size();
		//��ȡ���з�ҳ�������
		List<UserMessage> list=service.getAllUsersWithPageNumAndPageSize(pageNum,pageCount);
		request.setAttribute("list", list);//չʾ������
		request.setAttribute("pageCount", pageCount);//ÿҳ����
		request.setAttribute("pageNum", pageNum);//��ǰҳ
		request.setAttribute("total",total );//������
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
