<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
function checkFileType(filePath) {
    var fileFormat = filePath.split(".");
	if(fileFormat.indexOf("xls") > -1){
    	return true;
    }else if(fileFormat.indexOf("XLS") > -1){
    	return true;
    }else {
        return false;
    }
}

function upload_go() {
    var file = $("#uploadFile").val();
    if (file == "" || file == null) {
        alert("파일을 선택해주세요.");
        return false;
    } else if (!checkFileType(file)) {
        alert("엑셀 파일만 업로드 가능합니다.");
        return false;
    }

    if (confirm("업로드 하시겠습니까?")) {
	   	 document.frm.action="/mes/kw_bomUpload_i.do";
		 document.frm.submit();
		 $('#mloader').show();
    }
}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	
	<div class="content">
		<div class="content_tit">
			<h2>BOM Excel업로드</h2>
		</div>
	</div>
	
	<div class="tbl_write">
        <table>
	        <tbody>
	          	<tr>
					<th>업로드 파일</th>
					<td> 	
						<input type="file" name="uploadFile" id="uploadFile" />					
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="tbl_btn_right">
		<ul>
			<li>
				<a style="cursor: pointer;" onclick="upload_go()">
					등록
				</a>			
			</li>
			<li>
				<a style="cursor: pointer;" href="/mes/bom/kw_bom_lf.do">
					목록
				</a>				
			</li>
		</ul>
	</div>

</form>