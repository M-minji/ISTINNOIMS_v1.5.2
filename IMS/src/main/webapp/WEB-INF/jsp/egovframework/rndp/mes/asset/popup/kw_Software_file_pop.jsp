<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>File Add</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){	
	var eGubunPage = "${eGubunPage}";
	var eIMGregId = "${mesAssetVO.eDeviceImgId}";
	var eDeviceImgName = "${mesAssetVO.eDeviceImgName}";
	var eDeviceImgExtension = "${mesAssetVO.eDeviceImgExtension}";
	if (eGubunPage == "N") {
		if(typeof(opener.addFileInfoRow) != "undefined"){
			 window.opener.addFileInfoRow(eIMGregId,eDeviceImgName,eDeviceImgExtension);						
		}
		window.close();
	}
});

function mesIMGregInsert() {
	if($("#eDeviceImgName").val() == ''){
		alert("이미지 파일을 선택하세요.");
		$("#eDeviceImgName").focus();
		return false;
	}
	
	
	document.frm.submit();
}
 
function fileExtCheck(obj) {
    var file = obj.files[0];
    if (!file) {
        alert("파일을 선택해주세요.");
        return;
    }
    var fileCheckGubun = "${pageFileGubun}";
 	// 파일 용량 제한 (30MB)
    var maxFileSize = 30 * 1024 * 1024;

    // 파일 크기 확인
    if (file.size > maxFileSize) {
        alert('파일 용량은 30MB를 초과할 수 없습니다.');
        resetFileInput(obj);
        return;
    }
    
    
    // 허용되는 확장자 및 MIME 타입
    var allowedExtensions = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp','pdf'];
    var allowedMimeTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/bmp', 'image/webp', 'application/pdf'];
   
 	// fileCheckGubun 값이 "Y"일 때 추가적으로 허용되는 확장자 및 MIME 타입
    var officeExtensions = ['doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'hwp'];
    var officeMimeTypes = [
        'application/msword',                      // .doc
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', // .docx
        'application/vnd.ms-excel',               // .xls
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',       // .xlsx
        'application/vnd.ms-powerpoint',          // .ppt
        'application/vnd.openxmlformats-officedocument.presentationml.presentation', // .pptx
        'application/x-hwp','application/haansofthwp'                       // .hwp
    ];
    
    if (fileCheckGubun == "Y") {
        allowedExtensions = allowedExtensions.concat(officeExtensions);
        allowedMimeTypes = allowedMimeTypes.concat(officeMimeTypes);
    }
    
 	// 경고 메시지 동적 설정
    var uploadMessage = fileCheckGubun == "Y" 
        ? 'PDF, 이미지 파일(JPG, JPEG, PNG, GIF, BMP, WEBP) \n및 오피스 문서(DOC, DOCX, XLS, XLSX, PPT, PPTX, HWP)만 업로드 가능합니다.' 
        : 'PDF와 이미지 파일(JPG, JPEG, PNG, GIF, BMP, WEBP)만\n 업로드 가능합니다.';
    // 파일 확장자 확인
    var fileExt = file.name.split('.').pop().toLowerCase();
    if (allowedExtensions.indexOf(fileExt) === -1) {
    	alert(uploadMessage);
        resetFileInput(obj);
        return;
    }

    
    // 파일 확장자 확인
    var fileExt = file.name.split('.').pop().toLowerCase();
    if (allowedExtensions.indexOf(fileExt) === -1) {
    	alert('PDF와 이미지 파일(JPG, JPEG, PNG, GIF, BMP, WEBP)만 업로드 가능합니다.');
        resetFileInput(obj);
        return;
    }

    // MIME 타입 확인
    if (allowedMimeTypes.indexOf(file.type) === -1) {
        alert('허용되지 않은 파일 형식입니다.');
        resetFileInput(obj);
        return;
    }
    
 	// 파일 이름 길이 확인
    var baseName = file.name.substring(0, file.name.lastIndexOf('.'));
    if (baseName.length > 50) {
        alert('파일 이름은 50자 이내여야 합니다.');
        resetFileInput(obj);
        return;
    }

    // 특수 문자 확인 (정규 표현식)
    var specialCharPattern = /[^\sa-zA-Z0-9가-힣\-_()]/;
    if (specialCharPattern.test(baseName)) {
        alert('파일 이름에 특수 문자를 포함할 수 없습니다.');
        resetFileInput(obj);
        return;
    }

    // 바이너리 서명 확인 (EXE 헤더 검사)
    var reader = new FileReader();
    reader.onload = function (e) {
        var arrayBuffer = e.target.result;
        var uint8Array = new Uint8Array(arrayBuffer);
        var header = uint8Array.slice(0, 2).join(" ");
        if (header === "77 90") { // MZ 헤더 (EXE 파일)
            alert("실행 파일(EXE)은 업로드할 수 없습니다.");
            resetFileInput(obj);
            return;
        }

        // 파일 유효성 검증 성공
        $('#eDeviceImgName').val(file.name);
        $('#eDeviceImgExtension').val(fileExt);
    };
    
    reader.onerror = function () {
        alert("파일을 읽는 중 오류가 발생했습니다.");
        resetFileInput(obj);
    };
    
    reader.readAsArrayBuffer(file);
}

function resetFileInput(obj) {
    obj.value = ''; // 파일 입력 초기화
    $('#eDeviceImgName').val('');
    $('#eDeviceImgExtension').val('');
}




function fileExtCheckTwo(obj){ 
	var file = obj.files[0];
	 var fileExt = file.name.split('.').pop().toLowerCase();
	$('#eDeviceImgName').val(file.name);
	$('#eDeviceImgExtension').val(fileExt);
}
</script>


<form id="frm" name="frm" method="post"  enctype="multipart/form-data"  action="/mes/asset/popup/sw_file_insert.do" class="popup_wrap">
	<input type="hidden" name='kStaffKey' id='kStaffKey' value='${staffVO.kStaffKey }'>  
	<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>파일 등록 POP_${pageFileGubun }</h3>
			</div>
			<a href="javascript:self.close();">닫기</a>
		</div>
	</div> 
	<div class="popup_content">
		<div class="pop_con">
			<div class="file_box">
				<strong>*파일명50자 이내, 특수문자 불가<br><c:if test="${pageFileGubun  eq 'N'}">PDF와 이미지 파일(JPG, JPEG, PNG, GIF, BMP, WEBP)만 업로드 가능합니다.</c:if></strong>
				<div class="file_input"> 
      				<input type="file" id="fileInput" name="imagefile" onchange="fileExtCheck(this);"> 
      				<input type="text" name='eDeviceImgName' id='eDeviceImgName' value=''>  
      				<input type="hidden" name='eDeviceImgExtension' id='eDeviceImgExtension' value=''>  
      				<label for="fileInput" class="form_btn bg">파일 선택</label>
				</div>
			</div>
			<div class="bottom_btn">
				<button type="button" class="form_btn active" onclick="mesIMGregInsert();">등록</button>
				 <button type="button" class="form_btn"  onclick="javascript:self.close();">닫기</button>
			</div>
		</div>
	</div>
</form>