<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 그리드 S -->
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript">
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}
$(function(){
	tdBlock(6);
})

// 검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/item/cate/kw_cate_lf.do";
	document.frm.submit();
}

// 최상위 등록
function go_insert(){
	$("#mloader").show();
	document.frm.action = "/mes/item/cate/kw_cate_if.do";
	document.frm.submit();
}

// 하위 등록
function go_subInsert(key){
	$("#mloader").show();
	$("#sItemCateKey").val(key);
	
	document.frm.action = "/mes/item/cate/kw_cate_if.do";
	document.frm.submit();
}

// 상세
viewService.prototype.go_view = function(obj) {
	$("#mloader").show();
	$("#sItemCateKey").val(obj.childNodes[0].querySelector("input").value);
	
	document.frm.action = "/mes/item/cate/kw_cate_vf.do";
	document.frm.submit();
}

</script>
<style>
td.gridjs-td:last-child{text-align:center !important;} 
</style>
<form id="frm" name="frm" method="post" action="/mes/item/cate/kw_cate_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesCateVO.pageIndex}" />
	
	<input type="hidden" id="sItemCateKey" name="sItemCateKey" value="" />
	
	<div class="content_up">
		<div class="content_tit">
			<h2>품목분류관리</h2>
		</div>

		<div class="tbl_top">
			<ul class="tbl_top_left" >
				<li>
					<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
	              		<option value="10" <c:if test="${mesCateVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesCateVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesCateVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesCateVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	           		</select>
				</li>
				<li>
					<span>품목분류경로</span>
	        		<input type="text" class="input_search" id="searchWord" style="width:150px;" name="searchWord" value="${mesCateVO.searchWord}" maxlength="100"  onKeydown="if(event.keyCode == 13){fn_guestList(1);}"/>
	        	</li>
			</ul>
			<ul class="tbl_top_right">		
				<li>	
	        		<a style="cursor: pointer;" onclick="fn_guestList(1)">검색</a>
				</li>			
				<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">	
					<li>
		    			<a style="cursor: pointer;" onclick="go_insert();">최상위 추가</a>
		    		</li>
	    		</c:if>
	    	</ul>
	    </div>
	</div>

	<div  class="lf_tbl_list" id="tbl_a_list">
   		<table id="myTable" style="width:100%">
	  		<thead>
				<tr>
	      			<th style="width: 5%">순번</th>
	      			<th style="width: 35%">품목분류경로</th>
	      			<th style="width: 15%">품목분류명</th>
	      			<th style="width: 5%">코드번호</th>
	      			<th style="width: 5%">LOGO CODE</th>
	      			<th style="width: 15%">비고</th>
	      			<th style="width: 10%">관리</th>
	    		</tr>
	  		</thead>
	  		<tbody>
	  			<c:forEach var="cateList" items="${cateList}" varStatus="i">
	  				<tr style="cursor: pointer;" onclick="cateView('${cateList.sItemCateKey}');">
	  					<td>
	  						<c:out value="${paginationInfo.totalRecordCount - (mesCateVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}" />
	  						<input type="hidden"  value="${cateList.sItemCateKey}" />
	  					</td>
	  					<td style="text-align: left;">
	  						<c:out value="${cateList.sItemCatePath}" />
	  					</td>
	  					<td style="text-align: left;">
	  						<c:out value="${cateList.sItemCateName}" />
	  					</td>
	  					<td>
	  						<c:out value="${cateList.sItemCodeHead}" />
	  					</td>
	  					<td>
	  						<c:out value="${cateList.logoCode}" />
	  					</td>
	  					<td style="text-align: left;">
	  						<c:out value="${cateList.sItemCateEtc}" />
	  					</td>
	  					<td onclick="event.cancelBubble = true;">
	  						<c:if test="${cateList.sItemCateUKey eq 0}">
	  						<a class="mes_btn" onclick="go_subInsert('${cateList.sItemCateKey}');">하위추가</a>
	  						</c:if>
	  					</td>
	  				</tr>
	  			</c:forEach>
	  		</tbody>
		</table>
	</div>
	
 	 <div class="tbl_paging">
	  	<span>
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
	  	</span>
	</div>
  
</form>
