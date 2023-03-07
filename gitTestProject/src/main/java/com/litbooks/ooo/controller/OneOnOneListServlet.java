package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.ooo.service.OneOnOneService;
import com.litbooks.ooo.vo.OneOnOnePageData;

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
		//3. 비즈니스로직
		OneOnOneService service = new OneOnOneService();
		OneOnOnePageData opd = service.selectNoticeList(reqPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/OneOnOne/oneOnOneList.jsp");
		request.setAttribute("list", opd.getList());
		request.setAttribute("pageNavi", opd.getPageNavi());
		request.setAttribute("start", opd.getStart());
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
