<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>이미지삽입</title>
<link rel="stylesheet" type="text/css" href="/se/css/editor.css" />
<link href="/se/css/default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
  body { margin:0; padding:0; }
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script language="javascript">


function submitImageUploadForm(){
	var contentName = document.filefrm.contentName.value;
	if(contentName == null){
		contentName = 'content';
	}
	var ufNm = document.filefrm.updateImage.value;
	if (ufNm == "") {
		alert('본문에 삽입할 이미지를 선택해주세요.');
	}
	document.filefrm.action = "/smartImage.do?name=" + contentName;
	document.filefrm.submit();
	  jQuery("#updateImage").val("");
	
}

function closeWin() {
	var contentName = document.filefrm.contentName.value;
	if(contentName == null){
		contentName = 'content';
	}
  parent.parent.oEditors.getById[contentName].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
  return false;
}


function fileAttach(){ 
	var contentName = document.filefrm.contentName.value;
	var fname = document.filefrm.oldFileNm.value;
	if(contentName == null){
		contentName = 'content';
	}
	parent.parent.insertIMG(contentName,fname);
	if(contentName == 'content2'){
 		parent.parent.oEditors2.getById[contentName].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
	}else if(contentName == 'content3'){
 		parent.parent.oEditors3.getById[contentName].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
	}else if(contentName == 'content4'){
 		parent.parent.oEditors4.getById[contentName].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
	}else{
 		parent.parent.oEditors.getById[contentName].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
	}
} 

$(document).ready(function(){	
	fileAttach();
	var params = location.search;
	var paramsId = params.split('&');
	var paramsIds = paramsId[0].split('=');
	var contentName = '';
	if(paramsIds[1] != null){
		contentName =paramsIds[1];
		$('#contentName').val(contentName);
	}
});
</script>
</head>
<body><!--shop 리턴페이지  -->
<div id="naver_common_editor">
  <form id="filefrm" name="filefrm" method="post" enctype="multipart/form-data">
  <input type="hidden" name="oldFileNm" id="oldFileNm" value="${filesaveName }"/>
  <div class="pic_content" style="border:0;"><input type="hidden" name="contentName" id="contentName" value="${contentName }" >
    <p style="padding:5px;"><input type="file" name="updateImage" id="updateImage" style="width:222px;ime-mode:disabled" onkeydown="return false"></p>
    <p style="padding-top:5px;text-align:center">이미지 파일만 등록 가능합니다.</p> 
    <div style="text-align:center;padding-top:10px">
      <a href="javascript:submitImageUploadForm();"><img src="/se/img/btn_layer_confirm.gif" alt="확인" width="38" height="21" border="0"></a>
      <a href="javascript:closeWin()"><img src="/se/img/btn_layer_cancel.gif" alt="취소" width="38" height="21" border="0"></a>
    </div>
  </div>
  <div id="imageView" align="center">
  	<c:if test="${filesaveName == ''}">
  		<img src='/se/img/default-img.png' id="imimg" width="180px" height="140px;">
  	</c:if>
  	<c:if test="${filesaveName != ''}">
  		<img src='/smartImage/${filesaveName}' id="imimg" width="180px" height="140px;">
  	</c:if>
  </div>
<!--   <div align='center' style="padding-top:10px;"><a href="javascript:fileAttach();"><img src="/se/img/btn_layer_confirm.gif" alt="확인" width="38" height="21" border="0"></a></div> -->
  </form>
</div>
</body>
</html>