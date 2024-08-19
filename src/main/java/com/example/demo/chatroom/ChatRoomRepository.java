package com.example.demo.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderAndRecipentId(String sender, String recipentId);
}
