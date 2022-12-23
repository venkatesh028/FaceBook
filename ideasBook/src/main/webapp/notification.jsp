<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>
    ideasBook
    </title>
    
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
       <il><a href = "newsFeed">Back</a></il>
    </ul>
    </nav>  
    
<c:choose>
<c:when test = "${listOfNotifications != null}">
    <c:forEach items="${listOfNotifications}" var="notification">  
    <form action = "get-request" method = "Get"> 
    <input type = hidden value = ${notification.requestId} name = "requestId">
    <lable>Friend Request Given By ${notification.requestedUserName} At ${notification.requestGivenAt}</lable>
    <input type = "submit" value = "view">
    </form>
    </c:forEach>
</c:when>  
<c:otherwise>
<h1>No Notification's Found</h1>
</c:otherwise>
</c:choose>
<body>
</html>