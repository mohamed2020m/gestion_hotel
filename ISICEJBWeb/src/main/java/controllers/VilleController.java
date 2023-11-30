package controllers;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */

@WebServlet(name = "/VilleController")
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(VilleController.class.getName());

    @EJB(beanName = "VilleServiceEJB")
    private IDaoLocale<Ville> ejb;

    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Handle create action (already implemented)
            String nom = request.getParameter("ville");
            ejb.create(new Ville(nom));

            // Redirect after successful create
            response.sendRedirect(request.getContextPath() + "/VilleController");
            return;
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Ville villeToUpdate = ejb.findById(id);

            if (villeToUpdate != null) {
                String nom = request.getParameter("ville");
                villeToUpdate.setNom(nom);
                ejb.update(villeToUpdate);

                // Redirect after successful update
                response.sendRedirect(request.getContextPath() + "/VilleController");
                return;
            }
        } else if ("delete".equals(action)) {
            // Handle delete action
            int id = Integer.parseInt(request.getParameter("id"));
            Ville villeToDelete = ejb.findById(id);

            if (villeToDelete != null) {
                ejb.delete(villeToDelete);

                // Redirect after successful delete
                response.sendRedirect(request.getContextPath() + "/VilleController");
                return;
            }
        }

        List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet
    }
}


//@WebServlet(name = "/VilleController")
//public class VilleController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static final Logger logger = Logger.getLogger(VilleController.class.getName());
//
//	@EJB(beanName = "VilleServiceEJB")
//	private IDaoLocale<Ville> ejb;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public VilleController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String action = request.getParameter("action");
//
//		if ("create".equals(action)) {
//            // Handle create action (already implemented)
//            String nom = request.getParameter("ville");
//            ejb.create(new Ville(nom));
//        } else if ("update".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Ville villeToUpdate = ejb.findById(id);
//
//            if (villeToUpdate != null) {
//                String nom = request.getParameter("ville");
//                villeToUpdate.setNom(nom);
//                ejb.update(villeToUpdate);
//            }
//        } else if ("delete".equals(action)) {
//            // Handle delete action
//            int id = Integer.parseInt(request.getParameter("id"));
//            Ville villeToDelete = ejb.findById(id);
//            System.out.println("villeToDelete: " + villeToDelete.getNom());
//            if (villeToDelete != null) {
//                ejb.delete(villeToDelete);
//            }
//        }
//
//        List<Ville> villes = ejb.findAll();
//        request.setAttribute("villes", villes);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
//        dispatcher.forward(request, response);
//        
////		String nom = request.getParameter("ville");
////		ejb.create(new Ville(nom));
////		
////		request.setAttribute("villes", ejb.findAll());
////		RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");	
////		dispatcher.forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
