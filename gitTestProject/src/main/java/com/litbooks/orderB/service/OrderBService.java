package com.litbooks.orderB.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.orderB.dao.OrderBDao;
import com.litbooks.orderB.vo.OrderB;

import common.JDBCTemplate;

public class OrderBService {
	private OrderBDao dao;

	public OrderBService() {
		super();
		dao = new OrderBDao();
	}

	/*
	// 어떤 회원이 주문을 했는지 조회
	public OrderB selectOneOrder(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderB o = dao.selectOneOrder(conn, memberNo);
		JDBCTemplate.close(conn);
		return o;
	}
	*/
	
	// 전체 주문내역 조회
	public ArrayList<OrderB> selectAllOrder(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderB> list = dao.selectAllOrder(conn, memberNo);
		JDBCTemplate.close(conn);
		return list;
	}


	
	
	
}
