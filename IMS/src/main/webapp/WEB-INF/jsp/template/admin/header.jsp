<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
 function bluring(){
   if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG")document.body.focus();
   }
   document.onfocusin=bluring;
   
   /* 2015 05 11 관리자 그룹변경 함수(탑메뉴 변경) */
   function groupChang() { 
	   var gKey = $("select[name=mainGroup]").val();
	   window.location.replace("/admin/login.do?mainGroup="+gKey);
   }
</script>
</head>
<body>




<!-- 웹  -->
<div class="menu">	
	   <div class="header_wrap">
	<!--         <td width="120" height="60" style="padding-left:30px;"> -->
	<%--        	<a href="<c:url value='/'/>" target="_parent"><img src="<c:url value='/images_main/logo2.gif'/>"  width='70' height='40'/></a>        </td> --%>
	       <ul class="header_logo">
	        	<li><a href="<c:url value='/admin/index.do'/>" target="_parent"><img src="<c:url value='/images/admin/admin.gif'/>"/></a></li>
	        </ul>
	        <ul class="header_text">
	        	<li><a href="/">Home</a></li>
				<li><a><%-- <c:out value='${adminUser.staffClass}' /> /  --%><c:out value='${adminUser.name}' /></a></li>
	<!-- 			<td width="90">  -->
				 <%-- adminGroupKey = <c:out value="${adminGroupKey}" /> --%>
				<!-- 항상 로그인시 사용한 크룹 표시  -->
	<!-- 			<select id="topMainGroup" name="mainGroup" onchange="groupChang()"> -->
	<%-- 				<c:forEach var="item" items="${adminGroupList}" varStatus="status"> --%>
	<%-- 				 	<option value="<c:out value='${item.groupKey}' />" <c:if test="${item.groupKey eq adminGroupKey }">selected</c:if>><c:out value="${item.name}" /></option> --%>
	<%-- 				</c:forEach> --%>
	<!-- 			</select> -->
	<!-- 			</td> -->
					
		            <li><a href="<c:url value='/admin/logout.do'/>" target="_parent">LOGOUT</a></li>

	           </ul>
	   </div>
	   
		<%-- <c:if test="${!empty adminMenuTopList}">
			<div id="all_nav">
				<div class="nav">
					<div class="gnb">
						<ul>
							<c:forEach var="result" items="${adminMenuTopList}"	varStatus="status">
								<li>
									<a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${result.root}&menuKey=${result.key}">${result.name}</a>
									<ul class="m_gnb_submenu">
										<li><a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${result.root}&menuKey=${result.key}">${result.name}</a></li>
										<c:forEach var="subMenu" items="${adminMenuResultAll}"	varStatus="index">
												
												<c:if test="${result.root  eq   subMenu.root }">
														<li><a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${subMenu.root}&menuKey=${subMenu.key}">${subMenu.name}</a></li>
												</c:if>
										</c:forEach>
									</ul>								
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</c:if> --%>
</div>	
	
	
	
	
	
	
	
	
	
	<!-- 모바일 -->
	
	<div class="m_menu">
	
		 <div class="m_header_wrap">
<!--         <td width="120" height="60" style="padding-left:30px;"> -->
<%--        	<a href="<c:url value='/'/>" target="_parent"><img src="<c:url value='/images_main/logo2.gif'/>"  width='70' height='40'/></a>        </td> --%>
	      	 <ul class="m_header_logo">
	        	<li class="admin_logo">
	        		<a href="<c:url value='/admin/index.do'/>" target="_parent">
	        			<img src="<c:url value='/images/admin/admin.gif'/>"/>
	        		</a>
	        	</li>
	       	</ul>
	       	<ul class="m_menu_right">
	       		<li class="m_menu">
						<a>
							<img src="/images/btn/site_map.gif" alt="모바일메뉴"/>
						</a>
				</li> 
	        </ul>
  		</div>
	
		<div id="m_all_nav">
		
			<div class="m_nav">
				<ul class="m_header_text">
					<li>
						<span style="color: #CC0000">${formatDate }</span>	
					</li>
				</ul>
				<ul class="m_header_img">
					<li><a><c:out value='${member.kMemberName}' />님</a></li>
	           		<li><a href="<c:url value='/admin/logout.do'/>" target="_parent">LOGOUT</a></li>
	            	<li class="close">
						<a href="/admin/index.do">
							<img src="/images/admin/close.gif" width="22" height="21" />
						</a>
					</li>
           		</ul>
           	</div>
           	
			<c:if test="${!empty adminMenuTopList}">
			
				<div class="m_gnb">
					<ul class="m_gnb_menu">
						<c:forEach var="result" items="${adminMenuTopList}"	varStatus="status">
						<li>
							<a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${result.root}&menuKey=${result.key}">
								<c:out value="${result.name}" />
							</a>
							<ul class="m_gnb_submenu">
								<c:forEach var="subMenu" items="${adminMenuResultAll}"	varStatus="index">
									<c:if test="${result.root  eq   subMenu.root }">
										<li>
											<a href="<c:url value='/admin/leftMenu.do'/>?rootKey=${subMenu.root}&menuKey=${subMenu.key}">${subMenu.name}</a>										
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
					</ul>
				</div>
			
			</c:if>
		</div>
		
</div>
	



	
</body>
</html>
