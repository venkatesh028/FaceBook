<html>
<head>
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

input[type=text] {
   width: 100%;
   border: 2px solid #efefef;
   box-sizing: border-box;
   margin-bottom: 10px;
   padding: 2%;
   border-radius: 8px;
}

.formContainer input[type=text]:focus {
    border: 3px solid;
    box-shadow: 2px 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
}

label{
    padding: 5px;
    font-weight: 600;
}

.savebutton {
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

.savebutton:hover {
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


<div class="formContainer"> 
    <form action="update" method="get" class = "form">
        <label>UserName</label>
        <input type = "text" value = "${profile.userName}" name="userName">
        <label>Bio</label>
        <input type = "text" value = "${profile.bio}" name="bio">
        <button type="submit" class="savebutton">Save</button>
    </form>
    <form class="login" >
    <a href="viewProfile">Back</a>
    </form>
    <h1>${Message}</h1>
</div> 


</body>
</html>