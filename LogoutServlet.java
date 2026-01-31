package com.cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get current session, if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Clear all session data
        }

        // Redirect to login page
        response.sendRedirect("login.jsp");
    }
}
