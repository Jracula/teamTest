package com.litbooks.orderB.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.orderB.service.OrderBService;

/**
 * Servlet implementation class AdminChangePayServlet
 */
@WebServlet(name = "AdminChangePay", urlPatterns = { "/adminChangePay.do" })
public class AdminChangePayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangePayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String orderPay = request.getParameter("orderPay");
		System.out.println(memberNo);
		System.out.println(orderPay);
		
		OrderBService service = new OrderBService();
		int result = service.changePay(memberNo, orderPay);
		
		if(result > 0) {
			response.sendRedirect("/adminOrderUpdate.do"); // 관리자페이지 서블릿 주소
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "결제방식 변경 실패");
			request.setAttribute("msg", "결제방식 변경 중 문제가 발생했습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/adminOrderUpdate.do"); // 관리자페이지 서블릿 주소
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
