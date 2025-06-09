<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>
<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">
	
	//상세보기 엑셀 다운
	function eExcelDownload() {
	    document.frm.action = "kw_blueprint_download.do";
	    document.frm.submit();
	    document.frm.action = "mes/blueprint/kw_blueprint_vf.do";
	}


	// 저장
	function update_go(){
		$("#mloader").show();
		$("#newPrint").val("old");
		document.frm.action = "/mes/blueprint/kw_blueprint_uf.do";
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
		if(confirm("해당 등록정보를 삭제하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/blueprint/kw_blueprint_d.do";
			document.frm.submit();
		}
	}

	// 삭제
	function delete_item_go(ver){
		$("#version").val(ver);
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_item_d.do";
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
	function process_go(key,blueprintItemKey){
		$("#mloader").show();
		$("#version").val(key);
		$("#blueprintItemKey").val(blueprintItemKey);
		$("#newPrint").val("new");
		document.frm.action = "/mes/blueprint/kw_blueprint_process_if.do";
		document.frm.submit();
	}

	//상세 등록 
	function process_sing(key,blueprintItemKey){
		$("#mloader").show();
		$("#version").val(key);
		$("#blueprintItemKey").val(blueprintItemKey);
		$("#newPrint").val("new");
		document.frm.action = "/mes/blueprint/kw_blueprint_process_vf.do";
		document.frm.submit();
	}

	function requestSign(blueprintItemKey, sSignKey, gubun){
		$("#mloader").show();
		$("#gubun").val(gubun);
		$("#blueprintItemKey").val(blueprintItemKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/blueprint/kw_blueprint_process_r.do";
		document.frm.submit();
	}
	
	

	function eDownload(eFileId){
	 if(confirm("선택한 파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eFileId+"&fileSn=0'/>");
		}
	}

	function eDownloadAll(){
		 var fileInputs = document.getElementsByName("eFileRowId");
		    for (var i = 0; i < fileInputs.length; i++) {
		        var fileId = fileInputs[i].value;
		            window.open("/cmm/fms/FileDown.do?atchFileId=" + fileId + "&fileSn=0");
		    }
	}
	
	$(document).ready(function(){
		settingSign();
	});
	

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
	
	

	function startApproval(sSignStatus){
		$("#mloader").show();
		$("#sSignStatus").val(sSignStatus)
		document.frm.action = "/mes/blueprint/kw_blueprint_vfr.do";
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
		var sSignTableKey = $("#eChangeKey").val();
		var kStaffKey = $("#kStaffKey").val();
		if(sSignContent != ""){
			$.ajax({
				type : "post"
			,	url : "/mes/issue/kw_uploadSignblueprintReasonAjax.do"
			,	data : {
					"sSignTableKey"		: sSignTableKey
				,	"sSignTableName"	: "I_CHANGE"
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "반려"
				,	"sSignContent"		: sSignContent	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
				 alert("반려처리되었습니다.")
					document.frm.action = "/mes/blueprint/kw_blueprint_vf.do";
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
				var sSignTableKey = $("#eChangeKey").val();
				var kStaffKey = $("#kStaffKey").val();
				
				$.ajax({
					type : "post"
				,	url : "/mes/asset/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignTableKey"		: sSignTableKey
					,	"sSignTableName"	: "I_CHANGE"
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
						document.frm.action = "/mes/blueprint/kw_blueprint_vf.do";
						document.frm.submit();
						
					}
				});
			}
		});
		
	}
</script>
<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />
	<input type="hidden" id="eChangeKey" name="eChangeKey" value="${info.eChangeKey}" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="${info.sSignStatus}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	<div class="content_top">
		<div class="content_tit">
			<h2>변경관리 상세</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${info.kStaffName}
					</td>
					<th >등록일</th>
					<td >
						${info.blueprintWdate}
					</td>
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
					<th>요청일자</th>
					<td colspan="3">${info.eReqDate }
						<input type="hidden" id="eReqDate" name="eReqDate" value="${info.eReqDate }" style="width:150px; text-align:center;" class="inp_color" readonly   />
					</td>
	  			 
				</tr>
  				<tr>
	  				<th>요청자</th>
					<td>${info.eRequester }
						<input type="hidden" name="eRequester" id="eRequester" style="width:95%; text-align:left;" maxLength="50" value="${info.eRequester }" />
						<input type="hidden" name="eReqOrg" id="eReqOrg" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqOrg }" />
						<input type="hidden" name="eReqDept" id="eReqDept" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqDept }" />
					</td>
					<th>요청자 소속</th>
					<td>${info.eRequesterOrg }
						<input type="hidden" name="eRequesterOrg" id="eRequesterOrg" style="width:95%; text-align:left;" maxLength="50" value="${info.eRequesterOrg }" />
					</td>
				</tr>
  				
				<tr>
					<th>
						요청내용
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
							${info.eReqContent }
					</td>
				</tr>
				<tr>
	  				<th>변경시작일시 </th>
					<td>${info.eWorkStart }
						<input type="hidden" name="eWorkStart" id="eWorkStart" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkStart }" />
					</td>
					<th>변경완료일시</th>
					<td>${info.eWorkEnd }
						<input type="hidden" name="eWorkEnd" id="eWorkEnd" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkEnd }" />
					</td>
				</tr>
				<tr>
	  				<th>요청사유</th>
					<td>${info.eWorkDetails }
						<input type="hidden" name="eWorkDetails" id="eWorkDetails" style="width:150px;" maxLength="50" value="${info.eWorkDetails }"  />
					</td>
					<th>중단여부</th>
					<td>${info.eIsInterruptedName }
						<input type="hidden" name="eIsInterrupted" id="eIsInterrupted" style="width:95%; text-align:left;" maxLength="50" value="${info.eIsInterrupted }" />
						<input type="hidden" name="eIsInterruptedName" id="eIsInterruptedName" style="width:95%; text-align:left;" maxLength="50" value="${info.eIsInterruptedName }" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
 	 <div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>상세 내역 정보</h2>
		</div>
		<div class="btns">
			<c:if test="${not empty aFileList}">
<!-- 				   <button class="form_btn md" onclick="add_row()" style="float:right">행추가 </button> -->
				<button type="button" class="form_btn md" onclick="eDownloadAll();">파일 모두 받기</button>
			</c:if>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
			<thead>
				<tr>
<!-- 					<th style="width: 8%;">구분</th> -->
					<th style="width: 200px;">작업자</th>
					<th style="width: 12%;">작업자소속</th>
					<th style="width: 12%;">작업일시</th>
					<th style="width: *;">내용</th>
					<th style="width: 16%;text-align: center;">첨부 파일</th>
				</tr>
			</thead>
			<tbody id="lineRowTwo">
				<c:forEach var="aDeteliList" items="${aDeteliList}" varStatus="i">
					<tr>
<!-- 											<td> -->
<!-- 												<a class='del' onclick="delRow(this,'DD');">X</a> -->
<!-- 											</td> -->
					
						<td>${aDeteliList.eWorker}
							<input type="hidden" id='eDepartment_${i.index}' name='eDepartment' value='${aDeteliList.eDepartment}' maxLength='30'/>
							<input type="hidden" id='eRowIndex_${i.index}' name='eRowIndex' value='${aDeteliList.eRowIndex}' maxLength='30'/>
						</td>
					
						<td>${aDeteliList.eDepartment}															
							<input type="hidden" id='eWorker_${i.index}' name='eWorker' value='${aDeteliList.eWorker}' maxLength='30'/>
						</td>		
					
						<td>${aDeteliList.eWorkDate}
							<input type="hidden" id='eWorkDate_${i.index}' name='eWorkDate' value='${aDeteliList.eWorkDate}' maxLength='30'/>
						</td>
					
						<td>${aDeteliList.eDescription}
							<input type="hidden" id='eDescription_${i.index}' name='eDescription' value='${aDeteliList.eDescription}' maxLength='30'/>
						</td>
					
						<td>  
<%-- 											<a class='mes_btn' onclick="addFile('${aDeteliList.eRowIndex}');">파일추가</a>  --%>
							<div id='fileList${aDeteliList.eRowIndex}'>
							 <c:forEach var="aFileList" items="${aFileList}" varStatus="j">
								 <c:if test="${aDeteliList.eRowIndex eq aFileList.eFileRowIndex}">
								    <div class="fileItem" id="fileItem_${j.index}">
<%-- 													    <a class="del" onclick="delFileRow(this, '${j.index}');">X</a> --%>
								    <span onclick="eDownload('${aFileList.eFileRowId}');">${aFileList.eFileRowName}</span>
								    <input type="hidden" name="eFileRowId" id="eFileRowId_${aFileList.eFileRowIndex}" value="${aFileList.eFileRowId}">
								    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${aFileList.eFileRowName}">
								    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${aFileList.eFileRowIndex}"></div>
								</c:if>
							</c:forEach>
							</div>	
						</td>	
					
					</tr>
				 </c:forEach>
			
				<c:if test="${empty aDeteliList }">
					<tr>
						<td colspan="5">등록된 상세내역이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	 <div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>장비 정보</h2>
		</div>
		<div class="btns">
<!-- 				   <button class="form_btn md" onclick="sel_asset()">장비 선택</button> -->
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
			<thead>
				<tr>
<!-- 					<th style="width: 10%;">구분</th> -->
					<th style="width: 12%;">자산유형</th>
					<th style="width: 12%;">자산번호</th>
					<th style="width: 12%;">자산명</th>
					<th style="width: 12%;">모델명</th>
					<th style="width: 12%;">망구분</th>
					<th style="width: 10%;">HOSTNAME</th>
					<th style="width: 10%;">IP</th>
					<th style="width: 10%;">OS</th>
				</tr>
			</thead>
			<tbody id="lineRow">
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr>
<!-- 						<td> -->
<!-- 							<a class='del' onclick="delRow(this);">X</a> -->
<!-- 						</td> -->
						
						<td> ${list.aAssetType}   
							<input type='hidden' id='eAssetKey_${i.index}' name='eAssetKey' value='${list.eAssetKey} '/>
						</td>
						
						<td>${list.aAssetNumber} 	 </td>		
						<td>${list.aAssetName}		</td>
						<td>${list.aAssetModel} 		</td>
						<td>${list.eNetworkType}  		</td>	
						<td>${list.eHostName} 			</td>	
						<td>${list.eIp}		</td>	
						<td>${list.eOs} 	</td>			
						
					</tr>
				</c:forEach>
			
				<c:if test="${empty assetList}">
					<tr>
						<td colspan="8">등록정보가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
<!-- 	결재정보 요청등록시. -->
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
						<th style="width:5%;">결재순서</th>
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
								<c:if test="${info.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignDecison ne '결재대기' }">${signList.sSignDecison}</c:if>
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
		<button type="button" class="topdown" onclick="eExcelDownload();">다운로드</button>
		<c:if test="${(info.kStaffKey eq staffVO.kStaffKey && (info.sSignStatus eq '등록' || info.sSignStatus eq '반려' || info.sSignStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (info.sSignStatus eq '등록' || info.sSignStatus eq '반려' || info.sSignStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
			</c:if>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
		<c:if test="${info.sSignStatus eq '등록'}">
			<c:if test="${info.kStaffKey eq staffVO.kStaffKey }">
				<button type="button" class="form_btn bg" onclick="startApproval('Y');">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${info.sSignStatus eq '승인요청'}">
			<c:if test="${info.kStaffKey eq staffVO.kStaffKey && info.sSignProgress eq '0'}">
				<button type="button" class="form_btn bg" onclick="startApproval('N');">요청취소</button>
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