<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>

<script language="JavaScript">
function fn_insert(){
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
	
	if (frm.homepage.value == ""){
		alert("URL을 입력하세요.");
		frm.homepage.focus();
		return false;
	}
	
	if(confirm("저장하시겠습니까?")){
		frm.submit();
	}
	
}

</script>

<div class="sub_container">
	<table width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20">&nbsp;</td>
			<td width="160" valign="top">
				<!-- left 메뉴 --> <c:out value="${subMenu }" escapeXml="false" /> <!-- left 메뉴 -->
			</td>
			<td width="30">&nbsp;</td>
			<td width="30" class="contents_bg">&nbsp;</td>
			<td width="760" valign="top">
				<!--컨텐츠 시작-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="30" class="navigate"><img
							src="./images_smart/sub/navi_icon.gif" align="absmiddle" /> <span
							class="navigate_s"><c:out value="${menuCategory}"
									escapeXml="false" /></span></td>
					</tr>
					<tr>
						<!-- 타이틀 -->
						<td height="50" class="contents_title">${menuNm}</td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
					</tr>
					<tr>
						<td>
<div id="sub_container2">						
		<!-- 보드 내용시작 -->
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td>
					<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="<c:url value='/boardInsert.do'/>">
						<input type="hidden" name="menuKey" value="${key}" />
						<input type="hidden" name="mngKey" value="${boardmng.key }">
						<input type="hidden" name="boardKey" value="${board.boardKey}" />
						<input type="hidden" name="type" value="${boardmng.type }" />            <!-- 파일결로 구분을 위해서 추가 -->
						
						<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
							<tr>
								<th width="100" scope="row">제목</th>
								<td colspan="3">
									<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box" />
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
									<input type="password" id="password" name="password" maxlength="10" size="20" class="box" />
								</td>
								<th scope="row">E-mail</th>
								<td>
									<input type="text" id="email" name="email" value="" maxlength="100" size="20" class="box" />
								</td>
							</tr>
							<c:if test="${boardmng.secretFlag == 'T'}">
							<tr>
								<th scope="row">비공개</th>
								<td colspan="3">
									<input type="radio" id="share" name="share" value="F" border="0" />설정
									<input type="radio" id="share" name="share" value="T" border="0" checked="checked" />해제
								</td>
							</tr>
							</c:if>
							
							<tr>
								<th scope="row">URL</th>
								<td colspan="3"><input type="text" id="homepage" name="homepage" value="" maxLength="100"  style="width:500px;"/></td>
							</tr>
							
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr height="35">
				<td align="right">						
					<input type="image" src="./images_smart/board/ok.gif" onclick="fn_insert();" /> <!--확인 btn-->
					<a href="javascript:insert_reset();"><img src="./images_smart/board/cancle.gif" border="0" /></a> <!--취소 btn-->
					<a href="<c:url value='/webMenu.do'/>?key=${key}"><img src="./images_smart/board/list.gif" border="0" /></a> <!--목록 btn-->
					
				</td>
			</tr>
		</table></td>
</tr>
</table> 

			</td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
	</table>
	<!--컨텐츠 끝-->	
</div>