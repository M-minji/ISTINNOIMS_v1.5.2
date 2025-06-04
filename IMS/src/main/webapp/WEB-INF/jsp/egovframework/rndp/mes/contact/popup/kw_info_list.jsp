<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

$(function(){
})


function selectAdd(num){  
	var obj = {
			ePosition :	$("#eDepartmentName_"+num).val(),
			eName :	$("#eContactName_"+num).val()
	}
	 
	if(typeof(opener.setReturnTextPop) != "undefined"){
	window.opener.setReturnTextPop(obj);
	window.close();
	}
}
	 
// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/contact/kw_info_list.do";
	document.frm.submit();
}

 
</script>

<form id="frm" name="frm" method="post" action="/mes/contact/kw_info_list.do" class="popup_wrap">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesContactVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesContactVO.searchType}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>담당자 선택</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div class="content_top">
			<div id="itemCateZone" class="filter_wrap">
				<div class="search_filter">
					<ul>
						<li>
							<span>담당자명</span>		
			            	<input type="text" class="input_search" id="searchWord" name="searchWord" value="${mesContactVO.searchWord}" />
			           	</li>
					</ul>
				</div>
				<div class="button_wrap">
					<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
				</div> 
			</div>
		</div>		
		
		<div class="pop_con">
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr>
						 	<th>소속사명</th>
				           	<th>부서명</th>
				           	<th>담당자명</th>
							<th>연락처</th>
							<th>메일</th>
						</tr>
			        </thead>
			        <tbody>
						<c:forEach var="list" items="${infoList}" varStatus="i">
		         			<tr style="cursor: pointer;" onclick="selectAdd('${i.index}');">
		           				<td>
		           					<c:out value="${list.eAgencyName}" />
		           					<input type="hidden" id="eAgencyName_${i.index}" value="${list.eAgencyName}" />
		           					<input type="hidden" id="eDepartmentName_${i.index}" value="${list.eDepartmentName}" />
		           					<input type="hidden" id="eContactName_${i.index}" value="${list.eContactName}" />
		           					<input type="hidden" id="ePhoneNumber_${i.index}" value="${list.ePhoneNumber}" />
		           				</td>
		           				<td>
		           					<c:out value="${list.eDepartmentName}" />	
		           				</td>
		           				<td>
		           					<c:out value="${list.eContactName}"/>
		           				</td>
		           				<td>
		           					<c:out value="${list.ePhoneNumber}" />	
		           				</td>
		           				<td>
		           					<c:out value="${list.eEmail}"/>
		           				</td>
		         			</tr>
		         		</c:forEach>
						<c:if test="${empty infoList}">
							<tr>
								<td colspan="20" class="tac">조회 정보가 없습니다.</td>
							</tr>
						</c:if>
			        </tbody>
				</table>
			</div>		
			<div class="page">	
				<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
			</div>	
		</div>
	</div>
</form>

