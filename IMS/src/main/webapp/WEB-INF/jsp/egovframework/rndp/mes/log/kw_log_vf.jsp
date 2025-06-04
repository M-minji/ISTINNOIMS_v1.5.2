<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<script type="text/javascript">
$(document).ready(function(){  
	datepickerSet('topStartDate', 'topEndDate');
});

// 검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.submit();
}

//ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

function setStartDate(d) {
	var settingDate = new Date();
	if(d == "7"){
		settingDate.setDate(settingDate.getDate() -7);
		$("#topStartDate").val(moment(settingDate).format("YYYY-MM-DD"));
	}else if(d == "1"){
		settingDate.setMonth(settingDate.getMonth() -1);
		$("#topStartDate").val(moment(settingDate).format("YYYY-MM-DD"));
	}else{
		settingDate.setMonth(settingDate.getMonth() -3);
		$("#topStartDate").val(moment(settingDate).format("YYYY-MM-DD"));
	}
	fn_guestList(1);
}


//엑셀다운
function excelDown(){
	document.frm.action = "/mes/Log/excelDownload.do";
	document.frm.submit();
	document.frm.action = "/mes/log/kw_log_vf.do";
}
</script>

<form id="frm" name="frm" method="post" action="/mes/log/kw_log_vf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${logVO.pageIndex}" />
	
	<input type="hidden" id="eLogNum" name="eLogNum" value="" />

	<div class="content_up">
		<div class="content_tit">	
			<h2>로그 현황</h2>
		</div>
		<div class="tbl_top"> 
			<ul class="tbl_top_left" >
				<li>
 	       			<select name="searchType" class="select_search" id="searchType" >
	              		<option value="1" <c:if test="${logVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
	              		<option value="2" <c:if test="${logVO.searchType eq 2}">selected="selected"</c:if>>주소</option>
	              		<option value="3" <c:if test="${logVO.searchType eq 3}">selected="selected"</c:if>>페이지 이름</option>
	              		<option value="4" <c:if test="${logVO.searchType eq 4}">selected="selected"</c:if>>접속자</option>
	           		</select>
	          	</li>
				<li>
	           		<input name="searchWord" type="text" class="searchWord" id="searchWord" value="${logVO.searchWord}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}"/>
	           	</li>
				<li>		
	           		<a style="cursor: pointer;" onclick="fn_guestList(1)">
	           			검색
	           		</a>
	           	</li>

				<li>
	           		<span>시작시간</span> 
		       		<input type="text" id="topStartDate" name="topStartDate" style="width:100px;" value="${logVO.topStartDate}" readonly />
		           	- <input type="text" id="topEndDate" name="topEndDate" style="padding-left:2px; width:100px;" value="${logVO.topEndDate}" readonly />
				</li>
				<li>	
					<a style="cursor: pointer;" onclick="setStartDate(7);">
					   1주일
			     	</a>
			    </li>
				<li>	
					<a style="cursor: pointer;" onclick="setStartDate(1);">
					    1개월
			     	</a>
			    </li>
				<li>	
					<a style="cursor: pointer;" onclick="setStartDate(3);">
					    3개월
			     	</a>
			    </li>
			</ul>
		</div>
	</div>
	     
   	<div class="lf_tbl_list">
       	<table style="width:100%;" id="myTable">
      		<thead>
	       		<tr>
	           		<th style="width:5%;">순번</th>
	           		<th style="width:5%;">접속자</th>
	           		<th style="width:10%;">시작시간</th>
	           		<th style="width:10%;">종료시간</th>
	           		<th style="width:25%;">페이지주소</th>
	           		<th style="width:5%;">페이지 구분</th>
<!-- 	           		<th style="width:10%;">구분</th> -->
<!-- 	           		<th style="width:10%;">페이지 이름</th> -->
	           		<th style="width:5%;">접속 시간(M)</th>
	           		<th style="width:5%;">IP</th>
<!-- 	           		<th style="width:5%;">연동 시간</th> -->
<!-- 	           		<th style="width:5%;">연동 현황</th> -->
	       		</tr>
	       	</thead>
	       	<tbody> 
	       		<c:forEach var="LogList" items="${LogList}" varStatus="i">
					<tr>
	       				<td>
	       					${paginationInfo.totalRecordCount - (logVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
	       				</td>
	       				<td>
	       					<c:out value="${LogList.staffName}" />
	       				</td>
	       				<td>
	       					<c:out value="${LogList.startDate}" />
	       				</td>
	       				<td>
	       					<c:out value="${LogList.endDate}" />
	       				</td>
	       				<td>
	       					<c:out value="${LogList.url}" />
	       				</td>
	       				<td>
	       					<c:out value="${LogList.pageGubun}" />
	       				</td>
<!-- 	       				<td> -->
<%-- 	       					<c:out value="${LogList.gubun}" /> --%>
<!-- 	       				</td> -->
<!-- 	       				<td> -->
<%-- 	       					<c:out value="${LogList.page}" /> --%>
<!-- 	       				</td> -->
	       				<td>
	       					<c:out value="${LogList.minute}" />
	       				</td>
	       				<td>
	       					<c:out value="${LogList.logIp}" />
	       				</td>
<!-- 	       				<td> -->
<%-- 	       					<c:out value="${LogList.recptnDt}" /> --%>
<!-- 	       				</td> -->
<!-- 	       				<td> -->
<%-- 	       					<c:out value="${LogList.recptnRslt}" /> --%>
<!-- 	       				</td> -->
	       			</tr>
	       		</c:forEach>
	       		<c:if test="${empty LogList}">
	       			<tr>
	       				<td colspan="12">조회 정보가 없습니다.</td>
	       			</tr>
	       		</c:if>
	       	</tbody>
		</table>
  	<div class="tbl_paging">	
		<span><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></span>
	</div>		
	</div>
	

	
		<div class="tbl_bottom"> 
			<ul class="tbl_bottom_left" >
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
	          			<option value="10" <c:if test="${logVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${logVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${logVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${logVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select> 
			    </li>
	       	</ul>

			<ul class="tbl_bottom_right" >
				<li>
					<a style="cursor: pointer;" onclick="excelDown();">
					    엑셀
			     	</a>
		     	</li>
			</ul>
		</div>
</form>