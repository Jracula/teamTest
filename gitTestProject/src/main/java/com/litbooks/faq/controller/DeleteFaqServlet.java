package com.litbooks.faq.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.faq.model.service.FaqService;
import com.litbooks.faq.model.vo.Faq;

/**
 * Servlet implementation class DeleteFaqServlet
 */
@WebServlet(name = "DeleteFaq", urlPatterns = { "/deleteFaq.do" })
public class DeleteFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFaqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int fNo = Integer.parseInt(request.getParameter("faqNo"));
		FaqService service = new FaqService();
		Faq f = service.deleteFaq(fNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(f != null) {
			if(f.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root+"upload/faq/"+f.getFilepath();
				File delFile = new File(deleteFile);
				delFile.delete(); //파일삭제코드			
		}
		request.setAttribute("title", "삭제성공");
		request.setAttribute("msg", "게시글이 삭제되었습니다.");
		request.setAttribute("icon", "success");
		request.setAttribute("loc", "/faqList.do?reqPage=1&fFlag=1");
		
		}else {
			request.setAttribute("title", "삭제실패");
			request.setAttribute("msg", "오류가 발생했습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/faqView.do?faqNo"+fNo);
		}
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
