<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="javascript" src="/js/km_common.js"></script>
<script type="javascript" src='<c:url value="/js/km_env_patent.js"/>'></script>
</head>

<body class="content_bg" onLoad="document.writeform.title.focus();">
	<div id="contents">
	<div class="content_tit">
		<h2>특허수정</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;특허관리&nbsp;>&nbsp;특허수정</p>
		</div>
	</div>
		<form name="writeform" method="post" action='<c:url value="/admin/envPatentUpdate.do"/>' >
			<input type="hidden" name="key" value="${envPatentInfo.key }">
			<input type="hidden" name="rank" value="${envPatentInfo.rank }">
			<TABLE class="tbl_view">
				<TBODY>
					<TR>
						<TH>순 서</th>
						<TD>${envPatentInfo.rank }</td>
					</tr>
					<TR>
						<TH>TITLE OF THE INVENTION</TH>
						<TD><input type="text" name="title"
							value="${envPatentInfo.title }" maxLength="100" size="70"
							onKeyDown="return is_enter();"></td>
					</tr>
					<TR>
						<TH>PATENT NUMBER</TH>
						<TD><input type="text" name="number"
							value="${envPatentInfo.number }" maxLength="50"></td>
					</tr>
					<TR>
						<TH>REGISTRATION DATE</th>
						<TD><input type="text" name="date" value="${envPatentInfo.date }"
							maxLength="8"> (Ex:20130326)
					</tr>
					<TR>
						<TH>N.B.[nota bene]</TH>
						<TD><input type="text" name="nb" value="${envPatentInfo.nb }"
							maxLength="20"></td>
					</tr>
				</TBODY>
			</table>
		 <div class="tbl_btn_right">
			<ul>
				<li>
					<a href="<c:url value="javascript:insert_go();"/>" >	
					저장</a>
				</li>
				<li>
					<a href="<c:url value="javascript:insert_reset();"/>" >
				취소</a>
				</li>
				<li>
					<a href='<c:url value="/admin/envPatentList.do"/>'>
				목록</a>
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
</html>
