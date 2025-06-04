<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>
<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />

<script type="text/javascript">

	$(document).ready(function(){
		settingSign(); // 싸인 설정
		  var firstEntryExitDate = $("input[name='eEntryExitDate']").first().val();
         var firstEntryRequestReason = $("input[name='eEntryRequestReason']").first().val();
         var firstEntryStaff = $("input[name='eEntryStaff']").first().val();
         var eEntryStaffOrg = $("input[name='eEntryStaffOrg']").first().val();
         var eExportLocation = $("input[name='eExportLocation']").first().val();
         $("#eEntryExitDate").text(firstEntryExitDate);
         $("#eEntryRequestReason").text(firstEntryRequestReason);
         $("#eEntryStaff").text(firstEntryStaff);
         $("#eEntryStaffOrg").text(eEntryStaffOrg);
         $("#eEntryStaffOrgTxt").text(eEntryStaffOrg);
         $("#eExportLocation").text(eExportLocation);
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
		$('#mloader').show();
		document.frm.action = "/mes/asset/kw_eCondition_out_uf.do";
		document.frm.submit();
	}
	
	function delete_go(){
		if(confirm("삭제하시겠습니까?")){
			document.frm.action = "/mes/asset/kw_eCondition_out_d.do";
			document.frm.submit();
		}
	}

	
	function cancle(){
		$('#mloader').show();
		document.frm.action = "/mes/asset/kw_eCondition_lf.do";
		document.frm.submit(); 
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
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
		
		  // eAssetKey의 개수를 다시 계산
	    var eAssetKeyArr = document.getElementsByName("eAssetKey").length;
	    
	    // eAssetKey의 개수가 0이면 메시지를 추가
	    if (eAssetKeyArr == 0) {
	        var tbody = document.getElementById("lineRow");
	        var messageRow = document.createElement('tr');
	        var messageCell = document.createElement('td');
	        
	        messageCell.colSpan = 10;
	        messageCell.textContent = "장비를 선택하세요.";
	        
	        messageRow.appendChild(messageCell);
	        tbody.appendChild(messageRow);
	    }
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
		var eAssetKey = $("#eEntryExitKey").val();
		var kStaffKey = $("#kStaffKey").val();
		if(sSignContent != ""){
			$.ajax({
				type : "post"
			,	url : "/mes/asset/kw_uploadSignConditionReasonAjax.do"
			,	data : {
					"eAssetKey"		: eAssetKey
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "반려"
				,	"sSignContent"		: sSignContent	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
				 alert("반려처리되었습니다.")
					document.frm.action = "/mes/asset/kw_eCondition_out_vf.do";
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
				var eAssetKey = $("#eEntryExitKey").val();
				var kStaffKey = $("#kStaffKey").val();
				
				$.ajax({
					type : "post"
				,	url : "/mes/asset/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignTableKey"		: eAssetKey
					,	"sSignTableName"	: "E_ENTRY"
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
						document.frm.action = "/mes/asset/kw_eCondition_out_vf.do";
						document.frm.submit();
						
					}
				});
			}
		});
		
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
		innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		obj.parentNode.innerHTML = innerStr;
	}
	
	function startApproval(eStatus){
		$("#mloader").show();
		$("#eStatus").val(eStatus)
		document.frm.action = "/mes/asset/kw_eCondition_out_vfr.do";
		document.frm.submit();
	}

	
</script>

<form name="frm" id="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="searchTypeSet1" id="searchTypeSet1" value="${mesAssetVO.searchTypeSet1}" />
	<input type="hidden" name="searchTypeSet2" id="searchTypeSet2" value="${mesAssetVO.searchTypeSet2}" />
	<input type="hidden" name="topStartDate" id="topStartDate" value="${mesAssetVO.topStartDate}" />
	<input type="hidden" name="topEndDate" id="topEndDate" value="${mesAssetVO.topEndDate}" />
	<input type="hidden" name="oSignPass" id="oSignPass" value="${mesAssetVO.oSignPass}" />
	<input type="hidden" id="eEntryExitKey" name="eEntryExitKey" value="${mesAssetVO.eEntryExitKey}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	<input type="hidden" id="eStatus" name="eStatus" value="" />
	<div class="content_top">	
		<div class="content_tit">
			<h2>보유자산 반출 정보 상세</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
					<tr>
	            		<th>작성자</th>
            			<td>${assetInfo.kStaffName }</td>
	            		
            			<th>작성일</th>
            			<td>${assetInfo.eAssetWdate }</td>
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
					<th>반출일</th>
					<td colspan="3"><span id="eEntryExitDate"></span>
					</td>
				</tr>
				<tr>
					<th>반출자</th>
					<td><span id="eEntryStaff"></span>
<!-- 						<input type="text" name="eEntryStaff" id="eEntryStaff" style="width:95%; text-align:left;" maxLength="50" value=""/> -->
					</td>
					<th>반출자소속</th>
					<td><span id="eEntryStaffOrg"></span>
					</td>
				</tr>
				<tr>
					<th>반출사유</th>
					<td><span id="eEntryRequestReason"></span>
<!-- 						<input type="text" name="eEntryRequestReason" id="eEntryRequestReason" style="width:95%; text-align:left;" maxLength="150" value=""/> -->
					</td>
					<th>반출처</th>
					<td><span id="eExportLocation"></span>
<!-- 						<input type="text" name="eEntryRequestReason" id="eEntryRequestReason" style="width:95%; text-align:left;" maxLength="150" value=""/> -->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!--
	<div class="content_top nofirst with_btn notit">
		<div class="btns">
				 <button type="button" onclick="sel_asset()" class="form_btn md">장비 선택</button> 
		</div>
	</div>-->
	<div class="normal_table">
		<table>
				<thead>
				<tr>
<!-- 					<th style="width: 4%;">구분</th> -->
					<th style="width: 8%;">자산유형</th>
					<th style="width: 12%;">자산번호</th>
					<th style="width: 12%;">자산명</th>
					<th style="width: 10%;">모델명</th>
					<th style="width: 10%;">망구분</th>
					<th style="width: 10%;">HOSTNAME</th>
					<th style="width: 10%;">IP</th>
					<th style="width: 10%;">OS</th>
					<th style="width: 8%;">반입일</th>
					<th style="width: 8%;">반입확인자</th>
				</tr>
			</thead>
			<tbody id="lineRow">
			 	<c:forEach var="list" items="${assetList}" varStatus="i">
			 		 <tr>
	<!-- 						 	<td>- -->
	<!-- 						 	</td> -->
					 	<td>${list.eAssetType }
					 		<input type='hidden' id='eAssetKey_${i.index}' name='eAssetKey' value='${list.eAssetKey}'/>
					 		<input type='hidden' id='eEntryExitDate_${i.index}' name='eEntryExitDate' value='${list.eEntryExitDate}'/>
					 		<input type='hidden' id='eEntryStaff_${i.index}' name='eEntryStaff' value='${list.eEntryStaff}'/>
					 		<input type='hidden' id='eEntryStaffOrg_${i.index}' name='eEntryStaffOrg' value='${list.eEntryStaffOrg}'/>
					 		<input type='hidden' id='eEntryRequestReason_${i.index}' name='eEntryRequestReason' value='${list.eEntryRequestReason}'/>
					 		<input type='hidden' id='eExportLocation_${i.index}' name='eExportLocation' value='${list.eExportLocation}'/>
					 	</td>
					 
					 	<td>${list.eAssetNumber}
					 	</td>		
					 
					 	<td>${list.eAssetName}
					 	</td>
					 
					 	<td>${list.eAssetModel}
					 	</td>
					 
					 	<td>${list.eNetworkType}
					 	</td>	
					 
					 	<td>${list.eHostName}
					 	</td>	
					 
					 	<td>${list.eIp}
					 	</td>	
					 
					 	<td>${list.eOs}
					 	</td>	
					 	<td>${list.eEntryImportDate}
					 	</td>	
					 	<td>${list.eEntryImporter}
					 	</td>	
					 </tr>
				 </c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<c:if test="${not empty signList}">
	<div class="content_top">	
		<div class="content_tit">
			<h2>결재현황</h2> 
		</div>
	</div>
	<div class="normal_table">
        <table>
        	<thead>
	          	<tr>
					<th style="width:5%; border-left: 1px solid #bfdaf7;">결재순서</th>
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
						<td>${signList.sSignStaffGubun}
							<c:if test="${assetInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
								<c:if test="${signList.sSignDecison eq '결재대기' }">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
								</c:if>
							</c:if>
							<c:if test="${signList.sSignDecison ne '결재대기' }">${signList.sSignDecison}</c:if>
						</td>
						<td <c:if test="${assetInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
							<c:if test="${assetInfo.eStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
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
		<c:if test="${(assetInfo.kStaffKey eq staffVO.kStaffKey && (assetInfo.eStatus eq '등록' || assetInfo.eStatus eq '반려' || assetInfo.eStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (assetInfo.eStatus eq '등록' || assetInfo.eStatus eq '반려' || assetInfo.eStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'  ||  staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'  ||  staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
			</c:if>
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
		<c:if test="${assetInfo.eStatus eq '등록'}">
				<c:if test="${assetInfo.kStaffKey eq staffVO.kStaffKey }">
					<button type="button" class="form_btn bg" onclick="startApproval('Y');">승인요청</button>
				</c:if>
			</c:if>
			<c:if test="${assetInfo.eStatus eq '승인요청'}">
				<c:if test="${assetInfo.kStaffKey eq staffVO.kStaffKey && assetInfo.sSignProgress eq '0'}">
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
