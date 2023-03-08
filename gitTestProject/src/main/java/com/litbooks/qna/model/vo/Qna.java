package com.litbooks.qna.model.vo;

public class Qna {
	private int qNo;
	private String qTitle;
	private int memberNo;
	private String qWriter;
	private String qContent;
	private int qReadCount;
	private String qRegDate;
	private String genreKor;
	private int qFlag;
	private String fileName;
	private String filepath;
	
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Qna(int qNo, String qTitle, int memberNo, String qWriter, String qContent, int qReadCount, String qRegDate,
			String genreKor, int qFlag, String fileName, String filepath) {
		super();
		this.qNo = qNo;
		this.qTitle = qTitle;
		this.memberNo = memberNo;
		this.qWriter = qWriter;
		this.qContent = qContent;
		this.qReadCount = qReadCount;
		this.qRegDate = qRegDate;
		this.genreKor = genreKor;
		this.qFlag = qFlag;
		this.fileName = fileName;
		this.filepath = filepath;
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


	public int getqFlag() {
		return qFlag;
	}


	public void setqFlag(int qFlag) {
		this.qFlag = qFlag;
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


	public String getQnaContentBr() {
		return qContent.replaceAll("\r\n", "<br>");
	
	
	
	}
}
