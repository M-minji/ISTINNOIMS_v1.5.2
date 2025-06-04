<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" src='<c:url value="/js/km_common.js"/>'></script>
<link rel="stylesheet" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" type="text/css" /> 
<script src="/js/jquery/jquery-3.7.1.min.js"></script> 
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){	
		$('#startDate').datepicker({
			 changeMonth: true,
			 changeYear: true,
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] });	
		$('#endDate').datepicker({
			 changeMonth: true, 
			 changeYear: true,
	         dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
	         dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
	         monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] });
		$('#startDate').val(nowDate());
		$('#endDate').val(nowDate());
		
	}); 
	
	function insert_go(){
		var recruitfrm = document.writeform;
		if (recruitfrm.recruit1.value == ""){
			alert("채용분야를  입력하세요.");
			recruitfrm.recruit1.focus();
			return;
		}

		if (recruitfrm.recruit2.value == ""){
			alert("지원자격를  입력하세요.");
			recruitfrm.recruit2.focus();
			return;
		}

		if (recruitfrm.recruit3.value == ""){
			alert("제출서류를  입력하세요.");
			recruitfrm.recruit3.focus();
			return;
		}

		if (recruitfrm.handlingInfo.value == ""){
			alert("채용 담당자 를  입력하세요.");
			recruitfrm.handlingInfo.focus();
			return;
		}

		if (recruitfrm.eveyFlag.checked == true){
			recruitfrm.eveyFlag.value="T";
		}else{
			recruitfrm.eveyFlag.value="F";
		}
		
		if (confirm("저장 하시겠습니까?")){
			recruitfrm.submit();
		}

	}
	
	function insert_reset(){
		document.writeform.reset();
		document.writeform.recruit1.focus();
	}
</script>

</head>


<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>채용정보추가</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;채용정보&nbsp;>&nbsp;채용정보추가</p>
		</div>
		</div>
		<form name="writeform" method="post" action='<c:url value="/admin/recruitInsert.do"/>' enctype="multipart/form-data">
			<TABLE class="tbl_view">
				<THEAD>
					<TR>
						<TH>채용분야</th>
						<TD><input type="text" name="recruit1" maxLength="1000" size="55"></td>
					</tr>
					<TR>
						<TH>지원자격</th>
						<TD><textarea name="recruit2" cols="53" rows="5" maxlength="1000"></textarea></td>
					</tr>
					<TR>
						<TH>제출서류</th>
						<TD><textarea name="recruit3" cols="53" rows="5" maxlength="1000"></textarea></td>
					</tr>
				</THEAD>
				<tbody>
					<TR>
						<TH>제출기간</th>
						<TD>
						<input type="text" id="startDate" name="startDate" size="10" readonly /> ~ 
						<input type="text" id="endDate" name="endDate" size="10" readonly /> 
						<input type="checkbox" name="eveyFlag" value="T" /> 상시채용
						</td>
					</tr>
					<tr>
						<TH>채용담당자</th>
						<TD><input type="text" name="handlingInfo" maxLength="1000" size="55"></td>
					</tr>
				<c:set var="compPost1" value=""/>	<!-- 변수 초기화 -->
				<c:set var="compAddr1" value=""/>	<!-- 변수 초기화 -->
				<c:forEach var="list" items="${envList}">
					<c:if test="${list.name == 'companyEmail' }">
						<tr>
							<TH>제출처 E-mail</th>
							<TD>
							<input type="text" name="comEmail" maxLength="100" size="55" value="${list.value }">
							</td>
						</tr>
					</c:if>
					<c:if test="${list.name == 'companyTel' }">
						<tr>
							<TH>제출처 Tel</th>
							<TD>
							<input type="text" name="comTel" maxLength="100" size="55" value="${list.value }">
							</td>
						</tr>
					</c:if>
					<c:if test="${list.name == 'companyFax' }">
						<tr>
							<TH>제출처 Fax</th>
							<TD>
							<input type="text" name="comFax" maxLength="10" size="55" value="${list.value }">
							</td>
						</tr>
					</c:if>
				<c:if test="${list.name == 'companyPost' }">
					<c:set var="compPost1" value="${list.value }" />
				</c:if>
				<c:if test="${list.name == 'companyAddr' }">
					<c:set var="compAddr1" value="${list.value }" />
				</c:if>
				<c:if test='${ (compPost1 ne "") && (compAddr1 ne "" ) }'>	
						<tr>
							<TH>제출처 주소</th>
							<TD>
							<input type="text" name="comAddr" maxLength="500"size="55" value="[${compPost1 }]&nbsp;${compAddr1}">
							</td>																					
						</tr>
						<c:set var="compPost1" value="" />
						<c:set var="compAddr1" value="" />
				</c:if>
				</c:forEach>
					<tr>
						<TH>입사지원서양식</th>
						<TD><input type="file" name="fileName1" size="50"></td>
					</tr>
				</tbody>
			</table>
		  <div class="tbl_btn_right">
			<ul>
				<li>
					<a href="javascript:insert_go();"> 
					저장</a>
				</li>	
				<li><a href="javascript:insert_reset();">
					취소</a>
				</li>
				<li>
				<a href='<c:url value="/admin/envRecruit.do"/>'>
					목록</a>	
				</li>
			</ul>
		</div>
		</form>
	</div>
</body>
</html>
