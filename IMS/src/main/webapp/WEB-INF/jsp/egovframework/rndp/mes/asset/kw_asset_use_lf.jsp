<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>
<link href="/css/mes/mermaid.min.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<script type="text/javascript">
$(function(){
	datepickerSet('topStartDate', 'topEndDate');
	tdBlock(10);
}); 

function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/asset/kw_asset_use_lf.do";
	document.listForm.submit();
}

	
viewService.prototype.go_view = function(obj) {
			
	$("#mloader").show();
	$("#aAssetUseHKey").val(obj.childNodes[0].querySelector("input").value);
	document.listForm.action = "/mes/asset/kw_asset_use_vf.do";
	document.listForm.submit();
}


function go_insert(key, status){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_asset_use_if.do";
	document.listForm.submit();
}

function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

function requestSign(aAssetUseHKey, sSignKey, gubun){
	
	if(confirm("승인요청 하시겠습니까?")){
		$("#mloader").show();
		$("#sSignStatus").val(gubun);
		$("#aAssetUseHKey").val(aAssetUseHKey);
		$("#sSignKey").val(sSignKey);
		document.listForm.action = "/mes/asset/kw_asset_use_r.do";
		document.listForm.submit();
	}
	fn_guestList(1);
}

</script>

<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_asset_use_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="aAssetUseHKey" name="aAssetUseHKey" value="" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<input type="hidden" id="gubun" name="gubun" value="" />
	
	<div class="content_up">
		<div class="content_tit">
			<h2>대상장비 이력 현황</h2>
		</div>		
		
		<div class="tbl_top"> 
			<ul class="tbl_top_left">
				<li>
					<span>자산번호</span>
					<input type="text"  name="searchTypeSet1" id="searchTypeSet1" style="width:150px;"  value="<c:out value='${mesAssetVO.searchTypeSet1}'/>" /> 
				</li>
				<li>
					<span>자산명</span>
					<input type="text"  name="searchTypeSet2" id="searchTypeSet2" style="width:150px;"  value="<c:out value='${mesAssetVO.searchTypeSet2}'/>" /> 
				</li>
				<li>
					<span>진행상태</span>
					<select name="searchTypeSet3" class="select_search" id="searchTypeSet3">
	       				<option value="all" <c:if test="${mesAssetVO.searchTypeSet3 eq 'all'}">selected</c:if>>전체</option>
	       				<option value="결재제외" <c:if test="${mesAssetVO.searchTypeSet3 eq '결재제외'}">selected</c:if>>결재제외</option>
 	       				<option value="승인요청" <c:if test="${mesAssetVO.searchTypeSet3 eq '승인요청'}">selected</c:if>>승인요청</option>
 	       				<option value="승인" <c:if test="${mesAssetVO.searchTypeSet3 eq '승인요청'}">selected</c:if>>승인</option>
 	       				<option value="반려" <c:if test="${mesAssetVO.searchTypeSet3 eq '반려'}">selected</c:if>>반려</option>
	     			</select>					 
				</li>
		    	<li>
	           		<span>작성일자</span>
		       		<input type="text" id="topStartDate" name="topStartDate" style="width:100px;" value="${mesAssetVO.topStartDate}" readonly />
		           	- <input type="text" id="topEndDate" name="topEndDate" style="width:100px;" style="padding-left:2px;" value="${mesAssetVO.topEndDate}" readonly />
				</li>
				<li>
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
					<span>제조사</span>
					<input type="text" id="searchTypeSet6" name="searchTypeSet6" style="width:100px;" value="${mesAssetVO.searchTypeSet6}" />
				</li>
				<li>
					<span>모델명</span>
					<input type="text" id="searchTypeSet7" name="searchTypeSet7" style="width:100px;" value="${mesAssetVO.searchTypeSet7}" />
				</li>
				<li>
					<span>제조번호</span>
					<input type="text" id="searchTypeSet8" name="searchTypeSet8" style="width:100px;" value="${mesAssetVO.searchTypeSet8}" />
				</li>
				<li>
					<span>설치위치</span>
					<input type="text" id="searchTypeSet9" name="searchTypeSet9" style="width:100px;" value="${mesAssetVO.searchTypeSet9}" />
				</li>
				<li>
					<span>작성자</span>
					<input type="text" id="searchTypeSet10" name="searchTypeSet10" style="width:100px;" value="${mesAssetVO.searchTypeSet10}" />
				</li>
			</ul>		
		</div>
	</div> 	
	
	<div id="tableContainer" class="lf_tbl_list">
		<table id="myTable" >
			<thead>
				<tr>
					<th style="width:2%;">No.</th>
					<th style="width:8%;">자산번호</th>
					<th style="width:8%;">자산명</th>
					<th style="width:8%;">제조사</th>
					<th style="width:8%;">모델명</th>
					<th style="width:8%;">제조번호</th>
					<th style="width:8%;">설치위치</th>
					<th style="width:8%;">이력건수</th>
					<th style="width:8%;">작성일자</th>
					<th style="width:8%;">작성자</th>
					<th style="width:8%;">진행상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetUseList}" varStatus="i">
					<tr>
						<td>
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.aAssetUseHKey}" />
						</td>
						<td>
							${list.aAssetNumber}
						</td>
						<td>
							${list.aAssetName}
						</td>
						<td>
							${list.aAssetMaker}
						</td>
						<td>
							${list.aAssetModel}
						</td>
						<td>
							${list.aAssetManufactureNumber}
						</td>
						<td>
							${list.aAssetIntroducer}
						</td>
						<td>
							${list.aAssetUseItemCnt}건
						</td>
						<td>
							${list.aAssetDate}
						</td>
						<td>
							${list.aAssetUseStaff}
						</td>
						<td onclick="event.cancelBubble = true;">
							${list.sSignStatus}
							<c:if test="${list.sSignStatus eq '등록' || list.sSignStatus eq '반려'}">
								<a class="mes_btn" onclick="requestSign('${list.aAssetUseHKey}', '${list.sSignKey}','승인요청');">승인요청</a>
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
	             	<option value="20" <c:if test="${mesAssetVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              	<option value="50" <c:if test="${mesAssetVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              	<option value="100" <c:if test="${mesAssetVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
      			</select> 
   			</li>
        </ul>
		<ul class="tbl_bottom_right">
			<li>
	    		<a onclick="excelDownload();">액셀 다운로드</a>
	    	</li>
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">		
				<li>
		    		<a onclick="go_insert();">등록</a>
		    	</li>
    		</c:if>
	    </ul>
	</div>  
	
	
 	<div class="page">	
	  <span><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></span>
	</div>
	
</form>