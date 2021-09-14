package by.vgulab.epam.controller.tour;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (ServiceFactory factory = getFactory()) {
            TourService service = factory.getTourService();
            List<Tour> tours = service.findAll();
            req.setAttribute("tour", tours);
            req.getRequestDispatcher("/WEB-INF/jsp/tour/list.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
