<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ideasBook</title>
    <link rel="stylesheet" href="css/searchPage.css">
</head>
<body>

<div class="topnav">
<a href="newsFeed" method="get">Back</a>
</div>

<div class="searchPage">
    <form action="search" method="get">
        <input type="text" name="userName" placeholder="userName">
         <input type="submit" value="Search">
    </form>

    <c:choose>
    <c:when test="${profile ne null}">
            <table cellpadding='20px'>
            <tr>
                <td>
                <img src="profile.jpg" alt="mine" width='200px' height='200px'>
                </td>
                <td class="info">
                <h1>${profile.getUserName()}</h1>
                <h1>${profile.getBio()}</h1>
                <h1>Friends :${profile.getFriendsCount()}</h1>
                </td>
            </tr>  
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