package SopraAJC.NotreProjet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionKey;


public interface SessionRepository extends JpaRepository <Session, SessionKey>{

	/*@Query("select distinct s from Session s left join fetch s.sessionBatiment.batiment left join fetch s.sessionRessource.ressource where s.id =:id")
	Optional<Session> findByIdWithRessourcesAndBatiments(@Param("id") Integer id);*/
	
	@Query("select distinct s from Session s where s.id.partie=:partie")	
	List<Session> findByPartie(@Param(value = "partie") Partie partie);
	
	@Query("select distinct s from Session s where s.id.compte=:compte")	
	List<Session> findByCompte(@Param(value = "compte") Compte compte);
	
	@Query("select distinct s from Session s left join fetch s.sessionBatiment left join fetch s.sessionRessource where s.id.partie =:partie")
	List<Session> findByPartieWithRessourcesAndBatiments(@Param(value="partie") Partie partie);
	
}
