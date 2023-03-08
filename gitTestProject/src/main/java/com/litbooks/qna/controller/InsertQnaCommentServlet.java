package com.litbooks.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.notice.service.NoticeService;
import com.litbooks.qna.model.service.QnaService;
import com.litbooks.qna.model.vo.QnaComment;

/**
 * Servlet implementation class InsertQnaCommentServlet
 */
@WebServlet(name = "InsertQnaComment", urlPatterns = { "/insertQnaComment.do" })
public class InsertQnaCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQnaCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		QnaComment qc = new QnaComment();
		qc.setQcContent(request.getParameter("qcContent"));
		qc.setQcWriter(request.getParameter("qcWriter"));
		qc.setQnaRef(Integer.parseInt(request.getParameter("qnaRef")));
		qc.setQcRef(Integer.parseInt(request.getParameter("qcRef")));
		//3. 비즈니스로직
		QnaService service = new QnaService();
		int result = service.insertQnaComment(qc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 작성 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 작성 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/qnaView.do?qnaNo="+qc.getQnaRef());
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
