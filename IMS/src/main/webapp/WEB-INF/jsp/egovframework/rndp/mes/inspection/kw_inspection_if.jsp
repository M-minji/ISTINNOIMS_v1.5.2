<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		datepickerIdSet("eRegistrationDate");
		datepickerIdSet("eInspectionDate");
		const today = nowDate();
		$('#eInspectionDate').val(today);
		$('#eRegistrationDate').val(today);
		$('#eRegistrationDateDisplay').text(today);
		
		$('#oPass').prop('checked', true);
		$("#oSignPass").val("Y");
	});
		
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}	
	
	function insert_go(){
		 	var inspectionType = document.getElementById('eInspectionType').value;
		    var inspectionCycle = document.getElementById('eInspectionCycle').value;
		    var eInspector = document.getElementById('eInspector').value;

		    if (!inspectionType) {
		        alert("점검구분을 선택해주세요.");
		        inspectionType.focus();
		        return false;
		    }

		    if (!inspectionCycle) {
		        alert("점검주기를 선택해주세요.");
		        inspectionCycle.focus();
		        return false;
		    }
		    if (!eInspector) {
		        alert("점검자를 선택하세요.");
		        eInspector.focus();
		        return false;
		    }
		    
		    if($("#oSignPass").val() != 'Y'){
				if(document.getElementsByName("sSignStaffKey").length == 0){
					alert("승인권자를 선택해주세요");
					return false;
					}
				}
		    
		if(confirm("등록하시겠습니까?")){
			$("#eRemark").val($("<div>").text($("#eRemark").val()).html());
			$("#eOther").val($("<div>").text($("#eOther").val()).html());
			document.writeForm.action = "/mes/inspection/kw_inspection_i.do";
			document.writeForm.submit();
		}
		
	
	}
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/inspection/kw_inspection_lf.do";
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
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetTypeName').value = value2;
     }
	 function eAssetStatusName() {
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
			innerStr += "		<td>";
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
	        messageCell.textContent = "점검 서류를 선택하세요.";
	        
	        
	        messageRow.appendChild(messageCell);
	        tbody.appendChild(messageRow);
	    }
	}
		
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
		
		  // eAssetKey의 개수를 다시 계산
	    var eFileIDArr = document.getElementsByName("eAssetKey").length;
	    
	    // eAssetKey의 개수가 0이면 메시지를 추가
	    if (eFileIDArr == 0) {
	        var tbody = document.getElementById("lineRow");
	        var messageRow = document.createElement('tr');
	        var messageCell = document.createElement('td');
	        
	        messageCell.colSpan = 10;
	        messageCell.textContent = "장비를 선택하세요.";
	        
	        messageRow.appendChild(messageCell);
	        tbody.appendChild(messageRow);
	    }
	}

	function selectName(selectElement, inputId) {
	    var selectedOption = selectElement.options[selectElement.selectedIndex];
	    var selectedValue = selectedOption.getAttribute('data-value2');
	    document.getElementById(inputId).value = selectedValue;
	    
	    
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
</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	
	<div class="content_top">	
		<div class="content_tit">
			<h2>점검 정보 등록</h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${staffVO.kStaffName}
						<input type="hidden" id="eRegistrant" name="eRegistrant"  value="${staffVO.kStaffName}" maxlength="50"/>
						<input type="hidden" id="eStaffKey" name="eStaffKey"  value="${staffVO.kStaffKey}" maxlength="50"/>
					</td>
					<th >등록일</th>
					<td >
						<input type="hidden" id="eRegistrationDate" name="eRegistrationDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
						<span id="eRegistrationDateDisplay"></span>
					</td>
  				</tr>		
			</tbody>
		</table>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
				
				<tr>
					<th><span style="color: red">* </span>점검구분</th>
					<td>
						<input type="hidden" id="eInspectionTypeName" name="eInspectionTypeName" />
							<select id='eInspectionType' name='eInspectionType' style="width: 100px;"  onchange="selectName(this,'eInspectionTypeName')" required="required">
								<option value=''>선택</option>
								<c:forEach var='list' items='${gubun38List}'>
									<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
								</c:forEach>
							</select>
					</td>
					<th><span style="color: red">* </span>점검주기</th>
					<td>
						<input type="hidden" id="eInspectionCycleName" name="eInspectionCycleName" />
							<select id='eInspectionCycle' name='eInspectionCycle' style="width: 100px;"  onchange="selectName(this,'eInspectionCycleName')"  required="required">
								<option value=''>선택</option>
								<c:forEach var='list' items='${gubun39List}'>
									<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
								</c:forEach>
							</select>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>점검일자</th>
					<td colspan="3">
						<input type="text" name="eInspectionDate" id="eInspectionDate" style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly"/>
					</td>
				
				</tr>
				<tr>
					<th><span style="color: red">* </span>점검자</th>
					<td>
						<input type="text" id="eInspector" name="eInspector" style="width:75%;" maxLength="50" />
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eInspector')" >담당자 선택</a>
						<input type="hidden" name="eInspectionResult" id="eInspectionResult" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eRequester" id="eRequester" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eOrganization" id="eOrganization" style="width:95%; text-align:left;" maxLength="150" value=""/>
						<input type="hidden" name="eDepartment" id="eDepartment" style="width:95%; text-align:left;" maxLength="150" value=""/>
					</td>
					<th>점검자 소속</th>
					<td>
						<input type="text" id="eInspectorOrg" name="eInspectorOrg" style="width:95%;" maxLength="50" />
						<span id="eInspectorOrgTxt" style="display: none;"></span>
					</td>
				</tr>
				<tr>
					<th>특이사항</th>
					<td id="td_editor" colspan="3" align="center" scope="row">
						<input type="hidden" name="eOther" id="eOther" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<textarea id="eRemark" name="eRemark" cols="100%" rows="20" style="font-size: 20px; width: 100%; height: 300px; resize: none;" maxLength="1000"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="normal_table">
		<table>
			<colgroup>
				<col style="width: 200px" />
				<col />
			</colgroup>
<%-- 			<caption style="text-align: left; margin-bottom:10px;"> --%>
<!-- 				  style="float:right" -->
<%-- 			</caption> --%>
			<thead>
				<tr>
					<th colspan="2">점검 서류  <a class="form_btn md ml5" onclick="addFile()" >파일 선택</a></th>
				</tr>
				<tr>
					<th style="width: 200px;">구분</th>
					<th style="width: 75%;">파일명</th>
				</tr>
			</thead>
			<tbody id="fileRow">
				<tr>
					<td colspan="2">첨부파일 선택하세요.</td>
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
				<col style="width: 200px" />
				<col style="width: 200px" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">결재 정보</th>
				</tr>
				<tr>
					<th style="width: 200px">결재순서</th>
					<th style="width: 200px">부서</th>
					<th style="width: *%;">성명</th>
				</tr>
			</thead>
			<tbody id="lineRow3">			
			</tbody>
			
		</table>
	</div>
	
	<div class="bottom_btn">
		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' }">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
	</div>
</form>
