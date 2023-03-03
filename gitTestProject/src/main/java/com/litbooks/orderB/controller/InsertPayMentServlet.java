package com.litbooks.orderB.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertPayMentServlet
 */
@WebServlet(name = "InsertPayMent", urlPatterns = { "/insertPayMent.do" })
public class InsertPayMentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPayMentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		String orderRegDate = request.getParameter("orderRegDate");
		String bookTitle = request.getParameter("bookTitle");
		int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
		String orderPay = request.getParameter("orderPay");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
