<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


<style>
.tbl_write>table>tbody>tr>td {
	width:10%;
}

</style>
<script type="text/javascript">


$(document).ready(function(){	
	datepickerSet('oStartDate', 'oEndDate');
	$("#oStartDate").val(nowDate());
	$("#oEndDate").val(nowDate());
})

// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}
	
// 오늘 날짜
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate; 
}

// 목록
function cancel(){
	/* $("#mloader").show(); */
	document.frm.action = "/mes/output/kw_output_lf.do";
	document.frm.submit(); 
}

//등록
function insert_go(){
	if(chkIns()){
		if(confirm("처리 내역을 등록하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/output/kw_output_process_i.do";
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
	
	if(document.getElementsByName("sSignStaffKey").length == 0){
		alert("승인권자를 선택해주세요");
		return false;
	}
	
	return true;
}

// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();
	
}

var btnGubun;
function mesIMGreg(index) {
	btnGubun = index;
	var url = "/mes/output/popup/kw_output_IMGreg_lf.do";
	window.open(url,"outputFileList","width=1100,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

// 파일 추가
var itemRowIndex = 0;
	
function addRow(){
		
	var innerStr = "";
		
	// 구분(행삭제)
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a class='del' onclick='delRow(this);'>X</a>";
	innerStr += "		</td>";
	
	// 산출물명
	innerStr += "		<td>";
	innerStr += "			<select id='oOutputItemGubun_"+itemRowIndex+"' name='oOutputItemGubun' onchange='setTxt("+itemRowIndex+")'>";
	innerStr += "			<option value='0'>구분</option>";
	<c:forEach var="gubunList" items="${gubunList}" varStatus="i">
	innerStr += "				<option value='${gubunList.sGubunName}'>${gubunList.sGubunName}</option>";
	</c:forEach>
	innerStr += "			</select>";
	innerStr += "		</td>";
	
	
	//산출물명
	innerStr += "		<td>"; 
	innerStr +=	"			<input type='text' id='oOutputItemName_"+itemRowIndex+"' name='oOutputItemName' value='' />";
	innerStr += "		</td>";

	
	// 파일명
	innerStr += "		<td>"; 
	innerStr +=	"			<input type='hidden' id='fileKey' name='fileKey' value='0' />";
	innerStr +="			<input type='hidden' id='fileIndex' name='fileIndex' value='"+itemRowIndex+"' />";
	innerStr +="			 <a onclick='mesIMGreg("+itemRowIndex+");' class='mes_btn'>등록</a>";
	innerStr +="			<input type='hidden' id='oOutputItemFilid"+itemRowIndex+"' name='oOutputItemFilid' style='width:300px' value='' />";
	innerStr +="			<input type='text' id='eIMGregName"+itemRowIndex+"' name='eIMGregName' style='width:300px' value='' />";
	innerStr += "		</td>";

	
	// 비고
	innerStr += "		<td>"; 
	innerStr +=	"			<input type='text' id='oOutputItemEtc_"+itemRowIndex+"' name='oOutputItemEtc' value='' />";
	innerStr += "		</td>";
	innerStr += "	</tr>";
	$(innerStr).appendTo("#lineRow3");
	
	itemRowIndex++;
}

function delete_go(){

	if(confirm("삭제하시겠습니까?")){
		
		document.frm.action = "/mes/output/kw_output_d.do";
		document.frm.submit();
	}
}

function setComma(){
var cost = $("#oBusMoney").val();
$("#oBusMoney").val(comma(cost));
}

function fileAdd(eID, eNAME){
// 	alert(eID);
// 	btnGubun
$("#oOutputItemFilid"+btnGubun).val(eID);
$("#eIMGregName"+btnGubun).val(eNAME);
	
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
	innerStr += "			"+(obj.kPositionName)+" "+(obj.kClassName)+" "+(obj.kStaffName)+"";
	innerStr += "		</td>";
	innerStr += "	</tr>";
	
	$(innerStr).appendTo("#lineRow3");		
	
	referIndex++;
}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/output/kw_output_process_if.do">
  	<input  type="hidden" id="outputKey" name="outputKey" value="${outputInfo.outputKey}" />
	<div class="content">
		<div class="content_tit">
			<h2>산출물 관리 상세</h2>
		</div>
	</div>
	
	<div class="tbl_esti_detail_total">		
		<div class="tbl_write">
 			<table>
 				<tbody>
	  				<tr>
		  				<th style="width:10%;">사업명</th>
						<td style="width:10%;">
							<span>${outputInfo.oComName}</span>
						</td>
						<th style="width:10%;">사업기간</th>
						<td style="width:15%;">
							<span>${outputInfo.oStartDate}</span>
							~
							<span>${outputInfo.oEndDate}</span>
						</td>
						<th style="width:10%;">사업비용</th>
						<td>
							<span>${outputInfo.oBusMoney}</span>
						</td>
					</tr>
					<tr>
						<th style="width:10%;">도입업체</th>
						<td style="width:15%;">
							<span>${outputInfo.oIntoCom}</span>
						</td>
						<th style="width:10%;">도입업체주소</th>
						<td style="width:15%;">
							<span>${outputInfo.oIntroAdress}</span>	
						</td>
						<th style="width:10%;">상세주소</th>
						<td style="width:15%;">
							<span>${outputInfo.oDetailAdress}</span>
						</td>
					</tr>
					<tr>
					<th style="width:10%;">도입업체부서 및 담당자</th>
						<td style="width:15%;">
							<span>${outputInfo.oIntroManager}</span>
						</td>
					<th style="width:10%;">담당자연락처</th>
						<td style="width:15%;">
								<span>${outputInfo.oManagerPhone}</span>
						</td>
					<th style="width:10%;">담당자이메일</th>
						<td style="width:15%;">
							<span>${outputInfo.oManagerEmail}</span>
						</td>	
					</tr>
					<tr>
					<th style="width:10%;">담당PM명</th>
						<td style="width:15%;">
							<span>${outputInfo.kStaffName}</span>
						</td>
					<th style="width:10%;">담당PM연락처</th>
						<td style="width:15%;">
							<span>${outputInfo.oPmPhone}</span>
						</td>
					<th style="width:10%;">담당PM이메일</th>
						<td style="width:15%;">
							<span>${outputInfo.oPmEmail}</span>
						</td>	
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="tbl_list">          	
		<table>
			<thead>  
	            <tr>
	         		<th style="width:10%">산출물구분</th>
	         		<th style="width:10%">산출물명</th>
	         		<th style="width:10%">파일첨부</th>
	         		<th style="width:10%">비고</th>
	         	</tr>
	         </thead>
	         <tbody>
	         	<c:forEach var="outputItemList" items="${outputItemList}" varStatus="i">
			         <tr>
			         	<td style="width:15%;">
			         		<c:out value="${outputItemList.oOutputItemGubun}" />
						</td>
						<td style="width:15%;">
							<c:out value="${outputItemList.oOutputItemName}" />	
						</td>
			         	<td style="width:15%;">
										<a href="javascript:fn_egov_downFile('${outputItemList.atchFileId}','${outputItemList.fileSn}')">		
									<c:out value="${outputItemList.eIMGregName}"/></a>
						</td>
						<td style="width:15%;">
							<c:out value="${outputItemList.oOutputItemEtc}" />
						</td>
			         </tr>
	         	</c:forEach>
			</tbody>
		</table> 
	</div>
				
	<div style="margin-top:60px;border-top: 2px solid #6bb4ef;">   
		<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<a onclick="approvalPop();">승인권자 선택</a>
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