package com.litbooks.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.Recomm;

import common.JDBCTemplate;

public class BookDao {

	
	//bookDetail.jsp을 위한 정보를 넘겨주기 위해서 책 한 권에 대한 모든 정보들을 조회함  
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


	//시리즈물인 책들의 bookNo들을 ArrayList로 넘겨주기 위한 함수
	public ArrayList<Book> selectSeriesBooks(Connection conn, int book1st){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();

		String query = "SELECT * FROM BOOK WHERE BOOK_1ST=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, book1st);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int bookNo = rset.getInt("BOOK_NO");
				String bookTitle = rset.getString("BOOK_TITLE");
				String bookGenre = rset.getString("BOOK_GENRE");
				String writer = rset.getString("WRITER");
				String publisher = rset.getString("PUBLISHER");
				int bookPrice = rset.getInt("BOOK_PRICE");
				int discount = rset.getInt("DISCOUNT");
				int onSale = rset.getInt("ONSALE");
				String bookIntro = rset.getString("BOOK_INTRO");
				int bookEpi = rset.getInt("BOOK_EPI");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage);
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}


	//책제목으로 검색
	public ArrayList<Book> selectBooksByTitle(Connection conn, String searchTitle, int onSale){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();

		String query = "SELECT * FROM BOOK WHERE (BOOK_TITLE LIKE '%?%') AND (ONSALE=?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTitle);
			pstmt.setInt(2, onSale);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int bookNo = rset.getInt("BOOK_NO");
				String bookTitle = rset.getString("BOOK_TITLE");
				String bookGenre = rset.getString("BOOK_GENRE");
				String writer = rset.getString("WRITER");
				String publisher = rset.getString("PUBLISHER");
				int bookPrice = rset.getInt("BOOK_PRICE");
				int discount = rset.getInt("DISCOUNT");
				String bookIntro = rset.getString("BOOK_INTRO");
				int bookEpi = rset.getInt("BOOK_EPI");
				int book1st = rset.getInt("BOOK_1ST");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage);
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}


	//작가이름으로 검색
	public ArrayList<Book> selectBooksByWriter(Connection conn, String searchTitle, int onSale){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();

		String query = "SELECT * FROM BOOK WHERE (WRITER LIKE '%?%') AND (ONSALE=?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchTitle);
			pstmt.setInt(2, onSale);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int bookNo = rset.getInt("BOOK_NO");
				String bookTitle = rset.getString("BOOK_TITLE");
				String bookGenre = rset.getString("BOOK_GENRE");
				String writer = rset.getString("WRITER");
				String publisher = rset.getString("PUBLISHER");
				int bookPrice = rset.getInt("BOOK_PRICE");
				int discount = rset.getInt("DISCOUNT");
				String bookIntro = rset.getString("BOOK_INTRO");
				int bookEpi = rset.getInt("BOOK_EPI");
				int book1st = rset.getInt("BOOK_1ST");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage);
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}


	public int insertRecomm(Connection conn, com.litbooks.book.vo.Recomm rc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String qurey = "insert into recomm values(recomm_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		//댓글번호,책(게시글)번호,회원ID,댓글내용,날짜
		try {
			pstmt = conn.prepareStatement(qurey);
	        pstmt.setInt(1, rc.getBookNo());
	        pstmt.setString(2, rc.getMemberId());
	        pstmt.setString(3, rc.getRecommContent());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	

}
