<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){	

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
	
	
	// 등록
	function update_go(){
		if(chkIns()){
			if(confirm("처리 내역을 수정하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_process_u.do";
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
	
	var btnGubun = "";
	function mesIMGreg(gubun) { 
		btnGubun = gubun;
		var url = "/mes/maintance/popup/mesIMGregAdd.do";
		window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	}
	
	var fileIndex = 0;
	function fileAdd(AddFileId, atchFileName){
		
		var ulobj = document.getElementById("lineRow2");
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
	<input type="hidden" id="sSignKey"		name="sSignKey"		value="${maintanceInfo.sSignKey}" />
  	<input type="hidden" id="oSignPass" name="oSignPass" value="${maintanceInfo.oSignPass}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>처리할 요청 정보</h2>
		</div>
	</div>
	
	
	
	<div class="tbl_write">
        <table>
	        <tbody>
	          	<tr>
					<th style="width:15%;">요청기관</th>
					<td style="width:35%; text-align:left; padding-left:5px;">
						${maintanceInfo.mMaintanceCatePath}
					</td>
					<th style="width:15%;">요청자</th>
					<td style="width:35%; text-align:left; padding-left:5px;"> 
						${maintanceInfo.mMaintanceStaffName}
					</td>
				</tr>
				<tr>
					<th>유형</th>
					<td style="text-align:left; padding-left:5px;"> 	
						${maintanceInfo.mMaintanceType}
					</td>
					<th>요청일자</th>
					<td style="text-align:left; padding-left:5px;"> 	
						${maintanceInfo.mMaintanceDate}
					</td>
				</tr>
				<tr>
					<th>모델명</th>
					<td style="text-align:left; padding-left:5px;"> 	
						${maintanceInfo.mMaintanceModel}
					</td>
					<th>SN</th>
					<td style="text-align:left; padding-left:5px;"> 	
						${maintanceInfo.mMaintanceSerial}
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

	
	<div class="tbl_list">          	
		<table>
			<caption style="text-align: left;">
				□ 파일정보
			</caption>
			<thead>  
	            <tr>
	         		<th style="width:5%">순번</th>
	         		<th>파일명</th>
	         	</tr>
	        </thead>
	        <tbody>
				<c:forEach var="file" items="${files}" varStatus="i">
		           	<tr>
		           		<td>
		           			<c:out value="${i.index + 1}" />
		           		</td>
		           		<td>
		           			<a href="javascript:fn_egov_downFile('${file.eAddFileId}','0')">
		           				<c:out value="${file.atchFileName}"/>
		           			</a>
		           		</td>
		           	</tr>
				</c:forEach>
				<c:if test="${empty files}">
					<tr>
						<td colspan="2">내역이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table> 
	</div>
	

	<div class="content" style="padding-top:20px;">
		<div class="content_tit">
			<h2>유지관리 처리내역</h2>
		</div>
	</div>
		
	<div class="tbl_write">
        <table>
	        <tbody>
	          	<tr>
					<th style="width:15%;">*처리구분</th>
					<td style="width:35%;">
						<select id="mProcessType" name="mProcessType">
							<option value="처리완료" <c:if test="${maintanceInfo.mProcessType eq '처리완료'}">selected</c:if>>처리완료</option>
							<option value="처리불가" <c:if test="${maintanceInfo.mProcessType eq '처리불가'}">selected</c:if>>처리불가</option>
						</select>
					</td>
					<th style="width:15%;">*요청자</th>
					<td style="width:35%;">
						${staffVo.kStaffName}
						<input type="hidden" id="mProcessStaffKey" name="mProcessStaffKey" value="${staffVo.kStaffKey}"/>
						<input type="hidden" id="mProcessStaffName" name="mProcessStaffName" value="${staffVo.kStaffName}"/>
					</td>
				</tr>
				<tr>
					<th>*처리내용</th>
					<td> 	
						<input type="text" id="mProcessResult" name="mProcessResult" style="width:90%; text-align:center;" maxLength="100" value="${maintanceInfo.mProcessResult}"/>
					</td>
					<th>*처리일자</th>
					<td>
						<input type="text" id="mProcessDate" name="mProcessDate" style="width:150px; text-align:center;" class="inp_color"  value="${maintanceInfo.mProcessDate}" readonly/>
					</td>
				</tr>
				<tr>
					<th	colspan="4" style="text-align:center;">
						요청내용
					</th>
				</tr>
				<tr> 
					<td id="td_editor" colspan="4" align="center" scope="row"> 
						<textarea id="mProcessContent" name="mProcessContent" cols="100%" rows="20" style="font-size: 20px; width: 100%;">
${maintanceInfo.mProcessContent}
						</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="tbl_top">
		<ul class="tbl_top_left">
			<li>
				<a class="mes_btn" onclick="mesIMGreg(1);">첨부파일 추가</a>
			</li>
		</ul>
	</div>	
	
	<div class="tbl_list">          	
		<table>
			<thead>  
	          	<tr>
	             	<th colspan="2">파일 정보</th>
	            </tr>
	            <tr>
	         		<th style="width:5%">구분</th>
	         		<th>파일명</th>
	         	</tr>
	         </thead>
	         <tbody id="lineRow2">
				<c:forEach var="file" items="${filesProcess}" varStatus="i">
					<tr>
		           		<td>
		           			<a style="cursor: pointer;" class="del" onclick="delRow(this);">X</a>
		           			<input type='hidden' id='AddFileId${i.index }' name='eAddFileId' value='${file.eAddFileId}' />
		           			<input type='hidden' id='atchFileName${i.index }' name='atchFileName' value='${file.atchFileName}' />
		           		</td>
		           		<td>
		           			<a href="javascript:fn_egov_downFile('${file.eAddFileId}','0')">
		           				<c:out value="${file.atchFileName}"/>
		           			</a>
		           		</td>
		           	</tr>
				</c:forEach>
			</tbody>
		</table> 
	</div>
	
	<div style="margin-top:30px;">   
		<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<a onclick="approvalPop();">승인권자 선택</a>
					결재 제외<input type="checkbox" id="oPass" name="oPass" class="checkbox"<c:if test="${maintanceInfo.oSignPass eq 'Y' }">checked="checked"</c:if>/>
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
					<a onclick="update_go();">수정</a>
				</li>
			</c:if>
			<li>
				<a onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>

</form>