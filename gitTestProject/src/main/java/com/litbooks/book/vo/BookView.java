package com.litbooks.book.vo;

import java.util.ArrayList;


public class BookView {
	private Book b;
	private ArrayList<Recomm> recommList;
	private ArrayList<Recomm> rerecommList;
	private String pageNavi;
	private int start;
	
	public BookView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//상세페이지 출력용 생성자
	public BookView(Book b, ArrayList<Recomm> recommList, ArrayList<Recomm> rerecommList) {
		super();
		this.b = b;
		this.recommList = recommList;
		this.rerecommList = rerecommList;
	}
	
	//전체 조회용 생성자
		public BookView(ArrayList<Recomm> recommList, String pageNavi, int start) {
			super();
			this.recommList = recommList;
			this.pageNavi = pageNavi;
			this.start = start;
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
	
	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	
	public ArrayList<Recomm> getRecommList() {
		return recommList;
	}
	public void setRecommList(ArrayList<Recomm> recommList) {
		this.recommList = recommList;
	}
	public ArrayList<Recomm> getRerecommList() {
		return rerecommList;
	}
	public void setRerecommList(ArrayList<Recomm> rerecommList) {
		this.rerecommList = rerecommList;
	}
	
	
	
}
