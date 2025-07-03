<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>

<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/js/PretendardGOV-1.3.9/pretendard-gov-all.css">
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<script type="text/javascript">
function notice(message) {
	new jBox('Notice', {
		content: message,
		color: 'green',
	      offset: {
	        y: 62
	      },
	      autoClose: 2500,
	      addClass: 'complite-notice'
		});
  }
window.addEventListener("DOMContentLoaded", function () {
	const type = sessionStorage.getItem("actionType");
	if (type) {     
		let message;
		switch (type) {
	      case 'create':
	        message = "등록이 완료되었습니다!";
	        break;
	      case 'delete':
	        message = "삭제가 완료되었습니다!";
	        break;
	      default:
	        message = ""; 
	    }
		notice(message);
	    sessionStorage.removeItem("actionType");
  }
});
//현재날짜
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
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

//검색
function fn_guestList(pageNo) {
	$("#mloader").show();
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/blueprint/kw_blueprint_lf.do";
	document.frm.submit();
}

//ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

//등록
function go_insert(){
	$("#mloader").show();
	document.frm.action = "/mes/blueprint/kw_blueprint_if.do";
	document.frm.submit();
}

//BOM 상세
viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){
		$("#mloader").show();
		$("#eChangeKey").val(obj.childNodes[0].querySelector("input").value);
		document.frm.action = "/mes/blueprint/kw_blueprint_vf.do";
		document.frm.submit();
	}
}
$(document).ready(function() {
   // 그리드 헤더의 너비를 조정하는 스타일 변경
 $('gridjs-wrapper').css('overflow-y', 'hidden');  
 $('gridjs-tr').css('overflow-y', 'hidden');  
 $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
 $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); 
 $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); 
 $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '100px'); 
 $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '100px'); 
 $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '260px'); 
 $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '140px'); 
 $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '140px'); 
 $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '260px'); 
 $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '140px'); 
 $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '140px'); 
 tdBlock(9);
 datepickerSet('topStartDate', 'topEndDate');
 $('#mloader').hide();
});


function startApproval(eChangeKey, sSignStatus){
	$("#mloader").show();
	$("#eChangeKey").val(eChangeKey);
	$("#sSignStatus").val(sSignStatus);
	document.frm.action = "/mes/blueprint/kw_blueprint_lfr.do";
	document.frm.submit();
}

function excelDwonload(){
    document.frm.action = "/mes/blueprint/kw_blueprintExcelListDwonload.do";
    document.frm.submit();
    document.frm.action = "/mes/blueprint/kw_blueprint_lf.do";
}

</script>
<style>
	.jBox-Notice.complite-notice .jBox-content {
	  font-size: 16px; 
	  font-family: 'Pretendard GOV', sans-serif;
	  font-weight: 400; 
	}
</style>

<form id="frm" name="frm" method="post" action="/mes/blueprint/kw_blueprint_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="eChangeKey" name="eChangeKey" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" />

	<div class="content_top">
		<div class="content_tit">
			<h2>변경관리</h2>
		</div>
		<div class="filter_wrap">
			<div class="search_filter">
				<ul>
					 <li>
						<span>요청자</span>
						<input type="text" id="eTextSearchWord1" name="eTextSearchWord1" class="searchWord" value="${mesBlueprintVO.eTextSearchWord1}" maxlength="30"  />
					</li>
					<li>
						<span>요청내용</span>
						<input type="text" id="eTextSearchWord2" name="eTextSearchWord2" class="searchWord" value="${mesBlueprintVO.eTextSearchWord2}" maxlength="30" />
					</li>
					<li>
						<span>요청사유</span>
						<input type="text" id="eTextSearchWord3" name="eTextSearchWord3" class="searchWord" value="${mesBlueprintVO.eTextSearchWord3}" maxlength="30" />
					</li>
		     		<li>
		           		<span>요청일자</span>
		           		<div class="date">
				       		<input type="text" id="topStartDate" name="topStartDate" value="${mesBlueprintVO.topStartDate}" readonly />
				           	- <input type="text" id="topEndDate" name="topEndDate" style="padding-left:2px;" value="${mesBlueprintVO.topEndDate}" readonly />
				        </div>
					</li>
		    	</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn ico_excel" onclick="excelDwonload();">엑셀 다운로드</button>
				<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
	    </div>		
 	</div>   
 	
 	
 	
   <div class="data_table"  >
		<table id="myTable"  >
			<thead>
				<tr>
					<th>No.</th>
					<th>작성자</th>
					<th>요청일자</th>
					<th>요청자</th>
					<th>요청내용</th>
					
<!-- 					<th>요청기관</th> -->
<!-- 					<th>요청부서</th> -->
					
					<th>변경시작일시</th>
					<th>변경완료일시</th>
					<th>요청사유</th>
					<th>중단여부</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${infoList}" varStatus="i">
					<tr onclick="eView(<c:out value='${list.eChangeKey}'/>);">
						<td>
							<c:if test="${list.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
							${paginationInfo.totalRecordCount - (mesBlueprintVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eChangeKey}" />
						</td>
						<td>${list.kStaffName}</td>
						<td>${list.eReqDate} 	</td>
						<td>${list.eRequester} 	</td>
						<td>${list.eReqContent}</td>
						
<%-- 						<td>${list.eWorkDetails}</td> --%>
<%-- 						<td>${list.eReqDept}</td> --%>
<%-- 						<td>${list.eReqDate}</td> --%>
						
						<td>${list.eWorkStart}</td>
						<td>${list.eWorkEnd}</td>
						<td>${list.eWorkDetails}</td>
						<td>${list.eIsInterruptedName}</td>
						<td> 
							<c:choose>
								<c:when test="${list.sSignStatus eq '반려'}">
									반려
								</c:when>
								<c:when test="${list.sSignStatus eq '제외' || list.sSignStatus eq '승인'}">
									등록
								</c:when>
								<c:when test="${list.kStaffKey eq staffVO.kStaffKey || staffVO.kAdminAuth eq 'T'}">
									<c:choose>
										<c:when test="${list.sSignStatus eq '등록'}">
											<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eChangeKey}','Y');">승인요청</a>
										</c:when>
										<c:when test="${list.sSignProgress eq '0'}">
											<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eChangeKey}','N');">요청취소</a>
										</c:when>
										<c:otherwise>
											결재 진행 중
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${list.sSignStatus eq '등록'}">
									결재 대기
								</c:when>
								<c:otherwise>
									결재 진행 중
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>	
	
	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
           		<option value="20" <c:if test="${mesBlueprintVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
           		<option value="50" <c:if test="${mesBlueprintVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
           		<option value="100" <c:if test="${mesBlueprintVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
        	</select> 
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList"/>
		</div>
		<div class="btns">
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' || staffVO.kAdminAuth eq 'T'}">
			<button type="button" class="form_btn active" onclick="go_insert();" >등록</button>
			</c:if>
		</div>
	</div>	
</form>

	
