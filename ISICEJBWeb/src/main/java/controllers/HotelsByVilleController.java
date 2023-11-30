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

@WebServlet("/HotelsByVilleController")
public class HotelsByVilleController extends HttpServlet {
    @EJB(beanName = "HotelServiceEJB")
    private IDaoLocale<Hotel> hotelService;

    @EJB(beanName = "VilleServiceEJB")
    private IDaoLocale<Ville> villeService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ville> villes = villeService.findAll();
        request.setAttribute("villes", villes);

        String villeIdParam = request.getParameter("villeId");
        if (villeIdParam != null && !villeIdParam.isEmpty()) {
            int villeId = Integer.parseInt(villeIdParam);
            Ville selectedVille = villeService.findById(villeId);

            if (selectedVille != null) {
                List<Hotel> hotels = hotelService.getHotelsByVille(selectedVille);
                request.setAttribute("hotels", hotels);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("hotelsByVille.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet
    }
}
