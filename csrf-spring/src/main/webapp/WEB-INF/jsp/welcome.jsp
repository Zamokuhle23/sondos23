<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="false"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
<%--	<jsp:include page="../../../../../../../boot-security/src/main/webapp/WEB-INF/jsp/menu.jsp" />--%>
	�<sec:csrfInput /> ����
	<h3 style="color: red;">Hello Masondo</h3>
</body>
</html>