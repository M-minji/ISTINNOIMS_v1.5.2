<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>도면 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){	
	var eGubunPage = "${mesBlueprintVO.eGubunPage}";
	
	if (eGubunPage == "N") {
		if(typeof(opener.fileAdd) != "undefined"){
			window.opener.fileAdd(addFileId,atchFileName);						
		}
		window.close();
	}
});
function mesIMGregInsert() {
	//alert("테스트 이미지 alert!");
	if($("#eIMGregName").val() == ""){
		alert("등록할 파일이 없습니다.\n파일을 추가하세요.");
		$("#eIMGregName").focus();
		return false;
	}
	

	var uploadFiles = [];
	

	var form = jQuery("ajaxFrom")[0];        
	var formData = new FormData(form);        
	formData.append("message", "ajax로 파일 전송하기");        
	formData.append("file", jQuery("#imagefile")[0].files[0]);

	document.getElementById("progess").style.display = "";
	document.getElementById("imagefile").style.display = "none";
	document.getElementsByClassName("tbl_btn_right")[0].style.display = "none";
	$.ajax({  
		url: '/mes/blueprint/popup/mesIMGregAddAjax.do'
	,	data : formData
	,	type : 'post'
    ,	dataType: 'JSON'
	,	contentType : false
	,	processData: false
	,	xhr: function(){  
			//XMLHttpRequest 재정의 가능   
			var xhr = $.ajaxSettings.xhr();    
			xhr.upload.onprogress = function(e){  
				//progress 이벤트 리스너 추가     
				var percent = e.loaded  * 100 / e.total;      
				console.log(percent);
				setProgress(percent);    
			};    
			return xhr;  
		},  success : function(data) {   
				console.log(data);	
				var addFileId = data.result.atchFileId;
				var atchFileName = data.result.result[0].orignlFileNm;
				if(typeof(opener.fileAdd) != "undefined"){
					window.opener.fileAdd(addFileId, atchFileName);						
				}
				window.close();
			}
	});
	
	
	// document.frm.submit();
}

function fileExtCheck(obj){  
	const maxFileSizeInBytes = 1500000000;
	if (obj.files && obj.files[0]) {
	  var file = obj.files[0];
	  
	//파일확장자 체크.
		var fileName = file.name;
		var fileExtension = fileName.split('.').pop();
		var allowedExtensions = ['jpg', 'jpeg', 'png', 'gif', 'text', 'txt', 'pdf', 'docx', 'xlsx', 'ppt', 'pptx', 'hwp', 'hwpx'];
		
	 /* if (allowedExtensions.indexOf(fileExtension.toLowerCase()) === -1) {
		    alert("허용되지 않는 확장자입니다. JPG, JPEG, PNG, GIF, TXT, PDF, DOCX, XLSX, XLS, PPTX, PPT, HWP, HWPX 파일만 업로드 가능합니다.");
		    obj.value = ''; // 파일 선택 초기화
		    return false;
	  } */
	 
	 
	  if (file.size > maxFileSizeInBytes) {
		  alert("파일 용량을 1.5GB 이하 등록 가능합니다.")
		  obj.form.reset();
		  $('#eIMGregName').val("");
		  $('#eIMGregSize').val("");
	  }else{
		   $('#eIMGregName').val(file.name);
		   $('#eIMGregSize').val(file.size);
	  }
	}
}


function setProgress(per){
	$("#progressBar").val(per);
	$("#progressStatus").text(Math.round(per * 100) / 100 + "%");
}
</script>


<form id="frm" name="frm" method="post"  enctype="multipart/form-data"  action="/mes/blueprint/popup/mesIMGregAdd_i.do">
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>첨부파일  추가</h3>
			</div>
			<a href="javascript:self.close();">
				<img src="/images/btn/close.gif" width="22" height="21"/>
			</a>
		</div>
		<div id="itemCateZone" class="tbl_top"> 
			<ul class="tbl_top_left">
				파일 용량을 1.5GB 이하 등록 가능합니다.
			</ul>
		</div>
	</div> 

	<div class="popup_content">
		<div class="lf_tbl_list" id="pop_result_list" align="center"> 
			<table>
			  <tbody>
					<tr>
		      			<td>
		      				<input type="file" id="imagefile" name="imagefile" onchange="fileExtCheck(this);"> 
		      				<input type="hidden" id="eIMGregName" name='eIMGregName' id='eIMGregName' value=''>  
		      				<input type="hidden" id="eIMGregSize" name='eIMGregSize' id='eIMGregSize' value=''>  
		      				
		      				
							<div id="progess" style="display:none;">
								<progress id="progressBar" value="0" max="100" style="width:100%"></progress>
								<span id="progressStatus">0%</span>
							</div>
		      			</td>
					</tr>
	    		</tbody>
			</table>
		</div>
		
		
		<div class="page">
		</div>
		<div class="tbl_btn_right">
			<ul>
				<li>
					<a style="cursor: pointer;" onclick="mesIMGregInsert();">등록	</a>
				</li>
				<li>		
					 <a href="javascript:self.close();">
	  					닫기
		  			</a>
				</li>
			</ul>
		</div>
	</div>
</form>