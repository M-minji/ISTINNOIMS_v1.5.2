<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="sub_container">
	<table width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20">&nbsp;</td>
			<td width="160" valign="top">
				<c:out value="${subMenu }" escapeXml="false"/>
			</td>
			<td width="30">&nbsp;</td>
			<td width="760" valign="top">
			<div id="sub_container2">
			<!--컨텐츠 시작-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" class="navigate">
					<img src="./images_smart/sub/navi_icon.gif" align="absmiddle" />
					<span class="navigate_s"><c:out value="${menuCategory}" escapeXml="false" /></span>
					</td>
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
						
						<c:choose>
						<c:when test="${content eq '' }">
							<table width="740" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100px">&nbsp;</td>
									<td>
										<table width="500" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													준비중입니다.	
												</Td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<c:out value="${content }" escapeXml="false"/>								
						</c:otherwise>
						</c:choose>
						
						</td>
					</tr>
					<tr>
						<td height="50">&nbsp;</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<!--컨텐츠 끝-->
</div>
