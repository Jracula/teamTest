package com.litbooks.faq.controller;

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
 * Servlet implementation class FaqUpdateFrmServlet
 */
@WebServlet(name = "FaqUpdateFrm", urlPatterns = { "/faqUpdateFrm.do" })
public class FaqUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateFrmServlet() {
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
		Faq f = service.getFaq(fNo);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/faq/faqUpdateFrm.jsp");
		request.setAttribute("f", f);
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
