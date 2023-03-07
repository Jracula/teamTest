package com.litbooks.ooo.vo;

public class OneOnOne {
	private int oNo;
	private String oTitle;
	private int memberNo;
	private String oWriter;
	private String oContent;
	private int oReadCount;
	private String oRegDate;
	private String fileName;
	private String filepath;
	public OneOnOne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneOnOne(int oNo, String oTitle, int memberNo, String oWriter, String oContent, int oReadCount,
			String oRegDate, String fileName, String filepath) {
		super();
		this.oNo = oNo;
		this.oTitle = oTitle;
		this.memberNo = memberNo;
		this.oWriter = oWriter;
		this.oContent = oContent;
		this.oReadCount = oReadCount;
		this.oRegDate = oRegDate;
		this.fileName = fileName;
		this.filepath = filepath;
	}
	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	public String getoTitle() {
		return oTitle;
	}
	public void setoTitle(String oTitle) {
		this.oTitle = oTitle;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getoWriter() {
		return oWriter;
	}
	public void setoWriter(String oWriter) {
		this.oWriter = oWriter;
	}
	public String getoContent() {
		return oContent;
	}
	public void setoContent(String oContent) {
		this.oContent = oContent;
	}
	public int getoReadCount() {
		return oReadCount;
	}
	public void setoReadCount(int oReadCount) {
		this.oReadCount = oReadCount;
	}
	public String getoRegDate() {
		return oRegDate;
	}
	public void setoRegDate(String oRegDate) {
		this.oRegDate = oRegDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}


