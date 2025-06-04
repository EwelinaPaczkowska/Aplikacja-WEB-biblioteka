package pl.utp.bookapp.repository;

import pl.utp.bookapp.model.Book;
import java.sql.*;
import java.util.*;

public class BookRepository {
    private static final String DB_URL = "jdbc:derby:memory:bookdb;create=true";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println(">>> Sterownik Derby załadowany");
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Błąd ładowania sterownika Derby!");
            e.printStackTrace();
        }
    }


    public BookRepository() {
        initDb();
    }

    private void initDb() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE books (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255))");
            stmt.execute("INSERT INTO books VALUES (1, 'Pan Tadeusz', 'Adam Mickiewicz')");
            stmt.execute("INSERT INTO books VALUES (2, 'Lalka', 'Boleslaw Prus')");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }

    public List<Book> search(String keyword) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ?")) {
            String query = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }
}
