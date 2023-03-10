package com.litbooks.ooo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.litbooks.member.vo.Member;
import com.litbooks.ooo.service.OneOnOneService;
import com.litbooks.ooo.vo.OneOnOne;

/**
 * Servlet implementation class DeleteOneOnOneServlet
 */
@WebServlet(name = "DeleteOneOnOne", urlPatterns = { "/deleteOneOnOne.do" })
public class DeleteOneOnOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOneOnOneServlet() {
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
		int oNo = Integer.parseInt(request.getParameter("oneOnOneNo"));
		
		HttpSession session = request.getSession(false);
		Member me = (Member) session.getAttribute("m");
		int memberNo = me.getMemberNo();
		
		//3. 비즈니스로직
		OneOnOneService service = new OneOnOneService();
		//삭제라는 행동만 할거면 int로 받을 거지만
		//파일도 지워야하기때문에 filepath가 필요함 -> Notice로 받아옴
		OneOnOne o = service.deleteOneOnOne(oNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(o != null) {
			//DB가 성공적으로 삭제되었을 때 첨부파일이 있는지 확인한 후 
			//첨부파일이 있을 경우 삭제
			if(o.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root+"upload/oneOnOne/"+o.getFilepath();
				File delFile = new File(deleteFile);
				delFile.delete(); //파일삭제코드
			}
			request.setAttribute("title", "삭제성공");
			request.setAttribute("msg", "게시글이 삭제되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo="+memberNo);
		}else {
			request.setAttribute("title", "삭제실패");
			request.setAttribute("msg", "오류가 발생했습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/oneOnOneView.do?oneOnOneNo"+oNo);
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
