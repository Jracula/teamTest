package com.litbooks.book.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.book.service.BookService;
import com.litbooks.book.vo.Book;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class BookUpdateFrmServlet
 */
@WebServlet(name = "BookUpdateFrm", urlPatterns = { "/bookUpdateFrm.do" })
public class BookUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		Member m = (Member) session.getAttribute("m");
		if (m == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "접근 제한");
			request.setAttribute("msg", "비회원은 접근 불가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			if (m.getMemberLevel() == 1) {
				BookService service = new BookService();
				ArrayList<String> list = service.selectGenre();	//GENRE 테이블 읽어오기
				Book b = service.getBook(bookNo);
				request.setAttribute("genreList", list);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/book/bookUpdateFrm.jsp");
				request.setAttribute("book", b);
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "접근 제한");
				request.setAttribute("msg", "도서 정보 수정은 관리자만 가능합니다.");
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
