package by.vgulab.epam.controller.tour;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Food;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.Type;
import by.vgulab.epam.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class TourSaveServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Tour tour = new Tour();

        try {
            tour.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        try {
            TourService tourService = getFactory().getTourService();
            tour.setType(Type.values()[Integer.parseInt(req.getParameter("type"))]);
            tour.setCountry(req.getParameter("country"));
            tour.setTown(req.getParameter("town"));
            tour.setDate(Date.valueOf(req.getParameter("date")));
            tour.setDay(Integer.parseInt(req.getParameter("day")));
            tour.setFood(Food.values()[Integer.parseInt(req.getParameter("food"))]);
            tour.setPrice(Integer.parseInt(req.getParameter("price")));

            tourService.save(tour);

        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/tour/list.html");
    }
}
