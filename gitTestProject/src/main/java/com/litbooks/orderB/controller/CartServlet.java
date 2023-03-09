package com.litbooks.orderB.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.basket.Service.BasketService;
import com.litbooks.basket.vo.Basket;
import com.litbooks.book.vo.Book;
import com.litbooks.member.vo.Member;

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
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));

	//현재 세션의 정보를 일단 받음
		HttpSession session = request.getSession(false);
		Member me = (Member) session.getAttribute("m");

	//비로그인자는 접근 거부하는 if문
		if (me == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "접근 제한");
			request.setAttribute("msg", "비회원은 접근 불가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
	//본인 것이 맞으면 정상 실행
			if (me.getMemberNo() == memberNo) {
//servlet 작성부 시작


		BasketService service = new BasketService();
		
		// 장바구니에서 책번호 조회
		ArrayList<Basket> list = service.selectBookNumber(memberNo);
		
		if(list.isEmpty()) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "장바구니 조회불가");
			request.setAttribute("msg", "책을 장바구니에 담아주세요");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
			return;
		} else {
			// 장바구니에서 회원이 담은 책 제목, 책 가격 조회
			ArrayList<Book> bask = service.selectBookDetail(memberNo);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/cart.jsp");
			request.setAttribute("list", list);
			request.setAttribute("bask", bask);
			view.forward(request, response);
		}


//servlet 작성부 끝
	//본인 것이 아닐 때 거부
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "접근 제한");
				request.setAttribute("msg", "본인의 계정이 아닙니다.");
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
