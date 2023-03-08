package com.litbooks.orderB.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.member.vo.Member;
import com.litbooks.orderB.service.OrderBService;

/**
 * Servlet implementation class OrderPayOneServlet
 */
@WebServlet(name = "OrderPayOne", urlPatterns = { "/orderPayOne.do" })
public class OrderPayOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPayOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("m");
		
		if (m == null) {
			PrintWriter out = response.getWriter();
			out.print(false);
		} else {
			if (m.getMemberLevel() == 2) {
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int bookNo = Integer.parseInt(request.getParameter("bookNo"));
				int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
				String payMethod = request.getParameter("payMethod");
				
				OrderBService service = new OrderBService();
				int result = service.insertPayOne(memberNo, bookNo, bookPrice, payMethod);

				PrintWriter out = response.getWriter();
				out.print(result);
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
