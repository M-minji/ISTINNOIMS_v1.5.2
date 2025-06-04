<%-- <%@ page import="com.sun.mail.imap.protocol.Item"%> --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript" src='<c:url value="/js/km_common.js"/>'></script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="title">
			<h2>특허관리</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;특허관리</p>
		</div>
		</div>
		<div class="tbl_top">
				<ul class="tbl_top_left">
					<li>
					<a href='<c:url value="/admin/envPatentInsertfrm.do"/>' >특허추가</a>
					</li>
				</ul>
				<ul class="tbl_top_right">
					<li>
						<img src="<c:url value="/images/admin/admin_icon_E.gif"/>" align="absmiddle">
					</li>
					<li>
						<img src="<c:url value="/images/admin/admin_icon_M.gif"/>" align="absmiddle">
					</li>
					<li>
						<img src="<c:url value="/images/admin/admin_icon_D.gif"/>" align="absmiddle">
					</li>
				</ul>
			</div>
		<table class="tbl_list">
			<thead>
				<tr>
					<th>번 호</th>
					<th>TITLE OF THE INVENTION</th>
					<th>PATENT<br>NUMBER</th>
					<th>REGISTRATION<br>DATE</th>
					<th>N.B.<br>[nota bene]</th>
					<th>관 리</th>
				</tr>
			</thead>
			<TBODY>
				<c:forEach var="list" items="${patentList}">
					<tr onMouseOver="style.backgroundColor='#EAEAEA'"
						onMouseOut="style.backgroundColor=''">
						<td><c:out value="${list.rank}" /></td>
						<td><c:out value="${list.title}" /></td>
						<td><c:out value="${list.number}" /></td>
						<td><c:out value="${list.date}" /></td>
						<td><c:out value="${list.nb}" /></td>
						<td>
							<a href='<c:url value="/admin/envPatentUpdatefrm.do"/>?key=<c:out value='${list.key}' />' title="수정">
								<img src="<c:url value="/images/admin/icon_s_E.gif"/>" align="absmiddle"/>
							</a>&nbsp;
							<a href='<c:url value="/admin/envPatentOrderfrm.do"/>?rank=<c:out value='${list.rank}'/>&title=<c:out value='${list.title}'/>' title="순서변경">
								<img src="<c:url value="/images/admin/icon_s_M.gif"/>" align="absmiddle"/>
							</a>&nbsp;
							<a href='<c:url value="/admin/envPatentDelete.do"/>?key=<c:out value='${list.key}' />' title="삭제" onclick="return delete_list2();">
								<img src="<c:url value="/images/admin/icon_s_D.gif"/>" align="absmiddle"/>
							</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty patentList}">
					<tr height="22" bgcolor="#fff">
						<td colspan="6" align="center">등록된 특허 내용이 없습니다.</td>
					</tr>
				</c:if>
			</TBODY>
		</table>
	</div>
</body>
</html>
