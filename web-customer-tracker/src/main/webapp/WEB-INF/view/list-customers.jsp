<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>
<head>
    <title>List Customers</title>

    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <p>
            User: <security:authentication property="principal.username"/>

            Role: <security:authentication property="principal.authorities"/>
        </p>

        <%--add customer--%>
        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd';
               return false;"
               class="add-button"
        />
        </security:authorize>

        <%--search customer--%>
        <form:form action="search" method="post">
            Search customer: <input type="text" name="searchName"/>
            <input type="submit" value="Search" class="add-button">
        </form:form>

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="customer" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormUpdate">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>

                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${customer.id}"/>
                </c:url>

                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>

                    <td>
                        <%--display update link--%>
                        <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                            <a href="${updateLink}">Update</a>
                        </security:authorize>

                        <security:authorize access="hasAnyRole('ADMIN')">
                            <a href="${deleteLink}"
                               onclick="if (!confirm('Are you sure want to delete this customer')) return false">
                                Delete</a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<hr>

<%--Logout button--%>
<form:form action="/logout" method="post">
    <input type="submit" value="Logout" class="add-button"/>
</form:form>

</body>
</html>