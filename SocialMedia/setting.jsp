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

.container {
    margin: auto;
    margin-top: 50px;
    text-align: center;
}

/* hide form */
.open-personal-info{
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  width: 50%;
}


.personal-info {
  display: none; 
  position: fixed;
  top : 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 3px solid blue;
}

.form-info {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

.form-info input[type=text], .form-info input[type=date], .form-info input[type=number] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
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

<button class = "open-personal-info" onclick = "openPersonalInfo()">View Personal Info</button>

<div class = personal-info id = "myInfo">
    <form action = "update-info" class = "form-info">
    <lable>Email : </lable>
    <input type = "text" value = "${user.email}" name = "email">
    <lable>DOB : </lable>
    <input type = "date" value = "${user.dateOfBirth}" name = "dateOfBirth">
    <lable>Phone No :</lable>
    <input type = "number" value = "${user.phoneNumber}" name = "phoneNumber">
    <button type = "submit" class = "savebutton">Update</button> 
    <button type="button" class="btn cancel" onclick="closePersonalInfo()">Close</button>
    </form>
</div>

<h1>Pesonal Details</h1>
<h1>Update Password</h1>
<h1>
</div>

</div>




<script>
function openPersonalInfo() {
  document.getElementById("myInfo").style.display = "block";
}

function closePersonalInfo() {
  document.getElementById("myInfo").style.display = "none";
}
</script>
</body>
</html>