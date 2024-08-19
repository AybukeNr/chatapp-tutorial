package com.example.demo.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService messageService;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage message = messageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(),"/queue/messages",new ChatNotification(
                message.getId(), message.getMessage(), message.getReceiverId(), message.getSenderId()
        ));
    }

    @GetMapping("/messages{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,@PathVariable String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
