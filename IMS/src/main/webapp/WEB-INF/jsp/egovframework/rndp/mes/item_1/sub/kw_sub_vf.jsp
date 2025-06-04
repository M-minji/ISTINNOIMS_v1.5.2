<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

// 저장
function go_update(){
	$("#mloader").show();
	document.frm.action = "/mes/item/sub/kw_sub_uf.do";
	document.frm.submit();
}


// 삭제
function delete_go(){
	if("${itemUseCnt.useCnt}" < "1"){
		if(confirm("삭제하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/item/sub/kw_sub_d.do";
			document.frm.submit();
		}
	}else{
		alert("사용되고 있는 품목입니다. 삭제할 수 없습니다.");
	}
}


// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/item/sub/kw_sub_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/item/sub/kw_sub_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSubVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesSubVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesSubVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesSubVO.searchWord}" />
	
	<input type="hidden" id="sItemKey" name="sItemKey" value="${subInfo.sItemKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>자재관리 상세</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
		<table>
	  		<tbody>
	  			<tr>
		    		<th>대분류</th>
					<td colspan="3">
	    				 <c:if test="${subInfo.mainCate eq 'P'}">P:Plastic Magne</c:if>
	    				 <c:if test="${subInfo.mainCate eq 'N'}">N:Nd Magnet</c:if>
	    				 <c:if test="${subInfo.mainCate eq 'R'}">R:Rubber Magnet</c:if>
	    				 <c:if test="${subInfo.mainCate eq 'O'}">O:기타 아이템</c:if>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>자재분류</th>
		    		<td>
		    			<c:out value="${subInfo.sItemCatePath}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>특수코드</th>
					<td colspan="3">
		    			<c:if test="${subInfo.specialCode eq 'A'}">A:사급  </c:if>
	    				<c:if test="${subInfo.specialCode eq 'B'}">B:도급  </c:if>
	    				<c:if test="${subInfo.specialCode eq 'C'}">C:해당없음</c:if>
		    		</td>
		  		</tr>	
		  		<tr>
		    		<th>자재코드</th>
		    		<td>
		    			<c:out value="${subInfo.sItemCode}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>자재명</th>
		    		<td>
		    			<c:out value="${subInfo.sItemName}" />
		    		</td>
		  		</tr>	  		
		  		<tr>
		    		<th>규격</th>
		    		<td>
		    			<c:out value="${subInfo.sItemStandard}" />
		    		</td>
		  		</tr>
				<tr>
					<th>단가</th>
					<td>
						<fmt:formatNumber value="${subInfo.sItemPrice}" pattern="#,###.0000" /> <c:out value="${subInfo.sItemMoneyUnitTxt}" />
					</td>
				</tr>
		  		<tr>
		    		<th>자재단위</th>
		    		<td>
		    			<c:out value="${subInfo.sItemUnitTxt}" />
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>적정재고</th>
		    		<td>
		    			<c:out value="${subInfo.sItemInven}" />
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>비고</th>
		    		<td>
		    			<c:out value="${subInfo.sItemEtc}" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>업체명</th>
		    		<td>
		    			<c:out value="${subInfo.sItemCompanyName}" />
		    		</td>
		  		</tr>
		  		<c:if test="${itemUseCnt.useCnt  ne '0' }">
		  		<tr>
		    		<th>사용여부</th>
		    		<td colspan="3">
		    			<font color="red">[삭제 불가]</font>
		    			 <c:if test="${itemUseCnt.useBomiCnt  ne '0' }">[BOM관리] </c:if>
		    			 <c:if test="${itemUseCnt.useMiCnt  ne '0' }">[자재발주관리] </c:if>
		    			 <c:if test="${itemUseCnt.useMimiCnt  ne '0' }">[자재입고관리] </c:if>
		    			 <c:if test="${itemUseCnt.useOrdpmiCnt  ne '0' }">[생산관리] </c:if>
		    			  <c:if test="${itemUseCnt.useBmiiCnt  ne '0' }">[기초재고관리] </c:if>
		    			 <c:if test="${itemUseCnt.useInvenCnt  ne '0' }">[재고] </c:if> 사용중 
		    		</td>
		  		</tr>
		  		</c:if>
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
