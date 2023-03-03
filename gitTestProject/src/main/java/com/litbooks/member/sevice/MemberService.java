package com.litbooks.member.sevice;

import java.sql.Connection;

import com.litbooks.member.dao.MemberDao;
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
}
