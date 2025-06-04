<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>아이디(ID) 확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/intranet/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/intranet/board.css'/>" />
<script language="javascript">
<!--
function check()
{
	with (document.searchform)
	{
		if (id.value == "")
		{
			alert("등록하시려는 아이디(ID)를 입력하세요.");
			return false;
		}

		if (id.value.length < 5)
		{
			alert("등록하시려는 아이디(ID)는 5자이상 입력하세요.");
			return false;
		}

		return true;
	}
}

function selectID(id2)
{
	with (opener.frm)
	{
		id.value = id2;
		idflg.value='T';
		id.focus();
	}

	self.close();
}
//-->
</script>
</head>

<body onload="document.searchform.id.focus();">
<table class="popup_top">
 <tr>
  <td class="tit">아이디 중복 검색</td>
 </tr>
</table>
<form name="searchform" method="post" action="<c:url value="/checkId.do"/>" onSubmit="return check();">
<table class="popup_content">
 <tr>
  <td>
  <c:if test="${result == false && id != '' }">
   <table class="top">
    <tr>
     <td>입력하신 <b>'${id}'</b> 아이디(ID) 는 사용하실 수 없습니다.</td>
    </tr>
   </table>  
   </c:if>
   <c:if test="${result == true}">       
  
   <table class="top">
    <tr>
     <td>입력하신  <b>'${id }'</b> 아이디(ID) 는  사용하실 수 있습니다.<br>
						신청하시려면 '신청하기' 버튼을 누르시기 바랍니다.</td>
	 <td><a href="javascript:selectID('${id }');"><img src="<c:url value='/images_in/btn/btn_id_app.gif'/>" border="0" align="absmiddle"></a></td>
    </tr>
   </table>
	</c:if>                        
   <table class="post">
    <tr>
     <td>사용하려는 아이디(ID)를 입력하시고 중복여부를 확인하시기 바랍니다.<br>
		 <b>아이디(ID) 입력</b>&nbsp;&nbsp;&nbsp;
		 <input type="text" name="id" value="${id }" maxlength="15">
		 <input type="image" src="<c:url value='/images_in/k_checkid.gif'/>" alt="중복확인" align="middle">								</td>
    </tr>	
   </table>
</form>
<table class="tbl_btn">
 <tr>
  <td>
		<a href="javascript:self.close();"><img src="<c:url value='/images_in/btn_close.gif'/>" style="width:50px; height:23px" alt="닫기"/></a>
		</td></tr></table>
</body>
</html>
