<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>영업관리</title>
<link href="/css/sales/board.css" rel="stylesheet" type="text/css" />
<link href="/css/sales/common.css" rel="stylesheet" type="text/css" />
<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/js/kw_common.js" type="text/javascript"></script>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<t:insertAttribute name="header" />
		</div>
		<div class="sub_bg"></div>
	</div>
	<div class="container">
		<div id="left">
			<t:insertAttribute name="leftMenu" />
		</div>
		<div id="contents">
			<div class="path">
				<t:insertAttribute name="menuTitle" />
			</div>
			<t:insertAttribute name="content" />
		</div>
	</div>
	<div class="footer">
		<t:insertAttribute name="footer" />
	</div>
</body>
</html>


