<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript">

function menuChange(root,groupKey,name){
	document.frm.root.value = root;
	document.frm.groupKey.value = groupKey;
	document.frm.name.value = name;
	document.frm.action = "/admin/menuOrderfrm.do";
	document.frm.submit();
}

function menuChange2(root,groupKey,name,depth,step){
	document.frm.root.value = root;
	document.frm.groupKey.value = groupKey;
	document.frm.name.value = name;
	document.frm.depth.value = depth;
	document.frm.step.value = step;
	document.frm.action = "/admin/menuSubOrderfrm.do";
	document.frm.submit();
}

</script>

</head>

<body class="content_bg">
	<div id="contents">
		<div class="title">
			<h2>메뉴관리</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;메뉴관리</p>
			</div>
		</div>

			
	<div class="tbl_top">
			<ul class="tbl_top_left">
				<li>
					<a href="/admin/menuInsertfrm.do?key=0&rootKey=${rootKey}&menuKey=${menuKey}&groupKey=${groupKey}">
						최상위 메뉴추가
					</a>
				</li>
			</ul>
			<ul class="tbl_top_right">
				<li>	
					<img src="<c:url value='/images/admin/admin_icon_S.gif'/>">
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_E.gif'/>">
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_V_s.gif'/>">
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_H.gif'/>">
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_O.gif'/>"> 
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_F.gif'/>"> 
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_M.gif'/>"> 
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_D.gif'/>">
				</li>
				<%-- <li>
					<form name="searchform" method="post" action="<c:url value='/admin/menuList.do'/>">
						<input type="hidden" name="rootKey" value="${rootKey}">
						<input type="hidden" name="menuKey" value="${menuKey}">
						<select name="groupKey" onChange="searchform.submit();">
							<c:forEach var="item" items="${groupList}">
								<option value="<c:out value='${item.groupKey}'/>" <c:if test='${item.groupKey eq groupKey}'>selected</c:if>>
									<c:out value="${item.name}" />
								</option>
							</c:forEach>
						</select>
					</form>
				</li> --%>
			</ul>
		</div>

<form name="frm" id="frm" method="post" >
	<input type="hidden" name="key" 	id="key" 	value="0">
	<input type="hidden" name="ref" 	id="ref" 	value="0">
	<input type="hidden" name="root" 	id="root" 	value="0">
	<input type="hidden" name="depth" 	id="depth" 	value="0">
	<input type="hidden" name="step" 	id="step" 	value="0">
	<input type="hidden" name="groupKey" 	id="groupKey" 	value="0">
	<input type="hidden" name="name" 	id="name" >		
		
		<table class="tbl_list">
			<thead>
				<tr>
					<th scope=col>번 호</th>
					<th scope=col>메 뉴 명</th>
					<th scope=col>스크린ID</th>
					<th scope=col>메뉴타입</th>
					<th scope=col>화면표시</th>
					<th scope=col>관리자</th>
					<th scope=col>관 리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${menuList}" varStatus="i">
					<tr title="<c:out value='${item.description}' />" onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''">
						<td><c:out value="${i.count}" /></td>
						<td class="tit">${item.depthImg}<c:out value="${item.nameForm}" escapeXml="false" /></td>
						<td><c:out value="${item.screenId}" /></td>
						<td><c:out value="${item.typeForm}" escapeXml="false" /></td>
						<td><c:out value="${item.visibleForm}" escapeXml="false" /></td>
						<td><c:out value="${item.adminId}" /></td>
						
						<c:set var="link" value="key=${item.key}&groupKey=${item.groupKey}" />
						<c:set var="link2" value="root=${item.root}&groupKey=${item.groupKey}&name=${item.name}" />
						<td>
							<c:if test="${vo.adminFlag eq 'T'}">
								<a href="<c:url value='/admin/menuInsertfrm.do'/>?<c:out value='${link}' />" title="하위메뉴추가">
									<img src="<c:url value='/images/admin/icon_s_S.gif'/>">
								</a>
							</c:if>
							<a href="<c:url value='/admin/menuUpdatefrm.do'/>?<c:out value='${link}' />" title="메뉴수정">
								<img src="<c:url value='/images/admin/icon_s_E.gif'/>">
							</a> 
							<c:choose>
								<c:when test="${item.visible eq 'T'}">
									<a href="<c:url value='/admin/menuVisit.do'/>?<c:out value='${link}' />&visible=F" title="메뉴숨김"><img src="<c:url value='/images/admin/icon_s_H.gif'/>"></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/menuVisit.do'/>?<c:out value='${link}' />&visible=T" title="메뉴보임"><img src="<c:url value='/images/admin/icon_s_V.gif'/>"></a>
								</c:otherwise>
							</c:choose> 
							<c:choose>
								<c:when test="${item.statistic eq 'F'}">
									<a href="<c:url value='/admin/menuStatis.do'/>?<c:out value='${link}' />&statistic=T"
										title="접속통계기능활성"><img src="<c:url value='/images/admin/icon_s_O.gif'/>"></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/admin/menuStatis.do'/>?<c:out value='${link}' />&statistic=F"
										title="접속통계기능비활성"><img src="<c:url value='/images/admin/icon_s_F.gif'/>"></a>
								</c:otherwise>
							</c:choose> 
							<c:choose>
								<c:when test="${item.depth eq 0}">
									<a onclick="menuChange('${item.root}','${item.groupKey}','${item.name}' );" title="메뉴순서변경">
									<img src="<c:url value='/images/admin/icon_s_M.gif'/>"></a>
								</c:when>
								<c:otherwise>
									<a onclick="menuChange2('${item.root}','${item.groupKey}','${item.name}', '${item.depth}', '${item.step}' );" title="메뉴순서변경">
									<img src="<c:url value='/images/admin/icon_s_M.gif'/>"></a>
								</c:otherwise>
							</c:choose> 
							<c:if test="${vo.adminFlag eq 'T'}">
								<a href="<c:url value='/admin/menuDelete.do'/>?<c:out value='${link}' />" title="메뉴삭제" onclick="return delete_list();">
									<img src="<c:url value='/images/admin/icon_s_D.gif'/>">
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty menuList}">
					<tr>
						<td colspan="6" align="center">등록된 메뉴 목록이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
</form>
		
	  <div class="tbl_btn_right">
		<ul>
			<li>
				<a>메뉴업테이트를 클릭하셔야 홈페이지의 메뉴가 변경 적용됩니다.</a>
				</li>
			<li>	
				<a href="<c:url value='/admin/matrixList.do'/>?groupKey=${groupKey}">
					메뉴정보업데이트
				</a>
			</li>
		</ul>
	</div>
	</div>
</body>
</html>
