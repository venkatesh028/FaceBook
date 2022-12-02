<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>
</head>
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



<form action="addComment" method="Post">
<input type="hidden" name="postId" value=${postId}>
<input type="text" name="content">
<input type="submit" value="comment">
</form>
<c:choose>
    <c:when test="${listOfComments ne null}">
        <c:forEach items="${listOfComments}" var = "comment">   
            <table>
            <td>
            <h3>${comment.commentedUserName}</h3>
            <h3>${comment.content}</h3>
            </td>
            <td>
            <form>
            <input type="hidden" name="postId">
            </form>
            </td>       
        </c:forEach>
    </c:when>    
    <c:otherwise>
        <br><br>
        <h1>No comments found</h1>
        <br>
    </c:otherwise>
</c:choose>  
</body>
</html>