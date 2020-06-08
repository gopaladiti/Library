package com.example.library.controller;

import com.example.library.model.BookRequest;
import com.example.library.model.Books;
import com.example.library.model.ReturnReceipt;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    @GetMapping("/books")
    public List<String> getBooks() {
        List<String> listOfBooks = ((List<Books>)bookRepository.findAll()).stream()
                .map(books -> books.getTitle()).collect(Collectors.toList());
        return listOfBooks;
    }

    @GetMapping("/books/user")
    public List<String> getBooksBorrowedByUser(@RequestParam Integer userId) {
        return null;
    }

    @PostMapping("/books/user")
    public String borrowBooks(@RequestBody BookRequest data) {
        return "Success";
    }

    @PutMapping("/books/user")
    public ReturnReceipt returnBooks(@RequestBody BookRequest data) {
        return null;
    }

}
