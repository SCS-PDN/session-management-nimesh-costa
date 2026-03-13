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

            // Map ID to course
            Course selected = null;
            if ("CSC3103".equals(courseId)) selected = new Course("CSC3103", "Server Side Web Programming", "Dr. Aris");
            else if ("CSC3104".equals(courseId)) selected = new Course("CSC3104", "Database Management", "Prof. Sarah");
            else if ("CSC3105".equals(courseId)) selected = new Course("CSC3105", "Network Security", "Dr. Lee");

            // Add only if not already enrolled
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