package by.vgulab.epam.controller.user.password;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;
import by.vgulab.epam.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordResetServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (ServiceFactory serviceFactory = getFactory()) {
            UserService userService = serviceFactory.getUserService();
            User user = userService.findById(Long.parseLong(req.getParameter("id")));
            if (user != null) {
                userService.changePassword(user.getId(), user.getPassword(), null);
            }
            resp.sendRedirect(req.getContextPath() + "/user/list.html");
        } catch (NumberFormatException e) {
            resp.sendError(400);
        } catch (Exception e) {
            throw new ServletException(e);
        }


    }
}
