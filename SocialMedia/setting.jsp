<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

.change-password{
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  width: 50%;
}

.delete-account{
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

.password-info {
  display: none; 
  position: fixed;
  top : 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 3px solid blue;
}

.delete {
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

.form-password {
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

.form-password input[type = password] {
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
  <il><a href="viewProfile">Back</a></il>
  </ul>
</nav>


<c:if test = "${message ne null}">
    <script>
     alert("${message}");
    </script>
</c:if>

<div class = "buttons">
<button class = "open-personal-info" onclick = "openPersonalInfo()">View Personal Info</button>
<button class = "change-password" onclick = "openPasswordForm()">Change Password</button>
<button class = "delete-account" onclick = "openDeleteForm()">Delete Account</button>
</div>

<div class = personal-info id = "myInfo">
    <div class = "container">
    <button class = "editbutt" onclick = "edit()">Edit</button>
    <form action = "update-info" class = "form-info" id = "form-info" method = "Post">
    <lable>Email  </lable>
    <input type = "text" value = "${user.email}" name = "email">
    <lable>DOB  </lable>
    <input type = "date" value = "${user.dateOfBirth}" name = "DOB">
    <lable>Phone No </lable>
    <input type = "number" value = "${user.phoneNumber}" name = "phoneNumber">
    <button type = "submit" class = "savebutton" id = "upadatebut">Update</button> 
    <button type = "button" class="btn cancel" onclick="closePersonalInfo()">Close</button>
    </form>
    </div>
</div>

<div class = password-info id = "password">
    <div class = "container">
    <form action = "update-password" class = "form-password" id = "password" method = "Post">
    <lable> Current Password </label>
    <input type = password name = "oldPassword">
    <lable> New Password </lable>
     <input type="password" placeholder="Password@12" name="newPassword" pattern = "^[a-zA-Z0-9]{4,9}[@$&*#]{1,2}[a-zA-Z0-9]{1,6}"
       title = " Mini:8 Max: 16 Start with Mixture of Captial and small letter and number mini:4 max:9 and one from {@$&*#} ends with Captial and small letter or number" required/>      
    <button type = "submit" class = "savebutton" id = "updatebutton">Update</button>
    <button type = "button" class = "btn cancel" onclick = "closePasswordForm()">Close</button>     
    </form>
    </div>
</div>

<div class = delete id = "deleteAccount">
    <div class = "container">
    <form action = "delete-account" class = "form-password" id = "deleteAccount" method = "Post">
    <lable>Password</lable>
    <input type = password name = "password">
    <button type = "submit" class = "savebutton" id = "delete">Delete</button>
    <button type = "button" class = "btn cancel" onclick = "closeDeleteForm()">close</button>
    </form>
    </div>
</div>

<script>
function openPersonalInfo() {
    document.getElementById("myInfo").style.display = "block";

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
}
 
function openPasswordForm() {
    document.getElementById("password").style.display = "block";
}

function closePasswordForm() {
    document.getElementById("password").style.display = "none";
}

function openDeleteForm() {
    document.getElementById("deleteAccount").style.display = "block";
}

function closeDeleteForm() {
    document.getElementById("deleteAccount").style.display = "none";
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