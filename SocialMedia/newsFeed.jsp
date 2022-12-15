<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>
<link rel="stylesheet" href="css/navStyle.css">

<style>

body{
background-color: #efefef;
}

.topNav {
    background: #1877f2;
    width: 100%;
    overflow: hidden;
    position: fixed;
    top: 0;
} 

*{
    margin:0px;
    padding: 0px 0px;
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

.topnav a.active {
  background-color: #04AA6D;
  color: white;
}

.logout{
    float:right;
}

.feed {
  margin: auto;
  margin-top: 20px;
  width: 50%;
  border: 3px;
  padding: 10px;
  padding-top: 4%;
}

.post{
  border: 3px solid black;
  margin:15px;
}

.userName{ 
    border:2px;
    padding: 2px;
}

.content{
    margin-top:5px;
    margin-left:10px;
    margin-right:10px;
    text-align: center;
    border:2px solid blue;
    padding:10px;
}

h3{
    margin-top: 0px;
    margin-bottom: 0px;
}

table{
   width:100%;   
}

td{
    text-align: center;
}
  
input[type=submit]{
  background-color: blue;
  border: 2px solid black;
  border-radius:10px;
  color: white;
  padding: 16px 36px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}


</style>
</head>

<body>
<nav class="topNav">
  <ul class="navList">
  <il><a href="newsFeed" method="post">NewsFeed</a></il>
  <il><a href="addPost.jsp">AddPost</a></il>
  <il><a href="viewProfile">Profile</a></il>
  <il><a href="notification">Notification</a></il> 
  <il><a href="search.jsp">Search</a></il>
  <il class="logout"><a href="logout">Logout</a></il>
  </ul>
</nav>

<c:choose>
<c:when  test="${sessionScope.userId != null}">

<div class="feed">
    <c:forEach items="${listOfPosts}" var="post">  
        <div class="post">
            <div class="userName">
                <h3>${post.postedUserName}</h3>
            </div>

            <div class="content">
                <p><b>${post.content}</b></p>
            </div>

            <div>
            <table>
                <tr>
                    <td>
                        <form action="addLike" method="post">
                            <input type="hidden" name="postId" value = ${post.id}>
                            <input type="submit" value="Like">
                        </form>
                        <form action="likedUsers" method="get">
                            <input type="hidden" name="postId" value = ${post.id}>
                            <button type="submit">${post.likeCount}</button>
                        </form>        
                    </td>
                    <td>
                        <form action="viewComments">
                            <input type="hidden" name="postId" value = ${post.id}>
                            <input type="submit" value="Comment">
                        </form>
                         <form action="viewComments" method="get">
                            <input type="hidden" name="postId" value = ${post.id}>
                            <button type="submit">${post.commentCount}</button>
                        </form>
                    </td>      
               </tr>         
            </table>
            </div>
        </div>  
    </c:forEach>
</div>
</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>


</body>
</html>