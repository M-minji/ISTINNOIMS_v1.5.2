<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>선택하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/popup.css'/>" rel="stylesheet" type="text/css" />
<script language="JavaScript">
<!--
function check()
{
	with (document.searchform)
	{
		if (positionKey.value == "")
		{
			alert("부서를 선택하세요.");
			return false;
		}
		else
		{
			submit();
		}
	}
}


function selecter()
{
	
	with (opener.writeform)
	{
		if("<c:out value='${gubun}' />" == "admin"){
			staffKey.value = "<c:out value='${staffKey}' />";
			name.value = "<c:out value='${vo.iclass}' /> <c:out value='${vo.name}' />";
			email.value = "<c:out value='${vo.email}' />";
			telephone.value = "<c:out value='${vo.mobile}' />";
			group.value = "<c:out value='${vo.name}' />";
		}
	}

	self.close();
}
//-->
</script>
</head>

<body>
	<table class="popup_top">
		<tr>
			<td class="tit">관리자 선택하기</td>
		</tr>
	</table>
	<table class="popup_content">
		<form name="searchform" id="searchform" method="post" action="/web/admin/adminStaff.do">
					<input type="hidden" name="num" value="<c:out value='${num}' />">
					<input type="hidden" name="gubun" value="<c:out value='${gubun}' />">
					<table class="tbl_view">
						<tr>
							<th>부서정보</th>
							<td>
								<select name="positionKey"  onChange="searchform.submit();">
									<option value="">== 부서 ==</option>
									<c:forEach var="item" items="${positionList}">
										<c:set var="sel" value="" />
										<c:if test="${item.key eq positionKey}">
											<c:set var="sel" value="selected" />
										</c:if>
										<option value="<c:out value='${item.key}' />"
											<c:out value='${sel}' />>
											<c:out value="${item.name}" />
										</option>
									</c:forEach>
								</select> 
								<select name="staffKey" onChange="searchform.submit();">
									<option value="">== 선택 ==</option>
									<c:forEach var="item" items="${staffList}">
										<c:set var="sel" value="" />
										<c:if test="${item.staffKey eq staffKey}">
											<c:set var="sel" value="selected" />
										</c:if>
										<option value="<c:out value='${item.staffKey}' />"
											<c:out value='${sel}' />>
											<c:out value="${item.name}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>직 급</th>
							<td><c:out value="${vo.iclass}" /></td>
						</tr>
					</table>
				</form>
				  <div class="tbl_btn_right">
					<ul>
						<li>
							<a href="javascript:selecter();">저장</a> 
						</li>
						<li>
							<a href="javascript:self.close();">닫기</a>
						</li>
					</ul>
				</div>
			
	</table>
</body>
</html>
