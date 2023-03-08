package com.litbooks.member.sevice;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.book.vo.BookView;
import com.litbooks.book.vo.Recomm;
import com.litbooks.member.dao.MemberDao;
import com.litbooks.member.vo.AllMemberData;
import com.litbooks.member.vo.Member;

import common.JDBCTemplate;

public class MemberService {
	private MemberDao dao;

	public MemberService() {
		super();
		dao = new MemberDao();
	}
	//로그인
	public Member selectOneMember(Member m) {
	    Connection conn = JDBCTemplate.getConnection();
	    Member member = dao.selectOneMember(conn, m);
	    JDBCTemplate.close(conn);
	    return member;
	}

	//아이디 중복체크
	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectOneMember(conn, memberId);
		JDBCTemplate.close(conn);
		return m;
	}
	
	//회원가입
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//수정 업데이트 
	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		 int result = dao.updateMember(conn,m);
		 if(result>0) {
			 JDBCTemplate.commit(conn);
		 }else {
			 JDBCTemplate.rollback(conn);
		 }
		 JDBCTemplate.close(conn);
		return result;
	}
	
	//탈퇴
	public int deleteMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		Member m = dao.selectMember(conn, memberId);
		
		if(m != null) {	
			result = dao.deleteMember(conn, memberId);
			if(result>0) {
				result = dao.insetDelMember(conn, m);
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		return result;
	}
	

	//전체회원 조회
	public AllMemberData allMemberList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = numPerPage*reqPage;
		int start = end - numPerPage+1;
		ArrayList<Member> list = dao.allMemberList(conn,start,end);
		
		int totalCount = dao.selectMemberCount(conn);
		
		//전체 페이지 수 
		int totalPage = 0;
		if(totalCount%numPerPage == 0){
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//네비게이션
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/selectAllMember.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi +="</a></li>";
		}
		
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) { //보고있는 페이지랑 페이지번호가 같을때 숫자에 효과
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/selectAllMember.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/selectAllMember.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) { 
				break;
			}
		}
			//다음버튼
			if(pageNo <= totalPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo)+"'>";
				pageNavi += "<span class='material-icons'>chevron_right</span>";
				pageNavi +="</a></li>";
			}
			
			pageNavi+= "</ul>";
			
			JDBCTemplate.close(conn);
			

			AllMemberData amd = new AllMemberData(list,pageNavi,start);
			
			return amd;

	}
	//댓글 전체 조회
	public BookView allRecommList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = numPerPage*reqPage;
		int start = end - numPerPage+1;
		ArrayList<Recomm> recommlist = dao.allRecommList(conn,start,end);
		
		int totalCount = dao.selectMemberCount(conn);
		
		//전체 페이지 수 
		int totalPage = 0;
		if(totalCount%numPerPage == 0){
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//네비게이션
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		String pageNavi = "<ul class='pagination circle-style'>";
		
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/selectAllRecomm.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi +="</a></li>";
		}
		
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) { //보고있는 페이지랑 페이지번호가 같을때 숫자에 효과
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/selectAllRecomm.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/selectAllRecomm.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) { 
				break;
			}
		}
			//다음버튼
			if(pageNo <= totalPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo)+"'>";
				pageNavi += "<span class='material-icons'>chevron_right</span>";
				pageNavi +="</a></li>";
			}
			
			pageNavi+= "</ul>";
			
			JDBCTemplate.close(conn);
			

			BookView bv = new BookView(recommlist, pageNavi, start);
			
			return bv;
	}

}




