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
import com.litbooks.orderB.vo.OrderB;

/**
 * Servlet implementation class AdminOrderUpdateServlet
 */
@WebServlet(name = "AdminOrderUpdate", urlPatterns = { "/adminOrderUpdate.do" })
public class AdminOrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		OrderBService service = new OrderBService();
		ArrayList<OrderB> list = service.selectAdminAllList();
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("m");
		
		if(m == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "접근 제한");
			request.setAttribute("msg", "비회원은 접근 불가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			if(m.getMemberLevel() == 1) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/adminOrderUpdate.jsp");
				request.setAttribute("list", list);
				view.forward(request, response);				
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "접근 제한");
				request.setAttribute("msg", "결제내역 변경은 관리자만 가능합니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "/index.jsp");
				view.forward(request, response);
			}
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
