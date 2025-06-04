<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="<c:url value='/js/kw_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<script type="text/javascript">

$(document).ready(function(){	
	var width= '100%'; 
	$("#content").css('width',width); // 에디터 넓이 셋팅
});


function fn_insert() {

	var frm = document.frm;
	
	if (frm.kEdulistSubject.value == "") {
		alert("제목을 입력하세요.");
		frm.kEdulistSubject.focus();
		return false;
	}

	if (frm.kEdulistCompany.value == "") {
		alert("회사명을 입력하세요.");
		frm.kEdulistCompany.focus();
		return false;
	}

	if (frm.kEdulistName.value == "") {
		alert("담당자를 입력하세요.");
		frm.kEdulistName.focus();
		return false;
	}

	if (frm.kEdulistPart.value == "") {
		alert("부서를 입력하세요.");
		frm.kEdulistPart.focus();
		return false;
	}
	
	if (frm.kEdulistPart.value == "") {
		alert("부서를 입력하세요.");
		frm.kEdulistPart.focus();
		return false;
	}
	
	if (frm.kEdulistTel1.value == "") {
		alert("전화번호를 입력하세요.");
		frm.kEdulistTel1.focus();
		return false;
	}	
	if (frm.kEdulistTel2.value == "") {
		alert("전화번호를 입력하세요.");
		frm.kEdulistTel2.focus();
		return false;
	}
	if (frm.kEdulistTel3.value == "") {
		alert("전화번호를 입력하세요.");
		frm.kEdulistTel3.focus();
		return false;
	}	
	
	
	
	if (confirm("저장하시겠습니까?")) {
		frm.submit();
	}
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

<!-- 보드 내용시작 -->
<table style="width: 100%;">
	<tr>
		<td>
			<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="/jsp/homeEdu/kw_eduUsrList_i.do">

				
				<table class="board_view">
					<tr>
						<th class="boardtitle" width="100px" scope="row">제목</th>
						<td class="boardtext" colspan="3">
							<input type="text" id="kEdulistSubject" name="kEdulistSubject" class='chkro' value="2018년 스마트팩토리 구축 지원 상담 신청" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th class="boardtitle" width="100px" scope="row">회사명</th>
						<td class="boardtext" width="200px">
							<input type="text" id="kEdulistCompany" name="kEdulistCompany" size="20" maxlength="100" class="box" />
						</td>
						<th class="boardtitle" width="100px" scope="row">담당자</th>
						<td class="boardtext" width="200px">
							<input type="text" id="kEdulistName" name="kEdulistName" size="20" maxlength="50" class="box" />
						</td>
					</tr>
					<tr>
						<th class="boardtitle" scope="row">부서</th>
						<td class="boardtext" >
							<input type="text" id="kEdulistPart" name="kEdulistPart" size="20" maxlength="100" class="box" />
						</td>
						<th class="boardtitle" scope="row">직위</th>
						<td class="boardtext">
							<input type="text" id="kEdulistPosition" name="kEdulistPosition" size="20" maxlength="50" class="box" />
						</td>
					</tr>
					<tr>
						<th class="boardtitle" scope="row">담당자 전화</th>
						<td class="boardtext">
							<input type="text" id="kEdulistTel1" name="kEdulistTel1" maxlength="4" size="4" class="box" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" />-
							<input type="text" id="kEdulistTel2" name="kEdulistTel2" maxlength="4" size="4" class="box" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>-
							<input type="text" id="kEdulistTel3" name="kEdulistTel3" maxlength="4" size="4" class="box" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>
						</td>
						<th class="boardtitle" scope="row">E-mail</th>
						<td class="boardtext">
							<input type="text" id="kEdulistEmail1" name="kEdulistEmail1" maxlength="40" size="20" class="box" />@
							<input type="text" id="kEdulistEmail2" name="kEdulistEmail2" maxlength="30" size="10" class="box" />
						</td>
					</tr>
					<tr>
						<th class="boardtitle" scope="row">주소</th>
						<td class="boardtext" colspan="3">
							<input type="text" id="kEdulistAddr1" name="kEdulistAddr1" maxlength="100" size="40" class="box" />
							<input type="text" id="kEdulistAddr2" name="kEdulistAddr2" maxlength="50" size="10" class="box" />
						</td>
					</tr>					
					<tr>
						<td colspan="4" align="center" scope="row">
							<textarea id="kEdulistMemo" name="kEdulistMemo" cols="100%" rows="15" style="font-size: 13px;"></textarea>
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
				<table  class="okbtn">
					<tr height="35">
						<td align="center">
							<a class='erp_btn' style="cursor: pointer;" onClick="javascript:fn_insert();">저장</a>	
						</td>
					</tr>
				</table></td>
		</tr>
</table>


				