<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		datepickerIdSet("eEntryImportDate");
		datepickerIdSet("eEntryWdate");
		const today = nowDate();
		$('#eEntryWdateDisplay').text(today);
		$('#eEntryImportDate').val(today);
		$('#eEntryWdate').val(today);
		
		 $('#oPass').prop('checked', true);
		 $("#oSignPass").val("Y");
		 
		 addRow();
		 
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
		  var eAssetKeyArr = document.getElementsByName("eAssetTypeName").length;
		 
		    // eAssetKey의 개수가 0이면 메시지를 추가
		    if (eAssetKeyArr == 0) {
		    	alert("장비 정보가 없습니다.");
		    	return;
		    }else{
		    	var eAssetTypeNameInputs = document.getElementsByName("eAssetTypeName");
	    	    var eAssetNameInputs = document.getElementsByName("eAssetName");
		    	 for(var i=0; i < eAssetKeyArr ; i++){
		    		 var eAssetTypeNameValue = eAssetTypeNameInputs[i].value.trim();
		    	     var eAssetNameValue = eAssetNameInputs[i].value.trim();
		    	        
		    	        if (eAssetTypeNameValue === '') {
		    	            alert("장비 유형을 선택하세요.");
		    	            return;
		    	        }

		    	        if (eAssetNameValue === '') {
		    	            alert("자산명을 입력하세요.");
		    	            eAssetNameInputs[i].focus();
		    	            return;
		    	        }
				    }
		    }
		    eEntryImporter

		    if($("#eEntryImporter").val() == ""){
		    	alert("반입확인자를 선택하세요.");
		    	$("#eEntryImporter").focus();
		    	return;
		    }
		    
		    
		    
		    
		    
		    
				if($("#oSignPass").val() != 'Y'){
				if(document.getElementsByName("sSignStaffKey").length == 0){
					alert("승인권자를 선택해주세요");
					return false;
					}
				}
		    
		    
		  
		    for(var i=0; i < eAssetKeyArr ; i++){
				var eAssetTypeName = document.getElementsByName("eAssetTypeName")[i].value;
				var eAssetName = document.getElementsByName("eAssetName")[i].value;
				var eAssetMaker = document.getElementsByName("eAssetMaker")[i].value;
				var eAssetSNumber = document.getElementsByName("eAssetSNumber")[i].value;
				
				var eAssetModel = document.getElementsByName("eAssetModel")[i].value;
				var eAssetNetworkType = document.getElementsByName("eAssetNetworkType")[i].value;
				var eAssetHostName = document.getElementsByName("eAssetHostName")[i].value;
				var eAssetIp = document.getElementsByName("eAssetIp")[i].value;
				var eAssetOs = document.getElementsByName("eAssetOs")[i].value;
				
				var eAssetPurpose = document.getElementsByName("eAssetPurpose")[i].value;
				var eAssetType = document.getElementsByName("eAssetType")[i].value;
				var eAssetDeviceType = document.getElementsByName("eAssetDeviceType")[i].value;
				
				document.getElementsByName("eAssetTypeName")[i].value = ConverttoHtml(eAssetTypeName);
				document.getElementsByName("eAssetName")[i].value = ConverttoHtml(eAssetName);
				document.getElementsByName("eAssetMaker")[i].value = ConverttoHtml(eAssetMaker);
				document.getElementsByName("eAssetSNumber")[i].value = ConverttoHtml(eAssetSNumber);
				
				document.getElementsByName("eAssetModel")[i].value = ConverttoHtml(eAssetModel);
				document.getElementsByName("eAssetNetworkType")[i].value = ConverttoHtml(eAssetNetworkType);
				document.getElementsByName("eAssetHostName")[i].value = ConverttoHtml(eAssetHostName);
				document.getElementsByName("eAssetIp")[i].value = ConverttoHtml(eAssetIp);
				document.getElementsByName("eAssetOs")[i].value = ConverttoHtml(eAssetOs);
				
				document.getElementsByName("eAssetPurpose")[i].value = ConverttoHtml(eAssetPurpose);
				document.getElementsByName("eAssetType")[i].value = ConverttoHtml(eAssetType);
				document.getElementsByName("eAssetDeviceType")[i].value = ConverttoHtml(eAssetDeviceType);
			}
		    
 
		
		if(confirm("등록하시겠습니까?")){
	  
			document.writeForm.action = "/mes/equipment/kw_equipment_in_i.do";
			document.writeForm.submit();
		}
		
	
	}
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/equipment/kw_equipment_in_lf.do";
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
 
	
	
	var row_Index = 0;
	function addRow(){
		
		var eAssetKeyArr = document.getElementsByName("eAssetType").length;
		if(eAssetKeyArr == 0){
			var tbody = document.getElementById("lineRow");
		    tbody.innerHTML = "";  
		}
		
		
		var innerStr = "";
		 
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick=\"delRow(this);\">X</a>";
		innerStr += "			<input type='hidden' id='eAssetTypeName"+row_Index+"' name='eAssetTypeName' />";
		innerStr += "		</td>";
		// 자산유형
		innerStr += "		<td>";
		innerStr += "		<select id='eAssetType"+row_Index+"' name='eAssetType'  onchange='updateAssetTypeName("+row_Index+")' style='width: 120px;' >";
		innerStr += "		<option value=''>선택</option>";
		<c:forEach var='list' items='${gubun36List}'>
		innerStr += "		<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'  data-value3='${list.sGubunEtc}'>${list.sGubunName}</option>";						
		</c:forEach>
		innerStr += "	</select>";
		
		innerStr += "		</td>";
		// 자산명
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetName' id='eAssetName"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		<input type='hidden' name='eAssetPurpose' id='eAssetPurpose"+row_Index+"' style='width:95%; text-align:left;' maxLength='100' value='' />";
		innerStr += "		<input type='hidden' name='eAssetType' id='eAssetType"+row_Index+"' style='width:95%; text-align:left;' maxLength='100' value='' />";
		innerStr += "		<input type='hidden' name='eAssetDeviceType' id='eAssetDeviceType"+row_Index+"' style='width:95%; text-align:left;' maxLength='100' value='' />";
		innerStr += "		</td>";
		// 제조사
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetMaker' id='eAssetMaker"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";
		// 제조번호
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetSNumber' id='eAssetSNumber"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";		
		// 모델명
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetModel' id='eAssetModel"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";
		// 망구분
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetNetworkType' id='eAssetNetworkType"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";	
		// host
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetHostName' id='eAssetHostName"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";	
		// ip주소
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetIp' id='eAssetIp"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";	
		// os
		innerStr += "		<td>";
		innerStr += "		<input type='text' name='eAssetOs' id='eAssetOs"+row_Index+"' style='width:95%; text-align:left;' maxLength='30' value='' />";
		innerStr += "		</td>";	
		innerStr += "	</tr>";
		
		$(innerStr).appendTo("#lineRow");	
		
		row_Index++;
		
	}
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
		
		  // eAssetKey의 개수를 다시 계산
	    var eAssetKeyArr = document.getElementsByName("eAssetType").length;
	    
	    // eAssetKey의 개수가 0이면 메시지를 추가
	    if (eAssetKeyArr == 0) {
	        var tbody = document.getElementById("lineRow");
	        var messageRow = document.createElement('tr');
	        var messageCell = document.createElement('td');
	        
	        messageCell.colSpan = 10;
	        messageCell.textContent = "행 추가를  선택하여 임시 장비 정보를 입력하세요.";
	        
	        messageRow.appendChild(messageCell);
	        tbody.appendChild(messageRow);
	    }
	}

	
	 function updateAssetTypeName(index) {
         var selectElement = document.getElementById('eAssetType'+index);
         var selectedOption = selectElement.options[selectElement.selectedIndex];
         var value2 = selectedOption.getAttribute('data-value2');
         document.getElementById('eAssetTypeName'+index).value = value2;
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

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="searchWord" id="searchWord" value="${mesEquipmentVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesEquipmentVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesEquipmentVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesEquipmentVO.searchType}" />
	<input type="hidden" name="oSignPass" id="oSignPass" value="${mesEquipmentVO.oSignPass}" />
	<div class="content_top">	
		<div class="content_tit">
			<h2>반입 정보 등록</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${staffVO.kStaffName}
					</td>
					<th >등록일</th>
					<td >
						<input type="hidden" id="eEntryWdate" name="eEntryWdate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
						<span id="eEntryWdateDisplay"></span>
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
					<th><span style="color: red">* </span>반입일</th>
					<td colspan="3">
						<input type="text" name="eEntryImportDate" id="eEntryImportDate" style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>반입확인자</th>
					<td>
						<input type="text" id="eEntryImporter" name="eEntryImporter" style="width:75%;" maxLength="50" />
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eEntryImporter')">담당자 선택</a>
					</td>
					<th>반입확인자 소속</th>
					<td>
						<input type="text" id="eEntryImporterOrg" name="eEntryImporterOrg" style="width:95%;" maxLength="50"/>
						<span id="eEntryImporterOrgTxt" style="display: none;"></span>
					</td>
				</tr>
				<tr>
					<th>반입사유</th>
					<td colspan="1">
						<input type="text" name="eEntryNote" id="eEntryNote" style="width:95%; text-align:left;" maxLength="100" value=""/>
					</td>
					<th>반입처</th>
					<td colspan="1">
						<input type="text" name="eEntryLocation" id="eEntryLocation" style="width:95%; text-align:left;" maxLength="100" value=""/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="content_top nofirst with_btn notit" id="viewDiv1">
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addRow()">행추가</button>
		</div>
	</div>
	
	<div class="normal_table">
		<table>
				<thead>
					<tr>
						<th style="width: 8%;">구분</th>
						<th style="width: 10%;"><span style="color: red">* </span>자산유형</th>
						<th style="width: 12%;"><span style="color: red">* </span>자산명</th>
						<th style="width: 10%;">제조사</th>
						<th style="width: 10%;">제조번호</th>
						<th style="width: 10%;">모델명</th>
						<th style="width: 10%;">망구분</th>
						<th style="width: 10%;">HOSTNAME</th>
						<th style="width: 10%;">IP</th>
						<th style="width: 10%;">OS</th>
					</tr>
				</thead>
			<tbody id="lineRow">
				<tr>
					<td colspan="10">행 추가를  선택하여 임시 장비 정보를 입력하세요.</td>
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
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
	</div>
</form>
