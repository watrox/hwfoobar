package net.mvorobiev.misc.hwfoobar.dao;

import java.util.List;

import net.mvorobiev.misc.hwfoobar.model.Book;

public interface BookDao {
	public List<Book> listAllBooks();
	
	public Book getBook(Long id);
	
	public void addBook(Book book);
	
	public void removeBook (Long id);
	
	public void updateBook (Book book);
	
	public int getBooksCount();
}
