package by.vgulab.epam.controller.order;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Order;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.OrderService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user  = (User)session.getAttribute("session_user");

        try (ServiceFactory factory = getFactory()) {

            OrderService service = factory.getOrderService();
            if (user.getRole() == Role.ADMINISTRATOR && user.getRole() == Role.MANAGER){
                List<Order> orders = service.findAll();
                req.setAttribute("name", user.getName());
                req.setAttribute("order", orders);
            }
            if (user.getRole() == Role.CUSTOMER){
                List<Order> orders = service.findOrder(user.getId());
                req.setAttribute("name", user.getName());
                req.setAttribute("order", orders);
            } else {
                resp.sendError(404);
            }

            req.getRequestDispatcher("/WEB-INF/jsp/order/list.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            throw new ServletException();
        }

    }
}
