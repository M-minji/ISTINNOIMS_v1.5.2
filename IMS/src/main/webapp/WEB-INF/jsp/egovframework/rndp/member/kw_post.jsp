<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>우편번호 찾기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/intra/common.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/intra/board.css'/>" />
<script language="JavaScript">
	function check() {
		with (document.searchform) {
			if (searchWord.value == "") {
				alert("검색하시려는 읍/면/동/리를 입력하세요.");
				return false;
			} else {
				submit();
			}
		}
	}

	function selectPost(post, addr) {
		
		if(typeof(opener.selectPost) != "undefined"){
			opener.selectPost(post, addr);
		}else if(typeof(opener.selectPost_staffIntra) != "undefined"){
			opener.selectPost_staffIntra(post, addr);
		}else if(typeof(opener.selectPost_staffIntra2) != "undefined"){
			opener.selectPost_staffIntra2(post, addr);
		}else if(typeof(opener.selectPost_company) != "undefined"){
			opener.selectPost_company(post, addr);
		}else{
			if(typeof(opener.frm) != "undefined"){
				with (opener.frm) {
					
					post1.value = post.substring(0, 3);
					post2.value = post.substring(4, 7);
					address1.value = addr;
					address2.focus();
				}
			}
		}
		
// 		if(typeof(opener.frm) != "undefined"){
// 			with (opener.frm) {
				
// 				post1.value = post.substring(0, 3);
// 				post2.value = post.substring(4, 7);
// 				address1.value = addr;
// 				address2.focus();
// 			}
// 		}else{
			
// 			if(typeof(opener.selectPost) != "undefined"){
// 				opener.selectPost(post, addr);
// 			}
			
// 			if(typeof(opener.selectPost_staffIntra) != "undefined"){
// 				opener.selectPost_staffIntra(post, addr);
// 			}
			
// 			if(typeof(opener.selectPost_staffIntra2) != "undefined"){
// 				opener.selectPost_staffIntra2(post, addr);
// 			}
// 		}
		
		self.close();
	}
</script>
</head>

<body onload="document.searchform.searchWord.focus();">
<form name="searchform" method="post" action="<c:url value='/post.do'/>" onSubmit="return check();">
<table class="popup_top">
 <tr>
  <td class="tit">우편번호 찾기</td>
 </tr>
</table>
<table class="popup_content">
 <tr>
  <td>
   <table class="top">
	 <tr>
		 <td>찾고자 하는 읍/면/동/리의 이름을 입력해 주십시오.
			 <br>(예 : 서울시 강남구 역삼동 => <strong>역삼, 역삼동</strong>)</td>
	 </tr>
   	</table>
   	<table class="post">
		<tr>
			<td><strong>읍/면/동/리  입력</strong></td>
			<td><input type="text" name="searchWord" value="" maxlength="10"></td>
			<td><input type="image" src="<c:url value='/images_in/k_find.gif'/>" alt="찾기" align="middle"></td>						
								</tr>
							</table>
				<table class="post_list" >
					<c:forEach var="result" items="${result}">
					<tr>
						<td>&nbsp;&nbsp;<a href="javascript:selectPost('${result.zipcode}', '${result.sido} ${result.gugun } ${result.dong } ${result.bunji }');">
						${result.zipcode }&nbsp;&nbsp;${result.sido }${result.gugun }${result.dong }${result.bunji }
						</td>
					</tr>
					</c:forEach>
				</table>
				<table class="tbl_btn">
			 		<tr>
			  			<td><a href="javascript:self.close();"><img src="<c:url value='/images_in/btn/btn_close.gif'/>" alt="닫기" /></a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>