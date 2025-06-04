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
	datepickerIdSet("topStartDate");
	datepickerIdSet("topEndDate");
	 $('table[role="grid"].gridjs-table tr').css("cursor","default");
	//$('table[role="grid"].gridjs-table th').css('pointer-events', 'none');
	$('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	$('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '180px');
	$('table[role="grid"].gridjs-table td:nth-child(3)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	$('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '160px');
	$('table[role="grid"].gridjs-table td:nth-child(4)').each(function() {
	    var content = $(this).html();
	    // <p>와 </p>를 제거
	    $(this).html(content);
	 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
	    $(this).css({
	        'white-space': 'nowrap',
	        'overflow': 'hidden',
	        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
	    });
	});
	$('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '160px');
	$('table[role="grid"].gridjs-table td:nth-child(5)').each(function() {
	    var content = $(this).html();
	    // <p>와 </p>를 제거
	    $(this).html(content);
	 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
	    $(this).css({
	        'white-space': 'nowrap',
	        'overflow': 'hidden',
	        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
	    });
	});
	$('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '160px');
	$('table[role="grid"].gridjs-table td:nth-child(6)').each(function() {
	    var content = $(this).html();
	    // <p>와 </p>를 제거
	    $(this).html(content);
	 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
	    $(this).css({
	        'white-space': 'nowrap',
	        'overflow': 'hidden',
	        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
	    });
	});
	
	
}); 

function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/asset/kw_Hardware_lf.do";
	document.listForm.submit();
}

viewService.prototype.go_view = function(obj) {
// 	if(obj.childNodes[0].querySelector("input")){
// 		$("#mloader").show();
// 		$("#eAssetKey").val(obj.childNodes[0].querySelector("input").value);
// 		document.listForm.action = "/mes/asset/kw_asset_vf.do";
// 		document.listForm.submit();
// 	}
}

function go_insert(){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_asset_if.do";
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
 eDate();
 
 $(".clear-input").click(function() {
     var targetId = $(this).data("target");
     $("#" + targetId).val("");
 });
});



function selectName(selectElement, inputId) {
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var selectedValue = selectedOption.getAttribute('data-value2');
    document.getElementById(inputId).value = selectedValue;
    
}
function clearInput(inputId) {
    document.getElementById(inputId).value = '';
}
 
</script>
  
<style>
  
</style>
<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_asset_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="eAssetKey" name="eAssetKey" value="" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>하드웨어 라이프사이클</h2>
		</div>		
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>자산유형</span>
							<select id='searchTypeSet1' name='searchTypeSet1'  >
								<option value=''>전체조회</option>
								<c:forEach var='list' items='${gubun36List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'   <c:if test="${mesAssetVO.searchTypeSet1 eq list.sGubunName  }">selected="selected"</c:if> >${list.sGubunName}</option>						
								</c:forEach>
							</select> 
					</li>
					<li>
						<span>자산번호</span>
						<input type="text" id="searchWord" name="searchWord" class="searchWord" value="${mesAssetVO.searchWord}" maxlength="30" />
					</li>
					<li>
						<span>제조사</span>
						<input type="text" id="searchTypeSet3" name="searchTypeSet3" class="searchWord" value="${mesAssetVO.searchTypeSet3}" maxlength="30"  />
					</li>
					<li>
						<span>자산명</span>
						<input type="text" id="searchTypeSet2" name="searchTypeSet2" class="searchWord" value="${mesAssetVO.searchTypeSet2}" maxlength="30" />
					</li>
					<li>
						<span>모델명</span>
						<input type="text" id="searchTypeSet4" name="searchTypeSet4" class="searchWord" value="${mesAssetVO.searchTypeSet4}" maxlength="30" />
					</li>
					<li>
						<span>일반/노후 </span>
							<select id='searchTypeSet9' name='searchTypeSet9'   >
								<option value='' >전체조회</option>
								<option value='노후장비' <c:if test="${mesAssetVO.searchTypeSet9 eq '노후장비'  }">selected="selected"</c:if>>노후장비</option>
								<option value='일반장비' <c:if test="${mesAssetVO.searchTypeSet9 eq '일반장비'  }">selected="selected"</c:if>>일반장비</option>
							</select>
					</li>
	<!-- 				<li><span style="font-size: 24px;font-weight: bold;">&#91;</span></li> -->
					<li>
						 <span   onclick="clearInput('topStartDate')">EoS</span>
						<input type="text" id="topStartDate" name="topStartDate" value="${mesAssetVO.topStartDate}" readonly  class="inp_color" />
					</li>
					<li>
						 <span   onclick="clearInput('topEndDate')">EoL</span>
						<input type="text" id="topEndDate" name="topEndDate"  value="${mesAssetVO.topEndDate}" readonly  class="inp_color" />
					</li>
				</ul>
			</div>
			<div class="button_wrap">
		    	<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>
	
	<div class="data_table">
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>자산유형</th>
					<th>자산번호</th>
					<th>모델명</th>
					<th>자산명</th>
					<th>제조사</th>
					<th>도입일</th>
					<th>EoS</th>
					<th>EoL</th>
					<th>자산상태</th>
					<th>내구년수</th>
					<th>일반/노후</th>
					 
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr onclick="assetView(<c:out value='${list.eAssetKey}'/>);">
						<td>
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eAssetKey}" />
						</td>
						<td>${list.eAssetTypeName }</td>
						<td>${list.eAssetNumber }</td>
						<td>${list.eAssetModel}</td>
						<td>${list.eAssetName}</td>
						<td>${list.eAssetMaker}</td>
						<td>${list.eAssetDate}</td>
						<td>${list.eEosDate} </td>
						<td>${list.eEolDate}  </td>
						<td>${list.eAssetStatusName}  </td>
						<td>${list.eLifespan}  </td>
						<td>${list.eLifeType}  </td>
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
			<!--  <button type="button" class="form_btn bg" onclick="formDownload()">양식 다운로드</button> -->
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
			<!--  
			<button type="button" class="form_btn bg" onclick="document.getElementById('managerFile').click();" >엑셀 등록</button>
			<input id="managerFile" type="file"  style="display: none;" onchange="readExcel(event);"  accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
			<button type="button" class="form_btn active" onclick="go_insert()">자산등록</button>
			 -->
			</c:if>
		</div>
	</div>
</form>