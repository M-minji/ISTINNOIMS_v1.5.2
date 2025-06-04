<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

$(function(){
	datepickerIdSet("searchTypeSet5");
})


function selectValue(idx) {

	var obj = {
		aAssetKey 			:  $("#aAssetKey_"+idx).val(),
		aAssetNumber 		:  $("#aAssetNumber_"+idx).val(),
		aAssetStatus 		:  $("#aAssetStatus_"+idx).val(),
		aAssetName 			:  $("#aAssetName_"+idx).val(),
		aAssetMaker 		:  $("#aAssetMaker_"+idx).val(),
		aAssetModel			:  $("#aAssetModel_"+idx).val(),
		aAssetManufactureNumber	:  $("#aAssetManufactureNumber_"+idx).val(),
		aAssetIntroducer 	:  $("#aAssetIntroducer_"+idx).val(),
		aAssetDate 			:  $("#aAssetDate_"+idx).val(),
		aAssetCost 			:  $("#aAssetCost_"+idx).val(),
		aAssetType 			:  $("#aAssetType_"+idx).val(),
		eNetworkType 			:  $("#eNetworkType_"+idx).val(),
		eHostName 		:  $("#eHostName_"+idx).val(),
		eIp 		:  $("#eIp_"+idx).val(),
		eOs 		:  $("#eOs_"+idx).val()
		
								 
	};
	
	
	if(typeof(opener.setAssetPop) != "undefined"){
		window.opener.setAssetPop(obj);
		window.close();
	}
}

// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/asset/kw_asset_License_lf.do";
	document.frm.submit();
}


function fn_search_detail(){
	
	if($("#search_detail").is(":visible")){
		$('#search_detail').attr("style", "display : none;");
	}else{
		$('#search_detail').attr("style", "display : block;");
	}	
}

</script>

<form id="frm" name="frm" method="post" action="/mes/asset/kw_asset_License_lf.do" class="popup_wrap">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesAssetVO.searchType}">
	<input type="hidden" id="eUsedLicenseQuantity" name="eUsedLicenseQuantity" value="${mesAssetVO.eUsedLicenseQuantity}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>대상장비 조회 <i class="badge"><c:if test="${not empty mesAssetVO.eUsedLicenseQuantity}">총 등록된 수량 ${mesAssetVO.eUsedLicenseQuantity}건</c:if></i></h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
	<%-- 	<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1);">
	              		<option value="20" <c:if test="${mesAssetVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesAssetVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesAssetVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select>					
				</li>
				<li>
					<span>자산번호</span>
					<input type="text" id="searchTypeSet1" name="searchTypeSet1" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet1}" />
				</li>
				<li>
					<span>설치위치</span>
					<input type="text" id="searchTypeSet2" name="searchTypeSet2" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet2}" />
				</li>
				<li>
					<span>자산명</span>
					<input type="text" id="searchTypeSet3" name="searchTypeSet3" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet3}" />
				</li>
				<li>
					<span>제조사</span>
					<input type="text" id="searchTypeSet4" name="searchTypeSet4" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet4}" />
				</li>
				<li>
					<span>도입일</span>
					<input type="text" id="searchTypeSet5" name="searchTypeSet5" style="width:100px;" class="inp_color" value="${mesAssetVO.searchTypeSet5}" readonly="readonly"/>
				</li>
				<li>
					<a onclick="fn_guestList(1);" style="cursor: pointer;">
		    			검색
		     		</a>
				</li>
<!-- 				<li> -->
<!-- 					<a onclick="fn_search_detail();"  style="cursor: pointer;"> -->
<!-- 		    			상세검색 -->
<!-- 		     		</a> -->
<!-- 				</li> -->
			</ul>
			<ul id="search_detail" style="display: none;">
				<li>
					<span>모델명</span>
					<input type="text" id="searchTypeSet6" name="searchTypeSet6" style="width:100px;" value="${mesAssetVO.searchTypeSet6}" />
				</li>
				<li>
					<span>상태</span>
					<input type="text" id="searchTypeSet7" name="searchTypeSet7" style="width:100px;" value="${mesAssetVO.searchTypeSet7}" />
				</li>
			</ul>
		</div>  
--%>
		
		<div class="pop_con">
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr>
							<th style="width:2%;">No.</th>
							<th style="width:8%;">자산유형</th>
							<th style="width:8%;">자산번호</th>
							<th style="width:8%;">자산명</th>
							<th style="width:8%;">제조사</th>
							<th style="width:8%;">모델명</th>
							<th style="width:8%;">HOST NAME</th>
							<th style="width:8%;">IP</th>
							<th style="width:8%;">OS</th>
							<th style="width:8%;">설치 수량</th>
							<th style="width:8%;">도입일</th>
	<!-- 						<th style="width:8%;">도입원가</th> -->
	<!-- 						<th style="width:10%;">자산관리</th> -->
						</tr>
			        </thead>
			        <tbody>
						<c:forEach var="list" items="${assetList}" varStatus="i">
							<tr >
	<%-- 						style="cursor: pointer;" onclick="selectValue('${i.index}');" --%>
								<td>
									${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
									
									<input type="hidden" id="aAssetKey_${i.index}" name="aAssetKey" value="${list.aAssetKey}" />
									<input type="hidden" id="aAssetNumber_${i.index}" name="aAssetNumber" value="${list.aAssetNumber}" />
									<input type="hidden" id="aAssetStatus_${i.index}" name="aAssetStatus" value="${list.aAssetStatus}" />
									<input type="hidden" id="aAssetType_${i.index}" name="aAssetType" value="${list.aAssetType}" />
									<input type="hidden" id="aAssetName_${i.index}" name="aAssetName" value="${list.aAssetName}" />
									<input type="hidden" id="aAssetMaker_${i.index}" name="aAssetMaker" value="${list.aAssetMaker}" />
									<input type="hidden" id="aAssetModel_${i.index}" name="aAssetModel" value="${list.aAssetModel}" />
									<input type="hidden" id="aAssetManufactureNumber_${i.index}" name="aAssetManufactureNumber" value="${list.aAssetManufactureNumber}" />
									<input type="hidden" id="aAssetImage_${i.index}" name="aAssetImage" value="${list.aAssetImage}" />
									<input type="hidden" id="aAssetIntroducer_${i.index}" name="aAssetIntroducer" value="${list.aAssetIntroducer}" />
									<input type="hidden" id="aAssetDate_${i.index}" name="aAssetDate" value="${list.aAssetDate}" />
									<input type="hidden" id="aAssetCost_${i.index}" name="aAssetCost" value="${list.aAssetCost}" />	
																
									<input type="hidden" id="eNetworkType_${i.index}" name="eNetworkType" value="${list.eNetworkType}" />	
									<input type="hidden" id="aAssetType_${i.index}" name="aAssetType" value="${list.aAssetType}" />	
									<input type="hidden" id="eHostName_${i.index}" name="eHostName" value="${list.eHostName}" />								
									<input type="hidden" id="eIp_${i.index}" name="eIp" value="${list.eIp}" />								
									<input type="hidden" id="eOs_${i.index}" name="eOs" value="${list.eOs}" />								
								</td>
								<td>
									${list.aAssetType}
								</td>
								<td>
									${list.aAssetNumber}
								</td>
								<td>
									${list.aAssetName}
								</td>
								<td>
									${list.aAssetMaker}
								</td>
								<td>
									${list.aAssetModel}
								</td>
								<td>
									${list.eHostName}
								</td>
								<td>
									${list.eIp}
								</td>
								<td>
									${list.eOs}
								</td>
								<td>
									${list.eLicenseQuantity}
								</td>
								<td>
									${list.aAssetDate}
								</td>
	<!-- 							<td style="text-align:right; padding-right:5px;"> -->
	<%-- 								${list.aAssetCost} --%>
	<!-- 							</td> -->
							</tr>
						</c:forEach>
						<c:if test="${empty assetList}">
							<tr>
								<td colspan="20">조회 정보가 없습니다.</td>
							</tr>
						</c:if>
			        </tbody>
				</table>
			</div>		
			
			<div class="list_btm center">
				<div class="paging"><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></div>
			</div>
		</div>
	</div>
</form>

