package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.faq.model.service.FaqService;
import com.litbooks.member.vo.Member;
import com.litbooks.ooo.service.OneOnOneService;
import com.litbooks.ooo.vo.OneOnOnePageData;
import com.litbooks.qna.model.vo.QnaPageData;

/**
 * Servlet implementation class OneOnOneListServlet
 */
@WebServlet(name = "OneOnOneList", urlPatterns = { "/oneOnOneList.do" })
public class OneOnOneListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneOnOneListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int reqPage1 = Integer.parseInt(request.getParameter("reqPage1"));
		String qMemberNo = request.getParameter("memberNo");
		
		

	//현재 세션의 정보를 일단 받음
		HttpSession session = request.getSession(false);
		Member me = (Member) session.getAttribute("m");
		String getMemberNo = String.valueOf(me.getMemberNo());

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
			if (getMemberNo.equals(qMemberNo)) {
//servlet 작성부 시작
		
		//3. 비즈니스로직
		FaqService service = new FaqService();
		QnaPageData qpd = service.selectQnaList(reqPage,reqPage1,qMemberNo);
		OneOnOneService service1 = new OneOnOneService();
		OneOnOnePageData opd = service1.selectOneOnOneList(reqPage,reqPage1,qMemberNo);
		OneOnOnePageData opd1 = service1.selectOneOnOneList1(reqPage,reqPage1,qMemberNo);
		
	
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/oneOnOne/oneOnOneList.jsp");
		request.setAttribute("list", qpd.getList());
		request.setAttribute("list1", opd.getList());
		request.setAttribute("list2", opd1.getList());
		request.setAttribute("pageNavi", qpd.getPageNavi());
		request.setAttribute("pageNavi1", opd.getPageNavi());
		request.setAttribute("pageNavi2", opd1.getPageNavi());
		request.setAttribute("start", qpd.getStart());
		request.setAttribute("start1", opd.getStart());
		request.setAttribute("start2", opd1.getStart());
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
