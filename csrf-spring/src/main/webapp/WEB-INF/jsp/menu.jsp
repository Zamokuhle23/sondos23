<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

	<a href="${pageContext.request.contextPath}/welcome">Home</a> | &nbsp;

	<a href="${pageContext.request.contextPath}/addNewStudent">Add
        Employee</a> |   <a
        href="${pageContext.request.contextPath}/getStudents">Show
        Employees</a> |   <u><h2 style="color: red;">
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2></u>

    <form id="logoutForm" method="POST" action="${contextPath}/logout">
<%--    	 <sec:csrfInput />     --%>
    
    </form>

</div>