package SopraAJC.NotreProjet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.models.SessionRessourceKey;



public interface SessionRessourceRepository  extends JpaRepository <SessionRessource, SessionRessourceKey>{
	
	@Query("select distinct s from SessionRessource s where s.id.session=:session")	
	List<SessionRessource> findBySession(@Param(value = "session") Session session);
	
//	Optional<SessionRessource> findBySessionAndRessource(Session session, Ressource ressource);
//  n'est plus nécessaire car findById fait la même chose 
//	exemple : 
//	SessionRessourceKey sessionRessourceKey = new SessionRessourceKey(session, ressource);
//	findById(sessionRessourceKey);
}
