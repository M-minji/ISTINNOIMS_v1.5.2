<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>이미지 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){	
	var eGubunPage = "${envVO.eGubunPage}";
	var addFileId = "${atchFileId}";
	var atchFileName = "${atchFileName}";
	var comLogImgName = "${comLogImgName}";
	console.log(comLogImgName);
	if (eGubunPage == "N") {
		if(typeof(opener.fileAdd) != "undefined"){
			 window.opener.fileAdd(addFileId,atchFileName);						
		}
		opener.location.reload();
		window.close();
	}
});
function mesIMGregInsert() {
	//alert("테스트 이미지 alert!");
	if($("#eIMGregName").val() == ''){
		alert("이미지 파일을 선택하세요.");
		$("#eIMGregName").focus();
		return false;
	}
	
	document.frm.submit();
	
	
	//window.opener.location ="/admin/envEtcList.do?rootKey=31&menuKey=60&groupKey=1";
	
}
function fileExtCheck(obj){  
	$('#eIMGregName').val(obj.files[0].name);
}

</script>
<style>
.pop_head{
    height: 60px;border-bottom:2px solid #a3b2c1;
} 
#pop_head{
 	display: block;
}
.tit {
    color: #000;
    background-color: #e7f0f9;
   display: block;
    height: 100%;
        border-bottom: 2px solid #a3b2c1;
}
.tit>ul{    
	display: inline-block;
    width: 100%;
   }
.tit>ul>li:nth-child(1){
	width: 50%;
    float: left;
}   
.tit>ul>li:nth-child(1)>h3{padding-left: 0.5em;}
.tit>ul>li:nth-child(2){
    width: 50%;
    line-height: 4;
    height: 100%;
    float: right;
    text-align: right;
}   
.tit>ul>li:nth-child(2)>a>img{padding-right: 10px;}

.tbl_btn_right{ text-align: center;}



</style>

<form id="frm" name="frm" method="post"  enctype="multipart/form-data"  action="/admin/env/popup/mesIMGregAdd_i.do">
<input type="hidden" name='name' id='name' value='${EnvVO.name}'> 


	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<ul>
				 	<li><h3>이미지 등록 </h3></li>
				 	<li>
					 	<a href="javascript:self.close();">
							<img src="/images/admin/close.gif" width="22" height="21"/>
	 					</a> 
				 	</li>
				</ul>
				
			</div>
<!-- 			<a href="javascript:self.close();"> -->
<!-- 				<img src="/images/admin/close.gif" width="22" height="21"/> -->
<!-- 			</a> -->
		</div>
		<div id="itemCateZone" class="tbl_top"> 
			<ul class="tbl_top_left">
			</ul>
		</div>
	</div> 

	<div class="popup_content">
		<div class="lf_tbl_list" id="pop_result_list" align="center"> 
			<table>
			  <tbody>
					<tr>
		      			<td style="padding: 1em;">
		      				<input type="file" name="imagefile" onchange="fileExtCheck(this);"> 
		      				<input type="hidden" name='eIMGregName' id='eIMGregName' value=''>  
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
					<a style="cursor: pointer;" onclick="mesIMGregInsert();">
								등록
						</a>
				</li>
<!-- 				<li>		
					 <a href="javascript:self.close();">
			  					닫기
		  			</a>
				</li> -->
			</ul>
		</div>
	</div>
</form>