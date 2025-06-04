<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.css" rel="stylesheet" media="all" />
<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript">
	
	function fn_guestList(pageNo) {
		document.writeform.pageIndex.value = pageNo;
		document.writeform.action = "<c:url value='/webMenu.do'/>";
		document.writeform.submit();
	}
	

</script>
<div class="sub_container">
	<form name="writeform" method="post" action="<c:url value='/webMenu.do'/>">		
		<input type="hidden" id="groupKey" name="groupKey" value="${groupKey }" />
		<input type="hidden" id="key" name="key" value="${key }" />
		<input type="hidden" id="pageIndex" name="pageIndex" value="${bVO.pageIndex}" />
		
		<div class="content_tit">
		공지사항
		</div>
		<!--컨텐츠 시작-->
		<table class="tbl_list">
			<thead>
				<tr>
					<th width="60" scope="col">번호</th>
					<th scope="col">제 목</th>
					<th width="100" scope="col">아이디</th>
					<th width="100" scope="col">날짜</th>
					<th width="60" scope="col">조회</th>
				</tr>
			</thead>
			<tbody>
          <!--  본게시물 s -->
          <c:forEach var="board" items="${boardList}" varStatus="status">
          <tr  onMouseOver="this.style.backgroundColor='#F4F4F4'" onMouseOut="this.style.backgroundColor=''">
            <td>${status.index+1+(bVO.pageIndex-1)*10}</td>
            <td class="subject">	
            	<a
				<c:choose>
				<c:when test="${strReadAuth == 'T' || strReadAuth == 'M' || strReadAuth == 'A'}">
					href="<c:url value='/webMenu.do'/>?key=${key}&boardKey=${board.boardKey}&pageGb=v" 
				</c:when>
				<c:when test="${strReadAuth == 'S' }"> href="javascript:javascript:goPasswordDialogPopup('${board.boardKey}');" </c:when>
				<c:when test="${strReadAuth == 'F' }"> href="javascript:alert('읽기 권한이 없습니다.')" </c:when>
				</c:choose>>${board.subjectForm}
					<c:if test="${board.fileCnt ne 0 }">
						<img src="/images/images/att_.gif" />
					</c:if>
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
			</tbody>
		</table>
	</form>
	<!--컨텐츠 끝-->
	
	
	
	
</div>



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

<form name="writeform" method="post" action="kw_tboard_lf.do">
<table class="search_box">
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
</table>
</form>