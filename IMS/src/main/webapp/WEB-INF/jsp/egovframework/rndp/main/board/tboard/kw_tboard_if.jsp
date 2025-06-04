<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/js/kw_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script type="text/javascript">

$(document).ready(function(){	
	var width= '100%'; 
	$("#content").css('width',width); // 에디터 넓이 셋팅
});

</script>

<!-- 보드 내용시작 -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="/jsp/boardInsert.do">
				<input type="hidden" name="menuKey" value="${key}" /> 
				<input type="hidden" name="mngKey" value="${boardmng.key }"/>
				<input type="hidden" name="boardKey" value="${board.boardKey}" /> 
				<input type="hidden" name="depth" value="${board.depth }" />
				<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
				<input type="hidden" name="fileSize" id="fileSize" value="${boardmng.fileSize }" />
				<input type="hidden" name="fileNumber" id="fileNumber" value="${boardmng.fileNumber }" />
				<input type="hidden" name="fileFlag" id="fileFlag" value="${boardmng.fileFlag }" />
				<input type="hidden" name="smartStr" id="smartStr" value=""/>
				
				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
					<tr>
						<th class="boardtitle" width="140px" scope="row">제목</th>
						<td class="boardtext" colspan="3">
							<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box" value="${board.subject }" />
						</td>
					</tr>
					<tr>
						<th class="boardtitle" scope="row">아이디</th>
						<td class="boardtext" width="200px">
							<input type="text" id="id" name="id" value="${user.id}" size="20" maxlength="20" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
						<th class="boardtitle" width="100px" scope="row">성명</th>
						<td class="boardtext">
							<input type="text" id="name" name="name" value="${user.name }" size="20" maxlength="50" class="box" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
					</tr>
					<tr>
						<th class="boardtitle" scope="row">비밀번호</th>
						<td class="boardtext">
							<input type="password" id="password" name="password" maxlength="10" size="20" class="box" />
						</td>
						<th class="boardtitle" scope="row">E-mail</th>
						<td class="boardtext">
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
						<td colspan="4" align="center" scope="row">
							<textarea id="content" name="content" cols="100%" rows="15" style="font-size: 13px;"></textarea>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="4" align="center" scope="row"> -->
<!-- 							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="captcha"> -->
<!-- 								<tr valign="top"> -->
<!-- 									<td width="150px" valign="top"> -->
<%-- 									 <img src="<c:url value='/jcaptcha'/>" id="captchaImg" name="captchaImg" alt="캡차이미지" /> --%>
<!-- 									</td> -->
<!-- 									<td valign="middle"> -->
<!-- 										<p>왼쪽 이미지를 보이는 대로 입력해주세요.</p> -->
<!-- 										<input type="text" name="j_captcha" title="그림문자 입력" maxlength="5" style="width: 100px" class="input_txt2" value="" /> -->
										
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 							</table> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
		<tr>
			<td>
				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="okbtn">
					<tr height="35">
						<td align="center">
							<a href="#" onClick="javascript:fn_insert();"><img src="/images_main/v_ok.gif" border="0" /></a>						
							<a href="javascript:history.back(-1);"><img src="/images_main/v_cancle.gif" border="0" /></a> <!--취소 btn-->
							<a href="<c:url value='/webMenu.do'/>?key=${key}"><img src="/images_main/v_list.gif" border="0" /></a> <!--목록 btn-->
						</td>
					</tr>
				</table></td>
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

// 	function insertIMG(irid, filename, videoFlag, smartKey) {
// 		 var sHTML = "<img src='" + filename + "' border='0' >";
// 		oEditors.getById[irid].exec("PASTE_HTML", [ sHTML ]);
		
// 		if(document.frm.smartStr.value == ""){
// 			document.frm.smartStr.value = smartKey;
// 		}else{
// 			document.frm.smartStr.value +=","+smartKey;
// 		}
// 	}
	
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
		var ua = window.navigator.userAgent;
		
		var docName = obj.value.substring(obj.value.lastIndexOf("\\")+1);
		var prefixList = ['exe','cgi', 'pl', 'plx', 'html', 'htm', 'xhtml', 'php', 'php3', 'php4', 'asp', 'asa', 'jsp', 'jsa', 'java', 'htaccess'];
		var fielExt = getFileExtension(docName);
		var chkBw = browserChk();
       	for (var i = 0; i < prefixList.length; i++){
           if (fielExt == prefixList[i]) {
        	   alert("확장자가  "+fielExt+"인 파일은 첨부할 수 없습니다."); 
        	   if(chkBw == 'IE'){
        		   $("#"+fileId).replaceWith( $("#"+fileId).clone(true) );
        	   }else{
        		   $("#"+fileId).val(""); 
        	   }
        	  
               return false;
           }
       	} 
       	return false;
	}
	
	function browserChk(){
		var ua = window.navigator.userAgent;
		var browser = "";
		if(ua.indexOf('MSIE') > 0 || ua.indexOf('Trident') > 0){
			browser = "IE";
		}else if(ua.indexOf('Opera') > 0 || ua.indexOf('OPR') > 0){
			browser = "Opera";
		}else if(ua.indexOf('Firefix') > 0){
			browser = "Firefox";
		}else if(ua.indexOf('Safari') > 0) {
			if(ua.indexOf('Chrome') > 0){
				browser = "Chrome";
			}else{
				browser = "Safari";
			}
		}
			
		return browser;
	}
	</script>


				