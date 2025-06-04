<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="<c:url value='/js/kw_login.js'/>"></script>

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
							
<!-- 보드 내용시작 -->

<div id="sub_container2">
<form name="frm" id="frm" method="post" action="<c:url value='/login.do'/>" onSubmit="return insert_go()">
<input type="hidden" id="menuKey" name="menuKey" value="${key}"/>							
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_box">
            <tr>
              <th width="70">&nbsp;</th>
              <th><table border="0" cellspacing="0" cellpadding="0" class="login_box2">
                <tr>
                  <th width="80">아이디</th>
                  <td width="310">
                  	<input type="text" class="login_input" style="margin-bottom:3px; line-height:31px;" name='id' tabIndex='1'/>
                  </td>
                  <td width="130" rowspan="2">
                  	<!--로그인 btn -->
                  	<input type='image' src="./images_smart/sub/btn_login.gif" tabIndex='5'/>
                  </td>
                </tr>
                <tr>
                  <th>비밀번호</th>
                  <td>
                  	<input type="password" class="login_input" style="margin-bottom:3px; line-height:31px;" name='password' tabIndex='2' />
                  </td>
                </tr>
                <tr>
                  <th width="80"></th>
                  <td width="310">
                    <table cellspacing=0 cellpadding=0 width="50%" border=0>
                    <tbody>
                    <tr>
                    <td><input onclick='blur()' type='radio' checked value='M' name='type' tabIndex='3'/> 일반인 </td>
                    <td><input onclick='blur()' type='radio' value='A' name='type' tabIndex='4'/> 관리자 </td>
                    </tr>
                    </tbody>
                    </table>
                  </td>
                </tr>
              </table></th>
              <th width="70">&nbsp;</th>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><table border="0" cellspacing="0" cellpadding="0" class="login_box_search">
                <tr>
                  <th width="350">아직 회원이 아니십니까?</th>
                  <td width="155"><!--회원가입 btn --><a href="<c:url value='/webMenu.do'/>?key=21"><img src="./images_smart/sub/btn_join.gif" /></a></td>
                </tr>
                <tr>
                  <th>아이디 또는 비밀번호를 잊으셨나요?</th>
                  <td><a><!--아이디 비밀번호찾기 btn --><img src="./images_smart/sub/btn_search.gif" /></a></td>
                </tr>
              </table></td>
              <td>&nbsp;</td>
            </tr>
          </table>				
</form>	
</div>					
<!-- 보드 내용 끝 -->								
							
							
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
