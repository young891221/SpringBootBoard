<!DOCTYPE html>
<html>
<head>
<title>ZUM BoardView</title>
<link href="/resources/vendor/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="/resources/vendor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${helper.src('/resources/js/boardScriptView.js')}"></script>
</head>
<body>
	
	<#-- top bar -->
	<#include '/layout/boardLayoutTop.ftl'>
	
	<input type="hidden" id="board_id" name="board_id" value="${selectOneBoard.board_id}"/>
	<input type="hidden" id="status" name="status" value="${selectOneBoard.board_status}"/>
	<input type="hidden" id="group" name="group" value="${selectOneBoard.board_group}"/>
	
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
							<select id="board_group" name="board_group">
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
					<th class="active">Writing Date</th>
					<td><input type="text" class="span11 form-control" value="${selectOneBoard.board_insert_date}" readonly></td>
				</tr>

				<tr>
					<th class="active">Title</th>
					<td><input type="text" class="span11 form-control"
						placeholder="Text input" id="board_title" value="${selectOneBoard.board_title}"></td>
				</tr>



				<tr>
					<th class="active">Suboard_title</th>
					<td><input type="text" class="span11 form-control"
						placeholder="Text input" id="board_sub_title" value="${selectOneBoard.board_sub_title}"></td>
				</tr>

				<tr>
					<th class="active">Content</th>
					<td><textarea id="board_content" name="board_content"
							class="span11 form-control input-sm ckeditor"
							placeholder="Message" maxlength="140" rows="7"
							style="height: 200px;">${selectOneBoard.board_content}</textarea>
						<#if selectOneBoard.board_image?exists>
							<span class="help-block" name="change_image">${selectOneBoard.board_image}</span>
							<input type="hidden" id="board_image" name="board_image" value="${selectOneBoard.board_image}"/>
						</#if>
						<a type="button" class="btn" href="javascript:popupOpen();" >이미지 올리기</a>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input class="span11" type="text" placeholder="example@zuminternet.com" id="member_email" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input class="span11" type="password" placeholder="PassWord" id="member_pw" /></td>
				</tr>
			</table>

			<br style="clear: both">
			<p class="pull-right">
				<button type="button" class="btn"
					onClick="javascript:window.location.href='/boardList'">All List</button>
				<#if selectOneBoard.member_id.member_email = Session['member_email']>
					<button type="button" class="btn btn-primary" onClick="memberCheck('update')">Update</button>
					<button type="button" class="btn btn-success" onClick="memberCheck('delete')">Delete</button>
				</#if>
			</p>
		</div>
	</div>

</body>
</html>

