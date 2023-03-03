package com.litbooks.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.Recomm;

import common.JDBCTemplate;

public class BookDao2 {

	
	public ArrayList<Recomm> selectRecomm(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Recomm> list
		=new ArrayList<Recomm>();
		String query = "select * from RECOMM where BOOK_REF=? and RECOMM_REF is null order by 1";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Recomm rc = new Recomm();
				rc.setRecommContent(rset.getString("recomm_content"));
				rc.setRecommDate(rset.getString("recomm_date"));
				rc.setRecommNo(rset.getInt("recomm_no"));
				rc.setRecommRef(rset.getInt("recomm_ref"));
				rc.setRcWriter(rset.getString("member_id"));
				rc.setBookNo(rset.getInt("book_ref"));
				list.add(rc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}


		public ArrayList<Recomm> selectRerecomm(Connection conn, int bookNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Recomm> list
			=new ArrayList<Recomm>();
			String query = "select * from RECOMM where BOOK_REF=? and RECOMM_REF is null order by 1";
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, bookNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Recomm rc = new Recomm();
					rc.setRecommContent(rset.getString("recomm_content"));
					rc.setRecommDate(rset.getString("recomm_date"));
					rc.setRecommNo(rset.getInt("recomm_no"));
					rc.setRecommRef(rset.getInt("recomm_ref"));
					rc.setRcWriter(rset.getString("member_id"));
					rc.setBookNo(rset.getInt("book_ref"));
					list.add(rc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
		}


		public Book selectOneBook(Connection conn, int bookNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Book b = null;
			String query = "SELECT * FROM BOOK WHERE BOOK_NO=?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bookNo);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					String bookTitle = rset.getString("BOOK_TITLE");
					String bookGenre = rset.getString("BOOK_GENRE");
					String writer = rset.getString("WRITER");
					String publisher = rset.getString("PUBLISHER");
					int bookPrice = rset.getInt("BOOK_PRICE");
					int discount = rset.getInt("DISCOUNT");
					int onSale = rset.getInt("ONSALE");
					String bookIntro = rset.getString("BOOK_INTRO");
					int bookEpi = rset.getInt("BOOK_EPI");
					int book1st = rset.getInt("BOOK_1ST");
					int nonFee = rset.getInt("NONFEE");
					String bookImage = rset.getString("BOOK_IMAGE");
					b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return b;
		}
	

}
