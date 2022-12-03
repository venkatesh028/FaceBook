<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ideasBook</title>
    <link rel="stylesheet" href="css/postPage.css">

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

.postframe {
  margin: auto;
  margin-top:25px;
  width: 50%;
  border: 3px solid blue;
  padding: 10px;
  text-align:center;
}

textarea{
padding-left: 0.8rem;
margin-top: 50px;
border :3px solid purple;
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

<c:choose>
<c:when  test="${sessionScope.userId != null}">

<body>

<c:choose>
<c:when test="${null == post}">

<div class="topnav">
<a href="newsFeed" method="get">Back</a>
</div>
<div class="postframe">
    <form action = "addPost" method = "post">
        <textarea name="content" value = "Hii" placeholder="Write your toughts..."rows="5" cols="50" required></textarea><br><br>
        <input type="submit" value="Post">
    </form>
</div>
</c:when>

<c:otherwise>
<div class = "topnav">
<a href="viewProfile" method="get">Back</a>
</div>

<div class="postframe">
    <form action = "update-post" method = "get">
        <textarea name = "content" placeholder = "Write your toughts..."rows="5" cols="50">${post.content}</textarea><br><br>
        <input type = "hidden" value = "${post.id}" name = "postId">
        <input type = "submit" value = "Update">
    </form>
</div>
</c:otherwise>

</c:choose>
</body>


</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>
</html>