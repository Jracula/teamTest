package com.litbooks.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.qna.model.service.QnaService;
import com.litbooks.qna.model.vo.Qna;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet(name = "QnaWrite", urlPatterns = { "/qnaWrite.do" })
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2-1. 파일이 업로드 될 경로 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/qna";
		//2-2. 파일 최대크기 지정
		int maxSize = 10*1024*1024;
		//2-3. 데이터를 꺼내기 위한 객체 변환작업
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
		int qMemberNo = Integer.parseInt(mRequest.getParameter("qMemberNo"));
		String boardWriter = mRequest.getParameter("qWriter");
		String boardTitle = mRequest.getParameter("qTitle");
		String boardContent = mRequest.getParameter("qContent");
		String filename = mRequest.getOriginalFileName("upfile");
		String filepath = mRequest.getFilesystemName("upfile");
		Qna b = new Qna();
		b.setqWriter(boardWriter);
		b.setqTitle(boardTitle);
		b.setqContent(boardContent);
		b.setFileName(filename);
		b.setFilepath(filepath);
		b.setMemberNo(qMemberNo);
		
		//3. 비즈니스 로직
		QnaService service = new QnaService();
		int result = service.insertBoard(b);
		

		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result > 0) {
			request.setAttribute("title", "게시글 작성 성공");
			request.setAttribute("msg", "게시글이 작성되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "게시글 작성 실패");
			request.setAttribute("msg", "오류가 발생했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/qnaList.do?reqPage=1");
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
