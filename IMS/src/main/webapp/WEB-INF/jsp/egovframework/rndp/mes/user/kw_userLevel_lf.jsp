<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>   

<script>
	$(document).ready(function() {
		 $('table[role="grid"].gridjs-table tr').css("cursor","default");
		 $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	});
	function go_insert(){
		document.listForm.action="/mes/user/kw_userLevel_if.do";
		document.listForm.submit();
	}
	function userLevelInfo(key){
		document.listForm.kClassKey.value = key;
		document.listForm.action="/mes/user/kw_userLevel_uf.do";
		document.listForm.submit();		
	}
	function userLevelDel(key){
		if(confirm("삭제하시겠습니까?")){
			document.listForm.kClassKey.value = key;
			document.listForm.action="/mes/user/kw_userLevel_d.do";
			document.listForm.submit();			
		}
	}
	
	function mesUserMenu(key){
		document.listForm.kClassKey.value = key;
		document.listForm.action = "/mes/user/kw_userMenu_vf.do";
		document.listForm.submit();
	}
</script>
<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<style>
td.gridjs-td:last-child{text-align:center !important;} 
</style>
<form name="listForm" id="listForm">	
<input type="hidden" name="kClassKey" id="kClassKey" />	

	<div class="content_top">
		<div class="content_tit">
			<h2>직급 관리</h2>	 
		</div>
	</div>
	
	<div class="data_table">
		<table id="myTable" style="width :100%;''">
			<colgroup>
				<col width="10%" />
				<col width="80%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
					<th>직급명</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="levelList" items="${levelList}" varStatus="i">
					<tr>
						<td>${i.index + 1}</td>
						<td>${levelList.kClassName}</td>
						<td>
							<div class="table_btn">
								<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
									<a class="form_btn sm" onclick="userLevelInfo(${levelList.kClassKey})">
										수정
									</a>
								</c:if>
							 	<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
									<a class="form_btn sm" onclick="userLevelDel(${levelList.kClassKey})">
										삭제
									</a>									
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="list_btm right">
		<div class="btns">
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
			<button type="button" class="form_btn active" onclick="go_insert()">등록</button>
			</c:if>
		</div>
	</div>
</form>