package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.basket.vo.Basket;
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



	//GENRE 테이블 전체를 읽어오는 함수
	public ArrayList<String> selectGenre(){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> list = dao.selectGenre(conn);
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


	//header의 검색바에서 검색
	public ArrayList<Book> selectBooksInHeader(String searchKeyword){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksInHeader(conn, searchKeyword);
		JDBCTemplate.close(conn);
		return list;
	}


	//상세 조건으로 책 검색
	public ArrayList<Book> selectBooksByWish(String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksByWish(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//댓글 입력
	public int insertRecomm(Recomm rc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertRecomm(conn,rc);
		
		return result;
	}

	// 장바구니 조회를 위한 책 테이블 전체조회
	public ArrayList<Book> selectAllBook(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Basket> list = dao.selectCart(conn, memberNo);
		
		
		ArrayList<Book> list = dao.selectAllBook(conn, memberNo);
		JDBCTemplate.close(conn);
		return list;
	}

	

}
