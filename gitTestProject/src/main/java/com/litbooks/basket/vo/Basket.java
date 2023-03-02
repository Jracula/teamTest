package com.litbooks.basket.vo;


//BASKET 테이블


public class Basket {
	private int basketNo;	//장바구니 번호	Primary key
	private int memberNo;	//회원번호
	private int BookNo;	//책번호
	
	public Basket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Basket(int basketNo, int memberNo, int bookNo) {
		super();
		this.basketNo = basketNo;
		this.memberNo = memberNo;
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
}
