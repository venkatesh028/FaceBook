<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <!-- <link rel="stylesheet" href="css/registerPage.css"> --!>

<style>
body{
    background: #efefef;
}

.formContainer{
    margin: auto;
    margin-top: 5%;
    padding: 3% 4%;
    width: 50%;
    background: white;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
} 

.formContainer:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

input[type=text], input[type=password], input[type=email], input[type=date] {
   width: 100%;
   border: 1px solid #ccc;
   box-sizing: border-box;
   margin-bottom: 10px;
   padding: 2%;
   border-radius: 8px;
}

.formContainer input[type=text]:focus, input[type=password]:focus, input[type=email]:focus, input[type=date]:focus {
    border: 3px solid blue;
    box-sizing: border-box;
    box-shadow: 2px 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
}

label{
    padding: 5px;
    font-weight: 600;
}

.registerbtn {
  background-color: #1877f2;
  font-weight: Bold;
  font-size: 20px;
  border-radius: 8px;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}


.logoBanner {
     display: flex;
     justify-content: Center;
     margin: 0px;    
}

.logo {
    font-size: 3em;
    color: #1877f2;
    font-family: sans-serif;
    margin-bottom: 0px;
}

.registerbtn:hover {
  opacity:1;
}

.login{
    text-align: center;
}

a{
 text-decoration: none;
}

</style>
</head>
<body>
   
    <div class="logoBanner">
    <h1 class="logo">IdeasBook</h1> 
    </div>
    <div class="formContainer"> 

    <form action="register" method="post" class = "form">
      <label>Email </label>
      <input type="email" placeholder="Email Id" name="email" required/>
      <label>New Password </label>
      <input type="password" placeholder="Password" name="password" required/>
      <label>Date Of Birth </label>
      <input type="date" name="DOB">
      <label>Username </label>
      <input type="text" placeholder="UserName" name="userName" required/>
      <button type="submit" class="registerbtn">Register</button>
    </form>
    <form class="login" >
    <a href="login.jsp"> Already Exist?</a>
    </form>
    <h1>${Message}</h1>
    </div>  

</body>
</html>