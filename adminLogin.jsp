<!DOCTYPE html>
<html>
<head>
<title>Admin Login</title>
<style>
body { font-family: Arial; background:#f4f4f4; }
.box { width:300px; margin:100px auto; background:white; padding:20px; border-radius:8px; }
input { width:100%; padding:10px; margin:10px 0; }
</style>
</head>
<body>

<div class="box">
<h2>Admin Login</h2>
<form action="AdminLoginServlet" method="post">
<input type="text" name="username" placeholder="Username" required>
<input type="password" name="password" placeholder="Password" required>
<input type="submit" value="Login">
</form>
</div>

</body>
</html>
