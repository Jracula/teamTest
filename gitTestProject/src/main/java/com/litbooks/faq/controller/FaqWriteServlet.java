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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FaqWriteServlet
 */
@WebServlet(name = "FaqWrite", urlPatterns = { "/faqWrite.do" })
public class FaqWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqWriteServlet() {
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
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/faq";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		String faqTitle = mRequest.getParameter("faqTitle");
		String faqContent = mRequest.getParameter("faqContent");
		String faqWriter = mRequest.getParameter("faqWriter");
		int fFlag = Integer.parseInt(mRequest.getParameter("fFlag"));
		String filename = mRequest.getOriginalFileName("upfile");
		String filepath = mRequest.getFilesystemName("upfile");
		int fMemberNo = Integer.parseInt(mRequest.getParameter("fMemberNo"));
		
		Faq f = new Faq();
		f.setfContent(faqContent);
		f.setfTitle(faqTitle);
		f.setfWriter(faqWriter);
		f.setFilename(filename);
		f.setFilepath(filepath);
		f.setMemberNo(fMemberNo);
		f.setfFlag(fFlag);
		
		
		//3. 비즈니스 로직
		FaqService service = new FaqService();
		int result = service.insertFaq(f);
		System.out.println(result);
		
		//4. 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "자주하는 질문 게시글 작성 성공");
			request.setAttribute("msg", "게시글이 작성되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "자주하는 질문 게시글 작성 실패");
			request.setAttribute("msg", "오류가 발생했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/faqList.do?reqPage="+fMemberNo+"&fFlag="+fFlag);
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
