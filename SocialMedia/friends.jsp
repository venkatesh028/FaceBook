<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<c:choose>
<c:when  test="${sessionScope.userId != null}">


<body>
<h1>Friends</h1>
<c:choose>
<c:when test = "${not empty friends}">
<c:forEach var = "friend" items = "${friends}">
<h1>${friend}</h1>
</c:forEach>
</c:when>
<c:otherwise>
<h1> No Friends</h1>
</c:otherwise>
</c:choose>

</body>

</c:when>
<c:otherwise>
<c:redirect url = "login.jsp"/>
</c:otherwise>
</c:choose>

</html>