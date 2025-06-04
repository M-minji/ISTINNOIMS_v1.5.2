<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>

<script type="text/javascript">

function fn_update(){
	var frm = document.frm;	
	if (frm.subject.value == ""){
		alert("제목을 입력하세요.");
		frm.subject.focus();
		return false;
	}

	if (frm.id.value == ""){
		alert("아이디를 입력하세요.");
		frm.id.focus();
		return false;
	}

	if (frm.name.value == ""){
		alert("이름을 입력하세요.");
		frm.name.focus();
		return false;
	}

	if (frm.password.value == ""){
		alert("비밀번호를 입력하세요.");
		frm.password.focus();
		return false;
	}
	if(confirm("저장하시겠습니까?")){
		frm.submit();
	}
}

</script>

<!-- 보드 내용시작 -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<form name="frm" id="frm" method="post" action="<c:url value='/boardUpdate.do'/>" enctype="multipart/form-data">
			<input type="hidden" name="menuKey" value="${key }"/>
			<input type="hidden" name="mngKey" value="${key }"/>
			<input type="hidden" name="boardKey" value="${boardUser.boardKey}">
			<input type="hidden" name="type" value="${boardmng.type }" />
			
				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
					<tr>
						<th width="100" scope="row">제목</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box" value="${boardUser.subject}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td width="250">
							<input type="text" id="id" name="id" value="${user.id}" size="20" maxlength="20" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
						<th width="100" scope="row">성명</th>
						<td>
							<input type="text" id="name" name="name" value="${user.name }" size="20" maxlength="50" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
					</tr>
					<tr>
						<th scope="row">비밀번호</th>
						<td>
							<input type="password" id="password" name="password" maxlength="10" size="20" class="box" value="${boardUser.password}" />
						</td>
						<th scope="row">E-mail</th>
						<td>
							<input type="text" id="email" name="email"  maxlength="100" size="20" class="box" value="${boardUser.email}" />
						</td>
					</tr>
					<c:if test="${boardmng.secretFlag == 'T'}">
					<tr>
						<th scope="row">비공개</th>
						<td colspan="3">
							<input type="radio" id="share" name="share" value="F" border="0" <c:if test="${boardUser.share == 'F' }">checked</c:if> />설정
							<input type="radio" id="share" name="share" value="T" border="0" <c:if test="${boardUser.share == 'T' }">checked</c:if> />해제
						</td>
					</tr>
					</c:if>
					
					<tr>
						<th scope="row">URL</th>
						<td colspan="3"><input type="text" id="homepage" name="homepage" value="${boardUser.homepage}" maxLength="100"  style="width:500px;"/></td>
					</tr>
					
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr height="35">
					<td align="right">						
						<input type="image" src="/images_main/v_ok.gif" onclick="fn_update();" /> <!--확인 btn-->
						<a href="javascript:insert_reset();"><img src="/images_main/v_cancle.gif" border="0" /></a> <!--취소 btn-->
						<a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=r"><img src="/images_main/v_list.gif" border="0" /></a> <!--목록 btn-->
						
					</td>
				</tr>
			</table></td>
	</tr>
</table>
	
