package com.litbooks.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet(name = "signin", urlPatterns = { "/signin.do" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		Member m = new Member();
		m.setMemberId(loginId);
		m.setMemberPw(loginPw);
		
		MemberService service = new MemberService();
		Member member = service.selectOneMember(m);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(member == null) {
	    	 request.setAttribute("title", "로그인실패");
	    	 request.setAttribute("msg", "아이디또는 비밀번호 확인");
	    	 request.setAttribute("icon", "error");
	    	 request.setAttribute("loc", "/");
	     }else {
	    	 HttpSession session = request.getSession(); //브라우저 닫을때까지 정보 유지
	    	 session.setAttribute("m", member);
	    	 request.setAttribute("title", "로그인 성공");
	    	 request.setAttribute("msg", "환영합니다");
	    	 request.setAttribute("icon", "success");
	    	 request.setAttribute("loc", "/");
	     }
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
