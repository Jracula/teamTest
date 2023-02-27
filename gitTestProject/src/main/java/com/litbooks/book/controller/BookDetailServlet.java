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
import com.litbooks.book.vo.Book;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet(name = "BookDetail", urlPatterns = { "/bookDetail.do" })
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		BookService service = new BookService();
		Book b = service.selectOneBook(bookNo);
		if(b == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "등록되지 않은 도서번호입니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			// 시리즈물 조회를 위해서 selectSeriesBooks 함수 호출
			ArrayList<Book> list = service.selectSeriesBooks(b.getBook1st());
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookDetail.jsp");
			request.setAttribute("b", b);
			request.setAttribute("seriesList", list);
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
