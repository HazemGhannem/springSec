package com.example.springsecurity.Repository;

import com.example.springsecurity.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TachRepository extends JpaRepository<Tache,Long> {
}
