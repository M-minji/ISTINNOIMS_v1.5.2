<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="<c:url value='/js/km_menu.js'/>"></script>
	</head>
<body class="content_bg">
<div id="contents">
  
  <div class="content_tit">
  	<h2>메뉴추가</h2>
  	<div class="path">
  		<p>HOME&nbsp;>&nbsp;메뉴관리&nbsp;>&nbsp;메뉴추가</p>
  	</div>
  </div>
  
  <jsp:include page="km_admin_txt.jsp" flush="true" />
		<form name="writeform" id="menuBeanVO" method="post" action="<c:url value='/admin/menuInsert.do'/>">
		<input type="hidden" name="key" value="<c:out value='${key}' />" /> 
		<input type="hidden" name="groupKey" value="<c:out value='${groupKey}' />" />
			<table class="tbl_view">
				<tbody>
					<%-- <tr>
						<th>그 룹 명</th>
						<td>${group}</td>
					</tr> --%>
					<tr>
						<th>메 뉴 명</th>
						<td>${category}
						<input type="text" name="name" maxLength="50"/></td>
					</tr>
					<!--<tr>
		                			<th>타이틀이미지 URL</th>
									<td><input type="text" name="titlefile" maxLength="100" style="width:200px;" /></td>
								</tr> -->
					<tr>
						<th>페이지 유무</th>
						<td><input type="radio" name="exist" value="T" checked />있음
							<input type="radio" name="exist" value="F" />없음</td>
					</tr>
					<tr>
						<th>화면표시 여부</th>
						<td><input type="radio" name="visible" value="T" checked />표시함
							<input type="radio" name="visible" value="F" />숨김</td>
					</tr>
					<!-- <tr>
		                			<th>메뉴이미지1 URL</th>
									<td><input type="text" name="image1" maxLength="100" style="width:200px;" /></td>
								</tr>
								<tr>
		                			<th>메뉴이미지2 URL</th>
									<td><input type="text" name="image2" maxLength="100" style="width:200px;" /></td>
								</tr>
								<tr>
		                			<th>메뉴이미지3 URL</th>
									<td><input type="text" name="image3" maxLength="100" style="width:200px;" /></td>
								</tr>
								<tr>
		                			<th>메뉴이미지4 URL</th>
									<td><input type="text" name="image4" maxLength="100" style="width:200px;" /></td>
								</tr>
								<tr>
		                			<th>메뉴 색상</th>
									<td><input type="text" name="color" maxLength="7" style="width:200px;"/></td>
								</tr> -->
					<tr>
						<th>운 영 형 태</th>
						<td><input type="radio" name="type" value="C" onClick="type_check()" checked />일반페이지 
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
									<option value="${item.key}">${item.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>스크린ID</th>
						<td><input type="text" name="screenId" /></td>
					</tr>
					<tr>
						<th>기존,신규,변경</th>
						<td>
							<select name="screenHistory" > 
								<option value="신규" >신규</option>
								<option value="기존" >기존</option>
								<option value="변경" >변경</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>프로그램 링크</th>
						<td><input type="text" name="program" maxLength="100"
							disabled /></td>
					</tr>
					<tr>
						<th>사이트링크 URL</th>
						<td><input type="text" name="link" maxLength="100"
							disabled /></td>
					</tr>
					<tr>
						<th>관리자 선택</th>
						<td>
							<c:forEach var="item" items="${adminList}" varStatus="i">
								<input type="checkbox" name="adminStr" value="${item.id}" <c:if test="${i.count == 1 }">checked</c:if> /> ${item.id}
							</c:forEach>			
							&nbsp;&nbsp;&nbsp;[&nbsp;<input type="checkbox" name="inherit" value="T" checked/>상위관리자 상속&nbsp;]
						</td>
					</tr>
					<tr>
						<th>접근권한 설정</th>
						<td><input type="radio" name="permit" value="T"
							onClick="permit_check()" />설정 <input type="radio" name="permit"
							value="F" onClick="permit_check()" checked />해제&nbsp; <c:choose>
								<c:when test="${groupKey eq 2}">
									<select name="level" disabled>
										<option value="100" selected>부서선택</option>
										<c:forEach var="item" items="${positionList}">
											<option value="<c:out value='${item.rank}' />">
												<c:out value="${item.name}" />
											</option>
										</c:forEach>
									</select>
								</c:when>
								<c:when test="${groupKey eq 3}">
									<select name="level" disabled>
										<option value="100" selected>등급선택</option>
										<option value="1">시솝</option>
										<option value="2">부시솝</option>
										<option value="3">정회원</option>
										<option value="4">준회원</option>
									</select>
								</c:when>
								<c:otherwise>
									<select name="level" disabled>
										<option value="100" selected>등급선택</option>
										<c:forEach var="item" items="${levelList}">
											<option value="<c:out value='${item.rank}' />">
												<c:out value="${item.subject}" />
											</option>
										</c:forEach>
									</select> 이상
											</c:otherwise>
							</c:choose></td>
					</tr>
					<!--
								<tr>
		                			<th>코멘트설정 여부</th>
									<td><input type="radio" name="comment" value="T" checked />설정
										<input type="radio" name="comment" value="F" />해제
									</td>
								</tr>
								-->
					<tr>
						<th>접속통계 여부</th>
						<td><input type="radio" name="statistic" value="T" />설정 <input
							type="radio" name="statistic" value="F" checked />해제</td>
					</tr>
					<tr>
						<th>메뉴 설명</th>
						<td><textarea name="description" cols="48" rows="5"></textarea></td>
					</tr>
				</tbody>
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
							<a href="<c:url value='/admin/menuList.do'/>?groupKey=${groupKey}">목록</a>
						</li>
					</ul>
				</div>
		</form>
	</div>
</body>
</html>
