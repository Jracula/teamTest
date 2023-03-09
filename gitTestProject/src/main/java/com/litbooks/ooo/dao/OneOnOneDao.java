package com.litbooks.ooo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.ooo.vo.OneOnOne;
import com.litbooks.qna.model.vo.Qna;

import common.JDBCTemplate;

public class OneOnOneDao {

	public ArrayList<OneOnOne> selectOneOnOneList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOne> list = new ArrayList<OneOnOne>();
		String query = "select * from (select rownum as rnum, n.* from(select o_no, o_title, o_writer, o_date from oneonone order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOne o = new OneOnOne();
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setoRegDate(rset.getString("o_date"));
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

	public int selectOneOnOneCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from oneonone"; //사용하기쉽도록 별칭붙이기
		
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
		String query = "insert into oneonone values(ooo_no_seq.nextval, ?, ?, ?, ?, ?, 0, to_char(sysdate, 'yyyy-mm-dd'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, o.getoMemberNo());
			pstmt.setString(2, o.getoWriter());
			pstmt.setString(3, o.getoTitle());
			pstmt.setString(4, o.getoContent());
			pstmt.setInt(5, o.getOflag());
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
		String query = "update oneonone set o_read_count = o_read_count+1 where o_no = ?";
		
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
		
		String query = "select * from oneonone where o_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, oNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OneOnOne();
				o.setoContent(rset.getString("o_content"));
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoMemberNo(rset.getInt("o_member_no"));
				o.setoRegDate(rset.getString("o_date"));
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

	public ArrayList<OneOnOne> selectOneOnOneList(Connection conn, int start1, int end1, String qMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOne> list = new ArrayList<OneOnOne>();
		String query = "select * from (select rownum as rnum, n.* from(select o_no, o_title, o_content, o_writer, o_date, o_flag "
				+ "from oneonone where o_member_no=? order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qMemberNo);
			pstmt.setInt(2, start1);
			pstmt.setInt(3, end1);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOne o = new OneOnOne();
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoContent(rset.getString("o_content"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
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

	public int selectOneOnONeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from oneonone";
		
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return totalCount;
	}

}
