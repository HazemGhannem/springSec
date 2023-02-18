package com.example.springsecurity.Repository;


import com.example.springsecurity.Entity.DetailEquipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailEquipeRespositry extends JpaRepository<DetailEquipe,Long> {
}
