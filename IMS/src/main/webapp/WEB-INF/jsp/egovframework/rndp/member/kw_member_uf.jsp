<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
function insertM_go(){
	with (document.frm)
	{
		/* 
		if (address1.value == "")
		{
			alert("주소를 입력하세요.");
			address1.focus();
			return false;
		}

		if (address2.value == "")
		{
			alert("주소를 입력하세요.");
			address2.focus();
			return false;
		}
 */
 		if(changeFlag.value == "F"){
			if (isNaN(telephone1.value))
			{
				alert("전화번호는 숫자로 입력하세요.");
				telephone1.select();
				return false;
			}
	
			if (isNaN(telephone2.value))
			{
				alert("전화번호는 숫자로 입력하세요.");
				telephone2.select();
				return false;
			}
	
			if (isNaN(telephone3.value))
			{
				alert("전화번호는 숫자로 입력하세요.");
				telephone3.select();
				return false;
			}
	
			if (isNaN(mobile1.value))
			{
				alert("핸드폰번호는 숫자로 입력하세요.");
				mobile1.select();
				return false;
			}
	
			if (isNaN(mobile2.value))
			{
				alert("핸드폰번호는 숫자로 입력하세요.");
				mobile2.select();
				return false;
			}
	
			if (isNaN(mobile3.value))
			{
				alert("핸드폰번호는 숫자로 입력하세요.");
				mobile3.select();
				return false;
			}
	
			if (email.value == "")
			{
				alert("이메일을 입력하세요.\n비밀번호 분실시 사용됩니다.");
				email.focus();
				return false;
			}
 		}else{
 			
 			var pass = changePasswod.value;
 			var same = true, inc = true;
 			
 			if (password.value == "")
 			{
 				alert("현재 비밀번호를 입력하세요.");
 				password.focus();
 				return false;
 			}
 			
 			if (pass == "")
 			{
 				alert("변경할 비밀번호를 입력하세요.");
 				changePasswod.focus();
 				return false;
 			}

 			if (pass != password2.value)
 			{
 				alert("변경할 비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요.");
 				password2.select();
 				return false;
 			}

 			if (pass.length < 4)
 			{
 				alert("비밀번호는 4글자 이상 입력하세요.");
 				changePasswod.focus();
 				return false;
 			}

 			for (var i = 0; i < pass.length - 1; i++)
 			{
 				if (pass.charAt(i) != pass.charAt(i + 1))
 				{
 					same = false;
 				}
 			}

 			if (same)
 			{
 				alert("비밀번호는 같은 단어를 연속으로 사용할 수 없습니다.");
 				changePasswod.select();
 				return false;
 			}

 			for (var i = 0; i < pass.length - 1; i++)
 			{
 				if (pass.charCodeAt(i) != pass.charCodeAt(i + 1) - 1)
 				{
 					inc = false;
 				}
 			}

 			if (inc)
 			{
 				alert("비밀번호는 증가값을 사용할 수 없습니다.");
 				changePasswod.select();
 				return false;
 			}
 		}
		
		return true;
	}
}


function insert_reset()
{
	with (document.frm)
	{
		reset();
	}
}

</script>

<div class="sub_container">
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
						<td height="55" class="navigate">
						<img src="./images_smart/sub/navi_icon.gif" align="absmiddle" />
						<span class="navigate_s"><c:out value="${menuCategory}" escapeXml="false" /></span>
						</td>
					</tr>
					<tr>
						<!-- 타이틀 -->	
						<td height="50" class="contents_title">${menuNm}</td>
					</tr>
					<tr>
						<td height="35">&nbsp;</td>
					</tr>
					<tr>
						<td>

<form name="frm" method="post" action="<c:url value='/webMemberUpdate.do'/>" onSubmit="return insertM_go();">
<input type="hidden" id="menuKey" name="menuKey" value="${key}"/>
<input type="hidden" name="key" value='${memInfo.key }'>
<input type="hidden" name="changeFlag" value='${memInfo.changeFlag }'>
<table cellSpacing="0" cellPadding="0" width="100%" border="0">	

	<tr>
		<td><!--표-2 -->
			<table width="740px" border="0" cellpadding="0" cellspacing="0">
			
				<tr>
					<td style="padding-top:10px;">
					<!-- 게시판 Start -->		
						
						<table cellSpacing="0" cellPadding=0 width="100%" border="0" class="board_view">
						<c:if test="${memInfo.changeFlag eq 'F' }">
							<tr>
								<TH width="150" class=line_right scope=row>아 이 디</TH>
								<TD class=td_view>${memInfo.id }</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>이 름</TH>
								<TD class=td_view>${memInfo.name }</td>
							</tr>
							
							<tr>
								<TH width="150" class=line_right scope=row>이 메 일</TH>
								<TD class=td_view>
									<input type="text" name="email" class="box" value="${memInfo.email }" maxLength="100" size="30">
									<input type="radio" value="T" name="emailFlag" <c:if test="${memInfo.emailFlag == 'T' }">checked</c:if>>수신&nbsp;
									<input type="radio" value="F" name="emailFlag" <c:if test="${memInfo.emailFlag == 'F' }">checked</c:if>>수신거부
								</td>
							</tr>
							<%-- 
							<tr>
								<TH width="150" class=line_right scope=row>우편번호</TH>
								<TD class=td_view>
									<input type="text" name="post1" value="<%=member.getPost1()%>" maxLength="3" size="3"> -
									<input type="text" name="post2" value="<%=member.getPost2()%>" maxLength="3" size="3">
									<a href="javascript:searchPost();"><img src="image/k_search.gif" border="0" align="absmiddle"></a>
								</td>
							</tr>
							 --%>
							<%--  
							<tr>
								<TH width="150" class=line_right scope=row>주 소</TH>
								<TD class=td_view><input type="text" name="address1" value="<%=member.getAddress1()%>" maxLength="100" size="50"></td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>나머지 주소</TH>
								<TD class=td_view><input type="text" name="address2" value="<%=member.getAddress2()%>" maxLength="100" size="50"></td>
							</tr>
							 --%>
							<tr>
								<TH width="150" class=line_right scope=row>전화번호</TH>
								<TD class=td_view>
									<input type="text" class="box" name="telephone1" value="${memInfo.telephone1 }" maxLength="4" size="4"> -
                 	                        <input type="text" class="box" name="telephone2" value="${memInfo.telephone2 }" maxLength="4" size="4"> -
									<input type="text" class="box" name="telephone3" value="${memInfo.telephone3 }" maxLength="4" size="4">
								</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>핸 드 폰</TH>
								<TD class=td_view>
									<input type="text" class="box" name="mobile1" value="${memInfo.mobile1 }" maxLength="4" size="4"> -
									<input type="text" class="box" name="mobile2" value="${memInfo.mobile2 }" maxLength="4" size="4"> -
									<input type="text" class="box" name="mobile3" value="${memInfo.mobile3 }" maxLength="4" size="4">
								</td>
							</tr>
						</c:if>
						<c:if test="${memInfo.changeFlag eq 'T' }">
							<tr>
								<TH width="150" class=line_right scope=row>현재 비밀번호<span class="mran">*</span></TH>
								<TD class=td_view><input type="password" class="box" name="password" value="" maxLength="8" size="21">
								&nbsp;&nbsp;4~15자의 영문대소문자, 숫자, '_', '-' 만  가능합니다.</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>변경 비밀번호<span class="mran">*</span></TH>
								<TD class=td_view><input type="password" class="box" name="changePasswod" value="" maxLength="8" size="21">
								&nbsp;&nbsp;4~15자의 영문대소문자, 숫자, '_', '-' 만  가능합니다.</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>변경 비밀번호 확인<span class="mran">*</span></TH>
								<TD class=td_view><input type="password" class="box" name="password2" value="" maxLength="8" size="21"></td>
							</tr>
						</c:if>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
    	<td>&nbsp;</td>
  	</tr>
	<tr>
		<td>	
			<DIV class=list_btn>
            <DIV class=bg align="center">
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" valign="middle">
						<input type="image" src="/images_in/btn/btn_save.gif">
						<a href="javascript:insert_reset();"><img src="/images_in/btn/btn_cancle.gif" border="0"></a>
						<a href="javascript:history.back();"><img src="/images_in/btn/btn_again.gif" border="0"></a>
						
					</td>
				</tr>						
			</table>
			</DIV>
			</DIV>					
		</td>
	</tr>			
</table>
</form>

	</td>
						</tr>
						<tr>
							<td height="50">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--컨텐츠 끝-->
	</div>