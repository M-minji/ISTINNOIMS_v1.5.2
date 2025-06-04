<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script>
var idCheck = "F";
function checkStaffId() {
	var kAdminId = $.trim(document.getElementById("kAdminId").value);
	document.subfrm.kAdminId.value = kAdminId;
	if (kAdminId == "") {		/* 아이디 널값 체크 */
		alert("아이디를 입력하세요.");
		document.writeForm.kAdminId.focus();
	} else {
		$.ajax({			/* 아이디 중복체크 */
			type : "post",
			dataType : "json",
			url : "<c:url value='/popup/kw_adminIdChk.do'/>",
			data : $('#subfrm').serialize(),
			success : function(msg) { //응답이 성공 상태 코드를 반환하면 호출
				var resultFlag = msg.result.resultFlag;
				if (resultFlag == "T") {
					alert("사용 가능한 아이디 입니다.");
				} else {
					alert("중복된 아이디입니다.");
				}
				idCheck = resultFlag;
			},
			error : function(e) {
				alert('에러발생');
			}
		});
	}
}
function againCheckStaffId() {		/* 아이디 중복 체크 후 아이디를 변경하였을경우 다시 기본 값 셋팅 */
	idCheck = 'F';
}

function adminIn(){
	with (document.writeForm) {
		if (kAdminId.value == "") {
			alert("아이디를 입력하세요.");
			kAdminId.focus();
			return;
		}
		if (idCheck == "F") {
			alert("아이디를 확인 하세요. 중복된 아이디는 사용 할 수 없습니다.");
			kAdminId.focus();
			return;
		}
		if (kAdminName.value == "") {
			alert("이름을 입력하세요.");
			kAdminName.focus();
			return;
		}
		if (kAdminPwd.value == "") {
			alert("비밀번호를 입력하세요.");
			kAdminPwd.focus();
			return;
		}
		if (kAdminEmail.value == "") {
			alert("이메일을 입력하세요.");
			kAdminEmail.focus();
			return;
		}			
		if (kAdminTelePhone.value == "" ) {
			alert("핸드폰을 입력하세요.");
			kAdminTelePhone.focus();
			return;
		}	

	if (confirm("저장 하시겠습니까?")){
		document.writeForm.action = "/mes/kw_admin_i.do";
		document.writeForm.submit();
	}
}
}
</script>
<form name="writeForm" id="writeForm">		
	<input type="hidden" name="searchType" id="searchType" value="<c:out value='${mesUserVO.searchType}'/>">
	<input type="hidden" name="searchWord" id="searchWord" value="<c:out value='${mesUserVO.searchWord}'/>">
	<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${mesUserVO.pageIndex}'/>">
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="<c:out value='${mesUserVO.recordCountPerPage}'/>">
		<div class="content">
			<div class="content_tit">
				<h2>시스템 계정 관리 등록</h2>
			</div>
		</div>
			
	
		
			<div class="tbl_write_f">
				<table>
					<tbody>
						<tr>
							<th>I D</th>
							<td>
								<P>
									<input type="text" style='width:50%;' name="kAdminId" id="kAdminId" maxLength="50" onkeydown="againCheckStaffId()"  />
									<a class="mes_btn" onclick="javascript:checkStaffId()">선택</a>
								</P>
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" style='width:50%;' name="kAdminName" maxLength="50"/></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" style='width:50%;' name="kAdminPwd" maxLength="50" /></td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td><input type="text" style='width:50%;' name="kAdminTelePhone" maxLength="50" /></td>
						</tr>
						<tr>
							<th>E-Mail</th>
							<td><input type="text" style='width:50%;' name="kAdminEmail" maxLength="50" /></td>
						</tr>
						<tr>
							<th>설명</th>
							<td><input type="text" style='width:50%;' name="kAdminMemo" maxLength="50" /></td>
						</tr>
						<tr>
							<th>최고 관리자 설정</th>
							<td>
								<input type="radio" name="kAdminFlag" value="T" checked="checked"><span>최고관리자</span>
								<input type="radio" name="kAdminFlag" value="F"><span>일반관리자</span>
							</td>							
						</tr>			
					</tbody>
				</table>
			</div>
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:adminIn();">등록</a>
					</li>
					<li>
						<a href="/mes/kw_admin_lf.do">목록</a>
					</li>
				</ul>
			</div>
		
</form>
<form name="subfrm" id="subfrm">
	<input type="hidden" id="kAdminId" name="kAdminId">
</form>