package com.litbooks.faq.model.vo;

import java.util.ArrayList;

public class FaqPageData {
	private ArrayList<Faq> list;
	private String pageNavi;
	private int start;
	
	public FaqPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FaqPageData(ArrayList<Faq> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Faq> getList() {
		return list;
	}
	public void setList(ArrayList<Faq> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}
