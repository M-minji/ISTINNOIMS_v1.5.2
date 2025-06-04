<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>   

<script>
	function go_insert(){
		document.listForm.action="/mes/kw_memberPo_if.do";
		document.listForm.submit();
	}
	function memberPoInfo(key){
		document.listForm.kPositionKey.value = key;
		document.listForm.action="/mes/kw_memberPo_uf.do";
		document.listForm.submit();		
	}
	function memberPoDel(key){
		if(confirm("삭제하시겠습니까?")){
			document.listForm.kPositionKey.value = key;
			document.listForm.action="/mes/kw_memberPo_d.do";
			document.listForm.submit();			
		}
	}
</script>


 

<form name="listForm" id="listForm">	
<input type="hidden" name="kPositionKey" id="kPositionKey" />	
	
		<div class="content_up">
			 <div class="content_tit">
				<h2>부서 관리</h2>
			</div> 
		</div>
		<div class="tbl_top">
			<ul class="tbl_top_right" >
				<li><a onclick="go_insert()">등록</a></li>
			</ul>
		</div>
			
			<div class="lf_tbl_list">
				<table>
					<colgroup>
						<col width="7%" />
						<col width="*" />
						<col width="*" />
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>부서명</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="poList" items="${poList}" varStatus="i">
							<tr>
								<td>${i.index + 1}</td>
								<td>${poList.kPositionName}</td>
								<td>
									<a class="list_btn" onclick="memberPoInfo(${poList.kPositionKey})">
										수정
									</a>										
									<a class="list_btn" onclick="memberPoDel(${poList.kPositionKey})">
										삭제
									</a>										
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${empty poList}">
						<tr>
							<td colspan="3">부서목록이 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>
			
	
		<div class="tbl_paging">
			<span><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_guestList" /></span>
		</div>
	
</form>