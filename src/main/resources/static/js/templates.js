this["Handlebars"] = this["Handlebars"] || {};
this["Handlebars"]["templates"] = this["Handlebars"]["templates"] || {};

this["Handlebars"]["templates"]["boardSubList"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper, options, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression, alias5=helpers.blockHelperMissing, buffer = 
  "				<tr>\r\n					<td>"
    + alias4(((helper = (helper = helpers.board_id || (depth0 != null ? depth0.board_id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_id","hash":{},"data":data}) : helper)))
    + "</td>\r\n					<td>"
    + alias4(((helper = (helper = helpers.board_group || (depth0 != null ? depth0.board_group : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_group","hash":{},"data":data}) : helper)))
    + "</td>\r\n";
  stack1 = ((helper = (helper = helpers.isStatus || (depth0 != null ? depth0.isStatus : depth0)) != null ? helper : alias2),(options={"name":"isStatus","hash":{},"fn":container.program(2, data, 0),"inverse":container.program(4, data, 0),"data":data}),(typeof helper === alias3 ? helper.call(alias1,options) : helper));
  if (!helpers.isStatus) { stack1 = alias5.call(depth0,stack1,options)}
  if (stack1 != null) { buffer += stack1; }
  buffer += "					<td>"
    + alias4(((helper = (helper = helpers.board_insert_date || (depth0 != null ? depth0.board_insert_date : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_insert_date","hash":{},"data":data}) : helper)))
    + "</td>\r\n";
  stack1 = ((helper = (helper = helpers.isUpdateDate || (depth0 != null ? depth0.isUpdateDate : depth0)) != null ? helper : alias2),(options={"name":"isUpdateDate","hash":{},"fn":container.program(6, data, 0),"inverse":container.program(8, data, 0),"data":data}),(typeof helper === alias3 ? helper.call(alias1,options) : helper));
  if (!helpers.isUpdateDate) { stack1 = alias5.call(depth0,stack1,options)}
  if (stack1 != null) { buffer += stack1; }
  return buffer + "					<td>"
    + alias4(((helper = (helper = helpers.board_view_count || (depth0 != null ? depth0.board_view_count : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_view_count","hash":{},"data":data}) : helper)))
    + "</td>\r\n				</tr>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "						<td><a href=\"javascript:window.location.href='/selectOneBoard?board_id="
    + alias4(((helper = (helper = helpers.board_id || (depth0 != null ? depth0.board_id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_id","hash":{},"data":data}) : helper)))
    + "'\">[임시]"
    + alias4(((helper = (helper = helpers.board_title || (depth0 != null ? depth0.board_title : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_title","hash":{},"data":data}) : helper)))
    + "</a></td>\r\n";
},"4":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "						<td><a href=\"javascript:window.location.href='/selectOneBoard?board_id="
    + alias4(((helper = (helper = helpers.board_id || (depth0 != null ? depth0.board_id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_id","hash":{},"data":data}) : helper)))
    + "'\">[공지]"
    + alias4(((helper = (helper = helpers.board_title || (depth0 != null ? depth0.board_title : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"board_title","hash":{},"data":data}) : helper)))
    + "</a></td>\r\n";
},"6":function(container,depth0,helpers,partials,data) {
    var helper;

  return "						<td>"
    + container.escapeExpression(((helper = (helper = helpers.board_update_date || (depth0 != null ? depth0.board_update_date : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"board_update_date","hash":{},"data":data}) : helper)))
    + "</td>\r\n";
},"8":function(container,depth0,helpers,partials,data) {
    return "						<td></td>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1, helper, options, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.lambda, alias5=container.escapeExpression, buffer = 
  "		\r\n		\r\n";
  stack1 = ((helper = (helper = helpers.list || (depth0 != null ? depth0.list : depth0)) != null ? helper : alias2),(options={"name":"list","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data}),(typeof helper === alias3 ? helper.call(alias1,options) : helper));
  if (!helpers.list) { stack1 = helpers.blockHelperMissing.call(depth0,stack1,options)}
  if (stack1 != null) { buffer += stack1; }
  return buffer + "			\r\n			\r\n			<!-- Pagination -->\r\n			<!--\r\n			<#--\r\n			<div class=\"pagination pagination-centered\">\r\n			  <ul class=\"pagination\">\r\n			  	<li><a ng-href=\"/selectOptionBoard\">&laquo;</a></li>\r\n			    <div ng-if=\"pagingVO.startPage > pagingVO.pageBlock\">\r\n			  		<li><a ng-href=\"/selectOptionBoard?page="
    + alias5(alias4(((stack1 = (depth0 != null ? depth0.pagingVO : depth0)) != null ? stack1.startPage : stack1), depth0))
    + " - "
    + alias5(alias4(((stack1 = (depth0 != null ? depth0.pagingVO : depth0)) != null ? stack1.pageBlock : stack1), depth0))
    + "\">&lsaquo;</a></li>\r\n			  	</div>\r\n			  	<div ng-if=\"pagingVO.startPage < pagingVO.pageBlock\">\r\n			  		<li><a href=\"#\">&lsaquo;</a></li>\r\n			    </div>\r\n\r\n					<div ng-repeat=\"i in range\">\r\n						<div ng-if=\"pagingVO.currentPage = i\">\r\n	  						<li class=\"active\">\r\n	  							<a ng-href=\"/selectOptionBoard?page=i\">"
    + alias5(((helper = (helper = helpers.i || (depth0 != null ? depth0.i : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"i","hash":{},"data":data}) : helper)))
    + "\r\n	  								<span class=\"sr-only\"></span>\r\n	  							</a>\r\n	  						</li>\r\n  						</div>\r\n  						<div ng-if=\"pagingVO.currentPage != i\">\r\n  							<li>\r\n  							<a ng-href=\"/selectOptionBoard?page=i\">"
    + alias5(((helper = (helper = helpers.i || (depth0 != null ? depth0.i : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"i","hash":{},"data":data}) : helper)))
    + " \r\n  								<span class=\"sr-only\"></span>\r\n  							</a>\r\n  							</li>\r\n  						</div>\r\n  					</div>	\r\n					\r\n				 <div ng-if=\"pagingVO.endPage < pagingVO.pageCount\">\r\n			    	<li><a href=\"/selectOptionBoard?page=${pagingVO.startPage + pagingVO.pageBlock}\">&rsaquo;</a></li>\r\n			  	 </div>\r\n			  	 <div ng-if=\"pagingVO.endPage > pagingVO.pageCount\">\r\n			  	 	<li><a href=\"#\">&rsaquo;</a></li>\r\n			  	 </div>\r\n			  	 <li><a ng-href=\"/selectOptionBoard?page="
    + alias5(alias4(((stack1 = (depth0 != null ? depth0.pagingVO : depth0)) != null ? stack1.pageCount : stack1), depth0))
    + "\">&raquo;</a></li>\r\n			  </ul>\r\n			</div>\r\n			-->\r\n			-->\r\n		";
},"useData":true});