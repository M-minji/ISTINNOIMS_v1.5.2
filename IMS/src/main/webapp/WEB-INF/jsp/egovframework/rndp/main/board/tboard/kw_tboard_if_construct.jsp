<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/js/kw_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<!-- 보드 내용시작 -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="/jsp/boardInsert.do">
				<input type="hidden" name="menuKey" value="${key}" /> 
				<input type="hidden" name="mngKey" value="${boardmng.key }"/>
				<input type="hidden" name="boardKey" value="${board.boardKey}" /> 
				<input type="hidden" name="depth" value="${board.depth }" />
				<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
				<input type="hidden" name="fileSize" id="fileSize" value="${boardmng.fileSize }" />
				<input type="hidden" name="fileNumber" id="fileNumber" value="${boardmng.fileNumber }" />
				<input type="hidden" name="fileFlag" id="fileFlag" value="${boardmng.fileFlag }" />
				<input type="hidden" name="smartStr" id="smartStr" value=""/>
				
				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">

					<tr>
						<th width="120px">* 제 목</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box" />
						</td>
					</tr>
					<tr>
						<th width="120px">* 성 명</th>
						<td colspan="3">
							<input type="text" id="name" name="name" maxlength="50" class="box" size="70"  <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
					</tr>
					<tr>
						<th width="120px">* 이메일</th>
						<td colspan="3">
							<input type="text" id="email" name="email" maxlength="100" size="70" class="box" />
						</td>
					</tr>	
					<tr>
						<th width="120px">전 화</th>
						<td colspan="3">
							<input type="text" id="comp_tel" name="comp_tel" maxlength="13" size="70" class="box" />
						</td>
					</tr>				
					<tr>
						<th width="120px">* 휴대폰</th>
						<td colspan="3">
							<input type="text" id="comp_fax" name="comp_fax" maxlength="13" size="70" class="box" />
						</td>
					</tr>
					<tr>
						<th width="120px">내 용</th>
						<td colspan="3">
							<textarea id="content" name="content" cols="70" rows="10" style="font-size: 13px;"></textarea>
						</td>
					</tr>
					<tr>
						<th width="120px">* 비밀번호</th>
						<td colspan="3">
							<input type="password" id="password" name="password" maxlength="10" size="70" class="box">
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	
	
	
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr height="35">
				<td>
				</td>
				<td align="center">
					<a href="#" onClick="javascript:fn_insert();"><img src="/images_main/v_ok.gif" border="0" /></a> <!-- 저장 btn -->		
					<a href="javascript:history.back(-1);"><img src="/images_main/v_cancle.gif" border="0" /></a> <!-- 취소 btn -->
					<a href="<c:url value='/webMenu.do'/>?key=${key}"><img src="/images_main/v_list.gif" border="0" /></a> <!--목록 btn-->
				</td>
			</tr>
		</table>
	</tr>
	
</table>


<script type="text/javascript">
function fn_insert() {

	var frm = document.frm;
	
	if (frm.subject.value == "") {
		alert("제목을 입력하세요.");
		frm.subject.focus();
		return false;
	}

	if (frm.name.value == "") {
		alert("이름을 입력하세요.");
		frm.name.focus();
		return false;
	}
	
	if (frm.email.value == "") {
		alert("이메일을 입력하세요.");
		frm.email.focus();
		return false;
	}

	if (frm.comp_fax.value == "") {
		alert("휴대폰을 입력하세요.");
		frm.comp_fax.focus();
		return false;
	}
	
	if (frm.password.value == "") {
		alert("비밀번호를 입력하세요.");
		frm.password.focus();
		return false;
	}
	
	if (confirm("저장하시겠습니까?")) {
		frm.submit();
	}
}
</script>


				