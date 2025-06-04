<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="left_top">
   <ul>
     <li><b>Today : <span style="color:#CC0000">${formatDate }</span></b></li>
     <li>즐거운 하루 되세요!</li>
     <li><img src="/images_sales/common/icon_person.gif" />&nbsp;<b>
      <c:out value="${user.level}" />&nbsp;/&nbsp;<c:out value="${user.name}" /> 
		</b> 님</li>
     <li><a href="/sales/login/kw_logout.jsp"><img src="/images_sales/common/logout_btn.gif" /></a></li>
   </ul>
 </div>
   <div class="lnb">
     <ul>
			<div class="lnb_tit">
				<h3 ><c:out value="${salesMenuInfo.leftTitle}" /></h3>
			</div>
			<ul>
				<c:forEach var="item" items="${salesLeftMenu}" varStatus="i">
					<c:if test="${item.depth ne '2'}">
						<li <c:if test="${item.key eq salesMenuInfo.key}"> class='active' </c:if> >
							<a href="/sales/webMenu.do?key=<c:out value='${item.key}' />"><span><c:out value="${item.name}" /></span></a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
    </ul>        
  </div>