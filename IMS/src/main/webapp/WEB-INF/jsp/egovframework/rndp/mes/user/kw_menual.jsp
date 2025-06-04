<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>사용자 메뉴얼 관리</title>
	
	
</head>
<body>
	<script>
		function saveFile(){
			if($('#insertedFile').val() == 0){
				alert("업로드할 파일이 등록이 되지 않았습니다");
				return false;
			}
			if(confirm("저장하시겠습니까?")){
				document.frm.submit();
			}
		}
		
		function fileTest(file){
			var maxSize = 8 * 1024 * 1024;
			var fileSize = 0;
			var fileName = " ";

			fileSize = file.files[0].size;
			fileName = file.files[0].name;
			
			console.log(fileSize);
			console.log(fileName);
			
			$('#orignName').val(file.files[0].name);
			$('#fileSize').val(file.files[0].size);
			$('#insertedFile').val(1);
			
		}
		
		//파일다운
		function fn_egov_downFile(atchFileId){
			console.log(atchFileId);
			if(confirm("파일을 다운로드 하시겠습니까?")){
				window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn=0'/>");
			}
		}
		function filesize(size){
			var fs = size / 1024
			if(fs.tofixed(0) > 1024){
				fs = fs / 1024;
			}
			return fs.tofixed(1);
		}
	</script>
	<form name="frm" id="frm" enctype="multipart/form-data" method="post" action="/mes/user/kw_menual_u.do">
		<input type="hidden" name="atchFileId" id="atchFileId"/>
		<input type="hidden" name="orignName" id="orignName"/>
		<input type="hidden" name="fileSize" id="fileSize"/>
		<input type="hidden" name="kStaffName" id="kStaffName"/>
		<input type="hidden" name="insertedFile" id="insertedFile" value=0 />
		<input type="hidden" name="fs" id="fs" value="${menual.fileSize}"/>
		<div class="content_up">
			<div class="content_tit">
				<h2>사용자메뉴얼 관리</h2>
			</div>
		</div>
		
		<div class="tbl_top">
			<ul class="tbl_top_right">
				<li>
					<a class="mes_btn" onclick="saveFile();">저장</a>
				</li>
			</ul>
		</div>
		
		<div class="tbl_list">          	
		<table>
			<thead>  
	          	<tr>
	             	<th colspan="4">메뉴얼 파일 정보</th>
	         	</tr>
	         	<tr>
	       			<th style="width:50%;">파일명</th>
	       			<th style="width:10%;">파일크기</th>
	         		<th style="width:20%;">등록일자</th>
	         		<th style="width:20%;">등록자</th>
	         	</tr>
	         </thead>
	         <tbody>
	         	<tr>
	         		<td>
	         			<a href="javascript:fn_egov_downFile('${menual.atchFileId}')">${menual.orignName}</a>
	         		<td>
	         			 ${menual.fileSize} Byte
	         		</td>
	         		<td>
	         			${menual.uploadDate}
	         		</td>
	         		<td>
	         			${menual.kStaffName}
	         		</td>
	         	</tr>
	         </tbody>
	         
		</table> 
		<table>
			<thead>  
	          	<tr>
	             	<th>메뉴얼 파일 수정</th>
	         		<td><input type='file' id='filename' name='filename' onChange="fileTest(this.form.filename)" style='width:300px' <%-- value="${file.atchFileId}" --%>/></td>
	         	</tr>
	         </thead>
		</table> 
	</div>	
	</form>
</body>
</html>