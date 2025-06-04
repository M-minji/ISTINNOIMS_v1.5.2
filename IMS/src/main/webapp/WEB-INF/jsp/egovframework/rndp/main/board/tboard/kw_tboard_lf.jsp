<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta name="viewport" content="width-device-width, initial-scale=1.0">

<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<link type="text/css" href="/css/main/board.css" rel="stylesheet"	 />
<script src="/js/jquery-ui-1.14.1/jquery-ui.js"	type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script type="text/javascript">

	function fn_guestList(pageNo) {
		document.writeform.pageIndex.value = pageNo;
		document.writeform.action = "<c:url value='/webMenu.do'/>";
		document.writeform.submit();
	}
	
	
	function goPasswordDialogPopup(boardKey){
		
		$('#password').val('');
		$('#boardKey').val(boardKey);
		
		$( "#dialog").dialog({ 
			draggable: true,
			width: 430,
			modal: true,
			title: "비밀번호 입력",
			position: [500,250]

		});
// 		$( "#dialog").parent().appendTo(jQuery("form:first"));
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
			$('form[name=frm]').attr('action','<c:url value='/webMenu.do'/>').submit();
			
		}
	}	

</script>


<!-- <div id="board_div_header" style="width: 740px; "> -->
<%-- ${boardmng.header} --%>
<!-- </div>			 -->
<form name="writeform" method="post" action="<c:url value='/webMenu.do'/>">
<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" />
<input type="hidden" id="key" name="key" value="${key }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" />
<!-- 보드 내용시작 -->		
				
<table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
        <td><table cellspacing="0" cellpadding="0" class="board_list">
          <col class="col1" width="50" />
          <col class="col2" />
          <col class="col3" width="110" />
          <col class="col4" width="100" />
          <col class="col5" width="50" />
          <tr>
            <th>번호</th>
            <th class="thtitle">제목</th>
            <th>글쓴이</th>
            <th>날짜</th>
            <th>조회</th>
          </tr>
          <!--  본게시물 s -->
          <c:forEach var="board" items="${boardList}" varStatus="status">
          <tr  onMouseOver="this.style.backgroundColor='#F4F4F4'" onMouseOut="this.style.backgroundColor=''">
            <td>${paginationInfo.totalRecordCount - (status.index+(bVO.pageIndex-1)*10)}</td>
            <td class="subject">	
	            
	            <c:if test="${board.share == 'T'}"><!-- 공개글 -->
	            	<c:if test="${board.depth == 0}">
						<img src="/images_main/ico_list_0.gif" border="0">
					</c:if>				
					<c:if test="${strReadAuth == 'T' || strReadAuth == 'M' || strReadAuth == 'A'}">
						<a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${board.boardKey}&pageGb=v">
					</c:if>
					<c:if test="${strReadAuth == 'S' }">
						<a href="javascript:javascript:goPasswordDialogPopup('${board.boardKey}');">
					</c:if>
					<c:if test="${strReadAuth == 'F' }">
						<a href="javascript:alert('읽기 권한이 없습니다.')">
					</c:if>
						${board.subjectForm}	
					<c:if test="${board.fileCnt ne 0 }"><img src="/images_main/att_.gif" align="middle"/></c:if>
					</a>
				</c:if>	
				
 				<c:if test="${board.share == 'F'}"><!-- 비공개글 -->
 					<c:if test="${board.depth == 0}">
						<img src="/images_main/ico_list_0.gif" border="0">
					</c:if>	
					<c:if test="${board.id == uid || strReadAuth == 'A'}">
						<a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${board.boardKey}&pageGb=v">
					</c:if>
					<c:if test="${board.id != uid || strReadAuth != 'A'}">
						<a href="javascript:javascript:goPasswordDialogPopup('${board.boardKey}');">
					</c:if>
						${board.subjectForm}	
					<c:if test="${board.fileCnt ne 0 }"><img src="/images_main/att_.gif" align="middle"/></c:if>
					</a>					
				</c:if>	

            </td>
            <td>
            	${board.name }
            </td>
            <td>${board.wdateForm }</td>
            <td>${board.visit }</td>
          </tr>
          </c:forEach>
          <!--  본게시물 e -->
          <c:if test="${empty boardList && empty noticeList }">
          <tr>
            <td colspan="5">등록된 게시물이 없습니다.</td>
          </tr>
          </c:if>         
           
        </table></td>
      </tr>
      <tr>
        <td align="center" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td><table width="100%" cellspacing="0" cellpadding="0" border="0" class="board_page">
          <tr>
            <td><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><table width="100%" cellspacing="0" cellpadding="0" border="0" class="board_search">
            <tr>
              <td align="center">
              	<input type="radio" name="searchType" value="subject" onclick="blur()" <c:if test="${bVO.searchType eq 'subject'}">checked</c:if>/>제목
                <input type="radio" name="searchType" value="name" onclick="blur()" <c:if test="${bVO.searchType eq 'name'}">checked</c:if>/>이름
                <input type="radio" name="searchType" value="content" onclick="blur()" <c:if test="${bVO.searchType eq 'content'}">checked</c:if>/>내용&nbsp;
                <input type="text" class="text" name="searchWord" value="${bVO.searchWord}" maxLength="30" style="width:150px;"/>
				<input type="image" src="/images_main/btn_search2.gif" class="writebtn" onClick="javascript:fn_guestList(1);"/>		
						
				<c:if test="${strWriteAuth == 'T' || strWriteAuth == 'A' || strWriteAuth == 'M'}">
					<a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=c">
						<img src="/images_main/btn_write.gif" border="0" align="top" />
					</a>
				</c:if>								
            	</td>
            </tr>
        </table></td>
      </tr>
    </table>				
</form>
<!--컨텐츠 끝-->
<!-- 보드 내용 끝 -->			
					
<div id="dialog" title="Basic dialog" style="display:none;border:1px solid;">
		
	<form name="frm" id="frm" method="post" >
		<input type="hidden" name="key" id="key" value="${key}"/>
		<input type="hidden" name="boardKey" id="boardKey" />
		<input type="hidden" name="type" id="type" value="${boardmng.type}"/>
		<input type="hidden" name="pageGb" id="pageGb" value="v" />
		<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" />
		<input type="hidden" name="authFlag" id="authFlag" value="s" />
		<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" />
		
		<table style="width: 100%;">
			<tr align="center" height="26">
				<td>비밀번호를 입력하세요.</td>
			</tr>
			<tr align="center" height="26">
				<td><input type="password" id="password" name="password"/></td>
			</tr>
			<tr align="center" height="26">
				<td>
					<a href="javascript:goSubmit();"><img src="/images_main/k_write2.gif" /></a>
					<a href="javascript:goPasswordDialogClose();"><img src="/images_main/k_cancel.gif" border="0"'/></a>
				</td>
			</tr>
		</table>
	</form>
</div>				
	

