package com.litbooks.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.book.service.BookService;
import com.litbooks.book.vo.Recomm;

/**
 * Servlet implementation class InsertRecommServlet
 */
@WebServlet(name = "InsertRecomm", urlPatterns = { "/insertRecomm.do" })
public class InsertRecommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRecommServlet() {
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
		Recomm rc = new Recomm();
		rc.setRecommContent(request.getParameter("recommContent"));
		rc.setRcWriter(request.getParameter("rcWriter"));
		rc.setRecommRef(Integer.parseInt(request.getParameter("recommRef")));
		rc.setBookNo(Integer.parseInt(request.getParameter("bookNo")));
		//비로직
		BookService service = new BookService();
		int result = service.insertRecomm(rc);
		
		//결처리
		RequestDispatcher view
		=request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 작성 완료");
			request.setAttribute("icon", "success");

		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 작성 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/bookDetail.do?bookNo="+rc.getBookNo());
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
