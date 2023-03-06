package com.litbooks.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.AllMemberData;
import com.litbooks.member.vo.Member;

/**
 * Servlet implementation class SelectAllMemberServlet
 */
@WebServlet(
		name = "selectAllMember", 
		urlPatterns = { "/selectAllMember.do" })
public class SelectAllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//2
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));

		//3
		MemberService service = new MemberService();
	    AllMemberData amd = service.allMemberList(reqPage);
	    
	    //4
	    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/allMemberList.jsp");
	    request.setAttribute("list", amd.getList());
		request.setAttribute("pageNavi", amd.getPageNavi());
		request.setAttribute("start", amd.getStart());
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
