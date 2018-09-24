package com.capstone.tech.repositories;

import com.capstone.tech.models.Languages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguagesRepo extends CrudRepository<Languages, Long> {
//
//    @Query("SELECT l.language FROM Languages l JOIN UserLanguages ul ON ul.language_id = l.id WHERE ul.user_id = ?1")
//    List<Languages> findAllById(long id);
}
