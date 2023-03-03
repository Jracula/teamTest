package com.litbooks.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet(name = "mypage", urlPatterns = { "/mypage.do" })
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memebrId = request.getParameter("memberId");
		
		//3
		MemberService service = new MemberService();
		Member m = service.selectOneMember(memebrId);
		
		//4
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp");
		request.setAttribute("m", m); 
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
