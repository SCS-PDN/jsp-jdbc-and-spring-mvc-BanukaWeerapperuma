<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Available Courses</title>
</head>
<body>
<h2>Available Courses</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Instructor</th>
        <th>Credits</th>
        <th>Action</th>
    </tr>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.courseId}</td>
            <td>${course.name}</td>
            <td>${course.instructor}</td>
            <td>${course.credits}</td>
            <td>
                <form action="${pageContext.request.contextPath}/register/${course.courseId}" method="post">
                    <input type="submit" value="Register"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
