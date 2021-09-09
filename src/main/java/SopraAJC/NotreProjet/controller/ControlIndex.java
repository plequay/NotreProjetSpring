package SopraAJC.NotreProjet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SopraAJC.NotreProjet.models.Joueur;

@WebServlet("/index")
public class ControlIndex extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String surnom=request.getParameter("pseudo");
		String login=request.getParameter("login");
		String password=request.getParameter("mdp");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		Joueur j = new Joueur(login, password, prenom, nom, surnom);
		Context.getInstance().getDaoC().insert(j);
		System.out.println(j);
		
		this.getServletContext().getRequestDispatcher("/choixPartie.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String surnom=request.getParameter("pseudo");
		String login=request.getParameter("login");
		String password=request.getParameter("mdp");
		
		Joueur j = new Joueur(login, password, prenom, nom, surnom);
		Context.getInstance().getDaoC().insert(j);
		System.out.println(j);
		
		this.getServletContext().getRequestDispatcher("/choixPartie.jsp").forward(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String surnom=request.getParameter("pseudo");
		String login=request.getParameter("login");
		String password=request.getParameter("mdp");
		
		Joueur j = new Joueur(login, password, prenom, nom, surnom);
		Context.getInstance().getDaoC().insert(j);
		System.out.println(j);
		
		this.getServletContext().getRequestDispatcher("/choixPartie.jsp").forward(request, response);
	}

	
}
