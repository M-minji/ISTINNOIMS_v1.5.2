<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<!-- jBox -->
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){	
		datepickerSet("eRequestDate", "eProcessingDate");
		const today = nowDate();
		$("#eCreationDate").val(today);
		$("#eCreationDateDisplay").text(today);
		set_eHandler();
// 		getCateData(1);  10월 2일 요청사항으로 제외처리
// 		settting1();
		
		$("#eRequestDate").val(nowDate());
		$("#eProcessingDate").val(nowDate());
		 $('#oPass').prop('checked', true);
		 $("#oSignPass").val("Y");
		
		 if (typeof (history.pushState) != "undefined") { 
// 				history.pushState(null, '장애등록페이지', '*/*/testURL'); 
			  history.pushState(null, null, location.href);
			} else { 
				//브라우저가 지원하지 않는 경우 처리
				alert(1);
			}
		
	});
	
	function addFile(){
		  var url = "/mes/issue/popup/kw_File_add.do";
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

	function settting1(){  
		 var selectedValue2 = $('#eIssueType option:selected').data('value2');
         $('#eIssueTypeName').val(selectedValue2);

         window.selectName = function(selectElement, hiddenElementId) {
             var selectedOption = $(selectElement).find('option:selected');
             var value2 = selectedOption.data('value2');
             $('#' + hiddenElementId).val(value2);
         }
	}
	function getCateData(depth){
			$.ajax({
					type		: "post"
				,	dataType	: "json"
				,	url			: "/mes/maintance/kw_getCateListAjax.do"
				,	data		: {
						kPositionUpKey : $("#eIssueSelect"+(depth-1)).val()
					}
				,	success		: function(msg){
					var selectElement = document.getElementById("eIssueSelect"+depth);
	
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
					$(innerStr).appendTo("#eIssueSelect"+depth);
				}
				, error		: function(e){
					alert("에러발생");
				}
			});

		
		for(var i=2; i>=0; i--){
			if(document.getElementById("eIssueSelect"+i).value != 0){
				$("#eIssueCateKey").val(document.getElementById("eIssueSelect"+i).value);
				$("#eIssueCateName").val(document.getElementById("eIssueSelect"+i).options[document.getElementById("eIssueSelect"+i).selectedIndex].textContent);
				
				return 0;
			}
		}
	}
	
	// 현재날짜
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}
	
	
	var fileIndex = 0;
	function fileAdd(AddFileId, atchFileName){
		
		var ulobj = document.getElementById("lineRow");
		var liobj = document.createElement('tr');
		var idx = ulobj.childNodes.length;
		
		fileIndex++;
		
		liobj.id = "filename_" + fileIndex;
		liobj.style.padding = "0";
		ulobj.appendChild(liobj);
		
		var innerStr = "";
		innerStr +=	"		<td>";
		innerStr += "			<a class='del' onclick=\"delRow(this);\" style='text-decoration:none;'>X</a>";
		innerStr +=	"		</td>";
		innerStr +=	"		<td>";
		innerStr += 			atchFileName;
		innerStr += "			<input type='hidden' id='fileKey'   name='fileKey' value='0' />";
		innerStr += "			<input type='hidden' id='AddFileId"+btnGubun+"' name='eAddFileId' value='"+AddFileId+"' />";
		innerStr += "			<input type='hidden' id='atchFileName"+btnGubun+"' name='atchFileName' value='"+atchFileName+"' />";
		innerStr +=	"		</td>";
		liobj.innerHTML = innerStr;	
		
	} 
	
	var btnGubun = "";
	function mesIMGreg(gubun) { 
		btnGubun = gubun;
		var url = "/mes/maintance/popup/mesIMGregAdd.do";
		window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	}
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/issue/kw_issue_lf.do";
		document.frm.submit();
	}
	
	
	//파일 추가
	var rowIndex = 0;
		
	function addRow(){
			
		var innerStr = "";
			
		// 구분(행삭제)
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick='delRow(this);'>X</a>";
		innerStr += "		</td>";
		
		// 파일명
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='hidden' id='fileKey' name='fileKey' value='0' />";
		innerStr +="			<input type='hidden' id='fileIndex' name='fileIndex' value='"+rowIndex+"' />";
		innerStr +="			<input type='file' id='filename' name='filename"+rowIndex+"' style='width:300px' />";
		innerStr += "		</td>";
		innerStr += "	</tr>";
			
		$(innerStr).appendTo("#lineRow");
		
		rowIndex++;
	}
	
	
	// 등록
	function insert_go(){
		if(chkIns()){
			if(confirm("등록하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/issue/kw_issue_i.do";
				document.frm.submit();
			}
		}
	}
	
	// validation
	function chkIns(){
// 		if($("#eIssueSelect1").val() == "0"){
// 			alert("요청기관을 선택하세요.");
// 			return false;
// 		}
		
		if($("#eIssueTypeName").val() == ""){
			alert("상세구분을 선택하세요.");
			$("#eIssueTypeName").focus();
			return false;
		}
		
		if($("#eAssetType").val() == ""){
			alert("자산유형을 선택하세요.");
			$("#eAssetType").focus();
			return false;
		}
		if($("#eRequester").val() == ""){
			alert("요청자를 선택하세요.");
			$("#eRequester").focus();
			return false;
		}
		if($("#eProcessingTypeName").val() == ""){
			alert("처리유형을 선택하세요.");
			$("#eProcessingTypeName").focus();
			return false;
		}
		
		
		
			
		var eRowWorkerArr = document.getElementsByName("eRowWorker").length;
		
		if(eRowWorkerArr > 0){
			for(var j=0; j < eRowWorkerArr ; j++){
				var eRowWorker = document.getElementsByName("eRowWorker")[j].value;
				if (eRowWorker.trim() == '') {
					alert((j+1)+"번째 처리자명을 입력하세요.");
					document.getElementsByName("eRowWorker")[j].focus;
					return false;
				}
			}
			
			for(var j=0; j < eRowWorkerArr ; j++){
				var eRowWorker = document.getElementsByName("eRowWorker")[j].value;
				var eRowDepartment = document.getElementsByName("eRowDepartment")[j].value;
				var eRowPosition = document.getElementsByName("eRowPosition")[j].value;
				var eRowContact = document.getElementsByName("eRowContact")[j].value;
				var eRowNotes = document.getElementsByName("eRowNotes")[j].value;
				
				document.getElementsByName("eRowWorker")[j].value = ConverttoHtml(eRowWorker);
				document.getElementsByName("eRowDepartment")[j].value = ConverttoHtml(eRowDepartment);
				document.getElementsByName("eRowPosition")[j].value = ConverttoHtml(eRowPosition);
				document.getElementsByName("eRowContact")[j].value = ConverttoHtml(eRowContact);
				document.getElementsByName("eRowNotes")[j].value = ConverttoHtml(eRowNotes);
				
				if(j==0){
					$("#eHandler").val($("<div>").text(eRowWorker).html()); 
					$("#eHandlerOrg").val($("<div>").text(eRowDepartment).html());
				}
				
			}
		}else{
			alert("처리자 정보가 없습니다.");
			return false;
		}
		
		
		if($("#oSignPass").val() != 'Y'){
		if(document.getElementsByName("sSignStaffKey").length == 0){
			alert("승인권자를 선택해주세요.");
			return false;
			}
		}
		$("#eIssueContent").val($("<div>").text($("#eIssueContent").val()).html());
		return true;
	}
	
	function selectName(selectElement, inputId) {
	    var selectedOption = selectElement.options[selectElement.selectedIndex];
	    var selectedValue = selectedOption.getAttribute('data-value2');
	    document.getElementById(inputId).value = selectedValue;
	    
	    if (inputId === "eProcessingTypeName") {
	        // eProcessingType의 선택된 data-value2 값 가져오기
	        const selectedProcessingType = selectedOption.getAttribute('data-value2');
	        const issueTypeSelect = document.querySelector('#eIssueType');
	        const options = issueTypeSelect.querySelectorAll('option');
	        
	        if (!issueTypeSelect.querySelector('option[value=""]')) {
	            const defaultOption = document.createElement('option');
	            defaultOption.value = "";
	            defaultOption.textContent = "선택";
	            issueTypeSelect.prepend(defaultOption);
	        }
	        
	        // eIssueType 옵션 필터링
	        options.forEach(option => {
	            const issueTypeValue = option.getAttribute('data-value3');
	            if (selectedProcessingType && issueTypeValue === selectedProcessingType) {
	                option.style.display = '';
	                
	            } else {
	                option.style.display = 'none';
	            }
	        });

	        // eIssueType 선택 초기화
        	issueTypeSelect.value = "";
       		$("#eIssueTypeName").val("");
	    }
	    
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
	
	 
	 
	
	function sel_assetTwo(){	
		var sUrl = "/mes/maintance/kw_maintance_box_lf.do?searchType=Y";
		window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
		window.focus();
	}
	
	 function clearTxt(objId) {
         $("#"+objId).val("");
     }
	 
	 
	 
	 function handleOPassClick() {
			// 체크박스의 상태를 직접 변수에 저장
		    var isChecked = $("#oPass").is(":checked");
		    if(isChecked){
		    	 $("#oSignPass").val("Y");
		    		var elements = document.getElementsByName("sSignStaffKey");
			        if (elements.length > 0) {
			            if (confirm("선택한 결재자 정보를 삭제하고 \n결재 제외처리 하시겠습니까?")) {
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
	 
		 var fileRowIdex = 0;

		  function addFileInfoRow(eFileID,eFileName,eFileExt){
			  var eAssetKeyArr = document.getElementsByName("eFileID").length;
				if(eAssetKeyArr == 0){
					var tbody = document.getElementById("fileRow");
				    tbody.innerHTML = "";  
				}
				var innerStr = "";
				
				// 행삭제
				innerStr += "	<tr>";
				innerStr += "		<td class='tac'>";
				innerStr += "			<a class='del' onclick=\"delRowf(this);\">X</a>";
				innerStr += "		</td>";
				innerStr += "		<td>" +eFileName;
				innerStr += "			<input type='hidden' id='eFileID_"+fileRowIdex+"' name='eFileID' value='"+eFileID+"'/>";
				innerStr += "			<input type='hidden' id='eFileName_"+fileRowIdex+"' name='eFileName' value='"+eFileName+"'/>";
				innerStr += "			<input type='hidden' id='eFileExt_"+fileRowIdex+"' name='eFileExt' value='"+eFileExt+"'/>";
				innerStr += "		</td>";
	 
				innerStr += "	</tr>";
				
				$(innerStr).appendTo("#fileRow");	
				
				fileRowIdex++;
				
			}
		  
		  function delRowf(obj){
				var tr = $(obj).parent().parent();
				tr.remove();
				  // eAssetKey의 개수를 다시 계산
			    var eAssetKeyArr = document.getElementsByName("eFileID").length;
			    
			    // eAssetKey의 개수가 0이면 메시지를 추가
			    if (eAssetKeyArr == 0) {
			        var tbody = document.getElementById("fileRow");
			        var messageRow = document.createElement('tr');
			        var messageCell = document.createElement('td');
			        
			        messageCell.colSpan = 2;
			        messageCell.textContent = "첨부파일을 선택하세요.";
			        messageCell.classList.add('tac');
			        
			        
			        messageRow.appendChild(messageCell);
			        tbody.appendChild(messageRow);
			    }
			}
		
		
		var row_HandlerIndex = 0;
		function set_eHandler(){
			
			var eRowWorkerArr = document.getElementsByName("eRowWorker").length;
			if(eRowWorkerArr == 0){
				var tbody = document.getElementById("lineRowTwo");
			    tbody.innerHTML = "";  
			}
			
			var innerStr = "";
			
			// 행삭제
			innerStr += "	<tr>";
			innerStr += "		<td class='tac'>";
			innerStr += "			<a class='del' onclick=\"delRow(this);\">X</a>";
			innerStr += "		</td>";
			// 처리자  
			innerStr += "		<td>";
			innerStr += "			<input type='text' id='eRowWorker_"+row_HandlerIndex+"' name='eRowWorker' value='' maxLength='30'/>";
			innerStr += "		</td>";		
			// 처리자 소속소속
			innerStr += "		<td>";
			innerStr += "			<input type='text' id='eRowDepartment_"+row_HandlerIndex+"' name='eRowDepartment' value='' maxLength='30'/>";
			innerStr += "		</td>";
			innerStr += "		<td>"; 
			innerStr += "			<input type='text' id='eRowPosition_"+row_HandlerIndex+"' name='eRowPosition' value='' maxLength='30'/>";
			innerStr += "		</td>";
			// 내용
			innerStr += "		<td>";
			innerStr += "			<input type='text' id='eRowContact_"+row_HandlerIndex+"' name='eRowContact' value='' maxLength='30'/>";
			innerStr += "		</td>";
			innerStr += "		<td>";
			innerStr += "			<input type='text' id='eRowNotes_"+row_HandlerIndex+"' name='eRowNotes' value='' maxLength='30'/>";
			innerStr += "		</td>";		
			innerStr += "	</tr>";
			
			$(innerStr).appendTo("#lineRowTwo");	
			
			row_HandlerIndex++;
		}
		
</script>


<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/issue/kw_issue_i.do"> 
	<input type="hidden" id="pageIndex" 		name="pageIndex" 			value="${mesIssueVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" 	value="${mesIssueVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" 		name="topStartDate" 		value="${mesIssueVO.topStartDate}" />
	<input type="hidden" id="topEndDate" 		name="topEndDate" 			value="${mesIssueVO.topEndDate}" />
	<input type="hidden" id="searchWord" 		name="searchWord" 			value="${mesIssueVO.searchWord}" />
	<input type="hidden" id="searchType" 		name="searchType" 			value="${mesIssueVO.searchType}" />
		
 	<input type="hidden" id="eIssueStaffKey" name="eIssueStaffKey" value="${staffVo.kStaffKey}"/>
	<input type="hidden" id="eIssueStaffName" name="eIssueStaffName" value="${staffVo.kStaffName}"/>
	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	
	<input type="hidden" id="eHandler" name="eHandler"     />
	<input type="hidden" id="eHandlerOrg" name="eHandlerOrg"   />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>장애정보 등록 </h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${staffVo.kStaffName}
						 <input type="hidden" id="eAuthor" name="eAuthor"  value="${staffVo.kStaffName}" maxlength="50"/>
					</td>
					<th >등록일</th>
					<td >
						<input type="hidden" id="eCreationDate" name="eCreationDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
						<span id="eCreationDateDisplay"></span>
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
					<th ><span style="color: red">* </span>자산유형</th>
					<td colspan="3" >
						<input type="hidden" id="eAssetTypeName" name="eAssetTypeName" />
						<select id='eAssetType' name='eAssetType'  onchange="selectName(this,'eAssetTypeName')" style="width:120px;" >
							<option value=''>선택</option>
							<c:forEach var='gubun36List' items='${gubun36List}'>
								<option value='${gubun36List.sGubunKey}' data-value2='${gubun36List.sGubunName}'>${gubun36List.sGubunName}</option>						
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>처리유형</th>
					<td  colspan="1"> 
						<input type="hidden" id="eProcessingTypeName" name="eProcessingTypeName" />	
						<select id="eProcessingType" name="eProcessingType" style="width:120px;" onchange="selectName(this,'eProcessingTypeName')" >
						<option value="">선택</option>
							<c:forEach var="gubun33List" items="${gubun33List}">
								<option value='${gubun33List.sGubunKey}' data-value2='${gubun33List.sGubunName}' >${gubun33List.sGubunName}</option>							
							</c:forEach>		       				
		     			</select>
					</td>
					<th><span style="color: red">* </span>상세구분</th>
					<td>
						<input type="hidden" id="eIssueTypeName" name="eIssueTypeName" />
						<select id="eIssueType" name="eIssueType" style="width:120px;" onchange="selectName(this,'eIssueTypeName')">
						<option value="">선택</option>
						<c:forEach var="gubun34List" items="${gubun34List}">
							<option value="${gubun34List.sGubunKey}" data-value2='${gubun34List.sGubunName}' data-value3='${gubun34List.sGubunEtc}' >${gubun34List.sGubunName}</option>	
													
						</c:forEach>		       				
		     			</select>
					</td>
				</tr>
				<tr>
					<th ><span style="color: red">* </span>요청자</th>
					<td >
						<input type="text" id="eRequester" name="eRequester" style="width:75%;" maxLength="50" />
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eRequester')">담당자 선택</a>
					</td>
					<th >요청자 소속</th>
					<td >
						<input type="text" id="eRequesterOrg" name="eRequesterOrg" style="width:95%;" maxLength="50"/>
						<span id="eRequesterOrgTxt" style="display: none;"></span>
					</td>
  				</tr>
				<tr>
					<th >요청일자</th>
					<td >
						<input type="text" id="eRequestDate" name="eRequestDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly"/>
					</td>
					<th >  <span style="cursor: pointer;" onclick="clearTxt('eProcessingDate');">처리일자</span></th>
					<td >
						<input type="text" id="eProcessingDate" name="eProcessingDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly"/>
					</td>
  				</tr>
				<tr>
					<th>
						요청내용
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
						<textarea id="eIssueContent" name="eIssueContent" cols="100%" rows="20" style="font-size: 20px; width: 100%; height: 300px; resize: none;"  maxLength="1000"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="normal_table row mt20">
		<table>
			<colgroup>
				<col style="width: 200px;" />
				<col />
			</colgroup>
<%-- 			<caption style="text-align: left; margin-bottom:10px;"> --%>
<!-- 				  style="float:right" -->
<%-- 			</caption> --%>
			<thead>
				<tr>
					<th colspan="2">첨부파일  <a class="form_btn md ml5" onclick="addFile()" >파일 선택</a></th>
				</tr>
				<tr>
					<th style="width: 200px;">구분</th>
					<th style="width: 80%;">파일명</th>
				</tr>
			</thead>
			<tbody id="fileRow">
				<tr>
					<td colspan="2" class="tac">첨부파일 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	 
 	<div class="content_top nofirst with_btn" id="viewDiv2">
		<div class="content_tit">
			<h2>처리자 정보</h2>
		</div>
		<div class="btns">
			<button type="button" class="form_btn md" onclick="set_eHandler()">처리자 추가</button>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
				<thead>
				<tr>
					<th style="width: 10%;">구분</th>
					<th style="width: 18%;"><span style="color: red">* </span>처리자명</th>
					<th style="width: 18%;">처리자소속</th>
					<th style="width: 18%;">처리내용</th>
					<th style="width: 18%;">연락처</th>
					<th style="width: 18%;">비고</th>
				</tr>
			</thead>
			<tbody id="lineRowTwo">
				<tr>
					<td colspan="6">처리자 정보를 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
 	<div class="content_top nofirst with_btn"- id="viewDiv1">
		<div class="content_tit">
			<h2>장비 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" onclick="sel_asset()" class="form_btn md">장비 선택</button>
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
				<tr>
					<td colspan="10">장비를 선택하세요.</td>
				</tr>
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
				<col style="width: 200px;" />
				<col style="width: 200px;" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">결재 정보</th>
				</tr>
				<tr>
					<th>결재순서</th>
					<th>결재구분</th>
					<th>결재자</th>
				</tr>
			</thead>
			<tbody id="lineRow3">			
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
			<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>

</form>