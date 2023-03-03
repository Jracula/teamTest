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
		
		OrderBService service = new OrderBService();
		
		ArrayList<OrderB> list = service.selectAdminList();
		
		/*Member m = (Member) session.getAttribute("m");
		if (m.getMemberLevel() == 1) {
			// 수정가능
		} else {
			// 수정 불가능
		}*/
		
		if(list.isEmpty()) {
			request.setAttribute("title", "????");
			request.setAttribute("msg", "관리자만 이용가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/adminOrderList.jsp");
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
