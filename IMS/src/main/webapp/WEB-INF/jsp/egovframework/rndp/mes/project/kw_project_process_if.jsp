<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	datepickerSet('pStartDate', 'pEndDate');
	datepickerIdSet("pReDate");
	$("#pStartDate").val(nowDate());
	$("#pEndDate").val(nowDate());
	$("#pReDate").val(nowDate());
})

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
	document.frm.action = "/mes/project/kw_project_lf.do";
	document.frm.submit(); 
}

//등록
function insert_go(){
	if(chkIns()){
		if(confirm("처리 내역을 등록하시겠습니까?")){
// 			$("#mloader").show();
			document.frm.action = "/mes/project/kw_project_process_i.do";
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

function delete_go(){

	if(confirm("삭제하시겠습니까?")){

		document.frm.action = "/mes/project/kw_project_d.do";
		document.frm.submit();
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
	innerStr += "			"+(obj.kPositionName)+" "+(obj.kClassName)+" "+(obj.kStaffName)+"";
	innerStr += "		</td>";
	innerStr += "	</tr>";
	
	$(innerStr).appendTo("#lineRow3");		
	
	referIndex++;
}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/project/kw_project_process_i.do">
  <input  type="hidden" id="pProjectkey" name="pProjectkey" value="${projectInfo.pProjectkey}" />
	<div class="content">
		<div class="content_tit">
			<h2>프로젝트 회의록 상세</h2>
		</div>
	</div>
	
	<div class="tbl_esti_detail_total">		
		<div class="tbl_write">
 			<table>
 				<tbody>
	  				<tr>
		  				<th style="width:10%;">사업명</th>
						<td style="width:10%;">
							<span>${projectInfo.pComName}</span>
						</td>
						<th style="width:10%;">사업기간</th>
						<td style="width:15%;">
							<span>${projectInfo.pStartDate}</span>
							<span>${projectInfo.pEndDate}</span>
						</td>
						<th style="width:10%;">도입업체</th>
						<td>
							<span>${projectInfo.pItroCom}</span>
						</td>
					</tr>
					<tr>
						<th style="width:10%;">회의구분</th>
						<td style="width:15%;">
							<span>${projectInfo.pGubunStep}</span>
							<span>${projectInfo.pGubun}</span>
						</td>
						<th style="width:10%;">요청자</th>
						<td style="width:15%;">
							<span>${projectInfo.pRequest}</span>
						</td>
						<th style="width:10%;">요청일시</th>
						<td style="width:15%;">
							<span>${projectInfo.pRequest}</span>
						</td>
					</tr>
					<tr>
						<th style="width:10%;">회의제목</th>
						<td style="width:15%;" colspan="5">
							<span>${projectInfo.pTilte}</span>
						</td>
					</tr>
					<tr>
						<th style="width:10%;">회의내용</th>
						<td id="td_editor" colspan="5" align="center" scope="row"> 
						${projectInfo.pContent}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
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
					<a style="cursor: pointer;" onclick="insert_go();">등록</a>
				</li>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>
</form>