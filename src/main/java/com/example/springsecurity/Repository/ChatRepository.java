package com.example.springsecurity.Repository;

import com.example.springsecurity.Entity.ChatMessage;
import com.example.springsecurity.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findAllBySender(Etudiant e);

    List<ChatMessage> findBySenderIdEtudiantAndReciverIdEtudiantOrderBySenddateDesc(Long t,Long y);

}
