<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script src="/js/jquery.table2excel.js"></script>
<style id="compiled-css" type="text/css">
	.ui-datepicker-calendar {
	   display: none;
	}
	.ui-datepicker-month {
	   display: none;
	}
	.ui-datepicker-prev{
	   display: none;
	}
	.ui-datepicker-next{
	   display: none;
	}
	button.ui-datepicker-current{
		display: none;
	}
	.tbl_list {  height: auto;}
</style>
<script type="text/javascript">

$(document).ready(function(){	
// 	datepickerSet('eStartDate','eEndDate');
	  $('gridjs-wrapper').css('overflow-y', 'hidden');  
	  $('gridjs-tr').css('overflow-y', 'hidden');  
	  $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '80px'); //       <th >NO.</th>    
	  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); //       <th >사업명</th>    
	  $('table[role="grid"].gridjs-table td:nth-child(2)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '155px'); //     <th >사업기간</th>   
	  $('table[role="grid"].gridjs-table th:nth-child(4)').css("width", "135px"); //    <th >담당자</th>    산출물
	  $('table[role="grid"].gridjs-table td:nth-child(4)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
		    	    "cursor": "default",
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(5)').css("width", "100px"); //       <th >담당자연락처</th> >  보고서
	  $('table[role="grid"].gridjs-table td:nth-child(5)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			    	  "cursor": "default",
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '80px'); //       <th >담당자메일</th>  담당자
	  $('table[role="grid"].gridjs-table td:nth-child(6)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '100px'); //       <th >주사업자</th>   담연
	  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '100px'); //       <th >사업PM</th>   담메
	  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '100px'); //       <th >PM연락처</th>  주사
	  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '80px'); //     <th >PM이메일</th>  사p
	  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '100px'); //      <th >진행상태</th>   p연
	  $('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '100px'); //      <th >산출물</th>     pm이메일?
	  $('table[role="grid"].gridjs-table th:nth-child(13)').css('width', '80px'); //       <th >보고서</th>    진행상태
	tdBlock(3);                                                                           
	tdBlock(4);      
	
	$('#eStartDate').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'yy',
        closeText: '선택',
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1));
        }
    });
  
}); 	

// 현재날짜
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}

// ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

// 검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.submit();
}

// 등록
function go_insert(){
	$("#mloader").show();
	document.frm.action = "/mes/output/kw_output_if.do";
	document.frm.submit();
}

//회으록등록
function go_insert2(){
	$("#mloader").show();
	document.frm.action = "/mes/output/kw_meeting_if.do";
	document.frm.submit();
}


function setStartDate(d) {
    var settingDate = new Date();
    if(d == '7'){
        settingDate.setDate(settingDate.getDate()-7);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else if(d == '1'){
        settingDate.setMonth(settingDate.getMonth()-1);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else{
        settingDate.setMonth(settingDate.getMonth()-3);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }
    fn_guestList(1);
}

// 상세
viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){
		$("#mloader").show();
		$("#eProjectNum").val(obj.childNodes[0].querySelector("input").value);
		document.frm.action = "/mes/project/kw_project_vf.do";
		document.frm.submit();
	}
}

//처리내역 등록 
function process_go(key){
	$("#mloader").show();
	$("#outputKey").val(key);
	document.frm.action = "/mes/output/kw_output_process_if.do";
	document.frm.submit();
}

//상세 등록 
function maintanceView(key){
	$("#mloader").show();
	$("#outputKey").val(key);
	document.frm.action = "/mes/output/kw_output_vf.do";
	document.frm.submit();
}

function requestSign(outputKey, sSignKey, gubun){
	$("#mloader").show();
	$("#gubun").val(gubun);
	$("#outputKey").val(outputKey);
	$("#sSignKey").val(sSignKey);
	document.frm.action = "/mes/output/kw_output_process_r.do";
	document.frm.submit();
}

//js기반 엑셀다운로드
function excelDown(){
	//var title = $(".content_tit h2").text();
	var title = "산출물관리";
	var date = nowDate();
	$("#excelDownload").table2excel({ 
		// exclude CSS class 
		exclude:".noExl", 
		name:title+"1", 
		filename:date+"_"+title+".xls",//파일명 
		//fileext:".xls", // file 확장자 (예전버전이어야함)익스에서 작동안함 name에 바로 넣어야함
	    exclude_img: false, 
	    exclude_links: false, //a태그 버튼 안없어짐 ??? 원인 파악 불가
	    exclude_inputs: false //hidden 제거 
	});
}
function eDeliverable_update(eProjectNum){
	$("#eProjectNum").val(eProjectNum);
	document.frm.action = "/mes/output/kw_eDeliverable_insert.do";
	document.frm.submit();
}
function eReport_update(eProjectNum){
	$("#eProjectNum").val(eProjectNum);
	document.frm.action = "/mes/output/kw_eReport_insert.do";
	document.frm.submit();
}
 
</script>

<form id="frm" name="frm" method="post" action="/mes/output/kw_output_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesOutputVO.pageIndex}">
	 
	<input type="hidden" id="outputKey" name="outputKey" value="" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="" />
  	<input type="hidden" id="gubun" name="gubun" value="" />
  	<input type="hidden" id="eProjectNum" name="eProjectNum" value="" />
	 
	<div class="content_top">
		<div class="content_tit">
			<h2>산출물관리</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>사업명</span>
						<input name="eSearchWord1" type="text" id="eSearchWord1" value="${mesOutputVO.eSearchWord1}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="50" />
					</li>
					<li>
		           		<span>사업시행연도</span>
			       		<input type="text" id="eStartDate" name="eStartDate" class="inp_color" style="width:100px;" value="${mesOutputVO.eStartDate}" readonly />
	<%-- 		           	~ <input type="text" id="eEndDate" name="eEndDate" class="inp_color"  style="width:100px; text-align: center;" value="${mesOutputVO.eEndDate}" readonly /> --%>
					</li>
					<li>
						<span>담당자</span>
						<input name="eSearchWord2" type="text" id="eSearchWord2" value="${mesOutputVO.eSearchWord2}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="50" />
					</li>
					<li>
						<span>사업 PM</span>
						<input name="eSearchWord3" type="text" id="eSearchWord3" value="${mesOutputVO.eSearchWord3}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="50" />
					</li>
	<!-- 				<li> 진행상태: -->
	<%-- 					<input name="eSearchWord4" type="text" style="width:150px;"  id="eSearchWord4" value="${mesOutputVO.eSearchWord4}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="50" /> --%>
	<!-- 				</li> -->
					<li>	
				</ul>
			</div>
			<div class="button_wrap">
	     		<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>
	
	<div  class="data_table">
		<table id="myTable" >
			<thead>
				<tr>
			    	<th >NO.</th>
			    	<th >사업명</th>
			    	<th >사업기간</th>
			      	<th >산출물</th>
			      	<th >보고서</th>
			    	<th >담당자</th>
			      	<th >담당자연락처</th>
			      	<th >담당자메일</th>
			      	<th >주사업자</th>
			      	<th >사업PM</th>
			      	<th >PM연락처</th>
			      	<th >PM이메일</th>
			      	<th >진행상태</th>
				</tr>
			</thead>
			<tbody> 
 				<c:forEach var="outputList" items="${outputList}" varStatus="i"> 
 					<tr onclick="incomView('${outputList.eProjectNum}');"> 
 						<td  style="text-align:left;"> 
 							${paginationInfo.totalRecordCount - (mesOutputVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index} 
 							<input type="hidden" value="${outputList.eProjectNum}" /> 
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${outputList.eProjectName}" />
 						</td> 
 						<td style="text-align:left;"> 
 							${outputList.eStartDate} - ${outputList.eEndDate}
 						</td> 
						<td onclick="event.cancelBubble = true;"> 
							<c:if test="${outputList.sSignStatus eq '제외' || outputList.sSignStatus eq '승인' || outputList.sSignStatus eq '반려'}">결재 ${outputList.sSignStatus}:</c:if>
							<c:if test="${outputList.sSignStatus eq '등록'}">
								 결재 준비 
							</c:if>	
							<c:if test="${outputList.sSignStatus eq '승인' || outputList.sSignStatus eq '제외'}">
								<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">			
									<c:if test="${outputList.kStaffKey eq staffVO.kStaffKey}">
			 							<a class="form_btn sm" onclick="eDeliverable_update(${outputList.eProjectNum});">산출물등록</a> 
		 							</c:if>
	 							</c:if> 
							</c:if>
						</td>
 						<td onclick="event.cancelBubble = true;">
 						<c:if test="${outputList.sSignStatus eq '승인' || outputList.sSignStatus eq '제외'}">
 							<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">		
 								<c:if test="${outputList.kStaffKey eq staffVO.kStaffKey}">
 								<a class="form_btn sm" onclick="eReport_update(${outputList.eProjectNum});">보고서 등록</a> 
 								</c:if>
 							 </c:if>
						</c:if>
						</td>
 						<td style="text-align:left;"> 
 							<c:out value="${outputList.eManager}" /> 
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${outputList.eManagerContact}" /> 
 						</td> 
 						<td style="text-align:left;">
 							<c:out value="${outputList.eManagerEmail}" /> 
 						</td>
 						<td style="text-align:left;"> 
							<c:out value="${outputList.eMainContractor}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${outputList.eProjectPM}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${outputList.ePMContact}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${outputList.ePMEmail}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${outputList.eProjectStatus}" /> 
						</td>
					</tr>
				</c:forEach> 	
	  		</tbody>
		</table>
	</div>
	
	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
	        	<option value="10" <c:if test="${paginationInfo.recordCountPerPage eq 10}">selected="selected"</c:if>>10개씩 보기</option>
	       		<option value="20" <c:if test="${paginationInfo.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
	       		<option value="50" <c:if test="${paginationInfo.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
	       		<option value="100" <c:if test="${paginationInfo.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
			</select> 
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
			<p class="txt_info">프로젝트 정보는 프로젝트관리에서 등록/수정 가능합니다.</p>
		</div>
	</div>
</form>