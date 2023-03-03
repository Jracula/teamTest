package com.litbooks.book.vo;

import java.util.ArrayList;

public class SearchResultPage {
	private ArrayList<Book> list;
	private String pageNavi;
	private int start;
	private int numPerPage;
	private int reqPage;
	private int totalCount;
	
	public SearchResultPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchResultPage(ArrayList<Book> list, String pageNavi, int start, int numPerPage, int reqPage,
			int totalCount) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
		this.numPerPage = numPerPage;
		this.reqPage = reqPage;
		this.totalCount = totalCount;
	}

	public ArrayList<Book> getList() {
		return list;
	}

	public void setList(ArrayList<Book> list) {
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

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getReqPage() {
		return reqPage;
	}

	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
