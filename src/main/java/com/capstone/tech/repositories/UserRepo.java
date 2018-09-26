package com.capstone.tech.repositories;

import com.capstone.tech.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);

    @Query(value = "SELECT COUNT(user_id) from posts WHERE user_id = ?1", nativeQuery = true)
    Long findNumberOfPostsById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(user_id) from comments WHERE user_id = ?1", nativeQuery = true)
    Long findNumberOfCommentsById(@Param("id") Long id);
}
