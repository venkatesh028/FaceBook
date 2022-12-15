<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>
<style>
*{
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

</style>
</head>
<body>
    <nav class = "topNav">
    <ul class = "navList">
       <il><a href = "notification">Back</a></il>
    </ul>
    </nav>  
    
    <div>
    <c:choose>
    <c:when test = "${friendRequest ne null}">
    

    <div class="profileFrame">    
    <img src="iconforprofile.png">
    <h1>${profile.getUserName()}</h1>
    <h1>${profile.getBio()}</h1>
    <h1>Friends :${profile.getFriendsCount()}</h1>
    </div>
    <div class = "acceptDiv">
    <form action = "update-request">
    <input type = hidden value = "${friendRequest.id}" name = "requestId">
    <input type = hidden value = "accepted" name = "requestStatus">
    <input type = "submit" value = "accept">
    </form>
    </div>
    <div class = "rejectDiv">
    <form action = "update-request">
    <input type = hidden value = "${friendRequest.id}" name = "requestId">
    <input type = hidden value = "rejected" name = "requestStatus">
    <input type = "submit" value = "reject">
    </form>
    </div>   
    </c:when> 
    <c:otherwise>
    No request found
    </c:otherwise>
    </c:choose>
    </div>
</body>
</html>