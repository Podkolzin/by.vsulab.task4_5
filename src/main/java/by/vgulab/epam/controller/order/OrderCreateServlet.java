package by.vgulab.epam.controller.order;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Order;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.Tour;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.service.TourService;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderCreateServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;
        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("session_user");


        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try (ServiceFactory serviceFactory = getFactory()) {
                TourService tourService = serviceFactory.getTourService();
                Tour tour = tourService.findById(id);
                req.setAttribute("name", user.getName());
                req.setAttribute("tour", tour);
                boolean tourCanBeDeleted = tourService.canDelete(id);
                req.setAttribute("userCanBeDeleted", tourCanBeDeleted);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/order/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        try {
            OrderService orderService = getFactory().getOrderService();
            HttpSession session = req.getSession();
            User user  = (User)session.getAttribute("session_user");
       //     order.setUserId(Long.valueOf(String.valueOf(session.getAttribute("name"))));
      //      order.setUserId(Long.valueOf(req.getParameter("order.userId")));




            order.setUserId(user.getId());
            order.setTourId(Long.valueOf(req.getParameter("tour.tourId")));
            order.setPayment(Long.valueOf(req.getParameter("tour.price")));
            orderService.createOrder(order);

            List<Order> orders = orderService.findOrder(order.getUserId());

            req.setAttribute("order", orders);

            req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}
