package by.vgulab.epam.controller.tour;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try (ServiceFactory serviceFactory = getFactory()) {

                TourService tourService = serviceFactory.getTourService();
                tourService.delete(id);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/tour/list.html");

    }
}
