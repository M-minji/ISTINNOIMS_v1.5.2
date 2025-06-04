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
	$('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	datepickerSet('topStartDate', 'topEndDate');
}); 


function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/asset/kw_Software_Register_lf.do";
	document.listForm.submit();
}

viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){
		$("#mloader").show();
		$("#eSWRegisterKey").val(obj.childNodes[0].querySelector("input").value);
		document.listForm.action = "/mes/asset/kw_Software_Register_vf.do";
		document.listForm.submit();
	}
}
function go_insert(){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_Software_Register_if.do";
	document.listForm.submit();
}
function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}


function formDownload(){
    document.listForm.action = "/mes/asset/kw_assetExcelFormSample.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_Software_Register_lf.do";
}

function excelDwonload(){
    document.listForm.action = "/mes/asset/kw_assetExcelListDwonload.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_Software_Register_lf.do";
}

function readExcel(e) {
	$('#mloader').show();
	var checkCnt = 0;
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
            		,	url    : "/mes/asset/kw_eAssetSWinsertAjax.do"
            		,	dataType : "json"
            		,	async: false
                 	, 	data: {
                 		 eManufacturer: ConverttoHtml(arr[i][0] || ""),
                         eProductName: ConverttoHtml(arr[i][1] || ""),
                         eVersion: ConverttoHtml(arr[i][2] || ""),
                         ePurchaseDate: arr[i][3] || "",
                         eStartDate: arr[i][4] || "",
                         eEndDate: arr[i][5] || "",
                         eLicenseQuantity: arr[i][6] || "",
                         eRemarks: ConverttoHtml(arr[i][7] || ""),
                         eAuthor: ConverttoHtml(arr[i][8] || ""),
                         aAssetDate: arr[i][9] || ""
	                 	}
            		,	success:function(msg){
            			checkCnt++;
//             				console.log("성공");
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
    
    $('#mloader').hide();
    fn_guestList(1); 
    reader.readAsBinaryString(input.files[0]);
    
    e.target.value = ''; 
}

$(document).ready(function() {
 	//$('table[role="grid"].gridjs-table td[data-column-id="수량"]').css('text-align', 'right');
	//$('table[role="grid"].gridjs-table td[data-column-id="사용수량"]').css('text-align', 'right');
	
	  $('gridjs-wrapper').css('overflow-y', 'hidden');  
	  $('gridjs-tr').css('overflow-y', 'hidden');  
	  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); // No.
	  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); // 작성자
	  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '140px'); // 제조사
	  $('table[role="grid"].gridjs-table td:nth-child(3)').each(function() {
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '200px'); // 라이선스명
	  $('table[role="grid"].gridjs-table td:nth-child(4)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '80px'); // 버전
	  $('table[role="grid"].gridjs-table td:nth-child(5)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '100px'); // 구매일
	  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '100px'); // 시작일
	  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '100px'); // 종료일
	  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '120px'); // 유효기간
	  $('table[role="grid"].gridjs-table td:nth-child(9)').each(function() {
			    $(this).css({
			    	  "cursor": "default"
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '80px'); // 수량
	  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '80px'); // 사용수량
	  //$('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '260px'); // 비고
	  $('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '120px'); // 상태
	  tdBlock(8);
	  tdBlock(12);
	  $('#mloader').hide();
  
 
});
function dataUpdate(key){	
	var sUrl = "/mes/asset/kw_Software_update_pop.do?eSWRegisterKey="+key;
	window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
	window.focus();
}
function startApproval(eSWRegisterKey, eStatus){
	$("#mloader").show();
	$("#eSWRegisterKey").val(eSWRegisterKey);
	$("#eStatus").val(eStatus);
	document.listForm.action = "/mes/asset/kw_Software_Register_lfr.do";
	document.listForm.submit();
}
</script>
  
<style>
  .highlighted-row {
    background-color: #c0c0c0 !important;
     #tableContainer {
        width: 100%;
        overflow-x: auto; /* 가로 스크롤 추가 */
    }

    #myTable {
        width: 100%;
        border-collapse: collapse;
        table-layout: fixed; /* 고정 테이블 레이아웃 */
    }

    #myTable th, #myTable td {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #myTable th {
        background-color: #f2f2f2;
        text-align: left;
    }
}
</style>
<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_Software_Register_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="eSWRegisterKey" name="eSWRegisterKey" value="" />
	<input type="hidden" id="eStatus" name="eStatus" value="" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>소프트웨어 라이선스 관리</h2>
		</div>		
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>제조사</span>
						<input type="text" id="searchTypeSet1" name="searchTypeSet1" class="searchWord" value="${mesAssetVO.searchTypeSet1}" maxlength="30" />
					</li>
					<li>
						<span>라이선스명</span>
						<input type="text" id="searchTypeSet2" name="searchTypeSet2" class="searchWord" value="${mesAssetVO.searchTypeSet2}"  maxlength="30" />
					</li>
					<li>
						<span>유효기간</span>
						<input type="text" id="searchTypeSet3" name="searchTypeSet3" class="searchWord" value="${mesAssetVO.searchTypeSet3}" placeholder="숫자_미만값조회"  maxlength="5" />
					</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn ico_excel" onclick="excelDwonload();">엑셀 다운로드</button>
	     		<button type="button" class="form_btn bg" onclick="fn_guestList(1);">검색</button>
			</div>
		</div>
	</div>
		
	<div class="data_table">
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>작성자</th>
					<th>제조사</th>
					<th>라이선스명</th>
					<th>버전</th>
					<th>구매일</th>
					<th>시작일</th>
					<th>종료일</th>
					<th>유효기간</th>
					<th>수량</th>
					<th>사용수량</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr onclick="assetView(<c:out value='${list.eSWRegisterKey}'/>);">
						<td>
							<c:if test="${list.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eSWRegisterKey}" />
						</td>
						<td>
							${list.kStaffName}
						</td>
						<td>
							${list.eManufacturer}
						</td>
						<td>
							${list.eProductName}
						</td>
						<td>
							${list.eVersion}
						</td>
						<td style="text-align: center; ">
							${list.ePurchaseDate}
						</td>
						<td style="text-align: center; ">
							${list.eStartDate}
						</td>
						<td style="text-align: center; ">
							${list.eEndDate}
						</td>
						<td>
							${list.eValidityPeriod}
							<c:if test="${list.eValidityPeriod eq '만료' && staffVO.kStaffAuthWriteFlag eq 'T'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey}">
							 		<a class="form_btn sm" onclick="dataUpdate(${list.eSWRegisterKey});">만료일 갱신</a>
						 		</c:if>
							</c:if>
						</td>
						<td>
							${list.eLicenseQuantity}
						</td>
						<td>
							${list.eUsedLicenseQuantity}
						</td>
						<!--  <td>
							${list.eRemarks}
						</td> -->
						<td>  
							<c:if test="${list.eStatus eq '제외'}">결재 제외</c:if>
							<c:if test="${list.eStatus eq '승인' || list.eStatus eq '반려'}">${list.eStatus}</c:if>
							<c:if test="${list.eStatus eq '등록'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey}"><a style="cursor: pointer;" class="form_btn sm" onclick="event.stopPropagation(); startApproval('${list.eSWRegisterKey}','Y');">승인요청</a></c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
							</c:if>
							
							<c:if test="${list.eStatus eq '승인요청'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey && list.sSignProgress eq '0'}">
									<a style="cursor: pointer;" class="form_btn sm" onclick="event.stopPropagation(); startApproval('${list.eSWRegisterKey}','N');">요청취소</a>
								</c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
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
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
			<button type="button" class="form_btn active" onclick="go_insert()">라이선스정보 등록</button>
			</c:if>
		</div>
	</div>
</form>