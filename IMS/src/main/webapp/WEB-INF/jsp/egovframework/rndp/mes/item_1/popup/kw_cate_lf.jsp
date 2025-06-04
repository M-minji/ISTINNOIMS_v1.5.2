<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">
// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/item/popup/kw_cate_lf.do";
	document.frm.submit();
}

// 자재분류 선택
function selectCate(idx){
	var sItemCateKey	= $("#sItemCateKey_"+idx).val();
	var sItemCateName	= $("#sItemCateName_"+idx).val();
	var sItemCatePath	= $("#sItemCatePath_"+idx).val();
	var middleCate	= $("#middleCate_"+idx).val();
	var logoCode	= $("#logoCode_"+idx).val();
	
	if(typeof(window.opener.setCatePop) != "undefined"){
		window.opener.setCatePop(sItemCateKey, sItemCateName, sItemCatePath , middleCate, logoCode);
		window.close();
	}
}
</script>

<form id="frm" name="frm" method="post" action="/mes/item/popup/kw_cate_lf.do">
	<input type="hidden" name="pageIndex" value="${mesSubVO.pageIndex}"/>
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>자재분류 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">	
		<div id="itemCateZone" class="tbl_top"> 
			<ul class="tbl_top_left">
				<li>
	          		<select id="recordCountPerPage" name="recordCountPerPage" class="select_recordCountPerPage" onchange="fn_guestList(1)">
	              		<option value="10" <c:if test="${mesSubVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesSubVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesSubVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesSubVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	      			</select>					
				</li>
				<li>
					<span>품목분류경로</span>
	        		<input type="text" class="input_search" id="searchWord" style="width:150px;" name="searchWord" value="${mesSubVO.searchWord}" maxlength="100" />
	        	</li>
				<li>
					<a onclick="fn_guestList(1)" style="cursor: pointer;" >
		     			검색
		     		</a>
				</li>
			</ul>
		</div>
		
		<div class="lf_tbl_list" id="pop_result_list" >
			<table>
		        <thead>
					<tr>
		      			<th style="width: 10%">순번</th>
		      			<th style="width: 30%">품목분류경로</th>
		      			<th style="width: 30%">품목분류명(중분류)</th>
		      			<th style="width: 10%">CODE</th>
		      			<th style="width: 10%">LOGO CODE</th>
	      				<th style="width: *">비고</th>
		    		</tr>
		        </thead>
		        <tbody>		    		
					<c:forEach var="cateList" items="${cateList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="selectCate('${i.index}');">
							<td>
								${paginationInfo.totalRecordCount - (mesSubVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
								
								<input type="hidden" id="sItemCateKey_${i.index}" name="sItemCateKey" value="${cateList.sItemCateKey}" />
								<input type="hidden" id="middleCate_${i.index}" name="middleCate" value="${cateList.middleCate}" />
								<input type="hidden" id="sItemCateName_${i.index}" name="sItemCateName" value="${cateList.sItemCateName}" />
								<input type="hidden" id="sItemCatePath_${i.index}" name="sItemCatePath" value="${cateList.sItemCatePath}" />
								<input type="hidden" id="logoCode_${i.index}" name="logoCode" value="${cateList.logoCode}" />
							</td>
							<td style="text-align: left;">
								<c:out value="${cateList.sItemCatePath}" />
							</td>
							<td>
								<c:out value="${cateList.sItemCateName}" />
							</td>
							<td>
								<c:out value="${cateList.middleCate}" />
							</td>
							<td>
								<c:out value="${cateList.logoCode}" />
							</td>
							<td>
								<c:out value="${cateList.sItemCateEtc}" />
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty cateList}">
						<tr>
							<td colspan="5">조회 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="page">	
		  	<span><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_guestList" /></span>
		</div>
	</div>
</form>
