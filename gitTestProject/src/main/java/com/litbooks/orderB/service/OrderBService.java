package com.litbooks.orderB.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.litbooks.book.vo.Book;
import com.litbooks.member.vo.Member;
import com.litbooks.orderB.dao.OrderBDao;
import com.litbooks.orderB.vo.OrderB;
import com.litbooks.orderB.vo.OrderBPageData;

import common.JDBCTemplate;

public class OrderBService {
	private OrderBDao dao;

	public OrderBService() {
		super();
		dao = new OrderBDao();
	}

	// 전체 주문내역 조회
	public OrderBPageData selectAllOrder(int reqPage, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();

		int numPerPage = 10; // 총 게시물 수

		int end = numPerPage * reqPage; // 끝번호 10 / 20 / 30
		int start = end - numPerPage + 1; // 시작번호 1 / 11 / 21

		ArrayList<OrderB> list = dao.selectAllOrder(conn, memberNo, start, end);

		// 페이징제작 시작
		// 1. 전체페이지 게시물 수를 계산 -> 총 게시물 수 조회
		int totalCount = dao.selectOrderCount(conn);

		// 2. 전체 페이지 수 계산
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		// 3. 네비게이션 사이즈 (< 1,2,3,4,5 > 작업)
		int pageNaviSize = 5;

		// 4. 페이지 네비게이션 시작 번호

		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		// 5. 페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";

		// 이전버튼 생성
		if (pageNo != 1) {
			pageNavi += "<li>";
			//pageNavi += "<a class='page-item' href='/orderList.do?reqPage="+(pageNo - 1)+"'>";
			pageNavi += "<a class='page-item' href='/orderList.do?reqPage="+(pageNo - 1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}

		// 페이지 숫자 (1 2 3 4 5)
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/orderList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/orderList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}

		// 다음버튼 생성
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/orderList.do?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}

		pageNavi += "</ul>";

		JDBCTemplate.close(conn);
		OrderBPageData opd = new OrderBPageData(list, pageNavi, start);
		return opd;
	}

	// 어떤회원이 결제했는지 조회
	public OrderB selectOrderNumber(int memberNo, int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderB o = dao.selectOrderNumber(conn, memberNo, bookNo);
		JDBCTemplate.close(conn);
		return o;
	}

	// (관리자페이지) 주문내역 전체조회
	public ArrayList<OrderB> selectAdminList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderB> list = dao.selectAdminList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	// (관리자페이지) 결제방식 변경
	public int changePay(int orderNo, int orderPay) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changePay(conn, orderNo, orderPay);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 장바구니에서 책번호 조회
	public ArrayList<Book> selectBookNo(String basketNo) {
		Connection conn = JDBCTemplate.getConnection();
		String[] bNo = basketNo.split("/");
		ArrayList<Book> list = new ArrayList<Book>();
		for (String no : bNo) {
			int basketOneNo = Integer.parseInt(no);
			Book b = dao.selectBookBasketNo(conn, basketOneNo);
			list.add(b);
		}
		JDBCTemplate.close(conn);
		return list;
	}

	// 선택회원 결제상태 변경
	public boolean checkedChange(String status, String chkStatus) {
		Connection conn = JDBCTemplate.getConnection();

		StringTokenizer sT1 = new StringTokenizer(status, "/");
		StringTokenizer sT2 = new StringTokenizer(chkStatus, "/");
		boolean result = true;

		while (sT1.hasMoreTokens()) {
			int orderNo = Integer.parseInt(sT1.nextToken());
			int orderPay = Integer.parseInt(sT2.nextToken());

			int changeResult = dao.changePay(conn, orderNo, orderPay);
			if (changeResult == 0) {
				result = false;
				break;
			}
		}
		if (result) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 결제내역 insert 문
	public int insertOrder(int memberNo1, String bookNo, String bookPrice, int price, String payMethod, String basketNo) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		String[] bookNoArr = bookNo.split("/");
		String[] bookPriceArr = bookPrice.split("/");

		String query = "insert into order_b(order_no, status, member_no, order_price, order_pay, order_reg_date, book_no, book_price";
		for (int i = 0; i < bookNoArr.length - 1; i++) {
			query += ", book_no" + (i + 2) + ", " + "book_price" + (i + 2);

		}
		query += ")";
		query += "values(order_b_seq.nextval,1,?,?,?,TO_CHAR(SYSDATE,'YYYY-MM-DD'),?,?";
		for (int i = 0; i < bookNoArr.length - 1; i++) {
			query += ",?,?";
		}

		query += ")";

		//System.out.println(query);

		int result = dao.insertOrder(conn, memberNo1, bookNoArr, bookPriceArr, price, query, payMethod);
		if(result > 0) {
			String[] basketArr = basketNo.split("/");
			int result2 = 0;
			for(String no : basketArr) {
				int result3 = dao.deleteBasket(conn, no);
				if(result3 == 0) {
					break;
				}else {
					result2 += result3;
				}
			}
			
			if(result2 == basketArr.length) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
				return 0;
			}
		} else {
			JDBCTemplate.rollback(conn);
			return 0;
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 책 단권 구매
	public int insertPayOne(int memberNo, int bookNo, int bookPrice, String payMethod) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertPayOne(conn, memberNo, bookNo, bookPrice, payMethod);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 현재 로그인 한 회원이 구매한 책인지 확인하기 위한 함수
	public int checkPermission(int memberNo, int bookNo) {
		Connection conn = JDBCTemplate.getConnection();
		int answer = dao.checkPermission(conn, memberNo, bookNo);
		JDBCTemplate.close(conn);
		return answer;
	}
}
