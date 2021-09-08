package by.vgulab.epam.controller.user;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceFactory factory = getFactory()) {
            UserService userService = factory.getUserService();
            List<User> users = userService.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
