<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="/mes/main.do"> 
	<img src="/images/btn/path_icon.gif"> 
</a>
<c:out value="${mesMenuInfo.menuCategory}" escapeXml="false"/>