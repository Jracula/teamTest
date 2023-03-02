package com.litbooks.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.litbooks.member.vo.Member;

import common.JDBCTemplate;

public class MemberDao {

	public Member selectOneMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
				
		String query = "select*from member where member_id=? and member_pw=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			rset = pstmt.executeQuery();	
			
			if(rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return member;
	}

	
	//아이디 중복 체크
	public Member selectOneMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rset = null;
		
		String query = "select * from member where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				m.setMemberEmail(rset.getString("member_email"));
				m.setEnrollDate(rset.getString("enroll_date"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getString("member_pw"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}

}












