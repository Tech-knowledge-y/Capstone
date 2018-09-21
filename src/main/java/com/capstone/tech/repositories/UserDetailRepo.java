package com.capstone.tech.repositories;

import com.capstone.tech.models.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends CrudRepository<UserDetail, Long> {
}
