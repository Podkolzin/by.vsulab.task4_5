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

public class OrderListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (ServiceFactory factory = getFactory()) {

            OrderService service = factory.getOrderService();

            List<Order> orders = service.findAll();

            req.setAttribute("order", orders);
            req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException();
        }


    }
}
