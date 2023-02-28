package com.litbooks.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.litbooks.board.model.vo.Board;

import common.JDBCTemplate;

public class BoardDao {

	public ArrayList<Board> selectBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<Board>();
		
		String query = "select q_no, q_writer, q_title, q_reg_date, q_read_count from qna";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setqNo(rset.getInt("q_no"));
				b.setqReadCount(rset.getInt("q_read_count"));
				b.setqRegDate(rset.getString("q_reg_date"));
				b.setqTitle(rset.getString("q_title"));
				b.setqWriter(rset.getString("q_writer"));
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

}
