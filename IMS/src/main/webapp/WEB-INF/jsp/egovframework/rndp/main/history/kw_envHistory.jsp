<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<!--컨텐츠 시작-->

<c:set var="mm" value="${monthList}" />			<!-- 4개 mon, year -->						
<c:forEach var="yy" items="${yearList}" varStatus="i">	

	<table class="historybox" width="100%" border="0" cellspacing="0" cellpadding="0">	
		<tr>
			<td width="90%" valign="top">
				<div class="history_l">${yy.historyYear}</div>
				<div class="history_c">
					<c:forEach var="list" items="${historyList}" varStatus="j">			                																
						<c:if test="${yy.historyYear eq list.historyYear}" >
							<div class="history_s">
								<img src="images/sub/blet.gif" width="9" height="9" /> &nbsp;${list.historyMonth}
								<img src="images/sub/blet.gif" width="9" height="9" /> &nbsp;${list.historyContent}
							</div>
						</c:if>
					</c:forEach>							
				</div>
			</td>
		</tr>						
	</table>	
</c:forEach>




<%-- <c:set var="mm" value="${mmList}" />			<!-- 4개 mon, year -->						 --%>
<%-- <c:forEach var="yy" items="${yearList}" varStatus="i">	 --%>

<!-- <table class="historybox" width="100%" border="0" cellspacing="0" cellpadding="0">	 -->
<!-- <tr> -->
<!-- 	<td width="90%" valign="top"> -->
<!-- 	<table border="0" cellspacing="0" cellpadding="0"> -->
<!-- 		<tr> -->
<%-- 			<td height="40" class="history_l">${yy.year}</td> --%>
<!-- 		</tr> -->
<%-- 		<c:forEach var="list" items="${yearListInfo}" varStatus="j">			                																 --%>
<%-- 		<c:if test="${yy.year eq list.year}" > --%>
<!-- 		<tr> -->
<!-- 			<td height="20" class="history_s"> -->
<!-- 				<img src="images/sub/blet.gif" width="9" height="9" /> -->
<%-- 				&nbsp;${list.content} --%>
<!-- 	 		</td> -->
<!-- 		</tr> -->
<%-- 		</c:if> --%>
<%-- 		</c:forEach> --%>
<!-- 	</table> -->
<!-- 	</td> -->
<!-- </tr>						 -->
<!-- </table>	 -->
<%-- </c:forEach> --%>
					