package SopraAJC.NotreProjet.test;

import SopraAJC.NotreProjet.models.Admin;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.models.Partie;

public class Test {

	//	public static void main(String[] args) {
	//						
	//
	//		EntityManager em = Context.getInstance().getEmf().createEntityManager();
	//		
	//		Joueur j = new Joueur("login","pass");
	//		Partie pa = new Partie(100, true);
	//		Session s = new Session(false, pa, j);
	//		
	//		Catapulte c = new Catapulte();
	//		Merveille m = new Merveille();
	//		Scierie sc = new Scierie();
	//		Four f = new Four();
	//		Fonderie fo = new Fonderie();
	//
	//		s.getConstructions().add(c);
	//		s.getConstructions().add(m);
	//		s.getConstructions().add(f);
	//		s.getConstructions().add(sc);
	//		s.getConstructions().add(fo);
	//		
	//		Bois b = new Bois(2);
	//		Pierre p = new Pierre(3);
	//		Gold g = new Gold (5);
	//		
	//		s.getRessources().add(g);
	//		s.getRessources().add(p);
	//		s.getRessources().add(b);
	//		
	//
	//		em.getTransaction().begin();
	//
	////		Context.getInstance().getDaoC().insert(j);
	////		Context.getInstance().getDaoP().insert(pa);
	////
	////
	////		Context.getInstance().getDaoB().insert(m);
	////		Context.getInstance().getDaoB().insert(f);
	////		Context.getInstance().getDaoB().insert(sc);
	////		Context.getInstance().getDaoB().insert(c);
	////		Context.getInstance().getDaoB().insert(fo);
	//
	//		Context.getInstance().getDaoS().update(s);
	//
	//		em.getTransaction().commit();
	//		
	//		em.close();
	//		
	//		
	//		
	//		Context.getInstance().closeEmf();
	//	}
//
//	static Compte connected=null;
//
//	public static void main(String[] args) 
//	{	
//		menuPrincipal();
//	
//	}
//
//	public static void menuPrincipal() {
//
//		System.out.println("\nAppli Building Game");
//		System.out.println("1- Se connecter");
//		System.out.println("2- Fermer l'appli");
//		int choix = Context.saisieInt("Choisir un menu");
//		switch(choix) 
//		{
//		case 1 : seConnecter();break;
//		case 2 : System.exit(0);break;
//		}
//
//		menuPrincipal();
//	}

//	public static void seConnecter() {
//		String compteCree = Context.saisieString("\nAvez-vous deja cree un compte ? (y/n)");
//
//		if(compteCree.equalsIgnoreCase("y"))
//		{
//			String login = Context.saisieString("\nSaisir login");
//			String password = Context.saisieString("Saisir password");
//			connected = Context.getInstance().getDaoC().seConnecter(login, password);
//
//			if(connected instanceof Admin) 
//			{
//				menuAdmin();
//			}
//			else if(connected instanceof Joueur) 
//			{
//				menuJoueur();
//			}
//			else 
//			{
//				System.out.println("Identifiants invalides !");
//			}
//		}
//		else
//		{
//			String login = Context.saisieString("\nSaisissez votre login : ");
//			String password = Context.saisieString("\nSaisissez votre mot de passe : ");
//			String prenom = Context.saisieString("\nVeuillez indiquez votre prenom : ");
//			String nom = Context.saisieString("\nVeuillez indiquez votre nom : ");
//			String surnom = Context.saisieString("\nChoisissez le nom sous lequel vous souhaitez �tre reconnu durant la partie : ");
//			Joueur j = new Joueur(login, password, prenom, nom, surnom);
//			Context.getInstance().getDaoC().insert(j);
//
//			connected = Context.getInstance().getDaoC().seConnecter(login,password);
//
//			if(connected instanceof Admin) 
//			{
//				menuAdmin();
//			}
//			else if(connected instanceof Joueur) 
//			{
//				menuJoueur();
//			}
//			else 
//			{
//				System.out.println("Identifiants invalides !");
//			}
//		}
//	}
//
//	public static void menuAdmin() {
//		System.out.println("\nMenu Admin");
//		System.out.println("1- ");
//		System.out.println("2- ");
//		System.out.println("3- Se deconnecter");
//
//		int choix = Context.saisieInt("Choisir un menu");
//
//		switch(choix) 
//		{
//		case 1 : ;break;
//		case 2 : ;break;
//		case 3 : connected=null;menuPrincipal();break;
//		}
//		menuAdmin();
//	}
//
//	public static void menuJoueur() {
//		System.out.println("\nMenu Joueur");
//		System.out.println("1- Demarrer une nouvelle partie");
//		System.out.println("2- Reprendre une partie existante");
//		System.out.println("3- Se deconnecter");
//		int choix = Context.saisieInt("Choisir un menu");
//
//		switch(choix) 
//		{
//		case 1 : nouvPartie();break;
//		case 2 : loadPartie();break;
//		case 3 : connected=null;menuPrincipal();break;
//		}
//		menuJoueur();
//	}
//
//	public static Partie nouvPartie()
//	{
//		int nbJoueur = Context.saisieInt("\nChoisissez le nombre de joueurs (entre 2 et 4)");
//		//int nbTour = saisieInt("\nChoisissez le nombre de tours pour votre partie (entre 5 et 10)");
//		int nbTour = 100;
//
//
//		connected.toString();
//
//		Partie nouvPartie = new Partie();
//		Partie p = Context.getInstance().getDaoP().insert(nouvPartie);
//		p.initPartie(nbJoueur);
//		// session cr�e dans le initPartie
//		//		Session s = new Session(false, p,connected);
//		//		Context.getInstance().getDaoS().insert(s);
//
//		menuPartie(p);
//		return p;
//	}
//
//	public static void menuPartie(Partie p)
//	{
//		System.out.println("\nVous avez demarre une nouvelle partie de 'Notre Projet' !\n ");
//		System.out.println("Etes-vous pret a vous entretuer ?");
//		System.out.println("1- Oui je vais tous vous demonter !! ");
//		System.out.println("2- Laisse-moi quelques minutes stp");
//		System.out.println("3- Se deconnecter");
//		int choix = Context.saisieInt("Choisir un menu");
//
//		switch(choix) 
//		{
//		case 1 : p.startPartie();;break;
//		case 2 : menuPartie(p);break;
//		case 3 : connected=null;menuPrincipal();break;
//		}
//	}

	
	public static void loadPartie()
	{

	}

}

