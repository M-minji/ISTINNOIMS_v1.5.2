<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script>

function memberUp(){
	if (document.writeForm.kAdminPwd.value == ""){
		alert("현재 패스워드를 입력하세요.");
		document.writeForm.kAdminPwd.focus();
		return;
	}
	if (document.writeForm.kAdminNewPwd.value == "")
	{
		alert("변경할 패스워드를 입력하세요.");
		document.writeForm.kAdminNewPwd.focus();
		return;
	}
	if (document.writeForm.kAdminNewPwdResult.value == "")
	{
		alert("확인 패스워드를 입력하세요.");
		document.writeForm.kAdminNewPwdResult.focus();
		return;
	}
	
	if (document.writeForm.kAdminNewPwd.value.length < 4)
	{
		alert("패스워드는 4자 이상으로 입력하세요.");
		document.writeForm.kAdminNewPwd.focus();
		return;
	}
	
	if (document.writeForm.kAdminNewPwd.value != document.writeForm.kAdminNewPwdResult.value)
	{
		alert("변경 패스워드와 확인패스워드가 다릅니다. 확인바랍니다. ");
		document.writeForm.kAdminNewPwd.focus();
		return;
	}		

	
	if (confirm("변경 하시겠습니까?")){
		document.writeForm.action = "/mes/kw_admin_pwd_u.do";
		document.writeForm.submit();
	}
}

</script>
<form name="writeForm" id="writeForm">		
	<input type="hidden" name="kAdminKey" id="kAdminKey" value="${vo.kAdminKey}" />
	
		<div class="content">
			<div class="content_tit">
				<h2>계정 비밀번호 번경</h2>
			</div>
		</div>
		
	
			<div class="tbl_write_f">
				<table>
					<tbody>
						<tr>
							<th>이름</th>
							<td>${vo.kAdminName}</td>
						</tr>
						<tr>	
							<th>I D</th>
							<td>${vo.kAdminId}</td>
						</tr>
						<tr>
							<th>현재비밀번호</th>
							<td><input type="password" name="kAdminPwd" id="kAdminPwd" maxLength="50" /></td>
						</tr>
						<tr>
							<th>변경비밀번호</th>
							<td><input type="password" name="kAdminNewPwd" id="kAdminNewPwd" maxLength="50" /></td>
						</tr>
						<tr>
							<th>변경비밀번호확인</th>
							<td><input type="password" name="kAdminNewPwdResult" id="kAdminNewPwdResult" maxLength="50" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:memberUp();">변경</a>
					</li>
					<li>
						<a href="/mes/kw_admin_lf.do">목록</a>
					</li>
				</ul>
			</div>
					
	
</form>