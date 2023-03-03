package com.litbooks.basket.vo;

import java.util.ArrayList;

import com.litbooks.book.vo.Book;

public class MultiData {
	private ArrayList<Basket> basketList;
	private ArrayList<Book> bookList;
	
	public MultiData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultiData(ArrayList<Basket> list, ArrayList<Book> list2) {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Basket> getBasketList() {
		return basketList;
	}

	public void setBasketList(ArrayList<Basket> basketList) {
		this.basketList = basketList;
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
}
