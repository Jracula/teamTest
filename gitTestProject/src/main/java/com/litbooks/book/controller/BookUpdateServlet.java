package com.litbooks.book.controller;

import java.io.File;
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
 * Servlet implementation class BookUpdateServlet
 */
@WebServlet(name = "BookUpdate", urlPatterns = { "/bookUpdate.do" })
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateServlet() {
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
		if (m == null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "접근 제한");
			request.setAttribute("msg", "비회원은 접근 불가능합니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/index.jsp");
			view.forward(request, response);
		} else {
			if (m.getMemberLevel() == 1) {	//관리자 계정인지 확인

				String root = getServletContext().getRealPath("/"); // "/"의 절대 경로를 산출
				String saveDirectory = root + "upload/book/cover-image";
				// 2-2 업로드 파일의 최대용량을 설정 (일반적으로 10MB)
				int maxSize = 10 * 1024 * 1024;
				// 2-3 multipart/form-data에서 데이터를 꺼내기 위한 객체 변환
				// request → MultipartRequest 객체로 변환 by cos.jar
				// 매개변수 5개를 전달하면 변환
				// 1)request 2)파일저장경로 3)파일최대크기 4)인코딩타입 5)파일중복처리 객체
				MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
				int bookNo = Integer.parseInt(mRequest.getParameter("bookNo"));
				String bookTitle = mRequest.getParameter("bookTitle");
				int status = Integer.parseInt(mRequest.getParameter("status"));
				String filepath = mRequest.getFilesystemName("imagefile");
				String oldFilepath = mRequest.getParameter("oldFilepath");
				int bookEpi = Integer.parseInt(mRequest.getParameter("bookEpi"));
				int book1st = 0;	//book1st값을 받지 않았을 경우를 위해 0으로 초기화
				if(mRequest.getParameter("book1st").length()!=0) {
					book1st = Integer.parseInt(mRequest.getParameter("book1st"));
				}
				int nonFee = 0;	//기본값(무료 감상이 체크되지 않은 상태)를 위해 0으로 초기화
				if(mRequest.getParameter("nonFee")!=null) {
					nonFee = Integer.parseInt(mRequest.getParameter("nonFee"));
				}
				int bookPrice = Integer.parseInt(mRequest.getParameter("bookPrice"));
				int discount = Integer.parseInt(mRequest.getParameter("discount"));
				int onSale = 0;	//판매 중이 체크되지 않은 상태를 위해 0으로 초기화
				if(mRequest.getParameter("onSale")!=null) {
					onSale = Integer.parseInt(mRequest.getParameter("onSale"));
				}
				String bookGenre = mRequest.getParameter("bookGenre");
				String writer = mRequest.getParameter("writer");
				String publisher = mRequest.getParameter("publisher");
				String bookIntro = mRequest.getParameter("bookIntro");

				Book b = new Book();
				b.setBookNo(bookNo);
				b.setBookTitle(bookTitle);
				b.setBookGenre(bookGenre);
				b.setWriter(writer);
				b.setPublisher(publisher);
				b.setBookPrice(bookPrice);
				b.setDiscount(discount);
				b.setOnSale(onSale);
				b.setBookIntro(bookIntro);
				b.setBookEpi(bookEpi);
				b.setBook1st(book1st);
				b.setNonFee(nonFee);
				b.setBookImage(filepath);
				BookService service = new BookService();
				int result = service.updateBook(b);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				if (result > 0) {
					if(status==0) {	//기존에 이미지파일이 있었고, 삭제나 교체를 한 경우
						File delFile = new File(saveDirectory + "/" + oldFilepath);
						delFile.delete(); //기존의 이미지파일 삭제
						if(filepath != null) {	//이미지파일을 교체한 경우
							String numbering = String.format("%08d", bookNo);
							String format = filepath.substring(filepath.lastIndexOf(".")); //.확장자
							String newFilePath = numbering + format;
							service.updateBookImage(newFilePath, bookNo);
				
							java.io.File fullPath = new java.io.File(saveDirectory + "/" + filepath);
							java.io.File newFullPath = new java.io.File(saveDirectory + "/" + newFilePath);
							fullPath.renameTo(newFullPath);
						}else {	//기존 이미지파일을 삭제만 한 경우
							service.updateBookImage("", bookNo);
						}
					}else if(status==1) {
						String hasImage = service.getBook(bookNo).getBookImage();
						if(filepath != null) {
							if(hasImage == null) {	//기존에 이미지파일이 없었고, 신규 파일이 업로드된 경우
								String numbering = String.format("%08d", bookNo);
								String format = filepath.substring(filepath.lastIndexOf(".")); //.확장자
								String newFilePath = numbering + format;
								service.updateBookImage(newFilePath, bookNo);
					
								java.io.File fullPath = new java.io.File(saveDirectory + "/" + filepath);
								java.io.File newFullPath = new java.io.File(saveDirectory + "/" + newFilePath);
								fullPath.renameTo(newFullPath);
							}
						}
					}
					request.setAttribute("title", "등록 성공");
					request.setAttribute("msg", "정상적으로 도서 정보가 수정되었습니다.");
					request.setAttribute("icon", "success");
				} else {
					request.setAttribute("title", "등록 실패");
					request.setAttribute("msg", "알 수 없는 이유로 도서 정보 수정에 실패했습니다.\n허용 글자 수 제한을 넘겼을 수 있습니다.");
					request.setAttribute("icon", "error");
				}
				request.setAttribute("loc", "/bookDetail.do?bookNo="+bookNo);
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "접근 제한");
				request.setAttribute("msg", "도서 정보 수정은 관리자만 가능합니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "/index.jsp");
				view.forward(request, response);
			}
		}
	}

}
