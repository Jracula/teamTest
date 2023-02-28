package com.litbooks.board.model.vo;

public class Board {
	private int qNo;
	private String qTitle;
	private int memberNo;
	private String qWriter;
	private String qContent;
	private int qReadCount;
	private String qRegDate;
	private String genreKor;
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int qNo, String qTitle, int memberNo, String qWriter, String qContent, int qReadCount, String qRegDate,
			String genreKor) {
		super();
		this.qNo = qNo;
		this.qTitle = qTitle;
		this.memberNo = memberNo;
		this.qWriter = qWriter;
		this.qContent = qContent;
		this.qReadCount = qReadCount;
		this.qRegDate = qRegDate;
		this.genreKor = genreKor;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getqWriter() {
		return qWriter;
	}
	public void setqWriter(String qWriter) {
		this.qWriter = qWriter;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public int getqReadCount() {
		return qReadCount;
	}
	public void setqReadCount(int qReadCount) {
		this.qReadCount = qReadCount;
	}
	public String getqRegDate() {
		return qRegDate;
	}
	public void setqRegDate(String qRegDate) {
		this.qRegDate = qRegDate;
	}
	public String getGenreKor() {
		return genreKor;
	}
	public void setGenreKor(String genreKor) {
		this.genreKor = genreKor;
	}
	
	
	
	
	
}
