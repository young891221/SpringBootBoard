<!DOCTYPE html>
<html>
<head>
<title>ZUM BoardForm</title>
<link href="/resources/vendor/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="/resources/vendor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${helper.src('/resources/js/boardScriptForm.js')}"></script>
</head>
<body>

	<#-- top bar -->
	<#include '/layout/boardLayoutTop.ftl'>
	
	<#-- body -->
	<div class="row-fluid">
		<div class="span8 offset2">
			<br><br>
			<table class="table table-striped">
				<thead>
					<tr>
						<div class="pull-right">
							<select class="form-control" id="board_status" name="board_status">
								<option value="">--게시판상태--</option>
								<option value="0">임시저장</option>
								<option value="1">현재공지</option>
							</select> 
							<select id="board_group" >
								<option value="">--서비스분류--</option>
								<option value="zum">zum.com</option>
								<option value="shopping">shopping.zum.com</option>
								<option value="hub">hub.zum.com</option>
							</select>
						</div>
					</tr>
				</thead>
			</table>

			<table class="table table-striped">
				<tr>
					<th class="active">Title</th>
					<td><input type="text" class="span11 form-control"
						placeholder="Text input" id="board_title" name="board_title"></td>
				</tr>

				<tr>
					<th class="active">Suboard_title</th>
					<td><input type="text" class="span11 form-control"
						placeholder="Text input" id="board_sub_title" name="board_sub_title"></td>
				</tr>

				<tr>
					<th class="active">Content</th>
					<td><textarea id="board_content" name="board_content"
							class="span11 form-control input-sm ckeditor" id="message"
							placeholder="Message" maxlength="140" rows="7"
							style="height: 200px;"></textarea> 
						<span class="help-block"></span>
						<a type="button" class="btn" href="javascript:popupOpen();" >이미지 올리기</a>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" class="span11 form-control" value="${Session['member_email']}" id="member_email" name="member_email" readonly></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="span11" type="PassWord" placeholder="PassWord" id="member_pw" name="member_pw"/></td>
				</tr>
			</table>

			<br style="clear: both">
			<p class="pull-right">
				<button type="button" class="btn"
					onClick="javascript:window.location.href='/boardList'">All List</button>
				<button type="button" class="btn btn-inverse" onClick="boardInsert()">Insert</button>
			</p>
		</div>
	</div>
	
</body>
</html>

