<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		datepickerIdSet("eEntryExporterDate");
		$('#eEntryExporterDate').val(nowDate());
		
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
		  var eEquipmentItemKey  = document.getElementsByName("eEquipmentItemKey").length;
		    // eAssetKey의 개수가 0이면 메시지를 추가
		    if (eEquipmentItemKey == 0) {
		    	alert("장비 정보가 없습니다.");
		    	return;
		    } 
		  
		
		if(confirm("등록하시겠습니까?")){
	  
			document.writeForm.action = "/mes/equipment/kw_equipment_out_i.do";
			document.writeForm.submit();
		}
		
	
	}
	
	function cancle(){
		$('#mloader').show();
		document.writeForm.action = "/mes/equipment/kw_equipment_out_lf.do";
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
	function setAssetPop(obj){
		
		var eEquipmentItemKeyArr = document.getElementsByName("eEquipmentItemKey").length;
		if(eEquipmentItemKeyArr == 0){
			var tbody = document.getElementById("lineRow");
		    tbody.innerHTML = "";  
		}else{
			row_Index = eEquipmentItemKeyArr;
			
			for(var i=0; i<eEquipmentItemKeyArr; i++){
				var eEquipmentItemKeyElement = document.getElementsByName("eEquipmentItemKey")[i];
				  if (eEquipmentItemKeyElement.value === obj.eEquipmentItemKey) {
		                return;
		            }
			}
			
			
			
			
			
		}
		
		
		var innerStr = "";
		 
		// 행삭제
		innerStr += "	<tr>";
		innerStr += "		<td>";
		innerStr += "			<a class='del' onclick=\"delRow(this);\">X</a>";
		innerStr += "			<input type='hidden' id='eAssetTypeName"+row_Index+"' name='eAssetTypeName' value='"+obj.eAssetTypeName+"'/>";
		innerStr += "			<input type='hidden' id='eEquipmentItemKey"+row_Index+"' name='eEquipmentItemKey' value='"+obj.eEquipmentItemKey+"'/>";
		innerStr += "		</td>";
		// 자산유형
		innerStr += "		<td>"+obj.eAssetTypeName;
		
		innerStr += "		</td>";
		// 자산명
		innerStr += "		<td>"+obj.eAssetName;
		innerStr += "		</td>";
		// 제조사
		innerStr += "		<td>"+obj.eAssetMaker;
		innerStr += "		</td>";
		// 제조번호
		innerStr += "		<td>"+obj.eAssetSNumber;
		innerStr += "		</td>";		
		// 모델명
		innerStr += "		<td>"+obj.eAssetModel;
		innerStr += "		</td>";
		// 망구분
		innerStr += "		<td>"+obj.eAssetNetworkType;
		innerStr += "		</td>";	
		// host
		innerStr += "		<td>"+obj.eAssetHostName;
		innerStr += "		</td>";	
		// ip주소
		innerStr += "		<td>"+obj.eAssetIp;
		innerStr += "		</td>";	
		// os
		innerStr += "		<td>"+obj.eAssetOs;
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
	    var eEquipmentItemKeyArr = document.getElementsByName("eEquipmentItemKey").length;
	    
	    // eAssetKey의 개수가 0이면 메시지를 추가
	    if (eEquipmentItemKeyArr == 0) {
	        var tbody = document.getElementById("lineRow");
	        var messageRow = document.createElement('tr');
	        var messageCell = document.createElement('td');
	        
	        messageCell.colSpan = 10;
	        messageCell.textContent = "장비  선택 버튼을 클릭하여  임시 장비 정보를 선택하세요.";
	        
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
				,	url			: "/mes/maintance/kw_getAssetInfoList.do"
				,	data		: {
					eEquipmentItemKey : obj.eEquipmentItemKey
					}
				,	success		: function(msg){
					var eAssetInfoList  = msg.result.dataList;
						for(var i = 0; i < eAssetInfoList.length; i++){
							setAssetPop(eAssetInfoList[i]);
						}
					}
				
				});
		}
	 
</script>

<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="searchWord" id="searchWord" value="${mesEquipmentVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesEquipmentVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesEquipmentVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesEquipmentVO.searchType}" />
<%-- 	<input type="hidden" name="oSignPass" id="oSignPass" value="${mesEquipmentVO.oSignPass}" /> --%>
	<div class="content">	
		<div class="content_tit">
			<h2>반출 정보 등록 </h2>
		</div>
	</div>
	
	<div class="tbl_write2">
		<table>
			<tbody>
				<tr>
					<th>*반출일</th>
					<td>
						<input type="text" name="eEntryExporterDate" id="eEntryExporterDate" style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly"/>
					</td>
					<th>반출확인자</th>
					<td>
						<input type="text" name="eExitExporter" id="eExitExporter" style="width:95%; text-align:left;" maxLength="50" value="${staffVO.kStaffName}"/>
					</td>
				</tr>
				<tr>
					<th>특이사항</th>
					<td colspan="3">
						<input type="text" name="eEntryNote" id="eEntryNote" style="width:95%; text-align:left;" maxLength="150" value=""/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="tbl_list">
		<table>
			<caption style="text-align: left; margin-bottom:10px;">
				   <a class="mes_btn" onclick="sel_asset()" style="float:right">장비 선택</a>
<!-- 				   <a class="mes_btn" onclick="addRow()" style="float:right">장비 선택</a> -->
			</caption>
				<thead>
					<tr>
						<th style="width: 8%;">구분</th>
						<th style="width: 10%;">*자산유형</th>
						<th style="width: 12%;">*자산명</th>
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
					<td colspan="10">장비  선택 버튼을 클릭하여  임시 장비 정보를 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="margin-top:30px;visibility: hidden;">   
		<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
				
					<a onclick="approvalPop();">승인권자 선택</a>
					 <label for="oPass" class="checkbox-container">
						결재 제외<input type="checkbox" id="oPass" name="oPass" class="checkbox" onclick="handleOPassClick();"/>
					</label>
				</li>
			</ul>
		</div>
			
		<div class="tbl_list">
			<table>
				<thead>
					<tr>
						<th colspan="5">결재 정보</th>
					</tr>
					<tr>
						<th style="width: 10%;">결재순서</th>
						<th style="width: 20%;">부서</th>
						<th style="width: *%;">성명</th>
					</tr>
				</thead>
				<tbody id="lineRow3">			
				</tbody>
				
			</table>
		</div>
	</div>
	<div class="tbl_btn_right">
		<ul>
			<li>
				<a onclick="insert_go();">등록</a>
			</li>
			<li>
				<a onclick="cancle();">목록</a>
			</li>
		</ul>
	</div>
</form>
