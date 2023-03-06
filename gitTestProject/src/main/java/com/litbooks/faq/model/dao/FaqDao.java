package com.litbooks.faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.faq.model.vo.Faq;

import common.JDBCTemplate;

public class FaqDao {

	public int insertFaq(Connection conn, Faq f) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = "insert into faq values(f_no_seq.nextval,?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, f.getMemberNo());
			pstmt.setString(2, f.getfWriter());
			pstmt.setString(3, f.getfTitle());
			pstmt.setString(4, f.getfContent());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Faq> selectFaqList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = new ArrayList<Faq>();
		String query = "select * from (select rownum as rnum, n.* from(select f_no, f_title, f_content, f_read_count, f_reg_date, f_writer, f_flag from faq order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Faq f = new Faq();
				f.setfNo(rset.getInt("f_no"));
				f.setfTitle(rset.getString("f_title"));
				f.setfContent(rset.getString("f_content"));
				f.setfReadCount(rset.getInt("f_read_count"));
				f.setfRegDate(rset.getString("f_reg_date"));
				f.setfWriter(rset.getString("f_writer"));
				f.setfFlag(rset.getInt("f_flag"));
				list.add(f);
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

	public ArrayList<Faq> selectFaqList(Connection conn, int start, int end, int fFlag) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = new ArrayList<Faq>();
		String query = "select * from (select rownum as rnum, n.* from(select f_no, f_title, f_content, f_read_count, f_reg_date, f_writer, f_flag "
				+ "from faq order by 1 desc)n where f_flag=?) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fFlag);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Faq f = new Faq();
				f.setfNo(rset.getInt("f_no"));
				f.setfTitle(rset.getString("f_title"));
				f.setfContent(rset.getString("f_content"));
				f.setfReadCount(rset.getInt("f_read_count"));
				f.setfRegDate(rset.getString("f_reg_date"));
				f.setfWriter(rset.getString("f_writer"));
				f.setfFlag(rset.getInt("f_flag"));
				list.add(f);
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
	
	public int selectFaqCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from faq";
		
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

	public int updateReadCount(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update faq set f_read_count = f_read_count+1 where f_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Faq selectOneFaq(Connection conn, int faqNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Faq f = null;
		String query = "select * from faq where f_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f = new Faq();
				f.setfContent(rset.getString("f_content"));
				f.setfNo(rset.getInt("f_no"));
				f.setfReadCount(rset.getInt("f_read_count"));
				f.setfRegDate(rset.getString("f_reg_date"));
				f.setfTitle(rset.getString("f_title"));
				f.setfWriter(rset.getString("f_writer"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return f;
	}
	


}
