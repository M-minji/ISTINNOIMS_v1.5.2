<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
function move_go()
{
	with (document.searchform)
	{
		if (selectRank.value == "")
		{
			alert("사업부문명을 선택하세요.");
			return;
		}
		submit();
	}
}

function insert_reset(){
	with (document.searchform){
		reset();
		selectRank.focus();
	}
}
</script>
</head>

<body>

<form name="searchform" method="post" action="/admin/intra/position/updateRank.do">
	<input type="hidden" name="kPositionKey" id="kPositionKey" value="<c:out value='${positionIntraVO.kPositionKey}'/>">
	<input type="hidden" name="kPositionRank" id="kPositionRank" value="<c:out value='${positionIntraVO.kPositionRank}'/>">
	<input type="hidden" name="kPositionName" id="kPositionName" value="<c:out value='${positionIntraVO.kPositionName}'/>">

		<div class="content">
			<div class="content_tit">
		  		<h2>부서변경</h2>	
		  </div>
	 	</div>
		
		<div class="detail_box">
			<h3>순서변경 요령</h3>
			<ul>
				<li>
					<img src="/images_admin/btn/blt.png" /> 이전 페이지에서 클릭한 부서가 현재
						페이지에서 선택한 부서의 자리로 순서가 변경됩니다.<BR>부서을 선택하시고 변경하기를
						클릭하세요.
				</li>
			</ul>
		</div>
		
		
		
		<div class="tbl_write_f">	
			<table>
				<tbody>
					<tr>
						<th>선택된 부서</th>
						<td><c:out value='${positionIntraVO.kPositionName}'/></td>
					</tr>
					<tr>
						<th>대상 부서</th>
						<td><select name="selectRank">
								<option value="" selected>부서 선택</option>
								<c:forEach var="item" items="${moveList}">
									<option value="<c:out value='${item.kPositionRank}' />">
										<c:out value="${item.kPositionName}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:move_go();">변경</a>
					</li>
					<li>	
						<a href="javascript:insert_reset();">다시</a>
					</li>
					<li>	
						<a href="/admin/intra/position/positionList.do">목록</a>
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>
