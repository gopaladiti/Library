package com.example.library.repositories;

import com.example.library.model.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Books, Integer> {
}
