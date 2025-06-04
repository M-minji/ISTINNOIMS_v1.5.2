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
		$("#mMaintanceDate").val(nowDate());
		
		getCateData(1);
		datepickerIdSet("mRequestDate");
		$("#mRequestDate").val(nowDate());
		datepickerIdSet("mProcessingDate");
	});
	

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
		innerStr += "			<a class='del' onclick=\"delRow(this);\" style='text-decoration:none;'>X</a>";
		innerStr +=	"		</td>";
		innerStr +=	"		<td>";
		innerStr += 			atchFileName;
		innerStr += "			<input type='hidden' id='fileKey'   name='fileKey' value='0' />";
		innerStr += "			<input type='hidden' id='AddFileId"+btnGubun+"' name='eAddFileId' value='"+AddFileId+"' />";
		innerStr += "			<input type='hidden' id='atchFileName"+btnGubun+"' name='atchFileName' value='"+atchFileName+"' />";
		innerStr +=	"		</td>";
		liobj.innerHTML = innerStr;	
		
	} 
	
	var btnGubun = "";
	function mesIMGreg(gubun) { 
		btnGubun = gubun;
		var url = "/mes/maintance/popup/mesIMGregAdd.do";
		window.open(url ,"mesIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
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
	function insert_go(){
		if(chkIns()){
			if(confirm("등록하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_maintance_i.do";
				document.frm.submit();
			}
		}
	}
	
	// validation
	function chkIns(){
		if($("#eMaintanceSelect1").val() == "0"){
			alert("요청기관을 선택하세요.");
			return false;
		}
		
		if($("#mMaintanceIssueType").val() == ""){
			alert("장애구분을 선택하세요.");
			$("#mMainTanceType").focus();
			return false;
		}
		
		if($("#eAssetType").val() == ""){
			alert("자산유형을 선택하세요.");
			$("#eAssetType").focus();
			return false;
		}
		
		if($("#mMaintanceType").val() == ""){
			alert("처리유형을 선택하세요.");
			$("#mMainTanceType").focus();
			return false;
		}
		
		if($("#mRequester").val() == ""){
			alert("요청자를 선택하세요.");
			$("#mRequester").focus();
			return false;
		}
		return true;
	}
	
	function selectName(selectElement, inputId) {
	    var selectedOption = selectElement.options[selectElement.selectedIndex];
	    var selectedValue = selectedOption.getAttribute('data-value2');
	    document.getElementById(inputId).value = selectedValue;
	    if(inputId == "mIssueTypeName"){
	    	IssueTypeView();
	    }
	    
	    
	}
	
	function IssueTypeView() {
		
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
		
 	<input type="hidden" id="mMaintanceStaffKey" name="mMaintanceStaffKey" value="${staffVo.kStaffKey}"/>
	<input type="hidden" id="mMaintanceStaffName" name="mMaintanceStaffName" value="${staffVo.kStaffName}"/>
	<div class="content">
		<div class="content_tit">
			<h2>유지관리 등록</h2>
		</div>
	</div>
	
	<div class="tbl_write2">
        <table>
	        <tbody>
	        	<tr>
<!-- 					<th >작성자</th> -->
<%-- 					<td >${staffVo.kStaffName} --%>
<%-- 						<input type="hidden" id="mAuthor" name="mAuthor"  value="${staffVo.kStaffName}" maxlength="50"/> --%>
<!-- 					</td> -->
					<th >관리번호</th>
					<td >
						*등록시 자동생성
					</td>
					<th >작성일</th>
					<td >
						<input type="text" id="mMaintanceDate" name="mMaintanceDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
						<input type="hidden" id="mAuthor" name="mAuthor"  value="${staffVo.kStaffName}" maxlength="50"/>
					</td>
  				</tr>
	          	<tr>
					<th style="width:15%;">*요청기관</th>
					<td> 
            		 <input type="hidden" id="mMaintanceCateKey" name="eMaintanceCateKey" value="0"/>
						<input type="hidden" id="mMaintanceCateName" name="eMaintanceCateName" value=""/>
						<input type="hidden" id="maintanceSelect0" name="eaintanceSelect0" value="0"/>
						
						<span id="mMaintanceCate1">
							<select id="maintanceSelect1" name="eMaintanceSelect1" style="width:120px;" onChange="getCateData(2)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						>
						<span id="mMaintanceCate2">
						
							<select id="maintanceSelect2" name="eMaintanceSelect2" style="width:120px;" onChange="getCateData(3)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						>
						<span id="mMaintanceCate3">
							<select id="maintanceSelect3" name="eMaintanceSelect3" style="width:120px;" onChange="getCateData(4)">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
						>
						<span id="mMaintanceCate4">
							<select id="maintanceSelect4" name="eMaintanceSelect4" style="width:120px;">
								<option value="0" selected>선택 없음</option>
							</select>
						</span>
            		</td>
            		<th>*장애구분</th>
					<td>
						<input type="hidden" id="mIssueTypeName" name="mIssueTypeName" />
						<select id="mMaintanceIssueType" name="mMaintanceIssueType" style="width:25%;" onchange="selectName(this,'mIssueTypeName')">
<!-- 						<option value="">선택</option> -->
						<c:forEach var="gubun34List" items="${gubun34List}">
							<option value="${gubun34List.sGubunName}" data-value2='${gubun34List.sGubunName}' <c:if test="${gubun34List.sGubunName eq '단순장애'}">selected="selected"</c:if>>${gubun34List.sGubunName}</option>	
													
						</c:forEach>		       				
		     			</select>
					</td>
				</tr>
				<tr>
					<th style="width: 15% !important;">*자산유형</th>
					<td style="width: 35% !important;">
					<input type="hidden" id="eAssetTypeName" name="eAssetTypeName" />
					<select id='eAssetType' name='eAssetType'  onchange="selectName(this,'eAssetTypeName')" style="width:120px;" >
						<option value=''>선택</option>
						<c:forEach var='list' items='${gubun36List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'>${list.sGubunName}</option>						
						</c:forEach>
					</select>
					</td>
					<th>*처리유형</th>
					<td> 
						<input type="hidden" id="mMaintanceTypeName" name="mMaintanceTypeName" />	
						<select id="mMaintanceType" name="mMaintanceType" style="width:25%;" onchange="selectName(this,'mMaintanceTypeName')" >
						<option value="">선택</option>
							<c:forEach var="gubun33List" items="${gubun33List}">
								<option value='${gubun33List.sGubunKey}' data-value2='${gubun33List.sGubunName}' >${gubun33List.sGubunName}</option>							
							</c:forEach>		       				
		     			</select>
					</td>
					
				</tr>
				<tr>
					<th >*요청자</th>
					<td >
						<input type="text" id="mRequester" name="mRequester" style="width:90%; " maxLength="100"/>
					</td>
					<th >처리 담당자</th>
					<td >
						<input type="text" id="mHandler" name="mHandler" style="width:90%; " maxLength="100"/>
					</td>
  				</tr>
 					<tr>
					<th >요청일자</th>
					<td >
						<input type="text" id="mRequestDate" name="mRequestDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly"/>
					</td>
					<th >처리일자</th>
					<td >
						<input type="text" id="mProcessingDate" name="mProcessingDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly"/>
					</td>
  				</tr>
				<tr>
					<th	colspan="4" style="text-align:center;">
						요청내용
					</th>
				</tr>
				<tr>
					<td id="td_editor" colspan="4" align="center" scope="row"> 
						<textarea id="mMaintanceContent" name="mMaintanceContent" cols="100%" rows="20" style="font-size: 20px; width: 100%;" maxLength="1000"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	 
	
	
	
	
<!-- 	<div class="tbl_top"> -->
<!-- 		<ul class="tbl_top_left"> -->
<!-- 			<li style="padding:0"> -->
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