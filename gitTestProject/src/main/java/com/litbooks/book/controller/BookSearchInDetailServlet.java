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
import com.litbooks.book.vo.SearchResultPage;

/**
 * Servlet implementation class BookSearchServlet
 */
@WebServlet(name = "BookSearch", urlPatterns = { "/bookSearchInDetail.do" })
public class BookSearchInDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchInDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchTitle = request.getParameter("searchKeyword");
		String selectedGenre[] = {};
		BookService service = new BookService();
		SearchResultPage bsr = service.selectBooksInHeader(searchTitle);
		request.setAttribute("books", bsr.getList());
		request.setAttribute("pageNavi", bsr.getPageNavi());
		request.setAttribute("start", 1);
		ArrayList<String> list = service.selectGenre();	//GENRE 테이블 읽어오기
		request.setAttribute("genreList", list);
		request.setAttribute("recievedTitle", searchTitle);
		request.setAttribute("recievedWriter", "");
		request.setAttribute("recievedGenre", selectedGenre);
		request.setAttribute("recievedOnSale", 0);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookSearchResult.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String searchTitleD = request.getParameter("searchTitle");
		String searchTitle = searchTitleD.trim().replaceAll("(\\s)\\1+","$1");
		String searchWriterD = request.getParameter("searchWriter");
		String searchWriter = searchWriterD.trim().replaceAll("(\\s)\\1+","$1");
		String selectedGenre[] = request.getParameterValues("selectedGenre");
		String noneArry[] = {};
		int onlyOnSale = 0;	//기본값(판매중지 제외가 체크되지 않은 상태)를 위해 0으로 초기화
		if(request.getParameter("onSale")!=null) {
			onlyOnSale = 1;
		}
		BookService service = new BookService();
		SearchResultPage bsr = service.selectBooksByWish(reqPage, searchTitle, searchWriter, onlyOnSale, selectedGenre);
		request.setAttribute("books", bsr.getList());
		request.setAttribute("pageNavi", bsr.getPageNavi());
		request.setAttribute("start", bsr.getStart());
		ArrayList<String> list = service.selectGenre();	//GENRE 테이블 읽어오기
		request.setAttribute("genreList", list);
		request.setAttribute("recievedTitle", searchTitle);
		request.setAttribute("recievedWriter", searchWriter);
		if(selectedGenre!=null) {
			request.setAttribute("recievedGenre", selectedGenre);
		}else {
			request.setAttribute("recievedGenre", noneArry);
		}
		request.setAttribute("recievedOnSale", onlyOnSale);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookSearchResult.jsp");
		view.forward(request, response);
	}

}
