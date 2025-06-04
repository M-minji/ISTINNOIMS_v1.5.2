<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	datepickerIdSet("eReqDate");
	datepickerIdSet("blueprintWdate");
	
	var sSignStatus  = $("#sSignStatus").val();
	if(sSignStatus == "등록"  || sSignStatus == "반려"){
		$("#oSignPass").val("N");
		 $('#oPass').prop('checked', false);
	}else{
		 $('#oPass').prop('checked', true);
		$("#oSignPass").val("Y");
	}
	
});


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
	function update_go(){  
		if(chkIns()){
			if(confirm("저장하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/blueprint/kw_issue_u.do";
				document.frm.submit();
			}
		}
	}
	
	function chkIns(){
		if($("#eRequester").val() == ""){
			alert("작성자 명을 입력하세요.");
			$("#eRequester").focus();
			return false;
		}
		

		var idx = document.getElementsByName("eDepartment").length;
		
		if(idx > 0){
			for(var i=0; i < idx ; i++){
				var eWorker = document.getElementsByName("eWorker")[i].value;
				if (eWorker.trim() == '') {
					alert((i+1)+"번째 작업자를 입력하세요.");
					document.getElementsByName("eWorker")[i].focus;
					return false;
				}
			}
			
		}
		
		
		
		var eRowIdx = document.getElementsByName("eRowWorker").length;
		
		if(eRowIdx > 0){
			for(var j=0; j < eRowIdx ; j++){
				var eWorker = document.getElementsByName("eRowWorker")[j].value;
				if (eWorker.trim() == '') {
					alert((j+1)+"번째 회의 참석자를 입력하세요.");
					document.getElementsByName("eRowWorker")[j].focus;
					return false;
				}
			}
 
		}
		
		
		
		if($("#oSignPass").val() != 'Y'){
			if(document.getElementsByName("sSignStaffKey").length == 0){
				alert("승인권자를 선택해주세요");
				return false;
				}
			}
		$("#eIssueCause").val($("<div>").text($("#eIssueCause").val()).html()); 
		$("#eSolutionMethod").val($("<div>").text($("#eSolutionMethod").val()).html());
		$("#eWorkStart").val($("<div>").text($("#eWorkStart").val()).html());
		$("#eWorkEnd").val($("<div>").text($("#eWorkEnd").val()).html());

		if(idx > 0){
			for(var i=0; i < idx ; i++){
				var eDepartment = document.getElementsByName("eDepartment")[i].value;
				var eWorker = document.getElementsByName("eWorker")[i].value;
				var eWorkDate = document.getElementsByName("eWorkDate")[i].value;
				var eDescription = document.getElementsByName("eDescription")[i].value;
				
				document.getElementsByName("eDepartment")[i].value =  newConverttoHtml(eDepartment);
				document.getElementsByName("eWorker")[i].value =  newConverttoHtml(eWorker);
				document.getElementsByName("eWorkDate")[i].value = newConverttoHtml(eWorkDate);
				document.getElementsByName("eDescription")[i].value = newConverttoHtml(eDescription);
			}
		}
		
		if(eRowIdx > 0){
			for(var j=0; j < eRowIdx ; j++){
				var eRowDepartment = document.getElementsByName("eRowDepartment")[j].value;
				var eRowWorker = document.getElementsByName("eRowWorker")[j].value;
				var eRowPosition = document.getElementsByName("eRowPosition")[j].value;
				var eRowContact = document.getElementsByName("eRowContact")[j].value;
				var eRowNotes = document.getElementsByName("eRowNotes")[j].value;
				
				document.getElementsByName("eRowDepartment")[j].value = newConverttoHtml(eRowDepartment);
		        document.getElementsByName("eRowWorker")[j].value = newConverttoHtml(eRowWorker);
		        document.getElementsByName("eRowPosition")[j].value = newConverttoHtml(eRowPosition);
		        document.getElementsByName("eRowContact")[j].value = newConverttoHtml(eRowContact);
		        document.getElementsByName("eRowNotes")[j].value = newConverttoHtml(eRowNotes);
				
			}
		}
		$("#eReqContent").val($("<div>").text($("#eReqContent").val()).html());
		return true;
	}
		
	function decodeHtmlEntities(str) {
	    const textarea = document.createElement("textarea");
	    textarea.innerHTML = str;
	    return textarea.value;
	}

	function newConverttoHtml(str) {  
		 // 먼저 텍스트로 변환 후 HTML 엔티티를 처리
	    str = $("<div>").text(str).html();  // HTML 특수 문자를 변환
	    // 콤마만 HTML 엔티티로 변환
	    str = str.replace(/\,/g, "&#44;");
	    // 작은따옴표 변환
	    str = str.replace(/'/g, "&#39;");
	    // 큰따옴표 변환
	    str = str.replace(/"/g, "&quot;");
	    return str;
	}
	// 삭제
	function delete_go(){
		if(confirm("삭제하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/blueprint/kw_issue_d.do";
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
		document.frm.action = "/mes/blueprint/kw_issue_lf.do";
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
	
	

	function sel_asset(){	
		// 동적으로 폼 생성
	    const form = document.createElement("form");
	    form.method = "POST";
	    form.action = "/mes/asset/kw_asset_box_lf.do";
	    form.target = "AddrAdd"; // 새 창 이름
	    
	    const csrfTokenGubun = document.createElement("input");
	    csrfTokenGubun.type = "hidden";
	    csrfTokenGubun.name = "csrfToken";
	    csrfTokenGubun.value = $("input[name=csrfToken]").val();
	    form.appendChild(csrfTokenGubun);
	    
	    const kMenuKeyGubun = document.createElement("input");
	    kMenuKeyGubun.type = "hidden";
	    kMenuKeyGubun.name = "kMenuKey";
	    kMenuKeyGubun.value = "${key}";
	    form.appendChild(kMenuKeyGubun);

	    // 폼을 문서에 추가
	    document.body.appendChild(form);

	    // 새 창 열기
	    window.open("", "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");

	    // 폼 전송
	    form.submit();

	    // 폼 제거
	    document.body.removeChild(form);
	}

	function setAssetReturnPop(obj){  
		$.ajax({
				type		: "post"
			,	dataType	: "json"
			,	url			: "/mes/asset/kw_getAssetInfoList.do"
			,	data		: {
					eAssetKey : obj.aAssetKeyList
				}
			,	success		: function(msg){
				var eAssetInfoList  = msg.result.dataList;
					for(var i = 0; i < eAssetInfoList.length; i++){
						setAssetPop(eAssetInfoList[i]);
					}
				}
			
			});
	}


	var row_Index = 0;
	function setAssetPop(obj){
		
		var eAssetKeyArr = document.getElementsByName("eAssetKey").length;
		if(eAssetKeyArr == 0){
			var tbody = document.getElementById("lineRow");
		    tbody.innerHTML = "";  
		}
		
		
		var innerStr = "";
		
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick=\"delRow(this);\">X</a>";
		innerStr += "		</td>";
		// 자산유형
		innerStr += "		<td>" +obj.aAssetType;
		innerStr += "			<input type='hidden' id='eAssetKey_"+row_Index+"' name='eAssetKey' value='"+obj.aAssetKey+"'/>";
		innerStr += "		</td>";
		// 자산번호
		innerStr += "		<td>" +obj.aAssetNumber;
		innerStr += "		</td>";		
		// 자산명
		innerStr += "		<td>" +obj.aAssetName;
		innerStr += "		</td>";
		// 모델명
		innerStr += "		<td>" +obj.aAssetModel;
		innerStr += "		</td>";
		// 망구분
		innerStr += "		<td>"+obj.eNetworkType;
		innerStr += "		</td>";	
		// host
		innerStr += "		<td>"+obj.eHostName;
		innerStr += "		</td>";	
		// ip주소
		innerStr += "		<td>"+obj.eIp;
		innerStr += "		</td>";	
		// os
		innerStr += "		<td>"+obj.eOs;
		innerStr += "		</td>";	
		innerStr += "	</tr>";
		
		$(innerStr).appendTo("#lineRow");	
		
		row_Index++;
		
	}
	
	// 행삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}


	var rowTwo_Index = 0;
	function add_row(){
		
		var eWorkerArr = document.getElementsByName("eWorker").length;
		if(eWorkerArr == 0){
			var tbody = document.getElementById("lineRowTwo");
		    tbody.innerHTML = "";  
		}else{
			if(rowTwo_Index == 0){
				rowTwo_Index=eWorkerArr;
			}
		}
		
		
		var innerStr = "";
		
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick=\"delRowTwo(this);\">X</a>";
		innerStr += "		</td>";
		// 작업자
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eWorker_"+rowTwo_Index+"' name='eWorker' value='' maxLength='30'/>";
		innerStr += "		</td>";		
		// 소속
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eDepartment_"+rowTwo_Index+"' name='eDepartment' value='' maxLength='30'/>";
		innerStr += "			<input type='hidden' id='eRowIndex_"+rowTwo_Index+"' name='eRowIndex' value='"+rowTwo_Index+"' maxLength='30'/>";
		innerStr += "		</td>";
		// 작업일자  style='width:100px; text-align: center;' class='inp_color' readonly
		innerStr += "		<td>"; 
		innerStr += "			<input type='text' id='eWorkDate_"+rowTwo_Index+"' name='eWorkDate' value='' maxLength='30'/>";
		innerStr += "		</td>";
		// 내용
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eDescription_"+rowTwo_Index+"' name='eDescription' value='' maxLength='30'/>";
		innerStr += "		</td>";
		// 파일
		innerStr += "		<td><a class='form_btn md' onclick=\"addFile('"+rowTwo_Index+"');\">파일추가</a>"; 
		innerStr += "		<div id='fileList"+rowTwo_Index+"'></div>";	
		innerStr += "		</td>";	
		innerStr += "		</tr>";	
//	 	datepickerIdSet("eWorkDate_"+rowTwo_Index);
		
		$(innerStr).appendTo("#lineRowTwo");	
		
		rowTwo_Index++;
		
	}

	//행삭제
	function delRowTwo(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}

	var fileRowIndex = "";
	function addFile(rowIndex){  
		fileRowIndex =rowIndex;
		var url = "/mes/asset/popup/mesPDFAdd.do";
		// 동적으로 폼 생성
	    const form = document.createElement("form");
	    form.method = "POST";
	    form.action = url;
	    form.target = "ePDFAdd"; // 새 창 이름
	    
	    const csrfTokenGubun = document.createElement("input");
	    csrfTokenGubun.type = "hidden";
	    csrfTokenGubun.name = "csrfToken";
	    csrfTokenGubun.value = $("input[name=csrfToken]").val();
	    form.appendChild(csrfTokenGubun);
	    
	    const kMenuKeyGubun = document.createElement("input");
	    kMenuKeyGubun.type = "hidden";
	    kMenuKeyGubun.name = "kMenuKey";
	    kMenuKeyGubun.value = "${key}";
	    form.appendChild(kMenuKeyGubun);

	    const ePageGubun = document.createElement("input");
	    ePageGubun.type = "hidden";
	    ePageGubun.name = "ePageGubun";
	    ePageGubun.value = "Y";
	    form.appendChild(ePageGubun);
	    
	    // 폼을 문서에 추가
	    document.body.appendChild(form);

	    // 새 창 열기
	    window.open("", "ePDFAdd","width=600,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");

	    // 폼 전송
	    form.submit();

	    // 폼 제거
	    document.body.removeChild(form);
	}

	var fileIndex = 0;
	function addFileInfoRow(eIMGregId,eIMGregName,eIMGregExtension){  
		 var eFileRowIdArr = document.getElementsByName("eFileRowId").length;
			fileIndex =(eFileRowIdArr+1);  

		  var rowGubunCheck = "#fileList"+fileRowIndex;
		  // 파일을 감싸는 div 요소 생성
		  var fileItemDiv_ID = "fileItem_"+fileIndex
	       var fileDiv = $('<div class="fileItem" id="' + fileItemDiv_ID + '"></div>');
		  $(rowGubunCheck).append(fileDiv);
		  
		  
		  var eItemGubunArr = document.getElementsByName("eItemGubun").length;
			row_Index =(eItemGubunArr+1);
		 
		  
		  var deleteLink = $('<a class="del" onclick="delFileRow(this, \'' + fileIndex + '\');">X</a>');
		  var spanElement = $('<span>' + eIMGregName + '</span>').attr('onclick', 'eDownload("' + eIMGregId + '")');
		  var fileIdInput = $('<input type="hidden" name="eFileRowId" id="eFileRowId_' + fileIndex + '">').val(eIMGregId);
		  var fileNameInput = $('<input type="hidden" name="eFileRowName" id="eFileRowName_' + fileIndex + '">').val(eIMGregName);
		  var fileIndexInput = $('<input type="hidden" name="eFileRowIndex" id="eFileRowIndex_' + fileIndex + '">').val(fileRowIndex); //하위테이블 구분자ㅑㅐ
		  
		  $("#"+fileItemDiv_ID).append(deleteLink);
		  $("#"+fileItemDiv_ID).append(spanElement);
		  $("#"+fileItemDiv_ID).append(fileIdInput);
		  $("#"+fileItemDiv_ID).append(fileNameInput);
		  $("#"+fileItemDiv_ID).append(fileIndexInput);
		fileIndex++;
	}

	//파일 삭제 함수
	function delFileRow(link, fileId) {
	    $('#fileItem_' + fileId).remove(); // 해당 파일 항목의 div 요소를 삭제함
	}

	function eDownload(eFileId){
		 
		 if(confirm("선택한 파일을 다운로드 하시겠습니까?")){
				window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eFileId+"&fileSn=0'/>");
			}
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




	var rowThr_Index = 0;
	function addNotesList(){
		
		var eRowWorkerArr = document.getElementsByName("eRowWorker").length;
		if(eRowWorkerArr == 0){
			var tbody = document.getElementById("lineRowThr");
		    tbody.innerHTML = "";  
		}else{
			if(rowTwo_Index == 0){
				rowThr_Index=eRowWorkerArr;
			}
		}
		
		
		var innerStr = "";
		
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick=\"delRowThr(this);\">X</a>";
		innerStr += "		</td>";
		// 참여자
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eRowWorker_"+rowThr_Index+"' name='eRowWorker' value='' maxLength='30'/>";
		innerStr += "		</td>";		
		// 소속
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eRowDepartment_"+rowThr_Index+"' name='eRowDepartment' value='' maxLength='30'/>";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr += "			<input type='text' id='eRowPosition_"+rowThr_Index+"' name='eRowPosition' value='' maxLength='30'/>";
		innerStr += "		</td>";
		// 내용
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eRowContact_"+rowThr_Index+"' name='eRowContact' value='' maxLength='30'/>";
		innerStr += "		</td>";
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='eRowNotes_"+rowThr_Index+"' name='eRowNotes' value='' maxLength='30'/>";
		innerStr += "		</td>";
	 
		innerStr += "	</tr>";	
//	 	datepickerIdSet("eWorkDate_"+rowTwo_Index);
		
		$(innerStr).appendTo("#lineRowThr");	
		
		rowThr_Index++;
		
	}

	//행삭제
	function delRowThr(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	function approvalPop(){
		
		 var checkbox = $('#oPass');
	    if (checkbox.prop('checked')) {
	    	if(confirm("결재 제외로 선택되었습니다.\n결재자를 선택하시겠습니까?")){
	    		checkbox.prop('checked', false);
	    		$("#oSignPass").val("N");
	    	}else{
	    		return;
	    	}
	    }
		
		
		var ln = document.getElementsByName("referSign").length;
		var kStaffKey = "";
		var gubun = "";
		var preKStaffKey = "";
		for(var i = 0; i < ln; i++){
			var kStaffKey1 = document.getElementsByName("referSign")[i].value;
			var gubun1 = document.getElementsByName("gubun")[i].value;
			if(kStaffKey == ""){
				kStaffKey = kStaffKey1;
				gubun = gubun1;
			}else{
				kStaffKey = kStaffKey + ",";
				kStaffKey = kStaffKey + kStaffKey1;
				gubun = gubun + ",";
				gubun = gubun + gubun1;
			}
			
		}
		
		const form = document.createElement("form");
	    form.method = "POST";
	    form.action = "/mes/sign/popup/kw_signStaff_lf.do";
	    form.target = "AddrAdd"; // 새 창 이름
	    
	    const kStaffKeyGubun = document.createElement("input");
	    kStaffKeyGubun.type = "hidden";
	    kStaffKeyGubun.name = "kStaffKey1";
	    kStaffKeyGubun.value = kStaffKey;
	    form.appendChild(kStaffKeyGubun);
	    
	    
	    const gubunGubun = document.createElement("input");
	    gubunGubun.type = "hidden";
	    gubunGubun.name = "gubun1";
	    gubunGubun.value = gubun;
	    form.appendChild(gubunGubun);
	    
	    const csrfTokenGubun = document.createElement("input");
	    csrfTokenGubun.type = "hidden";
	    csrfTokenGubun.name = "csrfToken";
	    csrfTokenGubun.value = $("input[name=csrfToken]").val();
	    form.appendChild(csrfTokenGubun);
	    
	    const kMenuKeyGubun = document.createElement("input");
	    kMenuKeyGubun.type = "hidden";
	    kMenuKeyGubun.name = "kMenuKey";
	    kMenuKeyGubun.value = "${key}";
	    form.appendChild(kMenuKeyGubun);

	    // 폼을 문서에 추가
	    document.body.appendChild(form);

	    // 새 창 열기
	    window.open("", "AddrAdd", "width=1000, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
	    // 폼 전송
	    form.submit();

	    // 폼 제거
	    document.body.removeChild(form);
	}
	

	var referIndex = 0;
	function reCirSet(obj){
		//결재순서
		var lnTmp = document.getElementsByName("sSignStaffKey").length;
		
		var innerStr = "";
		
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<input type='hidden' id='referSign_"+referIndex+"' name='referSign' value='"+(obj.kStaffKey)+"'/>";
		innerStr += "			<input type='hidden' id='sSignStaffKey_"+referIndex+"' name='sSignStaffKey' value='"+(obj.kStaffKey)+"'/>";
		innerStr += "			<input type='hidden' id='sSignStaffPosition_"+referIndex+"' name='sSignStaffPosition' value='"+(obj.kPositionName)+"'/>";
		innerStr += "			<input type='hidden' id='sSignStaffName_"+referIndex+"' name='sSignStaffName' value='"+(obj.kStaffName)+"'/>";
		innerStr += "			<input type='hidden' id='sSignSequence_"+referIndex+"' name='sSignSequence' value='"+(Number(lnTmp)+1)+"'/>";
		innerStr += "			<input type='hidden' id='sSignStaffGubun_"+referIndex+"' name='sSignStaffGubun' value='"+(obj.gubun)+"'/>";
		innerStr += "			<input type='hidden' id='gubun_"+referIndex+"' name='gubun' value='"+(obj.gubun)+"'/>";
		innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>"+(Number(lnTmp)+1)+"</span>";
		innerStr += "		</td>";
		// 종류
		innerStr += "		<td>"
		innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>"+obj.gubun+"</span>";
		innerStr += "		</td>";		
		// 이름
		innerStr += "		<td>";
		innerStr += "			"+(obj.kPositionName)+" / "+(obj.kClassName)+" / "+(obj.kStaffName)+"";
		innerStr += "		</td>";
		innerStr += "	</tr>";
		
		$(innerStr).appendTo("#lineRow3");		
		
		referIndex++;
	}
	
	function deleteGyeoljaeList(){
		$('#lineRow3').empty();
	}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />
	<input type="hidden" id="eIssueKey" name="eIssueKey" value="${mesBlueprintVO.eIssueKey}" />
	
	<input type="hidden" id="eChangeKey" name="eChangeKey" value="${info.eChangeKey}" />
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
  	<input type="hidden" id="sSignStatus" name="sSignStatus" value="${info.sSignStatus}" />

	<div class="content_top">
		<div class="content_tit">
			<h2>문제관리 정보 수정 페이지</h2>
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
					<td >${info.blueprintWdate }
						<input type="hidden" id="blueprintWdate" name="blueprintWdate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
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
					<td colspan="3"> 
						<input type="text" id="eReqDate" name="eReqDate" value="${info.eReqDate }" style="width:150px; text-align:center;" class="inp_color" readonly   />
					</td>
				</tr>
  				<tr>
	  				<th><span style="color: red">* </span>요청자</th>
					<td>
						<input type="text" id="eRequester" name="eRequester" style="width:70%;" value="${info.eRequester}" maxLength="50"/>
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eRequester')">담당자 선택</a>
						<input type="hidden" name="eReqOrg" id="eReqOrg" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqOrg }" />
						<input type="hidden" name="eReqDept" id="eReqDept" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqDept }" />
					</td>
					<th >요청자 소속</th>
						<td >
							<input type="text" id="eRequesterOrg" name="eRequesterOrg" style="width:95%;" maxLength="50" value="${info.eRequesterOrg}" />
							<span id="eRequesterOrgTxt" style="display: none;"></span>
						</td>
				</tr>
  				
				<tr>
					<th>
						문제사항
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
							<textarea id="eReqContent" name="eReqContent" cols="100%" rows="10" style="font-size: 20px; width: 100%;">${info.eReqContent}</textarea>
					</td>
				</tr>
				
			</tbody>
		</table>
	</div>
		
	<div class="normal_table row">
		<table>
			<tbody>
				<tr>
	  				<th>문제원인</th>
					<td> 
						<input type="text" name="eIssueCause" id="eIssueCause" style="width:95%; text-align:left;" maxLength="50" value="${info.eIssueCause }" />
					</td>
					<th>해결방법</th>
					<td> 
						<input type="text" name="eSolutionMethod" id="eSolutionMethod" style="width:95%; text-align:left;" maxLength="50" value="${info.eSolutionMethod }" />
					</td>
				</tr>
	 				<tr>
	  				<th>처리시작일시</th>
					<td> 
						<input type="text" name="eWorkStart" id="eWorkStart" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkStart }" />
					</td>
					<th>처리완료일시</th>
					<td>
						<input type="text" name="eWorkEnd" id="eWorkEnd" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkEnd }" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	 
	
	<div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>회의록 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addNotesList()">회의록 추가</button>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
			<thead>
				<tr>
					<th style="width: 8%;">구분</th>
					<th style="width: 18%;"><span style="color: red">* </span>회의참여자</th>
					<th style="width: 18%;">참여자소속</th>
					<th style="width: 18%;">역할</th>
					<th style="width: 18%;">연락처</th>
					<th style="width: *;">비고</th>
				</tr>
			</thead>
			<tbody id="lineRowThr">
				<c:forEach var="aNotesList" items="${aNotesList}" varStatus="i">
							<tr>
								<td>
									<a class='del' onclick="delRow(this,'DD');">X</a>
								</td>
							
								<td> 
									<input type="text" id='eRowWorker_${i.index}' name='eRowWorker' value='${aNotesList.eRowWorker}' maxLength='30'/>
								</td>
									
								<td> 															
									<input type="text" id='eRowDepartment_${i.index}' name='eRowDepartment' value='${aNotesList.eRowDepartment}' maxLength='30'/>
								</td>
							
								<td> 
									<input type="text" id='eRowPosition_${i.index}' name='eRowPosition' value='${aNotesList.eRowPosition}' maxLength='30'/>
								</td>
							
								<td> 
									<input type="text" id='eRowContact_${i.index}' name='eRowContact' value='${aNotesList.eRowContact}' maxLength='30'/>
								</td>
								<td> 
									<input type="text" id='eRowNotes_${i.index}' name='eRowNotes' value='${aNotesList.eRowNotes}' maxLength='30'/>
								</td>
							
							</tr>
				 </c:forEach>
			
				<c:if test="${empty aNotesList }">
					<tr>
						<td colspan="6">회의록을 추가 하여 정보를 입력하세요.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>상세 내역 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" onclick="add_row()" class="form_btn md">내역 추가</button>
<%-- 						<c:if test="${not empty aFileList}"> <button type="button" class="form_btn md" onclick="eDownloadAll();">파일 모두 받기</button></c:if> --%>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
				<thead>
				<tr>
					<th style="width: 8%;">구분</th>
					<th style="width: 12%;"><span style="color: red">* </span>작업자</th>
					<th style="width: 12%;">작업자소속</th>
					<th style="width: 12%;">작업일시</th>
					<th style="width: *;">내용</th>
					<th style="width: 20%;">첨부 파일</th>
				</tr>
			</thead>
			<tbody id="lineRowTwo">
				<c:forEach var="aDeteliList" items="${aDeteliList}" varStatus="i">
					<tr>
						<td>
							<a class='del' onclick="delRowTwo(this);">X</a>
						</td>
					
						<td> 
							<input type="text" id='eWorker_${i.index}' name='eWorker' value='${aDeteliList.eWorker}' maxLength='30'/>
						</td>		
					
						<td> 														
							<input type="text" id='eDepartment_${i.index}' name='eDepartment' value='${aDeteliList.eDepartment}' maxLength='30'/>
							<input type="hidden" id='eRowIndex_${i.index}' name='eRowIndex' value='${aDeteliList.eRowIndex}' maxLength='30'/>
						</td>
						<td> 
							<input type="text" id='eWorkDate_${i.index}' name='eWorkDate' value='${aDeteliList.eWorkDate}' maxLength='30'/>
						</td>
					
						<td> 
							<input type="text" id='eDescription_${i.index}' name='eDescription' value='${aDeteliList.eDescription}' maxLength='30'/>
						</td>
					
						<td>
							<a class='form_btn md' onclick="addFile('${aDeteliList.eRowIndex}');">파일추가</a> 
							<div id='fileList${aDeteliList.eRowIndex}'>
							 <c:forEach var="aFileList" items="${aFileList}" varStatus="j">
								 <c:if test="${aDeteliList.eRowIndex eq aFileList.eFileRowIndex}">
								    <div class="fileItem" id="fileItem_${j.index}">
										    <a class="del" onclick="delFileRow(this, '${j.index}');">X</a>
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
						<td colspan="10">상세내역을 추가 하여 정보를 입력하세요.</td>
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
			 <button type="button" class="form_btn md"  onclick="sel_asset()">장비 선택</button>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
			<thead>
				<tr>
					<th style="width: 10%;">구분</th>
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
						<td>
							<a class='del' onclick="delRow(this);">X</a>
						</td>
						
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
						<td colspan="9">장비를 선택하세요.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="content_top nofirst with_btn">
		<div class="content_tit flex">
			<h2>승인권자</h2>
			<label for="oPass" class="inp_chkbox">
				<input type="checkbox" id="oPass" name="oPass" class="checkbox" onclick="handleOPassClick();"/>
				<i></i>
				결재 제외
			</label>
		</div>
		<div class="btns">
			 <button type="button" onclick="approvalPop()" class="form_btn md">승인권자 선택</button>
		</div>
	</div>
	<div class="normal_table">
		<table>
			<colgroup>
				<col style="width: 200px;"/>
				<col style="width: 200px;"/>
				<col />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">결재 정보</th>
				</tr>
				<tr>
					<th>결재순서</th>
					<th>부서</th>
					<th>성명</th>
				</tr>
			</thead>
			<tbody id="lineRow3"> 
				<c:forEach var="slist" items="${signList}" varStatus="j">
						<tr>
							<td>
								<input type='hidden' id='sSignStaffKey_${j.index}' name='sSignStaffKey' value='${slist.sSignStaffKey}'/>
								<input type='hidden' id='sSignStaffPosition_${j.index}' name='sSignStaffPosition' value='${slist.sSignStaffPosition}'/>
								<input type='hidden' id='sSignStaffName_${j.index}' name='sSignStaffName' value='${slist.sSignStaffName}'/>
								<input type='hidden' id='sSignSequence_${j.index}' name='sSignSequence' value='${slist.sSignSequence}'/>
								<input type='hidden' id='sSignStaffGubun_${j.index}' name='sSignStaffGubun' value='${slist.sSignStaffGubun}'/>
								<input type='hidden' id='referSign_${j.index}' name='referSign' value='${slist.sSignStaffKey}'/>
								<input type='hidden' id='gubun_${j.index}' name='gubun' value='${slist.sSignStaffGubun}'/>
								<span id='sn_sp_${j.index}' class='sn_sp'>${slist.sSignSequence}</span>
							</td>
						
							<td>
								<span id='sn_sp_${j.index}' class='sn_sp'>${slist.sSignStaffGubun}</span>
							</td>		
						
							<td>
								${slist.kPositionName}&nbsp;/&nbsp;${slist.kClassName}&nbsp;/&nbsp;${slist.sSignStaffName}
							</td>
						
						</tr>	
					</c:forEach>
				<c:if test="${empty signList}">
					<tr>
						<td colspan="3" style="text-align: center;">등록 정보가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active"  onclick="update_go();">저장</button>
		<button type="button" class="form_btn"  onclick="cancel();">목록</button>
	</div>
</form>