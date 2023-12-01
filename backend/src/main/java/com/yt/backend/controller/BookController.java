package com.yt.backend.controller;

import com.yt.backend.model.Book;
import com.yt.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/hellobooks")
    public String hello_world() {
        return "Hello World";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Book Added Successfully..";
    }

    @RequestMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return "Book Deleted";
    }

}