package com.yt.backend.service;
import org.springframework.stereotype.Service;
import com.yt.backend.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(long id);
    List<Book> getBooks();
    void deleteBook(long id);
    Book updateBook(Book book);
}