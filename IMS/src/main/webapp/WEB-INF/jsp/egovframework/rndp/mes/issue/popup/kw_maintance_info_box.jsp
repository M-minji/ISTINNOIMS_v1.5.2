<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

$(function(){
	datepickerSet('topStartDate', 'topEndDate');
// 	datepickerIdSet("eSearchWordH");
})


function selectItem(){  
	ePageInfoList();
	 var previousValues = document.getElementById("ePageInfo").value.split("_");
	   if(previousValues.length > 0){
			var obj = {
				mMaintanceKey :	$("#ePageInfo").val()
			}
			if(typeof(opener.setMaintanceReturnPop) != "undefined"){
			window.opener.setMaintanceReturnPop(obj);
			window.close();
			}
	   }
}
function opnerValueChk(aAssetKey, idx) {

	var eAssetKeyArr = opener.document.getElementsByName("mMaintanceKey").length;
	if(eAssetKeyArr > 0){
		for(var i=0; i < eAssetKeyArr ; i++){
			var eAssetKey = opener.document.getElementsByName("mMaintanceKey")[i].value;
			
				if(eAssetKey == aAssetKey){
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
    var aAssetKeylen = document.getElementsByName("mRowMaintanceKey").length;
    var ePageInfoInput = document.getElementById("ePageInfo");
    
    if (aAssetKeylen > 0) {
        var currentValues = ePageInfoInput.value.split("_"); // 현재 ePageInfo의 값 배열로 변환
        var newValues = []; // 새로운 값들을 임시 배열에 담습니다.
        
        for (var idx = 0; idx < aAssetKeylen; idx++) {
            var aAssetKeyValue = document.getElementsByName("mRowMaintanceKey")[idx].value;
            
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
	document.frm.action = "/mes/maintance/kw_maintance_box_lf.do";
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

<form id="frm" name="frm" method="post" action="/mes/maintance/kw_maintance_box_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesMaintanceVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesMaintanceVO.searchType}">
	<input type="hidden" id="ePageInfo" name="ePageInfo" value="${mesMaintanceVO.ePageInfo}">
	<input type="hidden" id="mMaintanceKey" name="mMaintanceKey" value="${mesMaintanceVO.mMaintanceKey}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>유지 정보 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<span>관리번호</span>
					<input type="text" id="eSearchWordA" name="eSearchWordA" style="width:150px;" class="searchWord" value="${mesMaintanceVO.eSearchWordA}" maxlength="30" />
				</li>
   		 		<li>
					<span>자산유형</span>
					<input type="hidden" id="eSearchWordB" name="eSearchWordB" style="width:150px;" class="searchWord" value="${mesMaintanceVO.eSearchWordB}" maxlength="30" />
					<select id='eAssetType' name='eAssetType'  onchange="selectName(this,'eSearchWordB')" style="width:100px;" >
						<option value='' <c:if test="${mesMaintanceVO.eSearchWordB eq ''}">selected="selected"</c:if>>전체</option>
						<c:forEach var='list' items='${gubun36List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${list.sGubunName eq mesMaintanceVO.eSearchWordB}">selected="selected"</c:if>>${list.sGubunName}</option>						
						</c:forEach>
					</select>
				</li>
				<li>
					<span>처리유형</span>
					<input type="hidden" id="eSearchWordC" name="eSearchWordC" style="width:150px;" class="searchWord" value="${mesMaintanceVO.eSearchWordC}" maxlength="30"  />
					<select id="mMaintanceType" name="mMaintanceType" style="width:100px;" onchange="selectName(this,'eSearchWordC')" >
						<option value="" <c:if test="${mesMaintanceVO.eSearchWordC eq ''}">selected="selected"</c:if>>전체</option>
							<c:forEach var="gubun33List" items="${gubun33List}">
								<option value='${gubun33List.sGubunKey}' data-value2='${gubun33List.sGubunName}' <c:if test="${gubun33List.sGubunName eq mesMaintanceVO.eSearchWordC}">selected="selected"</c:if>>${gubun33List.sGubunName}</option>							
							</c:forEach>		       				
		     			</select>
				</li>
				<li>
					<span>장애구분</span>
						<input type="hidden" id="eSearchWordD" name="eSearchWordD" style="width:100px;" value="${mesMaintanceVO.eSearchWordD}" maxlength="30" />
						<select id="mMaintanceIssueType" name="mMaintanceIssueType" style="width:120px;" onchange="selectName(this,'eSearchWordD')">
							<option value="" <c:if test="${mesMaintanceVO.eSearchWordG eq ''}">selected="selected"</c:if>>전체</option>
							<c:forEach var="gubun34List" items="${gubun34List}">
								<option value="${gubun34List.sGubunName}" data-value2='${gubun34List.sGubunName}' <c:if test="${gubun34List.sGubunName eq mesMaintanceVO.eSearchWordG}">selected="selected"</c:if>>${gubun34List.sGubunName}</option>	
													
							</c:forEach>	
						</select>
				</li>
				<li>
					 <span>요청일자</span>
		       		<input type="text" name="topStartDate" id="topStartDate" value="${mesMaintanceVO.topStartDate}" style="width:100px;text-align: center;" readonly  class="inp_color"/>
		           	- <input type="text" name="topEndDate" id="topEndDate" value="${mesMaintanceVO.topEndDate}" style="padding-left:2px; width:100px;text-align: center;" readonly class="inp_color" />
		     	</li>
				<li>	
		     		<a onclick="fn_guestList(1)">검색</a>
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
<%-- 					<input type="text" id="searchTypeSet6" name="searchTypeSet6" style="width:100px;" value="${mesAssetVO.searchTypeSet6}" /> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<span>상태</span> -->
<%-- 					<input type="text" id="searchTypeSet7" name="searchTypeSet7" style="width:100px;" value="${mesAssetVO.searchTypeSet7}" /> --%>
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
						<th >관리 번호</th>
			      		<th >자산유형</th>
			      		<th >처리유형</th>
			      		<th >요청기관</th>
			      		<th >요청일자</th>
			      		<th >요청내용</th>
					</tr>
		        </thead>
		        <tbody>
					<c:forEach var="list" items="${maintanceList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="rowCheck('${list.mMaintanceKey}')" >
							<td onclick="event.cancelBubble = true;">
				        		<input type="hidden" id="cnt_${i.index}" name="rowIndex" value="${i.index}"/>
								<input type="checkbox" id="check_${list.mMaintanceKey}" name="check">
								<input type="hidden" id="mmRowMaintanceKey_${i.index}" name="mRowMaintanceKey" value="${list.mMaintanceKey}" />
					 						
							</td>
							<td style="text-align:left; padding-left:5px;">
								${list.mMaintanceNumber}
							</td>
							<td style="text-align:left; padding-left:5px;">
								${list.eAssetTypeName}
							</td>
							<td style="text-align:left; padding-left:5px;">
								${list.mMaintanceTypeName}
							</td>
							<td style="text-align:left; padding-left:5px;">
								${list.mMaintanceCateName}
							</td>
							<td style="text-align:center;">
								${list.mRequestDate}
							</td>
							<td style="text-align:center;">
								${list.mMaintanceContent}
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty maintanceList}">
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

