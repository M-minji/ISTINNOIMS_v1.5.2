<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.js"	type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script type="text/javascript">

function goPasswordDialogPopup(pageGb){
	
	$('#password').val('');
	$('#pageGb').val(pageGb);
	
	$( "#dialog").dialog({ 
		draggable: true,
		width: 430,
		modal: true,
		title: "비밀번호 입력",
		position: [500,250]
	});
}

function goPasswordDialogClose(){
	$( "#dialog" ).dialog('close');
}

$(function () {
    $("#password").keydown(function (key) {
        if (key.keyCode == 13) {
            goSubmit();
        }
    });

});

function goSubmit(){
	if($('#password').val() == ''){
		alert("비밀번호를 입력하세요.");
		$('#password').focus();
	}else{
		if($('#pageGb').val() == 'u'){
			$('form[name=frm]').attr('action','/webMenu.do').submit();
		}else{
			if(confirm("삭제하시겠습니까?")){
				$('form[name=frm]').attr('action','/jsp/boardDelete.do').submit();
			}
		}
	}
}

function goUpdate(){
	if(confirm("관리자 권한으로 저장하시겠습니까?")){
		$('#pageGb').val('u');
		$('form[name=frm]').attr('action','/webMenu.do').submit();
	}
}

function goDelete(){
	if(confirm("관리자 권한으로 삭제하시겠습니까?")){
		$('form[name=frm]').attr('action','/jsp/boardDelete.do').submit();
	}
}

</script>

							
<!-- 보드 내용시작 -->							
<table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="board_view" >
            <thead>
              <tr align="center">
                <th width="100">제 목</th>
                <td colspan="5">${boardUser.subject }</td>
              </tr>
              <tr>
              <c:choose>
					<c:when test="${boardmng.realFlag == 'T'}">
                	<th>이 름</th>
                	<td width="200">                
						<a href="mailto:">${boardUser.name }</a>
                	</td>
                	</c:when>
					<c:otherwise>
					<th>아이디</th>
                	<td width="200">                
						<a href="mailto:">${boardUser.id }</a>
                	</td>
                	</c:otherwise>
                </c:choose>
					
                <th width="50">날 짜</th>
                <td width="100">${boardUser.wdateForm }</td>
                <th width="50">조 회</th>
                <td width="50">${boardUser.visit }</td>
              </tr>
            </thead>
            <tbody>
			<tr>
				<td colspan="6" class="contents"><c:out value="${boardUser.content}" escapeXml="false" /></td>
			</tr>
							
							
              <c:if test="${boardmng.fileFlag == 'T'}">	
	              <c:if test="${not empty fileList}">	
	              <tr>
	                <th>첨부파일</th>
	                <td colspan="5">
	                	<table id="toTable" cellspacing="0" cellpadding="0" width="100%" border="0" class="file" >
	                	<c:forEach items="${fileList}" var="fileList">
	                        <tr>
	                          <td> <a href="<c:url value='/download.do'/>?fileKey=${fileList.fileKey}&type=${boardmng.type}" >${fileList.filename }</a>
									 [다운횟수 : ${fileList.down} &nbsp;용량 : <fmt:formatNumber value="${fileList.size / 1024}" type="number"/> KB]</td>
	                        </tr>
	                    </c:forEach>
	                </table></td>
	              </tr>
	              </c:if>
              </c:if>
<%--               <c:if test="${user.type == 'A'}"> --%>
<!--                 <tr> -->
<!--                   <th>IP주소</th> -->
<%--                   <td colspan="5">${boardUser.ip }</td> --%>
<!--                 </tr> -->
<%--               </c:if> --%>
            </tbody>
        </table>
        </td>
      </tr>
      <tr>
        <td height="30">&nbsp;</td>
      </tr>
      <tr>
        <td>
        <table cellspacing="0" cellpadding="0" width="100%" border="0">
            <tr height="17">
              <td></td>
            </tr>
            <tr>
              <td align="center">
              <c:choose>
				  <c:when test="${user.type == 'A'}">
		              <a href="javascript:goUpdate();"><img src="/images_main/v_modify.gif" border="0" /></a> <!-- 저장 btn -->
		              <a href="javascript:goDelete('${key}','${boardUser.boardKey}','d','${boardmng.type}')"><img src="/images_main/v_del.gif" border="0" /></a>
	              </c:when>
	              <c:otherwise>
		              <c:if test="${strWriteAuth == 'T' || strWriteAuth == 'M' || strWriteAuth == 'A' }">
		              <a href="javascript:goPasswordDialogPopup('u');"><img src="/images_main/v_modify.gif" border="0" /></a> <!--저장 btn-->
		              <a href="javascript:goPasswordDialogPopup('d');"><img src="/images_main/v_del.gif" border="0" /></a><!--삭제 btn-->
		              </c:if>
	              </c:otherwise>
              </c:choose>
              <c:if test="${strReplyAuth == 'T' || strReplyAuth == 'M' || strReplyAuth == 'A'}">
              <!--답글 btn--><a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${boardUser.boardKey}&pageGb=c&depth=${boardUser.depth}"><img src="/images_main/v_reply.gif" border="0" /></a>
              </c:if>
              <!--목록 btn--><a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=r"><img src="/images_main/v_list.gif" border="0" /></a></td>
            </tr>
        </table>
        </td>
      </tr>
    </table>							

	
<div id="dialog" title="Basic dialog" style="display:none;border:1px solid;">
	<form name="frm" id="frm" method="post" action="">
		<table width="100%">
			<input type="hidden" name="key" id="key" value="${key}"/>
			<input type="hidden" name="boardKey" id="boardKey" value="${boardUser.boardKey}"/>
			<input type="hidden" name="pageGb" id="pageGb" value=""/>
			<input type="hidden" name="type" id="type" value="${boardmng.type}"/>
			<input type="hidden" name="smartStr" id="smartStr" value="${boardUser.smartStr}"/>
			<tr align="center" height="26">
				<td>비밀번호를 입력하세요.</td>
			</tr>
			<tr align="center" height="26">
				<td><input type="password" id="password" name="password"/></td>
			</tr>
			<tr align="center" height="26">
				<td>
					<a href="javascript:goSubmit();"><img src="/images_main/k_write2.gif" /></a>
					<a href="javascript:goPasswordDialogClose();"><img src="/images_main/k_cancel.gif" border="0"/></a>
				</td>
			</tr>
		</table>
	</form>
</div>
							
<!-- 보드 내용 끝 -->			
	
