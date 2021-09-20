package by.vgulab.epam.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexPageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("addUser").equals("addUser")) {
            req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);

        } else if (req.getParameter("orderUser").equals("orderUser")) {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);

        }
    }
}
