package pl.utp.bookapp.service;

import pl.utp.bookapp.model.Book;
import pl.utp.bookapp.repository.BookRepository;
import java.util.*;

public class BookService {
    private static final BookRepository repository = new BookRepository();

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> searchBooks(String keyword) {
        return repository.search(keyword);
    }
}
