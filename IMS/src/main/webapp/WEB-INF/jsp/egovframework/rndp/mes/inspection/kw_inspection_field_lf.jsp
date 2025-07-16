<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript">
// 검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/inspection/kw_inspection_field_lf.do";
	document.frm.submit();
}
function go_insert() {
	document.frm.action = "/mes/inspection/kw_inspection_field_if.do";
	document.frm.submit();
}
// 저장
viewService.prototype.go_view = function(obj) {
	$("#mloader").show();
	$("#eFieldKey").val(obj.childNodes[0].querySelectorAll("input")[0].value);
	document.frm.action = "/mes/inspection/kw_inspection_field_uf.do";
	document.frm.submit();
}

$(document).ready(function() {
	$('#searchWord').blur();
	$('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	$('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '80px');
	$('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '150px');
	console.clear();

});
</script>
<style>
td.gridjs-td{padding:.7rem;}
</style>              
<form id="frm" name="frm" method="post">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesInspectionVO.pageIndex}" />
	
	<input type="hidden" id="eFieldKey" name="eFieldKey" value="" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>점검결과 필드관리</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<select name="searchType" class="select_search" id="searchType"  >
							<option value="1"
								<c:if test="${mesInspectionVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
							<option value="2"
								<c:if test="${mesInspectionVO.searchType eq 2}">selected="selected"</c:if>>이름</option>
							<option value="3"
								<c:if test="${mesInspectionVO.searchType eq 3}">selected="selected"</c:if>>필드</option>
						</select>
					</li>
					<li> 
						<input name="searchWord" type="text" class="searchWord" style="width:150px;" id="searchWord" value="${mesInspectionVO.searchWord}"  onkeypress="if(event.keyCode == 13 ){fn_guestList(1);}" maxlength="20"/>  
					</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>

	<div class="data_table">
   		<table id="myTable" >
    	   	<thead>
				<tr>
		           	<th style="width: 10%;">No.</th>
		           	<th>이름</th>
					<th>필드1</th>
					<th>필드2</th>
					<th>필드3</th>
					<th>필드4</th>
					<th>필드5</th>
				</tr>
    	   	</thead>
       		<tbody>
         		<c:forEach var="list" items="${fieldList}" varStatus="i">
         			<tr style="cursor: pointer;" onclick="go_update('${list.eFieldKey}');">
           				<td>
           					${paginationInfo.totalRecordCount - (mesInspectionVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
           					<input type="hidden" value="${list.eFieldKey}" />
           				</td>
           				<td>
           					${list.eFieldName}
           				</td>
           				<td>
           					${list.eField1}
           				</td>
           				<td>
           					 ${list.eField2} 
           				</td>
           				<td>
           					 ${list.eField3} 
           				</td>
           				<td>
           					 ${list.eField4}
           				</td>
           				<td>
           				     ${list.eField5}
           				</td>
         			</tr>
         		</c:forEach>
       		</tbody>
		</table>
	</div>

	<div class="list_btm">
		<div class="options"></div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' || staffVO.kAdminAuth eq 'T'}">
			<button type="button" class="form_btn active" onclick="go_insert();">등록</button>
			</c:if>
		</div>
	</div>
</form>
