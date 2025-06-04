<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"       prefix="t"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(주)E&P</title>
<link href="/css/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/sub.css" rel="stylesheet"	type="text/css" />
<link href="/css/board.css" rel="stylesheet"	type="text/css" />
<script src="/js/jquery/jquery-1.11.0.min.js"	type="text/javascript"></script>
<script src="/js/jquery/jquery.slides.min.js"	type="text/javascript"></script>
<script type="text/javascript" src="/js/kw_common.js"></script>
<script  type="text/javascript" src="/js/main/script.js"></script>

</head>
<body>
	<div class="wrap">
		<div class="header"><t:insertAttribute name="header"/></div>
		<div class="subHeader"><t:insertAttribute name="subHeader"/></div>
		<div class="container"><t:insertAttribute name="content"/></div>		
		<div class="footer"><t:insertAttribute name="footer"/></div>		
	</div>
</body>
</html>