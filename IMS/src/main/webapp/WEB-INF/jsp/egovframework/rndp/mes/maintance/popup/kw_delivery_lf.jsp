<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

function selectDelivery(idx) {
	
	var obj = {
		sComDeliveryKey 			:  $("#sComDeliveryKey_"+idx).val(),
		sComDeliveryPost 			:  $("#sComDeliveryPost_"+idx).val(),
		sComDeliveryAddress 		:  $("#sComDeliveryAddress_"+idx).val(),
		sComDeliveryAddressDetail 	:  $("#sComDeliveryAddressDetail_"+idx).val()
	};
	
	if(typeof(opener.setDeliveryPop) != "undefined"){
		window.opener.setDeliveryPop(obj);
		window.close();
	}
}

// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/suju/popup/kw_delivery_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/suju/popup/kw_delivery_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSujuVO.pageIndex}">
	
	<input type="hidden" id="sComKey" name="sComKey" value="${mesSujuVO.sComKey}" />
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>배송지 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1);">
	              		<option value="10" <c:if test="${mesSujuVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesSujuVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesSujuVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesSujuVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select>					
				</li>
				<li>
					<select name="searchType" class="select_search" id="searchType">
						<option value="1" <c:if test="${mesSujuVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
						<option value="2" <c:if test="${mesSujuVO.searchType eq 2}">selected="selected"</c:if>>우편번호</option>
						<option value="3" <c:if test="${mesSujuVO.searchType eq 3}">selected="selected"</c:if>>주소</option>
					</select>
				</li>
				<li>
					<input type="text" id="searchWord" name="searchWord" class="input_search" value="${mesSujuVO.searchWord}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}"/>
				</li>
				<li>
					<a onclick="fn_guestList(1);" style="cursor: pointer;">
		    			검색
		     		</a>
				</li>
			</ul>
		</div>
		
		<div class="lf_tbl_list" id="pop_result_list">
			<table>
		        <thead>
					<tr>
			            <th style="width: 5%;">순번</th>
			            <th style="width: 8%;">우편번호</th>
			            <th style="width: 30%;">주소</th>
			            <th style="width: 12%;">상세주소</th>
			            <th style="width: 15%;">비고</th>
					</tr>
		        </thead>
		        <tbody>
					<c:forEach var="deliveryList" items="${deliveryList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="selectDelivery('${i.index}');">
							<td>
								${paginationInfo.totalRecordCount - (mesSujuVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
								
								<input type="hidden" id="sComDeliveryKey_${i.index}" name="sComDeliveryKey" value="${deliveryList.sComDeliveryKey}" />
								<input type="hidden" id="sComDeliveryPost_${i.index}" name="sComDeliveryPost" value="${deliveryList.sComDeliveryPost}" />
								<input type="hidden" id="sComDeliveryAddress_${i.index}" name="sComDeliveryAddress" value="${deliveryList.sComDeliveryAddress}" />
								<input type="hidden" id="sComDeliveryAddressDetail_${i.index}" name="sComDeliveryAddressDetail" value="${deliveryList.sComDeliveryAddressDetail}" />
							</td>
							<td>
								<c:out value="${deliveryList.sComDeliveryPost}"/>
							</td>
				            <td>
								<c:out value="${deliveryList.sComDeliveryAddress}"/>
							</td>
							<td>
								<c:out value="${deliveryList.sComDeliveryAddressDetail}"/>
							</td>
							<td>
								<c:out value="${deliveryList.sComDeliveryEtc}"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty deliveryList}">
						<tr>
							<td colspan="5">내역이 없습니다.</td>
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

