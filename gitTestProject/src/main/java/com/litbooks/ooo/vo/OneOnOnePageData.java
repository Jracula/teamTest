package com.litbooks.ooo.vo;

import java.util.ArrayList;

public class OneOnOnePageData {
	private ArrayList<OneOnOne> list;
	private String pageNavi;
	private int start;
	
	public OneOnOnePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OneOnOnePageData(ArrayList<OneOnOne> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<OneOnOne> getList() {
		return list;
	}
	public void setList(ArrayList<OneOnOne> list) {
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
