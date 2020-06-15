package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Books> findAll() {
        return (List<Books>)bookRepository.findAll();
    }

    public Optional<Books> findByBookId(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    public Books save(Books book) {
        return bookRepository.save(book);
    }

    public Books updateAvailability(Integer bookId, int availability) {
        Optional<Books> book = findByBookId(bookId);
        if(book.isPresent())
            book.get().setAvailable(availability);
        return save(book.get());
    }

}
