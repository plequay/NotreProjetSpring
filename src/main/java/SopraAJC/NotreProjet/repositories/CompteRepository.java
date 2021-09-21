package SopraAJC.NotreProjet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Compte;

public interface CompteRepository extends JpaRepository <Compte, Long> {

//	Optional<Compte> findBySurnom(String surnom); l'attribut surnom n'existe plus

//    Optional<Compte> findByLoginAndPassword(String login, String password); Login a été remplacé par Username

    Optional<Compte> findByUsername(String username);

    Optional<Compte> findByUsernameAndPassword(String username, String Password);

}
