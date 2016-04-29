<html>
<head>
<title>ZUM BoardPop</title>
<link href="/resources/vendor/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="/resources/vendor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${helper.src('/resources/js/boardScriptFormPop.js')}"></script>
</head>
<body>
	<div class="text-center">
		<div style="padding: 15px;">
			<form id="upload-file-form" name="upload-file-form">
		  		<input class="btn btn-success" id="upload-file-input" type="file" name="uploadFile" accept="*" />
			</form>
		</div>
		<div>
			<img id="getImage" src="" style="height:60%; weight:60%;">
		</div>
		<div style="padding: 20px;">
			<a type="button" id="complete" class="btn">완료</a>
		</div>
	</div>
	
</body>
</html>