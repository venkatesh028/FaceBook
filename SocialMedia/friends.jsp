<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

<style>
* {
    margin : 0px;
    padding: 0px;
}

.topNav {
    background: #1877f2;
    width: 100%;
    overflow: hidden;
    position: fixed;
} 

.navList{
 margin :0px;
}

.friends {
 padding-top: 5%;
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

.firends {
    
    text-align: center;    
}

</style>
</head>
<c:choose>
<c:when  test="${sessionScope.userId != null}">


<body>

<nav class = "topNav">
  <ul class="navList">
  <il><a href="viewProfile">Back</a></il>
  </ul>
</nav>

<div class = "friends">
<c:choose>
<c:when test = "${not empty friends}">
<c:forEach var = "friend" items = "${friends}">
<h1><img src="iconforprofile.png">${friend}</h1><br>
</c:forEach>
</c:when>
<c:otherwise>
<h1> No Friends</h1>
</c:otherwise>
</c:choose>
</div>

</body>

</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>

</html>