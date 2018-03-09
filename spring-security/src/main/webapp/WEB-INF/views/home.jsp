<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Home page</title>
</head>

<body>
<h2>Home Page</h2>

<%--Display user name & role--%>
<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Role: <security:authentication property="principal.authorities"/>
</p>

<%--Add link to point to /leaders--%>
<security:authorize access="hasRole('MANAGER')">
<p>
    <a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
</p>
</security:authorize>


<%--Add link to point to /systems--%>
<security:authorize access="hasRole('ADMIN')">
<p>
    <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
</p>
</security:authorize>

<hr>
<%--Add logout button--%>
<form:form action="/logout" method="post">
    <input type="submit" value="Logout"/>
</form:form>

</body>
</html>
