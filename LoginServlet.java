import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Hardcoded valid username/password
        String validUser = "admin";
        String validPass = "1234";

        if (username.equals(validUser) && password.equals(validPass)) {

            // 3. Create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // 4. Create cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(userCookie);

            // 5. Redirect to dashboard servlet
            response.sendRedirect("DashboardServlet");
        } else {

            // 6. Invalid login → Return to login page
            response.sendRedirect("login.html?error=1");
        }
    }
}