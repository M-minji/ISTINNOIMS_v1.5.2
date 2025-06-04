<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<script src="/js/jquery/jquery-3.7.1.min.js"	type="text/javascript"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.js"	type="text/javascript"></script>

<script type="text/javascript">

	function fn_guestList(pageNo) {
		document.writeform.pageIndex.value = pageNo;
		document.writeform.action = "<c:url value='/webMenu.do'/>";
		document.writeform.submit();
	}
	
	
	function goPasswordDialogPopup( boardKey){
		
		$('#password').val('');
		$('#boardKey').val(boardKey);
		
		$( "#dialog").dialog({ 
			draggable: true,
			width: 430,
			modal: true,
			title: "비밀번호 입력",
			position: [500,250]

		});
		$( "#dialog").parent().appendTo(jQuery("form:first"));
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

	<div class="sub_container">
		
		<form name="writeform" method="post" action="<c:url value='/webMenu.do'/>">
		<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" />
		<input type="hidden" id="key" name="key" value="${key }" />
		<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" />
		
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20">&nbsp;</td>
				<td width="160" valign="top">
					<!-- left 메뉴 -->
					<c:out value="${subMenu }" escapeXml="false"/>
					<!-- left 메뉴 -->
				</td>
				<td width="30">&nbsp;</td>
				<td width="30" class="contents_bg">&nbsp;</td>
				<td width="760" valign="top">
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
        <td><table cellspacing="0" cellpadding="0" class="board_list">
          <col width="50" />
          <col />
          <col width="110" />
          <col width="100" />
          <col width="50" />
          <tr>
            <th>번호</th>
            <th>제&nbsp;&nbsp;&nbsp;목</th>
            <th>글쓴이</th>
            <th>날짜</th>
            <th>조회</th>
          </tr>
          <!--  공지 s -->
          <c:forEach var="notice" items="${noticeList}" varStatus="status">
          	<tr onMouseOver="this.style.backgroundColor='#F4F4F4'" onMouseOut="this.style.backgroundColor=''">
            <td><font color="red">[공지]</font></td>
            <td class="subject">
            	<c:if test="${notice.depth == 0}">
            	<a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${notice.boardKey}&pageGb=v"><img src="<c:url value='/images_in/image/ico_list_0.gif'/>" border="0"/></a>
            	</c:if>						
				<c:if test="${strReadAuth == 'T' || strReadAuth == 'M' || strReadAuth == 'A'}">
					<a href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${notice.boardKey}&pageGb=v">
				</c:if>
				<c:if test="${strReadAuth == 'F' }">
					<a href="javascript:alert('읽기 권한이 없습니다.')">
				</c:if>
					${notice.subjectForm}
				</a>
            </td>
            <td>
            	${notice.name }
            </td>
            <td>${notice.wdateForm }</td>
            <td>${notice.visit }</td>
          </tr>
          </c:forEach>
          <!--  공지 e -->
          <!--  본게시물 s -->
          <c:forEach var="board" items="${boardList}" varStatus="status">
          <tr  onMouseOver="this.style.backgroundColor='#F4F4F4'" onMouseOut="this.style.backgroundColor=''">
            <td>${status.index+1+(bVO.pageIndex-1)*10}</td>
            <td class="subject">					
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
				<c:if test="${board.fileCnt ne 0 }"><img src="<c:url value='/images/images/att_.gif'/>"/></c:if>
				</a>
            </td>
            <td>
            	${board.name }
            </td>
            <td>${board.wdateForm }</td>
            <td>${board.visit }</td>
          </tr>
          </c:forEach>
          <!--  본게시물 e -->
          <c:if test="${empty boardList }">
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
              <td>
              <input type="radio" name="searchType" value="subject" onclick="blur()" <c:if test="${bVO.searchType eq 'subject'}">checked</c:if>/>제목
                <input type="radio" name="searchType" value="name" onclick="blur()" <c:if test="${bVO.searchType eq 'name'}">checked</c:if>/>이름
                <input type="radio" name="searchType" value="content" onclick="blur()" <c:if test="${bVO.searchType eq 'content'}">checked</c:if>/>내용&nbsp;
                <input type="text" name="searchWord" value="${bVO.searchWord}" maxLength="30" style="width:150px;"/>
				<input type="image" src="<c:url value='/images_in/btn/btn_search2.gif'/>" align="absmiddle" onClick="javascript:fn_guestList(1);"/>				
				<c:if test="${strWriteAuth == 'T' || strWriteAuth == 'A' || strWriteAuth == 'M'}">
					<a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=c">
					<img src="<c:url value='/images_in/btn/btn_write.gif'/>" align="absmiddle" border="0">
					</a>
				</c:if>								
            	</td>
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
				</td>
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
					<a href="javascript:goSubmit();"><img src="<c:url value='/images_in/k_write2.gif'/>" /></a>
					<a href="javascript:goPasswordDialogClose();"><img src="<c:url value='/images_in/k_cancel.gif" border="0"'/>"/></a>
				</td>
			</tr>
		</table>
	</form>
</div>				
	</div>
	

