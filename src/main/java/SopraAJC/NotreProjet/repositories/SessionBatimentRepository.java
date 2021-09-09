package SopraAJC.NotreProjet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;



public interface SessionBatimentRepository extends JpaRepository<SessionBatiment, Integer> {

	Optional<SessionBatiment> findBySessionAndBatiment(Session session, Batiment batiment);
	
	List<SessionBatiment> findBySession(Session session);

	@Query("select s from SessionBatiment s where s.session=:session and  s.batiment in(select p from Production p)")
	List<SessionBatiment> findBySessionAndBatimentProduction(@Param(value = "session") Session session);
	
	@Query("select s from SessionBatiment s where s.session=:session and  s.batiment in(select p from Attaque p)")
	List<SessionBatiment> findBySessionAndBatimentAttaque(@Param(value = "session") Session session);

	@Query("select s from SessionBatiment s where s.session=:session and  s.batiment in(select p from Defense p)")
	List<SessionBatiment> findBySessionAndBatimentDefense(@Param(value = "session") Session session);

	
//	@Query("select s from SessionBatiment s where s.batiment")
//	List<SessionBatiment> findByTypeBatiment(@Param(value="type") Attaque type);

//	@Query("select s form SessionBatiment s left join fetch s.batiment where s.session=:session and batiment.type_batiment=:type")
//	List<SessionBatiment> findBySessionAndTypeBatiment(@Param(value="type") String type, @Param(value="session") Session session);

//	@Query("select s from SessionBatiment s where s.session=:session "
//			+ "and  s.batiment in(select p from :type p)")
//	List<SessionBatiment> findBySessionAndBatimentType(
//			@Param(value = "session") Session session,
//			@Param(value = "type") String type);
	


}
