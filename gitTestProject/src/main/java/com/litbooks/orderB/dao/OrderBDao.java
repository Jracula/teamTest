package com.litbooks.orderB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.orderB.vo.OrderB;

import common.JDBCTemplate;

public class OrderBDao {

	public ArrayList<OrderB> selectAllOrder(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();
		
		String query = "select order_no, order_reg_date, book_title, order_price, order_pay from book b left join order_b o on (b.book_no = o.order_no)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderB o = new OrderB();
				int orderNo = rset.getInt("order_no");
				String orderRegDate = rset.getString("order_reg_date");
				String bookTitle = rset.getString("book_title");
				int orderPrice = rset.getInt("order_price");
				String orderPay = rset.getString("order_pay");
				list.add(o);
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
	
}
