<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
<title>담당자 조회</title>

<script type="text/javascript">

function selectCom(idx) {

	var obj = {
		sComConName 	:  $("#sComConName_" + idx).val(),
		sComConPhone 	:  $("#sComConPhone_" + idx).val(),
		sComConDep 		:  $("#sComConDep_" + idx).val()
	};
	
	if(typeof(opener.setConPop) != "undefined"){
		window.opener.setConPop(obj);
		window.close();
	}
}

</script>
</head>
<body style="overflow-X:hidden">
<form id="frm" name="frm" method="post" action="/mes/buy/popup/kw_contact.do">
	
		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>담당자 조회</h3>
				</div>
				<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
			</div>
		</div>

		<div class="popup_content">
			<div class="tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr>
				            <th>담당자명</th>
				            <th>연락처</th>
				            <th>부서</th>
						</tr>
			        </thead>
			        <tbody>
						<c:forEach var="item" items="${companyConcactList}" varStatus="i">
							<tr id="comInfoRow" onclick="javascript:selectCom('<c:out value='${i.index}'/>');">
					            <td>
						            <input type="hidden" id="sComConName_${i.index }" name="sComConName_${i.index }" value="${item.sComConName}">
						            <input type="hidden" id="sComConPhone_${i.index }" name="sComConPhone_${i.index }" value="${item.sComConPhone}">
						            <input type="hidden" id="sComConDep_${i.index }" name="sComConDep_${i.index }" value="${item.sComConDep}">
					            	<c:out value="${item.sComConName}"/>
					            </td>
					            <td>
					            	${item.sComConPhone}
					            </td>
					            <td>
					            	${item.sComConDep}
					            </td>
							</tr>
						</c:forEach>
						<c:if test="${empty companyConcactList }">
							<tr>
								<td colspan="4"> 담당자가 없습니다. </td>
							</tr>
						</c:if>
			        </tbody>
		      </table>
			</div>
		</div>
	
	</form>
</body>
