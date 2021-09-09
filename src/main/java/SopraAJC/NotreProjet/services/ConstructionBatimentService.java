package SopraAJC.NotreProjet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;

public class ConstructionBatimentService {

	@Autowired
	BatimentRepository batRepo;

	@Autowired
	SessionRepository sessionRepo;

	@Autowired
	SessionRessourceRepository sessionRessRepo;

	@Autowired
	SessionBatimentRepository sessionBatRepo;

	public boolean verificationConstructible(Batiment batiment, Session session) // Verification du nombre de ressources
																					// du joueur pour acheter un
																					// batiment (renvoie un bool)
	{
		for (SessionRessource sr : sessionRessRepo.findBySession(session)) {
			for (CoutBatiment cb : batiment.getCoutBatiment()) {
				if (cb.getId().getRessource().getNom().equals(sr.getId().getRessource().getNom())
						&& cb.getCout() > sr.getQuantite()) {
					return false;
				}
			}
		}
		return true;
	}

	public List<Batiment> Constructible(Session session) {

		List<Batiment> listBat = new ArrayList<Batiment>();
		List<Batiment> allBat = batRepo.findAll();
		for (Batiment bat : allBat) {
			if (verificationConstructible(bat, session)) {
				listBat.add(bat);
			}
		}

		return listBat;
	}

	public void constructBat(Batiment batiment, Session session) // Construction d'un batiment (ajout a la
																	// liste/actuAtt/ActuDef/ActuRessources)
	{
		SessionBatiment sb = new SessionBatiment(session, batiment,
				batRepo.findByNom(batiment.getNom()).get().getPointsDefense());
		List<SessionRessource> listSr = sessionRessRepo.findBySession(session);

		for (SessionRessource sr : listSr) 
		{
			for (CoutBatiment cb : batiment.getCoutBatiment())
				if (cb.getId().getRessource().getNom().equals(sr.getId().getRessource().getNom())) {
					sr.setQuantite(sr.getQuantite() - cb.getCout());
				}
			sessionRessRepo.save(sr);
		}
		sessionBatRepo.save(sb);
	}

	public boolean verificationAmeliorable(Batiment batiment, Session session) // Verification du nombre de ressources
																				// du joueur pour acheter un batiment
																				// (renvoie un bool)
	{
		SessionBatiment sessionBat = sessionBatRepo.findBySessionAndBatiment(session, batiment).get();
		int lvl = sessionBat.getLevel();
		for (SessionRessource sr : sessionRessRepo.findBySession(session)) {
			for (CoutBatiment cb : batiment.getCoutBatiment()) {
				if (cb.getId().getRessource().getNom().equals(sr.getId().getRessource().getNom())
						&& (lvl * cb.getCout()) > sr.getQuantite()) {
					return false;
				}
			}
		}
		return true;
	}

	public List<SessionBatiment> Ameliorable(Session session) {

		List<SessionBatiment> listBat = new ArrayList<SessionBatiment>();
		List<SessionBatiment> allBat = sessionBatRepo.findBySession(session);
		for (SessionBatiment bat : allBat) {
			if (verificationAmeliorable(bat.getBatiment(), session)) {
				listBat.add(bat);
			}
		}
		return listBat;
	}

	public void ameliorationBat(SessionBatiment sessionBat, Session session) {

		int lvl = sessionBat.getLevel();
		// Amelioration du batiment
		sessionBat.setLevel(lvl + 1);
		sessionBat.setPointsDAttaque(2 * lvl * sessionBat.getPointsDAttaque());
		sessionBat.setPointsDeVie(2 * lvl * sessionBat.getPointsDeVie());

		// Suppression des ressources de la session
		for (SessionRessource sr : session.getSessionRessource()) 
		{
			for (CoutBatiment cb : sessionBat.getBatiment().getCoutBatiment())
				if (cb.getId().getRessource().getNom().equals(sr.getId().getRessource().getNom())) {
					sr.setQuantite(sr.getQuantite() - (lvl * cb.getCout()));
				}
			sessionRessRepo.save(sr);
		}

	}

}
