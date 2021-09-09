package SopraAJC.NotreProjet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Partie;
import util.Context;
@WebServlet("/choixPartie")

public class ControlChoixPartie extends HttpServlet {

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Partie> p = Context.getInstance().getDaoP().findAll();

		request.setAttribute("parties", p);
		System.out.println(p);
		this.getServletContext().getRequestDispatcher("/NotreProjetPageJeu.jsp").forward(request, response);
		
		
		
		System.out.println(p+"doGet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		if(request.getParameter("type_form").equals("PUT"))
		{
			doPut(request);
		}
		else if(request.getParameter("type_form").equals("DELETE"))
		{
			doDelete(request);
		}
		else if(request.getParameter("type_form").equals("POST"))
		{
			
			Ville depart=daoV.findById(Integer.parseInt(request.getParameter("id_depart")));
			Ville destination=daoV.findById(Integer.parseInt(request.getParameter("id_destination")));
			
			Trajet t = new Trajet(depart,destination,Double.parseDouble(request.getParameter("distance")));
			
			daoT.insert(t);
						
		}*/
		System.out.println("doPsot");
		doGet(request, response);
	}
/*
	protected void doPut(HttpServletRequest request) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));

		System.out.println(request.getParameter("id_depart"));
		Ville depart=daoV.findById(Integer.parseInt(request.getParameter("id_depart")));
		Ville destination=daoV.findById(Integer.parseInt(request.getParameter("id_destination")));
		Trajet t = new Trajet(id,depart,destination,Double.parseDouble(request.getParameter("distance")));

		daoT.update(t);
	}

	protected void doDelete(HttpServletRequest request) throws ServletException, IOException {

		daoT.delete(Integer.parseInt(request.getParameter("id")));
	}
}*/
}