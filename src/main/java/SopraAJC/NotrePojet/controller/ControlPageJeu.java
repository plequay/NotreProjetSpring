package SopraAJC.NotrePojet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;

@Controller
@RequestMapping("/pagejeu")
public class ControlPageJeu {
		
	@Autowired
	SessionRepository sr;
	
	@Autowired 
	PartieRepository pr;
	
	@GetMapping("")
	public String init(HttpSession session) {
		Partie partie = pr.findById(1).get();
		if(session.getAttribute("players")==null) {
			session.setAttribute("players",sr.findByPartieWithRessourcesAndBatiments(partie));
		}
		
		List<Session> players = (List<Session>) session.getAttribute("players");
		List<Session> watchers = new ArrayList();
		for(Session player : players) {
			if(player.isTourEnCours()== true) {
				session.setAttribute("player",player);
			} else {
				watchers.add(player);
			}
		}
		session.setAttribute("watchers", watchers);
		return "pagejeu";
	}
	
	@GetMapping("/tour")
	public String tourDeJeu(HttpSession session) {
		List<Session> players = (List<Session>) session.getAttribute("players");
	
		for(Session player : players) {
			if(player.isTourEnCours()== true) {
				session.setAttribute("player",player);
			} else {
				session.setAttribute("watchers", player);
			}
		}
		return "pagejeu";
	}

}
