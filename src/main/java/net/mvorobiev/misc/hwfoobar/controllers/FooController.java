package net.mvorobiev.misc.hwfoobar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.mvorobiev.misc.hwfoobar.model.Book;
import net.mvorobiev.misc.hwfoobar.services.BookService;

@Controller
public class FooController {
	
	private BookService bookService;
	
	@Autowired
	public FooController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/listBooks") 
	public String listBooks(Model model) {
		List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("allBooks", allBooks);
		return "listBooks";
	}
	
	@GetMapping("/addBook")
	public String addBookGet(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}
	
	@PostMapping("/addBook")
	public String addBookPost(@ModelAttribute("book") Book book, Model model ) {
		this.bookService.addBook(book);
		
		return "redirect:/listBooks";
	}
	
	@GetMapping("/{id}")
	public String showBookById(@PathVariable Long id, Model model) {
		Book book = this.bookService.getBook(id);
		model.addAttribute("book", book);
		
		return "showBook";
	}
	
	@GetMapping("/{id}/edit")
	public String editBook(@PathVariable Long id, Model model) {
		Book book = this.bookService.getBook(id);
		model.addAttribute("book", book);
		return "editBook";
	}
	
	@PostMapping("/{id}")
	public String updateBook (@ModelAttribute("book") Book book, @PathVariable Long id, Model model) {
		//Book book = (Book)model.getAttribute("book");
		this.bookService.updateBook(book);
		return "redirect:/listBooks";
	}
	
	@DeleteMapping("/{id}")
	public String deleteBookP (@PathVariable Long id) {
		this.bookService.deleteBook(id);
		return "redirect:/listBooks";
	} 
	
	@GetMapping("/resources/css/ccc.css")
	public String getStaticResource() {
		return "resources/css/ccc.css";
	}
}
