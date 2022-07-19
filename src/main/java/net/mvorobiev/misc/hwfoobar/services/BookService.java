package net.mvorobiev.misc.hwfoobar.services;

import java.util.List;

import net.mvorobiev.misc.hwfoobar.model.Book;

public interface BookService {
	public List<Book> getAllBooks();
	
	public void addBook(Book book);
	
	public Book getBook(Long id);
	
	public void updateBook (Book book);
	
	public void deleteBook (Long id);
}
