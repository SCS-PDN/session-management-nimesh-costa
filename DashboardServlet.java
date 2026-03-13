package lk.ac.pdn;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("user");
        List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");

        
        List<Course> availableCourses = new ArrayList<>();
        availableCourses.add(new Course("CSC3103", "Server Side Web Programming", "Dr. Aris"));
        availableCourses.add(new Course("CSC3104", "Database Management", "Prof. Sarah"));
        availableCourses.add(new Course("CSC3105", "Network Security", "Dr. Lee"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        out.println("<html><head><title>Course Dashboard</title></head><body>");
        out.println("<h1>Welcome, " + username + "!</h1>");
        out.println("<a href='LogoutServlet'>Logout</a>");

        out.println("<h2>Available Courses</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Instructor</th><th>Action</th></tr>");
        for (Course c : availableCourses) {
            out.println("<tr><td>" + c.getCourseId() + "</td><td>" + c.getCourseName() + "</td><td>" + c.getInstructor() + "</td>");
            out.println("<td><a href='EnrollServlet?courseId=" + c.getCourseId() + "'>Enroll</a></td></tr>");
        }
        out.println("</table>");

        out.println("<h2>Your Enrolled Courses</h2><ul>");
        if (enrolled != null) {
            for (Course c : enrolled) {
                out.println("<li>" + c.getCourseName() + " (" + c.getCourseId() + ")</li>");
            }
        } else {
            out.println("<li>No courses enrolled yet.</li>");
        }
        out.println("</ul></body></html>");
    }
}