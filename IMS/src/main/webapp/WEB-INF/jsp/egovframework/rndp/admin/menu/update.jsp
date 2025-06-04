<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_menu.js'/>"></script>
</head>

<body class="content_bg">
<div id="contents">
  <div class="content_tit">
  	<h2>메뉴수정</h2>
 	 <div class="path">
 	 	<p>HOME&nbsp;>&nbsp;메뉴관리&nbsp;>&nbsp;메뉴수정</p>
 	 </div>
  </div>
  <jsp:include page="km_admin_txt.jsp" flush="true" />
				<form name="writeform" id="menuBeanVO" method="post" action="<c:url value='/admin/menuUpdate.do'/>">
				<input type="hidden" name="key" value="<c:out value='${key}' />" />
				<input type="hidden" name="groupKey" value="<c:out value='${groupKey}' />" />
				<input type="hidden" name="statistic" value="F" />
				<input type="hidden" name="permit" value="F"   />
				<table class="tbl_view">
				<thead>
					<%-- <tr>
						<th>그 룹 명</th>
						<td>${group}</td>
					</tr> --%>
					<tr>
						<th>메 뉴 명</th>
						<td>${category}<input type="text" name="name" value="${menuName}" maxLength="50" /></td>
					</tr>
					<tr>
						<th>페이지 유무</th>
						<td><input type="radio" name="exist" value="T" />있음
							<input type="radio" name="exist" value="F" />없음
						</td>
					</tr>
								<c:choose>
									<c:when test="${menu.exist eq 'T'}">
										<c:set var="flag" value="0" />
									</c:when>
									<c:otherwise>
										<c:set var="flag" value="1" />
									</c:otherwise>
								</c:choose>
								
								<script type="text/javascript">
								writeform.exist[<c:out value="${flag}" />].checked = true;
								</script>
				<tr>
					<th>화면표시 여부</th>
					<td><input type="radio" name="visible" value="T" />표시함
						<input type="radio" name="visible" value="F" />숨김
					</td>
				</tr>
								<c:choose>
									<c:when test="${menu.visible eq 'T'}">
										<c:set var="flag" value="0" />
									</c:when>
									<c:otherwise>
										<c:set var="flag" value="1" />
									</c:otherwise>
								</c:choose>
								<script type="text/javascript">
								writeform.visible[<c:out value="${flag}" />].checked = true;
								</script>
								<tr>
									<th>운영 형태</th>
									<td><input type="radio" name="type" value="C" onClick="type_check()" />일반페이지
										<input type="radio" name="type" value="B" onClick="type_check()" />게시판
										<input type="radio" name="type" value="P" onClick="type_check()" />프로그램링크
										<input type="radio" name="type" value="L" onClick="type_check()" />사이트링크
									</td>
								</tr>
								<tr>
									<th>게시판 선택</th>
									<td><select name="boardKey" disabled>
										<option value="" selected>게시판선택</option>
										<c:forEach var="item" items="${boardList}" varStatus="i">
											<option value="${item.key}"  <c:if test="${menu.boardKey == item.key }">selected</c:if>  >${item.name}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>스크린ID</th>
									<td><input type="text" name="screenId" value="<c:out value='${menu.screenId}' />"/></td>
								</tr>
								<tr>
									<th>기존,신규,변경</th>
									<td>
										<select name="screenHistory" > 
											<option value="신규" <c:if test="${menu.screenHistory == '신규' }">selected</c:if>>신규</option>
											<option value="기존" <c:if test="${menu.screenHistory == '기존' }">selected</c:if>>기존</option>
											<option value="변경" <c:if test="${menu.screenHistory == '변경' }">selected</c:if>>변경</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>프로그램 링크</th>
									<td><input type="text" name="program" value="<c:out value='${menu.program}' />" maxLength="100" size="50" disabled /></td>
								</tr>
								<tr>
									<th>사이트링크 URL</th>
									<td><input type="text" name="link" value="<c:out value='${menu.link}' />" maxLength="100" size="50" disabled /></td>
								</tr>
								<tr>
									<th>관리자 선택</th>
									<td>
										<c:set var="admin_id" value="${fn:split(menu.adminStr, ',')}" />
										<c:set var="admin_id_check" value=""/>
										<c:forEach var="item" items="${adminList}" varStatus="i">
											<c:forEach var="id" items="${admin_id }">
												<c:if test="${fn:trim(id) == fn:trim(item.id)}">
													<c:set var="admin_id_check" value="checked" />
												</c:if>
											</c:forEach>
											
											<input type="checkbox" name="adminStr" value="${item.id}" <c:if test="${admin_id_check == 'checked' || i.index == 0}">checked</c:if>  /> ${item.id}
											<c:set var="admin_id_check" value=""/>
										</c:forEach>
										&nbsp;&nbsp;&nbsp;[&nbsp;<input type="checkbox" name="inherit" value="T" />상위관리자 상속&nbsp;]
										<c:if test="${menu.inherit eq 'T'}">
											<script type="text/javascript">
											writeform.inherit.checked = true;
											</script>
										</c:if>
									</td>
								</tr>
<!-- 								<tr> -->
<!-- 									<th>접근권한 설정</th> -->
<!-- 									<td> -->
<!-- 									<input type="radio" name="permit" value="T" onClick="permit_check()" />설정 -->
<!-- 									<input type="radio" name="permit" value="F" onClick="permit_check()" />해제	&nbsp; -->
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${menu.groupKey eq 2}"> --%>
<!-- 												<select name="level" disabled> -->
<!-- 													<option value="100">부서선택</option> -->
<%-- 													<c:forEach var="item" items="${positionList}"> --%>
<%-- 														<option value="<c:out value='${item.rank}' />"><c:out value="${item.name}" /></option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 												<script type="text/javascript"> -->
<%-- // 												writeform.level.value = "<c:out value='${menu.level}' />"; --%>
<!-- 												</script> -->
<%-- 											</c:when> --%>
<%-- 											<c:when test="${menu.groupKey eq 3}"> --%>
<!-- 												<select name="level" disabled> -->
<!-- 													<option value="100" selected>등급선택</option> -->
<%-- 													<option value="1" <c:if test="${menu.level == 1 }">selected</c:if>>시솝</option> --%>
<%-- 													<option value="2" <c:if test="${menu.level == 2 }">selected</c:if>>부시솝</option> --%>
<%-- 													<option value="3" <c:if test="${menu.level == 3 }">selected</c:if>>정회원</option> --%>
<%-- 													<option value="4" <c:if test="${menu.level == 4 }">selected</c:if>>준회원</option> --%>
<!-- 												</select> -->
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<!-- 											<select name="level" disabled> -->
<!-- 												<option value="100">등급선택</option> -->
<%-- 												<c:forEach var="item" items="${levelList}"> --%>
<%-- 													<option value="<c:out value='${item.rank}' />"> --%>
<%-- 														<c:out value="${item.subject}" /> --%>
<!-- 													</option> -->
<%-- 												</c:forEach> --%>
<!-- 												</select> 이상 -->
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<th>접속통계 여부</th> -->
<!-- 									<td><input type="radio" name="statistic" value="T" />설정 -->
<!-- 										<input type="radio" name="statistic" value="F" />해제 -->
<!-- 									</td> -->
<!-- 								</tr> -->
								<c:choose>
									<c:when test="${menu.statistic eq 'T'}">
										<c:set var="flag" value="0" />
									</c:when>
									<c:otherwise>
										<c:set var="flag" value="1" />
									</c:otherwise>
								</c:choose>
								<script type="text/javascript">
								writeform.statistic[<c:out value="${flag}" />].checked = true;
								</script>
								<tr>
									<th>메뉴 설명</th>
									<td><textarea name="description" cols="48" rows="5"><c:out value="${menu.description}" /></textarea></td>
								</tr>
							</table>
			  <div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:insert_go();">저장</a>
					</li>
					<li>
						<a href="javascript:insert_reset();">취소</a>
					</li>
					<li>
						<a href="<c:url value='/admin/menuList.do'/>?groupKey=<c:out value='${groupKey}' />">목록</a>
					</li>
				</ul>
			</div>
				
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
type_click("<c:out value='${menu.type}' />");
permit_click("<c:out value='${menu.permit}' />");
</script>
