<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/admin/admin_main.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/admin/admin_board.css'/>" />
<script language="javascript">
	function bluring() {
		if (event.srcElement.tagName == "A"
				|| event.srcElement.tagName == "IMG")
			document.body.focus();
	}
	document.onfocusin = bluring;
</script>
</head>

<body>
		
	<c:if test="${!empty adminMenuTopList}">
			<div id="all_nav">
				<div class="nav">
						<ul class="gnb">
							<c:forEach var="result" items="${adminMenuTopList}"	varStatus="status">
								<li>
									<a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${result.root}&menuKey=${result.key}">${result.name}</a>
									<ul class="m_gnb_submenu">
										<c:forEach var="subMenu" items="${adminMenuResultAll}"	varStatus="index">
											<c:if test="${result.root  eq   subMenu.root }">
												<li>
													<a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${subMenu.root}&menuKey=${subMenu.key}">
														${subMenu.name}
													</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>						
								</li>
							</c:forEach>
						</ul>
				</div>
			</div>
		</c:if>	


	</body>

</html>





<%-- <body <c:if test="${!empty leftMenuResult}">class="left_bg"</c:if>>

		<c:forEach var="item" items="${leftMenuResult}" varStatus="status">
				<c:if test="${item.depth == 0}">
					<div class="lnb_tit">
						<i class="fas fa-cogs"></i>
						<h3><c:out value='${item.name}' /></h3>
					</div>
				</c:if>
				<c:if test="${item.depth != 0}">
					<ul class="lnb">
						<li>
							<a href='<c:url value="${item.url}"/>?rootKey=${item.root}&menuKey=${item.key}&groupKey=${groupKey}'>
								<c:out value="${item.name}" />
							</a>
						</li>
					</ul>
				</c:if>
		</c:forEach>

	</body> --%>