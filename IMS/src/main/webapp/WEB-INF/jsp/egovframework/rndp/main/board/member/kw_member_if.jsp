<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">

function insertM_go(){
	with (document.frm)
	{
		var pass = password.value;
		var same = true, inc = true;

		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		if (id.value.length < 5)
		{
			alert("아이디는 5글자 이상 입력하세요.");
			id.focus();
			return false;
		}
		
		if(idflg.value == 'F'){
			alert("아이디 중복 확인을 해주세요.");
			id.focus();
			return false;
		}

		if (pass == "")
		{
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}

		if (pass != password2.value)
		{
			alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요.");
			password2.select();
			return false;
		}

		if (pass.length < 4)
		{
			alert("비밀번호는 4글자 이상 입력하세요.");
			password.focus();
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
			password.select();
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
			password.select();
			return false;
		}

		

		if (name.value == "")
		{
			alert("이름를 입력하세요.");
			name.focus();
			return false;
		}
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


// 아이디 체크
function checkId()
{
    var id = document.frm.id.value;
    newWin = window.open("<c:url value='/checkId.do'/>?id=" + id, "", "top=65, left=250, width=520, height=270");
}


// 우편번호 찾기
function searchPost()
{
    newWin = window.open("<c:url value='/post.do'/>", "", "top=65, left=250, width=520, height=270, scrollbars=yes");
}

</script>


		       
<form name="frm"  method="post" action="<c:url value='/webMemberInsert.do'/>" onSubmit="return insertM_go();">
<input type="hidden" id="menuKey" name="menuKey" value="${key}"/>
<input type="hidden" id="idflg" name="idflg" value="F"/>
<table cellSpacing="0" cellPadding="0" width="100%" border="0">	
	
	<tr>
		<td height="106" background="<c:url value='/images_in/sub_join_img.gif'/>" style="padding-left:190px;">
			<table width="510" border="0" cellspacing="2" cellpadding="2">
	            <tr>
	              <td><img src="<c:url value='/images_in/blt_orange.gif'/>" width="8" height="8" align="absmiddle" /> 아래 빈칸을 채워주세요. (<span class="mran"><strong>*</strong></span>) 표시는 꼭 입력해주세요.</td>
	            </tr>
			</table>         
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><!--표-2 -->
			<table width="740px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-top:10px;">
					<!-- 게시판 Start -->		
						<table cellSpacing="0" cellPadding=0 width="100%" border="0" class="board_view">
							<tr>
								<TH width="150"  scope=row>아 이 디<span class="mran">*</span></TH>
								<TD>
									<input type="text" name="id" maxLength="15">
									&nbsp;<a href="javascript:checkId();"><img src="<c:url value='/images_in/k_checkid.gif'/>" alt="중복확인" border=0 align=absMiddle></a>
								    &nbsp;&nbsp;5~15자의 영문소문자, 숫자, '_', '-' 만 가능합니다.</TD>
							</tr>
							<tr>
								<TH scope=row>비밀번호<span class="mran">*</span></TH>
								
								</td>
								<TD ><input type="password" name="password" maxLength="15" size="21">
								&nbsp;&nbsp;4~15자의 영문대소문자, 숫자, '_', '-' 만  가능합니다.</TD>
							</tr>										
							<tr>
								<TH class=line_right scope=row>&nbsp;비밀번호 확인<span class="mran">*</span></TH>
								<TD class=td_view><input type="password" name="password2" maxLength="40" size="21"></td>
							</tr>
							<tr>
								<TH class=line_right scope=row>이 름<span class="mran">*</span></TH>
								<TD class=td_view><input type="text" name="name" maxLength="40"></td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>전화번호</TH>
								<TD class=td_view>
									<input type="text" name="telephone1" maxLength="4" size="4"> -
                 	                        <input type="text" name="telephone2" maxLength="4" size="4"> -
									<input type="text" name="telephone3" maxLength="4" size="4">
								</td>
							</tr>
							<tr>
								<TH class=line_right scope=row>핸 드 폰</TH>
								<TD class=td_view>
									<input type="text" name="mobile1" maxLength="4" size="4"> -
									<input type="text" name="mobile2" maxLength="4" size="4"> -
									<input type="text" name="mobile3" maxLength="4" size="4">
								</td>
							</tr>
							<tr>
								<TH class=line_right scope=row>이 메 일<span class="mran">*</span></TH>
								<TD class=td_view>
									<input type="text" name="email" maxLength="100" size="30">
									<input type="radio" value="T" name="emailFlag" onClick="blur()" checked>수신&nbsp;
									<input type="radio" value="F" name="emailFlag" onClick="blur()">수신거부
								</td>
					
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
						<input type="image" src="<c:url value='/images_in/k_save.gif'/>">
						<a href="javascript:insert_reset();"><img src="<c:url value='/images_in/k_cancel.gif'/>" border="0"></a>
						<a href="javascript:history.back();"><img src="<c:url value='/images_in/k_no.gif'/>" border="0"></a></td>
						
					</td>
				</tr>						
			</table>
			</DIV>
			</DIV>
		</td>
	</tr>						
</table>
</form>
		            
					   
					   
					   
					   
					   
					   
					