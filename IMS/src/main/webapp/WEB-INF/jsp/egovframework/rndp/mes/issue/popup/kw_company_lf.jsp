<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<script type="text/javascript">

function selectCompany(idx) {
	
	var obj = {
		sComKey 			:  $("#sComKey_"+idx).val(),
		sComName 			:  $("#sComName_"+idx).val(),
		
		//은행정보
		sComBank 			:  $("#sComBank_"+idx).val(),
		sComAccountName 	:  $("#sComAccountName_"+idx).val(),
		sComAccountNum 		:  $("#sComAccountNum_"+idx).val(),
		
		//배송지주소
		sComDeliveryKey 	:  $("#sComDeliveryKey_"+idx).val(),
		sComDeliveryPost	:  $("#sComDeliveryPost_"+idx).val(),
		sComDeliveryAddress	:  $("#sComDeliveryAddress_"+idx).val(),
		sComDeliveryAddressDetail	:  $("#sComDeliveryAddressDetail_"+idx).val(),
		
		//대표주소
		sComGubunTxt 		:  $("#sComGubunTxt_"+idx).val(),
		sComPost 			:  $("#sComPost_"+idx).val(),
		sComAddress 		:  $("#sComAddress_"+idx).val(),
		sComAddressDetail 	:  $("#sComAddressDetail_"+idx).val(),
		
		//담당자정보
		sComConName 		:  $("#sComConName_"+idx).val(),
		sComConPhone 		:  $("#sComConPhone_"+idx).val(),
		sComConDep 			:  $("#sComConDep_"+idx).val()
	};
	
	if(typeof(opener.setComPop) != "undefined"){
		window.opener.setComPop(obj);
		window.close();
	}
}

// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/suju/popup/kw_company_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/suju/popup/kw_company_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSujuVO.pageIndex}">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>거래처 조회</h3>
			</div>
			<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
	<div class="popup_content">		
		<div id="itemCateZone" class="tbl_top">
			<ul class="tbl_top_left">
				<li>
	          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1);">
	              		<option value="10" <c:if test="${mesSujuVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
	              		<option value="20" <c:if test="${mesSujuVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
	              		<option value="50" <c:if test="${mesSujuVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
	              		<option value="100" <c:if test="${mesSujuVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
       				</select>					
				</li>
				<li>
					<select name="searchType" class="select_search" id="searchType">
						<option value="1" <c:if test="${mesSujuVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
						<option value="2" <c:if test="${mesSujuVO.searchType eq 2}">selected="selected"</c:if>>거래처코드</option>
						<option value="3" <c:if test="${mesSujuVO.searchType eq 3}">selected="selected"</c:if>>거래처명</option>
					</select>
				</li>
				<li>
					<input type="text" id="searchWord" name="searchWord" class="input_search" value="${mesSujuVO.searchWord}" onKeydown="if(event.keyCode == 13){fn_guestList(1);}"/>
				</li>
				<li>
					<a onclick="fn_guestList(1);" style="cursor: pointer;">
		    			검색
		     		</a>
				</li>
			</ul>
		</div>
		
		<div class="lf_tbl_list" id="pop_result_list">
			<table>
		        <thead>
					<tr>
			            <th style="width: 5%;">순번</th>
			            <th style="width: 8%;">구분</th>
			            <th style="width: 10%;">거래처코드</th>
			            <th style="width: 12%;">거래처명</th>
			            <th style="width: 10%;">사업자번호</th>
			            <th style="width: 10%;">TEL</th>
			            <th style="width: 10%;">FAX</th>
			            <th style="width: 10%;">담당자명</th>
			            <th style="width: 10%;">연락처</th>
			            <th style="width: 10%;">부서</th>
			           <!--  <th style="width: 10%;">우편번호</th>
			            <th style="width: 10%;">배송지주소</th>
			            <th style="width: 10%;">상세주소</th> -->
					</tr>
		        </thead>
		        <tbody>
					<c:forEach var="companyList" items="${companyList}" varStatus="i">
						<tr style="cursor: pointer;" onclick="selectCompany('${i.index}');">
							<td>
								${paginationInfo.totalRecordCount - (mesSujuVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
								
								<input type="hidden" id="sComKey_${i.index}" name="sComKey" value="${companyList.sComKey}" />
								<input type="hidden" id="sComName_${i.index}" name="sComName" value="${companyList.sComName}" />
								<input type="hidden" id="sComBank_${i.index}" name="sComBank" value="${companyList.sComBank}" />
								<input type="hidden" id="sComAccountName_${i.index}" name="sComAccountName" value="${companyList.sComAccountName}" />
								<input type="hidden" id="sComAccountNum_${i.index}" name="sComName" value="${companyList.sComAccountNum}" />
								
								<input type="hidden" id="sComDeliveryKey_${i.index}" name="sComDeliveryKey" value="${companyList.sComDeliveryKey}" />
								<input type="hidden" id="sComDeliveryPost_${i.index}" name="sComDeliveryPost" value="${companyList.sComDeliveryPost}" />
								<input type="hidden" id="sComDeliveryAddress_${i.index}" name="sComDeliveryAddress" value="${companyList.sComDeliveryAddress}" />
								<input type="hidden" id="sComDeliveryAddressDetail_${i.index}" name="sComDeliveryAddressDetail" value="${companyList.sComDeliveryAddressDetail}" />
								
								<input type="hidden" id="sComGubunTxt_${i.index}" name="sComGubunTxt" value="${companyList.sComGubunTxt}" />
								<input type="hidden" id="sComPost_${i.index}" name="sComPost" value="${companyList.sComPost}" />
								<input type="hidden" id="sComAddress_${i.index}" name="sComAddress" value="${companyList.sComAddress}" />
								<input type="hidden" id="sComAddressDetail_${i.index}" name="sComAddressDetail" value="${companyList.sComAddressDetail}" />
								
								<input type="hidden" id="sComConName_${i.index}" name="sComConName" value="${companyList.sComConName}" />
								<input type="hidden" id="sComConPhone_${i.index}" name="sComConPhone" value="${companyList.sComConPhone}" />
								<input type="hidden" id="sComConDep_${i.index}" name="sComConDep" value="${companyList.sComConDep}" />
								
							</td>
							<td>
								<c:out value="${companyList.sComGubunTxt}"/>
							</td>
				            <td>
								<c:out value="${companyList.sComCode}"/>
							</td>
							<td>
								<c:out value="${companyList.sComName}"/>
							</td>
							<td>
								<c:out value="${companyList.sComCrn}"/>
							</td>
							<td>
								<c:out value="${companyList.sComPhone}"/>
							</td>
							<td>
								<c:out value="${companyList.sComFax}"/>
							</td>
							<td>
								<c:out value="${companyList.sComConName}"/>
							</td>
							<td>
								<c:out value="${companyList.sComConPhone}"/>
							</td>
							<td>
								<c:out value="${companyList.sComConDep}"/>
							</td>
							<%-- <td>
								<c:out value="${companyList.sComDeliveryPost}"/>
							</td>
							<td>
								<c:out value="${companyList.sComDeliveryAddress}"/>
							</td>
							<td>
								<c:out value="${companyList.sComDeliveryAddressDetail}"/>
							</td> --%>
						</tr>
					</c:forEach>
					<c:if test="${empty companyList}">
						<tr>
							<td colspan="20">조회 정보가 없습니다.</td>
						</tr>
					</c:if>
		        </tbody>
			</table>
		</div>		
		
		<div class="page">	
			<span>
		  		<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
			</span>
		</div>	
	</div>
</form>

