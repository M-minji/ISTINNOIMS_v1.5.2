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
$(document).ready(function(){
	settingSign(); // 싸인 설정
});
function delete_go(){
	var useCount = $("#eUsedLicenseQuantity").val();
	if(useCount != "") {
		alert(useCount + "개의 자산에서 해당 라이선스를 사용 중 입니다.\n" + "자산의 라이선스 정보도 함께 삭제됩니다.");
	}
	if(confirm("삭제하시겠습니까?")){
		document.frm.action = "/mes/asset/kw_Software_Register_d.do";
		document.frm.submit();
	}
}
function list_go(){
	document.frm.action = "/mes/asset/kw_Software_Register_lf.do";
	document.frm.submit();
}
function update_go(){
	$('#mloader').show();
	document.frm.action = "/mes/asset/kw_Software_Register_uf.do";
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

function deleteLog(key){
	if(confirm("해당 갱신이력을 삭제하시겠습니까?")){
		document.frm.eSWRegisterLogKey = key;
		document.frm.action = "/mes/asset/kw_Software_Log_uf.do";
		document.frm.submit();
	}

}

function eAssetPop(){
	var sUrl = "/mes/asset/kw_asset_License_lf.do?eSWRegisterKey="+ $("#eSWRegisterKey").val()+"&eUsedLicenseQuantity="+ $("#eUsedLicenseQuantity").val();
	window.open(sUrl, "AddrAdd", "width=1200, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
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
	var eAssetKey = $("#eSWRegisterKey").val();
	var kStaffKey = $("#kStaffKey").val();
	if(sSignContent != ""){
		$.ajax({
			type : "post"
		,	url : "/mes/asset/kw_uploadSignSWReasonAjax.do"
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
				document.frm.action = "/mes/asset/kw_Software_Register_vf.do";
				document.frm.submit();
			}
		});
	}else{
		alert("반려사유를 입력하세요.");
		$("#sSignContent").focus();
		return;
	}
	
}

function startApproval(eStatus){
	$("#eStatus").val(eStatus)
	document.frm.action = "/mes/asset/kw_Software_Register_vfr.do";
	document.frm.submit();
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
			var eAssetKey = $("#eSWRegisterKey").val();
			var kStaffKey = $("#kStaffKey").val();
			
			$.ajax({
				type : "post"
			,	url : "/mes/asset/kw_uploadSignImgAjax.do"
			,	data : {
					"sSignTableKey"		: eAssetKey
				,	"sSignTableName"	: "A_ASSET_SW"
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
					document.frm.action = "/mes/asset/kw_Software_Register_vf.do";
					document.frm.submit();
					
				}
			});
		}
	});
	
}

function eDownload(fileId,eFileName){
	 if(confirm(eFileName+"을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+fileId+"&fileSn=0'/>");
		}
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
	<input  type="hidden" id="aAssetKey" name="aAssetKey" value="${assetInfo.aAssetKey}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesAssetVO.searchType}" />
	<input type="hidden" id="eSWRegisterKey" name="eSWRegisterKey" value="${swInfo.eSWRegisterKey }" />	
	<input type="hidden" id="eSWRegisterLogKey" name="eSWRegisterLogKey" value="" />	
	<input type="hidden" id="eUsedLicenseQuantity" name="eUsedLicenseQuantity" value="${swInfo.eUsedLicenseQuantity}" />	
	<input type="hidden" id="eStatus" name="eStatus" value="${swInfo.eStatus}" />	
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />	
	<div class="content_top">	
		<div class="content_tit">
			<h2>소프트웨어 라이선스 정보 상세</h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
					<tr>
            		<th>작성자</th>
            		<td>${swInfo.eAuthor }
						<input type="hidden" name="eAuthor" id="eAuthor" style="width:95%; text-align:left;" maxLength="100" value="${staffVO.kStaffName}"/>
            		</td>
            		<th>등록일</th>
            		<td>${swInfo.aAssetDate }
						<input type="hidden" id="aAssetDate" name="aAssetDate" style="width:150px; text-align:center;" class="inp_color" readonly />
            		</td>
          		</tr>					
			</tbody>
		</table>
	</div>
		
 
	<div class="normal_table">          	
		<table>
			<thead>  
	          	<tr>
	             	<th style="width: 10%;">제조사</th>
	             	<th style="width: 15%;">라이선스명</th>
	             	<th style="width: 8%;">버전</th>
	             	<th style="width: 8%;">구매일</th>
	             	<th style="width: 8%;">시작일</th>
	             	<th style="width: 8%;">종료일</th>
	             	<th style="width: 8%;">유효기간</th>
	             	<th style="width: 8%;">수량</th>
	             	<th style="width: *;">첨부 파일</th>
	            </tr>
	           
	         </thead>
	         <tbody id="lineRow">
				<tr>
					<td>${swInfo.eManufacturer }</td>
					<td>${swInfo.eProductName }</td>
					<td>${swInfo.eVersion }</td>
					<td>${swInfo.ePurchaseDate }</td>
					<td>${swInfo.eStartDate }</td>
					<td>${swInfo.eEndDate }</td>
					<td>${swInfo.eValidityPeriod }</td>
					<td>${swInfo.eLicenseQuantity }</td>
	  <!--  				<td>${swInfo.eRemarks }</td>	 -->	
					<td>  
						<div id='fileList'>
						 <c:forEach var="aFileList" items="${aFileList}" varStatus="j">
						    <div class="fileItem" id="fileItem_${j.index}">
						    <span style=text-decoration:underline; onclick="eDownload('${aFileList.eFileRowId}','${aFileList.eFileRowName}');">${aFileList.eFileRowName}</span>
						    <input type="hidden" name="eFileRowId" id="eFileRowId_${aFileList.eFileRowIndex}" value="${aFileList.eFileRowId}">
						    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${aFileList.eFileRowName}">
						    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${aFileList.eFileRowIndex}"></div>
						</c:forEach>
						</div>	
					</td>	
				</tr>
			</tbody>
		</table> 
	</div>
		
	<div class="content_top nofirst">
		<div class="content_tit">
			<h2>라이선스 갱신 이력</h2>
		</div>
	</div>
	<div class="normal_table">
		<table>
				<thead>
				<tr>
					<th style="width: 10%;">구분</th>
					<th style="width: 12%;">갱신 전: 만료일자</th>
					<th style="width: 12%;">갱신 후: 만료일자</th>
					<th style="width: 12%;">갱신등록일</th> 
				</tr>
			</thead>
			<tbody id="lineRowTwo">
			 		<c:forEach var="list" items="${updateList}" varStatus="i">
			          	<tr>
							<td style="text-align:center; width:10%; padding-left:0px;">
								${i.index + 1} &nbsp;&nbsp;&nbsp;
								<c:if test="${i.last}">
									<a class="del" onclick="deleteLog(${item.eSWRegisterLogKey});">X</a>
								</c:if>
							</td>
							<td style="text-align:center; width:30%;">
								${list.eOldExpiryDate}
							</td>
							<td style="text-align:center; width:30%;">
								${list.eNewExpiryDate}
							</td>
							<td style="text-align:center; width:30%;">
								${list.eSWRegisterLogWDate}
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty updateList}">
						<tr>
							<td colspan="4" style="text-align: center;">등록 정보가 없습니다.</td>
						</tr>
					</c:if>
			</tbody>
		</table>
	</div>	
	
	<div class="bottom_btn">
		<c:if test="${swInfo.eUsedLicenseQuantity  ne '0'}">
			<span>총 사용 수량 : ${swInfo.eUsedLicenseQuantity}</span>
		</c:if>
		<button type="button" class="form_btn active ml20" onclick="eAssetPop()" >라이선스 사용자산 조회</button>
	</div>

	
	<c:if test="${not empty signList}">
		<div class="content_top nofirst" style="padding-top:20px;">
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
							<td>
								${signList.sSignStaffGubun}:
								<c:if test="${swInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignStaffKey ne staffVO.kStaffKey }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${swInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${swInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
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
	
	<div class="bottom_btn">
		<c:if test="${(swInfo.kStaffKey eq staffVO.kStaffKey && (swInfo.eStatus eq '등록' || swInfo.eStatus eq '반려' || swInfo.eStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (swInfo.eStatus eq '등록' || swInfo.eStatus eq '반려' || swInfo.eStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T' ||  staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T' || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
			</c:if>
		</c:if>
		<button type="button" class="form_btn" onclick="list_go();">목록</button>
		<c:if test="${swInfo.eStatus eq '등록'}">
			<c:if test="${swInfo.kStaffKey eq staffVO.kStaffKey }">
				<button type="button" class="form_btn bg" onclick="startApproval('Y');">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${swInfo.eStatus eq '승인요청'}">
			<c:if test="${swInfo.kStaffKey eq staffVO.kStaffKey && swInfo.sSignProgress eq '0'}">
				<button type="button" class="form_btn bg" onclick="startApproval('N');">승인취소</button>
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
