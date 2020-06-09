package com.example.library.repositories;

import com.example.library.model.BorrowedBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBooksRepository extends CrudRepository<BorrowedBooks, Integer> {

}
