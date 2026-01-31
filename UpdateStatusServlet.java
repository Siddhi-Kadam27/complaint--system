package com.cms;
import java.io.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String cid=req.getParameter("cid");
        String status=req.getParameter("status");

        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("UPDATE complaints SET status=? WHERE cid=?");
            ps.setString(1,status);
            ps.setString(2,cid);
            ps.executeUpdate();

            res.sendRedirect("adminViewComplaints.jsp");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
