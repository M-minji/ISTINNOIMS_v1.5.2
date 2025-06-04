
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(document).ready(function(){
	addRow();
}); 

function insert_go(){
	if(chkIns()){
		if(confirm("저장하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/contact/kw_contact_i.do";	
			document.frm.submit();	
		}
	}
}

// validation
function chkIns(){
	var ln = document.getElementsByName("eContactName").length;
		
	if(ln > 0){
		for(var i = 0; i < ln; i++){
			 
			if(document.getElementsByName("eContactName")[i].value == ""){
				alert((i+1)+"번째 담당자명을 입력해주세요.");
				document.getElementsByName("eContactName")[i].focus();
				return false;
			}
		}
	}else{
		alert("등록할 행이 없습니다.");
		return false;
	} 
	
	
	
	if(ln > 0){
		for(var i = 0; i < ln; i++){
				var eAgencyName = document.getElementsByName("eAgencyName")[i].value;
				var eContactName = document.getElementsByName("eContactName")[i].value;
				var eDepartmentName = document.getElementsByName("eDepartmentName")[i].value;
				var ePhoneNumber = document.getElementsByName("ePhoneNumber")[i].value;
				var eEmail = document.getElementsByName("eEmail")[i].value;
				var eNotes = document.getElementsByName("eNotes")[i].value;
				
				
				document.getElementsByName("eAgencyName")[i].value = ConverttoHtml(eAgencyName);
				document.getElementsByName("eContactName")[i].value = ConverttoHtml(eContactName);
				document.getElementsByName("eDepartmentName")[i].value = ConverttoHtml(eDepartmentName);
				document.getElementsByName("ePhoneNumber")[i].value = ConverttoHtml(ePhoneNumber);
				document.getElementsByName("eEmail")[i].value = ConverttoHtml(eEmail);
				document.getElementsByName("eNotes")[i].value = ConverttoHtml(eNotes);
		}
	}
	
	
	
	
	return true;
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/contact/kw_contact_lf.do";
	document.frm.submit(); 
}

// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();		
}

//행추가
var itemRowIndex = 0;

function addRow() {	

	var innerStr = "";
	
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a class='del' onclick='delRow(this)'>X</a>";
	innerStr += "		</td>";
	// 소속사명
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eAgencyName_"+itemRowIndex+"' name='eAgencyName' style='width:90%;'  maxlength='50' />";
	innerStr += "		</td>";
	// 부서명
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eDepartmentName_"+itemRowIndex+"' name='eDepartmentName' style='width:90%;'  maxlength='50' />";
	innerStr += "		</td>";
	// 담당자명
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eContactName_"+itemRowIndex+"' name='eContactName' style='width:90%;'  maxlength='50'/>";
	innerStr += "		</td>";	
	// 연락처
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='ePhoneNumber_"+itemRowIndex+"' name='ePhoneNumber' style='width:90%;'  maxlength='50'/>";
	innerStr += "		</td>";	
	// 메일
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eEmail_"+itemRowIndex+"' name='eEmail' style='width:90%;'  maxlength='50' />";
	innerStr += "		</td>";	
	// 기타
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eNotes_"+itemRowIndex+"' name='eNotes' style='width:90%;'  maxlength='50' />";
	innerStr += "		</td>";	
	innerStr += "	</tr>"; 
									
	$(innerStr).appendTo("#lineRow");
	
	itemRowIndex++;
}

</script>
<form id="frm" name="frm" method="post" action="/mes/contact/kw_contact_i.do">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesContactVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesContactVO.recordCountPerPage}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesContactVO.searchWord}" />
	<input type="hidden" name="searchGubun" id="searchGubun" value="${mesContactVO.searchGubun}" />


	<div class="content_top with_btn">
		<div class="content_tit">
			<h2>담당자 정보 등록</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addRow();">입력 행추가</button>
		</div>
	</div>
	<div class="normal_table">
		<table>
			<thead>
		       <tr>
      				<th style="width:6%;">행삭제</th>
       				<th>소속사</th>
       				<th>부서</th>
       				<th><span style="color: red">* </span>담당자명</th>
       				<th>연락처</th>
       				<th>이메일</th>
       				<th>기타</th>
      			</tr>
       		</thead>
  	 		<tbody id="lineRow">
  	 		
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>
</form>
