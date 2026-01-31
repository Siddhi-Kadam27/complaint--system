<%@ page session="true" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*,com.cms.DBConnection" %>

<%
if(session.getAttribute("admin")==null){
    response.sendRedirect("adminLogin.jsp");
    return;
}
%>

<html>
<head>
<title>All Complaints</title>
<style>
table { width:100%; border-collapse:collapse; }
th,td { border:1px solid black; padding:10px; }
th { background:#007BFF; color:white; }
</style>
</head>

<body>
<h2>All User Complaints</h2>

<table>
<tr>
<th>ID</th>
<th>User</th>
<th>Title</th>
<th>Description</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
try{
    Connection con=DBConnection.getConnection();
    String q="SELECT c.cid,u.email,c.title,c.description,c.status FROM complaints c JOIN users u ON c.user_id=u.id";
    PreparedStatement ps=con.prepareStatement(q);
    ResultSet rs=ps.executeQuery();

    while(rs.next()){
%>

<tr>
<td><%=rs.getInt("cid")%></td>
<td><%=rs.getString("email")%></td>
<td><%=rs.getString("title")%></td>
<td><%=rs.getString("description")%></td>
<td><%=rs.getString("status")%></td>
<td>
<form action="UpdateStatusServlet" method="post">
<input type="hidden" name="cid" value="<%=rs.getInt("cid")%>">
<select name="status">
<option>Pending</option>
<option>In Progress</option>
<option>Resolved</option>
</select>
<input type="submit" value="Update">
</form>
</td>
</tr>

<%
    }
}catch(Exception e){
    out.println(e);
}
%>

</table>
<br>
<a href="adminDashboard.jsp">Back</a>

</body>
</html>
