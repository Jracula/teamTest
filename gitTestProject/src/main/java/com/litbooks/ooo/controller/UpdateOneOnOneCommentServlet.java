package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.notice.service.NoticeService;
import com.litbooks.ooo.vo.OneOnOneComment;

/**
 * Servlet implementation class UpdateOneOnOneCommentServlet
 */
@WebServlet(name = "UpdateOneOnOneComment", urlPatterns = { "/updateOneOnOneComment.do" })
public class UpdateOneOnOneCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOneOnOneCommentServlet() {
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
		OneOnOneComment oc = new OneOnOneComment();
		oc.setOo_content(request.getParameter("ocContent"));
		oc.setOo_no(Integer.parseInt(request.getParameter("ocNo")));
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		//3. 비즈니스로직
		NoticeService service = new NoticeService();
		int result = service.updateOneOnOneComment(oc);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글이 수정되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글이 수정에 실패햇습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/oneOnOneView.do?oNo="+oNo);
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
