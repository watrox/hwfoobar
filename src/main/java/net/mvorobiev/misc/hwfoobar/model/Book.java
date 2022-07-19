package net.mvorobiev.misc.hwfoobar.model;

public class Book {
	private Long id;
	private String title;
	private String author;
	private Long price;
	
	public Book() {
		this.id=null;
		this.title=null;
		this.author=null;
		this.price=null;
		
	}

	public Book(Long id, String title, String author, Long price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
}
