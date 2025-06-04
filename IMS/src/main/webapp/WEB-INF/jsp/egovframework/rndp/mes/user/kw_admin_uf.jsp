<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script type="text/javascript" src="https://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

function memberUp(){
	document.writeForm.action = "/mes/kw_admin_u.do";
	document.writeForm.submit();
}

</script>
<form name="writeForm" id="writeForm">		
	<input type="hidden" name="kAdminKey" id="kAdminKey" value="${vo.kAdminKey}" />
	
		<div class="content">
			<div class="content_tit">
				<h2>시스템 계정 관리 수정</h2>
			</div>
		</div>
		
	
	
			<div class="tbl_write_f">
				<table>
					<tbody>
						<tr>
							<th>I D</th>
							<td>${vo.kAdminId}</td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" name="kAdminName" maxLength="50" style="width: 150px;" value="${vo.kAdminName }" /></td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td><input type="text" name="kAdminTelePhone" maxLength="50" style="width: 150px;" value="${vo.kAdminTelePhone }" /></td>
						</tr>
						<tr>
							<th>E-Mail</th>
							<td><input type="text" name="kAdminEmail" maxLength="50" style="width: 150px;" value="${vo.kAdminEmail }" /></td>
						</tr>
						<tr>
							<th>설명</th>
							<td><input type="text" name="kAdminMemo" maxLength="50" style="width: 150px;" value="${vo.kAdminMemo }" /></td>
						</tr>
						<tr>
							<th>최고 관리자 설정</th>
							<td>
								<input type="radio" name="kAdminFlag" value="T" <c:if test="${vo.kAdminFlag eq 'T'}">checked</c:if>><span>최고관리자</span>
								<input type="radio" name="kAdminFlag" value="F" <c:if test="${vo.kAdminFlag eq 'F'}">checked</c:if>><span>일반관리자</span>
							</td>							
						</tr>			
					</tbody>
				</table>
			</div>
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:memberUp();">저장</a>
					</li>
					<li>
						<a href="/mes/kw_admin_lf.do">목록</a>
					</li>
				</ul>
			</div>
					
	
</form>