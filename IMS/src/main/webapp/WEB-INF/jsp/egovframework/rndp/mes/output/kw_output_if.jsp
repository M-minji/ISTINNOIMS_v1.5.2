<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


<style>
.tbl_write>table>tbody>tr>td {
	width:10%;
}

.file-wrapper {
    display: none;
}
</style>
<script type="text/javascript">


$(document).ready(function(){	
	datepickerSet('oStartDate', 'oEndDate');
	$("#oStartDate").val(nowDate());
	$("#oEndDate").val(nowDate());
	$("#oPass").click(function(){
		 if($("#oPass").is(":checked")){
			  $("#oSignPass").val("Y");
		 }else{
			  $("#oSignPass").val("N");
		 }
	});
    
    // 삭제 버튼이 클릭될 때 eIMGregName 값을 비워주는 함수
    $(document).on('click', '.del', function() {
        var parentWrapper = $(this).closest('.file-wrapper');
        parentWrapper.find('.eIMGregName').val('');
        parentWrapper.hide();
    });
});


    
    function toggleFileWrapper(btnGubun) {
	        var eIMGregName = document.getElementById('eIMGregName'+btnGubun).value;
// 	        var $fileWrapper = $('.file-wrapper[data-file-index="' + btnGubun + '"]');
	        var $fileWrapper = $('#file-wrapper' + btnGubun);
	        
	        if (eIMGregName != '') {
	            $fileWrapper.show();
	        } else {
	            $fileWrapper.hide();
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


// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();
	
}

var btnGubun;
function mesIMGreg(index) {
	btnGubun = index;
	var url = "/mes/output/popup/kw_output_IMGreg_lf.do";
	window.open(url,"outputFileList","width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
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
	innerStr += "			<select id='oOutputItemGubun_"+itemRowIndex+"' name='oOutputItemGubun' onchange='setTxt("+itemRowIndex+")' value=''>";
	innerStr += "			<option value=''>구분</option>";
	<c:forEach var="gubunList" items="${gubunList}" varStatus="i">
	innerStr += "				<option value='${gubunList.sGubunName}'>${gubunList.sGubunName}</option>";
	</c:forEach>
	innerStr += "			</select>";
	innerStr += "		</td>";
	
	
	//산출물명
	innerStr += "		<td>"; 
	innerStr +=	"			<input type='text' id='oOutputItemName_"+itemRowIndex+"' name='oOutputItemName' value='' maxlength='30' />";
	innerStr += "		</td>";

	
	// 파일명
	innerStr += "		<td>"; 
	innerStr += "        <div class='file-wrapper' id='file-wrapper"+itemRowIndex+"'>";
	innerStr +=	"			<input type='hidden' id='fileKey' name='fileKey' value='0' />";
	innerStr +="			<input type='hidden' id='fileIndex"+itemRowIndex+"' name='fileIndex' value='"+itemRowIndex+"' />";
	innerStr +="			<input type='hidden' id='oOutputItemFileId"+itemRowIndex+"' name='oOutputItemFileId' style=''width:70%' value='' />";
	innerStr += "		<div style='border:1px solid #aaa; border-radius:20px;width: fit-content; float: left;'>";
	innerStr +="			<input type='text' id='eIMGregName"+itemRowIndex+"' name='eIMGregName' style='width:70%; border:none;' value='' readonly />";
	innerStr +="			<a class='del' onclick='ImgFileRm("+itemRowIndex+");'>X</a>";
	innerStr += "		</div>"; 
	innerStr += "        </div>";
	innerStr +="			 <a onclick='mesIMGreg("+itemRowIndex+");' class='mes_btn'>등록</a>"; 
	innerStr += "		</td>";

	 
	// 비고
	innerStr += "		<td>"; 
	innerStr +=	"			<input type='text' id='oOutputItemEtc_"+itemRowIndex+"' name='oOutputItemEtc' value='' maxlength='50' />";
	innerStr += "		</td>";
	innerStr += "	</tr>";
	$(innerStr).appendTo("#lineRow3");
	
	itemRowIndex++;
}

//첨부파일 삭제
function ImgFileRm(itemRowIndex){
	$('#fileIndex'+itemRowIndex+', #oOutputItemFileId'+itemRowIndex+',#eIMGregName'+itemRowIndex+'').val('');
}

function insert_go(){
	if(chkIns()){    
		if(confirm("저장하시겠습니까?")){
		 
			$("#mloader").show(); 
			document.frm.action = "/mes/output/kw_output_i.do";
	 		document.frm.submit();
		}
	}
}

//validation
function chkIns(){
	if($("#oComName").val() == ""){
		alert("사업명을 적어주세요.");
		$("#oComName").focus();
		return false;
	}

	if($("#oIntroManager").val() == ""){
		alert("담당자를 적어주세요.");
		$("#oIntroManager").focus();
		return false;
	}
	if($("#oManagerPhone").val() == ""){
		alert("담당자 연락처를 적어주세요.");
		$("#oManagerPhone").focus();
		return false;
	}
	
	var Len = document.getElementsByName("oOutputItemGubun").length;
	for(i = 0; i < Len; i++){
		if(document.getElementsByName("oOutputItemGubun")[i].value == ""){
			alert("산출물 구분을 선택해주세요.");
			document.getElementsByName("oOutputItemGubun")[i].focus();
			return false;
		}
		if(document.getElementsByName("oOutputItemName")[i].value == ""){
			alert("산출물 명을 작성해주세요.");
			document.getElementsByName("oOutputItemName")[i].focus();
			return false;
		}
		if(document.getElementsByName("eIMGregName")[i].value == ""){
			alert("파일을 선택해주세요.");
			document.getElementsByName("eIMGregName")[i].focus();
			return false;
		}
	
	}
	if($("#oSignPass").val() != 'Y'){
		if(document.getElementsByName("sSignStaffKey").length == 0){
			alert("승인권자를 선택해주세요");
			return false;
		}
	}
	return true;
}

function setComma(){
var cost = $("#oBusMoney").val();
$("#oBusMoney").val(comma(cost));
}

function fileAdd(eID, eNAME){
// 	alert(eID);
// 	btnGubun
$("#oOutputItemFileId"+btnGubun).val(eID);
$("#eIMGregName"+btnGubun).val(eNAME);

toggleFileWrapper(btnGubun);
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


var referIndex = document.getElementsByName("sSignStaffKey").length;
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
	
	$(innerStr).appendTo("#lineRow4");		
	
	referIndex++;
}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/output/kw_output_if.do">
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />

	<div class="content">
		<div class="content_tit">
			<h2>산출물 관리 등록</h2>
		</div>
	</div>
	
	<div class="tbl_esti_detail_total">		
		<div class="tbl_write">
 			<table>
 				<tbody>
	  				<tr>
		  				<th style="width:10%;">*사업명</th>
						<td style="width:90%;" colspan="3">
							<input type="text" id="oComName" name="oComName" value="" style="width:90%;" maxlength="30"/>
						</td>
						<th style="width:10%;">*사업기간</th>
						<td style="width:15%;">
							<input type="text" class="inp_color" id="oStartDate" name="oStartDate" style="width:40%;" value="" readonly /> -
							<input type="text" class="inp_color" id="oEndDate" name="oEndDate" style="width:40%;" value="" readonly />
						</td>
						<!-- <th style="width:10%;">사업비용</th>
						<td>
							<input type="text" id="oBusMoney" name="oBusMoney" style="width:90%; text-align: right;"  maxlength="10"  value="" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" onblur="setComma();" />
						</td> -->
					</tr>
					<!-- <tr>
						<th style="width:10%;">*도입업체</th>
						<td style="width:15%;">
							<input type="text" id="oIntoCom" name="oIntoCom" style="width:90%;" value=""  />
						</td>
						<th style="width:10%;">*도입업체주소</th>
						<td style="width:15%;">
							<input type="text" id="oIntroAdress" name="oIntroAdress" style="width:90%;" value=""  />
						</td>
						<th style="width:10%;">*상세주소</th>
						<td style="width:15%;">
							<input type="text" id="oDetailAdress" name="oDetailAdress" style="width:90%;" value=""  />
						</td>
					</tr> -->
					<tr>
					<th style="width:10%;">* 담당자</th>
						<td style="width:15%;">
							<input type="text" id="oIntroManager" name="oIntroManager" style="width:90%;" value=""  maxlength="30"/>
						</td>
					<th style="width:10%;">*담당자연락처</th>
						<td style="width:15%;">
							<input type="text" id="oManagerPhone" name="oManagerPhone" style="width:90%;" value="" maxlength="30" />
						</td>
					<th style="width:10%;">담당자이메일</th>
						<td style="width:15%;">
							<input type="text" id="oManagerEmail" name="oManagerEmail" style="width:90%;" value="" maxlength="30" />
						</td>	
					</tr>
					<tr>
					<th style="width:10%;">담당PM명</th>
						<td style="width:15%;">
							<input type="text" id="oPmName" name="oPmName" style="width:90%;" value="${mesOutputVO.kStaffName}" maxlength="30" /> 
						</td>
					<th style="width:10%;">담당PM연락처</th>
						<td style="width:15%;">
							<input type="text" id="oPmPhone" name="oPmPhone" style="width:90%;" value="" maxlength="30" />
						</td>
					<th style="width:10%;">담당PM이메일</th>
						<td style="width:15%;">
							<input type="text" id="oPmEmail" name="oPmEmail" style="width:90%;" value="" maxlength="30" />
						</td>	
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="tbl_top">
		<ul class="tbl_top_left" >
			<li>
				<a style="cursor: pointer;" onclick="addRow();">행추가</a>
			</li>
		</ul>
	</div>

	<div class="tbl_list">          	
		<table>
			<thead>  
	            <tr>
	         		<th style="width:5%">구분</th>
	         		<th style="width:10%">산출물구분</th>
	         		<th style="width:10%">산출물명</th>
	         		<th style="width:10%">파일첨부</th>
	         		<th style="width:10%">비고</th>
	         	</tr>
	         </thead>
	         <tbody id="lineRow3">
	
			</tbody>
		</table> 
	</div>
				
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
				<tbody id="lineRow4">			
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr>
							<td>
								<input type="hidden" id="sSignStaffKey_${i.index}" name="sSignStaffKey" value="${signList.sSignStaffKey}"/>
								<input type="hidden" id="sSignStaffName_${i.index}" name="sSignStaffName" value="${signList.sSignStaffName}"/>
								<input type="hidden" id="sSignSequence_${i.index}" name="sSignSequence" value="${i.index}"/>
								<input type='hidden' id='referSign_${j.index}' name='referSign' value='${signList.sSignStaffKey}'/>
								<input type='hidden' id='gubun_${j.index}' name='gubun' value='${signList.sSignStaffGubun}'/>
								${i.index + 1}
							</td>
							<td>
								결재자
							</td>
							<td style="text-align:left; padding-left:5px; width:20%;">
								${signList.sSignStaffName}
							</td>
						</tr>
					</c:forEach>
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