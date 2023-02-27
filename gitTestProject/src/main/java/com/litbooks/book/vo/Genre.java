package com.litbooks.book.vo;


//GENRE 테이블


public class Genre {
	private String genreKor;	//장르명
	private int genreNo;	//장르번호
	private String genreEng;	//8자리 이내 영문코드
	
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(String genreKor, int genreNo, String genreEng) {
		super();
		this.genreKor = genreKor;
		this.genreNo = genreNo;
		this.genreEng = genreEng;
	}

	public String getGenreKor() {
		return genreKor;
	}

	public void setGenreKor(String genreKor) {
		this.genreKor = genreKor;
	}

	public int getGenreNo() {
		return genreNo;
	}

	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}

	public String getGenreEng() {
		return genreEng;
	}

	public void setGenreEng(String genreEng) {
		this.genreEng = genreEng;
	}
}
