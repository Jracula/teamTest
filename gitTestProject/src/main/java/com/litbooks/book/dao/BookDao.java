package com.litbooks.book.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.basket.vo.Basket;
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
				int bookScore = rset.getInt("BOOK_SCORE");
				b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage, bookScore);
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
				int bookScore = rset.getInt("BOOK_SCORE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage, bookScore);
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


	//GENRE 테이블 전체를 읽어오는 함수
	public ArrayList<String> selectGenre(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<String> list = new ArrayList<String>();

		String query = "SELECT GENRE_KOR FROM GENRE";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				String genreKor = rset.getString("GENRE_KOR");
				list.add(genreKor);
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


	//책 1권 신규 등록
	public int insertBook(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO BOOK VALUES (BOOK_SEQ.NEXTVAL, ?, ?, NVL(?, '작자미상'), NVL(?, '출판사불명'), ?, ?, DEFAULT, ?, ?, CASE WHEN ?=0 THEN BOOK_SEQ.NEXTVAL ELSE ? END, ?, '', 0)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBookTitle());
			pstmt.setString(2, b.getBookGenre());
			pstmt.setString(3, b.getWriter());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getBookPrice());
			pstmt.setInt(6, b.getDiscount());
			//판매중으로만 등록하므로 ON_SALE은 DEFAULT
			pstmt.setString(7, b.getBookIntro());
			pstmt.setInt(8, b.getBookEpi());
			pstmt.setInt(9, b.getBook1st());
			pstmt.setInt(10, b.getBook1st());	//book1st에 0이 아닌 특정 값을 줬다면, bookNo가 그 값인 책이 1권으로 지정됨
			pstmt.setInt(11, b.getNonFee());
			//bookImage 파일명은 다시 명명할 것이므로 지금 정해줄 필요 없음
			//신규이므로 평점은 DEFAULT인 0
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}



	//책 1권 정보 수정. 이미지는 여기서 수정 안 함
	public int updateBook(Connection conn, Book b) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE BOOK SET BOOK_TITLE = ?, BOOK_GENRE = ?, WRITER = NVL(?, '작자미상'), PUBLISHER = NVL(?, '출판사불명'), BOOK_PRICE = ?, DISCOUNT = ?, ONSALE = ?, BOOK_INTRO = ?, BOOK_EPI = ?, BOOK_1ST = ?, NONFEE = ? WHERE BOOK_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBookTitle());
			pstmt.setString(2, b.getBookGenre());
			pstmt.setString(3, b.getWriter());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getBookPrice());
			pstmt.setInt(6, b.getDiscount());
			pstmt.setInt(7, b.getOnSale());
			pstmt.setString(8, b.getBookIntro());
			pstmt.setInt(9, b.getBookEpi());
			pstmt.setInt(10, b.getBook1st());
			pstmt.setInt(11, b.getNonFee());
			pstmt.setInt(12, b.getBookNo());
			//bookImage 파일명은 다시 명명할 것이므로 지금 정해줄 필요 없음
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	//가장 마지막으로 등록된 책의 bookNo를 알아오기 위한 함수  
	public int getLatestBookNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int bookNo=0;

		String query = "SELECT BOOK_NO FROM (SELECT BOOK_NO FROM BOOK ORDER BY BOOK_NO DESC) WHERE ROWNUM=1";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				bookNo = rset.getInt("BOOK_NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return bookNo;
	}

	
	//신규 등록 도서의 이미지가 있었을 경우, 이미지 파일명 재설정 
	public int updateBookImage(Connection conn, String newFilePath, int bookNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "UPDATE BOOK SET BOOK_IMAGE=? WHERE BOOK_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newFilePath);
			pstmt.setInt(2, bookNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	//header의 검색바에서 검색
	public ArrayList<Book> selectBooksInHeader(Connection conn, String searchTitle, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();
		String[] titles = searchTitle.split(" ");

		String queryHead = "SELECT * FROM (SELECT ROWNUM AS RN, RESULT. * FROM (SELECT * FROM BOOK WHERE ((BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryTail = "))RESULT) WHERE RN BETWEEN 1 AND ?";
		String query = queryHead+queryHead2+queryTail;	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			pstmt.setInt(titles.length+1, numPerPage);
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
				int book1st = rset.getInt("BOOK_1ST");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				int bookScore = rset.getInt("BOOK_SCORE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage, bookScore);
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


	//상세 조건으로 책 검색
	public ArrayList<Book> selectBooksByWish(Connection conn, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[], int start, int end){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();
		String[] titles = searchTitle.split(" ");
		String[] writers = searchWriter.split(" ");

		String queryHead = "SELECT * FROM (SELECT ROWNUM AS RN, RESULT. * FROM (SELECT * FROM BOOK WHERE ((BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryHead3 = ") AND ((WRITER LIKE ?)";
		for(int i=1; i<writers.length; i++) {
			queryHead3 += " AND (WRITER LIKE ?)";
		}
		String queryBody = "";
		if(selectedGenre!=null) {	//체크박스로 장르들을 선택한 것이 1개 이상이면
			queryBody=") AND (BOOK_GENRE IN (?";	//WHERE에 BOOK_GENRE도 걸어줌
			for(int i=1; i<selectedGenre.length; i++) {
				queryBody += ", ?";
			}
			queryBody += ")";
		}
		String queryTail = ")";
		String queryOnsale ="";
		if(onlyOnSale==1) {	//판매중지 제외에 체크되었으면, WHERE에 ONSALE=1도 추가
			queryOnsale =" AND (ONSALE=1)";
		}
		String query = queryHead+queryHead2+queryHead3+queryBody+queryTail+queryOnsale+")RESULT) WHERE RN BETWEEN ? AND ?";	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			for(int i=0; i<writers.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+titles.length+1, '%'+writers[i]+'%');
			}
			if(selectedGenre!=null) {
				for(int i=0; i<selectedGenre.length; i++) {
					pstmt.setString(i+titles.length+writers.length+1, selectedGenre[i]);
				}
				pstmt.setInt(selectedGenre.length+titles.length+writers.length+1, start);
				pstmt.setInt(selectedGenre.length+titles.length+writers.length+2, end);
			} else {
				pstmt.setInt(titles.length+writers.length+1, start);
				pstmt.setInt(titles.length+writers.length+2, end);
			}
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
				int book1st = rset.getInt("BOOK_1ST");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				int bookScore = rset.getInt("BOOK_SCORE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage, bookScore);
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


	//검색결과물의 수 - header.jsp에서 검색
	public int selectSearchResultCount(Connection conn, String searchTitle) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		String[] titles = searchTitle.split(" ");
		
		String queryHead = "SELECT COUNT(*) AS CNT FROM (SELECT * FROM BOOK WHERE (BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryTail = ")";
		String query = queryHead+queryHead2+queryTail;	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}


	//검색결과물의 수 - 상세 검색
	public int selectSearchResultCount(Connection conn, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		String[] titles = searchTitle.split(" ");
		String[] writers = searchWriter.split(" ");

		String queryHead = "SELECT COUNT(*) AS CNT FROM (SELECT * FROM BOOK WHERE ((BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryHead3 = ") AND ((WRITER LIKE ?)";
		for(int i=1; i<writers.length; i++) {
			queryHead3 += " AND (WRITER LIKE ?)";
		}
		String queryBody = "";
		if(selectedGenre!=null) {	//체크박스로 장르들을 선택한 것이 1개 이상이면
			queryBody=") AND (BOOK_GENRE IN (?";	//WHERE에 BOOK_GENRE도 걸어줌
			for(int i=1; i<selectedGenre.length; i++) {
				queryBody += ", ?";
			}
			queryBody += ")";
		}
		String queryTail = ")";
		String queryOnsale ="";
		if(onlyOnSale==1) {	//판매중지 제외에 체크되었으면, WHERE에 ONSALE=1도 추가
			queryOnsale =" AND (ONSALE=1)";
		}
		String query = queryHead+queryHead2+queryHead3+queryBody+queryTail+queryOnsale+")";	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			for(int i=0; i<writers.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+titles.length+1, '%'+writers[i]+'%');
			}
			if(selectedGenre!=null) {
				for(int i=0; i<selectedGenre.length; i++) {
					pstmt.setString(i+titles.length+writers.length+1, selectedGenre[i]);
				}
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	
	//1권인 책만 상세 조건으로 책 검색
	public ArrayList<Book> selectBook1stByWish(Connection conn, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[], int start, int end){
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Book> list = new ArrayList<Book>();
		String[] titles = searchTitle.split(" ");
		String[] writers = searchWriter.split(" ");

		String queryHead = "SELECT * FROM (SELECT ROWNUM AS RN, RESULT. * FROM (SELECT * FROM BOOK WHERE (BOOK_EPI=1) AND ((BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryHead3 = ") AND ((WRITER LIKE ?)";
		for(int i=1; i<writers.length; i++) {
			queryHead3 += " AND (WRITER LIKE ?)";
		}
		String queryBody = "";
		if(selectedGenre!=null) {	//체크박스로 장르들을 선택한 것이 1개 이상이면
			queryBody=") AND (BOOK_GENRE IN (?";	//WHERE에 BOOK_GENRE도 걸어줌
			for(int i=1; i<selectedGenre.length; i++) {
				queryBody += ", ?";
			}
			queryBody += ")";
		}
		String queryTail = ")";
		String queryOnsale ="";
		if(onlyOnSale==1) {	//판매중지 제외에 체크되었으면, WHERE에 ONSALE=1도 추가
			queryOnsale =" AND (ONSALE=1)";
		}
		String query = queryHead+queryHead2+queryHead3+queryBody+queryTail+queryOnsale+")RESULT) WHERE RN BETWEEN ? AND ?";	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			for(int i=0; i<writers.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+titles.length+1, '%'+writers[i]+'%');
			}
			if(selectedGenre!=null) {
				for(int i=0; i<selectedGenre.length; i++) {
					pstmt.setString(i+titles.length+writers.length+1, selectedGenre[i]);
				}
				pstmt.setInt(selectedGenre.length+titles.length+writers.length+1, start);
				pstmt.setInt(selectedGenre.length+titles.length+writers.length+2, end);
			} else {
				pstmt.setInt(titles.length+writers.length+1, start);
				pstmt.setInt(titles.length+writers.length+2, end);
			}
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
				int book1st = rset.getInt("BOOK_1ST");
				int nonFee = rset.getInt("NONFEE");
				String bookImage = rset.getString("BOOK_IMAGE");
				int bookScore = rset.getInt("BOOK_SCORE");
				Book b = new Book(bookNo, bookTitle, bookGenre, writer, publisher, bookPrice, discount, onSale, bookIntro, bookEpi, book1st, nonFee, bookImage, bookScore);
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


	//검색결과물의 수 - 1권인 책만 상세 검색
	public int selectSearchResult1stCount(Connection conn, String searchTitle, String searchWriter, int onlyOnSale, String selectedGenre[]) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalCount = 0;
		String[] titles = searchTitle.split(" ");
		String[] writers = searchWriter.split(" ");

		String queryHead = "SELECT COUNT(*) AS CNT FROM (SELECT * FROM BOOK WHERE (BOOK_EPI=1) AND ((BOOK_TITLE LIKE ?)";
		String queryHead2 = "";
		for(int i=1; i<titles.length; i++) {
			queryHead2 += " AND (BOOK_TITLE LIKE ?)";
		}
		String queryHead3 = ") AND ((WRITER LIKE ?)";
		for(int i=1; i<writers.length; i++) {
			queryHead3 += " AND (WRITER LIKE ?)";
		}
		String queryBody = "";
		if(selectedGenre!=null) {	//체크박스로 장르들을 선택한 것이 1개 이상이면
			queryBody=") AND (BOOK_GENRE IN (?";	//WHERE에 BOOK_GENRE도 걸어줌
			for(int i=1; i<selectedGenre.length; i++) {
				queryBody += ", ?";
			}
			queryBody += ")";
		}
		String queryTail = ")";
		String queryOnsale ="";
		if(onlyOnSale==1) {	//판매중지 제외에 체크되었으면, WHERE에 ONSALE=1도 추가
			queryOnsale =" AND (ONSALE=1)";
		}
		String query = queryHead+queryHead2+queryHead3+queryBody+queryTail+queryOnsale+")";	//완성된 query문

		try {
			pstmt = conn.prepareStatement(query);
			for(int i=0; i<titles.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+1, '%'+titles[i]+'%');
			}
			for(int i=0; i<writers.length; i++) {	//키워드를 포함해야 하는 조건이므로 앞뒤에 %
				pstmt.setString(i+titles.length+1, '%'+writers[i]+'%');
			}
			if(selectedGenre!=null) {
				for(int i=0; i<selectedGenre.length; i++) {
					pstmt.setString(i+titles.length+writers.length+1, selectedGenre[i]);
				}
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	
	// 장바구니 조회를 위한 책 테이블 전체조회
	public ArrayList<Book> selectAllBook(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<>();
		
		String query = "select * from book where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Book b = new Book();
				b.setBookNo(rset.getInt("book_no"));
				b.setBookTitle(rset.getString("book_title"));
				b.setBookGenre(rset.getString("book_genre"));
				b.setWriter(rset.getString("writer"));
				b.setPublisher(rset.getString("publisher"));
				b.setBookPrice(rset.getInt("book_price"));
				b.setDiscount(rset.getInt("discount"));
				b.setOnSale(rset.getInt("onsale"));
				b.setBookIntro(rset.getString("book_intro"));
				b.setBookEpi(rset.getInt("book_epi"));
				b.setBook1st(rset.getInt("book_1st"));
				b.setNonFee(rset.getInt("nonfee"));
				b.setBookImage(rset.getString("book_image"));
				b.setBookScore(rset.getInt("book_score"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	// 장바구니 목록 조회
	public ArrayList<Basket> selectAllBasket(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Basket> list = new ArrayList<>();
		
		String query = "select * from basket where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Basket bask = new Basket();
				bask.setBasketNo(rset.getInt("basket_no"));
				bask.setMemberNo(rset.getInt("member_no"));
				bask.setBookNo(rset.getInt("book_no"));
				list.add(bask);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

		//댓글 입력
		public int insertRecomm(Connection conn, Recomm rc) {
			PreparedStatement pstmt = null;
			int result = 0;
			String qurey = "insert into recomm values(recomm_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
			//댓글번호,책(게시글)번호,회원ID,댓글내용,날짜,대댓글참조번호,평점
			try {
				pstmt = conn.prepareStatement(qurey);
		        pstmt.setInt(1, rc.getBookRef());
		        pstmt.setString(2, rc.getRcWriter());
		        pstmt.setString(3, rc.getRecommContent());
		        pstmt.setString(4, (rc.getRecommRef()==0)?null:String.valueOf(rc.getRecommRef()));
		        pstmt.setInt(5, rc.getRating());
		        
		        result= pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			
			return result;
		}
		
		//댓글 전체 출력
		public ArrayList<Recomm> selectRecomm(Connection conn, int bookNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Recomm> list
			=new ArrayList<Recomm>();
			String query = "select * from recomm where BOOK_REF=? and RECOMM_REF is null order by 1";
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
					rc.setBookRef(rset.getInt("book_ref"));
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

		//대댓글 전체 출력
			public ArrayList<Recomm> selectRerecomm(Connection conn, int bookNo) {
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				ArrayList<Recomm> list
				=new ArrayList<Recomm>();
				String query = "select * from recomm where BOOK_REF=? and RECOMM_REF is not null order by 1";
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
						rc.setBookRef(rset.getInt("book_ref"));
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




			public int updateRecomm(Connection conn, Recomm rc) {
				PreparedStatement pstmt = null;
				int result = 0;
				String query = "update recomm set recomm_content=? where recomm_no=?";
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, rc.getRecommContent());
					pstmt.setInt(2, rc.getRecommNo());
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}




			public int deleteRecomm(Connection conn, int recommNo) {
				PreparedStatement pstmt = null;
				int result = 0;
				String query = "delete from recomm where recomm_no=?";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, recommNo);
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JDBCTemplate.close(conn);
				}
				return result;
			}

}
