<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>ideasBook</title>
</head>
<body>
<div>
<c:forEach items="${listOfPosts}" var = "post">    
    ${post}<br>
</c:forEach>
</div>
<form action ="viewProfile" method = "get">

<input type="submit" value ="View Profile">
</form>
</body>
</html>