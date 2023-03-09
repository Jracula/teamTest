package com.litbooks.qna.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.qna.model.service.QnaService;
import com.litbooks.qna.model.vo.Qna;

/**
 * Servlet implementation class FaqFileDownServlet
 */
@WebServlet(name = "QnaFileDown", urlPatterns = { "/qnaFileDown.do" })
public class QnaFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
				request.setCharacterEncoding("utf-8");
				//2.값추출
				//파일 다운 시 filename과 filepath가 둘다 필요함
				int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
				//3.비즈니스로직
				QnaService service = new QnaService();
				//Notice n = service.selectOneNotice(noticeNo); 사용하면 안되는 이유 : 조회수가 올라가기때문에
				Qna q = service.getQna(qnaNo);
				//4.결과처리
				//RequestDispather 사용X -> 파일이동을 할게 아니므로
				//Servlet이 서버에 upload되어있는 파일과 클라이언트를 연결해주는 역할을 할것
				
				//다운로드를 진행할 파일과 서블릿을 연결
				String root = getServletContext().getRealPath("/"); //webapp을 가져옴
				//webapp폴더 기준으로 다운로드를 진행할 파일명 연결
				String downFile = root+"upload/qna/"+q.getFilepath();//실제업로드된 이름이 필요하므로 filepath
				//파일을 서블릿으로 읽어오기위한 스트림생성(+속도 개선을 위한 보조스트림 생성)
				FileInputStream fis = new FileInputStream(downFile);
				BufferedInputStream bis = new BufferedInputStream(fis);//속도개선
				//읽어온파일을 사용자에게 내보낼 스트림생성(+보조스트림)
				ServletOutputStream sos = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(sos);
				//파일명 처리
				String resFilename = new String(q.getFileName().getBytes("UTF-8"),"ISO-8859-1");
				//파일 다운로드를 위한 HTTP 헤더 설정
				response.setContentType("application/octet-stream");//파일형식이란것을 알려줌
				response.setHeader("Content-Disposition", "attachment;filename="+resFilename);//파일이름을 알려줌
				//파일전송
				while(true) {
					int read = bis.read();
					//파일을 계속 읽다가 다읽으면 종료
					if(read != -1) {
						bos.write(read);
					}else {
						break;
					}
				}
				bos.close();
				bis.close();
			}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
