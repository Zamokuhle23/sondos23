package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet({"/", "/home"})
public class profileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username.equals("admin") & password.equals("admin") ||username.equals("user") & password.equals("user")) {
          HttpSession httpSession = req.getSession();
          httpSession.setAttribute("username",username);
          httpSession.setAttribute("isLogged",true);
            resp.sendRedirect("profile.jsp");
        }else {
            String message="Wrong Username or Password!";
            resp.sendRedirect("home?message=" + URLEncoder.encode(message, "UTF-8"));
        }
    }
}
