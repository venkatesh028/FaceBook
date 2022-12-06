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
    text-align: center;
}

/* hide form */

.buttons {
    margin-top: 20px;
    display : auto;
    text-align: center;
}

.parent {
    position :absolute;
    width: 100%;
    height: 100%;
    top : 0;
    left: 0;
    background-color: rgba(0,0,0,0.5);
}

.open-personal-info{
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  width: 50%;
}

.change-password {
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

.editbutt {
    width :100%;
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


<div class = "buttons">
<button class = "open-personal-info" onclick = "openPersonalInfo()">View Personal Info</button>
<button class = "change-password" onclick = "openPasswordForm()">Change Password</button>
</div>
<div class = "parent" id = "parent" style = "display :none">

<div class = personal-info id = "myInfo">
    <div class = "container">
    <button class = "editbutt" onclick = "edit()">Edit</button>
    <form action = "update-info" class = "form-info" id = "form-info">
    <lable>Email  </lable>
    <input type = "text" value = "${user.email}" name = "email">
    <lable>DOB  </lable>
    <input type = "date" value = "${user.dateOfBirth}" name = "dateOfBirth">
    <lable>Phone No </lable>
    <input type = "number" value = "${user.phoneNumber}" name = "phoneNumber">
    <button type = "submit" class = "savebutton" id = "upadatebut">Update</button> 
    <button type="button" class="btn cancel" onclick="closePersonalInfo()">Close</button>
    </form>
    </div>
</div>


<div class = personal-info id = "myInfo">
    <div class = "container">
    <form action = "update-password" class = "form-password" id = "form-password">
    <lable>old Password  </lable>
    <input type = "text" name = "oldPassword">
    <lable>New Password</lable>
    <input type = "text" name = "newPassword">
    <lable>Phone No </lable>
    <input type = "number" value = "${user.phoneNumber}" name = "phoneNumber">
    <button type = "submit" class = "savebutton" id = "upadatebut">Update</button> 
    <button type="button" class="btn cancel" onclick="closePersonalInfo()">Close</button>
    </form>
    </div>
</div>

</div>



<script>
function openPersonalInfo() {
    document.getElementById("myInfo").style.display = "block";
    document.getElementById("parent").style.display = "block";

    var form = document.getElementById("form-info");
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].readOnly = true;
    }
}

function edit() {
    var form = document.getElementById("form-info");
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].readOnly = false;
    }
}

function closePersonalInfo() {
    document.getElementById("myInfo").style.display = "none";
    document.getElementById("parent").style.display = "none";
}

var personalInfoForm = getElemetById('myInfo');
var parent = getElementById('parent');

window.onclick = function(event) {
    if (event.target == personalInfoForm) {
        personalInfoFrom.display = "none";
        parent.style.display = "none";
    }
}



</script>
</body>
</html>