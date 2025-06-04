<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>▒▒▒  eGovFrame Potal 온라인 지원 포탈  ▒▒▒</title>

<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css" />

<script language="javascript">
document.addEventListener("DOMContentLoaded", function(){
	var seconds = 3; // 시작 시간
	var countdownElement = document.getElementById('countdown');

	var countdownTimer = setInterval(function() {
	    seconds--;
	    countdownElement.textContent = seconds;

	    if (seconds <= 0) {
	        clearInterval(countdownTimer);
	        // 이동할 페이지 URL을 지정합니다.
	        window.location.href = '/mes/main.do';
	    }
	}, 1000); // 1초마다 실행
});
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><br />
    <br />
    <br />
    <table width="600" border="0" cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/images/egovframework/com/cmm/blue_bg.jpg">
      <tr>
        <td align="center"><table width="100%" border="0" cellspacing="9" cellpadding="0">
          <tr>
            <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left"><img src="${pageContext.request.contextPath}/images/egovframework/com/cmm/er_logo.jpg" width="379" height="57" /></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>

              <tr>
                <td align="center"><table width="520" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="74" rowspan="2" align="center"><img src="${pageContext.request.contextPath}/images/egovframework/com/cmm/danger.jpg" width="74" height="74" /></td>
                    <td width="399" align="left" class="lt_text2">
                    	HTTP 404 Error<br/><br/>
                    	<span id="countdown">3</span>초 뒤에 페이지 이동합니다.
                    </td>
                  </tr>
                </table>
                  <table width="500" border="0" cellspacing="2" cellpadding="2">
                                  </table></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>
            </table>
              <br /></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
