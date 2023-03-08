package com.litbooks.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.book.vo.BookView;
import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.AllMemberData;

/**
 * Servlet implementation class selectAllRecommServlet
 */
@WebServlet(name = "selectAllRecomm", urlPatterns = { "/selectAllRecomm.do" })
public class selectAllRecommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectAllRecommServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
    	request.setCharacterEncoding("utf-8");
		
		//값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));

		//비로직
		MemberService service = new MemberService();
	    BookView bv = service.allRecommList(reqPage);
	    
	    //4
	    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/allRecommList.jsp");
	    request.setAttribute("recommlist", bv.getRecommList());
		request.setAttribute("pageNavi", bv.getPageNavi());
		request.setAttribute("start", bv.getStart());
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
