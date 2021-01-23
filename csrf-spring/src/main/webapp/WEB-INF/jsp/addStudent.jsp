<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<jsp:include page="menu.jsp" />
<body>
	<h3 style="color: red;">Add New Student</h3>

	<div id="addStudent">
		<%--@elvariable id="emp" type=""--%>
		<form:form action="/addNewStudent" method="post"
			modelAttribute="emp">
			<p>
				<label>Enter Employee Id</label>
				<form:input path="studId" />
			</p>
			<p>
				<label>Enter Name</label>
				<form:input path="studName" />
			</p>
			<input type="SUBMIT" value="Submit" />
				 <sec:csrfInput />     
			
		</form:form>
	</div>
</body>
</html>
