package com.example.library.controller;

import com.example.library.model.BookRequest;
import com.example.library.model.BorrowedBooks;
import com.example.library.model.ReturnReceipt;
import com.example.library.service.BookService;
import com.example.library.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    private BorrowedBooksService borrowedBooksService;

    @Autowired
    public BookController(BookService bookService, BorrowedBooksService borrowedBooksService) {
        this.bookService = bookService;
        this.borrowedBooksService = borrowedBooksService;
    }

    @GetMapping
    public List<String> getBooks() {
        List<String> listOfBooks = bookService.findAll().stream()
                .map(books -> books.getTitle()).collect(Collectors.toList());
        return listOfBooks;
    }

    @GetMapping("/user")
    public List<String> getBooksBorrowedByUser(@RequestParam Integer userId) {
        List<String> listOfBooks = borrowedBooksService.findAll().stream()
                .filter(borrowedBooks -> borrowedBooks.getUser().getUserId() == userId)
                .map(borrowedBooks -> borrowedBooks.getBook().getTitle()).collect(Collectors.toList());
        return listOfBooks;
    }

    @PostMapping("/user")
    public List<String> borrowBooks(@RequestBody BookRequest data) {
        return null;
    }

    @PutMapping("/user")
    public ReturnReceipt returnBooks(@RequestBody BookRequest data) {
        return null;
    }

}
