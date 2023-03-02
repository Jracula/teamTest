package com.litbooks.qna.model.vo;

import java.util.ArrayList;

public class QnaViewData {
	private Qna q;
	private ArrayList<QnaComment> commentList;
	private ArrayList<QnaComment> reCommentList;
	public QnaViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaViewData(Qna q, ArrayList<QnaComment> commentList, ArrayList<QnaComment> reCommentList) {
		super();
		this.q = q;
		this.commentList = commentList;
		this.reCommentList = reCommentList;
	}
	public Qna getB() {
		return q;
	}
	public void setB(Qna q) {
		this.q = q;
	}
	public ArrayList<QnaComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<QnaComment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<QnaComment> getReCommentList() {
		return reCommentList;
	}
	public void setReCommentList(ArrayList<QnaComment> reCommentList) {
		this.reCommentList = reCommentList;
	}
	
	
	
}
