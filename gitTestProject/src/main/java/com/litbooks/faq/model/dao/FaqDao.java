package com.litbooks.faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.faq.model.vo.Faq;
import com.litbooks.qna.model.vo.Qna;

import common.JDBCTemplate;

public class FaqDao {

	public int insertFaq(Connection conn, Faq f) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = "insert into faq values(f_no_seq.nextval,?,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, f.getMemberNo());
			pstmt.setString(2, f.getfWriter());
			pstmt.setString(3, f.getfTitle());
			pstmt.setString(4, f.getfContent());
			pstmt.setInt(5, f.getfFlag());
			pstmt.setString(6, f.getFilename());
			pstmt.setString(7, f.getFilepath());
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
				+ "from faq where f_flag=? order by 1 desc)n) where rnum between ? and ?";
		
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
	
	public int selectFaqCount(Connection conn, int fFlag) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from faq where f_flag=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fFlag);
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
	
	public int selectQnaCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from qna";
		
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

	public ArrayList<Qna> selectQnaList(Connection conn, int start, int end, String qMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "select * from (select rownum as rnum, n.* from(select q_no, q_title, q_content, q_read_count, q_reg_date, q_writer, q_flag "
				+ "from qna where q_member_no=? order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qMemberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setqNo(rset.getInt("q_no"));
				q.setqTitle(rset.getString("q_title"));
				q.setqContent(rset.getString("q_content"));
				q.setqRegDate(rset.getString("q_reg_date"));
				q.setqWriter(rset.getString("q_writer"));
				q.setqFlag(rset.getInt("q_flag"));
				list.add(q);
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

	public Faq selectOneBoard(Connection conn, int fNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Faq f = null;
		String query = "select * from faq where f_no = ? order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				f = new Faq();
				f.setFilename(rset.getString("filename"));
				f.setFilepath(rset.getString("filepath"));
				f.setMemberNo(rset.getInt("f_member_no"));
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

	public int updateFaq(Connection conn, Faq f) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update faq set f_title = ?, f_content=?, filename = ?, filepath = ? where f_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getfTitle());
			pstmt.setString(2, f.getfContent());
			pstmt.setString(3, f.getFilename());
			pstmt.setString(4, f.getFilepath());
			pstmt.setInt(5, f.getfNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int deleteFaq(Connection conn, int fNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from faq where f_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}

