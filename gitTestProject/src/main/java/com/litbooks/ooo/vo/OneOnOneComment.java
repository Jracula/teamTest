package com.litbooks.ooo.vo;

public class OneOnOneComment {
	private int oo_no;
	private String oo_writer;
	private String oo_content;
	private int oo_ref;
	private String oo_date;
	public OneOnOneComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneOnOneComment(int oo_no, String oo_writer, String oo_content, int oo_ref, String oo_date) {
		super();
		this.oo_no = oo_no;
		this.oo_writer = oo_writer;
		this.oo_content = oo_content;
		this.oo_ref = oo_ref;
		this.oo_date = oo_date;
	}
	public int getOo_no() {
		return oo_no;
	}
	public void setOo_no(int oo_no) {
		this.oo_no = oo_no;
	}
	public String getOo_writer() {
		return oo_writer;
	}
	public void setOo_writer(String oo_writer) {
		this.oo_writer = oo_writer;
	}
	public String getOo_content() {
		return oo_content;
	}
	public void setOo_content(String oo_content) {
		this.oo_content = oo_content;
	}
	public int getOo_ref() {
		return oo_ref;
	}
	public void setOo_ref(int oo_ref) {
		this.oo_ref = oo_ref;
	}
	public String getOo_date() {
		return oo_date;
	}
	public void setOo_date(String oo_date) {
		this.oo_date = oo_date;
	}
	public String getOoContentBr() {
		return oo_content.replaceAll("\r\n", "<br>");
	}
	
}
