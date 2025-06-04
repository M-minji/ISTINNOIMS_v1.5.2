<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/kw_error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>관리자 로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/admin/login.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mes/common.css'/>" />
<script type="text/javascript">
	function fn_keyDown(event){
		if(event.keyCode == 13){
			login();
		}			
	}
	function login() {
		writeform.submit();
	}
</script>

</head>
<body onLoad="document.writeform.id.focus();" class="login_type_b"><!--  login_type_b 클래스 부여시 b타입 -->  
	<div class="login_wrap">
		<header>
			<h1>            
				<img src='/cmm/fms/getImage.do?atchFileId=${mainLogo}&fileSn=0'  alt="로고" onerror="this.src='/images/images/innost_logo.png'"/>             
			</h1>
		</header>
		<section class="login_area">
			<div class="login_inner">
				<ul class="login_tabs">
					<li><a href="/mes/main.do">회원 로그인</a></li>
					<li class="on">관리자 로그인</li> 
				</ul>
				<form name="writeform" id="adminVO" method="post" action="<c:url value='/admin/login.do'/>"> 
					<div class="login_form">
						<div class="inp" >
							<input type="text" name="id" value="" placeholder="아이디를 입력하세요." tabindex="1" class="inputbox"  required>
							<label for="userId"></label>
						</div>
						<div class="inp" id="inputWrapPw">
							<input type="password" name="password" value="" placeholder="비밀번호를 입력하세요."  tabindex="2" id="password" class="password" onKeydown="javascript:fn_keyDown(event)" required>
							<label for="password"></label>  
						</div> 
						<button type="button" class="login_btn" onclick="login()">로그인</button>
					</div>
				</form>
			</div>
		</section>
	</div>

	<c:if test="${error eq 'noAuth' }">
		<script type="text/javascript"> 
		/* 2015 05 13  최고관리자 정보를 노출할수 없으므로 오프라인으로 해결  */
			alert('작업 권한이 있는 메뉴가 없습니다.\r\n최고관리자에게 권한설정을 직접 요청하세요.');
		</script>
	</c:if>
	<c:if test="${error eq 'flag' }">
		<script type="text/javascript">
			alert('Admin로그인 정보가 잘못되었습니다.\r\n다시 로그인 하세요.');
		</script>
	</c:if>
	<c:if test="${openerGb eq 'ok' }">
		<script type="text/javascript">
			window.opener.location.reload();
		</script>
	</c:if>
</body>
</html>
