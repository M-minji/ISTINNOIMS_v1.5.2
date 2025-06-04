<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"	src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script language="JavaScript">

	$(document).ready(function(){	
		var width= '100%'; 
		$("#content").css('width',width); // 에디터 넓이 셋팅
	});

	var msgChk = "";
		
	function fn_update(){
	
		var frm = document.frm;	
		oEditors.getById["content"].exec("UPDATE_IR_FIELD", []);
		if (frm.subject.value == ""){
			alert("제목을 입력하세요.");
			frm.subject.focus();
			return false;
		}
	
		if (frm.id.value == ""){
			alert("아이디를 입력하세요.");
			frm.id.focus();
			return false;
		}
	
		if (frm.name.value == ""){
			alert("이름을 입력하세요.");
			frm.name.focus();
			return false;
		}
	
		if (frm.password.value == ""){
			alert("비밀번호를 입력하세요.");
			frm.password.focus();
			return false;
		}
		if(confirm("저장하시겠습니까?")){
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
	     return true;
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
	
	function fileKeyDelete(key, cnt){
		$('#fileKey').val(key)
		$.ajax({
			type: "post",                                           //포스트 방식
		 	dataType : "json",                                      //dataType
		  	url: "/fileKeyDelete.do",         						//url
		  	data: $('#frm').serialize(),				
		  	success: function(msg){									//응답이 성공 상태 코드를 반환하면 호출	
			  	if(msg.result.result == "success"){
			  		$('#'+key).remove();
			  		$('#filename_'+cnt).attr("name", "filename_0_"+cnt)
			  	}
			},
			error: function (e) { 
				alert('에러발생'); 
			}                         
			
		});
	}
</script>
</head>



<!-- 보드 내용시작 -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<Td style="text-align: left; height: 50px;">
			<b>파일 변경시에는 변경하고싶은 첨부파일의 번호에서 첨부문서를 다시 선택하면 됩니다.</b><br/>
			<b>첨부파일에 삭제버튼을 클릭하면 첨부파일이 삭제 됩니다.</b>  
		</Td>
	</tr>
	<tr>
		<td>
			<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="<c:url value='/jsp/boardUpdate.do'/>">
				<%-- <input type="hidden" name="menuKey" value="${key}" /> 
				<input type="hidden" name="mngKey" value="${boardmng.key }"/>
				<input type="hidden" name="boardKey" value="${boardUser.boardKey}" /> 
				<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
				
				 --%>
				<input type="hidden" name="menuKey" value="${key}" /> 
				<input type="hidden" name="mngKey" value="${boardmng.key }"/>
				<input type="hidden" name="boardKey" value="${boardUser.boardKey}" /> 
				<input type="hidden" name="depth" value="${boardUser.depth }" />
				<input type="hidden" name="groupKey" value="${groupKey}" />
				<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
				<input type="hidden" name="fileSize" id="fileSize" value="${boardmng.fileSize }" />
				<input type="hidden" name="fileNumber" id="fileNumber" value="${boardmng.fileNumber }" />
				<input type="hidden" name="fileFlag" id="fileFlag" value="${boardmng.fileFlag }" />
				
				<!-- 신규 파일 삭제 필요 -->
				<input type="hidden" name="fileKey" id="fileKey" value="" />
				<input type="hidden" name="smartStr" id="smartStr" value="${boardUser.smartStr }"/>
				
				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
					<tr>
						<th width="140px" scope="row">제목 ${board.boardKey}</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box"  value="${boardUser.subject}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td width="200px">
							<input type="text" id="id" name="id" value="${user.id}" size="20" maxlength="20" class="box" value="${boardUser.id}" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
						<th width="100px" scope="row">성명</th>
						<td>
							<input type="text" id="name" name="name" value="${user.name }" size="20" maxlength="50" class="box" value="${boardUser.name}" <c:if test="${boardmng.writePermit != 'E'}">readonly</c:if> />
						</td>
					</tr>
					<tr>
						<th scope="row">비밀번호</th>
						<td>
							<input type="password" id="password" name="password" maxlength="10" size="20" class="box" value="${boardUser.password}"/>
						</td>
						<th scope="row">E-mail</th>
						<td>
							<input type="text" id="email" name="email" maxlength="100" size="20" class="box" value="${boardUser.email}" />
						</td>
					</tr>
					<c:if test="${boardmng.secretFlag == 'T'}">
					<tr>
						<th scope="row">비공개</th>
						<td colspan="3">
							<input type="radio" id="share" name="share" value="F" border="0" <c:if test="${boardUser.share == 'F' }">checked</c:if>/>설정
							<input type="radio" id="share" name="share" value="T" border="0" <c:if test="${boardUser.share == 'T' }">checked</c:if> />해제
						</td>
					</tr>
					</c:if>
					<c:if test="${boardmng.fileFlag == 'T' }">
					<c:set var="fileKeyNm" value=""/>
					<c:forEach items="${fileList}" var="fileList" varStatus="i">	
						<tr id="${fileList.fileKey}">
							<th class="line_right" scope="row">이전파일<c:out value="${i.index + 1}" /></th>
							<td class="td_view" colspan="3">
								<font color="red">${fileList.filename}</font>
								<a href="#none;" onClick="fileKeyDelete('${fileList.fileKey}', '${i.index}');">
								<img src="/images_main/btn_del_s.gif" align="absmiddle"> </a>
							</td>
						</tr>
					</c:forEach>
					<c:forEach begin="0" end="${boardmng.fileNumber-1}" varStatus="j">
						<tr>
							<th class="line_right" scope="row">첨부파일${j.index+1}</th>
						 	<td class="td_view" colspan="3">
						 		<c:set var="fileKeyNm" value="0"/>
						 		<c:forEach items="${fileList}" var="fileList" varStatus="i">	
									<c:if test="${j.index == i.index }">
										<c:set var="fileKeyNm" value="${fileList.fileKey}"/>	
									</c:if>
								</c:forEach>
							 	<input type='file' name='filename_${fileKeyNm}_${j.index}' id='filename_${fileKeyNm}_${j.index}' maxLength='100' size='45' class='' onChange="fileExtCheck(this,'${j.index}','filename_${fileKeyNm}_${j.index}');"/>(용량 : ${boardmng.fileSize } MB)
						 	</td>
						</tr>
					</c:forEach>
					</c:if>
					<tr>
						<td colspan="4" align="center" scope="row"><textarea id="content" name="content" cols="100%" rows="15" style="font-size: 13px;">${boardUser.content }</textarea></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="4" align="center" scope="row"> -->
<!-- 							<table width="100%" cellspacing="0" cellpadding="0" border="0" class="captcha"> -->
<!-- 								<tr valign="top"> -->
<!-- 									<td width="150" valign="top"> -->
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
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr height="35">
					<td align="right">						
						<input type="image" src="/images_main/v_ok.gif" onClick="javascript:fn_update();" /> <!--확인 btn-->
<!-- 						<a href="javascript:insert_reset();"><img src="/images_main/v_cancle.gif" border="0" /></a>  -->
						<a href="#" onclick="history.go(-1); return false ;"><img src="/images_main/v_cancle.gif" border="0" /></a> <!--취소 btn-->
						<a href="<c:url value='/webMenu.do'/>?key=${key}&pageGb=r&groupKey=${groupKey}"><img src="/images_main/v_list.gif" border="0" /></a> <!--목록 btn-->
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

	function insertIMG(irid, filename, videoFlag, smartKey) {
		 var sHTML = "<img src='" + filename + "' border='0'  style='width:300; height:300;'>";
		oEditors.getById[irid].exec("PASTE_HTML", [ sHTML ]);
		
		if(document.frm.smartStr.value == ""){
			document.frm.smartStr.value = smartKey;
		}else{
			document.frm.smartStr.value +=","+smartKey;
		}
	}
</script> 


<!-- 보드 내용 끝 -->

			
	