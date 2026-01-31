package com.cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        if(userEmail == null){
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            // Get user ID from DB
            String userQuery = "SELECT id FROM users WHERE email=?";
            PreparedStatement ps1 = con.prepareStatement(userQuery);
            ps1.setString(1, userEmail);
            ResultSet rs = ps1.executeQuery();
            int userId = 0;
            if(rs.next()) {
                userId = rs.getInt("id");
            }

            // Get form data
            String title = request.getParameter("title");
            String description = request.getParameter("description");

            // Insert complaint
            String sql = "INSERT INTO complaints(user_id,title,description) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, description);

            int x = ps.executeUpdate();
            if(x > 0){
                response.sendRedirect("dashboard.jsp");
            } else {
                response.getWriter().println("Failed to submit complaint");
            }

        } catch(Exception e){
            e.printStackTrace(response.getWriter());
        }
    }
}
