<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function update_go(){
	
	if(confirm("저장하시겠습니까?")){
		$("#mloader").show();
		document.writeform.action = "/admin/envUpdate.do";
		document.writeform.submit();	
	}
}

var btnGubun = "";
function mesIMGreg(gubun) { btnGubun = gubun;
var filelength = document.getElementsByName("eAddFileId"+gubun).length;
	if(filelength > 0){
		if(confirm("이전 등록 파일은 삭제되며 복구할 수 없습니다")){
			fileDel("filename_"+gubun );
		}
	}
	var url = "/admin/env/popup/mesIMGregAdd.do";
	window.open(url ,"adminIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

//파일 첨부
var fileIndex = 0;
function fileAdd(AddFileId,atchFileName){
	
// 	mainLogImg

	$("#"+btnGubun).val(AddFileId);
	$("#"+btnGubun+"Name").val(atchFileName);

} 


function fileDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));	
}
</script>
<title>기본정보</title>
</head>
<body class="content_bg">

	<div id="contents">
		<div class="content_tit">
			<h2>기본정보</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;기본정보</p>
			</div>
		</div>
		<form name="writeform" id="envVO" method="post" action="<c:url value="/admin/envUpdate.do"/>"  enctype="multipart/form-data" >
			<TABLE class="tbl_view">
				<tbody>
					<tr>
						<TH>회사명</th>
						<TD>
						<input type="hidden" name="name" value="companyName"/>
						<input type="text" name="value" value="${companyName}" size="50">
						</td>
					</tr>
					<tr>
						<TH>대표자</th>
						<TD>
						<input type="hidden" name="name" value="companyCeo"/>
						<input type="text" name="value" value="${companyCeo}" size="50">
						</td>
					</tr>
					<tr>
						<TH>설립일</th>
						<TD>
						<input type="hidden" name="name" value="companyIncorpo"/>
						<input type="text" name="value" value="${companyIncorpo}" size="50">
						</td>
					</tr>
					<tr>
						<TH>우편번호</th>
						<TD>
						<input type="hidden" name="name" value="companyPost"/>
						<input type="text" name="value" value="${companyPost}" size="50">
						</td>
					</tr>
					<tr>
						<TH>주소</th>
						<TD>
						<input type="hidden" name="name" value="companyAddr"/>
						<input type="text" name="value" value="${companyAddr}" size="50">
						</td>
					</tr>
					<tr>
						<TH>대표전화</th>
						<TD>
						<input type="hidden" name="name" value="companyTel"/>
						<input type="text" name="value" value="${companyTel}" size="50">
						</td>
					</tr>
					<tr>
						<TH>팩스</th>
						<TD>
						<input type="hidden" name="name" value="companyFax"/>
						<input type="text" name="value" value="${companyFax}" size="50">
						</td>
					</tr>
				</tbody>
			</TABLE>
			 <div class="tbl_btn_right">
				<ul>
					<li>
						<a onclick="update_go();" style="cursor: pointer;">저장</a>
					</li>
					<li>
						<a href="javascript:document.writeform.reset();">취소</a>
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>