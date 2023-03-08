package com.litbooks.orderB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.book.vo.Book;
import com.litbooks.orderB.vo.OrderB;

import common.JDBCTemplate;

public class OrderBDao {

	// 전체주문 조회하기 (OrderList.do) // 수정필요
	public ArrayList<OrderB> selectAllOrder(Connection conn, int memberNo, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();

		//String query = "select * from(select rownum as rnum, n.* from (select order_no, order_reg_date, book_title, order_pay, b.book_price from book b left join order_b o on(b.book_no = o.book_no) where member_no=?)n) where rnum between ? and ?";
		String query = "select * from(select rownum as rnum, n.* from(select order_no, order_count(o.order_no) order_count, order_reg_date, book_title, order_pay, b.book_price from order_b o left join book b on(b.book_no = o.book_no) where member_no=?)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderB o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				o.setOrderCount(rset.getInt("order_count")-1);
				if(o.getOrderCount() != 0) {
					o.setBook_title(rset.getString("book_title") + " 외 " + o.getOrderCount() + " 권");
				}else {
					o.setBook_title(rset.getString("book_title"));
				}
				o.setOrderPrice(rset.getInt("book_price"));
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
	public int selectOrderCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;

		String query = "select count(*) as cnt from order_B WHERE MEMBER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
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
	public OrderB selectOrderNumber(Connection conn, int memberNo, int bookNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderB o = new OrderB();

		String query = "select m.member_no, b.book_no, b.book_price, b.publisher, b.book_title, o.order_price, o.order_reg_date, o.status from member m left join book b on (m.member_no = b.book_no) left join order_b o on (o.order_no = m.member_no) where m.member_no=? and b.book_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, bookNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				o = new OrderB();
				o.setMemberNo(rset.getInt("member_no"));
				o.setBookNo(rset.getInt("book_no"));
				o.setBookPrice(rset.getInt("book_price"));
				o.setPublisher(rset.getString("publisher"));
				o.setBook_title(rset.getString("book_title"));
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

	// (관리자페이지) 주문내역 전체조회 (adminOrderList.do)
	public ArrayList<OrderB> selectAdminList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();

		//String query = "select * from(select rownum as rnum, n.* from (select o.order_no, m.member_id, b.book_title, b.book_price, o.order_pay, o.order_reg_date, o.status from order_b o join book b on (b.book_no = o.book_no) join member m on (o.member_no = m.member_no))n) where rnum between ? and ?";
		
		String query = "select * from(select rownum as rnum, n.* from (select o.order_no, order_count(o.order_no) order_count ,m.member_id, b.book_title, b.book_price, o.order_pay, o.order_reg_date, o.status from order_b o join book b on (b.book_no = o.book_no) join member m on (o.member_no = m.member_no))n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderB o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				o.setMemberId(rset.getString("member_id"));
				o.setOrderCount(rset.getInt("order_count")-1);
				if(o.getOrderCount() != 0) {
					o.setBook_title(rset.getString("book_title") + " 외 " + o.getOrderCount() + " 권");
				}else {
					o.setBook_title(rset.getString("book_title"));
				}
				o.setStatus(rset.getString("status"));
				o.setBookPrice(rset.getInt("book_price"));
				o.setOrderPay(rset.getString("order_pay"));
				o.setOrderRegDate(rset.getString("order_reg_date"));
				list.add(o);
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

	// (관리자페이지) 결제방식 변경
	public int changePay(Connection conn, int orderNo, int orderPay) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update order_b set status=? where order_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderPay);
			pstmt.setInt(2, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 체크된 장바구니 책번호가 책테이블에 있는지 조회
	public Book selectBookBasketNo(Connection conn, int basketOneNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Book b = null;

		String query = "SELECT * FROM BOOK WHERE BOOK_NO=(select book_no from basket where basket_no=?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, basketOneNo);
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
				float bookScore = rset.getFloat("BOOK_SCORE");
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

	// 결제내역 insert
	public int insertOrder(Connection conn, int memberNo1, String[] bookNoArr, String[] bookPriceArr, int price,
			String query, String payMethod) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo1);
			pstmt.setInt(2, price);
			pstmt.setString(3, payMethod);
			int index = 4;
			for (int i = 0; i < bookNoArr.length; i++) {
				pstmt.setString(index++, bookNoArr[i]);
				pstmt.setString(index++, bookPriceArr[i]);
			}
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 장바구니 삭제
	public int deleteBasket(Connection conn, String basketNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "delete from basket where basket_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, basketNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 책 단권 구매
	public int insertPayOne(Connection conn, int memberNo, int bookNo, int bookPrice, String payMethod) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO ORDER_B VALUES(ORDER_B_SEQ.NEXTVAL, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 3)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, bookNo);
			pstmt.setInt(3, bookPrice);
			pstmt.setInt(4, bookPrice);
			pstmt.setString(5, payMethod);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 관리자페이지 결제내역 총 게시물 조회
	public int selectAdminCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from order_b";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
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

	// 관리자페이지 구매내역 조회
	public ArrayList<OrderB> selectAdminAllList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderB> list = new ArrayList<>();
		
		String query = "select o.order_no, m.member_id, b.book_title, b.book_price, o.order_pay, o.order_reg_date, o.status from order_b o join book b on (b.book_no = o.book_no) join member m on (o.member_no = m.member_no) order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OrderB o = new OrderB();
				o.setOrderNo(rset.getInt("order_no"));
				o.setMemberId(rset.getString("member_id"));
				o.setOrderRegDate(rset.getString("order_reg_date"));
				o.setBook_title(rset.getString("book_title"));
				o.setOrderPay(rset.getString("order_pay"));
				o.setBookPrice(rset.getInt("book_price"));
				o.setStatus(rset.getString("status"));
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

	// 현재 로그인 한 회원이 결제완료한 책인지 확인하기 위한 함수
	public int checkPermission(Connection conn, int memberNo, int bookNo) {
		int check = 0;
		int answer = 0;
		for (int i = 1; i<10; i++) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String queryHead = "SELECT ORDER_NO FROM ORDER_B WHERE MEMBER_NO = ? AND STATUS = 3 AND BOOK_NO";
			String queryTail = "= ?";
			String query = queryHead + i + queryTail;
			if(i==1) {
				query = queryHead + queryTail;
			}
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setInt(2, bookNo);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					check = rset.getInt("ORDER_NO");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			if (check >= 1) {
				answer=1;
				break;
			}
		}
		return answer;
	}
}
