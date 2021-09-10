package by.vgulab.epam.controller.user;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = null;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try (ServiceFactory serviceFactory = getFactory()) {
                UserService userService = serviceFactory.getUserService();
                User user = userService.findById(id);
                req.setAttribute("user", user);
                boolean userCanBeDeleted = userService.canDelete(id);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}
