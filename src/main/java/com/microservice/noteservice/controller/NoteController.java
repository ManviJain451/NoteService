package com.microservice.noteservice.controller;

import com.microservice.noteservice.dto.EncryptedNoteRequest;
import com.microservice.noteservice.model.Note;
import com.microservice.noteservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public Note saveNote(@RequestBody EncryptedNoteRequest request) throws Exception {
        return noteService.saveDecryptedNote(
                request.getEncryptedContent(),
                request.getEncryptedAESKey(),
                request.getTitle(),
                request.getUserEmail()
        );
    }

    @GetMapping("/{email}")
    public List<Note> getUserNotes(@PathVariable String email) {
        return noteService.getUserNotes(email);
    }
}

