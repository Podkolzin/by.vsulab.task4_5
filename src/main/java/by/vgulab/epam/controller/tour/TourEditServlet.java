package by.vgulab.epam.controller.tour;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Food;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.Type;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourEditServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){

        }
        if(id != null){
            try (ServiceFactory serviceFactory = getFactory()){

                TourService tourService = serviceFactory.getTourService();
                Tour tour = tourService.findById(id);
                req.setAttribute("tour", tour);
                boolean tourCanBeDeleted = tourService.canDelete(id);
                req.setAttribute("tourCanBeDeleted", tourCanBeDeleted);
            } catch (Exception e){
                throw new ServletException(e);
            }
        }
        req.setAttribute("types", Type.values());
        req.setAttribute("foods", Food.values());
        req.getRequestDispatcher("/WEB-INF/jsp/tour/edit.jsp").forward(req, resp);
    }
}
