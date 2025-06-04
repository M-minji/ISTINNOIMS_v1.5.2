<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mermaid2.min.css" rel="stylesheet"	/>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script src="/js/jquery.table2excel.js"></script>

<script type="text/javascript">
$(document).ready(function(){
		datepickerSet('topStartDate', 'topEndDate');
		datepickerIdSet("eSearchWordG");
		tdBlock(10);
	    viewGubun();
	}); 
	
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
		    exclude_links: false, //a태그 버튼 안없어짐 ??? 원인 파악 불가
		    exclude_inputs: false //hidden 제거 
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
		document.frm.action = "/mes/maintance/kw_maintance_lf.do";
		document.frm.submit();
	}
	
	// 등록
	function go_insert(){
		$("#mloader").show();
		document.frm.action = "/mes/maintance/kw_maintance_if.do";
// 		document.frm.action = "/mes/maintance/kw_process_if.do";
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
			$("#mMaintanceKey").val(obj.childNodes[0].querySelector("input").value);
			document.frm.action = "/mes/maintance/kw_maintance_vf.do";
			document.frm.submit();
		}
	}
	// 처리내역 등록 
	function process_go(key){  
		$("#mloader").show();
		$("#mMaintanceKey").val(key);
		document.frm.action = "/mes/maintance/kw_process_if.do";
		document.frm.submit();
	}
	
	// 상세 등록 
	function maintanceView(key){
		$("#mloader").show();
		$("#mMaintanceKey").val(key);
		document.frm.action = "/mes/maintance/kw_maintance_vf.do";
		document.frm.submit();
	}
	
	
	
	function requestSign(mMaintanceKey, sSignKey, gubun){
		$("#mloader").show();
		$("#gubun").val(gubun);
		$("#mMaintanceKey").val(mMaintanceKey);
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
	    
	    fn_guestList(1);
	}
	
</script>
<style>
	/* 단일일경우 :nth-child(n)*/
	/* 해당하는 숫자부터 연속으로 이어짐  */
	td.gridjs-td:nth-child(n+6):nth-child(-n+7) {text-align:center !important;} 
</style>

<form id="frm" name="frm" method="post" action="/mes/maintance/kw_maintance_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesMaintanceVO.pageIndex}" />
	<input type="hidden" id="viewGubun" name="viewGubun" value="${mesMaintanceVO.viewGubun}" />
	
	<input type="hidden" id="mMaintanceKey" name="mMaintanceKey" value="" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="" />
	<input type="hidden" id="gubun" name="gubun" value="" />
	
	<div class="content_up">	
		<div class="content_tit">
			<h2>유지관리 등록</h2>
		</div>
	
		<div class="tbl_top"> 
			<ul class="tbl_top_left" > 
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
		       		<input type="text" name="topStartDate" id="topStartDate" value="${mesMaintanceVO.topStartDate}"  style="width:100px;text-align: center;" readonly  class="inp_color"/>
		           	~ <input type="text" name="topEndDate" id="topEndDate" value="${mesMaintanceVO.topEndDate}"  style="width:100px;text-align: center;" readonly class="inp_color" />
		     	</li>
				<li>	
		     		<a onclick="fn_guestList(1)">검색</a>
		    	</li>
		    	<li>
					<a onclick="fn_search_detail();" style="cursor: pointer;">
		    			상세검색
		     		</a>
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
		    <ul id="search_detail" style="display: none;">
				<li>
					<span>요청내용</span>
					<input type="text" id="eSearchWordE" name="eSearchWordE" style="width:100px;" value="${mesAssetVO.eSearchWordE}" maxlength="30" />
				</li>
				<li>
					<span>작업자</span>
					<input type="text" id="eSearchWordF" name="eSearchWordF" style="width:100px;" value="${mesAssetVO.eSearchWordF}" maxlength="30" />
				</li>
				<li>
					<span>처리일자</span>
					<input type="text" id="eSearchWordG" name="eSearchWordG" style="width:100px;" value="${mesAssetVO.eSearchWordG}" maxlength="30" style="pointer-events: none;"  class="inp_color" onchange="fn_guestList(1)" />
				</li>
				 
			</ul>	
		</div>
	</div>

	<div id="tableContainer" class="lf_tbl_list">
		<table id="myTable" style="width:100%; height:500px; overflow:auto;">
			<thead>
				<tr>
		      		<th >관리 번호</th>
		      		<th >자산유형</th>
		      		<th >처리유형</th>
		      		<th >요청기관</th>
		      		<th >요청일자</th>
		      		<th >요청내용</th>
		      		<th >처리일자</th>
		      		<th >처리내용</th>
		      		<th >작업자</th>
		      		<th >장애구분</th>
		      		<th >진행상태</th>
		    	</tr>
			</thead>
	  		<tbody> 
				<c:forEach var="list" items="${maintanceList}" varStatus="i">
					<tr >
						<td>
<%-- 							${paginationInfo.totalRecordCount - (mesMaintanceVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index} --%>
							${list.mMaintanceNumber}
							<input type="hidden" value="${list.mMaintanceKey}" />
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
						<td style="text-align:left; padding-left:5px;">
							${list.mRequestDate}
						</td>
						<td style="text-align:center;">
							${list.mMaintanceContent}
						</td>
						<td style="text-align:center;">
							${list.mProcessingDate}
						</td>
						<td style="text-align:center;">
							${list.mProcessDate}
						</td>
						<td style="text-align:left; padding-left:5px;">
							${list.mHandler}
						</td>
						<td>
							${list.mIssueTypeName}
						</td>
						<td onclick="event.cancelBubble = true;">
							<c:if test="${list.sSignStatus eq '요청등록'}">
								<a class="mes_btn" onclick="process_go(${list.mMaintanceKey});">처리등록</a>
							</c:if>
							<c:if test="${list.sSignStatus eq '처리등록' || list.sSignStatus eq '반려'}">
									<a class="mes_btn" onclick="requestSign(${list.mMaintanceKey}, ${list.sSignKey},'요청');">승인요청</a>
							</c:if>
							<c:if test="${list.sSignStatus eq '승인요청'}">
								<c:if test="${list.sSignStaffKey ne staffVo.kStaffKey}">
									승인 요청중
									<a class="mes_btn" onclick="requestSign(${list.mMaintanceKey}, ${list.sSignKey},'취소');">승인요청취소</a>
								</c:if>
								<c:if test="${list.sSignStaffKey eq staffVo.kStaffKey}">
									<a class="mes_btn" onclick="maintanceView(${list.mMaintanceKey});">결재처리</a>
								</c:if>
							</c:if>
							<c:if test="${list.sSignStatus eq '승인'}">
								승인 완료
							</c:if>
							<c:if test="${list.sSignStatus eq '결재제외'}">
								결제 제외
							</c:if>
						</td>
					</tr>
				</c:forEach>
	       	</tbody>
		</table>
  	</div>
  	
	<div class="tbl_bottom">
		<ul class="tbl_bottom_left" >
			<li>
          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
              		<option value="20" <c:if test="${mesMaintanceVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
              		<option value="50" <c:if test="${mesMaintanceVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
              		<option value="100" <c:if test="${mesMaintanceVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
      				</select> 
   			</li>
           </ul>
		<ul class="tbl_bottom_right">
<!-- 			<li> -->
<!-- 	    		<a onclick="excelDownload();">액셀 다운로드</a> -->
<!-- 	    	</li> -->
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">		
				<li>
		    		<a onclick="go_insert();">등록</a>
		    	</li>
    		</c:if>
	    </ul>
	</div>  
			
 	<div class="page">	
	  <span><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></span>
	</div>
	

	<table id="excelDownload" style="width:100%; display:none;">
		<thead>
			<tr>
	      		<th >관리 번호</th>
	      		<th >자산유형</th>
	      		<th >처리유형</th>
	      		<th >요청기관</th>
	      		<th >요청일자</th>
	      		<th >요청내용</th>
	      		<th >완료일자</th>
	      		<th >처리내용</th>
	      		<th >작업자</th>
	      		<th >장애구분</th>
	      		<th >진행상태</th>
	      		
	    	</tr>
		</thead>
  		<tbody> 
			<c:forEach var="list" items="${maintanceList}" varStatus="i">
				<tr onclick="maintanceView('${list.mMaintanceKey}');">
					<td>
						${paginationInfo.totalRecordCount - (mesMaintanceVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
						<input type="hidden" value="${list.mMaintanceKey}" />
					</td>
					<td style="text-align:left; padding-left:5px;">
						${list.eAssetTypeName}
					</td>
					<td style="text-align:left; padding-left:5px;">
						${list.mMaintanceModel}
					</td>
					<td style="text-align:left; padding-left:5px;">
						${list.mMaintanceCateName}
					</td>
					<td style="text-align:left; padding-left:5px;">
						${list.mMaintanceType}
					</td>
					<td style="text-align:left; padding-left:5px;">
						${list.mMaintanceSerial}
					</td>
					<td style="text-align:center;">
						${list.mMaintanceStaffName}
					</td>
					<td style="text-align:center;">
						${list.mMaintanceDate}
					</td>
					<td style="text-align:center;">
						${list.mProcessDate}
					</td>
					<td>
						${list.mProcessStaffName}
					</td>
					<td onclick="event.cancelBubble = true;">
						<c:if test="${list.sSignStatus eq '요청등록'}"> 
<%-- 							<a class="mes_btn" onclick="process_go(${list.mMaintanceKey});">처리등록</a> --%>
						</c:if>
						<c:if test="${list.sSignStatus eq '처리등록' || list.sSignStatus eq '반려'}">
<%-- 								<a class="mes_btn" onclick="requestSign(${list.mMaintanceKey}, ${list.sSignKey});">승인요청</a> --%>
						</c:if>
						<c:if test="${list.sSignStatus eq '승인요청'}">
							<c:if test="${list.sSignStaffKey ne staffVo.kStaffKey}">
								승인 요청중
							</c:if>
							<c:if test="${list.sSignStaffKey eq staffVo.kStaffKey}">
<%-- 								<a class="mes_btn" onclick="maintanceView(${list.mMaintanceKey});">결재처리</a> --%>
							</c:if>
						</c:if>
						<c:if test="${list.sSignStatus eq '승인'}">
							승인 완료
						</c:if>
					</td>
				</tr>
			</c:forEach>
       	</tbody>
	</table>
</form>