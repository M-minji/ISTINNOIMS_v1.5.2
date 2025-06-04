<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">

function checkItem(idx){
	var obj = {
			eEstimateKey 				:	$("#eEstimateKey_"+idx).val(),
			eEstimateNum 				:	$("#eEstimateNum_"+idx).val(),
			eEstimateName				:	$("#eEstimateName_"+idx).val(),
			eEstimateDate				:	$("#eEstimateDate_"+idx).val(),
			eEstimatePost				:	$("#eEstimatePost_"+idx).val(),
			eEstimateAddress			:	$("#eEstimateAddress_"+idx).val(),
			eEstimateAddressDetail		:	$("#eEstimateAddressDetail_"+idx).val(),
			eEstimateVatGubun			:	$("#eEstimateVatGubun_"+idx).val(),
			eEstimateTotMoney			:	$("#eEstimateTotMoney_"+idx).val(),
			eEstimateTotVat				:	$("#eEstimateTotVat_"+idx).val(),
			eEstimateTot				:	$("#eEstimateTot_"+idx).val(),
			sComKey						:	$("#sComKey_"+idx).val(),
			sComName					:	$("#sComName_"+idx).val(),
			sComBank					:	$("#sComBank_"+idx).val(),
			sComAccountName				:	$("#sComAccountName_"+idx).val(),
			sComAccountNum				:	$("#sComAccountNum_"+idx).val(),
			sComGubun					:	$("#sComGubun_"+idx).val(),
			sComGubunTxt				:	$("#sComGubunTxt_"+idx).val(),
	};
	
	if(typeof(opener.setEstimatePop) != "undefined"){
		window.opener.setEstimatePop(obj);
		window.close();
	}   
}
	
// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/suju/popup/kw_estimate_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/suju/popup/kw_estimate_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSujuVO.pageIndex}">

		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>견적서 조회</h3>
				</div>
				<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
			</div>
		</div> 

		<div class="popup_content">	
			<div id="itemCateZone" class="tbl_top"> 
				<ul class="tbl_top_left">
					<li>
		          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
		          			<option value="10" <c:if test="${mesSujuVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
		              		<option value="20" <c:if test="${mesSujuVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
		              		<option value="50" <c:if test="${mesSujuVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
		              		<option value="100" <c:if test="${mesSujuVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	       				</select> 
			   		</li>
					<li>
						<select name="searchType" class="select_search" id="searchType" >
		       				<option value="1" <c:if test="${mesSujuVO.searchType eq 1}">selected="selected"</c:if>>전체조회</option>
		       				<option value="2" <c:if test="${mesSujuVO.searchType eq 2}">selected="selected"</c:if>>견적서명</option>
		       				<option value="3" <c:if test="${mesSujuVO.searchType eq 3}">selected="selected"</c:if>>거래처</option>
		       				<option value="4" <c:if test="${mesSujuVO.searchType eq 4}">selected="selected"</c:if>>작성자</option>
		     			</select>
					</li>
					<li>
						<input name="searchWord" type="text" class="input_search" id="searchWord" value="${mesSujuVO.searchWord}" />
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
					</li>
				</ul>
			</div>	
		
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr style="width:100%">
							<th style="width: 5%;">순번</th>
						    <th style="width: 8%;">견적번호</th>
						    <th style="width: 10%;">견적일자</th>
						    <th style="width: 10%;">견적서명</th>
						    <th style="width: 12%;">거래처명</th>
						    <th style="width: 12%;">모델명</th>
						    <th style="width: 8%;">작성자</th>
						</tr>
			        </thead>
			        <tbody>
	 					<c:forEach var="estimateList" items="${estimateList}" varStatus="i">
							<tr style="cursor: pointer;" onclick="checkItem('${i.index}');">
								<td>
									${paginationInfo.totalRecordCount - (mesSujuVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
									
									<input type="hidden" id="eEstimateKey_${i.index}" name="eEstimateKey" value="${estimateList.eEstimateKey}" />
									<input type="hidden" id="eEstimateNum_${i.index}" name="eEstimateNum" value="${estimateList.eEstimateNum}" />
									<input type="hidden" id="eEstimateName_${i.index}" name="eEstimateName" value="${estimateList.eEstimateName}" />
									<input type="hidden" id="eEstimateDate_${i.index}" name="eEstimateDate" value="${estimateList.eEstimateDate}" />
									<input type="hidden" id="eEstimatePost_${i.index}" name="eEstimatePost" value="${estimateList.eEstimatePost}" />
									<input type="hidden" id="eEstimateAddress_${i.index}" name="eEstimateAddress" value="${estimateList.eEstimateAddress}" />
									<input type="hidden" id="eEstimateAddressDetail_${i.index}" name="eEstimateAddressDetail" value="${estimateList.eEstimateAddressDetail}" />
									<input type="hidden" id="eEstimateVatGubun_${i.index}" name="eEstimateVatGubun" value="${estimateList.eEstimateVatGubun}" />
									<input type="hidden" id="eEstimateTotMoney_${i.index}" name="eEstimateTotMoney" value="${estimateList.eEstimateTotMoney}" />
									<input type="hidden" id="eEstimateTotVat_${i.index}" name="eEstimateTotVat" value="${estimateList.eEstimateTotVat}" />
									<input type="hidden" id="eEstimateTot_${i.index}" name="eEstimateTot" value="${estimateList.eEstimateTot}" />
									<input type="hidden" id="sComKey_${i.index}" name="sComKey" value="${estimateList.sComKey}" />
									<input type="hidden" id="sComName_${i.index}" name="sComName" value="${estimateList.sComName}" />
									<input type="hidden" id="sComBank_${i.index}" name="sComBank" value="${estimateList.sComBank}" />
									<input type="hidden" id="sComAccountName_${i.index}" name="sComAccountName" value="${estimateList.sComAccountName}" />
									<input type="hidden" id="sComAccountNum_${i.index}" name="sComAccountNum" value="${estimateList.sComAccountNum}" />
									<input type="hidden" id="sComGubun_${i.index}" name="sComGubun" value="${estimateList.sComGubun}" />
									<input type="hidden" id="sComGubunTxt_${i.index}" name="sComGubunTxt" value="${estimateList.sComGubunTxt}" />
								</td>
					            <td>
					            	<c:out value="${estimateList.eEstimateNum}" />
					            </td>
					            <td>
					            	<c:out value="${estimateList.eEstimateDate}" />
					            </td>
					            <td>
					            	<c:out value="${estimateList.eEstimateName}" />
					            </td>
					             <td>
					            	<c:out value="${estimateList.sComName}" />
					            </td>
					            <td>
					            	<c:out value="${estimateList.eItemName}" />
					            	<c:if test="${estimateList.gbnCnt > 1}">
										외...${estimateList.gbnCnt - 1}개	
									</c:if>
					            </td>
					            <td>
					            	<c:out value="${estimateList.kStaffName}" />
					            </td>
							</tr>
						</c:forEach>
						<c:if test="${empty estimateList}">
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

