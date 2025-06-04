<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> --%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>


<head>
<title>품목조회</title>


<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">


$(document).ready(function(){	
	
	$('#topStartDate, #topEndDate').datepicker(
			{
				changeMonth : true,
				dateFormat : "yy-mm-dd",
				dayNames : [ '일요일' ,'월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
				dayNamesMin : [  '일','월', '화', '수', '목', '금', '토' ],
				monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
			});
	
	$('#topStartDate, #topEndDate').val(nowDate());
	
}); 


function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}	

function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/bom/popup/kw_bom_pop_lf.do";
	document.frm.submit();
}

function selectbomitem(idx) {
	

	var obj = {
			eBomName : $("#eBomName_" + idx).val(),
			eItemName : $("#eItemName_" + idx).val(),
			eItemKey : $("#eItemKey_" + idx).val(),
			eItemPartNo : $("#eItemPartNo_" + idx).val(),
			eBomRev : $("#eBomRev_" + idx).val(),
			eItemCateKey : $("#eItemCateKey_" + idx).val(),
			eBomKey : $("#eBomKey_" + idx).val(),
			eBomOption : $("#eBomOption_" + idx).val()
	};
	
	if(typeof(opener.setBom) != "undefined"){
		window.opener.setBom(obj);
 		window.close();
	}
}

</script>




</head>

<body style="overflow-X:hidden">

<form id="frm" name="frm" method="post" action="/mes/bom/popup/kw_bom_pop_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBomVO.pageIndex}" />
<%-- 	<input type="hidden" id="eBomKey" name="eBomKey" value="${bomInfo.eBomKey}" /> --%>


			<div class="pop_head">
				<div id="pop_head">
					<div class="tit">
						<h3>BOM 현황</h3>
					</div>
					<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
				</div>
			</div>
				
			<div class="popup_content">	
				
				<div id="itemCateZone" class="tbl_top"> 
					<ul class="tbl_top_left">
						<li>
			          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
			              		<option value="10" <c:if test="${mesBomVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
			              		<option value="20" <c:if test="${mesBomVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
			              		<option value="50" <c:if test="${mesBomVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
			              		<option value="100" <c:if test="${mesBomVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
		       				</select>					
						</li>
						<li>
							<select name="searchType" class="select_search" id="searchType">
			       				<option value="1" <c:if test="${mesBomVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
			       				<option value="2" <c:if test="${mesBomVO.searchType eq 2}">selected="selected"</c:if>>품목명</option>
		 	       				<option value="3" <c:if test="${mesBomVO.searchType eq 3}">selected="selected"</c:if>>Part No.</option>
		 	       				<option value="4" <c:if test="${mesBomVO.searchType eq 4}">selected="selected"</c:if>>문서번호</option>
			     			</select>
						</li>
						<li> 
				     		<input name="searchWord" type="text" class="searchWord" id="searchWord" value="${mesBomVO.searchWord}" onKeydown="fn_keyDown()" />
				     	</li>
				     	<li>	
				     		<a onclick="fn_guestList(1)">
				     			검색
				     		</a>
				    	</li>
					</ul>
				</div>
			
				<div class="lf_tbl_list" id="pop_result_list">
					<table>
						<thead>
							<tr>
					      		<th style="width:5%">순번</th>
							    <th style="width:25%">품목명</th>
							    <th style="width:15%">Part No.</th>
					      		<th style="width:15%">문서번호</th>
							    <th style="width:10%">Revision</th>
							    <th style="width:15%">Option</th>  
					    	</tr>
						</thead>
					  	<tbody> 
							<c:forEach var="list" items="${bomPopList}" varStatus="i">
								<tr onclick="selectbomitem(<c:out value='${i.index }'/>);" >
									<td>
										${paginationInfo.totalRecordCount - (mesBomVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
										<input type="hidden" id="eBomName_${i.index }"				name="eBomName" 			value="${list.eBomName}" />
										<input type="hidden" id="eItemName_${i.index }"				name="eItemName" 			value="${list.eItemName}" />
										<input type="hidden" id="eItemKey_${i.index }"				name="eItemKey" 			value="${list.eItemKey}" />
										<input type="hidden" id="eItemPartNo_${i.index }"			name="eItemPartNo" 			value="${list.eItemPartNo}" />
										<input type="hidden" id="eBomRev_${i.index }"				name="eBomRev" 				value="${list.eBomRev}" />
										<input type="hidden" id="eItemCateKey_${i.index }"			name="eItemCateKey" 		value="${list.eItemCateKey}" />
										<input type="hidden" id="eBomOption_${i.index }"			name="eBomOption" 			value="${list.eBomOption}" />
										<input type="hidden" id="eBomKey_${i.index }"				name="eBomKey" 				value="${list.eBomKey}" />
									</td>
									<td><c:out value="${list.eItemName}"/></td>
									<td><c:out value="${list.eItemPartNo}"/></td>
									<td><c:out value="${list.eBomName}"/></td>
									<td><c:out value="${list.eBomRev}"/></td>
									<td><c:out value="${list.eBomOption}"/></td>
								</tr>
							</c:forEach>
				       		<c:if test="${empty bomPopList}">
								<tr>
									<td colspan="6">BOM 내역이 없습니다. </td>
								</tr>
							</c:if>
				       	</tbody>
					</table>
				</div>
			
			
			  	
				<div class="page">	
					<span><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_guestList" /></span>
				</div>
			</div>
		
	
</form>