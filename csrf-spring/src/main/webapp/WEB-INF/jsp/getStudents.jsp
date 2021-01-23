<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="false"%>
<html>
<head>
<title>Show Employees</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	  	 <sec:csrfInput />     
	    
	<h3 style="color: red;">Show All Students</h3>
	<ul>
		<%--@elvariable id="employees" type="java.util.List"--%>
		<c:forEach var="listValue" items="${students}">
			<li>${listValue}</li>
		</c:forEach>
	</ul>
</body>
</html>