<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function update_go(){
		if (confirm("변경 하시겠습니까?")){
			document.writeform.submit();
		}
	}
	
	function insert_reset(){
		document.writeform.reset();
	}
</script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>계정별 권한 관리</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;계정별
				권한 관리&nbsp;>&nbsp;권한설정</p>
			</div>
		</div>
		<div class="tbl_top">
				<ul class="tbl_top_right">
					<li>
					<form name="searchform" method="post" action="<c:url value='/admin/adminAuthUpdatefrm.do'/>">
					<span>계정 : </span>
					 <select name=key onChange="searchform.submit();">
						<c:forEach var="admin" items="${admin}" varStatus="status">
							<option value="${admin.key}" <c:if test="${admin.key eq key}">selected</c:if>>${admin.id }</option>
						</c:forEach>
					</select>
					</form>		
				</li>
			</ul>
		</div>
		<form name="writeform" id="adminAuthVO" method="post" action="<c:url value='/admin/adminAuthUpdate.do'/>">
			<input type="hidden" name="adminKey" value="${key}">
			<table class="tbl_list">
				<thead>
					<tr>
						<th width="50" scope=col>번 호</th>
						<th width="*" scope=col>메 뉴 명</th>
						<th width="300" scope=col>권 한</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="menu" items="${menu}" varStatus="status">
					<input type="hidden" name="strMenuKey" id="" value="${menu.menuKey}">
					
					<tr align="" height="22" bgcolor=""
						onMouseOver="style.backgroundColor='#EAEAEA'"
						onMouseOut="style.backgroundColor=''">
						<td>${status.index+1}</td>
						<td>${menu.menuName}</td>
						<td>
							  <label><input type="radio" name="flag${menu.menuKey}" value="T" <c:if test="${menu.flag == 'T'}">checked</c:if>> 권한있음</label>&nbsp;&nbsp;&nbsp;
							  <label><input type="radio" name="flag${menu.menuKey}" value="F" <c:if test="${menu.flag == 'F' || menu.flag == null}">checked</c:if>> 권한없음</label>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>

		</form>
	  <div class="tbl_btn_right">
			<ul>
				<li>
					<a href="javascript:update_go();">확인</a>
				</li>
				<li>
					<a href="javascript:writeform.reset();">취소</a>
				</li>
				<li>
					<a href="<c:url value='/admin/adminAuthList.do'/>?key=${key}">목록</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
