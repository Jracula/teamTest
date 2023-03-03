package com.litbooks.basket.vo;


//BASKET 테이블


public class Basket {
	private int basketNo;	//장바구니 번호	Primary key
	private int memberNo;	//회원번호
	private int BookNo;	//책번호
	private String bookTitle;
	private int bookPrice;
	
	public Basket() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 장바구니에서 책제목, 책가격을 조회하기 위한 생성자
	public Basket(int basketNo, int memberNo, int bookNo, String bookTitle, int bookPrice) {
		super();
		this.basketNo = basketNo;
		this.memberNo = memberNo;
		BookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
	}

	public Basket(int basketNo, int memberNo, int bookNo) {
		super();
		this.basketNo = basketNo;
		this.memberNo = memberNo;
		BookNo = bookNo;
	}
	
	public Basket(int basketNo, int bookNo) {
		super();
		this.basketNo = basketNo;
		BookNo = bookNo;
	}

	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBookNo() {
		return BookNo;
	}

	public void setBookNo(int bookNo) {
		BookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	
}
