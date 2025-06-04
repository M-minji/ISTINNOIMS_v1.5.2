<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/js/kw_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>


			
	<div class="sub_container">
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="20">&nbsp;</td>
				<td width="160" valign="top">
					<!-- left 메뉴 --> <c:out value="${subMenu }" escapeXml="false" /> <!-- left 메뉴 -->
				</td>
				<td width="30">&nbsp;</td>
				<td width="30" class="contents_bg">&nbsp;</td>
				<td width="760" valign="top">
					<!--컨텐츠 시작-->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="30" class="navigate"><img
								src="./images_smart/sub/navi_icon.gif" align="absmiddle" /> <span
								class="navigate_s"><c:out value="${menuCategory}"
										escapeXml="false" /></span></td>
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
<div id="sub_container2">						
			<!-- 보드 내용시작 -->
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td>
						<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="/boardInsert.do">
							<input type="hidden" name="menuKey" value="${key}" /> 
							<input type="hidden" name="mngKey" value="${boardmng.key }"/>
							<input type="hidden" name="boardKey" value="${board.boardKey}" /> 
							<input type="hidden" name="depth" value="${board.depth }" />
							<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
							<input type="hidden" name="fileSize" id="fileSize" value="${boardmng.fileSize }" />
							<input type="hidden" name="fileNumber" id="fileNumber" value="${boardmng.fileNumber }" />
							<input type="hidden" name="fileFlag" id="fileFlag" value="${boardmng.fileFlag }" />
							
							<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
								<tr>
									<th width="100" scope="row">제목</th>
									<td colspan="3">
										<c:if test="${user.type eq 'A'}">
										<font color="red"><b>공지</b></font>&nbsp;										
										<input type="checkbox" name="pubflag" value="T" border="0" /> &nbsp;
										</c:if> 
										<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box" value="${board.subject }" />
									</td>
								</tr>
								<tr>
									<th scope="row">아이디</th>
									<td width="250">
										<input type="text" id="id" name="id" value="${user.id}" size="20" maxlength="20" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
									</td>
									<th width="100" scope="row">성명</th>
									<td>
										<input type="text" id="name" name="name" value="${user.name }" size="20" maxlength="50" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
									</td>
								</tr>
								<tr>
									<th scope="row">비밀번호</th>
									<td>
										<input type="password" id="password" name="password" maxlength="10" size="20" class="box" />
									</td>
									<th scope="row">E-mail</th>
									<td>
										<input type="text" id="email" name="email" value="" maxlength="100" size="20" class="box" />
									</td>
								</tr>
								<c:if test="${boardmng.secretFlag == 'T'}">
								<tr>
									<th scope="row">비공개</th>
									<td colspan="3">
										<input type="radio" id="share" name="share" value="F" border="0" />설정
										<input type="radio" id="share" name="share" value="T" border="0" checked="checked" />해제
									</td>
								</tr>
								</c:if>
								<c:if test="${boardmng.fileFlag == 'T' }">
								<c:forEach var="i" begin="1" end="${boardmng.fileNumber}">
									<tr>
										<th class="line_right" scope="row">첨부파일${i}</th>
									 	<td class="td_view" colspan="3">
									 		<input type="file" name="filename${i}" id="filename${i}" maxLength="100" size="45" class="" onChange="fileExtCheck(this,'${i}', 'filename${i}');">(용량 : ${boardmng.fileSize } MB)
									 	</td>
									</tr>
								</c:forEach>
								</c:if>
								<tr>
									<td colspan="4" align="center" scope="row"><textarea id="content" name="content" cols="100%" rows="15" style="font-size: 13px;"></textarea></td>
								</tr>
								<tr>
									<td colspan="4" align="center" scope="row">
										<table width="100%" cellspacing="0" cellpadding="0" border="0" class="captcha">
											<tr valign="top">
												<td width="150" valign="top">
												 <img src="<c:url value='/jcaptcha'/>" id="captchaImg" name="captchaImg" alt="캡차이미지" />
												</td>
												<td valign="middle">
													<p>왼쪽 이미지를 보이는 대로 입력해주세요.</p>
													<input type="text" name="j_captcha" title="그림문자 입력" maxlength="5" style="width: 100px" class="input_txt2" value="" />
													
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr height="35">
					<td align="right">
						<a href="#" onClick="javascript:fn_insert();"><img src="./images_smart/board/ok.gif" border="0" /></a>						
						<a href="javascript:insert_reset();"><img src="./images_smart/board/cancle.gif" border="0" /></a> <!--취소 btn-->
						<a href="<c:url value='/webMenu.do'/>?key=${key}"><img src="./images_smart/board/list.gif" border="0" /></a> <!--목록 btn-->
					</td>
				</tr>
			</table></td>
	</tr>
</table> <script type="text/javascript">
	var imagepath = "./../smartImage";
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : 'content',
		sSkinURI : "<c:url value='/se/SEditorSkin.html'/>",
		fCreator : "createSEditorInIFrame"
	});

	function insertIMG(irid, fileame) {
		var sHTML = "<img src='" + imagepath + "/" + fileame + "' border='0' style='width:300; height:300;'>";
		oEditors.getById[irid].exec("PASTE_HTML", [ sHTML ]);
	}
	
	function fn_insert() {

		var frm = document.frm;
		oEditors.getById["content"].exec("UPDATE_IR_FIELD", []);
		if (frm.subject.value == "") {
			alert("제목을 입력하세요.");
			frm.subject.focus();
			return false;
		}

		if (frm.id.value == "") {
			alert("아이디를 입력하세요.");
			frm.id.focus();
			return false;
		}

		if (frm.name.value == "") {
			alert("이름을 입력하세요.");
			frm.name.focus();
			return false;
		}

		if (frm.password.value == "") {
			alert("비밀번호를 입력하세요.");
			frm.password.focus();
			return false;
		}
		if (confirm("저장하시겠습니까?")) {
			frm.submit();
		}
	}

	
	
	
	
	function getFileExtension( fileExtName ){
	    var lastIndex = -1;
	    lastIndex = fileExtName.lastIndexOf('.');
	    var extension = "";
		if ( lastIndex != -1 ){
	    	extension = fileExtName.substring( lastIndex+1, fileExtName.len );
		} else {
	    	extension = "";
		}
		return extension;
	}
	
	function fileExtCheck(obj, cnt, fileId){   
		var docName = obj.value.substring(obj.value.lastIndexOf("\\")+1);
		var prefixList = ['exe','cgi', 'pl', 'plx', 'html', 'htm', 'xhtml', 'php', 'php3', 'php4', 'asp', 'asa', 'jsp', 'jsa', 'java', 'htaccess'];
		var fielExt = getFileExtension(docName);
       	for (var i = 0; i < prefixList.length; i++){
           if (fielExt == prefixList[i]) {
        	   alert("확장자가  "+fielExt+"인 파일은 첨부할 수 없습니다."); 
        	   document.getElementById(fileIdt).select();
			   document.selection.clear();
               return false;
           }
       	} 
       	return false;
		}
	</script>


				</td>
			</tr>
			<tr>
				<td height="50">&nbsp;</td>
			</tr>
		</table>
		<!--컨텐츠 끝-->
	</div>
	
