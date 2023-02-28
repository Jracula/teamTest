package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.book.dao.BookDao;
import com.litbooks.book.vo.Book;

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


	//책제목으로 검색
	public ArrayList<Book> selectBooksByTitle(String searchTitle, int onSale){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksByTitle(conn, searchTitle, onSale);
		JDBCTemplate.close(conn);
		return list;
	}


	//작가이름으로 검색
	public ArrayList<Book> selectBooksByWriter(String searchTitle, int onSale){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBooksByWriter(conn, searchTitle, onSale);
		JDBCTemplate.close(conn);
		return list;
	}

}
