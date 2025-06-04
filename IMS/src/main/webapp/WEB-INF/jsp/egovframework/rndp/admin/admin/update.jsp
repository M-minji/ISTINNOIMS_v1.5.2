<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript">
function insert_u()
{
	with (document.writeform)
	{
		
		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return;
		}

		if (memo.value.length >= 1000)
		{
			alert("아이디 설명은 1000자 이내로 입력하세요.");
			memo.focus();
			return;
		}
		if (confirm("수정 하시겠습니까?")){
			submit();
		}else{
			return;
		}
		
	}
}

function insert_reset(){
	document.writeform.reset();
	document.writeform.id.focus();
}

</script>
</head>

<body class="content_bg">
<div id="contents">
  <div class="content_tit">
  	<h2>관리자 계정 수정</h2>	
		  <div class="path">
 	 			<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;관리자계정관리&nbsp;>&nbsp;관리자계정수정</p>
  		</div>
  	</div>
  <form name="writeform" method="post" action="<c:url value='/admin/adminUpdate.do'/>">
  <input type="hidden" name="key" value="${result.key}">
  <table class="tbl_view">
   	<tbody>
	  <tr>
		<th>성명</th>
		<td>${result.name}</TD>
	  </tr>
		<tr>
		<th>아 이 디</th>
		<td><input type="text" name="id" value="${result.id}" maxLength="20" style="width:200px;"></td>
	  </tr>
	  <input type="hidden" name="group" value="${result.group}">
	  <%-- <tr>
		<th>그 룹 명</th>
		<td><input type="text" name="group" value="<%=admin.getGroup()%>" maxLength="50" style="width:200px;"></td>
	  </tr> --%>
	  <tr>
		<th>핸드폰번호</th>
		<td><input type="text" name="telephone" value="${result.telephone}" maxLength="20" style="width:200px;"> (SMS 서비스 이용시 필요)</td>
	  </tr>
	  <tr>
		<th>E-mail</th>
		<td><input type="text" name="email" value="${result.email}" maxLength="80" style="width:400px;"></td>
	  </tr>
	  <tr>
	    <th>아이디 설명</th>
		<td><textarea name="memo" cols="68" rows="5" style="width:600px;">${result.memo}</textarea></td>
	  </tr>
	  <tr>
		<th>최고 관리자 설정</th>
		<td>
		  <input type="radio" name="adminFlag" value="T" <c:if test="${result.adminFlag == 'T'}">checked</c:if>>최고 관리자&nbsp;&nbsp;
		  <input type="radio" name="adminFlag" value="F" <c:if test="${result.adminFlag == 'F'}">checked</c:if>>일반 관리자
		</td>
	  </tr>
	</tbody>
  </table>
  <div class="tbl_btn_right">
	<ul>
		<li>
			<a href="javascript:insert_u();">저장</a>
		</li>
		<li>
			<a href="javascript:insert_reset();">수정</a>
		</li>
		<li>
		<a href="<c:url value='/admin/adminList.do'/>">목록</a>
		</li>
	  </ul>
	 </div>
  </form>
</div>
</body>
</html>
