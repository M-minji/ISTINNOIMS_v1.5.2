<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">

// 저장
function go_update(){
	$("#mloader").show();
	document.frm.action = "/mes/item/item/kw_item_uf.do";
	document.frm.submit();
}


// 삭제
function delete_go(){
	if("${itemUseCnt.useCnt}" < "1"){
		if(confirm("삭제하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/item/item/kw_item_d.do";
			document.frm.submit();
		}
	}else{
		alert("사용되고 있는 품목입니다. 삭제할 수 없습니다.");
	}
}


// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/item/item/kw_item_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/item/item/kw_item_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesItemVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesItemVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesItemVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesItemVO.searchWord}" />
	
	<input type="hidden" id="eItemKey" name="eItemKey" value="${itemInfo.eItemKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>제품관리 상세</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
		<table>
	  		<tbody>
	  			<tr>
		    		<th style="width: 5%">대분류</th>
					<td colspan="3">
	    				 <c:if test="${itemInfo.mainCate eq 'P'}">P:Plastic Magne</c:if>
	    				 <c:if test="${itemInfo.mainCate eq 'N'}">N:Nd Magnet</c:if>
	    				 <c:if test="${itemInfo.mainCate eq 'R'}">R:Rubber Magnet</c:if>
	    				 <c:if test="${itemInfo.mainCate eq 'O'}">O:기타 아이템</c:if>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>제품분류</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemCatePath}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>특수코드</th>
					<td colspan="3">
		    			<c:if test="${itemInfo.specialCode eq 'A'}">A:사급  </c:if>
	    				<c:if test="${itemInfo.specialCode eq 'B'}">B:도급  </c:if>
	    				<c:if test="${itemInfo.specialCode eq 'C'}">C:해당없음</c:if>
		    		</td>
		  		</tr>		  					
		  		<tr>
		    		<th>제품코드</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemCode}" />
		    		</td>
		  		</tr>	
		  		<tr>
		    		<th>모델명</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemName}" />
		    		</td>
		  		</tr>  		
		  		<tr>
		    		<th>규격</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemStandard}" />
		    		</td>
		  		</tr>
				<tr>
					<th>기본 단가</th>
					<td>
						<fmt:formatNumber value="${itemInfo.eItemPrice}" pattern="#,###.0000" /> <c:out value="${itemInfo.eItemMoneyUnitTxt}" />
					</td>
					<%-- <th>원가</th>
					<td>
						<fmt:formatNumber value="${itemInfo.eItemCost}" pattern="#,###" /> <c:out value="${itemList.eItemMoneyUnitTxt}" />
					</td> --%>
				</tr>
		  		<tr>
		    		<th>제품단위</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemUnitTxt}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>적정재고</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemInven}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>비고</th>
					<td colspan="3">
		    			
		    		</td>
		  		</tr>
				<%-- <tr>
					<th>외주 단가</th>
					<td colspan="3"></td>
				</tr>
				<tr>
		    		<th>업체명</th>
					<td colspan="3">
		    			<c:out value="${itemInfo.eItemCompanyName}" />
		    		</td>
		  		</tr>
				<c:forEach items="${itemInfo.outPriceList}" var="outPriceList">
					<tr>
						<th></th>
						<td style="width: 8%">
							${outPriceList.eItemOutProcessName}
						</td>
						<td colspan="2">
							${outPriceList.eItemOutProcessPrice}  <c:out value="${itemList.eItemMoneyUnitTxt}" />
						</td>
					</tr>
				</c:forEach> --%>
				
				<c:if test="${itemUseCnt.useCnt  ne '0' }">
		  		<tr>
		    		<th>사용여부</th>
		    		<td colspan="3">
		    			<font color="red">[삭제 불가]</font>
		    			 <c:if test="${itemUseCnt.useBlpCnt  ne '0' }">[도면관리] </c:if>
		    			 <c:if test="${itemUseCnt.useBomCnt  ne '0' }">[BOM관리] </c:if>
		    			 <c:if test="${itemUseCnt.useInvenCnt  ne '0' }">[재고] </c:if> 사용중 
		    		</td>
		  		</tr>
		  		</c:if>
			</tbody>
			<tbody>
				<tr>
		    		<th>포장사양서</th>
					<td colspan="3">
		    			<textarea rows="15" style="width: 99%; resize: none; border: none; outline: none;" readonly="readonly">${itemInfo.eItemPacking}</textarea>
		    		</td>
		  		</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="go_update();">수정</a>
	         	</li>
         	</c:if>
         	<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
         	<c:if test="${itemUseCnt.useCnt eq '0' }">
	         	<li>
					<a style="cursor: pointer;" onclick="delete_go();">삭제</a>
	         	</li>
	         </c:if>
         	</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>	
       		</li>
		</ul>
	</div>
	
</form>
