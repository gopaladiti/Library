package com.example.library.repositories;

import com.example.library.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    public UserInfo findByUserId(int user);

    public UserInfo findByUsername(String name);
}
