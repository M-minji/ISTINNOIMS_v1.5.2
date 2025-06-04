<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function is_enter(){
    if (window.event.keyCode == 13){
		return false;
    }
}

function insert_go(){
	with (document.writeform){
		if (kPositionName.value == ""){
			alert("부서명을 입력하세요.");
			kPositionName.focus();
			return false;
		}
	}
		document.writeform.submit();
}

function cancle(){
	$('#mloader').show();
	document.writeform.action = "/mes/user/kw_position_lf.do";
	document.writeform.submit();
}
</script>
</head>

<body>
<form name="writeform" method="post" action="/mes/user/kw_position_u.do">
	<input type="hidden" name="kPositionKey" id="kPositionKey"		value="<c:out value='${mesUserVO.kPositionKey}'/>">
		
		<div class="content">
			<div class="content_tit">
				<h2>부서 수정</h2>
			</div>
		</div>

		<div class="tbl_write_f">	
			<table>
				<tbody>
					<%-- <tr>
						<th>순 위</th>
						<td><c:out value='${mesUserVO.kPositionRank}' /></td>
					</tr> --%>
					<tr>
						<th>부서 명</th>
						<td><input type="text" name="kPositionName"
							value="${mesUserVO.kPositionName}" maxLength="50"
							onKeyDown="return is_enter();"></td>
					</tr>
				</tbody>
			</table>
		</div>
		
			<div class="tbl_btn_right">
				<ul>
					<li><a href="javascript:insert_go();">저장</a></li>
					<!-- <li><a href="javascript:insert_reset();">다시</a></li> -->
					<li>	<a href="javascript:cancle();">목록</a></li>
				</ul>
			</div>

		</form>
</body>
</html>
