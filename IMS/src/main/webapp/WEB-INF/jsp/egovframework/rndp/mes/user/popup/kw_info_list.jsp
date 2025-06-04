<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

$(function(){
})


function selectAdd(num){  
	 
		var obj = {
				ePosition :	$("#staffPosition_"+num).val(),
				eName :	$("#staffName_"+num).val()
		}
		if(typeof(opener.setReturnTextPop) != "undefined"){
		window.opener.setReturnTextPop(obj);
		window.close();
		}
	 
}

//검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/user/kw_user_list.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/user/kw_user_list.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesUserVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesUserVO.searchType}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>작업자 선택</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>작업자명:		
	            	<input type="text" class="input_search" id="searchWord" name="searchWord" value="${mesUserVO.searchWord}" />
	           	</li>
	           	<li>	
	           		<a style="cursor: pointer;" onclick="fn_guestList(1)">검색</a>        	     			
				</li>	
			</ul> 
		</div>
		
		
		<div class="lf_tbl_list" id="pop_result_list">
			<table>
		        <thead>
					<tr>
			           	<th>부서명</th>
			           	<th>직급</th>
			           	<th>담당자명</th>
					</tr>
		        </thead>
		        <tbody>
					<c:forEach var="list" items="${infoList}" varStatus="i">
	         			<tr style="cursor: pointer;" onclick="selectAdd('${i.index}')">
	           				 
	           				<td>
	           					<c:out value="${list.kPositionName}" />	
           						<input type="hidden" id="staffNum_${i.index}" value="${list.mesUserKey}" />
	           					<input type="hidden" id="staffClass_${i.index}" value="${list.kClassName}" />
	           					<input type="hidden" id="staffPosition_${i.index}" value="${list.kPositionName}" />
	           					<input type="hidden" id="staffName_${i.index}" value="${list.mesUserName}" />
	           				</td>
	           				<td>
	           					<c:out value="${list.kClassName}" />
	           				</td>
	           				<td>
	           					<c:out value="${list.mesUserName}"/>
	           				</td>
	         			</tr>
	         		</c:forEach>
					<c:if test="${empty infoList}">
						<tr>
							<td colspan="20">조회 정보가 없습니다.</td>
						</tr>
					</c:if>
		        </tbody>
			</table>
		</div>		
		
		<div class="page">	
			<span>
		  		<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
			</span>
		</div>	
	</div>
</form>

