package com.litbooks.ooo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.ooo.vo.OneOnOne;

import common.JDBCTemplate;

public class OneOnOneDao {

	public ArrayList<OneOnOne> selectOneOnOneList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOne> list = new ArrayList<OneOnOne>();
		String query = "select * from (select rownum as rnum, n.* from(select notice_no, notice_title, notice_writer, read_count, reg_date from notice order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOne o = new OneOnOne();
				o.setoNo(rset.getInt("notice_no"));
				o.setoTitle(rset.getString("notice_title"));
				o.setoWriter(rset.getString("notice_writer"));
				o.setoReadCount(rset.getInt("read_count"));
				o.setoRegDate(rset.getString("reg_date"));
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from notice"; //사용하기쉽도록 별칭붙이기
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	public int insertOneOnOne(Connection conn, OneOnOne o) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice values(notice_seq.nextval, ?, ?, ?, 0, to_char(sysdate, 'yyyy-mm-dd'), ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, o.getoTitle());
			pstmt.setString(2, o.getoWriter());
			pstmt.setString(3, o.getoContent());
			pstmt.setString(4, o.getFileName());
			pstmt.setString(5, o.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int oNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set read_count = read_count+1 where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, oNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public OneOnOne selectOneNotice(Connection conn, int oNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OneOnOne o = null;
		String query = "select * from notice where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, oNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OneOnOne();
				o.setFileName(rset.getString("filename"));
				o.setFilepath(rset.getString("filepath"));
				o.setoContent(rset.getString("notice_content"));
				o.setoNo(rset.getInt("notice_no"));
				o.setoTitle(rset.getString("notice_title"));
				o.setoWriter(rset.getString("notice_writer"));
				o.setoReadCount(rset.getInt("read_count"));
				o.setoRegDate(rset.getString("reg_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return o;
		
		
	}

}
