<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>


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
	document.frm.action = "/mes/suju/kw_suju_period_lf.do";
	document.frm.submit();
}

// 검색 ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

function setStartDate(d) {
    var settingDate = new Date(); 	
    if(d == '7'){
        settingDate.setDate(settingDate.getDate()-7);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else if(d == '1'){	
        settingDate.setMonth(settingDate.getMonth()-1);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else{	
        settingDate.setMonth(settingDate.getMonth()-3);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }
}

</script>

<form id="frm" name="frm" method="post" action="/mes/suju/kw_suju_period_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSujuVO.pageIndex}" />
	
	<div class="content_up">	
		<div class="content_tit">
			<h2>납기준수현황</h2>
		</div>
	
		<div class="tbl_top">
			<ul class="tbl_top_left" >
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
	              		<option value="20" <c:if test="${mesSujuVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesSujuVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesSujuVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select> 
	   			 </li>
	   			 <li>
					<select name="searchType" class="select_search" id="searchType">
	       				<option value="1" <c:if test="${mesSujuVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
	       				<option value="2" <c:if test="${mesSujuVO.searchType eq 2}">selected="selected"</c:if>>수주번호</option>
	       				<option value="3" <c:if test="${mesSujuVO.searchType eq 3}">selected="selected"</c:if>>거래처</option>
 	       				<option value="4" <c:if test="${mesSujuVO.searchType eq 4}">selected="selected"</c:if>>모델명</option>
	     			</select>
				</li>
				<li> 
		     		<input name="searchWord" type="text" class="searchWord" style="width:150px;"  id="searchWord" value="${mesSujuVO.searchWord}" onKeydown="fn_keyDown()" />
		     	</li>
		     	<li>
	           		<span>수주일자</span>
		       		<input type="text" name="topStartDate" id="topStartDate" value="${mesSujuVO.topStartDate}" style="width:100px;" readonly />
		           	~ <input type="text" name="topEndDate" id="topEndDate" value="${mesSujuVO.topEndDate}" style="width:100px;" readonly />
		     	</li>
		    	<li>	
					<a style="cursor: pointer;" onclick="setStartDate(7)">
					   1주일
			     	</a>
			    </li>
				<li>	
					<a style="cursor: pointer;" onclick="setStartDate(1)">
					    1개월
			     	</a>
			    </li>
				<li>	
					<a style="cursor: pointer;" onclick="setStartDate(3)">
					    3개월
			     	</a>
			    </li>
		    </ul>
		    <ul class="tbl_top_right">
		    	<li>	
		     		<a onclick="fn_guestList(1)">검색</a>
		    	</li>
		    </ul>
		</div>
	</div>

	<div class="lf_tbl_list">
		<table id="myTable" >
			<thead>
				<tr>
		      		<th style="width: 5%;">순번</th>
				    <th style="width: 8%;">수주번호</th>
				    <th style="width: 10%;">수주일자</th>
				    <th style="width: 10%;">출고요청일자</th>
				    <th style="width: 10%;">출고완료일자</th>
				    <th style="width: *">납품일(출고일 - 수주일)</th>
				    <th style="width: 10%;">거래처명</th>
				    <th style="width: 12%;">모델명</th>
				    <th style="width: 7%;">수주수량</th>
				    <th style="width: 7%;">출고수량</th>
				    <th style="width: 10%;">상태</th>
		    	</tr>
			</thead>
	  		<tbody> 
				<c:forEach var="periodList" items="${periodList}" varStatus="i">
					<tr>
						<td>
							${paginationInfo.totalRecordCount - (mesSujuVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
						</td>
						<td>
							<c:out value="${periodList.eSujuNum}" />
						</td>
						<td>
							<c:out value="${periodList.eSujuDate}" />
						</td>
						<td>
							<c:out value="${periodList.eSujuShipDate}" />
						</td>
						<td>
							<c:out value="${periodList.eSujuItemDate}" />
						</td>
						<td>
							<c:out value="${periodList.eSujuItemDelay}" />
						</td>
						<td>
							<c:out value="${periodList.sComName}" />
						</td>
						<td>
							<c:out value="${periodList.eItemName}" />
						</td>
						<td>
							<c:out value="${periodList.eSujuItemCount}" />
						</td>
						<td>
							<c:out value="${periodList.eMoveplaceItemCount}" />
						</td>
						<td>
							<c:if test="${periodList.eSujuItemConfirm eq 'Y'}">
								출고완료
							</c:if>
							<c:if test="${periodList.eSujuItemConfirm ne 'Y'}">
								출고대기
							</c:if>
						</td>
					</tr>
				</c:forEach>
	       	</tbody>
		</table>
  	</div>
  	
 	<div class="page">	
	  <span><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_guestList" /></span>
	</div>
</form>

	
