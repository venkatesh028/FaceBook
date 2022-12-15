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
    position: fixed;
    top : 0;
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
    border : 2px solid black;
    margin-top: 4%;
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


.postContainer {
    border: 2px solid black;
}

.post {
    display: inline-block;
    margin: 5px;
    background-color: grey;
    width: 24%;
    height: 40%;
}

.post .content{
    margin-top: 50px;
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


.btn {
  background-color: #2196f314;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  outline: none;
  padding:1px;
  margin-bottom: 0px;
}

.dropdown {
  position: absolute;
  display: inline-block;
}

.dropdown-content {
  display: none;
  background-color: white;
  position: absolute;
  min-width: 50px;
  z-index: 1;
}

.dropdown-content a:hover {background-color: #ddd}

.dropdown:hover .dropdown-content {
  display: block;
}

.btn:hover, .dropdown:hover .btn {
  background-color: #0b7dda;
}

.edit{
    display: none;
}

</style>
</head>

<c:choose>
<c:when  test="${sessionScope.userId != null}">

<body>
    <nav class="topNav">
       <ul class="navList">
       <il><a href="newsFeed" method="post">NewsFeed</a></il>
       <il><a href="addPost.jsp">AddPost</a></il>
       <il><a href="viewProfile">Profile</a></il>
       <il><a href="#contact">Notification</a></il>
       <il><a href="setting">Setting</a></il>
       <il class="logout"><a href="logout">Logout</a></il>
       </ul>
    </nav>  

    <div class="profileFrame">    
        <img src="iconforprofile.png">
        <h1>${profile.getUserName()}</h1>
        <h1>${profile.getBio()}</h1>
        <a href="getfriends"><h1>Friends :${profile.getFriendsCount()}</h1></a>
        <form action = "updateProfile"> 
            <button type="submit" class="EditButton">Edit</button>
        </form>

    </div>

    <div class="postContainer">

    <c:choose>
        <c:when test="${listOfPosts ne null}">
            <c:forEach items="${listOfPosts}" var = "post">
                <div class="post">      
                     <div class="dropdown">               
                         <button class="btn" style="border-left:1px solid #0d8bf2">
                         <i class="fa fa-caret-down"></i>
                         </button>
                      <div class="dropdown-content">
                          <form action="edit-post" method="get">
                              <input type="hidden" name="postId" value = ${post.id}>
                              <input type="submit" value="Edit">
                          </form>
                          <form action="delete-post" method="get">
                              <input type="hidden" name="postId" value = ${post.id}>
                              <input type="submit" value="Delete">
                          </form>
                       </div>
                     </div>
                <div class="content">
                    <p><b>${post.content}</b></p>
                </div>

               <div class="likediv">
                   <form action="profileAddLike" method="post">
                      <input type="hidden" name="postId" value = ${post.id}>
                      <button type="submit" class="likebutton">Like</button>
                   </form>
                   <form action="profilePage-likedUsers" method="get">
                      <input type="hidden" name="postId" value = ${post.id}>
                      <button type="submit class="count">${post.likeCount}</button>
                   </form>
               </div>

               <div class="commentdiv">
                  <form action="profilePage-viewComments" method="get">
                      <input type="hidden" name="postId" value = ${post.id}>
                      <button type="submit" class="commentbutton">Comment</button>
                  </form>
                  <form action="profilePage-viewComments" method="get">
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

</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>

</html>