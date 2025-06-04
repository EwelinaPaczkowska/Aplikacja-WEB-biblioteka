package pl.utp.bookapp.controller;

import pl.utp.bookapp.model.Book;
import pl.utp.bookapp.service.BookService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BookController extends HttpServlet {
    private final BookService service = new BookService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("q");
        System.out.println(">>> Szukam dla zapytania: " + query);

        List<Book> books = (query == null || query.isEmpty()) ? service.getAllBooks() : service.searchBooks(query);
        System.out.println(">>> Znaleziono książek: " + books.size());

        req.setAttribute("books", books);
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }
}
