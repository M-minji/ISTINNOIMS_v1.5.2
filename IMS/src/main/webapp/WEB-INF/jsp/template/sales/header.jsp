<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<c:if test="${menu.key eq 0}">
	<c:set target="${menu}" property="key" value="${user.menuKey}" />
</c:if>
<!-- Layer 시작 -->
<c:set var="sel" value="0" />
<c:set var="left" value="0" />
		<div id="sub_bg"> 
<c:forEach var="item" items="${salesTopMenu}" varStatus="i">
	<c:set var="left" value="${left + 100 - i.index * 2 + 40}" />
	<c:set var="width" value="0" />
	
		<div id="sub<c:out value='${i.index}' />" class="top_submenu" style="position:absolute; left:<c:out value='${left}' />px;width:800px;height:30px;z-index:31; display:none; ">
		  <ul> 
<%-- 			<c:set target="${submenu}" property="key" value="${item.key}" /> --%>
			<c:set var="refMenuList" value="${submenu.refMenuList}" />
			<c:set var="subSize" value="${submenu.listSize}" />
			<c:set var="subSize" value="${subSize * 100}" />
			<c:forEach var="item2" items="${salesAllRefMenu}" varStatus="j">
				<c:if test="${item.key eq item2.ref }">
			  <li><a href="/sales/webMenu.do?key=<c:out value='${item2.key}' />"><c:out value="${item2.name}" /> </a></li>
<%-- 				<string:length var="length"><c:out value="${item2.name}" /></string:length> --%>
				<c:set var="width" value="${width + length * 7}" />
				<c:choose>
					<c:when test="${j.last}">
						<c:set var="width" value="${width + 60}" />
					</c:when>
					<c:otherwise>
						<c:set var="width" value="${width + 60}" />
					</c:otherwise>
				</c:choose>
				<c:if test="${item2.key eq menu.key}">
					<c:set var="sel" value="${i.index}" />
				</c:if> 
				</c:if>
				
			</c:forEach>
			<c:if test="${left + width gt 800}">
				<script type="text/javascript">
				document.all.sub<c:out value="${i.index}" />.style.left = <c:out value="${left - ((left + width) - 800)}" />;
				</script>
			</c:if> 
			<c:out value="${i.end}" /> 
		    </ul>
		</div>
		<script type="text/javascript">
		document.all.sub<c:out value="${i.index}" />.style.width = <c:out value="${width}" />;
	</script>
	</c:forEach>
		</div>
	<!-- Layer 끝 -->
<!--Top 시작-->
<!-- 메뉴시작  -->

<div id="wrap">
	<div id="header_wrap">
		<div id="header">
			<!--logo-->
			<div class="logo">
				<ul>
					<li><a href="/sales/main.do"><img src="/images_intra/common/logo.jpg" /></a></li> 
				</ul>
			</div>
			<!--topmenu-->
			<div class="gnb">
				<ul>
					<c:forEach var="item" items="${salesTopMenu}" varStatus="i">
						<li><a
							href="/sales/webMenu.do?key=<c:out value='${item.key}' />"
							onmouseover="sub_on3(<c:out value='${i.index}' />);"
							target="_self"> <c:out value="${item.name}" />
						</a></li>
					</c:forEach>
				</ul>
			</div>
		
		</div>
	</div>
<script type="text/javascript">
set_root(<c:out value="${fn:length(salesTopMenu)}" />);
sel_menu3(<c:out value="${sel}" />);
/**
 * SLB 사용을 위해 추가
 */ 
/* if(document.getElementById("mainFlag").value=="sub"){
openLoadingPage();
} */
</script>