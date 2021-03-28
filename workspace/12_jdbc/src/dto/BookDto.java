package dto;

import java.sql.Date;

public class BookDto {

	private int bookNo;
	private String bookTitle;
	private String bookWriter;
	private Date pubDate;

	public BookDto() {
	}

	public BookDto(int bookNo, String bookTitle, String bookWriter, Date pubDate) {
		super();
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.bookWriter = bookWriter;
		this.pubDate = pubDate;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "BookDto [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", bookWriter=" + bookWriter + ", pubDate="
				+ pubDate + "]";
	}

}