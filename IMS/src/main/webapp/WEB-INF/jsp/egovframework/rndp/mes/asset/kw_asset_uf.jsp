<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		datepickerIdSet("eAssetDate");
		datepickerIdSet("eEosDate");
		datepickerIdSet("eEolDate");
		datepickerIdSet("aAssetDate");
		
		eDate('eEosDate');
		eDate('eEolDate');
		setComma('eAssetCost');
		addFileInfoRowTwo();
		updateAssetTypeName();
		eAssetStatusCheck();
		maintanceSetting();
		
		var sSignStatus  = $("#sSignStatus").val();
		if(sSignStatus == "등록"  || sSignStatus == "반려"){
			$("#oSignPass").val("N");
			 $('#oPass').prop('checked', false);
		}else{
			 $('#oPass').prop('checked', true);
			$("#oSignPass").val("Y");
		}
		
	});
		
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}	
	
	function update_go(){
		if(document.getElementById("eAssetTypeName").value == ""){
			alert("자산유형를 입력하세요.");
			document.getElementById("eAssetType").focus();
			return false;
		}

		if(document.getElementById("eAssetName").value == ""){
			alert("자산명을 입력하세요.");
			document.getElementById("eAssetName").focus();
			return false;
		}
	 
		if(document.getElementById("eAssetSNumber").value == ""){
			alert("제조번호(S/N)를 입력하세요.");
			document.getElementById("eAssetSNumber").focus();
			return false;
		}
		
		if(document.getElementById("eAssetModel").value == ""){
			alert("모델명을 입력하세요.");
			document.getElementById("eAssetModel").focus();
			return false;
		}
		
		if(document.getElementById("eEosDate").value == ""){
			document.getElementById("eEosDate").value = '2099-01-01';
		}
		
		if(document.getElementById("eEolDate").value == ""){
			document.getElementById("eEolDate").value = '2099-01-01';
		}
		
		var eLicenseQuantityArr = document.getElementsByName("eLicenseQuantity");
		if(eLicenseQuantityArr.length > 0){
			for (var aa = 0; aa < eLicenseQuantityArr.length; aa++) {
			 	var value = eLicenseQuantityArr[aa].value;
				if (isValidLicenseQuantity(value)){
					alert((aa+1)+"번째 라이선스의 수량을 입력하세요.");
					eLicenseQuantityArr[aa].focus();
					return;
			    }
			}
		}
		

		function isValidLicenseQuantity(value) {
		    // 공백 제거
		    value = value.trim();

		    // 천단위 콤마 제거
		    value = value.replace(/,/g, '');

		    // 숫자인지 체크
		    if (isNaN(value) || value === '') {
		        return true; // 숫자가 아니거나 빈 값일 경우
		    } else {
		        return ; // 숫자인 경우
		    }
		}
		if(confirm("저장하시겠습니까?")){
			if($("#eAssetCost").val() == ""){
				$("#eAssetCost").val(0);
			}
			
			var cost = $("#eAssetCost").val();
			$("#eAssetCost").val(uncomma(cost));

			$("#eAuthor").val(ConverttoHtml($("#eAuthor").val())); 
			$("#eAssetName").val(ConverttoHtml($("#eAssetName").val())); 
			$("#eAssetNumber").val(ConverttoHtml($("#eAssetNumber").val())); 
			$("#eAssetMaker").val(ConverttoHtml($("#eAssetMaker").val())); 
			$("#eAssetModel").val(ConverttoHtml($("#eAssetModel").val())); 
			$("#eAssetSNumber").val(ConverttoHtml($("#eAssetSNumber").val())); 
			$("#eAssetPurpose").val(ConverttoHtml($("#eAssetPurpose").val())); 
			$("#eNetworkType").val(ConverttoHtml($("#eNetworkType").val())); 
			$("#eHostName").val(ConverttoHtml($("#eHostName").val())); 
			$("#eIp").val(ConverttoHtml($("#eIp").val())); 
			$("#eOs").val(ConverttoHtml($("#eOs").val())); 
			$("#eAssetEtc").val(ConverttoHtml($("#eAssetEtc").val())); 
			$("#eDeviceType").val(ConverttoHtml($("#eDeviceType").val())); 
			
			document.writeForm.action = "/mes/asset/kw_asset_u.do";
			document.writeForm.submit();
		}
			
	}
	function clearInput(inputId) {
	    document.getElementById(inputId).value = '';
	}
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/asset/kw_asset_lf.do";
		document.writeForm.submit(); 
	}
	
	function maintanceSetting(){
		//초기값 세팅
		
// 		등록시 선택값 확인
		var checkNum1 = $("#checkNum1").val();
	 
		if(checkNum1 != ""){
			getCateData(1);
		}
		var checkNum2 = $("#checkNum2").val();
		if(checkNum2 != ""){  
			getCateData(2);
		}
		
		var checkNum3 = $("#checkNum3").val();
		if(checkNum3 != ""){  
			getCateData(3);
		}
		
		var checkNum4 = $("#checkNum4").val();
		if(checkNum4 != ""){  
			getCateData(4);
		}
		
	}
	
	
	function getCateData(depth){   
		$.ajax({
				type		: "post"
			,	dataType	: "json"
			,	url			: "/mes/maintance/kw_getCateListAjax.do"
			,	data		: {
					kPositionUpKey : $("#maintanceSelect"+(depth-1)).val()
				}
			, 	async: false // 동기 처리 설정
			,	success		: function(msg){
				var selectElement = document.getElementById("maintanceSelect"+depth);

				// option 요소들을 반복하여 검사하고 value가 0이 아닌 것들을 제거
				for(var i = selectElement.options.length - 1; i >= 0; i--) {
				    if(selectElement.options[i].value !== "0") {
				        selectElement.remove(i);
				    }
				}
			
				var innerStr = "";
				var list = msg.result.dataList;
				var checkNum = $("#checkNum"+(depth)).val();
				for(var i=0; i<list.length; i++){ 
					 var selected = (list[i].kPositionKey === checkNum) ? "selected" : "";
					innerStr += "<option value = '"+(list[i].kPositionKey)+"'"+ selected+">"+(list[i].kPositionName)+"</option>"; 
				}
				$(innerStr).appendTo("#maintanceSelect"+depth);
				
			}
			, error		: function(e){
				alert("에러발생");
			}
		});
	}
		
	 function updateAssetTypeName() {
         var selectElement = document.getElementById('eAssetType');
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetTypeName').value = value2;
     }
	 
	 function eAssetStatusCheck() {
         var selectElement = document.getElementById('eAssetStatus');
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetStatusName').value = value2;
     }
	 
	 

	  function eDate(gubun){
		  var currentDate = new Date();
		  var dateInput;
		  if(gubun == 'eEosDate'){
			  dateInput  = $("#eEosDate").val();
		  }else{
			  dateInput  = $("#eEolDate").val();
		  }
		  var formattedDate = formatDateData(new Date(dateInput));
		    // 유효한 날짜인지 확인
		    if (!isNaN(formattedDate.getTime())) {
		    	 var differenceInDays = Math.floor((formattedDate-currentDate) / (1000 * 60 * 60 * 24));
		    	  var expired = differenceInDays < 0;
		    	  var spantext = "";
		    	  if(expired){
		    		  spantext = "만료";
		    	  }else{
		    		  spantext = differenceInDays+"일 남음";
		    	  }
		    	  
		    	$("#"+gubun+"Txt").html(spantext);
		    	
		    }else{
		    	$("#"+gubun+"Txt").html("");
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
	
	function eDownload(){
		 var eIMGregId  = $('#eAssetImageId').val();
		 if(confirm("이미지를 다운로드 하시겠습니까?")){
				window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eIMGregId+"&fileSn=0'/>");
			}
	}
	
	 function convertToUppercase(input) {
         input.value = input.value.toUpperCase();
     }
	  
	  function ePDFViewer(pdfId,ext){
			var fileExt =  ext.toLowerCase();
			var url = "/mes/asset/ePDFViewer.do?fileId="+pdfId+"&eIMGregExtension="+fileExt;
			window.open(url ,"ePDFViewer" ,"width=800,height=900,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
		}

	  function addFile(){
		  var url = "/mes/inspection/popup/kw_File_add.do";
		  const form = document.createElement("form");
		    form.method = "POST";
		    form.action = url;
		    form.target = "ePDFAdd"; // 새 창 이름
		    
		    const csrfTokenGubun = document.createElement("input");
		    csrfTokenGubun.type = "hidden";
		    csrfTokenGubun.name = "csrfToken";
		    csrfTokenGubun.value = $("input[name=csrfToken]").val();
		    form.appendChild(csrfTokenGubun);

		    const ePageGubun = document.createElement("input");
		    ePageGubun.type = "hidden";
		    ePageGubun.name = "ePageGubun";
		    ePageGubun.value = "N";
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

	  function addFileInfoRow(eIMGregId,eIMGregName,eIMGregExtension){
		  $('#eAssetImageName').val(eIMGregName);
		  $('#eAssetImageId').val(eIMGregId);
		  $('#eAssetImageExt').val(eIMGregExtension);
			if(eIMGregExtension.toLowerCase() == "pdf"){
				 var pdfUrl = "/cmm/fms/PDFView.do?atchFileId="+eIMGregId+"&fileSn=0";
				$('#pdfViewer').attr('src', pdfUrl);
			    $('#pdfViewer').show();
			    $('#eImgViewer').hide();
			}else{
			  var imageUrl = "/cmm/fms/getImage.do?atchFileId="+eIMGregId+"&fileSn=0";	
		    	$('#eImgViewer').attr('src',imageUrl);
		    	$('#eImgViewer').show();
		    	$('#pdfViewer').hide();
			}
			$("#delBtn").show(); 
	  }
	  
	  function addFileInfoRowTwo(){
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
				$("#delBtn").show(); 
		}
	  function addLicense() {
			var url = "/mes/asset/popup/kw_license_pop.do";
			window.open(url ,"eLicenseAdd" ,"width=1280,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
		}
		function setInfoReturnPop(obj) {
			$.ajax({
				type		: "post"
			,	dataType	: "json"
			,	url			: "/mes/asset/kw_licenseInfoList.do"
			,	data		: {
					eSWRegisterKey : obj.eKeyList
				}
			,	success		: function(msg){
				var eLicenseInfo  = msg.result.dataList;
					for(var i = 0; i < eLicenseInfo.length; i++){
						setLicenseInfo(eLicenseInfo[i]);
					}
				}
			
			});
		}
		var lineRowLicense_Index = 0;
		function setLicenseInfo(obj) {
			
			var eSWRegisterKeyArr = document.getElementsByName("eSWRegisterKey").length;
			if(eSWRegisterKeyArr == 0){
				var tbody = document.getElementById("lineRowLicense");
			    tbody.innerHTML = "";  
			}else{
				if(lineRowLicense_Index == 0){
					lineRowLicense_Index=eSWRegisterKeyArr+1;
				}
				for(var i=0; i < eSWRegisterKeyArr ; i++){
					var eSWRegisterKey = document.getElementsByName("eSWRegisterKey")[i].value;
						if(obj.eSWRegisterKey == eSWRegisterKey){
							return false;
						}
				}
				
			}
			
			
			var innerStr = "";
			
			// 행삭제
			innerStr += "	<tr>";
			innerStr += "		<td>";
			innerStr += "			<a class='del' onclick=\"delRow(this);\">X</a>";
			innerStr += "		</td>";
			// 제조사
			innerStr += "		<td>" +obj.eManufacturer;
			innerStr += "			<input type='hidden' id='eSWRegisterKey_"+lineRowLicense_Index+"' name='eSWRegisterKey' value='"+obj.eSWRegisterKey+"'/>";
			innerStr += "		</td>";
			// 라이선스명
			innerStr += "		<td>" +obj.eProductName;
			innerStr += "		</td>";		
			// 버전
			innerStr += "		<td>" +obj.eVersion;
			innerStr += "		</td>";
			// 구매일
			innerStr += "		<td>" +obj.ePurchaseDate;
			innerStr += "		</td>";
			// 만료일
			innerStr += "		<td>"+obj.eEndDate;
			innerStr += "		</td>";	
			// 비고
			innerStr += "		<td>"+obj.eRemarks;
			innerStr += "		</td>";	
			// 적용수량
			innerStr += "		<td>";
			innerStr +=	"			<input type='text' id='eLicenseQuantity_"+lineRowLicense_Index+"' name='eLicenseQuantity' value='' style='width:98%; text-align:left;' maxLength='4' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"  />";
			innerStr += "		</td>";	
			 
			innerStr += "	</tr>";
			
			$(innerStr).appendTo("#lineRowLicense");	
			
			lineRowLicense_Index++;
			
		}
		
		//행 삭제
		function delRow(obj){
			var tr = $(obj).parent().parent();
			tr.remove();
			var eSWRegisterKeyArr = document.getElementsByName("eSWRegisterKey").length;
			if(eSWRegisterKeyArr == 0){
				var tbody = document.getElementById("lineRowLicense");
				tbody.innerHTML = '<tr><td colspan="8" style="text-align: center;">등록 정보가 없습니다.</td></tr>';  
			}
		}
		function delRowTwo(obj){
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
				if(lnTmp > 0){
					if(referIndex == 0){
						referIndex=lnTmp+1;
					}
				}
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
				// 순번
				//innerStr += "		<td>"
				//innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>"+(Number(lnTmp)+1)+"</span>";
				//innerStr += "		</td>";
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
			
			function handleOPassClick() {
				// 체크박스의 상태를 직접 변수에 저장
			    var isChecked = $("#oPass").is(":checked");
			    if(isChecked){
			    	 $("#oSignPass").val("Y");
			    		var elements = document.getElementsByName("sSignStaffKey");
				        if (elements.length > 0) {
				            if (confirm("선택한 결재자 정보를 삭제고 \n결재 제외처리 하시겠습니까?")) {
				                $('#lineRow3').empty();
				            } else {
				            	$("#oPass").prop('checked', false);
				            	 $("#oSignPass").val("N");
				                return; 
				            }
				        }
			    } else {
		            $("#oSignPass").val("N");
			    }
			}
			
			 function eAssetSNumberCheck(ogj){
		         var value = $(ogj).val();
			         $.ajax({
			 			type		: "post"
			 		,	dataType	: "json"
			 		,	url			: "/mes/asset/kw_asset_sNumber_check.do"
			 		,	data		: {
			 			eAssetSNumber : value,
			 			eAssetKey :$("#eAssetKey").val()
			 			}
			 		,	success		: function(msg){
			 			var dataInfo  = msg.result.dataAdd;
			 			 if (dataInfo) {
			                 // 데이터가 있는 경우 -> 중복 제조번호
			                 alert(value+"는 중복되는 제조번호입니다. 제조번호를 확인해 주십시오. ");
			                 $("#eAssetSNumber").val(""); // 필드 초기화
			             }
			 			}
			 		});

		     }
			 
			 function eAssetNumberCheck(obb){
		         var value = $(obb).val();
			         $.ajax({
			 			type		: "post"
			 		,	dataType	: "json"
			 		,	url			: "/mes/asset/kw_eAssetNumberCheck.do"
			 		,	data		: {
			 			eAssetNumber : value,
			 			eAssetKey :$("#eAssetKey").val()
			 			}
			 		,	success		: function(msg){
			 			var dataInfo  = msg.result.dataAdd;
			 			 if (dataInfo) {
			                 // 데이터가 있는 경우 -> 중복 제조번호
			                 alert(value+"는 중복되는 자산번호입니다. 입력할 자산번호를 확인해 주십시오. ");
			                 $("#eAssetNumber").val(""); // 필드 초기화
			             }
			 			}
			 		});

		     }
			 
			 function delFile(){
				$('#eAssetImageName').val("");
				$('#eAssetImageId').val("");
				$('#eAssetImageExt').val("");
 				$('#pdfViewer').attr('src', "");
		    	$('#pdfViewer').hide();
 			    $('#eImgViewer').hide();
 			    $("#addBtn").text("추가");
 			    $("#delBtn").hide(); 

			 }
			 
			 function setDate(inputId) {
				    document.getElementById(inputId).value = nowDate();
				    eDate(inputId);
				}
			 
			 
</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="searchWord" id="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesAssetVO.searchType}" />
	<input type="hidden" name="eAssetKey" id="eAssetKey" value="${assetInfo.eAssetKey}" />
	<input type="hidden" name="sSignStatus" id="sSignStatus" value="${assetInfo.sSignStatus}" />
	<input type="hidden" name="oSignPass" id="oSignPass" value="" />
	<input type="hidden" name="kStaffName" id="kStaffName" value="${staffVO.kStaffName}" />
	<input type="hidden" name="kStaffKey" id="kStaffKey" value="${staffVO.kStaffKey}" />
	<div class="content_top">	
		<div class="content_tit">
			<h2>대상장비 정보 수정</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
            		<th>작성자</th>
            		<td>${assetInfo.kStaffName}
						<input type="hidden" name="eAuthor" id="eAuthor" style="width:35%; text-align:left;"   value="${assetInfo.kStaffName}" class="inp_color"/>
            		</td>
            		<th>등록일</th>
            		<td>${assetInfo.eAssetWdate }
						<input type="hidden" id="aAssetDate" name="aAssetDate" style="width:150px; text-align:center;" class="inp_color" readonly value="${assetInfo.eAssetWdate }" />
            		</td>
          		</tr>			
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
				<tr>
					<th><span style="color: red">* </span>자산유형</th>
					<td>
					<input type="hidden" id="eAssetTypeName" name="eAssetTypeName" value="${assetInfo.eAssetTypeName}" />
					<select id='eAssetType' name='eAssetType'  onchange="updateAssetTypeName()" >
						<option value=''>선택</option>
						<c:forEach var='list' items='${gubun36List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'  <c:if test="${assetInfo.eAssetType  eq list.sGubunKey}">selected="selected" </c:if>	 >${list.sGubunName}</option>			
									
						</c:forEach>
					</select>
					</td>
					<th><span style="color: red">* </span>자산번호</th>
					<td>
						<input type="text" name="eAssetNumber" id="eAssetNumber" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eAssetNumber}" onchange="eAssetNumberCheck(this)"/>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>자산명</th>
					<td>
						<input type="text" name="eAssetName" id="eAssetName" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eAssetName}"/>
					</td>
					<th>설치위치</th>
            		<td> 
            		 <input type="hidden" id="mMaintanceCateKey" name="eMaintanceCateKey" value="0"/>
						<input type="hidden" id="mMaintanceCateName" name="eMaintanceCateName" value=""/>
						<input type="hidden" id="maintanceSelect0" name="eaintanceSelect0" value="0"/>
						<input type="hidden" id="checkNum1" name="checkNum1" value="${assetInfo.eMaintanceSelect1}"/>
						<input type="hidden" id="checkNum2" name="checkNum2" value="${assetInfo.eMaintanceSelect2}"/>
						<input type="hidden" id="checkNum3" name="checkNum3" value="${assetInfo.eMaintanceSelect3}"/>
						<input type="hidden" id="checkNum4" name="checkNum4" value="${assetInfo.eMaintanceSelect4}"/>
						
						<span id="mMaintanceCate1">
							<select id="maintanceSelect1" name="eMaintanceSelect1" style="width:120px;" onChange="getCateData(2)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						<i class="ico_arrow"></i>
						<span id="mMaintanceCate2">
						
							<select id="maintanceSelect2" name="eMaintanceSelect2" style="width:120px;" onChange="getCateData(3)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						<i class="ico_arrow"></i>
						<span id="mMaintanceCate3">
							<select id="maintanceSelect3" name="eMaintanceSelect3" style="width:120px;" onChange="getCateData(4)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						<i class="ico_arrow"></i>
						<span id="mMaintanceCate4">
							<select id="maintanceSelect4" name="eMaintanceSelect4" style="width:120px;">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
            		</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>제조사</th>
					<td>
						<input type="text" name="eAssetMaker" id="eAssetMaker" style="width:95%; text-align:left;" maxLength="50" value="${assetInfo.eAssetMaker}"/>
					</td>
					<th><span style="color: red">* </span>모델명</th>
					<td>
						<input type="text" name="eAssetModel" id="eAssetModel" style="width:95%; text-align:left;" maxLength="50" value="${assetInfo.eAssetModel}"/>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>제조번호(S/N)</th>
					<td>
						<input type="text" name=eAssetSNumber id="eAssetSNumber" style="width:95%; text-align:left;" maxLength="50" value="${assetInfo.eAssetSNumber}" onchange="eAssetSNumberCheck(this)" />
					</td>
					<th>자산상태</th>
					<td>
					 	<input type="hidden" id="eAssetStatusName" name="eAssetStatusName" value="${assetInfo.eAssetStatusName}" />
						<select id='eAssetStatus' name='eAssetStatus'  onchange="eAssetStatusCheck()"  >
							<c:forEach var='list' items='${gubun37List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}' <c:if test="${assetInfo.eAssetStatus  eq list.sGubunKey}">selected="selected" </c:if>	>${list.sGubunName}</option>						
							</c:forEach>
						</select>
					</td>
				</tr>
          	 	<tr>
          			<th>도입원가(원)</th>
            		<td>
						<input type="text" name="eAssetCost" id="eAssetCost" style="width:95%; text-align:left; padding-right: 5px;" maxLength="10" onblur="setComma(this.id)" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" value="${assetInfo.eAssetCost}"/>
            		</td>
            			<th>도입일</th>
            		<td>
						<input type="text" id="eAssetDate" name="eAssetDate" style="width:150px; text-align:center;" class="inp_color" value="${assetInfo.eAssetDate}" readonly />
            		</td>
          		</tr>		
        		<tr>
            		<th>장비구분</th>
            		<td>
						<input type="text" id="eDeviceType" name="eDeviceType" style="width:95%;" maxlength="50" oninput="convertToUppercase(this)" value="${assetInfo.eDeviceType}"  />
            		</td>
            		<th>사업명</th>
            		<td>
						<input type="text" name="eAssetPurpose" id="eAssetPurpose" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eAssetPurpose}"/>
            		</td>
          		</tr>		
        		<tr>
        			<th>내구연수</th>
					<td colspan="1">
            			<input type="text" name="eLifespan" id="eLifespan" style="width:150px; text-align:left;" maxLength="100" value="${assetInfo.eLifespan}"/>
           			 	: <select id="eLifeType" name="eLifeType">
				            <option value="일반장비"   <c:if test="${assetInfo.eLifeType  eq '일반장비'}">selected="selected" </c:if> >일반장비</option>
				            <option value="노후장비"  <c:if test="${assetInfo.eLifeType  eq '노후장비'}">selected="selected" </c:if> >노후장비</option>
				        </select>
					</td>
            		<th>망구분</th>
            		<td>
						<input type="text" name="eNetworkType" id="eNetworkType" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eNetworkType}" />
            		</td>
          		</tr>		
          		<tr>          		
					<th onclick="clearInput('eEosDate')">EoS</th>
            		<td>
            			<input type="text" name="eEosDate" id="eEosDate" style="width:150px; text-align:center;" value="${assetInfo.eEosDate}"  class="inp_color" onchange="eDate('eEosDate')"  readonly />
            			<span id="eEosDateTxt"></span>
            			
            		</td>
            		<th onclick="clearInput('eEolDate')">EoL</th>
            		<td>
            			<input type="text" name="eEolDate" id="eEolDate" style="width:150px; text-align:center;" value="${assetInfo.eEolDate}"  class="inp_color" onchange="eDate('eEolDate')"  readonly  />
            			<span id="eEolDateTxt"></span>
            		</td>
          		</tr>	
          		<tr> 
        			<th>HOST NAME</th>
            		<td>
            			<input type="text" name="eHostName" id="eHostName" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eHostName}" />
            		</td>
            		<th rowspan="4">장비사진
            			<a class="form_btn md" onclick="addFile();" id="addBtn"> 
	            			<c:if test="${empty assetInfo.eAssetImageId }">추가</c:if>
	            			<c:if test="${not empty assetInfo.eAssetImageId }">변경</c:if>
            			</a>
            			<c:if test="${not empty assetInfo.eAssetImageId }">
            				<a class="form_btn md" onclick="delFile();" id="delBtn">이미지 삭제 </a>
            			</c:if>
           			</th>
            		<td rowspan="4">
						<input type="hidden" id="eAssetImageName"  name="eAssetImageName" value="${assetInfo.eAssetImageName}">
						<input type="hidden" id="eAssetImageId"  name="eAssetImageId" value="${assetInfo.eAssetImageId}">
						<input type="hidden" id="eAssetImageExt"  name="eAssetImageExt" value="${assetInfo.eAssetImageExt}">
						
						 <iframe id="pdfViewer" width="200px;" height="200px;" frameborder="0"></iframe>
	             		 <img id="eImgViewer"  style="display: none;width: 200px;height: 200px;"  src="" onclick="eDownload();">
	             		<br> <span>※이미지 추가 및 변경, 삭제 후 저장을 꼭 하셔야 반영됩니다.</span>
					</td>
				</tr>	
        		<tr>
            		<th>IP</th>
            		<td>
            			<input type="text" name="eIp" id="eIp" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eIp}"  />
            		</td>
          		</tr>	
          		<tr>
          			<th>OS</th>
            		<td>
            			<input type="text" name="eOs" id="eOs" style="width:95%; text-align:left;" maxLength="100" value="${assetInfo.eOs}" />
            		</td>
          		</tr>		
				<tr>          		
					<th>비고</th>
					<td colspan="1">
						<input id="eAssetEtc" name="eAssetEtc" style="resize: none; width:95%;" maxLength="100" value="${assetInfo.eAssetEtc}" />
					</td>
          		</tr>		          		 
			</tbody>
		</table>
	</div>
	<div class="content_top nofirst with_btn">
		<div class="content_tit">
			<h2>라이선스 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addLicense();">라이선스 정보 추가</button>
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
					<th style="width: 12%;"><span style="color: red">* </span>적용 수량</th> 
				</tr>
			</thead>
			<tbody id="lineRowLicense">
					 <c:forEach var="list" items="${assetList}" varStatus="i">
						 <tr >
						 		<td>
									<a class='del' onclick="delRow(this);">X</a>
								</td>
							
								<td>${list.eManufacturer }
									<input type='hidden' id='eSWRegisterKey_${i.index}' name='eSWRegisterKey' value='${list.eSWRegisterKey }'/>
								</td>
							
								<td>${list.eProductName }</td>	
							
								<td>${list.eVersion }</td>
							 	<td>${list.ePurchaseDate }</td>
							 	<td>${list.eEndDate }</td>
							 	<td>${list.eRemarks }</td>
							
								<td>
									<input type='text' id='eLicenseQuantity_${i.index}' name='eLicenseQuantity' value='${list.eLicenseQuantity }' style='width:98%; text-align:left;' maxLength='4' onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"  />
								</td>	
						 </tr>
					 </c:forEach>
					<c:if test="${empty assetList}">
						<tr>
							<td colspan="8" style="text-align: center;">등록 정보가 없습니다.</td>
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
						<th style="width: 10%;">결재순서</th>
						<th style="width: 10%;">결재구분</th>
						<th style="width: *%;">결재자</th>
					</tr>
				</thead>
				<tbody id="lineRow3"> 
					<c:forEach var="slist" items="${signList}" varStatus="j">
							<tr>
								<td>
									<a class='del' onclick="delRowTwo(this);">X</a>
			
									<input type='hidden' id='sSignStaffKey_${j.index}' name='sSignStaffKey' value='${slist.sSignStaffKey}'/>
									<input type='hidden' id='sSignStaffPosition_${j.index}' name='sSignStaffPosition' value='${slist.sSignStaffPosition}'/>
									<input type='hidden' id='sSignStaffName_${j.index}' name='sSignStaffName' value='${slist.sSignStaffName}'/>
									<input type='hidden' id='sSignSequence_${j.index}' name='sSignSequence' value='${slist.sSignSequence}'/>
									<input type='hidden' id='sSignStaffGubun_${j.index}' name='sSignStaffGubun' value='${slist.sSignStaffGubun}'/>
								</td>
							
								<td>
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
		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' }">
		<button type="button" class="form_btn active" onclick="update_go();">저장</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
	</div>
</form>
