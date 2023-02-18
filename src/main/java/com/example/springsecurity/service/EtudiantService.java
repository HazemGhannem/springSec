package com.example.springsecurity.service;

import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class EtudiantService implements IEtudiantService{
    @Autowired
    private EtudiantRepository repository;

}
