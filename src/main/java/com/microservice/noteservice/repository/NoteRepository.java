package com.microservice.noteservice.repository;

import com.microservice.noteservice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByUserEmail(String userEmail);
}