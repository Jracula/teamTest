package com.litbooks.orderB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.orderB.vo.OrderB;

import common.JDBCTemplate;

public class OrderBDao {

	
	// 전체주문 조회하기
	public ArrayList<OrderB> selectAllOrder(Connection conn, int memberNo, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();
		
		String query = "select * from(select rownum as rnum, n.* from (select order_no, order_reg_date, book_title, order_pay, order_price from book b left join order_b o on(b.book_no = o.order_no) where member_no=?)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderB o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				o.setBook_title(rset.getString("book_title"));
				o.setOrderPrice(rset.getInt("order_price"));;
				o.setOrderPay(rset.getString("order_pay"));
				o.setOrderRegDate(rset.getString("order_reg_date"));
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

	// 결제 게시물 수 조회
	public int selectOrderCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from order_B";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return totalCount;
	}

	// 어떤 회원이 결제했는지 조회
	public OrderB selectOrderNumber(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderB o = null;
		
		String query = "select m.member_no, b.book_no, b.book_price, o.order_price, o.order_reg_date, o.status from member m left join book b on (m.member_no = b.book_no) left join order_b o on (o.order_no = m.member_no) where m.member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OrderB();
				o.setMemberNo(rset.getInt("member_no"));
				System.out.println(o.getMemberNo());
				o.setBookNo(rset.getInt("book_no"));
				System.out.println(o.getBookNo());
				o.setBookPrice(rset.getInt("book_price"));
				o.setOrderPrice(rset.getInt("order_price"));
				o.setOrderRegDate(rset.getString("order_reg_date"));
				o.setStatus(rset.getString("status"));
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


	



	
}
