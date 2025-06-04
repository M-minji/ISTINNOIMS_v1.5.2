<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.submit();
}

// 최상위 등록
function insert_go(){
	if(chkIns()){
		if(confirm("저장하시겠습니까?")){
			$("#mloader").show();
			document.frm.action = "/mes/item/cate/kw_cate_i.do";
			document.frm.submit();
		}
	}
}

// validation
function chkIns(){
	if($("#sItemCateName").val() == ""){
		alert("품복분류명을 입력하세요.");
		$("#sItemCateName").focus();
		return false;
	}
	if($("#sItemCodeHead").val() == ""){
		alert("코드번호을 입력하세요.");
		$("#sItemCodeHead").focus();
		return false;
	}
	if($("#logoCode").val() == ""){
		alert("logoCode을 입력하세요.");
		$("#logoCode").focus();
		return false;
	}
	
	return true;
}

</script>


<form id="frm" name="frm" method="post" action="/mes/item/cate/kw_cate_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesCateVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesCateVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesCateVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesCateVO.searchWord}" />
	
	<input type="hidden" id="sItemCateUKey" name="sItemCateUKey" value="${cateInfo.sItemCateKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>품목분류관리 등록</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
  		<table>
	  		<tbody>
		  		<tr>
	   				<th>*품목분류경로</th>
	   				<td>
	   					<c:if test="${empty cateInfo}">
	   						최상위
	   					</c:if>
	   					<c:if test="${!empty cateInfo}">
	   						${cateInfo.sItemCatePath} >
	   					</c:if>
	   				</td>
	   			</tr>
				<tr>
	      			<th>*품목분류명</th>
	      			<td>
	      				<input style="width: 90%;" type="text" id="sItemCateName" name="sItemCateName" value="" />
	      			</td>
	   		 	</tr>
				<tr>
					<th>*코드번호</th>
					<td>
						<c:if test="${empty cateInfo}">
							<input style="width: 90%; text-transform: uppercase;" type="text" id="sItemCodeHead" name="sItemCodeHead" value=""  maxlength="2" />
						</c:if>
	   					<c:if test="${!empty cateInfo}">
	   						<input style="width: 90%; text-transform: uppercase;" type="text" id="sItemCodeHead" name="sItemCodeHead" value="${cateInfo.sItemCodeHead}"  maxlength="2" readonly="readonly" />
	   					</c:if>
					</td>
				</tr>
				<tr>
					<th>*LOGO CODE</th>
					<td>
						<input style="width: 90%; text-transform: uppercase;" type="text" id="logoCode" name="logoCode" value=""  maxlength="2" />
					</td>
				</tr>
				<tr>
	      			<th>비고</th>
	      			<td>
	      				<input style="width: 90%;" type="text" id="sItemCateEtc" name="sItemCateEtc" value="" />
	      			</td>
	    		</tr>
	  		</tbody>
		</table>
	</div>

	<div class="tbl_btn_right">
		<ul>
          	<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<li>
					<a onclick="insert_go();">저장</a> 
				</li>
			</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">
					목록
				</a>
			</li>
		</ul>
	</div>
</form>
