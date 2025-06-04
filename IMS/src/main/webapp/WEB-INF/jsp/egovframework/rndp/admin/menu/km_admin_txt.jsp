
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="detail_box">
	<h3>입력요령</h3>
	<ul>
		<li>
			<img src="<c:url value='/images/admin/blt.gif'/>" /> 페이지 유무 : 해당 메뉴에 연결할 페이지가 있는지 여부 입니다. <br>
				&nbsp;&nbsp;상위메뉴인경우 페이지 유무를 없음에 체크하세요.<br> 
				&nbsp;&nbsp;하위메뉴인경우 페이지 유무에 있음을 체크하세요.
		</li>
		<li>
			<img src="<c:url value='/images/admin/blt.gif'/>" /> 운영형태의  일반페이지 선택시 관리자 메뉴중 컨텐츠관리에서 해당내용을 입력하세요.<br> 
				&nbsp;&nbsp;게시판 선택시 게시판선택에서 선택하세요.(게시판 선택의 항목은 관리자메뉴중 게시판관리에서 등록합니다.)<br> 
				&nbsp;&nbsp;프로그램링크 선택시 파일명(개발된 파일)은 전체 경로를 입력하십세요.(예 : /jsp/menu/km_menu_lf.jsp)<br>
					&nbsp;&nbsp;사이트링크 선택시 url을 입력하세요.(예 : http://www.naver.com)
		</li>
		<li>
			<img src="<c:url value='/images/admin/blt.gif'/>" /> 지정한 관리자 이외에 상위 메뉴 관리자 또한 이 메뉴를 관리할 수 있게 하려면 상위관리자 상속을 체크하세오.
		</li>
		<li>
			<img src="<c:url value='/images/admin/blt.gif'/>"  /> 운영형태 중 일반페이지를 제외한 나머지를 선택하였을 경우 각 항목에 해당하는 입력값을 반드시 입력하세요.
		</li>
<!-- 		<li> -->
<%-- 			<img src="<c:url value='/images/admin/blt.gif'/>"  /> 접속통계를 설정하면 통계시 적용됩니다. --%>
<!-- 		</li> -->
	</ul>
</div>
