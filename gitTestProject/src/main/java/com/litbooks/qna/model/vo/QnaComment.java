package com.litbooks.qna.model.vo;

public class QnaComment {
	private String bcNo;
	private String bcWriter;
	private String bcContent;
	private String bcDate;
	private int boardRef;
	private int bcRef;
	public QnaComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaComment(String bcNo, String bcWriter, String bcContent, String bcDate, int boardRef, int bcRef) {
		super();
		this.bcNo = bcNo;
		this.bcWriter = bcWriter;
		this.bcContent = bcContent;
		this.bcDate = bcDate;
		this.boardRef = boardRef;
		this.bcRef = bcRef;
	}
	public String getBcNo() {
		return bcNo;
	}
	public void setBcNo(String bcNo) {
		this.bcNo = bcNo;
	}
	public String getBcWriter() {
		return bcWriter;
	}
	public void setBcWriter(String bcWriter) {
		this.bcWriter = bcWriter;
	}
	public String getBcContent() {
		return bcContent;
	}
	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}
	public String getBcDate() {
		return bcDate;
	}
	public void setBcDate(String bcDate) {
		this.bcDate = bcDate;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBcRef() {
		return bcRef;
	}
	public void setBcRef(int bcRef) {
		this.bcRef = bcRef;
	}
	
	
}


