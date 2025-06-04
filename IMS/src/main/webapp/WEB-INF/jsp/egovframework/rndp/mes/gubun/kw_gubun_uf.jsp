<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function update_go(){
		if(chkIns()){
			if(confirm("저장하시겠습니까?")){
				$("#mloader").show();
				document.frm.submit();	
			}
		}
	}

// validation
function chkIns(){
	if ($("#sGubunCateKey").val() == ""){
		alert("구분을 선택하세요.");
		$("#sGubunCateKey").focus();
		return false;
	}
	
	if ($("#sGubunName").val() == ""){
		alert("구분명을 입력하세요.");
		$("#sGubunName").focus();
		return false;
	}
	return true;
}

// 삭제
function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		$("#mloader").show();
		document.frm.action = "/mes/gubun/kw_gubun_d.do";
		document.frm.submit();
	}
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/gubun/kw_gubun_lf.do";
	document.frm.submit(); 
}
</script>


<form id="frm" name="frm" method="post" action="/mes/gubun/kw_gubun_u.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesGubunVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesGubunVO.recordCountPerPage}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesGubunVO.searchWord}" />
	<input type="hidden" id="searchGubun" name="searchGubun" value="${mesGubunVO.searchGubun}" />

	<input type="hidden" id="sGubunKey" name="sGubunKey" value="${gubunInfo.sGubunKey}" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>코드관리 수정</h2>
		</div>
	</div>
	
	<div class="normal_table">
        <table>
	       	<thead>
	       		<tr>
	           		<th style="width: 200px"><span style="color: red">* </span>구분</th>
	           		<th><span style="color: red">* </span>구분명</th>
	           		<th>영문표기(약어)</th>
	       		</tr>
	       	</thead>
	       	<tbody>
	       		<tr>
					<td>
						<select id="sGubunCateKey" name="sGubunCateKey">
							<option value="" >미구분</option>
							<c:forEach var="gubunCateList" items="${gubunCateList}">
								<option value="${gubunCateList.sGubunCateKey}" <c:if test="${gubunCateList.sGubunCateKey eq gubunInfo.sGubunCateKey}">selected</c:if>>
									<c:out value="${gubunCateList.sGubunCateName}" />
								</option>
							</c:forEach>
						</select>
					</td>
	           		<td>
	           			<input type="text" id="sGubunName" name="sGubunName" style="width: 100%;" maxlength="20" value="${gubunInfo.sGubunName}" />
	           		</td>
	           		<td>
	           			<input type="text" id="sGubunEtc" name="sGubunEtc" style="width: 100%;" maxlength="20" value="${gubunInfo.sGubunEtc}" />
	           		</td>
	       		</tr>
	       	</tbody>
   		</table>
   	</div>
   	
   	<div class="bottom_btn">
   		<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
		<button type="button" class="form_btn active" onclick="update_go();">저장</button>
		</c:if>
		<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
		<button type="button" class="form_btn bg" onclick="delete_go();">삭제</button>
		</c:if>
		<button type="button" class="form_btn" onclick="cancel();">목록</button>
	</div>
</form>
