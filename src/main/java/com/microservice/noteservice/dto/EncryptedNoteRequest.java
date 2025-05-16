package com.microservice.noteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncryptedNoteRequest {
    private String encryptedContent;
    private String encryptedAESKey;
    private String title;
    private String userEmail;
}

