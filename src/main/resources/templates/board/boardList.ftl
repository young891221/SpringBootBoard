<!DOCTYPE html>
<html>
<head>
<title>ZUM Board</title>
<link href="/resources/vendor/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="/resources/vendor/jquery/dist/jquery.min.js"></script>
<script src="/resources/vendor/handlebars/handlebars.min.js"></script>
<script src="/resources/vendor/underscore/underscore-min.js"></script>
<script src="/resources/js/templates.js"></script>
<script src="${helper.src('/resources/js/boardScriptList.js')}"></script>
</head>
<body>
	
	<#-- top bar -->
	<#include '/layout/boardLayoutTop.ftl'>
	
	<#-- body -->
	<div class="row-fluid">
		<div class="span10 offset1">
			<br><br><br>
			<div class="row-fluid">
				<div class="span12">
					<a class="btn pull-left" type="button" href="/boardForm">글작성</a>
					<Button class="btn pull-right" type="button" onClick="selectOptionList()">검색</button>
					<input class="span3 pull-right" type="text" style="margin-right:5px;" id="selectContent" name="selectContent">
					<select class="span2 pull-right"  style="margin-right:5px; display:none;" id="optionContent" name="optionContent">
						<option value="zum" selected>zum.com</option>
						<option value="shopping">shopping.zum.com</option>
						<option value="hub">hub.zum.com</option>
					</select>
					<select class="span2 pull-right"  style="margin-right:5px;" id="selectOption" name="selectOption">
						<option value="">--선택--</option>
						<option value="board_title">제목</option>
						<option value="board_group">서비스 분류</option>
					</select>
					<button type="button" class="btn pull-right" style="margin-right:5px;"
					onClick="javascript:window.location.href='/boardList'">Reload</button>
				</div>
			</div>
		<br>
		<div id="mainHide" name="mainHide">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>No</th>
						<th>서비스 분류</th>
						<th>제목</th>
						<th>작성 날짜</th>
						<th>수정 날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<#list selectBoardList as board>
						<tr>
							<td>${board.board_id}</td>
							<td>${board.board_group}</td>
							<#if board.board_status?has_content && board.board_status=0>
								<td><a href="javascript:window.location.href='/selectOneBoard?board_id=${board.board_id}'">[임시]${board.board_title}</a></td>
							<#else>
								<td><a href="javascript:window.location.href='/selectOneBoard?board_id=${board.board_id}'">[공지]${board.board_title}</a></td>
							</#if>
							<td>${board.board_insert_date}</td>
							<#if board.board_update_date??>
								<td>${board.board_update_date}</td>
							<#else>
								<td></td>
							</#if>
							<td>${board.board_view_count}</td>
						</tr>
					</#list>
				</tbody>
			</table>
			
			<!-- Pagination -->
			<div class="pagination pagination-centered">
			  <ul class="pagination">
			  	<li><a href="/boardList?page=1">&laquo;</a></li>
			    <#if (pagingVO.startPage > pagingVO.pageBlock)>
			  		<li><a href="/boardList?page=${pagingVO.startPage - pagingVO.pageBlock}">&lsaquo;</a></li>
			  	<#else>
			  		<li><a href="#">&lsaquo;</a></li>
			    </#if>
			    <#assign x=pagingVO.endPage>
					<#list pagingVO.startPage..x as i>
						<#if (pagingVO.currentPage = i)>
	  						<li class="active">
	  							<a href="/boardList?page=${i}">${i} 
	  								<span class="sr-only"></span>
	  							</a>
	  						</li>
  						<#else>
  							<li>
  							<a href="/boardList?page=${i}">${i} 
  								<span class="sr-only"></span>
  							</a>
  						</li>
  						</#if>
					</#list>
				 <#if (pagingVO.endPage < pagingVO.pageCount)>	
			    	<li><a href="/boardList?page=${pagingVO.startPage + pagingVO.pageBlock}">&rsaquo;</a></li>
			  	 <#else>
			  	 	<li><a href="#">&rsaquo;</a></li>
			  	 </#if>
			  	 <li><a href="/boardList?page=${pagingVO.pageCount}">&raquo;</a></li>
			  </ul>
			</div>
		</div>
		
		<div id="subHide" name="subHide" style="display:none">
			<#include '/board/boardSubList.ftl'>
		</div>
	
		</div> 
	</div>
</body>
</html>

