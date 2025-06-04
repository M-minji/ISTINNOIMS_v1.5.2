<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	datepickerSet('topStartDate', 'topEndDate');
	$('#mloader').show();
}); 

function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}


function go_back(){
	document.listForm.action = "/mes/sulbi/kw_sulbi_lf.do";
	document.listForm.submit();
}

function go_info(eSulbiReKey){

	$('#mloader').show();
	$('#eSulbiReKey').val(eSulbiReKey);
	document.listForm.action = "/mes/kw_sulbi_repair_uf.do";
	document.listForm.submit();
}



function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/kw_sulbi_correction_lf.do";
	document.listForm.submit();
}

function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

</script>


<form name="listForm" id="listForm" method="post">		
	<input  type="hidden" id="pageIndex" name="pageIndex" value="${mesSulbiVO.pageIndex}"/>
	<input  type="hidden" id="eSulbiKey" name="eSulbiKey" />
	<input  type="hidden" id="eSulbiName" name="eSulbiName" />
	<input  type="hidden" id="eSulbiReKey" name="eSulbiReKey" />
	<input type="hidden" name="key" id="key" value="<c:out value='${key}'/>"/>

		<div class="content_up">
			<div class="content_tit">
				<h2>설비 수리 현황</h2>
			</div>		
		
			<div class="tbl_top"> 
				<ul class="tbl_top_left">
					<li>
						<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
		              		<option value="20" <c:if test="${mesSulbiVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
		              		<option value="50" <c:if test="${mesSulbiVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
		              		<option value="100" <c:if test="${mesSulbiVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
		       			</select> 
	       			</li>
					<li>
						<select name="searchType" class="select_search" id="searchType" >
							<option value="1" <c:if test="${mesSulbiVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
							<option value="2" <c:if test="${mesSulbiVO.searchType eq 2}">selected="selected"</c:if>>장비명</option>
							
						</select> 
					</li>
					<li>
						<input type="text"  name="searchWord" style="width:150px;"  id="searchWord"  value="<c:out value='${mesSulbiVO.searchWord}'/>" onKeydown="fn_keyDown(event)" /> 
					</li>
					<li>
						<span>수리일</span>
						<input type="text" name="topStartDate" style="width:100px;" readonly="readonly" id="topStartDate" value="${mesSulbiVO.topStartDate }" />
		           	~<input type="text" name="topEndDate" style="width:100px;" readonly="readonly" id="topEndDate"  value="${mesSulbiVO.topEndDate }" />
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
		  			</li>
				</ul> 	
				
			</div>
		</div> 	
			
			<div class="lf_tbl_list">
				<table>
					<colgroup>
						<col width="5%"/>
						<col width="*"/>
						<col width="*"/>
						<col width="*"/>
						<col width="*"/>
						<col width="*"/>
					</colgroup>
					<thead>
						<tr>
							<th>순번</th>				
							<th>장비명</th>
							<th>수리일</th>
							<th>수리 내용</th>
							<th>수리 금액</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${list}" varStatus="i">
							<tr style="cursor: pointer;" onclick="go_info(<c:out value='${list.eSulbiReKey}'/>);">
								<td>${paginationInfo.totalRecordCount - (mesSulbiVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}</td>
								<td>${list.eSulbiName}</td>
								<td>${list.eSulbiReDate}</td>
								<td>${list.eSulbiReContent}</td>
								<td><fmt:formatNumber value="${list.eSulbiRePrice}" pattern="#,###" /></td>
								<td>${list.eSulbiReEtc}</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${empty list}">
						<tr>
							<td colspan="6">수리내역이 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>
					
	
		<div class="tbl_paging">
			<span><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></span>
		</div>
	
</form>

