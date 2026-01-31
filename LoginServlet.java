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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // LOGIN SUCCESS
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);

                response.sendRedirect("dashboard.jsp");
            } else {
                // LOGIN FAILED
                response.getWriter().println("<h3>Invalid Email or Password</h3>");
                response.getWriter().println("<a href='login.jsp'>Try Again</a>");
            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
