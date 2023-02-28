package com.litbooks.book.vo;

import com.litbooks.member.vo.Member;

public class Recomm {
	    private int RecommNo;
	    private int BookNo;
	    private String memberId;
	    private String RecommContent;
	    private String RecommDate;
		public Recomm() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Recomm(int recommNo, int bookNo, String memberId, String recommContent, String recommDate) {
			super();
			RecommNo = recommNo;
			BookNo = bookNo;
			this.memberId = memberId;
			RecommContent = recommContent;
			RecommDate = recommDate;
		}
		public int getRecommNo() {
			return RecommNo;
		}
		public void setRecommNo(int recommNo) {
			RecommNo = recommNo;
		}
		public int getBookNo() {
			return BookNo;
		}
		public void setBookNo(int bookNo) {
			BookNo = bookNo;
		}
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getRecommContent() {
			return RecommContent;
		}
		public void setRecommContent(String recommContent) {
			RecommContent = recommContent;
		}
		public String getRecommDate() {
			return RecommDate;
		}
		public void setRecommDate(String recommDate) {
			RecommDate = recommDate;
		}
		
		
		
	
	
	
	
	
	
}
