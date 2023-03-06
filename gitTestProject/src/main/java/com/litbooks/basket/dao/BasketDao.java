package com.litbooks.basket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.basket.vo.Basket;
import com.litbooks.book.vo.Book;

import common.JDBCTemplate;

public class BasketDao {

	
	//장바구니에 이미 동일한 책이 있는지 확인하기 위한 함수
	public int selectOneBasket(Connection conn, int bookNo, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int basketNo = 0;

		String query = "SELECT BASKET_NO FROM BASKET WHERE BOOK_NO=? AND MEMBER_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			pstmt.setInt(2, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				basketNo = rset.getInt("BASKET_NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return basketNo;	//장바구니에 이미 있었다면, 0이 아닌 값이 반환됨
	}


	//특정 회원이 장바구니에 넣어놓은 책번호들을 ArrayList로 받는 함수. 따라서 ArrayList.size() == 장바구니에 담은 수
	public ArrayList selectNumberOfBasket(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList baskets = new ArrayList();

		String query = "SELECT BASKET_NO FROM BASKET WHERE MEMBER_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int basketNo = rset.getInt("BASKET_NO");
				baskets.add(basketNo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return baskets;	//baskets의 크기 == 장바구니에 담은 수
	}

	//장바구니에 1개 넣기
	public int insertOneBasket(Connection conn, int bookNo, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "INSERT INTO BASKET VALUES (BASKET_SEQ.NEXTVAL, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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

	// 장바구니에서 책번호 조회
	public ArrayList<Basket> selectBookNumber(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Basket> baskets = new ArrayList<>();

		String query = "SELECT BASKET_NO, BOOK_NO FROM BASKET WHERE MEMBER_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Basket ba = new Basket();
				ba.setBasketNo(rset.getInt("BASKET_NO"));
				ba.setBookNo(rset.getInt("BOOK_NO"));				
				baskets.add(ba);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return baskets;	//baskets의 크기 == 장바구니에 담은 수
	}

	// 장바구니에서 회원이 담은 책 제목, 책 가격 Book 테이블 JOIN
	public ArrayList<Book> selectBookDetail(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> bask = new ArrayList<>();
		
		String query = "select b.book_no, b.book_title, b.book_price from basket ba left join book b on (b.book_no = ba.book_no) where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book ba = new Book();
				ba.setBookNo(rset.getInt("book_no"));
				ba.setBookTitle(rset.getString("book_title"));
				ba.setBookPrice(rset.getInt("book_price"));
				bask.add(ba);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return bask;
	}

	// 장바구니 일괄체크 후 삭제
	public int cartDelete(Connection conn, int bookNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from basket where book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			JDBCTemplate.close(pstmt);
		}
		return result;
	}



}
