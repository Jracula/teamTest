package com.litbooks.orderB.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.basket.vo.MultiData;
import com.litbooks.book.service.BookService;
import com.litbooks.book.vo.Book;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "Cart", urlPatterns = { "/cart.do" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		int memberNo = 2;
		
		BookService service = new BookService();
		
		//int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		
		// 책 테이블 전체 조회
		//ArrayList<Book> list = service.selectAllBook(memberNo);
		MultiData mda = service.selectAllBook(memberNo);
		
		
		if(mda == null) {
			request.setAttribute("title", "카트 조회불가");
			request.setAttribute("msg", "에러");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/cart.jsp");
			request.setAttribute("baskList", mda.getBasketList());
			request.setAttribute("bookList", mda.getBookList());
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
