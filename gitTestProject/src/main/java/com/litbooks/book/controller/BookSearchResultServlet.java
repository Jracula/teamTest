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
 * Servlet implementation class BookSearchResultServlet
 */
@WebServlet(name = "BookSearchResult", urlPatterns = { "/bookSearchResult.do" })
public class BookSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchKeyword = request.getParameter("searchKeyword");
		BookService service = new BookService();
		ArrayList<Book> books = service.selectBooksInHeader(searchKeyword);
		request.setAttribute("books", books);
		ArrayList<String> list = service.selectGenre();	//GENRE 테이블 읽어오기
		request.setAttribute("genreList", list);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookSearchResult.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
