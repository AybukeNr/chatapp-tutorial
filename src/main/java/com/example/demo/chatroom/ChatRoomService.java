package com.example.demo.chatroom;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    public Optional<String> getChatRoomId(String sendereId, String receivereId, boolean createNewRoomIfNotExists) {
       return chatRoomRepository.findBySenderAndRecipentId(sendereId,receivereId).map(ChatRoom::getChatId)
                .or(()-> {
                    if(createNewRoomIfNotExists){
                        String chatId = createChatId(sendereId, receivereId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });

    }

    private String createChatId(String sendereId, String receivereId) {
        String chatId = String.format("%s_%s", sendereId, receivereId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(sendereId)
                .receiverId(receivereId)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(receivereId)
                .receiverId(sendereId)
                .build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;

    }



}
