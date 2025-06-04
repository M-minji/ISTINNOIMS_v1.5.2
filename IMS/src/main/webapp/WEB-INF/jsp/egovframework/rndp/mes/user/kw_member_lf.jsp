<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script>
function memberInfo(key){
	document.listForm.kMemberKey.value = key;
	document.listForm.action = "/mes/kw_member_uf.do";
	document.listForm.submit();
}
function memberMenu(key){
	document.listForm.kMemberKey.value = key;
	document.listForm.action = "/mes/kw_memberMenu_vf.do";
	document.listForm.submit();
}
</script>



<form name="listForm" id="listForm">		
	<input type="hidden" name="kMemberKey" id="kMemberKey" />
	<input type="hidden" name="pageIndex" id="pageIndex" value ="1"/>
	
		<div class="content">
			<div class="content_tit">
				<h2>직원 관리</h2>
			</div>
		</div>
		
<!--	
			<div class="tbl_top">
 				<ul class="tbl_top_left">
					<li>
						<select name="searchType" class="select_search" id="searchType" style="width: 100px;">
							<option value="1">전체</option>
							<option value="2">입고번호</option>
							<option value="3">거래처</option>
							<option value="4">생산국가</option>
							<option value="5">브랜드</option>
						</select> 
					</li>
					<li>
						<input name="searchWord" type="text" class="searchWord"	id="searchWord" value="" />
					</li>
					<li>
						<span>입고일 </span>
						<input type="text" class='inp_color_bg_bg' name="topStartDate" id="topStartDate" value="" style="width: 70px;" readonly/>
						~
						<input type="text" class='inp_color_bg_bg' name="topEndDate" id="topEndDate" value="" style="width: 70px;" readonly /> 
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
			  		</li>
				</ul>  -->
<!-- 				<ul class="tbl_top_right"> -->
<!-- 					<li><a onclick="go_insert()">등록</a></li> -->
<!-- 				</ul> 
			</div>
		</div>
			-->
			
			
			<div class="lf_tbl_list">
				<table>
					<colgroup>
						<col width="7%" />
						<col width="*" />
						<col width="*" />
						<col width="*" />
						<col width="*" />
						<col width="*" />
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>이름</th>
							<th>I D</th>
							<th>E-Mail</th>
							<th>휴대전화</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody><%-- onclick="memberInfo(${memberList.kMemberKey});" style="cursor:pointer;" --%>
						<c:forEach var="memberList" items="${memberList}" varStatus="i">
							<tr >
								<td>${i.index + 1}</td>
								<td>${memberList.kMemberName}</td>
								<td>${memberList.kMemberId}</td>
								<td>${memberList.kMemberEmail}</td>
								<td>${memberList.kMemberMobilie1} - ${memberList.kMemberMobilie2} - ${memberList.kMemberMobilie3}</td>
								<td>
									<a class="list_btn" onclick="memberMenu(${memberList.kMemberKey})">
										직원별메뉴
									</a>								
									<a class="list_btn" onclick="memberInfo(${memberList.kMemberKey})">
										수정
									</a>								
								</td>								
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${empty memberList}">
						<tr>
							<td colspan="6">직원목록이 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>
				
	
		<div class="tbl_paging">
			<span><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_guestList" /></span>
		</div>
	
</form>