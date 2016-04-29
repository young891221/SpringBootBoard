		
		
		<table class="table table-striped" id="subFlag">
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
				<tbody id="subList"></tbody>
			</table>
			
			
			<!-- Pagination -->
			<#--
			<div class="pagination pagination-centered">
			  <ul class="pagination">
			  	<li><a ng-href="/selectOptionBoard">&laquo;</a></li>
			    <div ng-if="pagingVO.startPage > pagingVO.pageBlock">
			  		<li><a ng-href="/selectOptionBoard?page={{pagingVO.startPage}} - {{pagingVO.pageBlock}}">&lsaquo;</a></li>
			  	</div>
			  	<div ng-if="pagingVO.startPage < pagingVO.pageBlock">
			  		<li><a href="#">&lsaquo;</a></li>
			    </div>

					<div ng-repeat="i in range">
						<div ng-if="pagingVO.currentPage = i">
	  						<li class="active">
	  							<a ng-href="/selectOptionBoard?page=i">{{i}}
	  								<span class="sr-only"></span>
	  							</a>
	  						</li>
  						</div>
  						<div ng-if="pagingVO.currentPage != i">
  							<li>
  							<a ng-href="/selectOptionBoard?page=i">{{i}} 
  								<span class="sr-only"></span>
  							</a>
  							</li>
  						</div>
  					</div>	
					
				 <div ng-if="pagingVO.endPage < pagingVO.pageCount">
			    	<li><a href="/selectOptionBoard?page=${pagingVO.startPage + pagingVO.pageBlock}">&rsaquo;</a></li>
			  	 </div>
			  	 <div ng-if="pagingVO.endPage > pagingVO.pageCount">
			  	 	<li><a href="#">&rsaquo;</a></li>
			  	 </div>
			  	 <li><a ng-href="/selectOptionBoard?page={{pagingVO.pageCount}}">&raquo;</a></li>
			  </ul>
			</div>
			-->
		