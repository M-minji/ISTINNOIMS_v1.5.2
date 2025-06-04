<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
		       <!-- ###################### content start ########################## -->

<table cellSpacing="0" cellPadding="0" width="100%" border="0">	

	<tr>
		<td>
			<table width="740px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-top:10px;">
						<table cellSpacing="0" cellPadding=0 width="100%" border="0" class="board_view">	
							<tr>
								<TH width="150" class=line_right scope=row>아 이 디</th>
								<TD class=td_view>&nbsp;${memInfo.id }</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>이 름</th>
								<TD class=td_view>&nbsp;${memInfo.name }</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>이 메 일</th>
								<TD class=td_view>&nbsp;${memInfo.email } ${memInfo.emailFlagForm }
								</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>전화번호</th>
								<TD class=td_view>&nbsp;${memInfo.telephone }</td>
							</tr>
							<tr>
								<TH width="150" class=line_right scope=row>핸 드 폰</th>
								<TD class=td_view>&nbsp;${memInfo.mobile }</td>
							</tr>
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
						<a href="javascript:document.writeform.submit();">
						<img src="/images_in/btn/btn_modify.gif" border="0"></a>	
						<a href="javascript:document.writeformPass.submit();">
						<img src="/images_in/btn/btn_pass_chg.gif" border="0"></a>						
													
						<a href="javascript:history.back();"><img src="/images_in/btn/btn_again.gif" border="0"></a>								
					</td>
				</tr>						
			</table>
			</DIV>
			</DIV>
		</td>
	</tr>
</table>
<form name="writeform" method="post" action="<c:url value='/webMenu.do'/>">	
<input type="hidden" name="key" value='24'>
</form>
<form name="writeformPass" method="post" action="<c:url value='/webMenu.do'/>">	
<input type="hidden" name="key" value='24'>
<input type="hidden" name="pubflag" value='T'>
</form>
		            
					  
			   <!-- ###################### content end ########################## -->
			
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