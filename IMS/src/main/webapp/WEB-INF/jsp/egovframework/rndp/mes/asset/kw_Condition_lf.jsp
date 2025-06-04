<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script src="/js/xlsx.full.min.js"></script>
<script src="/js/papaparse.min.js"></script>


<script type="text/javascript"> 
$(function(){
	datepickerSet("topStartDate","topEndDate");
	  $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '100px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '120px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '120px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '100px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '120px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '100px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '90px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '100px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '120px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '120px'); 
	  $('table[role="grid"].gridjs-table th:nth-child(13)').css('width', '300px'); 
	  $('table[role="grid"].gridjs-table td:nth-child(13)').each(function() {
		    $(this).css({
		    	  "cursor": "default"
		    });
		});
	  tdBlock(12);
	  $('#mloader').hide();
}); 

function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	if ($("#viewDetail").val() === "Y") {
		document.listForm.viewDetail.value = "Y";
    }
	document.listForm.action = "/mes/asset/kw_eCondition_lf.do";
	document.listForm.submit();
}
 

function go_insert(){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_eCondition_if.do";
	document.listForm.submit();
}

function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}


function formDownload(){
    document.listForm.action = "/mes/asset/kw_assetExcelForm.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_asset_lf.do";
}

function readExcel(e) {
	$('#mloader').show();
    var input = event.target;
    var reader = new FileReader();
	var cnt = 0;
    reader.onload = function () {
        var data = reader.result;
        var workBook = XLSX.read(data, { type: 'binary' , cellDates: true, dateNF: 'yyyy-mm-dd' });
        workBook.SheetNames.forEach(function (sheetName) {
            var arr = XLSX.utils.sheet_to_json(workBook.Sheets[sheetName], {header:1, raw:false});
            console.log(arr);
        	for(var i = 1 ; i < arr.length; i++){  
        		if(arr[i][0] != ""){
        			$.ajax({
            			method : "post"
            		,	cache  : false
            		,	url    : "/mes/asset/kw_uploadAssetAjax.do"
            		,	dataType : "json"
            		,	async: false
                 	, 	data: {
                 			aAssetNumber: arr[i][0]
      			      	,	aAssetStatus: arr[i][1]
      				    ,	aAssetType: arr[i][2]
      					,	aAssetName: arr[i][3]
	      				,	aAssetMaker: arr[i][4]
	      				,	aAssetModel: arr[i][5]
	      				,	aAssetManufactureNumber: arr[i][6]
	      				,	aAssetForm: arr[i][7]
	      				,	aAssetIntroducer: arr[i][8]
	      				,	aAssetDate: arr[i][9]
	      				,	aAssetCost: uncomma(arr[i][10])
	      				,	aAssetPurpose: arr[i][11]
	      				,	aAssetEtc: arr[i][12]
	                 	}
            		,	success:function(msg){
            				console.log("성공");
            			}
            		,	error: function(xhr, status, error) {
                     		// 요청이 실패했을 때 수행할 작업
                     		console.log(error);
                     		if (xhr.status === 500) {
                     			// AJAX 요청 중단
                     			console.log('AJAX 요청이 중지되었습니다.');
							}
						} 
             		});
        		}
       		} 
        });
    };
    reader.readAsBinaryString(input.files[0]);
    
    e.target.value = ''; 
}


function eDate(){
	  var currentDate = new Date();
	  var eEosDates = document.getElementsByName("eEosDate");
	  var eEolDates = document.getElementsByName("eEolDate");
	  
	  for (var i = 0; i < eEosDates.length; i++) {
        var eEosDate = eEosDates[i].value;
        var eEolDate = eEolDates[i].value;
        
        var formattedEosDate = formatDateData(new Date(eEosDate));
        var formattedEolDate = formatDateData(new Date(eEolDate));
        
        // eEosDate 처리
        if (!isNaN(formattedEosDate.getTime())) {
            var differenceInDaysEos = Math.floor((formattedEosDate - currentDate) / (1000 * 60 * 60 * 24));
            var expiredEos = differenceInDaysEos < 0;
            var spantextEos = expiredEos ? ": 만료" : ":까지 " + differenceInDaysEos + "일 남음";
            
            document.getElementById("eEosDateTxt_" + i).innerHTML = spantextEos;
        } else {
            document.getElementById("eEosDateTxt_" + i).innerHTML = "";
        }
        
        // eEolDate 처리
        if (!isNaN(formattedEolDate.getTime())) {
            var differenceInDaysEol = Math.floor((formattedEolDate - currentDate) / (1000 * 60 * 60 * 24));
            var expiredEol = differenceInDaysEol < 0;
            var spantextEol = expiredEol ? ": 만료" : ":까지 " + differenceInDaysEol + "일 남음";
            
            document.getElementById("eEolDateTxt_" + i).innerHTML = spantextEol;
        } else {
            document.getElementById("eEolDateTxt_" + i).innerHTML = "";
        }
    }
	  
}

function formatDateData(date) {
	var formattedDate = new Date(date);
    return formattedDate;
}



$(document).ready(function() {
	if ($("#viewDetail").val() === "Y") {
        $("#search_detail").css("display", "flex");
    }
 $(".clear-input").click(function() {
     var targetId = $(this).data("target");
     $("#" + targetId).val("");
 });
 
 
 tdBlock(11);
});

viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){  
// 		$("#mloader").show();
		$("#eEntryExitKey").val(obj.childNodes[0].querySelector("input").value);
// 		obj.childNodes[0].querySelector("input").value();
		document.listForm.action = "/mes/asset/kw_eCondition_out_vf.do";
		document.listForm.submit();
	}
}

function eImport_go(eEntryExitItemKey){	
	var sUrl = "/mes/asset/kw_eImport_update_pop.do?eEntryExitItemKey="+eEntryExitItemKey;
	window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
	window.focus();
}


function startApproval(eEntryExitKey, eStatus){
	$("#mloader").show();
	$("#eEntryExitKey").val(eEntryExitKey);
	$("#eStatus").val(eStatus);
	document.listForm.action = "/mes/asset/kw_eCondition_lfr.do";
	document.listForm.submit();
}

function excelDwonload(){
    document.listForm.action = "/mes/asset/kw_eConditionExcelListDwonload.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_eCondition_lf.do";
}
</script>
  
<style>
  
</style>
<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_eCondition_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="eEntryExitKey" name="eEntryExitKey" value="" />
	<input type="hidden" id="eStatus" name="eStatus" value="" />
	<input type="hidden" id="viewDetail" name="viewDetail" value="${mesAssetVO.viewDetail}" />
	<div class="content_top">
		<div class="content_tit">
			<h2>보유자산 반출입 관리</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>자산번호</span>
						<input type="text" id="searchTypeSet7" name="searchTypeSet7" class="searchWord" value="${mesAssetVO.searchTypeSet7}" maxlength="30"  />
					</li>
					<li>
						<span>자산명</span>
						<input type="text" id="searchTypeSet1" name="searchTypeSet1" class="searchWord" value="${mesAssetVO.searchTypeSet1}" maxlength="30"  />
					</li>
					<li>
						<span>제조사</span>
						<input type="text" id="searchTypeSet2" name="searchTypeSet2" class="searchWord" value="${mesAssetVO.searchTypeSet2}" maxlength="30"  />
					</li>
					<li>
						<span>모델명</span>
						<input type="text" id="searchTypeSet3" name="searchTypeSet3" class="searchWord" value="${mesAssetVO.searchTypeSet3}" maxlength="30" />
					</li>
					<li>
						<span>반출일</span>
						<div class="date">
							<input type="text" id="topStartDate" name="topStartDate" value="${mesAssetVO.topStartDate}" readonly />
				           	- <input type="text" id="topEndDate" name="topEndDate" style="padding-left:2px;" value="${mesAssetVO.topEndDate}" readonly />
			           	</div>						
					</li>
				</ul>
				<ul id="search_detail">
					<li>
						<span>반출자</span>
						<input type="text" id="searchTypeSet4" name="searchTypeSet4" value="${mesAssetVO.searchTypeSet4}" maxlength="30" />
					</li>
					<li>
						<span>반출사유</span>
						<input type="text" id="searchTypeSet5" name="searchTypeSet5" value="${mesAssetVO.searchTypeSet5}" maxlength="30" />
					</li>
					<li>
						<span>반입확인자</span>
						<input type="text" id="searchTypeSet6" name="searchTypeSet6" class="searchWord" value="${mesAssetVO.searchTypeSet6}" maxlength="30"  />
					</li>
				</ul> 
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn ico_search" onclick="fn_search_detail();" >상세검색</button>
				<button type="button" class="form_btn ico_excel" onclick="excelDwonload();">엑셀 다운로드</button>
	     		<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>	
	<div class="data_table">
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>작성자</th>
					<th>자산유형</th>
					<th>자산번호</th>
					<th>자산명</th>
					<th>제조사</th>
					<th>모델명</th>
					<th>반출일</th>
					<th>반출자</th>
					<th>반출사유</th>
					<th>반입일자</th>
					<th>반입확인자</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr onclick="assetView(<c:out value='${list.eEntryExitItemKey}'/>);">
						<td >
							<c:if test="${list.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eEntryExitKey}" />
						</td>
						<td>${list.kStaffName}</td>
						<td>${list.eAssetType}</td>
						<td>${list.eAssetNumber}</td>
						<td>${list.eAssetName}</td>
						<td>${list.eAssetMaker}</td>
						<td>${list.eAssetModel}</td>
						<td>${list.eEntryExitDate}</td>
						<td>${list.eEntryStaff}</td>
						<td>${list.eEntryRequestReason}</td>
						<td>${list.eEntryImportDate}</td>
						<td>${list.eEntryImporter}</td>
						<td onclick="event.cancelBubble = true;">  
							<c:if test="${list.eStatus eq '제외'}">결재 제외	</c:if>
							<c:if test="${list.eStatus eq '승인' || list.eStatus eq '반려'}">${list.eStatus}</c:if>
							<c:if test="${list.eStatus eq '등록'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey}"><a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eEntryExitKey}','Y');">승인요청</a></c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
							</c:if>
							<c:if test="${list.eStatus eq '승인요청'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey && list.sSignProgress eq '0'}">
									<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eEntryExitKey}','N');">요청취소</a>
								</c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
							</c:if>
							
							<c:if test="${empty list.eEntryImporter}"> 
								-${list.eEntryExitDate}:반출 &nbsp;&nbsp; 
								<c:if test="${list.eStatus eq '승인'  || list.eStatus eq '제외'}">
									<a class="form_btn sm" onclick="eImport_go(${list.eEntryExitItemKey});">반입등록</a>
								</c:if>
							</c:if>	
							<c:if test="${not empty list.eEntryImporter}">
						     	-${list.eEntryImportDate}: 반입완료
							</c:if>
						
							
						</td>
						 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
          		<option value="10" <c:if test="${mesAssetVO.recordCountPerPage eq 10}">selected="selected"</c:if>>10개씩 보기</option>
          		<option value="20" <c:if test="${mesAssetVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
          		<option value="50" <c:if test="${mesAssetVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
          		<option value="100" <c:if test="${mesAssetVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
   			</select> 
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList"/>
		</div>
		<div class="btns">
			<!--  <button type="button" class="form_btn ico_excel" onclick="formDownload()">양식 다운로드</button>  -->
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
				<!--  <button type="button" class="form_btn ico_excel" onclick="document.getElementById('managerFile').click();">엑셀 등록</button>  -->
				<input id="managerFile" type="file"  style="display: none;" onchange="readExcel(event);"  accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
				<button type="button" class="form_btn active" onclick="go_insert()">반출 등록</button>
			</c:if>
		</div>
	</div>
</form>