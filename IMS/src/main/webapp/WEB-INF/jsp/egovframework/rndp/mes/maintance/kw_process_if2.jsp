<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/se/js/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		toggleDivVisibility();
		datepickerSet("mProcessDate");
		$("#oPass").click(function(){
			 if($("#oPass").is(":checked")){
				  $("#oSignPass").val("Y");
			 }else{
				  $("#oSignPass").val("N");
			 }
		});
	});
	
	
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
		innerStr += "			<a class='del' onclick=\"fileDel('filename_" + fileIndex+"');\" style='text-decoration:none;'>X</a>";
		innerStr +=	"		</td>";
		innerStr +=	"		<td>";
		innerStr += 			atchFileName;
		innerStr += "			<input type='hidden' id='fileKey'   name='fileKey' value='0' />";
		innerStr += "			<input type='hidden' id='AddFileId"+btnGubun+"' name='eAddFileId' value='"+AddFileId+"' />";
		innerStr += "			<input type='hidden' id='atchFileName"+btnGubun+"' name='atchFileName' value='"+atchFileName+"' />";
		innerStr +=	"		</td>";
		liobj.innerHTML = innerStr;	
		
	} 
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/maintance/kw_maintance_lf.do";
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
	
	var btnGubun = "";
	function mesIMGreg(gubun) { 
		btnGubun = gubun;
		var url = "/mes/maintance/popup/mesIMGregAdd.do";
		window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	}
	
	// 등록
	function insert_go(){
		if(chkIns()){
			if(confirm("처리 내역을 등록하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_process_i.do";
				document.frm.submit();
			}
		}
	}
	
	// validation
	function chkIns(){
		if($("#mProcessType").val() == "0"){
			alert("처리구분을 입력하세요.");
			$("#mProcessType").focus();
			return false;
		}
		if($("#mProcessResult").val() == ""){
			alert("처리내용을 입력하세요.");
			$("#mProcessResult").focus();
			return false;
		}

		if($("#oSignPass").val() != 'Y'){
		if(document.getElementsByName("sSignStaffKey").length == 0){
			alert("승인권자를 선택해주세요");
			return false;
			}
		}
		return true;
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
		innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>결재자</span>";
		innerStr += "		</td>";		
		// 이름
		innerStr += "		<td>";
		innerStr += "			"+(obj.kPositionName)+" "+(obj.kClassName)+" "+(obj.kStaffName)+"";
		innerStr += "		</td>";
		innerStr += "	</tr>";
		
		$(innerStr).appendTo("#lineRow3");		
		
		referIndex++;
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
	
	
	function toggleDivVisibility() {
	    var mIssueTypeName = document.getElementById("mIssueTypeName").value;
	    var viewDiv1 = document.getElementById("viewDiv1");
	    var viewDiv2 = document.getElementById("viewDiv2");

	    // 초기화: 모든 div 숨기기
	    viewDiv1.style.display = "none";
	    viewDiv2.style.display = "none";

	    // mIssueTypeName 값에 따라 해당 div 보이게 하기
	    if (mIssueTypeName === "복합장애") {
	        viewDiv1.style.display = "block";
	        viewDiv2.style.display = "block";
	    } else {
	        // 다른 값이면 숨기기
	        viewDiv1.style.display = "none";
	        viewDiv2.style.display = "none";
	    }
	}
</script>

<style>
	#td_editor{
		padding-left : 0em;
	}
</style>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/maintance/kw_maintance_i.do"> 
	<input type="hidden" id="pageIndex" 		name="pageIndex" 			value="${mesMaintanceVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" 	value="${mesMaintanceVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" 		name="topStartDate" 		value="${mesMaintanceVO.topStartDate}" />
	<input type="hidden" id="topEndDate" 		name="topEndDate" 			value="${mesMaintanceVO.topEndDate}" />
	<input type="hidden" id="searchWord" 		name="searchWord" 			value="${mesMaintanceVO.searchWord}" />
	<input type="hidden" id="searchType" 		name="searchType" 			value="${mesMaintanceVO.searchType}" />
	
	
	<input type="hidden" id="mMaintanceKey"		name="mMaintanceKey"		value="${maintanceInfo.mMaintanceKey}" />
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	
	<div class="content">
		<div class="content_tit">
			<h2>유지관리 이력 등록</h2>
		</div>
	</div>
	
	
	
	<div class="tbl_write">
        <table>
	        <tbody>
	          	<tr>
					<th >작성자</th>
					<td >${maintanceInfo.mAuthor}
						<input type="hidden" id="mAuthor" name="mAuthor"  value="${maintanceInfo.mAuthor}" maxlength="50"/>
					</td>
					<th >작성일</th>
					<td >${maintanceInfo.mMaintanceDate}
						<input type="hidden" id="mMaintanceDate" name="mMaintanceDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
					</td>
  				</tr>
	          	<tr>
					<th style="width:15%;">*요청기관</th>
					<td> ${maintanceInfo.mMaintanceCatePath}
<!--             		 <input type="hidden" id="mMaintanceCateKey" name="eMaintanceCateKey" value="0"/> -->
<!-- 						<input type="hidden" id="mMaintanceCateName" name="eMaintanceCateName" value=""/> -->
<!-- 						<input type="hidden" id="maintanceSelect0" name="eaintanceSelect0" value="0"/> -->
						
<!-- 						<span id="mMaintanceCate1"> -->
<!-- 							<select id="maintanceSelect1" name="eMaintanceSelect1" style="width:120px;" onChange="getCateData(2)"> -->
<!-- 								<option value="0" selected>선택 없음</option> -->
<!-- 							</select> -->
<!-- 						</span> -->
<!-- 						> -->
<!-- 						<span id="mMaintanceCate2"> -->
						
<!-- 							<select id="maintanceSelect2" name="eMaintanceSelect2" style="width:120px;" onChange="getCateData(3)"> -->
<!-- 								<option value="0" selected>선택 없음</option> -->
<!-- 							</select> -->
<!-- 						</span> -->
<!-- 						> -->
<!-- 						<span id="mMaintanceCate3"> -->
<!-- 							<select id="maintanceSelect3" name="eMaintanceSelect3" style="width:120px;" onChange="getCateData(4)"> -->
<!-- 								<option value="0" selected>선택 없음</option> -->
<!-- 							</select> -->
<!-- 						</span> -->
<!-- 						> -->
<!-- 						<span id="mMaintanceCate4"> -->
<!-- 							<select id="maintanceSelect4" name="eMaintanceSelect4" style="width:120px;"> -->
<!-- 								<option value="0" selected>선택 없음</option> -->
<!-- 							</select> -->
<!-- 						</span> -->
            		</td>
            		<th>*장애구분</th>
					<td>${maintanceInfo.mIssueTypeName}
						<input type="hidden" id="mIssueTypeName" name="mIssueTypeName" value="${maintanceInfo.mIssueTypeName}" />
<!-- 						<select id="mMaintanceIssueType" name="mMaintanceIssueType" style="width:25%;" onchange="selectName(this,'mIssueTypeName')"> -->
<!-- <!-- 						<option value="">선택</option> --> 
<%-- 						<c:forEach var="gubun34List" items="${gubun34List}"> --%>
<%-- 							<option value="${gubun34List.sGubunName}" data-value2='${gubun34List.sGubunName}' <c:if test="${gubun34List.sGubunName eq maintanceInfo.mIssueTypeName}">selected="selected"</c:if>>${gubun34List.sGubunName}</option>	 --%>
<%-- 						</c:forEach>		       				 --%>
<!-- 		     			</select> -->
					</td>
				</tr>
				<tr>
					<th style="width: 15% !important;">*자산유형</th>
					<td style="width: 35% !important;">${maintanceInfo.eAssetTypeName}
<!-- 					<input type="hidden" id="eAssetTypeName" name="eAssetTypeName" /> -->
<!-- 					<select id='eAssetType' name='eAssetType'  onchange="selectName(this,'eAssetTypeName')" style="width:120px;" > -->
<!-- 						<option value=''>선택</option> -->
<%-- 						<c:forEach var='list' items='${gubun36List}'> --%>
<%-- 							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'  <c:if test="${list.sGubunName eq maintanceInfo.eAssetTypeName}">selected="selected"</c:if>>${list.sGubunName}</option>						 --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
					</td>
					<th>*처리유형</th>
					<td> ${maintanceInfo.mMaintanceTypeName}
<!-- 						<input type="hidden" id="mMaintanceTypeName" name="mMaintanceTypeName" />	 -->
<!-- 						<select id="mMaintanceType" name="mMaintanceType" style="width:25%;" onchange="selectName(this,'mMaintanceTypeName')" > -->
<!-- 						<option value="">선택</option> -->
<%-- 							<c:forEach var="gubun33List" items="${gubun33List}"> --%>
<%-- 								<option value='${gubun33List.sGubunKey}' data-value2='${gubun33List.sGubunName}'  <c:if test="${gubun33List.sGubunName eq maintanceInfo.eAssetTypeName}">selected="selected"</c:if>>${gubun33List.sGubunName}</option>							 --%>
<%-- 							</c:forEach>		       				 --%>
<!-- 		     			</select> -->
					</td>
					
				</tr>
				<tr>
					<th >*요청자</th>
					<td > ${maintanceInfo.mRequester}
						<input type="hidden" id=mRequester name="mRequester" style="width:90%;" value="${maintanceInfo.mRequester}" maxLength="100"/>
					</td>
					<th >처리 담당자</th>
					<td >${maintanceInfo.mHandler}
						<input type="hidden" id="mHandler" name="mHandler" style="width:90%;" value="${maintanceInfo.mHandler}" maxLength="100"/>
					</td>
  				</tr>
 					<tr>
					<th >요청일자</th>
					<td >${maintanceInfo.mRequestDate}
						<input type="hidden" id="mRequestDate" name="mRequestDate" style="width:120px;text-align: center;" value="${maintanceInfo.mRequestDate}" class="inp_color"  readonly="readonly"/>
					</td>
					<th >처리일자</th>
					<td >${maintanceInfo.mProcessingDate}
						<input type="hidden" id="mProcessingDate" name="mProcessingDate" style="width:120px;text-align: center;" value="${maintanceInfo.mProcessingDate}" class="inp_color"  readonly="readonly"/>
					</td>
  				</tr>
				<tr>
					<th	colspan="4" style="text-align:center;">
						요청내용
					</th>
				</tr>
				<tr>
					<td id="td_editor" colspan="4" align="center" scope="row"> 
						${maintanceInfo.mMaintanceContent}
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	
<!-- 	<div class="tbl_list">          	 -->
<!-- 		<table> -->
<%-- 			<caption style="text-align: left;"> --%>
<!-- 				□ 파일정보 -->
<%-- 			</caption> --%>
<!-- 			<thead>   -->
<!-- 	            <tr> -->
<!-- 	         		<th style="width:5%">순번</th> -->
<!-- 	         		<th>파일명</th> -->
<!-- 	         	</tr> -->
<!-- 	        </thead> -->
<!-- 	        <tbody> -->
<%-- 				<c:forEach var="file" items="${files}" varStatus="i"> --%>
<!-- 		           	<tr> -->
<!-- 		           		<td> -->
<%-- 		           			<c:out value="${i.index + 1}" /> --%>
<!-- 		           		</td> -->
<!-- 		           		<td> -->
<%-- 		           			<a href="javascript:fn_egov_downFile('${file.eAddFileId}','0')"> --%>
<%-- 		           				<c:out value="${file.atchFileName}"/> --%>
<!-- 		           			</a> -->
<!-- 		           		</td> -->
<!-- 		           	</tr> -->
<%-- 				</c:forEach> --%>
<%-- 				<c:if test="${empty files}"> --%>
<!-- 					<tr> -->
<!-- 						<td colspan="2">내역이 없습니다.</td> -->
<!-- 					</tr> -->
<%-- 				</c:if> --%>
<!-- 			</tbody> -->
<!-- 		</table>  -->
<!-- 	</div> -->
	

	<div class="content" style="padding-top:20px;" >
		<div class="content_tit">
			<h2>유지관리 처리내역</h2>
		</div>
	</div>
		
	<div class="tbl_write">
        <table>
	        <tbody>
<!-- 	          	<tr> -->
<!-- 					<th style="width:15%;">*처리구분</th> -->
<!-- 					<td style="width:35%;"> -->
<!-- 						<select id="mProcessType" name="mProcessType"> -->
<!-- 							<option value="처리완료" selected>처리완료</option> -->
<!-- 							<option value="처리불가">처리불가</option> -->
<!-- 						</select> -->
<!-- 					</td> -->
<!-- 					<th style="width:15%;">*요청자</th> -->
<!-- 					<td style="width:35%;"> -->
<%-- 						${staffVo.kStaffName} --%>
<%-- 						<input type="hidden" id="mProcessStaffKey" name="mProcessStaffKey" value="${staffVo.kStaffKey}"/> --%>
<%-- 						<input type="hidden" id="mProcessStaffName" name="mProcessStaffName" value="${maintanceInfo.mRequester}"/> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<th>*처리내용</th>
					<td> 	
						<input type="text" id="mProcessResult" name="mProcessResult" style="width:90%; text-align:center;" maxLength="100"/>
					</td>
					<th>*처리일자</th>
					<td>
						<input type="text" id="mProcessDate" name="mProcessDate" style="width:150px; text-align:center;" class="inp_color"  value="${maintanceInfo.mProcessingDate}" readonly/>
					</td>
				</tr>
				<tr>
					<th	colspan="4" style="text-align:center;">
						요청내용
					</th>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="content" style="padding-top:20px;" id="viewDiv1">
		<div class="content_tit">
			<h2>장비 정보</h2>
		</div>
	</div>
	<div class="tbl_list" id="viewDiv2">
		<table>
			<caption style="text-align: left; margin-bottom:10px;">
				   <a class="mes_btn" onclick="sel_asset()" style="float:right">장비 선택</a>
			</caption>
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
	
	<div class="content" style="padding-top:20px;">
		<div class="content_tit">
			<h2>이력 정보</h2>
		</div>
	</div>
	<div class="tbl_list">
		<table>
			<caption style="text-align: left; margin-bottom:10px;">
				   <a class="mes_btn" onclick="sel_assetTwo()" style="float:right">이력 선택</a>
			</caption>
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
			<tbody id="lineRowTwo">
				<tr>
					<td colspan="10">이력정보를 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
<!-- 	<div class="tbl_top" style="margin-top:30px;"> -->
<!-- 		<ul class="tbl_top_left"> -->
<!-- 			<li> -->
<!-- 				<a class="mes_btn" onclick="mesIMGreg(1);">첨부파일 추가</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 	</div>	 -->
	
<!-- 	<div class="tbl_list">          	 -->
<!-- 		<table> -->
<!-- 			<thead>   -->
<!-- 	          	<tr> -->
<!-- 	             	<th colspan="2">파일 정보</th> -->
<!-- 	            </tr> -->
<!-- 	            <tr> -->
<!-- 	         		<th style="width:5%">구분</th> -->
<!-- 	         		<th>파일명</th> -->
<!-- 	         	</tr> -->
<!-- 	         </thead> -->
<!-- 	         <tbody id="lineRow"> -->
<!-- 			</tbody> -->
<!-- 		</table>  -->
<!-- 	</div> -->
	
	<div style="margin-top:30px;">   
		<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<a onclick="approvalPop();">승인권자 선택</a>
					결재 제외<input type="checkbox" id="oPass" name="oPass" class="checkbox"/>
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
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<li>
					<a onclick="insert_go();">등록</a>
				</li>
			</c:if>
			<li>
				<a onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>

</form>