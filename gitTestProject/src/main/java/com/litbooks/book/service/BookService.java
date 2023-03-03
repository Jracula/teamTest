package com.litbooks.book.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.basket.vo.Basket;
import com.litbooks.book.dao.BookDao;
import com.litbooks.book.vo.Book;
import com.litbooks.book.vo.Recomm;
import com.litbooks.book.vo.SearchResultPage;

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
	public SearchResultPage selectBooksInHeader(String searchTitle){
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 30;	//페이지당 검색결과물 수
		ArrayList<Book> list = dao.selectBooksInHeader(conn, searchTitle, numPerPage);
		// 최대 페이지 수
		int totalCount = dao.selectSearchResultCount(conn, searchTitle);
		int maxPage = 0;
		if(totalCount%numPerPage == 0) {
			maxPage = totalCount/numPerPage;
		} else {
			maxPage = totalCount/numPerPage + 1;
		}
		int reqPage=1;
		// 페이지 이동 버튼 표시 수
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		// 페이지 네비게이션
				String pageNavi = "<ul class='pagination'>";
				if(pageNo != 1) {
					pageNavi += "<li>";
					pageNavi += "<span class='page-item' style='cursor: pointer;'>";
					pageNavi += "<span class='reqPage' style='display: none;'>";
					pageNavi += pageNo-1;
					pageNavi += "</span>";
					pageNavi += "<span class='material-icons'>chevron_left</span>";
					pageNavi += "</span></li>";
				}
				
				for(int i=0; i<pageNaviSize; i++) {
					if(pageNo == reqPage) {
						pageNavi += "<li>";
						pageNavi += "<span class='page-item' style='cursor: pointer;'>";
						pageNavi += "<span style='display: none;'>";
						pageNavi += pageNo;
						pageNavi += "</span>";
						pageNavi += pageNo;
						pageNavi += "</span></li>";
					}else {
						pageNavi += "<li>";
						pageNavi += "<span class='page-item' style='cursor: pointer;'>";
						pageNavi += "<span style='display: none;'>";
						pageNavi += pageNo;
						pageNavi += "</span>";
						pageNavi += pageNo;
						pageNavi += "</span></li>";
					}
					pageNo++;
					if(pageNo > maxPage) {
						break;
					}
				}

				if(pageNo <= maxPage) {
					pageNavi += "<li>";
					pageNavi += "<span class='page-item' style='cursor: pointer;'>";
					pageNavi += "<span style='display: none;'>";
					pageNavi += pageNo;
					pageNavi += "</span>";
					pageNavi += "<span class='material-icons'>chevron_right</span>";
					pageNavi += "</span></li>";
				}
				
				pageNavi += "</ul>";
		
		SearchResultPage bsr = new SearchResultPage(list, pageNavi, 1, numPerPage, 1, totalCount);
		
		JDBCTemplate.close(conn);
		return bsr;
	}


	//상세 조건으로 책 검색
	public SearchResultPage selectBooksByWish(int reqPage, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 30;	//페이지당 검색결과물 수
		int end = numPerPage*reqPage;
		int start = numPerPage*(reqPage-1)+1;
		ArrayList<Book> list = dao.selectBooksByWish(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre, start, end);
		// 최대 페이지 수
		int totalCount = dao.selectSearchResultCount(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre);
		int maxPage = 0;
		if(totalCount%numPerPage == 0) {
			maxPage = totalCount/numPerPage;
		} else {
			maxPage = totalCount/numPerPage + 1;
		}
		
		// 페이지 이동 버튼 표시 수
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		// 페이지 네비게이션
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<span class='page-item' style='cursor: pointer;'>";
			pageNavi += "<span class='reqPage' style='display: none;'>";
			pageNavi += pageNo-1;
			pageNavi += "</span>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</span></li>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<span class='page-item' style='cursor: pointer;'>";
				pageNavi += "<span style='display: none;'>";
				pageNavi += pageNo;
				pageNavi += "</span>";
				pageNavi += pageNo;
				pageNavi += "</span></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<span class='page-item' style='cursor: pointer;'>";
				pageNavi += "<span style='display: none;'>";
				pageNavi += pageNo;
				pageNavi += "</span>";
				pageNavi += pageNo;
				pageNavi += "</span></li>";
			}
			pageNo++;
			if(pageNo > maxPage) {
				break;
			}
		}

		if(pageNo <= maxPage) {
			pageNavi += "<li>";
			pageNavi += "<span class='page-item' style='cursor: pointer;'>";
			pageNavi += "<span style='display: none;'>";
			pageNavi += pageNo;
			pageNavi += "</span>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</span></li>";
		}
		
		pageNavi += "</ul>";
		
		SearchResultPage bsr = new SearchResultPage(list, pageNavi, start, numPerPage, reqPage, totalCount);
		
		JDBCTemplate.close(conn);
		return bsr;
	}



	//1권인 책만 상세 조건으로 검색
	public SearchResultPage selectBook1stByWish(int reqPage, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]){
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 30;	//페이지당 검색결과물 수
		int end = numPerPage*reqPage;
		int start = numPerPage*(reqPage-1)+1;
		ArrayList<Book> list = dao.selectBook1stByWish(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre, start, end);
		// 최대 페이지 수
		int totalCount = dao.selectSearchResultCount(conn, searchTitle, searchWriter, onlyOnSale, selectedGenre);
		int maxPage = 0;
		if(totalCount%numPerPage == 0) {
			maxPage = totalCount/numPerPage;
		} else {
			maxPage = totalCount/numPerPage + 1;
		}
		
		// 페이지 이동 버튼 표시 수
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		// 페이지 네비게이션
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<span class='page-item' style='cursor: pointer;'>";
			pageNavi += "<span class='reqPage' style='display: none;'>";
			pageNavi += pageNo-1;
			pageNavi += "</span>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</span></li>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<span class='page-item' style='cursor: pointer;'>";
				pageNavi += "<span style='display: none;'>";
				pageNavi += pageNo;
				pageNavi += "</span>";
				pageNavi += pageNo;
				pageNavi += "</span></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<span class='page-item' style='cursor: pointer;'>";
				pageNavi += "<span style='display: none;'>";
				pageNavi += pageNo;
				pageNavi += "</span>";
				pageNavi += pageNo;
				pageNavi += "</span></li>";
			}
			pageNo++;
			if(pageNo > maxPage) {
				break;
			}
		}

		if(pageNo <= maxPage) {
			pageNavi += "<li>";
			pageNavi += "<span class='page-item' style='cursor: pointer;'>";
			pageNavi += "<span style='display: none;'>";
			pageNavi += pageNo;
			pageNavi += "</span>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</span></li>";
		}
		
		pageNavi += "</ul>";
		
		SearchResultPage bsr = new SearchResultPage(list, pageNavi, start, numPerPage, reqPage, totalCount);
		
		JDBCTemplate.close(conn);
		return bsr;
	}


	
	//댓글 입력
	public int insertRecomm(Recomm rc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertRecomm(conn,rc);
		
		return result;
	}

	

}
