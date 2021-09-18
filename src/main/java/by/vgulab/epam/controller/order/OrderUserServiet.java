package by.vgulab.epam.controller.order;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Order;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderUserServiet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/user/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("order.userId");

        Long idLong = Long.valueOf(id);

        try (ServiceFactory serviceFactory = getFactory()){
            OrderService orderService = serviceFactory.getOrderService();
            List<Order> order = orderService.findOrder(idLong);
            req.setAttribute("order", order);
        } catch (Exception e){
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req, resp);

    }
}
