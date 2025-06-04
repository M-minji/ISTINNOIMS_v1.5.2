<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function update_go(){
		if(chkIns()){
			if(confirm("저장하시겠습니까?")){
				$("#mloader").show();
				document.frm.submit();	
			}
		}
	}

// validation
function chkIns(){
	if ($("#eContactName").val() == ""){
		alert("담당자명 선택하세요.");
		$("#eContactName").focus();
		return false;
	}
	
	return true;
}

// 삭제
function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		$("#mloader").show();
		document.frm.action = "/mes/contact/kw_contact_d.do";
		document.frm.submit();
	}
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/contact/kw_contact_lf.do";
	document.frm.submit(); 
}
</script>


<form id="frm" name="frm" method="post" action="/mes/contact/kw_contact_u.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesContactVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesContactVO.recordCountPerPage}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesContactVO.searchWord}" />
	<input type="hidden" id="searchGubun" name="searchGubun" value="${mesContactVO.searchGubun}" />

	<input type="hidden" id="eContactNum" name="eContactNum" value="${info.eContactNum}" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>담당자 정보 수정</h2>
		</div>
	</div>
	
	<div class="normal_table">
        <table>
	       	<thead>
	       		<tr>
	           		<th>소속사</th>
       				<th>부서</th>
       				<th><span style="color: red">* </span>담당자명</th>
       				<th>연락처</th>
       				<th>이메일</th>
       				<th>기타</th>
	       		</tr>
	       	</thead>
	       	<tbody>
	       		<tr>
	           		<td>
	           			<input type="text" id="eAgencyName" name="eAgencyName" style="width: 90%;" maxlength="30" value="${info.eAgencyName}" />
	           		</td>
	           		<td>
	           			<input type="text" id="eDepartmentName" name="eDepartmentName" style="width: 90%;" maxlength="30" value="${info.eDepartmentName}" />
	           		</td>
	           		<td>
	           			<input type="text" id="eContactName" name="eContactName" style="width: 90%;" maxlength="30" value="${info.eContactName}" />
	           		</td>
	           		<td>
	           			<input type="text" id="ePhoneNumber" name="ePhoneNumber" style="width: 90%;" maxlength="30" value="${info.ePhoneNumber}" />
	           		</td>
	           		<td>
	           			<input type="text" id="eEmail" name="eEmail" style="width: 90%;" maxlength="30" value="${info.eEmail}" />
	           		</td>
	           		<td>
	           			<input type="text" id="eNotes" name="eNotes" style="width: 90%;" maxlength="30" value="${info.eNotes}" />
	           		</td>
	       		</tr>
	       	</tbody>
   		</table>
   	</div>
   	
   	<div class="bottom_btn">
   		<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
		<button type="button" class="form_btn bg" onclick="update_go();">수정</button>
		</c:if>
		<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
		<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>

</form>
