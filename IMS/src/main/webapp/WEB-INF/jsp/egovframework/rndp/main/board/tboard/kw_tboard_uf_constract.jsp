<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"	src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script language="JavaScript">
	var msgChk = "";
		
	function fn_update(){
	
		var frm = document.frm;	

		if (frm.subject.value == "") {
			alert("제목을 입력하세요.");
			frm.subject.focus();
			return false;
		}

		if (frm.name.value == "") {
			alert("이름을 입력하세요.");
			frm.name.focus();
			return false;
		}
		
		if (frm.email.value == "") {
			alert("이메일을 입력하세요.");
			frm.email.focus();
			return false;
		}

		if (frm.comp_fax.value == "") {
			alert("휴대폰을 입력하세요.");
			frm.comp_fax.focus();
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

</script>
</head>

<!-- 보드 내용시작 -->
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="<c:url value='/jsp/boardUpdate.do'/>">
				<input type="hidden" name="menuKey" value="${key}" /> 
				<input type="hidden" name="mngKey" value="${boardmng.key }"/>
				<input type="hidden" name="boardKey" value="${boardUser.boardKey}" /> 
				<input type="hidden" name="depth" value="${boardUser.depth }" />
				<input type="hidden" name="groupKey" value="${groupKey}" />
				<input type="hidden" name="type" value="${boardmng.type }" /> <!-- 파일결로 구분을 위해서 추가 -->
				<input type="hidden" name="fileSize" id="fileSize" value="${boardmng.fileSize }" />
				<input type="hidden" name="fileNumber" id="fileNumber" value="${boardmng.fileNumber }" />
				<input type="hidden" name="fileFlag" id="fileFlag" value="${boardmng.fileFlag }" />

				<table cellspacing="0" cellpadding="0" width="100%" border="0" class="board_view">
					<tr>
						<th class="boardtitle2" width="140px" scope="row">* 제 목${board.boardKey}</th>
						<td colspan="3">
							<input type="text" id="subject" name="subject" maxlength="100" size="70" class="box"  value="${boardUser.subject}"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">* 성 명</th>
						<td colspan="3">
							<input type="text" id="name" name="name" maxlength="100" size="70" class="box"  value="${boardUser.name}"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">* 이메일</th>
						<td colspan="3">
							<input type="text" id="email" name="email" maxlength="100" size="70" class="box"  value="${boardUser.email}"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">전 화</th>
						<td colspan="3">
							<input type="text" id="comp_tel" name="comp_tel" maxlength="100" size="70" class="box"  value="${boardUser.comp_tel}"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">* 휴대폰</th>
						<td colspan="3">
							<input type="text" id="comp_fax" name="comp_fax" maxlength="100" size="70" class="box"  value="${boardUser.comp_fax}"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">내 용</th>
						<td colspan="3">
<%-- 							<input type="text" id="comp_fax" name="comp_fax" maxlength="100" size="70" class="box"  value="${boardUser.comp_fax}"/> --%>
							<textarea id="content" name="content" cols="70" rows="10" style="font-size: 13px;">${boardUser.content}</textarea>
						</td>
					</tr>
					<tr>
						<th class="boardtitle2" width="140px" scope="row">* 비밀번호</th>
						<td colspan="3">
							<input type="password" id="password" name="password" maxlength="100" size="70" class="box"  value=""/>
						</td>
					</tr>

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
					<td class="okbtn2" align="right">						
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
// 	var imagepath = "";
// 	var oEditors = [];
// 	nhn.husky.EZCreator.createInIFrame({
// 		oAppRef : oEditors,
// 		elPlaceHolder : 'content',
// 		sSkinURI : "<c:url value='/se/SEditorSkin.html'/>",
// 		fCreator : "createSEditorInIFrame"
// 	});

// 	function insertIMG(irid, filename, videoFlag, smartKey) {
// 		 var sHTML = "<img src='" + filename + "' border='0'  style='width:300; height:300;'>";
// 		oEditors.getById[irid].exec("PASTE_HTML", [ sHTML ]);
		
// 		if(document.frm.smartStr.value == ""){
// 			document.frm.smartStr.value = smartKey;
// 		}else{
// 			document.frm.smartStr.value +=","+smartKey;
// 		}
// 	}
</script> 


<!-- 보드 내용 끝 -->

			
	