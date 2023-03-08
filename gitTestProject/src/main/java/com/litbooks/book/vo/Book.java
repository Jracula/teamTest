package com.litbooks.book.vo;


//BOOK 테이블


public class Book {
	private int bookNo;	//책번호	Primary key
	private String bookTitle;	//책제목
	private String bookGenre;	//장르
	private String writer;	//작가
	private String publisher;	//출판사
	private int bookPrice;	//책가격. 최소 0
	private int discount;	//할인율. 0~100만 가능하며 0이면 정가 판매. 기본값은 0
	private int onSale;	//0이면 판매중단, 1이면 판매중. 기본값은 1
	private String bookIntro;	//책소개 글. 줄바꿈 포함됨
	private int bookEpi;	//몇권(몇화)인지 표시. 단권 또는 1권(1화)면 1    2권(2화)이면 2
	private int book1st;	//1권인 책의 bookNo를 기입. 단권 또는 1권이면 자기자신 object의 bookNo와 동일.
	private int nonFee;	//0이면 유료, 1이면 무료. 기본값은 0
	private String bookImage;	//그림파일의 파일명 또는 파일경로
	private float bookScore;	//후기 평점. 기본값은 0(=후기 없음)
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 전체 parameter 생성자
	public Book(int bookNo, String bookTitle, String bookGenre, String writer, String publisher, int bookPrice, int discount, int onSale, String bookIntro, int bookEpi, int book1st, int nonFee, String bookImage, float bookScore) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookGenre = bookGenre;
		this.writer = writer;
		this.publisher = publisher;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.onSale = onSale;
		this.bookIntro = bookIntro;
		this.bookEpi = bookEpi;
		this.book1st = book1st;
		this.nonFee = nonFee;
		this.bookImage = bookImage;
		this.bookScore = bookScore;
	}

	// 신규 책 등록용 생성자
	public Book(String bookTitle, String bookGenre, String writer, String publisher, int bookPrice, int discount, String bookIntro, int bookEpi, int book1st, int nonFee) {
		super();
		this.bookTitle = bookTitle;
		this.bookGenre = bookGenre;
		this.writer = writer;
		this.publisher = publisher;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.bookIntro = bookIntro;
		this.bookEpi = bookEpi;
		this.book1st = book1st;
		this.nonFee = nonFee;
	}

	// 일부 추려낸 생성자
	public Book(int bookNo, String bookTitle, String bookGenre, int bookPrice, int discount, int bookEpi, int book1st, int nonFee) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookGenre = bookGenre;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.bookEpi = bookEpi;
		this.book1st = book1st;
		this.nonFee = nonFee;
	}

	// 장바구니 조회를 위한 생성자
	public Book(int bookNo, String bookTitle, int bookPrice) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getOnSale() {
		return onSale;
	}

	public void setOnSale(int onSale) {
		this.onSale = onSale;
	}

	public String getBookIntro() {
		return bookIntro;
	}

	public void setBookIntro(String bookIntro) {
		this.bookIntro = bookIntro;
	}

	public int getBookEpi() {
		return bookEpi;
	}

	public void setBookEpi(int bookEpi) {
		this.bookEpi = bookEpi;
	}

	public int getBook1st() {
		return book1st;
	}

	public void setBook1st(int book1st) {
		this.book1st = book1st;
	}

	public int getNonFee() {
		return nonFee;
	}

	public void setNonFee(int nonFee) {
		this.nonFee = nonFee;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public float getBookScore() {
		return bookScore;
	}

	public void setBookScore(float bookScore) {
		this.bookScore = bookScore;
	}	
}
