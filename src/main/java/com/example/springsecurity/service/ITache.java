package com.example.springsecurity.service;

import com.example.springsecurity.Entity.Tache;

import java.util.List;

public interface ITache {
    Tache CreateTache(Tache T);

    Boolean settachtostudiant(Long id, Long idt);

    Tache GeTache(Long T);

    List<Tache> GetAllTaches();

    void DeleteTache(Long T);

    Tache UpdateTache(Tache e, Long id);
}
