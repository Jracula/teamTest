package com.litbooks.orderB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.orderB.vo.OrderB;

import common.JDBCTemplate;

public class OrderBDao {

	/*
	// 어떤 회원이 주문을 했는지 조회
	public OrderB selectOneOrder(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderB o = null;
		
		String query = "select * from order_b where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				o.setStatus(rset.getString("status"));
				o.setMemberNo(rset.getInt("member_no"));
				o.setBookNo(rset.getInt("book_no"));
				o.setBookNo2(rset.getInt("book_no2"));
				o.setBookNo3(rset.getInt("book_no3"));
				o.setBookNo4(rset.getInt("book_no4"));
				o.setBookNo5(rset.getInt("book_no5"));
				o.setBookNo6(rset.getInt("book_no6"));
				o.setBookNo7(rset.getInt("book_no7"));
				o.setBookNo8(rset.getInt("book_no8"));
				o.setBookNo9(rset.getInt("book_no9"));
				o.setBookNo10(rset.getInt("book_no10"));
				o.setBookPrice(rset.getInt("book_price"));
				o.setBookPrice2(rset.getInt("book_price2"));
				o.setBookPrice3(rset.getInt("book_price3"));
				o.setBookPrice4(rset.getInt("book_price4"));
				o.setBookPrice5(rset.getInt("book_price5"));
				o.setBookPrice6(rset.getInt("book_price6"));
				o.setBookPrice7(rset.getInt("book_price7"));
				o.setBookPrice8(rset.getInt("book_price8"));
				o.setBookPrice9(rset.getInt("book_price9"));
				o.setBookPrice10(rset.getInt("book_price10"));
				o.setOrderPrice(rset.getInt("order_price"));
				o.setOrderPay(rset.getString("order_pay"));
				o.setOrderRegDate(rset.getString("order_reg_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return o;
	}
	*/
	
	// 전체주문 조회하기
	public ArrayList<OrderB> selectAllOrder(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();
		
		//String query = "select * from order_b where member_no=?";
		String query = "select order_no, order_reg_date, book_title, order_price, order_pay from book b left join order_b o on (b.book_no = o.order_no) where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderB o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				System.out.println(o.getOrderNo());
				o.setStatus(rset.getString("status"));
				System.out.println(o.getStatus());
				o.setMemberNo(rset.getInt("member_no"));
				System.out.println(o.getMemberNo());
				o.setBookNo(rset.getInt("book_no"));
				System.out.println(o.getBookNo());
				o.setBookNo2(rset.getInt("book_no2"));
				System.out.println(o.getBookNo2());
				o.setBookNo3(rset.getInt("book_no3"));
				System.out.println(o.getBookNo3());
				o.setBookNo4(rset.getInt("book_no4"));
				System.out.println(o.getBookNo4());
				o.setBookNo5(rset.getInt("book_no5"));
				System.out.println(o.getBookNo5());
				o.setBookNo6(rset.getInt("book_no6"));
				System.out.println(o.getBookNo6());
				o.setBookNo7(rset.getInt("book_no7"));
				System.out.println(o.getBookNo7());
				o.setBookNo8(rset.getInt("book_no8"));
				System.out.println(o.getBookNo8());
				o.setBookNo9(rset.getInt("book_no9"));
				System.out.println(o.getBookNo9());
				o.setBookNo10(rset.getInt("book_no10"));
				System.out.println(o.getBookNo10());
				o.setBookPrice(rset.getInt("book_price"));
				System.out.println(o.getBookPrice());
				o.setBookPrice2(rset.getInt("book_price2"));
				System.out.println(o.getBookPrice2());
				o.setBookPrice3(rset.getInt("book_price3"));
				System.out.println(o.getBookPrice3());
				o.setBookPrice4(rset.getInt("book_price4"));
				System.out.println(o.getBookPrice4());
				o.setBookPrice5(rset.getInt("book_price5"));
				System.out.println(o.getBookPrice5());
				o.setBookPrice6(rset.getInt("book_price6"));
				System.out.println(o.getBookPrice6());
				o.setBookPrice7(rset.getInt("book_price7"));
				System.out.println(o.getBookPrice7());
				o.setBookPrice8(rset.getInt("book_price8"));
				System.out.println(o.getBookPrice8());
				o.setBookPrice9(rset.getInt("book_price9"));
				System.out.println(o.getBookPrice9());
				o.setBookPrice10(rset.getInt("book_price10"));
				System.out.println(o.getBookPrice10());
				o.setOrderPrice(rset.getInt("order_price"));
				System.out.println(o.getOrderPrice());
				o.setOrderPay(rset.getString("order_pay"));
				System.out.println(o.getOrderPay());
				o.setOrderRegDate(rset.getString("order_reg_date"));
				System.out.println(o.getOrderRegDate());
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
