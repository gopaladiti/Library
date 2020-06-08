package com.example.library.repositories;

import com.example.library.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {
}
