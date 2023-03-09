package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.ooo.service.OneOnOneService;
import com.litbooks.ooo.vo.OneOnOne;
import com.litbooks.ooo.vo.OneOnOneViewData;

/**
 * Servlet implementation class OneOnOneView1Servlet
 */
@WebServlet(name = "OneOnOneView1", urlPatterns = { "/oneOnOneView1.do" })
public class OneOnOneView1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneOnOneView1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		//3. 비즈니스로직
		OneOnOneService service = new OneOnOneService();
		OneOnOne o = service.selectOneNotice(oNo, memberNo);
		OneOnOneViewData ovd = service.selectOneNotice(oNo);
		
		if(o == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "게시글이 존재하지 않습니다.");
			request.setAttribute("icon", "info");
			request.setAttribute("loc", "/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo="+memberNo);
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/oneOnOne/oneOnOneView.jsp");
			request.setAttribute("o", ovd.getO());
			request.setAttribute("commentList", ovd.getCommentList());
			view.forward(request, response);
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
