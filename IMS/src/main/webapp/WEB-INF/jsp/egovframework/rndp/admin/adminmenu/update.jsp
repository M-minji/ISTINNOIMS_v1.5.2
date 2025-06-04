<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/kw_error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
function update_go(){
	var adminmenufrm = document.writeform; 

	if (adminmenufrm.name.value == ""){
		alert("메뉴명을 입력하세요.");
		adminmenufrm.name.focus();
		return;
	}

	if (confirm("수정 하시겠습니까?")){
		adminmenufrm.submit();
	}
}


function insert_reset(){
	document.writeform.reset();
	document.writeform.name.focus();
}
</script>
</head>

<body onLoad="document.writeform.name.focus();" class="content_bg">
<div id="contents">
  <form name="writeform" id="adminmenuVO" method="post" action="<c:url value="/admin/adminMenuUpdate.do"/>">
  <input type="hidden" name="key" value="${key}"/>
  
  <div class="content_tit">
  	<h2>메뉴수정</h2>
	  	<div class="path">
	  		<p>HOME&nbsp;>&nbsp;관리자메뉴관리&nbsp;>&nbsp;메뉴수정</p>
	  	</div>
  </div>	
  
  <jsp:include page="input.jsp" flush="true" />
	<div class="tbl_write">	 
	  <table>
	   <tbody> 
		  <tr>
		    <th>메 뉴 명</th>
			<td>${category}<input type="text" name="name" value="${menu.name}" maxLength="50" style="width:200px;"></td>
		  </tr>
		  <tr>
			<th>링크 URL</th>
			<td><input type="text" name="url" value="${menu.url}" maxLength="60" style="width:400px;"></td>
		  </tr>
		  <c:if test="${ menu.preRefKey eq 0 }">									
		  <tr>
			<th>Group</th>
			<td>
			  <select name="groupKey" style="width:200px;">
				<option value="0">공통</option>
				<c:forEach var="item" items="${groupList}">
				  <option value="<c:out value='${item.groupKey}' />" <c:if test="${ item.groupKey eq groupKey }"> selected </c:if> ><c:out value="${item.name}" /></option>
				</c:forEach>
			  </select>		
			    <input type="hidden" name="target" value="right" >	
			</td>
	      </tr>
		  </c:if>
		  <tr>
<!-- 			<th>타 겟</th> -->
<!-- 			<td> -->
<%-- 			  <input type="radio" name="target" value="right"  <c:if test="${menu.target=='right'}">checked</c:if>>오른쪽&nbsp;&nbsp; --%>
<%-- 			  <input type="radio" name="target" value="_top" <c:if test="${menu.target=='_top'}">checked</c:if>>전체 --%>
<!-- 			</td> -->
		  </tr>
		</tbody>
	  </table>
	</div>

   <div class="tbl_btn_right">
	<ul>
		<li> 
			<a href="javascript:update_go();">확인</a>
		</li>
		<li>
			<a href="javascript:insert_reset();">취소</a>
        </li>
        <li>
        	<a href="<c:url value='/admin/adminMenuList.do'/>">목록</a>
        </li>
     </ul>
    </div>
  </form>
</div>
</body>
</html>
