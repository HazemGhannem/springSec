package com.example.springsecurity.service;

import com.example.springsecurity.Entity.ChatMessage;
import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.Repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatRepository chatRepository;
    private IUserService userService;
    public ChatMessage update( ChatMessage e,  Long ids,Long id) {
        return chatRepository.findById(e.getId())
                .map(element -> {
                    element.setSender(userService.FindbyId(ids));
                    element.setReciver(userService.FindbyId(id));
                    return chatRepository.save(element);
                })
                .orElseGet(() -> {
                    e.setId(id);
                    return chatRepository.save(e);
                });
    }
    public ChatMessage add(ChatMessage chat){

        chat.setSenddate(LocalDateTime.now());
        return chatRepository.save(chat);
    }
    public List<ChatMessage> get(Etudiant e){
        return chatRepository.findAllBySender(e);
    }
    public List<ChatMessage>getbetwinsenderandreciver(Long ids,Long idr){
        return chatRepository.findBySenderIdEtudiantAndReciverIdEtudiantOrderBySenddateDesc(ids,idr);
    }

}
