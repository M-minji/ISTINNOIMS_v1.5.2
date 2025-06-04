<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>파일 업로드</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/popup.css'/>" rel="stylesheet" type="text/css" />
</head>

<body>
	<table class="popup_top">
		<tr>
			<td class="tit">파일 업로드</td>
		</tr>
	</table>
	<table class="popup_content">
		<tr>
			<td>
				<form name="writeform" method="post" action="<c:url value='/admin/contentFileUpdate.do'/>" enctype="multipart/form-data">
					<input type="hidden" name="menuKey" value="${menu.menuKey }"> 
					<input type="hidden" name="root" value="${menu.root }"> 
					<input type="hidden" name="groupKey" value="${menu.groupKey }">
					<table class="tbl_view">
						<tr>
							<td width="100" height="30" bgcolor="f5f5f5" class="line_right"><b><font color="#565656">파일 선택</b></font></td>
							<td class="td_view"><input type="file" name="filename" size="35"></td>
						</tr>
						<tr>
							<td colspan="2" align="center" class="non_border"><input type="image" value="업로드" src="<c:url value='/images/admin/k_upload.gif'/>"></td>
						</tr>
					</table>
				</form>
				  <div class="tbl_btn_right">
					<ul>
						<li>
							<a href="javascript:self.close();">
								닫기
							</a>
						</li>
					</ul>
				</div>
		</table>
			
	
</body>
</html>
