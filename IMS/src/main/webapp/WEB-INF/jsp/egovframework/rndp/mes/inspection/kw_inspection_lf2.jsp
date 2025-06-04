<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mermaid2.min.css" rel="stylesheet"	/>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"> 
$(function(){
	datepickerSet("eTopStartDate","eTopEndDate");
	$('#mloader').hide();
// 	datepickerIdSet("eSearchTypeSet4");
}); 

function fn_guestList(pageNo) {
// 	$('#mloader').show();
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/inspection/kw_inspection_lf.do";
	document.frm.submit();
}
 

function go_insert(){
// 	$("#mloader").show();
	document.frm.action = "/mes/inspection/kw_inspection_if.do";
	document.frm.submit();
}

function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}


function formDownload(){
    document.frm.action = "/mes/inspection/kw_assetExcelForm.do";
    document.frm.submit();
    document.frm.action = "/mes/inspection/kw_inspection_lf.do";
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
            		,	url    : "/mes/inspection/kw_uploadAssetAjax.do"
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
 
 tdBlock(9);
 
});


function eImport_go(eEntryExitItemKey){	
	var sUrl = "/mes/asset/kw_eImport_update_pop.do?eEntryExitItemKey="+eEntryExitItemKey;
	window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
	window.focus();
}


function eView(key){
	$("#mloader").show();
	document.frm.action = "/mes/inspection/kw_inspection_vf.do";
	document.frm.submit();
}


viewService.prototype.go_view = function(obj) {   
	if(obj.childNodes[0].querySelector("input")){  
		$("#mloader").show();
		$("#eInspectionKey").val(obj.childNodes[0].querySelector("input").value);
		document.frm.action = "/mes/inspection/kw_inspection_vf.do";
		document.frm.submit();
	}
}

function eInspectionResult(rowKey){
// 	$('#mloader').show();
	$('#eInspectionKey').val(rowKey);
	document.frm.action = "/mes/inspection/kw_inspection_Result.do";
	document.frm.submit(); 
}



$(document).ready(function() {
	 // No를 제외한 나머지 열의 너비를 150px로 설정-다른항목 한가지를 제외하고 공통으로 변경할때 -
	// 	  $('table[role="grid"].gridjs-table th:not(:nth-child(1))').css('width', '150px');
    // 그리드 헤더의 너비를 조정하는 스타일 변경
  $('gridjs-wrapper').css('overflow-y', 'hidden');  
  $('gridjs-tr').css('overflow-y', 'hidden');  
  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); // No.
  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '70px'); // 작성자
  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '70px'); // 점검구분
  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '70px'); // 점검주기
  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '70px'); // 점검일자
  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '120px'); // 점검자
  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '340px'); // 특이사항 
  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '240px'); // 상테
  tdBlock(7);
 
});

function selectName(selectElement, inputId) {
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var selectedValue = selectedOption.getAttribute('data-value2');
    document.getElementById(inputId).value = selectedValue;
    
//     fn_guestList(1);
}


function startApproval(eInspectionKey, sSignStatus){
	$("#mloader").show();
	$("#eInspectionKey").val(eInspectionKey);
	$("#sSignStatus").val(sSignStatus);
	document.frm.action = "/mes/inspection/kw_inspection_lfr.do";
	document.frm.submit();
}

function excelDwonload(){
    document.frm.action = "/mes/inspection/kw_inspectionExcelListDwonload.do";
    document.frm.submit();
    document.frm.action = "/mes/inspection/kw_inspection_lf.do";
}

</script>
  
<style>
  
</style>
<form name="frm" id="frm" method="post" >		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesInspectionVO.pageIndex}"/>
	<input type="hidden" id="eInspectionKey" name="eInspectionKey" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<div class="content_up">
		<div class="content_tit">
			<h2>점검관리 현황</h2>
		</div>		
		
		<div class="tbl_top"> 
			<ul class="tbl_top_left">
				
				<li>
					<span>점검구분</span>
					<input type="hidden" id="eSearchTypeSet1" name="eSearchTypeSet1" style="width:150px;" class="searchWord" value="${mesInspectionVO.eSearchTypeSet1}" maxlength="30"  />
							<select id='selectBox1' name='selectBox1' style="width: 100px;"  onchange="selectName(this,'eSearchTypeSet1')" >
								<option value='' data-value2=''>전체</option>
								<c:forEach var='list' items='${gubun38List}'>
									<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${list.sGubunName eq mesInspectionVO.eSearchTypeSet1}">selected="selected"</c:if> >${list.sGubunName}</option>
								</c:forEach>
							</select>
				</li>
				
				<li>
					<span>점검주기</span>
					<input type="hidden" id="eSearchTypeSet4" name="eSearchTypeSet4" style="width:100px;" class="inp_color"  value="${mesInspectionVO.eSearchTypeSet4}" readonly/>
						<select id='selectBox2' name='selectBox2' style="width: 100px;"  onchange="selectName(this,'eSearchTypeSet4')" >
							<option value='' data-value2=''>전체</option>
							<c:forEach var='list' items='${gubun39List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${list.sGubunName eq mesInspectionVO.eSearchTypeSet4}">selected="selected"  </c:if>>${list.sGubunName}</option>						
							</c:forEach>
						</select>
				</li>
			
				<li>
					<span>점검일자</span>
					<input type="text" id="eTopStartDate" name="eTopStartDate" style="width:100px;text-align: center;" class="inp_color"  value="${mesInspectionVO.eTopStartDate}" readonly />
		           	- <input type="text" id="eTopEndDate" name="eTopEndDate" style="padding-left:2px; width:100px;text-align: center;" class="inp_color"  value="${mesInspectionVO.eTopEndDate}" readonly />
					
				</li>
				<li style="    padding-left: 50px;">
					<a onclick="fn_guestList(1);" style="cursor: pointer;">
		    			검색
		     		</a>
				</li>
				<li>
					<a onclick="excelDwonload();" style="cursor: pointer;">
		    			현황 엑셀 다운로드
		     		</a>
				</li>
				
<!-- 				<li> -->
<!-- 					<a onclick="fn_search_detail();" style="cursor: pointer;"> -->
<!-- 		    			상세검색 -->
<!-- 		     		</a> -->
<!-- 				</li> -->
			</ul>
			<ul id="search_detail" style="display: none;">
				<li>
					<span>호스트명</span>
					<input type="text" id="eSearchTypeSet5" name="eSearchTypeSet5" style="width:100px;" value="${mesInspectionVO.eSearchTypeSet5}" maxlength="30" />
					
				</li>
				<li>
					<span>IP</span>
					<input type="text" id="eSearchTypeSet6" name="eSearchTypeSet6" style="width:100px;" value="${mesInspectionVO.eSearchTypeSet6}" maxlength="30" />
				</li>
				<li>
					<span>OS</span>
					<input type="text" id="eSearchTypeSet7" name="eSearchTypeSet7" style="width:150px;" class="searchWord" value="${mesInspectionVO.eSearchTypeSet7}" maxlength="30"  />
				</li>
 
			</ul>	
		</div>
	</div> 	
	
	<div id="tableContainer" class="lf_tbl_list"  >
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>작성자</th>
					<th>점검구분</th>
					<th>점검주기</th>
					<th>점검일자</th>
					<th>점검자</th>
					<th>특이사항</th>
					<th>상태</th>
<!-- 					<th>기관</th> -->
<!-- 					<th>요청자</th> -->
					
<!-- 					<th>점검결과</th> -->
<!-- 					<th>작성자</th> -->
<!-- 					<th>등록일</th> -->
				
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${infoList}" varStatus="i">
					<tr onclick="eView(<c:out value='${list.eInspectionKey}'/>);">
						<td>
							${paginationInfo.totalRecordCount - (mesInspectionVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eInspectionKey}" />
						</td>
						<td>${list.kStaffName}</td>
						<td>${list.eInspectionTypeName}</td>
						<td>${list.eInspectionCycleName}</td>
						<td>${list.eInspectionDate}</td>
						<td>${list.eInspector}</td>
						<td>${list.eRemark}</td>
						<td> 
							<c:if test="${list.sSignStatus eq '제외' || list.sSignStatus eq '승인' || list.sSignStatus eq '반려'}">결재 ${list.sSignStatus}:</c:if>
							<c:if test="${list.sSignStatus eq '등록'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey}"><a style="cursor: pointer;" class="mes_btn" onclick="startApproval('${list.eInspectionKey}','Y');">승인요청</a></c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
							</c:if>							
							<c:if test="${list.sSignStatus eq '승인요청'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey && list.sSignProgress eq '0'}">
									<a style="cursor: pointer;" class="mes_btn" onclick="startApproval('${list.eInspectionKey}','N');">요청취소</a>
								</c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
							</c:if>
							<c:if test="${list.sSignStatus eq '승인' || list.sSignStatus eq '제외'}">
								<c:if test="${list.eStatus eq '등록'}"> 
									<a style="cursor: pointer;" class="mes_btn"  onclick="eInspectionResult('${list.eInspectionKey}');">점검결과 등록</a>
								</c:if> 
								<c:if test="${list.eStatus eq '점검결과등록'}">
									${list.eStatus}완료
								</c:if>
							</c:if>
						</td>
<%-- 						<td>${list.eOrganization}<c:if test="${not empty list.eDepartment}">:${list.eDepartment}</c:if>	</td> --%>
<%-- 						<td>${list.eRequester}</td> --%>
<%-- 						<td>${list.eInspectionResult}</td> --%>
<%-- 						<td>${list.eRegistrant}</td> --%>
<%-- 						<td>${list.eRegistrationDate}</td> --%>
						
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
	              		<option value="20" <c:if test="${mesInspectionVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesInspectionVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesInspectionVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
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
						<a style="cursor: pointer;" class="mes_btn" onclick="go_insert()">점검 등록</a>
					</li>
				</c:if>
			</ul>
		</div>
</form>