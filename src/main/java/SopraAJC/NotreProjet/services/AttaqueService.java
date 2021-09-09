package SopraAJC.NotreProjet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.exceptions.AttaqueException;
import SopraAJC.NotreProjet.exceptions.TargetException;
import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionKey;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;

public class AttaqueService {

	@Autowired
	SessionRepository sRepo;
	
	@Autowired
	SessionBatimentRepository sbRepo;
	
	public Session getSession(SessionKey id) {
		return sRepo.findById(id).get();
	}
	
	public Session getSessionByPartieAndCompte(Partie partie, Compte compte) {
		return sRepo.findByPartieAndCompte(partie, compte).get();
	}
	
	public List<SessionBatiment> getBatimentAttaqueBySession(Session session){
		return sbRepo.findBySessionAndBatimentAttaque(session);
	}
	
	public List<SessionBatiment> getBatimentBySession(Session session){
		return sbRepo.findBySession(session);
	}
	
	public void attackAllBatimentWithAllBatiment(Session attack, Session target) {
		List<SessionBatiment> attackBatiment = sbRepo.findBySessionAndBatimentAttaque(attack);
		double attaque = 0;
		for(SessionBatiment sb : attackBatiment) {
			if(!sb.isUsed()) {
				attaque+=sb.getPointsDAttaque();
			}
			sb.setUsed(true);
			sbRepo.save(sb);
		}
		if(attaque == 0) {
			// Impossible d'attaquer
			// Bloquer le bouton d'attaque
			throw new AttaqueException();
		}
		
		List<SessionBatiment> targetBatiment = sbRepo.findBySession(target);
		double degats = attaque/targetBatiment.size();
		for(SessionBatiment sb : targetBatiment) {
			sb.setPointsDeVie(sb.getPointsDeVie()-degats);
			sbRepo.save(sb);
		}
	}
	
	public void attackAllBatimentWithOneBatiment(Session attack, Batiment attackBatiment, Session target) {
		Optional<SessionBatiment> opt = sbRepo.findBySessionAndBatiment(attack, attackBatiment);
		if(opt.isPresent()) {
			SessionBatiment attackBat = opt.get();
			if(attackBat.isUsed()) {
				throw new AttaqueException();
			}
			attackBat.setUsed(true);
			sbRepo.save(attackBat);
			
			List<SessionBatiment> targetBatiment = sbRepo.findBySession(target);
			double degats = attackBat.getPointsDAttaque()/targetBatiment.size();
			for(SessionBatiment sb : targetBatiment) {
				sb.setPointsDeVie(sb.getPointsDeVie()-degats);
				sbRepo.save(sb);
			}
		}
		throw new AttaqueException();
	}
	
	public void attackOneBatimentWithAllBatiment(Session attack, Session target, Batiment targetBatiment) {
		List<SessionBatiment> attackBatiment = sbRepo.findBySessionAndBatimentAttaque(attack);
		double attaque = 0;
		for(SessionBatiment sb : attackBatiment) {
			if(!sb.isUsed()) {
				attaque+=sb.getPointsDAttaque();
			}
			sb.setUsed(true);
			sbRepo.save(sb);
		}
		if(attaque == 0) {
			// Impossible d'attaquer
			// Bloquer le bouton d'attaque
			throw new AttaqueException();
		}
		
		Optional<SessionBatiment> opt = sbRepo.findBySessionAndBatiment(target, targetBatiment);
		if(opt.isPresent()) {
			SessionBatiment targetBat = opt.get();
			targetBat.setPointsDeVie(targetBat.getPointsDeVie()-attaque);
			sbRepo.save(targetBat);
		}
		throw new TargetException();
	}
	
	public void attackOneBatimentWithOneBatiment(Session attack, Batiment attackBatiment, Session target, Batiment targetBatiment) {
		Optional<SessionBatiment> optAttack = sbRepo.findBySessionAndBatiment(attack, attackBatiment);
		if(optAttack.isEmpty()) {
			throw new AttaqueException();
		}
		SessionBatiment attackBat = optAttack.get();
		if(attackBat.isUsed()) {
			throw new AttaqueException();
		}
		attackBat.setUsed(true);
		sbRepo.save(attackBat);
		
		Optional<SessionBatiment> optTarget = sbRepo.findBySessionAndBatiment(target, targetBatiment);
		if(optTarget.isEmpty()) {
			throw new TargetException();
		}
		SessionBatiment targetBat = optTarget.get();
		targetBat.setPointsDeVie(targetBat.getPointsDeVie()-attackBat.getPointsDAttaque());
		sbRepo.save(targetBat);
	}
}
