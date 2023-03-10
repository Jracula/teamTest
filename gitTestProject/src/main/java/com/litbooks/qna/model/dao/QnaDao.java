package com.litbooks.qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.litbooks.qna.model.vo.Qna;
import com.litbooks.qna.model.vo.QnaComment;

import common.JDBCTemplate;

public class QnaDao {

	public ArrayList<Qna> selectBoard(Connection conn, int end, int start) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		
		String query = "select * from (select rownum as rnum, n.* from(select q_no, q_title, q_content, q_read_count, q_reg_date, q_writer from qna order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setqNo(rset.getInt("q_no"));
				q.setqReadCount(rset.getInt("q_read_count"));
				q.setqRegDate(rset.getString("q_reg_date"));
				q.setqTitle(rset.getString("q_title"));
				q.setqWriter(rset.getString("q_writer"));
				list.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	public int insertBoard(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna values(q_no_seq.nextval, ?, ?, ?, ?, 0, to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, q.getMemberNo());
			pstmt.setString(2, q.getqWriter());
			pstmt.setString(3, q.getqTitle());
			pstmt.setString(4, q.getqContent());
			pstmt.setInt(5, q.getqFlag());
			pstmt.setString(6, q.getFileName());
			pstmt.setString(7, q.getFilepath());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectBoardCount(Connection conn) {
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

	public ArrayList<Qna> selectBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		
		String query = "select q_no, q_title, q_writer, q_read_count, q_reg_date from qna order by 1 desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setqNo(rset.getInt("q_no"));
				q.setqTitle(rset.getString("q_title"));
				q.setqWriter(rset.getString("q_writer"));
				q.setqReadCount(rset.getInt("q_read_count"));
				q.setqRegDate(rset.getString("q_reg_date"));
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

	public int updateReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update qna set q_read_count = q_read_count+1 where q_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Qna selectOneBoard(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna q = null;
		String query = "select * from qna where q_no = ? order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				q = new Qna();
				q.setFileName(rset.getString("filename"));
				q.setFilepath(rset.getString("filepath"));
				q.setMemberNo(rset.getInt("q_member_no"));
				q.setqContent(rset.getString("q_content"));
				q.setqNo(rset.getInt("q_no"));
				q.setqReadCount(rset.getInt("q_read_count"));
				q.setqRegDate(rset.getString("q_reg_date"));
				q.setqTitle(rset.getString("q_title"));
				q.setqWriter(rset.getString("q_writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		
		
	}
		
		return q;

}

	public ArrayList<QnaComment> selectBoardComment(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from qna_comment where q_ref = ? and qc_ref is null order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setQcContent(rset.getString("qc_content"));
				qc.setQcDate(rset.getString("qc_date"));
				qc.setQcNo(rset.getInt("qc_no"));
				qc.setQcRef(rset.getInt("qc_ref"));
				qc.setQcWriter(rset.getString("qc_writer"));
				qc.setQnaRef(rset.getInt("q_ref"));
				list.add(qc);
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

	public ArrayList<QnaComment> selectReCommentList(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from qna_comment where q_ref = ? and qc_ref is not null order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setQcContent(rset.getString("qc_content"));
				qc.setQcDate(rset.getString("qc_date"));
				qc.setQcNo(rset.getInt("qc_no"));
				qc.setQcRef(rset.getInt("qc_ref"));
				qc.setQcWriter(rset.getString("qc_writer"));
				qc.setQnaRef(rset.getInt("q_ref"));
				list.add(qc);
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

	public int deleteQna(Connection conn, int qNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna where q_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int updateQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update qna set q_title = ?, q_content=?, filename = ?, filepath = ? where q_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getqTitle());
			pstmt.setString(2, q.getqContent());
			pstmt.setString(3, q.getFileName());
			pstmt.setString(4, q.getFilepath());
			pstmt.setInt(5, q.getqNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int insertQnaCommnet(Connection conn, QnaComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna_comment values(qc_no_seq.nextval, ?, ?, ?, ?, to_char(sysdate, 'yyyy-mm-dd'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qc.getQcWriter());
			pstmt.setString(2, qc.getQcContent());
			pstmt.setInt(3, qc.getQnaRef());
			
			//NcRef에 0을 넣어줬는데 insert될 때 references조건을 만족하지X
			//댓글의 경우 nc_no == 0에 해당하는 값이 없으므로 에러발생
			//댓글일 경우 null / 대댓글의경우 해당번호를 넣어줘야함
			if(qc.getQcRef() == 0) {
				pstmt.setString(4, null);
			}else {
				pstmt.setInt(4, qc.getQcRef());								
			} 
			//3항연산자
			//pstmt.setString(4, (nc.getNcRef()==0)?null:String.valueOf(nc.getNcRef()));
			
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

