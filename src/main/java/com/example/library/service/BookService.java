package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Books> findAll() {
        return (List<Books>)bookRepository.findAll();
    }
}
