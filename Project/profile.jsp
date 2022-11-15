<html>
<head>
<title>ideasBook</title>
</head>
<body>
<h1>Username : ${profile.getUserName()}</h1>
<h1>Bio : ${profile.getBio()}</h1>
<h1>Friends Count : ${profile.getFriendsCount()}</h1>
<form action = "updateProfile">
    <input type = submit value = "Edit">
</form>
</body>
</html>