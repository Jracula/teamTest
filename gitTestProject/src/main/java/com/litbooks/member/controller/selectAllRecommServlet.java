package com.litbooks.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.book.vo.BookView;
import com.litbooks.member.sevice.MemberService;
import com.litbooks.member.vo.AllMemberData;
import com.litbooks.member.vo.Member;

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

	//관리자 로그인인지 확인하기 위해서 현재 세션의 정보를 일단 받음
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
	//관리자가 맞으면 정상 실행
			if (me.getMemberLevel() == 1) {
//servlet 작성부 시작


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


//servlet 작성부 끝
	//관리자가 아닌 로그인일 때 거부
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "접근 제한");
				request.setAttribute("msg", "관리자 전용 기능입니다.");
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
