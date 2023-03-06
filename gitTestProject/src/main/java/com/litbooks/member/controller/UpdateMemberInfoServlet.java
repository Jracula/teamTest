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
import javax.swing.text.View;

import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class UpdateMemberInfoServlet
 */
@WebServlet(
		name = "updateMemberInfo", 
		urlPatterns = { "/updateMemberInfo" }, 
		initParams = { 
				@WebInitParam(name = "/updateMemberInfo.do", value = "")
		})
public class UpdateMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//2
		String memberPw = request.getParameter("memberPw");
		Member m = new Member();
		if(memberPw.equals("")){
			m.setMemberPhone(request.getParameter("memberPhone"));
			m.setMemberEmail(request.getParameter("memberEmail"));
			m.setMemberId(request.getParameter("memberId"));
			}
		m.setMemberPw(request.getParameter("memberPw"));
		m.setMemberPhone(request.getParameter("memberPhone"));
		m.setMemberEmail(request.getParameter("memberEmail"));
		m.setMemberId(request.getParameter("memberId"));
		
		//3
		MemberService serivce = new MemberService();
		int result = serivce.updateMember(m);
		
		//4
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("title", "수정 완료");
			request.setAttribute("msg","정보 수정이 완료되었습니다");
			request.setAttribute("icon","success");
			request.setAttribute("loc","/mypage.do");
			
			HttpSession session = request.getSession();
	    	 Member member =(Member)session.getAttribute("m");
	    	 member.setMemberPw(m.getMemberPw());
	    	 member.setMemberPhone(m.getMemberPhone());
	    	 member.setMemberEmail(getServletInfo());
			
		}else {
			request.setAttribute("title", "수정 실패");
			request.setAttribute("msg","수정에 실패했습니다");
			request.setAttribute("icon","error");
			request.setAttribute("loc","/mypage.do");
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
