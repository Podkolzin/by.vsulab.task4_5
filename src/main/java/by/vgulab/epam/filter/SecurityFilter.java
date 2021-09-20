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
    private static Map<String, Set<Role>> allowedRoles = new HashMap<>();
    static {
     Set<Role> admin = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR));
     Set<Role> employ = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR, Role.MANAGER));
     Set<Role> all = new HashSet<>(Arrays.asList(Role.ADMINISTRATOR, Role.CUSTOMER, Role.MANAGER));

     allowedRoles.put("/user/list.html", employ);
     allowedRoles.put("/user/edit.html", all);
     allowedRoles.put("/user/save.html", all);
        allowedRoles.put("/tour/list.html", all);
        allowedRoles.put("/tour/edit.html", admin);
        allowedRoles.put("/tour/save.html", admin);

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean allow = false;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.substring(contextPath.length());
        Set<Role> roles = allowedRoles.get(uri);
        System.out.println(uri + " >>> " + roles);
        if (roles != null) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("session_user");
 //               req.setAttribute("name", user.getName());
                System.out.println(uri);
                if (user != null && roles.contains(user.getRole())) {
                    allow = true;
                }
            }
        }else {
            allow = true;
        }
        if (allow){
                chain.doFilter(req, resp);
            } else {
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }
}
