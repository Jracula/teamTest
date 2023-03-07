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
import com.litbooks.orderB.vo.OrderBPageData;

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
		
		//int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		OrderBService service = new OrderBService();
		OrderBPageData opd = service.selectAllOrder(reqPage);
		
		if(opd == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "결제하지 않은 회원입니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/orderList.jsp");
			request.setAttribute("list", opd.getList());
			request.setAttribute("pageNavi", opd.getPageNavi());
			request.setAttribute("start", opd.getStart());
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
