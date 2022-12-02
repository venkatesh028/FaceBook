<html>
<head>
    <title>ideasBook</title>


<style>
* {
    margin : 0px;
    padding: 0px;
}

.topNav {
    background: #1877f2;
    width: 100%;
    overflow: hidden;
} 

.navList{
 margin :0px;
}

body{
    background: #efefef;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.logout{
    float:right;
}

.left {
  display: inline-block;
  height: 100%;
  width: 160px;
  background-color: #111;
  padding-top: 16px;
}

.right {
    display: inline-block;
    padding : 50px;
    background-color: blue;
}

</style>
</head>
<body>
<nav class = "topNav">
  <ul class="navList">
  <il><a href="newsFeed" method="post">NewsFeed</a></il>
  <il><a href="addPost.jsp">AddPost</a></il>
  <il><a href="viewProfile">Profile</a></il>
  <il><a href="#contact">Notification</a></il>
  <il><a href="#about">Setting</a></il>
  <il class="logout"><a href="logout">Logout</a></il>
  </ul>
</nav>

<div class = "container"> 

<div class = "left">
<h1> Hii </h1>
</div>

<div class = "right">
<h1>Dont</h1>
</div>

</div>
</body>
</html>