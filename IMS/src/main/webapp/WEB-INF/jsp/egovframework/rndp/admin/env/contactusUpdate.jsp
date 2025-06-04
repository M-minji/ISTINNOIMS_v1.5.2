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
			<h2>기타문의관리</h2>	
			<div class="path">
				<p>HOME&nbsp;기업정보관리&nbsp;>&nbsp;기타문의관리</p>
			</div>
		</div>
		<form name="writeform" id="envVO" method="post" action="<c:url value="/admin/contactusUpdate.do"/>"	>
			<TABLE class="tbl_view">
			<tbody>
			<c:forEach var="list" items="${envList}">
			<c:if test="${list.name == 'inquiryProduct' }">
				<tr>
					<TH>제품문의</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			<c:if test="${list.name == 'inquiryQuality' }">
				<tr>
					<TH>C/S, 품질문의</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			<c:if test="${list.name == 'inquiryPurchase' }">
				<tr>
					<TH>구매관련문의</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			<c:if test="${list.name == 'inquiryEmail' }">
				<tr>
					<TH>E-mail</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			<c:if test="${list.name == 'inquiryTel' }">
				<tr>
					<TH>Tel</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			<c:if test="${list.name == 'inquiryFax' }">
				<tr>
					<TH>Fax</th>
					<TD>
					<input type="hidden" name="name" value="${list.name}"/>
					<input type="text" name="value" value="${list.value }" size="50">
					</td>
				</tr>
			</c:if>	
			</c:forEach>
			</tbody>
			</table>
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
