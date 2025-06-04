<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script src="/js/jquery.table2excel.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
		// 달력 설정
		datepickerSet('topStartDate', 'topEndDate');
		datepickerIdSet("eSearchWordG");
		
		// 스타일 적용
		viewGubun();
		tdBlock(10);
		  $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
		  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '90px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '100px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '100px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '100px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '100px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '90px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '240px'); 
		  $('table[role="grid"].gridjs-table td:nth-child(8)').each(function() {
			    var content = $(this).html();
			    // <p>와 </p>를 제거
			    content = content.replace(/<p[^>]*>/g, '').replace(/<\/p>/g, '');
			    $(this).html(content);
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
		  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '120px'); 
		  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '120px'); 
		  $('table[role="grid"].gridjs-table td:nth-child(10)').each(function() {
			 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
			    $(this).css({
			        'white-space': 'nowrap',
			        'overflow': 'hidden',
			        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
			    });
			});
		  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '160px'); 
		  
		 
		  
	}); 
	
	// 현황 다운로드
	function excelDownload(){
		//var title = $(".content_tit h2").text();
		var title = "유지관리";
		var date = nowDate();
		$("#excelDownload").table2excel({ 
			// exclude CSS class 
			exclude:".noExl", 
			name:title+"1", 
			filename:date+"_"+title+".xls",//파일명 
			//fileext:".xls", // file 확장자 (예전버전이어야함)익스에서 작동안함 name에 바로 넣어야함
		    exclude_img: false, 
		    exclude_links: false,  
		    exclude_inputs: false 
		});
	}
	

	// 현재날짜
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}
	
	// 검색
	function fn_guestList(pageNo) {
		$("#mloader").show();
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "/mes/issue/kw_issue_lf.do";
		document.frm.submit();
	}
	
	// 등록
	function go_insert(){
		$("#mloader").show();
		document.frm.action = "/mes/issue/kw_issue_if.do";
		document.frm.submit();
	}
	
	// 검색 ENTER
	function fn_keyDown(){
		if(event.keyCode == 13){
			fn_guestList(1);
		}			
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
	}
	
	// 상세
	viewService.prototype.go_view = function(obj) {
		if(obj.childNodes[0].querySelector("input")){
			$("#mloader").show();
			$("#eIssueKey").val(obj.childNodes[0].querySelector("input").value);
			document.frm.action = "/mes/issue/kw_issue_vf.do";
			document.frm.submit();
		}
	}
	
	function requestSign(mMaintanceKey, sSignKey, gubun){
		$("#mloader").show();
		$("#gubun").val(gubun);
// 		$("#mMaintanceKey").val(mMaintanceKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/maintance/kw_maintance_r.do";
		document.frm.submit();
	}
	
	function selectName(selectElement, inputId) {
	    var selectedOption = selectElement.options[selectElement.selectedIndex];
	    var selectedValue = selectedOption.getAttribute('data-value2');
	    document.getElementById(inputId).value = selectedValue;
	    if(inputId == "mIssueTypeName"){
	    	IssueTypeView();
	    }
	    
// 	    fn_guestList(1);
	}
    function clearESearchWordG() {
//         document.getElementById("eSearchWordG").value = "";
    	 $("#eSearchWordG").val("");
    }
    function process_go(eIssueKey) {
    	$("#eIssueKey").val(eIssueKey);
    	$("#ePageGubun").val("Y");
    	document.frm.action = "/mes/issue/kw_issue_vf.do";
		document.frm.submit();
    }
    
    function startApproval(eIssueKey, sSignStatus){
    	$("#mloader").show();
    	$("#eIssueKey").val(eIssueKey);
    	$("#sSignStatus").val(sSignStatus);
    	document.frm.action = "/mes/issue/kw_issue_lfr.do";
    	document.frm.submit();
    }
    function excelDwonload(){
        document.frm.action = "/mes/issue/kw_issueExcelListDwonload.do";
        document.frm.submit();
        document.frm.action = "/mes/issue/kw_issue_lf.do";
    }
    
</script>

<style>
  .highlighted-row {
    background-color: #c0c0c0 !important;
     #tableContainer {
        width: 100%;
        table-layout: fixed; /* 고정 테이블 레이아웃 */
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
     .gridjs-table th {
    resize: none;
  }
}
</style>
<form id="frm" name="frm" method="post" >
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesIssueVO.pageIndex}" />
	
	<input type="hidden" id="gubun" name="gubun" value="N" />
	<input type="hidden" id="ePageGubun" name="ePageGubun" value="N" />
	<input type="hidden" id="eIssueKey" name="eIssueKey" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<input type="hidden" id="viewGubun" name="eViewGubun" value="${mesIssueVO.eViewGubun}" />
	
	<div class="content_top">	
		<div class="content_tit">
			<h2>장애관리</h2> 
		</div>
<%-- 	${staffVO.kAdminAuth } --%>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
	<!-- 				<li> -->
	<!-- 					<span>관리번호</span> -->
	<%-- 					<input type="text" id="eSearchWordA" name="eSearchWordA" style="width:150px;" class="searchWord" value="${mesIssueVO.eSearchWordA}" maxlength="30" /> --%>
	<!-- 				</li> -->
	   		 		<li>
						<span>자산유형</span>
						<input type="hidden" id="eSearchWordB" name="eSearchWordB" class="searchWord" value="${mesIssueVO.eSearchWordB}" maxlength="30" />
						<select id='eAssetType' name='eAssetType'  onchange="selectName(this,'eSearchWordB')">
							<option value='' <c:if test="${mesIssueVO.eSearchWordB eq ''}">selected="selected"</c:if>>전체</option>
							<c:forEach var='list' items='${gubun36List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${list.sGubunName eq mesIssueVO.eSearchWordB}">selected="selected"</c:if>>${list.sGubunName}</option>						
							</c:forEach>
						</select>
					</li>
					<li>
						<span>처리유형</span>
						<input type="hidden" id="eSearchWordC" name="eSearchWordC" class="searchWord" value="${mesIssueVO.eSearchWordC}" maxlength="30"  />
						<select id="eProcessingTypeName" name="eProcessingTypeName" onchange="selectName(this,'eSearchWordC')" >
							<option value="" <c:if test="${mesIssueVO.eSearchWordC eq ''}">selected="selected"</c:if>>전체</option>
								<c:forEach var="gubun33List" items="${gubun33List}">
									<option value='${gubun33List.sGubunKey}' data-value2='${gubun33List.sGubunName}' <c:if test="${gubun33List.sGubunName eq mesIssueVO.eSearchWordC}">selected="selected"</c:if>>${gubun33List.sGubunName}</option>							
								</c:forEach>		       				
			     			</select>
					</li>
					<li>
						<span>상세구분</span>
							<input type="hidden" id="eSearchWordD" name="eSearchWordD" value="${mesIssueVO.eSearchWordD}" maxlength="30" />
							<select id="eIssueType" name="eIssueType" onchange="selectName(this,'eSearchWordD')">
								<option value="" <c:if test="${mesIssueVO.eSearchWordG eq ''}">selected="selected"</c:if>>전체</option>
								<c:forEach var="gubun34List" items="${gubun34List}">
									<option value="${gubun34List.sGubunName}" data-value2='${gubun34List.sGubunName}' <c:if test="${gubun34List.sGubunName eq mesIssueVO.eSearchWordD}">selected="selected"</c:if>>${gubun34List.sGubunName}</option>	
														
								</c:forEach>	
							</select>
					</li>
					
					<li>
						<span>요청내용</span>
						<input type="text" id="eSearchWordE" name="eSearchWordE" value="${mesIssueVO.eSearchWordE}" maxlength="30" />
					</li>
					<li>
						 <span>요청일자</span>
						 <div class="date">
				       		<input type="text" name="topStartDate" id="topStartDate" value="${mesIssueVO.topStartDate}" readonly />
				           	- <input type="text" name="topEndDate" id="topEndDate" style="padding-left:2px;" value="${mesIssueVO.topEndDate}" readonly />
				         </div>
			     	</li>
	<!-- 		    	<li>	 -->
	<!-- 					<a style="cursor: pointer;" onclick="setStartDate(7)">1주일</a> -->
	<!-- 			    </li> -->
	<!-- 				<li>	 -->
	<!-- 					<a style="cursor: pointer;" onclick="setStartDate(1)">1개월</a> -->
	<!-- 			    </li> -->
	<!-- 				<li>	 -->
	<!-- 					<a style="cursor: pointer;" onclick="setStartDate(3)">3개월</a> -->
	<!-- 			    </li> -->
			    </ul>
			    <ul id="search_detail">
					<li>
						<span>요청자</span>
						<input type="text" id="eSearchWordH" name="eSearchWordH" value="${mesIssueVO.eSearchWordH}" maxlength="30" />
					</li>
					<li>
						<span>처리자</span>
						<input type="text" id="eSearchWordF" name="eSearchWordF" value="${mesIssueVO.eSearchWordF}" maxlength="30" />
					</li>
					<li>
						<span onclick="clearESearchWordG()">처리일자</span>
						<input type="text" id="eSearchWordG" name="eSearchWordG" value="${mesIssueVO.eSearchWordG}" maxlength="30"  class="inp_color" onchange="fn_guestList(1)" />
					</li>
				    <li class="info_txt">*처리일자 텍스트를 클릭하면 빈값 처리</li>
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
		<table id="myTable" >
			<thead>
				<tr>
					<th >No.</th>   
		      		<th >작성자</th>
		      		<th >자산유형</th>
		      		<th >처리유형</th>
		      		<th >상세구분</th>
		      		<th >요청일자</th>
		      		<th >요청자</th>
		      		<th >요청내용</th>
		      		<th >처리일자</th>
		      		<th >처리자</th>
		      		<th >상태</th>
		    	</tr>
			</thead>
	  		<tbody> 
				<c:forEach var="list" items="${infoList}" varStatus="i">
				<tr onclick="maintanceView('${list.eIssueKey}');">
					<td>
 						<c:if test="${list.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
						${paginationInfo.totalRecordCount - (mesIssueVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
						<input type="hidden" value="${list.eIssueKey}" />
					</td>
					<td>
						${list.kStaffName}
					</td>
					<td>
						${list.eAssetTypeName}
					</td>
					<td>
						${list.eProcessingTypeName}
					</td>
					<td>
						${list.eIssueTypeName}
					</td>
					<td>
						${list.eRequestDate}
					</td>
					<td>
						${list.eRequester}
					</td>
					<td>
						 <c:out value="${list.eIssueContent}" escapeXml="false" />
					</td>
					
					<td>
							${list.eActualWorkDate}  
					</td>
					<td>
						${list.eHandler}  
						<c:if test="${list.aAssetCost ne '0'}">
							외 ${list.aAssetCost}명 
						</c:if> 
					</td>
					<td onclick="event.cancelBubble = true;">
						<c:if test="${list.sSignStatus eq '승인'}">결재 완료</c:if>
						<c:if test="${list.sSignStatus eq '제외'}">결재 제외:</c:if>
						<c:if test="${list.sSignStatus eq '반려'}">${list.sSignStatus} </c:if>
			 			<c:if test="${list.sSignStatus eq '등록'}">
							<c:if test="${list.kStaffKey eq staffVO.kStaffKey}"><a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eIssueKey}','Y');">승인요청</a></c:if>
							<c:if test="${list.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
						</c:if> 
			 			<c:if test="${list.sSignStatus eq '승인요청'}">
							<c:if test="${list.kStaffKey eq staffVO.kStaffKey && list.sSignProgress eq '0'}">
								<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eIssueKey}','N');">요청취소</a>
							</c:if>
							<c:if test="${list.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
						</c:if>	 
						 <c:if test="${list.sSignStatus eq '제외' || list.sSignStatus eq '승인'}"> 
						 	<c:if test="${list.kStaffKey eq staffVO.kStaffKey || staffVO.kAdminAuth eq 'T'}">					 
							 	<c:if test="${list.eIssueStatus eq '요청등록'}">
									<a class="form_btn sm" onclick="process_go(${list.eIssueKey});">처리등록</a>
								</c:if>
							</c:if>
							<c:if test="${list.eIssueStatus eq '처리등록'}">${list.eIssueStatus}</c:if>
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
				<option value="20" <c:if test="${mesIssueVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
				<option value="50" <c:if test="${mesIssueVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
				<option value="100" <c:if test="${mesIssueVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
			</select> 
		</div>
	    <div class="paging">	
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
<!-- 			<li> -->
<!-- 	    		<a onclick="excelDownload();">액셀 다운로드</a> -->
<!-- 	    	</li> -->
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">		
				<button type="button" class="form_btn active" onclick="go_insert();">등록</button>
    		</c:if>
	    </div>
	</div>  
</form>