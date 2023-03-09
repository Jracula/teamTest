package com.litbooks.ooo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.ooo.service.OneOnOneService;
import com.litbooks.ooo.vo.OneOnOne;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class OneOnOneWriteServlet
 */
@WebServlet(name = "OneOnOneWrite", urlPatterns = { "/oneOnOneWrite.do" })
public class OneOnOneWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneOnOneWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1. 인코딩
				request.setCharacterEncoding("utf-8");

				String oneOnOneTitle = request.getParameter("oneOnOneTitle");
				String oneOnOneWriter = request.getParameter("oneOnOneWriter");
				String oneOnOneContent = request.getParameter("oneOnOneContent");
				int oneOnOneFlag = Integer.parseInt(request.getParameter("oneOnOneFlag"));
				int oMemberNo = Integer.parseInt(request.getParameter("oMemberNo"));
				//파일은 2개를 가져와야함 - 원본파일이름 / 경로에올라가는파일이름
				//페이지에 내가 첨부한 파일이름(중복처리 전 파일명)
				
				OneOnOne o = new OneOnOne();
				o.setoTitle(oneOnOneTitle);
				o.setoWriter(oneOnOneWriter);
				o.setoContent(oneOnOneContent);
				o.setoMemberNo(oMemberNo);
				o.setOflag(oneOnOneFlag);

				
				//3. 비즈니스로직
				OneOnOneService service = new OneOnOneService();
				int result = service.insertOneOnOne(o);
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
				request.setAttribute("loc", "/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo="+oMemberNo);
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
