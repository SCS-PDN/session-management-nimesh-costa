package lk.ac.pdn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie killCookie = new Cookie("username", "");
        killCookie.setMaxAge(0);
        killCookie.setPath(request.getContextPath()); 
        response.addCookie(killCookie);

       
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}
