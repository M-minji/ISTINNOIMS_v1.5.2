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
	document.frm.action = "/mes/contact/kw_contact_lf.do";
	document.frm.submit();
}
function go_insert() {
	document.frm.action = "/mes/contact/kw_contact_if.do";
	document.frm.submit();
}
// 저장
viewService.prototype.go_view = function(obj) {
	$("#mloader").show();
	$("#eContactNum").val(obj.childNodes[0].querySelectorAll("input")[0].value);
	document.frm.action = "/mes/contact/kw_contact_uf.do";
	document.frm.submit();
}
$(document).ready(function() {
	setTimeout(() => {
	    document.activeElement.blur(); // 현재 포커스된 요소 포커스 해제
	  }, 100);
	$('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	$('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '80px');
	$('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '150px');
	$('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '150px');
});
</script>
<style>
td.gridjs-td{padding:.7rem;}
</style>              
<form id="frm" name="frm" method="post">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesContactVO.pageIndex}" />
	
	<input type="hidden" id="eContactNum" name="eContactNum" value="" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>담당자 정보 관리 </h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>담당자명</span>		
		            	<input type="text" class="input_search" id="searchWord" name="searchWord" value="${mesContactVO.searchWord}" maxlength="20"/>
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
		           	<th>소속사</th>
		           	<th>부서</th>
		           	<th>담당자명</th>
					<th>연락처</th>
					<th>이메일</th>
					<th>기타</th>
				</tr>
    	   	</thead>
       		<tbody>
         		<c:forEach var="list" items="${infoList}" varStatus="i">
         			<tr style="cursor: pointer;" onclick="go_update('${list.eContactNum}');">
           				<td>
           					${paginationInfo.totalRecordCount - (mesContactVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
           					<input type="hidden" value="${list.eContactNum}" />
           				</td>
           				<td>
           					${list.eAgencyName}
           				</td>
           				<td>
           					${list.eDepartmentName}
           				</td>
           				<td>
           					 ${list.eContactName} 
           				</td>
           				<td>
           					 ${list.ePhoneNumber} 
           				</td>
           				<td>
           					 ${list.eEmail}
           				</td>
           				<td>
           				     ${list.eNotes}
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
			<button type="button" class="form_btn active" onclick="go_insert();">등록</button>
		</div>
	</div>
</form>
