package com.example.library.service;

import com.example.library.model.BorrowedBooks;
import com.example.library.repositories.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBooksService {

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;

    public List<BorrowedBooks> findAll() {
        return (List< BorrowedBooks>)borrowedBooksRepository.findAll();
    }

}
