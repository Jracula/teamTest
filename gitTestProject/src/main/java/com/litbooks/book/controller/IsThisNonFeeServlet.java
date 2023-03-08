package com.litbooks.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.basket.Service.BasketService;
import com.litbooks.book.service.BookService;
import com.litbooks.book.vo.Book;
import com.litbooks.member.vo.Member;
import com.litbooks.orderB.service.OrderBService;

/**
 * Servlet implementation class IsThisNonFeeServlet
 */
@WebServlet(name = "IsThisNonFee", urlPatterns = { "/isThisNonFee.do" })
public class IsThisNonFeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IsThisNonFeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bookNo = Integer.parseInt(request.getParameter("theBookNo"));
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("m");
		BookService service = new BookService();
		Book b = service.getBook(bookNo);
		int result = 2;	//비로그인
		if (b.getNonFee() == 1) {
			result = 3;	//무료감상
		} else if (b.getNonFee() == 0) {
			if (m != null) {
				result = 0;	//유료인데 읽기권한이 없음
				if (m.getMemberLevel() == 2) {
					OrderBService service2 = new OrderBService();
					result = service2.checkPermission(m.getMemberNo(), bookNo);
				} else if (m.getMemberLevel() == 1) {
					result = 1;	//유료지만 읽기권한 있음
				}
			}
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
