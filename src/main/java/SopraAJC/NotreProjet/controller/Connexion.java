package SopraAJC.NotreProjet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SopraAJC.NotreProjet.models.Admin;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import ch.qos.logback.core.Context;

@WebServlet("/pageco")
public class Connexion extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		Compte c = Context.getInstance().getDaoC().seConnecter(login, password);
		System.out.println(login+"        "+password + "doGet");
	
		if(c instanceof Joueur) 
		{
		this.getServletContext().getRequestDispatcher("/choixPartie.html").forward(request, response);
		}
		else if(c instanceof Admin) 
		{
			this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else 
		{
			this.getServletContext().getRequestDispatcher("/pageco.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Compte c = Context.getInstance().getDaoC().seConnecter(login, password);
		
		if(c instanceof Joueur) 
		{
			this.getServletContext().getRequestDispatcher("/choixPartie.htm").forward(request, response);
		}
		else if(c instanceof Admin) 
		{
			this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else 
		{
			this.getServletContext().getRequestDispatcher("/pageco.jsp").forward(request, response);
		}
		System.out.println(login+"        "+password + "doPost");
	}
}
