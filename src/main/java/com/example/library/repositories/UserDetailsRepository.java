package com.example.library.repositories;

import com.example.library.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {

    public UserDetails findByUserId(int user);

    public UserDetails findByUsername(String name);
}
