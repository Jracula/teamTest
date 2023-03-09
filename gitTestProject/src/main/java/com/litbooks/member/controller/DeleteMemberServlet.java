package com.litbooks.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(name = "deleteMember", urlPatterns = { "/deleteMember.do" })
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//2
		String memberId = request.getParameter("memberId");


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
			if (me.getMemberId().equals(memberId)) {
//servlet 작성부 시작


		//3
		MemberService service = new MemberService();
		int result = service.deleteMember(memberId);
		
		//4
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
	         request.setAttribute("title", "탈퇴 성공!");
	         request.setAttribute("msg", "탈퇴성공");
	         request.setAttribute("icon", "success");
	         request.setAttribute("loc", "/logout.do"); //탈퇴하고, 로그아웃 후 메인페이지로 이동
	      }else {
	         request.setAttribute("title", "탈퇴 실패");
	         request.setAttribute("msg", "탈퇴 실패");
	         request.setAttribute("icon", "error");
	         request.setAttribute("loc", "/mypage.do?memberId="+memberId);
	      }
	      view.forward(request, response);


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
