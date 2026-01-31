<%@ page session="true" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.cms.DBConnection" %>

<%
String userEmail = (String) session.getAttribute("userEmail");
if (userEmail == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<html>
<head>
<title>My Complaints</title>
<style>
table {
  width: 80%;
  margin: 40px auto;
  border-collapse: collapse;
}
th, td {
  padding: 10px;
  border: 1px solid black;
}
th {
  background: #007BFF;
  color: white;
}
</style>
</head>

<body>

<h2 align="center">My Complaints</h2>

<table>
<tr>
  <th>Title</th>
  <th>Description</th>
  <th>Status</th>
</tr>

<%
try {
    Connection con = DBConnection.getConnection();

    String sql = "SELECT complaints.title, complaints.description, complaints.status " +
                 "FROM complaints JOIN users ON complaints.user_id = users.id " +
                 "WHERE users.email=?";

    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, userEmail);

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
%>
<tr>
  <td><%= rs.getString("title") %></td>
  <td><%= rs.getString("description") %></td>
  <td><%= rs.getString("status") %></td>
</tr>
<%
    }
} catch(Exception e) {
    out.println(e);
}
%>

</table>

<center>
<a href="dashboard.jsp">Back to Dashboard</a>
</center>

</body>
</html>
