package com.capstone.tech.repositories;

import com.capstone.tech.models.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepo extends CrudRepository<Availability, Long> {
    List<Availability> findAll();
}
