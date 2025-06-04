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
	datepickerSet('oStartDate', 'oEndDate');
	$("#oStartDate").val(nowDate());
	$("#oEndDate").val(nowDate());
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
	innerStr += "			<select id='oOutputItemGubun_"+itemRowIndex+"' name='oOutputItemGubun' onchange='setTxt("+itemRowIndex+")' value=''>";
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

function update_go(){

	var eIMGregName = document.getElementsByName("eIMGregName")[0].value;
	if(eIMGregName !== "" ){	
		var eIMGregName = document.frm.eIMGregName.value;
	    var _fileLen = eIMGregName.length;
	    var _lastDot = eIMGregName.lastIndexOf('.');
	    
	    // 확장자 명만 추출한 후 소문자로 변경
	    var _fileExt = eIMGregName.substring(_lastDot, _fileLen).toLowerCase();
	   
	
		if(_fileExt!=""){
			
		
			if(_fileExt ==  ".txt" ||
					_fileExt == ".jpeg" ||
					_fileExt == ".jpg" ||
					_fileExt == ".excel" ||
					_fileExt == ".gif" ||
					_fileExt == ".png" ||
					_fileExt == ".doc" ||
					_fileExt == ".pdf" ||
					_fileExt == ".xlsx" ||
					_fileExt == ".hwp" ){
			}else{
				alert("문서확장자를 확인해주세요.\nbmp, txt, jpeg, jpg, excel, gif, png, doc, pdf, hwp, xlsx 만 입력할수 있습니다.");
				return false;
			}
		}
	}
    
	if(confirm("저장하시겠습니까?")){
		
		 
	if($("#oBusMoney").val() == ""){
				$("#oBusMoney").val(0);
	}
			
		var cost = $("#oBusMoney").val();
		$("#oBusMoney").val(uncomma(cost));
		$("#mloader").show(); 
		document.frm.action = "/mes/output/kw_output_u.do";
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

</script>

<form id="frm" name="frm" method="post" >
  	<input  type="hidden" id="outputKey" name="outputKey" value="${outputInfo.outputKey}" />
  
	<div class="content">
		<div class="content_tit">
			<h2>산출물 관리 수정</h2>
		</div>
	</div>
	
	<div class="tbl_esti_detail_total">		
		<div class="tbl_write">
 			<table>
 				<tbody>
	  				<tr>
		  				<th style="width:10%;">사업명</th>
						<td style="width:10%;">
							<input type="text" name="oComName"  value="${outputInfo.oComName}" readonly/>
						</td>
						<th style="width:10%;">사업기간</th>
						<td style="width:15%;">
							<input type="text" class="inp_color" id="oStartDate" name="oStartDate" style="width:40%;" value="${outputInfo.oStartDate}" readonly /> ~
							<input type="text" class="inp_color" id="oEndDate" name="oEndDate" style="width:40%;" value="${outputInfo.oEndDate}" readonly />
						</td>
						<th style="width:10%;">사업비용</th>
						<td>
							<input type="text" id="oBusMoney" name="oBusMoney" style="width:90%; text-align: right;"  maxlength="10"  value="${outputInfo.oBusMoney}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" onblur="setComma();" />
						</td>
					</tr>
					<tr>
						<th style="width:10%;">도입업체</th>
						<td style="width:15%;">
							<input type="text" id="oIntoCom" name="oIntoCom" style="width:90%;" value="${outputInfo.oIntoCom}"  />
						</td>
						<th style="width:10%;">도입업체주소</th>
						<td style="width:15%;">
							<input type="text" id="oIntroAdress" name="oIntroAdress" style="width:90%;" value="${outputInfo.oIntroAdress}"   />
						</td>
						<th style="width:10%;">상세주소</th>
						<td style="width:15%;">
							<input type="text" id="oDetailAdress" name="oDetailAdress" style="width:90%;" value="${outputInfo.oIntroAdress}"  />
						</td>
					</tr>
					<tr>
					<th style="width:10%;">도입업체부서 및 담당자</th>
						<td style="width:15%;">
							<input type="text" id="oIntroManager" name="oIntroManager" style="width:90%;" value="${outputInfo.oIntroManager}"  />
						</td>
					<th style="width:10%;">담당자연락처</th>
						<td style="width:15%;">
							<input type="text" id="oManagerPhone" name="oManagerPhone" style="width:90%;" value="${outputInfo.oManagerPhone}"  />
						</td>
					<th style="width:10%;">담당자이메일</th>
						<td style="width:15%;">
							<input type="text" id="oManagerEmail" name="oManagerEmail" style="width:90%;" value="${outputInfo.oManagerEmail}"  />
						</td>	
					</tr>
					<tr>
					<th style="width:10%;">담당PM명</th>
						<td style="width:15%;">
							<input type="text" id="oPmName" name="oPmName" style="width:90%;" value="${outputInfo.oPmName}"  />
						</td>
					<th style="width:10%;">담당PM연락처</th>
						<td style="width:15%;">
							<input type="text" id="oPmPhone" name="oPmPhone" style="width:90%;" value="${outputInfo.oPmPhone}"  />
						</td>
					<th style="width:10%;">담당PM이메일</th>
						<td style="width:15%;">
							<input type="text" id="oPmEmail" name="oPmEmail" style="width:90%;" value="${outputInfo.oPmEmail}"  />
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
	         <tbody>
	         <c:forEach var="outputItemList" items="${outputItemList}" varStatus="i">
			         <tr>
			         	<td style="width:15%;">
							<a class='del' onclick='delRow(this);'>X</a>
						</td>
			         	<td style="width:15%;">
			         		<select id="oOutputItemGubun" name="oOutputItemGubun"  value="${outputItemList.oOutputItemGubun}">";
								<option value='0'>구분</option>";
									<c:forEach var="gubunList" items="${gubunList}" varStatus="i">
								<option value="${gubunList.sGubunName}"<c:if test="${gubunList.sGubunName eq outputItemList.oOutputItemGubun}">selected</c:if>>${outputItemList.oOutputItemGubun}</option>
									</c:forEach>
							</select>
			         	</td>
						<td style="width:15%;">
							<input type="text" id="oOutputItemName" name="oOutputItemName" style="width:90%;" value="${outputItemList.oOutputItemName}"  />
						</td>
			         	<td style="width:15%;">
							<input type="hidden" id="fileKey"  name='fileKey' value="0" />
							<input type="hidden" id="fileIndex" name="fileIndex" value="" />
							<input type="hidden" id="oOutputItemFileId" name=oOutputItemFileId style='width:300px' value="" />
							<a onclick="mesIMGreg();" class="mes_btn">등록</a>
							<input type="text" id="eIMGregName" name='eIMGregName' style="width:50%" value="${outputItemList.eIMGregName}" />
						</td>
						<td style="width:15%;">
							<input type="text" id="oOutputItemEtc" name="oOutputItemEtc" style="width:90%;" value="${outputItemList.oOutputItemEtc}"  />
						</td>
			         </tr>
	         	</c:forEach>
	         </tbody>	
	         <tbody id="lineRow3">
			</tbody>
		</table> 
	</div>
				
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="update_go();">수정</a>
				</li>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>
</form>