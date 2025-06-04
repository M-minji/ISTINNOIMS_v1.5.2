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
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		settingSign();
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
		
		if(confirm("등록하시겠습니까?")){
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
 
		function addFile(rowIndex){  
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
	
	  function addFile(){
			var url = "/mes/inspection/popup/kw_File_add.do";
			window.open(url ,"ePDFAdd" ,"width=600,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");

		}
	  function eDownload(fileId,eFileName){
			 if(confirm(eFileName+"을 다운로드 하시겠습니까?")){
					window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+fileId+"&fileSn=0'/>");
				}
		}
	  
		
		function eModifunction(){
			$('#mloader').show();
			document.writeForm.action = "/mes/inspection/kw_inspection_uf.do";
			document.writeForm.submit(); 
		}
		
		function eDelete(){
			 if(confirm("해당 등록정보를 삭제하시겠습니까?")){
				document.writeForm.action = "/mes/inspection/kw_inspection_d.do";
				document.writeForm.submit(); 
			 }
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
			innerStr += "<a class='form_btn bg' onclick='setSign(this, event);'>사인</a>";
			innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
			obj.parentNode.innerHTML = innerStr;
		}



		//화면 캡처 후 인쇄로 넘어가기
		function capture() { 
			html2canvas($("#print_div"), {
				onrendered: function(canvas) { 
					var img = canvas.toDataURL("image/png");
					console.log(img); 
//						window.open(img); // 이미지를 윈도우 팝업으로..
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
		
		// 상세보기 엑셀 다운
		function eExcelDownload() {
		    document.writeForm.action = "/mes/inspection/kw_inspection_download.do";
		    document.writeForm.submit();
		    document.writeForm.action = "/mes/inspection/kw_inspection_vf.do";
		}
			
		
		

		function startApproval(sSignStatus){
			$("#mloader").show();
			$("#sSignStatus").val(sSignStatus)
			document.writeForm.action = "/mes/inspection/kw_inspection_vfr.do";
			document.writeForm.submit();
		}

		function changeContent(value){
			var innerStr = "";
			
			if(value == "승인"){
				innerStr += "<a class='form_btn bg' onclick='setSign(this, event);'>사인</a>";
				innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
			} else if(value == "반려"){
				innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='50'/>";
				innerStr += "<a class='form_btn bg' onclick='sSignContentAdd();'>반려 사유 저장</a>";
			}
			document.getElementById("sSignContentSet").innerHTML = innerStr;
		}

		function sSignContentAdd(){
			var sSignContent = $("#sSignContent").val();
			var sSignTableKey = $("#eInspectionKey").val();
			var kStaffKey = $("#kStaffKey").val();
			if(sSignContent != ""){
				$.ajax({
					type : "post"
				,	url : "/mes/issue/kw_uploadSignInspectionReasonAjax.do"
				,	data : {
						"sSignTableKey"		: sSignTableKey
					,	"sSignTableName"	: "INSPECTION"
					,	"sSignStaffKey"		: kStaffKey
					,	"sSignDecison"		: "반려"
					,	"sSignContent"		: sSignContent	
					}
				,	dataType : "json"
				,	async : false
				,	cache : false
				,	success : function(msg){
					 alert("반려처리되었습니다.")
						document.writeForm.action = "/mes/inspection/kw_inspection_vf.do";
						document.writeForm.submit();
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
					var sSignTableKey = $("#eInspectionKey").val();
					var kStaffKey = $("#kStaffKey").val();
					
					$.ajax({
						type : "post"
					,	url : "/mes/asset/kw_uploadSignImgAjax.do"
					,	data : {
							"sSignTableKey"		: sSignTableKey
						,	"sSignTableName"	: "INSPECTION"
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
							document.writeForm.action = "/mes/inspection/kw_inspection_vf.do";
							document.writeForm.submit();
							
						}
					});
				}
			});
			
		}

</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesInspectionVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesInspectionVO.recordCountPerPage}" />
	<input type="hidden" name="eInspectionKey" id="eInspectionKey" value="${selInfo.eInspectionKey}" />
	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="${selInfo.sSignStatus}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	<div class="content_top">	
		<div class="content_tit">
			<h2>점검관리 상세</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${selInfo.eRegistrant}
						<input type="hidden" id="eRegistrant" name="eRegistrant"  value="${selInfo.kStaffName}" maxlength="50"/>
						<input type="hidden" id="eStaffKey" name="eStaffKey"  value="${selInfo.kStaffKey}" maxlength="50"/>
					</td>
					<th >등록일</th>
					<td >${selInfo.eRegistrationDate}
						<input type="hidden" id="eRegistrationDate" name="eRegistrationDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
					</td>
  				</tr>		
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
				<tr>
					<th>점검구분</th>
					<td>${selInfo.eInspectionTypeName}
						<input type="hidden" id="eInspectionTypeName" name="eInspectionTypeName" />
							<select id='eInspectionType' name='eInspectionType' style="width: 100px;visibility: hidden;"  onchange="selectName(this,'eInspectionTypeName')" >
								<option value=''>선택</option>
								<c:forEach var='list' items='${gubun38List}'>
									<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
								</c:forEach>
							</select>
					</td>
					<th>점검주기</th>
					<td>${selInfo.eInspectionCycleName}
						<input type="hidden" id="eInspectionCycleName" name="eInspectionCycleName" />
							<select id='eInspectionCycle' name='eInspectionCycle' style="width: 100px;visibility: hidden;"  onchange="selectName(this,'eInspectionCycleName')" >
								<option value=''>선택</option>
								<c:forEach var='list' items='${gubun39List}'>
									<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
								</c:forEach>
							</select>
						<input type="hidden" name="eRequester" id="eRequester" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eOrganization" id="eOrganization" style="width:95%; text-align:left;" maxLength="150" value=""/>
						<input type="hidden" name="eDepartment" id="eDepartment" style="width:95%; text-align:left;" maxLength="150" value=""/>
					</td>
				</tr>
				<tr>
					
					<th>점검일자</th>
					<td colspan="3">${selInfo.eInspectionDate}
						<input type="hidden" name="eInspectionDate" id="eInspectionDate" style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly"/>
					</td>
				</tr> 
				<tr>
					<th>점검자</th>
					<td colspan="1">${selInfo.eInspector}
						<input type="hidden" name="eInspector" id="Inspector"  style="width:95%; text-align:left;" maxLength="50"  value=""  />
						<input type="hidden" name="eInspectionResult" id="eInspectionResult" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eOther" id="eOther" style="width:95%; text-align:left;" maxLength="50" value=""/>
					</td>
					<th>점검자 소속</th>
					<td>${selInfo.eInspectorOrg}
						<input type="hidden" name="eInspectorOrg" id="eInspectorOrg"  style="width:95%; text-align:left;" maxLength="50"  value="${selInfo.eInspectorOrg}"  />
					
					</td>
				</tr> 
				<tr>
					<th>특이사항</th>
					<td id="td_editor" align="center" scope="row" colspan="3">${selInfo.eRemark}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
		<table>
			<thead>
<!-- 				<tr> -->
<!-- 					<th colspan="2"> -->
<!-- 					 <a class="mes_btn" onclick="addFile()" >파일 선택</a> -->
<!-- 					</th> -->
<!-- 				</tr> -->
				<tr>
<!-- 					<th style="width: 25%;">구분</th> -->
					<th style="width: 100%;">점검  서류</th>
				</tr>
			</thead>
			<tbody id="fileRow">
			 		<c:if test="${not empty eFileInfoList}">
						 <c:forEach var="list" items="${eFileInfoList}" >
					 		<tr> <td><a onclick="eDownload('${list.eFileID}','${list.eFileName}');">${list.eFileName}</a></td></tr>
						 </c:forEach>
					 </c:if>
			 		<c:if test="${empty eFileInfoList}">
			 			<tr>
			 				<td class="tac">등록된 파일이 없습니다.</td>
			 			</tr>
			 		</c:if>
			</tbody>
		</table>
	</div> 

	<c:if test="${not empty eResultInfoList}">
	<div class="normal_table">
		<table>
<%-- 			<caption style="text-align: left; margin-bottom:10px;"> --%>
<!-- 				   <a class="mes_btn" onclick="sel_asset()" style="float:right">장비 선택</a> -->
<%-- 			</caption> --%>
			<thead>
				<tr>
					<th colspan="15">장비 정보</th>
				</tr>
				<tr>
					<th style="width: 5%;">구분</th>
					<th style="width: 7%;">자산유형</th>
					<th style="width: 7%;">자산명</th>
					<th style="width: 7%;">제조사</th>
					<th style="width: 7%;">모델명</th>
					<th style="width: 7%;">망구분</th>
					<th style="width: 5%;">장비구분</th>
					<th style="width: 5%;">${fieldInfo.eField1}</th>
					<th style="width: 5%;">${fieldInfo.eField2}</th>
					<th style="width: 5%;">${fieldInfo.eField3}</th>
					<th style="width: 5%;">${fieldInfo.eField4}</th>
					<th style="width: 5%;">${fieldInfo.eField5}</th>
					<th style="width: 5%;">점검일자</th>
					<th style="width: 5%;">점검결과</th>
					<th style="width: 5%;">비고</th>
				</tr>
			</thead>
			<tbody id="lineRow">
			<c:forEach var="list" items="${eResultInfoList}" varStatus="i">
			
			 <tr>
			 	<td>${i.index+1}</td>
			 	<td>${list.eAssetType}</td>
			 	<td>${list.eAssetName}</td>
			 	<td>${list.eAssetMaker}</td>
			 	<td>${list.eAssetModel}</td>
			 	<td>${list.eNetworkType}</td>
			 	<td>${list.eDeviceType}</td>
			 	<td>${list.eField1}</td>
			 	<td>${list.eField2}</td>
			 	<td>${list.eField3}</td>
			 	<td>${list.eField4}</td>
			 	<td>${list.eField5}</td>
			 	<td>${list.eItemInspectionDate}</td>
			 	<td>${list.eItemRemark}</td>
			 	<td>${list.eItemOther}</td>
			 </tr>
			
			</c:forEach>
			
			
			
<!-- 				<tr> -->
<!-- 					<td colspan="15">장비를 선택하세요.</td> -->
<!-- 				</tr> -->
			</tbody>
		</table>
	</div>
	</c:if>
	
<!-- 	결재정보 요청등록시. -->
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
							<td>${signList.sSignStaffGubun}:
								<c:if test="${selInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignDecison ne '결재대기' }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${selInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${selInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
						        		<a class="form_btn bg" onclick="setSign(this, event);">사인</a>
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
		<button type="button" onclick="eExcelDownload();" class="topdown">다운로드</button>
		<c:if test="${(selInfo.kStaffKey eq staffVO.kStaffKey && (selInfo.sSignStatus eq '등록' || selInfo.sSignStatus eq '반려' || selInfo.sSignStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (selInfo.sSignStatus eq '등록' || selInfo.sSignStatus eq '반려' || selInfo.sSignStatus eq '제외'))}">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T' || staffVO.kAdminAuth eq 'T'}">
				<button type="button" onclick="eModifunction();" class="form_btn bg">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T' || staffVO.kAdminAuth eq 'T'}">
				<button type="button" onclick="eDelete();" class="form_btn bg">삭제</button>
			</c:if>
		</c:if>
		<button type="button" onclick="cancle();" class="form_btn">목록</button>
		<c:if test="${selInfo.sSignStatus eq '등록'}">
			<c:if test="${selInfo.kStaffKey eq staffVO.kStaffKey }">
				<button type="button" onclick="startApproval('Y');" class="form_btn bg">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${selInfo.sSignStatus eq '승인요청'}">
			<c:if test="${selInfo.kStaffKey eq staffVO.kStaffKey && selInfo.sSignProgress eq '0'}">
				<button type="button" onclick="startApproval('N');" class="form_btn bg">승인취소</button>
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
