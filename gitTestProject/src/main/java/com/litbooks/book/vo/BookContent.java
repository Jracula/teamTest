package com.litbooks.book.vo;


//BOOK_CONTENT 테이블


public class BookContent {
	private int bookNo;	//책번호	Primary key
	private String content1;	//책내용. 텍스트 또는 그림파일 경로들. 더 필요하면 DB에서 content2도 생성 후 java에서도 변수 추가 선언.
	private int contentVer;	//개정판 상태를 숫자로 표시. 기본값(초판)은 1
	private String contentDate;	//내용물 최종 수정 시간. YYYY-MM-DD로 표현. 초기값은 최초 등록 시간.
	
	public BookContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookContent(int bookNo, String content1, int contentVer, String contentDate) {
		super();
		this.bookNo = bookNo;
		this.content1 = content1;
		this.contentVer = contentVer;
		this.contentDate = contentDate;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public int getContentVer() {
		return contentVer;
	}

	public void setContentVer(int contentVer) {
		this.contentVer = contentVer;
	}

	public String getContentDate() {
		return contentDate;
	}

	public void setContentDate(String contentDate) {
		this.contentDate = contentDate;
	}
}
