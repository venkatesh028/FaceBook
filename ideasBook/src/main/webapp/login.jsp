<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
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
   border: 2px solid #efefef;
   box-sizing: border-box;
   margin-bottom: 10px;
   padding: 2%;
   border-radius: 8px;
}

.formContainer input[type=text]:focus, input[type=password]:focus, input[type=email]:focus, input[type=date]:focus {
    border: 3px solid;
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

.registerPage{
    display: flex
    justify-content: flex;
}

.create{
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

    <form action="login" method="post" class = "form">
      <label>Email </label>
      <input type="email" placeholder="Email Id" name="email" required/>
      <label>New Password </label>
      <input type="password" placeholder="Password" name="password" required/>
      <button type="submit" class="registerbtn">Login</button>
    </form>
    <form class="create" >
    <a href="register.jsp"> Don't Have Account?</a>
    </form>

    <h1>${Message}</h1>
    </div> 
     

</body>
</html>