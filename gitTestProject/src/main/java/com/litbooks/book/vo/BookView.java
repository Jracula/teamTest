package com.litbooks.book.vo;

import java.util.ArrayList;


public class BookView {
	public BookView(Book b, ArrayList<Recomm> recommList, ArrayList<Recomm> rerecommList) {
		super();
		this.b = b;
		this.recommList = recommList;
		this.rerecommList = rerecommList;
	}
	private Book b;
	private ArrayList<Recomm> recommList;
	private ArrayList<Recomm> rerecommList;
	public BookView() {
		super();
		// TODO Auto-generated constructor stub
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
