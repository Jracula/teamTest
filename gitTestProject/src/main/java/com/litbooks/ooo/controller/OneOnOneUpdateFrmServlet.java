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

/**
 * Servlet implementation class OneOnOneUpdateFrmServlet
 */
@WebServlet(name = "OneOnOneUpdateFrm", urlPatterns = { "/oneOnOneUpdateFrm.do" })
public class OneOnOneUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneOnOneUpdateFrmServlet() {
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
		//3. 비즈니스로직
		OneOnOneService service = new OneOnOneService();
		OneOnOne o = service.getOneOnOne(oNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/oneOnOne/oneOnOneUpdateFrm.jsp");
		request.setAttribute("o", o);
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
