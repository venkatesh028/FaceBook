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

.commentPage{
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

.comment {
    text-align: left;
    margin-top: 10px;

}

h3{ 
    display: inline;
}

.edit {
    display: inline;
}

.delete{
    display: inline;
}
    
</style>

</head>

<c:choose>
<c:when  test="${sessionScope.userId != null}">


<body>

<c:choose>
    <c:when test="${root == 'profile'}">
        <div class="topnav">
            <a href="viewProfile" method="get">Back</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="topnav">
            <a href="newsFeed" method="get">Back</a>
        </div>
    </c:otherwise>
</c:choose>

<c:choose>
<c:when test = "${comment ne null}">
<div class="commentPage">
    <form action="update-comment" method="Post">
    <input type= "hidden" name="id" value="${comment.id}">
    <input type= "text"   name = "content" value="${comment.content}">
    <input type= "submit" value="Update">
    </form>
</div>
</c:when>

<c:otherwise>
<div class="commentPage">
    <form action="addComment" method="Post">
    <input type= "hidden" name="postId" value=${postId}>
    <input type= "text" name="content">
    <input type="submit" value="comment">
    </form>
    <c:choose>
    <c:when test="${not empty listOfComments}">
        <c:forEach items="${listOfComments}" var = "comments">   

            <div class="comment">
            <h3>${comments.commentedUserName}</h3><h3><b>:</b> ${comments.content}</h3>
            <c:if test = "${sessionScope.userId == comments.commentedUserId}">
                <form action = "edit-comment" class = "edit">
                    <input type = "hidden" name = "commentId" value = ${comments.id}>
                    <input type = "submit" value = "Edit">
                </form>
                <form action = "delete-comment" class = "delete" method = "Get"> 
                     <input type="hidden" name="postId" value=${postId}>
                    <input type = "hidden" name = "commentId" value = ${comments.id}>
                    <input type = "submit" value= "delete">
                </form>
            </c:if>
            <div>  
        </c:forEach>
    </c:when>    
    <c:otherwise>
        <br><br>
        <h1>No comments found</h1>
        <br>
    </c:otherwise>
    </c:choose> 
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