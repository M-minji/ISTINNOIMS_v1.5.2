<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="icon" href="/images/login/favicon.ico" type="image/x-icon"> -->
<link href="/css/mes/common.css" rel="stylesheet" type="text/css" /> 
<link href="/css/mes/login.css" rel="stylesheet" type="text/css" />  
<link rel="stylesheet" type="text/css" href='/css/mes/pretendard.css'>
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<title>로그인</title>
</head>


<script type="text/javascript">

function modal1(message) {
	new jBox('Modal', {
	    height: 200,
	    title: message,
	    blockScrollAdjust: ['header'],
	    content:'',
	    overlay: false,   
	    addClass: 'no-content-modal',
	    position: {
	        x: 'center',
	        y: 'center'
	      },
	      offset: {
	        y: -130
	      }
	  }).open();
  }


$(document).ready(function(){  
	window.addEventListener("beforeunload", function (event) {
		sessionStorage.setItem("num", Number(sessionStorage.getItem("num"))+ 1 );
	});    	
	$('#mloader').show();
	$('#kStaffId').focus();
	history.pushState(null, null, location.href);
	window.onpopstate = function () {
	    history.go(1);
	};
	
	if("${confirm}" == "O" && sessionStorage.getItem("num") == "1"){
		var str = "";
		if(Number("${count}") == 100){
			str = "<p>아이디 또는 비밀번호가 잘못되었습니다.</p><p>5회 틀리면 계정이 잠금됩니다.</p>";	
		}else{
			if(Number("${count}") >= 5){
				str = "<p>로그인 실패로 계정이 잠금 상태 입니다.</p><p>관리자에게 잠금 해제를 요청하세요.</p>";
			}else{
				str = "<p>아이디 또는 비밀번호가 잘못되었습니다.</p><p>5회 틀리면 계정이 잠금됩니다.</p>";
			}
		}
		modal1(str);
	}
});

/* 직원등록신청 팝업 */
function staffReq() {
	var url = "/popup/mes/km_staff_req_if.do";
	window.open(url, "test", "toolbar=no, location=no, directories=no, "
			+ "status=no, menubar=no, scrollbars=yes, "
			+ "resizable=yes, top=0, left=0, "
			+ "width=900, height=750");
	/* win_open(url); */
}    
	
function sendLogin(){
	
	  var inputElement = document.getElementById('kStaffId');
	    if (!inputElement.value.trim()) { // 빈값 또는 공백 체크
	        modal1("아이디를 입력하세요.");
	        inputElement.focus(); // 입력창으로 포커스 이동
	        return false;
	    }
	    
	    var passwordElement = document.getElementById('password');
	    if (!passwordElement.value.trim()) { // 빈값 또는 공백 체크
	    	modal1("비밀번호를 입력하세요.");
	        passwordElement.focus(); // 입력창으로 포커스 이동
	        return false;
	    }
	    
	sessionStorage.removeItem("num");
	
	
	writeform.submit();
}

function fn_keyDown(){
	if(event.keyCode == 13){
		sendLogin();
	}			
}
	
</script>
<style>
	.no-content-modal .jBox-content {
  		display: none; 
	}

	.no-content-modal .jBox-title {
		padding-bottom: 10px;
	}
	
	.no-content-modal .jBox-title {
	  	color: white;
	}
	.jBox-Modal {
	  background: #4869fb;
	  border-radius: 8px;
   	  overflow: hidden;
	}
</style>

<body class="login_type_a"><!--  login_type_b 클래스 부여시 b타입 -->  
	<div class="login_wrap">
		<header>
			<h1>
				<img src='/cmm/fms/getImage.do?atchFileId=${mainLogo}&fileSn=0'   alt="로고" onerror="this.src='/images/images/innost_logo.png'"/>
			</h1>
		</header>
		<section class="login_area">
			<div class="login_inner">
				<ul class="login_tabs">
					<li class="on">회원 로그인</li>
					<li><a href="/admin/loginfrm.do">관리자 로그인</a></li> 
				</ul>
				<form name="writeform" method="post" action="/mes/login.do">   
					<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>
					<div class="login_form">
						<div class="inp" style="height:48px">
							<input type="text" tabIndex="1" maxLength="20" name="kStaffId" value="" placeholder="아이디를 입력하세요." id="kStaffId"/>
							<label for="userId"></label>
						</div>
						<div class="inp" id="inputWrapPw" style="height:48px;">
							<input onKeydown="fn_keyDown()" tabIndex="2" data-placement="bottom"  name="kStaffPassword"  title="password" type="password" id="password" class="password" placeholder="비밀번호를 입력하세요." maxLength="20">
							<label for="password"></label> 
						</div> 
						<button type="button" class="login_btn" onclick="sendLogin()">로그인</button>
					</div>
					<div class="register">
						<span>아직 계정이 없으신가요?</span>
						<a href="javascript:staffReq()" tabIndex="4">회원가입</a>
					</div>
				</form>
			</div>
		</section>
	</div>
</body>
</html>

 