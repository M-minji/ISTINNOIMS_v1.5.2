<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>

<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>

<script type="text/javascript">
	$(document).ready(function(){	
		settingSign();
	});
	
	// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}
	
	// 목록
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/maintance/kw_maintance_lf.do";
		document.frm.submit();
	}
	
	// 삭제
	function delete_go(status){
		if(status == "요청등록"){
			if(confirm("요청사항을 삭제하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_maintance_d.do";
				document.frm.submit();
			}
		}else{
			if(confirm("처리현황을 삭제하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/maintance/kw_process_d.do";
				document.frm.submit();
			}
		}
	}
	
	// 결재
	function startApproval(mMaintanceKey, sSignKey){
		$("#mloader").show();
		$("#mMaintanceKey").val(mMaintanceKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/maintance/kw_maintance_rv.do";
		document.frm.submit();
	}
	
	// 수정
	function update_go(status){
		if(status == "요청등록"){
			$("#mloader").show();
			document.frm.action = "/mes/maintance/kw_maintance_uf.do";
			document.frm.submit();
		}else{
			$("#mloader").show();
			document.frm.action = "/mes/maintance/kw_process_uf.do";
			document.frm.submit();
		}
	}
	
	// 처리내역 등록 
	function process_go(){
		$("#mloader").show();
		document.frm.action = "/mes/maintance/kw_process_if.do";
		document.frm.submit();
	}
	


	function settingSign(){
		/* 사인 ============================= */
		var canvas = $("#signature")[0];
		var signature = new SignaturePad(canvas, {
			minWidth : 2,
			maxWidth : 2,
			penColor : "rgb(0, 0, 0)"
		});
		
		$("#save").on("click", function() {
			if(signature.isEmpty()) {
				alert("내용이 없습니다.");
			} else {
				
				//저장버튼시 부서, 날짜, 서명을 저장한다.
				var data = signature.toDataURL("image/png");
				var sSignKey = $("#sSignKey").val();
				var kStaffKey = $("#kStaffKey").val();
				
				$.ajax({
					type : "post"
				,	url : "/mes/sign/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignKey"			: sSignKey
					,	"sSignStaffKey"		: kStaffKey
					,	"sSignDecison"		: "승인"
					,	"sSignContent"		: data	
					}
				,	dataType : "json"
				,	async : false
				,	cache : false
				,	success : function(msg){
						innerStr = "";
						innerStr += "<img style='width:150px; height:100px;' onclick='setSignHtml(this,"+signId+");' src='"+data+"'/>";
						innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'>"+data+"</textarea>";
						
						saveObj.parentNode.innerHTML = innerStr;
						
						$('#modal-close').get(0).click();
					}
				});
			}
		});
		
	}

	/*SIGN PAD*/
	var scrollPosition = 0;
	function setSign(obj, eventTmp, signIdTmp){
			
		if($("#eProreqAppDate"+signIdTmp).val() == ""){
			if(confirm("서명일자를 오늘로 설정하시겠습니까?")){
				$("#eProreqAppDate"+signIdTmp).val(nowDate());
			}else{
				return false;
			}
			
		}
		
		event.preventDefault();
		scrollPosition = window.scrollY;
		document.body.style.overflow = "hidden";
		$("#setModal").modal({
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	    });
		saveObj = obj;
		signId = signIdTmp;
		
		var canvas = $("#signature")[0];
	  	var cnvs = document.getElementById('signature');
		// context
	    var ctx = canvas.getContext('2d');
	    // 픽셀 정리
	    ctx.clearRect(0, 0, cnvs.width, cnvs.height);
	    // 컨텍스트 리셋
	    ctx.beginPath();
	}

	function closeModal(){
		document.body.style.overflow = "auto";
		setTimeout(function() {
			window.scrollTo(0, scrollPosition);
		}, 50);
	}

	function setSignHtml(obj){
		innerStr = "";
		innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		obj.parentNode.innerHTML = innerStr;
	}



//	화면 캡처 후 인쇄로 넘어가기
	function capture() { 
		html2canvas($("#print_div"), {
			onrendered: function(canvas) { 
				var img = canvas.toDataURL("image/png");
				console.log(img); 
//					window.open(img); // 이미지를 윈도우 팝업으로..
				win = window.open();
				self.focus(); 
				win.document.open();
		  		win.document.write('<html><head><title></title>');
		  		win.document.write('</haed><body><table><tr><td>');
		  		win.document.write('<img  width="95%" src=' + img + '>');
		  		win.document.write('</td></tr></table></body></html>');
				win.document.close();
	  			setTimeout(function(){
	  			    win.print();
	  			    win.close();
	  			    }, 5);

			}
		}); 
	} 	

	function printPop(setKey){
		var url = "/mes/proreq/popup/kw_proreq_vf_popup.do";
		url += "?eProreqKey="+setKey;
		window.open(url ,"addrAdd" ,"width=1200,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
		
	}


	function excelDown(){
		//alert("다운로드된 파일은 'C:/salesDownload/'에 저장 됩니다.");
	    var f = document.frm; 
	    f.method = "post";
	    f.action = "/mes/proreq/excelDown.do";
	    f.submit();	
	}
	
	function changeContent(value){
		var innerStr = "";
		
		if(value == "승인"){
			innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
			innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		} else if(value == "반려"){
			innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='100'/>";
		}
		document.getElementById("sSignContentSet").innerHTML = innerStr;
	}
	
	function setApproval(){
		if(document.getElementById("sSignContent") == null){
			alert("승인 후 싸인이 필요합니다");
			return false;
		}
		if(document.getElementById("sSignContent").value == ""){
			alert("반려사유가 입력되지 않았습니다 ");
			document.getElementById("sSignContent").focus();
			return false;
		}
		$("#mloader").show();
		document.frm.action = "/mes/maintance/kw_maintance_s.do";
		document.frm.submit();
	}

</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/maintance/kw_maintance_i.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesMaintanceVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesMaintanceVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesMaintanceVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesMaintanceVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesMaintanceVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesMaintanceVO.searchType}" />
	
	<input type="hidden" id="mMaintanceKey" name="mMaintanceKey" value="${maintanceInfo.mMaintanceKey}" />
	<input type="hidden" id="sSignKey" name="sSignKey" value="${maintanceInfo.sSignKey}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVo.kStaffKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>유지관리 상세</h2>
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
<!-- 						<input type="hidden" id="mIssueTypeName" name="mIssueTypeName" /> -->
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
<!-- 				□ 파일정보  -->
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
<!-- 						<td colspan="2">등록 정보가 없습니다.</td> -->
<!-- 					</tr> -->
<%-- 				</c:if> --%>
<!-- 			</tbody> -->
<!-- 		</table>  -->
<!-- 	</div> -->
	
	<c:if test="${maintanceInfo.sSignStatus ne '요청등록'}">
		<div class="content" style="padding-top:20px;">
			<div class="content_tit">
				<h2>유지관리 처리내역</h2>
			</div>
		</div>
		
		<div class="tbl_write">
	        <table>
		        <tbody>
		          	<tr>
						<th style="width:15%;">요청기관</th>
						<td style="width:35%; text-align:left; padding-left:5px;">
							${maintanceInfo.mProcessType}
						</td>
						<th style="width:15%;">처리자</th>
						<td style="width:35%; text-mProcessStaffName; padding-left:5px;"> 
							${maintanceInfo.mProcessStaffName}
						</td>
					</tr>
					<tr>
						<th>처리결과</th>
						<td style="text-align:left; padding-left:5px;"> 	
							${maintanceInfo.mProcessResult}
						</td>
						<th>완료일자</th>
						<td style="text-align:left; padding-left:5px;"> 	
							${maintanceInfo.mProcessDate}
						</td>
					</tr>
					<tr>
						<th	colspan="4" style="text-align:center;">
							처리내용
						</th>
					</tr>
					<tr>
						<td id="td_editor" colspan="4" align="center" scope="row"> 
							${maintanceInfo.mProcessContent}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		
<!-- 		<div class="tbl_list">          	 -->
<!-- 			<table> -->
<%-- 				<caption style="text-align: left;"> --%>
<!-- 					□ 파일정보   -->
<%-- 				</caption> --%>
<!-- 				<thead>   -->
<!-- 		            <tr> -->
<!-- 		         		<th style="width:5%">순번</th> -->
<!-- 		         		<th>파일명</th> -->
<!-- 		         	</tr> -->
<!-- 		        </thead> -->
<!-- 		        <tbody> -->
<%-- 					<c:forEach var="file" items="${filesProcess}" varStatus="i"> --%>
<!-- 			           	<tr> -->
<!-- 			           		<td> -->
<%-- 			           			<c:out value="${i.index + 1}" /> --%>
<!-- 			           		</td> -->
<!-- 			           		<td> -->
<%-- 			           			<a href="javascript:fn_egov_downFile('${file.eAddFileId}','0')"> --%>
<%-- 			           				<c:out value="${file.atchFileName}"/> --%>
<!-- 			           			</a> -->
<!-- 			           		</td> -->
<!-- 			           	</tr> -->
<%-- 					</c:forEach> --%>
<%-- 					<c:if test="${empty filesProcess}"> --%>
<!-- 						<tr> -->
<!-- 							<td colspan="2">등록 정보가 없습니다.</td> -->
<!-- 						</tr> -->
<%-- 					</c:if> --%>
<!-- 				</tbody> -->
<!-- 			</table>  -->
<!-- 		</div> -->
	</c:if>
	
	
	
	<c:if test="${maintanceInfo.sSignStatus ne '요청등록'}">
		<div class="content" style="padding-top:20px;">
			<div class="content_tit">
				<h2>결재현황</h2>
			</div>
		</div>
		
		<div class="tbl_write">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%; border-left: 1px solid #bfdaf7;">결재순서</th>
						<th style="width:10%;">결재자</th>
						<th style="width:10%;">결정</th>
						<th style="width:10%;">결재여부</th>
						<th style="width:60%;">반려사유 및 싸인</th>
					</tr>
	        	</thead>
		        <tbody>
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVo.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:left; padding-left:5px; width:10%;">
								${signList.sSignStaffName}
							</td>
							<td style="text-align:center; width:10%;">
								${signList.sSignDecison}
							</td>
							<td>
								<c:if test="${maintanceInfo.sSignStatus eq '승인요청' && maintanceInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
								</c:if>
							</td>
							<td <c:if test="${maintanceInfo.sSignStatus eq '승인요청' && maintanceInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${maintanceInfo.sSignStatus eq '승인요청' && maintanceInfo.sSignStaffKey eq staffVo.kStaffKey && signList.sSignStaffKey eq staffVo.kStaffKey}">
						        		<a class="mes_btn" onclick="setSign(this, event);">사인</a>
								</c:if>
								<c:if test="${signList.sSignDecison eq '승인'}">
									<img src="${signList.sSignContent}"/>
								</c:if>
								<c:if test="${signList.sSignDecison eq '반려'}">
									${signList.sSignContent}
								</c:if>
							</td>
							
						</tr>
					</c:forEach>
					<c:if test="${empty signList}">
						<tr>
							<td colspan="6" style="text-align: center;">등록 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</c:if>
	
	
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${maintanceInfo.sSignStatus eq '승인요청' && maintanceInfo.sSignStaffKey eq staffVo.kStaffKey}">
				<li>
					<a style="cursor: pointer;" onclick="setApproval();">결재처리</a>
				</li>
			</c:if>
			<c:if test="${maintanceInfo.sSignStatus ne '승인요청' && maintanceInfo.sSignStatus ne '승인'}">
<%-- 				<c:if test="${maintanceInfo.sSignStatus eq '처리등록'}"> --%>
<!-- 					<li> -->
<%-- 						<a style="cursor: pointer;" onclick="startApproval('${maintanceInfo.sSignStatus}');">결재요청</a> --%>
<!-- 					</li> -->
<%-- 				</c:if> --%>
				<c:if test="${maintanceInfo.sSignStatus eq '요청등록'}">
					<li>
						<a style="cursor: pointer;" onclick="process_go();">처리내역 등록</a>
					</li>
				</c:if>
				<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
					<li>
						<a style="cursor: pointer;" onclick="update_go('${maintanceInfo.sSignStatus}');">수정</a>
					</li>
				</c:if>
				<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
					<li>
						<a style="cursor: pointer;" onclick="delete_go('${maintanceInfo.sSignStatus}');">삭제</a>
					</li>
				</c:if>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>


	<div id="setModal" class="modal" style="display:none;">
		<a id="modal-close" href="#close-modal" rel="modal:close" class="close-modal " onclick="closeModal()">Close</a>
		<div class="modal-content">
			<div class="tbl_top" id="modalAddRow">
				<ul class="tbl_top_right">
					<li>
		          		<a class="mes_btn" id="save">저장</a>
			   		</li>
				</ul>
			</div>
			<div class="lf_tbl_list scrolltbody" style="margin-top: 0px; border: 0.5px #d1d1d1 solid; border-radius:5px;">
				<canvas id="signature" width="450" height="200"></canvas>
			</div>
		</div>
	</div>	
</form>