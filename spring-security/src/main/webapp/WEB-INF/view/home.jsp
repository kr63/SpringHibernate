<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
</head>

<body>
<h2>Home Page</h2>

<%--Add logout button--%>
<form:form action="/logout" method="post">
    <input type="submit" value="Logout"/>
</form:form>

</body>
</html>
