package com.litbooks.book.service;

import java.sql.Connection;

import com.litbooks.book.dao.BookDao;
import com.litbooks.book.vo.Book;

import common.JDBCTemplate;

public class BookService {
	private BookDao dao;
	
	public BookService() {
		super();
		dao = new BookDao();
	}

	public Book selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		Book b = dao.selectOneBook(conn, bookNo);
		JDBCTemplate.close(conn);
		return b;
	}

}
