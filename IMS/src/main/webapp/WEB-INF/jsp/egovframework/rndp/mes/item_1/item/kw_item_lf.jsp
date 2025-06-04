<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<script type="text/javascript">
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}
// 검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.submit();
}

// 등록
function go_insert(){
	$("#mloader").show();
	document.frm.action = "/mes/item/item/kw_item_if.do";
	document.frm.submit();
}

// 제품 상세
viewService.prototype.go_view = function(obj) {
	$("#mloader").show();
	$("#eItemKey").val(obj.childNodes[0].querySelector("input").value);
	document.frm.action = "/mes/item/item/kw_item_vf.do";
	document.frm.submit();
}

</script>
<style>
/* 단일일경우 :nth-child(n)*/
/* 해당하는 숫자부터 연속으로 이어짐  */
td.gridjs-td:nth-child(n+8) {text-align:right !important;}  
</style>

<form id="frm" name="frm" method="post" action="/mes/item/item/kw_item_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesItemVO.pageIndex}" />
	<input type="hidden" id="eItemKey" name="eItemKey" value="" />
	
	<div class="content_up">
		<div class="content_tit">
			<h2>제품관리</h2>
		</div>
	
		<div class="tbl_top">
			<ul class="tbl_top_left" >
				<li>
					<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
	              		<option value="10" <c:if test="${mesItemVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesItemVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesItemVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesItemVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	           		</select> 
				</li>
				<li>
	      			<select name="searchType" class="select_search" id="searchType">
	       				<option value="1" <c:if test="${mesItemVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
	       				<option value="2" <c:if test="${mesItemVO.searchType eq 2}">selected="selected"</c:if>>제품코드</option>
	       				<option value="3" <c:if test="${mesItemVO.searchType eq 3}">selected="selected"</c:if>>제품분류</option>
	       				<option value="4" <c:if test="${mesItemVO.searchType eq 4}">selected="selected"</c:if>>모델명</option>
	     			</select>
	     		</li>
				<li>	
	     			<input name="searchWord" type="text" style="width:150px;" class="searchWord" id="searchWord" value="${mesItemVO.searchWord}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}"/>
	     		</li>
	    		<li>[검색 구분자 "" 공백 한 칸 ] [모델명 규격]</li>
	    	</ul>
	    	<ul class="tbl_top_right">
	    		<li>	
	     			<a style="cursor: pointer;" onclick="fn_guestList(1)">
	     				검색
	     			</a>
	    		</li>					
	    		<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">	
		    		<li>
		    			<a style="cursor: pointer;" onclick="go_insert();" >
		    				등록
		    			</a>
		    		</li>
	    		</c:if>
			</ul>
	    </div>		
 	</div>   
    
    <div  class="lf_tbl_list" id="tbl_a_list">
  		<table id="myTable" style="width:100%">
	  		<thead>
				<tr>
	      			<th>순번</th>
	      			<th>제품분류</th>
	      			<th>모델명</th>
	      			<th>모델코드</th>
	      			<th>규격</th>
	      			<th>단위</th>
	      			<th>업체명</th>
	      			<th>단가</th>
	      			<th>단위</th>
	      			<th>적정재고</th>
	      			<!-- <th>원가 + 외주</th> -->
	    		</tr>
	  		</thead>
	  		<tbody> 
	  			<c:forEach var="itemList" items="${itemList}" varStatus="i">
	  				<tr style="cursor: pointer;" onclick="itemView('${itemList.eItemKey}');">
	  					<td>
	  						${paginationInfo.totalRecordCount - (mesItemVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
	  						<input type="hidden" value="${itemList.eItemKey}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemCatePath}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemName}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemCode}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemStandard}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemUnitTxt}" />
	  					</td>
	  					<td>
	  						<c:out value="${itemList.eItemCompanyName}" />
	  					</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${itemList.eItemPrice}" pattern="#,##0.0000" /> 
						</td>
	  					<td>
	  						<c:out value="${itemList.eItemMoneyUnitTxt}" />
	  					</td>
	  					
	  					<td style="text-align: right;">
	  						<c:out value="${itemList.eItemInven}" />
	  					</td>
						<%-- <td style="text-align: right;">
							<fmt:formatNumber value="${itemList.eItemCost}" pattern="#,###" /> <c:out value="${itemList.eItemMoneyUnitTxt}" />
						</td> --%>
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






