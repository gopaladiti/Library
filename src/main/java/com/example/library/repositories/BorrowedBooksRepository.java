package com.example.library.repositories;

import com.example.library.model.BorrowedBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBooksRepository extends CrudRepository<BorrowedBooks, Integer> {

    public List<BorrowedBooks> findByUserUserId(Integer userId);

    public List<BorrowedBooks> findByBookId(Integer bookId);

}
