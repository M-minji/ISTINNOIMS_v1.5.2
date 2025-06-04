<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-chache"/>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="Pragma" content="no-cache"/>
<title>관리자</title>

<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous" /> -->
<link href="/css/mes/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/admin/admin_board.css" rel="stylesheet"	type="text/css" />
<link href="/css/admin/admin_main.css" rel="stylesheet"	type="text/css" />


<script	type="text/javascript" src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>"></script>
<script type="text/javascript" src="/js/kw_common.js"></script>
<script  type="text/javascript" src="/js/main/script.js"></script>




</head>













<div id="wrap"> 
		<div class="header"><t:insertAttribute name="header"/><div id="lnb_box" ><t:insertAttribute name="subHeader"/></div></div> 
		<div class="contents">
			<div class="content">
				<div class="lnb_left">
					 
					<ul class="lnb">
						<c:forEach var="item" items="${leftMenuResult}" varStatus="status">
								<c:if test="${item.depth == 0}">
									<li class="lnb_tit">
										<!-- <i class="fas fa-cogs"></i> -->
										<h1><c:out value='${item.name}' /></h1> 
									</li>
								</c:if> 
						
							
 								<c:if test="${item.depth != 0}">
									<li>
										<a href='<c:url value="${item.url}"/>?rootKey=${item.root}&menuKey=${item.key}&groupKey=${groupKey}'>
											<c:out value="${item.name}" />
										</a>
									</li>
								</c:if>

								</c:forEach> 
						  </ul>	 
						  
<%-- 		<div class="sub_menu">
					<ul>
						<c:forEach var="item" items="${leftMenuResult}" varStatus="status">
								<c:if test="${item.depth != 0}">
									<li>
										<a href='<c:url value="${item.url}"/>?rootKey=${item.root}&menuKey=${item.key}&groupKey=${groupKey}'>
											<c:out value="${item.name}" />
										</a>
									</li>
								</c:if>
								</c:forEach>
						  </ul>		
				</div>		 --%>
				
				</div>	
				<t:insertAttribute name="content"/>
			</div>
		</div>

	<div class="footer">
		<ul>
			<li></li>
			<li></li>
		</ul>
	</div>
	</div>   
</body>
</html>
