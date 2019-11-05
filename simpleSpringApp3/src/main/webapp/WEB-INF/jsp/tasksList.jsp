<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>--%>
</head>
<body>
<div>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text" name="summary" placeholder="enter summary"/>
        <input type="text" name="assignee" placeholder="enter assignee"/>
        <input type="date" name="startDate" placeholder="enter start Date">
        <input type="date" name="endDate" placeholder="enter end Date">
        <button type="submit">Add new Task</button>
    </form>
</div>


</body>

</html>