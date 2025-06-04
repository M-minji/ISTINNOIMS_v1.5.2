<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script>
/* 페이징 */
function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	$("#listForm").attr("action", "/mes/user/kw_user_req_lf.do");
	$("#listForm").submit();
}

function mesUserResInsert(key){
	document.listForm.mesUserRequestKey.value = key;
	document.listForm.action = "/mes/user/kw_user_req_i.do";
	document.listForm.submit();
}

function mesUserResBut(key,gubun){
	$("#mesUserRequestKey").val(key);
	var actionUrl = ""
	if(gubun == "i" ){ 
		if(confirm("승인하시겠습니까?")){
			actionUrl ="/mes/user/kw_user_req_i.do";
		}else{
			return false;
		}
	}else if(gubun == "u" ){  
		actionUrl ="/mes/user/kw_user_req_uf.do";
	}else if(gubun == "d" ){  
		if(confirm("삭제하시겠습니까?")){
			actionUrl ="/mes/user/kw_user_req_d.do";
		}else{
			return false;
		}
	}
	
	if(actionUrl != ""){
		document.listForm.action=actionUrl;
		document.listForm.submit();
	}
}

$(function(){
	$('table[role="grid"].gridjs-table tr').css("cursor","default");
	tdBlock(5);
})
</script>
<style>
td.gridjs-td:last-child{text-align:center !important;}  
th.gridjs-th{padding: .7rem;}
td.gridjs-td{padding: .7rem;} 
</style>
<form name="listForm" id="listForm">		
	<input type="hidden" name="mesUserRequestKey" id="mesUserRequestKey" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesUserVO.pageIndex}" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>직원 신청 승인</h2>
		</div>
	</div>
		
	<div class="data_table nofirst">
		<table id="myTable" >
			<colgroup>
				<col width="7%" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>    
					<th>이름</th>
					<th>I D</th>
					<th>E-Mail</th>
					<th>휴대전화</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${mesUserList}" varStatus="i">
					<tr style="cursor: pointer;" onclick="mesUserResBut(${list.mesUserRequestKey},'u')">
						<td>
							${paginationInfo.totalRecordCount - (mesUserVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
						</td>
						<td>
							<a style="cursor: pointer;" onclick="mesUserReqInfo(${list.mesUserRequestKey});">
								${list.mesUserName}
							</a>
						</td>
						<td>${list.mesUserId}</td>
						<td>${list.mesUserEmail}</td>
						<td>${list.mesUserMobile1} - ${list.mesUserMobile2} - ${list.mesUserMobile3}</td>
						<td onclick="event.cancelBubble = true;">
							<div class="table_btn">
								<a class="form_btn sm" onclick="mesUserResBut(${list.mesUserRequestKey},'i')">
									승인
								</a>									
								<a class="form_btn sm" onclick="mesUserResBut(${list.mesUserRequestKey},'d')">
									삭제
								</a>									
							</div>						
						</td>		
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="list_btm center">
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
	</div>
			
	

</form>