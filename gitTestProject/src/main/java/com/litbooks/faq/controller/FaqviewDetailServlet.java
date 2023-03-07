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
import com.litbooks.faq.model.vo.FaqPageData;

/**
 * Servlet implementation class FaqviewDetailServlet
 */
@WebServlet(name = "FaqviewDetail", urlPatterns = { "/faqviewDetail.do" })
public class FaqviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int fFlag = Integer.parseInt(request.getParameter("value"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		FaqService service = new FaqService();
		FaqPageData fpd = service.selectFaqList(reqPage,fFlag);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/faq/faqList.jsp");
		request.setAttribute("list", fpd.getList());
		request.setAttribute("pageNavi", fpd.getPageNavi());
		request.setAttribute("start", fpd.getStart());
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
