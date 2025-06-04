<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>Pop Add</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){	
	datepickerSet("eOldExpiryDate","eNewExpiryDate");
	$("#eNewExpiryDate").val(nowDate())
	var eGubunPage = "${eGubunPage}";
	 
	if (eGubunPage == "N") {
		if(typeof(opener.fn_guestList) != "undefined"){
			 window.opener.fn_guestList(1);						
		}
		window.close();
	}
});

function mesPopInsert() {
	document.frm.action = "/mes/asset/kw_Software_update.do";
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


<form id="frm" name="frm" method="post"  enctype="multipart/form-data"  class="popup_wrap">
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>라이선스 갱신 등록 POP</h3>
			</div>
			<a href="javascript:self.close();">
				<img src="/images/btn/close.gif" width="22" height="21">
			</a>
		</div>
	</div> 
	
	<div class="popup_content">
		<div class="pop_con">
			<div class="lf_tbl_list" id="pop_result_list" align="center"> 
				<table>
					<thead>
						<tr>
							<th>제조사</th>
							<th>라이선스명</th>
							<th>종료일</th>
							<th>갱신종료일</th>
						</tr>
					</thead>
					<tbody>	
						<tr>
			      			<td>${swInfo.eManufacturer}</td>
			      			<td>${swInfo.eProductName}</td>
			      			<td>${swInfo.eEndDate}
			      				<input type="hidden" id="eOldExpiryDate" name="eOldExpiryDate" value="${swInfo.eEndDate}"/>
			      				<input type="hidden" id="eSWRegisterKey" name="eSWRegisterKey" value="${swInfo.eSWRegisterKey}" />
			      			</td>
			      			<td>
			       				<input type="text" id="eNewExpiryDate" name="eNewExpiryDate" style="width:150px; text-align:center;" class="inp_color" readonly />
			      			</td>
						</tr>
		    		</tbody>
				</table>
			</div>
			<div class="list_btm right">
				<div class="btns">
					<button type="button" class="form_btn active" onclick="mesPopInsert();">등록</button>
					<button type="button" class="form_btn" onclick="javascript:self.close();">닫기</button>
				</div>
			</div>
		</div>
	</div>
</form>