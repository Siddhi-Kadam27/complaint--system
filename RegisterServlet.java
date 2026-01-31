package com.cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try {
            // 1. Get DB Connection
            Connection con = DBConnection.getConnection();

            if (con == null) {
                response.getWriter().println("❌ Database not connected");
                return;
            }

            // 2. Insert user into database
            String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int x = ps.executeUpdate();

            // 3. Check result
            if (x > 0) {
                response.getWriter().println("<h2>Registration Successful</h2>");
                response.getWriter().println("<a href='login.jsp'>Click here to Login</a>");
            } else {
                response.getWriter().println("❌ Registration Failed");
            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
