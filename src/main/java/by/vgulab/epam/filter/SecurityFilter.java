package by.vgulab.epam.filter;

import by.vgulab.epam.domain.Role;
import by.vgulab.epam.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static Map<String, Set<Role>> allowedPages = new HashMap<>();

    static {
        Set<Role> admin = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR));
        Set<Role> employ = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR, Role.MANAGER));
        Set<Role> all = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR, Role.CUSTOMER, Role.MANAGER));

        allowedPages.put("/user/list.html", all);
        allowedPages.put("/user/edit.html", all);
        allowedPages.put("/user/save.html", all);
        allowedPages.put("/tour/list.html", all);
        allowedPages.put("/tour/edit.html", employ);
        allowedPages.put("/tour/save.html", employ);
        allowedPages.put("/order/list.html", all);


        //       allowedPages.put(".html", all);


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean allow = false;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.substring(contextPath.length());
        Set<Role> roles = allowedPages.get(uri);
        if (roles != null) {
            HttpSession httpSession = req.getSession();
            if (httpSession != null) {
                User user = (User) httpSession.getAttribute("session_user");
                if (user != null && roles.contains(user.getRole())) {
                    allow = true;
                }
            }
        } else {
            allow = true;
        }
        if (allow) {
            chain.doFilter(req, resp);
        } else {
//            resp.sendRedirect(req.getContextPath() + "/login.html");

            chain.doFilter(req, resp);

        }
//        HttpSession session = null;
//        if (uri.equals("/login.html")) {
//            session = req.getSession(false);
//            if (session != null) {
//                Set<Role> roles = allowedPages.get(uri);
//                User user = (User) session.getAttribute("session_user");
//                //               req.setAttribute("name", user.getName());
//                System.out.println(uri);
//                if (user != null && roles.contains(user.getRole())) {
//                    chain.doFilter(req, resp);
//                }
//            }
//        }
//        if (uri.equals("/index.html")) {
//            chain.doFilter(req, resp);
////        } else if (session == null) {
////            resp.sendRedirect(req.getContextPath() + "/login.html");
////            chain.doFilter(req, resp);
//        }
    }
}
