package by.vgulab.epam.controller.user;

import by.vgulab.epam.controller.BaseServlet;
import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;
import by.vgulab.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSaveServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();

        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        try {
            UserService userService = getFactory().getUserService();
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            if (!req.getParameter("name").equals("")) {
                user.setName(req.getParameter("name"));
            } else {
                user.setName("null");
            }
            if (!req.getParameter("surname").equals("")) {
                user.setSurname(req.getParameter("surname"));
            } else {
                user.setSurname("null");
            }
            if (!req.getParameter("email").equals("")) {
                user.setEmail(req.getParameter("email"));
            } else {
                user.setEmail("null");
            }
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

        HttpSession httpSession = req.getSession(false);
        if (httpSession.getAttribute("session_user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/order/list.html");
        }
    }
}
