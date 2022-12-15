<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ideasBook</title>
<style>
    
*{
    margin:0px;
    padding: 0px 0px;
}


body{
    background-color: #efefef;
}

.topnav {
  background-color: #1877f2;
  overflow: hidden;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.info {
    padding-left: 50px;
    padding-right: 50px;
}

.searchPage{
  margin: auto;
  margin-top: 20px;
  width: 50%;
  padding: 10px;
  text-align:center;
}

 input[type=text] {
  padding: 6px;
  border: none;
  margin-top: 8px;
  margin-right: 16px;
  font-size: 17px;
}

.button {
    border-radius: 8px;
    padding :10px 10px;
    cursor: pointer;
    background: #1877f2;
    font-weight: bold;
    color: white;
}

.button:hover {
    background: #efefef;
    color: black;
}

</style>


</head>

<c:choose>
<c:when  test="${sessionScope.userId != null}">

<body>

<div class="topnav">
<a href="newsFeed" method="get">Back</a>
</div>

<div class="searchPage">
    <form action="search" method="get">
        <input type="text" name="userName" placeholder="userName">
         <input type="submit" value = "Search" class="button">
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
                ${isFriend}
                <c:if test = "${isFriend == 'false'}">
                <form action = "add-friend">
                <input type = hidden value = ${profile.userId} name = "searchedProfileId"/> 
                <input type = "submit" value = "Add friend">
                </form>
                </c:if>
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

</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>
</html>