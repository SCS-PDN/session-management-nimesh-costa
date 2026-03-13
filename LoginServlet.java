package lk.ac.pdn;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("student1".equals(user) && "pass1".equals(pass)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            Cookie userCookie = new Cookie("username", user);
            userCookie.setMaxAge(60 * 60);
            response.addCookie(userCookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("index.html");
        }
    }
}