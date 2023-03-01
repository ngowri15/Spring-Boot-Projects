package com.training.model;

public class BookDetails {

	private long isbnId;
	private String bookName;
	private String authorName;
	private String publicationName;
	private Integer yearPublished;

	public BookDetails() {

	}

	public BookDetails(long isbnId, String bookName, String authorName, String publicationName, Integer yearPublished) {
		this.isbnId = isbnId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publicationName = publicationName;
		this.yearPublished = yearPublished;
	}

	public long getIsbnId() {
		return isbnId;
	}

	public void setIsbnId(long isbnId) {
		this.isbnId = isbnId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	@Override
	public String toString() {
		return "BookDetails [isbnId=" + isbnId + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", publicationName=" + publicationName + ", yearPublished=" + yearPublished + "]";
	}

}