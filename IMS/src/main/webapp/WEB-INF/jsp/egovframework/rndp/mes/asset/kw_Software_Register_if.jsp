<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		addRow();
		datepickerIdSet("aAssetDate");
		const today = nowDate();
		$('#eCreationDateDisplay').text(today);
		$('#aAssetDate').val(today);
		
		$("#filename").change(function(){
	         readURL(this);
	      });
	 $('#oPass').prop('checked', true);
	 $("#oSignPass").val("Y");
	
		
	});
	
	// 파일추가
	var fileRowIndex = "";
	function addFile(rowIndex){  
		fileRowIndex =rowIndex;
		var url = "/mes/asset/popup/sw_file_add.do";
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
	function nowDate(){
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	    var nowDate = year + "-" + month + "-" + day;
		
	    return nowDate;
	}	
	
	function insert_go(){
		
		var idx = document.getElementsByName("eManufacturer").length;
		if(idx<=0){
			alert("\"행추가\"하여 등록할 정보를 입력하세요.");
			return false;
		}else{
			for(var i=0; i < idx ; i++){
				var eProductName = document.getElementsByName("eProductName")[i].value;
				if (eProductName.trim() == '') {
					alert((i+1)+"번째 라이선스명을 입력하세요.");
					document.getElementsByName("eProductName")[i].focus;
					return false;
				}
				if (document.getElementsByName("eLicenseQuantity")[i].value == '' || document.getElementsByName("eLicenseQuantity")[i].value == 0) {
					alert((i+1)+"번째 수량을 입력하세요.");
					document.getElementsByName("eLicenseQuantity")[i].focus;
					return false;
				}
			}
			
			for(var i=0; i < idx ; i++){
				var eManufacturer = document.getElementsByName("eManufacturer")[i].value;
				var eProductName = document.getElementsByName("eProductName")[i].value;
				var eVersion = document.getElementsByName("eVersion")[i].value;
//				var eRemarks = document.getElementsByName("eRemarks")[i].value;
				
				
				document.getElementsByName("eManufacturer")[i].value = ConverttoHtml(eManufacturer);
				document.getElementsByName("eProductName")[i].value = ConverttoHtml(eProductName);
				document.getElementsByName("eVersion")[i].value = ConverttoHtml(eVersion);
//				document.getElementsByName("eRemarks")[i].value = ConverttoHtml(eRemarks);
				
			}
		}
		
		if($("#oSignPass").val() != 'Y'){
			if(document.getElementsByName("sSignStaffKey").length == 0){
				alert("승인권자를 선택해주세요");
				return false;
				}
			}
		
		if(confirm("등록하시겠습니까?")){
			$('#mloader').show();
			document.writeForm.action = "/mes/asset/kw_Software_Register_i.do";
			document.writeForm.submit();
		}
	
	}
	//파일 삭제 함수
	function delFileRow(link, fileId) {
	    $('#fileItem_' + fileId).remove(); // 해당 파일 항목의 div 요소를 삭제함
	}
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/asset/kw_Software_Register_lf.do";
		document.writeForm.submit(); 
	}
	
	//파일 선택시 이미지사진 띄우기
	function readURL(input) {
		
		var rValue = true;		 
	    var ext = ["bmp", "jpeg", "jpg", "gif", "png", "tiff", "pat", "tif"];
	    
	    rValue = checkFileExt($("#aAssetImageName"), ext); //확장자 체크
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
	    	$('#aAssetImageName').val("");
	    	$('#filename').val("");
	    	
	    }
	    
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
	
	var rowIndex = 0;
	
	function addRow(){
			
		var innerStr = "";
			
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick='delRow(this);'>X</a>";
		innerStr += "		</td>";
		
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eManufacturer_"+rowIndex+"' name='eManufacturer' value='' maxLength='50'  />";
		innerStr +=	"			<input type='hidden' id='rowIndex_"+rowIndex+"' name='rowIndex' value='"+rowIndex+"'  />";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eProductName_"+rowIndex+"' name='eProductName' value='' maxLength='50'  />";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eVersion_"+rowIndex+"' name='eVersion' value='' maxLength='30'  />";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='ePurchaseDate_"+rowIndex+"' name='ePurchaseDate' value='"+nowDate()+"' class='inp_color' style='width:98%; text-align:center;'  readonly  />";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eStartDate_"+rowIndex+"' name='eStartDate' value='"+nowDate()+"' class='inp_color'  style='width:98%; text-align:center;' readonly  />";
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eEndDate_"+rowIndex+"' name='eEndDate' value='"+nowDate()+"' class='inp_color'  style='width:98%; text-align:center;' readonly onchange=\"calculateDateDifference("+rowIndex+")\"  />";
		innerStr += "		</td>";
		innerStr += "		<td><span id='rowDate_"+rowIndex+"'></span>"; //유효기간
		innerStr += "		</td>";
		innerStr += "		<td>"; 
		innerStr +=	"			<input type='text' id='eLicenseQuantity_"+rowIndex+"' name='eLicenseQuantity' value='' style='width:98%; text-align:left;' maxLength='6' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"  />";
		innerStr += "		</td>";
		
//		innerStr += "		<td>"; 
//		innerStr +=	"			<input type='text' id='eRemarks"+rowIndex+"' name='eRemarks' value='' maxLength='100'  />";
//		innerStr += "		</td>";
		
		// 파일
		innerStr += "		<td><a class='form_btn md' onclick=\"addFile('"+rowIndex+"');\">파일추가</a>"; 
		innerStr += "		<div id='fileList"+rowIndex+"'></div>";	
		innerStr += "		</td>";	
	
			
		$(innerStr).appendTo("#lineRow");
		datepickerIdSet("ePurchaseDate_"+rowIndex);
		datepickerSet("eStartDate_"+rowIndex, "eEndDate_"+rowIndex);
		calculateDateDifference(rowIndex);
		rowIndex++;
	}
	
	function calculateDateDifference(rowItemIndex) {
		    //현재 날짜 가져오기
		    var currentDate = new Date();
		    //지정 날짜 가져오기
		 	var startDateInput = $("#eStartDate_"+rowItemIndex).val();
		    var endDateInputString = $("#eEndDate_"+rowItemIndex).val() ;
		    var endDateInput = endDateInputString == "" ? formatDate(currentDate) : formatDateData(new Date(endDateInputString)) ;
		    $("#eEndDate_"+rowItemIndex).val(formatDate(endDateInput));
		 
		    var formattedStartDate = formatDateData(new Date(startDateInput));
		    var formattedEndDate = formatDateData(new Date(endDateInputString));
		    // 유효한 날짜인지 확인
		    if (!isNaN(formattedEndDate.getTime())) {
		    	 var differenceInDays = Math.floor((formattedEndDate-currentDate) / (1000 * 60 * 60 * 24));
		    	  var expired = differenceInDays < 0;
		    	  var spantext = "";
		    	  if(expired){
		    		  spantext = "만료";
		    	  }else{
		    		  spantext = differenceInDays+"일";
		    	  }
		    	$("#rowDate_"+rowItemIndex).html(spantext);
		    	
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
	
	
	//행 삭제
	function delRow(obj){
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
	function setDateToInputs(type) {
	    // 값 설정을 위한 기본 날짜 값
	    var startDate = "2000-01-01";
	    var endDate = "2099-01-01";
	    if (type === 'S') {
	        message = "추가된 모든 라이선스의 시작일을 " + startDate + "로 설정하시겠습니까?";
	    } else if (type === 'E') {
	        message = "추가된 모든 라이선스의 종료일을 " + endDate + "로 설정하시겠습니까?";
	    }  
	    
	    if (confirm(message)) {
	        var inputs;
	        var rowIndexValue  =   document.getElementsByName("rowIndex");
	        if (type === 'S') {
	            inputs = document.getElementsByName("eStartDate");
	            for (var i = 0; i < inputs.length; i++) {
	                inputs[i].value = startDate;
	            }
	            alert("모든 eStartDate 필드에 날짜가 " + startDate + "로 설정되었습니다.");
	        } else if (type === 'E') {
	            inputs = document.getElementsByName("eEndDate");
	            for (var i = 0; i < inputs.length; i++) {
	                inputs[i].value = endDate;
	                calculateDateDifference(rowIndexValue[i].value);
	            }
	            alert("모든 eEndDate 필드에 날짜가 " + endDate + "로 설정되었습니다.");
	        }
	    }  
	}
	
	function delRowTwo(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	
	
</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	<div class="content_top">	
		<div class="content_tit">
			<h2>소프트웨어 라이선스 정보 등록</h2>
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
	
	<div class="content_top nofirst with_btn notit" id="viewDiv1">
		<div class="content_tit">
			<h2></h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addRow();">행추가</button>
		</div>
	</div>
	
	<div class="normal_table">          	
		<table>
			<thead>  
	          	<tr>
	             	<th style="width: 5%;">구분</th>
	             	<th style="width: 10%;">제조사</th>
	             	<th style="width: 15%;"><span style="color: red">* </span>라이선스명</th>
	             	<th style="width: 8%;">버전</th>
	             	<th style="width: 8%;">구매일</th>
	             	<th style="width: 8%;">시작일 <a class="form_btn md" onclick="setDateToInputs('S')">일괄반영</a> </th>
	             	<th style="width: 8%;">종료일 <a class="form_btn md" onclick="setDateToInputs('E')">일괄반영</a></th>
	             	<th style="width: 8%;">유효기간</th>
	             	<th style="width: 8%;"><span style="color: red">* </span>수량</th>
	             	<th style="width: *;">첨부 파일</th>
	            </tr>
	         </thead>
	         <tbody id="lineRow">
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
		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' }">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
	</div>

</form>
