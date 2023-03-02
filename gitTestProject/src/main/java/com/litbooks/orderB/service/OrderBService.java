package com.litbooks.orderB.service;

import java.sql.Connection;
import java.util.ArrayList;

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
	public OrderBPageData selectAllOrder(int memberNo, int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int numPerPage = 10; // 총 게시물 수
		
		int end = numPerPage * reqPage; // 끝번호 10 / 20 / 30 
		int start = end - numPerPage + 1; // 시작번호 1 / 11 / 21
		
		ArrayList<OrderB> list = dao.selectAllOrder(conn, memberNo, start,end);

		// 페이징제작 시작
		// 1. 전체페이지 게시물 수를 계산 -> 총 게시물 수 조회
		int totalCount = dao.selectOrderCount(conn);
		
		// 2. 전체 페이지 수 계산
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
												 	 
		}
		
		// 3. 네비게이션 사이즈 (< 1,2,3,4,5 > 작업)
		int pageNaviSize = 5;
		
		// 4. 페이지 네비게이션 시작 번호 
		
		int pageNo = ((reqPage-1)/pageNaviSize) * pageNaviSize + 1;
		
		// 5. 페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		
		// 이전버튼 생성
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		
		// 페이지 숫자 (1 2 3 4 5)
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/noticeList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		
		// 다음버튼 생성
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo)+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		JDBCTemplate.close(conn);
		OrderBPageData opd = new OrderBPageData(list, pageNavi, start);
		return opd;
	}

	// 어떤회원이 결제했는지 조회
	public OrderB selectOrderNumber(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderB o = dao.selectOrderNumber(conn, memberNo);
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
	public int changePay(int memberNo, String orderPay) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changePay(conn, memberNo, orderPay);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// (관리자페이지) 주문내역 취소
	public int orderCancel() {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.orderCancel(conn);
		return 0;
	}
	
	
}
