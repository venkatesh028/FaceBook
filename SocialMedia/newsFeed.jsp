<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>
<link rel="stylesheet" href="css/navStyle.css">
</head>
<body>
<div class="topnav">
  <ul class="navList">
  <il><a href="newsFeed" method="post">NewsFeed</a></il>
  <il><a href="addPost.jsp">AddPost</a></il>
  <il><a href="viewProfile">Profile</a></il>
  <il><a href="">Notification</a></il> 
  <il><a href="search.jsp">Search</a></il>
  <il><a href="setting">Setting</a></il>
  <il class="logout"><a href="logout">Logout</a></il>
  </ul>
</div>

<div class="feed">
    <c:forEach items="${listOfPosts}" var = "post">  
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
                            <input type="hidden" name="commentedUserId" value = ${commentedUserId}>
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
</body>
</html>