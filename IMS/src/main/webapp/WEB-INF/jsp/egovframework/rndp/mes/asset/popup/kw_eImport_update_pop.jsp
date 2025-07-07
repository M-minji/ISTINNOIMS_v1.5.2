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
$(document).ready(function(){	
	
	var eGubunPage = "${eGubunPage}";
	if (eGubunPage == "N") {
 		window.opener.location.reload(); // 부모 창 새로고침
		window.close();
	}
	datepickerIdSet("eEntryImportDate");
	$('#eEntryImportDate').val(nowDate());
});

function mesIMGregInsert() {
	if($("#eEntryImportDate").val() == ''){
		alert("반입일자를 선택하세요.");
		$("#eEntryImportDate").focus();
		return false;
	}
	if($("#eEntryImporter").val() == ''){
		modal1("반입 확인자를 입력하세요.", "#eEntryImporter");
		return false;
	}
	
	document.frm.submit();
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

<form id="frm" name="frm" method="post"   action="/mes/asset/kw_eImport_update.do" class="popup_wrap">
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>보유자산 반입 등록 </h3>
			</div>
			<a href="javascript:self.close();">
				<img src="/images/btn/close.gif" width="22" height="21">
			</a>
		</div>
	</div> 

	<div class="popup_content">
		<div class="pop_con">
			<div class="normal_table row" id="pop_result_list" align="center"> 
				<table>
				  <tbody>
						<tr>
							<th>반입 일자</th>
			      			<td>
			      				<input type="hidden" name='eEntryExitItemKey' id='eEntryExitItemKey' value='${mesAssetVO.eEntryExitItemKey }'> 
			      				<input type="text" name='eEntryImportDate' id='eEntryImportDate' value='' style="width:120px; text-align:center;" class="inp_color"   value=""  readonly="readonly">   
			      			</td>
			      			<th>반입 확인자</th>
			      			<td>
			      				<input type="text" name='eEntryImporter' id='eEntryImporter' style="width:100%;" value='' maxlength="30">  
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