<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<!-- <script type="text/javascript" src="/se/js/ckeditor/ckeditor.js"></script> -->

<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>
<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){	
		toggleViewProcess();
// 		datepickerIdSet("eActualWorkDate");
		settingSign();
		$("#process_go").on("click", function() {
		    process_go();
		  });
	});
	
	 function toggleViewProcess() {
         var ePageGubun = $('#ePageGubun').val();
         var eIssueStatus = $('#eIssueStatus').val();
         if (ePageGubun == 'Y') {
        	 process_go();
         } else {
        	
        	 if(eIssueStatus == '처리등록') {
            	 $('#viewProcess').css('visibility', 'visible');
            	 $('#viewProcess').css('position', 'static');
             }else{
            	 $('#viewProcess').css('visibility', 'hidden');
            	 $('#viewProcess').css('position', 'absolute');
             }
         }
         
     }
	 
	 // 상세보기 엑셀 다운
	 function eExcelDownload(){
		    document.frm.action = "/mes/issue/kw_issue_download.do"; //예시 호출 mapping값
		    document.frm.submit();   //action값으로 전송 요청
		    document.frm.action = "/mes/issue/kw_issue_vf.do";//동작 후 action 값을 현황 페이지 주소로 재변경
		}
	 
	 
		// 처리내역 등록 
		function process_go(){
			
			  var eIssueStatus = $('#eIssueStatus').val();
			 $('#assetAddBtn').css('visibility', 'visible');
			 $('.del').css('visibility', 'visible');
			 
			 $('#viewProcess').css('visibility', 'visible');
			 $('#viewProcess').css('position', 'static');
			 $('#ePageGubun').val("Y");
			 var $link = $('#process_go');
			 $link.attr('onclick', 'processUpdate();'); // 새로운 함수로 변경
             $link.text('처리정보 저장'); // 텍스트 변경
             
		}
		// 처리내역 등록 
		
		
		
		
		function processUpdate(){
			
// 			if($("#eActualWorkDate").val() == ""){
// 				alert("처리일자를 선택하세요.");
// 				$("#eActualWorkDate").focus();
// 				return false;
// 			}
			
// 			if($("#eActualWorker").val() == ""){
// 				alert("실 처리자 입력하세요.");
// 				$("#eActualWorker").focus();
// 				return false;
// 			}
			
			$("#eActualDetails").val($("<div>").text($("#eActualDetails").val()).html());
 			$("#mloader").show();
 			document.frm.action = "/mes/issue/kw_process_uf.do";
 			document.frm.submit();
		}
	 
	// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}
	
	// 목록
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/issue/kw_issue_lf.do";
		document.frm.submit();
	}
	
	// 삭제
	function delete_go(){
		var status = $("#eIssueStatus").val();
			if(confirm("해당 등록정보를 삭제하시겠습니까?")){
				$("#mloader").show();
				document.frm.action = "/mes/issue/kw_issue_d.do";
				document.frm.submit();
			}
	 
	}
	
	// 결재
	function startApproval(sSignStatus){
		$("#mloader").show();
		$("#sSignStatus").val(sSignStatus)
		document.frm.action = "/mes/issue/kw_issue_vfr.do";
		document.frm.submit();
	}
	
	// 수정
	function update_go(){
		var status =  $("#eIssueStatus").val();
			$("#mloader").show();
			document.frm.action = "/mes/issue/kw_issue_uf.do";
			document.frm.submit();
	 
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
		}else{
			if(row_Index == 0){  
				row_Index = eAssetKeyArr;
			}
			
			for(var i=0; i < eAssetKeyArr ; i++){ 
					if(obj.aAssetKey == document.getElementsByName("eAssetKey")[i].value){
						return false;
					}
			}
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
	
	//행 삭제
	function delRow(obj){
		var tr = $(obj).parent().parent();
		tr.remove();
	}
	
	function clearTxt(objId) {
        $("#"+objId).val("");
    }
	

	
	/* 결재 기능처리  */
	function changeContent(value){
		var innerStr = "";
		
		if(value == "승인"){
			innerStr += "<a class='form_btn bg' onclick='setSign(this, event);'>사인</a>";
			innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		} else if(value == "반려"){
			innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:500px' maxLength='50'/>";
			innerStr += "<a class='form_btn bg ml5' onclick='sSignContentAdd();'>반려 사유 저장</a>";
		}
		document.getElementById("sSignContentSet").innerHTML = innerStr;
	}

	function sSignContentAdd(){
		var sSignContent = $("#sSignContent").val();
		var sSignTableKey = $("#eIssueKey").val();
		var kStaffKey = $("#kStaffKey").val();
		if(sSignContent != ""){
			$.ajax({
				type : "post"
			,	url : "/mes/issue/kw_uploadSignIssueReasonAjax.do"
			,	data : {
					"sSignTableKey"		: sSignTableKey
				,	"sSignTableName"	: "I_ISSUE"
				,	"sSignStaffKey"		: kStaffKey
				,	"sSignDecison"		: "반려"
				,	"sSignContent"		: sSignContent	
				}
			,	dataType : "json"
			,	async : false
			,	cache : false
			,	success : function(msg){
				 alert("반려처리되었습니다.")
					 document.frm.action = "/mes/issue/kw_issue_vf.do";
					document.frm.submit();
				}
			});
		}else{
			alert("반려사유를 입력하세요.");
			$("#sSignContent").focus();
			return;
		}
		
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
				var sSignTableKey = $("#eIssueKey").val();
				var kStaffKey = $("#kStaffKey").val();
				$.ajax({
					type : "post"
				,	url : "/mes/asset/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignTableKey"		: sSignTableKey
					,	"sSignTableName"	: "I_ISSUE"
					,	"sSignStaffKey"		: kStaffKey
					,	"sSignDecison"		: "승인"
					,	"sSignContent"		: data	
					}
				,	dataType : "json"
				,	async : false
				,	cache : false
				,	success : function(msg){
						innerStr = "";
						innerStr += "<img style='width:150px; height:100px;' onclick='setSignHtml(this);' src='"+data+"'/>";
						innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'>"+data+"</textarea>";
						
						saveObj.parentNode.innerHTML = innerStr;
						
						$('#modal-close').get(0).click();
						document.frm.action = "/mes/issue/kw_issue_vf.do";
						document.frm.submit();
						
					}
				});
			}
		});
		
	}


	/*SIGN PAD*/
	var scrollPosition = 0;
	function setSign(obj, eventTmp){
		
		event.preventDefault();
		scrollPosition = window.scrollY;
		document.body.style.overflow = "hidden";
		$("#setModal").modal({
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	    });
		saveObj = obj;
		
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
		innerStr += "<a class='form_btn bg' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		obj.parentNode.innerHTML = innerStr;
	}
	
	function addFile(){
		var url = "/mes/issue/popup/kw_File_add.do";
		window.open(url ,"ePDFAdd" ,"width=600,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");

	}
	function eDownload(fileId,eFileName){
		 if(confirm(eFileName+"을 다운로드 하시겠습니까?")){
				window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+fileId+"&fileSn=0'/>");
			}
	}



	//화면 캡처 후 인쇄로 넘어가기
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
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesIssueVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesIssueVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesIssueVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesIssueVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesIssueVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesIssueVO.searchType}" />
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
<!-- 	등록한 정보의 상태값을 확인 -->
	<input type="hidden" id="eIssueStatus" name="eIssueStatus" value="${issueInfo.eIssueStatus}" />
	<input type="hidden" id="ePageGubun" name="ePageGubun" value="${mesIssueVO.ePageGubun}" />
	<input type="hidden" id="eIssueKey" name="eIssueKey" value="${issueInfo.eIssueKey}" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="${issueInfo.sSignStatus}" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>장애관리 상세</h2>
		</div>
	</div>
	
	<div class="normal_table row">
		<table>
			<tbody>
          		<tr>
					<th >작성자</th>
					<td >${issueInfo.eAuthor}
						 <input type="hidden" id="eAuthor" name="eAuthor"  value="${issueInfo.eAuthor}" maxlength="50"/>
					</td>
					<th >등록일</th>
					<td >
						${issueInfo.eCreationDate}
						<input type="hidden" id="eCreationDate" name="eCreationDate" style="width:120px;text-align: center;" class="inp_color"  readonly="readonly" />
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
					<th>자산유형</th>
					<td colspan="3">${issueInfo.eAssetTypeName}
				</tr>
				<tr>
					<th>처리유형</th>
					<td> ${issueInfo.eProcessingTypeName}</td>
					<th>상세구분</th>
					<td>${issueInfo.eIssueTypeName}	</td>
				</tr>
				<tr>
					<th >요청자</th>
					<td > ${issueInfo.eRequester}
						<input type="hidden" id=eRequester name="eRequester" value="${issueInfo.eRequester}" maxLength="100"/>
					</td>
					<th >요청자 소속</th>
					<td >
						<span id="eRequesterOrgTxt">${issueInfo.eRequesterOrg}</span>
						<input type="hidden" id="eRequesterOrg" name="eRequesterOrg" maxLength="100" class="inp_color"  readonly="readonly" />
					</td>
  				</tr>
				<tr>
					<th >요청일자</th>
					<td >${issueInfo.eRequestDate}
						<input type="hidden" id="eRequestDate" name="eRequestDate" style="width:120px;text-align: center;" value="${issueInfo.eRequestDate}" class="inp_color"  readonly="readonly"/>
					</td>
					<th >처리일자</th>
					<td >${issueInfo.eProcessingDate}
						<input type="hidden" id="eProcessingDate" name="eProcessingDate" style="width:120px;text-align: center;" value="${issueInfo.eProcessingDate}" class="inp_color"  readonly="readonly"/>
					</td>
  				</tr>
				<tr>
					<th>
						요청내용
					</th>
					<td id="td_editor" colspan="3" align="center" scope="row"> 
					 ${issueInfo.eIssueContent} 
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="normal_table row mt20">
		<table>
			<thead>
<!-- 				<tr> -->
<!-- 					<th colspan="2"> -->
<!-- 					 <a class="form_btn md" onclick="addFile()" >파일 선택</a> -->
<!-- 					</th> -->
<!-- 				</tr> -->
				<tr>
<!-- 					<th style="width: 25%;">구분</th> -->
					<th style="width: 100%;">첨부파일</th>
				</tr>
			</thead>
			<tbody id="fileRow">
			 		<c:if test="${not empty eFileInfoList}">
						 <c:forEach var="list" items="${eFileInfoList}" >
					 		<tr> <td><a onclick="eDownload('${list.eFileID}','${list.eFileName}');">${list.eFileName}</a></td></tr>
						 </c:forEach>
					 </c:if>
			 		<c:if test="${empty eFileInfoList}">
			 			<tr><td class="tac">등록된 파일이 없습니다.</td></tr>
			 		</c:if>
			</tbody>
		</table>
	</div> 
 	
 	
	<div class="content_top nofirst" id="viewDiv4">
		<div class="content_tit">
			<h2>처리자 정보</h2>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
			<caption>
<!-- 				   <a class="form_btn md" onclick="add_row()" style="float:right">행추가 </a> -->
			</caption>
				<thead>
				<tr> 
<!-- 					<th style="width: 10%;">구분</th> -->
					<th style="width: 18%;">처리자명</th>
					<th style="width: 18%;">처리자소속</th>
					<th style="width: 18%;">처리내용</th>
					<th style="width: 18%;">연락처</th>
					<th style="width: *;">비고</th>
				</tr>
			</thead>
			<tbody id="lineRowTwo">
				<c:forEach var="list" items="${eHandlerList}" varStatus="i">
						<tr>
<!-- 											<td> -->
<!-- 												<a class='del' onclick="delRow(this,'DD');">X</a> -->
<!-- 											</td> -->
						
							<td>${list.eRowWorker}
								<input type="hidden" id='eRowWorker_${i.index}' name='eRowWorker' value='${list.eRowWorker}' maxLength='30'/>
							</td>
									
							<td>${list.eRowDepartment}															
								<input type="hidden" id='eRowDepartment_${i.index}' name='eRowDepartment' value='${list.eRowDepartment}' maxLength='30'/>
							</td>
						
							<td>${list.eRowPosition}
								<input type="hidden" id='eRowPosition_${i.index}' name='eRowPosition' value='${list.eRowPosition}' maxLength='30'/>
							</td>
						
							<td>${list.eRowContact}
								<input type="hidden" id='eRowContact_${i.index}' name='eRowContact' value='${list.eRowContact}' maxLength='30'/>
							</td>
							<td>${list.eRowNotes}
								<input type="hidden" id='eRowNotes_${i.index}' name='eRowNotes' value='${list.eRowNotes}' maxLength='30'/>
							</td>
						
						</tr>
			 </c:forEach>
			
			
				<c:if test="${empty eHandlerList }">
					<tr>
						<td colspan="5">등록정보가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	 <div class="content_top nofirst with_btn" id="viewDiv1">
		<div class="content_tit">
			<h2>장비 정보</h2>
		</div>
		<div class="btns">
			<c:if test="${issueInfo.sSignStatus eq '승인' || issueInfo.sSignStatus eq '제외' }">
			   	<a class="form_btn md" onclick="sel_asset()"  style="visibility: hidden;" id="assetAddBtn">장비 선택</a>
		   </c:if>
		</div>
	</div>
	<div class="normal_table" id="viewDiv2">
		<table>
				<thead>
				<tr>
					<th style="width: 200px;">구분</th>
					<th style="width: 12%;">자산유형</th>
					<th style="width: 12%;">자산번호</th>
					<th style="width: 12%;">자산명</th>
					<th style="width: 12%;">모델명</th>
					<th style="width: 12%;">망구분</th>
					<th style="width: 12%;">HOSTNAME</th>
					<th style="width: 12%;">IP</th>
					<th style="width: 12%;">OS</th>
				</tr>
			</thead>
			<tbody id="lineRow">
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr>
						<td>
							<c:if test="${issueInfo.sSignStatus eq '승인' || issueInfo.sSignStatus eq '제외' }">
								
								<a class='del' onclick="delRow(this);" style="visibility: hidden;">X</a>  
							</c:if>
							<c:if test="${issueInfo.eIssueStatus eq '처리등록'}">-	</c:if>
						</td>
						
						<td> ${list.aAssetType}   
							<input type='hidden' id='eAssetKey_${i.index}' name='eAssetKey' value='${list.eAssetKey} '/>
						</td>
						
						<td>${list.aAssetNumber} 	 </td>		
						<td>${list.aAssetName}		</td>
						<td>${list.aAssetModel} 		</td>
						<td>${list.eNetworkType}  		</td>	
						<td>${list.eHostName} 			</td>	
						<td>${list.eIp}		</td>	
						<td>${list.eOs} 	</td>			
						
					</tr>
				</c:forEach>
			
				<c:if test="${empty assetList}">
					<tr>
						<td colspan="10">장비를 선택하세요.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div  id="viewProcess" style="visibility: hidden; overflow:hidden;">
		<div class="content_top nofirst"  id="viewDiv3">
			<div class="content_tit">
				<h2>처리 정보</h2>
			</div>
		</div>
		<div class="normal_table">
			<table>
				<thead>
					<tr>
						<th colspan="4">처리내용
							 <input type="hidden" id="eActualWorkDate" name="eActualWorkDate" style="width:120px;text-align: center;" value="${issueInfo.eActualWorkDate}" class="inp_color"  readonly="readonly"/>
							 <input type="hidden" id="eActualWorker" name="eActualWorker" style="width:120px;text-align: center;" value="${issueInfo.eActualWorker}" maxlength="14"/>
						</th>
					</tr>
				</thead>
				 <tbody>	
<!-- 					<tr> -->
<!-- 						<th >*실 처리일자</th> -->
<!-- 						<td> -->
<%-- 						<c:if test="${issueInfo.eIssueStatus eq '요청등록'}"> --%>
<%-- 							 <input type="text" id="eActualWorkDate" name="eActualWorkDate" style="width:120px;text-align: center;" value="${issueInfo.eActualWorkDate}"  maxlength="100" class="inp_color"  readonly="readonly"/> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${issueInfo.eIssueStatus eq '처리등록'}">${issueInfo.eActualWorkDate} --%>
<%-- 							<input type="hidden" id="eActualWorkDate" name="eActualWorkDate" style="width:120px;text-align: center;" value="${issueInfo.eActualWorkDate}" class="inp_color"  readonly="readonly"/> --%>
<%-- 						</c:if> --%>
<!-- 						</td> -->
<!-- 						<th >*실 처리자</th> -->
<!-- 						<td> -->
<%-- 							<c:if test="${issueInfo.eIssueStatus eq '요청등록'}"> --%>
<%-- 								 <input type="text" id="eActualWorker" name="eActualWorker" style="width:120px;text-align: center;" value="${issueInfo.eActualWorker}" maxlength="14"/> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${issueInfo.eIssueStatus eq '처리등록'}">${issueInfo.eActualWorker} --%>
<%-- 								 <input type="hidden" id="eActualWorker" name="eActualWorker" style="width:120px;text-align: center;" value="${issueInfo.eActualWorker}" maxlength="14"/> --%>
<%-- 							</c:if> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<c:if test="${issueInfo.eIssueStatus eq '요청등록'}">
						<td id="td_editor" colspan="4" align="center" scope="row"> 
							<textarea id="eActualDetails" name="eActualDetails" cols="100%" rows="20" style="font-size: 20px; width: 100%;" maxLength="1000">${issueInfo.eActualDetails}</textarea>
						</td>
						</c:if>
						<c:if test="${issueInfo.eIssueStatus eq '처리등록'}">
							<td colspan="4" style="text-align: left; padding: 4px 16px;"> ${issueInfo.eActualDetails}
								<textarea id="eActualDetails" name="eActualDetails"  style="text-align: center; font-size: 20px; width: 100%; position: absolute; height: 0; overflow:hidden; visibility: hidden;">${issueInfo.eActualDetails}</textarea>
							</td>
						</c:if>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:if test="${not empty signList}">
		<div class="normal_table">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%;">결재순서</th>
						<th style="width:8%;">결재자</th>
						<th style="width:8%;">결정</th>
						<th style="width:15%;">결재구분</th>
						<th style="width:60%;">반려사유 및 싸인</th>
					</tr>
	        	</thead>
		        <tbody>
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
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
								${signList.sSignStaffGubun}:
								<c:if test="${issueInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey }">
									<c:if test="${signList.sSignDecison eq '결재대기' }">
										<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
											<option value="승인" selected>승인</option>
											<option value="반려">반려</option>
										</select>
									</c:if>
								</c:if>
								<c:if test="${signList.sSignStaffKey ne staffVO.kStaffKey || issueInfo.sSignStatus eq '승인'  }">${signList.sSignDecison}</c:if>
							</td>
							<td <c:if test="${issueInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${issueInfo.sSignStatus eq '승인요청' && signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">
						        		<a class="form_btn bg" onclick="setSign(this, event);">사인</a>
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
	<div class="bottom_btn">
		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
			<c:if test="${issueInfo.sSignStatus eq '승인' || issueInfo.sSignStatus eq '제외' }">
				<c:if test="${issueInfo.eIssueStatus eq '요청등록'}">
					<c:if test="${issueInfo.kStaffKey eq staffVO.kStaffKey || staffVO.kAdminAuth eq 'T'}">
						<button type="button" id="process_go" onclick="process_go();" class="form_btn active">처리정보 등록하기</button>
					</c:if>
				</c:if>
			</c:if>
		</c:if>
		<button type="button" class="topdown" onclick="eExcelDownload();">다운로드</button>
		<c:if test="${(issueInfo.kStaffKey eq staffVO.kStaffKey && (issueInfo.sSignStatus eq '등록' || issueInfo.sSignStatus eq '반려' || issueInfo.sSignStatus eq '제외')) || (staffVO.kAdminAuth eq 'T' && (issueInfo.sSignStatus eq '등록' || issueInfo.sSignStatus eq '반려' || issueInfo.sSignStatus eq '제외')) }">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'  || staffVO.kAdminAuth eq 'T'}">
				<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
			</c:if>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
		<c:if test="${issueInfo.sSignStatus eq '등록'}">
			<c:if test="${issueInfo.kStaffKey eq staffVO.kStaffKey }">
				<button type="button" class="form_btn bg" onclick="startApproval('Y');">승인요청</button>
			</c:if>
		</c:if>
		<c:if test="${issueInfo.sSignStatus eq '승인요청'}">
			<c:if test="${issueInfo.kStaffKey eq staffVO.kStaffKey && issueInfo.sSignProgress eq '0'}">
				<button type="button" class="form_btn bg" onclick="startApproval('N');">요청취소</button>
			</c:if>
		</c:if>
	</div>
	<div id="setModal" class="modal" style="display:none;">
		<a id="modal-close" href="#close-modal" rel="modal:close" class="close-modal " onclick="closeModal()">Close</a>
		<div class="modal-content">
			<div class="lf_tbl_list scrolltbody" style="margin-top: 0px; border: 0.5px #d1d1d1 solid; border-radius:5px;max-height:200px;">
				<canvas id="signature" width="450" height="200"></canvas>
			</div>
			<div class="tac mt20" id="modalAddRow">
		     <a class="form_btn md" id="save">저장 </a>
			</div>
		</div>
	</div>	
 
</form>