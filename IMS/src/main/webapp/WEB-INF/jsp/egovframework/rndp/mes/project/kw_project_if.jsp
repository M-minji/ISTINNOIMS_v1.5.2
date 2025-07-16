<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/js/PretendardGOV-1.3.9/pretendard-gov-all.css">
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<script type="text/javascript">
function modal1(message, focusSelector) {
	lastScrollY = window.scrollY;
	new jBox('Modal', {
	    height: 200,
	    title: message,
	    blockScrollAdjust: ['header'],
	    content:'',
	    overlay: false,   
	    addClass: 'no-content-modal',
	    position: {
	        x: 'center',
	        y: 'top'
	      },
	      offset: {
	        y: 65
	      },
	        onCloseComplete: function () {
	        	if (focusSelector) {
	            	window.scrollTo(0, 0);
	                setTimeout(() => {
	                    document.querySelector(focusSelector)?.focus();
	                }, 10);
	            } else{
	            	window.scrollTo(0, lastScrollY);
	            }
	        }
	  }).open();
  }
function modal3(message, onConfirm) {
	new jBox('Confirm', {
		content: message,
	    cancelButton: '아니요',
	    confirmButton: '네',
	    blockScrollAdjust: ['header'],
	    confirm: onConfirm
	  }).open();
  }
function setToolTip(){
		var elements = document.getElementsByName("sSignStaffKey");
		var checkbox = $('#oPass');
		if (checkbox.prop('checked')) {
		} else if(elements.length > 0){
			$("#approvalWrap").addClass("hoverToolTip");
				window.hoverTipBox = new jBox('Tooltip', {
		    attach: '.hoverToolTip',
		    theme: 'TooltipDark',
		    animation: 'zoomOut',
		    content: '선택된 결재선이 삭제됩니다.',
		    adjustDistance: {
		      top: 70,
		      right: 5,
		      bottom: 5,
		      left: 5
		    },
		    zIndex: 90
		  }); 
		}
	}
	function removeToolTip() {
		if (window.hoverTipBox) {
			$('.jBox-wrapper').remove();
			$('.jBox-tooltip').remove();
			$('#approvalWrap').removeClass('hoverToolTip');
			window.hoverTipBox = null;
		}
 }

$(document).ready(function(){	
	datepickerSet('eStartDate', 'eEndDate');
	datepickerIdSet('pWdate');
	const today = nowDate();
	$('#eCreationDateDisplay').text(today);
	$("#eStartDate").val(today);
	$("#eEndDate").val(today);
	$("#pWdate").val(today);
	$('#oPass').prop('checked', true);
	$("#oSignPass").val("Y");
});

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
	$("#eStartDate").val("");
	$("#eEndDate").val("");
	document.frm.action = "/mes/project/kw_project_lf.do";
	document.frm.submit(); 
}

function insert_go(){
	if(chkIns()){  
		modal3("등록하시겠습니까?", function () {
			$("#mloader").show();
			sessionStorage.setItem("actionType", "create");
			document.frm.action = "/mes/project/kw_project_i.do";
  	        document.frm.submit();
		});
	}
}

//validation
function chkIns(){
	if($("#eProjectName").val() == ""){
		modal1("사업명을 입력하세요.", "#eProjectName");
		return false;
	}

	if($("#eManager").val() == ""){
		modal1("담당자를 입력하세요.", "#eManager");
		return false;
	}
	
	if($("#oSignPass").val() != 'Y'){
		if(document.getElementsByName("sSignStaffKey").length == 0){
			modal1("결재자를 선택하세요.");
			return false;
			}
		}
	
	$("#eRemarks").val($("<div>").text($("#eRemarks").val()).html());
	return true;
}
 

//행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();
	
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
	innerStr += "			<input type='text' id='pProjectItemEtc_"+btnGubun+"' name='pProjectItemEtc' maxlength='100' value=''/>";
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


function approvalPop(){
	
	 var checkbox = $('#oPass');
   if (checkbox.prop('checked')) {
   		checkbox.prop('checked', false);
   		$("#oSignPass").val("N");
  
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
		setToolTip();
	}
	
	function deleteGyeoljaeList(){
		$('#lineRow3').empty();
	}
	
	function delRowTwo(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}

</script>
<style>
	.no-content-modal .jBox-content {
  		display: none; 
	}
	.no-content-modal .jBox-title {
		padding-bottom: 10px;
	}
	.no-content-modal .jBox-title {
	  	color: white;
	 	font-weight: 400;  
	    font-family: 'Pretendard GOV', sans-serif;
	}
	.jBox-Modal {
	  background: #4869fb !important;
	  border-radius: 8px !important;
   	  overflow: hidden !important;
}    
</style>
<form id="frm" name="frm" method="post" enctype="multipart/form-data">
  	<input type="hidden" id="oSignPass" name="oSignPass" value="" />
	<div class="content_top">
		<div class="content_tit">
			<h2>프로젝트  등록</h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
            		<th>작성자</th>
            		<td>${staffVo.kStaffName}
						<input type="hidden" name="eAuthor" id="eAuthor" style="width:35%; text-align:left;"   value="${staffVo.kStaffName}" class="inp_color"/>
            		</td>
            		<th>등록일</th>
            		<td>
						<input type="hidden" id="pWdate" name="pWdate" style="width:150px; text-align:center;" class="inp_color" readonly="readonly" />
						<span id="eCreationDateDisplay"></span>
            		</td>
          		</tr>			
			</tbody>
		</table>
	</div>
	<div class="normal_table row">
			<table>
				<tbody>
  				<tr>
	  				<th><span style="color: red">* </span>사업명</th>
					<td>
						<input type="text" id="eProjectName" name="eProjectName" style="width:100%; text-align: left;padding-left: 5px;"  value="" maxlength="30"/>
					</td>
					<th>사업기간</th>
					<td>
						<input type="text" class="inp_color" id="eStartDate" name="eStartDate" style="width:100px;text-align: center;" value="" readonly /> -
						<input type="text" class="inp_color" id="eEndDate" name="eEndDate" style="width:100px;text-align: center;" value="" readonly />
					</td>
					<th>주사업자</th>
					<td>
						<input type="text" id="eMainContractor" name="eMainContractor" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
				</tr>
				<tr>
					<th><span style="color: red">* </span>담당자</th>
					<td >
						<div style="display: flex; align-items: center; gap: 10px;">
							<input type="text" id="eManager" name="eManager" style="flex: 1; min-width: 0; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
							<a class="form_btn bg" onclick="selectWorkerPop('R', 'eManager')" style="margin-left: auto;">담당자 선택</a>
						</div>
					</td>
					<th>담당자 연락처</th>
					<td>
						<input type="text" id="eManagerHP" name="eManagerHP" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
						<span id="eManagerHPTxt" style="display: none;"></span>
					</td>
					<th>담당자 이메일</th>
					<td>
						<input type="text" id="eManagerMail" name="eManagerMail" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
						<span id="eManagerMailTxt" style="display: none;"></span>
					</td>
				</tr>
				<tr>
					<th>사업 PM</th>
					<td>
	 					<input type="text" id="eProjectPM" name="eProjectPM" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>PM 연락처</th>
					<td>
						<input type="text" id="ePMContact" name="ePMContact" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
					<th>PM 이메일</th>
					<td>
						<input type="text" id="ePMEmail" name="ePMEmail" style="width:100%; text-align: left;padding-left: 5px;"  maxlength="30"  value=""  />
					</td>
				</tr>
				<tr>
					<th>비고</th>
           			<td id="td_editor" colspan="5" align="center" scope="row"> 
						<textarea id="eRemarks" name="eRemarks" cols="100%" rows="20" style="font-size: 20px; width: 100%; height: 300px; resize: none;" ></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="content_top nofirst with_btn">
		<div class="content_tit flex">
			<h2>결재 정보</h2>
			<div id="approvalWrap">
			<label for="oPass" class="inp_chkbox">
				<input type="checkbox" id="oPass" name="oPass" class="checkbox" onclick="handleOPassClick();" onchange="removeToolTip();"/>
				<i></i>
				결재 제외
			</label></div>
		</div>
		<div class="btns">
			 <button type="button" onclick="approvalPop()" class="form_btn md">결재선 선택</button>
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
					<th>결재순서</th>
					<th>결재구분</th>
					<th>결재자</th>
				</tr>
			</thead>
			<tbody id="lineRow3">		
				<tr>
					<td colspan="3">결재 정보가 없습니다.</td>
				</tr>	
			</tbody>
		</table>
	</div>
	  
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		<button type="button" class="form_btn" onclick="cancel();">취소</button>
	</div>
</form>