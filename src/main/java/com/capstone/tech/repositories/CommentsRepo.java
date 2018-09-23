package com.capstone.tech.repositories;

import com.capstone.tech.models.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CommentsRepo extends CrudRepository<Comments, Long> {
}
