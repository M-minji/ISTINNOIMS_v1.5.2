<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	function insert_pgo(){
	
		if (document.writeform.password.value == ""){
			alert("현재 패스워드를 입력하세요.");
			document.writeform.password.focus();
			return;
		}
		if (document.writeform.newpassword.value == "")
		{
			alert("변결할 패스워드를 입력하세요.");
			document.writeform.newpassword.focus();
			return;
		}
		if (document.writeform.nppassword.value == "")
		{
			alert("확인 패스워드를 입력하세요.");
			document.writeform.nppassword.focus();
			return;
		}
		
		if (document.writeform.newpassword.value.length < 4)
		{
			alert("패스워드는 4자 이상으로 입력하세요.");
			document.writeform.newpassword.focus();
			return;
		}
		
		if (document.writeform.nppassword.value != document.writeform.newpassword.value)
		{
			alert("변경 패스워드와 확인패스워드가 다릅니다. 확인바랍니다. ");
			document.writeform.newpassword.focus();
			return;
		}		
	
		
		if (confirm("변경 하시겠습니까?")){
			document.writeform.submit();
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
  		<h2>관리자 계정비밀번호변경</h2>	
  <div class="path">
  		<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;관리자계정관리&nbsp;>&nbsp;관리자계정비밀번호변경</p>
  		</div>
  </div>
  <form name="writeform" id="adminVO" method="post" action="<c:url value='/admin/adminPwdChange.do'/>">
  <input type="hidden" name="key" value="${result.key}">
  <table class="tbl_view">
   	<tbody>
	  <tr>
		<th>성명</th>
     	<td>${result.name}</td>
	  </tr>
	<tr>
		<th>아 이 디</th>
		<td><input type="text" name="id" value="${result.id}" maxLength="20" style="width:200px;"></td>
	  </tr>
	  <tr>
		<th>현재 비밀번호</th>
		<td><input type="password" name="password" value="" maxLength="10" style="width:200px;"></td>
	  </tr>
	  <tr>
		<th>변경 비밀번호</th>
		<td><input type="password" name="newpassword" value="" maxLength="10" style="width:200px;"></td>
	  </tr>
	  <tr>
	    <th>비밀번호 확인</th>
		<td><input type="password" name="nppassword" value="" maxLength="10" style="width:200px;"></td>
	  </tr>
	  <input type="hidden" name="group" value="KASI">
	</tbody>
  </table>
  <div class="tbl_btn_right">
	<ul>
		<li>
			<a href="javascript:insert_pgo();">저장</a>
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
