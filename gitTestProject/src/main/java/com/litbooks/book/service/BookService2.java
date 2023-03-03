
package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.book.dao.BookDao;
import com.litbooks.book.dao.BookDao2;
import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.BookView;
import com.litbooks.book.vo.Recomm;

import common.JDBCTemplate;

public class BookService2 {
	private BookDao2 dao;
	
	public BookService2() {
		super();
		dao = new BookDao2();
	}

	//bookDetail.jsp을 위한 정보를 넘겨주기 위해서 책 한 권에 대한 모든 정보들을 조회함  
	
	public BookView selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		if(result >0) {
	    	  JDBCTemplate.commit(conn);
	    	  Book b = dao.selectOneBook(conn, bookNo);
	    	  //1.일반댓글 조회
	    	  ArrayList<Recomm> recommList
	    	  =dao.selectRecomm(conn,bookNo);
	    	  //2.대댓글 조회
	    	  ArrayList<Recomm> rerecommList
	    	  =dao.selectRerecomm(conn,bookNo);
	    	  BookView bv =new BookView(b, recommList, rerecommList);
	    	  JDBCTemplate.close(conn);
	    	  return bv;
	      }else {
	    	  JDBCTemplate.rollback(conn);
	    	  JDBCTemplate.close(conn);
	    	  return null;
	      }
	}
	
	
	
	

}
