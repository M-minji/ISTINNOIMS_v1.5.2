<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function insert_go(){
		var adminmenufrm = document.writeform; 

		if (adminmenufrm.name.value == ""){
			alert("메뉴명을 입력하세요.");
			adminmenufrm.name.focus();
			return;
		}

		if (confirm("저장 하시겠습니까?")){
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
 
  <div class="title">
  	<h2>메뉴추가</h2>
  	<div class="path">
  		<p>HOME&nbsp;>&nbsp;관리자메뉴관리&nbsp;>&nbsp;메뉴추가</p>
  	</div>
 </div>
 
  <jsp:include page="input.jsp" flush="true" />
  <form name="writeform" id="adminmenuVO" method="post" action="<c:url value="/admin/adminMenuInsert.do"/>">
  <input type="hidden" name="key" value="${key}">			        
  
  
	  <table class="tbl_write">			
	    <tbody>
	      <tr>
	        <th>메 뉴 명</th>
			<td>${category}<input type="text" name="name" maxLength="50"></td>
		  </tr>
		<tr>
	        <th>링크 URL</th>
			<td><input type="text" name="url" maxLength="60"></td>
		  </tr>
		  <tr>
	        <th>Group</th>
			<td>					
			  <select name="groupKey" style="width:200px;">
			  	<option value="0">공통</option>
				<c:forEach var="item" items="${groupList}">
					<option value="<c:out value='${item.groupKey}' />" <c:if test="${item.groupKey == groupKey}">selected</c:if>><c:out value="${item.name}" /></option>
				</c:forEach>
			  </select>		
			  <input type="hidden" name="target" value="right" >							
			</td>
		  </tr>
<!-- 		  <tr> -->
<!-- 	        <th>타 겟</th> -->
<!-- 			<td> -->
<!-- 		      <input type="radio" name="target" value="right" checked>오른쪽&nbsp;&nbsp; -->
<!-- 			  <input type="radio" name="target" value="_top">전체 -->
<!-- 			</td> -->
<!-- 		  </tr> -->
		</tbody>
	  </table>

 
 
  <div class="tbl_btn_right">
	<ul>
		<li>
	    	<a href="javascript:insert_go();">저장</a>
		</li>
		<li>
			<a href="javascript:insert_reset();">수정</a>
		</li>
		<li>
			<a href="<c:url value='/admin/adminMenuList.do'/>?groupKey=${groupKey}">목록</a>
		</li>
	</ul>
</div>
  </form>
</div>
</body>
</html>
