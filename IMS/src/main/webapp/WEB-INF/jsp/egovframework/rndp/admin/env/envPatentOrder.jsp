<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="javascript" src='<c:url value="/js/km_env_patent.js"/>'></script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="content_tit">
			<h2>특허변경</h2>
		<div class="path">
			<p>HOME&nbsp;>&nbsp;기업정보관리&nbsp;>&nbsp;특허관리&nbsp;>&nbsp;특허변경</p>
		</div>
	</div>
		<div class="detail_box">
			<h3>순서변경 요령</h3>
			<ul>
				<li>
					<img src='<c:url value="/images/admin/blt.png"/>' /> 이전 페이지에서 클릭한 특허가 현재
					페이지에서 선택한 특허 자리로 순서가 변경됩니다.<BR> &nbsp;&nbsp;특허제목을 선택하시고 변경하기를
					클릭하세요.
				</li>
			</ul>
		</div>
		<form name="searchform" method="post" action='<c:url value="/admin/envPatentOrder.do"/>' >
			<input type="hidden" name="rank" value="${ktVO.rank }">
			<TABLE class="tbl_view">
				<TBODY>
					<TR>
						<TH>선택된 특허명</th>
						<TD>${ktVO.title }</td>		
					</tr>
					<TR>
						<TH>대상 특허명</th>
						<TD><select name="rank2">
								<option value="" selected>특허 선택</option>
								<c:forEach var="item" items="${rankOfList}">
									<option value="<c:out value='${item.rank}' />">
										<c:out value="${item.title}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
				</TBODY>
			</table>
			 <div class="tbl_btn_right">
				<ul>
					<li>
						<a href="<c:url value="javascript:move_go();"/>" >			
						수정</a>
					</li>
					<li>
						<a href="<c:url value="javascript:insert_reset();"/>" >
						취소</a>
					</li>
					<li>
						<a href='<c:url value="/admin/envPatentList.do"/>'>
						목록</a>
					</li>	
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
