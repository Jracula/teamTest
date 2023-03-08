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
import com.litbooks.qna.model.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FaqUpdateServlet
 */
@WebServlet(name = "FaqUpdate", urlPatterns = { "/faqUpdate.do" })
public class FaqUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2.값추출
		//2-1. 파일업로드 경로설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/faq";
		//2-2. 파일최대크기 지정
		int maxSize = 10*1024*1024;
		//2-3. request -> MultipartRequest 객체로 변환(파일업로드시점)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		int fNo = Integer.parseInt(mRequest.getParameter("faqNo"));
		String fTitle = mRequest.getParameter("faqTitle");
		String fContent = mRequest.getParameter("faqContent");
		
		//기존파일이 지워졌으면 "delete", 그외에는 모두 "stay"(원래 파일이없거나, 지워지지않았을경우)
		String status = mRequest.getParameter("status"); 
		//새 첨부파일이 있으면 새 첨부파일 값, 없으면 null
		String filename = mRequest.getOriginalFileName("upfile");
		String filepath = mRequest.getFilesystemName("upfile");
		
		//기존 첨부파일이 있었으면 기존첨부파일 값, 없으면 null
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//oldFilename/oldFilepath가 있으면 기존파일이 있는경우
		if(oldFilename != null && status.equals("stay")) {
			//기존파일이 존재하고 삭제버튼을 눌렀을 경우
			filename = oldFilename;
			filepath = oldFilepath;
		}
		Faq f = new Faq();
		f.setfNo(fNo);
		f.setfTitle(fTitle);
		f.setfContent(fContent);
		f.setFilename(filename);
		f.setFilepath(filepath);
	
		//3.비즈니스로직
		FaqService service = new FaqService();
		int result = service.updateFaq(f);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "변경완료");
			request.setAttribute("msg", "공지사항이 수정되었습니다.");
			request.setAttribute("icon", "success");
			
			if(status.equals("delete")) {
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				delFile.delete();
			}
		}else {
			request.setAttribute("title", "변경실패");
			request.setAttribute("msg", "관리자에게 문의하세요.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/faqView.do?faqNo="+f.getfNo());
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
