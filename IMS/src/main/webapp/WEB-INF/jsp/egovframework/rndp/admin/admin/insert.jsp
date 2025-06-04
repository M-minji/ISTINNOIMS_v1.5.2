<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	
	function insert_go(){
	
		if (document.adminVO.id.value == ""){
			alert("아이디를 입력하세요.");
			document.adminVO.id.focus();
			return;
		}

		if (document.adminVO.password.value.length < 4)
		{
			alert("패스워드는 4자 이상으로 입력하세요.");
			document.adminVO.password.focus();
			return;
		}

		if (document.adminVO.memo.value.length >= 1000)
		{
			alert("아이디 설명은 1000자 이내로 입력하세요.");
			document.adminVO.memo.focus();
			return;
		}

		if (confirm("저장 하시겠습니까?")){
			document.adminVO.submit();
		}
	}
	
	function insert_reset(){
		document.adminVO.reset();
		document.adminVO.id.focus();
	}
</script>
</head>

<body class="content_bg">
<div id="contents">
  <div class="content_tit">
  	<h2>관리자 계정 추가</h2>
  		<div class="path">
  			<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;관리자계정관리&nbsp;>&nbsp;관리자계정추가</p>
  		</div>
  </div>	
  <form name="adminVO" id="adminVO" method="post" action="<c:url value='/admin/adminInsert.do'/>">				        
  <table class="tbl_write">		 	
    <tbody>
      <tr>
        <th>성명</th>
		<td>
		  <input type="text" name="name" maxLength="20">
		</td>
	  </tr>
	  <tr>
        <th>아 이 디</th>
	    <td><input type="text" name="id" maxLength="20"></td>
	  </tr>
	  <tr>
        <th>비밀번호</th>
		<td><input type="password" name="password" maxLength="10"></td>
	  </tr>
	  <input type="hidden" name="group" value="1">
	  <tr>
	  	<th>핸드폰번호</th>
		<td><input type="text" name="telephone" maxLength="20"></td>
	  </tr>
	  <tr>
        <th>E-mail</th>
		<td><input type="text" name="email" maxLength="80"></td>
	  </tr>
	  <tr>
        <th>아이디 설명</th>
		<td><textarea name="memo" cols="68" rows="5"></textarea></td>
	  </tr>
	  <tr>	
		<th>최고 관리자 설정</th>
		<td>
		  <input type="radio" name="adminFlag" value="T">최고 관리자&nbsp;&nbsp;
		  <input type="radio" name="adminFlag" value="F" checked>일반 관리자
		</td>
	  </tr>
	</tbody>
  </table>
  <div class="tbl_btn_right">
	<ul>
		<li>
			<a href="javascript:insert_go();">저장</a>
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
