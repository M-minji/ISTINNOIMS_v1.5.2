<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="/jsp/kw_error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<script type="text/javascript">

var btnGubun = "";
function mesIMGreg(gubun) {
	btnGubun = gubun;
	var filelength = document.getElementsByName(comLogImgName).length;

	if(filelength > 0){
		if(confirm("이전 등록 파일은 삭제되며 복구할 수 없습니다")){
			fileDel("filename_"+gubun );
		}
	}
	var url = "/admin/env/popup/mesIMGregAdd.do?name="+gubun;
	window.open(url ,"adminIMGregAdd" ,"width=500,height=550,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

//파일 첨부
/*
var fileIndex = 0;
function fileAdd(AddFileId,atchFileName){
	$("#"+btnGubun).val(AddFileId);
	$("#"+btnGubun+"Name").val(atchFileName);
	
} 
*/


function fileDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));	
}


function update_go(){
	
	if(confirm("저장하시겠습니까?")){
		$("#mloader").show();
		document.writeform.action = "/admin/envEtcUpdate.do";
		document.writeform.submit();	
	}
}

function aaa(){
	$.ajax({
		type : "post",
		url : "/admin/env/mesIMGregDelete.do",
		data : { "name": "comLogImgName",
           		 "value": ""},
		dataType : "json",
		async : false,
		cache : false,
		success : function(msg){
			var bList = msg.result.list;
		}   
	});
}

function aaa2(){
	$.ajax({
		type : "post",
		url : "/admin/env/mesIMGregDelete.do",
		data : { "name": "mainLogImgName",
           		 "value": ""},
		dataType : "json",
		async : false,
		cache : false,
		success : function(msg){
			var bList = msg.result.list;
		}   
	});
}
</script>
<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>기타관리</h2>
			<div class="path">
				<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;기타관리</p>
			</div>
	</div>
		<form name="writeform" id="envVO" method="post" action="<c:url value="/admin/envEtcUpdate.do"/>" enctype="multipart/form-data">
			<TABLE class="tbl_view">
				<tbody>
				<c:forEach var="list" items="${envList}">
					<c:if test="${list.name == 'companyHomePage' }">
						<tr>
							<TH>홈페이지</th>
							<TD>
								<input type="hidden" name="strNum" value="1"/>
								<input type="hidden" name="name" value="${list.name}"/>
								<input type="text" name="value" value="${list.value }" size="50">
							</td>
						</tr>
					</c:if>
					<c:if test="${list.name == 'companyEmail'}">
						<tr>
							<TH>관리자 이메일</th>
							<TD>
								<input type="hidden" name="strNum" value="2"/>
								<input type="hidden" name="name" value="${list.name}"/>
								<input type="text" name="value" value="${list.value }" size="50">
							</td>
						</tr>
					</c:if>
					<c:if test="${list.name == 'companyText' }">
						<tr>
							<TH style="width:20%;">하단 텍스트</th>
							<TD>
								<input type="hidden" name="strNum" value="5"/>
								<input type="hidden" name="name" value="${list.name}"/>
								<textarea name="value" cols="48" rows="5">${list.value }</textarea>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				
				<tr style="align-items: center;">
						<th colspan="1">페이지 헤더 로고 <a onclick="mesIMGreg('comLogImgName');" class='mes_btn'>등록</a></th>
						<td colspan="1"> 
						 <input type="hidden" name="name" value="comLogImgName" id="comLogImgName"/>
						 <input type="hidden" name="value" value="${comLogImgName}" id="comLogImgName"/>
							 <c:if test="${!empty(comLogImgName)}">
								<img style="width:400px; height:300px;" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=${comLogImgName}&fileSn=0'  onerror="this.src='/images/images/innost_logo.png'" />
<!-- 							<a onclick="aaa()">X</a> -->
							</c:if>
							<c:if test="${empty(comLogImgName)}">
								파일 없음
							</c:if>
						</td>
						</tr>
						<tr>
						<th colspan="1">로그인 기업 로고 <a onclick="mesIMGreg('mainLogImgName');" class='mes_btn'>등록</a></th>
						<td colspan="1">
							 <input type="hidden" name="name" value="mainLogImg"/>
					 		 <input type="hidden" name="value" value="${mainLogImg}" id="mainLogImg">
					 		 <input type="hidden" name="name" value="mainLogImgName"/>
						 	<input type="hidden" name="value" value="${mainLogImgName}" id="mainLogImgName"/>
						    	<c:if test="${!empty(mainLogImgName)}">
						   			<img style="width:400px; height:300px;" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=${mainLogImgName}&fileSn=0'  onerror="this.src='/images/images/innost_logo.png'" />
<!-- 						   			<a onclick="aaa2()">X</a> -->
								</c:if>	
								<c:if test="${empty(mainLogImgName)}">
								파일 없음
							</c:if>
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
