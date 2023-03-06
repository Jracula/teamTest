package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.book.dao.BookDao;
import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.Recomm;
import com.litbooks.book.vo.BookView;

import common.JDBCTemplate;

public class BookService {
	private BookDao dao;
	
	public BookService() {
		super();
		dao = new BookDao();
	}


	//bookDtaile.jsp 댓글 부분까지 함께 출력하기 위한 정보를 조회함
	public BookView selectOneBook(int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		Book bn = dao.selectOneBook(conn, bookNo);
		if(bn != null) {
	    	  JDBCTemplate.commit(conn);
	    	  Book b = dao.selectOneBook(conn, bookNo);
	    	  //1.일반댓글 조회
	    	  ArrayList<Recomm> recommList
	    	  =dao.selectRecomm(conn, bookNo);
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
	
	//bookDetail.jsp을 위한 정보를 넘겨주기 위해서 책 한 권에 대한 모든 정보들을 조회함 
	public Book getBook(int bookNo) {
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



	//1권인 책만 상세 조건으로 검색
	public ArrayList<Book> selectBook1stByWish(String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = dao.selectBook1stByWish(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre);
		JDBCTemplate.close(conn);
		return list;
	}

	
	//댓글 입력
	public int insertRecomm(Recomm rc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertRecomm(conn,rc);
		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	//update comment
	public int UpdateRecomm(Recomm rc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateNotice(conn, rc );
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int deleteNoticeComment(int recommNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateNotice(conn, recommNo );
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	
	}



