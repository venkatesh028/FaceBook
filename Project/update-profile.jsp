<html>
<head>
</head>
<body>
<form action="update" method = "get">
    <input type = "text" value = "${profile.userName}" name="userName">
    <input type = "text" value = "${profile.bio}" name="bio">
    <input type = submit value = "Save">
</form>
<h1>${Message}</h1>
</body>
</html>