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
 * Servlet implementation class UpdateRecommServlet
 */
@WebServlet(name = "UpdateRecomm", urlPatterns = { "/updateRecomm.do" })
public class UpdateRecommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRecommServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//1.인코딩
			request.setCharacterEncoding("utf-8");
			//2.값추출
			Recomm rc = new Recomm();
			rc.setRecommContent(request.getParameter("recommContent"));
			rc.setRecommNo(Integer.parseInt(request.getParameter("recommNo")));
			int bookNo = Integer.parseInt(request.getParameter("bookNo"));
			//3.비로직
			BookService service = new BookService();
			int result = service.UpdateRecomm(rc);
			//4.결처리
			RequestDispatcher view
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			if(result>0) {
				request.setAttribute("title", "성공");
				request.setAttribute("msg", "댓글 수정 완료");
				request.setAttribute("icon", "success");
			}else {
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "댓글 수정 실패");
				request.setAttribute("icon", "error");
				
			}
			request.setAttribute("loc", "/bookDetail.do?bookNo="+bookNo);
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
