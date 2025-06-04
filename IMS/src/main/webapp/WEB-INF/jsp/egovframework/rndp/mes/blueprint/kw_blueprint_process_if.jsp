<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">


	// 오늘 날짜
	function nowDate(){
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		var nowDate = year + "-" + month + "-" + day;

		return nowDate;
	}

	// 저장
	function insert_go(){
		$("#mloader").show();
		$("#newPrint").val("old");
		document.frm.action = "/mes/blueprint/kw_blueprint_process_i.do";
		document.frm.submit();
	}

	// 저장
	function update_go2(version){
		$("#mloader").show();
		$("#version").val(version);
		$("#newPrint").val("new");
		document.frm.action = "/mes/blueprint/kw_blueprint_uf.do";
		document.frm.submit();
	}


	// 삭제
	function delete_go(){
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_d.do";
		document.frm.submit();
	}


	// 목록
	function cancel(){
		$("#mloader").show();
		document.frm.action = "/mes/blueprint/kw_blueprint_lf.do";
		document.frm.submit();
	}




	//파일다운
	function fn_egov_viewFile_md(id, extension){
		var url = "";
		
		if(extension != "pdf"){
			url = "<c:url value='/cmm/fms/getImage.do'/>?atchFileId="+id+"&fileSn=0"
			fn_egov_viewFile(url);
		}else{
			url = "<c:url value='/cmm/fms/getPdf.do'/>?atchFileId="+id+"&fileSn=0"
			fn_view_pdf(url);
		}
	}

	// 파일다운
	function fn_egov_downFile(atchFileId, fileSn){
		if(confirm("파일을 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
	}

	
	//처리내역 등록 
	function process_go(key){
		$("#mloader").show();
		$("#outputKey").val(key);
		document.frm.action = "/mes/output/kw_output_process_if.do";
		document.frm.submit();
	}

	//상세 등록 
	function maintanceView(key){
		$("#mloader").show();
		$("#outputKey").val(key);
		document.frm.action = "/mes/output/kw_output_vf.do";
		document.frm.submit();
	}

	function requestSign(outputKey, sSignKey){
		$("#mloader").show();
		$("#outputKey").val(outputKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/output/kw_output_process_r.do";
		document.frm.submit();
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

<form id="frm" name="frm" method="post" enctype="multipart/form-data" action="/mes/blueprint/kw_blueprint_i.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesBlueprintVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesBlueprintVO.recordCountPerPage}" />
	<input type="hidden" id="topStartDate" name="topStartDate" value="${mesBlueprintVO.topStartDate}" />
	<input type="hidden" id="topEndDate" name="topEndDate" value="${mesBlueprintVO.topEndDate}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesBlueprintVO.searchWord}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesBlueprintVO.searchType}" />
	<input type="hidden" id="blueprintKey" name="blueprintKey" value="${mesBlueprintVO.blueprintKey}" />
	<input type="hidden" id="blueprintItemKey" name="blueprintItemKey" value="${mesBlueprintVO.blueprintItemKey}" />
	<input type="hidden" id="version" name="version" value="${mesBlueprintVO.version}" />
	<input type="hidden" id="newPrint" name="newPrint" value="${mesBlueprintVO.newPrint}" />

	<div class="content">
		<div class="content_tit">
			<h2>변경/문제 상세</h2>
		</div>
	</div>

	<div class="tbl_esti_detail_total">
		<div class="tbl_write">
			<table>
				<tbody>
				<tr>
					<th>번호</th>
					<td>
						${blueprintInfo.blueprintNumber}
					</td>
					<th>등록일</th>
					<td>
						${blueprintInfo.blueprintWdate}
					</td>
				</tr>
				<tr>
					<th>*사유</th>
					<td>
						${blueprintInfo.eItemName}
					</td>
					<th>분류</th>
					<td>
						${blueprintInfo.eItemCatePath}
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>

	<%--	<div class="tbl_top">--%>
	<%--		<ul class="tbl_top_left">--%>
	<%--			<a style="cursor: pointer;" onclick="addRow();" >--%>
	<%--				행추가--%>
	<%--			</a>--%>
	<%--		</ul>--%>
	<%--	</div>--%>

	<div class="tbl_list">
	   		<table>
	   			<thead>
					<tr>
						<th style="width: 45%;">파일명</th>
						<th style="width: 50%;">비고</th>
				    </tr>
		    	</thead>
		  		<tbody id="fileUL">
		  			<c:forEach var="fileList2" items="${fileList2}" varStatus="status" >
		  				<tr>
							<c:if test="${fileList2.eBlueprintFileGubun eq '도면'}">
 								<td id="filename_${status.index}">
		 							<a href="javascript:fn_egov_downFile('${fileList2.eAddFileId7}','0')" style="text-decoration:none;">
		 								${fileList2.atchFileName}
		 							</a>
					  				<input type='hidden' id='fileKey'   name='fileKey' value='0' />
								 	<input type='hidden' id='AddFileId' name='eAddFileId1' value='${fileList2.eAddFileId7}' />
								 	<input type='hidden' id='atchFileName' name='atchFileName1' value='${fileList2.atchFileName}' />
						 		</td>
 								<td>
								 	${fileList2.eBlueprintItemEtc}
						 		</td>
					     	</c:if>	
					     </tr>
	     			</c:forEach>
	     		</tbody>
	     		<thead>
						<tr>
							<th style="text-align:center;" colspan="2">
								요청 내용
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							${blueprintInfo.blueprintEtc}
						</td>
					</tr>
		  		</tbody>
			</table>
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
			<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="insert_go();">등록</a>
				</li>
			</c:if>
			<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="delete_go();">삭제</a>
				</li>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>
			</li>
		</ul>
	</div>
</form>