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
 		window.opener.location.reload(); // 부모 창 새로고침
		window.close();
	}
	datepickerIdSet("eEntryExporterDate");
	$('#eEntryExporterDate').val(nowDate());
	
});

function mesIMGregInsert() {
	if($("#eEntryExporterDate").val() == ''){
		alert("반출일을 선택하세요.");
		$("#eEntryExporterDate").focus();
		return false;
	}
	if($("#eExitExporter").val() == ''){
		alert("반출 확인자를 입력하세요.");
		$("#eExitExporter").focus();
		return false;
	}
	
	document.frm.submit();
}
  
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}

</script>


<form id="frm" name="frm" method="post"   action="/mes/equipment/kw_eImport_update.do" class="popup_wrap">
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>임시자산 반출 등록</h3>
			</div>
			<a href="javascript:self.close();">
				<img src="/images/btn/close.gif" width="22" height="21">
			</a>
		</div>
	</div> 

	<div class="popup_content">
		<div class="pop_con">
			<div class="normal_table row" id="pop_result_list" align="center" style="margin-top: 0"> 
				<table>
				  <tbody>
						<tr>
							<th>반출 일자</th>
			      			<td>
			      				<input type="hidden" name='eEquipmentItemKey' id='eEquipmentItemKey' value='${mesEquipmentVO.eEquipmentItemKey }'> 
			      				<input type="text" name='eEntryExporterDate' id='eEntryExporterDate' value='' style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly">   
			      			</td>
			      			<th>반출 확인자</th>
			      			<td>
			      				<input type="text" name='eExitExporter' id='eExitExporter' value='' maxlength="30">  
			      			</td>
						</tr>
		    		</tbody>
				</table>
			</div>
			
			<div class="list_btm right">
				<div class="btns">
					<button type="button" class="form_btn active" onclick="mesIMGregInsert();">등록</button>
					<button type="button" class="form_btn" onclick="javascript:self.close();">닫기</button>
				</div>
			</div>
		</div>
	</div>
</form>