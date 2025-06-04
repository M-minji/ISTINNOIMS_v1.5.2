<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src='<c:url value="/js/km_common.js"/>'></script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>채용정보</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;채용정보</p>
			</div>
		</div>
	<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<a href='<c:url value="/admin/recruitInsertfrm.do"/>' >
					채용정보 추가
				</li>
			</ul>
			<ul class="tbl_top_right">
				<li>
					<img src="<c:url value="/images/admin/admin_icon_E.gif"/>">
				</li>
				<li>
					<img src="<c:url value="/images/admin/admin_icon_V_s.gif"/>">
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
					<th width="10%" scope=col>번 호</th>
					<th width="*" scope=col>내용</th>
					<th width="15%" scope=col>시작일</th>
					<th width="15%" scope=col>종료일</th>
					<th width="15%" scope=col>관리</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="list" items="${envRecruitList}" varStatus="i">
					<fmt:formatDate var="wdate" value="${list.writeDate}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초" />
					<tr title="<c:out value='${wdate}' />" onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''">
						<td><c:out value="${i.count}" /></td>
						<td><c:out value="${list.recruit1}" escapeXml="false" /></td>
						<td><c:out value="${list.startDate}" /></td> 
 						<td><c:out value="${list.endDate}" /></td> 
						<td>
						<a href='<c:url value="/admin/recruitUpdatefrm.do"/>?key=<c:out value='${list.key}' />' title="수정">
						<img src="<c:url value="/images/admin/icon_s_E.gif"/>"></a>&nbsp;
							<c:choose>
								<c:when test="${list.visible eq 'T'}">
									<a href="<c:url value='/admin/recruitActive.do'/>?key=<c:out value='${list.key}'/>&visible=F" title="숨김">
									<img src="<c:url value="/images/admin/icon_s_H.gif"/>"></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/recruitActive.do'/>?key=<c:out value='${list.key}'/>&visible=T" title="보임">
									<img src="<c:url value="/images/admin/icon_s_V.gif"/>"></a>
								</c:otherwise>
							</c:choose>
							<a href='<c:url value="/admin/recruitDelete.do"/>?key=<c:out value='${list.key}' />' title="삭제" onclick="return delete_list2();">
							<img src="<c:url value="/images/admin/icon_s_D.gif"/>"></a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty envRecruitList}">
					<tr>
						<td colspan="5" align="center">등록된 목록이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>
