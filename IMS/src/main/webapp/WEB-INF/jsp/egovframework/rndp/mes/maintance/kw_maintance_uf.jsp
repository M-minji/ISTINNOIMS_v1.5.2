<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<!-- <script type="text/javascript" src="/se/js/ckeditor/ckeditor.js"></script> -->

<script type="text/javascript">
	$(document).ready(function(){	
		datepickerSet("mMaintanceDate");
		
	});
	
	function getCateData(depth){
		if(depth != 4){
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
		}

		
		for(var i=2; i>=0; i--){
			if(document.getElementById("maintanceSelect"+i).value != 0){
				$("#mMaintanceCateKey").val(document.getElementById("maintanceSelect"+i).value);
				$("#mMaintanceCateName").val(document.getElementById("maintanceSelect"+i).options[document.getElementById("maintanceSelect"+i).selectedIndex].textContent);
				
				return 0;
			}
		}
	}
	
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
			if(confirm("수정하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_maintance_u.do";
				document.frm.submit();
			}
		}
	}
	
	// validation
	function chkIns(){
		if($("#mMaintanceCateKey").val() == "0"){
			alert("요청기관을 선택하세요.");
			return false;
		}
		if($("#mMainTanceType").val() == ""){
			alert("유형을 선택하세요.");
			$("#mMainTanceType").focus();
			return false;
		}
		if($("#mMaintanceModel").val() == ""){
			alert("모델명을 입력하세요.");
			$("#mMaintanceModel").focus();
			return false;
		}
		if($("#mMaintanceSerial").val() == ""){
			alert("시리얼넘버(SN)를 입력하세요.");
			$("#mMaintanceSerial").focus();
			return false;
		}
		
		return true;
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
	<input type="hidden" id="mMaintanceStaffKey" name="mMaintanceStaffKey" value="${maintanceInfo.kStaffKey}"/>
	<input type="hidden" id="mMaintanceStaffName" name="mMaintanceStaffName" value="${maintanceInfo.kStaffName}"/>
	<div class="content">
		<div class="content_tit">
			<h2>유지 내역 수정</h2>
		</div>
	</div>
	
	<div class="tbl_write">
        <table>
	        <tbody>
	        	  <tr>
	        	<th style="width:15%;">*관리번호</th>
				<td style="width:35%;">${maintanceInfo.mMaintanceNumber}
					<input type="hidden" id="mMaintanceNumber" name="mMaintanceNumber" style="width:90%; text-align:left;" maxlength="30" value="${maintanceInfo.mMaintanceNumber}" />	
				</td>
	        	<th>*작성일자</th>
					<td>
						<input type="text" id="mMaintanceDate" name="mMaintanceDate" style="width:150px; text-align:center;" class="inp_color" readonly value="${maintanceInfo.mMaintanceDate}" />
					</td>
	        </tr>
	          	<tr>
					<th style="width:15%;">*요청기관</th>
					<td style="width:35%;" style="text-align:left; padding-left:5px;">
						<input type="hidden" id="mMaintanceCateKey" name="mMaintanceCateKey" value="${maintanceInfo.mMaintanceCateKey}"/>
						<input type="hidden" id="mMaintanceCateName" name="mMaintanceCateName" value="${maintanceInfo.mMaintanceCateName}"/>
						${maintanceInfo.mMaintanceCatePath}
					</td>
					<th>*장애구분</th>
					<td>
						<select id="mMaintanceIssueType" name="mMaintanceIssueType" style="width:25%;">
						<option value="">선택</option>
						<c:forEach var="gubun34List" items="${gubun34List}">
							<option value="${gubun34List.sGubunName}" 	<c:if test="${maintanceInfo.mMaintanceIssueType eq gubun34List.sGubunName}">selected="selected" </c:if>	 >${gubun34List.sGubunName}</option>
												
						</c:forEach>		       				
		     			</select>
					</td>
				</tr>
				<tr>
					<th>*처리유형</th>
					<td> 	
	     				<select id="mMaintanceType" name="mMaintanceType" style="width:25%;" >
							<option value="">선택</option>
							<c:forEach var="gubun33List" items="${gubun33List}">
								<option value="${gubun33List.sGubunName}" <c:if test="${maintanceInfo.mMaintanceType eq gubun33List.sGubunName}">selected="selected" </c:if> >${gubun33List.sGubunName}</option>							
							</c:forEach>		       				
		     			</select>
					</td>
					<th style="width:15%;">*요청자</th>
					<td style="width:35%;">
						<input type="text" id="mMaintanceRequester" name="mMaintanceRequester" value="${maintanceInfo.mMaintanceRequester}"  style="width:90%; " maxLength="100"/> 
					</td>
					
				</tr>
				<tr>
					<th>*모델명</th>
					<td> 	
						<input type="text" id="mMaintanceModel" name="mMaintanceModel" style="width:90%; text-align:center;" maxLength="100" value="${maintanceInfo.mMaintanceModel}"/>		
					</td>
					<th>*SN</th>
					<td>
						<input type="text" id="mMaintanceSerial" name="mMaintanceSerial" style="width:90%; text-align:center;" maxLength="100" value="${maintanceInfo.mMaintanceSerial}"/>
					</td>
				</tr>
				<tr>
					<th	colspan="4" style="text-align:center;">
						요청내용
					</th>
				</tr>
				<tr><td id="td_editor" colspan="4" align="center" scope="row"> 
						<textarea id="mMaintanceContent" name="mMaintanceContent" cols="100%" rows="20" style="font-size: 20px; width: 100%;">
${maintanceInfo.mMaintanceContent}
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
				<c:forEach var="file" items="${files}" varStatus="i">
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