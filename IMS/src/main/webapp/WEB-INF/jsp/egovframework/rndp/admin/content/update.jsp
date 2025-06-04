<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" src="<c:url value='/js/kw_common.js'/>"></script>
<script language="javascript" src="<c:url value='/js/km_content.js'/>"></script>

<script type="text/javascript" src="<c:url value="/se/js/HuskyEZCreator.js" />"></script>
<script type="text/javascript">

function insert_go()
{
	var frm = document.frm;	
	oEditors.getById["content"].exec("UPDATE_IR_FIELD", []);
	if (frm.content.value == ""){
		alert("내용을 입력하세요.");
		frm.content.focus();
		return false;
	}
	
	if(confirm("수정하시겠습니까?")){
		frm.submit();
	}
}
</script>
</head>
<body class="content_bg">
<div id="contents">
  <div class="content_tit">
  	<h2>컨텐츠입력</h2>
  	<div class="path">
  		<p>HOME&nbsp;>&nbsp;컨텐츠관리&nbsp;>&nbsp;컨텐츠입력</p>
  	</div>
</div>
<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
				<tr>
		          	<td class="path">          
			            <table border="0" align="right" cellpadding="0" cellspacing="0">
			              <tr>
			                <td>HOME > <span class="path">컨텐츠 관리</span> >  <span class="path_s">컨텐츠 입력</span></td>
			              </tr>
			            </table>
			        </td>
		        </tr>			
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
					        	<td>
						          	<table border="0" cellpadding="0" cellspacing="0" class="center_tbg">
										<tr>
											<td class="center_txt" >컨텐츠 입력  (${menu.name})</td>
										</tr>
									</table>
								</td>
				        	</tr>
							<tr>
				         		<td>
								<form name="frm" method="post" action="<c:url value='/admin/contentUpdate.do'/>">						
								<input type="hidden" name="root" value="${menu.root}">
								<input type="hidden" name="rootKey" value="${menu.rootKey}">
								<input type="hidden" name="groupKey" value="${menu.groupKey}">
								<input type="hidden" name="menuKey" value="${menu.menuKey}">
								<input type="hidden" name="type" value="admin" /><!-- 동영상 관련 체크 -->
									<table border="0" cellpadding="0" cellspacing="1">
										<tr>
											<td bgcolor="white" style="padding:2">
												<table border="0" cellspacing="0" cellpadding="0" >
												<tr>
													<td>
							
													<TEXTAREA id="content" name="content" class="textarea2" cols="99%" rows="35"  style="font-size:13px;"><c:out value='${menu.content }' escapeXml="false"/></TEXTAREA>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr><td height="30"></td></tr>
										<tr>
											<td>
											<div class=list_btn>
          									<div class=bg align="center">
											<table border="0" cellspacing="1" cellpadding="1">
												<tr>
													<td align="right" valign="middle">
														<a href="javascript:insert_go();">저장</a>
														<a href="javascript:insert_reset();">취소</a>
														<a href="<c:url value='/admin/adminContentList.do'/>?rootKey=${menu.rootKey}&groupKey=${menu.groupKey}">등록</a>
													</td>
												</tr>
											</table>
											</div>
											</div>
											</td>
										</tr>			
									</table>
								</form>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
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
</body>
</html>
