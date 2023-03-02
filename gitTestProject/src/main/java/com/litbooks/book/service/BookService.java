package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.book.dao.BookDao;
import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.Recomm;

import common.JDBCTemplate;

public class BookService {
	private BookDao dao;
	
	public BookService() {
		super();
		dao = new BookDao();
	}

	//bookDetail.jsp을 위한 정보를 넘겨주기 위해서 책 한 권에 대한 모든 정보들을 조회함  
	public Book selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		Book b = dao.selectOneBook(conn, bookNo);
		JDBCTemplate.close(conn);
		return b;
	}


	//시리즈물인 책들의 bookNo들을 ArrayList로 넘겨주기 위한 함수
	public ArrayList<Book> selectSeriesBooks(int book1st){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectSeriesBooks(conn, book1st);
		JDBCTemplate.close(conn);
		return list;
	}


	//책 1권 신규 등록
	public int insertBook(Book b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBook(conn, b);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
	//가장 마지막으로 등록된 책의 bookNo를 알아오기 위한 함수  
	public int getLatestBookNo() {
		Connection conn = JDBCTemplate.getConnection();
		int bookNo = dao.getLatestBookNo(conn);
		JDBCTemplate.close(conn);
		return bookNo;
	}


	//신규 등록 도서의 커버이미지가 있었을 경우, 이미지 파일명 재설정 
	public void updateBookImage(String newFilePath, int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBookImage(conn, newFilePath, bookNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return;
	}
	
	
	//책제목으로 검색
	public ArrayList<Book> selectBooksByTitle(String searchTitle, int onSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksByTitle(conn, searchTitle, onSale, selectedGenre);
		JDBCTemplate.close(conn);
		return list;
	}


	//작가이름으로 검색
	public ArrayList<Book> selectBooksByWriter(String searchWriter, int onSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksByWriter(conn, searchWriter, onSale, selectedGenre);
		JDBCTemplate.close(conn);
		return list;
	}
	
	
	//댓글 입력
	public int insertRecomm(Recomm rc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertRecomm(conn,rc);
		
		return result;
	}

	

}
