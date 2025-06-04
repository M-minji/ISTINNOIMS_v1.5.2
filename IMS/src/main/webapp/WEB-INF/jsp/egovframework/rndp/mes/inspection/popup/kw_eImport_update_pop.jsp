<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>Update</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){	
	var eGubunPage = "${eGubunPage}";
	if (eGubunPage == "N") {
// 		if(typeof(opener.addFileInfoRow) != "undefined"){
// 			 window.opener.addFileInfoRow(eIMGregId,eDeviceImgName,eDeviceImgExtension);						
// 		}
 		window.opener.location.reload(); // 부모 창 새로고침
		window.close();
	}
	datepickerIdSet("eEntryImportDate");
	
});

function mesIMGregInsert() {
	if($("#eEntryImportDate").val() == ''){
		alert("반입일자를 선택하세요.");
		$("#eEntryImportDate").focus();
		return false;
	}
	
	
	document.frm.submit();
}
  

</script>


<form id="frm" name="frm" method="post"   action="/mes/asset/kw_eImport_update.do">
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>반입 등록 POP</h3>
			</div>
			<a href="javascript:self.close();">
				<img src="/images/btn/close.gif" width="22" height="21">
			</a>
		</div>
	</div> 

	<div class="popup_content">
		<div class="lf_tbl_list" id="pop_result_list" align="center"> 
			<table>
			  <tbody>
					<tr>
						<th style="background-color: #f8fafc;">*반입 일자</th>
		      			<td style="text-align: left;">
		      				<input type="hidden" name='eEntryExitItemKey' id='eEntryExitItemKey' value='${mesAssetVO.eEntryExitItemKey }'> 
		      				<input type="text" name='eEntryImportDate' id='eEntryImportDate' value='' style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly">   
		      			</td>
		      			<th style="background-color: #f8fafc;">반입 확인자</th>
		      			<td style="text-align: left;">
		      				<input type="text" name='eEntryImporter' id='eEntryImporter' value='' maxlength="30">  
		      			</td>
					</tr>
	    		</tbody>
			</table>
		</div>
		<div class="page">
		</div>
		<div class="tbl_btn_right">
			<ul>
				<li>
					<a style="cursor: pointer;" onclick="mesIMGregInsert();">
								등록
						</a>
				</li>
				<li>		
					 <a href="javascript:self.close();">
			  					닫기
		  			</a>
				</li>
			</ul>
		</div>
	</div>
</form>