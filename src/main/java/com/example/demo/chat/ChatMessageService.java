package com.example.demo.chat;


import com.example.demo.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(),chatMessage.getReceiverId(),true)
                .orElseThrow(RuntimeException::new);
        chatMessage.setId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId,String recieverId){
        String chatId = chatRoomService.getChatRoomId(senderId,recieverId,true).orElseThrow();
        List<ChatMessage> chats = chatMessageRepository.findByChatId(chatId);
        return chats;
    }



}
