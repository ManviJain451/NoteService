package com.microservice.noteservice.service;


import com.microservice.noteservice.config.RSAKeyConfig;
import com.microservice.noteservice.model.Note;
import com.microservice.noteservice.repository.NoteRepository;
import com.microservice.noteservice.utils.CryptoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repo;
    private final RSAKeyConfig rsaKeyConfig;

    public Note saveDecryptedNote(String encryptedContent, String encryptedAESKey, String title, String userEmail) throws Exception {

        System.out.println(encryptedContent);
        System.out.println(encryptedAESKey);
        System.out.println(title);
        System.out.println(userEmail);

        String decryptedAESKey = CryptoUtils.decryptAESKeyWithRSA(encryptedAESKey, rsaKeyConfig.getPrivateKey());
        String content = CryptoUtils.decryptDataWithAES(encryptedContent, decryptedAESKey);

        Note note = new Note();
        note.setUserEmail(userEmail);
        note.setTitle(title);
        note.setContent(content);
        return repo.save(note);
    }

    public List<Note> getUserNotes(String userEmail) {
        return repo.findByUserEmail(userEmail);
    }
}
