<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.submit();
}

// 삭제
function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		$("#mloader").show();
		document.frm.action = "/mes/item/cate/kw_cate_d.do";
		document.frm.submit();
	}
}

// 저장
function go_update(){
	$("#mloader").show();
	document.frm.action = "/mes/item/cate/kw_cate_uf.do";
	document.frm.submit();
}

</script>


<form id="frm" name="frm" method="post" action="/mes/item/cate/kw_cate_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesCateVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesCateVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesCateVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesCateVO.searchWord}" />
	
	<input type="hidden" id="sItemCateKey" name="sItemCateKey" value="${cateInfo.sItemCateKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>품목분류관리 상세</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
  		<table>
	  		<tbody>
		  		<tr>
	   				<th>품목분류경로</th>
	   				<td>
	   					<c:out value="${cateInfo.sItemCatePath}" />
	   				</td>
	   			</tr>
				<tr>
	      			<th>품목분류명</th>
	      			<td>
	      				<c:out value="${cateInfo.sItemCateName}" />
	      			</td>
	   		 	</tr>
				<tr>
					<th>코드번호</th>
					<td>
						${cateInfo.sItemCodeHead}
					</td>
				</tr>
				<tr>
					<th>LOGO CODE</th>
					<td>
						${cateInfo.logoCode}
					</td>
				</tr>
				<tr>
	      			<th>비고</th>
	      			<td>
	      				<c:out value="${cateInfo.sItemCateEtc}" />
	      			</td>
	    		</tr>
	  		</tbody>
		</table>
	</div>

	<div class="tbl_btn_right">
		<ul>
			<!-- 자재관리, 제품관리에서 분류가 사용중인지 체크  -->
			<c:if test="${cateInfo.sItemUseYN eq 'Y'}">
	          	<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
					<li>
						<a style="cursor: pointer;" onclick="go_update();">수정</a>
		         	</li>
	         	</c:if>
	         	<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
		         	<li>
						<a style="cursor: pointer;" onclick="delete_go();">삭제</a>
		         	</li>
	         	</c:if>
         	</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>	
       		</li>
		</ul>
	</div>
</form>
