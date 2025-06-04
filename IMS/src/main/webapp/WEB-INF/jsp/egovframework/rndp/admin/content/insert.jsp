<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript" src="<c:url value='/js/km_content.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tools.min.js'/>"></script>

<script type="text/javascript" src="<c:url value="/se/js/HuskyEZCreator.js" />"></script>
<script type="text/javascript">
function insert_go(){
	var frm = document.frm;	
	oEditors.getById["content"].exec("UPDATE_IR_FIELD", []);
	if (frm.content.value == ""){
		alert("내용을 입력하세요.");
		frm.content.focus();
		return ;
	}
	
	if(confirm("저장하시겠습니까?")){
		frm.submit();
	}
}
</script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>컨텐츠입력 (${menu.name})</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;컨텐츠관리&nbsp;>&nbsp;컨텐츠입력</p>
			</div>
		</div>
		<form name="frm" method="post" action="<c:url value='/admin/contentInsert.do'/>">
			<input type="hidden" name="root" value="${menu.root}">
								<input type="hidden" name="rootKey" value="${menu.rootKey}">
								<input type="hidden" name="groupKey" value="${menu.groupKey}">
								<input type="hidden" name="menuKey" value="${menu.menuKey}">
								<input type="hidden" name="type" value="admin" /><!-- 동영상 관련 체크 -->

			<TEXTAREA id="content" name="content" class="textarea2" cols="99%" rows="35"  style="font-size:13px;"></TEXTAREA>
			<br>
			<br>
		 <div class="tbl_btn_right">
			<ul>
				<li>
					<a href="javascript:insert_go();">저장</a> 
				</li>
				<li>
					<a href="javascript:insert_reset();">취소</a>
				</li>
				<li>
					 <a href="<c:url value='/admin/adminContentList.do'/>?rootKey=${menu.rootKey}&groupKey=${menu.groupKey}">목록</a></td>
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
<script type="text/javascript"> 
 var imagepath = "../smartImage";
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "<c:url value='/se/SEditorSkin.html'/>",
		fCreator: "createSEditorInIFrame"
	});

	function insertIMG(irid,fileame){
		  var sHTML = "<img src='" + imagepath + "/" + fileame + "' border='0'>";
		  oEditors.getById[irid].exec("PASTE_HTML", [sHTML]);
		}
</script> 
</html>
