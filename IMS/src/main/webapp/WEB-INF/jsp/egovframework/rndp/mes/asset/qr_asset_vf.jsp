<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		document.frm.action = "/mes/asset/kw_asset_d.do";
		document.frm.submit();
	}
}
function list_go(){
	document.frm.action = "/mes/asset/kw_asset_lf.do";
	document.frm.submit();
}
function update_go(){
	$('#mloader').show();
	document.frm.action = "/mes/asset/kw_asset_uf.do";
	document.frm.submit();
}


$.fn.tap = function(){
    var self = $(this),
        btn = self.find('.btn_area button');
  
    btn.click(function(){
      var num = $(this).attr('data-num');
      
      btn.removeClass('act');
      $(this).addClass('act');      
      self.find('.tap_item').removeClass('act');
      self.find('.tap_item[data-num="'+ num +'"]').addClass('act');
    });
}
$('.tap').tap();




function eDate(gubun){
	  var currentDate = new Date();
	  var eEosDate  = $("#eEosDate").val()
	  var eEolDate  = $("#eEolDate").val()
	  
	  var formatteEosDate = formatDateData(new Date(eEosDate));
	  var formatteEolDate = formatDateData(new Date(eEolDate));
	    // 유효한 날짜인지 확인
	    if (!isNaN(formatteEosDate.getTime())) {
	    	 var differenceInDays = Math.floor((formatteEosDate-currentDate) / (1000 * 60 * 60 * 24));
	    	  var expired = differenceInDays < 0;
	    	  var spantext = "";
	    	  if(expired){
	    		  spantext = ": 만료";
	    	  }else{
	    		  spantext = ": "+differenceInDays+"일 남음";
	    	  }
	    	  
	    	$("#eEosDateTxt").html(spantext);
	    	
	    }else{
	    	$("#eEosDateTxt").html("");
	    }
	    
	    if (!isNaN(formatteEolDate.getTime())) {
	    	 var differenceInDays = Math.floor((formatteEolDate-currentDate) / (1000 * 60 * 60 * 24));
	    	  var expired = differenceInDays < 0;
	    	  var spantext = "";
	    	  if(expired){
	    		  spantext = ": 만료";
	    	  }else{
	    		  spantext = ": "+differenceInDays+"일 남음";
	    	  }
	    	  
	    	$("#eEolDateTxt").html(spantext);
	    	
	    }else{
	    	$("#eEolDateTxt").html("");
	    }
	  
}

function formatDate(date) {
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}

function formatDateData(date) {
	var formattedDate = new Date(date);
    return formattedDate;
}
function addFileInfoRow(){
	 var allowedExtensions = ['jpg', 'jpeg', 'png', 'gif', 'pdf'];
	 var eIMGregId  = $('#eAssetImageId').val();
	 var eIMGregExtension  = $('#eAssetImageExt').val();
		if(eIMGregExtension.toLowerCase() == "pdf"){
			 var pdfUrl = "/cmm/fms/PDFView.do?atchFileId="+eIMGregId+"&fileSn=0";
			$('#pdfViewer').attr('src', pdfUrl);
		    $('#pdfViewer').show();
		    $('#eImgViewer').hide();
		}else if (allowedExtensions.includes(eIMGregExtension.toLowerCase())) {
		  var imageUrl = "/cmm/fms/getImage.do?atchFileId="+eIMGregId+"&fileSn=0";	
	    	$('#eImgViewer').attr('src',imageUrl);
	    	$('#eImgViewer').show();
	    	$('#pdfViewer').hide();
		}
}
function eDownload(){
	 var eIMGregId  = $('#eAssetImageId').val();
	 if(confirm("이미지를 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eIMGregId+"&fileSn=0'/>");
		}
}
$(document).ready(function(){
	addFileInfoRow();
	eDate();
});
function eBarcodePop(){
	var eAssetKey = $("#eAssetKey").val();
	var sUrl = "/mes/asset/kw_asset_barcode.do?eAssetKey="+eAssetKey;
	window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
}
function eClose() {  
	window.close();
}
</script>
<style>
.tabs {
  background-color: #ffffff;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.2);
  width: 100%;
  margin: 50px auto;}

/* 탭 스타일 */
.tab_item {
  width: calc(100%/3 - 2px);
  height: 50px;
  border-bottom: 3px solid #22499d;
  background-color: #f8fafc;
  line-height: 50px;
  font-size: 16px;
  text-align: center;
  color: #333333;
  display: block;
  float: left;
  text-align: center;
  font-weight: bold;
  transition: all 0.2s ease;
  border-top: 1px solid #ddd;
} 

.tabs>label.tab_item:nth-child(3){
border-right: 3px solid #f8fafc;
} 

/* 라디오 버튼 UI삭제*/
input[name="tab_item"] {
  display: none;
}

/* 탭 컨텐츠 스타일 */
.tab_content {
  display: none;
  padding: 40px;
  clear: both;
  overflow: hidden;
  border: 1px solid #ddd;
  border-top: 0;
} 


/* 선택 된 탭 콘텐츠를 표시 */
#all:checked ~ #all_content,
#programming:checked ~ #programming_content,
#design:checked ~ #design_content {
  display: block;
}

/* 선택된 탭 스타일 */
.tabs input:checked + .tab_item {
  background-color: #fff;
  color: #009edb;
  position: relative;
  
}
.tabs input:checked + .tab_item::after{ 
  content: "";
  position: absolute;
  left: 0;
  bottom: -3px;
  width: 100%;
  border-bottom: 3px solid #fff;
   border-top: 3px solid #22499d;
   top: 0;
   border-left: 3px solid #22499d;
   border-right: 3px solid #22499d;
}
</style> 



<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input  type="hidden" id="eAssetKey" name="eAssetKey" value="${assetInfo.eAssetKey}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesAssetVO.searchType}" />
		
	<div class="content">	
		<div class="content_tit">
			<h2>대상장비 상세정보 </h2>
		</div>
	</div>
	
	<div class="tbl_write">
		<table>
			<tbody>
				<tr>
					<th>자산유형</th>
					<td>
						${assetInfo.eAssetTypeName}
					</td>
					<th>자산번호</th>
					<td>
						${assetInfo.eAssetNumber}
					</td>
				</tr>
				<tr>
					<th>자산명</th>
					<td>
						${assetInfo.eAssetName}
					</td>
					<th>설치위치</th>
					<td> ${assetInfo.ePositionName1}  
					</td>
				</tr>
				<tr>
					<th>제조사</th>
					<td>
						${assetInfo.eAssetMaker}
					</td>
					<th>모델명</th>
					<td> 
						${assetInfo.eAssetModel} 
					</td>
				</tr> 	
			 	<tr>
					<th>제조번호(S/N)</th>
					<td>
						${assetInfo.eAssetSNumber}
					</td>
					<th>자산상태</th>
					<td> 
						${assetInfo.eAssetStatusName} 
					</td>
				</tr> 	
				 	
				<tr>
					<th>도입원가(원)</th>
					<td>
						${assetInfo.eAssetCost}
					</td>
					<th>도입일</th>
					<td> 
						${assetInfo.eAssetDate} 
					</td>
				</tr> 	
				 	 	
				<tr>
					<th>장비구분</th>
					<td>
						${assetInfo.eDeviceType}
					</td>
					<th>사업명</th>
					<td> 
						${assetInfo.eAssetPurpose} 
					</td>
				</tr> 	 	
				 	
			 	<tr>
					<th>망구분</th>
					<td>
						${assetInfo.eNetworkType}
					</td>
					<th>HOST NAME</th>
					<td> 
						${assetInfo.eHostName} 
					</td>
				</tr> 	 		
					 	
			 	<tr>
					<th>IP</th>
					<td>
						${assetInfo.eIp}
					</td>
					<th>OS</th>
					<td> 
						${assetInfo.eOs} 
					</td>
				</tr> 	 	 	
				<tr>
					<th>EoS</th>
					<td>
					<input type="hidden" name="eEosDate" id="eEosDate" style="width:150px; text-align:center;" class="inp_color" onchange="eDate('eEosDate')"  value="${assetInfo.eEosDate}" />
						${assetInfo.eEosDate} <span id="eEosDateTxt"></span>
					</td>
					<th>EoL</th>
					<td> 
					<input type="hidden" name=eEolDate id="eEolDate" style="width:150px; text-align:center;" class="inp_color" onchange="eDate('eEolDate')"  value="${assetInfo.eEolDate}" />
						${assetInfo.eEolDate}  <span id="eEolDateTxt"></span>
					</td>
				</tr> 	  
				<tr>
					<th>내구연수</th>
					<td colspan="1">
						${assetInfo.eLifespan}       : ${assetInfo.eLifeType} 
					</td>
					<th>비고</th>
					<td colspan="1">
${assetInfo.eAssetEtc} 
					</td>
				</tr> 	 
				<tr> 
					<th>장비사진(PNG/PDF)</th>
					<td colspan="3">
						<input type="hidden" id="eAssetImageName"  name="eAssetImageName" value="${assetInfo.eAssetImageName}">
						<input type="hidden" id="eAssetImageId"  name="eAssetImageId" value="${assetInfo.eAssetImageId}">
						<input type="hidden" id="eAssetImageExt"  name="eAssetImageExt" value="${assetInfo.eAssetImageExt}">
						
						 <iframe id="pdfViewer" width="450px;" height="450px;" frameborder="0"></iframe>
	             		 <img id="eImgViewer"  style="display: none;width: 300px;height: 300px;"  src="" onclick="eDownload();">
             		 </td>
				</tr> 	
			</tbody>
		</table>
	</div>
		
<!-- <div class="tabs"> -->
<!--     <input id="all" type="radio" name="tab_item" checked> -->
<!--     <label class="tab_item" for="all">Tab 1</label> -->
<!--     <input id="programming" type="radio" name="tab_item"> -->
<!--     <label class="tab_item" for="programming">Tab 2</label> -->
<!--     <input id="design" type="radio" name="tab_item"> -->
<!--     <label class="tab_item" for="design" style="border-right: 3px solid #f8fafc;">Tab 3</label>  -->
    
<!--     <div class="tab_content" id="all_content"> -->
<!--         contents1 -->
<!--     </div> -->
<!--     <div class="tab_content" id="programming_content"> -->
<!--        contents2 -->
<!-- 	</div> -->
<!--     <div class="tab_content" id="design_content"> -->
<!--         contents3 -->
<!-- 	</div> -->
<!-- </div>	 -->
		
		
		
		
		
		
	<div class="tbl_btn_right">
		<ul>
<%-- 			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'}"> --%>
<!-- 			<li> -->
<!-- 				<a style="cursor: pointer;" onclick="update_go();">수정</a>  -->
<!-- 			</li> -->
<%-- 			</c:if> --%>
<%-- 			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'}"> --%>
<!-- 			<li> -->
<!-- 				<a style="cursor: pointer;" onclick="delete_go();">삭제</a>  -->
<!-- 			</li> -->
<%-- 			</c:if> --%>
<!-- 			<li> -->
<!-- 				취소버튼  -->
<!-- 				<a style="cursor: pointer;" onclick="list_go();">목록	</a> -->
<!-- 			</li> -->
			<li>
				<a style="cursor: pointer;"  href="javascript:self.close();">닫기</a>
			</li>
		</ul>
	</div>
</form>
