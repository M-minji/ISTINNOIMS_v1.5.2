<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>기본정보</title>
</head>
<body class="content_bg">

	<div id="contents">
	
		<div class="content_tit">
			<h2>기본정보</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;기본정보</p>
			</div>
		</div>
		
		<form name="writeform" id="envVO" method="post" action="<c:url value="/admin/envUpdate.do"/>"   >
			<TABLE class="tbl_view">
				<tbody>
				<c:forEach var="list" items="${envList}">
				<c:if test="${list.name == 'companyName' }">
					<tr>
						<TH>회사명</th>
						<TD>
							<input type="hidden" name="name" value="${list.name}"/>
							<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyCeo' }">
					<tr>
						<TH>대표자</th>
						<TD>
							<input type="hidden" name="name" value="${list.name}"/>
							<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyIncorpo' }">
					<tr>
						<TH>설립일</th>
						<TD>
							<input type="hidden" name="name" value="${list.name}"/>
							<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyPost' }">
					<tr>
						<TH>우편번호</th>
						<TD>
							<input type="hidden" name="name" value="${list.name}"/>
							<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyAddr' }">
					<tr>
						<TH>주소</th>
						<TD>
						<input type="hidden" name="name" value="${list.name}"/>
						<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyTel' }">
					<tr>
						<TH>대표전화</th>
						<TD>
						<input type="hidden" name="name" value="${list.name}"/>
						<input type="text" name="value" value="${list.value }">
						</td>
					</tr>
				</c:if>	
				<c:if test="${list.name == 'companyFax' }">
					<tr>
						<TH>팩스</th>
						<TD>
						<input type="hidden" name="name" value="${list.name}"/>
						<input type="text" name="value" value="${list.value }">
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