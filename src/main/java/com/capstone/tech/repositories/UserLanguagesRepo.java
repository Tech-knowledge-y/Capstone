package com.capstone.tech.repositories;

import com.capstone.tech.models.Languages;
import com.capstone.tech.models.UserLanguages;
import org.springframework.data.repository.CrudRepository;

public interface UserLanguagesRepo extends CrudRepository<UserLanguages, Long> {
}
