<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


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
	settingSign();
	
	$('.tabs').each(function () {
		const $tabsContainer = $(this);
		const $inputs = $tabsContainer.find('.tab input[type="radio"]');
		const $contents = $tabsContainer.find('.tab_content');

		$inputs.on('change', function () {
			const targetId = '#' + $(this).attr('id') + '_content';
			$contents.hide();
			$tabsContainer.find(targetId).show();
		});

	  $inputs.first().prop('checked', true).trigger('change');
	});
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

// 목록
function cancel(){
	document.frm.action = "/mes/project/kw_project_lf.do";
	document.frm.submit(); 
}

function update_go(){
    
	if(confirm("수정페이지로 이동 하시겠습니까?")){
		document.frm.action = "/mes/project/kw_project_uf.do";
 		document.frm.submit();
	}
}

function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		document.frm.action = "/mes/project/kw_project_d.do";
		document.frm.submit();
	}
}
 

function printPop(setKey){
	var url = "/mes/proreq/popup/kw_proreq_vf_popup.do";
	url += "?eProreqKey="+setKey;
	window.open(url ,"addrAdd" ,"width=1200,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	
}

function changeContent(value){
	var innerStr = "";
	
	if(value == "승인"){
		innerStr += "<a class='form_btn md' onclick='setSign(this, event);'>사인</a>";
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
// 	$("#mloader").show();
	document.frm.action = "/mes/project/kw_project_process_s.do";
	document.frm.submit();
}

//처리내역 등록 
function process_go(){
	document.frm.action = "/mes/project/kw_project_process_if.do";
	document.frm.submit();
}

function eDownloadAll(fileGubun){
	 var fileInputs = document.getElementsByName("eFileRowId");
	   var fileGubuns = document.getElementsByName("eFileRowGubun");
	    for (var i = 0; i < fileInputs.length; i++) {
	        var fileId = fileInputs[i].value;
	        var currentFileGubun = fileGubuns[i].value;
	        if (fileId !== null && fileId !== "" && currentFileGubun === fileGubun) {
	            window.open("/cmm/fms/FileDown.do?atchFileId=" + fileId + "&fileSn=0");
	        }
	    }
}
function eDownload(fileId){
     window.open("/cmm/fms/FileDown.do?atchFileId=" + fileId + "&fileSn=0");
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
	innerStr += "<a class='form_btn md' onclick='setSign(this, event);'>사인</a>";
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



function startApproval(sSignStatus){
	$("#sSignStatus").val(sSignStatus)
	document.frm.action = "/mes/project/kw_project_vfr.do";
	document.frm.submit();
}

function changeContent(value){
	var innerStr = "";
	
	if(value == "승인"){
		innerStr += "<a class='form_btn md' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
	} else if(value == "반려"){
		innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='50'/>";
		innerStr += "<a class='form_btn md' onclick='sSignContentAdd();'>반려 사유 저장</a>";
	}
	document.getElementById("sSignContentSet").innerHTML = innerStr;
}

function sSignContentAdd(){
	var sSignContent = $("#sSignContent").val();
	var sSignTableKey = $("#eProjectNum").val();
	var kStaffKey = $("#kStaffKey").val();
	if(sSignContent != ""){
		$.ajax({
			type : "post"
		,	url : "/mes/issue/kw_uploadSignProjectReasonAjax.do"
		,	data : {
				"sSignTableKey"		: sSignTableKey
			,	"sSignTableName"	: "P_PROJECT"
			,	"sSignStaffKey"		: kStaffKey
			,	"sSignDecison"		: "반려"
			,	"sSignContent"		: sSignContent	
			}
		,	dataType : "json"
		,	async : false
		,	cache : false
		,	success : function(msg){
			 alert("반려처리되었습니다.")
				document.frm.action = "/mes/project/kw_project_vf.do";
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
			var sSignTableKey = $("#eProjectNum").val();
			var kStaffKey = $("#kStaffKey").val();
			
			$.ajax({
				type : "post"
			,	url : "/mes/asset/kw_uploadSignImgAjax.do"
			,	data : {
					"sSignTableKey"		: sSignTableKey
				,	"sSignTableName"	: "P_PROJECT"
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
					document.frm.action = "/mes/project/kw_project_vf.do";
					document.frm.submit();
					
				}
			});
		}
	});
	
}


 


</script>

<form id="frm" name="frm" method="post" >
  <input  type="hidden" id="eProjectNum" name="eProjectNum" value="${projectInfo.eProjectNum}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<div class="content_top">
		<div class="content_tit">
			<h2>프로젝트 상세</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
            		<th>작성자</th>
            		<td>${projectInfo.kStaffName}
            		</td>
            		<th>등록일</th>
            		<td>
						${projectInfo.pWdate}
            		</td>
          		</tr>			
			</tbody>
		</table>
	</div>
	
	<div class="normal_table row">
			<table>
				<tbody>
  				<tr>
	  				<th>사업명</th>
					<td>${projectInfo.eProjectName}
						<input type="hidden" id="eProjectName" name="eProjectName" style="width:90%; text-align: left;padding-left: 5px;"  value="" maxlength="50"/>
					</td>
					<th>사업기간</th>
					<td>${projectInfo.eStartDate} - ${projectInfo.eEndDate}
						<input type="hidden" class="inp_color" id="eStartDate" name="eStartDate" style="width:100px;text-align: center;" value="" readonly /> 
						<input type="hidden" class="inp_color" id="eEndDate" name="eEndDate" style="width:100px;text-align: center;" value="" readonly />
					</td>
					<th>주사업자</th>
					<td>${projectInfo.eMainContractor}
						<input type="hidden" id="eMainContractor" name="eMainContractor" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
				</tr>
				<tr>
					<th>담당자명</th>
					<td>${projectInfo.eManager}
						<input type="hidden" id="eManager" name="eManager" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>담당자연락처</th>
					<td>${projectInfo.eManagerContact}
						<input type="hidden" id="eManagerContact" name="eManagerContact" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>담당자메일</th>
					<td>${projectInfo.eManagerEmail}
						<input type="hidden" id="eManagerEmail" name="eManagerEmail" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
				</tr>
				<tr>
					<th>사업 PM명</th>
					<td>${projectInfo.eProjectPM}
	 					<input type="hidden" id="eProjectPM" name="eProjectPM" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>PM 연락처</th>
					<td>${projectInfo.ePMContact}
						<input type="hidden" id="ePMContact" name="ePMContact" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>PM 메일</th>
					<td>${projectInfo.ePMEmail}
						<input type="hidden" id="ePMEmail" name="ePMEmail" style="width:90%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
				</tr>
				<tr>
					<th>비고</th>
					<td id="td_editor" colspan="5" align="center" scope="row">${projectInfo.eRemarks}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<c:if test="${projectInfo.sSignStatus eq '승인' || projectInfo.sSignStatus eq '제외' }">
		<div class="tabs new_tabs">
			<div class="tab">
			    <label>
			    	<input id="all" type="radio" name="tab_item">
			    	<span>착수 산출물</span>
			    </label>
			    <label>
			    	<input id="programming" type="radio" name="tab_item">
			    	<span>수행 산출물</span>
			    </label>
			    <label>
			    	<input id="design" type="radio" name="tab_item">
			    	<span>완료 산출물</span>
			    </label>
			</div>
		    
		    <div class="tab_content" id="all_content">
		        <div class="content_top innertab">
					<div class="content_tit">
						<h2>착수 산출물 </h2>
					</div>
				</div>
				<div class="normal_table">
					<table>
						<thead>
							<tr>
								<th style="width: 10%;">산출물명</th>
								<th style="width: 10%;">등록일자</th>
								<th style="width: 12%;">소속</th> 
								<th style="width: 12%;">작성자</th> 
								<th style="width: 18%;">첨부파일
									<c:if test="${not empty fileAList}"><span class="form_btn md" onclick="eDownloadAll('A');">모두 받기</span></c:if>
								</th> 
								<th style="width: 18%;">비고</th> 
							</tr>
						</thead>
						<tbody id="lineRowA">
								 <c:forEach var="listA" items="${infoAList}" varStatus="i">
									<tr>
										<td>${listA.eItemName}
											<input type='hidden' id='eItemName_${listA.eItemIndex}' name='eItemName' value='${listA.eItemName}' style='width:98%;'  maxLength='30' />
											<input type='hidden' id='eItemIndex_${listA.eItemIndex}' name='eItemIndex' value='${listA.eItemIndex}'/>
											<input type='hidden' id='eItemGubun_${listA.eItemIndex}' name='eItemGubun' value='A'/>
										</td>
									
										<td>${listA.eItemDate}
											<input type='hidden' id='eItemDate_${listA.eItemIndex}' name='eItemDate' value='${listA.eItemDate}' style='width:98%;'  maxLength='30' />
										</td>		
									
										<td>${listA.eItemAffiliation}
											<input type='hidden' id='eItemAffiliation_${listA.eItemIndex}' name='eItemAffiliation' value='${listA.eItemAffiliation}' style='width:98%;'  maxLength='30' />
										</td>
									
										<td>${listA.eItemAuthor}
											<input type='hidden' id='eItemAuthor_${listA.eItemIndex}' name='eItemAuthor' value='${listA.eItemAuthor}' style='width:98%;'  maxLength='30' />
										</td>
									
										<td>
<%-- 											<a class='form_btn md' onclick="addFile('${listA.eItemIndex}','A');">파일추가</a>  --%>
											<div id='fileList${listA.eItemIndex}'>
											 <c:forEach var="fileA" items="${fileAList}" varStatus="j">
												 <c:if test="${listA.eItemIndex eq fileA.eFileRowIndex}">
												    <div class="fileItem" id="fileItem_${j.index}">
<%-- 													    <a class="del" onclick="delFileRow(this, '${j.index}');">X</a> --%>
												    <span onclick="eDownload('${fileA.eFileRowId}');">${fileA.eFileRowName}</span>
												    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileA.eFileRowIndex}" value="${fileA.eFileRowId}">
												    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileA.eFileRowName}">
												    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="A">
												    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileA.eFileRowIndex}"></div>
												</c:if>
											</c:forEach>
											</div>	
										</td>	
									
										<td>${listA.eItemRemarks}
											<input type="hidden" id='eItemRemarks_${listA.eItemIndex}' name='eItemRemarks' value='${listA.eItemRemarks}'  style='width:98%;'  maxLength='30' />
										</td>	
									
									</tr>
						 </c:forEach>
							<c:if test="${empty infoAList}">
								<tr>
									<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
    	</div>
	    <div class="tab_content" id="programming_content">
	      	  <div class="content_top innertab">
						<div class="content_tit">
							<h2>수행 산출물 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
								<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
										<c:if test="${not empty fileBList}"><span class="form_btn md" onclick="eDownloadAll('B');">모두 받기</span></c:if>
									</th> 
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowB">
									 <c:forEach var="listB" items="${infoBList}" varStatus="i">
										<tr>
											<td>${listB.eItemName}
												<input type='hidden' id='eItemName_${listB.eItemIndex}' name='eItemName' value='${listB.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listB.eItemIndex}' name='eItemIndex' value='${listB.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listB.eItemIndex}' name='eItemGubun' value='B'/>
											</td>
										
											<td>${listB.eItemDate}
												<input type='hidden' id='eItemDate_${listB.eItemIndex}' name='eItemDate' value='${listB.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listB.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listB.eItemIndex}' name='eItemAffiliation' value='${listB.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listB.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listB.eItemIndex}' name='eItemAuthor' value='${listB.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listB.eItemIndex}'>
												 <c:forEach var="fileB" items="${fileBList}" varStatus="j">
													 <c:if test="${listB.eItemIndex eq fileB.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileB.eFileRowId}');">${fileB.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileB.eFileRowIndex}" value="${fileB.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileB.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="B">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileB.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
											<td>${listB.eItemRemarks}
												<input type="hidden" id='eItemRemarks_${listB.eItemIndex}' name='eItemRemarks' value='${listB.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoBList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
		</div>
	    <div class="tab_content" id="design_content">
		       <div class="content_top innertab">
						<div class="content_tit">
							<h2>완료 산출물 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
								<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
										<c:if test="${not empty fileCList}"><span class="form_btn md" onclick="eDownloadAll('C');">모두 받기</span></c:if>
									</th> 
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowC">
									 <c:forEach var="listC" items="${infoCList}" varStatus="i">
										<tr>
											<td>${listC.eItemName}
												<input type='hidden' id='eItemName_${listC.eItemIndex}' name='eItemName' value='${listC.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listC.eItemIndex}' name='eItemIndex' value='${listC.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listC.eItemIndex}' name='eItemGubun' value='C'/>
											</td>
										
											<td>${listC.eItemDate}
												<input type="hidden" id='eItemDate_${listC.eItemIndex}' name='eItemDate' value='${listC.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listC.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listC.eItemIndex}' name='eItemAffiliation' value='${listC.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listC.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listC.eItemIndex}' name='eItemAuthor' value='${listC.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listC.eItemIndex}'>
												 <c:forEach var="fileC" items="${fileCList}" varStatus="j">
													 <c:if test="${listC.eItemIndex eq fileC.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileC.eFileRowId}');">${fileC.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileC.eFileRowIndex}" value="${fileC.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileC.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="C">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileC.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
										
											<td>${listC.eItemRemarks}
												<input type="hidden" id='eItemRemarks_${listC.eItemIndex}' name='eItemRemarks' value='${listC.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoCList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
		</div>
	</div>	
	
	<div class="tabs new_tabs">
		<div class="tab">
		    <label>
		    	<input id="allTwo" type="radio" name="tab_itemTwo" checked>
		    	<span>주간보고</span>
		    </label>
		    <label>
		    	<input id="programmingTwo" type="radio" name="tab_itemTwo">
		    	<span>월간 보고</span>
		    </label>
		    <label>
		    	<input id="designTwo" type="radio" name="tab_itemTwo">
		    	<span>분기 보고</span>
		    </label>
		    <label>
		    	<input id="yearlyTwo" type="radio" name="tab_itemTwo">
		    	<span>반기 보고</span>
		    </label>
		</div>
			    <div class="tab_content" id="allTwo_content">
			        <div class="content_top innertab">
						<div class="content_tit">
							<h2>주간 보고서 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
							<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
									<c:if test="${not empty fileAAList}"><span class="form_btn md" onclick="eDownloadAll('AA');">모두 받기</span></c:if>
									</th> 
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowA">
									 <c:forEach var="listAA" items="${infoAAList}" varStatus="i">
										<tr>
											<td>${listAA.eItemName}
												<input type='hidden' id='eItemName_${listAA.eItemIndex}' name='eItemName' value='${listAA.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listAA.eItemIndex}' name='eItemIndex' value='${listAA.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listAA.eItemIndex}' name='eItemGubun' value='AA'/>
											</td>
										
											<td>${listAA.eItemDate}
												<input type='hidden' id='eItemDate_${listAA.eItemIndex}' name='eItemDate' value='${listAA.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listAA.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listAA.eItemIndex}' name='eItemAffiliation' value='${listAA.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listAA.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listAA.eItemIndex}' name='eItemAuthor' value='${listAA.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listAA.eItemIndex}'>
												 <c:forEach var="fileAA" items="${fileAAList}" varStatus="j">
													 <c:if test="${listAA.eItemIndex eq fileAA.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileAA.eFileRowId}');">${fileAA.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileAA.eFileRowIndex}" value="${fileAA.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileAA.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="AA">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileAA.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
										
											<td>${listAA.eItemRemarks}
												<input type='hidden' id='eItemRemarks_${listAA.eItemIndex}' name='eItemRemarks' value='${listAA.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoAAList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
	    	</div>
	    <div class="tab_content" id="programmingTwo_content">
	      	  <div class="content_top innertab">
						<div class="content_tit">
							<h2>월간 보고서 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
								<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
									<c:if test="${not empty fileBBList}"><span class="form_btn md" onclick="eDownloadAll('BB');">모두 받기</span></c:if>
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowB">
									 <c:forEach var="listBB" items="${infoBBList}" varStatus="i">
										<tr>
											<td>${listBB.eItemName}
												<input type='hidden' id='eItemName_${listBB.eItemIndex}' name='eItemName' value='${listBB.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listBB.eItemIndex}' name='eItemIndex' value='${listBB.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listBB.eItemIndex}' name='eItemGubun' value='BB'/>
											</td>
										
											<td>${listBB.eItemDate}
												<input type='hidden' id='eItemDate_${listBB.eItemIndex}' name='eItemDate' value='${listBB.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listBB.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listBB.eItemIndex}' name='eItemAffiliation' value='${listBB.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listBB.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listBB.eItemIndex}' name='eItemAuthor' value='${listBB.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listBB.eItemIndex}'>
												 <c:forEach var="fileBB" items="${fileBBList}" varStatus="j">
													 <c:if test="${listBB.eItemIndex eq fileBB.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileBB.eFileRowId}');">${fileBB.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileBB.eFileRowIndex}" value="${fileBB.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileBB.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="BB">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileBB.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
										
											<td>${listBB.eItemRemarks}
												<input type='hidden' id='eItemRemarks_${listBB.eItemIndex}' name='eItemRemarks' value='${listBB.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoBBList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
		</div>
	    <div class="tab_content" id="designTwo_content">
		       <div class="content_top innertab">
						<div class="content_tit">
							<h2>분기 보고서 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
								<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
									<c:if test="${not empty fileCCList}"><span class="form_btn md" onclick="eDownloadAll('CC');">모두 받기</span></c:if>
									</th> 
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowC">
									 <c:forEach var="listCC" items="${infoCCList}" varStatus="i">
										<tr>
											<td>${listCC.eItemName}
												<input type='hidden' id='eItemName_${listCC.eItemIndex}' name='eItemName' value='${listCC.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listCC.eItemIndex}' name='eItemIndex' value='${listCC.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listCC.eItemIndex}' name='eItemGubun' value='CC'/>
											</td>
										
											<td>${listCC.eItemDate}
												<input type='hidden' id='eItemDate_${listCC.eItemIndex}' name='eItemDate' value='${listCC.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listCC.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listCC.eItemIndex}' name='eItemAffiliation' value='${listCC.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listCC.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listCC.eItemIndex}' name='eItemAuthor' value='${listCC.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listCC.eItemIndex}'>
												 <c:forEach var="fileCC" items="${fileCCList}" varStatus="j">
													 <c:if test="${listCC.eItemIndex eq fileCC.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileCC.eFileRowId}');">${fileCC.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileCC.eFileRowIndex}" value="${fileCC.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileCC.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="C">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileCC.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
										
											<td>${listCC.eItemRemarks}
												<input type='hidden' id='eItemRemarks_${listCC.eItemIndex}' name='eItemRemarks' value='${listCC.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoCCList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
		</div>
		<div class="tab_content" id="yearlyTwo_content">
		       <div class="content_top innertab">
						<div class="content_tit">
							<h2>반기 보고서 </h2>
						</div>
					</div>
					<div class="normal_table">
						<table>
								<thead>
								<tr>
									<th style="width: 10%;">산출물명</th>
									<th style="width: 10%;">등록일자</th>
									<th style="width: 12%;">소속</th> 
									<th style="width: 12%;">작성자</th> 
									<th style="width: 18%;">첨부파일
									<c:if test="${not empty fileDDList}"><span class="form_btn md" onclick="eDownloadAll('DD');">모두 받기</span></c:if>
									</th> 
									<th style="width: 18%;">비고</th> 
								</tr>
							</thead>
							<tbody id="lineRowD">
									 <c:forEach var="listDD" items="${infoDDList}" varStatus="i">
										<tr>
											<td>${listDD.eItemName}
												<input type='hidden' id='eItemName_${listDD.eItemIndex}' name='eItemName' value='${listDD.eItemName}' style='width:98%;'  maxLength='30' />
												<input type='hidden' id='eItemIndex_${listDD.eItemIndex}' name='eItemIndex' value='${listDD.eItemIndex}'/>
												<input type='hidden' id='eItemGubun_${listDD.eItemIndex}' name='eItemGubun' value='D'/>
											</td>
										
											<td>${listDD.eItemDate}
												<input type='hidden' id='eItemDate_${listDD.eItemIndex}' name='eItemDate' value='${listDD.eItemDate}' style='width:98%;'  maxLength='30' />
											</td>		
										
											<td>${listDD.eItemAffiliation}
												<input type='hidden' id='eItemAffiliation_${listDD.eItemIndex}' name='eItemAffiliation' value='${listDD.eItemAffiliation}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>${listDD.eItemAuthor}
												<input type='hidden' id='eItemAuthor_${listDD.eItemIndex}' name='eItemAuthor' value='${listDD.eItemAuthor}' style='width:98%;'  maxLength='30' />
											</td>
										
											<td>
												<div id='fileList${listDD.eItemIndex}'>
												 <c:forEach var="fileDD" items="${fileDDList}" varStatus="j">
													 <c:if test="${listDD.eItemIndex eq fileDD.eFileRowIndex}">
													    <div class="fileItem" id="fileItem_${j.index}">
													    <span onclick="eDownload('${fileDD.eFileRowId}');">${fileDD.eFileRowName}</span>
													    <input type="hidden" name="eFileRowId" id="eFileRowId_${fileDD.eFileRowIndex}" value="${fileDD.eFileRowId}">
													    <input type="hidden" name="eFileRowName" id="eFileRowName_${j.index}" value="${fileDD.eFileRowName}">
													    <input type="hidden" name="eFileRowGubun" id="eFileRowGubun_${j.index}" value="D">
													    <input type="hidden" name="eFileRowIndex" id="eFileRowIndex_${j.index}" value="${fileDD.eFileRowIndex}"></div>
													</c:if>
												</c:forEach>
												</div>	
											</td>	
										
											<td>${listDD.eItemRemarks}
												<input type='hidden' id='eItemRemarks_${listDD.eItemIndex}' name='eItemRemarks' value='${listDD.eItemRemarks}'  style='width:98%;'  maxLength='30' />
											</td>	
										
										</tr>
							 </c:forEach>
								<c:if test="${empty infoDDList}">
									<tr>
										<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
		</div>
	</div>
	</c:if>	
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
								<c:if test="${projectInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignDecison ne '결재대기' }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${projectInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${projectInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
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
							<td colspan="5" style="text-align: center;">등록 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	
	<div class="bottom_btn">
		<c:if test="${(projectInfo.kStaffKey eq staffVO.kStaffKey && (projectInfo.sSignStatus eq '등록' || projectInfo.sSignStatus eq '반려' || projectInfo.sSignStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (projectInfo.sSignStatus eq '등록' || projectInfo.sSignStatus eq '반려' || projectInfo.sSignStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<span class="info_txt">프로젝트 기본정보만 수정 가능합니다. </span>
				<button type="button" class="form_btn bg ml20" onclick="update_go();">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
			</c:if>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
		<c:if test="${projectInfo.sSignStatus eq '등록'}">
			<c:if test="${projectInfo.kStaffKey eq staffVO.kStaffKey }"> 
				<button type="button" class="form_btn bg" onclick="startApproval('Y');">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${projectInfo.sSignStatus eq '승인요청'}">
			<c:if test="${projectInfo.kStaffKey eq staffVO.kStaffKey && projectInfo.sSignProgress eq '0'}">
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