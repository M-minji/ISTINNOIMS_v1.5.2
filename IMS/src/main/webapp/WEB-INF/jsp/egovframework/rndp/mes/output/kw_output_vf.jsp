<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>

<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>

<style>
.tbl_write>table>tbody>tr>td {
	width:10%;
}

</style>
<script type="text/javascript">


$(document).ready(function(){	
	datepickerSet('oStartDate', 'oEndDate');
	$("#oStartDate").val(nowDate());
	$("#oEndDate").val(nowDate());
	settingSign();
})

// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}
	
// 오늘 날짜
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate; 
}

// 목록
function cancel(){
	/* $("#mloader").show(); */
	document.frm.action = "/mes/output/kw_output_lf.do";
	document.frm.submit(); 
}

//수정
function update_go(){
	/* $("#mloader").show(); */
	document.frm.action = "/mes/output/kw_output_uf.do";
	document.frm.submit(); 
}

// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();
	
}

var btnGubun;
function mesIMGreg(index) {
	btnGubun = index;
	var url = "/mes/output/popup/kw_output_IMGreg_lf.do";
	window.open(url,"outputFileList","width=1100,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
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
			var sSignKey = $("#sSignKey").val();
			var kStaffKey = $("#kStaffKey").val();
			
			$.ajax({
				type : "post"
			,	url : "/mes/sign/kw_uploadSignImgAjax.do"
			,	data : {
					"sSignKey"			: sSignKey
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "승인"
				,	"sSignContent"		: data	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
					innerStr = "";
					innerStr += "<img style='width:150px; height:100px;' onclick='setSignHtml(this,"+signId+");' src='"+data+"'/>";
					innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'>"+data+"</textarea>";
					
					saveObj.parentNode.innerHTML = innerStr;
					
					$('#modal-close').get(0).click();
				}
			});
		}
	});
	
}

/*SIGN PAD*/
var scrollPosition = 0;
function setSign(obj, eventTmp, signIdTmp){
		
	if($("#eProreqAppDate"+signIdTmp).val() == ""){
		if(confirm("서명일자를 오늘로 설정하시겠습니까?")){
			$("#eProreqAppDate"+signIdTmp).val(nowDate());
		}else{
			return false;
		}
		
	}
	
	event.preventDefault();
	scrollPosition = window.scrollY;
	document.body.style.overflow = "hidden";
	$("#setModal").modal({
        escapeClose: false,
        clickClose: false,
        showClose: false
    });
	saveObj = obj;
	signId = signIdTmp;
	
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

function printPop(setKey){
	var url = "/mes/proreq/popup/kw_proreq_vf_popup.do";
	url += "?eProreqKey="+setKey;
	window.open(url ,"addrAdd" ,"width=1200,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	
}
function delete_go(){

	if(confirm("삭제하시겠습니까?")){
		
		document.frm.action = "/mes/output/kw_output_d.do";
		document.frm.submit();
	}
}

function setComma(){
	var cost = $("#oBusMoney").val();
	$("#oBusMoney").val(comma(cost));
}

function fileAdd(eID, eNAME){
	// 	alert(eID);
	// 	btnGubun
	$("#oOutputItemFilid"+btnGubun).val(eID);
	$("#eIMGregName"+btnGubun).val(eNAME);
		
}

function changeContent(value){
	var innerStr = "";
	
	if(value == "승인"){
		innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
	} else if(value == "반려"){
		innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='100'/>";
	}
	document.getElementById("sSignContentSet").innerHTML = innerStr;
}

function setApproval(){
	if(document.getElementById("sSignContent") == null){
		alert("승인 후 싸인이 필요합니다");
		return false;
	}
	if(document.getElementById("sSignContent").value == ""){
		alert("반려사유가 입력되지 않았습니다 ");
		document.getElementById("sSignContent").focus();
		return false;
	}
	$("#mloader").show();
	document.frm.action = "/mes/output/kw_output_s.do";
	document.frm.submit();
}

//처리내역 등록 
function process_go(key){
	$("#mloader").show();
	document.frm.action = "/mes/output/kw_output_process_if.do";
	document.frm.submit();
}
</script>

<form id="frm" name="frm" method="post" >
  	<input  type="hidden" id="outputKey" name="outputKey" value="${outputInfo.outputKey}" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="${outputInfo.sSignKey}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVo.kStaffKey}" />
	<div class="content">
		<div class="content_tit">
			<h2>산출물 관리 상세</h2>
		</div>
	</div>
	
	<div class="tbl_esti_detail_total">		
		<div class="tbl_write">
 			<table>
 				<tbody>
	  				<tr>
		  				<th style="width:10%;">사업명</th>
						<td style="width:90%;" colspan="3">
							<span>${outputInfo.oComName}</span>
						</td>
						<th style="width:10%;">사업기간</th>
						<td style="width:15%;">
							<span>${outputInfo.oStartDate}</span>
							~
							<span>${outputInfo.oEndDate}</span>
						</td>
					</tr>
					<tr>
					<th style="width:10%;">담당자</th>
						<td style="width:15%;">
							<span>${outputInfo.oIntroManager}</span>
						</td>
					<th style="width:10%;">담당자연락처</th>
						<td style="width:15%;">
								<span>${outputInfo.oManagerPhone}</span>
						</td>
					<th style="width:10%;">담당자이메일</th>
						<td style="width:15%;">
							<span>${outputInfo.oManagerEmail}</span>
						</td>	
					</tr>
					<tr>
					<th style="width:10%;">담당PM명</th>
						<td style="width:15%;">
							<span>${outputInfo.oPmName}</span>
						</td>
					<th style="width:10%;">담당PM연락처</th>
						<td style="width:15%;">
							<span>${outputInfo.oPmPhone}</span>
						</td>
					<th style="width:10%;">담당PM이메일</th>
						<td style="width:15%;">
							<span>${outputInfo.oPmEmail}</span>
						</td>	
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="tbl_list">          	
		<table>
			<thead>  
	            <tr>
	         		<th style="width:10%">산출물구분</th>
	         		<th style="width:10%">산출물명</th>
	         		<th style="width:10%">파일첨부</th>
	         		<th style="width:10%">비고</th>
	         	</tr>
	         </thead>
	         <tbody>
	         	<c:forEach var="outputItemList" items="${outputItemList}" varStatus="i">
			         <tr>
			         	<td style="width:15%;">
			         		<c:out value="${outputItemList.oOutputItemGubun}" />
						</td>
						<td style="width:15%;">
							<c:out value="${outputItemList.oOutputItemName}" />	
						</td>
			         	<td style="width:15%;">
										<a href="javascript:fn_egov_downFile('${outputItemList.atchFileId}','${outputItemList.fileSn}')">		
									<c:out value="${outputItemList.eIMGregName}"/></a>
						</td>
						<td style="width:15%;">
							<c:out value="${outputItemList.oOutputItemEtc}" />
						</td>
			         </tr>
	         	</c:forEach>
			</tbody>
		</table> 
	</div>
				
	<c:if test="${outputInfo.sSignStatus ne '요청등록'}">
		<div class="content" style="padding-top:20px;">
			<div class="content_tit">
				<h2>결재현황</h2>
			</div>
		</div>
		
		<div class="tbl_write">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%; border-left: 1px solid #bfdaf7;">결재순서</th>
						<th style="width:10%;">결재자</th>
						<th style="width:10%;">결정</th>
						<th style="width:10%;">결정여부</th>
						<th style="width:60%;">반려사유 및 싸인</th>
					</tr>
	        	</thead>
		        <tbody>
		        	
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVo.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:left; padding-left:5px; width:20%;">
								${signList.sSignStaffName}
							</td>
							<td style="text-align:center; width:10%;">
								${signList.sSignDecison}
							</td>
							<td>
								<c:if test="${outputInfo.sSignStatus eq '승인요청' && outputInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
								</c:if>
							</td>
							<td <c:if test="${outputInfo.sSignStatus eq '승인요청' && outputInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${outputInfo.sSignStatus eq '승인요청' && outputInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
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
				</tbody>
			</table>
		</div>
	</c:if>
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${outputInfo.sSignStatus eq '승인요청' && outputInfo.sSignStaffKey eq staffVo.kStaffKey}">
				<li>
					<a style="cursor: pointer;" onclick="setApproval();">결재처리</a>
				</li>
			</c:if>
			<c:if test="${outputInfo.sSignStatus ne '승인요청' && outputInfo.sSignStatus ne '승인'}">
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="delete_go();">삭제</a>
				</li>
			</c:if>
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="update_go();">수정</a>
				</li>
			</c:if>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>
	<div id="setModal" class="modal" style="display:none;">
		<a id="modal-close" href="#close-modal" rel="modal:close" class="close-modal " onclick="closeModal()">Close</a>
		<div class="modal-content">
			<div class="tbl_top" id="modalAddRow">
				<ul class="tbl_top_right">
					<li>
		          		<a class="mes_btn" id="save">저장</a>
			   		</li>
				</ul>
			</div>
			<div class="lf_tbl_list scrolltbody" style="margin-top: 0px; border: 0.5px #d1d1d1 solid; border-radius:5px;">
				<canvas id="signature" width="450" height="200"></canvas>
			</div>
		</div>
	</div>	
</form>