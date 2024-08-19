package com.example.demo.chat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class ChatMessage {

    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
    private LocalDateTime date;
}
