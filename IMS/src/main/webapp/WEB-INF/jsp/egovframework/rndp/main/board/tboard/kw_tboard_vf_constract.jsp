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
				<tr align="center">
					<th class="boardtitle"  width="100">제 목</th>
                	<td colspan="4">${boardUser.subject }</td>
				</tr>
				<tr align="center">
					<th class="boardtitle"  width="100">성 명</th>
                	<td width="250">${boardUser.name }</td>
                	<th class="boardtitle" width="100">작성일</th>
                	<td>${boardUser.wdateForm }</td>
				</tr>
				<tr align="center">
					<th class="boardtitle" width="100">이메일</th>
                	<td colspan="4">${boardUser.email }</td>
				</tr>
				<tr align="center">
					<th class="boardtitle" width="100">전 화</th>
                	<td colspan="4">${boardUser.comp_tel }</td>
				</tr>
				<tr align="center">
					<th class="boardtitle" width="100">휴대폰</th>
                	<td colspan="4">${boardUser.comp_fax }</td>
				</tr>
				<tr align="center">
					<th class="boardtitle" width="100">내 용</th>
                	<td height="100" colspan="4">${boardUser.content }</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
        <td>&nbsp;</td>
	</tr>
	<tr>
		<td>
        	<table cellspacing="0" cellpadding="0" width="100%" border="0">
            	<tr height="17">
              		<td></td>
            	</tr>
            	<tr>
					<td class="okbtn"  align="center">	
	              		<c:choose>
					  		<c:when test="${user.type == 'A'}">
					        	<a href="javascript:goUpdate();"><img src="/images_main/v_modify.gif" border="0" /></a> <!-- 저장 btn -->
					        	<a href="javascript:goDelete('${key}','${boardUser.boardKey}','d','${boardmng.type}')"><img src="/images_main/v_del.gif" border="0" /></a><!--삭제 btn-->
		              		</c:when>
			              	<c:otherwise>
				            	<c:if test="${strWriteAuth == 'T' || strWriteAuth == 'M' || strWriteAuth == 'A' }">
									<a href="javascript:goPasswordDialogPopup('u');"><img src="/images_main/v_modify.gif" border="0" /></a><!--저장 btn--> 
					              	<a href="javascript:goPasswordDialogPopup('d');"><img src="/images_main/v_del.gif" border="0" /></a><!--삭제 btn-->
								</c:if>
							</c:otherwise>
						</c:choose>
						<a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=r"><img src="/images_main/v_list.gif" border="0" /></a><!--목록 btn-->
					</td>
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
	
