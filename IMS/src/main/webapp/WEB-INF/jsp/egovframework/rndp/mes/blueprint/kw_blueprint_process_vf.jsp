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

	// 오늘 날짜
	function nowDate(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		var nowDate = year + "-" + month + "-" + day;

		return nowDate;
	}

	// 저장
	function insert_go(){
		$("#mloader").show();
		$("#newPrint").val("old");
		document.frm.action = "/mes/blueprint/kw_blueprint_process_i.do";
		document.frm.submit();
	}

	// 저장
	function update_go2(version){
		$("#mloader").show();
		$("#version").val(version);
		$("#newPrint").val("new");
		document.frm.action = "/mes/blueprint/kw_blueprint_uf.do";
		document.frm.submit();
	}


	// 삭제
	function delete_go(){
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_d.do";
		document.frm.submit();
	}


	// 목록
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_lf.do";
		document.frm.submit();
	}




	//파일다운
	function fn_egov_viewFile_md(id, extension){
		var url = "";
		
		if(extension != "pdf"){
			url = "<c:url value='/cmm/fms/getImage.do'/>?atchFileId="+id+"&fileSn=0"
			fn_egov_viewFile(url);
		}else{
			url = "<c:url value='/cmm/fms/getPdf.do'/>?atchFileId="+id+"&fileSn=0"
			fn_view_pdf(url);
		}
	}

	// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}

	
	//처리내역 등록 
	function process_go(key){
		$("#mloader").show();
		$("#outputKey").val(key);
		document.frm.action = "/mes/output/kw_output_process_if.do";
		document.frm.submit();
	}

	//상세 등록 
	function maintanceView(key){
		$("#mloader").show();
		$("#outputKey").val(key);
		document.frm.action = "/mes/output/kw_output_vf.do";
		document.frm.submit();
	}

	function requestSign(outputKey, sSignKey){
		$("#mloader").show();
		$("#outputKey").val(outputKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/output/kw_output_process_r.do";
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
		setApproval();
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

	function printPop(setKey){
		var url = "/mes/proreq/popup/kw_proreq_vf_popup.do";
		url += "?eProreqKey="+setKey;
		window.open(url ,"addrAdd" ,"width=1200,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
		
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
		document.frm.action = "/mes/blueprint/kw_blueprint_process_s.do";
		document.frm.submit();
	}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/blueprint/kw_blueprint_process_vf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesBlueprintVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesBlueprintVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />
	<input type="hidden" id="blueprintKey" name="blueprintKey" value="${mesBlueprintVO.blueprintKey}" />
	<input type="hidden" id="blueprintItemKey" name="blueprintItemKey" value="${mesBlueprintVO.blueprintItemKey}" />
	<input type="hidden" id="version" name="version" value="${mesBlueprintVO.version}" />
	<input type="hidden" id="newPrint" name="newPrint" value="${mesBlueprintVO.newPrint}" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="${blueprintItemInfo.sSignKey}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVo.kStaffKey}" />

	<div class="content">
		<div class="content_tit">
			<h2>변경/문제 상세</h2>
		</div>
	</div>

	<div class="tbl_esti_detail_total">
		<div class="tbl_write">
			<table>
				<tbody>
				<tr>
					<th>번호</th>
					<td>
						${blueprintInfo.blueprintNumber}
					</td>
					<th>등록일</th>
					<td>
						${blueprintInfo.blueprintWdate}
					</td>
				</tr>
				<tr>
					<th>*사유</th>
					<td>
						${blueprintInfo.eItemName}
					</td>
					<th>분류</th>
					<td>
						${blueprintInfo.eItemCatePath}
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>

	<%--	<div class="tbl_top">--%>
	<%--		<ul class="tbl_top_left">--%>
	<%--			<a style="cursor: pointer;" onclick="addRow();" >--%>
	<%--				행추가--%>
	<%--			</a>--%>
	<%--		</ul>--%>
	<%--	</div>--%>

	<div class="tbl_list">
	   		<table>
	   			<thead>
					<tr>
						<th style="width: 45%;">파일명</th>
						<th style="width: 50%;">비고</th>
				    </tr>
		    	</thead>
		  		<tbody id="fileUL">
		  			<c:forEach var="fileList2" items="${fileList2}" varStatus="status" >
		  				<tr>
							<c:if test="${fileList2.eBlueprintFileGubun eq '도면'}">
 								<td id="filename_${status.index}">
		 							<a href="javascript:fn_egov_downFile('${fileList2.eAddFileId7}','0')" style="text-decoration:none;">
		 								${fileList2.atchFileName}
		 							</a>
					  				<input type='hidden' id='fileKey'   name='fileKey' value='0' />
								 	<input type='hidden' id='AddFileId' name='eAddFileId1' value='${fileList2.eAddFileId7}' />
								 	<input type='hidden' id='atchFileName' name='atchFileName1' value='${fileList2.atchFileName}' />
						 		</td>
 								<td>
								 	${fileList2.eBlueprintItemEtc}
						 		</td>
					     	</c:if>	
					     </tr>
	     			</c:forEach>
	     		</tbody>
	     		<thead>
						<tr>
							<th style="text-align:center;" colspan="2">
								요청 내용
							</th>
						</tr>
				</thead>
				<tbody>
							<tr>
								<td style="text-align:center;" colspan="2">
									${blueprintInfo.blueprintEtc}
								</td>
							</tr>
		  		</tbody>
			</table>
		</div>

	<c:if test="${outputInfo.sSignStatus ne '요청등록' && outputInfo.sSignStatus ne '처리등록'}">
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
								<c:if test="${blueprintItemInfo.sSignStatus eq '승인요청' && blueprintItemInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
								</c:if>
							</td>
							<td <c:if test="${blueprintItemInfo.sSignStatus eq '승인요청' && blueprintItemInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${blueprintItemInfo.sSignStatus eq '승인요청' && blueprintItemInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
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
			<c:if test="${blueprintItemInfo.sSignStatus eq '승인요청' && blueprintItemInfo.sSignStaffKey eq staffVo.kStaffKey}">
				<li>
					<a style="cursor: pointer;" onclick="setApproval();">결재처리</a>
				</li>
			</c:if>
			<c:if test="${blueprintItemInfo.sSignStatus ne '승인요청' && blueprintItemInfo.sSignStatus ne '승인'}">
				<c:if test="${blueprintItemInfo.sSignStatus eq '처리등록'}">
					<li>
						<a style="cursor: pointer;" onclick="startApproval('${blueprintItemInfo.sSignStatus}');">승인요청</a>
					</li>
				</c:if>
				<c:if test="${blueprintItemInfo.sSignStatus eq '요청등록'}">
					<li>
						<a style="cursor: pointer;" onclick="process_go();">처리내역 등록</a>
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