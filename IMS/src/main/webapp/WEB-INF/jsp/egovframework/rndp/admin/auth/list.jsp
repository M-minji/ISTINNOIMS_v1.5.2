<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body class="content_bg">
<div id="contents">
	<div class="title">
		<h2>계정별 권한 관리</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;계정별 권한 관리</p>
		</div>
	</div>
	<div class="tbl_top">
				<ul class="tbl_top_left">
					<li>
						<c:if test="${fn:length(menu) >0}">				
							<form name="writeform" method="post" action="<c:url value='/admin/adminAuthUpdatefrm.do'/>">
								<input type="hidden" name="key" value="${key}">
									<a href="javascript:writeform.submit()">권한설정하기</a>
							</form>
						</c:if>
					</li>
				</ul>
				<ul class="tbl_top_right">
					<li>
						<form name="searchform" method="post" action="<c:url value='/admin/adminAuthList.do'/>">
						<span>계정 : </span>
						 <select name=key onChange="searchform.submit();">
							<c:forEach var="admin" items="${admin}" varStatus="status">
								<option value="${admin.key}" <c:if test="${admin.key eq key}">selected</c:if>>${admin.id }</option>
							</c:forEach>
						</select>
						</form>		
					</li>
				</ul>
			</div>
		<table class="tbl_list">
			<thead>
				<tr>
					<th width="50" scope=col>번 호</th>
					<th width="*" scope=col>메 뉴 명</th>
					<th width="100" scope=col>권 한</th>
				</tr>
			</THEAD>
			<tbody>
				<c:forEach var="menu" items="${menu}" varStatus="status">
				<tr onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''">
					<td>${status.count}</td>
					<td>${menu.menuName}</td>
<%-- 										<td><c:if test="${menu.flag == 'T'}">권한있음</c:if><c:if test="${menu.flag != 'T'}">권한없음</c:if></td> --%>
					<td>${menu.flagForm}</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(menu) == 0}">
				<tr>
					<td colspan="3" align="center">등록된 메뉴 목록이 없습니다.</td>
				</tr>
				</c:if>

			</tbody>
		</table>
	</div>					
</body>
</html>
