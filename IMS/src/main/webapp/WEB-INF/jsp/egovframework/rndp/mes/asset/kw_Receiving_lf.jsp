<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	/>
<link href="/css/mes/mermaid2.min.css" rel="stylesheet"	/>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script src="/js/xlsx.full.min.js"></script>
<script src="/js/papaparse.min.js"></script>


<script type="text/javascript"> 
$(function(){
	datepickerSet("topStartDate","topEndDate");
}); 

function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/asset/kw_eReceiving_lf.do";
	document.listForm.submit();
}
 

function go_insert(){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_eReceiving_if.do";
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
 
 $(".clear-input").click(function() {
     var targetId = $(this).data("target");
     $("#" + targetId).val("");
 });
 $('#mloader').hide();
});

viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){  
		$("#eEntryExitKey").val(obj.childNodes[0].querySelector("input").value);
		document.listForm.action = "/mes/asset/kw_eReceiving_vf.do";
		document.listForm.submit();
	}
}


function eImport_go(eEntryExitItemKey){	
	var sUrl = "/mes/asset/kw_eImport_update_pop.do?eEntryExitItemKey="+eEntryExitItemKey;
	window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
	window.focus();
}
</script>
  
<style>
  
</style>
<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_eReceiving_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="eEntryExitKey" name="eEntryExitKey" value="" />
	
	<div class="content_up">
		<div class="content_tit">
			<h2>보유자산 반입 정보 현황</h2>
		</div>		
		
		<div class="tbl_top"> 
			<ul class="tbl_top_left">
				
				<li>
					<span>제조사</span>
					<input type="text" id="searchTypeSet1" name="searchTypeSet1" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet1}" maxlength="30"  />
				</li>
				<li>
					<span>자산명</span>
					<input type="text" id="searchTypeSet2" name="searchTypeSet2" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet2}" maxlength="30" />
				</li>
				<li>
					<span>반입 일자</span>
					<input type="text" id="topStartDate" name="topStartDate" style="width:100px;" class="inp_color"  value="${mesAssetVO.topStartDate}" readonly />
		           	- <input type="text" id="topEndDate" name="topEndDate" style="width:100px;" class="inp_color"  style="padding-left:2px;" value="${mesAssetVO.topEndDate}" readonly />
					
				</li>
				<li style="    padding-left: 50px;">
					<a onclick="fn_guestList(1);" style="cursor: pointer;">
		    			검색
		     		</a>
				</li>
				<li>
					<a onclick="fn_search_detail();" style="cursor: pointer;">
		    			상세검색
		     		</a>
				</li>
			</ul>
			<ul id="search_detail" style="display: none;">
				<li>
					<span>호스트명</span>
					<input type="text" id="searchTypeSet3" name="searchTypeSet3" style="width:100px;" value="${mesAssetVO.searchTypeSet3}" maxlength="30" />
					
				</li>
				<li>
					<span>IP</span>
					<input type="text" id="searchTypeSet4" name="searchTypeSet4" style="width:100px;" value="${mesAssetVO.searchTypeSet4}" maxlength="30" />
				</li>
				<li>
					<span>OS</span>
					<input type="text" id="searchTypeSet5" name="searchTypeSet5" style="width:150px;" class="searchWord" value="${mesAssetVO.searchTypeSet5}" maxlength="30"  />
				</li>
 
			</ul>	
		</div>
	</div> 	
	
	<div id="tableContainer" class="lf_tbl_list"  >
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>자산유형</th>
					<th>자산번호</th>
					<th>자산명</th>
					<th>제조사</th>
					<th>모델명</th>
					<th>반입일</th>
					<th>반입확인자</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetList}" varStatus="i">
<%-- 					<tr onclick="assetView(<c:out value='${list.eEntryExitItemKey}'/>);"> --%>
					<tr  >
						<td>
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eEntryExitItemKey}" />
						</td>
						<td>${list.eAssetType}</td>
						<td>${list.eAssetNumber}</td>
						<td>${list.eAssetName}</td>
						<td>${list.eAssetMaker}</td>
						<td>${list.eAssetModel}</td>
						<td>${list.eEntryImportDate}</td>
						<td>${list.eEntryImporter}</td>
						<td onclick="event.cancelBubble = true;"> 
							<c:if test="${not empty list.eEntryImporter}">
						     ${list.eEntryImportDate}: 반입완료
							</c:if>
						</td>
						 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="tbl_paging">
			<span>
				<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList"/>
			</span>
		</div>
	</div>
    	<div class="tbl_bottom"> 
			<ul class="tbl_bottom_left">
				<li>
					<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
	              		<option value="10" <c:if test="${mesAssetVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesAssetVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesAssetVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesAssetVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	       			</select> 
       			</li>
			</ul> 	
			<ul class="tbl_bottom_right">
					<li>
<!-- 						<a onclick="formDownload()">양식 다운로드</a> --> 
					</li>
				<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
					<li>
<!-- 						<a style="cursor: pointer;" onclick="document.getElementById('managerFile').click();" >엑셀 등록</a> -->
<!-- 		    			<input id="managerFile" type="file"  style="display: none;" onchange="readExcel(event);"  accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/> -->
					</li>
					<li>
						<a onclick="go_insert()">반입 등록</a>
					</li>
				</c:if>
			</ul>
		</div>
</form>