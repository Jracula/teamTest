package com.litbooks.faq.model.vo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.litbooks.faq.model.service.FaqService;

/**
 * Servlet implementation class FaqAjaxServlet
 */
@WebServlet(name = "FaqAjax", urlPatterns = { "/faqAjax.do" })
public class FaqAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int reqPage = 1;
		int fFlag = Integer.parseInt(request.getParameter("fFlag"));
		System.out.println(fFlag);
		//3.비즈니스 로직
		FaqService service = new FaqService();
		FaqPageData fpd = service.selectFaqList(reqPage,fFlag);
		System.out.println(fpd);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		gson.toJson(fpd, out);//4번에서 했던 모든작업을 gson이 대신해준다
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
