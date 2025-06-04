<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/css/mes/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mes_board.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mes_main.css" rel="stylesheet"	type="text/css" />

<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script> 
<script type="text/javascript" src="/js/kw_common.js"></script> 
<script type="text/javascript" src="/js/main/script.js"></script>

<script>
</script>
<title>통합유지관리시스템</title>
</head>
<body>
	<div class="wrap">
		<div class="header"><t:insertAttribute name="header"/></div>
		<div id="main_container">
<!-- 			 <div id="left"> -->
<%-- 				<t:insertAttribute name="leftMenu" /> --%>
<!-- 			</div>  -->
			<t:insertAttribute name="content"/>
		</div>
		<%-- <div class="footer"><t:insertAttribute name="footer"/></div> --%>
	</div>
</body>
</html>


