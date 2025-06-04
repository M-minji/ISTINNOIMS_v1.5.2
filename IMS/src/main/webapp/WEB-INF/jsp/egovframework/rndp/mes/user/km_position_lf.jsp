<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<script type="text/javascript">

function delete_list2(){
	var msg = "정말로 삭제하시겠습니까?";
	return confirm(msg);
}

function positionInfo(Key){
// 	alert("Key : "+Key);
	document.searchForm.kPositionKey.value = Key;
	document.searchForm.action="/mes/user/kw_position_vf.do";
	document.searchForm.submit();
}

function changePosition(Key){
// 	alert("Key : "+Key);
	document.searchForm.kPositionKey.value = Key;
	document.searchForm.action="/mes/user/kw_position_uf.do";
	document.searchForm.submit();
}

function deletePosition(Key, cnt){
	if(cnt <1 ){
		if(delete_list2() == true){
			document.searchForm.kPositionKey.value = Key;
			document.searchForm.action="/mes/user/kw_position_d.do";
			document.searchForm.submit();
		}
	}else{
		alert("사용중인 부서입니다. 삭제할수 없습니다.");
	}	
} 
</script>

<style>
td.gridjs-td:last-child{text-align:center !important;} 
</style>
</head>



<body>
	<form name="searchForm" id="searchForm" method="post" >
		<input name="kPositionKey" id="kPositionKey" type="hidden" />
	
		<div class="content_up">
			<div class="content_tit">
				<h2>부서 관리</h2>
			</div>
		</div>	

			
		<div class="lf_tbl_list">
			<table id="myTable" >
				<colgroup>
						<col width="80%" />
						<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<!-- <th>순 위</th> -->
						<th>부서 명</th>
						<th>관 리</th>
					</tr>
				</thead>
				<TBODY>
					<c:forEach var="item" items="${positionList}">
						<tr>
							<td><c:out value="${item.kPositionName}" /></td>
							
							<td>
								<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
									<a class="list_btn" onclick="positionInfo(<c:out value='${item.kPositionKey}'/>)" >수정</a>
								</c:if>
								<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
									<a class="list_btn" onclick="deletePosition(<c:out value='${item.kPositionKey}'/>,<c:out value='${item.useCnt}'/>)"  >삭제</a>
								</c:if>
							</td>
	
						</tr>
					</c:forEach>
				</TBODY>
			</table>
		</div>	
		<div class="tbl_bottom"> 
			<ul class="tbl_bottom_right" > 
				<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
					<li><a href="/mes/user/kw_position_if.do">부서추가</a></li>
				</c:if>
			</ul>
		</div>	
	</form>
</body>
</html>
