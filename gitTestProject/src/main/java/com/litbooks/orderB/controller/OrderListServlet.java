package com.litbooks.orderB.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.orderB.service.OrderBService;
import com.litbooks.orderB.vo.OrderB;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet(name = "OrderList", urlPatterns = { "/orderList.do" })
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//int bookNo = Integer.getInteger(request.getParameter("bookNo"));
		//int bookPrice = Integer.getInteger(request.getParameter("bookPrice"));
		int memberNo = Integer.getInteger(request.getParameter("memberNo"));
		
		OrderBService service = new OrderBService();
		// 어떤 회원이 주문을 했는지 조회
		//OrderB o = service.selectOneOrder(memberNo);
		ArrayList<OrderB> list = service.selectAllOrder(memberNo);
		
		if(list.isEmpty()) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "결제하지 않은 회원입니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/orderList.jsp");
			//ArrayList<OrderB> list = service.selectAllOrder(memberNo);
			//request.setAttribute("or", o);
			request.setAttribute("list", list);
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
