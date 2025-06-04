<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="/jsp/kw_error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta name="viewport" content="width-device-width, initial-scale=1.0">

	<title>홈페이지 로그인</title>
	
	<link href="<c:url value='/css/main/login.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/css/main/mobile.css'/>" rel="stylesheet"	type="text/css" /><!-- 반응형 CSS -->
 	
</head>
<body onLoad="document.writeform.id.focus();" >
	<form name="writeform" id="adminVO" method="post" action="<c:url value='/jsp/login.do'/>">
		<div id="wrap">
	   		<div id="header_wrap">
		  		<div id="header">
		   			<div class="top">
						<div class="logo">
			  				<ul>
								<li><a href="/main.do"><img src="/images_main/rndpmain/logo.png" ></a></li>
			  				</ul>
						</div>
		   			</div>
		  		</div>
	   		</div>
			<div id="contentbox">
	   			<div id="ct_box">
	        		<span>LOGIN</span>
	            	<p>로그인페이지입니다.<br>아이디와 비밀번호를 입력하여 로그인해주세요.</p>
	        	</div>
	        
	        	<ul id="ct_box2">
	            	<li>아이디&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" size="25" tabindex="1" class="inputbox" maxlength="20"></li>
	               	<li>비밀번호 <input type="password" name="password" size="25" tabindex="2"class="inputbox" maxlength="20" ></li>
	               	<li><a href="/"><img src="/images_main/login/home2.png"> 홈으로 되돌아가기</a></li>
		           
	        	</ul>
	        	<div id="ct_box3">
	        		<a href="javascript:writeform.submit();"><img src="/images_main/login/btn_login2.png"></a>
	        	</div>
	   		</div>
		</div> 
	</form>
</body>










<%-- <head>
<title>관리자 로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/admin_style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/admin_board.css'/>" />
</head>
<body bgcolor="#ebebeb" onLoad="document.writeform.id.focus();" >
<div id="wrap">
  <div class="login_top">
  </div>
  <form name="writeform" id="adminVO" method="post" action="<c:url value='/jsp/login.do'/>">
  <div id="login">
    <div class="login">
      <table class="loginbox">
        <tbody>
          <tr>
            <td width="555"><img src="<c:url value='/images/login/login_text2.jpg'/>" /></td>
            <td>
              <table class="loginform">
				<tr height="30">
	              <th>아이디</th>
				  <td><input type="text" name="id" value="" tabindex="1" class="inputbox"></td>								
				  <td rowspan="2"><input type="image" src="<c:url value='/images/login/login_b_btn.gif'/>" tabindex="3"></td>
				</tr>
				<tr height="30">
				  <th>비밀번호</th>								
				  <td><input type="password" name="password" value="" tabindex="2"class="inputbox"></td>
                </tr>
			  </table><br/>
			  <table>
			  	<tr>
			  		<td><a href="/"><font color="#c8c8c8" >홈페이지로 돌아가기</font></a></td>
			  	</tr>
			  </table>
			</td> 
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  </form>
</div>
<c:if test="${error eq 'noAuth' }">
	<script type="text/javascript"> 
	/* 2015 05 13  최고관리자 정보를 노출할수 없으므로 오프라인으로 해결  */
		alert('작업 권한이 있는 메뉴가 없습니다.\r\n최고관리자에게 권한설정을 직접 요청하세요.');
	</script>
</c:if>
<c:if test="${error eq 'flag' }">
	<script type="text/javascript">
		alert('로그인 정보가 잘못되었습니다.\r\n다시 로그인 하세요.');
	</script>
</c:if>
<c:if test="${openerGb eq 'ok' }">
	<script type="text/javascript">
		window.opener.location.reload();
	</script>
</c:if>
</body> --%>
</html>
