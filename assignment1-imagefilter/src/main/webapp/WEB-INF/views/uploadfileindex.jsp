<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<h2><a href="uploadfile.jsp">Upload An Image</a></h2>
<br>
<br>
<br>
<br>
<%
	if (session.getAttribute("uploadFile") != null
			&& !(session.getAttribute("uploadFile")).equals("")) {
%>
<h3>Uploaded File</h3>
<br>
<img
	src="<%="/assignment1-imagefilter/forms/image/image"%>"
/>
<%
	session.removeAttribute("uploadFile");
	}
%>
</body>
</html>