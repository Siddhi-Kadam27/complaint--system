package com.cms;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String u=req.getParameter("username");
        String p=req.getParameter("password");

        try {
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1,u);
            ps.setString(2,p);
            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                HttpSession s=req.getSession();
                s.setAttribute("admin",u);
                res.sendRedirect("adminDashboard.jsp");
            } else {
                res.getWriter().println("Invalid Admin Login");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
