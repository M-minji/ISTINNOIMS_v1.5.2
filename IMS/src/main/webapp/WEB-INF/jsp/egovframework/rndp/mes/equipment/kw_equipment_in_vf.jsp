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
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<script type="text/javascript">
function modal1(message, position) {
	if(position == null || position == "") {
		position = 65;
	}
	lastScrollY = window.scrollY;
	window.scrollTo(0, 0);
	new jBox('Modal', {
	    height: 200,
	    title: message,
	    blockScrollAdjust: ['header'],
	    content:'',
	    overlay: false,   
	    addClass: 'no-content-modal',
	    position: {
	        x: 'center',
	        y: 'top'
	      },
	      offset: {
	        y: position  //65
	      },
	        onCloseComplete: function () {
	        	window.scrollTo(0, lastScrollY);
	        }
	  }).open();
  }
function modal3(message, onConfirm, onCancel) {
	new jBox('Confirm', {
		content: message,
	    cancelButton: '아니요',
	    confirmButton: '네',
	    blockScrollAdjust: ['header'],
	    confirm: onConfirm,
	    cancel: onCancel
	  }).open();
  }
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
	      case 'update':
	        message = "수정이 완료되었습니다!";
	        break;
	      case 'sign':
	        message = "승인되었습니다.";
	        break;
	      case 'reject':
		        message = "반려되었습니다.";
		        break;
	      default:
	        message = ""; 
	    }
		notice(message);
	    sessionStorage.removeItem("actionType");
  }
});
	$(document).ready(function(){
		settingSign(); // 싸인 설정
	});
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/equipment/kw_equipment_in_lf.do";
		document.writeForm.submit(); 
	}
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
		
		  // eAssetKey의 개수를 다시 계산
	    var eAssetKeyArr = document.getElementsByName("eAssetKey").length;
	    
	    // eAssetKey의 개수가 0이면 메시지를 추가
	    if (eAssetKeyArr == 0) {
	        var tbody = document.getElementById("lineRow");
	        var messageRow = document.createElement('tr');
	        var messageCell = document.createElement('td');
	        
	        messageCell.colSpan = 10;
	        messageCell.textContent = "장비를 선택하세요.";
	        
	        messageRow.appendChild(messageCell);
	        tbody.appendChild(messageRow);
	    }
	}
	function update_go(){
		$('#mloader').show();
		document.writeForm.action = "/mes/equipment/kw_equipment_in_uf.do";
		document.writeForm.submit();
	}
	
	function delete_go(){
		modal3("삭제하시겠습니까?", function () {
			sessionStorage.setItem("actionType", "delete");
			document.writeForm.action = "/mes/equipment/kw_equipment_in_d.do";
			document.writeForm.submit();
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
//					window.open(img); // 이미지를 윈도우 팝업으로..
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

	function startApproval(eStatus){
		$("#mloader").show();
		$("#eStatus").val(eStatus)
		document.writeForm.action = "/mes/equipment/kw_equipment_in_vfr.do";
		document.writeForm.submit();
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
				modal1("사인을 해주세요.", 415);
				return;
			} 
			modal3("승인하시겠습니까?", function () {
				
				//저장버튼시 부서, 날짜, 서명을 저장한다.
				var data = signature.toDataURL("image/png");
				var eEquipmentInKey = $("#eEquipmentInKey").val();
				var kStaffKey = $("#kStaffKey").val();
				
				$.ajax({
					type : "post"
				,	url : "/mes/asset/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignTableKey"		: eEquipmentInKey
					,	"sSignTableName"	: "E_EQUIPMENT_IN"
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
						sessionStorage.setItem("actionType", "sign");
						document.writeForm.action = "/mes/equipment/kw_equipment_in_vf.do";
						document.writeForm.submit();
						
					}
				});
			});
		});
		
	}

	
	function changeContent(value){
		var innerStr = "";
		
		if(value == "승인"){
			innerStr += "<a class='form_btn bg' onclick='setSign(this, event);'>사인</a>";
			innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		} else if(value == "반려"){
			innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:500px' maxLength='50'/>";
			innerStr += "<a class='form_btn bg ml5' onclick='sSignContentAdd();'>반려</a>";
		}
		document.getElementById("sSignContentSet").innerHTML = innerStr;
	}

	function sSignContentAdd(){
		var sSignContent = $("#sSignContent").val();
		var eEquipmentInKey = $("#eEquipmentInKey").val();
		var kStaffKey = $("#kStaffKey").val();
		if(sSignContent != ""){
			
		}else{
			modal1("반려사유를 입력하세요.");
			return;
		}
		modal3("반려하시겠습니까?", function () {
			$.ajax({
				type : "post"
			,	url : "/mes/equipment/kw_uploadSignEquipmentReasonAjax.do"
			,	data : {
					"sSignTableKey"		: eEquipmentInKey
				,	"sSignTableName"	: "E_EQUIPMENT_IN"
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "반려"
				,	"sSignContent"		: sSignContent	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
					sessionStorage.setItem("actionType", "reject");
					document.writeForm.action = "/mes/equipment/kw_equipment_in_vf.do";
					document.writeForm.submit();
				}
			});
		});
	}
</script>
<style>
	.no-content-modal .jBox-content {
  		display: none; 
	}

	.no-content-modal .jBox-title {
		padding-bottom: 10px;
	}
	
	.no-content-modal .jBox-title {
	  	color: white;
	}
	
	.jBox-Modal {
	  background: #4869fb !important;
	  border-radius: 8px !important;
   	  overflow: hidden !important;
	}    
</style>
<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="searchWord" id="searchWord" value="${mesEquipmentVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesEquipmentVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesEquipmentVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesEquipmentVO.searchType}" />
	<input type="hidden" id="eEquipmentInKey" name="eEquipmentInKey" value="${mesEquipmentVO.eEquipmentInKey}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	<input type="hidden" id="eStatus" name="eStatus" value="${info.eStatus}" />	
	<div class="content_top">	
		<div class="content_tit">
			<h2>반입정보 상세</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
					<tr>
	            		<th>작성자</th>
            			<td>${info.kStaffName }</td>
	            		
            			<th>작성일</th>
            			<td>${info.eEntryWdate }</td>
          			</tr>					
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
		<table>
			<colgroup>
				<col style="width: 200px;"/>
				<col />
				<col style="width: 200px;"/>
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th>반입일</th>
					<td colspan="3"> ${info.eEntryImportDate}
					</td>
				</tr>
				<tr>
					<th>반입확인자</th>
					<td> ${info.eEntryImporter}
					</td>
					<th>반입확인자 소속</th>
					<td><span id="eEntryImporterOrgTxt">${info.eEntryImporterOrg}</span>
					</td>
				</tr>
				<tr>
					<th>반입사유</th>
					<td colspan="1">${info.eEntryNote}
					</td>
					<th>반입처</th>
					<td colspan="1">${info.eEntryLocation}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 
	<div class="content_top nofirst with_btn notit" id="viewDiv1">
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="sel_asset()">반출 장비 선택</button>
		</div>
	</div>
	 -->
	 
	<div class="normal_table">
		<table>
				<thead>
				<tr>
					<th style="width: 8%;">구분</th>
					<th style="width: 10%;">자산유형</th>
					<th style="width: 12%;">자산명</th>
					<th style="width: 10%;">제조사</th>
					<th style="width: 10%;">제조번호</th>
					<th style="width: 10%;">모델명</th>
					<th style="width: 10%;">망구분</th>
					<th style="width: 10%;">HOSTNAME</th>
					<th style="width: 10%;">IP</th>
					<th style="width: 10%;">OS</th>
					<th style="width: 10%;">반출일</th>
					<th style="width: 10%;">반출확인자</th>
				</tr>
			</thead>
			<tbody id="lineRow">
				<c:forEach var="list" items="${assetList}" varStatus="i">
				 		 <tr>
						 	<td>${i.index+1}
						 	</td>
						 	<td>${list.eAssetTypeName }
						 		<input type="hidden" id="eAssetPurpose_${i.index}" name="eAssetPurpose" value="${list.eAssetPurpose}" />
						 		<input type="hidden" id="eAssetType_${i.index}" name="eAssetType" value="${list.eAssetType}"/>
						 		<input type="hidden" id="eAssetDeviceType_${i.index}" name="eAssetDeviceType" value="${list.eAssetDeviceType}" />
						 	</td>
						 
						 	<td>${list.eAssetName}	</td>		
						 
						 	<td>${list.eAssetMaker} 	</td>
						 
						 	<td>${list.eAssetSNumber} 	</td>
						 
						 	<td>${list.eAssetModel} 	</td>	
						 
						 	<td>${list.eAssetNetworkType}	</td>	
						 
						 	<td>${list.eAssetHostName}	 	</td>	
						 
						 	<td>${list.eAssetIp}	 	</td>	
						 	<td>${list.eAssetOs}	 	</td>	
						 	
						 	<td>${list.eEntryExporterDate}	 	</td>	
						 	<td>${list.eExitExporter}	 	</td>	
						 </tr>
			 	</c:forEach>
			</tbody>
		</table>
	</div>
	
	<c:if test="${not empty signList}">
		<div class="content_top nofirst" style="padding-top:20px;">
			<div class="content_tit">
				<h2>결재 정보</h2>
			</div>
		</div>
		<div class="normal_table">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%;">결재순서</th>
						<th style="width:8%;">결재자</th>
						<th style="width:8%;">결정</th>
						<th style="width:15%;">결재구분</th>
						<th style="width:60%;">서명 또는 반려사유</th>
					</tr>
	        	</thead>
		        <tbody>
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:center; padding-left:5px; width:10%;">
								${signList.sSignStaffName}
							</td>
							<td style="text-align:center; width:10%;">
								${signList.sSignDecison} 
							</td>
							<td>
							<!--  ${signList.sSignStaffGubun}: -->
								<c:if test="${info.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignStaffKey ne staffVO.kStaffKey || (info.sSignStatus eq '승인요청' && signList.sSignDecison eq '승인') || info.sSignStatus eq '반려'}">${signList.sSignDecison}</c:if>
								<c:if test="${signList.sSignDecison ne '결재대기' && signList.sSignStaffKey eq staffVO.kStaffKey }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${info.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${info.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
						        		<a class="form_btn md" onclick="setSign(this, event);">사인</a>
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
		<c:if test="${info.kStaffKey eq staffVO.kStaffKey || staffVO.kAdminAuth eq 'T'}">
			<c:if test="${info.sSignStatus ne '승인' && info.sSignStatus ne '제외'}"> 
				<c:choose>
					<c:when test="${info.sSignStatus eq '등록'}"> 
						<button type="button" class="form_btn active" onclick="startApproval('Y');">승인요청</button>
					</c:when>
					<c:when test="${info.sSignProgress eq '0'}"> 
						<button type="button" class="form_btn active" onclick="startApproval('N');">요청취소</button>
					</c:when>
				</c:choose>
			</c:if>
			<c:choose>
				<c:when test="${info.sSignStatus eq '등록' || info.sSignStatus eq '반려' || info.sSignStatus eq '제외'}"> 
					<c:choose>
						<c:when test="${staffVO.kAdminAuth eq 'T'}"> 
							<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
							<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
						</c:when>
						<c:otherwise>
							<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'}">
								<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
							</c:if>
							<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'}">
								<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${info.sSignStatus eq '승인' && staffVO.kAdminAuth eq 'T'}">
					<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
					<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
				</c:when>
			</c:choose>
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
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
