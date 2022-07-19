package net.mvorobiev.misc.hwfoobar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.mvorobiev.misc.hwfoobar.dao.BookDao;
import net.mvorobiev.misc.hwfoobar.model.Book;

@Component
public class BookServiceImpl implements BookService{
	private static int BOOKS_COUNT;
	
	
	private BookDao bookDao;
	
	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
		this.BOOKS_COUNT = this.bookDao.getBooksCount();
	}

	public List<Book> getAllBooks() {
		return bookDao.listAllBooks();
	}

	public void addBook(Book book) {
		this.bookDao.addBook(book);
	}
	
	public Book getBook(Long id) {
		return this.bookDao.getBook(id);
	}
	
	public void updateBook(Book book) {
		this.bookDao.updateBook(book);
	}
	
	public void deleteBook (Long id) {
		this.bookDao.removeBook(id);
	}
}
