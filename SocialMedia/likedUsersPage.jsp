<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ideasBook</title>
    <link rel="stylesheet" href="css/likedUsersPage.css">
</head>
<body>
<div class="topnav">
<a href="newsFeed" method="get">Back</a>
</div>
<div class="likedUsersFrame">
    <c:choose>
    <c:when test="${likedUsers ne null}">
        <table> 
        <c:forEach items="${likedUsers}" var = "user">          
              <tr>
                  <td>
                  <img src="iconforprofile.png" alt="mine" width='100px' height='100px'>
                  </td>
                  <td> 
                  <h2>${user}</h2>
                  </td>
              </tr>            
        </c:forEach>
        </table>
    </c:when>    
    <c:otherwise>
        <br><br>
        <h1>No user found</h1>
        <br>
    </c:otherwise>
    </c:choose>       
</div>
</body>
</html>