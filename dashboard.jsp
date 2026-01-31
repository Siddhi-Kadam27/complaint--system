<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("userEmail");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<style>
body { font-family: Arial; background:#f4f4f4; }
.box {
  width: 60%;
  margin: 50px auto;
  background: white;
  padding: 20px;
  border-radius: 8px;
}
a {
  display:block;
  margin:10px 0;
  padding:10px;
  background:#007BFF;
  color:white;
  text-decoration:none;
  width:200px;
  text-align:center;
  border-radius:5px;
}
</style>
</head>
<body>

<div class="box">
  <h2>Welcome, <%= user %></h2>

  <a href="addComplaint.jsp">Add Complaint</a>
  <a href="viewComplaints.jsp">View My Complaints</a>
  <a href="LogoutServlet">Logout</a>
</div>

</body>
</html>
