package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.faq.model.service.FaqService;
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
		//3. 비즈니스로직
		FaqService service = new FaqService();
		QnaPageData qpd = service.selectQnaList(reqPage,reqPage1,qMemberNo);
		OneOnOneService service1 = new OneOnOneService();
		OneOnOnePageData opd = service1.selectOneOnOneList(reqPage,reqPage1,qMemberNo);
		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/oneOnOne/oneOnOneList.jsp");
		request.setAttribute("list", qpd.getList());
		request.setAttribute("list1", opd.getList());
		request.setAttribute("pageNavi", qpd.getPageNavi());
		request.setAttribute("pageNavi1", opd.getPageNavi());
		request.setAttribute("start", qpd.getStart());
		request.setAttribute("start1", opd.getStart());
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
