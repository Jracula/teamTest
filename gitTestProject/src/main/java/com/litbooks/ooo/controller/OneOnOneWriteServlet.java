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
				
				//2. 값추출
				//enctype="multipart/form-data"(파일업로드)의 경우 데이터를 변환 후 출력(예시를 들면 취급주의 택배에 뽁뽁이로 한번더 감싸있는 것처럼감싸져있음)
				// cos.jar라는 파일업로드를 도와주는 라이브러리를 이용(/WEB-INF/lib/폴더에 해당 라이브러리가 존재해야함)
				//2-1. 파일이 업로드될 경로를 지정
				//webapp폴더의 절대경로를 구하는 방법(절대경로 : C드라이버부터 경로)
				String root = getServletContext().getRealPath("/");
				//업로드한 파일을 저장할 경로
				String saveDirectory = root+"upload/oneOnOne";
				//2-2. 파일 업로드 최대용량 설정(일반적으로 웹은 10MB가 최대)
				int maxSize = 10*1024*1024; //10MB
				//2-3. multipart/form-data에서 데이터를 꺼내기위한 객체변환작업(뽁뽁이 뜯기)
				//request객체를 MultipartRequest 객체로 변환(cos.jar에서 제공해주는 기능)
				//매개변수 5개를 전달하면 변환
				//1)request, 2)파일저장경로, 3)파일최대크기, 4)인코딩타임, 5)파일중복처리(cos에서 제공해주는 객체가있음)
				MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
				//request -> MultipartRequest로 변환완료
				//변환완료시점 -> 파일업로드가 완료되는 시점
				//입력정보 추출(request가 아닌 MultipartRequest에서 추출)
				String oneOnOneTitle = mRequest.getParameter("oneOnOneTitle");
				String oneOnOneWriter = mRequest.getParameter("oneOnOneWriter");
				String oneOnOneContent = mRequest.getParameter("oneOnOneContent");
				//파일은 2개를 가져와야함 - 원본파일이름 / 경로에올라가는파일이름
				//페이지에 내가 첨부한 파일이름(중복처리 전 파일명)
				String filename = mRequest.getOriginalFileName("upfile");
				//실제 서버에 업로드된 파일이름(중복처리 후 파일명)
				String filepath = mRequest.getFilesystemName("upfile"); 
				OneOnOne o = new OneOnOne();
				o.setoTitle(oneOnOneTitle);
				o.setoWriter(oneOnOneWriter);
				o.setoContent(oneOnOneContent);
				o.setFileName(filename);
				o.setFilepath(filepath);
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
				request.setAttribute("loc", "/oneOnOneList.do?reqPage=1");
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
