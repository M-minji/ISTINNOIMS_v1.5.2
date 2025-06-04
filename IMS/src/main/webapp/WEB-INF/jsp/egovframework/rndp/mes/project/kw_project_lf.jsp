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
<script src="/js/xlsx.full.min.js"></script>
<script src="/js/papaparse.min.js"></script>
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
	  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); //         <th >NO.</th>
	  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); //       	<th >작성자</th>
	  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '120px'); //       	<th >작성일</th>
	  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '160px'); //       	<th >사업명</th>
	  $('table[role="grid"].gridjs-table td:nth-child(4)').each(function() {
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '190px'); //     	<th >사업기간</th>
	  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '100px'); //    	<th >담당자</th>
	  $('table[role="grid"].gridjs-table td:nth-child(6)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '140px'); //       	<th >담당자연락처</th>
	  $('table[role="grid"].gridjs-table td:nth-child(7)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-align': 'left !important',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '120px'); //       	<th >담당자메일</th>
	  $('table[role="grid"].gridjs-table td:nth-child(8)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-align': 'left !important',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '120px'); //       	<th >주사업자</th>
	  $('table[role="grid"].gridjs-table td:nth-child(9)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '100px'); //       	<th >사업PM</th>
	  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '160px'); //       	<th >PM연락처</th>
	  $('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '180px'); //     	<th >PM이메일</th>
	  $('table[role="grid"].gridjs-table th:nth-child(13)').css('width', '180px'); //      	<th >비고</th>
	  $('table[role="grid"].gridjs-table td:nth-child(13)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
	  $('table[role="grid"].gridjs-table th:nth-child(14)').css('width', '240px'); //      	<th >진행상태</th>
 
	  tdBlock(13);
	  
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
// 	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.submit();
}

// 등록
function go_insert(){
// 	$("#mloader").show();
	document.frm.action = "/mes/project/kw_project_if.do";
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
		$("#eProjectNum").val(obj.childNodes[0].querySelector("input").value);
		document.frm.action = "/mes/project/kw_project_vf.do";
		document.frm.submit();
	}
}

//처리내역 등록 
function process_go(key){
// 	$("#mloader").show();
	$("#pProjectkey").val(key);
	document.frm.action = "/mes/project/kw_project_process_if.do";
	document.frm.submit();
}

//상세 등록 
function maintanceView(key){
// 	$("#mloader").show();
	$("#eProjectNum").val(key);
	document.frm.action = "/mes/project/kw_project_vf.do";
	document.frm.submit();
}

function requestSign(pProjectkey, sSignKey, gubun){
// 	$("#mloader").show();
	$("#gubun").val(gubun);
	$("#pProjectkey").val(pProjectkey);
	$("#sSignKey").val(sSignKey);
	document.frm.action = "/mes/project/kw_project_process_r.do";
	document.frm.submit();
}

//js기반 엑셀다운로드
function excelDown(){
	//var title = $(".content_tit h2").text();
	var title = "프로젝트회의록관리";
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

function movePage(pProjectkey){
	$("#eProjectNum").val(pProjectkey);
	document.frm.action = "/mes/output/kw_eDeliverable_insert.do";
	document.frm.submit();
} 



function startApproval(eProjectNum, sSignStatus){
// 	$("#mloader").show();
	$("#eProjectNum").val(eProjectNum);
	$("#sSignStatus").val(sSignStatus);
	document.frm.action = "/mes/project/kw_project_lfr.do";
	document.frm.submit();
}


function excelDwonload(){
    document.frm.action = "/mes/project/kw_projectExcelListDwonload.do";
    document.frm.submit();
    document.frm.action = "/mes/project/kw_project_lf.do";
}

</script>
<form id="frm" name="frm" method="post" action="/mes/project/kw_project_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesProjectVO.pageIndex}">
	 
	<input type="hidden" id="eProjectNum" name="eProjectNum" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<input type="hidden" id="gubun" name="gubun" value="" />
	 
	<div class="content_top">
		<div class="content_tit">
			<h2>프로젝트관리</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>사업명</span>
						<input name="eSearchWord1" type="text" id="eSearchWord1" value="${mesProjectVO.eSearchWord1}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="30" />
					</li>
					<li>
		           		<span>사업시행연도</span>
			       		<input type="text" id="eStartDate" name="eStartDate" style="width:100px;" class="inp_color"  value="${mesProjectVO.eStartDate}" readonly />
	<%-- 		           	~ <input type="text" id="eEndDate" name="eEndDate" class="inp_color"  style="width:100px;text-align: center;" value="${mesProjectVO.eEndDate}" readonly /> --%>
					</li>
					<li>
						<span>담당자</span>
						<input name="eSearchWord2" type="text" id="eSearchWord2" value="${mesProjectVO.eSearchWord2}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="30" />
					</li>
					<li>
						<span>사업 PM</span>
						<input name="eSearchWord3" type="text"  id="eSearchWord3" value="${mesProjectVO.eSearchWord3}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="30" />
					</li>
	<!-- 				<li> 진행상태: -->
	<%-- 					<input name="eSearchWord4" type="text" style="width:150px;"  id="eSearchWord4" value="${mesProjectVO.eSearchWord4}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}" maxlength="50" /> --%>
	<!-- 				</li> -->
					<li>	
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn ico_excel" onclick="excelDwonload();">엑셀 다운로드</button>
	     		<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>
	
	<div class="data_table">
		<table id="myTable" >
			<thead>
				<tr>
			    	<th >NO.</th>
			    	<th >작성자</th>
			    	<th >등록일</th>
			    	<th >사업명</th>
			    	<th >사업기간</th>
			    	<th >담당자</th>
			      	<th >담당자연락처</th>
			      	<th >담당자메일</th>
			      	<th >주사업자</th>
			      	<th >사업PM</th>
			      	<th >PM연락처</th>
			      	<th >PM이메일</th>
					<th >비고</th>
			      	<th >진행상태</th>
				</tr>
			</thead>
			<tbody> 
 				<c:forEach var="projectList" items="${projectList}" varStatus="i"> 
 					<tr style="cursor: pointer;" onclick="incomView('${projectList.eProjectNum}');"> 
 						<td style="text-align:left;"> 
 							<c:if test="${projectList.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
 							${paginationInfo.totalRecordCount - (mesProjectVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index} 
 							<input type="hidden" value="${projectList.eProjectNum}" /> 
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${projectList.kStaffName}" />
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${projectList.pWdate}" />
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${projectList.eProjectName}" />
 						</td> 
 						<td style="text-align:left;"> 
 							${projectList.eStartDate} - ${projectList.eEndDate}
 						</td> 
 						<td style="text-align:left;"> 
 							<c:out value="${projectList.eManager}" /> 
 						</td> 
 						<td > 
 							<c:out value="${projectList.eManagerContact}" /> 
 						</td> 
 						<td >
 							<c:out value="${projectList.eManagerEmail}" /> 
 						</td>
 						<td style="text-align:left;"> 
							<c:out value="${projectList.eMainContractor}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${projectList.eProjectPM}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${projectList.ePMContact}" /> 
						</td>
 						<td style="text-align:left;"> 
							<c:out value="${projectList.ePMEmail}" /> 
						</td>
 						<td style="text-align:left;"> 
							 ${projectList.eRemarks} 
						</td>
						<td onclick="event.cancelBubble = true;">
							<c:if test="${projectList.sSignStatus eq '제외' || projectList.sSignStatus eq '승인' || projectList.sSignStatus eq '반려'}">결재 ${projectList.sSignStatus}:</c:if>
							<c:if test="${projectList.sSignStatus eq '등록'}">
								<c:if test="${projectList.kStaffKey eq staffVO.kStaffKey}">
									<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${projectList.eProjectNum}','Y');">승인요청</a>
								</c:if>
								<c:if test="${projectList.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
							</c:if>							
							<c:if test="${projectList.sSignStatus eq '승인요청'}">
								<c:if test="${projectList.kStaffKey eq staffVO.kStaffKey && projectList.sSignProgress eq '0'}">
									<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${projectList.eProjectNum}','N');">요청취소</a>
								</c:if>
								<c:if test="${projectList.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
							</c:if>
							<c:if test="${projectList.sSignStatus eq '승인' || projectList.sSignStatus eq '제외'}">
								<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">	
									<c:if test="${projectList.kStaffKey eq staffVO.kStaffKey}">
										<a class="form_btn sm" onclick="movePage(${projectList.eProjectNum});">산출물 등록</a>
									</c:if>
								</c:if> 
							</c:if>
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
			<!--  <button type="button" class="form_btn ico_excel" onclick="excelDown();">엑셀 다운로드</button>  -->
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">		
			<button type="button" class="form_btn active" onclick="go_insert();">등록</button>
			</c:if>
		</div>
	</div>
</form>