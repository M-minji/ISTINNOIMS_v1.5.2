<%@ page contentType="text/html; charset=UTF-8" errorPage="/jsp/kw_error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>이미지 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/mes/common.css" />
<link rel="stylesheet" type="text/css" href="/css/mes/mes_board.css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mes/popup.css'/>" />
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/km_diary.js'/>"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	//인서트 후 닫기 
	function onloadSet() {
		var del = "${del}";
		//alert("del = " + del);
		if (del != "1") {
			writeform.subject.focus();
		} else {
			window.opener.location.reload();
			self.close();
		}
	}
</script>
</head>

<body onload="onloadSet()">

<form name="writeform" method="post" action="/mes/staff/kw_staff_i.do" enctype="multipart/form-data">
	<input type="hidden" name="kStaffKey" value="${kStaffvo.kStaffKey }">
	<input type="hidden" name="kParamImage" value="${kStaffvo.kParamImage }">
						
				
		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>이미지 등록</h3>
				</div>
				<a href="javascript:self.close();">
					<img src="/images/btn/close.gif" width="22" height="21" />
				</a>
			</div>
		</div>
		
		
		<div class="popup_content"  id="popup">
		
			<div class="layoutDiv tbl_write_f">
				<table>
					<tbody>	
						<tr>
							<th colspan="2">
								<img src="/images/btn/blt_orange.gif"  />
									<c:if test="${kStaffvo.kParamImage eq '1' }">
									직원 이미지를 등록 하실 수 있습니다.
									</c:if> 
									<c:if test="${kStaffvo.kParamImage eq '2' }">
									결재시 사용할 이미지를 등록 하실 수 있습니다.
									</c:if> 
							</th>
						</tr>
						<tr>
							<th>		
								<c:if test="${kStaffvo.kParamImage eq '1' }">
									<h4>직원 사진 </h4>
								</c:if> 
								<c:if test="${kStaffvo.kParamImage eq '2' }">
									<h5>결재이미지 </h5>
								</c:if> 
							</th>	
							<td>		
							    <input type="file" name="imagefile" />
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
		</div>

			
		<!--표-1 -->
		<div id="chart" style="margin-left: 20px"></div>
		<!--//표-1-->
		
		
		
		<div class="tbl_btn_right">
			<ul>
				<li>
					<a href="javascript:writeform.submit();">저장</a>
				</li>
				<li>	
					<a href="javascript:writeform.reset();">다시</a>
				</li>
				<li>	
					<a href="javascript:self.close()">닫기</a>
				</li>
			</ul>
		</div>

</form>

</body>
</html>
