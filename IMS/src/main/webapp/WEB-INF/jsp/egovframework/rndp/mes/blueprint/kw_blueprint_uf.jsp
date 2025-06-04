<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		datepickerIdSet("eReqDate");
		datepickerIdSet("eWorkDate");
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

	// 목록
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_lf.do";
		document.frm.submit();
	}
	// 행삭제
	function delRow(obj){

		var tr = $(obj).parent().parent();
		tr.remove();

	}


	// 제품 선택
	function selecteItem(){
		var url = "/mes/blueprint/popup/kw_e_item_lf.do";
		window.open(url,"eItemList","width=1100,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	}
	// 제품 선택 반환
	function seteItemPop(obj){
		if(obj != null){
			$("#eItemKey").val(obj.eItemKey);
			$("#eItemName").val(obj.eItemName);
			$("#eItemCatePath").val(obj.eItemCatePath);
			$("#eItemCateName").val(obj.eItemCateName);
			$("#eItemCateKey").val(obj.eItemCateKey);
			$("#eItemCode").val(obj.eItemCode);
			$("#eItemStandard").val(obj.eItemStandard);
			$("#eItemUnitTxt").val(obj.eItemUnitTxt);
			$("#eItemUnit").val(obj.eItemUnit);
			$("#eItemGubun").val(obj.eItemGubun);


			$("#eItemCatePath_sp").text(obj.eItemCatePath);
			$("#eItemStandard_sp").text(obj.eItemStandard);
			$("#eItemCode_sp").text(obj.eItemCode);

		}
	}


	//행추가
	var itemRowIndex = 0;

	function addRow() {

		var innerStr = "";
 
		// 구분
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick='delRow(this)'>X</a>";
		innerStr += "		</td>";
		//파일등록
		innerStr += "		<td>";
		innerStr += "		</td>";
		// 미리보기
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='sGubunName_"+itemRowIndex+"' name='sGubunName' style='width:90%;' />";
		innerStr += "		</td>";
		// 비고
		innerStr += "		<td>";
		innerStr += "			<input type='text' id='sGubunEtc_"+itemRowIndex+"' name='sGubunEtc' style='width:90%;' />";
		innerStr += "		</td>";
		innerStr += "	</tr>";

		$(innerStr).appendTo("#lineRow");

		itemRowIndex++;
	}


	// 저장
	function update_go(){
		if(chkIns()){
			if(confirm("저장하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/blueprint/kw_blueprint_u.do";
				document.frm.submit();
			}
		}
	}

	// validation
	function chkIns(){
		
		if($("#eRequester").val() == ""){
			alert("요청자 명을 입력하세요.");
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
			
			for(var i=0; i < idx ; i++){
				var eDepartment = document.getElementsByName("eDepartment")[i].value;
				var eWorker = document.getElementsByName("eWorker")[i].value;
				var eWorkDate = document.getElementsByName("eWorkDate")[i].value;
				var eDescription = document.getElementsByName("eDescription")[i].value;
				
				document.getElementsByName("eDepartment")[i].value = ConverttoHtml(eDepartment);
				document.getElementsByName("eWorker")[i].value = ConverttoHtml(eWorker);
				document.getElementsByName("eWorkDate")[i].value = ConverttoHtml(eWorkDate);
				document.getElementsByName("eDescription")[i].value = ConverttoHtml(eDescription);
				
			}
		}
		
		if($("#oSignPass").val() != 'Y'){
			if(document.getElementsByName("sSignStaffKey").length == 0){
				alert("승인권자를 선택해주세요");
				return false;
				}
			}
		$("#eWorkStart").val($("<div>").text($("#eWorkStart").val()).html()); 
		$("#eWorkEnd").val($("<div>").text($("#eWorkEnd").val()).html());
		$("#eWorkDetails").val($("<div>").text($("#eWorkDetails").val()).html());
		$("#eReqContent").val($("<div>").text($("#eReqContent").val()).html());
		return true;
	}




	$(function() {
			$("#filename").on('change', function () {
				if (this.files && this.files[0]) {

					var maxSize = 10 * 1024 * 1024;
					var fileSize = this.files[0].size;

					if (fileSize > maxSize) {
						alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
						$(this).val('');
						return false;
					} else {

						readURL(this);
					}
				}
			})
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function (e) {
				$('#preImage').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	//행삭제
	function delRow(obj){

		var tr = $(obj).parent().parent();
		tr.remove();
	}

	function setImgregAdd(imgId) {
		$('#imgRow').empty();
	 	$('#eIMGregName').val(imgId);
		var innerStr = "";
		innerStr += "<tr id='imgTR'>";
		innerStr += "	<td  id='imgTD' style='border: none;''>";
		innerStr += "		<img height='150;'  width='300'  src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId="+imgId+"&fileSn=0'/>";
		innerStr += "	</td>"; 
		innerStr += "</tr>";	
		var row =innerStr;
		$(row).appendTo("#imgRow");	 
	}
	
	//파일 첨부
	var fileIndex = 0;
	function fileAdd(AddFileId, atchFileName){
		
		var ulobj = document.getElementById("fileUL");
		var liobj = document.createElement('tr');
		var idx = ulobj.childNodes.length;
		
		fileIndex++;
		
		liobj.id = "filename_" + fileIndex;
		liobj.style.padding = "0";
		ulobj.appendChild(liobj);
		
		var innerStr = "";
		innerStr +=	"		<td>";
		innerStr += "			<a class='del' onclick=\"fileDel('filename_" + fileIndex+"');\" style='text-decoration:none;'>X</a>";
		innerStr +=	"		</td>";
		innerStr +=	"		<td>";
		innerStr += 			atchFileName;
		innerStr += "			<input type='hidden' id='fileKey'   name='fileKey' value='0' />";
		innerStr += "			<input type='hidden' id='AddFileId' name='eAddFileId"+btnGubun+"' value='"+AddFileId+"' />";
		innerStr += "			<input type='hidden' id='atchFileName' name='atchFileName"+btnGubun+"' value='"+atchFileName+"' />";
		innerStr +=	"		</td>";
		innerStr +=	"		<td>";
		innerStr += "			<input type='text' id='eBlueprintItemEtc_"+btnGubun+"' name='eBlueprintItemEtc' maxlength='100' value=''/>";
		innerStr +=	"		</td>";
		liobj.innerHTML = innerStr;	
		
	} 
	
	
	function fileDel(idx){
		document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));	
	}
	
	var btnGubun = "";
	function mesIMGreg(gubun) { 
		btnGubun = gubun;
		var url = "/mes/blueprint/popup/mesIMGregAdd.do";
		window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
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

	//파일 삭제 함수
	function delFileRow(link, fileId) {
	    $('#fileItem_' + fileId).remove(); // 해당 파일 항목의 div 요소를 삭제함
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
		innerStr += "	</tr>";
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
	function delRowTwo(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	 function eCheck() {
         var selectElement = document.getElementById('eIsInterrupted');
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eIsInterruptedName').value = value2;
     }
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/blueprint/kw_blueprint_u.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesBlueprintVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesBlueprintVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />

	<input type="hidden" id="eChangeKey" name="eChangeKey" value="${info.eChangeKey}" />
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
  	<input type="hidden" id="sSignStatus" name="sSignStatus" value="${info.sSignStatus}" />

	<div class="content_top">
		<div class="content_tit">
			<h2>변경관리 수정 페이지</h2>
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
					<td colspan="3"> 
						<input type="text" id="eReqDate" name="eReqDate" value="${info.eReqDate }" style="width:150px; text-align:center;" class="inp_color" readonly   />
					</td>
				</tr>
  				<tr>
	  				<th><span style="color: red">* </span>요청자</th>
					<td> 
						<input type="text" id="eRequester" name="eRequester" style="width:70%;" maxLength="50" value="${info.eRequester}"   />
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eRequester')"  >담당자 선택</a>
						<input type="hidden" name="eReqOrg" id="eReqOrg" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqOrg }" />
						<input type="hidden" name="eReqDept" id="eReqDept" style="width:95%; text-align:left;" maxLength="50" value="${info.eReqDept }" />
					</td>
	  				<th>요청자 소속</th>
					<td>
						<input type="text" id="eRequesterOrg" name="eRequesterOrg" style="width:95%;" maxLength="50" value="${info.eRequesterOrg}"/>
						<span id="eRequesterOrgTxt" style="display: none;"></span>
					</td>
				</tr>
  				
				<tr>
					<th>
						요청내용
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
						<textarea id="eReqContent" name="eReqContent" cols="100%" rows="10"  style="font-size: 20px; width: 100%; height: 300px; resize: none;"  maxLength="1000">${info.eReqContent }</textarea>
					</td>
				</tr>
				<tr>
	  				<th>변경시작일시 </th>
					<td> 
						<input type="text" name="eWorkStart" id="eWorkStart" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkStart }" />
					</td>
					<th>변경완료일시</th>
					<td> 
						<input type="text" name="eWorkEnd" id="eWorkEnd" style="width:95%; text-align:left;" maxLength="50" value="${info.eWorkEnd }" />
					</td>
				</tr>
				<tr>
	  				<th>요청사유</th>
					<td> 
						<input type="text" name="eWorkDetails" id="eWorkDetails" style="width:95%;" maxLength="50" value="${info.eWorkDetails }"  />
					</td>
					<th>중단여부</th>
					<td> 
						<input type="hidden" id="eIsInterruptedName" name="eIsInterruptedName" value="${info.eIsInterruptedName }" />
						<select id='eIsInterrupted' name='eIsInterrupted'  onchange="eCheck()" style="width: 120px;" >
							<c:forEach var='list' items='${gubun40List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${list.sGubunKey eq info.eIsInterrupted}"> selected="selected" </c:if>	>${list.sGubunName}</option>
													
							</c:forEach>
						</select>
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
			 <button type="button" class="form_btn md" onclick="add_row()" >내역 추가</button>
			 <!-- <c:if test="${not empty aFileList}"><button type="button" class="form_btn md" onclick="eDownloadAll();">파일 모두 받기</button></c:if> -->
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
					<th style="width: 16%;text-align: center;">첨부 파일</th>
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
						<a class='mes_btn' onclick="addFile('${aDeteliList.eRowIndex}');">파일추가</a> 
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
						<td colspan="6">상세내역을 추가 하여 정보를 입력하세요.</td>
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
			 <button type="button" class="form_btn md" onclick="sel_asset()">장비 선택</button>
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
						<td colspan="10">장비를 선택하세요.</td>
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
		<button type="button" class="form_btn active" onclick="update_go();">저장</button>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>
</form>