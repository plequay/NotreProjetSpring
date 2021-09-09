package SopraAJC.NotreProjet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Compte;

public interface CompteRepository extends JpaRepository <Compte, Integer> {

	Optional<Compte> findBySurnom(String surnom);

    Optional<Compte> findByLoginAndPassword(String login, String password);

}
