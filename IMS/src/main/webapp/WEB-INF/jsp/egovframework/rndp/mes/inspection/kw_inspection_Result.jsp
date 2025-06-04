<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
// 		datepickerIdSet("eRegistrationDate");
// 		datepickerIdSet("eInspectionDate");
// 		$('#eInspectionDate').val(nowDate());
// 		$('#eRegistrationDate').val(nowDate());
		 
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
	  //  form.action = "/mes/asset/kw_asset_box_lf.do";
	    form.action = "/mes/asset/kw_asset_box_inspection.do";
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
	
	
	function sel_field(){	
		// 동적으로 폼 생성
	    const form = document.createElement("form");
	    form.method = "POST";
	    form.action = "/mes/inspection/kw_inspection_field_box.do";
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
		// 자산명
		innerStr += "		<td>" +obj.aAssetName;
		innerStr += "		</td>";
		// 제조사
		innerStr += "		<td>" +obj.aAssetMaker;
		innerStr += "		</td>";
		// 모델명
		innerStr += "		<td>" +obj.aAssetModel;
		innerStr += "		</td>";
		// 망구분
		innerStr += "		<td>"+obj.eNetworkType;
		innerStr += "		</td>";	
		// 장비구분
		innerStr += "		<td>"+obj.eDeviceType;
		innerStr += "		</td>";	
		// 필드1~5
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eField1_"+row_Index+"' name='eField1' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eField2_"+row_Index+"' name='eField2' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eField3_"+row_Index+"' name='eField3' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eField4_"+row_Index+"' name='eField4' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eField5_"+row_Index+"' name='eField5' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		// 점검일자//직접입력
		innerStr += "		<td>"
		innerStr += "			<input type='text' id='eItemInspectionDate_"+row_Index+"' name='eItemInspectionDate' class='inp_color'  style='width:98%; text-align:center;' readonly   />";
		innerStr += "		</td>";	
		innerStr += "		<td>"//특이사항
		innerStr += "			<input type='text' id='eItemRemark_"+row_Index+"' name='eItemRemark' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "		<td>"//비고
		innerStr += "			<input type='text' id='eItemOther_"+row_Index+"' name='eItemOther' style=\"width: 98%;\" maxlength=\"50\"  value=''/>";
		innerStr += "		</td>";	
		innerStr += "	</tr>";
		
		$(innerStr).appendTo("#lineRow");	
		datepickerSet("eInspectionDate", "eItemInspectionDate_"+row_Index);
		
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
	        
	        messageCell.colSpan = 15;
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
	  
		
		function eInspectionResult_go(){
			var eAssetKeyArr = document.getElementsByName("eAssetKey").length;
			if(eAssetKeyArr == 0){
				alert("장비를 추가하여 정보를 입력하세요.");
				return false;
			}
			if(eAssetKeyArr > 0){
					var eField1 = document.getElementsByName("eField1");
					var eField2 = document.getElementsByName("eField2");
					var eField3 = document.getElementsByName("eField3");
					var eField4 = document.getElementsByName("eField4");
					var eField5 = document.getElementsByName("eField5");
					var eItemInspectionDate = document.getElementsByName("eItemInspectionDate");
					var eItemRemark = document.getElementsByName("eItemRemark");
					var eItemOther = document.getElementsByName("eItemOther");
				for(var i=0; i < eAssetKeyArr ; i++){
					eField1[i].value = ConverttoHtml(eField1[i].value);
					eField2[i].value = ConverttoHtml(eField2[i].value);
					eField3[i].value = ConverttoHtml(eField3[i].value);
					eField4[i].value = ConverttoHtml(eField4[i].value);
					eField5[i].value = ConverttoHtml(eField5[i].value);
					eItemInspectionDate[i].value = ConverttoHtml(eItemInspectionDate[i].value);
					eItemRemark[i].value = ConverttoHtml(eItemRemark[i].value);
					eItemOther[i].value = ConverttoHtml(eItemOther[i].value);
					 
				}
			}
			
			
			
			if(confirm("점검결과를 저장 하시겠습니까?")){
				$('#mloader').show();
				document.writeForm.action = "/mes/inspection/kw_inspection_R_i.do";
				document.writeForm.submit(); 
			}
		}
		
		function setFieldReturnPop(data) {
			document.getElementById("eFieldKey").value = data.eFieldKey;
	
		    document.getElementById("field1").innerText = data.eField1;
		    document.getElementById("field2").innerText = data.eField2;
		    document.getElementById("field3").innerText = data.eField3;
		    document.getElementById("field4").innerText = data.eField4;
		    document.getElementById("field5").innerText = data.eField5;
		}

		
		
</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesInspectionVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesInspectionVO.recordCountPerPage}" />
	<input type="hidden" name="eInspectionKey" id="eInspectionKey" value="${mesInspectionVO.eInspectionKey}" />
	<input type="hidden" id="eFieldKey" name="eFieldKey"  value="${mesInspectionVO.eFieldKey}"/>
	<div class="content_top">	
		<div class="content_tit">
			<h2>점검 정보 결과 등록</h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
				<tr>
					<th >작성자</th>
					<td >${selInfo.eRegistrant}
						<input type="hidden" id="eRegistrant" name="eRegistrant"  value="${staffVO.kStaffName}" maxlength="50"/>
						<input type="hidden" id="eStaffKey" name="eStaffKey"  value="${staffVO.kStaffKey}" maxlength="50"/>
					</td>
					<th >작성일</th>
					<td >${selInfo.eRegistrationDate}
						<input type="hidden" id="eRegistrationDate" name="eRegistrationDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
					</td>
  				</tr>
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
					</td>
				</tr>
				<tr>
					<th>점검일자*</th>
					<td colspan="3">${selInfo.eInspectionDate}
						<input type="hidden" name="eInspectionDate" id="eInspectionDate" style="width:120px; text-align:center;" class="inp_color"   value="${selInfo.eInspectionDate}"  readonly="readonly"/>
					</td>
				</tr> 
				<tr>
					<th>점검자</th>
					<td>${selInfo.eInspector}
						<input type="hidden" name="eInspector" id="Inspector"  style="width:95%; text-align:left;" maxLength="50"  value=""  />
						<input type="hidden" name="eInspectionResult" id="eInspectionResult" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eRequester" id="eRequester" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eOrganization" id="eOrganization" style="width:95%; text-align:left;" maxLength="150" value=""/>
						<input type="hidden" name="eDepartment" id="eDepartment" style="width:95%; text-align:left;" maxLength="150" value=""/>
					</td>
					<th>점검자 소속</th>
					<td>${selInfo.eInspectorOrg}
						<input type="hidden" name="eInspectorOrg" id="eInspectorOrg"  style="width:95%; text-align:left;" maxLength="50"  value="${selInfo.eInspectorOrg}"  />
					</td>
				</tr> 
				<tr>
					<th>특이사항</th>
					<td colspan="3">${selInfo.eRemark}
						<input type="hidden" name="eRemark" id="eRemark" style="width:95%; text-align:left;" maxLength="50" value=""/>
						<input type="hidden" name="eOther" id="eOther" style="width:95%; text-align:left;" maxLength="50" value=""/>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th>기타</th> -->
<%-- 					<td colspan="3">${selInfo.eOther} --%>
<!-- 					</td> -->
<!-- 				</tr> -->
			</tbody>
		</table>
	</div>
	<div class="normal_table">
		<table>
			<thead>
<!-- 				<tr> -->
<!-- 					<th colspan="2"> -->
<!-- 					 <a class="mes_btn" onclick="addFile()" >파일 선택</a> -->
<!-- 					</th> -->
<!-- 				</tr> -->
				<tr>
<!-- 					<th style="width: 25%;">구분</th> -->
					<th style="width: 100%;">점검  파일명</th>
				</tr>
			</thead>
			<tbody id="fileRow">
				 <c:forEach var="list" items="${eFileInfoList}" >
			 		<tr> <td><a onclick="eDownload('${list.eFileID}','${list.eFileName}');">${list.eFileName}</a></td></tr>
				 </c:forEach>
			 	<c:if test="${empty eFileInfoList}">
		 		<tr> <td>등록된 파일이 없습니다.</td></tr>
		 		</c:if>
			</tbody>
		</table>
	</div> 
	
	<div class="content_top nofirst with_btn notit" id="viewDiv1">
		<div class="btns">
			 <button type="button" onclick="sel_asset()" class="form_btn md">장비 선택</button>
			 <button type="button" onclick="sel_field()" class="form_btn md">필드 선택</button>
		</div>
	</div>
	<div class="normal_table">
		<table>
			<colgroup>
				<col style="width: 50px;" />
				<col style="width: 80px;" />
				<col />
				<col />
				<col />
				<col style="width: 80px;" />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th>구분</th>
					<th>자산유형</th>
					<th>자산명</th>
					<th>제조사</th>
					<th>모델명</th>
					<th>망구분</th>
					<th>장비구분</th>
					<th id="field1">필드1</th>
					<th id="field2">필드2</th>
					<th id="field3">필드3</th>
					<th id="field4">필드4</th>
					<th id="field5">필드5</th>
					<th>점검일자</th>
					<th>점검결과</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody id="lineRow">
				<tr>
					<td colspan="15">장비를 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="eInspectionResult_go();">점검결과 저장</button>
		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T' }">
			<!--  <button type="button" class="form_btn bg" onclick="eModification();">수정</button>  -->
		</c:if>
		<button type="button" class="form_btn" onclick="cancle();">목록</button>
	</div>
</form>
