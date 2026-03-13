package lk.ac.pdn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);

        if (session != null && courseId != null) {
            List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolled == null) {
                enrolled = new ArrayList<>();
            }

            // ADDED: Logic to map ID to a Course object
            Course selected = null;
            if (courseId.equals("CSC3103")) selected = new Course("CSC3103", "Server Side Web Programming", "Dr. Jone");
            else if (courseId.equals("CSC2012")) selected = new Course("CSC 2012", "Database Management", "Prof. Sam");
            else if (courseId.equals("CSC2112")) selected = new Course("CSC2112", "Networking", "Dr. Same");

            // Prevent duplicates
            boolean exists = false;
            if (selected != null) {
                for (Course c : enrolled) {
                    if (c.getCourseId().equals(courseId)) exists = true;
                }
                if (!exists) enrolled.add(selected);
            }

            session.setAttribute("enrolledCourses", enrolled);
        }
        response.sendRedirect("DashboardServlet");
    }
}