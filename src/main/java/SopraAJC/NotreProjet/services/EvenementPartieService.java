package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;

public class EvenementPartieService {

	public void finTour() 
	{
		
	}
	
	public Session finDePartie(Partie partie)
	{
		Session vainqueur = null;
		
		for(Session session : partie.getSessions())
		{
			int somme = 0;
			for(SessionBatiment sb: session.getSessionBatiment())
			{
				somme+= sb.getPointsDeVie() ;
			}

			if(somme-joueur.getDef()==0)
			{
				this.partieEnCours = false;
				vainqueur = joueur;
			}

			else 
			{
				for(Batiment b : joueur.getConstructions())
				{
					if(b.toStringName().equals("Merveille"))
					{
						if(b.getLevel()==5)
						{
							this.partieEnCours = false;
							vainqueur = joueur;
						}
					}
				}
			}
		}
		return vainqueur;
	}
	
	public void deadPlayer() {
		
	}
	
	
}
