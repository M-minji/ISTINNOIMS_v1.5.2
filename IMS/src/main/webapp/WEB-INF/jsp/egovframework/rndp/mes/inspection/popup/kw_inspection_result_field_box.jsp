<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">



function selectItem() {  
    ePageInfoList();

    var selectedRow = document.querySelector("input[name='check']:checked");
    if (selectedRow) {
    	var rowInputs = selectedRow.closest("tr").querySelectorAll("input");
    	var eFieldKey = Array.from(rowInputs).find(input => input.name === "eFieldKey")?.value;
    	
        var eField1 = selectedRow.closest("tr").querySelector("input[name='eField1']").value;
        var eField2 = selectedRow.closest("tr").querySelector("input[name='eField2']").value;
        var eField3 = selectedRow.closest("tr").querySelector("input[name='eField3']").value;
        var eField4 = selectedRow.closest("tr").querySelector("input[name='eField4']").value;
        var eField5 = selectedRow.closest("tr").querySelector("input[name='eField5']").value;
        
        var obj = {
       		eFieldKey: eFieldKey || "",
            eField1: eField1 || "필드1",
            eField2: eField2 || "필드2",
            eField3: eField3 || "필드3",
            eField4: eField4 || "필드4",
            eField5: eField5 || "필드5"
        };

        if (typeof(opener.setFieldReturnPop) !== "undefined") {
            opener.setFieldReturnPop(obj);
            window.close();
        }
    }
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
    var aAssetKeylen = document.getElementsByName("eFieldKey").length;
    var ePageInfoInput = document.getElementById("ePageInfo");
    
    if (aAssetKeylen > 0) {
        var currentValues = ePageInfoInput.value.split("_"); // 현재 ePageInfo의 값 배열로 변환
        var newValues = []; // 새로운 값들을 임시 배열에 담습니다.
        
        for (var idx = 0; idx < aAssetKeylen; idx++) {
            var aAssetKeyValue = document.getElementsByName("eFieldKey")[idx].value;
            
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
	document.frm.action = "/mes/inspection/kw_inspection_field_box.do";
	document.frm.submit();
}


function rowCheck(index){
	// 모든 체크박스를 가져와서 반복
    var checkboxes = document.getElementsByName("check");

    checkboxes.forEach(function(checkbox) {
        checkbox.checked = false; // 모든 체크박스 체크 해제
    });

    // 현재 클릭한 체크박스만 체크
    var selectedCheckbox = document.getElementById("check_" + index);
    selectedCheckbox.checked = true;

    // ePageInfo 값 갱신 (선택한 항목만 저장)
    document.getElementById("ePageInfo").value = index;
}

</script>

<form id="frm" name="frm" method="post" action="/mes/inspection/kw_inspection_field_box.do" class="popup_wrap">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesInspectionVO.pageIndex}">
	<input type="hidden" id="searchType" name="searchType" value="${mesInspectionVO.searchType}">
	<input type="hidden" id="ePageInfo" name="ePageInfo" value="${mesInspectionVO.ePageInfo}">
	<input type="hidden" id="csrfToken" name="csrfToken" value="${csrfToken}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>점검결과 필드 조회</h3>
			</div>
			<a href="javascript:self.close();"></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="content_top">
			<div class="filter_wrap">
				<div class="search_filter">
					<ul>
						<li>
							<span>이름</span>
							<input type="text" id="eSearchTypeSet1" name="eSearchTypeSet1" class="searchWord" value="${mesInspectionVO.eSearchTypeSet1}" />
						</li>
						<li>
							<span>필드</span>
							<input type="text" id="eSearchTypeSet2" name="eSearchTypeSet2"  class="searchWord" value="${mesInspectionVO.eSearchTypeSet2}" />
						</li>
					</ul>
				</div>
				<div class="button_wrap">
					<button type="button" class="form_btn bg" onclick="fn_guestList(1);">검색</button>
				</div>	
			</div>
		</div>
		
		<div class="pop_con">
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr>
							<th style="width:2%;">선택</th>
							<th style="width:8%;">이름</th>
							<th style="width:8%;">필드1</th>
							<th style="width:8%;">필드2</th>
							<th style="width:8%;">필드3</th>
							<th style="width:8%;">필드4</th>
							<th style="width:8%;">필드5</th>
						</tr>
			        </thead>
			        <tbody>
						<c:forEach var="list" items="${fieldList}" varStatus="i">
							<tr style="cursor: pointer;" onclick="rowCheck('${list.eFieldKey}')" >
								<td onclick="event.cancelBubble = true;" style="cursor: default;">
					        		<input type="hidden" id="cnt_${i.index}" name="rowIndex" value="${i.index}"/>
					        		<label class="inp_chkbox">
					        			<input type="checkbox" id="check_${list.eFieldKey}" name="check" style="pointer-events: none;">
					        			<i></i>
					        		</label>
									<input type="hidden" id="eFieldKey" name="eFieldKey" value="${list.eFieldKey}" />
									<input type="hidden" id="eFieldName" name="eFieldName" value="${list.eFieldName}" />
									<input type="hidden" id="eField1" name="eField1" value="${list.eField1}" />
									<input type="hidden" id="eField2" name="eField2" value="${list.eField2}" />
									<input type="hidden" id="eField3" name="eField3" value="${list.eField3}" />
									<input type="hidden" id="eField4" name="eField4" value="${list.eField4}" />
									<input type="hidden" id="eField5" name="eField5" value="${list.eField5}" />				
								</td>
								<td>
									${list.eFieldName}
								</td>
								<td>
									${list.eField1}
								</td>
								<td>
									${list.eField2}
								</td>
								<td>
									${list.eField3}
								</td>
								<td>
									${list.eField4}
								</td>
								<td>
									${list.eField5}
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty fieldList}">
							<tr>
								<td colspan="20" class="tac">조회 정보가 없습니다.</td>
							</tr>
						</c:if>
			        </tbody>
				</table>
			</div>		
			
			<div class="list_btm">
				<div class="options">
					<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1);">
	              		<option value="20" <c:if test="${mesInspectionVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
	              		<option value="50" <c:if test="${mesInspectionVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
	              		<option value="100" <c:if test="${mesInspectionVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
       				</select>		
				</div>
				<div class="paging"><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></div>
				<div class="btns">
					<button type="button" class="form_btn active" onclick='selectItem()'>선택</button>
				</div>
			</div>
		</div>
	</div>
</form>

