package com.litbooks.basket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.basket.Service.BasketService;

/**
 * Servlet implementation class InsertOneIntoCartServlet
 */
@WebServlet(name = "InsertOneIntoCart", urlPatterns = { "/insertOneIntoCart.do" })
public class InsertOneIntoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertOneIntoCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bookNo = Integer.parseInt(request.getParameter("input1"));
		int memberNo = Integer.parseInt(request.getParameter("input2"));
		BasketService service = new BasketService();
		String message = service.selectBasket(bookNo, memberNo);
		if(message.equals("noError")) {
			int result = service.insertOneBasket(bookNo, memberNo);
			if(result>0) {
				message = "장바구니에 넣었습니다.";
			}else {
				message = "알 수 없는 이유로 장바구니에 넣지 못했습니다.";
			}
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
