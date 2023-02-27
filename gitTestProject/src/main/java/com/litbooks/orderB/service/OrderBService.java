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

	// 전체 주문 조회
	public ArrayList<OrderB> selectAllOrder() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderB> list = dao.selectAllOrder(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
	
	
}
