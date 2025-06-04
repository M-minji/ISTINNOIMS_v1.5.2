<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/kw_error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>

<script type="text/javascript">

function menuChange(temRoot,temName){
	document.frm.root.value = temRoot;
	document.frm.name.value = temName;
	document.frm.action = "/admin/adminMenuOrderfrm.do";
	document.frm.submit();
}

function menuChange2(temRoot,temDepth,temStep,temName){
	document.frm.root.value = temRoot;
	document.frm.depth.value = temDepth;
	document.frm.step.value = temStep;
	document.frm.name.value = temName;
	document.frm.action = "/admin/adminMenuSubOrderfrm.do";
	document.frm.submit();
}

function addSubMenu(temKey,temRef,temName){
	document.frm.key.value = temKey;
	document.frm.ref.value = temRef;
	document.frm.name.value = temName;
	document.frm.action = "/admin/adminMenuInsertfrm.do";
	document.frm.submit();
}

</script>
</head>
<body class="content_bg">
<form name="frm" id="frm" method="post" >
	<input type="hidden" name="key" 	id="key" 	value="0">
	<input type="hidden" name="ref" 	id="ref" 	value="0">
	<input type="hidden" name="root" 	id="root" 	value="0">
	<input type="hidden" name="depth" 	id="depth" 	value="0">
	<input type="hidden" name="step" 	id="step" 	value="0">
	<input type="hidden" name="name" 	id="name" >

	<div id="contents">
		<div class="title">
			<h2>관리자 메뉴 관리</h2>	
			<div class="path">
				<p>HOME&nbsp;>&nbsp;관리자메뉴</p>
			</div>
		</div> 
		
		
			<div class="tbl_top">
				<ul class="tbl_top_left">
					<li>
	      				<a href="<c:url value='/admin/adminMenuInsertfrm.do'/>?key=0">최상위 메뉴 추가</a></th>
	      			</li>
	      		</ul>
	      		<ul class="tbl_top_right">
					<li>
	        			<img src="<c:url value='/images/admin/admin_icon_S.gif'/>" width="37" height="13" align="absmiddle">&nbsp;&nbsp; 
	        		</li>
	        		<li>
	        			<img src="<c:url value='/images/admin/admin_icon_E.gif'/>" width="37" height="13" align="absmiddle">&nbsp;&nbsp; 
	        		</li>
	        		<li>
	        			<img src="<c:url value='/images/admin/admin_icon_M.gif'/>" width="37" height="13" align="absmiddle">&nbsp;&nbsp; 
	        		</li>
	        		<li>
	        			<img src="<c:url value='/images/admin/admin_icon_D.gif'/>" width="37" height="13" align="absmiddle">              
	      			</li>
	      		</ul>	
			</div>
		<table class="tbl_list">
			<thead>
				<tr>
					<th>번호</th>
					<th>메뉴명</th>
					<th>U R L</th>
					<th>타겟</th>
					<th>GROUP</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${result}" varStatus="status">
					<tr onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''">
	          			<td>${status.index+1}</td>
	          			<td class="tit">${result.depthImg}${result.name}</td>
	          			<td class="tit">${result.url}</td>
	          			<td>${result.targetForm} </td>
	          			<td>
	          				<c:choose>
	                  			<c:when test="${ result.ref eq 0 }">
	                  				${result.groupName}  
	                			</c:when>
	                  			<c:otherwise> &nbsp; </c:otherwise>
	                		</c:choose>
	                	</td>
	          			<td>
	          				<a href="#" onclick="addSubMenu('${result.key}','${result.ref}','${result.name}')" title="하위메뉴추가">
	          					<img src="<c:url value='/images/admin/icon_s_S.gif'/>" align="absmiddle">
	          				</a> 	          			
	          				<a href="<c:url value='/admin/adminMenuUpdatefrm.do'/>?key=${result.key }" title="메뉴수정">
	          					<img src="<c:url value='/images/admin/icon_s_E.gif'/>" align="absmiddle">
	          				</a>
							<c:if test="${result.depth == 0}">
		                  		<a href="#" onclick="menuChange('${result.root}','${result.name}' );" title="메뉴순서변경">
		                  			<img src="<c:url value='/images/admin/icon_s_M.gif'/>"  align="absmiddle">
		                  		</a>
							</c:if>
							<c:if test="${result.depth != 0}">
								<a href="#" onclick="menuChange2('${result.root}','${result.depth}','${result.step}','${result.name}' );" title="서브메뉴순서변경">
									<img src="<c:url value='/images/admin/icon_s_M.gif'/>" align="absmiddle">
								</a>
							</c:if>   
							<a href="<c:url value='/admin/adminMenuDelete.do'/>?key=${result.key}" title="메뉴삭제" onClick="return delete_list();">
								<img src="<c:url value='/images/admin/icon_s_D.gif'/>" align="absmiddle">
							</a>
						</td>
	          		</tr>
	          	</c:forEach>
			</tbody>
		</table>
	</div>
</form>
</body>
</html>
