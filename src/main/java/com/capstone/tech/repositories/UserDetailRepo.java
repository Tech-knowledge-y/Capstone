package com.capstone.tech.repositories;


import com.capstone.tech.models.UserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDetailRepo extends CrudRepository<UserDetail, Long> {
        UserDetail save (UserDetail userDetails);

        List<UserDetail> findAll();

        @Query(nativeQuery = true,
                value="SELECT * FROM user_details ud WHERE ud.user_id = ?1")
        UserDetail findByUserId(long id);
}
