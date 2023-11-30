package controllers;

import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Hotel;
import entities.Ville;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/HotelController")
public class HotelController extends HttpServlet {
    @EJB(beanName = "HotelServiceEJB")
    private IDaoLocale<Hotel> hotelService;

    @EJB(beanName = "VilleServiceEJB")
    private IDaoLocale<Ville> villeService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Handle create action
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String telephone = request.getParameter("telephone");
            int villeId = Integer.parseInt(request.getParameter("villeId"));

            Ville ville = villeService.findById(villeId);

            Hotel newHotel = new Hotel();
            newHotel.setNom(nom);
            newHotel.setAdresse(adresse);
            newHotel.setTelephone(telephone);
            newHotel.setVille(ville);

            hotelService.create(newHotel);

            // Redirect after successful create
            response.sendRedirect(request.getContextPath() + "/HotelController");
            return; // Ensure the response is not further processed after the redirect
        } else if ("update".equals(action)) {
            // Handle update action
            String idParameter = request.getParameter("id");

            if (idParameter != null && !idParameter.isEmpty()) {
                int hotelId = Integer.parseInt(idParameter);
                Hotel existingHotel = hotelService.findById(hotelId);

                if (existingHotel != null) {
                    // Update hotel details
                    String nom = request.getParameter("nom");
                    String adresse = request.getParameter("adresse");
                    String telephone = request.getParameter("telephone");
                    int villeId = Integer.parseInt(request.getParameter("villeId"));

                    Ville ville = villeService.findById(villeId);

                    existingHotel.setNom(nom);
                    existingHotel.setAdresse(adresse);
                    existingHotel.setTelephone(telephone);
                    existingHotel.setVille(ville);

                    hotelService.update(existingHotel);

                    // Redirect after successful update
                    response.sendRedirect(request.getContextPath() + "/HotelController");
                    return; // Ensure the response is not further processed after the redirect
                }
            }
        } else if ("delete".equals(action)) {
            // Handle delete action
            int hotelId = Integer.parseInt(request.getParameter("id"));
            Hotel hotelToDelete = hotelService.findById(hotelId);

            if (hotelToDelete != null) {
                hotelService.delete(hotelToDelete);
            }

            // Redirect after successful delete
            response.sendRedirect(request.getContextPath() + "/HotelController");
            return; // Ensure the response is not further processed after the redirect
        }

        List<Hotel> hotels = hotelService.findAll();
        List<Ville> villes = villeService.findAll();
        request.setAttribute("hotels", hotels);
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotel.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet
    }
}


//@WebServlet("/HotelController")
//public class HotelController extends HttpServlet {
//    @EJB
//    private IDaoLocale<Hotel> hotelService;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("create".equals(action)) {
//            // Handle create action
//            String nom = request.getParameter("nom");
//            String adresse = request.getParameter("adresse");
//            String telephone = request.getParameter("telephone");
//            int villeId = Integer.parseInt(request.getParameter("villeId"));
//
//            Ville ville = new Ville();
//            ville.setId(villeId);
//
//            Hotel newHotel = new Hotel();
//            newHotel.setNom(nom);
//            newHotel.setAdresse(adresse);
//            newHotel.setTelephone(telephone);
//            newHotel.setVille(ville);
//
//            hotelService.create(newHotel);
//        } else if ("update".equals(action)) {
//            // Handle update action
//            // Similar to create but using hotelService.update(...)
//        } else if ("delete".equals(action)) {
//            // Handle delete action
//            // Similar to create but using hotelService.delete(...)
//        }
//
//        List<Hotel> hotels = hotelService.findAll();
//        request.setAttribute("hotels", hotels);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("hotel.jsp");
//        dispatcher.forward(request, response);
//    }
//}

//public class hotelController {
//	private static final long serialVersionUID = 1L;
//
//	@EJB
//	private IDaoLocale<Hotel> ejb;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public hotelController() {
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
//            String nom = request.getParameter("hotels");
//            Hotel h = new Hotel();
//            h.setNom(nom);
//            h.setTelephone(action);
//            h.setVille(null);
//            h.se
//            ejb.create();
//        } else if ("update".equals(action)) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Ville villeToUpdate = ejb.findById(id);
//
//            if (villeToUpdate != null) {
//                String nom = request.getParameter("hotel");
//                villeToUpdate.setNom(nom);
//                ejb.update(villeToUpdate);
//            }
//        } else if ("delete".equals(action)) {
//            // Handle delete action
//            int id = Integer.parseInt(request.getParameter("id"));
//            Ville villeToDelete = ejb.findById(id);
//            System.out.println("hotelToDelete: " + villeToDelete.getNom());
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
//}
