package com.litbooks.qna.model.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.faq.model.vo.FaqPageData;
import com.litbooks.qna.model.dao.QnaDao;
import com.litbooks.qna.model.vo.Qna;
import com.litbooks.qna.model.vo.QnaComment;
import com.litbooks.qna.model.vo.QnaPageData;
import com.litbooks.qna.model.vo.QnaViewData;

import common.JDBCTemplate;

public class QnaService {
	private QnaDao dao;

	public QnaService() {
		super();
		dao = new QnaDao();
		// TODO Auto-generated constructor stub
	}
	

	public QnaPageData selectBoard(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = numPerPage*reqPage;
		int start = end-numPerPage+1;
		ArrayList<Qna> list = dao.selectBoard(conn,end,start);
		int totalCount = dao.selectBoardCount(conn);
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = (totalCount/numPerPage) + 1;
		}
		
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼(<)
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/boardList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자(1 2 3 4 5)
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/boardList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/boardList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			//for문을 중간에 탈출해야하는 경우가 있음 - 페이지가 끝나면 그 이후페이지(없는페이지)는 출력X
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/boardList.do?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//return 해줘야 하는 데이터가 2가지 - ArrayList<Notice> list / String pageNavi
		//-> 메소드는 1개만 리턴이 가능 -> 되돌려주고싶은 데이터를 합친 객체를 만들어준다
		//->vo에 NoticePageData 클래스를 만들어서 거기에 ArrayList<Notice> list / String pageNavi 생성해줌
		QnaPageData bpd = new QnaPageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		
		return bpd;

	}

	public int insertBoard(Qna b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBoard(conn,b);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}


	public ArrayList<Qna> selectBoard() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Qna> list = dao.selectBoard(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}


	public QnaViewData selectOneBoard(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateReadCount(conn,qnaNo);
		
		if(result > 0 ) {
			JDBCTemplate.commit(conn);
			Qna b = dao.selectOneBoard(conn,qnaNo);
			ArrayList<QnaComment> commentList = dao.selectBoardComment(conn,qnaNo);
			
		}
		
		return null;
	}

	
	
}
