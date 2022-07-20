package net.mvorobiev.misc.hwfoobar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.mvorobiev.misc.hwfoobar.model.Book;

@Component
public class BookDaoImpl implements BookDao{
    private static final String URL = "jdbc:mysql://localhost:3306/bookmanager";
    private static final String USERNAME = "spring";
    private static final String PASSWORD = "spring";

    private static Connection connection;
    private static boolean isMsqlDriverLoaded;
    
    static {
        try {
        	isMsqlDriverLoaded = true;
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            isMsqlDriverLoaded = false;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

	public List<Book> listAllBooks() {
		ResultSet rs;
		List<Book> allBooks = new ArrayList<Book>();
		
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return null;
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuerry = "SELECT * FROM books";
			rs = statement.executeQuery(sqlQuerry);
			while (rs.next()) {
				Book book = new Book(rs.getLong ("id"    ),
									rs.getString("title" ),
									rs.getString("author"),
									rs.getLong  ("price" )	);
				allBooks.add(book);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return allBooks;
	}
	
	//NOT TESTED
	public Book getBook(Long id) {
		ResultSet rs;
		Book book = null;
		
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return null;
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuerry = "SELECT * FROM books WHERE id = " + Long.toString(id);
			rs = statement.executeQuery(sqlQuerry);
			if (!rs.next())
				return null;
			book = new Book(rs.getLong  ("id"    ),
							rs.getString("title" ),
							rs.getString("author"),
							rs.getLong  ("price" )	);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return book;
	}
	
	public void addBook(Book book) {
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return;
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuerry = "INSERT INTO books (title, author, price) VALUES ('" 
					+ book.getTitle() + "', '"
					+ book.getAuthor() + "', "
					+ book.getPrice() + ");";
			statement.executeUpdate(sqlQuerry);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void removeBook (Long id) {
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return;
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuerry = "DELETE FROM books WHERE id = " + id;
			statement.executeUpdate(sqlQuerry);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	};
	
	public void updateBook (Book book) {
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return;
		
		try {
			Statement statement = connection.createStatement();
			String sqlQuerry = "UPDATE books SET title = '" + book.getTitle() 
										+ "', author = '"	+ book.getAuthor() 
										+ "', price = "		+ book.getPrice() 
										+ " WHERE id = " + book.getId();
			statement.executeUpdate(sqlQuerry);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	};

	public int getBooksCount() {
		if (connection == null || !BookDaoImpl.isMsqlDriverLoaded)
			return 0;
		
		try {
			ResultSet rs;
			Statement statement = connection.createStatement();
			String sqlQuerry = "SELECT COUNT(*) FROM books" ;
			rs = statement.executeQuery(sqlQuerry);
			rs.next();
			return rs.getInt(0);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
