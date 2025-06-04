<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript" src="/js/km_common.js"></script>
<script language="javascript" src="/js/km_image.js"></script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="title">
			<h2>연혁관리</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;연혁관리</p>
		</div>
		</div>
		<div class="tbl_top">
				<ul class="tbl_top_left">
					<li>
						<a href="<c:url value='/admin/HistoryInsertfrm.do'/>">연혁 추가</a>
					</li>
				</ul>
				<ul class="tbl_top_right">
					<li>			
						<img src="<c:url value="/images/admin/admin_icon_E.gif"/>">
					</li>
					<li>
						<img src="<c:url value="/images/admin/admin_icon_V.gif"/>">
					</li>
					<li>
						<img src="<c:url value="/images/admin/admin_icon_H.gif"/>">
					</li>
					<li>
						<img src="<c:url value="/images/admin/admin_icon_D.gif"/>">
					</li>
				</ul>
			</div>	
		<table class="tbl_list">
			<thead>
				<tr>
					<th>번 호</th>
					<th>년도</th>
					<th>월</th>
					<th>내용</th>
					<th>표 시</th>
					<th>관 리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${envHistoryList}" varStatus="i">

					<tr bgcolor="" title=""	onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''">
						<td><c:out value="${i.count}" /></td>
						<td><c:out value="${list.year}" escapeXml="false" /></td>
						<td><c:out value="${list.month}" escapeXml="false" /></td>
						<td><c:out value="${list.content}" escapeXml="false" /></td>
						<td><c:out value="${list.visibleForm}" escapeXml="false" /></td>
						<td>
						<a href="<c:url value='/admin/HistoryUpdatefrm.do'/>?key=<c:out value='${list.key}'/>"title="수정">
							<img src="<c:url value="/images/admin/icon_s_E.gif"/>"/>
						</a>
						<c:choose>
							<c:when test="${list.visible eq 'T'}">
								<a href='<c:url value='/admin/HistoryVisibleUpt.do'/>?key=<c:out value='${list.key}'/>&visible=F' title="숨김">
								<img src="<c:url value="/images/admin/icon_s_H.gif"/>"/></a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value='/admin/HistoryVisibleUpt.do'/>?key=<c:out value='${list.key}'/>&visible=T' title="보임">
								<img src="<c:url value="/images/admin/icon_s_V.gif"/>"/></a>
							</c:otherwise>
						</c:choose>
								<a href='<c:url value='/admin/HistoryDelete.do'/>?key=<c:out value='${list.key}'/>' title="삭제" onclick="return delete_list2();">
								<img src="<c:url value="/images/admin/icon_s_D.gif"/>"/></a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty envHistoryList}">
					<tr height="22" bgcolor="#fff">
						<td colspan="6" align="center">등록된 목록이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>
