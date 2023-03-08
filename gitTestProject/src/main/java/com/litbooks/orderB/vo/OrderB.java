package com.litbooks.orderB.vo;

public class OrderB {
	private int orderNo; // 주문번호
	private String status; // 주문상태 (결제완료, 결제취소, 결제대기)
	private int memberNo; // 회원번호
	private int bookNo;
	private int bookNo2;
	private int bookNo3;
	private int bookNo4;
	private int bookNo5;
	private int bookNo6;
	private int bookNo7;
	private int bookNo8;
	private int bookNo9;
	private int bookNo10;
	private int bookPrice;
	private int bookPrice2;
	private int bookPrice3;
	private int bookPrice4;
	private int bookPrice5;
	private int bookPrice6;
	private int bookPrice7;
	private int bookPrice8;
	private int bookPrice9;
	private int bookPrice10;
	private int orderPrice; // 총가격
	private String orderPay; // 결제수단
	private String orderRegDate; // 결제날짜
	private String book_title; // 책 제목
	private String memberId; // 회원 아이디
	private String publisher; // 출판사
	private int orderCount;
	
	public OrderB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderB(int orderNo, String status, int memberNo, int bookNo, int bookNo2, int bookNo3, int bookNo4,
			int bookNo5, int bookNo6, int bookNo7, int bookNo8, int bookNo9, int bookNo10, int bookPrice,
			int bookPrice2, int bookPrice3, int bookPrice4, int bookPrice5, int bookPrice6, int bookPrice7,
			int bookPrice8, int bookPrice9, int bookPrice10, int orderPrice, String orderPay, String orderRegDate,
			String book_title, String memberId, String publisher, int orderCount) {
		super();
		this.orderNo = orderNo;
		this.status = status;
		this.memberNo = memberNo;
		this.bookNo = bookNo;
		this.bookNo2 = bookNo2;
		this.bookNo3 = bookNo3;
		this.bookNo4 = bookNo4;
		this.bookNo5 = bookNo5;
		this.bookNo6 = bookNo6;
		this.bookNo7 = bookNo7;
		this.bookNo8 = bookNo8;
		this.bookNo9 = bookNo9;
		this.bookNo10 = bookNo10;
		this.bookPrice = bookPrice;
		this.bookPrice2 = bookPrice2;
		this.bookPrice3 = bookPrice3;
		this.bookPrice4 = bookPrice4;
		this.bookPrice5 = bookPrice5;
		this.bookPrice6 = bookPrice6;
		this.bookPrice7 = bookPrice7;
		this.bookPrice8 = bookPrice8;
		this.bookPrice9 = bookPrice9;
		this.bookPrice10 = bookPrice10;
		this.orderPrice = orderPrice;
		this.orderPay = orderPay;
		this.orderRegDate = orderRegDate;
		this.book_title = book_title;
		this.memberId = memberId;
		this.publisher = publisher;
		this.orderCount = orderCount;
	}
	
	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getBookNo2() {
		return bookNo2;
	}

	public void setBookNo2(int bookNo2) {
		this.bookNo2 = bookNo2;
	}

	public int getBookNo3() {
		return bookNo3;
	}

	public void setBookNo3(int bookNo3) {
		this.bookNo3 = bookNo3;
	}

	public int getBookNo4() {
		return bookNo4;
	}

	public void setBookNo4(int bookNo4) {
		this.bookNo4 = bookNo4;
	}

	public int getBookNo5() {
		return bookNo5;
	}

	public void setBookNo5(int bookNo5) {
		this.bookNo5 = bookNo5;
	}

	public int getBookNo6() {
		return bookNo6;
	}

	public void setBookNo6(int bookNo6) {
		this.bookNo6 = bookNo6;
	}

	public int getBookNo7() {
		return bookNo7;
	}

	public void setBookNo7(int bookNo7) {
		this.bookNo7 = bookNo7;
	}

	public int getBookNo8() {
		return bookNo8;
	}

	public void setBookNo8(int bookNo8) {
		this.bookNo8 = bookNo8;
	}

	public int getBookNo9() {
		return bookNo9;
	}

	public void setBookNo9(int bookNo9) {
		this.bookNo9 = bookNo9;
	}

	public int getBookNo10() {
		return bookNo10;
	}

	public void setBookNo10(int bookNo10) {
		this.bookNo10 = bookNo10;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookPrice2() {
		return bookPrice2;
	}

	public void setBookPrice2(int bookPrice2) {
		this.bookPrice2 = bookPrice2;
	}

	public int getBookPrice3() {
		return bookPrice3;
	}

	public void setBookPrice3(int bookPrice3) {
		this.bookPrice3 = bookPrice3;
	}

	public int getBookPrice4() {
		return bookPrice4;
	}

	public void setBookPrice4(int bookPrice4) {
		this.bookPrice4 = bookPrice4;
	}

	public int getBookPrice5() {
		return bookPrice5;
	}

	public void setBookPrice5(int bookPrice5) {
		this.bookPrice5 = bookPrice5;
	}

	public int getBookPrice6() {
		return bookPrice6;
	}

	public void setBookPrice6(int bookPrice6) {
		this.bookPrice6 = bookPrice6;
	}

	public int getBookPrice7() {
		return bookPrice7;
	}

	public void setBookPrice7(int bookPrice7) {
		this.bookPrice7 = bookPrice7;
	}

	public int getBookPrice8() {
		return bookPrice8;
	}

	public void setBookPrice8(int bookPrice8) {
		this.bookPrice8 = bookPrice8;
	}

	public int getBookPrice9() {
		return bookPrice9;
	}

	public void setBookPrice9(int bookPrice9) {
		this.bookPrice9 = bookPrice9;
	}

	public int getBookPrice10() {
		return bookPrice10;
	}

	public void setBookPrice10(int bookPrice10) {
		this.bookPrice10 = bookPrice10;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(String orderPay) {
		this.orderPay = orderPay;
	}

	public String getOrderRegDate() {
		return orderRegDate;
	}

	public void setOrderRegDate(String orderRegDate) {
		this.orderRegDate = orderRegDate;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
		
}
