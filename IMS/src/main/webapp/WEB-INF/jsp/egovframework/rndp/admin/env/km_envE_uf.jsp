<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="/jsp/kw_error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>기타관리</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;기타관리</p>
			</div>
		</div>
		<form name="writeform" method="post" action="km_env_eu.jsp"	enctype="multipart/form-data">
			<TABLE class="tbl_view">
				<tbody>
				<c:forEach var="list" items="${contactList}">
				<c:if test="${list.name == 'companyHomePage' }">
					<tr>
						<TH>홈페이지</th>
						<TD><input type="text" name="companyHomePage" value="${list.value }" size="50"></td>
					</tr>
				</c:if>
				<c:if test="${list.name == 'companyEmail' }">
					<tr>
						<TH>관리자 이메일</th>
						<TD><input type="text" name="companyEmail" value="${list.value }" size="50"></td>
					</tr>
				</c:if>
				<c:if test="${list.name == 'companyImage1' }">
					<tr>
						<TH>상단 로고</th>
						<TD>${list.value }<br/>
							<input type="file" name="companyImage1" value="${list.value }" size="50">
						</td>
					</tr>
				</c:if>
				<c:if test="${list.name == 'companyImage2' }">
					<tr>
						<TH>하단 로고</th>
						<TD>${list.value }<br/>
							<input type="file" name="companyImage2" value="${list.value }" size="50">
						</td>
					</tr>
				</c:if>
				<c:if test="${list.name == 'companyHomePage' }">
					<tr>
						<TH>하단 텍스트</th>
						<TD>
						<textarea name="companyText" cols="48" rows="5">${list.value }</textarea>
						</td>
					</tr>
				</c:if>
				</c:forEach>
				</tbody>
			</TABLE>
		  <div class="tbl_btn_right">
			<ul>
				<li>
					<a href="javascript:document.writeform.submit();">저장</a>
				</li>
				<li>
					<a href="javascript:document.writeform.reset();">취소</a>
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
</html>
