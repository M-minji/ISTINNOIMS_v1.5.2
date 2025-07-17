<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<link rel="stylesheet" href="/js/PretendardGOV-1.3.9/pretendard-gov-all.css">
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<script type="text/javascript">

function modal1(message, focusSelector) {
	lastScrollY = window.scrollY;
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
	        y: 65
	      },
	        onCloseComplete: function () {
	        	if (focusSelector) {
	            	window.scrollTo(0, 0);
	                setTimeout(() => {
	                    document.querySelector(focusSelector)?.focus();
	                }, 10);
	            } else{
	            	window.scrollTo(0, lastScrollY);
	            }
	        }
	  }).open();
  }
function modal3(message, onConfirm) {
	new jBox('Confirm', {
		content: message,
	    cancelButton: '아니요',
	    confirmButton: '네',
	    blockScrollAdjust: ['header'],
	    confirm: onConfirm
	  }).open();
  }
  
function setToolTip(){
		var elements = document.getElementsByName("sSignStaffKey");
		var checkbox = $('#oPass');
		if (checkbox.prop('checked')) {
		} else if(elements.length > 0){
			$("#approvalWrap").addClass("hoverToolTip");
				window.hoverTipBox = new jBox('Tooltip', {
		    attach: '.hoverToolTip',
		    theme: 'TooltipDark',
		    animation: 'zoomOut',
		    content: '선택된 결재선이 삭제됩니다.',
		    adjustDistance: {
		      top: 70,
		      right: 5,
		      bottom: 5,
		      left: 5
		    },
		    zIndex: 90
		  }); 
		}
	}
	function removeToolTip() {
		if (window.hoverTipBox) {
			$('.jBox-wrapper').remove();
			$('.jBox-tooltip').remove();
			$('#approvalWrap').removeClass('hoverToolTip');
			window.hoverTipBox = null;
		}
 }
	$(document).ready(function(){
		datepickerIdSet("eAssetDate");
		datepickerIdSet("eEosDate");
		datepickerIdSet("eEolDate");
		datepickerIdSet("aAssetDate");
		eAssetStatusCheck();
	
		 $('#oPass').prop('checked', true);
		 $("#oSignPass").val("Y");
		
		const today = nowDate();
		$('#eCreationDateDisplay').text(today);
		$('#eAssetDate').val(today);
		$('#aAssetDate').val(today);
		
		$("#filename").change(function(){
	         readURL(this);
	      });
		getCateData(1);
		updateAssetTypeName();
		
		$("#eAssetModel").blur(function() {
			modelCheck(this);
        });
	});
	
/* 	function handleOPassClick() {
		 체크박스의 상태를 직접 변수에 저장
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
	} */
	
	 function modelCheck(element) {
         var value = $(element).val();
	         $.ajax({
	 			type		: "post"
	 		,	dataType	: "json"
	 		,	url			: "/mes/asset/kw_asset_model_check.do"
	 		,	data		: {
	 			eAssetModel : value
	 			}
	 		,	success		: function(msg){
	 			var dataInfo  = msg.result.dataAdd;
		 			if (dataInfo.eEosDate !== null && dataInfo.eEosDate !== "") {
		 	            $("#eEosDate").val(dataInfo.eEosDate);
		 	           eDate("eEosDate");
		 	        }
		 	        if (dataInfo.eEolDate !== null && dataInfo.eEolDate !== "") {
		 	            $("#eEolDate").val(dataInfo.eEolDate);
		 	            eDate("eEolDate");
		 	        }
		 	        if (dataInfo.eLifespan !== null && dataInfo.eLifespan !== "" && Number.isInteger(parseInt(dataInfo.eLifespan))) {
		 	            $("#eLifespan").val(dataInfo.eLifespan);

		 	        }
	 			}
	 		});

     }
	var eAssetSNumCheckB = "F";
	 function eAssetSNumberCheck(ogj){
         var value = $(ogj).val();
         if (!value) {
      		// modal1("제조번호를 입력하세요.", "#eAssetSNumber");
              return; // 빈값일 경우 함수 종료
          }
	         $.ajax({
	 			type		: "post"
	 		,	dataType	: "json"
	 		,	url			: "/mes/asset/kw_asset_sNumber_check.do"
	 		,	data		: {
	 			eAssetSNumber : value
	 			}
	 		,	success		: function(msg){
	 			var dataInfo  = msg.result.dataAdd;
	 			 if (dataInfo) {
	                 // 데이터가 있는 경우 -> 중복 제조번호
					modal1("이미 등록된 제조번호입니다.", "#eAssetSNumber");
	      //           $("#eAssetSNumber").val(""); // 필드 초기화
	             }else {
	            	 eAssetSNumCheckB = "T";
	             }
	 			}
	 		});

     }
	 
	 var eAssetNumCheckB = "T";
	 function eAssetNumberCheck(obb){
		 eAssetNumCheckB = "F";
         var ttvalue = $(obb).val();
         if (!ttvalue) {
     	//	modal1("자산번호를 입력하세요.", "#eAssetNumber");
             return; // 빈값일 경우 함수 종료
         }
	         $.ajax({
	 			type		: "post"
	 		,	dataType	: "json"
	 		,	url			: "/mes/asset/kw_eAssetNumberCheck.do"
	 		,	data		: {
	 			eAssetSNumber : ttvalue
	 			}
	 		,	success		: function(msg){
	 			var dataInfo  = msg.result.dataAdd;
	 			 if (dataInfo) {
	                 // 데이터가 있는 경우 -> 중복 제조번호
					modal1("이미 등록된 자산번호입니다.", "#eAssetNumber");
	        //         $("#eAssetNumber").val(""); // 필드 초기화
	             } else {
	            	 eAssetNumCheckB = "T";
	             }
	 			}
	 		});

     }
	 
	 
	 
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}	
	
	function insert_go(){
		
		
		if(document.getElementById("eAssetTypeName").value == ""){
			modal1("자산유형를 선택하세요.", "#eAssetType");
	//		document.getElementById("eAssetType").focus();
			return false;
		}
		if(document.getElementById("eAssetNumber").value == ""){
			modal1("자산번호를 입력하세요.", "#eAssetNumber");
	//		document.getElementById("eAssetNumber").focus();
			return false;
		}

		if(document.getElementById("eAssetName").value == ""){
			modal1("자산명을 입력하세요.", "#eAssetName");
		//	document.getElementById("eAssetName").focus();
			return false;
		}
		if(document.getElementById("eAssetMaker").value == ""){
			modal1("제조사를 입력하세요.", "#eAssetMaker");
	//		document.getElementById("eAssetMaker").focus();
			return false;
		}
		
		if(document.getElementById("eAssetModel").value == ""){
			modal1("모델명을 입력하세요.", "#eAssetModel");
	//		document.getElementById("eAssetModel").focus();
			return false;
		}
	 
		if(document.getElementById("eAssetSNumber").value == ""){
			modal1("제조번호를 입력하세요.", "#eAssetSNumber");
	//		document.getElementById("eAssetSNumber").focus();
			return false;
		}
		
		if(eAssetNumCheckB == "F"){
			modal1("이미 등록된 자산번호입니다.", "#eAssetNumber");
			return false;
		}
		
		if(eAssetSNumCheckB == "F"){
			modal1("이미 등록된 제조번호입니다.", "#eAssetSNumber");
			return false;
		}
		
		
		
		var eLicenseQuantityArr = document.getElementsByName("eLicenseQuantity");
		if(eLicenseQuantityArr.length > 0){
			for (var aa = 0; aa < eLicenseQuantityArr.length; aa++) {
			 	var value = eLicenseQuantityArr[aa].value;
				if (isValidLicenseQuantity(value)){
					var text = "";
					if(aa+1 > 1) {
						text = (aa+1)+"번째 "
					}
					modal1(text + " 라이선스 적용 수량을 입력하세요.", "#eLicenseQuantityArr_" + aa);
				//	eLicenseQuantityArr[aa].focus();
					return;
			    }
			}
		}
		if($("#oSignPass").val() != 'Y'){
			if(document.getElementsByName("sSignStaffKey").length == 0){
				modal1("결재자를 선택하세요.");
				return false;
				}
			}
		modal3("등록하시겠습니까?", function() {
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
			
			
			sessionStorage.setItem("actionType", "create");
			document.writeForm.action = "/mes/asset/kw_asset_i.do";
			document.writeForm.submit();
		});
	
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
	
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/asset/kw_asset_lf.do";
		document.writeForm.submit(); 
	}
	
	//파일 선택시 이미지사진 띄우기
	function readURL(input) {
		
		var rValue = true;		 
	    var ext = ["bmp", "jpeg", "jpg", "gif", "png", "tiff", "pat", "tif"];
	    
	    rValue = checkFileExt($("#eAssetImageName"), ext); //확장자 체크
	    rValue = checkFileSize($("#filename"), "50000000"); //파일사이즈 체크
	    
	    
	    if(rValue){	//확장자 체크	
		
		    if (input.files && input.files[0]) {		    	
				var reader = new FileReader();				
				$('#image_section').show();
				
				reader.onload = function(e) {					
					$('#image_section').attr('src', e.target.result);
				}
		
				reader.readAsDataURL(input.files[0]);			  
		    }
		    
	    }else{
	    	
	    	$('#image_section').attr("src", "");
	    	$('#image_section').attr("style", "display : none;");
	    	$('#eAssetImageName').val("");
	    	$('#filename').val("");
	    	
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
					for(var i=0; i<list.length; i++){
						innerStr += "<option value = '"+(list[i].kPositionKey)+"'>"+(list[i].kPositionName)+"</option>"; 
					}
					$(innerStr).appendTo("#maintanceSelect"+depth);
				}
				, error		: function(e){
					alert("에러발생");
				}
			});

		
		for(var i=2; i>=0; i--){
			if(document.getElementById("maintanceSelect"+i).value != 0){
				$("#eMaintanceCateKey").val(document.getElementById("maintanceSelect"+i).value);
				$("#eMaintanceCateName").val(document.getElementById("maintanceSelect"+i).options[document.getElementById("maintanceSelect"+i).selectedIndex].textContent);
				
				return 0;
			}
		}
	}
	
	 function updateAssetTypeName() {
         var selectElement = document.getElementById('eAssetType');
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         if (!selectedOption || !selectedOption.value) {
             document.getElementById('eAssetTypeName').value = ''; // 유효하지 않을 때 빈 값 설정
             return; // 함수 종료
         }
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetTypeName').value = value2;
         
         var value3 = selectedOption ? selectedOption.getAttribute('data-value3') : null;
         if (value3 !== null && value3 !== undefined) {
             value3 = value3.trim();
         } 
         
         var regex = /^[a-zA-Z0-9\u3131-\uD79D]*$/; 
         
         if (!regex.test(value3)) {
             // 선택된 값이 한글, 영어, 숫자가 아닌 경우 assetNumberInput을 비움
             var assetNumberInput = document.getElementById('eAssetNumber');
             assetNumberInput.value = '';
         } else {
	         var inputElement = document.querySelector('#' + value3);
	         var assetNumberInput = document.getElementById('eAssetNumber');
	         if (inputElement && inputElement.value != '') {
	             assetNumberInput.value = inputElement.value;
	         } else {
	             assetNumberInput.value = ''; // 값이 없으면 빈 문자열로 설정
	         }
         }
     }
	 
	 function eAssetStatusCheck() {
         var selectElement = document.getElementById('eAssetStatus');
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetStatusName').value = value2;
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
	
	var keyList = "";
	function assetKeyListinput(){
		var rowArr = document.getElementsByName("eSWRegisterKey").length;
		
		if(rowArr > 0){
			for(var i=0; i < rowArr ; i++){
				var assetKey = document.getElementsByName("eSWRegisterKey")[i].value;
					keyList += (assetKey + ",");
			}
			keyList = keyList.slice(0, -1);
		}
	}
	
	function addLicense() {
		
		 const form = document.createElement("form");
		    form.method = "POST";
		    form.action = "/mes/asset/popup/kw_license_pop.do";
		    form.target = "eLicenseAdd";    

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

		    keyList = "";
		    assetKeyListinput();
		    
		    const aAssetKeyList = document.createElement("input");
		    aAssetKeyList.type = "hidden";
		    aAssetKeyList.name = "aAssetKeyList";
		    aAssetKeyList.value = keyList;
		    form.appendChild(aAssetKeyList);
		    
		 // 폼을 문서에 추가
		    document.body.appendChild(form);

		    // 새 창 열기
		    window.open("", "eLicenseAdd", "width=1280,height=700,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");

		    // 폼 전송
		    form.submit();

		    // 폼 제거
		    document.body.removeChild(form);
		    
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
 
    function eLifCheck() {
        // 도입일자를 가져옴
        var eAssetDate = document.getElementById('eAssetDate').value;
        // 수명 연도를 가져옴
        var eLifespan = document.getElementById('eLifespan').value;
        // 노후장비 판정을 위한 input 요소
        var eLifeType = document.getElementById('eLifeType');

        if (eAssetDate && eLifespan && !isNaN(eLifespan)) {
        
            // 도입일자를 Date 객체로 변환
            var assetDate = new Date(eAssetDate);
            var currentYear = new Date().getFullYear();
            var assetYear = assetDate.getFullYear();
            
            // 수명 연도 계산
            var lifespanYears = parseInt(eLifespan);
            // 도입일자의 연도와 수명 연도를 합산한 값이 현재 연도보다 작으면 노후장비로 판정
            if ((assetYear + lifespanYears) < currentYear) {
                eLifeType.value = "노후장비";
                $("#eLifespanTxt").html(" :: 노후장비");
            } else {
                eLifeType.value = "";
                $("#eLifespanTxt").html("");
            }
        }
    }
    
    function approvalPop(){
		
		 var checkbox = $('#oPass');
	    if (checkbox.prop('checked')) {
	    	
	    		checkbox.prop('checked', false);
	    		$("#oSignPass").val("N");
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
		setToolTip();
	}
	
	function deleteGyeoljaeList(){
		$('#lineRow3').empty();
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
	 	font-weight: 400;  
	    font-family: 'Pretendard GOV', sans-serif;
	}
	
	.jBox-Modal {
	  background: #4869fb !important;
	  border-radius: 8px !important;
   	  overflow: hidden !important;
   	  
}    
</style>
<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="searchWord" id="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="oSignPass" id="oSignPass" value="${mesAssetVO.oSignPass}" />

	<c:if test="${not empty eAssetCountList }">
		<c:forEach var="assetCount" items="${eAssetCountList}">
			 <input type="hidden" id="${assetCount.aAssetType}" value="${assetCount.aAssetTypeMax}" />
		</c:forEach>
	</c:if>
	<div class="content_top">	
		<div class="content_tit">
			<h2>장비정보 등록</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
            		<th>작성자</th>
            		<td>${staffVO.kStaffName}
						<input type="hidden" name="eAuthor" id="eAuthor" style="width:35%; text-align:left;"   value="${staffVO.kStaffName}" class="inp_color"/>
            		</td>
            		<th>등록일</th>
            		<td>
						<input type="hidden" id="aAssetDate" name="aAssetDate" style="width:150px; text-align:center;" class="inp_color" readonly />
						<span id="eCreationDateDisplay"></span>
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
					<input type="hidden" id="eAssetTypeName" name="eAssetTypeName" />
					<select id='eAssetType' name='eAssetType'  onchange="updateAssetTypeName()" style="width: 120px;" >
						<option value=''>선택</option>
						<c:forEach var='list' items='${gubun36List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'  data-value3='${list.sGubunEtc}'>${list.sGubunName}</option>						
						</c:forEach>
					</select>
					</td>
					<th><span style="color: red">* </span>자산번호</th>
					<td>
						<input type="text" name="eAssetNumber" id="eAssetNumber" style="width:100%; text-align:left;" maxLength="50" value="" onchange="eAssetNumberCheck(this)"/>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>자산명</th>
					<td>
						<input type="text" name="eAssetName" id="eAssetName" style="width:100%; text-align:left;" maxLength="100" value=""/>
					</td>
					<th>설치위치</th>
            		<td> 
            		 <input type="hidden" id="mMaintanceCateKey" name="eMaintanceCateKey" value="0"/>
						<input type="hidden" id="mMaintanceCateName" name="eMaintanceCateName" value=""/>
						<input type="hidden" id="maintanceSelect0" name="eaintanceSelect0" value="0"/>
						
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
						<input type="text" name="eAssetMaker" id="eAssetMaker" style="width:100%; text-align:left;" maxLength="30" value=""/>
					</td>
					<th><span style="color: red">* </span>모델명</th>
					<td>
						<input type="text" name="eAssetModel" id="eAssetModel" style="width:100%; text-align:left;" maxLength="30" value=""/>
					</td>
				</tr>
				
				
				<tr>
					<th><span style="color: red">* </span>제조번호(S/N)</th>
					<td>
						<input type="text" name="eAssetSNumber" id="eAssetSNumber" style="width:100%; text-align:left;" maxLength="30" value="" onchange="eAssetSNumberCheck(this)"/>
					</td>
					<th>자산상태</th>
					<td>
					 	<input type="hidden" id="eAssetStatusName" name="eAssetStatusName" value="" /><!-- 					 	style="pointer-events: none;"  class="inp_color"  -->
						<select id='eAssetStatus' name='eAssetStatus'  onchange="eAssetStatusCheck()" style="width: 150px;" >
							<c:forEach var='list' items='${gubun37List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
							</c:forEach>
						</select>
					</td>
				</tr>
          	
          		<tr>
          			<th>도입원가(원)</th>
            		<td>
						<input type="text" name="eAssetCost" id="eAssetCost" style="width:100%; text-align:left; padding-right: 5px;" maxLength="10" onblur="setComma(this.id)" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" value=""/>
            		</td>
            			<th>도입일</th>
            		<td>
						<input type="text" id="eAssetDate" name="eAssetDate" style="width:150px; text-align:center;" class="inp_color" readonly onchange="eLifCheck()"  />
            		</td>
          		</tr>		
        		<tr>
            		<th>장비구분</th>
            		<td>
						<input type="text" id="eDeviceType" name="eDeviceType" style="width:100%;" maxlength="50" oninput="convertToUppercase(this)"  />
            		</td>
            		<th>사업명</th>
            		<td>
						<input type="text" name="eAssetPurpose" id="eAssetPurpose" style="width:100%; text-align:left;" maxLength="50" value=""/>
            		</td>
          		</tr>		
        		<tr>
        			<th>내구연수</th>
					<td colspan="1">
            			<input type="text" name="eLifespan" id="eLifespan" style="width:150px; text-align:left; padding-right: 5px;" maxLength="2" value="" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"  onchange="eLifCheck()"  /> : (연 단위)
            			<input type="hidden" name="eLifeType" id="eLifeType" style="width:35%; text-align:left;" maxLength="100" value="일반장비" />
            			<span id="eLifespanTxt"></span>
<!--             			:  <select id="eLifeType" name="eLifeType"> -->
<!-- 					            <option value="일반장비" selected="selected">일반장비</option> -->
<!-- 					            <option value="노후장비">노후장비</option> -->
<!-- 					        </select> -->
            		
					</td>
            		<th>망구분</th>
            		<td>
						<input type="text" name="eNetworkType" id="eNetworkType" style="width:100%; text-align:left;" maxLength="50" value="" />
            		</td>
          		</tr>		
          		<tr>          		
					<th>EoS</th>
            		<td>
            			<input type="text" name="eEosDate" id="eEosDate" style="width:150px; text-align:center;" class="inp_color" onchange="eDate('eEosDate')"  readonly />
            			<span id="eEosDateTxt"></span>
            			*미입력시 2999-01-01 반영
            		</td>
            		<th>EoL</th>
            		<td>
            			<input type="text" name="eEolDate" id="eEolDate" style="width:150px; text-align:center;" class="inp_color" onchange="eDate('eEolDate')"  readonly  />
            			<span id="eEolDateTxt"></span>
            			*미입력시 2999-01-01 반영
            		</td>
          		</tr>	
          		<tr> 
        			<th>HOST NAME</th>
            		<td>
            			<input type="text" name="eHostName" id="eHostName" style="width:100%; text-align:left;" maxLength="30" value="" />
            		</td>
            		<th rowspan="4">장비사진<a class="form_btn md ml5" onclick="addFile();">추가</a></th>
            		<td rowspan="4">
						<input type="hidden" id="eAssetImageName"  name="eAssetImageName">
						<input type="hidden" id="eAssetImageId"  name="eAssetImageId">
						<input type="hidden" id="eAssetImageExt"  name="eAssetImageExt">
						 <iframe id="pdfViewer" width="200px;" height="200px;" frameborder="0"></iframe>
               			 <img id="eImgViewer"  style="display: none;width: 200px;height: 200px;"  src="" >
					</td>
				</tr>		
        		<tr>
            		<th>IP</th>
            		<td>
            			<input type="text" name="eIp" id="eIp" style="width:100%; text-align:left;" maxLength="30" value="" />
            		</td>
          		</tr>			
          		<tr>
            		<th>OS</th>
            		<td>
            			<input type="text" name="eOs" id="eOs" style="width:100%; text-align:left;" maxLength="30" value="" />
            		</td>
          		</tr>
				<tr>          		
					<th>비고</th>
					<td>
						<input id="eAssetEtc" name="eAssetEtc" style="resize: none; width:100%;" maxLength="100" />
					</td>
          		</tr>			
			</tbody>
		</table>
	</div>
	
	<div class="content_top nofirst with_btn" style="padding-top:20px;">
		<div class="content_tit">
			<h2>라이선스 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addLicense();">라이선스 추가</button>
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
					<tr>
						<td colspan="8" style="text-align: center;">등록 정보가 없습니다.</td>
					</tr>
			</tbody>
		</table>
	</div>
	<div class="content_top nofirst with_btn">
		<div class="content_tit flex">
			<h2>결재 정보</h2>
			<div id="approvalWrap">
			<label for="oPass" class="inp_chkbox">
				<input type="checkbox" id="oPass" name="oPass" class="checkbox" onclick="handleOPassClick();" onchange="removeToolTip();"/>
				<i></i>
				결재 제외
			</label></div>
		</div>
		<div class="btns">
			 <button type="button" onclick="approvalPop()" class="form_btn md">결재선 선택</button>
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
					<th>결재순서</th>
					<th>결재구분</th>
					<th>결재자</th>
				</tr>
			</thead>
			<tbody id="lineRow3">
				<tr>
					<td colspan="3">결재 정보가 없습니다.</td>
				</tr>			
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		<button type="button" class="form_btn" onclick="cancle();">취소</button>
	</div>
</form>
