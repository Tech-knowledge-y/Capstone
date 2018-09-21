package com.capstone.tech.repositories;

import com.capstone.tech.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
