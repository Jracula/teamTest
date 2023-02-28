package com.litbooks.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.book.service.BookService;
import com.litbooks.book.vo.Book;
import com.litbooks.member.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BookWriteServlet
 */
@WebServlet(name = "BookWrite", urlPatterns = { "/bookWrite.do" })
public class BookWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("m");
		if (m.getMemberLevel() == 1) {

			// multipart/form-data형식이면 데이터를 변환 후 출력 (cos.jar을 이용)
			// 2-1 파일이 업로드될 경로를 지정
			String root = getServletContext().getRealPath("/"); // "/"의 절대 경로를 산출
			String saveDirectory = root + "upload/book";
			// 2-2 업로드 파일의 최대용량을 설정 (일반적으로 10MB)
			int maxSize = 10 * 1024 * 1024;
			// 2-3 multipart/form-data에서 데이터를 꺼내기 위한 객체 변환
			// request → MultipartRequest 객체로 변환 by cos.jar
			// 매개변수 5개를 전달하면 변환
			// 1)request 2)파일저장경로 3)파일최대크기 4)인코딩타입 5)파일중복처리 객체
			MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			String bookTitle = mRequest.getParameter("bookTitle");
			String filepath = mRequest.getFilesystemName("imagefile");
			int bookEpi = Integer.parseInt(mRequest.getParameter("bookEpi"));
			int book1st = Integer.parseInt(mRequest.getParameter("book1st"));
			int nonFee = Integer.parseInt(mRequest.getParameter("nonFee"));
			int bookPrice = Integer.parseInt(mRequest.getParameter("bookPrice"));
			int discount = Integer.parseInt(mRequest.getParameter("discount"));
			String bookGenre = mRequest.getParameter("bookGenre");
			String bookWriter = mRequest.getParameter("bookWriter");
			String bookContent = mRequest.getParameter("bookContent");
			Book n = new Book();
			n.setBookTitle(bookTitle);
			n.setBookWriter(bookWriter);
			n.setBookContent(bookContent);
			n.setFilepath(filepath);
			BookService service = new BookService();
			int result = service.insertBook(n);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			if (result > 0) {
				request.setAttribute("title", "등록 성공");
				request.setAttribute("msg", "정상적으로 새 공지사항이 등록되었습니다.");
				request.setAttribute("icon", "success");
			} else {
				request.setAttribute("title", "등록 실패");
				request.setAttribute("msg", "알 수 없는 이유로 공지사항 등록에 실패했습니다.\n글자 수 제한을 넘겼을 수 있습니다.");
				request.setAttribute("icon", "error");
			}
			request.setAttribute("loc", "/booklist.do");
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "접근 제한");
			request.setAttribute("msg", "공지사항 작성은 관리자만 가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/booklist.do");
			view.forward(request, response);
		}
	}

}
