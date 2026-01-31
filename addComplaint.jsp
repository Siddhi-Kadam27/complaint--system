<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("userEmail");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Complaint</title>
</head>
<body>

<h2>Submit a Complaint</h2>

<form action="ComplaintServlet" method="post">
    <label>Title:</label><br>
    <input type="text" name="title" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="5" cols="30" required></textarea><br><br>

    <input type="submit" value="Submit Complaint">
</form>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>
