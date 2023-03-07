package com.litbooks.orderB.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.orderB.service.OrderBService;

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
		
		//memberNo : memberNo, bookTitle : bookTitle.join("/"), 
		// bookPrice : bookPrice.join("/"), price :price
		
		int memberNo1 = Integer.parseInt(request.getParameter("memberNo1"));
		String bookNo = request.getParameter("bookNo");
		String bookPrice = request.getParameter("bookPrice");
		String payMethod = request.getParameter("payMethod");
		String basketNo = request.getParameter("basketNo");
		int price = Integer.parseInt(request.getParameter("price"));
		
		OrderBService service = new OrderBService();
		int result = service.insertOrder(memberNo1, bookNo, bookPrice, price, payMethod, basketNo);
		
		
		/*
		System.out.println("회원번호 : " + memberNo1);
		System.out.println("책 이름 : " + bookTitle);
		System.out.println("책 가격 : " + bookPrice);
		System.out.println("장바구니 번호 : " + basketNo);
		System.out.println("총가격 : " + price);
		*/
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
