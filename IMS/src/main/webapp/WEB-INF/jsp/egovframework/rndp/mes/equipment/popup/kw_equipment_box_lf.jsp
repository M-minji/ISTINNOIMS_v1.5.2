<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

$(function(){
// 	datepickerIdSet("searchTypeSet5");
})


function selectItem(){  
	ePageInfoList();
	 var previousValues = document.getElementById("ePageInfo").value.split("_");
	   if(previousValues.length > 0){
			var obj = {
				eEquipmentItemKey :	$("#ePageInfo").val()
			}
			if(typeof(opener.setAssetReturnPop) != "undefined"){
			window.opener.setAssetReturnPop(obj);
			window.close();
			}
	   }
	
// 	var aAssetKeylen = document.getElementsByName("aAssetKey").length;
// 	if(aAssetKeylen > 0){
// 		for(var idx=0; idx < aAssetKeylen ; idx++){
// 			var aAssetKeyValue = document.getElementsByName("aAssetKey")[idx].value;
// 			var rowIndex = document.getElementsByName("rowIndex")[idx].value;
			
// 			if($("#check_"+aAssetKeyValue).is(":checked")){
// 				if(opnerValueChk($("#aAssetKey_"+rowIndex).val(), rowIndex) == true){
// 					var obj = {
// 							aAssetKey 			:  $("#aAssetKey_"+rowIndex).val(),
// 							aAssetNumber 		:  $("#aAssetNumber_"+rowIndex).val(),
// 							aAssetStatus 		:  $("#aAssetStatus_"+rowIndex).val(),
// 							aAssetName 			:  $("#aAssetName_"+rowIndex).val(),
// 							aAssetMaker 		:  $("#aAssetMaker_"+rowIndex).val(),
// 							aAssetModel			:  $("#aAssetModel_"+rowIndex).val(),
// 							aAssetManufactureNumber	:  $("#aAssetManufactureNumber_"+rowIndex).val(),
// 							aAssetIntroducer 	:  $("#aAssetIntroducer_"+rowIndex).val(),
// 							aAssetDate 			:  $("#aAssetDate_"+rowIndex).val(),
// 							aAssetCost 			:  $("#aAssetCost_"+rowIndex).val(),
// 							aAssetType 			:  $("#aAssetType_"+rowIndex).val(),
// 							eNetworkType 			:  $("#eNetworkType_"+rowIndex).val(),
// 							eHostName 		:  $("#eHostName_"+rowIndex).val(),
// 							eIp 		:  $("#eIp_"+rowIndex).val(),
// 							eOs 		:  $("#eOs_"+rowIndex).val()
// 						};
// 					if(typeof(opener.setAssetPop) != "undefined"){
// 						window.opener.setAssetPop(obj);
// 						window.close();
// 					}
// 				}
// 			} 
// 		}
// 	}

}
function opnerValueChk(rowKey, idx) {

	var eAssetKeyArr = opener.document.getElementsByName("eEquipmentItemKey").length;
	if(eAssetKeyArr > 0){
		for(var i=0; i < eAssetKeyArr ; i++){
			var eAssetKey = opener.document.getElementsByName("eEquipmentItemKey")[i].value;
			
				if(rowKey == aAssetKey){
					alert($("#cnt_" + (Number(idx)+1)).val() + "번째 항목은  선택된 항목입니다. ");										
					return false;
				}
		}
	}
	
	return true;

}	
var accumulatedValues  = [];
window.onload = function() {
	restoreCheckboxesFromPageInfo();
	
    // 이전 페이지에서 넘어온 ePageInfo의 값
    var previousValues = document.getElementById("ePageInfo").value.split("_");
    
    // 초기화된 accumulatedValues 배열에 이전 값들을 추가합니다.
    if (previousValues.length > 0 && previousValues[0] !== '') {
	    accumulatedValues = previousValues.filter(function(value) {
	        return value !== ''; // 빈 값은 제외합니다.
	    });
    } 
    
    
    
};

function ePageInfoList() {
    var aAssetKeylen = document.getElementsByName("eEquipmentItemKey").length;
    var ePageInfoInput = document.getElementById("ePageInfo");
    
    if (aAssetKeylen > 0) {
        var currentValues = ePageInfoInput.value.split("_"); // 현재 ePageInfo의 값 배열로 변환
        var newValues = []; // 새로운 값들을 임시 배열에 담습니다.
        
        for (var idx = 0; idx < aAssetKeylen; idx++) {
            var aAssetKeyValue = document.getElementsByName("eEquipmentItemKey")[idx].value;
            
            if (document.getElementById("check_" + aAssetKeyValue).checked) {
                // 선택된 aAssetKeyValue 값을 구분자 "_"로 분리하여 newValues에 추가
               if (newValues.indexOf(aAssetKeyValue) === -1) {
                    newValues.push(aAssetKeyValue); // 중복이 아니면 배열에 추가
                }
            }
        }
        
        // accumulatedValues 배열과 newValues 배열을 합쳐서 최종 값을 설정합니다.
        var finalValues = accumulatedValues.concat(newValues);
        
        // 최종 값을 ePageInfo에 설정합니다.
        ePageInfoInput.value = newValues.join("_");
    }
}

function restoreCheckboxesFromPageInfo() {
    var ePageInfoInput = document.getElementById("ePageInfo");
    var checkboxPrefix = "check_"; // 체크박스 id의 접두사
    
    if (ePageInfoInput) {
        var pageInfoValues = ePageInfoInput.value.split("_"); // ePageInfo의 값을 '_'로 분할하여 배열로 변환
        
        pageInfoValues.forEach(function(value) {
            var checkboxId = checkboxPrefix + value;
            var checkbox = document.getElementById(checkboxId);
            
            if (checkbox) {
                checkbox.checked = true; // 해당 체크박스를 체크 처리
            }
        });
    }
}
// 검색
function fn_guestList(pageNo) {
	ePageInfoList();
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/equipment/kw_equipment_box_lf.do";
	document.frm.submit();
}


function fn_search_detail(){
	
	if($("#search_detail").is(":visible")){
		$('#search_detail').attr("style", "display : none;");
	}else{
		$('#search_detail').attr("style", "display : block;");
	}	
}

function rowCheck(index){
	 // 해당 체크박스 요소를 가져옵니다.
    var checkbox = document.getElementById("check_" + index);

    // 체크박스의 현재 상태를 반전시킵니다.
    checkbox.checked = !checkbox.checked;
}
</script>

<form id="frm" name="frm" method="post" action="/mes/equipment/kw_equipment_box_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesEquipmentVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesEquipmentVO.searchType}">
	<input type="text" id="ePageInfo" name="ePageInfo" value="${mesEquipmentVO.ePageInfo}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>임시장비 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1);">
	              		<option value="20" <c:if test="${mesEquipmentVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesEquipmentVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesEquipmentVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select>					
				</li>
				<li>
					<span>모델명</span>
					<input type="text" id="eSearchWord1" name="eSearchWord1" style="width:150px;" class="searchWord" value="${mesEquipmentVO.eSearchWord1}" maxlength="30"  />
				</li>
				<li>
					<span>자산명</span>
					<input type="text" id="eSearchWord2" name="eSearchWord2" style="width:150px;" class="searchWord" value="${mesEquipmentVO.eSearchWord2}" maxlength="30" />
				</li>
				<li>
					<span>제조사</span>
					<input type="text" id="searchTypeSet4" name="eSearchWord3" style="width:150px;" class="eSearchWord3" value="${mesEquipmentVO.eSearchWord3}" maxlength="30"  />
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
<!-- 			<ul id="search_detail" style="display: none;"> -->
<!-- 				<li> -->
<!-- 					<span>모델명</span> -->
<%-- 					<input type="text" id="searchTypeSet6" name="searchTypeSet6" style="width:100px;" value="${mesEquipmentVO.searchTypeSet6}" /> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<span>상태</span> -->
<%-- 					<input type="text" id="searchTypeSet7" name="searchTypeSet7" style="width:100px;" value="${mesEquipmentVO.searchTypeSet7}" /> --%>
<!-- 				</li> -->
<!-- 			</ul> -->
			<ul class="tbl_top_right">
				<li>
					<a onclick='selectItem()'>선택</a>
				</li>
			</ul>
		</div>
		
		
		<div class="lf_tbl_list" id="pop_result_list">
			<table>
		        <thead>
					<tr>
						<th style="width:2%;">-</th>
<!-- 						<th style="width:2%;">No.</th> -->
						<th style="width:8%;">자산유형</th>
						<th style="width:8%;">자산명</th>
						<th style="width:8%;">제조사</th>
						<th style="width:8%;">제조번호</th>
						<th style="width:8%;">모델명</th>
						<th style="width:8%;">HOST NAME</th>
						<th style="width:8%;">IP</th>
						<th style="width:8%;">OS</th>
<!-- 						<th style="width:8%;">설치위치</th> -->
						<th style="width:8%;">도입일</th>
<!-- 						<th style="width:8%;">도입원가</th> -->
<!-- 						<th style="width:10%;">자산관리</th> -->
					</tr>
		        </thead>
		        <tbody>
					<c:forEach var="list" items="${assetList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="rowCheck('${list.eEquipmentItemKey}')" >
							<td onclick="event.cancelBubble = true;">
				        		<input type="hidden" id="cnt_${i.index}" name="rowIndex" value="${i.index}"/>
								<input type="checkbox" id="check_${list.eEquipmentItemKey}" name="check">
								<input type="hidden" id="eEquipmentItemKey_${i.index}" name="eEquipmentItemKey" value="${list.eEquipmentItemKey}" />
							</td>
							<td>
								${list.eAssetTypeName}
							</td>
							<td>
								${list.eAssetName}
							</td>
							<td>
								${list.eAssetMaker}
							</td>
							<td>
								${list.eAssetSNumber}
							</td>
							<td>
								${list.eAssetModel}
							</td>
							<td>
								${list.eAssetHostName}
							</td>
							<td>
								${list.eAssetIp}
							</td>
							<td>
								${list.eAssetOs}
							</td>
<!-- 							<td> -->
<%-- 								${list.ePositionName1} --%>
<!-- 							</td> -->
							<td>
								${list.eEntryImportDate}
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
		
		<div class="page">	
			<span>
		  		<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
			</span>
		</div>	
	</div>
</form>

