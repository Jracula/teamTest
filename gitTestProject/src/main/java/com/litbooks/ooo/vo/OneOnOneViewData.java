package com.litbooks.ooo.vo;

import java.util.ArrayList;

public class OneOnOneViewData {
	private OneOnOne o;
	private ArrayList<OneOnOneComment> commentList;

	
	public OneOnOneViewData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OneOnOneViewData(OneOnOne o, ArrayList<OneOnOneComment> commentList) {
		super();
		this.o = o;
		this.commentList = commentList;
	}


	public OneOnOne getO() {
		return o;
	}


	public void setO(OneOnOne o) {
		this.o = o;
	}


	public ArrayList<OneOnOneComment> getCommentList() {
		return commentList;
	}


	public void setCommentList(ArrayList<OneOnOneComment> commentList) {
		this.commentList = commentList;
	}




}
