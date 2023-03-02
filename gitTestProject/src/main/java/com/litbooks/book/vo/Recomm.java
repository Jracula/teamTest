package com.litbooks.book.vo;


public class Recomm {
	    private int recommNo;
	    private int bookNo;
	    private String rcWriter;
	    private String recommContent;
	    private String recommDate;
		private int recommRef;

		public Recomm() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Recomm(int recommNo, int bookNo, String rcWriter, String recommContent, String recommDate,
				int recommRef) {
			super();
			this.recommNo = recommNo;
			this.bookNo = bookNo;
			this.rcWriter = rcWriter;
			this.recommContent = recommContent;
			this.recommDate = recommDate;
			this.recommRef = recommRef;
		}

		public int getRecommNo() {
			return recommNo;
		}

		public void setRecommNo(int recommNo) {
			this.recommNo = recommNo;
		}

		public int getBookNo() {
			return bookNo;
		}

		public void setBookNo(int bookNo) {
			this.bookNo = bookNo;
		}

		public String getRcWriter() {
			return rcWriter;
		}

		public void setRcWriter(String rcWriter) {
			this.rcWriter = rcWriter;
		}

		public String getRecommContent() {
			return recommContent;
		}

		public void setRecommContent(String recommContent) {
			this.recommContent = recommContent;
		}

		public String getRecommDate() {
			return recommDate;
		}

		public void setRecommDate(String recommDate) {
			this.recommDate = recommDate;
		}

		public int getRecommRef() {
			return recommRef;
		}

		public void setRecommRef(int recommRef) {
			this.recommRef = recommRef;
		}
		
}
