<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/js/km_env.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.tools.min.js"></script>
<script type="text/javascript">
function chkYearMonth(){
	var checky = document.getElementById("checky");
	var yValue =checky.options[checky.selectedIndex].value;
	document.writeform.year.value = yValue; 
	
	var checkm = document.getElementById("checkm");
	var mValue =checkm.options[checkm.selectedIndex].value;
	document.writeform.month.value = mValue; 

	insert_go();
}
</script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>연혁추가</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;연혁관리&nbsp;>&nbsp;연혁추가</p>	
			</div>
		</div>
		<form name="writeform" method="post" action="<c:url value='/admin/HistoryInsert.do'/>">
			<TABLE class="tbl_view">
				<tbody>
					<TR>
						<TH>기간</TH>
						<TD>
						<select id="year" name="year">
						
								<c:forEach var="y" items="${yearList }" step="1">
									<option value="<c:out value='${y}'/>" <c:if test="${y eq year }">selected="selected"</c:if> >
										<c:out value="${y}" />
									</option>
								</c:forEach>
						</select>년 &nbsp;&nbsp;
						<select id="month" name="month">
				
								<c:forEach var="m" items="${monthList }" step="1">
									<option value="<c:out value='${m}'/>" <c:if test="${m eq month}">selected="selected"</c:if> >
										<c:out value="${m}" />
									</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<TR>
						<TH>내용</TH>
						<TD colspan="3"><textarea name="content" cols="60" rows="7" maxlength="990"></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:document.writeform.submit();">
						저장</a>
					</li>
					<li>
						<a href="javascript:document.writeform.reset();">취소</a>
					</li>
					<li>
						<a href="<c:url value='/admin/envHistory.do'/>">
						목록</a>
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
