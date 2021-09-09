package SopraAJC.NotreProjet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;

public class EvenementPartieService {

	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	PartieRepository partieRepo;
	
	public void finTour(Session session) 
	{
		session.setaJoueLeTours(true);
		session.setTourEnCours(false);
		Partie partie = session.getId().getPartie();
		int index = partie.getSessions().indexOf(session);
		List<Session> sessions = partie.getSessions();
		if (index == sessions.size()) {
			sessions.get(0).setaJoueLeTours(false);
			sessions.get(0).setTourEnCours(true);
		} else {
			sessions.get(index+1).setaJoueLeTours(false);
			sessions.get(index+1).setTourEnCours(true);
		}
	}
	
	public Session finDePartie(Partie partie)
	{
		Session vainqueur = null;
		double somme = 0;
		for(Session session : partie.getSessions())
		{
			
			for(SessionBatiment sb: session.getSessionBatiment())
			{
				somme+= sb.getPointsDeVie() ;
			}
			session.setDef(somme);
		}
		for(Session session : partie.getSessions())
		{
			if(somme-session.getDef()==0)
			{
				vainqueur = session;
			}
		
			else 
			{
				for(SessionBatiment sb : session.getSessionBatiment())
				{
					if(sb.getBatiment().getNom().equals("Merveille"))
					{
						if(sb.getLevel()==5)
						{
							vainqueur = session;
						}
					}
				}
			}
		}
		return vainqueur;
	}
	
	public Session deadPlayer(Partie partie) {
		Session mort = null;
		for(Session session : partie.getSessions())
		{
			double somme = 0;
			for(SessionBatiment sb: session.getSessionBatiment())
			{
				somme+= sb.getPointsDeVie() ;
			}
			if (somme <=0) {
				mort = session;
				sessionRepo.delete(session);
			}
		}
		return mort;
	}
	
	
}
