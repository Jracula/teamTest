package com.litbooks.orderB.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.orderB.service.OrderBService;
import com.litbooks.orderB.vo.OrderB;

/**
 * Servlet implementation class OrderPayMentServlet
 */
@WebServlet(name = "OrderPayMent", urlPatterns = { "/orderPayMent.do" })
public class OrderPayMentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPayMentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo")); // null??
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		
		OrderBService service = new OrderBService();
		
		// 가격을 가져오기 위해서 
		OrderB order = service.selectOrderNumber(memberNo);
		
		if(order == null) {
			request.setAttribute("title", "오류");
			request.setAttribute("msg", "에러");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/orderPayMent.do");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/orderPayMent.jsp");
			request.setAttribute("order", order);
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
