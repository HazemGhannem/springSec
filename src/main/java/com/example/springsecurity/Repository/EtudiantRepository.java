package com.example.springsecurity.Repository;


import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.Entity.Rolee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByNomEAndPrenomE(String nom,String prenom);
    @Query(value = "Select id_etudiant,nomE,prenomE,opp,id_Depart from Etudiant  WHERE  id_Depart = :idDepart ",
    nativeQuery = true)
     List<Etudiant> findwithsamedepa(@Param("idDepart") Long idDepart);


    Optional<Etudiant> findByEmail(String email);

    @Modifying
    @Query("UPDATE Etudiant set role = :Role WHERE email= :email")
    void UpdateUserRole(@Param("email")String email,@Param("Role") Rolee Role);

    @Modifying
    @Query("UPDATE Etudiant set role = :Role WHERE email= :email")
    void UpdateUserRoleUser(@Param("email")String email,@Param("Role") Rolee Role);

    @Modifying
    @Query("Update Etudiant  set banned = :banned WHERE email= :email ")
    void BannUser(@Param("email")String email,@Param("banned") Boolean banned);

    @Modifying
    @Query("Update Etudiant  set verified = :verified WHERE email= :email ")
    void VerifyUser(@Param("email")String email,@Param("verified") Boolean verified);

    @Query(value = "select count(createdate) from Etudiant where EXTRACT(YEAR FROM createdate)= :year",nativeQuery = true)
    int finddata(@Param("year") Long year);
}
