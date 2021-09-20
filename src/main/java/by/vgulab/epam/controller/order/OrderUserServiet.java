package by.vgulab.epam.controller.order;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderUserServiet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try (ServiceFactory serviceFactory = getFactory()) {
                UserService userService = serviceFactory.getUserService();
                User user = userService.readByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
      //              req.setAttribute("session_user", user.getName());
                    resp.sendRedirect(req.getContextPath() + "/order/list.html");
                } else {
                    resp.sendError(404);
                }
            } catch (Exception e) {
                throw new ServletException();
            }

        } else {
            resp.sendError(400);
        }
    }
}
