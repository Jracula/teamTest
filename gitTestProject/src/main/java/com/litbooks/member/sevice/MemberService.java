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

	public Member selectOneMember(Member m) {
	    Connection conn = JDBCTemplate.getConnection();
	    Member member = dao.selectOneMember(conn, m);
	    JDBCTemplate.close(conn);
	    return member;
	}
}
