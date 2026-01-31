<%@ page session="true" %>
<%
if(session.getAttribute("admin")==null){
    response.sendRedirect("adminLogin.jsp");
    return;
}
%>

<html>
<body>
<h2>Admin Dashboard</h2>
<a href="adminViewComplaints.jsp">View All Complaints</a><br><br>
<a href="AdminLogoutServlet">Logout</a>
</body>
</html>
