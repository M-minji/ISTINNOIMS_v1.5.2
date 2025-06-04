<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<script src="/js/jquery/jquery-3.7.1.min.js"	type="text/javascript"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.js"	type="text/javascript"></script>

<link href="<c:url value='/css/video/video-js.css'/>" rel="stylesheet">
<script src="<c:url value='/js/video/video.js'/>"></script>

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
			$('form[name=frm]').attr('action','<c:url value='/webMenu.do'/>').submit();
		}else{
			if(confirm("삭제하시겠습니까?")){
				$('form[name=frm]').attr('action','<c:url value='/boardDelete.do'/>').submit();
			}
		}
	}
}

function goUpdate(){
	if(confirm("관리자 권한으로 저장하시겠습니까?")){
		$('#pageGb').val('u');
		$('form[name=frm]').attr('action','<c:url value='/webMenu.do'/>').submit();
	}
}

function goDelete(){
	if(confirm("관리자 권한으로 삭제하시겠습니까?")){
		$('form[name=frm]').attr('action','<c:url value='/boardDelete.do'/>').submit();
	}
}
</script>

<div class="sub_container">
<table width="1000" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="20">&nbsp;</td>
		<td width="160" valign="top">
			<c:out value="${subMenu }" escapeXml="false"/>
		</td>
		<td width="30">&nbsp;</td>
		<td width="760" valign="top">
		
		<div id="sub_container2">
		<!--컨텐츠 시작-->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" class="navigate">
				<img src="./images_smart/sub/navi_icon.gif" align="absmiddle" />
				<span class="navigate_s"><c:out value="${menuCategory}" escapeXml="false" /></span>
				</td>
			</tr>
			<tr>
				<!-- 타이틀 -->	
				<td height="50" class="contents_title">${menuNm}</td>
			</tr>
			<tr>
				<td height="30">&nbsp;</td>
			</tr>
			<tr>
				<td>
						
	<div id="sub_container2">
	<!-- 보드 내용시작 -->							
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="board_view" >
            <thead>
              <tr align="center">
                <th width="100">제 목</th>
                <td colspan="5">${boardUser.subject }</td>
              </tr>
              <tr>
              <c:choose>
					<c:when test="${boardmng.realFlag == 'T'}">
                	<th>이 름</th>
                	<td>                
						<a href="mailto:">${boardUser.name }</a>
                	</td>
                	</c:when>
					<c:otherwise>
					<th>아이디</th>
                	<td>                
						<a href="mailto:">${boardUser.id }</a>
                	</td>
                	</c:otherwise>
                </c:choose>
					
                <th width="70">날 짜</th>
                <td width="100">${boardUser.wdateForm }</td>
                <th width="70">조 회</th>
                <td width="70">${boardUser.visit }</td>
              </tr>
            </thead>
            <tbody>
            <tr>
            	<td colspan="6" style="text-align: center;">
            		<c:forEach items="${fileList}" var="fileList" begin="1" varStatus="k">
            			<c:if test="${fileList.imgType eq 'img' }">
            				<img src="/imageMDownload.do?boardKey=${boardUser.boardKey}&type=${boardmng.type}&fileKey=${fileList.fileKey}" width="80%" border="0" >
            			</c:if>
            		
            			<c:if test="${fileList.imgType ne 'img' }">
	          				<embed width="640" height="360" 
	          				src="/smartImage/${fileList.serverFilename}"          				
							type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" WindowlessVideo='1'></embed>
						</c:if>
						</p>
            		</c:forEach>	
            		
            	</td>
            </tr>
             
              <c:if test="${user.type == 'A'}">
                <tr>
                  <th>IP주소</th>
                  <td colspan="6">${boardUser.ip }</td>
                </tr>
              </c:if>
            </tbody>
        </table></td>
      </tr>
      <tr>
        <td height="30">&nbsp;</td>
      </tr>
      <tr>
        <td><table cellspacing="0" cellpadding="0" width="100%" border="0">
            <tr height="17">
              <td></td>
            </tr>
            <tr>
              <td align="right">
              
              <c:choose>
			  <c:when test="${user.type == 'A'}">
              <!--저장 btn--><a href="javascript:goUpdate();"><img src="./images_smart/board/modify.gif" border="0" /></a> 
              <!--삭제 btn--><a href="javascript:goDelete('${key}','${boardUser.boardKey}','d','${boardmng.type}')"><img src="./images_smart/board/del.gif" border="0" /></a>
              </c:when>
              <c:otherwise>
              
	            <c:if test="${strWriteAuth == 'T' || strWriteAuth == 'A'|| strWriteAuth == 'M'|| strWriteAuth == 'S'}">
	              <a href="javascript:goPasswordDialogPopup('u');"><img src="<c:url value='/images_smart/board/modify.gif'/>" border="0" /></a> 
	              <a href="javascript:goPasswordDialogPopup('d');"><img src="<c:url value='/images_smart/board/del.gif'/>" border="0" /></a>   
	            </c:if>  
	          </c:otherwise>
              </c:choose>
               
              <%-- <c:if test="${strReplyAuth == 'T' || strReplyAuth == 'M' || strReplyAuth == 'A'}">
              <a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${boardUser.boardKey}&pageGb=c&depth=${boardUser.depth}&groupKey=${groupKey}"><img src="./images_smart/board/reply.gif" border="0" /></a>
              </c:if> --%>

              <a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=r&groupKey=${groupKey}"><img src="./images_smart/board/list.gif" border="0" /></a></td>
            </tr>
        </table></td>
      </tr>
    </table>							
	</div>					
							
						</td>
					</tr>
					<tr>
						<td height="50">&nbsp;</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<!--컨텐츠 끝-->
</div>
	
<div id="dialog" title="Basic dialog" style="display:none;border:1px solid;">
	<form name="frm" id="frm" method="post" action="">
		<table width="100%">
			<input type="hidden" name="key" id="key" value="${key}"/>
			<input type="hidden" name="boardKey" id="boardKey" value="${boardUser.boardKey}"/>
			<input type="hidden" name="pageGb" id="pageGb" value=""/>
			<input type="hidden" name="type" id="type" value="${boardmng.type}"/>
			<tr align="center" height="26">
				<td>비밀번호를 입력하세요.</td>
			</tr>
			<tr align="center" height="26">
				<td><input type="password" id="password" name="password"/></td>
			</tr>
			<tr align="center" height="26">
				<td>
					<a href="javascript:goSubmit();"><img src="<c:url value='/images_in/k_write2.gif'/>" /></a>
					<a href="javascript:goPasswordDialogClose();"><img src="<c:url value='/images_in/k_cancel.gif" border="0"'/>"/></a>
				</td>
			</tr>
		</table>
	</form>
</div>
					
<!-- 보드 내용 끝 -->		
	

