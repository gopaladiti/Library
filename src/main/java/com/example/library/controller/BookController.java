package com.example.library.controller;

import com.example.library.model.*;
import com.example.library.service.BookService;
import com.example.library.service.BorrowedBooksService;
import com.example.library.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private BookService bookService;

    private BorrowedBooksService borrowedBooksService;

    private UserDetailsService userDetailsService;

    @Autowired
    public BookController(BookService bookService, BorrowedBooksService borrowedBooksService,
                          UserDetailsService userDetailsService) {
        this.bookService = bookService;
        this.borrowedBooksService = borrowedBooksService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<Books> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<BorrowedBooks> getBooksBorrowedByUser(@PathVariable Integer userId) {
        List<BorrowedBooks> listOfBorrowedBooks = borrowedBooksService.getBorrowedBooksByUser(userId);
        return listOfBorrowedBooks;
    }

    @PostMapping("/user")
    public List<BorrowedBooks> borrowBooks(@RequestBody RentBook data) {
        UserDetails user = userDetailsService.getUserByUserId(data.getUserId());
        List<Books> listOfBooks = new ArrayList<>();
        for(int bookId: data.getListOfBooks()) {
            bookService.findByBookId(bookId).ifPresent(listOfBooks::add);
        }
        List<BorrowedBooks> listOfRequestedBooks = listOfBooks.stream()
                .map(book ->  new BorrowedBooks(
                        book,
                        user,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(14),
                        null)
                ).collect(Collectors.toList());
        List<BorrowedBooks> rentedBooks = new ArrayList<BorrowedBooks>();
        listOfRequestedBooks.stream().forEach(requestedBook -> rentedBooks
                .add(borrowedBooksService.save(requestedBook)));
        listOfRequestedBooks.stream().forEach(requestedBook ->
                bookService.updateAvailability(requestedBook.getBook().getId(), 1));
        return rentedBooks;
    }

    @DeleteMapping("/user/{userId}/{bookId}")
    public List<BorrowedBooks> returnBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        System.out.println(bookId);
        List<BorrowedBooks> borrowedBooks = borrowedBooksService.getBorrowedBooksByBookId(bookId);
        System.out.println(borrowedBooks.toString());
        borrowedBooksService.deleteBook(borrowedBooks.get(0).getId());
        bookService.updateAvailability(borrowedBooks.get(0).getBook().getId(), 0);
        return borrowedBooksService.getBorrowedBooksByUser(userId);
    }

}
