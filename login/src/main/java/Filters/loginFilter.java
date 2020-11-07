package Filters;

//import com.sun.org.slf4j.internal.LoggerFactory;

import org.slf4j.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class loginFilter implements Filter {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(loginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String name = req.getParameter("username");
        String pass = req.getParameter("password");
        String hashPassword;
        if(pass != null){
            hashPassword = passwordEncoder.encode(pass);
        }else{
            hashPassword = null;
        }

        String path = req.getServletPath();
        if ("/".equals(path) || "/home".equals(path) || "/home.jsp".equals(path)) {
            filterChain.doFilter(servletRequest, servletResponse);

        }
        else{
            Object isLoggedinObj = req.getSession().getAttribute("isLogged");
            if(isLoggedinObj != null){
                boolean isLogged = (Boolean) isLoggedinObj;
                if(isLogged){
                    filterChain.doFilter(servletRequest, servletResponse);
                   return;
                }
            }
           resp.sendRedirect("home");
            }

        log.info("Someone tried to login with username: " +name+ " and password: "+pass+" when encoded "+hashPassword);

    }


}
