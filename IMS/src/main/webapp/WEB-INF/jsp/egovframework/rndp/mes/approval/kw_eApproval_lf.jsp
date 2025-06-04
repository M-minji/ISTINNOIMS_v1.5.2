<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script src="/js/jquery.table2excel.js"></script>

<script type="text/javascript">
$(document).ready(function(){
		datepickerSet("eTopStartDate","eTopEndDate");
		  $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
		  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); 
	}); 
	
	 

	// 현재날짜
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}
	
	// 검색
	function fn_guestList(pageNo) {
		$("#mloader").show();
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "/mes/approval/kw_eApproval_lf.do";
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
	
	// 상세
	viewService.prototype.go_view = function(obj) {
		
		if(obj.childNodes[0].querySelector("input")){
			var index = obj.childNodes[0].querySelector("input").value;
			if($("#eMenuFlag_"+index).val() == "F"){
				alert("해당 메뉴의 읽기원한이 없습니다.\n관리자에게 문의 하십시오.");
				return
			}
			
			var eMenuName =$("#eMenuName_"+index).val();
			var eTableUrl =$("#eTableUrl_"+index).val();
			var eKeyName =$("#eKeyName_"+index).val();
			var eTableKey =$("#eTableKey_"+index).val();
			$("[name='" + eKeyName + "']").val(eTableKey);
			if (eKeyName == "eIssueKey1" || eKeyName == "eIssueKey2") {
		            $("[name='eIssueKey']").val(eTableKey);
		        }
			document.frm.action = eTableUrl;
			document.frm.submit();
		}
	}
 
	
</script>
<style>
	/* 단일일경우 :nth-child(n)*/
	/* 해당하는 숫자부터 연속으로 이어짐  */
	td.gridjs-td:nth-child(n+6):nth-child(-n+7) {text-align:center !important;} 
</style>

<form id="frm" name="frm" method="post" >
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesApprovalVO.pageIndex}" />
	<c:forEach var='list' items='${nameList}'>
		<input type="hidden"  name="${list.eKeyName}" value="" />
	</c:forEach>
	<div class="content_top">	
		<div class="content_tit">
			<h2>결재 현황</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>메뉴구분</span>
						<select id='eSearchTypeSet1' name='eSearchTypeSet1'  onchange="fn_guestList(1)" >
							<option value=''>전체메뉴</option>
							<c:forEach var='list' items='${nameList}'>
							<option value='${list.eTableName}' data-value2='${list.eMenuName}'   <c:if test="${mesApprovalVO.eSearchTypeSet1 eq list.eTableName  }">selected="selected"</c:if> >${list.eMenuName}</option>						
							</c:forEach>
						</select> 
					</li>
					<li>
						<span>결재유형</span>
						<select id='eSearchTypeSet2' name='eSearchTypeSet2'  onchange="fn_guestList(1)" >
							<option value=''>전체조회</option>
							<option value='결재' <c:if test="${mesApprovalVO.eSearchTypeSet2 eq  '결재'  }">selected="selected"</c:if> >결재</option>
							<option value='검토' <c:if test="${mesApprovalVO.eSearchTypeSet2 eq  '검토'  }">selected="selected"</c:if>>검토</option>
						</select> 
					</li>
					<li>
						 <span>요청일자</span>
						 <div class="date">
						 	<input type="text" name="eTopStartDate" id="eTopStartDate" value="${mesApprovalVO.eTopStartDate}"  readonly />
			           	- <input type="text" name="eTopEndDate" id="eTopEndDate" style="padding-left:2px;" value="${mesApprovalVO.eTopEndDate}"   readonly />
						 </div>
			     	</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>

	<div class="data_table">
		<table id="myTable" style="width:100%; max-height:500px; overflow:auto;">
			<thead>
				<tr>
					<th >No.</th>   
		      		<th >메뉴명</th>
		      		<th >결재유형</th>
		      		<th >요청일시</th>
		    	</tr>
			</thead>
	  		<tbody> 
				<c:forEach var="list" items="${infoList}" varStatus="i">
				<tr onclick="maintanceView('${list.eTableName}');">
					<td>
						${paginationInfo.totalRecordCount - (mesApprovalVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
  						<input type="hidden" value="${i.count}" />  
  						<input type="hidden" id="eMenuName_${i.count}" name="rowMenuName" value="${list.eMenuName}" />  
  						<input type="hidden" id="eTableUrl_${i.count}" name="rowTableUrl" value="${list.eTableUrl}" />  
  						<input type="hidden" id="eKeyName_${i.count}" name="rowKeyName" value="${list.eKeyName}" />  
  						<input type="hidden" id="eTableKey_${i.count}" name="rowTableKey" value="${list.eTableKey}" />  
  						<input type="hidden" id="eMenuFlag_${i.count}" name="roweMenuFlag" value="${list.eMenuFlag}" />  
					</td>
					<td style="text-align:center; padding-left:5px;">
						${list.eMenuName}
					</td>
					<td style="text-align:center; padding-left:5px;">
						${list.eGubun}
					</td>
					<td style="text-align:center; padding-left:5px;">
						${list.eDete}
					</td>
				</tr>
			</c:forEach>
	       	</tbody>
		</table>	
  	</div>
  	
  	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
	       		<option value="20" <c:if test="${mesIssueVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
	       		<option value="50" <c:if test="${mesIssueVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
	       		<option value="100" <c:if test="${mesIssueVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
			</select> 
		</div>
		<div class="paging"><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></div>
		<div class="btns">
			<!--  
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
			<button type="button" class="form_btn active"></button>
			</c:if> 
			  -->
		</div>
	</div>
</form>