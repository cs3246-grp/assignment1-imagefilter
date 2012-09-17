<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Upload Example</title>
<script type="text/javascript" src="<c:url value="/misc/js//jquery-1.8.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/util.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/jquery-fileupload/vendor/jquery.ui.widget.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/jquery-fileupload/jquery.iframe-transport.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/jquery-fileupload/jquery.fileupload.js" />"></script>
<script type="text/javascript" src="<c:url value="/misc/js/jquery.json-2.3.min.js" />"></script>

<script type="text/javascript">
$(function() {
	init();
});

function init() {
	$('#uploadForm').submit(function(event) {
		event.preventDefault();

		$.post('./uploadfile/filter', 
				{filtertype:$('#filtertype').val()},
				function(result) {
					$('#afterFilter').attr('src','./image/'+result).width(400);
				})
	})

	$('#upload').fileupload({
		dataType: 'image',
		change: function (e, data) {
			$.each(data.files, function(index, file) {
				$('#preview').attr('src','./image/'+file.name).width(400);
			})
		}
	})
}
</script>
</head>
<body>
<form id='uploadForm'>
	<fieldset>
		<legend>Image</legend>
		<label for='upload'>Choose a image:</label>
		<input id="upload" type="file" name="file" data-url="./uploadfile" >
		<br />
		<select id='filtertype'>
			<option value='blur'>blur</option>
			<option value='compound'>oil and old</option>
			<option value='lomo'>lomo</option>
			<option value='oil'>oil</option>
			<option value='old'>old</option>
		</select>
		<br />
		<input type='submit' value='apply' id='submit' />
	</fieldset>
</form>
<div>
	<img id='preview' />
	<img id='afterFilter' />
</div>
</body>
</html>