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

public class UserSaveServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        user.setLogin(req.getParameter("login"));
        try {
            UserService userService = getFactory().getUserService();
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            user.setName(req.getParameter("name"));
            user.setSurname(req.getParameter("surname"));
            user.setEmail(req.getParameter("email"));
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
                userService.save(user);
        } catch (Exception e) {
            throw new ServletException(e);
        }



//        try {
//            user.setId(Long.parseLong(req.getParameter("id")));
//        } catch (NumberFormatException e) {
//        }
//        user.setLogin(req.getParameter("login"));
//        try {
//            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
//        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//        }
//        if (user.getLogin() != null && user.getRole() != null) {
//            try (ServiceFactory factory = getFactory()) {
//                UserService service = factory.getUserService();
//                service.save(user);
//            } catch (Exception e) {
//                throw new ServletException(e);
//            }
//        }
        resp.sendRedirect(req.getContextPath() + "/user/list.html");
    }
}
