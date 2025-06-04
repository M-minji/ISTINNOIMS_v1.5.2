<%@ page contentType="text/html; charset=UTF-8"%>

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
$(document).ready(function() {

});
// 	jQuery.noConflict();
//      function submitImageUploadForm(){
//     	var ufNm = document.filefrm.updateImage.value;
//     	if (ufNm == "") {
//     	    alert('본문에 삽입할 이미지를 선택해주세요.');
//     	   return;
//     	}

//     	var filepoint = ufNm.substring(ufNm.lastIndexOf('.')+1, ufNm.length);
//     	var filetype = filepoint.toLowerCase();
//         jQuery.ajax({
//             url: "/smartImageUpload.do",
//             type: "POST",
//             dataType : "json", 
//             contentType: false,
//             processData: false,
//             data: function() {
//                 var data = new FormData();
//                 data.append("updateImage", jQuery("#updateImage").get(0).files[0]);
//                 data.append("oldFileNm", jQuery("#oldFileNm").val());
//                 return data;
//             }(),
//             success: function(msg) {
//                 var returnFileNm = msg.result.fileName;
//                 jQuery("#updateImage").val("");
//                 jQuery("#imimg").attr("src", "/smartImage/"+returnFileNm+"");
//                 jQuery("#oldFileNm").attr("value", returnFileNm);
//             },
//             error: function (e) { 
// 				alert('에러발생'); 
// 			}    
//         });
//     };


function submitImageUploadForm(){
	var ufNm = document.filefrm.updateImage.value;
	if (ufNm == "") {  
		alert('본문에 삽입할 이미지를 선택해주세요.');
		  return false;
	}
	var contentName = document.filefrm.contentName.value;
	if(contentName == null){
		contentName = 'content';
	}
	if(typeof(parent.parent.frm) != "undefined"){
		var typeFlag = parent.parent.frm.type.value;
		if(typeFlag == "I"){
			var imgFlag = "T";		
			if(document.filefrm.imgFlag.checked ==  true){
				imgFlag = "F";
			}	
			parent.parent.document.frm.imgFlag.value = imgFlag;	
		}
	}
		
	
	document.filefrm.action = "/smartImage.do?name=" + contentName;
	document.filefrm.submit();
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
// 	var typeFlag = parent.parent.frm.type.value;
// 	if(typeFlag == "I"){
// 		document.getElementById('tmp1').style.display='block';
// 	}else{
		document.getElementById('tmp').style.display='block';
// 	}  

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
<body><!--shop 시작페이지  -->
<div id="naver_common_editor">
  <form id="filefrm" name="filefrm" method="post" enctype="multipart/form-data">
  <input type="hidden" name="oldFileNm" id="oldFileNm" value="${filesaveName }"/>
  <div class="pic_content" style="border:0;">
    <p style="padding:5px;">
    <input type="file" name="updateImage" id="updateImage" style="width:222px;ime-mode:disabled" onkeydown="return false"></p>
    <p id="tmp1" style="padding-top:5px;text-align:center; display: none;">
    <input type="checkbox" name="imgFlag" id="imgFlag" >동영상 파일
    </p> 
    <input type="hidden" name="contentName" id="contentName" >
    <p id="tmp" style="padding-top:5px;text-align:center;  display: none;">
    	이미지 파일만 첨부 가능합니다.
    </p>
    <div style="text-align:center;padding-top:10px">
      <a href="javascript:submitImageUploadForm();"><img src="/se/img/btn_layer_confirm.gif" alt="확인" width="38" height="21" border="0"></a>
      <a href="javascript:closeWin()"><img src="/se/img/btn_layer_cancel.gif" alt="취소" width="38" height="21" border="0"></a>
    </div>
  </div>
  
  <div id="imageView" align="center"><img src='/se/img/default-img.png' id="imimg" width="180px" height="140px;"></div>
<!--   <div align='center' style="padding-top:10px;"><a href="javascript:fileAttach();"><img src="/se/img/btn_layer_confirm.gif" alt="확인" width="38" height="21" border="0"></a></div> -->
  </form>
</div>
</body>
</html>