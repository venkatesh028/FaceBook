<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>


<style>

*{
    margin:0px;
    padding: 0px 0px;   
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

.logout{
    float:right;
}

.profileFrame{
    text-align:center;
    margin:5px;
}

.EditButton {
  background-color: #1877f2;
  font-weight: Bold;
  font-size: 20px;
  border-radius: 8px;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 25%;
  opacity: 0.9;
}


.EditButton:hover {
  opacity:1;
}

h1{
   display:inline;
}

.postContainer {
    margin-left: 40px;
}

.post {
    display: inline-block;
    margin: 5px;
    background-color: grey;
}

.post .content{
    margin-top: 20px;
    margin-left:5px;
    margin-right:5px;
    border: 2px solid black;
    padding: 50px;
    text-align:center;
}

.post .likediv {
    display: inline-block;
    text-align: center;
    margin: 5px;
}

.post .commentdiv {
    display: inline-block;
    text-align: center;
    margin:5px;
}

.likediv .likebutton{
  background-color: #1877f2;
  font-weight: Bold;
  font-size: 20px;
  border-radius: 8px;
  color: white;
  padding: 5px 5px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.commentdiv .commentbutton{
  background-color: #1877f2;
  font-weight: Bold;
  font-size: 20px;
  border-radius: 8px;
  color: white;
  padding: 5px 5px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.noPostMessage {
    text-align: center;
}


</style>
</head>


<body>
<nav class="topNav">
  <ul class="navList">
  <il><a href="newsFeed" method="post">NewsFeed</a></il>
  <il><a href="addPost.jsp">AddPost</a></il>
  <il><a href="viewProfile">Profile</a></il>
  <il><a href="#contact">Notification</a></il>
  <il><a href="#about">Setting</a></il>
  <il class="logout"><a href="logout">Logout</a></il>
  </ul>
</nav>  

<div class="profileFrame">    
<img src="iconforprofile.png"><br>
<h1>${profile.getUserName()}</h1><br>
<h1>${profile.getBio()}</h1><br>
<a href="getfriends"><h1>Friends :${profile.getFriendsCount()}</h1></a><br>
<form action = "updateProfile">
<button type="submit" class="EditButton">Edit</button>
</form>

</div>

<div class="postContainer">

<c:choose>
    <c:when test="${listOfPosts ne null}">
        <c:forEach items="${listOfPosts}" var = "post">
            <div class="post">
                <div class="userName">
                <h1>${post.postedUserName}</h1>
                </div>
            <div class="content">
            <p><b>${post.content}</p>
            </div>
            <div class="likediv">
            <form action="addLike" method="post">
                <input type="hidden" name="postId" value = ${post.id}>
                <button type="submit" class="likebutton">Like</button>
            </form>
            <form action="likedUsers" method="get">
                <input type="hidden" name="postId" value = ${post.id}>
                <button type="submit class="count">${post.likeCount}</button>
            </form>
            </div>

            <div class="commentdiv">
            <form action="viewComments" method="get">
                <input type="hidden" name="postId" value = ${post.id}>
                <button type="submit" class="commentbutton">Comment</button>
            </form>
            <form action="viewComments" method="get">
                <input type="hidden" name="postId" value = ${post.id}>
                <button type="submit" class="count">${post.commentCount}</button>
            </form>
            </div>
            </div>
        </c:forEach>
     </c:when>
     <c:otherwise>
     <div class="noPostMessage">     
     <h1>No Post Uploaded</h1>
     <div>
     </c:otherwise>
</c:choose>

</div>

</body>
</html>