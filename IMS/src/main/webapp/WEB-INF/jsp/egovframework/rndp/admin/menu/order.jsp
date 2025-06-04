<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_menu.js'/>"></script>
</head>

<body class="content_bg">
<div id="contents">
  <div class="content_tit">
  	<h2>메뉴순서변경</h2>
  		<div class="path">
  			<p>HOME&nbsp;>&nbsp;메뉴관리&nbsp;>&nbsp;메뉴순서변경</p>
  		</div>
	</div>		
		
		<div class="detail_box">
				<h3>메뉴 순서변경 요령</h3>
			<ul>
				<li><img src="<c:url value='/images/admin/blt.gif'/>" />현재 선택된 메뉴가 대상메뉴에서 선택된 메뉴 다음에 위치하게됩니다.</li>
				<li><img src="<c:url value='/images/admin/blt.gif'/>" />대상메뉴를 선택하시고 변경을 클릭하세요.</li>
			</ul>
		</div>
		
		<form name="searchform" id="menuBeanVO" method="post" action="<c:url value='/admin/menuOrder.do'/>">
		<input type="hidden" name="root" value="<c:out value='${menu.root}' />" />
		<input type="hidden" name="groupKey" value="<c:out value='${menu.groupKey}' />">
		<table class="tbl_view">
			<tbody> 
	              <tr>
	                  <th>선택된 메뉴</th>
						<td>${menu.name}</td>
					</tr>
					<tr>
	                  	<th>대상메뉴 선택</th>
						<td><select name="root2">
							<option value="" selected>메뉴 선택</option>
								<c:forEach var="item" items="${menuList}">
									<option value="<c:out value='${item.root}' />"><c:out value="${item.name}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</TBODY>
		</table>
			 
			 <div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:move_go();">저장</a>
					</li>
					<li>
						<a href="javascript:move_reset();">취소</a>
					</li>
					<li>
						<a href="<c:url value='/admin/menuList.do'/>?groupKey=<c:out value='${menu.groupKey}' />">목록</a>
					</li>
				</ul>
			</div>
			
		</form>
	</div>
</body>
</html>
