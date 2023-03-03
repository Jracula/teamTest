package com.litbooks.book.controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.book.service.BookService;
import com.litbooks.book.service.BookService2;
import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.BookView;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet(name = "BookDetail2", urlPatterns = { "/bookDetail2.do" })
public class BookDetailServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		BookService2 service = new BookService2();
		BookView bv = service.selectOneBook(bookNo);
		if(bv == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "등록되지 않은 도서번호입니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			RequestDispatcher view
	    	  =request.getRequestDispatcher("/WEB-INF/views/book/bookDetail.jsp");
	    	  request.setAttribute("bv", bv.getB());
	    	  request.setAttribute("RecommList", bv.getRecommList());
	    	  request.setAttribute("RerecommList", bv.getRerecommList());
	    	  
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
