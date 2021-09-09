package SopraAJC.NotreProjet.services;

import java.util.ArrayList;
import java.util.List;

import model.Attaque;
import model.Batiment;
import model.Ressource;
import model.Session;

public class DraftService {
	
	//-------------------------------------------------------------------------------------------------
	//Batiment=========================================================================================
	//--------------------------------------------------------------------------------------------------
	public String toStringWithCost() {	
		
		return "";
	}
	
	//Attaque
	public void upgrade() {}
	
	//Defense
	public void upgrade() {}
	
	//Production
	protected Batiment upgrade(Batiment bati) {}
	
	//Bucheron
	public void upgrade() {}

	//Carrier
	public void upgrade() {}

	//Carriere
	public void upgrade() {}

	//Catapulte
	public void upgrade() {}
	
	//Fonderie
	public void transformation (String r1, int nb,  List <Ressource> re){}
	
	//Forteresse
	public void upgrade() {}
	
	//Four
	public void transformation (String r1, int nb,  List <Ressource> re){}
	
	//Merveille
	public void upgrade() {}
	
	//Mine
	public void upgrade() {}
	
	//Mineur
	public void upgrade() {}
	
	//Muraille
	public void upgrade() {}
	
	//Scierie
	public void upgrade() {}
	
	//Scorpion
	public void upgrade() {}

	
	
	//-------------------------------------------------------------------------------------------------
	//Ressource=========================================================================================
	//--------------------------------------------------------------------------------------------------	
	
	
	
	public void actuAchat(List <Ressource> cost) {}
	public void actuGain (int gain) {}
	
	
	//Charbon
	public void actuAchat(List<Ressource> cost) {}
	
	public void actuGain (int gain) {}
	
	//Cuivre
	public void actuAchat(List<Ressource> cost) {}
		
	public void actuGain (int gain) {}
	
	//Fer
	public void actuAchat(List<Ressource> cost) {}
			
	public void actuGain (int gain) {}
	
	//Or
	public void actuAchat(List<Ressource> cost) {}
			
	public void actuGain (int gain) {}
	
	//Pierre
	public void actuAchat(List<Ressource> cost) {}
				
	public void actuGain (int gain) {}
		
	//Minerais
	public void actuAchat(List<Ressource> cost) {}
			
	public void actuGain (int gain) {}
		
	//Bois
	public void actuAchat(List<Ressource> cost) {}
				
	public void actuGain (int gain) {}
	
	//-------------------------------------------------------------------------------------------------
	//Compte=========================================================================================
	//--------------------------------------------------------------------------------------------------
	
	//Joueur
	
	//Admin
	
	//
	
	//-------------------------------------------------------------------------------------------------
	//Partie=========================================================================================
	//--------------------------------------------------------------------------------------------------
	public int initPartie(int nbJoueur){}
	
	public void startPartie(){}
		
	public Session finDePartie(){}
	
	public void menuFinDePartie(Session vainqueur) {}
	
	
	//-------------------------------------------------------------------------------------------------
	//Session=========================================================================================
	//--------------------------------------------------------------------------------------------------
	
	public void construitBastide(){}
	
	public ArrayList<Batiment> actuDef() {}//Permet d'actualiser les points de defense du joueur ainsi que la liste des batiments du joueur (ATTENTION RENVOI UNE LISTE !!)
	
	public ArrayList <Batiment> actuAtt() {} //Permet d'actualiser les point d'attaque du joueur ainsi que la liste des batiments du joueur (ATTENTION RENVOI UNE LISTE !!)
	
	public boolean verification(Batiment batiment){} // Verification du nombre de ressources du joueur pour acheter un batiment (renvoie un bool)
	
	public void attaque(Session ennemi){} // Attaque de tous les batiments d'un autre joueur

	public void attaque(Session ennemi, Batiment batiment, double ptsAttaque){} // Attaque d'un batiment d'un autre joueur

	public void attaque (Session ennemi, Attaque batimentDAttaque, Batiment batimentDEfense) {}//Attaque d'un batiment d'un autre joueur par un batiment d'attaque

	public void transformation(String batiment,int nbRessource,String nomRessource){}

	public void constructBat(Batiment batiment) {} // Construction d'un batiment (ajout a la liste/actuAtt/ActuDef/ActuRessources)

	public void joueTour(){}
	
	public void piocherRessources(){}

	
	public void menuJoueur(){}

	public void afficheListeRessources() {}
	
	private void menuTransformation() {}
	
	public void menuAmeliorer(){}

	public boolean listeBatimentContains(String testedValue) {}
		
	public void displayOwnedConstWithUpdatePossibilities(){}
	
	public void displayOwnedConstWithUpdateNoPossibilities(){}
	
	public void menuConstruction(){}
	
	public static Batiment stringToBatiment(String batimentName){}
	
	public boolean getBatimentAttaque(){}

	public double choixBatimentAttaque(){}
	
	private double attaqueAvecTousBatiments() {}

	private double attaqueAvecUnBatiment(List<Integer> listeLigne) {	}

	public void displayBatimentAttaque(){}

	public void displayOwnedConstruction(){}

	public void displayConstructionPossibilites(){}
		
	public void displayConstructionNoPossibilites(){}
		
	public void menuAttaquer(){}
	
	public void menuAttaqueJoueur(double valeurAttaque){}

	public void menuAttaqueChoixBatiment(Session ennemi, double valeurAttaque){}

	
	public void attaqueJoueur(Session ennemi, double valeurAttaque){}

	
	public void menuFinDeTour(){}


	

	}

	






	
	
	
	
	
	


