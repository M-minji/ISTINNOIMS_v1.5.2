<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script type="text/javascript" src="https://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function memberUp(){
	document.writeForm.action = "/mes/kw_memberPo_u.do";
	document.writeForm.submit();
}

</script>
<form name="writeForm" id="writeForm">		
	<input type="hidden" name="kPositionKey" id="kPositionKey" value="${vo.kPositionKey }" />
	
			<div class="content">
				<div class="content_tit">
					<h2>직급 관리</h2>
				</div>
			</div>
		
		
		
			<div class="tbl_write_f">
				<table>
					<tbody>
						<tr>
							<th>직급명</th>
							<td>
								<input type="text" name="kPositionName" id="kPositionName" value="${vo.kPositionName}"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:memberUp();">저장</a>
					</li>
					<li>
						<a href="/mes/kw_memberLevel_lf.do">목록</a>
					</li>
				</ul>
			</div>
		
</form>