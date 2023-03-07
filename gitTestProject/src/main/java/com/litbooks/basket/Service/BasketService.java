package com.litbooks.basket.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.litbooks.basket.dao.BasketDao;
import com.litbooks.basket.vo.Basket;
import com.litbooks.book.vo.Book;

import common.JDBCTemplate;

public class BasketService {
	private BasketDao dao;

	public BasketService() {
		super();
		dao = new BasketDao();
	}

	//장바구니에 넣어도 되는지 검사하기
	public String selectBasket(int bookNo, int memberNo){
		Connection conn = JDBCTemplate.getConnection();
		String message = "noError";
		int basketNo = 0;
		basketNo = dao.selectOneBasket(conn, bookNo, memberNo);
		ArrayList baskets = dao.selectNumberOfBasket(conn, memberNo);
		JDBCTemplate.close(conn);
		if (basketNo!=0){
			message = "이미 회원님의 장바구니에 있는 책입니다.";
		} else if (baskets.size() >= 10) {
			message = "장바구니가 가득 찼습니다.";
		}
		return message;
	}

	//장바구니에 1개 넣기
	public int insertOneBasket(int bookNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertOneBasket(conn, bookNo, memberNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//특정 회원이 장바구니에 넣어놓은 책번호들을 ArrayList로 받는 함수. 따라서 ArrayList.size() == 장바구니에 담은 수
	public ArrayList selectNumberOfBasket(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList list = dao.selectNumberOfBasket(conn, memberNo);
		JDBCTemplate.close(conn);
		return list;
	}

	// 장바구니에서 책번호 조회
	public ArrayList<Basket> selectBookNumber(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Basket> list = dao.selectBookNumber(conn, memberNo);
		JDBCTemplate.close(conn);
		return list;
	}
	
	// 장바구니에서 회원이 담은 책 제목, 책 가격 Book 테이블 JOIN
	public ArrayList<Book> selectBookDetail(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> bask = dao.selectBookDetail(conn, memberNo);
		JDBCTemplate.close(conn);
		return bask;
	}

	// 장바구니 일괄체크 후 삭제
	public boolean cartDelete(String no) {
		Connection conn = JDBCTemplate.getConnection();
		
		StringTokenizer sT1 = new StringTokenizer(no, "/");
		boolean result = true;
		
		while(sT1.hasMoreTokens()) {
			int bookNo = Integer.parseInt(sT1.nextToken());
			
			int changeResult = dao.cartDelete(conn, bookNo);
			if(changeResult == 0) {
				result = false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	
}
