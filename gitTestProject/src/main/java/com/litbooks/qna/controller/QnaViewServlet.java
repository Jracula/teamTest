package com.litbooks.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.qna.model.service.QnaService;
import com.litbooks.qna.model.vo.QnaViewData;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet(name = "QnaView", urlPatterns = { "/qnaView.do" })
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaViewServlet() {
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
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		//3. 비즈니스 로직
		QnaService service = new QnaService();
		QnaViewData qvd = service.selectOneBoard(qnaNo);
		
		//4. 결과 출력
		if(qvd == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "조회 실패");
			request.setAttribute("msg", "게시글이 존재하지 않습니다.");
			request.setAttribute("icon", "info");
			request.setAttribute("loc", "/qnaList.do?reqPage=1");
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/qna/qnaView.jsp");
			request.setAttribute("q", qvd.getQ());
			request.setAttribute("commentList", qvd.getCommentList());
			request.setAttribute("reCommentList", qvd.getReCommentList());
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
