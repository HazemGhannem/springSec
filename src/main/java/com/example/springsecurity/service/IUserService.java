package com.example.springsecurity.service;

import com.example.springsecurity.Entity.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Etudiant saveUser(Etudiant user);

    Etudiant UserOff(String email);

    Etudiant IsUserActive(String email);

    Etudiant findById(Long id);

    Optional<Etudiant> findByEmail(String email);

    @Transactional
    void makeAdmin(String email);

    @Transactional
    void MakeUserRole(String email);

    @Transactional
    void BannedUser(String email, Boolean bann);

    @Transactional
    void VerifyUser(String email, Boolean verif);



    Page<Etudiant> ShowAllStudent(Optional<Integer> page, Optional<String> sortBy);


    List<Etudiant> show();

    Etudiant FindbyId(Long id);

    Etudiant UpdatEtudiant(Etudiant e, Long id);

    Etudiant UpdatEtudiantAdmin(Etudiant e, Long id);

    @Async
    String SendSms(String Phone, String message);

    int Statistic();

    int getusernumber();
}
