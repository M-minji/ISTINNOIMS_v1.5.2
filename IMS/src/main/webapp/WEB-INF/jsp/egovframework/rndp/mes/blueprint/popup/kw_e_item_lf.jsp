<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">

// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/blueprint/popup/kw_e_item_lf.do";
	document.frm.submit();
}

//제품 선택
function checkItem(idx){
	var obj = {
			eItemKey 			:	$("#eItemKey_"+idx).val(),
			eItemName			:	$("#eItemName_"+idx).val(),
			eItemCatePath 		:	$("#eItemCatePath_"+idx).val(),
			eItemCateName 		:	$("#eItemCateName_"+idx).val(),
			eItemCateKey 		:	$("#eItemCateKey_"+idx).val(),
			eItemCode 			:	$("#eItemCode_"+idx).val(),
			eItemStandard 		:	$("#eItemStandard_"+idx).val(),
			eItemUnitTxt 		:	$("#eItemUnitTxt_"+idx).val(),
			eItemUnit 			:	$("#eItemUnit_"+idx).val(),
			eItemGubun 			:	$("#eItemGubun_"+idx).val(),
	};
	if(typeof(opener.seteItemPop) != "undefined"){
		window.opener.seteItemPop(obj);
		window.close();
	}
}

//검색 ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}
</script>

<form id="frm" name="frm" method="post" action="/mes/blueprint/popup/kw_e_item_lf.do">
	<input type="hidden" name="pageIndex"  id="pageIndex" value="${mesBlueprintVO.pageIndex}">

		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>제품 조회</h3>
				</div>
				<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
			</div>
		</div> 

		<div class="popup_content">	
			<div id="itemCateZone" class="tbl_top"> 
				<ul class="tbl_top_left">
					<li>
		          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
		          			<option value="10" <c:if test="${mesBlueprintVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
		              		<option value="20" <c:if test="${mesBlueprintVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
		              		<option value="50" <c:if test="${mesBlueprintVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
		              		<option value="100" <c:if test="${mesBlueprintVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	       				</select> 
			   		</li>
					<li>
						<select name="searchType" class="select_search" id="searchType" >
		       				<option value="1" <c:if test="${mesBlueprintVO.searchType eq 1}">selected="selected"</c:if>>전체조회</option>
		       				<option value="2" <c:if test="${mesBlueprintVO.searchType eq 2}">selected="selected"</c:if>>모델명</option>
		       				<option value="3" <c:if test="${mesBlueprintVO.searchType eq 3}">selected="selected"</c:if>>제품코드</option>
		       				<option value="4" <c:if test="${mesBlueprintVO.searchType eq 4}">selected="selected"</c:if>>제품분류</option>
		     			</select>
					</li>
					<li>
						<input name="searchWord" type="text" class="searchWord" id="searchWord" value="${mesBlueprintVO.searchWord}" onKeydown="fn_keyDown()" />
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
					</li>
				</ul>
				<ul class="tbl_top_right">
					<li>
						<a onclick="selectItem();">선택</a>
					</li>
				</ul>
			</div>	
		
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr style="width:100%">
			      			<th style="width: 3%;">순번</th>
			      			<th style="width: 12%;">제품분류경로</th>
			      			<th style="width: 8%;">제품분류</th>
			      			<th style="width: 10%;">제품코드</th>
			      			<th style="width: 12%;">모델명</th>
			      			<th style="width: 10%;">규격</th>
			      			<th style="width: 6%;">단위</th>
						</tr>
			        </thead>
			        <tbody>
	 					<c:forEach var="eItemList" items="${eItemList}" varStatus="i">
							<tr style="cursor: pointer;" onclick="checkItem('${i.index}');">
								<td>
 									<input type="hidden" id="cnt_${i.index}" name="cnt" value="${i.index + 1}" />
									<input type="hidden" id="eItemKey_${i.index}" name="eItemKey" value="${eItemList.eItemKey}" />
									<input type="hidden" id="eItemName_${i.index}" name="eItemName" value="${eItemList.eItemName}" />
									<input type="hidden" id="eItemCatePath_${i.index}" name="eItemCatePath" value="${eItemList.eItemCatePath}" />
									<input type="hidden" id="eItemCateName_${i.index}" name="eItemCateName" value="${eItemList.eItemCateName}" />
									<input type="hidden" id="eItemCateKey_${i.index}" name="eItemCateKey" value="${eItemList.eItemCateKey}" />
									<input type="hidden" id="eItemCode_${i.index}" name="eItemCode" value="${eItemList.eItemCode}" />
									<input type="hidden" id="eItemStandard_${i.index}" name="eItemStandard" value="${eItemList.eItemStandard}" />
									<input type="hidden" id="eItemUnitTxt_${i.index}" name="eItemUnitTxt" value="${eItemList.eItemUnitTxt}" />
									<input type="hidden" id="eItemUnit_${i.index}" name="eItemUnit" value="${eItemList.eItemUnit}" />
									<input type="hidden" id="eItemGubun_${i.index}" name="eItemGubun" value="${eItemList.eItemGubun}" />
									<c:out value="${i.index + 1 }" />
								</td>
					            <td>
					            	<c:out value="${eItemList.eItemCatePath}" />
					            </td>
					            <td>
					            	<c:out value="${eItemList.eItemCateName}" />
					            </td>
					            <td>
					            	<c:out value="${eItemList.eItemCode}" />
					            </td>
					             <td>
					            	<c:out value="${eItemList.eItemName}" />
					            </td>
					            <td>
					            	<c:out value="${eItemList.eItemStandard}" />
					            </td>
					            <td>
					            	<c:out value="${eItemList.eItemUnitTxt}" />
					            </td>
							</tr>
						</c:forEach>
						<c:if test="${empty eItemList}">
							<tr>
								<td colspan="7">조회 정보가 없습니다.</td>
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

