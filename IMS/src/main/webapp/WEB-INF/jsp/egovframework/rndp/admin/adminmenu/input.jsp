<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<div class="detail_box">
	<h2>입력요령</h2>
	<ul>
		<li>
			<img src="<c:url value='/images/admin/blt.png'/>"  /> 하부 메뉴가 있는 경우 URL은 입력하지 않아도 됩니다.
		</li>
		<li>
			<img src="<c:url value='/images/admin/blt.png'/>" /> 링크 URL은 예와 같이 전체 경로를 입력합니다.<br />
		&nbsp;(예 : /jsp/admin/km_admin_lf.jsp)
		</li>
		 <li>
		 	<img src="<c:url value='/images/admin/blt.png'/>" /> 타겟은 동작이 일어날 프레임을 지정하는 항목입니다. <br />
		&nbsp;&nbsp;로그아웃과 같이 윈도우 전체에 동작이 일어나야 하는 메뉴 같은 경우는 전체를 선택하시고, 다른 메뉴는 오른쪽을 선택하십시오.
		</li>
	</ul>
</div>
