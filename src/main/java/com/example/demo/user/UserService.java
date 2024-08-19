package com.example.demo.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
       user.setStatus(Status.ONLINE);
       userRepository.save(user);
    }
    public void disconnect(User user){
        User storedUser = userRepository.findById(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        storedUser.setStatus(Status.OFFLINE);
        userRepository.save(storedUser);
    }
    public List<User> getAllOnlineUsers(){
        return userRepository.findAllByStatus(String.valueOf(Status.ONLINE));
    }
}
