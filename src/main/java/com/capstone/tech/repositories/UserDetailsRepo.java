package com.capstone.tech.repositories;

import com.capstone.tech.models.UserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDetailsRepo extends CrudRepository<UserDetails, Long> {
        UserDetails save (UserDetails userDetails);

        List<UserDetails> findAll();

//        UserDetails deleteUserDetailsByFNameMatches (String userDetails);
//
//        UserDetails deleteUserDetailsByLNameMatches (String userDetails);
//
//        UserDetails findAllByCity (String userDetails);
//
//        UserDetails findAllByLName (String userDetails);
}
