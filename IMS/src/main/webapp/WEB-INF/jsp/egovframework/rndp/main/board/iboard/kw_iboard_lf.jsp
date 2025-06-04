<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<script src="/js/jquery/jquery-3.7.1.min.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script type="text/javascript">
	function fn_guestList(pageNo) {
		document.writeform.pageIndex.value = pageNo;
		document.writeform.action = "<c:url value='/webMenu.do'/>";
		document.writeform.submit();
	}

	function goPasswordDialogPopup(boardKey) {

		$('#password').val('');
		$('#boardKey').val(boardKey);

		$("#dialog").dialog({
			draggable : true,
			width : 430,
			modal : true,
			title : "비밀번호 입력",
			position : [ 500, 250 ]
		});
	}

	function goPasswordDialogClose() {
		$("#dialog").dialog('close');
	}

	$(function() {
		$("#password").keydown(function(key) {
			if (key.keyCode == 13) {
				goSubmit();
			}
		});

	});

	function goSubmit() {
		if ($('#password').val() == '') {
			alert("비밀번호를 입력하세요.");
			$('#password').focus();
		} else {
			$('form[name=frm]').attr('action', '<c:url value='/webMenu.do'/>')
					.submit();

		}
	}

	function goViewImgDialogPopup(strImg, strSubj) {

		var str = "<img src='" + strImg
				+ "' width='100%' height='100%' border='0' alt=''>";
		document.getElementById("imgLayer").innerHTML = str;

		$("#imgLayer").dialog({
			draggable : true,
			width : 600,
			modal : true,
			title : strSubj,
			position : [ 500, 200 ]
		});
	}
</script>

<!-- <div class="sub_container"> -->

<!-- 	<table width="1000" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 		<tr> -->
<!-- 			<td width="20">&nbsp;</td> -->
<!-- 			<td width="160" valign="top"> -->
<%-- 				<!-- left 메뉴 --> <c:out value="${subMenu }" escapeXml="false" /> <!-- left 메뉴 --> --%>
<!-- 			</td> -->
<!-- 			<td width="30">&nbsp;</td> -->
<!-- 			<td width="30" class="contents_bg">&nbsp;</td> -->
<!-- 			<td width="760" valign="top"> -->
				<!--컨텐츠 시작-->
<!-- 				<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 					<tr> -->
<!-- 						<td height="30" class="navigate"> -->
<!-- 							<img src="./images_smart/sub/navi_icon.gif" align="absmiddle" />  -->
<%-- 							<span class="navigate_s"><c:out value="${menuCategory}" escapeXml="false" /></span> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						타이틀 -->
<%-- 						<td height="50" class="contents_title">${menuNm}</td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td height="30">&nbsp;</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<div id="sub_container2"> -->

<form name="writeform" method="post" action="<c:url value='/webMenu.do'/>">
	<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" /> 
	<input type="hidden" id="key" name="key" value="${key }" /> 
	<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" />

	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td>
				<table cellspacing="0" cellpadding="0" class="board_list2" border="0" style="width: 105%;">
					<c:set var="jspStep" value="4" />
					<c:set var="countMax" value="${boardcount}" />

					<c:choose>
						<c:when test="${countMax != 0}">
							<c:forEach var="i" begin="0" end="${countMax - 1}" step="${jspStep}">
								<tr align="center">
									<c:forEach var="j" begin="0" end="${jspStep - 1}">
										<c:set var="imgcnt" value="${i + j}" />
										<c:choose>
											<c:when test="${imgcnt < countMax}">
												<!--값 채우기  -->
												<c:forEach var="board" items="${boardList}" varStatus="status">
													<c:if test="${status.index eq imgcnt}">
														<td>

															<table width="160" border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td height="170" class="box">
																		<c:if test="${strReadAuth == 'T' || strReadAuth == 'M' || strReadAuth == 'A'}">
																			<a href="<c:url value="/webMenu.do"/>?key=${key}&boardKey=${board.boardKey}&pageGb=v">
																		</c:if> 
																		<c:if test="${strReadAuth == 'S' }">
																			<a href="javascript:javascript:goPasswordDialogPopup('${board.boardKey}');">
																		</c:if> 
																		<c:if test="${strReadAuth == 'F' }">
																			<a href="javascript:alert('읽기 권한이 없습니다.')">
																		</c:if> 
																				<img src="<c:url value="/imageDownload.do"/>?boardKey=${board.boardKey}&type=${boardmng.type}" width="160" height="170" border="0" alt="${board.subject }"> 
																			</a>
																	</td>
																</tr>
																<tr>
																	<td class="subject">
																		<c:if test="${strReadAuth == 'T' || strReadAuth == 'M' || strReadAuth == 'A'}">
																			<a href="<c:url value="/webMenu.do"/>?key=${key}&boardKey=${board.boardKey}&pageGb=v">
																		</c:if> 
																		<c:if test="${strReadAuth == 'S' }">
																			<a href="javascript:javascript:goPasswordDialogPopup('${board.boardKey}');">
																		</c:if> 
																		<c:if test="${strReadAuth == 'F' }">
																			<a href="javascript:alert('읽기 권한이 없습니다.')">
																		</c:if> ${board.subject }
																		</a>
																	</td>
																</tr>
															</table>

														</td>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<!--공백채우기 -->
												<td>&nbsp;</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>

					<c:if test="${empty boardList}">
						<tr>
							<td>등록된 게시물이 없습니다</td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center" valign="top">&nbsp;</td>
		</tr>
		<tr>
			<td><table width="100%" cellspacing="0" cellpadding="0"
					border="0" class="board_page">
					<tr>
						<td><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="board_search">
					<c:if test="${strWriteAuth == 'T' || strWriteAuth == 'M' || strWriteAuth == 'A' }">
						<tr>
							<td colspan="4" align="center">
								<a href="<c:url value='/webMenu.do'/>?key=${key}&groupKey=${groupKey }&pageGb=c">
									<img src="<c:url value='/images_main/btn_write.gif'/>" align="absmiddle" border="0">
								</a>
							</td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10">&nbsp;</td>
		</tr>
	</table>
</form>
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->


<!-- 보드 내용 끝 -->


<div id="dialog" title="Basic dialog" style="display: none; border: 1px solid;">

	<form name="frm" id="frm" method="post">
		<input type="hidden" name="key" id="key" value="${key}" /> 
		<input type="hidden" name="boardKey" id="boardKey" /> 
		<input type="hidden" name="type" id="type" value="${boardmng.type}" /> 
		<input type="hidden" name="pageGb" id="pageGb" value="v" /> 
		<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" /> 
		<input type="hidden" name="authFlag" id="authFlag" value="s" /> 
		<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" />

		<table style="width: 100%;">
			<tr align="center" height="26">
				<td>비밀번호를 입력하세요.</td>
			</tr>
			<tr align="center" height="26">
				<td><input type="password" id="password" name="password" /></td>
			</tr>
			<tr align="center" height="26">
				<td>
					<a href="javascript:goSubmit();"><img src="<c:url value='/images_in/k_write2.gif'/>" /></a> 
					<a href="javascript:goPasswordDialogClose();"><img src="<c:url value='/images_in/k_cancel.gif" border="0"'/>" /></a>
				</td>
			</tr>
		</table>
	</form>
</div>

<div id="imgLayer" title="Basic dialog" style="display: none; border: 1px solid; z-index: 1000">
	<a href="#" onclick="window.close()">닫기</a>
</div>

<!-- </div> -->

