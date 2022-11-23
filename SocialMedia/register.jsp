<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
</head>
<body>
    <form action="register" method="post">
      <label>Email :</label>
      <input type="email" placeholder="Email Id" name="email"><br><br>
      <label>New Password :</label>
      <input type="password" placeholder="Password" name="password"><br><br>
      <label>Date Of Birth :</label>
      <input type="date" name="DOB"><br><br>
      <label>Set Username :</label>
      <input type="text" placeholder="UserName" name="userName"><br><br>
      <input type="submit" value="Create Account">
    </form>
    <h1>${Message}</h1>
</body>
</html>