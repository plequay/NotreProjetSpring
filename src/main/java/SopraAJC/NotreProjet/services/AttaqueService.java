package SopraAJC.NotreProjet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
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
				sb.setUsed(true);
			}
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
			sb.setPointsDeVie(Math.round(sb.getPointsDeVie()-degats));
			if(sb.getPointsDeVie()<=0) {
				sbRepo.delete(sb);
			}
			else 
			{
				sbRepo.save(sb);
			}
			
		}
	}
	
	public void attackAllBatimentWithOneBatiment(Session attack, Integer idBatAtt, Session target) {
		Optional<SessionBatiment> opt = sbRepo.findById(idBatAtt);
		if(opt.isPresent()) {
			SessionBatiment attackBat = opt.get();
			if(attackBat.isUsed()) {
				throw new AttaqueException();
			}
			else {
				attackBat.setUsed(true);
			}
			sbRepo.save(attackBat);
			
			List<SessionBatiment> targetBatiment = sbRepo.findBySession(target);
			double degats = attackBat.getPointsDAttaque()/targetBatiment.size();
			for(SessionBatiment sb : targetBatiment) {
				sb.setPointsDeVie(Math.round(sb.getPointsDeVie()-degats));
				if(sb.getPointsDeVie()<=0) {
					sbRepo.delete(sb);
				}
				else 
				{
					sbRepo.save(sb);
				}
			}
		}
		else{
			throw new AttaqueException();
		}
	}
	
	public void attackOneBatimentWithAllBatiment(Session attack, Session target, Integer idBatTar) {
		List<SessionBatiment> attackBatiment = sbRepo.findBySessionAndBatimentAttaque(attack);
		double attaque = 0;
		for(SessionBatiment sb : attackBatiment) {
			if(!sb.isUsed()) {
				attaque+=sb.getPointsDAttaque();
				sb.setUsed(true);
			}
			sbRepo.save(sb);
		}
		if(attaque == 0) {
			// Impossible d'attaquer
			// Bloquer le bouton d'attaque
			throw new AttaqueException();
		}
		
		Optional<SessionBatiment> opt = sbRepo.findById(idBatTar);
		if(opt.isPresent()) {
			SessionBatiment targetBat = opt.get();
			targetBat.setPointsDeVie(targetBat.getPointsDeVie()-attaque);
			if(targetBat.getPointsDeVie()<=0) {
				sbRepo.delete(targetBat);
			}
			else 
			{
				sbRepo.save(targetBat);
			}
		}
		else {
			throw new TargetException();
		}
	}
	
	public void attackOneBatimentWithOneBatiment(Session attack, Integer idBatAtt, Session target, Integer idBatTar) {
		Optional<SessionBatiment> optAttack = sbRepo.findById(idBatAtt);
		if(!optAttack.isPresent()) {
			throw new AttaqueException();
		}
		SessionBatiment attackBat = optAttack.get();
		if(attackBat.isUsed()) {
			throw new AttaqueException();
		}
		attackBat.setUsed(true);
		sbRepo.save(attackBat);
		
		Optional<SessionBatiment> optTarget = sbRepo.findById(idBatTar);
		if(!optTarget.isPresent()) {
			throw new TargetException();
		}
		SessionBatiment targetBat = optTarget.get();
		targetBat.setPointsDeVie(targetBat.getPointsDeVie()-attackBat.getPointsDAttaque());
		if(targetBat.getPointsDeVie()<=0) {
			sbRepo.delete(targetBat);
		}
		else 
		{
			sbRepo.save(targetBat);
		}
	}
}
