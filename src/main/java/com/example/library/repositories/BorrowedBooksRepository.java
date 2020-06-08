package com.example.library.repositories;

import com.example.library.model.BorrowedBooks;
import org.springframework.data.repository.CrudRepository;

public interface BorrowedBooksRepository extends CrudRepository<BorrowedBooks, Integer> {

}
