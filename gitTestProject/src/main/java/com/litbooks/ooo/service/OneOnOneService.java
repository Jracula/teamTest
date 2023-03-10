package com.litbooks.ooo.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.ooo.dao.OneOnOneDao;
import com.litbooks.ooo.vo.OneOnOne;
import com.litbooks.ooo.vo.OneOnOneComment;
import com.litbooks.ooo.vo.OneOnOnePageData;
import com.litbooks.ooo.vo.OneOnOneViewData;

import common.JDBCTemplate;

public class OneOnOneService {
	
	private OneOnOneDao dao;

	public OneOnOneService() {
		super();
		dao = new OneOnOneDao();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public OneOnOnePageData selectOneOnOneList(int reqPage, String qMemberNo) {
	 * Connection conn = JDBCTemplate.getConnection(); //공지사항 페이징처리 //1. 한 페이지당 게시물
	 * 수 지정 -> 10개 int numPerPage = 5; //요청페이지(reqPage)가 1페이지이면 -> 최신글 1~10(정렬기준)
	 * //요청페이지(reqPage)가 2페이지이면 -> 최신글 11~20 //요청페이지(reqPage)가 3페이지면 -> 최신글 21~30
	 * //reqPage == 4 -> 31~40; int end = numPerPage*reqPage; int start = end -
	 * numPerPage + 1;
	 * 
	 * ArrayList<OneOnOne> list = dao.selectOneOnOneList(conn, start, end); //페이징제작
	 * 시작 //전체페이지수를 계산 -> 총게시물 수를 조회 int totalCount =
	 * dao.selectOneOnOneCount(conn,qMemberNo); //전체 페이지수를 계산 int totalPage = 0;
	 * if(totalCount%numPerPage == 0) { totalPage = totalCount/numPerPage; }else {
	 * totalPage = (totalCount/numPerPage) + 1; } //네비게이션 사이즈 : 지정해줘야 할 값 / 총페이지가
	 * 23페이지 일때 1~10페이지만 보여주고 다음버튼을 보여줄지 정하는것 //아래 페이징버튼에 < 1 2 3 4 5 > 이렇게 만들어서
	 * 5페이지만 보여줄 것 int pageNaviSize = 5; //페이지 네비게이션 시작번호 //reqPage 1 ~ 5 : 1 2 3 4
	 * 5 //reqPage 6 ~ 10 : 6 7 8 9 10 //reqPage 11 ~ 15 : 11 12 13 14 15
	 * 
	 * int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1; //
	 * ((reqPage-1)/5)*5 + 1 // reqPage = 1~5 -> reqPage-1 = 0~4 // 0/5*5 + 1 = 1 ->
	 * 시작번호 1이 나옴 -> for문으로 증가해줄것
	 * 
	 * //페이지 네비게이션 제작 시작 String pageNavi = "<ul class='pagination circle-style'>";
	 * //이전버튼(<) if(pageNo != 1) { pageNavi += "<li>"; pageNavi +=
	 * "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo-1)+"'>";
	 * pageNavi += "<span class='material-icons'>chevron_left</span>"; pageNavi +=
	 * "</a></li>"; } //페이지 숫자(1 2 3 4 5) for(int i=0; i<pageNaviSize; i++) {
	 * if(pageNo == reqPage) { pageNavi += "<li>"; pageNavi +=
	 * "<a class='page-item active-page' href='/oneOnOneList.do?reqPage="+(pageNo)+
	 * "'>"; pageNavi += pageNo; pageNavi += "</a></li>"; }else { pageNavi +=
	 * "<li>"; pageNavi +=
	 * "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo)+"'>";
	 * pageNavi += pageNo; pageNavi += "</a></li>"; } pageNo++; //for문을 중간에 탈출해야하는
	 * 경우가 있음 - 페이지가 끝나면 그 이후페이지(없는페이지)는 출력X if(pageNo>totalPage) { break; } }
	 * //다음버튼 if(pageNo <= totalPage) { pageNavi += "<li>"; pageNavi +=
	 * "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo)+"'>";
	 * pageNavi += "<span class='material-icons'>chevron_right</span>"; pageNavi +=
	 * "</a></li>"; } pageNavi += "</ul>";
	 * 
	 * OneOnOnePageData opd = new OneOnOnePageData(list, pageNavi, start);
	 * JDBCTemplate.close(conn); return opd;
	 * 
	 * }
	 */

	public int insertOneOnOne(OneOnOne o) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertOneOnOne(conn, o);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	
	}

	public OneOnOne selectOneOnOne(int oNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateReadCount(conn, oNo);
		if(result > 0) {
			OneOnOne o = dao.selectOneNotice(conn, oNo);
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return o;
			
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		
	}

	public OneOnOne getOneOnOne(int oNo) {
		Connection conn = JDBCTemplate.getConnection();
		OneOnOne o = dao.selectOneNotice(conn, oNo);
		JDBCTemplate.close(conn);
		return o;
	}

	public OneOnOnePageData selectOneOnOneList(int reqPage, String qMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = numPerPage*reqPage;
		int start = end-numPerPage+1;
		
		ArrayList<OneOnOne> list = dao.selectOneOnOneList(conn,start,end,qMemberNo);
		int totalCount = dao.selectOneOnOneCount(conn,qMemberNo);
		
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
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo-1)+"&memberNo="+qMemberNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자(1 2 3 4 5)
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/oneOnOneList.do?reqPage="+(pageNo)+"&memberNo="+qMemberNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo)+"&memberNo="+qMemberNo+"'>";
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
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//return 해줘야 하는 데이터가 2가지 - ArrayList<Notice> list / String pageNavi
		//-> 메소드는 1개만 리턴이 가능 -> 되돌려주고싶은 데이터를 합친 객체를 만들어준다
		//->vo에 NoticePageData 클래스를 만들어서 거기에 ArrayList<Notice> list / String pageNavi 생성해줌
		OneOnOnePageData opd = new OneOnOnePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return opd;
	}

	public OneOnOnePageData selectOneOnOneList(int reqPage, int reqPage1, String qMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = numPerPage*reqPage1;
		int start = end-numPerPage+1;
		
		ArrayList<OneOnOne> list = dao.selectOneOnOneList(conn,start,end,qMemberNo);
		int totalCount = dao.selectOneOnOneCount(conn,qMemberNo);
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = (totalCount/numPerPage) + 1;
		}
		
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage1-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼(<)
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo-1)+"&memberNo="+qMemberNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자(1 2 3 4 5)
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage1) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"&memberNo="+qMemberNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"&memberNo="+qMemberNo+"'>";
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
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//return 해줘야 하는 데이터가 2가지 - ArrayList<Notice> list / String pageNavi
		//-> 메소드는 1개만 리턴이 가능 -> 되돌려주고싶은 데이터를 합친 객체를 만들어준다
		//->vo에 NoticePageData 클래스를 만들어서 거기에 ArrayList<Notice> list / String pageNavi 생성해줌
		OneOnOnePageData opd = new OneOnOnePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return opd;
	}

	public int updateOneOnOne(OneOnOne o) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateOneOnOne(conn,o);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

	public OneOnOne selectOneNotice(int oNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		OneOnOne o = dao.selectOneNotice(conn, oNo);
		JDBCTemplate.close(conn);
		return o;
	}

	public OneOnOnePageData selectOneOnOneList1(int reqPage, int reqPage1, String qMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 5;
		int end = numPerPage*reqPage1;
		int start = end-numPerPage+1;
		
		ArrayList<OneOnOne> list = dao.selectOneOnOneList(conn,start,end);
		int totalCount = dao.selectOneOnOneCount(conn);
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = (totalCount/numPerPage) + 1;
		}
		
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage1-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		//이전버튼(<)
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo-1)+"&memberNo="+qMemberNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자(1 2 3 4 5)
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage1) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"&memberNo="+qMemberNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"&memberNo="+qMemberNo+"'>";
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
			pageNavi += "<a class='page-item' href='/oneOnOneList.do?reqPage="+reqPage+"&reqPage1="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//return 해줘야 하는 데이터가 2가지 - ArrayList<Notice> list / String pageNavi
		//-> 메소드는 1개만 리턴이 가능 -> 되돌려주고싶은 데이터를 합친 객체를 만들어준다
		//->vo에 NoticePageData 클래스를 만들어서 거기에 ArrayList<Notice> list / String pageNavi 생성해줌
		OneOnOnePageData opd = new OneOnOnePageData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return opd;
	}

	public OneOnOneViewData selectOneNotice(int oNo) {
		Connection conn = JDBCTemplate.getConnection();
		//상세보기를 하면 조회수가 1씩 카운트 해줘야함 - connection 닫기전에 해줘야한다.
		//업데이트가 먼저 발생해야 쿼리문을 읽어올 때 +1된 조회수를 얻을 수 있다.
		int result = dao.updateReadCount(conn, oNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
			OneOnOne o = dao.selectOneNotice(conn, oNo);
			//1. 일반댓글 조회
			//여러개일 수 있으므로 ArrayList로 받음
			ArrayList<OneOnOneComment> commentList = dao.selectNoticeComment(conn, oNo); 
			//return해줘야할게 notice / arrayList(2개) 이므로 vo를 새로 만든다
			OneOnOneViewData ovd = new OneOnOneViewData(o, commentList);
			
			//return이 둘다 들어가고있으므로 close를 두 경우 다 해줘야한다.
			JDBCTemplate.close(conn);
			return ovd;
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
	}

	public int insertOneOnOneComment(OneOnOneComment oc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertOneOnOneCommnet(conn, oc);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	

}
