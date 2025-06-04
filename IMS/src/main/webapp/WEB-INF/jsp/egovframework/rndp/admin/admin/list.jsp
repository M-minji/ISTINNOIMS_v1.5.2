<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript">
function fn_delete(key){
	if (confirm("삭제 하시겠습니까?")){
		document.adminVO.key.value = key;
		document.adminVO.action = "<c:url value='/admin/adminDelete.do'/>";
		document.adminVO.submit();
	}
}

</script>
</head>

<body class="content_bg">
<div id="contents">
  <div class="title">
  		<h2>관리자 계정 관리</h2>
  	<div class="path">
  		<p>HOME&nbsp;>&nbsp;계정관리&nbsp;>&nbsp;관리자계정관리</p>
  	</div>
  </div>	
<div class="tbl_top">
	<ul class="tbl_top_left">
		<li>
      		<a href="<c:url value='/admin/adminInsertfrm.do'/>">계정추가</a>
     	</li>
     </ul>
     <ul class="tbl_top_right">
		<li>
        	<img src="<c:url value='/images/admin/admin_icon_P.gif'/>" />			                
		</li>
		<li>
			<img src="<c:url value='/images/admin/admin_icon_E.gif'/>" />			                
		</li>
		<li>		
			<img src="<c:url value='/images/admin/admin_icon_D.gif'/>" />
	  	</li>
	 </ul>
</div>
  <table class="tbl_list">
    <thead>
	  <tr>
		<th width="50" scope=col>번 호</th>
		<th width="100" scope=col>이름</th>
		<th width="100" scope=col>아 이 디</th>	
		<th width="*" scope=col>E-mail</th>
		<th width="100" scope=col>휴 대 폰</th>
		<th width="80" scope=col>관 리</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach var="result" items="${result}" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>${result.name }</td>
			<td>${result.id}</td>
			<td>${result.email}</td>
			<td>${result.telephone}</td>
			<td>
				<a href="<c:url value='/admin/adminPwdChangefrm.do'/>?key=${result.key}" title="비밀번호변경"><img src="<c:url value='/images/admin/icon_s_P.gif'/>"></a>&nbsp;
		  <a href="<c:url value='/admin/adminUpdatefrm.do'/>?key=${result.key}" title="계정수정"><img src="<c:url value='/images/admin/icon_s_E.gif'/>"></a>&nbsp;
<%-- 		  <c:if test="${result.adminFlag == 'T'}"> --%>
		  	<a href="javascript:fn_delete('${result.key}');"><img src="<c:url value='/images/admin/icon_s_D.gif'/>"></a>
<%-- 		  </c:if> --%>
			</td>
		</tr>
	</c:forEach>						
 	</tbody>
  </table>
</div>
<form name="adminVO" id="adminVO" method="post" action="">
	<input type="hidden" name="key" id="key" />
</form>
</body>
</html>
