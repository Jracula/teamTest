package com.litbooks.qna.model.vo;

import java.util.ArrayList;

import com.litbooks.faq.model.vo.Faq;

public class QnaPageData {
	private ArrayList<Qna> list;
	private String pageNavi;
	private int start;
	public QnaPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaPageData(ArrayList<Qna> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<Qna> getList() {
		return list;
	}
	public void setList(ArrayList<Qna> list) {
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
