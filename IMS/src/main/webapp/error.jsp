<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    // 캐시 방지
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>유효하지 않은 접근입니다</title>

    <link href="${pageContext.request.contextPath}/css/egovframework/com/com.css" rel="stylesheet" type="text/css" />

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            position: relative;
            text-align: center;
            max-width: 800px;
            padding: 30px;
            border-radius: 15px;
        }

        .error-background {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: -1;
            opacity: 0.2; /* 투명도 */
            width: 100%;
            height: 100%;
            background: url('${pageContext.request.contextPath}/images/egovframework/com/cmm/sleepy_bear_large.png') no-repeat center;
            background-size: cover;
            border-radius: 15px;
        }

        .error-container h1 {
            font-size: 36px;
            color: #ff9900;
            margin: 20px 0;
        }

        .error-container p {
            font-size: 18px;
            color: #333;
            margin: 10px 0;
        }

        .countdown {
            font-weight: bold;
            color: #ff0000;
            margin-top: 20px;
            font-size: 20px;
        }

        .buttons {
            margin-top: 30px;
        }

        .buttons a {
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            padding: 10px 20px;
            margin: 0 10px;
            border-radius: 5px;
            font-size: 16px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .buttons a:hover {
            background-color: #0056b3;
        }
    </style>

    <script type="text/javascript">
       document.addEventListener("DOMContentLoaded", function() {
            var seconds = 3; // 시작 시간
            var countdownElement = document.getElementById('countdown');

            var countdownTimer = setInterval(function() {
                seconds--;
                countdownElement.textContent = seconds;

                if (seconds <= 0) {
                    clearInterval(countdownTimer);
                    // 이동할 페이지 URL을 지정합니다.
                    var currentUrl = window.location.href;
		            var referrerUrl = document.referrer; // 이전 페이지 URL
					console.log(referrerUrl)
		            if (referrerUrl.includes("/mes/login.do")) {
					    // 관리자 페이지에서 왔을 경우
					    if (window.opener && !window.opener.closed) {
					        // 팝업창인 경우 창 닫기
					        window.close();
					    } else {
					        // 일반 창인 경우 리다이렉션
					        window.location.href = '${pageContext.request.contextPath}/mes/loginfrm.do';
					    }
					} else {
					    // 기본 리다이렉션
					    if (window.opener && !window.opener.closed) {
					        // 팝업창인 경우 창 닫기
					        window.close();
					    } else {
					        // 일반 창인 경우 리다이렉션
					        window.location.href = '${pageContext.request.contextPath}/mes/main.do';
					    }
					}
                }
            }, 1000); // 1초마다 실행
        });
    </script>
</head>

<body>
    <div class="error-container">
        <!-- 귀여운 캐릭터 이미지 -->
<%--         <img src="${pageContext.request.contextPath}/images/egovframework/com/cmm/sleepy_bear.png" /> --%>
        <img src="/images/images/innost_error.png"  style="width: 700px; height: auto;"/>

        <h1>유효하지 않은 접근입니다</h1>
        <p>존재하지 않는 주소를 입력하셨거나 요청하신 페이지가 삭제되었을 수 있습니다.</p>
        <p>
            <strong><span id="countdown">3</span>초 뒤에 페이지 이동합니다.</strong>
        </p>

        <div class="buttons">
            <a href="${pageContext.request.contextPath}/mes/main.do">메인 페이지로</a>
            <a href="javascript:history.back()">이전 페이지로</a>
        </div>
    </div>
</body>
</html>
