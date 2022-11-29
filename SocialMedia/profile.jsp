<html>
<head>
<title>ideasBook</title>
<link rel="stylesheet" href="css/profile.css">
</head>
<body>
<div class="topnav">
  <ul class="navList">
  <il><a href="newsFeed" method="post">NewsFeed</a></il>
  <il><a href="viewProfile">Profile</a></il>
  <il><a href="#contact">Notification</a></il>
  <il><a href="#about">Setting</a></il>
  <il class="logout"><a href="logout">Logout</a></il>
  </ul>
</div>


<div class="profileFrame">

    <table cellpadding='20px'>
        <tr>
            <td>
            <img src="iconforprofile.png" alt="mine" width='200px' height='200px'>
            </td>
            <td class="info">
                <h1>${profile.getUserName()}</h1>
                <h1>${profile.getBio()}</h1>
                <a href="getfriends"><h1>Friends :${profile.getFriendsCount()}</h1></a>
            </td>
            <td class="edit">
              <form action = "updateProfile">
              <input type = submit value = "Edit">
              </form>
            </td>
        </tr>  
    </table>
    
</div>

</body>
</html>