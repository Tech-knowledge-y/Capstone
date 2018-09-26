package com.capstone.tech.repositories;

import com.capstone.tech.models.UserAvailability;
import org.springframework.data.repository.CrudRepository;

public interface UserAvailabilityRepo extends CrudRepository<UserAvailability, Long> {
}
