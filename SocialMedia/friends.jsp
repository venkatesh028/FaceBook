<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
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
</html>