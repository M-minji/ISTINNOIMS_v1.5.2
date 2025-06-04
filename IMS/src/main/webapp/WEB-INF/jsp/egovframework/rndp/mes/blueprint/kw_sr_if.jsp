<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


<script type="text/javascript">

$(document).ready(function(){
	datepickerIdSet("eReqDate");
	datepickerIdSet("blueprintWdate");
	const today = nowDate();
	$('#blueprintWdateDisplay').text(today);
	$("#eReqDate").val(today);
	$("#blueprintWdate").val(today);
	$('#oPass').prop('checked', true);
	$("#oSignPass").val("Y");
});

	// 첨부파일 추가
	function addFile(){
		  var url = "/mes/blueprint/popup/kw_sr_File_add.do";
		  const form = document.createElement("form");
		    form.method = "POST";
		    form.action = url;
		    form.target = "ePDFAdd"; // 새 창 이름
		    
		    const csrfTokenGubun = document.createElement("input");
		    csrfTokenGubun.type = "hidden";
		    csrfTokenGubun.name = "csrfToken";
		    csrfTokenGubun.value = $("input[name=csrfToken]").val();
		    form.appendChild(csrfTokenGubun);
			
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
			innerStr += "		<td class='tac'>";
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
		        messageCell.textContent = "첨부파일을 선택하세요.";
		        
		        
		        messageRow.appendChild(messageCell);
		        tbody.appendChild(messageRow);
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
	$("#mloader").show();
	document.frm.action = "/mes/blueprint/kw_sr_lf.do";
	document.frm.submit(); 
}
// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();
}



// 등록
function insert_go(){
	if(chkIns()){
		if(confirm("저장하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/blueprint/kw_sr_i.do";
			document.frm.submit();
		}
	}
}

// validation
function chkIns(){
	
	if($("#eRequester").val() == ""){
		alert("요청자  명을 입력하세요.");
		$("#eRequester").focus();
		return false;
	}
	 
	if($("#oSignPass").val() != 'Y'){
		if(document.getElementsByName("sSignStaffKey").length == 0){
			alert("승인권자를 선택해주세요");
			return false;
		}
	}
	
	$("#eReqContent").val($("<div>").text($("#eReqContent").val()).html()); 
	$("#eWorkStart").val($("<div>").text($("#eWorkStart").val()).html());
	$("#eWorkEnd").val($("<div>").text($("#eWorkEnd").val()).html());
	return true;
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
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#preImage').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#preImage').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function setImgregAdd(imgId) {
	$('#imgRow').empty();
 	$('#eIMGregName').val(imgId);
	var innerStr = "";
	innerStr += "<tr id='imgTR'>";
	innerStr += "	<td  id='imgTD' style='border: none;''>";
	innerStr += "		<img height='150;'  width='300'  src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId="+imgId+"&fileSn=0'/>";
	innerStr += "	</td>"; 
	innerStr += "</tr>";	
	var row =innerStr;
	$(row).appendTo("#imgRow");	 
}

//파일 첨부
var fileIndex = 0;
function fileAdd(AddFileId, atchFileName){
	
	var ulobj = document.getElementById("fileUL");
	var liobj = document.createElement('tr');
	var idx = ulobj.childNodes.length;
	
	fileIndex++;
	
	liobj.id = "filename_" + fileIndex;
	liobj.style.padding = "0";
	ulobj.appendChild(liobj);
	
	var innerStr = "";
	innerStr +=	"		<td>";
	innerStr += "			<a onclick=\"fileDel('filename_" + fileIndex+"');\" style='text-decoration:none;'>X</a>";
	innerStr +=	"		</td>";
	innerStr +=	"		<td>";
	innerStr += 			atchFileName;
	innerStr += "			<input type='hidden' id='fileKey'   name='fileKey' value='0' />";
	innerStr += "			<input type='hidden' id='AddFileId' name='eAddFileId"+btnGubun+"' value='"+AddFileId+"' />";
	innerStr += "			<input type='hidden' id='atchFileName' name='atchFileName"+btnGubun+"' value='"+atchFileName+"' />";
	innerStr +=	"		</td>";
	innerStr +=	"		<td>";
	innerStr += "			<input type='text' id='eBlueprintItemEtc_"+btnGubun+"' name='eBlueprintItemEtc' maxlength='100' value=''/>";
	innerStr +=	"		</td>";
	liobj.innerHTML = innerStr;	
	
} 


function fileDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));	
}

var btnGubun = "";
function mesIMGreg(gubun) { 
	btnGubun = gubun;
	var url = "/mes/blueprint/popup/mesIMGregAdd.do";
	window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
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
	
	function delRowTwo(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}


</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/blueprint/kw_sr_i.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesBlueprintVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesBlueprintVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />
	<input type="hidden" id="blueprintNumber" name="blueprintNumber" value="" />
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>SR 정보 등록</h2>
		</div>
	</div>
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${staffVo.kStaffName}
					</td>
					<th >등록일</th>
					<td >
						<input type="hidden" id="blueprintWdate" name="blueprintWdate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
						<span id="blueprintWdateDisplay"></span>
					</td>
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
					<th>요청일자</th>
					<td colspan="3">
						<input type="text" id="eReqDate" name="eReqDate" style="width:150px; text-align:center;" class="inp_color" readonly   />
					</td>
	  				 
				</tr>
  				<tr>
					 
	  				<th><span style="color: red">* </span>요청자</th>
					<td>
						<input type="text" id="eRequester" name="eRequester" style="width:70%;" maxLength="50" />
						<a class="form_btn bg" onclick="selectWorkerPop('R', 'eRequester')" >담당자 선택</a>
						<input type="hidden" name="eReqOrg" id="eReqOrg" style="width:95%; text-align:left;" maxLength="50" value="" />
						<input type="hidden" name="eReqDept" id="eReqDept" style="width:95%; text-align:left;" maxLength="50" value="" />
					</td>
					<th >요청자 소속</th>
					<td >
						<input type="text" id="eRequesterOrg" name="eRequesterOrg" style="width:95%;" maxLength="50"/>
						<span id="eRequesterOrgTxt" style="display: none;"></span>
					</td>
				</tr>
  			
  				
				<tr>
					<th>
						요청내용
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
						<textarea id="eReqContent" name="eReqContent" cols="100%" rows="10" style="font-size: 20px; width: 100%; height: 300px; resize: none;" maxLength="1000"></textarea>
					</td>
				</tr>
				<tr>
	  				<th>요청사유</th>
					<td>
						<input type="text" name="eIssueCause" id="eIssueCause" style="width:95%; text-align:left;" maxLength="50" value="" />
					</td>
					<th>해결방법</th>
					<td>
						<input type="text" name="eSolutionMethod" id="eSolutionMethod" style="width:95%; text-align:left;" maxLength="50" value="" />
					</td>
				</tr>
  				<tr>
	  				<th>처리시작일시</th>
					<td>
						<input type="text" name="eWorkStart" id="eWorkStart" style="width:95%; text-align:left;" maxLength="50" value="" />
					</td>
					<th>처리완료일시</th>
					<td>
						<input type="text" name="eWorkEnd" id="eWorkEnd" style="width:95%; text-align:left;" maxLength="50" value="" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="normal_table row">
		<table>
			<colgroup>
				<col style="width: 200px;"/>
				<col />
			</colgroup>
<%-- 			<caption style="text-align: left; margin-bottom:10px;"> --%>
<!-- 				  style="float:right" -->
<%-- 			</caption> --%>
			<thead>
				<tr>
					<th colspan="2">첨부파일  <a class="form_btn md" onclick="addFile()" >파일 선택</a></th>
				</tr>
				<tr>
					<th style="width: 200px;">구분</th>
					<th>파일명</th>
				</tr>
			</thead>
			<tbody id="fileRow">
				<tr>
					<td colspan="2" class="tac">첨부파일 선택하세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
 
	
	<div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>장비 정보</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="sel_asset()">장비 선택</button>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
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
					<td colspan="9">장비를 선택하세요.</td>
				</tr>
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
		<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>
</form>