package com.litbooks.basket.Service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.basket.dao.BasketDao;
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

	
}
