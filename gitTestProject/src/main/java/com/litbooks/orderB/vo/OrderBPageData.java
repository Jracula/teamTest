package com.litbooks.orderB.vo;

import java.util.ArrayList;



public class OrderBPageData {
	private ArrayList<OrderB> list;
	private String pageNavi;
	private int start;
	
	public OrderBPageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBPageData(ArrayList<OrderB> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}

	public ArrayList<OrderB> getList() {
		return list;
	}

	public void setList(ArrayList<OrderB> list) {
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
