<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
						
<!-- 보드 내용시작 -->


<form name="frm" id="frm" method="post" action="<c:url value='/login.do'/>" onSubmit="return insert_go()">
<input type="hidden" id="menuKey" name="menuKey" value="${key}"/>
<input type="hidden" id="type" name="type" value="A" />							
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_box">
     <tr>
       <th width="70">&nbsp;</th>
       <th><table border="0" cellspacing="0" cellpadding="0" class="login_box2">
         <tr>
           <th width="80">아이디</th>
           <td width="310">ddd<input type="text" class="login_input" style="margin-bottom:3px; line-height:31px;" name='id' tabIndex='1'/></td>
           <td width="130" rowspan="2">sss<!--로그인 btn --><input type='image' src="/images/images_btn/login_b_btn.gif" tabIndex='5'/></td>
         </tr>
         <tr>
           <th>비밀번호</th>
           <td><input type="password" class="login_input" style="margin-bottom:3px; line-height:31px;" name='password' tabIndex='2' /></td>
         </tr>
       </table></th>
       <th width="70">&nbsp;</th>
     </tr>
   </table>				
</form>						
<!-- 보드 내용 끝 -->								
							
	