<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>프로젝트명</title>
    
	<link rel="stylesheet" href="/resources/css/login.css" />
</head>
<body class="no-skin">
	<div class="container-fluid">
		<div class="layout">
			<h3><img src="/resources/images/login_logo.png"/> Front Pilot</h3>
			
			<#-- GOOGLE SIGNIN -->
			<form id="go_signin" action="/signin/google" method="POST">
				<input type="hidden" name="scope" value="email profile" />
				<input type="Image" name="submit button" src="/resources/images/google_login.png" ></input>
			</form>
			
		</div>
	</div>
</body>
</html>