package com.litbooks.basket.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.basket.Service.BasketService;
import com.litbooks.member.vo.Member;

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
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("m");
		String message = "";
		if (m == null) {
			message = "로그인이 필요합니다.";
		} else {
			if (m.getMemberLevel() == 2) {
				int bookNo = Integer.parseInt(request.getParameter("input1"));
				int memberNo = m.getMemberNo();
				BasketService service = new BasketService();
				message = service.selectBasket(bookNo, memberNo);
				if(message.equals("noError")) {
					int result = service.insertOneBasket(bookNo, memberNo);
					if(result>0) {
						message = "장바구니에 넣었습니다.";
					}else {
						message = "알 수 없는 이유로 장바구니에 넣지 못했습니다.";
					}
				}
			} else {
				message = "일반 회원 로그인이 필요합니다. 관리자는 장바구니 기능을 이용할 수 없습니다.";
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
