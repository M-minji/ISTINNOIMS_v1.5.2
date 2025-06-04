<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">
// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/item/popup/kw_item_lf.do";
	document.frm.submit();
}

// 제품분류 선택
function selectItem(idx){
	var eItemCateKey	= $("#eItemCateKey_"+idx).val();
	var eItemCateName	= $("#eItemCateName_"+idx).val();
	var eItemCatePath	= $("#eItemCatePath_"+idx).val();
	var logoCode	= $("#logoCode_"+idx).val();
	var middleCate	= $("#middleCate_"+idx).val();
	
	if(typeof(window.opener.setItemPop) != "undefined"){
		window.opener.setItemPop(eItemCateKey, eItemCateName, eItemCatePath ,middleCate, logoCode);
		window.close();
	}
}
</script>

<form id="frm" name="frm" method="post" action="/mes/item/popup/kw_item_lf.do">
	<input type="hidden" name="pageIndex" value="${mesItemVO.pageIndex}"/>
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>제품분류 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">	
		<div id="itemCateZone" class="tbl_top"> 
			<ul class="tbl_top_left">
				<li>
	          		<select id="recordCountPerPage" name="recordCountPerPage" class="select_recordCountPerPage" onchange="fn_guestList(1)">
	              		<option value="10" <c:if test="${mesItemVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesItemVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesItemVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesItemVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	      			</select>					
				</li>
				<li>
					<span>품목분류경로</span>
	        		<input type="text" class="input_search" id="searchWord" style="width:150px;" name="searchWord" value="${mesItemVO.searchWord}" maxlength="100" />
	        	</li>
				<li>
					<a onclick="fn_guestList(1)" style="cursor: pointer;" >
		     			검색
		     		</a>
				</li>
			</ul>
		</div>
		
		<div  class="lf_tbl_list" id="pop_result_list" >
			<table>
		        <thead>
					<tr>
		      			<th>순번</th>
		      			<th>품목분류경로</th>
		      			<th>품목분류명</th>
		      			<th>CODE</th>
		      			<th>LOGO CODE</th>
	      				<th>비고</th>
		    		</tr>
		        </thead>
		        <tbody>		    		
					<c:forEach var="itemList" items="${itemList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="selectItem('${i.index}');">
							<td>
								${paginationInfo.totalRecordCount - (mesItemVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
								
								<input type="hidden" id="eItemCateKey_${i.index}" name="eItemCateKey" value="${itemList.eItemCateKey}" />
								<input type="hidden" id="eItemCateName_${i.index}" name="eItemCateName" value="${itemList.eItemCateName}" />
								<input type="hidden" id="eItemCatePath_${i.index}" name="eItemCatePath" value="${itemList.eItemCatePath}" />
								<input type="hidden" id="middleCate_${i.index}" name="middleCate" value="${itemList.middleCate}" />
								<input type="hidden" id="logoCode_${i.index}" name="logoCode" value="${itemList.logoCode}" />
							</td>
							<td style="text-align: left;">
								<c:out value="${itemList.eItemCatePath}" />
							</td>
							<td>
								<c:out value="${itemList.eItemCateName}" />
							</td>
							<td>
								<c:out value="${itemList.middleCate}" />
							</td>
							<td>
								<c:out value="${itemList.logoCode}" />
							</td>
							<td>
								<c:out value="${itemList.eItemCateEtc}" />
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty itemList}">
						<tr>
							<td colspan="4">조회 정보가 없습니다.</td>
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
