package com.litbooks.faq.model.vo;

public class Faq {
	private int fNo;
	private int memberNo;
	private String fWriter;
	private String fTitle;
	private String fContent;
	private int fReadCount;
	private String fRegDate;
	private String filename;
	private String filepath;
	
	
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Faq(int fNo, int memberNo, String fWriter, String fTitle, String fContent, int fReadCount, String fRegDate,
			String filename, String filepath) {
		super();
		this.fNo = fNo;
		this.memberNo = memberNo;
		this.fWriter = fWriter;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fReadCount = fReadCount;
		this.fRegDate = fRegDate;
		this.filename = filename;
		this.filepath = filepath;
	}


	public int getfNo() {
		return fNo;
	}


	public void setfNo(int fNo) {
		this.fNo = fNo;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getfWriter() {
		return fWriter;
	}


	public void setfWriter(String fWriter) {
		this.fWriter = fWriter;
	}


	public String getfTitle() {
		return fTitle;
	}


	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}


	public String getfContent() {
		return fContent;
	}


	public void setfContent(String fContent) {
		this.fContent = fContent;
	}


	public int getfReadCount() {
		return fReadCount;
	}


	public void setfReadCount(int fReadCount) {
		this.fReadCount = fReadCount;
	}


	public String getfRegDate() {
		return fRegDate;
	}


	public void setfRegDate(String fRegDate) {
		this.fRegDate = fRegDate;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getfContentBr() {
		return fContent.replaceAll("\r\n", "<br>");
	}
	
}

