<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript">
	function moveSub_go(){
		if (document.searchform.step2.value == ""){
			alert("메뉴를 선택하세요.");
			return;
		}
		
		if (confirm("변경 하시겠습니까?")){
			document.searchform.submit();
		}
	}
	
	function move_reset(){
		document.searchform.reset();
	}

</script>
</head>
<body class="content_bg">
<div id="contents">
  
  <div class="content_tit">
  		<h2>메뉴순서변경</h2>	
  		<div class="path">
  			<p>HOME&nbsp;>&nbsp;관리자메뉴관리&nbsp;>&nbsp;메뉴순서변경</p>
  		</div>
  </div>
 
  <div class="detail_box">
   	 <h3>메뉴 순서변경 요령</h3>
	   	 <ul>
			<li>
				<img src="<c:url value='/images/admin/blt.png'/>" align="absmiddle"/>&nbsp;현재 선택된 메뉴가 대상메뉴에서 선택된 메뉴 다음에 위치하게됩니다. 
			</li>
			<li>
				<img src="<c:url value='/images/admin/blt.png'/>" align="absmiddle"/>&nbsp;대상메뉴를 선택하시고 변경을 클릭하세요.
			</li>
		</ul>		   
	</div>
         
<form name="searchform" id="adminmenuVO" method="post" action="<c:url value='/admin/adminMenuSubOrder.do'/>">
<input type="hidden" name="root" value="${root}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="name" value="${name}">
<input type="hidden" name="depth" value="${depth}">
			
  <table class="tbl_write">
    <tbody>  
      <tr>
        <th>선택된 메뉴</th>
		<td>${name}</td>
	  </tr>             
      <tr>
        <th>대상메뉴 선택</th>
		<td>
		  <select name="step2">
			<option value="" selected>메뉴 선택</option>
			<c:forEach var="item" items="${result}" varStatus="status">
				<option value="${item.step}">${item.name}</option>	
			</c:forEach>			
		  </select>
		</td>
      </tr>
	</tbody>
  </table>
  <div class="tbl_btn_right">
	<ul>
		<li>
			<a href="javascript:moveSub_go();">확인</a>
		</li>
		<li>
			<a href="javascript:move_reset();">취소</a>
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
