package com.example.springsecurity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage  implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id; // Cl
        private String content;
        @ManyToOne

        private Etudiant sender;
        @ManyToOne

        private Etudiant reciver;

        private LocalDateTime senddate;



}
