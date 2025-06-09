<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>
<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
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
	 var eAssetImageId  = $('#eAssetImageId').val();
	 var eAssetImageExt  = $('#eAssetImageExt').val();
	 var sUrl = "/mes/asset/ePDFViewer.do?eDeviceImgId="+eAssetImageId+"&eDeviceImgExtension="+eAssetImageExt;
		window.open(sUrl, "AddrAdd", "width=1200, height=850, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
// 	 if(confirm("이미지를 다운로드 하시겠습니까?")){
// 			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eAssetImageId+"&fileSn=0'/>");
// 		}
}
$(document).ready(function(){
	addFileInfoRow();
	eDate();
	settingSign(); // 싸인 설정
});
function eBarcodePop(){
	var eAssetKey = $("#eAssetKey").val();
	var sUrl = "/mes/asset/kw_asset_barcode.do?eAssetKey="+eAssetKey;
	window.open(sUrl, "AddrAdd", "width=800, height=450, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
}
function sel_assetPop(popGubun){
	var eAssetKey = $("#eAssetKey").val();
	var sUrl = "";
	if(popGubun == "C"){ //반입반출
		sUrl  = "/mes/asset/kw_Condition_Pop.do?eAssetKey="+eAssetKey;
	}
	if(popGubun == "R"){ //부품교체
		sUrl  = "/mes/asset/kw_Replacement_Pop.do?eAssetKey="+eAssetKey;
		
	}
	if(popGubun == "M"){ //장애이력
		sUrl  = "/mes/asset/kw_Maintance_Pop.do?eAssetKey="+eAssetKey;
// 		alert("장애이력이 없습니다.");
// 		return false;
	}
	window.open(sUrl, "AddrAdd", "width=900, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
}


function startApproval(sSignStatus){
	$("#mloader").show();
	$("#sSignStatus").val(sSignStatus)
	document.frm.action = "/mes/asset/kw_asset_rv.do";
	document.frm.submit();
}

function changeContent(value){
	var innerStr = "";
	
	if(value == "승인"){
		innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
	} else if(value == "반려"){
		innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='50'/>";
		innerStr += "<a class='mes_btn' onclick='sSignContentAdd();'>반려 사유 저장</a>";
	}
	document.getElementById("sSignContentSet").innerHTML = innerStr;
}

function sSignContentAdd(){
	var sSignContent = $("#sSignContent").val();
	var eAssetKey = $("#eAssetKey").val();
	var kStaffKey = $("#kStaffKey").val();
	if(sSignContent != ""){
		$.ajax({
			type : "post"
		,	url : "/mes/asset/kw_uploadSignRejectionReasonAjax.do"
		,	data : {
				"eAssetKey"		: eAssetKey
			,	"sSignStaffKey"		: kStaffKey
			,	"sSignDecison"		: "반려"
			,	"sSignContent"		: sSignContent	
			}
		,	dataType : "json"
		,	async : false
		,	cache : false
		,	success : function(msg){
			 alert("반려처리되었습니다.")
				document.frm.action = "/mes/asset/kw_asset_vf.do";
				document.frm.submit();
			}
		});
	}else{
		alert("반려사유를 입력하세요.");
		$("#sSignContent").focus();
		return;
	}
	
}

function settingSign(){
	/* 사인 ============================= */
	var canvas = $("#signature")[0];
	var signature = new SignaturePad(canvas, {
		minWidth : 2,
		maxWidth : 2,
		penColor : "rgb(0, 0, 0)"
	});
	
	$("#save").on("click", function() {
		if(signature.isEmpty()) {
			alert("내용이 없습니다.");
		} else {
			
			//저장버튼시 부서, 날짜, 서명을 저장한다.
			var data = signature.toDataURL("image/png");
			var eAssetKey = $("#eAssetKey").val();
			var kStaffKey = $("#kStaffKey").val();
			
			$.ajax({
				type : "post"
			,	url : "/mes/asset/kw_uploadSignImgAjax.do"
			,	data : {
					"sSignTableKey"		: eAssetKey
				,	"sSignTableName"	: "A_ASSET"
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "승인"
				,	"sSignContent"		: data	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
					innerStr = "";
					innerStr += "<img style='width:150px; height:100px;' onclick='setSignHtml(this);' src='"+data+"'/>";
					innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'>"+data+"</textarea>";
					
					saveObj.parentNode.innerHTML = innerStr;
					
					$('#modal-close').get(0).click();
					document.frm.action = "/mes/asset/kw_asset_vf.do";
					document.frm.submit();
					
				}
			});
		}
	});
	
}

/*SIGN PAD*/
var scrollPosition = 0;
function setSign(obj, eventTmp){
	
	event.preventDefault();
	scrollPosition = window.scrollY;
	document.body.style.overflow = "hidden";
	$("#setModal").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
	saveObj = obj;
	
	var canvas = $("#signature")[0];
  	var cnvs = document.getElementById('signature');
	// context
    var ctx = canvas.getContext('2d');
    // 픽셀 정리
    ctx.clearRect(0, 0, cnvs.width, cnvs.height);
    // 컨텍스트 리셋
    ctx.beginPath();
}

function closeModal(){
	document.body.style.overflow = "auto";
	setTimeout(function() {
		window.scrollTo(0, scrollPosition);
	}, 50);
}

function setSignHtml(obj){
	innerStr = "";
	innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
	innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
	obj.parentNode.innerHTML = innerStr;
}



//화면 캡처 후 인쇄로 넘어가기
function capture() { 
	html2canvas($("#print_div"), {
		onrendered: function(canvas) { 
			var img = canvas.toDataURL("image/png");
			console.log(img); 
//				window.open(img); // 이미지를 윈도우 팝업으로..
			win = window.open();
			self.focus(); 
			win.document.open();
	  		win.document.write('<html><head><title></title>');
	  		win.document.write('</haed><body><table><tr><td>');
	  		win.document.write('<img  width="95%" src=' + img + '>');
	  		win.document.write('</td></tr></table></body></html>');
			win.document.close();
  			setTimeout(function(){
  			    win.print();
  			    win.close();
  			    }, 5);

		}
	}); 
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
	<input type="hidden" name="sSignStatus" id="sSignStatus" value="" />
	<input type="hidden" name="kStaffKey" id="kStaffKey" value="${staffVO.kStaffKey}" />
		
	<div class="content_top">	
		<div class="content_tit">
			<h2>대상장비 상세정보 </h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
					<tr>
	            		<th>작성자</th>
            			<td>${assetInfo.kStaffName }</td>
	            		
            			<th>등록일</th>
            			<td>${assetInfo.eAssetWdate }</td>
          			</tr>					
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
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
			 		<th>내구연수</th>
					<td>
						${assetInfo.eLifespan}       : ${assetInfo.eLifeType} 
					</td>
					<th>망구분</th>
					<td>
						${assetInfo.eNetworkType}
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
					<th>HOST NAME</th>
					<td> 
						${assetInfo.eHostName} 
					</td>
					<th rowspan="4">장비사진 </th>
					<td rowspan="4">
						<input type="hidden" id="eAssetImageName"  name="eAssetImageName" value="${assetInfo.eAssetImageName}">
						<input type="hidden" id="eAssetImageId"  name="eAssetImageId" value="${assetInfo.eAssetImageId}">
						<input type="hidden" id="eAssetImageExt"  name="eAssetImageExt" value="${assetInfo.eAssetImageExt}">
						
						 <iframe id="pdfViewer" width="200px;" height="200px;" frameborder="0"></iframe>
	             		 <img id="eImgViewer"  style="display: none;width: 200px;height: 200px;"  src="" onclick="eDownload();">
             		 </td>
				</tr> 	
					 	
			 	<tr>
					<th>IP</th>
					<td>
						${assetInfo.eIp}
					</td>
				</tr>
				
				<tr>
					<th>OS</th>
					<td> 
						${assetInfo.eOs} 
					</td>
				</tr> 	 	 	
				  
				<tr>
					<th>비고</th>
					<td colspan="1">
						${assetInfo.eAssetEtc} 
					</td>
				</tr> 	 
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" onclick="sel_assetPop('C')" class="form_btn active">반/출입 조회</button>
		<button type="button" onclick="sel_assetPop('R')" class="form_btn active">부품교체이력 조회</button>
		<button type="button" onclick="sel_assetPop('M')" class="form_btn active">장애이력 조회</button>
		<button type="button" onclick="eBarcodePop()" class="form_btn active">QR코드출력</button>
	</div>

	<c:if test="${not empty assetList}">
		<div class="content_top nofirst">
			<div class="content_tit">
				<h2>라이선스 정보</h2> 
			</div>
		</div>
		<div class="normal_table">
			<table>
					<thead>
					<tr>
						<th style="width: 8%;">구분</th>
						<th style="width: 10%;">제조사</th>
						<th style="width: 10%;">라이선스명</th>
						<th style="width: 12%;">버전</th> 
						<th style="width: 12%;">구매일</th> 
						<th style="width: 12%;">만료일</th> 
						<th style="width: 24%;">비고</th> 
						<th style="width: 12%;">적용 수량</th> 
					</tr>
				</thead>
				<tbody id="lineRowLicense">
						 <c:forEach var="list" items="${assetList}" varStatus="i">
							 <tr >
							 	<td>-</td>
							 	<td>${list.eManufacturer }</td>
							 	<td>${list.eProductName }</td>
							 	<td>${list.eVersion }</td>
							 	<td>${list.ePurchaseDate }</td>
							 	<td>${list.eEndDate }</td>
							 	<td>${list.eRemarks }</td>
							 	<td>${list.eLicenseQuantity }</td>
							 </tr>
						 </c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>	
<!-- 	결재정보 요청등록시. -->
	<c:if test="${not empty signList}">
		<div class="content_top nofirst">
			<div class="content_tit">
				<h2>결재현황</h2>
			</div>
		</div>
		
		<div class="normal_table">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%; border-left: 1px solid #bfdaf7;">결재순서</th>
						<th style="width:10%;">결재자</th>
						<th style="width:10%;">결정</th>
						<th style="width:10%;">결재구분</th>
						<th style="width:60%;">반려사유 및 싸인</th>
					</tr>
	        	</thead>
		        <tbody>
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:left; padding-left:5px; width:10%;">
								${signList.sSignStaffName}
							</td>
							<td style="text-align:center; width:10%;">
								${signList.sSignDecison} 
							</td>
							<td>${signList.sSignStaffGubun}:
								<c:if test="${assetInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignDecison ne '결재대기' }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${assetInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${assetInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
						        		<a class="mes_btn" onclick="setSign(this, event);">사인</a>
								</c:if>
								<c:if test="${signList.sSignDecison eq '승인'}">
									<img src="${signList.sSignContent}"/>
								</c:if>
								<c:if test="${signList.sSignDecison eq '반려'}">
									${signList.sSignContent}
								</c:if>
							</td>
							
						</tr>
					</c:forEach>
					<c:if test="${empty signList}">
						<tr>
							<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	
	<div class="bottom_btn mt20">
		<c:if test="${(assetInfo.kStaffKey eq staffVO.kStaffKey && (assetInfo.sSignStatus eq '등록' || assetInfo.sSignStatus eq '반려' || assetInfo.sSignStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (assetInfo.sSignStatus eq '등록' || assetInfo.sSignStatus eq '반려' || assetInfo.sSignStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" onclick="update_go();" class="form_btn bg">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" onclick="delete_go();" class="form_btn bg">삭제</button>
			</c:if>
		</c:if>
		<button type="button" onclick="list_go();" class="form_btn">목록</button>
		<c:if test="${assetInfo.sSignStatus eq '등록'}">
			<c:if test="${assetInfo.kStaffKey eq staffVO.kStaffKey }">
				<button type="button" onclick="startApproval('Y');" class="form_btn bg">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${assetInfo.sSignStatus eq '승인요청'}">
			<c:if test="${assetInfo.kStaffKey eq staffVO.kStaffKey && assetInfo.sSignProgress eq '0'}">
				<button type="button" onclick="startApproval('N');" class="form_btn bg">요청취소</button>
			</c:if>
		</c:if>
	</div>
	
	<div id="setModal" class="modal" style="display:none;">
		<a id="modal-close" href="#close-modal" rel="modal:close" class="close-modal " onclick="closeModal()">Close</a>
		<div class="modal-content">
			<div class="lf_tbl_list scrolltbody" style="margin-top: 0px; border: 0.5px #d1d1d1 solid; border-radius:5px;max-height:200px;">
				<canvas id="signature" width="450" height="200"></canvas>
			</div>
			<div class="tac mt20" id="modalAddRow">
		     <a class="form_btn md" id="save">저장 </a>
			</div>
		</div>
	</div>	
	
</form>
