<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Upload Example</title>
<script type="text/javascript" src="<c:url value="/misc/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/submit.js" />"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
<script src="<c:url value="/misc/js/jquery.fileupload.js" />"></script>
<script src="<c:url value="/misc/js/jquery.iframe-transport.js" />"></script>
</head>
<body>
<form:form modelAttribute="uploadItem" id="uploadform" name="frm" enctype="multipart/form-data">
	<fieldset><legend>Upload File</legend>
	<table id='form'>
		<tr>                                                                                   
			<td><form:label for="fileData" path="fileData">File</form:label><br />
			</td>
			<td><form:input path="fileData" id="image" type="file" onchange="readURL(this);" /></td>
		</tr>
		<tr>
			<td><form:label path="fileData">Filter</form:label><br />
			</td>
			<td>
				<form:select path="filterType" name="filterType">
					<form:option value="volvo">Volvo</form:option>
					<form:option value="saab">Saab</form:option>
					<form:option value="fiat">Fiat</form:option>
					<form:option value="audi">Audi</form:option>
				</form:select>
			</td>
		</tr>
		<tr>
			<td><br />
			</td>
			<td><button class="button" type="submit">Apply Filter</button></td>
		</tr>
		
	</table>
	</fieldset>
</form:form>
<div>
	<img id="preview" />
</div>
</body>
</html>