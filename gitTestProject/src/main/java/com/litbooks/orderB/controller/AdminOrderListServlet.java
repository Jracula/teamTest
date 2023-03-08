package com.litbooks.orderB.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.member.vo.Member;
import com.litbooks.orderB.service.OrderBService;
import com.litbooks.orderB.vo.AdminPageData;
import com.litbooks.orderB.vo.OrderB;

/**
 * Servlet implementation class AdminOrderListServlet
 */
@WebServlet(name = "AdminOrderList", urlPatterns = { "/adminOrderList.do" })
public class AdminOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		OrderBService service = new OrderBService();
		AdminPageData apd = service.selectAdminList(reqPage);
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("m");
		
		if(m.getMemberLevel() != 1) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "????");
			request.setAttribute("msg", "관리자만 이용가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
			return;
		}
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/adminOrderList.jsp");
		request.setAttribute("list", apd.getList());
		request.setAttribute("pageNavi", apd.getPageNavi());
		request.setAttribute("start", apd.getStart());
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
