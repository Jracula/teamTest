package com.litbooks.book.vo;


public class Recomm {
	    private int recommNo;
	    private int bookRef;
	    private String rcWriter;
	    private String recommContent;
	    private String recommDate;
		private int recommRef;
		private int rating;

		public Recomm() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getRecommNo() {
			return recommNo;
		}

		public void setRecommNo(int recommNo) {
			this.recommNo = recommNo;
		}

		public int getBookRef() {
			return bookRef;
		}

		public void setBookRef(int bookRef) {
			this.bookRef = bookRef;
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
		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public Recomm(int recommNo, int bookRef, String rcWriter, String recommContent, String recommDate,
				int recommRef, int rating) {
			super();
			this.recommNo = recommNo;
			this.bookRef = bookRef;
			this.rcWriter = rcWriter;
			this.recommContent = recommContent;
			this.recommDate = recommDate;
			this.recommRef = recommRef;
			this.rating = rating;
		}

		

}
